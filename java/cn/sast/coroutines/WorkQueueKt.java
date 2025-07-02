package cn.sast.coroutines;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WorkQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��<\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001av\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b2B\u0010\t\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000b\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\n¢\u0006\u0002\b\u0011H\u0086@¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"runWork", "", "T", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "numberOfCoroutines", "", "initialWorkload", "", "doWork", "Lkotlin/Function3;", "Lcn/sast/coroutines/WorkQueue;", "Lkotlin/ParameterName;", "name", "nextItem", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineDispatcher;ILjava/util/Collection;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: WorkQueueKt.class */
public final class WorkQueueKt {

    /* compiled from: WorkQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "WorkQueue.kt", l = {56, 57}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "I$0"}, n = {"queue", "numberOfCoroutines"}, m = "runWork", c = "cn.sast.coroutines.WorkQueueKt")
    /* renamed from: cn.sast.coroutines.WorkQueueKt$runWork$1, reason: invalid class name */
    /* loaded from: WorkQueueKt$runWork$1.class */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
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
            return WorkQueueKt.runWork(null, 0, null, null, (Continuation) this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object runWork(@org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineDispatcher r6, int r7, @org.jetbrains.annotations.NotNull java.util.Collection<? extends T> r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super cn.sast.coroutines.WorkQueue<T>, ? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.coroutines.WorkQueueKt.runWork(kotlinx.coroutines.CoroutineDispatcher, int, java.util.Collection, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
