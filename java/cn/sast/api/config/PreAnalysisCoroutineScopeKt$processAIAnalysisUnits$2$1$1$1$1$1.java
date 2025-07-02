package cn.sast.api.config;

import cn.sast.api.util.IMonitor;
import cn.sast.api.util.PhaseIntervalTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.AIAnalysisApi;
import com.feysh.corax.config.api.AIAnalysisUnit;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
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
@DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {77, 77}, i = {PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1"}, n = {"$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.api.config.PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1")
@SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,99:1\n16#2,8:100\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1\n*L\n76#1:100,8\n*E\n"})
/* loaded from: PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1.class */
final class PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ IMonitor $monitor;
    final /* synthetic */ AIAnalysisUnit $it;
    final /* synthetic */ AIAnalysisBaseImpl $this_processAIAnalysisUnits;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1(IMonitor $monitor, AIAnalysisUnit $it, AIAnalysisBaseImpl $receiver, Continuation<? super PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.$monitor = $monitor;
        this.$it = $it;
        this.$this_processAIAnalysisUnits = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1$1$1(this.$monitor, this.$it, this.$this_processAIAnalysisUnits, continuation);
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
                    $this$bracket$iv = iMonitor != null ? iMonitor.timer("AIAnalysis.processAIAnalysisUnits:" + UtilsKt.getSootTypeName(this.$it.getClass())) : null;
                    AIAnalysisUnit aIAnalysisUnit = this.$it;
                    AIAnalysisApi aIAnalysisApi = this.$this_processAIAnalysisUnits;
                    if ($this$bracket$iv == null) {
                        this.label = 1;
                        if (aIAnalysisUnit.config(aIAnalysisApi, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    s$iv = $this$bracket$iv.start();
                    this.L$0 = $this$bracket$iv;
                    this.L$1 = s$iv;
                    this.label = 2;
                    if (aIAnalysisUnit.config(aIAnalysisApi, this) == coroutine_suspended) {
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
