package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.AnalyzeTaskRunner;
import cn.sast.framework.SootCtx;
import cn.sast.framework.engine.FlowDroidEngine;
import cn.sast.framework.engine.PreAnalysisImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.result.ResultCollector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import soot.Scene;
import soot.jimple.infoflow.cfg.BiDirICFGFactory;
import soot.jimple.toolkits.callgraph.CallGraph;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcn/sast/framework/AnalyzeTaskRunner$Env;"})
@DebugMetadata(f = "FySastCli.kt", l = {1118}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.cli.command.FySastCli$runAnalyze$5$5")
/* loaded from: FySastCli$runAnalyze$5$5.class */
final class FySastCli$runAnalyze$5$5 extends SuspendLambda implements Function2<AnalyzeTaskRunner.Env, Continuation<? super Unit>, Object> {
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ MainConfig $mainConfig;
    final /* synthetic */ ProjectFileLocator $locator;
    final /* synthetic */ SootCtx $sootCtx;
    final /* synthetic */ FySastCli$runAnalyze$info$1 $info;
    final /* synthetic */ ResultCollector $result;
    final /* synthetic */ FlowDroidEngine $flowEngine;
    final /* synthetic */ Void $cfgFactory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$runAnalyze$5$5(MainConfig $mainConfig, ProjectFileLocator $locator, SootCtx $sootCtx, FySastCli$runAnalyze$info$1 $info, ResultCollector $result, FlowDroidEngine $flowEngine, Void $cfgFactory, Continuation<? super FySastCli$runAnalyze$5$5> $completion) {
        super(2, $completion);
        this.$mainConfig = $mainConfig;
        this.$locator = $locator;
        this.$sootCtx = $sootCtx;
        this.$info = $info;
        this.$result = $result;
        this.$flowEngine = $flowEngine;
        this.$cfgFactory = $cfgFactory;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
        Continuation<Unit> fySastCli$runAnalyze$5$5 = new FySastCli$runAnalyze$5$5(this.$mainConfig, this.$locator, this.$sootCtx, this.$info, this.$result, this.$flowEngine, this.$cfgFactory, $completion);
        fySastCli$runAnalyze$5$5.L$0 = value;
        return fySastCli$runAnalyze$5$5;
    }

    public final Object invoke(AnalyzeTaskRunner.Env p1, Continuation<? super Unit> p2) {
        return create(p1, p2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                AnalyzeTaskRunner.Env it = (AnalyzeTaskRunner.Env) this.L$0;
                MainConfig mainConfig = this.$mainConfig;
                ProjectFileLocator projectFileLocator = this.$locator;
                CallGraph sootMethodCallGraph = this.$sootCtx.getSootMethodCallGraph();
                FySastCli$runAnalyze$info$1 fySastCli$runAnalyze$info$1 = this.$info;
                ResultCollector resultCollector = this.$result;
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                PreAnalysisImpl analysisImpl = new PreAnalysisImpl(mainConfig, projectFileLocator, sootMethodCallGraph, fySastCli$runAnalyze$info$1, resultCollector, sceneV);
                this.$result.setPreAnalysis(analysisImpl);
                this.label = 1;
                if (this.$flowEngine.analyzeInScene(it.getTask(), it.getProvider(), it.getSootCtx(), analysisImpl, (BiDirICFGFactory) this.$cfgFactory, SetsKt.setOf(this.$result), SetsKt.setOf(this.$result), this.$result, (Continuation) this) == coroutine_suspended) {
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
