package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.dataflow.infoflow.InfoflowConfigurationExt;
import cn.sast.dataflow.infoflow.provider.MethodSummaryProvider;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.IFlowDroidResultCollector;
import cn.sast.framework.result.IMissingSummaryReporter;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootMethod;
import soot.jimple.infoflow.AbstractInfoflow;
import soot.jimple.infoflow.InfoflowConfiguration;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;
import soot.jimple.infoflow.android.data.AndroidMemoryManager;
import soot.jimple.infoflow.android.source.AccessPathBasedSourceSinkManager;
import soot.jimple.infoflow.cfg.BiDirICFGFactory;
import soot.jimple.infoflow.cfg.DefaultBiDiICFGFactory;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.data.FlowDroidMemoryManager;
import soot.jimple.infoflow.handlers.PostAnalysisHandler;
import soot.jimple.infoflow.handlers.ResultsAvailableHandler;
import soot.jimple.infoflow.problems.TaintPropagationResults;
import soot.jimple.infoflow.results.InfoflowResults;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;
import soot.jimple.infoflow.solver.memory.IMemoryManager;
import soot.jimple.infoflow.solver.memory.IMemoryManagerFactory;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinitionProvider;
import soot.jimple.infoflow.sourcesSinks.manager.ISourceSinkManager;

/* compiled from: FlowDroidEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��v\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� 12\u00020\u0001:\u00011B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J:\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0012\u0010\"\u001a\u00020\u00152\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ^\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0'2\u0006\u0010+\u001a\u00020!H\u0086@¢\u0006\u0002\u0010,JV\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0'2\u0006\u0010+\u001a\u00020!H\u0086@¢\u0006\u0002\u0010-J\u0018\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u0002002\u0006\u0010$\u001a\u00020%H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u00062"}, d2 = {"Lcn/sast/framework/engine/FlowDroidEngine;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "infoFlowConfig", "Lsoot/jimple/infoflow/InfoflowConfiguration;", "extInfoFlowConfig", "Lcn/sast/dataflow/infoflow/InfoflowConfigurationExt;", "<init>", "(Lcn/sast/api/config/MainConfig;Lsoot/jimple/infoflow/InfoflowConfiguration;Lcn/sast/dataflow/infoflow/InfoflowConfigurationExt;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getInfoFlowConfig", "()Lsoot/jimple/infoflow/InfoflowConfiguration;", "getExtInfoFlowConfig", "()Lcn/sast/dataflow/infoflow/InfoflowConfigurationExt;", "sourceSinkManager", "Lsoot/jimple/infoflow/sourcesSinks/manager/ISourceSinkManager;", "sourceSinkProvider", "Lsoot/jimple/infoflow/sourcesSinks/definitions/ISourceSinkDefinitionProvider;", "analyze", "", "preAnalysis", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "soot", "Lcn/sast/framework/SootCtx;", "provider", "Lcn/sast/framework/entries/IEntryPointProvider;", "cfgFactory", "Lsoot/jimple/infoflow/cfg/BiDirICFGFactory;", "result", "Lcn/sast/framework/result/IFlowDroidResultCollector;", "missWrapper", "Lcn/sast/framework/result/IMissingSummaryReporter;", "beforeAnalyze", "analyzeInScene", "task", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "resultAddedHandlers", "", "Lsoot/jimple/infoflow/problems/TaintPropagationResults$OnTaintPropagationResultAdded;", "onResultsAvailable", "Lsoot/jimple/infoflow/handlers/ResultsAvailableHandler;", "methodSummariesMissing", "(Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;Lcn/sast/framework/entries/IEntryPointProvider;Lcn/sast/framework/SootCtx;Lcn/sast/api/config/PreAnalysisCoroutineScope;Lsoot/jimple/infoflow/cfg/BiDirICFGFactory;Ljava/util/Set;Ljava/util/Set;Lcn/sast/framework/result/IMissingSummaryReporter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcn/sast/api/config/PreAnalysisCoroutineScope;Lcn/sast/framework/SootCtx;Lcn/sast/framework/entries/IEntryPointProvider;Lsoot/jimple/infoflow/cfg/BiDirICFGFactory;Ljava/util/Set;Ljava/util/Set;Lcn/sast/framework/result/IMissingSummaryReporter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configureInfoFlow", "infoflow", "Lsoot/jimple/infoflow/AbstractInfoflow;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nFlowDroidEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowDroidEngine.kt\ncn/sast/framework/engine/FlowDroidEngine\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,235:1\n1863#2,2:236\n1628#2,3:251\n49#3,13:238\n62#3,11:254\n*S KotlinDebug\n*F\n+ 1 FlowDroidEngine.kt\ncn/sast/framework/engine/FlowDroidEngine\n*L\n177#1:236,2\n184#1:251,3\n183#1:238,13\n183#1:254,11\n*E\n"})
/* loaded from: FlowDroidEngine.class */
public final class FlowDroidEngine {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final InfoflowConfiguration infoFlowConfig;

