package cn.sast.idfa.analysis;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.common.OS;
import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.coroutines.caffine.impl.RecCoroutineCacheImplKt;
import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.graph.HashMutableDirectedGraph;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.FixPointStatus;
import cn.sast.idfa.progressbar.ProgressBarExt;
import cn.sast.idfa.progressbar.ProgressBarExtKt;
import com.feysh.corax.cache.coroutines.RecCoroutineLoadingCache;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarRenderer;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;
import soot.SootMethod;
import soot.jimple.infoflow.collect.ConcurrentHashSet;
import soot.jimple.infoflow.solver.executors.InterruptableExecutor;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: ForwardInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��ì\u0001\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\b&\u0018�� ´\u0001*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004*\u001a\b\u0004\u0010\u0005*\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007:\u0006²\u0001³\u0001´\u0001B\u0011\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\f\u0010%\u001a\u00020&*\u00020'H\u0016J\b\u0010>\u001a\u00020?H\u0016J;\u0010B\u001a\u00028\u00042\u0006\u0010C\u001a\u00028��2\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00010E2\u0006\u0010F\u001a\u00028\u00022\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u0017H&¢\u0006\u0002\u0010IJ3\u0010J\u001a\u00028\u00042\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00010E2\u0006\u0010C\u001a\u00028��2\u0006\u0010F\u001a\u00028\u00022\u0006\u0010H\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010KJ\u001e\u0010L\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u0001H\u0096@¢\u0006\u0002\u0010OJ\u0016\u0010P\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u0004H\u0096@¢\u0006\u0002\u0010QJ\u0015\u0010R\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u0004H\u0016¢\u0006\u0002\u0010SJ5\u0010T\u001a\u00028\u00022\u0006\u0010U\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010V\u001a\u00028��2\u0006\u0010W\u001a\u00028\u00022\u0006\u0010X\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010YJL\u0010Z\u001a\u0018\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u0003\u0018\u00010[2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010X\u001a\u00028\u0002H\u0096@¢\u0006\u0002\u0010]JY\u0010^\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010X\u001a\u00028\u00022\u0006\u0010_\u001a\u00028\u00022\u0006\u0010H\u001a\u00020\u0017H&¢\u0006\u0002\u0010`JY\u0010a\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010X\u001a\u00028\u00022\u0006\u0010_\u001a\u00028\u00022\u0006\u0010H\u001a\u00020\u0017H&¢\u0006\u0002\u0010`J\u000e\u0010q\u001a\u00020?2\u0006\u0010r\u001a\u00020sJJ\u0010t\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[2\u0006\u0010U\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010W\u001a\u00028\u0002H\u0096@¢\u0006\u0002\u0010]J\u0015\u0010u\u001a\u00020?2\u0006\u0010V\u001a\u00028��H&¢\u0006\u0002\u0010vJV\u0010w\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u001a\u0010x\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[H\u0096@¢\u0006\u0002\u0010yJ6\u0010z\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010W\u001a\u00028\u0002H\u0096@¢\u0006\u0002\u0010]Jc\u0010{\u001a(\u0012\u0004\u0012\u00028��\u0012\u001e\u0012\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[0}0|2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\f\u0010~\u001a\b\u0012\u0004\u0012\u00028��0\u007f2\u0006\u0010W\u001a\u00028\u0002H\u0096@¢\u0006\u0003\u0010\u0080\u0001J\u0013\u0010\u0084\u0001\u001a\u00020?*\u00028\u0004H\u0096@¢\u0006\u0002\u0010QJ3\u0010\u0087\u0001\u001a\u00028\u00042\u0019\u0010\u0088\u0001\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020f2\u0006\u0010U\u001a\u00028\u0004H\u0096@¢\u0006\u0003\u0010\u0089\u0001J\u0018\u0010\u008c\u0001\u001a\u00020?2\u0006\u0010C\u001a\u00028��H\u0086@¢\u0006\u0003\u0010\u008d\u0001J1\u0010\u008e\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u0090\u0001\u0012\u0006\u0012\u0004\u0018\u00010&0\u008f\u00012\u0006\u0010r\u001a\u00020s2\u000e\u0010\u0091\u0001\u001a\t\u0012\u0004\u0012\u00028��0\u0092\u0001H\u0016J\u0019\u0010\u0093\u0001\u001a\u00020?2\u000e\u0010\u0091\u0001\u001a\t\u0012\u0004\u0012\u00028��0\u0092\u0001H\u0016J:\u0010\u0094\u0001\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010X\u001a\u00028\u00022\b\u0010\u0095\u0001\u001a\u00030\u0096\u0001H¦@¢\u0006\u0003\u0010\u0097\u0001JE\u0010\u0098\u0001\u001a\u00028\u00022\u001a\u0010M\u001a\u0016\u0012\u0005\u0012\u00030\u0099\u0001\u0012\u0005\u0012\u00030\u009a\u0001\u0012\u0004\u0012\u00028\u00020\u00062\u0007\u0010N\u001a\u00030\u009a\u00012\u0007\u0010\\\u001a\u00030\u009a\u00012\u0006\u0010W\u001a\u00028\u0002H\u0016¢\u0006\u0003\u0010\u009b\u0001J6\u0010\u009c\u0001\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010V\u001a\u00028��2\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010X\u001a\u00028\u0002H&¢\u0006\u0002\u0010YJe\u0010\u009d\u0001\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00030[2\u0006\u0010M\u001a\u00028\u00042\u0006\u0010_\u001a\u00028\u00022\u0006\u0010V\u001a\u00028��2\u0007\u0010\u009e\u0001\u001a\u00028\u00022\u0007\u0010\u009f\u0001\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0006\u0010H\u001a\u00020\u0017H&¢\u0006\u0003\u0010 \u0001J)\u0010¡\u0001\u001a\u00028\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0007\u0010¢\u0001\u001a\u00028\u0002H\u0096@¢\u0006\u0003\u0010£\u0001J2\u0010¤\u0001\u001a\u0004\u0018\u00018\u00022\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0007\u0010¥\u0001\u001a\u00028\u0002H\u0016¢\u0006\u0003\u0010¦\u0001J:\u0010§\u0001\u001a\u00030¨\u00012\u0006\u0010M\u001a\u00028\u00042\u0006\u0010N\u001a\u00028\u00012\u0006\u0010\\\u001a\u00028\u00012\u0007\u0010©\u0001\u001a\u00028\u00022\u0007\u0010ª\u0001\u001a\u00028\u0002H\u0016¢\u0006\u0003\u0010«\u0001J\u001e\u0010H\u001a\u00020\u00172\u0006\u0010V\u001a\u00028��2\u0006\u0010x\u001a\u00028\u0002H&¢\u0006\u0003\u0010¬\u0001J&\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u00028��0\u007f2\u0007\u0010®\u0001\u001a\u00028��2\u0006\u0010N\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0001J%\u0010°\u0001\u001a\b\u0012\u0004\u0012\u00028\u00010E2\u0006\u0010C\u001a\u00028��2\u0006\u0010H\u001a\u00020\u0017H&¢\u0006\u0003\u0010±\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u0010\u001a\u00020\u0011X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0017@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u001f\u0010\u0019R\u000e\u0010 \u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n��\u001a\u0004\b#\u0010$R\u001a\u0010(\u001a\u00020)X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00028��05¢\u0006\b\n��\u001a\u0004\b6\u00107R\"\u00108\u001a\n\u0012\u0004\u0012\u00028��\u0018\u000109X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0014\u0010@\u001a\u00020)X\u0096D¢\u0006\b\n��\u001a\u0004\bA\u0010+R\u000e\u0010b\u001a\u00020cX\u0082\u0004¢\u0006\u0002\n��R8\u0010d\u001a \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020f\u0012\u0004\u0012\u00028\u00040eX\u0086.¢\u0006\u000e\n��\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001a\u0010k\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bl\u0010+\"\u0004\bm\u0010-R\u001a\u0010n\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bo\u0010\u0013\"\u0004\bp\u0010\u0015R\u0017\u0010\u0081\u0001\u001a\u00028\u00028BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0010\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0082\u000e¢\u0006\u0002\n��R\u0016\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028��0\u008b\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006µ\u0001"}, d2 = {"Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis;", "M", "N", "A", "R", "CTX", "Lcn/sast/idfa/analysis/Context;", "Lcn/sast/idfa/analysis/InterProceduralAnalysis;", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "stopWatch", "Lcom/google/common/base/Stopwatch;", "timeOutDuration", "", "getTimeOutDuration", "()J", "setTimeOutDuration", "(J)V", "timeOutOn", "", "getTimeOutOn", "()Z", "setTimeOutOn", "(Z)V", "usedTime", "", "value", "isTimeout", "limitedAnalytics", "progressBarExt", "Lcn/sast/idfa/progressbar/ProgressBarExt;", "getProgressBarExt", "()Lcn/sast/idfa/progressbar/ProgressBarExt;", "wrapperCustom", "Lcn/sast/idfa/progressbar/ProgressBarExt$DefaultProcessInfoRenderer;", "Lme/tongfei/progressbar/ProgressBar;", "numberThreads", "", "getNumberThreads", "()I", "setNumberThreads", "(I)V", "staticFieldTrackingMode", "Lcn/sast/api/config/StaticFieldTrackingMode;", "getStaticFieldTrackingMode", "()Lcn/sast/api/config/StaticFieldTrackingMode;", "setStaticFieldTrackingMode", "(Lcn/sast/api/config/StaticFieldTrackingMode;)V", "reachableMethods", "Lsoot/jimple/infoflow/collect/ConcurrentHashSet;", "getReachableMethods", "()Lsoot/jimple/infoflow/collect/ConcurrentHashSet;", "directedGraph", "Lcn/sast/graph/HashMutableDirectedGraph;", "getDirectedGraph", "()Lcn/sast/graph/HashMutableDirectedGraph;", "setDirectedGraph", "(Lcn/sast/graph/HashMutableDirectedGraph;)V", "cacheConfig", "", "progressBarVolume", "getProgressBarVolume", "makeContext", "method", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "entryValue", "reverse", "isAnalyzable", "(Ljava/lang/Object;Lsoot/toolkits/graph/DirectedGraph;Ljava/lang/Object;ZZ)Lcn/sast/idfa/analysis/Context;", "newContext", "(Lsoot/toolkits/graph/DirectedGraph;Ljava/lang/Object;Ljava/lang/Object;Z)Lcn/sast/idfa/analysis/Context;", "computeInValue", "context", "node", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeEntryValue", "(Lcn/sast/idfa/analysis/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeExitValue", "(Lcn/sast/idfa/analysis/Context;)Ljava/lang/Object;", "initCallEdgeValue", "currentContext", "callee", "callSiteValue", "inValue", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "evalCall", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "succ", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recursiveCallFlowFunction", "siteValue", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Z)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "failedInvokeResult", "contextStateId", "Ljava/util/concurrent/atomic/AtomicLong;", "cache", "Lcom/feysh/corax/cache/coroutines/RecCoroutineLoadingCache;", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$FactKey;", "getCache", "()Lcom/feysh/corax/cache/coroutines/RecCoroutineLoadingCache;", "setCache", "(Lcom/feysh/corax/cache/coroutines/RecCoroutineLoadingCache;)V", "dataFlowInterProceduralCalleeTimeOut", "getDataFlowInterProceduralCalleeTimeOut", "setDataFlowInterProceduralCalleeTimeOut", "dataFlowInterProceduralCalleeDepChainMaxNum", "getDataFlowInterProceduralCalleeDepChainMaxNum", "setDataFlowInterProceduralCalleeDepChainMaxNum", "init", "scope", "Lkotlinx/coroutines/CoroutineScope;", "processCallCoroutine", "skip", "(Ljava/lang/Object;)V", "postCallAtCallSite", "in1", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prevCallFunction", "processAndReturnResult", "", "Lkotlinx/coroutines/Deferred;", "callees", "", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Set;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bottom", "getBottom", "()Ljava/lang/Object;", "processContent", "transformStmtTotalCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "interProceduralAnalyze", "key", "(Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$FactKey;Lcn/sast/idfa/analysis/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "curAnalysingMethods", "", "processMethod", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doAnalyze", "Lkotlin/Pair;", "Lkotlinx/coroutines/Job;", "entries", "", "doAnalysis", "normalFlowFunction", "isNegativeBranch", "Ljava/util/concurrent/atomic/AtomicBoolean;", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/concurrent/atomic/AtomicBoolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callLocalFlowFunction", "Lsoot/SootMethod;", "Lsoot/Unit;", "(Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lsoot/Unit;Ljava/lang/Object;)Ljava/lang/Object;", "callEntryFlowFunction", "callExitFlowFunction", "callEdgeValue", "calleeCtx", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Z)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "returnFlowFunction", "returnValue", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wideningFunction", "in", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "hasChange", "Lcn/sast/idfa/analysis/FixPointStatus;", "old", "new", "(Lcn/sast/idfa/analysis/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/idfa/analysis/FixPointStatus;", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "resolveTargets", "callerMethod", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Set;", "getCfg", "(Ljava/lang/Object;Z)Lsoot/toolkits/graph/DirectedGraph;", "FactKey", "InvokeResult", "Companion", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nForwardInterProceduralAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 4 FixPointStatus.kt\ncn/sast/idfa/analysis/FixPointStatus$Companion\n*L\n1#1,678:1\n1279#2,2:679\n1293#2,2:681\n1296#2:684\n1557#2:685\n1628#2,3:686\n326#3:683\n10#4:689\n*S KotlinDebug\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis\n*L\n341#1:679,2\n341#1:681,2\n341#1:684\n512#1:685\n512#1:686,3\n342#1:683\n663#1:689\n*E\n"})
/* loaded from: ForwardInterProceduralAnalysis.class */
public abstract class ForwardInterProceduralAnalysis<M, N, A, R, CTX extends Context<M, N, A>> extends InterProceduralAnalysis<M, N, A> {

