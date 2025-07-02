package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ACheckCallAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: ACheckCallAnalysis$evalCall$1$1.class */
/* synthetic */ class ACheckCallAnalysis$evalCall$1$1 extends FunctionReferenceImpl implements Function2<CalleeCBImpl.EvalCall, Continuation<? super Unit>, Object>, SuspendFunction {
    ACheckCallAnalysis$evalCall$1$1(Object receiver) {
        super(2, receiver, Intrinsics.Kotlin.class, "suspendConversion0", "evalCall$lambda$4$suspendConversion0$3(Lkotlin/jvm/functions/Function1;Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(CalleeCBImpl.EvalCall p0, Continuation<? super Unit> continuation) {
        return ACheckCallAnalysis.evalCall$lambda$4$suspendConversion0$3((Function1) this.receiver, p0, continuation);
    }
}