    @NotNull
    private final InfoflowConfigurationExt extInfoFlowConfig;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(FlowDroidEngine::logger$lambda$5);

    /* compiled from: FlowDroidEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "FlowDroidEngine.kt", l = {140, 145}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"}, n = {"this", "task", "provider", "soot", "preAnalysis", "cfgFactory", "resultAddedHandlers", "onResultsAvailable", "methodSummariesMissing", "sourceSinkProviderInstance", "this", "task", "provider", "soot", "cfgFactory", "resultAddedHandlers", "onResultsAvailable", "methodSummariesMissing", "sourceSinkProviderInstance", "wrapper"}, m = "analyzeInScene", c = "cn.sast.framework.engine.FlowDroidEngine")
    /* renamed from: cn.sast.framework.engine.FlowDroidEngine$analyzeInScene$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: FlowDroidEngine$analyzeInScene$1.class */
    static final class C00181 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        /* synthetic */ Object result;
        int label;

        C00181(Continuation<? super C00181> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowDroidEngine.this.analyzeInScene(null, null, null, null, null, null, null, null, (Continuation) this);
        }
    }

    public FlowDroidEngine(@NotNull MainConfig mainConfig, @NotNull InfoflowConfiguration infoFlowConfig, @NotNull InfoflowConfigurationExt extInfoFlowConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(infoFlowConfig, "infoFlowConfig");
        Intrinsics.checkNotNullParameter(extInfoFlowConfig, "extInfoFlowConfig");
        this.mainConfig = mainConfig;
        this.infoFlowConfig = infoFlowConfig;
        this.extInfoFlowConfig = extInfoFlowConfig;
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final InfoflowConfiguration getInfoFlowConfig() {
        return this.infoFlowConfig;
    }

    @NotNull
    public final InfoflowConfigurationExt getExtInfoFlowConfig() {
        return this.extInfoFlowConfig;
    }

    @NotNull
    public final ISourceSinkManager sourceSinkManager(@NotNull ISourceSinkDefinitionProvider sourceSinkProvider) {
        Intrinsics.checkNotNullParameter(sourceSinkProvider, "sourceSinkProvider");
        InfoflowAndroidConfiguration config = new InfoflowAndroidConfiguration();
        config.getSourceSinkConfig().merge(this.infoFlowConfig.getSourceSinkConfig());
        return new AccessPathBasedSourceSinkManager(sourceSinkProvider.getSources(), sourceSinkProvider.getSinks(), config);
    }

    /* compiled from: FlowDroidEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/FlowDroidEngine$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: FlowDroidEngine$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$5() {
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void analyze$default(FlowDroidEngine flowDroidEngine, PreAnalysisCoroutineScope preAnalysisCoroutineScope, SootCtx sootCtx, IEntryPointProvider iEntryPointProvider, BiDirICFGFactory biDirICFGFactory, IFlowDroidResultCollector iFlowDroidResultCollector, IMissingSummaryReporter iMissingSummaryReporter, int i, Object obj) {
        if ((i & 8) != 0) {
            biDirICFGFactory = null;
        }
        flowDroidEngine.analyze(preAnalysisCoroutineScope, sootCtx, iEntryPointProvider, biDirICFGFactory, iFlowDroidResultCollector, iMissingSummaryReporter);
    }

    /* compiled from: FlowDroidEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "FlowDroidEngine.kt", l = {83}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.FlowDroidEngine$analyze$1")
    /* renamed from: cn.sast.framework.engine.FlowDroidEngine$analyze$1, reason: invalid class name */
    /* loaded from: FlowDroidEngine$analyze$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ PreAnalysisCoroutineScope $preAnalysis;
        final /* synthetic */ SootCtx $soot;
        final /* synthetic */ IEntryPointProvider $provider;
        final /* synthetic */ BiDirICFGFactory $cfgFactory;
        final /* synthetic */ IFlowDroidResultCollector $result;
        final /* synthetic */ IMissingSummaryReporter $missWrapper;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PreAnalysisCoroutineScope $preAnalysis, SootCtx $soot, IEntryPointProvider $provider, BiDirICFGFactory $cfgFactory, IFlowDroidResultCollector $result, IMissingSummaryReporter $missWrapper, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$preAnalysis = $preAnalysis;
            this.$soot = $soot;
            this.$provider = $provider;
            this.$cfgFactory = $cfgFactory;
            this.$result = $result;
            this.$missWrapper = $missWrapper;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return FlowDroidEngine.this.new AnonymousClass1(this.$preAnalysis, this.$soot, this.$provider, this.$cfgFactory, this.$result, this.$missWrapper, continuation);
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
                    if (FlowDroidEngine.this.analyze(this.$preAnalysis, this.$soot, this.$provider, this.$cfgFactory, SetsKt.setOf(this.$result), SetsKt.setOf(this.$result), this.$missWrapper, (Continuation) this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void analyze(@NotNull PreAnalysisCoroutineScope preAnalysis, @NotNull SootCtx soot, @NotNull IEntryPointProvider provider, @Nullable BiDirICFGFactory cfgFactory, @NotNull IFlowDroidResultCollector result, @NotNull IMissingSummaryReporter missWrapper) {
        Intrinsics.checkNotNullParameter(preAnalysis, "preAnalysis");
        Intrinsics.checkNotNullParameter(soot, "soot");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(missWrapper, "missWrapper");
        BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(preAnalysis, soot, provider, cfgFactory, result, missWrapper, null), 1, (Object) null);
    }

    public static /* synthetic */ void beforeAnalyze$default(FlowDroidEngine flowDroidEngine, BiDirICFGFactory biDirICFGFactory, int i, Object obj) {
        if ((i & 1) != 0) {
            biDirICFGFactory = null;
        }
        flowDroidEngine.beforeAnalyze(biDirICFGFactory);
    }

    public final void beforeAnalyze(@Nullable BiDirICFGFactory cfgFactory) {
        this.infoFlowConfig.setWriteOutputFiles(false);
        if (cfgFactory != null && (this.infoFlowConfig instanceof InfoflowAndroidConfiguration)) {
            String androidPath = this.mainConfig.getAndroidPlatformDir();
            if (cfgFactory instanceof DefaultBiDiICFGFactory) {
                ((DefaultBiDiICFGFactory) cfgFactory).setIsAndroid(androidPath != null);
            }
        }
        if (this.infoFlowConfig instanceof InfoflowAndroidConfiguration) {
            if (!Intrinsics.areEqual(this.infoFlowConfig.getAnalysisFileConfig().getAndroidPlatformDir(), "unused")) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!Intrinsics.areEqual(this.infoFlowConfig.getAnalysisFileConfig().getTargetAPKFile(), "unused")) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        FlowDroidEngineKt.fix(this.infoFlowConfig);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x034a A[LOOP:2: B:39:0x0340->B:41:0x034a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x03fa A[Catch: Throwable -> 0x0496, all -> 0x04b6, LOOP:3: B:44:0x03f0->B:46:0x03fa, LOOP_END, TryCatch #1 {Throwable -> 0x0496, blocks: (B:43:0x03bc, B:44:0x03f0, B:46:0x03fa, B:47:0x0422), top: B:69:0x03bc, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeInScene(@org.jetbrains.annotations.NotNull cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask r10, @org.jetbrains.annotations.NotNull cn.sast.framework.entries.IEntryPointProvider r11, @org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r12, @org.jetbrains.annotations.NotNull cn.sast.api.config.PreAnalysisCoroutineScope r13, @org.jetbrains.annotations.Nullable soot.jimple.infoflow.cfg.BiDirICFGFactory r14, @org.jetbrains.annotations.NotNull java.util.Set<? extends soot.jimple.infoflow.problems.TaintPropagationResults.OnTaintPropagationResultAdded> r15, @org.jetbrains.annotations.NotNull java.util.Set<? extends soot.jimple.infoflow.handlers.ResultsAvailableHandler> r16, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IMissingSummaryReporter r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instructions count: 1294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.FlowDroidEngine.analyzeInScene(cn.sast.framework.entries.IEntryPointProvider$AnalyzeTask, cn.sast.framework.entries.IEntryPointProvider, cn.sast.framework.SootCtx, cn.sast.api.config.PreAnalysisCoroutineScope, soot.jimple.infoflow.cfg.BiDirICFGFactory, java.util.Set, java.util.Set, cn.sast.framework.result.IMissingSummaryReporter, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object analyzeInScene$default(FlowDroidEngine flowDroidEngine, IEntryPointProvider.AnalyzeTask analyzeTask, IEntryPointProvider iEntryPointProvider, SootCtx sootCtx, PreAnalysisCoroutineScope preAnalysisCoroutineScope, BiDirICFGFactory biDirICFGFactory, Set set, Set set2, IMissingSummaryReporter iMissingSummaryReporter, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            biDirICFGFactory = null;
        }
        return flowDroidEngine.analyzeInScene(analyzeTask, iEntryPointProvider, sootCtx, preAnalysisCoroutineScope, biDirICFGFactory, set, set2, iMissingSummaryReporter, continuation);
    }

    private static final Object analyzeInScene$lambda$0(MethodSummaryProvider $wrapper) {
        return "taint wrapper size: " + $wrapper.getClassSummaries().getAllSummaries().size();
    }

    private static final Unit analyzeInScene$lambda$1(IMissingSummaryReporter $methodSummariesMissing, SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        $methodSummariesMissing.reportMissingMethod(method);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object analyze$default(FlowDroidEngine flowDroidEngine, PreAnalysisCoroutineScope preAnalysisCoroutineScope, SootCtx sootCtx, IEntryPointProvider iEntryPointProvider, BiDirICFGFactory biDirICFGFactory, Set set, Set set2, IMissingSummaryReporter iMissingSummaryReporter, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            biDirICFGFactory = null;
        }
        return flowDroidEngine.analyze(preAnalysisCoroutineScope, sootCtx, iEntryPointProvider, biDirICFGFactory, set, set2, iMissingSummaryReporter, continuation);
    }

    @Nullable
    public final Object analyze(@NotNull final PreAnalysisCoroutineScope preAnalysis, @NotNull final SootCtx soot, @NotNull final IEntryPointProvider provider, @Nullable final BiDirICFGFactory cfgFactory, @NotNull final Set<? extends TaintPropagationResults.OnTaintPropagationResultAdded> set, @NotNull final Set<? extends ResultsAvailableHandler> set2, @NotNull final IMissingSummaryReporter methodSummariesMissing, @NotNull Continuation<? super Unit> continuation) {
        beforeAnalyze(cfgFactory);
        Object objCollect = provider.getIterator().collect(new FlowCollector() { // from class: cn.sast.framework.engine.FlowDroidEngine.analyze.3
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((IEntryPointProvider.AnalyzeTask) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(IEntryPointProvider.AnalyzeTask task, Continuation<? super Unit> continuation2) {
                Set entries = CollectionsKt.toMutableSet(task.getEntries());
                Set it = task.getAdditionalEntries();
                if (it != null) {
                    Boxing.boxBoolean(entries.addAll(it));
                }
                Scene.v().setEntryPoints(CollectionsKt.toList(entries));
                soot.constructCallGraph();
                Object objAnalyzeInScene = this.analyzeInScene(task, provider, soot, preAnalysis, cfgFactory, set, set2, methodSummariesMissing, continuation2);
                return objAnalyzeInScene == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnalyzeInScene : Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    private final void configureInfoFlow(AbstractInfoflow infoflow, final IEntryPointProvider.AnalyzeTask task) {
        infoflow.setMemoryManagerFactory(new IMemoryManagerFactory() { // from class: cn.sast.framework.engine.FlowDroidEngine.configureInfoFlow.1
            public final IMemoryManager<Abstraction, soot.Unit> getMemoryManager(boolean tracingEnabled, FlowDroidMemoryManager.PathDataErasureMode erasePathData) {
                return new AndroidMemoryManager<>(tracingEnabled, erasePathData, task.mo276getComponents());
            }
        });
        infoflow.setMemoryManagerFactory((IMemoryManagerFactory) null);
        infoflow.setPostProcessors(SetsKt.setOf(new PostAnalysisHandler() { // from class: cn.sast.framework.engine.FlowDroidEngine.configureInfoFlow.2
            public InfoflowResults onResultsAvailable(InfoflowResults results, IInfoflowCFG cfg) {
                Intrinsics.checkNotNullParameter(results, "results");
                Intrinsics.checkNotNullParameter(cfg, "cfg");
                return results;
            }
        }));
    }
}
