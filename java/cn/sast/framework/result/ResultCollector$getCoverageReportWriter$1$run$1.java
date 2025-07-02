package cn.sast.framework.result;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.ResultCollector;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "ResultCollector.kt", l = {264, 264}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "$this$bracket$iv", "s$iv", "alreadyLogged$iv"}, m = "run", c = "cn.sast.framework.result.ResultCollector$getCoverageReportWriter$1")
/* loaded from: ResultCollector$getCoverageReportWriter$1$run$1.class */
final class ResultCollector$getCoverageReportWriter$1$run$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int I$0;
    /* synthetic */ Object result;
    final /* synthetic */ ResultCollector.C00441 this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultCollector$getCoverageReportWriter$1$run$1(ResultCollector.C00441 this$0, Continuation<? super ResultCollector$getCoverageReportWriter$1$run$1> continuation) {
        super(continuation);
        this.this$0 = this$0;
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(null, (Continuation) this);
    }
}
