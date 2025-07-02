package cn.sast.cli.command;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.IPAnalysisEngine;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0006\n��\n\u0002\u0010\u0002\u0010��\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""})
@DebugMetadata(f = "FySastCli.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.cli.command.FySastCli$runAnalyze$5$3")
/* loaded from: FySastCli$runAnalyze$5$3.class */
final class FySastCli$runAnalyze$5$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FySastCli this$0;
    final /* synthetic */ IPAnalysisEngine $analyzer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$runAnalyze$5$3(FySastCli this$0, IPAnalysisEngine $analyzer, Continuation<? super FySastCli$runAnalyze$5$3> $completion) {
        super(1, $completion);
        this.this$0 = this$0;
        this.$analyzer = $analyzer;
    }

    public final Continuation<Unit> create(Continuation<?> $completion) {
        return new FySastCli$runAnalyze$5$3(this.this$0, this.$analyzer, $completion);
    }

    public final Object invoke(Continuation<? super Unit> p1) {
        return create(p1).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) throws IOException {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                if (this.this$0.getSerializeCG()) {
                    this.$analyzer.dump(this.this$0.getOutput());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
