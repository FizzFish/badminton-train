package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.api.config.PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1")
/* loaded from: PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1.class */
final class PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ PreAnalysisCoroutineScope $preAnalysisScope;
    final /* synthetic */ AIAnalysisBaseImpl $this_processAIAnalysisUnits;
    final /* synthetic */ Semaphore $semaphore;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1(PreAnalysisCoroutineScope $preAnalysisScope, AIAnalysisBaseImpl $receiver, Semaphore $semaphore, Continuation<? super PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1> continuation) {
        super(2, continuation);
        this.$preAnalysisScope = $preAnalysisScope;
        this.$this_processAIAnalysisUnits = $receiver;
        this.$semaphore = $semaphore;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1 = new PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1(this.$preAnalysisScope, this.$this_processAIAnalysisUnits, this.$semaphore, continuation);
        preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1.L$0 = value;
        return preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$2$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                this.$preAnalysisScope.setScope($this$coroutineScope);
                this.$this_processAIAnalysisUnits.initializeClassCallBacks($this$coroutineScope, this.$semaphore);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
