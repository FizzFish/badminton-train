package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IClassCheckPoint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import soot.SootClass;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisImpl.kt", l = {761, 153}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$0"}, n = {"$this$async", "$this$withPermit$iv", "$this$withPermit$iv"}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1")
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1\n+ 2 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreKt\n*L\n1#1,760:1\n81#2,6:761\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1\n*L\n151#1:761,6\n*E\n"})
/* loaded from: PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1.class */
final class PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ PreAnalysisImpl this$0;
    final /* synthetic */ SootClass $sootClass;
    final /* synthetic */ PreAnalysisImpl.C00221 this$1;
    final /* synthetic */ Function2<IClassCheckPoint, Continuation<? super T>, Object> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1(PreAnalysisImpl $receiver, SootClass $sootClass, PreAnalysisImpl.C00221 $receiver2, Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$sootClass = $sootClass;
        this.this$1 = $receiver2;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisImpl$atClass$1$asyncResult$1$1$1$1 = new PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1<>(this.this$0, this.$sootClass, this.this$1, this.$block, continuation);
        preAnalysisImpl$atClass$1$asyncResult$1$1$1$1.L$0 = value;
        return preAnalysisImpl$atClass$1$asyncResult$1$1$1$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super T> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x011b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl$atClass$1$asyncResult$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
