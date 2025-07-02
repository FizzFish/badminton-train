package cn.sast.framework.result;

import cn.sast.api.report.IResultCollector;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.jimple.infoflow.handlers.ResultsAvailableHandler;
import soot.jimple.infoflow.problems.TaintPropagationResults;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u00012\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lcn/sast/framework/result/IFlowDroidResultCollector;", "Lcn/sast/api/report/IResultCollector;", "Lsoot/jimple/infoflow/problems/TaintPropagationResults$OnTaintPropagationResultAdded;", "Lsoot/jimple/infoflow/handlers/ResultsAvailableHandler;", "corax-framework"})
/* loaded from: IFlowDroidResultCollector.class */
public interface IFlowDroidResultCollector extends IResultCollector, TaintPropagationResults.OnTaintPropagationResultAdded, ResultsAvailableHandler {

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IFlowDroidResultCollector$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IFlowDroidResultCollector $this, @NotNull Continuation<? super Unit> continuation) {
            Object objFlush = IResultCollector.DefaultImpls.flush($this, continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }
    }
}
