package cn.sast.framework;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.MainConfig;
import cn.sast.api.config.ProjectConfig;
import cn.sast.api.config.SaConfig;
import cn.sast.api.config.ScanFilter;
import cn.sast.api.config.SrcPrecedence;
import cn.sast.api.incremental.IncrementalAnalyze;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.ProjectMetrics;
import cn.sast.api.util.IMonitor;
import cn.sast.api.util.OthersKt;
import cn.sast.api.util.PhaseIntervalTimer;
import cn.sast.api.util.Timer;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.compiler.EcjCompiler;
import cn.sast.framework.graph.CGUtils;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.incremental.IncrementalAnalyzeImplByChangeFiles;
import cn.sast.framework.report.IWrapperFileGenerator;
import cn.sast.framework.report.NullWrapperFileGenerator;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.rewrite.LibraryClassPatcher;
import cn.sast.framework.rewrite.StringConcatRewriterTransform;
import cn.sast.idfa.analysis.ProcessInfoView;
import com.feysh.corax.config.api.ISootInitializeHandler;
import com.feysh.corax.config.api.rules.ProcessRule;
import com.github.ajalt.mordant.rendering.TextStyle;
import com.github.ajalt.mordant.rendering.Theme;
import driver.PTAFactory;
import driver.PTAPattern;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;
import org.utbot.framework.plugin.services.JdkInfoService;
import qilin.CoreConfig;
import qilin.core.PTAScene;
import qilin.core.pag.ValNode;
import qilin.pta.PTAConfig;
import qilin.util.MemoryWatcher;
import qilin.util.PTAUtils;
import soot.G;
import soot.Main;
import soot.Pack;
import soot.PackManager;
import soot.PointsToAnalysis;
import soot.Scene;
import soot.Singletons;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.jimple.infoflow.AbstractInfoflow;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.pointer.DumbPointerAnalysis;
import soot.options.Options;
import soot.util.Chain;

/* compiled from: SootCtx.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� Z2\u00020\u0001:\u0001ZB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J(\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J\"\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020\u001dH\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\u0006\u0010\"\u001a\u00020\u0017J(\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0$2\u0006\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020&0+H\u0086@¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u000e\u00103\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u00104\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010-\u001a\u00020\u00172\u0006\u00105\u001a\u000206H\u0016J\u0010\u0010-\u001a\u00020\u00172\u0006\u00107\u001a\u000208H\u0016J\u0018\u00109\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\b\u0010'\u001a\u0004\u0018\u00010(J\u001c\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020<0;*\b\u0012\u0004\u0012\u00020>0=J\u0010\u0010?\u001a\u00020\u001b*\b\u0012\u0004\u0012\u00020>0=J.\u0010@\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\b\b\u0002\u0010A\u001a\u00020\u001b2\u0014\b\u0002\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0CJ\u001a\u0010D\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010E\u001a\u00020\u0017H\u0016J\u0014\u0010F\u001a\u00020\u00172\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010G\u001a\u00020\u00172\u0006\u00105\u001a\u0002062\u0006\u0010H\u001a\u00020IH\u0016J\b\u0010G\u001a\u00020\u0017H\u0016J\b\u0010J\u001a\u00020\u0017H\u0016J\b\u0010K\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010.\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b/\u00100R$\u0010N\u001a\u00020M2\u0006\u0010L\u001a\u00020M8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020M8F¢\u0006\u0006\u001a\u0004\bT\u0010PR\u0017\u0010U\u001a\b\u0012\u0004\u0012\u00020W0V8F¢\u0006\u0006\u001a\u0004\bX\u0010Y¨\u0006["}, d2 = {"Lcn/sast/framework/SootCtx;", "Lcom/feysh/corax/config/api/ISootInitializeHandler;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "monitor", "Lcn/sast/api/util/IMonitor;", "getMonitor", "()Lcn/sast/api/util/IMonitor;", "_loadClassesTimer", "Lcn/sast/api/util/Timer;", "_classesClassificationTimer", "_cgConstructTimer", "cgAlgorithmProvider", "Lcn/sast/framework/CgAlgorithmProvider;", "getCgAlgorithmProvider", "()Lcn/sast/framework/CgAlgorithmProvider;", "setCgAlgorithmProvider", "(Lcn/sast/framework/CgAlgorithmProvider;)V", "configureCallGraph", "", "options", "Lsoot/options/Options;", "callGraphAlgorithm", "", "appOnly", "", "enableReflection", "constructCallGraph", "cgAlgorithm", "record", "showPta", "findClassesInnerJar", "", "", "Lcn/sast/common/IResFile;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "(Lcn/sast/framework/report/ProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findClassesInnerJarUnderAutoAppClassPath", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configure", "autoAppClassesLocator", "getAutoAppClassesLocator", "()Lcn/sast/framework/report/ProjectFileLocator;", "autoAppClassesLocator$delegate", "Lkotlin/Lazy;", "configureSootClassPath", "configureAfterSceneInit", "scene", "Lsoot/Scene;", "main", "Lsoot/Main;", "classesClassification", "activeBodyMethods", "Lkotlin/Pair;", "", "Lsoot/util/Chain;", "Lsoot/SootClass;", "show", "showClasses", "prefix", "fx", "Lkotlin/Function1;", "loadClasses", "configureSoot", "constructSoot", "releaseCallGraph", "g", "Lsoot/G;", "onBeforeCallGraphConstruction", "onAfterCallGraphConstruction", "value", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "callGraph", "getCallGraph", "()Lsoot/jimple/toolkits/callgraph/CallGraph;", "setCallGraph", "(Lsoot/jimple/toolkits/callgraph/CallGraph;)V", "sootMethodCallGraph", "getSootMethodCallGraph", "entryPoints", "", "Lsoot/SootMethod;", "getEntryPoints", "()Ljava/util/List;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSootCtx.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Logging.kt\norg/utbot/common/LoggingKt\n+ 4 Timer.kt\ncn/sast/api/util/TimerKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,731:1\n2632#2,3:732\n1557#2:767\n1628#2,2:768\n2318#2,14:770\n1630#2:784\n1053#2:785\n1368#2:787\n1454#2,5:788\n774#2:793\n865#2,2:794\n774#2:796\n865#2,2:797\n1053#2:871\n1053#2:872\n49#3,13:735\n62#3,11:756\n49#3,13:799\n62#3,11:836\n49#3,24:847\n16#4,8:748\n16#4,8:812\n16#4,8:820\n16#4,8:828\n1#5:786\n*S KotlinDebug\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx\n*L\n192#1:732,3\n313#1:767\n313#1:768,2\n314#1:770,14\n313#1:784\n315#1:785\n537#1:787\n537#1:788,5\n537#1:793\n537#1:794,2\n538#1:796\n538#1:797,2\n580#1:871\n586#1:872\n201#1:735,13\n201#1:756,11\n608#1:799,13\n608#1:836,11\n706#1:847,24\n212#1:748,8\n609#1:812,8\n612#1:820,8\n615#1:828,8\n*E\n"})
/* loaded from: SootCtx.class */
public class SootCtx implements ISootInitializeHandler {

    @NotNull
    private final MainConfig mainConfig;

    @Nullable
    private final IMonitor monitor;

    @Nullable
    private final Timer _loadClassesTimer;

    @Nullable
    private final Timer _classesClassificationTimer;

    @Nullable
    private final Timer _cgConstructTimer;
    public CgAlgorithmProvider cgAlgorithmProvider;

