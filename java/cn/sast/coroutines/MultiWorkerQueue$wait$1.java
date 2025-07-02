package cn.sast.coroutines;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MultiWorkerQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "MultiWorkerQueue.kt", l = {176}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.MultiWorkerQueue$wait$1")
/* loaded from: MultiWorkerQueue$wait$1.class */
final class MultiWorkerQueue$wait$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MultiWorkerQueue<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MultiWorkerQueue$wait$1(MultiWorkerQueue<T> multiWorkerQueue, Continuation<? super MultiWorkerQueue$wait$1> continuation) {
        super(2, continuation);
        this.this$0 = multiWorkerQueue;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new MultiWorkerQueue$wait$1(this.this$0, continuation);
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
                if (this.this$0.join((Continuation) this) == coroutine_suspended) {
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
