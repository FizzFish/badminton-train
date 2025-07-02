package cn.sast.coroutines;

import cn.sast.common.OS;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.Closeable;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.future.FutureKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MultiWorkerQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��|\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018��*\u0004\b��\u0010\u00012\u00020\u00022\u00020\u0003B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00028��H\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00028��H\u0096@¢\u0006\u0002\u0010\u001cJ \u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00028��2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096@¢\u0006\u0002\u0010!J\r\u0010$\u001a\u00020%*\u00020&H\u0082\bJ\r\u0010'\u001a\u00020%*\u00020&H\u0082\bJ\r\u0010(\u001a\u00020%*\u00020&H\u0082\bJ\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020&H\u0002J\b\u0010,\u001a\u00020\u000bH\u0002J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00028��0\u0013H\u0002J\u0013\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00028��¢\u0006\u0002\u00100J\u0014\u0010.\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028��01J\b\u00102\u001a\u00020\u000bH\u0016J\u000e\u00103\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u00104J\r\u00105\u001a\u00020\u000bH\u0007¢\u0006\u0002\b6J\u001b\u00107\u001a\u00020\u000b2\n\u00108\u001a\u0006\u0012\u0002\b\u000309H\u0002¢\u0006\u0004\b:\u00100R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R,\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0\u0011X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00130\u0011X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00160\u0015X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010)\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n��¨\u0006;"}, d2 = {"Lcn/sast/coroutines/MultiWorkerQueue;", "T", "", "Ljava/io/Closeable;", "name", "", "workersCount", "", "action", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "tasksQueue", "Lkotlinx/coroutines/channels/Channel;", "availableWorkers", "Lkotlinx/coroutines/CancellableContinuation;", "futureAtomicRef", "Lkotlinx/atomicfu/AtomicRef;", "Ljava/util/concurrent/CompletableFuture;", "workerPool", "Lcn/sast/coroutines/OnDemandAllocatingPool;", "Ljava/lang/Thread;", "beforeExecute", "task", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "process", "afterExecute", "t", "", "(Ljava/lang/Object;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tasksAndWorkersCounter", "Lkotlinx/atomicfu/AtomicLong;", "isClosed", "", "", "hasTasks", "hasWorkers", "activeCounter", "resumeJoinCoroutineAndUpdate", "dec", "workerRunLoop", "obtainWorker", "dispatch", "data", "(Ljava/lang/Object;)V", "", "close", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wait", "waitAndBlock", "checkChannelResult", "result", "Lkotlinx/coroutines/channels/ChannelResult;", "checkChannelResult-rs8usWo", "corax-api"})
@SourceDebugExtension({"SMAP\nMultiWorkerQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiWorkerQueue.kt\ncn/sast/coroutines/MultiWorkerQueue\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,186:1\n68#1:199\n70#1:201\n68#1:207\n70#1:212\n70#1:214\n1#2:187\n498#3,4:188\n174#3,4:192\n487#3,3:196\n490#3:200\n487#3,3:204\n490#3:208\n487#3,3:209\n490#3:213\n174#3,4:217\n174#3,4:221\n174#3,4:225\n1863#4,2:202\n1863#4,2:215\n*S KotlinDebug\n*F\n+ 1 MultiWorkerQueue.kt\ncn/sast/coroutines/MultiWorkerQueue\n*L\n119#1:199\n124#1:201\n140#1:207\n145#1:212\n148#1:214\n75#1:188,4\n77#1:192,4\n118#1:196,3\n118#1:200\n140#1:204,3\n140#1:208\n144#1:209,3\n144#1:213\n157#1:217,4\n162#1:221,4\n168#1:225,4\n135#1:202,2\n156#1:215,2\n*E\n"})
/* loaded from: MultiWorkerQueue.class */
public class MultiWorkerQueue<T> implements Cloneable, Closeable {

    @NotNull
    private final String name;
    private final int workersCount;

    @NotNull
    private final Function2<T, Continuation<? super Unit>, Object> action;

    @NotNull
    private final Channel<T> tasksQueue;

    @NotNull
    private final Channel<CancellableContinuation<T>> availableWorkers;

    @NotNull
    private final AtomicRef<CompletableFuture<Unit>> futureAtomicRef;

    @NotNull
    private final OnDemandAllocatingPool<Thread> workerPool;

    @NotNull
    private final AtomicLong tasksAndWorkersCounter;

    @NotNull
    private final AtomicLong activeCounter;

