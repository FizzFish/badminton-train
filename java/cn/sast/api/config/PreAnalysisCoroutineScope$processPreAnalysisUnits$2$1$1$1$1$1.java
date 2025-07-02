package cn.sast.api.config;

import cn.sast.api.util.IMonitor;
import cn.sast.api.util.PhaseIntervalTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.PreAnalysisUnit;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {43, 43}, i = {PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1"}, n = {"$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.api.config.PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1")
@SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,99:1\n16#2,8:100\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1\n*L\n42#1:100,8\n*E\n"})
/* loaded from: PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1.class */
final class PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ IMonitor $monitor;
    final /* synthetic */ PreAnalysisUnit $it;
    final /* synthetic */ PreAnalysisCoroutineScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1(IMonitor $monitor, PreAnalysisUnit $it, PreAnalysisCoroutineScope $receiver, Continuation<? super PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.$monitor = $monitor;
        this.$it = $it;
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1$1$1(this.$monitor, this.$it, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        PhaseIntervalTimer.Snapshot s$iv;
        PhaseIntervalTimer $this$bracket$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    IMonitor iMonitor = this.$monitor;
                    $this$bracket$iv = iMonitor != null ? iMonitor.timer("PreAnalysis.processPreAnalysisUnits:" + UtilsKt.getSootTypeName(this.$it.getClass())) : null;
                    PreAnalysisUnit preAnalysisUnit = this.$it;
                    PreAnalysisCoroutineScope preAnalysisCoroutineScope = this.this$0;
                    if ($this$bracket$iv == null) {
                        this.label = 1;
                        if (preAnalysisUnit.config(preAnalysisCoroutineScope, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    s$iv = $this$bracket$iv.start();
                    this.L$0 = $this$bracket$iv;
                    this.L$1 = s$iv;
                    this.label = 2;
                    if (preAnalysisUnit.config(preAnalysisCoroutineScope, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Unit unit = Unit.INSTANCE;
                    $this$bracket$iv.stop(s$iv);
                    return Unit.INSTANCE;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 2:
                    s$iv = (PhaseIntervalTimer.Snapshot) this.L$1;
                    $this$bracket$iv = (PhaseIntervalTimer) this.L$0;
                    ResultKt.throwOnFailure($result);
                    Unit unit2 = Unit.INSTANCE;
                    $this$bracket$iv.stop(s$iv);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            $this$bracket$iv.stop(s$iv);
            throw th;
        }
    }
}
