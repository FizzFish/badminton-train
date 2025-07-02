package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import soot.Unit;

/* compiled from: IPAnalysisEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "IPAnalysisEngine.kt", l = {204}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "context", "node", "succ"}, m = "normalFlowFunction", c = "cn.sast.framework.engine.IPAnalysisEngine$runAnalysisInScene$analysis$1")
/* loaded from: IPAnalysisEngine$runAnalysisInScene$analysis$1$normalFlowFunction$1.class */
final class IPAnalysisEngine$runAnalysisInScene$analysis$1$normalFlowFunction$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    /* synthetic */ Object result;
    final /* synthetic */ IPAnalysisEngine$runAnalysisInScene$analysis$1 this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IPAnalysisEngine$runAnalysisInScene$analysis$1$normalFlowFunction$1(IPAnalysisEngine$runAnalysisInScene$analysis$1 this$0, Continuation<? super IPAnalysisEngine$runAnalysisInScene$analysis$1$normalFlowFunction$1> continuation) {
        super(continuation);
        this.this$0 = this$0;
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.normalFlowFunction((AIContext) null, (Unit) null, (Unit) null, (IFact<IValue>) null, (AtomicBoolean) null, (Continuation<? super IFact<IValue>>) this);
    }
}
