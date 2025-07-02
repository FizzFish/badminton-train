package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: TaskQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "TaskQueue.kt", l = {99}, i = {PointsToGraphKt.pathStrictMod}, s = {"J$0"}, n = {"cancelStart"}, m = "invokeSuspend", c = "cn.sast.common.TaskQueueKt$runInMilliSeconds$2$run$1")
/* loaded from: TaskQueueKt$runInMilliSeconds$2$run$1.class */
final class TaskQueueKt$runInMilliSeconds$2$run$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    int label;
    final /* synthetic */ Job $job;
    final /* synthetic */ Function0<Unit> $timeoutAction;
    final /* synthetic */ String $name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TaskQueueKt$runInMilliSeconds$2$run$1(Job $job, Function0<Unit> function0, String $name, Continuation<? super TaskQueueKt$runInMilliSeconds$2$run$1> continuation) {
        super(2, continuation);
        this.$job = $job;
        this.$timeoutAction = function0;
        this.$name = $name;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new TaskQueueKt$runInMilliSeconds$2$run$1(this.$job, this.$timeoutAction, this.$name, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r7
            int r0 = r0.label
            switch(r0) {
                case 0: goto L20;
                case 1: goto L63;
                default: goto La0;
            }
        L20:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            kotlinx.coroutines.Job r0 = r0.$job
            boolean r0 = r0.isActive()
            if (r0 == 0) goto L9c
            mu.KLogger r0 = cn.sast.common.TaskQueueKt.access$getLogger$p()
            r1 = r7
            java.lang.String r1 = r1.$name
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return invokeSuspend$lambda$0(r1);
            }
            r0.warn(r1)
            long r0 = java.lang.System.currentTimeMillis()
            r9 = r0
            r0 = r7
            kotlinx.coroutines.Job r0 = r0.$job
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r2 = r7
            r3 = r9
            r2.J$0 = r3
            r2 = r7
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = kotlinx.coroutines.JobKt.cancelAndJoin(r0, r1)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L6d
            r1 = r13
            return r1
        L63:
            r0 = r7
            long r0 = r0.J$0
            r9 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L6d:
            long r0 = java.lang.System.currentTimeMillis()
            r11 = r0
            r0 = r11
            r1 = r9
            long r0 = r0 - r1
            r1 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L92
            mu.KLogger r0 = cn.sast.common.TaskQueueKt.access$getLogger$p()
            r1 = r7
            java.lang.String r1 = r1.$name
            r2 = r11
            r3 = r9
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return invokeSuspend$lambda$1(r1, r2, r3);
            }
            r0.warn(r1)
        L92:
            r0 = r7
            kotlin.jvm.functions.Function0<kotlin.Unit> r0 = r0.$timeoutAction
            java.lang.Object r0 = r0.invoke()
        L9c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        La0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.TaskQueueKt$runInMilliSeconds$2$run$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final Object invokeSuspend$lambda$0(String $name) {
        return $name + " runInMilliSeconds timeout";
    }

    private static final Object invokeSuspend$lambda$1(String $name, long $cancelEnd, long $cancelStart) {
        return $name + " runInMilliSeconds cancelAndJoin takes " + ($cancelEnd - $cancelStart);
    }
}
