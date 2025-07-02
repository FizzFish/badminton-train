package cn.sast.coroutines;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: WorkQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "WorkQueue.kt", l = {37, 41}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "I$0", "L$0", "I$0"}, n = {"$this$launch", "didReportIdle", "$this$launch", "didReportIdle"}, m = "invokeSuspend", c = "cn.sast.coroutines.WorkQueue$start$2$1$1")
/* loaded from: WorkQueue$start$2$1$1.class */
final class WorkQueue$start$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ WorkQueue<T> this$0;
    final /* synthetic */ AtomicInteger $idleCount;
    final /* synthetic */ int $numberOfCoroutines;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WorkQueue$start$2$1$1(WorkQueue<T> workQueue, AtomicInteger $idleCount, int $numberOfCoroutines, Continuation<? super WorkQueue$start$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = workQueue;
        this.$idleCount = $idleCount;
        this.$numberOfCoroutines = $numberOfCoroutines;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> workQueue$start$2$1$1 = new WorkQueue$start$2$1$1(this.this$0, this.$idleCount, this.$numberOfCoroutines, continuation);
        workQueue$start$2$1$1.L$0 = value;
        return workQueue$start$2$1$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        int i;
        CoroutineScope $this$launch;
        int iIncrementAndGet;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                $this$launch = (CoroutineScope) this.L$0;
                i = 0;
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                i = this.I$0;
                $this$launch = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            case 2:
                i = this.I$0;
                $this$launch = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch)) {
            Object nextItem = ChannelResult.getOrNull-impl(this.this$0.getChannel().tryReceive-PtdJZtk());
            if (nextItem == null) {
                if (i == 0) {
                    i = 1;
                    iIncrementAndGet = this.$idleCount.incrementAndGet();
                } else {
                    iIncrementAndGet = this.$idleCount.get();
                }
                int newIdleCount = iIncrementAndGet;
                if (newIdleCount == this.$numberOfCoroutines) {
                    return Unit.INSTANCE;
                }
                this.L$0 = $this$launch;
                this.I$0 = i;
                this.label = 1;
                if (DelayKt.delay(10L, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Function3 function3 = ((WorkQueue) this.this$0).doWork;
                Object obj = this.this$0;
                this.L$0 = $this$launch;
                this.I$0 = i;
                this.label = 2;
                if (function3.invoke(obj, nextItem, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