    @NotNull
    private final String name;

    @Nullable
    private Stopwatch stopWatch;
    private long timeOutDuration;
    private boolean timeOutOn;
    private double usedTime;
    private boolean isTimeout;
    private boolean limitedAnalytics;

    @NotNull
    private final ProgressBarExt progressBarExt;
    private int numberThreads;

    @NotNull
    private StaticFieldTrackingMode staticFieldTrackingMode;

    @NotNull
    private final ConcurrentHashSet<M> reachableMethods;

    @Nullable
    private HashMutableDirectedGraph<M> directedGraph;
    private final int progressBarVolume;

    @NotNull
    private final AtomicLong contextStateId;
    public RecCoroutineLoadingCache<FactKey<M, N, A>, CTX> cache;
    private int dataFlowInterProceduralCalleeTimeOut;
    private long dataFlowInterProceduralCalleeDepChainMaxNum;

    @NotNull
    private AtomicInteger transformStmtTotalCount;

    @NotNull
    private final Set<M> curAnalysingMethods;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(ForwardInterProceduralAnalysis::logger$lambda$13);

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: ForwardInterProceduralAnalysis$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FixPointStatus.values().length];
            try {
                iArr[FixPointStatus.HasChange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FixPointStatus.NeedWideningOperators.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FixPointStatus.Fixpoint.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {153}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"context", "node"}, m = "computeInValue$suspendImpl", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$computeInValue$1, reason: invalid class name */
    /* loaded from: ForwardInterProceduralAnalysis$computeInValue$1.class */
    static final class AnonymousClass1<M, N, A, R, CTX extends Context<M, N, A>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = forwardInterProceduralAnalysis;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ForwardInterProceduralAnalysis.computeInValue$suspendImpl(this.this$0, null, null, (Continuation) this);
        }
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {446}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"$this", "currentContext"}, m = "interProceduralAnalyze$suspendImpl", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$interProceduralAnalyze$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$interProceduralAnalyze$1.class */
    static final class C00481<M, N, A, R, CTX extends Context<M, N, A>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00481(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super C00481> continuation) {
            super(continuation);
            this.this$0 = forwardInterProceduralAnalysis;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ForwardInterProceduralAnalysis.interProceduralAnalyze$suspendImpl(this.this$0, null, null, (Continuation) this);
        }
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {249, 277, 281, 307}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"}, n = {"$this", "currentContext", "callee", "node", "succ", "callSiteValue", "callEdgeValue", "isAnalyzable", "$this", "currentContext", "callee", "node", "succ", "callSiteValue", "callEdgeValue", "key", "isAnalyzable", "$this", "currentContext", "callee", "node", "succ", "callSiteValue", "callEdgeValue", "isAnalyzable", "startNano", "$this", "currentContext", "callee", "node", "succ", "callSiteValue", "callEdgeValue", "t", "isAnalyzable"}, m = "processCallCoroutine$suspendImpl", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processCallCoroutine$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$processCallCoroutine$1.class */
    static final class C00491<M, N, A, R, CTX extends Context<M, N, A>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int I$0;
        long J$0;
        /* synthetic */ Object result;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00491(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super C00491> continuation) {
            super(continuation);
            this.this$0 = forwardInterProceduralAnalysis;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ForwardInterProceduralAnalysis.processCallCoroutine$suspendImpl(this.this$0, null, null, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {357, 364, 383, 386}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"$this", "$receiver", "$this", "currentContext", "node", "succ", "$this", "currentContext", "node", "succ", "in1", "$this", "currentContext", "node", "succ", "isNegativeBranch"}, m = "processContent$suspendImpl", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$processContent$1.class */
    static final class C00501<M, N, A, R, CTX extends Context<M, N, A>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00501(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super C00501> continuation) {
            super(continuation);
            this.this$0 = forwardInterProceduralAnalysis;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ForwardInterProceduralAnalysis.processContent$suspendImpl(this.this$0, null, (Continuation) this);
        }
    }

    @NotNull
    public abstract CTX makeContext(M m, @NotNull DirectedGraph<N> directedGraph, A a, boolean z, boolean z2);

    @Nullable
    public Object computeInValue(@NotNull CTX ctx, N n, @NotNull Continuation<? super A> continuation) {
        return computeInValue$suspendImpl(this, ctx, n, continuation);
    }

    @Nullable
    public Object computeEntryValue(@NotNull CTX ctx, @NotNull Continuation<? super A> continuation) {
        return computeEntryValue$suspendImpl(this, ctx, continuation);
    }

    @Nullable
    public Object evalCall(@NotNull CTX ctx, M m, N n, N n2, A a, @NotNull Continuation<? super InvokeResult<M, A, R>> continuation) {
        return evalCall$suspendImpl(this, ctx, m, n, n2, a, continuation);
    }

    @NotNull
    public abstract InvokeResult<M, A, R> recursiveCallFlowFunction(@NotNull CTX ctx, M m, N n, N n2, A a, A a2, boolean z);

    @NotNull
    public abstract InvokeResult<M, A, R> failedInvokeResult(@NotNull CTX ctx, M m, N n, N n2, A a, A a2, boolean z);

    @Nullable
    public Object processCallCoroutine(@NotNull CTX ctx, M m, N n, N n2, A a, @NotNull Continuation<? super InvokeResult<M, A, R>> continuation) {
        return processCallCoroutine$suspendImpl(this, ctx, m, n, n2, a, continuation);
    }

    public abstract void skip(M m);

    @Nullable
    public Object postCallAtCallSite(@NotNull CTX ctx, N n, N n2, @NotNull InvokeResult<M, A, R> invokeResult, @NotNull Continuation<? super InvokeResult<M, A, R>> continuation) {
        return postCallAtCallSite$suspendImpl(this, ctx, n, n2, invokeResult, continuation);
    }

    @Nullable
    public Object prevCallFunction(@NotNull CTX ctx, M m, N n, N n2, A a, @NotNull Continuation<? super A> continuation) {
        return prevCallFunction$suspendImpl(this, ctx, m, n, n2, a, continuation);
    }

    @Nullable
    public Object processAndReturnResult(@NotNull CTX ctx, N n, N n2, @NotNull Set<? extends M> set, A a, @NotNull Continuation<? super Map<M, ? extends Deferred<InvokeResult<M, A, R>>>> continuation) {
        return processAndReturnResult$suspendImpl(this, ctx, n, n2, set, a, continuation);
    }

    @Nullable
    public Object processContent(@NotNull CTX ctx, @NotNull Continuation<? super Unit> continuation) {
        return processContent$suspendImpl(this, ctx, continuation);
    }

    @Nullable
    public Object interProceduralAnalyze(@NotNull FactKey<M, N, A> factKey, @NotNull CTX ctx, @NotNull Continuation<? super CTX> continuation) {
        return interProceduralAnalyze$suspendImpl(this, factKey, ctx, continuation);
    }

    @Nullable
    public abstract Object normalFlowFunction(@NotNull CTX ctx, N n, N n2, A a, @NotNull AtomicBoolean atomicBoolean, @NotNull Continuation<? super A> continuation);

    public abstract A callEntryFlowFunction(@NotNull CTX ctx, M m, N n, N n2, A a);

    @NotNull
    public abstract InvokeResult<M, A, R> callExitFlowFunction(@NotNull CTX ctx, A a, M m, A a2, @NotNull CTX ctx2, N n, N n2, boolean z);

    @Nullable
    public Object returnFlowFunction(@NotNull CTX ctx, N n, A a, @NotNull Continuation<? super A> continuation) {
        return returnFlowFunction$suspendImpl(this, ctx, n, a, continuation);
    }

    public abstract boolean isAnalyzable(M m, A a);

    @NotNull
    public abstract DirectedGraph<N> getCfg(M m, boolean z);

    public ForwardInterProceduralAnalysis() {
        this(null, 1, null);
    }

    public /* synthetic */ ForwardInterProceduralAnalysis(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Companion.getClass().getSimpleName() : str);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ForwardInterProceduralAnalysis(@NotNull String name) {
        super(false);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.progressBarExt = new ProgressBarExt(0, 0, 3, null);
        this.numberThreads = OS.INSTANCE.getMaxThreadNum();
        this.staticFieldTrackingMode = StaticFieldTrackingMode.ContextFlowSensitive;
        this.reachableMethods = new ConcurrentHashSet<>();
        this.progressBarVolume = 100;
        this.contextStateId = new AtomicLong(0L);
        this.dataFlowInterProceduralCalleeTimeOut = -1;
        this.dataFlowInterProceduralCalleeDepChainMaxNum = -1L;
        this.transformStmtTotalCount = new AtomicInteger();
        Set<M> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        Intrinsics.checkNotNullExpressionValue(setNewConcurrentHashSet, "newConcurrentHashSet(...)");
        this.curAnalysingMethods = setNewConcurrentHashSet;
    }

    protected final long getTimeOutDuration() {
        return this.timeOutDuration;
    }

    protected final void setTimeOutDuration(long j) {
        this.timeOutDuration = j;
    }

    protected final boolean getTimeOutOn() {
        return this.timeOutOn;
    }

    protected final void setTimeOutOn(boolean z) {
        this.timeOutOn = z;
    }

    public final boolean isTimeout() {
        return this.isTimeout;
    }

    @NotNull
    public final ProgressBarExt getProgressBarExt() {
        return this.progressBarExt;
    }

    @NotNull
    public ProgressBarExt.DefaultProcessInfoRenderer wrapperCustom(@NotNull ProgressBar $this$wrapperCustom) {
        Intrinsics.checkNotNullParameter($this$wrapperCustom, "<this>");
        return (ProgressBarExt.DefaultProcessInfoRenderer) ProgressBarExtKt.wrapper($this$wrapperCustom, (v1, v2) -> {
            return wrapperCustom$lambda$0(r1, v1, v2);
        });
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$wrapperCustom$1$1] */
    private static final ForwardInterProceduralAnalysis$wrapperCustom$1$1 wrapperCustom$lambda$0(final ForwardInterProceduralAnalysis this$0, final ProgressBar processBar, final ProgressBarRenderer render) {
        Intrinsics.checkNotNullParameter(processBar, "processBar");
        Intrinsics.checkNotNullParameter(render, "render");
        return new ProgressBarExt.DefaultProcessInfoRenderer(processBar, render) { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$wrapperCustom$1$1
            @Override // cn.sast.idfa.progressbar.ProgressBarExt.DefaultProcessInfoRenderer
            public String getExtraMessage() {
                return super.getExtraMessage() + (((ForwardInterProceduralAnalysis) this$0).limitedAnalytics ? " !" : "");
            }
        };
    }

    public int getNumberThreads() {
        return this.numberThreads;
    }

    public void setNumberThreads(int i) {
        this.numberThreads = i;
    }

    @NotNull
    public StaticFieldTrackingMode getStaticFieldTrackingMode() {
        return this.staticFieldTrackingMode;
    }

    public void setStaticFieldTrackingMode(@NotNull StaticFieldTrackingMode staticFieldTrackingMode) {
        Intrinsics.checkNotNullParameter(staticFieldTrackingMode, "<set-?>");
        this.staticFieldTrackingMode = staticFieldTrackingMode;
    }

    @NotNull
    public final ConcurrentHashSet<M> getReachableMethods() {
        return this.reachableMethods;
    }

    @Nullable
    public final HashMutableDirectedGraph<M> getDirectedGraph() {
        return this.directedGraph;
    }

    public final void setDirectedGraph(@Nullable HashMutableDirectedGraph<M> hashMutableDirectedGraph) {
        this.directedGraph = hashMutableDirectedGraph;
    }

    public void cacheConfig() {
    }

    public int getProgressBarVolume() {
        return this.progressBarVolume;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public CTX newContext(@NotNull DirectedGraph<N> directedGraph, M m, A a, boolean z) {
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        programRepresentation().setOwnerStatement((Iterable) directedGraph, (Iterable<? extends N>) m);
        AIContext aIContext = (CTX) makeContext(m, directedGraph, a, false, z);
        aIContext.setBottomValue(bottomValue());
        List heads = directedGraph.getHeads();
        Iterator it = directedGraph.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            if (heads.contains(next)) {
                Object entryValue = aIContext.getEntryValue();
                Intrinsics.checkNotNull(entryValue);
                aIContext.setValueBefore(next, entryValue);
            } else {
                aIContext.setValueBefore(next, bottomValue());
            }
        }
        aIContext.initworklist();
        aIContext.setExitValue(bottomValue());
        return aIContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v17, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r6v0, types: [cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis<M, N, A, R, CTX extends cn.sast.idfa.analysis.Context<M, N, A>>] */
    /* JADX WARN: Type inference failed for: r7v0, types: [CTX extends cn.sast.idfa.analysis.Context<M, N, A>, cn.sast.idfa.analysis.Context, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <M, N, A, R, CTX extends cn.sast.idfa.analysis.Context<M, N, A>> java.lang.Object computeInValue$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis<M, N, A, R, CTX> r6, CTX r7, N r8, kotlin.coroutines.Continuation<? super A> r9) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.computeInValue$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.Context, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object computeEntryValue$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, Continuation<? super A> continuation) {
        Object entryValue = ctx.getEntryValue();
        Intrinsics.checkNotNull(entryValue);
        return entryValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public A computeExitValue(@NotNull CTX ctx) {
        Intrinsics.checkNotNullParameter(ctx, "context");
        DirectedGraph controlFlowGraph = ctx.getControlFlowGraph();
        Object objBottomValue = bottomValue();
        for (Object obj : controlFlowGraph.getTails()) {
            objBottomValue = meet(objBottomValue, ctx.getEdgeValue(obj, obj));
        }
        return (A) objBottomValue;
    }

    public A initCallEdgeValue(@NotNull CTX ctx, N n, M m, A a, A a2) {
        Intrinsics.checkNotNullParameter(ctx, "currentContext");
        return a2;
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object evalCall$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, M m, N n, N n2, A a, Continuation<? super InvokeResult<M, A, R>> continuation) {
        return null;
    }

    @NotNull
    public final RecCoroutineLoadingCache<FactKey<M, N, A>, CTX> getCache() {
        RecCoroutineLoadingCache<FactKey<M, N, A>, CTX> recCoroutineLoadingCache = this.cache;
        if (recCoroutineLoadingCache != null) {
            return recCoroutineLoadingCache;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cache");
        return null;
    }

    public final void setCache(@NotNull RecCoroutineLoadingCache<FactKey<M, N, A>, CTX> recCoroutineLoadingCache) {
        Intrinsics.checkNotNullParameter(recCoroutineLoadingCache, "<set-?>");
        this.cache = recCoroutineLoadingCache;
    }

    public final int getDataFlowInterProceduralCalleeTimeOut() {
        return this.dataFlowInterProceduralCalleeTimeOut;
    }

    public final void setDataFlowInterProceduralCalleeTimeOut(int i) {
        this.dataFlowInterProceduralCalleeTimeOut = i;
    }

    public final long getDataFlowInterProceduralCalleeDepChainMaxNum() {
        return this.dataFlowInterProceduralCalleeDepChainMaxNum;
    }

    public final void setDataFlowInterProceduralCalleeDepChainMaxNum(long j) {
        this.dataFlowInterProceduralCalleeDepChainMaxNum = j;
    }

    public final void init(@NotNull CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        setCache(FastCacheImpl.INSTANCE.buildRecCoroutineLoadingCache(scope, ForwardInterProceduralAnalysis::init$lambda$1, new C00472(this, null)));
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u0010��\u001a\u0002H\u0001\"\u001a\b��\u0010\u0001*\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u0005* \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u0002H\u00010\u00062\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007H\n"}, d2 = {"<anonymous>", "CTX", "Lcn/sast/idfa/analysis/Context;", "M", "N", "A", "Lcom/feysh/corax/cache/coroutines/RecCoroutineLoadingCache;", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$FactKey;", "key"})
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {231}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$init$2")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$init$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$init$2.class */
    static final class C00472 extends SuspendLambda implements Function3<RecCoroutineLoadingCache<FactKey<M, N, A>, CTX>, FactKey<M, N, A>, Continuation<? super CTX>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00472(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super C00472> continuation) {
            super(3, continuation);
            this.this$0 = forwardInterProceduralAnalysis;
        }

        public final Object invoke(RecCoroutineLoadingCache<FactKey<M, N, A>, CTX> recCoroutineLoadingCache, FactKey<M, N, A> factKey, Continuation<? super CTX> continuation) {
            C00472 c00472 = new C00472(this.this$0, continuation);
            c00472.L$0 = factKey;
            return c00472.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    FactKey<M, N, A> factKey = (FactKey) this.L$0;
                    M method = factKey.getMethod();
                    A in = factKey.getIn();
                    Context contextNewContext = this.this$0.newContext(this.this$0.getCfg(method, factKey.isAnalyzable()), method, in, factKey.isAnalyzable());
                    this.label = 1;
                    Object objInterProceduralAnalyze = this.this$0.interProceduralAnalyze(factKey, contextNewContext, (Continuation) this);
                    if (objInterProceduralAnalyze == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objInterProceduralAnalyze;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure(obj);
                    return obj;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private static final Object[] init$lambda$1(Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Object[]{it};
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v14 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v14 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:355)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v18 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v18 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v23 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v23 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:358)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r10v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:358)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r10v5 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v5 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:358)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r18v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r18v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r18v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r18v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r19v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r19v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r19v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r19v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to set immutable type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to set immutable type for var: r10v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:358)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:70)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:100)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:100)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 18, insn: 0x03f2: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r18 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('callEdgeValue' java.lang.Object)]), block:B:96:0x03c3 */
    /* JADX WARN: Not initialized variable reg: 19, insn: 0x0400: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r19 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('isAnalyzable' boolean)]), block:B:96:0x03c3 */
    static /* synthetic */ <M, N, A, R, CTX extends cn.sast.idfa.analysis.Context<M, N, A>> java.lang.Object processCallCoroutine$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis<M, N, A, R, CTX> r10, CTX r11, M r12, N r13, N r14, A r15, kotlin.coroutines.Continuation<? super cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R>> r16) {
        /*
            Method dump skipped, instructions count: 1181
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.processCallCoroutine$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.Context, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object processCallCoroutine$lambda$3(Object $callee, Context $currentContext, Object $node) {
        return "skip recursive await " + $callee + " at " + $currentContext.getMethod() + ": at line " + $node;
    }

    private static final Object processCallCoroutine$lambda$4(Object $callee, Object $node, Context $currentContext) {
        return "An error occurred when analyzing callee method: " + $callee + " at " + $node + " in " + $currentContext.getMethod();
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object postCallAtCallSite$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, N n, N n2, InvokeResult<M, A, R> invokeResult, Continuation<? super InvokeResult<M, A, R>> continuation) {
        return invokeResult;
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object prevCallFunction$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, M m, N n, N n2, A a, Continuation<? super A> continuation) {
        return a;
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object processAndReturnResult$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, N n, N n2, Set<? extends M> set, A a, Continuation<? super Map<M, ? extends Deferred<InvokeResult<M, A, R>>>> continuation) {
        Set<? extends M> $this$associateWith$iv = set;
        LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            result$iv.put(element$iv$iv, BuildersKt.async$default(CoroutineScopeKt.CoroutineScope(continuation.getContext()), (CoroutineContext) null, (CoroutineStart) null, new ForwardInterProceduralAnalysis$processAndReturnResult$2$1(forwardInterProceduralAnalysis, ctx, element$iv$iv, n, n2, a, null), 3, (Object) null));
        }
        return result$iv;
    }

    private final A getBottom() {
        return bottomValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r0v107, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v121, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v123, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v125, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v18, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v32, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v39, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v41, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v44, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v52, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v66, types: [cn.sast.idfa.analysis.ForwardInterProceduralAnalysis] */
    /* JADX WARN: Type inference failed for: r0v88, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <M, N, A, R, CTX extends cn.sast.idfa.analysis.Context<M, N, A>> java.lang.Object processContent$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis<M, N, A, R, CTX> r10, CTX r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws kotlin.NoWhenBranchMatchedException {
        /*
            Method dump skipped, instructions count: 873
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.processContent$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��*\u0004\b\u0005\u0010\u0001*\u0004\b\u0006\u0010\u0002*\u0004\b\u0007\u0010\u00032\u00020\u0004B\u001f\u0012\u0006\u0010\u0005\u001a\u00028\u0007\u0012\u0006\u0010\u0006\u001a\u00028\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0014\u001a\u00028\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0015\u001a\u00028\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\bHÆ\u0003J>\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0005\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u00070��2\b\b\u0002\u0010\u0005\u001a\u00028\u00072\b\b\u0002\u0010\u0006\u001a\u00028\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0005\u001a\u00028\u0007¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u00028\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$FactKey;", "M", "N", "A", "", "in", "method", "isAnalyzable", "", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Z)V", "getIn", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMethod", "()Z", "accessTimes", "Ljava/util/concurrent/atomic/AtomicInteger;", "getAccessTimes", "()Ljava/util/concurrent/atomic/AtomicInteger;", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Z)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$FactKey;", "equals", "other", "hashCode", "", "toString", "", "corax-idfa-framework"})
    /* loaded from: ForwardInterProceduralAnalysis$FactKey.class */
    public static final class FactKey<M, N, A> {
        private final A in;
        private final M method;
        private final boolean isAnalyzable;

        @NotNull
        private final AtomicInteger accessTimes = new AtomicInteger(0);

        public final A component1() {
            return this.in;
        }

        public final M component2() {
            return this.method;
        }

        public final boolean component3() {
            return this.isAnalyzable;
        }

        @NotNull
        public final FactKey<M, N, A> copy(A a, M m, boolean isAnalyzable) {
            return new FactKey<>(a, m, isAnalyzable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FactKey copy$default(FactKey factKey, Object obj, Object obj2, boolean z, int i, Object obj3) {
            A a = obj;
            if ((i & 1) != 0) {
                a = factKey.in;
            }
            M m = obj2;
            if ((i & 2) != 0) {
                m = factKey.method;
            }
            if ((i & 4) != 0) {
                z = factKey.isAnalyzable;
            }
            return factKey.copy(a, m, z);
        }

        @NotNull
        public String toString() {
            return "FactKey(in=" + this.in + ", method=" + this.method + ", isAnalyzable=" + this.isAnalyzable + ")";
        }

        public int hashCode() {
            int result = this.in == null ? 0 : this.in.hashCode();
            return (((result * 31) + (this.method == null ? 0 : this.method.hashCode())) * 31) + Boolean.hashCode(this.isAnalyzable);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FactKey)) {
                return false;
            }
            FactKey factKey = (FactKey) other;
            return Intrinsics.areEqual(this.in, factKey.in) && Intrinsics.areEqual(this.method, factKey.method) && this.isAnalyzable == factKey.isAnalyzable;
        }

        public FactKey(A a, M m, boolean isAnalyzable) {
            this.in = a;
            this.method = m;
            this.isAnalyzable = isAnalyzable;
        }

        public final A getIn() {
            return this.in;
        }

        public final M getMethod() {
            return this.method;
        }

        public final boolean isAnalyzable() {
            return this.isAnalyzable;
        }

        @NotNull
        public final AtomicInteger getAccessTimes() {
            return this.accessTimes;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r0v11, types: [cn.sast.idfa.analysis.Context] */
    /* JADX WARN: Type inference failed for: r0v31, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <M, N, A, R, CTX extends cn.sast.idfa.analysis.Context<M, N, A>> java.lang.Object interProceduralAnalyze$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis<M, N, A, R, CTX> r6, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<M, N, A> r7, CTX r8, kotlin.coroutines.Continuation<? super CTX> r9) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.interProceduralAnalyze$suspendImpl(cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$FactKey, cn.sast.idfa.analysis.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object processMethod(M m, @NotNull Continuation<? super Unit> continuation) {
        int dataFlowInterProceduralCalleeTimeOut = this.dataFlowInterProceduralCalleeTimeOut;
        FactKey key = new FactKey(boundaryValue(m), m, true);
        Object objSupervisorScope = SupervisorKt.supervisorScope(new C00512(this, key, dataFlowInterProceduralCalleeTimeOut, m, null), continuation);
        return objSupervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSupervisorScope : Unit.INSTANCE;
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010��\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {470, 480}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$0", "L$1"}, n = {"$this$supervisorScope", "$this$supervisorScope", "taskTimer"}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processMethod$2")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processMethod$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$processMethod$2.class */
    static final class C00512 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        final /* synthetic */ FactKey<M, N, A> $key;
        final /* synthetic */ int $dataFlowInterProceduralCalleeTimeOut;
        final /* synthetic */ M $method;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00512(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, FactKey<M, N, A> factKey, int $dataFlowInterProceduralCalleeTimeOut, M m, Continuation<? super C00512> continuation) {
            super(2, continuation);
            this.this$0 = forwardInterProceduralAnalysis;
            this.$key = factKey;
            this.$dataFlowInterProceduralCalleeTimeOut = $dataFlowInterProceduralCalleeTimeOut;
            this.$method = m;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> c00512 = new C00512(this.this$0, this.$key, this.$dataFlowInterProceduralCalleeTimeOut, this.$method, continuation);
            c00512.L$0 = value;
            return c00512;
        }

        public final Object invoke(CoroutineScope p1, Continuation<Object> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00a4 A[Catch: CancellationException -> 0x00f7, Throwable -> 0x016a, all -> 0x0199, TRY_LEAVE, TryCatch #3 {CancellationException -> 0x00f7, Throwable -> 0x016a, blocks: (B:18:0x00a4, B:25:0x00dd, B:24:0x00d7), top: B:63:0x0009, outer: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00ed  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 440
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.C00512.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final Unit invokeSuspend$lambda$0(Deferred $deferred) {
            $deferred.cancel(new CancellationException("timeout"));
            return Unit.INSTANCE;
        }

        private static final Object invokeSuspend$lambda$2() {
            return "Internal error: coroutineContext.job is not active.";
        }

        private static final Object invokeSuspend$lambda$3(Object $method) {
            return "Cancel analyze method: " + $method + ".";
        }

        private static final Object invokeSuspend$lambda$4(Object $method, CancellationException $cancel) {
            return "Cancel analyze: " + $method + ". cause: " + $cancel.getMessage();
        }

        private static final Object invokeSuspend$lambda$5(Object $method) {
            return "An error occurred when analyzing method: " + $method;
        }
    }

    @NotNull
    public Pair<Job, ProgressBarExt.DefaultProcessInfoRenderer> doAnalyze(@NotNull CoroutineScope scope, @NotNull Collection<? extends M> collection) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(collection, "entries");
        logger.info(() -> {
            return doAnalyze$lambda$6(r1);
        });
        init(scope);
        int size = collection.size();
        AtomicInteger counter = new AtomicInteger();
        ProgressBarExt.DefaultProcessInfoRenderer progressBarRenderer = !ExtSettings.INSTANCE.getEnableProcessBar() ? null : wrapperCustom(ProgressBarExt.getProgressBar$default(this.progressBarExt, "e", size, null, null, 12, null));
        if (progressBarRenderer != null) {
            progressBarRenderer.setSplitLines(20L);
        }
        Semaphore semaphore = SemaphoreKt.Semaphore$default(Math.max(getNumberThreads() * 4, 4), 0, 2, (Object) null);
        Collection<? extends M> $this$map$iv = collection;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(BuildersKt.launch$default(scope, new CoroutineName("process-" + item$iv$iv), (CoroutineStart) null, new ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1(semaphore, counter, this, item$iv$iv, size, progressBarRenderer, null), 2, (Object) null));
        }
        List jobs = (List) destination$iv$iv;
        return TuplesKt.to(BuildersKt.launch$default(scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(jobs, progressBarRenderer, this, null), 3, (Object) null), progressBarRenderer);
    }

    private static final Object doAnalyze$lambda$6(ForwardInterProceduralAnalysis this$0) {
        return "num threads: " + this$0.getNumberThreads();
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {534}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalyze$2")
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalyze$2, reason: invalid class name */
    /* loaded from: ForwardInterProceduralAnalysis$doAnalyze$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ List<Job> $jobs;
        final /* synthetic */ ProgressBarExt.DefaultProcessInfoRenderer $progressBarRenderer;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<? extends Job> list, ProgressBarExt.DefaultProcessInfoRenderer $progressBarRenderer, ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$jobs = list;
            this.$progressBarRenderer = $progressBarRenderer;
            this.this$0 = forwardInterProceduralAnalysis;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass2(this.$jobs, this.$progressBarRenderer, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (AwaitKt.joinAll(this.$jobs, (Continuation) this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ProgressBarExt.DefaultProcessInfoRenderer defaultProcessInfoRenderer = this.$progressBarRenderer;
            if (defaultProcessInfoRenderer != null) {
                defaultProcessInfoRenderer.close();
            }
            KLogger kLogger = ForwardInterProceduralAnalysis.logger;
            ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis = this.this$0;
            kLogger.info(() -> {
                return invokeSuspend$lambda$0(r1);
            });
            return Unit.INSTANCE;
        }

        private static final Object invokeSuspend$lambda$0(ForwardInterProceduralAnalysis this$0) {
            return RecCoroutineCacheImplKt.pp(this$0.getCache().getCacheStats());
        }
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    public void doAnalysis(@NotNull Collection<? extends M> collection) throws InterruptedException {
        Intrinsics.checkNotNullParameter(collection, "entries");
        ExecutorService interruptableExecutor = new InterruptableExecutor(getNumberThreads(), Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        ExecutorCoroutineDispatcher coroutineCtx = ExecutorsKt.from(interruptableExecutor);
        CoroutineScope scope = CoroutineScopeKt.CoroutineScope(coroutineCtx.plus(new CoroutineName("Scope-AI-analysis")));
        int size = collection.size();
        Ref.ObjectRef progressBarRenderer = new Ref.ObjectRef();
        BuildersKt.runBlocking$default((CoroutineContext) null, new C00461(size, this, scope, collection, progressBarRenderer, null), 1, (Object) null);
        if (!interruptableExecutor.isFinished()) {
            logger.warn(ForwardInterProceduralAnalysis::doAnalysis$lambda$8);
            interruptableExecutor.awaitCompletion(6000L, TimeUnit.SECONDS);
            logger.info(ForwardInterProceduralAnalysis::doAnalysis$lambda$9);
        }
        ProgressBarExt.DefaultProcessInfoRenderer $this$doAnalysis_u24lambda_u2411 = (ProgressBarExt.DefaultProcessInfoRenderer) progressBarRenderer.element;
        if ($this$doAnalysis_u24lambda_u2411 != null) {
            logger.warn(() -> {
                return doAnalysis$lambda$11$lambda$10(r1);
            });
        }
        interruptableExecutor.interrupt();
        interruptableExecutor.shutdown();
        if (!getCache().validateAfterFinished() || UsefulMetrics.Companion.getMetrics().isMemoryThresholdTriggered()) {
            getCache().cleanUp();
        }
        System.gc();
        Thread.sleep(500L);
        logger.info(ForwardInterProceduralAnalysis::doAnalysis$lambda$12);
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {553}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$3", "L$4", "L$5", "I$0"}, n = {"$this$bracket$iv", "msg$iv", "startTime$iv", "res$iv", "startTime", "alreadyLogged$iv"}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1")
    @SourceDebugExtension({"SMAP\nForwardInterProceduralAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalysis$1\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,678:1\n54#2,19:679\n*S KotlinDebug\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalysis$1\n*L\n549#1:679,19\n*E\n"})
    /* renamed from: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ForwardInterProceduralAnalysis$doAnalysis$1.class */
    static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int I$0;
        int label;
        final /* synthetic */ int $size;
        final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
        final /* synthetic */ CoroutineScope $scope;
        final /* synthetic */ Collection<M> $entries;
        final /* synthetic */ Ref.ObjectRef<ProgressBarExt.DefaultProcessInfoRenderer> $progressBarRenderer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C00461(int $size, ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CoroutineScope $scope, Collection<? extends M> collection, Ref.ObjectRef<ProgressBarExt.DefaultProcessInfoRenderer> objectRef, Continuation<? super C00461> continuation) {
            super(2, continuation);
            this.$size = $size;
            this.this$0 = forwardInterProceduralAnalysis;
            this.$scope = $scope;
            this.$entries = collection;
            this.$progressBarRenderer = objectRef;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new C00461(this.$size, this.this$0, this.$scope, this.$entries, this.$progressBarRenderer, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            int i;
            Ref.ObjectRef objectRef;
            LocalDateTime startTime;
            Ref.ObjectRef res$iv;
            LocalDateTime startTime$iv;
            ForwardInterProceduralAnalysis forwardInterProceduralAnalysis;
            final String msg$iv;
            LoggerWithLogMethod $this$bracket$iv;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                try {
                    switch (this.label) {
                        case PointsToGraphKt.pathStrictMod /* 0 */:
                            ResultKt.throwOnFailure($result);
                            $this$bracket$iv = LoggingKt.info(ForwardInterProceduralAnalysis.logger);
                            msg$iv = "Analyze for " + this.$size + " entry methods";
                            forwardInterProceduralAnalysis = this.this$0;
                            CoroutineScope coroutineScope = this.$scope;
                            Collection<M> collection = this.$entries;
                            Ref.ObjectRef<ProgressBarExt.DefaultProcessInfoRenderer> objectRef2 = this.$progressBarRenderer;
                            $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$1
                                public final Object invoke() {
                                    return "Started: " + msg$iv;
                                }
                            });
                            startTime$iv = LocalDateTime.now();
                            i = 0;
                            res$iv = new Ref.ObjectRef();
                            res$iv.element = Maybe.Companion.empty();
                            objectRef = res$iv;
                            startTime = LocalDateTime.now();
                            Pair<Job, ProgressBarExt.DefaultProcessInfoRenderer> pairDoAnalyze = forwardInterProceduralAnalysis.doAnalyze(coroutineScope, collection);
                            Job job = (Job) pairDoAnalyze.component1();
                            ProgressBarExt.DefaultProcessInfoRenderer render = (ProgressBarExt.DefaultProcessInfoRenderer) pairDoAnalyze.component2();
                            objectRef2.element = render;
                            this.L$0 = $this$bracket$iv;
                            this.L$1 = msg$iv;
                            this.L$2 = forwardInterProceduralAnalysis;
                            this.L$3 = startTime$iv;
                            this.L$4 = res$iv;
                            this.L$5 = startTime;
                            this.L$6 = objectRef;
                            this.I$0 = 0;
                            this.label = 1;
                            if (job.join(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            i = this.I$0;
                            objectRef = (Ref.ObjectRef) this.L$6;
                            startTime = (LocalDateTime) this.L$5;
                            res$iv = (Ref.ObjectRef) this.L$4;
                            startTime$iv = (LocalDateTime) this.L$3;
                            forwardInterProceduralAnalysis = (ForwardInterProceduralAnalysis) this.L$2;
                            msg$iv = (String) this.L$1;
                            $this$bracket$iv = (LoggerWithLogMethod) this.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ForwardInterProceduralAnalysis forwardInterProceduralAnalysis2 = forwardInterProceduralAnalysis;
                    LocalDateTime localDateTime = startTime;
                    ForwardInterProceduralAnalysis.logger.info(() -> {
                        return invokeSuspend$lambda$2$lambda$1(r1, r2);
                    });
                    objectRef.element = new Maybe(Unit.INSTANCE);
                    ((Maybe) res$iv.element).getOrThrow();
                    if (((Maybe) res$iv.element).getHasValue()) {
                        final LocalDateTime localDateTime2 = startTime$iv;
                        final String str = msg$iv;
                        final Ref.ObjectRef objectRef3 = res$iv;
                        $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$2
                            public final Object invoke() {
                                LocalDateTime localDateTime3 = localDateTime2;
                                Intrinsics.checkNotNull(localDateTime3);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime3);
                                String str2 = str;
                                Result.Companion companion = Result.Companion;
                                Object it = Result.constructor-impl(((Maybe) objectRef3.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str2 + " " + Result.box-impl(it);
                            }
                        });
                    } else {
                        final LocalDateTime localDateTime3 = startTime$iv;
                        final String str2 = msg$iv;
                        $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$3
                            public final Object invoke() {
                                LocalDateTime localDateTime4 = localDateTime3;
                                Intrinsics.checkNotNull(localDateTime4);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime4) + "): " + str2 + " <Nothing>";
                            }
                        });
                    }
                    return Unit.INSTANCE;
                } catch (Throwable t$iv) {
                    final LocalDateTime localDateTime4 = startTime$iv;
                    final String str3 = msg$iv;
                    $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$4
                        public final Object invoke() {
                            LocalDateTime localDateTime5 = localDateTime4;
                            Intrinsics.checkNotNull(localDateTime5);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime5);
                            String str4 = str3;
                            Result.Companion companion = Result.Companion;
                            Object it = Result.constructor-impl(ResultKt.createFailure(t$iv));
                            return "Finished (in " + strElapsedSecFrom + "): " + str4 + " :: EXCEPTION :: " + Result.box-impl(it);
                        }
                    });
                    throw t$iv;
                }
            } catch (Throwable th) {
                if (i == 0) {
                    if (((Maybe) res$iv.element).getHasValue()) {
                        final LocalDateTime localDateTime5 = startTime$iv;
                        final String str4 = msg$iv;
                        final Ref.ObjectRef objectRef4 = res$iv;
                        $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$5
                            public final Object invoke() {
                                LocalDateTime localDateTime6 = localDateTime5;
                                Intrinsics.checkNotNull(localDateTime6);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime6);
                                String str5 = str4;
                                Result.Companion companion = Result.Companion;
                                Object it = Result.constructor-impl(((Maybe) objectRef4.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str5 + " " + Result.box-impl(it);
                            }
                        });
                    } else {
                        final LocalDateTime localDateTime6 = startTime$iv;
                        final String str5 = msg$iv;
                        $this$bracket$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$6
                            public final Object invoke() {
                                LocalDateTime localDateTime7 = localDateTime6;
                                Intrinsics.checkNotNull(localDateTime7);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime7) + "): " + str5 + " <Nothing>";
                            }
                        });
                    }
                }
                throw th;
            }
        }

        private static final Object invokeSuspend$lambda$2$lambda$1(ForwardInterProceduralAnalysis this$0, LocalDateTime $startTime) {
            AtomicInteger atomicInteger = this$0.transformStmtTotalCount;
            Intrinsics.checkNotNull($startTime);
            return "Dataflow has transformed a total of " + atomicInteger + " code stmts in " + LoggingKt.elapsedSecFrom($startTime);
        }
    }

    private static final Object doAnalysis$lambda$8() {
        return "AIAnalysis: There are still tasks running in the executor. waiting ...";
    }

    private static final Object doAnalysis$lambda$9() {
        return "AIAnalysis: Done";
    }

    private static final Object doAnalysis$lambda$11$lambda$10(ProgressBarExt.DefaultProcessInfoRenderer $this_run) {
        return "Maximum memory usage: " + $this_run.getMaxUsedMemoryText() + " G";
    }

    private static final Object doAnalysis$lambda$12() {
        return "After Analyze: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    public A callLocalFlowFunction(@NotNull Context<SootMethod, soot.Unit, A> context, @NotNull soot.Unit node, @NotNull soot.Unit succ, A a) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        return bottomValue();
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��*\u0004\b\u0005\u0010\u0001*\u0004\b\u0006\u0010\u0002*\u0004\b\u0007\u0010\u00032\u00020\u0004B!\u0012\u0006\u0010\u0005\u001a\u00028\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000f\u001a\u00028\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0010\u001a\u00028\u0006HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ@\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0005\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u00070��2\b\b\u0002\u0010\u0005\u001a\u00028\u00052\b\b\u0002\u0010\u0006\u001a\u00028\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0007HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0013\u0010\u0005\u001a\u00028\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u00028\u0006¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "M", "A", "R", "", "callee", "callSiteOutAbstract", "resultValue", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getCallee", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getCallSiteOutAbstract", "getResultValue", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "equals", "", "other", "hashCode", "", "toString", "", "corax-idfa-framework"})
    /* loaded from: ForwardInterProceduralAnalysis$InvokeResult.class */
    public static final class InvokeResult<M, A, R> {
        private final M callee;
        private final A callSiteOutAbstract;

        @Nullable
        private final R resultValue;

        public final M component1() {
            return this.callee;
        }

        public final A component2() {
            return this.callSiteOutAbstract;
        }

        @Nullable
        public final R component3() {
            return this.resultValue;
        }

        @NotNull
        public final InvokeResult<M, A, R> copy(M m, A a, @Nullable R r) {
            return new InvokeResult<>(m, a, r);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ InvokeResult copy$default(InvokeResult invokeResult, Object obj, Object obj2, Object obj3, int i, Object obj4) {
            M m = obj;
            if ((i & 1) != 0) {
                m = invokeResult.callee;
            }
            A a = obj2;
            if ((i & 2) != 0) {
                a = invokeResult.callSiteOutAbstract;
            }
            R r = obj3;
            if ((i & 4) != 0) {
                r = invokeResult.resultValue;
            }
            return invokeResult.copy(m, a, r);
        }

        @NotNull
        public String toString() {
            return "InvokeResult(callee=" + this.callee + ", callSiteOutAbstract=" + this.callSiteOutAbstract + ", resultValue=" + this.resultValue + ")";
        }

        public int hashCode() {
            int result = this.callee == null ? 0 : this.callee.hashCode();
            return (((result * 31) + (this.callSiteOutAbstract == null ? 0 : this.callSiteOutAbstract.hashCode())) * 31) + (this.resultValue == null ? 0 : this.resultValue.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvokeResult)) {
                return false;
            }
            InvokeResult invokeResult = (InvokeResult) other;
            return Intrinsics.areEqual(this.callee, invokeResult.callee) && Intrinsics.areEqual(this.callSiteOutAbstract, invokeResult.callSiteOutAbstract) && Intrinsics.areEqual(this.resultValue, invokeResult.resultValue);
        }

        public InvokeResult(M m, A a, @Nullable R r) {
            this.callee = m;
            this.callSiteOutAbstract = a;
            this.resultValue = r;
        }

        public final M getCallee() {
            return this.callee;
        }

        public final A getCallSiteOutAbstract() {
            return this.callSiteOutAbstract;
        }

        @Nullable
        public final R getResultValue() {
            return this.resultValue;
        }
    }

    static /* synthetic */ <M, N, A, R, CTX extends Context<M, N, A>> Object returnFlowFunction$suspendImpl(ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, CTX ctx, N n, A a, Continuation<? super A> continuation) {
        return a;
    }

    @Nullable
    public A wideningFunction(@NotNull CTX ctx, N n, N n2, A a) {
        Intrinsics.checkNotNullParameter(ctx, "context");
        return null;
    }

    @NotNull
    public FixPointStatus hasChange(@NotNull CTX ctx, N n, N n2, A a, A a2) {
        Intrinsics.checkNotNullParameter(ctx, "context");
        FixPointStatus.Companion companion = FixPointStatus.Companion;
        boolean hasChange$iv = Intrinsics.areEqual(a, a2);
        return hasChange$iv ? FixPointStatus.HasChange : FixPointStatus.Fixpoint;
    }

    @NotNull
    public Set<M> resolveTargets(M m, N n) {
        return programRepresentation().getCalleesOfCallAt(m, n);
    }

    /* compiled from: ForwardInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-idfa-framework"})
    /* loaded from: ForwardInterProceduralAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$13() {
        return Unit.INSTANCE;
    }
}
