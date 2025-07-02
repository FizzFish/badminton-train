package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$PrevCall;"})
@DebugMetadata(f = "CheckerModeling.kt", l = {461}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.checker.CheckerModeling$register$4$4")
/* loaded from: CheckerModeling$register$4$4.class */
final class CheckerModeling$register$4$4 extends SuspendLambda implements Function2<CalleeCBImpl.PrevCall, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ CheckCallBack $cb;
    final /* synthetic */ ACheckCallAnalysis $this_register;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CheckerModeling$register$4$4(CheckCallBack $cb, ACheckCallAnalysis $receiver, Continuation<? super CheckerModeling$register$4$4> continuation) {
        super(2, continuation);
        this.$cb = $cb;
        this.$this_register = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> checkerModeling$register$4$4 = new CheckerModeling$register$4$4(this.$cb, this.$this_register, continuation);
        checkerModeling$register$4$4.L$0 = value;
        return checkerModeling$register$4$4;
    }

    public final Object invoke(CalleeCBImpl.PrevCall p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                CalleeCBImpl.PrevCall $this$put = (CalleeCBImpl.PrevCall) this.L$0;
                this.label = 1;
                if (this.$cb.check($this$put.getHf(), $this$put.getEnv(), $this$put, this.$this_register.getIcfg(), (Continuation) this) == coroutine_suspended) {
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
