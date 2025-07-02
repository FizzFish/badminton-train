package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IIexGetField;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""})
@DebugMetadata(f = "HeapFactory.kt", l = {472}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$1")
/* loaded from: HeapFactory$resolve$visitor$1$visit$1.class */
final class HeapFactory$resolve$visitor$1$visit$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ IIexGetField $expr;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$visit$1(IIexGetField $expr, HeapFactory$resolve$visitor$1 $receiver, Continuation<? super HeapFactory$resolve$visitor$1$visit$1> continuation) {
        super(2, continuation);
        this.$expr = $expr;
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> heapFactory$resolve$visitor$1$visit$1 = new HeapFactory$resolve$visitor$1$visit$1(this.$expr, this.this$0, continuation);
        heapFactory$resolve$visitor$1$visit$1.L$0 = value;
        return heapFactory$resolve$visitor$1$visit$1;
    }

    public final Object invoke(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                SequenceScope $this$sequence = (SequenceScope) this.L$0;
                Set lhs = SequencesKt.toSet((Sequence) this.$expr.getBase().accept(this.this$0.getSelf()));
                this.label = 1;
                if (this.this$0.resolve($this$sequence, (IExpr) this.$expr, this.this$0.toHV(lhs), this.$expr.getAccessPath(), 0, (Continuation) this) == coroutine_suspended) {
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
