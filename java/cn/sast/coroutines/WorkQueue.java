package cn.sast.coroutines;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WorkQueue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018��*\u0004\b��\u0010\u00012\u00020\u0002BS\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012B\u0010\u0005\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0��\u0012\u0013\u0012\u00118��¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\f¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��RL\u0010\u0005\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0��\u0012\u0013\u0012\u00118��¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcn/sast/coroutines/WorkQueue;", "T", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "doWork", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "nextItem", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "channel", "Lkotlinx/coroutines/channels/Channel;", "getChannel", "()Lkotlinx/coroutines/channels/Channel;", "start", "numberOfCoroutines", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: WorkQueue.class */
public final class WorkQueue<T> {

    @NotNull
    private final CoroutineDispatcher dispatcher;

    @NotNull
    private final Function3<WorkQueue<T>, T, Continuation<? super Unit>, Object> doWork;

    @NotNull
    private final Channel<T> channel;

    /* JADX WARN: Multi-variable type inference failed */
    public WorkQueue(@NotNull CoroutineDispatcher dispatcher, @NotNull Function3<? super WorkQueue<T>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(function3, "doWork");
        this.dispatcher = dispatcher;
        this.doWork = function3;
        this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    }

    @NotNull
    public final Channel<T> getChannel() {
        return this.channel;
    }

    @Nullable
    public final Object start(int numberOfCoroutines, @NotNull Continuation<? super Unit> continuation) {
        AtomicInteger idleCount = new AtomicInteger(0);
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(numberOfCoroutines, this, idleCount, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* compiled from: WorkQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "WorkQueue.kt", l = {44}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.WorkQueue$start$2")
    @SourceDebugExtension({"SMAP\nWorkQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkQueue.kt\ncn/sast/coroutines/WorkQueue$start$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,58:1\n1557#2:59\n1628#2,3:60\n*S KotlinDebug\n*F\n+ 1 WorkQueue.kt\ncn/sast/coroutines/WorkQueue$start$2\n*L\n17#1:59\n17#1:60,3\n*E\n"})
    /* renamed from: cn.sast.coroutines.WorkQueue$start$2, reason: invalid class name */
    /* loaded from: WorkQueue$start$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ int $numberOfCoroutines;
        final /* synthetic */ WorkQueue<T> this$0;
        final /* synthetic */ AtomicInteger $idleCount;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int $numberOfCoroutines, WorkQueue<T> workQueue, AtomicInteger $idleCount, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$numberOfCoroutines = $numberOfCoroutines;
            this.this$0 = workQueue;
            this.$idleCount = $idleCount;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$numberOfCoroutines, this.this$0, this.$idleCount, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Iterable $this$map$iv = RangesKt.until(0, this.$numberOfCoroutines);
                    WorkQueue<T> workQueue = this.this$0;
                    AtomicInteger atomicInteger = this.$idleCount;
                    int i = this.$numberOfCoroutines;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    IntIterator it = $this$map$iv.iterator();
                    while (it.hasNext()) {
                        it.nextInt();
                        destination$iv$iv.add(BuildersKt.launch$default($this$coroutineScope, ((WorkQueue) workQueue).dispatcher, (CoroutineStart) null, new WorkQueue$start$2$1$1(workQueue, atomicInteger, i, null), 2, (Object) null));
                    }
                    this.label = 1;
                    if (AwaitKt.joinAll((List) destination$iv$iv, (Continuation) this) == coroutine_suspended) {
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
}
