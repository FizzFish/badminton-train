package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.AnalyzeTaskRunner;
import cn.sast.framework.SootCtx;
import cn.sast.framework.engine.IPAnalysisEngine;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.metrics.MetricsMonitor;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.result.ResultCollector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcn/sast/framework/AnalyzeTaskRunner$Env;"})
@DebugMetadata(f = "FySastCli.kt", l = {1076, 1076, 1081, 1081}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 3, 3}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1"}, n = {"it", "it", "$this$bracket$iv", "s$iv", "$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.cli.command.FySastCli$runAnalyze$5$2")
@SourceDebugExtension({"SMAP\nFySastCli.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FySastCli.kt\ncn/sast/cli/command/FySastCli$runAnalyze$5$2\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,1247:1\n16#2,8:1248\n16#2,8:1256\n*S KotlinDebug\n*F\n+ 1 FySastCli.kt\ncn/sast/cli/command/FySastCli$runAnalyze$5$2\n*L\n1074#1:1248,8\n1080#1:1256,8\n*E\n"})
/* loaded from: FySastCli$runAnalyze$5$2.class */
final class FySastCli$runAnalyze$5$2 extends SuspendLambda implements Function2<AnalyzeTaskRunner.Env, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ FySastCli this$0;
    final /* synthetic */ MetricsMonitor $monitor;
    final /* synthetic */ DataFlowOptions $dataFlowOptions;
    final /* synthetic */ TargetOptions $target;
    final /* synthetic */ MainConfig $mainConfig;
    final /* synthetic */ SootCtx $sootCtx;
    final /* synthetic */ ProjectFileLocator $locator;
    final /* synthetic */ FySastCli$runAnalyze$info$1 $info;
    final /* synthetic */ ResultCollector $result;
    final /* synthetic */ IPAnalysisEngine $analyzer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$runAnalyze$5$2(FySastCli this$0, MetricsMonitor $monitor, DataFlowOptions $dataFlowOptions, TargetOptions $target, MainConfig $mainConfig, SootCtx $sootCtx, ProjectFileLocator $locator, FySastCli$runAnalyze$info$1 $info, ResultCollector $result, IPAnalysisEngine $analyzer, Continuation<? super FySastCli$runAnalyze$5$2> $completion) {
        super(2, $completion);
        this.this$0 = this$0;
        this.$monitor = $monitor;
        this.$dataFlowOptions = $dataFlowOptions;
        this.$target = $target;
        this.$mainConfig = $mainConfig;
        this.$sootCtx = $sootCtx;
        this.$locator = $locator;
        this.$info = $info;
        this.$result = $result;
        this.$analyzer = $analyzer;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
        Continuation<Unit> fySastCli$runAnalyze$5$2 = new FySastCli$runAnalyze$5$2(this.this$0, this.$monitor, this.$dataFlowOptions, this.$target, this.$mainConfig, this.$sootCtx, this.$locator, this.$info, this.$result, this.$analyzer, $completion);
        fySastCli$runAnalyze$5$2.L$0 = value;
        return fySastCli$runAnalyze$5$2;
    }

    public final Object invoke(AnalyzeTaskRunner.Env p1, Continuation<? super Unit> p2) {
        return create(p1, p2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x016d A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0217  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 708
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.cli.command.FySastCli$runAnalyze$5$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