    @NotNull
    private final Lazy autoAppClassesLocator$delegate;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SootCtx::logger$lambda$51);

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: SootCtx$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CgAlgorithmProvider.values().length];
            try {
                iArr[CgAlgorithmProvider.Soot.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CgAlgorithmProvider.QiLin.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProcessRule.ScanAction.values().length];
            try {
                iArr2[ProcessRule.ScanAction.Process.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[ProcessRule.ScanAction.Skip.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[ProcessRule.ScanAction.Keep.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SootCtx.kt", l = {288}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"md5Group"}, m = "findClassesInnerJar", c = "cn.sast.framework.SootCtx")
    /* renamed from: cn.sast.framework.SootCtx$findClassesInnerJar$1, reason: invalid class name */
    /* loaded from: SootCtx$findClassesInnerJar$1.class */
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
            return SootCtx.this.findClassesInnerJar(null, (Continuation) this);
        }
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SootCtx.kt", l = {311}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "findClassesInnerJarUnderAutoAppClassPath", c = "cn.sast.framework.SootCtx")
    /* renamed from: cn.sast.framework.SootCtx$findClassesInnerJarUnderAutoAppClassPath$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: SootCtx$findClassesInnerJarUnderAutoAppClassPath$1.class */
    static final class C00161 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00161(Continuation<? super C00161> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SootCtx.this.findClassesInnerJarUnderAutoAppClassPath((Continuation) this);
        }
    }

    public SootCtx(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.mainConfig = mainConfig;
        this.monitor = this.mainConfig.getMonitor();
        IMonitor iMonitor = this.monitor;
        this._loadClassesTimer = iMonitor != null ? iMonitor.timer("loadClasses") : null;
        IMonitor iMonitor2 = this.monitor;
        this._classesClassificationTimer = iMonitor2 != null ? iMonitor2.timer("classes.classification") : null;
        IMonitor iMonitor3 = this.monitor;
        this._cgConstructTimer = iMonitor3 != null ? iMonitor3.timer("callgraph.construct") : null;
        this.autoAppClassesLocator$delegate = LazyKt.lazy(() -> {
            return autoAppClassesLocator_delegate$lambda$25(r1);
        });
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    public void onBeforeCallGraphConstruction(@NotNull Scene scene, @NotNull Options options) {
        ISootInitializeHandler.DefaultImpls.onBeforeCallGraphConstruction(this, scene, options);
    }

    public void onAfterCallGraphConstruction(@NotNull CallGraph cg, @NotNull Scene scene, @NotNull Options options) {
        ISootInitializeHandler.DefaultImpls.onAfterCallGraphConstruction(this, cg, scene, options);
    }

    public static final /* synthetic */ KLogger access$getLogger$cp() {
        return logger;
    }

    @Nullable
    public final IMonitor getMonitor() {
        return this.monitor;
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcn/sast/framework/SootCtx$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "restAll", "", "instance_soot_Scene", "Lsoot/Scene;", "getInstance_soot_Scene", "()Lsoot/Scene;", "corax-framework"})
    /* loaded from: SootCtx$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final void restAll() {
            G.reset();
            PTAScene.reset();
        }

        @Nullable
        public final Scene getInstance_soot_Scene() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Field it = Singletons.class.getDeclaredField("instance_soot_Scene");
            it.setAccessible(true);
            Object obj = it.get(G.v());
            if (obj instanceof Scene) {
                return (Scene) obj;
            }
            return null;
        }
    }

    private static final Unit logger$lambda$51() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final CgAlgorithmProvider getCgAlgorithmProvider() {
        CgAlgorithmProvider cgAlgorithmProvider = this.cgAlgorithmProvider;
        if (cgAlgorithmProvider != null) {
            return cgAlgorithmProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cgAlgorithmProvider");
        return null;
    }

    public final void setCgAlgorithmProvider(@NotNull CgAlgorithmProvider cgAlgorithmProvider) {
        Intrinsics.checkNotNullParameter(cgAlgorithmProvider, "<set-?>");
        this.cgAlgorithmProvider = cgAlgorithmProvider;
    }

    public void configureCallGraph(@NotNull Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        setCgAlgorithmProvider(configureCallGraph(options, this.mainConfig.getCallGraphAlgorithm(), this.mainConfig.getApponly(), this.mainConfig.getEnableReflection()));
    }

    @NotNull
    public CgAlgorithmProvider configureCallGraph(@NotNull Options options, @NotNull String callGraphAlgorithm, boolean appOnly, boolean enableReflection) {
        CgAlgorithmProvider cgAlgorithmProvider;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(callGraphAlgorithm, "callGraphAlgorithm");
        String appOnlySootOptionValue = "apponly:" + (appOnly ? "true" : "false");
        options.set_ignore_resolving_levels(true);
        logger.info(() -> {
            return configureCallGraph$lambda$2$lambda$0(r1, r2);
        });
        if (StringsKt.equals(callGraphAlgorithm, "SPARK", true)) {
            options.setPhaseOption("cg.spark", "on");
            options.setPhaseOption("cg.spark", "on-fly-cg:true");
            options.setPhaseOption("cg.spark", appOnlySootOptionValue);
            cgAlgorithmProvider = CgAlgorithmProvider.Soot;
        } else if (StringsKt.startsWith(callGraphAlgorithm, "GEOM", true)) {
            options.setPhaseOption("cg.spark", "on");
            options.setPhaseOption("cg.spark", appOnlySootOptionValue);
            AbstractInfoflow.setGeomPtaSpecificOptions();
            if (StringsKt.equals(StringsKt.substringAfter$default(callGraphAlgorithm, "-", (String) null, 2, (Object) null), "HeapIns", true)) {
                options.setPhaseOption("cg.spark", "geom-encoding:HeapIns");
            } else if (StringsKt.equals(StringsKt.substringAfter$default(callGraphAlgorithm, "-", (String) null, 2, (Object) null), "PtIns", true)) {
                options.setPhaseOption("cg.spark", "geom-encoding:PtIns");
            } else {
                throw new IllegalStateException((callGraphAlgorithm + " is incorrect").toString());
            }
            cgAlgorithmProvider = CgAlgorithmProvider.Soot;
        } else if (StringsKt.equals(callGraphAlgorithm, "CHA", true)) {
            options.setPhaseOption("cg.cha", "on");
            options.setPhaseOption("cg.cha", appOnlySootOptionValue);
            cgAlgorithmProvider = CgAlgorithmProvider.Soot;
        } else if (StringsKt.equals(callGraphAlgorithm, "RTA", true)) {
            options.setPhaseOption("cg.spark", "on");
            options.setPhaseOption("cg.spark", "rta:true");
            options.setPhaseOption("cg.spark", "on-fly-cg:false");
            options.setPhaseOption("cg.spark", appOnlySootOptionValue);
            cgAlgorithmProvider = CgAlgorithmProvider.Soot;
        } else if (StringsKt.equals(callGraphAlgorithm, "VTA", true)) {
            options.setPhaseOption("cg.spark", "on");
            options.setPhaseOption("cg.spark", "vta:true");
            options.setPhaseOption("cg.spark", appOnlySootOptionValue);
            cgAlgorithmProvider = CgAlgorithmProvider.Soot;
        } else {
            PTAConfig $this$configureCallGraph_u24lambda_u242_u24lambda_u241 = PTAConfig.v();
            $this$configureCallGraph_u24lambda_u242_u24lambda_u241.getPtaConfig().ptaPattern = new PTAPattern(callGraphAlgorithm);
            $this$configureCallGraph_u24lambda_u242_u24lambda_u241.getPtaConfig().singleentry = false;
            $this$configureCallGraph_u24lambda_u242_u24lambda_u241.getPtaConfig().ctxDebloating = true;
            $this$configureCallGraph_u24lambda_u242_u24lambda_u241.getPtaConfig().stringConstants = true;
            cgAlgorithmProvider = CgAlgorithmProvider.QiLin;
        }
        if (enableReflection) {
            options.setPhaseOption("cg", "types-for-invoke:true");
        }
        options.setPhaseOption("cg.spark", "set-impl:hybrid");
        options.setPhaseOption("jb", "model-lambdametafactory:false");
        options.setPhaseOption("jb.ulp", "off");
        return cgAlgorithmProvider;
    }

    private static final Object configureCallGraph$lambda$2$lambda$0(String $callGraphAlgorithm, boolean $appOnly) {
        return "using cg algorithm: " + $callGraphAlgorithm + " with " + $appOnly;
    }

    public static /* synthetic */ void constructCallGraph$default(SootCtx sootCtx, CgAlgorithmProvider cgAlgorithmProvider, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructCallGraph");
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        sootCtx.constructCallGraph(cgAlgorithmProvider, z, z2);
    }

    /* JADX WARN: Finally extract failed */
    public void constructCallGraph(@NotNull CgAlgorithmProvider cgAlgorithm, boolean appOnly, boolean record) {
        boolean z;
        Intrinsics.checkNotNullParameter(cgAlgorithm, "cgAlgorithm");
        Scene scene = Scene.v();
        releaseCallGraph();
        onBeforeCallGraphConstruction();
        if (!this.mainConfig.getSkipClass()) {
            Iterable applicationClasses = scene.getApplicationClasses();
            Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
            Iterable $this$none$iv = applicationClasses;
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    Object element$iv = it.next();
                    SootClass it2 = (SootClass) element$iv;
                    Intrinsics.checkNotNull(it2);
                    if (!OthersKt.isSyntheticComponent(it2)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                throw new IllegalStateException("application classes must not be empty. check your --auto-app-classes, --process, --source-path, --class-path options".toString());
            }
        }
        scene.getOrMakeFastHierarchy();
        Timer timer = this._cgConstructTimer;
        PhaseIntervalTimer.Snapshot cgs = timer != null ? timer.start() : null;
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
        final String msg$iv = "Constructing the call graph [" + cgAlgorithm + "] ...";
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$1
            public final Object invoke() {
                return "Started: " + msg$iv;
            }
        });
        final LocalDateTime startTime$iv = LocalDateTime.now();
        final Ref.ObjectRef res$iv = new Ref.ObjectRef();
        res$iv.element = Maybe.Companion.empty();
        try {
            try {
                PackManager.v().getPack("wjpp").apply();
                scene.getOrMakeFastHierarchy();
                scene.setAllowModifyHierarchy(false);
                System.gc();
                logger.info(SootCtx::constructCallGraph$lambda$8$lambda$4);
                long pid = ProcessHandle.current().pid();
                MemoryWatcher memoryWatcher = new MemoryWatcher(pid, "Main PTA");
                memoryWatcher.start();
                try {
                    IMonitor iMonitor = this.monitor;
                    PhaseIntervalTimer $this$bracket$iv = iMonitor != null ? iMonitor.timer("cgAlgorithm:" + cgAlgorithm) : null;
                    if ($this$bracket$iv == null) {
                        switch (WhenMappings.$EnumSwitchMapping$0[cgAlgorithm.ordinal()]) {
                            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                                PackManager.v().getPack("cg").apply();
                                break;
                            case 2:
                                PTAUtils.setAppOnly(appOnly);
                                CoreConfig.CorePTAConfiguration ptaConfig = CoreConfig.v().getPtaConfig();
                                ptaConfig.printAliasInfo = ExtSettings.INSTANCE.getPrintAliasInfo();
                                ptaConfig.castNeverFailsOfPhantomClass = ExtSettings.INSTANCE.getCastNeverFailsOfPhantomClass();
                                ValNode.UseRoaringPointsToSet = ExtSettings.INSTANCE.getUseRoaringPointsToSet();
                                scene.getEntryPoints();
                                PointsToAnalysis pointsToAnalysisCreatePTA = PTAFactory.createPTA(PTAConfig.v().getPtaConfig().ptaPattern);
                                pointsToAnalysisCreatePTA.getCgb().getReachableMethods();
                                pointsToAnalysisCreatePTA.run();
                                pointsToAnalysisCreatePTA.getCallGraph();
                                scene.getCallGraph();
                                scene.setPointsToAnalysis(pointsToAnalysisCreatePTA);
                                PTAUtils.clear();
                                pointsToAnalysisCreatePTA.getPag().small();
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    } else {
                        PhaseIntervalTimer.Snapshot s$iv = $this$bracket$iv.start();
                        try {
                            switch (WhenMappings.$EnumSwitchMapping$0[cgAlgorithm.ordinal()]) {
                                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                                    PackManager.v().getPack("cg").apply();
                                    break;
                                case 2:
                                    PTAUtils.setAppOnly(appOnly);
                                    CoreConfig.CorePTAConfiguration ptaConfig2 = CoreConfig.v().getPtaConfig();
                                    ptaConfig2.printAliasInfo = ExtSettings.INSTANCE.getPrintAliasInfo();
                                    ptaConfig2.castNeverFailsOfPhantomClass = ExtSettings.INSTANCE.getCastNeverFailsOfPhantomClass();
                                    ValNode.UseRoaringPointsToSet = ExtSettings.INSTANCE.getUseRoaringPointsToSet();
                                    scene.getEntryPoints();
                                    PointsToAnalysis pointsToAnalysisCreatePTA2 = PTAFactory.createPTA(PTAConfig.v().getPtaConfig().ptaPattern);
                                    pointsToAnalysisCreatePTA2.getCgb().getReachableMethods();
                                    pointsToAnalysisCreatePTA2.run();
                                    pointsToAnalysisCreatePTA2.getCallGraph();
                                    scene.getCallGraph();
                                    scene.setPointsToAnalysis(pointsToAnalysisCreatePTA2);
                                    PTAUtils.clear();
                                    pointsToAnalysisCreatePTA2.getPag().small();
                                    break;
                                default:
                                    throw new NoWhenBranchMatchedException();
                            }
                            Unit unit = Unit.INSTANCE;
                            $this$bracket$iv.stop(s$iv);
                        } catch (Throwable th) {
                            $this$bracket$iv.stop(s$iv);
                            throw th;
                        }
                    }
                    memoryWatcher.stop();
                    logger.info(() -> {
                        return constructCallGraph$lambda$8$lambda$6(r1);
                    });
                    scene.setAllowModifyHierarchy(true);
                    System.gc();
                    logger.info(SootCtx::constructCallGraph$lambda$8$lambda$7);
                    res$iv.element = new Maybe(Unit.INSTANCE);
                    ((Maybe) res$iv.element).getOrThrow();
                    if (((Maybe) res$iv.element).getHasValue()) {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$2
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                String str = msg$iv;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                            }
                        });
                    } else {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$3
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                            }
                        });
                    }
                    Timer timer2 = this._cgConstructTimer;
                    if (timer2 != null) {
                        Intrinsics.checkNotNull(cgs);
                        timer2.stop(cgs);
                    }
                    CGUtils.INSTANCE.addCallEdgeForPhantomMethods();
                    showPta();
                    scene.releaseReachableMethods();
                    CGUtils cGUtils = CGUtils.INSTANCE;
                    Intrinsics.checkNotNull(scene);
                    cGUtils.fixScene(scene);
                    if (record) {
                        Chain $this$constructCallGraph_u24lambda_u249 = scene.getApplicationClasses();
                        Intrinsics.checkNotNull($this$constructCallGraph_u24lambda_u249);
                        Pair<Integer, Integer> pairActiveBodyMethods = activeBodyMethods($this$constructCallGraph_u24lambda_u249);
                        int active = ((Number) pairActiveBodyMethods.component1()).intValue();
                        int total = ((Number) pairActiveBodyMethods.component2()).intValue();
                        IMonitor iMonitor2 = this.monitor;
                        if (iMonitor2 != null) {
                            ProjectMetrics projectMetrics = iMonitor2.getProjectMetrics();
                            if (projectMetrics != null) {
                                projectMetrics.setApplicationMethodsHaveBody(active);
                            }
                        }
                        IMonitor iMonitor3 = this.monitor;
                        if (iMonitor3 != null) {
                            ProjectMetrics projectMetrics2 = iMonitor3.getProjectMetrics();
                            if (projectMetrics2 != null) {
                                projectMetrics2.setApplicationMethods(total);
                            }
                        }
                        Chain $this$constructCallGraph_u24lambda_u2410 = scene.getLibraryClasses();
                        Intrinsics.checkNotNull($this$constructCallGraph_u24lambda_u2410);
                        Pair<Integer, Integer> pairActiveBodyMethods2 = activeBodyMethods($this$constructCallGraph_u24lambda_u2410);
                        int active2 = ((Number) pairActiveBodyMethods2.component1()).intValue();
                        int total2 = ((Number) pairActiveBodyMethods2.component2()).intValue();
                        IMonitor iMonitor4 = this.monitor;
                        if (iMonitor4 != null) {
                            ProjectMetrics projectMetrics3 = iMonitor4.getProjectMetrics();
                            if (projectMetrics3 != null) {
                                projectMetrics3.setLibraryMethodsHaveBody(active2);
                            }
                        }
                        IMonitor iMonitor5 = this.monitor;
                        if (iMonitor5 != null) {
                            ProjectMetrics projectMetrics4 = iMonitor5.getProjectMetrics();
                            if (projectMetrics4 != null) {
                                projectMetrics4.setLibraryMethods(total2);
                            }
                        }
                    }
                    onAfterCallGraphConstruction();
                } catch (Throwable th2) {
                    memoryWatcher.stop();
                    logger.info(() -> {
                        return constructCallGraph$lambda$8$lambda$6(r1);
                    });
                    throw th2;
                }
            } catch (Throwable t$iv) {
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$4
                    public final Object invoke() {
                        LocalDateTime localDateTime = startTime$iv;
                        Intrinsics.checkNotNull(localDateTime);
                        String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                        String str = msg$iv;
                        Result.Companion companion = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(t$iv));
                        return "Finished (in " + strElapsedSecFrom + "): " + str + " :: EXCEPTION :: " + "";
                    }
                });
                throw t$iv;
            }
        } catch (Throwable th3) {
            if (0 == 0) {
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$5
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$constructCallGraph$$inlined$bracket$default$6
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
            }
            throw th3;
        }
    }

    private static final Object constructCallGraph$lambda$8$lambda$4() {
        return "Before build CG: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    private static final Object constructCallGraph$lambda$8$lambda$6(MemoryWatcher $memoryWatcher) {
        return $memoryWatcher.toString();
    }

    private static final Object constructCallGraph$lambda$8$lambda$7() {
        return "After build CG: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    public void constructCallGraph() {
        constructCallGraph$default(this, getCgAlgorithmProvider(), this.mainConfig.getApponly(), false, 4, null);
    }

    public final void showPta() {
        PointsToAnalysis pta = Scene.v().getPointsToAnalysis();
        if (Scene.v().getPointsToAnalysis() instanceof DumbPointerAnalysis) {
            logger.warn(SootCtx::showPta$lambda$11);
            Scene.v().setPointsToAnalysis(pta);
        }
        logger.info(SootCtx::showPta$lambda$12);
        logger.info(SootCtx::showPta$lambda$13);
        Scene sceneV = Scene.v();
        Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
        showClasses(sceneV, "After PTA: ", new AnonymousClass4(Theme.Companion.getDefault().getInfo()));
    }

    private static final Object showPta$lambda$11() {
        return "PointsToAnalysis of scene is DumbPointerAnalysis!!!";
    }

    private static final Object showPta$lambda$12() {
        return "PointsToAnalysis of scene is " + Scene.v().getPointsToAnalysis().getClass().getSimpleName();
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.framework.SootCtx$showPta$4, reason: invalid class name */
    /* loaded from: SootCtx$showPta$4.class */
    /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1<String, String> {
        AnonymousClass4(Object receiver) {
            super(1, receiver, TextStyle.class, "invoke", "invoke(Ljava/lang/String;)Ljava/lang/String;", 0);
        }

        public final String invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((TextStyle) this.receiver).invoke(p0);
        }
    }

    private static final Object showPta$lambda$13() {
        return "Call Graph has been constructed with " + Scene.v().getCallGraph().size() + " edges.";
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object findClassesInnerJar(@org.jetbrains.annotations.NotNull cn.sast.framework.report.ProjectFileLocator r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.util.Set<cn.sast.common.IResFile>>> r9) {
        /*
            r7 = this;
            r0 = r9
            boolean r0 = r0 instanceof cn.sast.framework.SootCtx.AnonymousClass1
            if (r0 == 0) goto L29
            r0 = r9
            cn.sast.framework.SootCtx$findClassesInnerJar$1 r0 = (cn.sast.framework.SootCtx.AnonymousClass1) r0
            r13 = r0
            r0 = r13
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r13
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L34
        L29:
            cn.sast.framework.SootCtx$findClassesInnerJar$1 r0 = new cn.sast.framework.SootCtx$findClassesInnerJar$1
            r1 = r0
            r2 = r7
            r3 = r9
            r1.<init>(r3)
            r13 = r0
        L34:
            r0 = r13
            java.lang.Object r0 = r0.result
            r12 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r14 = r0
            r0 = r13
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L9c;
                default: goto Lb1;
            }
        L5c:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r1 = r0
            r1.<init>()
            r10 = r0
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r1 = r0
            r1.<init>()
            r11 = r0
            cn.sast.framework.SootCtx$findClassesInnerJar$2 r0 = new cn.sast.framework.SootCtx$findClassesInnerJar$2
            r1 = r0
            r2 = r8
            r3 = r11
            r4 = r10
            r5 = 0
            r1.<init>(r2, r3, r4, r5)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r1 = r13
            r2 = r13
            r3 = r11
            r2.L$0 = r3
            r2 = r13
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r1)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Lad
            r1 = r14
            return r1
        L9c:
            r0 = r13
            java.lang.Object r0 = r0.L$0
            java.util.concurrent.ConcurrentHashMap r0 = (java.util.concurrent.ConcurrentHashMap) r0
            r11 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Lad:
            r0 = r11
            return r0
        Lb1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.SootCtx.findClassesInnerJar(cn.sast.framework.report.ProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "SootCtx.kt", l = {289}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this$coroutineScope"}, m = "invokeSuspend", c = "cn.sast.framework.SootCtx$findClassesInnerJar$2")
    @SourceDebugExtension({"SMAP\nSootCtx.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx$findClassesInnerJar$2\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,731:1\n1317#2,2:732\n*S KotlinDebug\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx$findClassesInnerJar$2\n*L\n289#1:732,2\n*E\n"})
    /* renamed from: cn.sast.framework.SootCtx$findClassesInnerJar$2, reason: invalid class name */
    /* loaded from: SootCtx$findClassesInnerJar$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ ProjectFileLocator $locator;
        final /* synthetic */ ConcurrentHashMap<String, Set<IResFile>> $md5Group;
        final /* synthetic */ ConcurrentHashMap<IResFile, String> $md5Map;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ProjectFileLocator $locator, ConcurrentHashMap<String, Set<IResFile>> concurrentHashMap, ConcurrentHashMap<IResFile, String> concurrentHashMap2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$locator = $locator;
            this.$md5Group = concurrentHashMap;
            this.$md5Map = concurrentHashMap2;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$locator, this.$md5Group, this.$md5Map, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:14:0x009b
            	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
            	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instructions count: 258
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.SootCtx.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final Object invokeSuspend$lambda$2$lambda$0(IResFile $clz, Exception $e) {
            return "extract jar file " + $clz.getPath() + " with error: " + $e.getMessage();
        }

        private static final Object invokeSuspend$lambda$2$lambda$1(IResFile $clz) {
            return "extract jar file " + $clz.getPath();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:48:0x0222
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object findClassesInnerJarUnderAutoAppClassPath(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Set<? extends cn.sast.common.IResFile>> r7) {
        /*
            Method dump skipped, instructions count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.SootCtx.findClassesInnerJarUnderAutoAppClassPath(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object findClassesInnerJarUnderAutoAppClassPath$lambda$17(IResFile $jar) {
        return "Bad archive file: " + $jar;
    }

    private static final Object findClassesInnerJarUnderAutoAppClassPath$lambda$18(IResFile $jar) {
        return "unknown scheme: " + $jar.getUri();
    }

    public void configure(@NotNull Options options) throws IOException {
        Intrinsics.checkNotNullParameter(options, "options");
        if (this.mainConfig.getSrc_precedence() == SrcPrecedence.prec_java) {
            Boolean boolIsAndroidScene = this.mainConfig.isAndroidScene();
            Intrinsics.checkNotNull(boolIsAndroidScene);
            if (!boolIsAndroidScene.booleanValue()) {
                IResDirectory classOpt = this.mainConfig.getOutput_dir().resolve("gen-classes").toDirectory();
                classOpt.deleteDirectoryRecursively();
                classOpt.mkdirs();
                List customOptions = this.mainConfig.getEcj_options();
                EcjCompiler ecj = new EcjCompiler(ExtensionsKt.toPersistentSet(this.mainConfig.getProcessDir()), this.mainConfig.getClasspath(), classOpt, customOptions, this.mainConfig.getUseDefaultJavaClassPath(), null, null, false, null, null, 992, null);
                if (!ecj.compile()) {
                    logger.error(SootCtx::configure$lambda$19);
                }
                if (!(!IResDirectory.DefaultImpls.listPathEntries$default(classOpt, null, 1, null).isEmpty())) {
                    throw new IllegalStateException(("\n\n!!! no class file found under " + classOpt + " !!!\n\n").toString());
                }
                this.mainConfig.setProcessDir(this.mainConfig.getProcessDir().add(classOpt));
                this.mainConfig.setClasspath(ExtensionsKt.toPersistentSet(ecj.getCollectClassPath()));
            }
        }
        getAutoAppClassesLocator().update();
        if (!this.mainConfig.getAutoAppClasses().isEmpty()) {
            Boolean boolIsAndroidScene2 = this.mainConfig.isAndroidScene();
            Intrinsics.checkNotNull(boolIsAndroidScene2);
            if (!boolIsAndroidScene2.booleanValue() && !this.mainConfig.getSkipClass()) {
                BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass3(null), 1, (Object) null);
            }
        }
        MainConfig $this$configure_u24lambda_u2424 = this.mainConfig;
        options.set_verbose(true);
        options.set_allow_phantom_elms(true);
        options.set_whole_program($this$configure_u24lambda_u2424.getWhole_program());
        options.set_src_prec($this$configure_u24lambda_u2424.getSrc_precedence().getSootFlag());
        options.set_prepend_classpath($this$configure_u24lambda_u2424.getPrepend_classpath());
        options.set_no_bodies_for_excluded($this$configure_u24lambda_u2424.getNo_bodies_for_excluded());
        options.set_include_all(true);
        options.set_allow_phantom_refs($this$configure_u24lambda_u2424.getAllow_phantom_refs());
        options.set_ignore_classpath_errors(false);
        options.set_throw_analysis($this$configure_u24lambda_u2424.getThrow_analysis());
        options.set_process_multiple_dex($this$configure_u24lambda_u2424.getProcess_multiple_dex());
        options.set_field_type_mismatches(2);
        options.set_full_resolver(true);
        options.classes().addAll($this$configure_u24lambda_u2424.getAppClasses());
        options.set_app(false);
        options.set_search_dex_in_archives(true);
        options.set_process_dir(CollectionsKt.toList($this$configure_u24lambda_u2424.getSoot_process_dir()));
        options.set_output_format($this$configure_u24lambda_u2424.getOutput_format());
        $this$configure_u24lambda_u2424.getSoot_output_dir().deleteDirectoryRecursively();
        $this$configure_u24lambda_u2424.getSoot_output_dir().mkdirs();
        options.set_output_dir($this$configure_u24lambda_u2424.getSoot_output_dir().getAbsolutePath());
        options.set_keep_offset(false);
        options.set_keep_line_number($this$configure_u24lambda_u2424.getEnableLineNumbers());
        options.set_ignore_resolution_errors(true);
        Boolean forceAndroidJar = $this$configure_u24lambda_u2424.getForceAndroidJar();
        if (forceAndroidJar != null) {
            boolean it = forceAndroidJar.booleanValue();
            if (it) {
                options.set_force_android_jar($this$configure_u24lambda_u2424.getAndroidPlatformDir());
            } else {
                options.set_android_jars($this$configure_u24lambda_u2424.getAndroidPlatformDir());
            }
            logger.info(() -> {
                return configure$lambda$24$lambda$23$lambda$22$lambda$21(r1);
            });
        }
        Boolean boolIsAndroidScene3 = this.mainConfig.isAndroidScene();
        Intrinsics.checkNotNull(boolIsAndroidScene3);
        if (boolIsAndroidScene3.booleanValue()) {
            options.set_throw_analysis(3);
        }
        if ($this$configure_u24lambda_u2424.getEnableOriginalNames()) {
            options.setPhaseOption("jb", "use-original-names:true");
        }
        options.set_debug(false);
        options.set_verbose(false);
        options.set_validate(false);
        configureCallGraph(options);
        Pack p = PackManager.v().getPack("jb");
        p.add(new Transform(StringConcatRewriterTransform.phase, new StringConcatRewriterTransform()));
        SaConfig saConfig = this.mainConfig.getSaConfig();
        if (saConfig != null) {
            ISootInitializeHandler sootConfig = saConfig.getSootConfig();
            if (sootConfig != null) {
                sootConfig.configure(options);
            }
        }
    }

    private static final Object configure$lambda$19() {
        return "\n\n!!! There are some errors in source code compilation !!!\n\n";
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "SootCtx.kt", l = {366}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.SootCtx$configure$3")
    /* renamed from: cn.sast.framework.SootCtx$configure$3, reason: invalid class name */
    /* loaded from: SootCtx$configure$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return SootCtx.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object objFindClassesInnerJarUnderAutoAppClassPath;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    objFindClassesInnerJarUnderAutoAppClassPath = SootCtx.this.findClassesInnerJarUnderAutoAppClassPath((Continuation) this);
                    if (objFindClassesInnerJarUnderAutoAppClassPath == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    objFindClassesInnerJarUnderAutoAppClassPath = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Set jarsUnderAutoCP = (Set) objFindClassesInnerJarUnderAutoAppClassPath;
            SootCtx.this.getMainConfig().setProcessDir(SootCtx.this.getMainConfig().getProcessDir().addAll(jarsUnderAutoCP));
            return Unit.INSTANCE;
        }
    }

    private static final Object configure$lambda$24$lambda$23$lambda$22$lambda$21(MainConfig $this_with) {
        return "android platform dir: " + $this_with.getAndroidPlatformDir();
    }

    @NotNull
    public final ProjectFileLocator getAutoAppClassesLocator() {
        return (ProjectFileLocator) this.autoAppClassesLocator$delegate.getValue();
    }

    private static final ProjectFileLocator autoAppClassesLocator_delegate$lambda$25(SootCtx this$0) {
        return new ProjectFileLocator(this$0.mainConfig.getMonitor(), this$0.mainConfig.getAutoAppClasses(), null, this$0.mainConfig.getAutoAppTraverseMode(), false, 16, null);
    }

    public final void configureSootClassPath(@NotNull Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        SortedSet cp = CollectionsKt.toSortedSet(this.mainConfig.get_soot_classpath());
        Scene.v().setSootClassPath((String) null);
        String str = File.pathSeparator;
        Intrinsics.checkNotNullExpressionValue(str, "pathSeparator");
        options.set_soot_classpath(CollectionsKt.joinToString$default(cp, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    public void configureAfterSceneInit(@NotNull Scene scene, @NotNull Options options) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(options, "options");
        configureSootClassPath(options);
    }

    public void configure(@NotNull Scene scene) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        logger.info(SootCtx::configure$lambda$26);
        Scene.v().addBasicClass("java.lang.String", 3);
        Scene.v().addBasicClass("java.lang.StringLatin1", 3);
        Scene.v().addBasicClass("java.util.Arrays", 3);
        Scene.v().addBasicClass("java.lang.Math", 3);
        Scene.v().addBasicClass("java.lang.StringCoding", 3);
        SaConfig saConfig = this.mainConfig.getSaConfig();
        if (saConfig != null) {
            ISootInitializeHandler sootConfig = saConfig.getSootConfig();
            if (sootConfig != null) {
                sootConfig.configure(scene);
            }
        }
    }

    private static final Object configure$lambda$26() {
        return "Initializing Soot Scene...";
    }

    public void configure(@NotNull Main main) {
        Intrinsics.checkNotNullParameter(main, "main");
        main.autoSetOptions();
        SaConfig saConfig = this.mainConfig.getSaConfig();
        if (saConfig != null) {
            ISootInitializeHandler sootConfig = saConfig.getSootConfig();
            if (sootConfig != null) {
                sootConfig.configure(main);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public final void classesClassification(@NotNull Scene scene, @Nullable ProjectFileLocator locator) throws NoWhenBranchMatchedException {
        String str;
        String origAction;
        Intrinsics.checkNotNullParameter(scene, "scene");
        Timer timer = this._classesClassificationTimer;
        PhaseIntervalTimer.Snapshot st = timer != null ? timer.start() : null;
        if (!(!this.mainConfig.getSkipClass())) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean findAny = false;
        Collection autoAppClasses = this.mainConfig.getAutoAppClasses();
        showClasses$default(this, scene, "Before classes classification: ", null, 4, null);
        Iterator it = Scene.v().getClasses().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            SootClass sc = (SootClass) it.next();
            if (!sc.isPhantom()) {
                ProjectFileLocator autoAppClassesLocator = getAutoAppClassesLocator();
                ClassResInfo.Companion companion = ClassResInfo.Companion;
                Intrinsics.checkNotNull(sc);
                IResFile sourceFile = autoAppClassesLocator.get((IBugResInfo) companion.of(sc), (IWrapperFileGenerator) NullWrapperFileGenerator.INSTANCE);
                String origAction2 = null;
                if (!autoAppClasses.isEmpty()) {
                    if (sourceFile != null && (this.mainConfig.getAutoAppSrcInZipScheme() || sourceFile.isFileScheme())) {
                        findAny = true;
                        sc.setApplicationClass();
                        origAction2 = "application";
                    } else if (sc.isApplicationClass()) {
                        sc.setLibraryClass();
                        origAction2 = "library";
                    }
                }
                String str2 = origAction2;
                if (str2 == null) {
                    if (sc.isApplicationClass()) {
                        str2 = "application";
                    } else if (sc.isLibraryClass()) {
                        str2 = "library";
                    } else {
                        str2 = "phantom";
                    }
                }
                String origAction3 = str2;
                IResFile sourceFileInLocator = locator != null ? locator.get((IBugResInfo) ClassResInfo.Companion.of(sc), (IWrapperFileGenerator) NullWrapperFileGenerator.INSTANCE) : null;
                if (sourceFileInLocator != null) {
                    origAction = "(src exists) " + origAction3;
                } else {
                    origAction = "(src not exists) " + origAction3;
                }
                ProcessRule.ScanAction scanAction = ScanFilter.getActionOf$default(this.mainConfig.getScanFilter(), origAction, sc, (String) null, 4, (Object) null);
                switch (WhenMappings.$EnumSwitchMapping$1[scanAction.ordinal()]) {
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        sc.setApplicationClass();
                        break;
                    case 2:
                        sc.setLibraryClass();
                        break;
                    case 3:
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        }
        Timer timer2 = this._classesClassificationTimer;
        if (timer2 != null) {
            Intrinsics.checkNotNull(st);
            timer2.stop(st);
        }
        if ((!autoAppClasses.isEmpty()) && !findAny) {
            if (this.mainConfig.getProjectConfig().getYmlFile() != null) {
                str = "Or check your project-scan-config file: " + this.mainConfig.getProjectConfig().getYmlFile() + " and get more information from match result: " + this.mainConfig.getOutput_dir().resolve(ProjectConfig.RECORD_FILE_NAME);
            } else {
                str = "";
            }
            String hintAppend1 = str;
            logger.error(() -> {
                return classesClassification$lambda$27(r1, r2);
            });
        }
        showClasses$default(this, scene, "After classes classification: ", null, 4, null);
    }

    private static final Object classesClassification$lambda$27(PersistentSet $autoAppClasses, String $hintAppend1) {
        return "\n\n\nSince " + $autoAppClasses + " has no source files corresponding to the classes, the classifier is unable to classify based on the location of the source code\n" + $hintAppend1 + "\n\n";
    }

    @NotNull
    public final Pair<Integer, Integer> activeBodyMethods(@NotNull Chain<SootClass> chain) {
        Intrinsics.checkNotNullParameter(chain, "<this>");
        Iterable<SootClass> $this$flatMap$iv = (Iterable) chain;
        Collection destination$iv$iv = new ArrayList();
        for (SootClass it : $this$flatMap$iv) {
            Iterable methods = it.getMethods();
            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
            Iterable list$iv$iv = methods;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SootMethod it2 = (SootMethod) element$iv$iv;
            if (!it2.isAbstract()) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable total = (List) destination$iv$iv2;
        Iterable $this$filter$iv2 = total;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            SootMethod it3 = (SootMethod) element$iv$iv2;
            if (it3.hasActiveBody()) {
                destination$iv$iv3.add(element$iv$iv2);
            }
        }
        List active = (List) destination$iv$iv3;
        return TuplesKt.to(Integer.valueOf(active.size()), Integer.valueOf(((Collection) total).size()));
    }

    @NotNull
    public final String show(@NotNull Chain<SootClass> chain) {
        Intrinsics.checkNotNullParameter(chain, "<this>");
        Pair<Integer, Integer> pairActiveBodyMethods = activeBodyMethods(chain);
        int active = ((Number) pairActiveBodyMethods.component1()).intValue();
        int total = ((Number) pairActiveBodyMethods.component2()).intValue();
        if (total == 0) {
            return "empty";
        }
        int size = chain.size();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Float.valueOf(active / total)};
        String str = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return size + "(" + total + "*" + str + ")";
    }

    public static /* synthetic */ void showClasses$default(SootCtx sootCtx, Scene scene, String str, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showClasses");
        }
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            function1 = SootCtx::showClasses$lambda$31;
        }
        sootCtx.showClasses(scene, str, function1);
    }

    private static final String showClasses$lambda$31(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    public final void showClasses(@NotNull Scene scene, @NotNull String prefix, @NotNull Function1<? super String, String> function1) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(function1, "fx");
        Chain classes = scene.getClasses();
        Chain applicationClasses = scene.getApplicationClasses();
        Chain libraryClasses = scene.getLibraryClasses();
        Chain phantomClasses = scene.getPhantomClasses();
        logger.info(() -> {
            return showClasses$lambda$33$lambda$32(r1, r2, r3, r4, r5, r6, r7);
        });
    }

    private static final Object showClasses$lambda$33$lambda$32(Function1 $fx, String $prefix, SootCtx this$0, Chain $applicationClasses, Chain $libraryClasses, Chain $phantomClasses, Chain $classes) {
        Intrinsics.checkNotNull($applicationClasses);
        String strShow = this$0.show($applicationClasses);
        Intrinsics.checkNotNull($libraryClasses);
        String strShow2 = this$0.show($libraryClasses);
        Intrinsics.checkNotNull($phantomClasses);
        String strShow3 = this$0.show($phantomClasses);
        Intrinsics.checkNotNull($classes);
        return $fx.invoke($prefix + "applicationClasses: " + strShow + ". libraryClasses: " + strShow2 + ". phantomClasses: " + strShow3 + ". classes: " + this$0.show($classes));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public void loadClasses(@NotNull Scene scene, @Nullable ProjectFileLocator locator) throws IllegalAccessException, NoWhenBranchMatchedException, NoSuchFieldException, IllegalArgumentException {
        PhaseIntervalTimer $this$bracket$iv;
        PhaseIntervalTimer.Snapshot s$iv;
        Intrinsics.checkNotNullParameter(scene, "scene");
        G.v().set_SourceLocator(new SourceLocatorPlus(this.mainConfig));
        switch (WhenMappings.$EnumSwitchMapping$0[getCgAlgorithmProvider().ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                break;
            case 2:
                Scene.v().addBasicClass("java.lang.ClassLoader", 3);
                Scene.v().addBasicClass("java.lang.ref.Finalizer", 3);
                PTAScene.v().addBasicClasses();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (!this.mainConfig.getSkipClass()) {
            LinkedList exclude = SootCtxKt.getExcludedPackages(scene);
            logger.info(() -> {
                return loadClasses$lambda$34(r1);
            });
            logger.info(() -> {
                return loadClasses$lambda$37(r1, r2);
            });
            logger.info(() -> {
                return loadClasses$lambda$40(r1);
            });
        }
        if (Options.v().src_prec() == 4) {
            String sunBootClassPath = this.mainConfig.getSunBootClassPath();
            String javaExtDirs = this.mainConfig.getJavaExtDirs();
            String str = sunBootClassPath;
            String str2 = javaExtDirs;
            if ((str == null || str.length() == 0) | (str2 == null || str2.length() == 0)) {
                throw new IllegalStateException("sunBootClassPath or javaExtDirs must not be null".toString());
            }
        }
        logger.info(SootCtx::loadClasses$lambda$41);
        if (!this.mainConfig.getSkipClass()) {
            LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
            final String msg$iv = "Loading Necessary Classes...";
            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$1
                public final Object invoke() {
                    return "Started: " + msg$iv;
                }
            });
            final LocalDateTime startTime$iv = LocalDateTime.now();
            final Ref.ObjectRef res$iv = new Ref.ObjectRef();
            res$iv.element = Maybe.Companion.empty();
            try {
                try {
                    PhaseIntervalTimer phaseIntervalTimer = this._loadClassesTimer;
                    if (phaseIntervalTimer != null) {
                        $this$bracket$iv = phaseIntervalTimer;
                        if ($this$bracket$iv == null) {
                            scene.loadNecessaryClasses(false);
                        } else {
                            s$iv = $this$bracket$iv.start();
                            try {
                                scene.loadNecessaryClasses(false);
                                Unit unit = Unit.INSTANCE;
                                $this$bracket$iv.stop(s$iv);
                            } finally {
                            }
                        }
                    }
                    IMonitor iMonitor = this.monitor;
                    $this$bracket$iv = iMonitor != null ? iMonitor.timer("classesClassification") : null;
                    if ($this$bracket$iv == null) {
                        classesClassification(scene, locator);
                    } else {
                        s$iv = $this$bracket$iv.start();
                        try {
                            classesClassification(scene, locator);
                            Unit unit2 = Unit.INSTANCE;
                            $this$bracket$iv.stop(s$iv);
                        } finally {
                        }
                    }
                    PhaseIntervalTimer phaseIntervalTimer2 = this._loadClassesTimer;
                    if (phaseIntervalTimer2 != null) {
                        PhaseIntervalTimer $this$bracket$iv2 = phaseIntervalTimer2;
                        if ($this$bracket$iv2 == null) {
                            Iterator it = scene.getApplicationClasses().iterator();
                            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                            while (it.hasNext()) {
                                SootClass appClass = (SootClass) it.next();
                                if (!appClass.isPhantom()) {
                                    scene.loadClass(appClass.getName(), 3);
                                }
                            }
                        } else {
                            PhaseIntervalTimer.Snapshot s$iv2 = $this$bracket$iv2.start();
                            try {
                                Iterator it2 = scene.getApplicationClasses().iterator();
                                Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
                                while (it2.hasNext()) {
                                    SootClass appClass2 = (SootClass) it2.next();
                                    if (!appClass2.isPhantom()) {
                                        scene.loadClass(appClass2.getName(), 3);
                                    }
                                }
                                Unit unit3 = Unit.INSTANCE;
                                $this$bracket$iv2.stop(s$iv2);
                            } finally {
                                $this$bracket$iv2.stop(s$iv2);
                            }
                        }
                    }
                    logger.info(SootCtx::loadClasses$lambda$46$lambda$45);
                    res$iv.element = new Maybe(Unit.INSTANCE);
                    ((Maybe) res$iv.element).getOrThrow();
                    if (((Maybe) res$iv.element).getHasValue()) {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$2
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                String str3 = msg$iv;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str3 + " " + "";
                            }
                        });
                    } else {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$3
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                            }
                        });
                    }
                    showClasses$default(this, scene, null, new AnonymousClass6(Theme.Companion.getDefault().getWarning()), 2, null);
                } catch (Throwable t$iv) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$4
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str3 = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(ResultKt.createFailure(t$iv));
                            return "Finished (in " + strElapsedSecFrom + "): " + str3 + " :: EXCEPTION :: " + "";
                        }
                    });
                    throw t$iv;
                }
            } catch (Throwable th) {
                if (0 == 0) {
                    if (((Maybe) res$iv.element).getHasValue()) {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$5
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                String str3 = msg$iv;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str3 + " " + "";
                            }
                        });
                    } else {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$loadClasses$$inlined$bracket$default$6
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                            }
                        });
                    }
                }
                throw th;
            }
        } else {
            scene.loadBasicClasses();
            scene.loadDynamicClasses();
            scene.doneResolving();
            CGUtils.createDummyMain$default(CGUtils.INSTANCE, scene, null, null, 6, null);
        }
        logger.info(SootCtx::loadClasses$lambda$47);
        CGUtils.INSTANCE.removeLargeClasses(scene);
        CGUtils.INSTANCE.makeSpuriousMethodFromInvokeExpr();
        PackManager.v().getPack("wjpp").apply();
        new LibraryClassPatcher().patchLibraries();
        IMonitor iMonitor2 = this.monitor;
        if (iMonitor2 != null) {
            ProjectMetrics projectMetrics = iMonitor2.getProjectMetrics();
            if (projectMetrics != null) {
                Collection applicationClasses = scene.getApplicationClasses();
                Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
                projectMetrics.setApplicationClasses(applicationClasses.size());
            }
        }
        IMonitor iMonitor3 = this.monitor;
        if (iMonitor3 != null) {
            ProjectMetrics projectMetrics2 = iMonitor3.getProjectMetrics();
            if (projectMetrics2 != null) {
                Collection libraryClasses = scene.getLibraryClasses();
                Intrinsics.checkNotNullExpressionValue(libraryClasses, "getLibraryClasses(...)");
                projectMetrics2.setLibraryClasses(libraryClasses.size());
            }
        }
        IMonitor iMonitor4 = this.monitor;
        if (iMonitor4 != null) {
            ProjectMetrics projectMetrics3 = iMonitor4.getProjectMetrics();
            if (projectMetrics3 != null) {
                Collection phantomClasses = scene.getPhantomClasses();
                Intrinsics.checkNotNullExpressionValue(phantomClasses, "getPhantomClasses(...)");
                projectMetrics3.setPhantomClasses(phantomClasses.size());
            }
        }
        IncrementalAnalyze incrementAnalyze = this.mainConfig.getIncrementAnalyze();
        IncrementalAnalyzeImplByChangeFiles incrementalAnalyzeImplByChangeFiles = incrementAnalyze instanceof IncrementalAnalyzeImplByChangeFiles ? (IncrementalAnalyzeImplByChangeFiles) incrementAnalyze : null;
        if (incrementalAnalyzeImplByChangeFiles != null) {
            incrementalAnalyzeImplByChangeFiles.update(scene, locator);
        }
        logger.info(SootCtx::loadClasses$lambda$48);
    }

    private static final Object loadClasses$lambda$34(LinkedList $exclude) {
        return "\nsoot exclude " + $exclude;
    }

    private static final Object loadClasses$lambda$37(Scene $scene, SootCtx this$0) {
        List listListOf = CollectionsKt.listOf("*PROCESS_DIRS");
        String sootClassPath = $scene.getSootClassPath();
        Intrinsics.checkNotNullExpressionValue(sootClassPath, "getSootClassPath(...)");
        List listSplit$default = StringsKt.split$default(sootClassPath, new String[]{File.pathSeparator}, false, 0, 6, (Object) null);
        List listProcess_dir = Options.v().process_dir();
        Intrinsics.checkNotNullExpressionValue(listProcess_dir, "process_dir(...)");
        Iterable $this$sortedBy$iv = CollectionsKt.plus(listListOf, CollectionsKt.minus(listSplit$default, CollectionsKt.toSet(listProcess_dir)));
        return "\nsoot classpath:\n " + CollectionsKt.joinToString$default(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.framework.SootCtx$loadClasses$lambda$37$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String it = (String) t;
                String it2 = (String) t2;
                return ComparisonsKt.compareValues(it, it2);
            }
        }), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v1) -> {
            return loadClasses$lambda$37$lambda$36(r6, v1);
        }, 30, (Object) null) + "\n";
    }

    private static final CharSequence loadClasses$lambda$37$lambda$36(SootCtx this$0, String p) {
        MainConfig mainConfig = this$0.mainConfig;
        Intrinsics.checkNotNull(p);
        return "\t" + mainConfig.tryGetRelativePathFromAbsolutePath(p).getIdentifier();
    }

    private static final Object loadClasses$lambda$40(SootCtx this$0) {
        Iterable iterableProcess_dir = Options.v().process_dir();
        Intrinsics.checkNotNullExpressionValue(iterableProcess_dir, "process_dir(...)");
        Iterable $this$sortedBy$iv = iterableProcess_dir;
        return "\nsoot process_dir:\n " + CollectionsKt.joinToString$default(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.framework.SootCtx$loadClasses$lambda$40$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String it = (String) t;
                String it2 = (String) t2;
                return ComparisonsKt.compareValues(it, it2);
            }
        }), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v1) -> {
            return loadClasses$lambda$40$lambda$39(r6, v1);
        }, 30, (Object) null) + "\n";
    }

    private static final CharSequence loadClasses$lambda$40$lambda$39(SootCtx this$0, String p) {
        MainConfig mainConfig = this$0.mainConfig;
        Intrinsics.checkNotNull(p);
        return "\t" + mainConfig.tryGetRelativePathFromAbsolutePath(p).getIdentifier();
    }

    private static final Object loadClasses$lambda$41() {
        return "Before Loading Classes: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    private static final Object loadClasses$lambda$46$lambda$45() {
        return "Load classes done";
    }

    /* compiled from: SootCtx.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.framework.SootCtx$loadClasses$6, reason: invalid class name */
    /* loaded from: SootCtx$loadClasses$6.class */
    /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function1<String, String> {
        AnonymousClass6(Object receiver) {
            super(1, receiver, TextStyle.class, "invoke", "invoke(Ljava/lang/String;)Ljava/lang/String;", 0);
        }

        public final String invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((TextStyle) this.receiver).invoke(p0);
        }
    }

    private static final Object loadClasses$lambda$47() {
        return "After Loading Classes: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    private static final Object loadClasses$lambda$48() {
        return "After Rewrite Classes: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    public void configureSoot() throws IOException {
        Companion.restAll();
        this.mainConfig.validate();
        JdkInfoService.INSTANCE.provide();
        Options options = Options.v();
        Intrinsics.checkNotNull(options);
        configure(options);
        if (!(Companion.getInstance_soot_Scene() == null)) {
            throw new IllegalStateException("Soot should not be initialized in clinit or init. check your plugins".toString());
        }
        Scene scene = Scene.v();
        Intrinsics.checkNotNull(scene);
        configure(scene);
        configureAfterSceneInit(scene, options);
        Main main = Main.v();
        Intrinsics.checkNotNull(main);
        configure(main);
    }

    public static /* synthetic */ void constructSoot$default(SootCtx sootCtx, ProjectFileLocator projectFileLocator, int i, Object obj) throws IllegalAccessException, NoWhenBranchMatchedException, NoSuchFieldException, IllegalArgumentException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructSoot");
        }
        if ((i & 1) != 0) {
            projectFileLocator = null;
        }
        sootCtx.constructSoot(projectFileLocator);
    }

    public void constructSoot(@Nullable ProjectFileLocator locator) throws IllegalAccessException, NoWhenBranchMatchedException, NoSuchFieldException, IllegalArgumentException {
        Scene scene = Scene.v();
        Intrinsics.checkNotNull(scene);
        loadClasses(scene, locator);
    }

    public void releaseCallGraph(@NotNull Scene scene, @NotNull G g) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(g, "g");
        scene.releaseCallGraph();
        scene.releasePointsToAnalysis();
        scene.releaseReachableMethods();
        g.resetSpark();
    }

    public void releaseCallGraph() {
        Scene sceneV = Scene.v();
        Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
        G gV = G.v();
        Intrinsics.checkNotNullExpressionValue(gV, "v(...)");
        releaseCallGraph(sceneV, gV);
    }

    public void onBeforeCallGraphConstruction() {
        SaConfig saConfig = this.mainConfig.getSaConfig();
        if (saConfig != null) {
            ISootInitializeHandler sootConfig = saConfig.getSootConfig();
            if (sootConfig != null) {
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                Options optionsV = Options.v();
                Intrinsics.checkNotNullExpressionValue(optionsV, "v(...)");
                sootConfig.onBeforeCallGraphConstruction(sceneV, optionsV);
            }
        }
    }

    public void onAfterCallGraphConstruction() {
        SaConfig saConfig = this.mainConfig.getSaConfig();
        if (saConfig != null) {
            ISootInitializeHandler sootConfig = saConfig.getSootConfig();
            if (sootConfig != null) {
                CallGraph callGraph = getCallGraph();
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                Options optionsV = Options.v();
                Intrinsics.checkNotNullExpressionValue(optionsV, "v(...)");
                sootConfig.onAfterCallGraphConstruction(callGraph, sceneV, optionsV);
            }
        }
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
        final String msg$iv = "Rewrite soot scene";
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$1
            public final Object invoke() {
                return "Started: " + msg$iv;
            }
        });
        final LocalDateTime startTime$iv = LocalDateTime.now();
        boolean alreadyLogged$iv = false;
        final Ref.ObjectRef res$iv = new Ref.ObjectRef();
        res$iv.element = Maybe.Companion.empty();
        try {
            try {
                CGUtils.INSTANCE.rewriteJimpleBodyAfterCG();
                res$iv.element = new Maybe(Unit.INSTANCE);
                ((Maybe) res$iv.element).getOrThrow();
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$2
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$3
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
            } catch (Throwable t$iv) {
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$4
                    public final Object invoke() {
                        LocalDateTime localDateTime = startTime$iv;
                        Intrinsics.checkNotNull(localDateTime);
                        String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                        String str = msg$iv;
                        Result.Companion companion = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(t$iv));
                        return "Finished (in " + strElapsedSecFrom + "): " + str + " :: EXCEPTION :: " + "";
                    }
                });
                alreadyLogged$iv = true;
                throw t$iv;
            }
        } catch (Throwable th) {
            if (!alreadyLogged$iv) {
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$5
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.SootCtx$onAfterCallGraphConstruction$$inlined$bracket$default$6
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
            }
            throw th;
        }
    }

    @NotNull
    public final CallGraph getCallGraph() {
        CallGraph callGraph = Scene.v().getCallGraph();
        Intrinsics.checkNotNullExpressionValue(callGraph, "getCallGraph(...)");
        return callGraph;
    }

    public final void setCallGraph(@NotNull CallGraph value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Scene.v().setCallGraph(value);
    }

    @NotNull
    public final CallGraph getSootMethodCallGraph() {
        CallGraph cg = new CallGraph();
        Iterator edgeIterator = getCallGraph().iterator();
        Intrinsics.checkNotNullExpressionValue(edgeIterator, "iterator(...)");
        while (edgeIterator.hasNext()) {
            Object next = edgeIterator.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            Edge e = (Edge) next;
            if (!e.isInvalid()) {
                cg.addEdge(new Edge(e.src(), e.srcUnit(), e.tgt(), e.kind()));
            }
        }
        return cg;
    }

    @NotNull
    public final List<SootMethod> getEntryPoints() {
        List<SootMethod> entryPoints = Scene.v().getEntryPoints();
        Intrinsics.checkNotNullExpressionValue(entryPoints, "getEntryPoints(...)");
        return entryPoints;
    }
}
