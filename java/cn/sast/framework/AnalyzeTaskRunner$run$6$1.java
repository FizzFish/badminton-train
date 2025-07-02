package cn.sast.framework;

import cn.sast.api.util.PhaseIntervalTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.AnalyzeTaskRunner;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnalyzeTaskRunner.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "AnalyzeTaskRunner.kt", l = {109, 109}, i = {PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1"}, n = {"$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.framework.AnalyzeTaskRunner$run$6$1")
@SourceDebugExtension({"SMAP\nAnalyzeTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalyzeTaskRunner.kt\ncn/sast/framework/AnalyzeTaskRunner$run$6$1\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,152:1\n16#2,8:153\n*S KotlinDebug\n*F\n+ 1 AnalyzeTaskRunner.kt\ncn/sast/framework/AnalyzeTaskRunner$run$6$1\n*L\n108#1:153,8\n*E\n"})
/* loaded from: AnalyzeTaskRunner$run$6$1.class */
final class AnalyzeTaskRunner$run$6$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AnalyzeTaskRunner this$0;
    final /* synthetic */ AnalyzeTaskRunner.Analysis $it;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnalyzeTaskRunner$run$6$1(AnalyzeTaskRunner $receiver, AnalyzeTaskRunner.Analysis $it, Continuation<? super AnalyzeTaskRunner$run$6$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$it = $it;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new AnalyzeTaskRunner$run$6$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        PhaseIntervalTimer.Snapshot s$iv;
        PhaseIntervalTimer $this$bracket$iv;
        Unit unit;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    $this$bracket$iv = this.this$0.getMonitor().timer("phase.after:" + this.$it.getPhaseName());
                    AnalyzeTaskRunner.Analysis analysis = this.$it;
                    if ($this$bracket$iv == null) {
                        Function1<Continuation<? super Unit>, Object> after = analysis.getAfter();
                        if (after == null) {
                            return null;
                        }
                        this.label = 1;
                        if (after.invoke(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    s$iv = $this$bracket$iv.start();
                    Function1<Continuation<? super Unit>, Object> after2 = analysis.getAfter();
                    if (after2 == null) {
                        unit = null;
                        Unit unit2 = unit;
                        return unit2;
                    }
                    this.L$0 = $this$bracket$iv;
                    this.L$1 = s$iv;
                    this.label = 2;
                    if (after2.invoke(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    unit = Unit.INSTANCE;
                    Unit unit22 = unit;
                    return unit22;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 2:
                    s$iv = (PhaseIntervalTimer.Snapshot) this.L$1;
                    $this$bracket$iv = (PhaseIntervalTimer) this.L$0;
                    ResultKt.throwOnFailure($result);
                    unit = Unit.INSTANCE;
                    Unit unit222 = unit;
                    return unit222;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } finally {
            $this$bracket$iv.stop(s$iv);
        }
    }
}
