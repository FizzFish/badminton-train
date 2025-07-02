package cn.sast.framework.engine;

import cn.sast.api.util.Timer;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.ISourceFileCheckPoint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisImpl.kt", l = {761, 305, 305}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, 2, 2, 2}, s = {"L$0", "L$1", "L$0", "L$0", "L$4", "L$5"}, n = {"$this$async", "$this$withPermit$iv", "$this$withPermit$iv", "$this$withPermit$iv", "$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1")
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1\n+ 2 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreKt\n+ 3 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,760:1\n81#2,3:761\n85#2,2:772\n16#3,8:764\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1\n*L\n302#1:761,3\n302#1:772,2\n304#1:764,8\n*E\n"})
/* loaded from: PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1.class */
final class PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Semaphore $semaphore;
    final /* synthetic */ IResFile $file;
    final /* synthetic */ PreAnalysisImpl this$0;
    final /* synthetic */ Timer $t;
    final /* synthetic */ PreAnalysisImpl.C00261 this$1;
    final /* synthetic */ Function2<ISourceFileCheckPoint, Continuation<? super T>, Object> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1(Semaphore $semaphore, IResFile $file, PreAnalysisImpl $receiver, Timer $t, PreAnalysisImpl.C00261 $receiver2, Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1> continuation) {
        super(2, continuation);
        this.$semaphore = $semaphore;
        this.$file = $file;
        this.this$0 = $receiver;
        this.$t = $t;
        this.this$1 = $receiver2;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisImpl$atSourceFile$1$asyncResult$1$async$1 = new PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1<>(this.$semaphore, this.$file, this.this$0, this.$t, this.this$1, this.$block, continuation);
        preAnalysisImpl$atSourceFile$1$asyncResult$1$async$1.L$0 = value;
        return preAnalysisImpl$atSourceFile$1$asyncResult$1$async$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super T> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: NullPointerException in pass: AttachTryCatchVisitor
        java.lang.NullPointerException
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$1$asyncResult$1$async$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
