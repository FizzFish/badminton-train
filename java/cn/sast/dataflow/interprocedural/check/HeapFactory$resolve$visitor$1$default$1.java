package cn.sast.dataflow.interprocedural.check;

import com.feysh.corax.config.api.IExpr;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""})
@DebugMetadata(f = "HeapFactory.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$default$1")
/* loaded from: HeapFactory$resolve$visitor$1$default$1.class */
final class HeapFactory$resolve$visitor$1$default$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ HeapFactory this$0;
    final /* synthetic */ IExpr $expr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$default$1(HeapFactory $receiver, IExpr $expr, Continuation<? super HeapFactory$resolve$visitor$1$default$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$expr = $expr;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new HeapFactory$resolve$visitor$1$default$1(this.this$0, this.$expr, continuation);
    }

    public final Object invoke(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                this.this$0.getLogger().error(String.valueOf(this.$expr));
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
