package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.api.config.ScanFilter;
import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.FileResInfo;
import cn.sast.api.util.IMonitor;
import cn.sast.api.util.Timer;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.common.ResourceImplKt;
import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IProjectFileLocator;
import cn.sast.framework.result.IPreAnalysisResultCollector;
import cn.sast.framework.util.SafeAnalyzeUtil;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.cache.coroutines.FastCache;
import com.feysh.corax.commons.NullableLateinit;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.IAnalysisDepends;
import com.feysh.corax.config.api.ICheckPoint;
import com.feysh.corax.config.api.IClassCheckPoint;
import com.feysh.corax.config.api.IClassMatch;
import com.feysh.corax.config.api.IFieldCheckPoint;
import com.feysh.corax.config.api.IFieldMatch;
import com.feysh.corax.config.api.IInvokeCheckPoint;
import com.feysh.corax.config.api.IMethodCheckPoint;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.INodeWithRange;
import com.feysh.corax.config.api.IPreAnalysisClassConfig;
import com.feysh.corax.config.api.IPreAnalysisConfig;
import com.feysh.corax.config.api.IPreAnalysisFieldConfig;
import com.feysh.corax.config.api.IPreAnalysisFileConfig;
import com.feysh.corax.config.api.IPreAnalysisInvokeConfig;
import com.feysh.corax.config.api.IPreAnalysisMethodConfig;
import com.feysh.corax.config.api.ISourceFileCheckPoint;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.report.Region;
import com.feysh.corax.config.api.rules.ProcessRule;
import com.feysh.corax.config.api.utils.UtilsKt;
import com.github.javaparser.Position;
import com.github.javaparser.ast.nodeTypes.NodeWithRange;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.tagkit.Host;
import soot.util.MultiMap;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Î\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� Ü\u00012\u00020\u00012\u00020\u0002:\u0004Ü\u0001Ý\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010I\u001a\u00020JH\u0016J\u0012\u0010K\u001a\u00020L2\b\b\u0002\u0010M\u001a\u00020=H\u0002J\u0012\u0010N\u001a\u00020L2\b\b\u0002\u0010M\u001a\u00020=H\u0002J \u0010b\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d*\n\u0012\u0006\u0012\u0004\u0018\u0001Hd0cH\u0016J\u001a\u0010e\u001a\u0004\u0018\u00010f2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020_H\u0002Jm\u0010j\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020\u001d0l2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020s\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0002\u0010vJm\u0010w\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\f\u0010x\u001a\b\u0012\u0004\u0012\u0002000l2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020z\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0002\u0010vJm\u0010{\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\f\u0010|\u001a\b\u0012\u0004\u0012\u0002020l2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020~\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0002\u0010vJs\u0010\u007f\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u000f\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/2\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u0081\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0082\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0083\u0001J\u0011\u0010\u0084\u0001\u001a\u00020L2\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001J\u0089\u0001\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2$\u0010\u0088\u0001\u001a\u001f\b\u0001\u0012\u0011\u0012\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020!0\u0089\u00010t\u0012\u0006\u0012\u0004\u0018\u00010u0n2\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u008b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u008c\u0001Jj\u0010j\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020s\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u008f\u0001Ja\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020s\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0091\u0001Jj\u0010w\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020z\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0094\u0001Ja\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020z\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0091\u0001Jj\u0010{\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2'\u0010q\u001a#\b\u0001\u0012\u0004\u0012\u00020~\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0098\u0001JQ\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2\u0017\u0010q\u001a\u0013\u0012\u0004\u0012\u00020~\u0012\u0004\u0012\u0002Hd0n¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u009a\u0001Jl\u0010\u007f\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\b\u0010\u009b\u0001\u001a\u00030\u0093\u00012\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u0081\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0082\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0094\u0001Jc\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u0081\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0082\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u0091\u0001Jl\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\u0007\u0010\u009d\u0001\u001a\u00020[2\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u008b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010\u009e\u0001Jy\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\u0004\b��\u0010d2\t\u0010 \u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_2\u0018\u0010m\u001a\u0014\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bp2(\u0010q\u001a$\b\u0001\u0012\u0005\u0012\u00030\u008b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0r¢\u0006\u0002\bpH\u0016R\u00020h¢\u0006\u0003\u0010¢\u0001JA\u0010£\u0001\u001a\u00020J2\b\u0010¤\u0001\u001a\u00030¥\u00012\u0007\u0010¦\u0001\u001a\u00020[2\b\u0010§\u0001\u001a\u00030¨\u00012\u0019\u0010©\u0001\u001a\u0014\u0012\u0005\u0012\u00030ª\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bpH\u0016J8\u0010£\u0001\u001a\u00020J2\b\u0010¤\u0001\u001a\u00030¥\u00012\b\u0010«\u0001\u001a\u00030¬\u00012\u0019\u0010©\u0001\u001a\u0014\u0012\u0005\u0012\u00030ª\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bpH\u0016J@\u0010\u00ad\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u0001Hd0®\u0001\"\u0004\b��\u0010d2\u001c\u0010q\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0t\u0012\u0006\u0012\u0004\u0018\u00010u0nH\u0016R\u00020h¢\u0006\u0003\u0010¯\u0001JJ\u0010£\u0001\u001a\u00020J\"\u000f\b��\u0010°\u0001*\u00030±\u0001*\u00030²\u0001*\u0003H°\u00012\b\u0010¤\u0001\u001a\u00030¥\u00012\u0019\u0010©\u0001\u001a\u0014\u0012\u0005\u0012\u00030ª\u0001\u0012\u0004\u0012\u00020J0n¢\u0006\u0002\bpH\u0016¢\u0006\u0003\u0010³\u0001J\u001b\u0010´\u0001\u001a\u00020[2\u0007\u0010µ\u0001\u001a\u00020[2\u0007\u0010¶\u0001\u001a\u00020_H\u0016J!\u0010·\u0001\u001a\u0011\u0012\u0004\u0012\u00020[\u0012\u0004\u0012\u00020_\u0018\u00010¸\u00012\u0007\u0010¹\u0001\u001a\u00020[H\u0016J\u0019\u0010º\u0001\u001a\u00030»\u0001*\u00020[2\t\u0010¼\u0001\u001a\u0004\u0018\u00010[H\u0016J\u001a\u0010½\u0001\u001a\n\u0012\u0004\u0012\u00020[\u0018\u00010/2\u0007\u0010¾\u0001\u001a\u00020_H\u0016J-\u0010¿\u0001\u001a\t\u0012\u0004\u0012\u00020!0À\u00012\t\u0010 \u0001\u001a\u0004\u0018\u00010_2\t\u0010¡\u0001\u001a\u0004\u0018\u00010_H\u0086@¢\u0006\u0003\u0010Á\u0001J\u0018\u0010Â\u0001\u001a\u00030Ã\u0001*\u00030Ä\u00012\u0007\u0010¦\u0001\u001a\u00020[H\u0002J\u0018\u0010Â\u0001\u001a\u00030Ã\u0001*\u00030Ä\u00012\u0007\u0010Å\u0001\u001a\u00020\u001dH\u0002J\u0018\u0010Â\u0001\u001a\u00030Ã\u0001*\u00030Ä\u00012\u0007\u0010Æ\u0001\u001a\u000202H\u0002J\u0018\u0010Â\u0001\u001a\u00030Ã\u0001*\u00030Ä\u00012\u0007\u0010Ç\u0001\u001a\u000200H\u0002JB\u0010Â\u0001\u001a\u00030Ã\u0001*\u00030Ä\u00012\u000b\b\u0002\u0010É\u0001\u001a\u0004\u0018\u00010_2\u0013\u0010Ê\u0001\u001a\u000e\u0012\u0005\u0012\u00030Ë\u00010/j\u0003`Ì\u00012\u000f\u0010Í\u0001\u001a\n\u0012\u0005\u0012\u00030Ï\u00010Î\u0001H\u0002J\u001e\u0010Õ\u0001\u001a\t\u0012\u0005\u0012\u00030Ö\u00010,2\u000e\u0010\u007f\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/J\u0019\u0010×\u0001\u001a\u00020J*\u00030Ø\u00012\b\u0010Ù\u0001\u001a\u00030Ø\u0001H\u0096\u0005J%\u0010×\u0001\u001a\u00020J*\t\u0012\u0005\u0012\u00030Ø\u00010l2\u000e\u0010Ú\u0001\u001a\t\u0012\u0005\u0012\u00030Ø\u00010lH\u0096\u0005J\u0014\u0010Û\u0001\u001a\u00030Ø\u00012\u0007\u0010Í\u0001\u001a\u00020uH\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n��\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001c¢\u0006\b\n��\u001a\u0004\b\"\u0010\u001fR\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010+\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010\u001d0\u001d0,X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010.\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u000100000/X\u0082\u0004¢\u0006\u0002\n��R\u001c\u00101\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u000102020/X\u0082\u0004¢\u0006\u0002\n��R\u001c\u00103\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010\u001d0\u001d0/X\u0082\u0004¢\u0006\u0002\n��R\u001c\u00104\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u000100000/X\u0082\u0004¢\u0006\u0002\n��R\u001c\u00105\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u000102020/X\u0082\u0004¢\u0006\u0002\n��R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u0004¢\u0006\u0002\n��R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020@0?X\u0082\u0004¢\u0006\u0002\n��R+\u0010B\u001a\u00020@2\u0006\u0010A\u001a\u00020@8V@VX\u0096\u008e\u0002¢\u0006\u0012\u001a\u0004\bE\u0010F\"\u0004\bG\u0010H*\u0004\bC\u0010DR\u001b\u0010O\u001a\u00020L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bP\u0010QR\u001b\u0010T\u001a\u00020L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010S\u001a\u0004\bU\u0010QR\u0014\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u001cX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010Y\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010Z\u001a\u00020[8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0018\u0010^\u001a\u00020_*\u00020[8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u000f\u0010È\u0001\u001a\u00020_X\u0082\u0004¢\u0006\u0002\n��R \u0010Ð\u0001\u001a\u00030Ñ\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bÔ\u0001\u0010S\u001a\u0006\bÒ\u0001\u0010Ó\u0001¨\u0006Þ\u0001"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisImpl;", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "Lcom/feysh/corax/config/api/IAnalysisDepends;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "cg", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "resultCollector", "Lcn/sast/framework/result/IPreAnalysisResultCollector;", "scene", "Lsoot/Scene;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/framework/report/IProjectFileLocator;Lsoot/jimple/toolkits/callgraph/CallGraph;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/framework/result/IPreAnalysisResultCollector;Lsoot/Scene;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getLocator", "()Lcn/sast/framework/report/IProjectFileLocator;", "getCg", "()Lsoot/jimple/toolkits/callgraph/CallGraph;", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getScene", "()Lsoot/Scene;", "analyzedClasses", "", "Lsoot/SootClass;", "getAnalyzedClasses", "()Ljava/util/Set;", "analyzedSourceFiles", "Lcn/sast/common/IResFile;", "getAnalyzedSourceFiles", "fastCache", "Lcom/feysh/corax/cache/coroutines/FastCache;", "getFastCache", "()Lcom/feysh/corax/cache/coroutines/FastCache;", "scanFilter", "Lcn/sast/api/config/ScanFilter;", "monitor", "Lcn/sast/api/util/IMonitor;", "allClasses", "", "kotlin.jvm.PlatformType", "allMethods", "", "Lsoot/SootMethod;", "allFields", "Lsoot/SootField;", "appOnlyClasses", "appOnlyMethods", "appOnlyFields", "changeFileBasedIncAnalysis", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles;", "dg", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "preAnalysisConfig", "Lcn/sast/api/config/PreAnalysisConfig;", "cancelAnalysisInErrorCount", "", "scopeLateInit", "Lcom/feysh/corax/commons/NullableLateinit;", "Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "scope", "getScope$delegate", "(Lcn/sast/framework/engine/PreAnalysisImpl;)Ljava/lang/Object;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "setScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "uninitializedScope", "", "createNormalAnalyzeSemaphore", "Lkotlinx/coroutines/sync/Semaphore;", "permit", "createResourceSemaphore", "globalNormalAnalyzeSemaphore", "getGlobalNormalAnalyzeSemaphore", "()Lkotlinx/coroutines/sync/Semaphore;", "globalNormalAnalyzeSemaphore$delegate", "Lkotlin/Lazy;", "globalResourceSemaphore", "getGlobalResourceSemaphore", "globalResourceSemaphore$delegate", "filesWhichHitSizeThreshold", "Lcn/sast/common/IResource;", "maximumFileSizeThresholdWarnings", "outputPath", "Ljava/nio/file/Path;", "getOutputPath", "()Ljava/nio/file/Path;", "fullCanonicalPathString", "", "getFullCanonicalPathString", "(Ljava/nio/file/Path;)Ljava/lang/String;", "nonNull", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "T", "getPhaseTimer", "Lcn/sast/api/util/Timer;", "unit", "Lcom/feysh/corax/config/api/CheckerUnit;", "apiName", "atClass", "classes", "", "config", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/IPreAnalysisClassConfig;", "Lkotlin/ExtensionFunctionType;", "block", "Lkotlin/Function2;", "Lcom/feysh/corax/config/api/IClassCheckPoint;", "Lkotlin/coroutines/Continuation;", "", "(Lcom/feysh/corax/config/api/CheckerUnit;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "atMethod", "methods", "Lcom/feysh/corax/config/api/IPreAnalysisMethodConfig;", "Lcom/feysh/corax/config/api/IMethodCheckPoint;", "atField", "fields", "Lcom/feysh/corax/config/api/IPreAnalysisFieldConfig;", "Lcom/feysh/corax/config/api/IFieldCheckPoint;", "atInvoke", "targets", "Lcom/feysh/corax/config/api/IPreAnalysisInvokeConfig;", "Lcom/feysh/corax/config/api/IInvokeCheckPoint;", "(Lcom/feysh/corax/config/api/CheckerUnit;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "chooseSemaphore", "fileSize", "", "atSourceFile", "files", "", "Lcom/feysh/corax/config/api/IPreAnalysisFileConfig;", "Lcom/feysh/corax/config/api/ISourceFileCheckPoint;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "clazz", "Lcom/feysh/corax/config/api/IClassMatch;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lcom/feysh/corax/config/api/IClassMatch;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "atAnyClass", "(Lcom/feysh/corax/config/api/CheckerUnit;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "method", "Lcom/feysh/corax/config/api/IMethodMatch;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lcom/feysh/corax/config/api/IMethodMatch;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "atAnyMethod", "field", "Lcom/feysh/corax/config/api/IFieldMatch;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lcom/feysh/corax/config/api/IFieldMatch;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "atAnyField", "(Lcom/feysh/corax/config/api/CheckerUnit;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "callee", "atAnyInvoke", "path", "(Lcom/feysh/corax/config/api/CheckerUnit;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "atAnySourceFile", "extension", "filename", "(Lcom/feysh/corax/config/api/CheckerUnit;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "report", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "file", "region", "Lcom/feysh/corax/config/api/report/Region;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "sootHost", "Lsoot/tagkit/Host;", "runInSceneAsync", "Lkotlinx/coroutines/Deferred;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Deferred;", "P", "Lcom/feysh/corax/config/api/ICheckPoint;", "Lcom/feysh/corax/config/api/INodeWithRange;", "(Lcom/feysh/corax/config/api/ICheckPoint;Lcom/feysh/corax/config/api/CheckType;Lkotlin/jvm/functions/Function1;)V", "archivePath", "archiveFile", "entry", "getZipEntry", "Lkotlin/Pair;", "innerFilePath", "getShadowFile", "Ljava/io/File;", "copyDest", "globPath", "glob", "findFiles", "Lkotlin/sequences/Sequence;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skip", "", "Lcom/feysh/corax/config/api/IPreAnalysisConfig;", "sc", "sf", "sm", "OrigAction", "origAction", "rule", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchItem;", "Lcom/feysh/corax/config/api/rules/ProcessRulesType;", "target", "Lkotlin/Lazy;", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchTarget;", "invokePointData", "Lcn/sast/framework/engine/PreAnalysisImpl$InvokePointData;", "getInvokePointData", "()Lcn/sast/framework/engine/PreAnalysisImpl$InvokePointData;", "invokePointData$delegate", "invokeCheckPoints", "Lcn/sast/framework/engine/InvokeCheckPoint;", "dependsOn", "Lcom/feysh/corax/config/api/XDecl;", "dep", "deps", "toDecl", "Companion", "InvokePointData", "corax-framework"})
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,760:1\n774#2:761\n865#2,2:762\n1368#2:764\n1454#2,5:765\n1368#2:770\n1454#2,5:771\n774#2:776\n865#2,2:777\n1368#2:779\n1454#2,5:780\n1368#2:785\n1454#2,5:786\n1557#2:792\n1628#2,3:793\n1454#2,5:796\n1#3:791\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl\n*L\n82#1:761\n82#1:762,2\n83#1:764\n83#1:765,5\n84#1:770\n84#1:771,5\n86#1:776\n86#1:777,2\n87#1:779\n87#1:780,5\n88#1:785\n88#1:786,5\n466#1:792\n466#1:793,3\n543#1:796,5\n*E\n"})
/* loaded from: PreAnalysisImpl.class */
public final class PreAnalysisImpl implements PreAnalysisCoroutineScope, IAnalysisDepends {
    private final /* synthetic */ IAnalysisDepends $$delegate_0;

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final IProjectFileLocator locator;

    @NotNull
    private final CallGraph cg;

    @NotNull
    private final SootInfoCache info;

    @NotNull
    private final IPreAnalysisResultCollector resultCollector;

    @NotNull
    private final Scene scene;

    @NotNull
    private final Set<SootClass> analyzedClasses;

    @NotNull
    private final Set<IResFile> analyzedSourceFiles;

    @NotNull
    private final ScanFilter scanFilter;

    @Nullable
    private final IMonitor monitor;

    @NotNull
    private final Set<SootClass> allClasses;

    @NotNull
    private final List<SootMethod> allMethods;

    @NotNull
    private final List<SootField> allFields;

    @NotNull
    private final List<SootClass> appOnlyClasses;

    @NotNull
    private final List<SootMethod> appOnlyMethods;

    @NotNull
    private final List<SootField> appOnlyFields;

    @Nullable
    private final IncrementalAnalyzeByChangeFiles changeFileBasedIncAnalysis;

    @Nullable
    private final IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph dg;

    @Nullable
    private final PreAnalysisConfig preAnalysisConfig;
    private final int cancelAnalysisInErrorCount;

    @NotNull
    private final NullableLateinit<CoroutineScope> scopeLateInit;

    @NotNull
    private final Lazy globalNormalAnalyzeSemaphore$delegate;

    @NotNull
    private final Lazy globalResourceSemaphore$delegate;

    @NotNull
    private final Set<IResource> filesWhichHitSizeThreshold;
    private final int maximumFileSizeThresholdWarnings;

    @NotNull
    private final String OrigAction;

    @NotNull
    private final Lazy invokePointData$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreAnalysisImpl.class, "scope", "getScope()Lkotlinx/coroutines/CoroutineScope;", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger kLogger = KotlinLogging.INSTANCE.logger(PreAnalysisImpl::kLogger$lambda$20);

    @NotNull
    public XDecl toDecl(@NotNull Object target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return this.$$delegate_0.toDecl(target);
    }

    public void dependsOn(@NotNull XDecl $this$dependsOn, @NotNull XDecl dep) {
        Intrinsics.checkNotNullParameter($this$dependsOn, "<this>");
        Intrinsics.checkNotNullParameter(dep, "dep");
        this.$$delegate_0.dependsOn($this$dependsOn, dep);
    }

    public void dependsOn(@NotNull Collection<? extends XDecl> collection, @NotNull Collection<? extends XDecl> collection2) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(collection2, "deps");
        this.$$delegate_0.dependsOn(collection, collection2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PreAnalysisImpl(@org.jetbrains.annotations.NotNull cn.sast.api.config.MainConfig r6, @org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r7, @org.jetbrains.annotations.NotNull soot.jimple.toolkits.callgraph.CallGraph r8, @org.jetbrains.annotations.NotNull com.feysh.corax.cache.analysis.SootInfoCache r9, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IPreAnalysisResultCollector r10, @org.jetbrains.annotations.NotNull soot.Scene r11) {
        /*
            Method dump skipped, instructions count: 1017
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl.<init>(cn.sast.api.config.MainConfig, cn.sast.framework.report.IProjectFileLocator, soot.jimple.toolkits.callgraph.CallGraph, com.feysh.corax.cache.analysis.SootInfoCache, cn.sast.framework.result.IPreAnalysisResultCollector, soot.Scene):void");
    }

    @Override // cn.sast.api.config.PreAnalysisCoroutineScope
    @Nullable
    public Object processPreAnalysisUnits(@NotNull Continuation<? super Unit> continuation) {
        return PreAnalysisCoroutineScope.DefaultImpls.processPreAnalysisUnits(this, continuation);
    }

    @NotNull
    public <T> Job runInScene(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        return PreAnalysisCoroutineScope.DefaultImpls.runInScene(this, $context_receiver_0, function1);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atMethod(@NotNull CheckerUnit $context_receiver_0, @NotNull KCallable<?> kCallable, @NotNull Function1<? super IPreAnalysisMethodConfig, Unit> function1, @NotNull Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        return PreAnalysisCoroutineScope.DefaultImpls.atMethod(this, $context_receiver_0, kCallable, function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atInvoke(@NotNull CheckerUnit $context_receiver_0, @NotNull KCallable<?> kCallable, @NotNull Function1<? super IPreAnalysisInvokeConfig, Unit> function1, @NotNull Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        return PreAnalysisCoroutineScope.DefaultImpls.atInvoke(this, $context_receiver_0, kCallable, function1, function2);
    }

    public <P extends ICheckPoint & INodeWithRange> void report(@NotNull P p, @NotNull CheckType checkType, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        PreAnalysisCoroutineScope.DefaultImpls.report(this, p, checkType, region, function1);
    }

    public void report(@NotNull ISourceFileCheckPoint $this$report, @NotNull CheckType checkType, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        PreAnalysisCoroutineScope.DefaultImpls.report(this, $this$report, checkType, region, function1);
    }

    @Nullable
    public Unit report(@NotNull ISourceFileCheckPoint $this$report, @NotNull CheckType checkType, @NotNull de.fraunhofer.aisec.cpg.sarif.Region cpgRegion, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        return PreAnalysisCoroutineScope.DefaultImpls.report(this, $this$report, checkType, cpgRegion, function1);
    }

    @Nullable
    public Unit report(@NotNull ISourceFileCheckPoint $this$report, @NotNull CheckType checkType, @NotNull Position jpsStart, @Nullable Position jpsEnd, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        return PreAnalysisCoroutineScope.DefaultImpls.report(this, $this$report, checkType, jpsStart, jpsEnd, function1);
    }

    @Nullable
    public Unit report(@NotNull ISourceFileCheckPoint $this$report, @NotNull CheckType checkType, @NotNull NodeWithRange<?> nodeWithRange, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        return PreAnalysisCoroutineScope.DefaultImpls.report(this, $this$report, checkType, nodeWithRange, function1);
    }

    public void report(@NotNull CheckType checkType, @NotNull Host sootHost, @Nullable Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        PreAnalysisCoroutineScope.DefaultImpls.report(this, checkType, sootHost, region, function1);
    }

    @Override // cn.sast.api.config.PreAnalysisCoroutineScope
    @NotNull
    public MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final IProjectFileLocator getLocator() {
        return this.locator;
    }

    @NotNull
    public CallGraph getCg() {
        return this.cg;
    }

    @NotNull
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @NotNull
    public final Scene getScene() {
        return this.scene;
    }

    @NotNull
    public final Set<SootClass> getAnalyzedClasses() {
        return this.analyzedClasses;
    }

    @NotNull
    public final Set<IResFile> getAnalyzedSourceFiles() {
        return this.analyzedSourceFiles;
    }

    @NotNull
    public FastCache getFastCache() {
        return FastCacheImpl.INSTANCE;
    }

    @Override // cn.sast.api.config.PreAnalysisCoroutineScope
    @NotNull
    public CoroutineScope getScope() {
        return (CoroutineScope) this.scopeLateInit.getValue(this, $$delegatedProperties[0]);
    }

    @Override // cn.sast.api.config.PreAnalysisCoroutineScope
    public void setScope(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<set-?>");
        this.scopeLateInit.setValue(this, $$delegatedProperties[0], coroutineScope);
    }

    @Override // cn.sast.api.config.PreAnalysisCoroutineScope
    public void uninitializedScope() {
        this.scopeLateInit.uninitialized();
    }

    private final Semaphore createNormalAnalyzeSemaphore(int permit) {
        return SemaphoreKt.Semaphore$default(permit, 0, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Semaphore createNormalAnalyzeSemaphore$default(PreAnalysisImpl preAnalysisImpl, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = preAnalysisImpl.getMainConfig().getParallelsNum() * 2;
        }
        return preAnalysisImpl.createNormalAnalyzeSemaphore(i);
    }

    private final Semaphore createResourceSemaphore(int permit) {
        return createNormalAnalyzeSemaphore(permit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Semaphore createResourceSemaphore$default(PreAnalysisImpl preAnalysisImpl, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            PreAnalysisConfig preAnalysisConfig = preAnalysisImpl.preAnalysisConfig;
            i = preAnalysisConfig != null ? preAnalysisConfig.getLargeFileSemaphorePermits() : 3;
        }
        return preAnalysisImpl.createResourceSemaphore(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Semaphore getGlobalNormalAnalyzeSemaphore() {
        return (Semaphore) this.globalNormalAnalyzeSemaphore$delegate.getValue();
    }

    private static final Semaphore globalNormalAnalyzeSemaphore_delegate$lambda$6(PreAnalysisImpl this$0) {
        return createNormalAnalyzeSemaphore$default(this$0, 0, 1, null);
    }

    private final Semaphore getGlobalResourceSemaphore() {
        return (Semaphore) this.globalResourceSemaphore$delegate.getValue();
    }

    private static final Semaphore globalResourceSemaphore_delegate$lambda$7(PreAnalysisImpl this$0) {
        return createResourceSemaphore$default(this$0, 0, 1, null);
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisImpl$Companion;", "", "<init>", "()V", "kLogger", "Lmu/KLogger;", "getKLogger", "()Lmu/KLogger;", "corax-framework"})
    /* loaded from: PreAnalysisImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getKLogger() {
            return PreAnalysisImpl.kLogger;
        }
    }

    private static final Unit kLogger$lambda$20() {
        return Unit.INSTANCE;
    }

    @NotNull
    public Path getOutputPath() {
        return getMainConfig().getOutput_dir().getPath();
    }

    @NotNull
    public String getFullCanonicalPathString(@NotNull Path $this$fullCanonicalPathString) {
        Intrinsics.checkNotNullParameter($this$fullCanonicalPathString, "<this>");
        return Resource.INSTANCE.of($this$fullCanonicalPathString).getAbsolute().getNormalize().toString();
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> nonNull(@NotNull PreAnalysisApi.Result<T> result) {
        Intrinsics.checkNotNullParameter(result, "<this>");
        return new PreAnalysisApi.Result<T>(this, result) { // from class: cn.sast.framework.engine.PreAnalysisImpl.nonNull.1
            private final Deferred<List<T>> asyncResult;

            {
                this.asyncResult = BuildersKt.async$default(this.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$nonNull$1$asyncResult$1(result, null), 3, (Object) null);
            }

            public Object await(Continuation<? super List<? extends T>> continuation) {
                return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
            }

            public Deferred<List<T>> getAsyncResult() {
                return this.asyncResult;
            }
        };
    }

    private final Timer getPhaseTimer(CheckerUnit unit, String apiName) {
        IMonitor iMonitor = this.monitor;
        if (iMonitor != null) {
            return iMonitor.timer("PreAnalysis:" + apiName + ".At:" + UtilsKt.getSootTypeName(unit.getClass()));
        }
        return null;
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atClass(@NotNull CheckerUnit $context_receiver_0, @NotNull Collection<? extends SootClass> collection, @NotNull Function1<? super IPreAnalysisClassConfig, Unit> function1, @NotNull Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(collection, "classes");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        Timer t = getPhaseTimer($context_receiver_0, "atClass");
        PreAnalysisClassConfig conf = new PreAnalysisClassConfig(false, false, 3, null);
        function1.invoke(conf);
        return new C00221(this, t, collection, conf, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"cn/sast/framework/engine/PreAnalysisImpl$atClass$1", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "safeAnalyzeUtil", "Lcn/sast/framework/util/SafeAnalyzeUtil;", "getSafeAnalyzeUtil", "()Lcn/sast/framework/util/SafeAnalyzeUtil;", "asyncResult", "Lkotlinx/coroutines/Deferred;", "", "getAsyncResult", "()Lkotlinx/coroutines/Deferred;", "corax-framework"})
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atClass$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atClass$1.class */
    public static final class C00221<T> implements PreAnalysisApi.Result<T> {
        private final SafeAnalyzeUtil safeAnalyzeUtil;
        private final Deferred<List<T>> asyncResult;

        C00221(PreAnalysisImpl $receiver, Timer $t, Collection<? extends SootClass> collection, PreAnalysisClassConfig $conf, Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            this.safeAnalyzeUtil = new SafeAnalyzeUtil($receiver.cancelAnalysisInErrorCount, 0, 2, null);
            this.asyncResult = BuildersKt.async$default($receiver.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$atClass$1$asyncResult$1($t, collection, $receiver, $conf, this, function2, null), 3, (Object) null);
        }

        public Object await(Continuation<? super List<? extends T>> continuation) {
            return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
        }

        public final SafeAnalyzeUtil getSafeAnalyzeUtil() {
            return this.safeAnalyzeUtil;
        }

        public Deferred<List<T>> getAsyncResult() {
            return this.asyncResult;
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atMethod(@NotNull CheckerUnit $context_receiver_0, @NotNull Collection<? extends SootMethod> collection, @NotNull Function1<? super IPreAnalysisMethodConfig, Unit> function1, @NotNull Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(collection, "methods");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        Timer t = getPhaseTimer($context_receiver_0, "atMethod");
        PreAnalysisMethodConfig conf = new PreAnalysisMethodConfig(false, false, 3, null);
        function1.invoke(conf);
        return new C00251(this, t, collection, conf, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"cn/sast/framework/engine/PreAnalysisImpl$atMethod$1", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "safeAnalyzeUtil", "Lcn/sast/framework/util/SafeAnalyzeUtil;", "getSafeAnalyzeUtil", "()Lcn/sast/framework/util/SafeAnalyzeUtil;", "asyncResult", "Lkotlinx/coroutines/Deferred;", "", "getAsyncResult", "()Lkotlinx/coroutines/Deferred;", "corax-framework"})
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atMethod$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atMethod$1.class */
    public static final class C00251<T> implements PreAnalysisApi.Result<T> {
        private final SafeAnalyzeUtil safeAnalyzeUtil;
        private final Deferred<List<T>> asyncResult;

        C00251(PreAnalysisImpl $receiver, Timer $t, Collection<? extends SootMethod> collection, PreAnalysisMethodConfig $conf, Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            this.safeAnalyzeUtil = new SafeAnalyzeUtil($receiver.cancelAnalysisInErrorCount, 0, 2, null);
            this.asyncResult = BuildersKt.async$default($receiver.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$atMethod$1$asyncResult$1($t, collection, $receiver, $conf, this, function2, null), 3, (Object) null);
        }

        public Object await(Continuation<? super List<? extends T>> continuation) {
            return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
        }

        public final SafeAnalyzeUtil getSafeAnalyzeUtil() {
            return this.safeAnalyzeUtil;
        }

        public Deferred<List<T>> getAsyncResult() {
            return this.asyncResult;
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atField(@NotNull CheckerUnit $context_receiver_0, @NotNull Collection<? extends SootField> collection, @NotNull Function1<? super IPreAnalysisFieldConfig, Unit> function1, @NotNull Function2<? super IFieldCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(collection, "fields");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        Timer t = getPhaseTimer($context_receiver_0, "atField");
        PreAnalysisFieldConfig conf = new PreAnalysisFieldConfig(false, false, 3, null);
        function1.invoke(conf);
        return new C00231(this, t, collection, conf, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"cn/sast/framework/engine/PreAnalysisImpl$atField$1", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "safeAnalyzeUtil", "Lcn/sast/framework/util/SafeAnalyzeUtil;", "getSafeAnalyzeUtil", "()Lcn/sast/framework/util/SafeAnalyzeUtil;", "asyncResult", "Lkotlinx/coroutines/Deferred;", "", "getAsyncResult", "()Lkotlinx/coroutines/Deferred;", "corax-framework"})
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atField$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atField$1.class */
    public static final class C00231<T> implements PreAnalysisApi.Result<T> {
        private final SafeAnalyzeUtil safeAnalyzeUtil;
        private final Deferred<List<T>> asyncResult;

        C00231(PreAnalysisImpl $receiver, Timer $t, Collection<? extends SootField> collection, PreAnalysisFieldConfig $conf, Function2<? super IFieldCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            this.safeAnalyzeUtil = new SafeAnalyzeUtil($receiver.cancelAnalysisInErrorCount, 0, 2, null);
            this.asyncResult = BuildersKt.async$default($receiver.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$atField$1$asyncResult$1($t, collection, $receiver, $conf, this, function2, null), 3, (Object) null);
        }

        public Object await(Continuation<? super List<? extends T>> continuation) {
            return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
        }

        public final SafeAnalyzeUtil getSafeAnalyzeUtil() {
            return this.safeAnalyzeUtil;
        }

        public Deferred<List<T>> getAsyncResult() {
            return this.asyncResult;
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atInvoke(@NotNull CheckerUnit $context_receiver_0, @Nullable List<? extends SootMethod> list, @NotNull Function1<? super IPreAnalysisInvokeConfig, Unit> function1, @NotNull Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        Timer t = getPhaseTimer($context_receiver_0, "atInvoke");
        PreAnalysisInvokeConfig conf = new PreAnalysisInvokeConfig(false, false, 3, null);
        function1.invoke(conf);
        return new C00241(this, t, list, conf, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"cn/sast/framework/engine/PreAnalysisImpl$atInvoke$1", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "safeAnalyzeUtil", "Lcn/sast/framework/util/SafeAnalyzeUtil;", "getSafeAnalyzeUtil", "()Lcn/sast/framework/util/SafeAnalyzeUtil;", "asyncResult", "Lkotlinx/coroutines/Deferred;", "", "getAsyncResult", "()Lkotlinx/coroutines/Deferred;", "corax-framework"})
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atInvoke$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atInvoke$1.class */
    public static final class C00241<T> implements PreAnalysisApi.Result<T> {
        private final SafeAnalyzeUtil safeAnalyzeUtil;
        private final Deferred<List<T>> asyncResult;

        C00241(PreAnalysisImpl $receiver, Timer $t, List<? extends SootMethod> list, PreAnalysisInvokeConfig $conf, Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            this.safeAnalyzeUtil = new SafeAnalyzeUtil($receiver.cancelAnalysisInErrorCount, 0, 2, null);
            this.asyncResult = BuildersKt.async$default($receiver.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$atInvoke$1$asyncResult$1($t, $receiver, list, $conf, this, function2, null), 3, (Object) null);
        }

        public Object await(Continuation<? super List<? extends T>> continuation) {
            return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
        }

        public final SafeAnalyzeUtil getSafeAnalyzeUtil() {
            return this.safeAnalyzeUtil;
        }

        public Deferred<List<T>> getAsyncResult() {
            return this.asyncResult;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.sync.Semaphore chooseSemaphore(long r6) {
        /*
            r5 = this;
            r0 = r5
            cn.sast.api.config.MainConfig r0 = r0.getMainConfig()
            cn.sast.api.config.SaConfig r0 = r0.getSaConfig()
            r1 = r0
            if (r1 == 0) goto L1b
            cn.sast.api.config.PreAnalysisConfig r0 = r0.getPreAnalysisConfig()
            r1 = r0
            if (r1 == 0) goto L1b
            int r0 = r0.getLargeFileSize()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L1d
        L1b:
            r0 = 0
        L1d:
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            if (r0 == 0) goto L36
            r0 = r6
            r1 = r8
            int r1 = r1.intValue()
            long r1 = (long) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L36
            r0 = r5
            kotlinx.coroutines.sync.Semaphore r0 = r0.getGlobalResourceSemaphore()
            goto L3a
        L36:
            r0 = r5
            kotlinx.coroutines.sync.Semaphore r0 = r0.getGlobalNormalAnalyzeSemaphore()
        L3a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl.chooseSemaphore(long):kotlinx.coroutines.sync.Semaphore");
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atSourceFile(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super Continuation<? super Iterator<? extends IResFile>>, ? extends Object> function1, @NotNull Function1<? super IPreAnalysisFileConfig, Unit> function12, @NotNull Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "files");
        Intrinsics.checkNotNullParameter(function12, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        Timer t = getPhaseTimer($context_receiver_0, "atSourceFile");
        PreAnalysisFileConfig conf = new PreAnalysisFileConfig(false, false, 3, null);
        function12.invoke(conf);
        return new C00261(this, function1, conf, t, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"cn/sast/framework/engine/PreAnalysisImpl$atSourceFile$1", "Lcom/feysh/corax/config/api/PreAnalysisApi$Result;", "safeAnalyzeUtil", "Lcn/sast/framework/util/SafeAnalyzeUtil;", "getSafeAnalyzeUtil", "()Lcn/sast/framework/util/SafeAnalyzeUtil;", "asyncResult", "Lkotlinx/coroutines/Deferred;", "", "getAsyncResult", "()Lkotlinx/coroutines/Deferred;", "corax-framework"})
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atSourceFile$1.class */
    public static final class C00261<T> implements PreAnalysisApi.Result<T> {
        private final SafeAnalyzeUtil safeAnalyzeUtil;
        private final Deferred<List<T>> asyncResult;

        C00261(PreAnalysisImpl $receiver, Function1<? super Continuation<? super Iterator<? extends IResFile>>, ? extends Object> function1, PreAnalysisFileConfig $conf, Timer $t, Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            this.safeAnalyzeUtil = new SafeAnalyzeUtil($receiver.cancelAnalysisInErrorCount, 0, 2, null);
            this.asyncResult = BuildersKt.async$default($receiver.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PreAnalysisImpl$atSourceFile$1$asyncResult$1(function1, $receiver, $conf, $t, this, function2, null), 3, (Object) null);
        }

        public Object await(Continuation<? super List<? extends T>> continuation) {
            return PreAnalysisApi.Result.DefaultImpls.await(this, continuation);
        }

        public final SafeAnalyzeUtil getSafeAnalyzeUtil() {
            return this.safeAnalyzeUtil;
        }

        public Deferred<List<T>> getAsyncResult() {
            return this.asyncResult;
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atClass(@NotNull CheckerUnit $context_receiver_0, @NotNull IClassMatch clazz, @NotNull Function1<? super IPreAnalysisClassConfig, Unit> function1, @NotNull Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atClass($context_receiver_0, clazz.matched(this.scene), function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atAnyClass(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super IPreAnalysisClassConfig, Unit> function1, @NotNull Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        PreAnalysisClassConfig conf = new PreAnalysisClassConfig(false, false, 3, null);
        function1.invoke(conf);
        return atClass($context_receiver_0, conf.getAppOnly() ? this.appOnlyClasses : this.allClasses, function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atMethod(@NotNull CheckerUnit $context_receiver_0, @NotNull IMethodMatch method, @NotNull Function1<? super IPreAnalysisMethodConfig, Unit> function1, @NotNull Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atMethod($context_receiver_0, method.matched(this.scene), function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atAnyMethod(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super IPreAnalysisMethodConfig, Unit> function1, @NotNull Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        PreAnalysisMethodConfig conf = new PreAnalysisMethodConfig(false, false, 3, null);
        function1.invoke(conf);
        List targets = conf.getAppOnly() ? this.appOnlyMethods : this.allMethods;
        return atMethod($context_receiver_0, targets, function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atField(@NotNull CheckerUnit $context_receiver_0, @NotNull IFieldMatch field, @NotNull Function1<? super IPreAnalysisFieldConfig, Unit> function1, @NotNull Function2<? super IFieldCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atField($context_receiver_0, field.matched(this.scene), function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atAnyField(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super IPreAnalysisFieldConfig, Unit> function1, @NotNull Function1<? super IFieldCheckPoint, ? extends T> function12) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function12, "block");
        PreAnalysisFieldConfig conf = new PreAnalysisFieldConfig(false, false, 3, null);
        function1.invoke(conf);
        List targets = conf.getAppOnly() ? this.appOnlyFields : this.allFields;
        return atField($context_receiver_0, targets, function1, new AnonymousClass1(function12));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atAnyField$1, reason: invalid class name */
    /* loaded from: PreAnalysisImpl$atAnyField$1.class */
    /* synthetic */ class AnonymousClass1<T> extends FunctionReferenceImpl implements Function2<IFieldCheckPoint, Continuation<? super T>, Object>, SuspendFunction {
        AnonymousClass1(Object receiver) {
            super(2, receiver, Intrinsics.Kotlin.class, "suspendConversion0", "atAnyField$suspendConversion0(Lkotlin/jvm/functions/Function1;Lcom/feysh/corax/config/api/IFieldCheckPoint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        public final Object invoke(IFieldCheckPoint p0, Continuation<? super T> continuation) {
            return PreAnalysisImpl.atAnyField$suspendConversion0((Function1) this.receiver, p0, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object atAnyField$suspendConversion0(Function1 $this$atAnyField_u24suspendConversion0, IFieldCheckPoint p0, Continuation $completion) {
        return $this$atAnyField_u24suspendConversion0.invoke(p0);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atInvoke(@NotNull CheckerUnit $context_receiver_0, @NotNull IMethodMatch callee, @NotNull Function1<? super IPreAnalysisInvokeConfig, Unit> function1, @NotNull Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atInvoke($context_receiver_0, callee.matched(this.scene), function1, function2);
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atAnyInvoke(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super IPreAnalysisInvokeConfig, Unit> function1, @NotNull Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atInvoke($context_receiver_0, (List<? extends SootMethod>) null, function1, function2);
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010(\n\u0002\u0018\u0002\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcn/sast/common/IResFile;"})
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$2")
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$2, reason: invalid class name */
    /* loaded from: PreAnalysisImpl$atSourceFile$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Iterator<? extends IResFile>>, Object> {
        int label;
        final /* synthetic */ Path $path;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Path $path, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$path = $path;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$path, continuation);
        }

        public final Object invoke(Continuation<? super Iterator<? extends IResFile>> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    return CollectionsKt.listOf(Resource.INSTANCE.fileOf(this.$path)).iterator();
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atSourceFile(@NotNull CheckerUnit $context_receiver_0, @NotNull Path path, @NotNull Function1<? super IPreAnalysisFileConfig, Unit> function1, @NotNull Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atSourceFile($context_receiver_0, new AnonymousClass2(path, null), function1, function2);
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010(\n\u0002\u0018\u0002\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcn/sast/common/IResFile;"})
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {411}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atAnySourceFile$1")
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$atAnySourceFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$atAnySourceFile$1.class */
    static final class C00211 extends SuspendLambda implements Function1<Continuation<? super Iterator<? extends IResFile>>, Object> {
        int label;
        final /* synthetic */ String $extension;
        final /* synthetic */ String $filename;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00211(String $extension, String $filename, Continuation<? super C00211> continuation) {
            super(1, continuation);
            this.$extension = $extension;
            this.$filename = $filename;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return PreAnalysisImpl.this.new C00211(this.$extension, this.$filename, continuation);
        }

        public final Object invoke(Continuation<? super Iterator<? extends IResFile>> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object objFindFiles;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    objFindFiles = PreAnalysisImpl.this.findFiles(this.$extension, this.$filename, (Continuation) this);
                    if (objFindFiles == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    objFindFiles = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return ((Sequence) objFindFiles).iterator();
        }
    }

    @NotNull
    public <T> PreAnalysisApi.Result<T> atAnySourceFile(@NotNull CheckerUnit $context_receiver_0, @Nullable String extension, @Nullable String filename, @NotNull Function1<? super IPreAnalysisFileConfig, Unit> function1, @NotNull Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(function2, "block");
        return atSourceFile($context_receiver_0, new C00211(extension, filename, null), function1, function2);
    }

    public void report(@NotNull CheckType checkType, @NotNull Path file, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(function1, "env");
        FileResInfo fileInfo = new FileResInfo(Resource.INSTANCE.fileOf(file));
        DefaultEnv finalEnv = new DefaultEnv(region.getMutable(), fileInfo.getReportFileName(), null, null, null, null, null, null, null, 508, null);
        function1.invoke(finalEnv);
        PreAnalysisReportEnv info = new PreAnalysisReportEnv(fileInfo, finalEnv);
        this.resultCollector.report(checkType, info);
    }

    public void report(@NotNull CheckType checkType, @NotNull Host sootHost, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        Object fieldCheckPoint;
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(sootHost, "sootHost");
        Intrinsics.checkNotNullParameter(function1, "env");
        if (sootHost instanceof SootClass) {
            fieldCheckPoint = new ClassCheckPoint((SootClass) sootHost, this.info);
        } else if (sootHost instanceof SootMethod) {
            fieldCheckPoint = new MethodCheckPoint((SootMethod) sootHost, this.info);
        } else if (sootHost instanceof SootField) {
            fieldCheckPoint = new FieldCheckPoint((SootField) sootHost, this.info);
        } else {
            kLogger.error(() -> {
                return report$lambda$9(r1);
            });
            fieldCheckPoint = null;
        }
        if (fieldCheckPoint != null) {
            Object point = fieldCheckPoint;
            report((PreAnalysisImpl) point, checkType, function1);
        }
    }

    private static final Object report$lambda$9(Host $sootHost) {
        return "SootHost type in report(,sootHost: " + $sootHost.getClass() + ",) is not support yet! only support types: SootClass, SootMethod, SootField";
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {441}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$runInSceneAsync$1")
    /* renamed from: cn.sast.framework.engine.PreAnalysisImpl$runInSceneAsync$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: PreAnalysisImpl$runInSceneAsync$1.class */
    static final class C00281<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        int label;
        final /* synthetic */ SafeAnalyzeUtil $safeAnalyzeUtil;
        final /* synthetic */ CheckerUnit $$context_receiver_0;
        final /* synthetic */ Function1<Continuation<? super T>, Object> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00281(SafeAnalyzeUtil $safeAnalyzeUtil, CheckerUnit $$context_receiver_0, Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super C00281> continuation) {
            super(2, continuation);
            this.$safeAnalyzeUtil = $safeAnalyzeUtil;
            this.$$context_receiver_0 = $$context_receiver_0;
            this.$block = function1;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new C00281<>(this.$safeAnalyzeUtil, this.$$context_receiver_0, this.$block, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super T> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objSafeRunInSceneAsync = this.$safeAnalyzeUtil.safeRunInSceneAsync(this.$$context_receiver_0, this.$block, (Continuation) this);
                    if (objSafeRunInSceneAsync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objSafeRunInSceneAsync;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @NotNull
    public <T> Deferred<T> runInSceneAsync(@NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(function1, "block");
        SafeAnalyzeUtil safeAnalyzeUtil = new SafeAnalyzeUtil(this.cancelAnalysisInErrorCount, 0, 2, null);
        return BuildersKt.async$default(getScope(), (CoroutineContext) null, (CoroutineStart) null, new C00281(safeAnalyzeUtil, $context_receiver_0, function1, null), 3, (Object) null);
    }

    public <P extends ICheckPoint & INodeWithRange> void report(@NotNull P p, @NotNull CheckType checkType, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
        Intrinsics.checkNotNullParameter(p, "<this>");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(function1, "env");
        DefaultEnv finalEnv = ((CheckPoint) p).getEnv$corax_framework();
        function1.invoke(finalEnv);
        PreAnalysisReportEnv info = new PreAnalysisReportEnv(((CheckPoint) p).getFile(), finalEnv);
        this.resultCollector.report(checkType, info);
    }

    @NotNull
    public Path archivePath(@NotNull Path archiveFile, @NotNull String entry) {
        Intrinsics.checkNotNullParameter(archiveFile, "archiveFile");
        Intrinsics.checkNotNullParameter(entry, "entry");
        return Resource.INSTANCE.archivePath(archiveFile, entry);
    }

    @Nullable
    public Pair<Path, String> getZipEntry(@NotNull Path innerFilePath) {
        Intrinsics.checkNotNullParameter(innerFilePath, "innerFilePath");
        IResource file = Resource.INSTANCE.of(innerFilePath);
        String zipEntry = file.getZipEntry();
        if (zipEntry == null) {
            return null;
        }
        return TuplesKt.to(file.getSchemePath(), zipEntry);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047 A[PHI: r0
  0x0047: PHI (r0v4 cn.sast.common.IResource) = (r0v3 cn.sast.common.IResource), (r0v18 cn.sast.common.IResource) binds: [B:3:0x0012, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.File getShadowFile(@org.jetbrains.annotations.NotNull java.nio.file.Path r5, @org.jetbrains.annotations.Nullable java.nio.file.Path r6) {
        /*
            r4 = this;
            r0 = r5
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            cn.sast.common.Resource r0 = cn.sast.common.Resource.INSTANCE
            r1 = r5
            cn.sast.common.IResource r0 = r0.of(r1)
            r7 = r0
            r0 = r7
            r1 = r6
            r2 = r1
            if (r2 == 0) goto L46
            r9 = r1
            r14 = r0
            r0 = 0
            r10 = r0
            cn.sast.common.Resource r0 = cn.sast.common.Resource.INSTANCE
            r1 = r9
            cn.sast.common.IResDirectory r0 = r0.dirOf(r1)
            r11 = r0
            r0 = r11
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            boolean r0 = r0.isFileScheme()
            if (r0 == 0) goto L3c
            r0 = r11
            goto L3d
        L3c:
            r0 = 0
        L3d:
            r1 = r14
            r2 = r0; r0 = r1; r1 = r2; 
            r2 = r1
            if (r2 != 0) goto L4e
        L46:
        L47:
            r1 = r4
            cn.sast.api.config.MainConfig r1 = r1.getMainConfig()
            cn.sast.common.IResDirectory r1 = r1.getOutput_dir()
        L4e:
            cn.sast.common.IResource r0 = r0.expandRes(r1)
            r8 = r0
            r0 = r8
            java.io.File r0 = r0.getFile()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl.getShadowFile(java.nio.file.Path, java.nio.file.Path):java.io.File");
    }

    @Nullable
    public List<Path> globPath(@NotNull String glob) throws IOException {
        Intrinsics.checkNotNullParameter(glob, "glob");
        Iterable iterableGlobPath = ResourceImplKt.globPath(glob);
        if (iterableGlobPath == null) {
            return null;
        }
        Iterable $this$map$iv = iterableGlobPath;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IResource it = (IResource) item$iv$iv;
            destination$iv$iv.add(it.getPath());
        }
        return (List) destination$iv$iv;
    }

    @Nullable
    public final Object findFiles(@Nullable String extension, @Nullable String filename, @NotNull Continuation<? super Sequence<? extends IResFile>> continuation) {
        if (extension != null) {
            return this.locator.getByFileExtension(extension, continuation);
        }
        if (filename != null) {
            return this.locator.getByFileName(filename, continuation);
        }
        return this.locator.getAllFiles(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean skip(IPreAnalysisConfig $this$skip, Path file) {
        return skip$default(this, $this$skip, null, this.scanFilter.getProcessRegex().getClazzRules(), LazyKt.lazy(LazyThreadSafetyMode.NONE, () -> {
            return skip$lambda$13(r5, r6);
        }), 1, null);
    }

    private static final ProcessRule.FileMatch.MatchTarget skip$lambda$13(PreAnalysisImpl this$0, Path $file) {
        return this$0.scanFilter.get($file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean skip(IPreAnalysisConfig $this$skip, SootClass sc) {
        return skip$default(this, $this$skip, null, this.scanFilter.getProcessRegex().getClazzRules(), LazyKt.lazy(LazyThreadSafetyMode.NONE, () -> {
            return skip$lambda$14(r5, r6);
        }), 1, null);
    }

    private static final ProcessRule.ClassMemberMatch.MatchTarget skip$lambda$14(PreAnalysisImpl this$0, SootClass $sc) {
        return this$0.scanFilter.get($sc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean skip(IPreAnalysisConfig $this$skip, SootField sf) {
        return skip($this$skip, null, this.scanFilter.getProcessRegex().getClazzRules(), LazyKt.lazy(LazyThreadSafetyMode.NONE, () -> {
            return skip$lambda$15(r5, r6);
        }));
    }

    private static final ProcessRule.ClassMemberMatch.MatchTarget skip$lambda$15(PreAnalysisImpl this$0, SootField $sf) {
        return this$0.scanFilter.get($sf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean skip(IPreAnalysisConfig $this$skip, SootMethod sm) {
        return skip$default(this, $this$skip, null, this.scanFilter.getProcessRegex().getClazzRules(), LazyKt.lazy(LazyThreadSafetyMode.NONE, () -> {
            return skip$lambda$16(r5, r6);
        }), 1, null);
    }

    private static final ProcessRule.ClassMemberMatch.MatchTarget skip$lambda$16(PreAnalysisImpl this$0, SootMethod $sm) {
        return this$0.scanFilter.get($sm);
    }

    static /* synthetic */ boolean skip$default(PreAnalysisImpl preAnalysisImpl, IPreAnalysisConfig iPreAnalysisConfig, String str, List list, Lazy lazy, int i, Object obj) {
        if ((i & 1) != 0) {
            str = preAnalysisImpl.OrigAction;
        }
        return preAnalysisImpl.skip(iPreAnalysisConfig, str, list, lazy);
    }

    private final boolean skip(IPreAnalysisConfig $this$skip, String origAction, List<? extends ProcessRule.IMatchItem> list, Lazy<? extends ProcessRule.IMatchTarget> lazy) {
        if (!$this$skip.getIgnoreProjectConfigProcessFilter()) {
            ProcessRule.ScanAction act = ScanFilter.getActionOf$default(this.scanFilter, list, origAction, (ProcessRule.IMatchTarget) lazy.getValue(), null, 8, null);
            if (act == ProcessRule.ScanAction.Skip) {
                return true;
            }
        }
        return !$this$skip.getProcessRules().isEmpty() && ProcessRule.INSTANCE.matches($this$skip.getProcessRules(), (ProcessRule.IMatchTarget) lazy.getValue()).getSecond() == ProcessRule.ScanAction.Skip;
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B)\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J/\u0010\u0010\u001a\u00020��2\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisImpl$InvokePointData;", "", "targetsToEdges", "Lsoot/util/MultiMap;", "Lsoot/SootMethod;", "Lcn/sast/framework/engine/InvokeCheckPoint;", "allPoint", "", "<init>", "(Lsoot/util/MultiMap;Ljava/util/Set;)V", "getTargetsToEdges", "()Lsoot/util/MultiMap;", "getAllPoint", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: PreAnalysisImpl$InvokePointData.class */
    public static final class InvokePointData {

        @NotNull
        private final MultiMap<SootMethod, InvokeCheckPoint> targetsToEdges;

        @NotNull
        private final Set<InvokeCheckPoint> allPoint;

        @NotNull
        public final MultiMap<SootMethod, InvokeCheckPoint> component1() {
            return this.targetsToEdges;
        }

        @NotNull
        public final Set<InvokeCheckPoint> component2() {
            return this.allPoint;
        }

        @NotNull
        public final InvokePointData copy(@NotNull MultiMap<SootMethod, InvokeCheckPoint> multiMap, @NotNull Set<InvokeCheckPoint> set) {
            Intrinsics.checkNotNullParameter(multiMap, "targetsToEdges");
            Intrinsics.checkNotNullParameter(set, "allPoint");
            return new InvokePointData(multiMap, set);
        }

        public static /* synthetic */ InvokePointData copy$default(InvokePointData invokePointData, MultiMap multiMap, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                multiMap = invokePointData.targetsToEdges;
            }
            if ((i & 2) != 0) {
                set = invokePointData.allPoint;
            }
            return invokePointData.copy(multiMap, set);
        }

        @NotNull
        public String toString() {
            return "InvokePointData(targetsToEdges=" + this.targetsToEdges + ", allPoint=" + this.allPoint + ")";
        }

        public int hashCode() {
            int result = this.targetsToEdges.hashCode();
            return (result * 31) + this.allPoint.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvokePointData)) {
                return false;
            }
            InvokePointData invokePointData = (InvokePointData) other;
            return Intrinsics.areEqual(this.targetsToEdges, invokePointData.targetsToEdges) && Intrinsics.areEqual(this.allPoint, invokePointData.allPoint);
        }

        public InvokePointData(@NotNull MultiMap<SootMethod, InvokeCheckPoint> multiMap, @NotNull Set<InvokeCheckPoint> set) {
            Intrinsics.checkNotNullParameter(multiMap, "targetsToEdges");
            Intrinsics.checkNotNullParameter(set, "allPoint");
            this.targetsToEdges = multiMap;
            this.allPoint = set;
        }

        @NotNull
        public final MultiMap<SootMethod, InvokeCheckPoint> getTargetsToEdges() {
            return this.targetsToEdges;
        }

        @NotNull
        public final Set<InvokeCheckPoint> getAllPoint() {
            return this.allPoint;
        }
    }

    private final InvokePointData getInvokePointData() {
        return (InvokePointData) this.invokePointData$delegate.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final cn.sast.framework.engine.PreAnalysisImpl.InvokePointData invokePointData_delegate$lambda$18(cn.sast.framework.engine.PreAnalysisImpl r10) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl.invokePointData_delegate$lambda$18(cn.sast.framework.engine.PreAnalysisImpl):cn.sast.framework.engine.PreAnalysisImpl$InvokePointData");
    }

    @NotNull
    public final Set<InvokeCheckPoint> invokeCheckPoints(@Nullable List<? extends SootMethod> list) {
        if (list == null) {
            return getInvokePointData().getAllPoint();
        }
        List<? extends SootMethod> $this$flatMapTo$iv = list;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            SootMethod it = (SootMethod) element$iv;
            Iterable iterable = getInvokePointData().getTargetsToEdges().get(it);
            Intrinsics.checkNotNullExpressionValue(iterable, "get(...)");
            Iterable list$iv = iterable;
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (Set) destination$iv;
    }
}
