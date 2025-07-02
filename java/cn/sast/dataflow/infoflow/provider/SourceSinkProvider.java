package cn.sast.dataflow.infoflow.provider;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.common.GLB;
import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.dataflow.infoflow.provider.SourceSinkProvider;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.ConfigInfoLogger;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.coroutines.FastCache;
import com.feysh.corax.config.api.BinOp;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.BuiltInField;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.ClassField;
import com.feysh.corax.config.api.Elements;
import com.feysh.corax.config.api.IBinOpExpr;
import com.feysh.corax.config.api.IBoolExpr;
import com.feysh.corax.config.api.IClassDecl;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IFieldDecl;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.IIexGetField;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.IIstSetField;
import com.feysh.corax.config.api.IIstStoreLocal;
import com.feysh.corax.config.api.IJDecl;
import com.feysh.corax.config.api.ILocalVarDecl;
import com.feysh.corax.config.api.IMethodDecl;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.IModelExpressionVisitor;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.IQOpExpr;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.ITaintType;
import com.feysh.corax.config.api.ITriOpExpr;
import com.feysh.corax.config.api.IUnOpExpr;
import com.feysh.corax.config.api.MGlobal;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.MParameter;
import com.feysh.corax.config.api.MReturn;
import com.feysh.corax.config.api.MapKeys;
import com.feysh.corax.config.api.MapValues;
import com.feysh.corax.config.api.MethodConfig;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.ViaProperty;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootMethod;
import soot.jimple.infoflow.data.SootMethodAndClass;
import soot.jimple.infoflow.sourcesSinks.definitions.AccessPathTuple;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkCategory;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinition;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinitionProvider;
import soot.jimple.infoflow.sourcesSinks.definitions.MethodSourceSinkDefinition;
import soot.jimple.infoflow.sourcesSinks.definitions.SourceSinkType;

/* compiled from: SourceSinkProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\u0018�� -2\u00020\u0001:\u0003-./B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0+H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001b0+H\u0016J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0+H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R&\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001eR \u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0016X\u0082\u000e¢\u0006\u0002\n��¨\u00060"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider;", "Lsoot/jimple/infoflow/sourcesSinks/definitions/ISourceSinkDefinitionProvider;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "preAnalysisImpl", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/api/config/PreAnalysisCoroutineScope;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "sourceDefinitions", "Ljava/util/concurrent/ConcurrentHashMap;", "Lsoot/SootMethod;", "Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel;", "getSourceDefinitions", "()Ljava/util/concurrent/ConcurrentHashMap;", "setSourceDefinitions", "(Ljava/util/concurrent/ConcurrentHashMap;)V", "sinkDefinitions", "getSinkDefinitions", "setSinkDefinitions", "checkTypesInSink", "", "Lcom/feysh/corax/config/api/ITaintType;", "getCheckTypesInSink", "()Ljava/util/Set;", "sourceSet", "Lsoot/jimple/infoflow/sourcesSinks/definitions/ISourceSinkDefinition;", "getSourceSet", "setSourceSet", "(Ljava/util/Set;)V", "sinkSet", "getSinkSet", "setSinkSet", "allMethods", "getAllMethods", "setAllMethods", "missClass", "", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSources", "", "getSinks", "Companion", "ModelingConfigImpl", "MethodModel", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSourceSinkProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,514:1\n1619#2:515\n1863#2:516\n1864#2:518\n1620#2:519\n1454#2,5:520\n1246#2,4:526\n1#3:517\n412#4:525\n216#5,2:530\n*S KotlinDebug\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider\n*L\n453#1:515\n453#1:516\n453#1:518\n453#1:519\n466#1:520,5\n471#1:526,4\n453#1:517\n471#1:525\n483#1:530,2\n*E\n"})
/* loaded from: SourceSinkProvider.class */
public final class SourceSinkProvider implements ISourceSinkDefinitionProvider {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final PreAnalysisCoroutineScope preAnalysisImpl;

    @NotNull
    private ConcurrentHashMap<SootMethod, MethodModel> sourceDefinitions;

    @NotNull
    private ConcurrentHashMap<SootMethod, MethodModel> sinkDefinitions;

    @NotNull
    private final Set<ITaintType> checkTypesInSink;

    @NotNull
    private Set<ISourceSinkDefinition> sourceSet;

    @NotNull
    private Set<ISourceSinkDefinition> sinkSet;

    @NotNull
    private Set<ISourceSinkDefinition> allMethods;