    /* compiled from: MultiWorkerQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "MultiWorkerQueue.kt", l = {48, 49, 50, 55}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, n = {"$this", "task", "$this", "task", "$this", "task"}, m = "process$suspendImpl", c = "cn.sast.coroutines.MultiWorkerQueue")
    /* renamed from: cn.sast.coroutines.MultiWorkerQueue$process$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: MultiWorkerQueue$process$1.class */
    static final class C00071<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        final /* synthetic */ MultiWorkerQueue<T> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00071(MultiWorkerQueue<T> multiWorkerQueue, Continuation<? super C00071> continuation) {
            super(continuation);
            this.this$0 = multiWorkerQueue;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return MultiWorkerQueue.process$suspendImpl(this.this$0, null, (Continuation) this);
        }
    }

    @Nullable
    public Object beforeExecute(T t, @NotNull Continuation<? super Unit> continuation) {
        return beforeExecute$suspendImpl(this, t, continuation);
    }

    @Nullable
    public Object process(T t, @NotNull Continuation<? super Unit> continuation) {
        return process$suspendImpl(this, t, continuation);
    }

    @Nullable
    public Object afterExecute(T t, @Nullable Throwable t2, @NotNull Continuation<? super Unit> continuation) {
        return afterExecute$suspendImpl(this, t, t2, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MultiWorkerQueue(@NotNull String name, int workersCount, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function2, "action");
        this.name = name;
        this.workersCount = workersCount;
        this.action = function2;
        if (!(this.workersCount > 0)) {
            throw new IllegalStateException(("workersCount: " + this.workersCount + " must be greater than zero").toString());
        }
        this.tasksQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.availableWorkers = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.futureAtomicRef = AtomicFU.atomic((Object) null);
        this.workerPool = new OnDemandAllocatingPool<>(this.workersCount, (v1) -> {
            return workerPool$lambda$2(r4, v1);
        });
        this.tasksAndWorkersCounter = AtomicFU.atomic(0L);
        this.activeCounter = AtomicFU.atomic(0L);
    }

    public /* synthetic */ MultiWorkerQueue(String str, int i, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? Math.max(OS.INSTANCE.getMaxThreadNum() - 1, 1) : i, function2);
    }

    @NotNull
    public Object clone() {
        return super.clone();
    }

    private static final Thread workerPool$lambda$2(MultiWorkerQueue this$0, int ctl) {
        String name = this$0.name + "-" + ctl;
        return ThreadsKt.thread$default(false, false, (ClassLoader) null, name, 0, () -> {
            return workerPool$lambda$2$lambda$1(r5);
        }, 23, (Object) null);
    }

    private static final Unit workerPool$lambda$2$lambda$1(MultiWorkerQueue this$0) {
        this$0.workerRunLoop();
        return Unit.INSTANCE;
    }

    static /* synthetic */ <T> Object beforeExecute$suspendImpl(MultiWorkerQueue<T> multiWorkerQueue, T t, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <T> java.lang.Object process$suspendImpl(cn.sast.coroutines.MultiWorkerQueue<T> r7, T r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.coroutines.MultiWorkerQueue.process$suspendImpl(cn.sast.coroutines.MultiWorkerQueue, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ <T> Object afterExecute$suspendImpl(MultiWorkerQueue<T> multiWorkerQueue, T t, Throwable t2, Continuation<? super Unit> continuation) {
        if (t2 != null) {
            t2.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    private final boolean isClosed(long $this$isClosed) {
        return ($this$isClosed & 1) == 1;
    }

    private final boolean hasTasks(long $this$hasTasks) {
        return $this$hasTasks >= 2;
    }

    private final boolean hasWorkers(long $this$hasWorkers) {
        return $this$hasWorkers < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeJoinCoroutineAndUpdate(long dec) {
        long cur$iv;
        long upd$iv;
        Object cur$iv2;
        AtomicLong $this$updateAndGet$iv = this.activeCounter;
        do {
            cur$iv = $this$updateAndGet$iv.getValue();
            upd$iv = cur$iv - dec;
        } while (!$this$updateAndGet$iv.compareAndSet(cur$iv, upd$iv));
        if (upd$iv != 0) {
            if (upd$iv < 0) {
                throw new IllegalStateException("Internal invariants of " + this + " were violated, please file this bug to us. activeCount: " + upd$iv);
            }
            return;
        }
        AtomicRef $this$getAndUpdate$iv = this.futureAtomicRef;
        do {
            cur$iv2 = $this$getAndUpdate$iv.getValue();
        } while (!$this$getAndUpdate$iv.compareAndSet(cur$iv2, (Object) null));
        CompletableFuture completableFuture = (CompletableFuture) cur$iv2;
        if (completableFuture != null) {
            completableFuture.complete(Unit.INSTANCE);
        }
    }

    /* compiled from: MultiWorkerQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "MultiWorkerQueue.kt", l = {93, 94, 194, 101}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.MultiWorkerQueue$workerRunLoop$1")
    @SourceDebugExtension({"SMAP\nMultiWorkerQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiWorkerQueue.kt\ncn/sast/coroutines/MultiWorkerQueue$workerRunLoop$1\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 3 MultiWorkerQueue.kt\ncn/sast/coroutines/MultiWorkerQueue\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,186:1\n487#2,3:187\n490#2:192\n68#3,2:190\n69#3:193\n351#4,11:194\n*S KotlinDebug\n*F\n+ 1 MultiWorkerQueue.kt\ncn/sast/coroutines/MultiWorkerQueue$workerRunLoop$1\n*L\n86#1:187,3\n86#1:192\n87#1:190,2\n91#1:193\n97#1:194,11\n*E\n"})
    /* renamed from: cn.sast.coroutines.MultiWorkerQueue$workerRunLoop$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: MultiWorkerQueue$workerRunLoop$1.class */
    static final class C00081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ MultiWorkerQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00081(MultiWorkerQueue<T> multiWorkerQueue, Continuation<? super C00081> continuation) {
            super(2, continuation);
            this.this$0 = multiWorkerQueue;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new C00081(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00b9 A[Catch: all -> 0x01c4, TRY_LEAVE, TryCatch #0 {all -> 0x01c4, blocks: (B:21:0x009c, B:26:0x00b9, B:32:0x00e2, B:39:0x0109, B:41:0x0168, B:49:0x018a, B:31:0x00dc, B:37:0x00ff, B:48:0x0184, B:54:0x01ad), top: B:65:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00fc  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0109 A[Catch: CancellationException -> 0x01b7, all -> 0x01c4, TRY_ENTER, TryCatch #0 {all -> 0x01c4, blocks: (B:21:0x009c, B:26:0x00b9, B:32:0x00e2, B:39:0x0109, B:41:0x0168, B:49:0x018a, B:31:0x00dc, B:37:0x00ff, B:48:0x0184, B:54:0x01ad), top: B:65:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01aa  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0066  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x01ba -> B:5:0x0030). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 475
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.coroutines.MultiWorkerQueue.C00081.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void workerRunLoop() {
        BuildersKt.runBlocking$default((CoroutineContext) null, new C00081(this, null), 1, (Object) null);
    }

    /* compiled from: MultiWorkerQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/CancellableContinuation;", "T", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "MultiWorkerQueue.kt", l = {115}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.MultiWorkerQueue$obtainWorker$1")
    /* renamed from: cn.sast.coroutines.MultiWorkerQueue$obtainWorker$1, reason: invalid class name */
    /* loaded from: MultiWorkerQueue$obtainWorker$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CancellableContinuation<? super T>>, Object> {
        int label;
        final /* synthetic */ MultiWorkerQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MultiWorkerQueue<T> multiWorkerQueue, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = multiWorkerQueue;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super CancellableContinuation<? super T>> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objReceive = ((MultiWorkerQueue) this.this$0).availableWorkers.receive((Continuation) this);
                    return objReceive == coroutine_suspended ? coroutine_suspended : objReceive;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final CancellableContinuation<T> obtainWorker() {
        CancellableContinuation<T> cancellableContinuation = (CancellableContinuation) ChannelResult.getOrNull-impl(this.availableWorkers.tryReceive-PtdJZtk());
        return cancellableContinuation == null ? (CancellableContinuation) BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(this, null), 1, (Object) null) : cancellableContinuation;
    }

    public final void dispatch(T t) {
        long cur$iv;
        long upd$iv;
        AtomicLong $this$getAndUpdate$iv = this.tasksAndWorkersCounter;
        do {
            cur$iv = $this$getAndUpdate$iv.getValue();
            if ((cur$iv & 1) == 1) {
                throw new IllegalStateException("Dispatcher " + this.name + " was closed, attempted to schedule: " + t);
            }
            upd$iv = cur$iv + 2;
        } while (!$this$getAndUpdate$iv.compareAndSet(cur$iv, upd$iv));
        this.activeCounter.incrementAndGet();
        if (cur$iv < 0) {
            Continuation continuationObtainWorker = obtainWorker();
            Result.Companion companion = Result.Companion;
            continuationObtainWorker.resumeWith(Result.constructor-impl(t));
        } else {
            this.workerPool.allocate();
            Object result = this.tasksQueue.trySend-JP2dKIU(t);
            m102checkChannelResultrs8usWo(result);
        }
    }

    public final void dispatch(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "data");
        Collection<? extends T> $this$forEach$iv = collection;
        Iterator<T> it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            dispatch((MultiWorkerQueue<T>) it.next());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        long cur$iv;
        long upd$iv;
        long cur$iv2;
        long upd$iv2;
        Object cur$iv3;
        AtomicLong $this$getAndUpdate$iv = this.tasksAndWorkersCounter;
        do {
            cur$iv = $this$getAndUpdate$iv.getValue();
            upd$iv = ((cur$iv & 1) > 1L ? 1 : ((cur$iv & 1) == 1L ? 0 : -1)) == 0 ? cur$iv : cur$iv | 1;
        } while (!$this$getAndUpdate$iv.compareAndSet(cur$iv, upd$iv));
        Iterable workers = this.workerPool.close();
        while (true) {
            AtomicLong $this$getAndUpdate$iv2 = this.tasksAndWorkersCounter;
            do {
                cur$iv2 = $this$getAndUpdate$iv2.getValue();
                upd$iv2 = (cur$iv2 > 0L ? 1 : (cur$iv2 == 0L ? 0 : -1)) < 0 ? cur$iv2 + 2 : cur$iv2;
            } while (!$this$getAndUpdate$iv2.compareAndSet(cur$iv2, upd$iv2));
            this.activeCounter.incrementAndGet();
            if (!(cur$iv2 < 0)) {
                break;
            } else {
                CancellableContinuation.DefaultImpls.cancel$default(obtainWorker(), (Throwable) null, 1, (Object) null);
            }
        }
        Iterable $this$forEach$iv = workers;
        for (Object element$iv : $this$forEach$iv) {
            Thread it = (Thread) element$iv;
            it.join();
        }
        AtomicRef $this$getAndUpdate$iv3 = this.futureAtomicRef;
        do {
            cur$iv3 = $this$getAndUpdate$iv3.getValue();
        } while (!$this$getAndUpdate$iv3.compareAndSet(cur$iv3, (Object) null));
    }

    @Nullable
    public final Object join(@NotNull Continuation<? super Unit> continuation) {
        Object cur$iv;
        Object upd$iv;
        Object cur$iv2;
        CompletableFuture it;
        AtomicRef $this$getAndUpdate$iv = this.futureAtomicRef;
        do {
            cur$iv = $this$getAndUpdate$iv.getValue();
            Object it2 = (CompletableFuture) cur$iv;
            Object completableFuture = it2;
            if (completableFuture == null) {
                completableFuture = new CompletableFuture();
            }
            upd$iv = completableFuture;
        } while (!$this$getAndUpdate$iv.compareAndSet(cur$iv, upd$iv));
        CompletableFuture old = (CompletableFuture) cur$iv;
        if (old != null) {
            return FutureKt.await(old, continuation);
        }
        resumeJoinCoroutineAndUpdate(0L);
        AtomicRef $this$getAndUpdate$iv2 = this.futureAtomicRef;
        do {
            cur$iv2 = $this$getAndUpdate$iv2.getValue();
            it = (CompletableFuture) cur$iv2;
        } while (!$this$getAndUpdate$iv2.compareAndSet(cur$iv2, it));
        CompletableFuture completableFuture2 = (CompletableFuture) cur$iv2;
        return completableFuture2 != null ? FutureKt.await(completableFuture2, continuation) : Unit.INSTANCE;
    }

    @JvmName(name = "waitAndBlock")
    public final void waitAndBlock() {
        BuildersKt.runBlocking$default((CoroutineContext) null, new MultiWorkerQueue$wait$1(this, null), 1, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkChannelResult-rs8usWo, reason: not valid java name */
    public final void m102checkChannelResultrs8usWo(Object result) {
        if (!ChannelResult.isSuccess-impl(result)) {
            throw new IllegalStateException("Internal invariants of " + this + " were violated, please file this bug to us", ChannelResult.exceptionOrNull-impl(result));
        }
    }
}
