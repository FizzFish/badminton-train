package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.MGlobal;
import com.feysh.corax.config.api.MParameter;
import com.feysh.corax.config.api.MReturn;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003j\u0002`\u00060\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/V;", "Lcn/sast/dataflow/interprocedural/check/ValueType;"})
@DebugMetadata(f = "HeapFactory.kt", l = {602}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this$sequence"}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$3")
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$3\n+ 2 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n*L\n1#1,1430:1\n621#2:1431\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$3\n*L\n602#1:1431\n*E\n"})
/* loaded from: HeapFactory$resolve$visitor$1$visit$3.class */
final class HeapFactory$resolve$visitor$1$visit$3 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super CompanionV<IValue>>, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> $atCall;
    final /* synthetic */ IIexLoad $expr;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$visit$3(ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB, IIexLoad $expr, HeapFactory$resolve$visitor$1 $receiver, Continuation<? super HeapFactory$resolve$visitor$1$visit$3> continuation) {
        super(2, continuation);
        this.$atCall = iCallCB;
        this.$expr = $expr;
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> heapFactory$resolve$visitor$1$visit$3 = new HeapFactory$resolve$visitor$1$visit$3(this.$atCall, this.$expr, this.this$0, continuation);
        heapFactory$resolve$visitor$1$visit$3.L$0 = value;
        return heapFactory$resolve$visitor$1$visit$3;
    }

    public final Object invoke(SequenceScope<? super CompanionV<IValue>> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public final Object invokeSuspend(Object $result) throws NoWhenBranchMatchedException {
        Iterator<CompanionV<IValue>> it;
        HeapFactory$resolve$visitor$1 heapFactory$resolve$visitor$1;
        SequenceScope $this$sequence;
        IHeapValues<IValue> global;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                $this$sequence = (SequenceScope) this.L$0;
                ICallCB $this$invokeSuspend_u24lambda_u240 = this.$atCall;
                IIexLoad iIexLoad = this.$expr;
                heapFactory$resolve$visitor$1 = this.this$0;
                MParameter op = iIexLoad.getOp();
                if (Intrinsics.areEqual(op, MReturn.INSTANCE)) {
                    global = $this$invokeSuspend_u24lambda_u240.getReturn();
                } else if (op instanceof MParameter) {
                    global = $this$invokeSuspend_u24lambda_u240.arg(op.getIndex());
                } else {
                    if (!Intrinsics.areEqual(op, MGlobal.INSTANCE)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    global = $this$invokeSuspend_u24lambda_u240.getGlobal();
                }
                IHeapValues r = global;
                it = r.iterator();
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                it = (Iterator) this.L$2;
                heapFactory$resolve$visitor$1 = (HeapFactory$resolve$visitor$1) this.L$1;
                $this$sequence = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            CompanionV v = it.next();
            this.L$0 = $this$sequence;
            this.L$1 = heapFactory$resolve$visitor$1;
            this.L$2 = it;
            this.label = 1;
            if ($this$sequence.yield(v, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
