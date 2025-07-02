package cn.sast.cli.command;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.AnalyzeTaskRunner;
import cn.sast.framework.engine.BuiltinAnalysis;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.ResultCollector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcn/sast/framework/AnalyzeTaskRunner$Env;"})
@DebugMetadata(f = "FySastCli.kt", l = {1059}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.cli.command.FySastCli$runAnalyze$5$1")
/* loaded from: FySastCli$runAnalyze$5$1.class */
final class FySastCli$runAnalyze$5$1 extends SuspendLambda implements Function2<AnalyzeTaskRunner.Env, Continuation<? super Unit>, Object> {
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ BuiltinAnalysis $builtinAnalysis;
    final /* synthetic */ ResultCollector $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$runAnalyze$5$1(BuiltinAnalysis $builtinAnalysis, ResultCollector $result, Continuation<? super FySastCli$runAnalyze$5$1> $completion) {
        super(2, $completion);
        this.$builtinAnalysis = $builtinAnalysis;
        this.$result = $result;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
        Continuation<Unit> fySastCli$runAnalyze$5$1 = new FySastCli$runAnalyze$5$1(this.$builtinAnalysis, this.$result, $completion);
        fySastCli$runAnalyze$5$1.L$0 = value;
        return fySastCli$runAnalyze$5$1;
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
                this.label = 1;
                if (this.$builtinAnalysis.analyzeInScene(it.getSootCtx(), this.$result, (Continuation) this) == coroutine_suspended) {
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
