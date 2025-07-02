package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
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
/* loaded from: ACheckCallAnalysis$evalCallAtCaller$1$1.class */
/* synthetic */ class ACheckCallAnalysis$evalCallAtCaller$1$1 extends FunctionReferenceImpl implements Function2<CallerSiteCBImpl.EvalCall, Continuation<? super Unit>, Object>, SuspendFunction {
    ACheckCallAnalysis$evalCallAtCaller$1$1(Object receiver) {
        super(2, receiver, Intrinsics.Kotlin.class, "suspendConversion0", "evalCallAtCaller$lambda$2$suspendConversion0(Lkotlin/jvm/functions/Function1;Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$EvalCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(CallerSiteCBImpl.EvalCall p0, Continuation<? super Unit> continuation) {
        return ACheckCallAnalysis.evalCallAtCaller$lambda$2$suspendConversion0((Function1) this.receiver, p0, continuation);
    }
}
