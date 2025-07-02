package cn.sast.dataflow.interprocedural.check;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "HeapFactory.kt", l = {310}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"this", "$this$emit"}, m = "emit", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1")
/* loaded from: HeapFactory$resolve$visitor$1$emit$1.class */
final class HeapFactory$resolve$visitor$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    /* synthetic */ Object result;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$emit$1(HeapFactory$resolve$visitor$1 this$0, Continuation<? super HeapFactory$resolve$visitor$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = this$0;
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, null, (Continuation) this);
    }
}
