package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TaskQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "TaskQueue.kt", l = {79, 80}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.common.TaskQueue$runTask$job$1")
/* loaded from: TaskQueue$runTask$job$1.class */
final class TaskQueue$runTask$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ TaskQueue<TaskData> this$0;
    final /* synthetic */ int $i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TaskQueue$runTask$job$1(TaskQueue<TaskData> taskQueue, int $i, Continuation<? super TaskQueue$runTask$job$1> continuation) {
        super(2, continuation);
        this.this$0 = taskQueue;
        this.$i = $i;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new TaskQueue$runTask$job$1(this.this$0, this.$i, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x004f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0091 -> B:5:0x0035). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r11 = r0
            r0 = r7
            int r0 = r0.label
            switch(r0) {
                case 0: goto L24;
                case 1: goto L52;
                case 2: goto L97;
                default: goto Lac;
            }
        L24:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            cn.sast.common.TaskQueue<TaskData> r0 = r0.this$0
            kotlinx.coroutines.channels.Channel r0 = cn.sast.common.TaskQueue.access$getQueue$p(r0)
            kotlinx.coroutines.channels.ChannelIterator r0 = r0.iterator()
            r9 = r0
        L35:
            r0 = r9
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r2 = r7
            r3 = r9
            r2.L$0 = r3
            r2 = r7
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.hasNext(r1)
            r1 = r0
            r2 = r11
            if (r1 != r2) goto L5f
            r1 = r11
            return r1
        L52:
            r0 = r7
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            r9 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L5f:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto La8
            r0 = r9
            java.lang.Object r0 = r0.next()
            r10 = r0
            r0 = r7
            cn.sast.common.TaskQueue<TaskData> r0 = r0.this$0
            kotlin.jvm.functions.Function3 r0 = cn.sast.common.TaskQueue.access$getAction$p(r0)
            r1 = r10
            r2 = r7
            int r2 = r2.$i
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            r3 = r7
            r4 = r7
            r5 = r9
            r4.L$0 = r5
            r4 = r7
            r5 = 2
            r4.label = r5
            java.lang.Object r0 = r0.invoke(r1, r2, r3)
            r1 = r0
            r2 = r11
            if (r1 != r2) goto La4
            r1 = r11
            return r1
        L97:
            r0 = r7
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            r9 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        La4:
            goto L35
        La8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.TaskQueue$runTask$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
