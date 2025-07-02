package cn.sast.api.config;

import cn.sast.api.util.IMonitor;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.AIAnalysisUnit;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {100, 75}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$0"}, n = {"$this$launch", "$this$withPermit$iv", "$this$withPermit$iv"}, m = "invokeSuspend", c = "cn.sast.api.config.PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1")
@SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1\n+ 2 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreKt\n*L\n1#1,99:1\n81#2,6:100\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1\n*L\n74#1:100,6\n*E\n"})
/* loaded from: PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1.class */
final class PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Semaphore $semaphore;
    final /* synthetic */ AIAnalysisUnit $it;
    final /* synthetic */ IMonitor $monitor;
    final /* synthetic */ AIAnalysisBaseImpl $this_processAIAnalysisUnits;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1(Semaphore $semaphore, AIAnalysisUnit $it, IMonitor $monitor, AIAnalysisBaseImpl $receiver, Continuation<? super PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1> continuation) {
        super(2, continuation);
        this.$semaphore = $semaphore;
        this.$it = $it;
        this.$monitor = $monitor;
        this.$this_processAIAnalysisUnits = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1 = new PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1(this.$semaphore, this.$it, this.$monitor, this.$this_processAIAnalysisUnits, continuation);
        preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1.L$0 = value;
        return preAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$2$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