    @NotNull
    private Set<Object> missClass;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SourceSinkProvider::logger$lambda$6);

    /* compiled from: SourceSinkProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SourceSinkProvider.kt", l = {450}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "initialize", c = "cn.sast.dataflow.infoflow.provider.SourceSinkProvider")
    /* renamed from: cn.sast.dataflow.infoflow.provider.SourceSinkProvider$initialize$1, reason: invalid class name */
    /* loaded from: SourceSinkProvider$initialize$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SourceSinkProvider.this.initialize((Continuation) this);
        }
    }

    public SourceSinkProvider(@NotNull MainConfig mainConfig, @NotNull PreAnalysisCoroutineScope preAnalysisImpl) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(preAnalysisImpl, "preAnalysisImpl");
        this.mainConfig = mainConfig;
        this.preAnalysisImpl = preAnalysisImpl;
        this.sourceDefinitions = new ConcurrentHashMap<>();
        this.sinkDefinitions = new ConcurrentHashMap<>();
        this.checkTypesInSink = new LinkedHashSet();
        this.sourceSet = new LinkedHashSet();
        this.sinkSet = new LinkedHashSet();
        this.allMethods = new LinkedHashSet();
        this.missClass = new LinkedHashSet();
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    /* compiled from: SourceSinkProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: SourceSinkProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$6() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final ConcurrentHashMap<SootMethod, MethodModel> getSourceDefinitions() {
        return this.sourceDefinitions;
    }

    public final void setSourceDefinitions(@NotNull ConcurrentHashMap<SootMethod, MethodModel> concurrentHashMap) {
        Intrinsics.checkNotNullParameter(concurrentHashMap, "<set-?>");
        this.sourceDefinitions = concurrentHashMap;
    }

    @NotNull
    public final ConcurrentHashMap<SootMethod, MethodModel> getSinkDefinitions() {
        return this.sinkDefinitions;
    }

    public final void setSinkDefinitions(@NotNull ConcurrentHashMap<SootMethod, MethodModel> concurrentHashMap) {
        Intrinsics.checkNotNullParameter(concurrentHashMap, "<set-?>");
        this.sinkDefinitions = concurrentHashMap;
    }

    @NotNull
    public final Set<ITaintType> getCheckTypesInSink() {
        return this.checkTypesInSink;
    }

    /* compiled from: SourceSinkProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0002\b\u0086\u0004\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0098\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2n\u0010\u001d\u001aj\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020#0\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020#0\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00150\u001eH\u0002JE\u0010'\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00192-\u0010\u001d\u001a)\u0012\u0013\u0012\u00110*¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(+\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0,\u0012\u0004\u0012\u00020\u00150)J\u0018\u0010.\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010/\u001a\u000200H\u0002J(\u00101\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,H\u0002J\u001e\u00103\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J9\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u0002062\u001f\u00107\u001a\u001b\u0012\b\u0012\u000609j\u0002`:\u0012\u0004\u0012\u00020\u001508j\u0002`<¢\u0006\u0002\b;2\u0006\u0010/\u001a\u000200H\u0016JZ\u0010=\u001a\u00020\u00152\u0006\u00105\u001a\u0002062\u001f\u00107\u001a\u001b\u0012\b\u0012\u000609j\u0002`:\u0012\u0004\u0012\u00020\u001508j\u0002`<¢\u0006\u0002\b;2\u0006\u0010\u0018\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0017\u0010A\u001a\u0013\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u001508¢\u0006\u0002\b;H\u0016J\\\u0010C\u001a\u00020\u00152\u0006\u00105\u001a\u0002062\u001f\u00107\u001a\u001b\u0012\b\u0012\u000609j\u0002`:\u0012\u0004\u0012\u00020\u001508j\u0002`<¢\u0006\u0002\b;2\u0006\u0010\u0018\u001a\u00020\u00192!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110E¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u001508H\u0016J\b\u0010F\u001a\u00020\u0015H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006G"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$ModelingConfigImpl;", "Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;", "preAnalysis", "Lcom/feysh/corax/config/api/PreAnalysisApi;", "<init>", "(Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider;Lcom/feysh/corax/config/api/PreAnalysisApi;)V", "getPreAnalysis", "()Lcom/feysh/corax/config/api/PreAnalysisApi;", "error", "Lcn/sast/dataflow/util/ConfigInfoLogger;", "getError", "()Lcn/sast/dataflow/util/ConfigInfoLogger;", "fastCache", "Lcom/feysh/corax/cache/coroutines/FastCache;", "getFastCache", "()Lcom/feysh/corax/cache/coroutines/FastCache;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "setSourceSink", "", "method", "Lsoot/SootMethod;", "expr", "Lcom/feysh/corax/config/api/IExpr;", "accessPath", "", "Lcom/feysh/corax/config/api/IClassField;", "cb", "Lkotlin/Function4;", "Lcom/feysh/corax/config/api/MLocal;", "Lkotlin/ParameterName;", "name", "p", "", "baseType", "fields", "fieldTypes", "addSource", "value", "Lkotlin/Function2;", "Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel;", "m", "", "Lcom/feysh/corax/config/api/ITaintType;", "findSourceFromStmt", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "addSink", "sinkKinds", "findSinkFromStmt", "addStmt", "decl", "Lcom/feysh/corax/config/api/IJDecl;", "config", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/MethodConfig;", "Lcom/feysh/corax/config/api/MethodConfigType;", "Lkotlin/ExtensionFunctionType;", "Lcom/feysh/corax/config/api/MethodConfigBlockType;", "check", "Lcom/feysh/corax/config/api/IBoolExpr;", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "eval", "accept", "", "validate", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nSourceSinkProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider$ModelingConfigImpl\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,514:1\n72#2,2:515\n72#2,2:518\n1#3:517\n1#3:520\n1#3:535\n1#3:548\n1863#4,2:521\n1863#4,2:523\n1611#4,9:525\n1863#4:534\n1864#4:536\n1620#4:537\n1611#4,9:538\n1863#4:547\n1864#4:549\n1620#4:550\n*S KotlinDebug\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider$ModelingConfigImpl\n*L\n139#1:515,2\n207#1:518,2\n139#1:517\n207#1:520\n61#1:535\n62#1:548\n300#1:521,2\n323#1:523,2\n61#1:525,9\n61#1:534\n61#1:536\n61#1:537\n62#1:538,9\n62#1:547\n62#1:549\n62#1:550\n*E\n"})
    /* loaded from: SourceSinkProvider$ModelingConfigImpl.class */
    public final class ModelingConfigImpl extends AIAnalysisBaseImpl {

        @NotNull
        private final PreAnalysisApi preAnalysis;

        @NotNull
        private final ConfigInfoLogger error;
        final /* synthetic */ SourceSinkProvider this$0;

        public ModelingConfigImpl(@NotNull SourceSinkProvider this$0, PreAnalysisApi preAnalysis) {
            Intrinsics.checkNotNullParameter(preAnalysis, "preAnalysis");
            this.this$0 = this$0;
            this.preAnalysis = preAnalysis;
            this.error = new ConfigInfoLogger();
        }

        @NotNull
        public PreAnalysisApi getPreAnalysis() {
            return this.preAnalysis;
        }

        @NotNull
        /* renamed from: getError, reason: merged with bridge method [inline-methods] */
        public ConfigInfoLogger m126getError() {
            return this.error;
        }

        @NotNull
        public FastCache getFastCache() {
            return FastCacheImpl.INSTANCE;
        }

        @NotNull
        public CoroutineScope getScope() {
            return GlobalScope.INSTANCE;
        }

        static /* synthetic */ void setSourceSink$default(ModelingConfigImpl modelingConfigImpl, SootMethod sootMethod, IExpr iExpr, List list, Function4 function4, int i, Object obj) throws NoWhenBranchMatchedException {
            if ((i & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            modelingConfigImpl.setSourceSink(sootMethod, iExpr, list, function4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setSourceSink(SootMethod method, IExpr expr, List<? extends IClassField> list, Function4<? super MLocal, ? super String, ? super List<String>, ? super List<String>, Unit> function4) throws NoWhenBranchMatchedException {
            cn.sast.dataflow.infoflow.provider.ModelingConfigImpl.Companion.getAccessPath(method, list, expr, (v1, v2, v3) -> {
                return setSourceSink$lambda$2(r4, v1, v2, v3);
            });
        }

        private static final Unit setSourceSink$lambda$2(Function4 $cb, MLocal op, String baseType, List fields) {
            Intrinsics.checkNotNullParameter(op, "op");
            Intrinsics.checkNotNullParameter(fields, "fields");
            List $this$mapNotNull$iv = fields;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                ClassField classField = (IClassField) element$iv$iv$iv;
                ClassField classField2 = classField instanceof ClassField ? classField : null;
                String fieldName = classField2 != null ? classField2.getFieldName() : null;
                if (fieldName != null) {
                    destination$iv$iv.add(fieldName);
                }
            }
            List fieldSigs = (List) destination$iv$iv;
            List $this$mapNotNull$iv2 = fields;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
                ClassField classField3 = (IClassField) element$iv$iv$iv2;
                ClassField classField4 = classField3 instanceof ClassField ? classField3 : null;
                String fieldType = classField4 != null ? classField4.getFieldType() : null;
                if (fieldType != null) {
                    destination$iv$iv2.add(fieldType);
                }
            }
            List fieldTypes = (List) destination$iv$iv2;
            $cb.invoke(op, baseType, fieldSigs, fieldTypes);
            return Unit.INSTANCE;
        }

        public final void addSource(@NotNull SootMethod method, @NotNull IExpr value, @NotNull Function2<? super MethodModel, ? super Set<? extends ITaintType>, Unit> function2) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(function2, "cb");
            Set sources = (Set) value.accept(new IModelExpressionVisitor<Set<? extends ITaintType>>() { // from class: cn.sast.dataflow.infoflow.provider.SourceSinkProvider$ModelingConfigImpl$addSource$visitor$1

                /* compiled from: SourceSinkProvider.kt */
                @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
                /* loaded from: SourceSinkProvider$ModelingConfigImpl$addSource$visitor$1$WhenMappings.class */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[BinOp.values().length];
                        try {
                            iArr[BinOp.OrSet.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[BinOp.AndSet.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        try {
                            iArr[BinOp.RemoveSet.ordinal()] = 3;
                        } catch (NoSuchFieldError e3) {
                        }
                        try {
                            iArr[BinOp.ContainsSet.ordinal()] = 4;
                        } catch (NoSuchFieldError e4) {
                        }
                        try {
                            iArr[BinOp.HasIntersectionSet.ordinal()] = 5;
                        } catch (NoSuchFieldError e5) {
                        }
                        try {
                            iArr[BinOp.Or.ordinal()] = 6;
                        } catch (NoSuchFieldError e6) {
                        }
                        try {
                            iArr[BinOp.And.ordinal()] = 7;
                        } catch (NoSuchFieldError e7) {
                        }
                        try {
                            iArr[BinOp.StartsWith.ordinal()] = 8;
                        } catch (NoSuchFieldError e8) {
                        }
                        try {
                            iArr[BinOp.EndsWith.ordinal()] = 9;
                        } catch (NoSuchFieldError e9) {
                        }
                        try {
                            iArr[BinOp.Contains.ordinal()] = 10;
                        } catch (NoSuchFieldError e10) {
                        }
                        try {
                            iArr[BinOp.StringEquals.ordinal()] = 11;
                        } catch (NoSuchFieldError e11) {
                        }
                        try {
                            iArr[BinOp.AnyOf.ordinal()] = 12;
                        } catch (NoSuchFieldError e12) {
                        }
                        try {
                            iArr[BinOp.LT.ordinal()] = 13;
                        } catch (NoSuchFieldError e13) {
                        }
                        try {
                            iArr[BinOp.LE.ordinal()] = 14;
                        } catch (NoSuchFieldError e14) {
                        }
                        try {
                            iArr[BinOp.EQ.ordinal()] = 15;
                        } catch (NoSuchFieldError e15) {
                        }
                        try {
                            iArr[BinOp.GE.ordinal()] = 16;
                        } catch (NoSuchFieldError e16) {
                        }
                        try {
                            iArr[BinOp.GT.ordinal()] = 17;
                        } catch (NoSuchFieldError e17) {
                        }
                        try {
                            iArr[BinOp.Add.ordinal()] = 18;
                        } catch (NoSuchFieldError e18) {
                        }
                        try {
                            iArr[BinOp.Sub.ordinal()] = 19;
                        } catch (NoSuchFieldError e19) {
                        }
                        try {
                            iArr[BinOp.Mul.ordinal()] = 20;
                        } catch (NoSuchFieldError e20) {
                        }
                        try {
                            iArr[BinOp.Div.ordinal()] = 21;
                        } catch (NoSuchFieldError e21) {
                        }
                        try {
                            iArr[BinOp.Mod.ordinal()] = 22;
                        } catch (NoSuchFieldError e22) {
                        }
                        try {
                            iArr[BinOp.Xor.ordinal()] = 23;
                        } catch (NoSuchFieldError e23) {
                        }
                        try {
                            iArr[BinOp.BvAnd.ordinal()] = 24;
                        } catch (NoSuchFieldError e24) {
                        }
                        try {
                            iArr[BinOp.BvOr.ordinal()] = 25;
                        } catch (NoSuchFieldError e25) {
                        }
                        try {
                            iArr[BinOp.BvXor.ordinal()] = 26;
                        } catch (NoSuchFieldError e26) {
                        }
                        try {
                            iArr[BinOp.BvShr.ordinal()] = 27;
                        } catch (NoSuchFieldError e27) {
                        }
                        try {
                            iArr[BinOp.BvShl.ordinal()] = 28;
                        } catch (NoSuchFieldError e28) {
                        }
                        try {
                            iArr[BinOp.BvLShr.ordinal()] = 29;
                        } catch (NoSuchFieldError e29) {
                        }
                        try {
                            iArr[BinOp.IsInstanceOf.ordinal()] = 30;
                        } catch (NoSuchFieldError e30) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m131visit(IIexLoad expr) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m132visit(IIexGetField expr) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m133visit(IUnOpExpr expr) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m134visit(ITriOpExpr expr) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m135visit(IQOpExpr expr) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
                }

                /* renamed from: default, reason: not valid java name and merged with bridge method [inline-methods] */
                public Set<ITaintType> m128default(IExpr expr) {
                    Intrinsics.checkNotNullParameter(expr, "expr");
                    return null;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m129visit(IBinOpExpr expr) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter(expr, "expr");
                    Set op1 = (Set) expr.getOp1().accept(this);
                    Set op2 = (Set) expr.getOp2().accept(this);
                    switch (WhenMappings.$EnumSwitchMapping$0[expr.getOp().ordinal()]) {
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            return op1 == null ? op2 : op2 == null ? op1 : SetsKt.plus(op1, op2);
                        case 2:
                            return op1 == null ? op2 : op2 == null ? op1 : CollectionsKt.intersect(op1, op2);
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                            return null;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m130visit(IIexConst expr) {
                    Intrinsics.checkNotNullParameter(expr, "expr");
                    Object obj = expr.getConst();
                    if (obj instanceof Set) {
                        return (Set) obj;
                    }
                    return null;
                }
            });
            if (sources == null) {
                return;
            }
            if (!sources.isEmpty()) {
                ConcurrentMap $this$getOrPut$iv = this.this$0.getSourceDefinitions();
                MethodModel methodModelPutIfAbsent = $this$getOrPut$iv.get(method);
                if (methodModelPutIfAbsent == null) {
                    MethodModel methodModel = new MethodModel(method, MethodSourceSinkDefinition.CallType.MethodCall);
                    methodModelPutIfAbsent = $this$getOrPut$iv.putIfAbsent(method, methodModel);
                    if (methodModelPutIfAbsent == null) {
                        methodModelPutIfAbsent = methodModel;
                    }
                }
                MethodModel m = methodModelPutIfAbsent;
                Intrinsics.checkNotNull(m);
                function2.invoke(m, sources);
            }
        }

        private final void findSourceFromStmt(final SootMethod method, IStmt stmt) {
            stmt.accept(new IModelStmtVisitor<Unit>() { // from class: cn.sast.dataflow.infoflow.provider.SourceSinkProvider$ModelingConfigImpl$findSourceFromStmt$stmtVisitor$1
                /* renamed from: default, reason: not valid java name */
                public /* bridge */ /* synthetic */ Object m148default(IStmt stmt2) {
                    m147default(stmt2);
                    return Unit.INSTANCE;
                }

                /* renamed from: visit, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ Object m149visit(IIstStoreLocal stmt2) {
                    visit(stmt2);
                    return Unit.INSTANCE;
                }

                /* renamed from: visit, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ Object m150visit(IIstSetField stmt2) throws NoWhenBranchMatchedException {
                    visit(stmt2);
                    return Unit.INSTANCE;
                }

                /* renamed from: default, reason: not valid java name */
                public void m147default(IStmt stmt2) {
                    Intrinsics.checkNotNullParameter(stmt2, "stmt");
                }

                public void visit(IIstStoreLocal stmt2) {
                    Intrinsics.checkNotNullParameter(stmt2, "stmt");
                    this.this$0.addSource(method, stmt2.getValue(), (v1, v2) -> {
                        return visit$lambda$0(r3, v1, v2);
                    });
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                private static final Unit visit$lambda$0(IIstStoreLocal $stmt, SourceSinkProvider.MethodModel m, Set sources) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter(m, "m");
                    Intrinsics.checkNotNullParameter(sources, "sources");
                    MParameter local = $stmt.getLocal();
                    SourceSinkType sourceSinkType = SourceSinkType.Source;
                    AccessPathTuple apField = AccessPathTuple.fromPathElements((String) null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), sourceSinkType);
                    if (local instanceof MReturn) {
                        Intrinsics.checkNotNull(apField);
                        m.addReturn(apField, sources);
                    } else if (local instanceof MParameter) {
                        int index = local.getIndex();
                        Intrinsics.checkNotNull(apField);
                        m.addParameter(index, apField, sources);
                    } else {
                        if (!Intrinsics.areEqual(local, MGlobal.INSTANCE)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        Intrinsics.checkNotNull(apField);
                        m.addGlobal(apField, sources);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                public void visit(IIstSetField stmt2) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter(stmt2, "stmt");
                    BuiltInField field = stmt2.getField();
                    if ((field instanceof ClassField) || (field instanceof TaintProperty)) {
                        SourceSinkProvider.ModelingConfigImpl modelingConfigImpl = this.this$0;
                        SootMethod sootMethod = method;
                        IExpr value = stmt2.getValue();
                        SourceSinkProvider.ModelingConfigImpl modelingConfigImpl2 = this.this$0;
                        SootMethod sootMethod2 = method;
                        modelingConfigImpl.addSource(sootMethod, value, (v4, v5) -> {
                            return visit$lambda$2(r3, r4, r5, r6, v4, v5);
                        });
                        return;
                    }
                    if (field instanceof BuiltInField) {
                        BuiltInField builtInField = field;
                        if (!Intrinsics.areEqual(builtInField, TaintProperty.INSTANCE) && !Intrinsics.areEqual(builtInField, ViaProperty.INSTANCE) && !Intrinsics.areEqual(builtInField, MapKeys.INSTANCE) && !Intrinsics.areEqual(builtInField, MapValues.INSTANCE) && !Intrinsics.areEqual(builtInField, Elements.INSTANCE)) {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                }

                private static final Unit visit$lambda$2(IClassField $field, SourceSinkProvider.ModelingConfigImpl this$0, SootMethod $method, IIstSetField $stmt, SourceSinkProvider.MethodModel m, Set sources) throws NoWhenBranchMatchedException {
                    List listEmptyList;
                    Intrinsics.checkNotNullParameter(m, "m");
                    Intrinsics.checkNotNullParameter(sources, "sources");
                    if ($field instanceof ClassField) {
                        listEmptyList = CollectionsKt.listOf($field);
                    } else {
                        if (!($field instanceof TaintProperty)) {
                            throw new IllegalStateException(("error " + $field).toString());
                        }
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    List fields = listEmptyList;
                    this$0.setSourceSink($method, $stmt.getBase(), fields, (v2, v3, v4, v5) -> {
                        return visit$lambda$2$lambda$1(r4, r5, v2, v3, v4, v5);
                    });
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                private static final Unit visit$lambda$2$lambda$1(SourceSinkProvider.MethodModel $m, Set $sources, MLocal op, String baseType, List fields, List fieldTypes) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter(op, "op");
                    Intrinsics.checkNotNullParameter(fields, "fields");
                    Intrinsics.checkNotNullParameter(fieldTypes, "fieldTypes");
                    SourceSinkType sourceSinkType = SourceSinkType.Source;
                    AccessPathTuple apField = AccessPathTuple.fromPathElements(baseType, fields, fieldTypes, sourceSinkType);
                    if (op instanceof MReturn) {
                        Intrinsics.checkNotNull(apField);
                        $m.addReturn(apField, $sources);
                    } else if (op instanceof MParameter) {
                        int index = ((MParameter) op).getIndex();
                        Intrinsics.checkNotNull(apField);
                        $m.addParameter(index, apField, $sources);
                    } else {
                        if (!Intrinsics.areEqual(op, MGlobal.INSTANCE)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        Intrinsics.checkNotNull(apField);
                        $m.addGlobal(apField, $sources);
                    }
                    return Unit.INSTANCE;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addSink(SootMethod method, IExpr expr, Set<? extends ITaintType> set) throws NoWhenBranchMatchedException {
            ConcurrentMap $this$getOrPut$iv = this.this$0.getSinkDefinitions();
            MethodModel methodModelPutIfAbsent = $this$getOrPut$iv.get(method);
            if (methodModelPutIfAbsent == null) {
                MethodModel methodModel = new MethodModel(method, MethodSourceSinkDefinition.CallType.MethodCall);
                methodModelPutIfAbsent = $this$getOrPut$iv.putIfAbsent(method, methodModel);
                if (methodModelPutIfAbsent == null) {
                    methodModelPutIfAbsent = methodModel;
                }
            }
            MethodModel m = methodModelPutIfAbsent;
            setSourceSink$default(this, method, expr, null, (v2, v3, v4, v5) -> {
                return addSink$lambda$5(r4, r5, v2, v3, v4, v5);
            }, 4, null);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        private static final Unit addSink$lambda$5(MethodModel $m, Set $sinkKinds, MLocal op, String baseType, List fields, List fieldTypes) throws NoWhenBranchMatchedException {
            Intrinsics.checkNotNullParameter(op, "op");
            Intrinsics.checkNotNullParameter(fields, "fields");
            Intrinsics.checkNotNullParameter(fieldTypes, "fieldTypes");
            SourceSinkType sourceSinkType = SourceSinkType.Sink;
            AccessPathTuple apField = AccessPathTuple.fromPathElements(baseType, fields, fieldTypes, sourceSinkType);
            if (op instanceof MReturn) {
                Intrinsics.checkNotNull(apField);
                $m.addReturn(apField, $sinkKinds);
            } else if (op instanceof MParameter) {
                int index = ((MParameter) op).getIndex();
                Intrinsics.checkNotNull(apField);
                $m.addParameter(index, apField, $sinkKinds);
            } else {
                if (!Intrinsics.areEqual(op, MGlobal.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                Intrinsics.checkNotNull(apField);
                $m.addGlobal(apField, $sinkKinds);
            }
            return Unit.INSTANCE;
        }

        private final Set<ITaintType> findSinkFromStmt(final SootMethod method, IExpr expr) {
            Set<ITaintType> set = (Set) expr.accept(new IModelExpressionVisitor<Set<? extends ITaintType>>() { // from class: cn.sast.dataflow.infoflow.provider.SourceSinkProvider$ModelingConfigImpl$findSinkFromStmt$visitor$1

                /* compiled from: SourceSinkProvider.kt */
                @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
                /* loaded from: SourceSinkProvider$ModelingConfigImpl$findSinkFromStmt$visitor$1$WhenMappings.class */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[BinOp.values().length];
                        try {
                            iArr[BinOp.OrSet.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[BinOp.AndSet.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        try {
                            iArr[BinOp.RemoveSet.ordinal()] = 3;
                        } catch (NoSuchFieldError e3) {
                        }
                        try {
                            iArr[BinOp.ContainsSet.ordinal()] = 4;
                        } catch (NoSuchFieldError e4) {
                        }
                        try {
                            iArr[BinOp.HasIntersectionSet.ordinal()] = 5;
                        } catch (NoSuchFieldError e5) {
                        }
                        try {
                            iArr[BinOp.AnyOf.ordinal()] = 6;
                        } catch (NoSuchFieldError e6) {
                        }
                        try {
                            iArr[BinOp.Or.ordinal()] = 7;
                        } catch (NoSuchFieldError e7) {
                        }
                        try {
                            iArr[BinOp.And.ordinal()] = 8;
                        } catch (NoSuchFieldError e8) {
                        }
                        try {
                            iArr[BinOp.StartsWith.ordinal()] = 9;
                        } catch (NoSuchFieldError e9) {
                        }
                        try {
                            iArr[BinOp.EndsWith.ordinal()] = 10;
                        } catch (NoSuchFieldError e10) {
                        }
                        try {
                            iArr[BinOp.Contains.ordinal()] = 11;
                        } catch (NoSuchFieldError e11) {
                        }
                        try {
                            iArr[BinOp.StringEquals.ordinal()] = 12;
                        } catch (NoSuchFieldError e12) {
                        }
                        try {
                            iArr[BinOp.LT.ordinal()] = 13;
                        } catch (NoSuchFieldError e13) {
                        }
                        try {
                            iArr[BinOp.LE.ordinal()] = 14;
                        } catch (NoSuchFieldError e14) {
                        }
                        try {
                            iArr[BinOp.EQ.ordinal()] = 15;
                        } catch (NoSuchFieldError e15) {
                        }
                        try {
                            iArr[BinOp.GE.ordinal()] = 16;
                        } catch (NoSuchFieldError e16) {
                        }
                        try {
                            iArr[BinOp.GT.ordinal()] = 17;
                        } catch (NoSuchFieldError e17) {
                        }
                        try {
                            iArr[BinOp.Add.ordinal()] = 18;
                        } catch (NoSuchFieldError e18) {
                        }
                        try {
                            iArr[BinOp.Sub.ordinal()] = 19;
                        } catch (NoSuchFieldError e19) {
                        }
                        try {
                            iArr[BinOp.Mul.ordinal()] = 20;
                        } catch (NoSuchFieldError e20) {
                        }
                        try {
                            iArr[BinOp.Div.ordinal()] = 21;
                        } catch (NoSuchFieldError e21) {
                        }
                        try {
                            iArr[BinOp.Mod.ordinal()] = 22;
                        } catch (NoSuchFieldError e22) {
                        }
                        try {
                            iArr[BinOp.Xor.ordinal()] = 23;
                        } catch (NoSuchFieldError e23) {
                        }
                        try {
                            iArr[BinOp.BvAnd.ordinal()] = 24;
                        } catch (NoSuchFieldError e24) {
                        }
                        try {
                            iArr[BinOp.BvOr.ordinal()] = 25;
                        } catch (NoSuchFieldError e25) {
                        }
                        try {
                            iArr[BinOp.BvXor.ordinal()] = 26;
                        } catch (NoSuchFieldError e26) {
                        }
                        try {
                            iArr[BinOp.BvShr.ordinal()] = 27;
                        } catch (NoSuchFieldError e27) {
                        }
                        try {
                            iArr[BinOp.BvShl.ordinal()] = 28;
                        } catch (NoSuchFieldError e28) {
                        }
                        try {
                            iArr[BinOp.BvLShr.ordinal()] = 29;
                        } catch (NoSuchFieldError e29) {
                        }
                        try {
                            iArr[BinOp.IsInstanceOf.ordinal()] = 30;
                        } catch (NoSuchFieldError e30) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m141visit(IIexLoad expr2) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr2);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m142visit(IIexGetField expr2) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr2);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m143visit(IUnOpExpr expr2) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr2);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m144visit(ITriOpExpr expr2) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr2);
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m145visit(IQOpExpr expr2) {
                    return (Set) IModelExpressionVisitor.DefaultImpls.visit(this, expr2);
                }

                /* renamed from: default, reason: not valid java name and merged with bridge method [inline-methods] */
                public Set<ITaintType> m138default(IExpr expr2) {
                    Intrinsics.checkNotNullParameter(expr2, "expr");
                    return null;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m139visit(IBinOpExpr expr2) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter(expr2, "expr");
                    Set op1 = (Set) expr2.getOp1().accept(this);
                    Set op2 = (Set) expr2.getOp2().accept(this);
                    switch (WhenMappings.$EnumSwitchMapping$0[expr2.getOp().ordinal()]) {
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            return op1 == null ? op2 : op2 == null ? op1 : SetsKt.plus(op1, op2);
                        case 2:
                            return op1 == null ? op2 : op2 == null ? op1 : CollectionsKt.intersect(op1, op2);
                        case 3:
                            if (op1 == null || op2 == null) {
                                return null;
                            }
                            Set $this$filterTo$iv = op1;
                            Collection destination$iv = (Set) new LinkedHashSet();
                            for (Object element$iv : $this$filterTo$iv) {
                                ITaintType it = (ITaintType) element$iv;
                                if (!op2.contains(it)) {
                                    destination$iv.add(element$iv);
                                }
                            }
                            return (Set) destination$iv;
                        case 4:
                        case 5:
                            this.this$0.addSink(method, expr2.getOp1(), op2);
                            this.this$0.addSink(method, expr2.getOp2(), op1);
                            return op1 == null ? op2 : op2 == null ? op1 : SetsKt.plus(op1, op2);
                        case 6:
                            return null;
                        case 7:
                            return null;
                        case 8:
                            return null;
                        case 9:
                            return null;
                        case 10:
                            return null;
                        case 11:
                            return null;
                        case 12:
                            return null;
                        case 13:
                            return null;
                        case 14:
                            return null;
                        case 15:
                            return null;
                        case 16:
                            return null;
                        case 17:
                            return null;
                        case 18:
                            return null;
                        case 19:
                            return null;
                        case 20:
                            return null;
                        case 21:
                            return null;
                        case 22:
                            return null;
                        case 23:
                            return null;
                        case 24:
                            return null;
                        case 25:
                            return null;
                        case 26:
                            return null;
                        case 27:
                            return null;
                        case 28:
                            return null;
                        case 29:
                            return null;
                        case 30:
                            return null;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }

                /* renamed from: visit, reason: merged with bridge method [inline-methods] */
                public Set<ITaintType> m140visit(IIexConst expr2) {
                    Intrinsics.checkNotNullParameter(expr2, "expr");
                    Object obj = expr2.getConst();
                    if (obj instanceof Set) {
                        Iterable $this$filterIsInstanceTo$iv = (Iterable) obj;
                        Collection destination$iv = (Set) new LinkedHashSet();
                        for (Object element$iv : $this$filterIsInstanceTo$iv) {
                            if (element$iv instanceof ITaintType) {
                                destination$iv.add(element$iv);
                            }
                        }
                        return (Set) destination$iv;
                    }
                    return null;
                }
            });
            return set == null ? SetsKt.emptySet() : set;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        public void addStmt(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IStmt stmt) throws NoWhenBranchMatchedException {
            Intrinsics.checkNotNullParameter(decl, "decl");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(stmt, "stmt");
            if (decl instanceof IMethodDecl) {
                IMethodMatch match = ((IMethodDecl) decl).getMatch();
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                Iterable sootMethods = match.matched(sceneV);
                Iterable $this$forEach$iv = sootMethods;
                for (Object element$iv : $this$forEach$iv) {
                    SootMethod it = (SootMethod) element$iv;
                    findSourceFromStmt(it, stmt);
                }
                return;
            }
            if (!(decl instanceof IClassDecl) && !(decl instanceof IFieldDecl) && !(decl instanceof ILocalVarDecl)) {
                throw new NoWhenBranchMatchedException();
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        public void check(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IBoolExpr expr, @NotNull CheckType checkType, @NotNull Function1<? super BugMessage.Env, Unit> function12) throws NoWhenBranchMatchedException {
            Intrinsics.checkNotNullParameter(decl, "decl");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(function12, "env");
            GLB.INSTANCE.plusAssign(checkType);
            if (!this.this$0.getMainConfig().isEnable(checkType)) {
                return;
            }
            if (decl instanceof IMethodDecl) {
                IMethodMatch match = ((IMethodDecl) decl).getMatch();
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                Iterable sootMethods = match.matched(sceneV);
                Iterable $this$forEach$iv = sootMethods;
                SourceSinkProvider sourceSinkProvider = this.this$0;
                for (Object element$iv : $this$forEach$iv) {
                    SootMethod it = (SootMethod) element$iv;
                    Set types = findSinkFromStmt(it, expr.getExpr());
                    synchronized (sourceSinkProvider.getCheckTypesInSink()) {
                        sourceSinkProvider.getCheckTypesInSink().addAll(types);
                        Unit unit = Unit.INSTANCE;
                    }
                }
                return;
            }
            if (!(decl instanceof IClassDecl) && !(decl instanceof IFieldDecl) && !(decl instanceof ILocalVarDecl)) {
                throw new NoWhenBranchMatchedException();
            }
        }

        public void eval(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IExpr expr, @NotNull Function1<Object, Unit> function12) {
            Intrinsics.checkNotNullParameter(decl, "decl");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(function12, "accept");
        }

        public void validate() {
        }
    }

    @NotNull
    public final Set<ISourceSinkDefinition> getSourceSet() {
        return this.sourceSet;
    }

    public final void setSourceSet(@NotNull Set<ISourceSinkDefinition> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.sourceSet = set;
    }

    @NotNull
    public final Set<ISourceSinkDefinition> getSinkSet() {
        return this.sinkSet;
    }

    public final void setSinkSet(@NotNull Set<ISourceSinkDefinition> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.sinkSet = set;
    }

    @NotNull
    public final Set<ISourceSinkDefinition> getAllMethods() {
        return this.allMethods;
    }

    public final void setAllMethods(@NotNull Set<ISourceSinkDefinition> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.allMethods = set;
    }

    /* compiled from: SourceSinkProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001:\u0001\"B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eJ\u001e\u0010 \u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eJ\u001e\u0010!\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0012¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0010¨\u0006#"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel;", "", "method", "Lsoot/SootMethod;", "callType", "Lsoot/jimple/infoflow/sourcesSinks/definitions/MethodSourceSinkDefinition$CallType;", "<init>", "(Lsoot/SootMethod;Lsoot/jimple/infoflow/sourcesSinks/definitions/MethodSourceSinkDefinition$CallType;)V", "getMethod", "()Lsoot/SootMethod;", "getCallType", "()Lsoot/jimple/infoflow/sourcesSinks/definitions/MethodSourceSinkDefinition$CallType;", "baseObjectsAndTypes", "", "Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel$AccPath;", "getBaseObjectsAndTypes", "()Ljava/util/Set;", "parametersMapAndTypes", "", "", "getParametersMapAndTypes", "()Ljava/util/Map;", "returnAPsAndTypes", "getReturnAPsAndTypes", "addParameter", "", "index", "atp", "Lsoot/jimple/infoflow/sourcesSinks/definitions/AccessPathTuple;", "types", "", "Lcom/feysh/corax/config/api/ITaintType;", "addGlobal", "addReturn", "AccPath", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nSourceSinkProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,514:1\n12574#2,2:515\n381#3,7:517\n*S KotlinDebug\n*F\n+ 1 SourceSinkProvider.kt\ncn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel\n*L\n359#1:515,2\n369#1:517,7\n*E\n"})
    /* loaded from: SourceSinkProvider$MethodModel.class */
    public static final class MethodModel {

        @NotNull
        private final SootMethod method;

        @NotNull
        private final MethodSourceSinkDefinition.CallType callType;

        @NotNull
        private final Set<AccPath> baseObjectsAndTypes;

        @NotNull
        private final Map<Integer, Set<AccPath>> parametersMapAndTypes;

        @NotNull
        private final Set<AccPath> returnAPsAndTypes;

        /* compiled from: SourceSinkProvider.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/SourceSinkProvider$MethodModel$AccPath;", "", "apt", "Lsoot/jimple/infoflow/sourcesSinks/definitions/AccessPathTuple;", "types", "", "Lcom/feysh/corax/config/api/ITaintType;", "<init>", "(Lsoot/jimple/infoflow/sourcesSinks/definitions/AccessPathTuple;Ljava/util/Set;)V", "getApt", "()Lsoot/jimple/infoflow/sourcesSinks/definitions/AccessPathTuple;", "getTypes", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
        /* loaded from: SourceSinkProvider$MethodModel$AccPath.class */
        public static final class AccPath {

            @NotNull
            private final AccessPathTuple apt;

            @Nullable
            private final Set<ITaintType> types;

            @NotNull
            public final AccessPathTuple component1() {
                return this.apt;
            }

            @Nullable
            public final Set<ITaintType> component2() {
                return this.types;
            }

            @NotNull
            public final AccPath copy(@NotNull AccessPathTuple apt, @Nullable Set<? extends ITaintType> set) {
                Intrinsics.checkNotNullParameter(apt, "apt");
                return new AccPath(apt, set);
            }

            public static /* synthetic */ AccPath copy$default(AccPath accPath, AccessPathTuple accessPathTuple, Set set, int i, Object obj) {
                if ((i & 1) != 0) {
                    accessPathTuple = accPath.apt;
                }
                if ((i & 2) != 0) {
                    set = accPath.types;
                }
                return accPath.copy(accessPathTuple, set);
            }

            @NotNull
            public String toString() {
                return "AccPath(apt=" + this.apt + ", types=" + this.types + ")";
            }

            public int hashCode() {
                int result = this.apt.hashCode();
                return (result * 31) + (this.types == null ? 0 : this.types.hashCode());
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof AccPath)) {
                    return false;
                }
                AccPath accPath = (AccPath) other;
                return Intrinsics.areEqual(this.apt, accPath.apt) && Intrinsics.areEqual(this.types, accPath.types);
            }

            public AccPath(@NotNull AccessPathTuple apt, @Nullable Set<? extends ITaintType> set) {
                Intrinsics.checkNotNullParameter(apt, "apt");
                this.apt = apt;
                this.types = set;
            }

            @NotNull
            public final AccessPathTuple getApt() {
                return this.apt;
            }

            @Nullable
            public final Set<ITaintType> getTypes() {
                return this.types;
            }
        }

        public MethodModel(@NotNull SootMethod method, @NotNull MethodSourceSinkDefinition.CallType callType) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(callType, "callType");
            this.method = method;
            this.callType = callType;
            this.baseObjectsAndTypes = new LinkedHashSet();
            this.parametersMapAndTypes = new LinkedHashMap();
            this.returnAPsAndTypes = new LinkedHashSet();
        }

        @NotNull
        public final SootMethod getMethod() {
            return this.method;
        }

        @NotNull
        public final MethodSourceSinkDefinition.CallType getCallType() {
            return this.callType;
        }

        @NotNull
        public final Set<AccPath> getBaseObjectsAndTypes() {
            return this.baseObjectsAndTypes;
        }

        @NotNull
        public final Map<Integer, Set<AccPath>> getParametersMapAndTypes() {
            return this.parametersMapAndTypes;
        }

        @NotNull
        public final Set<AccPath> getReturnAPsAndTypes() {
            return this.returnAPsAndTypes;
        }

        public final void addParameter(int index, @NotNull AccessPathTuple atp, @Nullable Set<? extends ITaintType> set) {
            Object obj;
            boolean z;
            Intrinsics.checkNotNullParameter(atp, "atp");
            String[] fields = atp.getFields();
            if (fields != null) {
                int i = 0;
                int length = fields.length;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    String str = fields[i];
                    Intrinsics.checkNotNull(str);
                    if (StringsKt.startsWith$default(str, "<", false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (!(!z)) {
                    throw new IllegalStateException(String.valueOf(atp).toString());
                }
            }
            AccPath ap = new AccPath(atp, set);
            if (index == -1) {
                this.baseObjectsAndTypes.add(ap);
                return;
            }
            if (!(index >= 0)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            Map $this$getOrPut$iv = this.parametersMapAndTypes;
            Integer numValueOf = Integer.valueOf(index);
            Object value$iv = $this$getOrPut$iv.get(numValueOf);
            if (value$iv == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                $this$getOrPut$iv.put(numValueOf, linkedHashSet);
                obj = linkedHashSet;
            } else {
                obj = value$iv;
            }
            ((Set) obj).add(ap);
        }

        public final void addGlobal(@NotNull AccessPathTuple atp, @Nullable Set<? extends ITaintType> set) {
            Intrinsics.checkNotNullParameter(atp, "atp");
        }

        public final void addReturn(@NotNull AccessPathTuple atp, @Nullable Set<? extends ITaintType> set) {
            Intrinsics.checkNotNullParameter(atp, "atp");
            AccPath ap = new AccPath(atp, set);
            this.returnAPsAndTypes.add(ap);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.infoflow.provider.SourceSinkProvider.initialize(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Set<AccessPathTuple> initialize$apt(Set<MethodModel.AccPath> set, Set<? extends ITaintType> set2) {
        Set<MethodModel.AccPath> $this$mapNotNullTo$iv = set;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            MethodModel.AccPath it = (MethodModel.AccPath) element$iv$iv;
            Set types = it.getTypes();
            AccessPathTuple apt = (types == null || set2 == null || !CollectionsKt.intersect(types, set2).isEmpty()) ? it.getApt() : null;
            if (apt != null) {
                destination$iv.add(apt);
            }
        }
        return (Set) destination$iv;
    }

    private static final Set<ITaintType> initialize$types(Set<MethodModel.AccPath> set) {
        Set<MethodModel.AccPath> $this$flatMapTo$iv = set;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            MethodModel.AccPath it = (MethodModel.AccPath) element$iv;
            Iterable types = it.getTypes();
            if (types == null) {
                types = SetsKt.emptySet();
            }
            Iterable list$iv = types;
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (Set) destination$iv;
    }

    private static final MethodSourceSinkDefinition initialize$definition(MethodModel $this$initialize_u24definition, Set<? extends ITaintType> set) {
        Set baseObjects = initialize$apt($this$initialize_u24definition.getBaseObjectsAndTypes(), set);
        Map $this$mapValuesTo$iv = $this$initialize_u24definition.getParametersMapAndTypes();
        Map destination$iv = new LinkedHashMap();
        Iterable $this$associateByTo$iv$iv = $this$mapValuesTo$iv.entrySet();
        for (Object element$iv$iv : $this$associateByTo$iv$iv) {
            Map.Entry it$iv = (Map.Entry) element$iv$iv;
            Map.Entry it = (Map.Entry) element$iv$iv;
            destination$iv.put(it$iv.getKey(), initialize$apt((Set) it.getValue(), set));
        }
        Set returnAPs = initialize$apt($this$initialize_u24definition.getReturnAPsAndTypes(), set);
        SootMethodAndClass am = new SootMethodAndClass($this$initialize_u24definition.getMethod());
        int iIntValue = destination$iv.isEmpty() ? 0 : ((Number) CollectionsKt.maxOrThrow(destination$iv.keySet())).intValue() + 1;
        Set[] parameters = new Set[iIntValue];
        for (int i = 0; i < iIntValue; i++) {
            int i2 = i;
            LinkedHashSet linkedHashSet = (Set) destination$iv.get(Integer.valueOf(i2));
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
            }
            parameters[i2] = linkedHashSet;
        }
        return new MethodSourceSinkDefinition(am, baseObjects, parameters, returnAPs, $this$initialize_u24definition.getCallType(), (ISourceSinkCategory) null);
    }

    private static final Set<ITaintType> initialize$types$5(MethodModel $this$initialize_u24types_u245) {
        Set it = new LinkedHashSet();
        it.addAll(initialize$types($this$initialize_u24types_u245.getBaseObjectsAndTypes()));
        Map $this$forEach$iv = $this$initialize_u24types_u245.getParametersMapAndTypes();
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            it.addAll(initialize$types(element$iv.getValue()));
        }
        it.addAll(initialize$types($this$initialize_u24types_u245.getReturnAPsAndTypes()));
        return it;
    }

    @NotNull
    public Collection<ISourceSinkDefinition> getSources() {
        return this.sourceSet;
    }

    @NotNull
    public Collection<ISourceSinkDefinition> getSinks() {
        return this.sinkSet;
    }

    @NotNull
    /* renamed from: getAllMethods, reason: collision with other method in class */
    public Collection<ISourceSinkDefinition> m125getAllMethods() {
        return SetsKt.plus(this.sourceSet, this.sinkSet);
    }
}
