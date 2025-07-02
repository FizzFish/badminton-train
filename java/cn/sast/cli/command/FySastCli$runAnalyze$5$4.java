package cn.sast.cli.command;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.FlowDroidEngine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import soot.jimple.infoflow.cfg.BiDirICFGFactory;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0006\n��\n\u0002\u0010\u0002\u0010��\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""})
@DebugMetadata(f = "FySastCli.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.cli.command.FySastCli$runAnalyze$5$4")
/* loaded from: FySastCli$runAnalyze$5$4.class */
final class FySastCli$runAnalyze$5$4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FlowDroidEngine $flowEngine;
    final /* synthetic */ Void $cfgFactory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$runAnalyze$5$4(FlowDroidEngine $flowEngine, Void $cfgFactory, Continuation<? super FySastCli$runAnalyze$5$4> $completion) {
        super(1, $completion);
        this.$flowEngine = $flowEngine;
        this.$cfgFactory = $cfgFactory;
    }

    public final Continuation<Unit> create(Continuation<?> $completion) {
        return new FySastCli$runAnalyze$5$4(this.$flowEngine, this.$cfgFactory, $completion);
    }

    public final Object invoke(Continuation<? super Unit> p1) {
        return create(p1).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                this.$flowEngine.beforeAnalyze((BiDirICFGFactory) this.$cfgFactory);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
