package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TaskQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��(\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0086@¢\u0006\u0002\u0010\f\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"logger", "Lmu/KLogger;", "runInMilliSeconds", "", "job", "Lkotlinx/coroutines/Job;", "milliSeconds", "", "name", "", "timeoutAction", "Lkotlin/Function0;", "(Lkotlinx/coroutines/Job;JLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: TaskQueueKt.class */
public final class TaskQueueKt {

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(TaskQueueKt::logger$lambda$0);

    /* compiled from: TaskQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "TaskQueue.kt", l = {109}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "J$0", "J$1"}, n = {"name", "timer", "milliSeconds", "start"}, m = "runInMilliSeconds", c = "cn.sast.common.TaskQueueKt")
    /* renamed from: cn.sast.common.TaskQueueKt$runInMilliSeconds$1, reason: invalid class name */
    /* loaded from: TaskQueueKt$runInMilliSeconds$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return TaskQueueKt.runInMilliSeconds(null, 0L, null, null, (Continuation) this);
        }
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object runInMilliSeconds(@org.jetbrains.annotations.NotNull final kotlinx.coroutines.Job r9, long r10, @org.jetbrains.annotations.NotNull final java.lang.String r12, @org.jetbrains.annotations.NotNull final kotlin.jvm.functions.Function0<kotlin.Unit> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.TaskQueueKt.runInMilliSeconds(kotlinx.coroutines.Job, long, java.lang.String, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object runInMilliSeconds$lambda$1(String $name, long $milliSeconds, long $end, long $start) {
        long j = $end - $start;
        return $name + " runInMilliSeconds cost more than expected expect=" + $milliSeconds + ", actual=" + $name;
    }
}
