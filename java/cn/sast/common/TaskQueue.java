package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;

/* compiled from: TaskQueue.kt */
@Deprecated(message = "Use MultiWorkerQueue instead.", replaceWith = @ReplaceWith(expression = "MultiWorkerQueue", imports = {}))
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018�� \u0019*\u0004\b��\u0010\u00012\u00020\u0002:\u0001\u0019BC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012(\u0010\u0007\u001a$\b\u0001\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00028��2\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0010\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0015J\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R2\u0010\u0007\u001a$\b\u0001\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001a"}, d2 = {"Lcn/sast/common/TaskQueue;", "TaskData", "", "name", "", "numberThreads", "", "action", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "queue", "Lkotlinx/coroutines/channels/Channel;", "addTask", "taskData", "isLast", "", "(Ljava/lang/Object;Z)V", "", "addTaskFinished", "runTask", "Lkotlinx/coroutines/Job;", "Companion", "corax-api"})
/* loaded from: TaskQueue.class */
public final class TaskQueue<TaskData> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String name;
    private final int numberThreads;

    @NotNull
    private final Function3<TaskData, Integer, Continuation<? super Unit>, Object> action;

    @NotNull
    private final Channel<TaskData> queue;

    /* JADX WARN: Multi-variable type inference failed */
    public TaskQueue(@NotNull String name, int numberThreads, @NotNull Function3<? super TaskData, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function3, "action");
        this.name = name;
        this.numberThreads = numberThreads;
        this.action = function3;
        this.queue = ChannelKt.Channel$default(this.numberThreads * 2, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    }

    public /* synthetic */ TaskQueue(String str, int i, Function3 function3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? Math.max(OS.INSTANCE.getMaxThreadNum() - 1, 1) : i, function3);
    }

    /* compiled from: TaskQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcn/sast/common/TaskQueue$Companion;", "", "<init>", "()V", "corax-api"})
    /* loaded from: TaskQueue$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    public static /* synthetic */ void addTask$default(TaskQueue taskQueue, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            z = false;
        }
        taskQueue.addTask(obj, z);
    }

    public final void addTask(TaskData taskdata, boolean isLast) {
        ChannelResult.getOrThrow-impl(ChannelsKt.trySendBlocking(this.queue, taskdata));
        if (isLast) {
            SendChannel.DefaultImpls.close$default(this.queue, (Throwable) null, 1, (Object) null);
        }
    }

    public final void addTask(@NotNull Iterable<? extends TaskData> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "taskData");
        Iterator<? extends TaskData> it = iterable.iterator();
        while (it.hasNext()) {
            addTask(it.next(), false);
        }
    }

    public final void addTaskFinished() {
        SendChannel.DefaultImpls.close$default(this.queue, (Throwable) null, 1, (Object) null);
    }

    @NotNull
    public final Job runTask() {
        CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        ArrayList jobs = new ArrayList();
        int i = this.numberThreads;
        for (int i2 = 0; i2 < i; i2++) {
            Job job = BuildersKt.launch$default(scope, new CoroutineName(this.name + "-" + i2), (CoroutineStart) null, new TaskQueue$runTask$job$1(this, i2, null), 2, (Object) null);
            jobs.add(job);
        }
        return BuildersKt.launch$default(scope, new CoroutineName(this.name + "-joinAll"), (CoroutineStart) null, new AnonymousClass1(jobs, null), 2, (Object) null);
    }

    /* compiled from: TaskQueue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "TaskQueue.kt", l = {85}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.common.TaskQueue$runTask$1")
    /* renamed from: cn.sast.common.TaskQueue$runTask$1, reason: invalid class name */
    /* loaded from: TaskQueue$runTask$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ ArrayList<Job> $jobs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ArrayList<Job> arrayList, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$jobs = arrayList;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass1(this.$jobs, continuation);
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
                    if (AwaitKt.joinAll(this.$jobs, (Continuation) this) == coroutine_suspended) {
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
