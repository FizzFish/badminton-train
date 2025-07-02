package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.ForwardInterProceduralAnalysis;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [A, R, M] */
/* compiled from: ForwardInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010��\u001a\u0016\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u00040\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\n"}, d2 = {"<anonymous>", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "M", "A", "R", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {343, 344, 348, 349}, i = {PseudoTopologicalOrderer.REVERSE}, s = {"L$0"}, n = {"prevCallRes"}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processAndReturnResult$2$1")
/* loaded from: ForwardInterProceduralAnalysis$processAndReturnResult$2$1.class */
final class ForwardInterProceduralAnalysis$processAndReturnResult$2$1<A, M, R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<M, A, R>>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;

    /* JADX INFO: Incorrect field signature: TCTX; */
    final /* synthetic */ Context $context;
    final /* synthetic */ M $callee;
    final /* synthetic */ N $node;
    final /* synthetic */ N $succ;
    final /* synthetic */ A $callSiteValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Incorrect types in method signature: (Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis<TM;TN;TA;TR;TCTX;>;TCTX;TM;TN;TN;TA;Lkotlin/coroutines/Continuation<-Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$processAndReturnResult$2$1;>;)V */
    /* JADX WARN: Multi-variable type inference failed */
    ForwardInterProceduralAnalysis$processAndReturnResult$2$1(ForwardInterProceduralAnalysis $receiver, Context $context, Object obj, Object obj2, Object obj3, Object obj4, Continuation $completion) {
        super(2, $completion);
        this.this$0 = $receiver;
        this.$context = $context;
        this.$callee = obj;
        this.$node = obj2;
        this.$succ = obj3;
        this.$callSiteValue = obj4;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new ForwardInterProceduralAnalysis$processAndReturnResult$2$1<>(this.this$0, this.$context, this.$callee, this.$node, this.$succ, this.$callSiteValue, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<M, A, R>> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$processAndReturnResult$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
