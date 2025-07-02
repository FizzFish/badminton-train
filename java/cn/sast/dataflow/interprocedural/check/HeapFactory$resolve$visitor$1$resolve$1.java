package cn.sast.dataflow.interprocedural.check;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "HeapFactory.kt", l = {321, 331, 340, 373, 401, 409, 417, 446, 451}, i = {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"this", "$this$resolve", "expr", "baseSet", "accessPath", "acpIndex", "this", "$this$resolve", "expr", "accessPath", "field", "out", "acpIndex", "this", "$this$resolve", "expr", "accessPath", "field", "out", "acpIndex", "this", "$this$resolve", "expr", "accessPath", "field", "out", "acpIndex", "this", "$this$resolve", "expr", "accessPath", "field", "out", "acpIndex", "this", "$this$resolve", "field", "out", "default", "set", "this", "$this$resolve", "field", "out", "default"}, m = "resolve", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1")
/* loaded from: HeapFactory$resolve$visitor$1$resolve$1.class */
final class HeapFactory$resolve$visitor$1$resolve$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int I$0;
    /* synthetic */ Object result;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$resolve$1(HeapFactory$resolve$visitor$1 this$0, Continuation<? super HeapFactory$resolve$visitor$1$resolve$1> continuation) {
        super(continuation);
        this.this$0 = this$0;
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolve(null, null, null, null, 0, (Continuation) this);
    }
}
