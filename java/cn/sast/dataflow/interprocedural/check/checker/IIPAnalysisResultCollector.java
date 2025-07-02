package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.api.report.IResultCollector;
import cn.sast.api.report.Report;
import cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IIPAnalysisResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "Lcn/sast/api/report/IResultCollector;", "afterAnalyze", "", "analysis", "Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis;", "reportDataFlowBug", "reports", "", "Lcn/sast/api/report/Report;", "corax-data-flow"})
/* loaded from: IIPAnalysisResultCollector.class */
public interface IIPAnalysisResultCollector extends IResultCollector {
    void afterAnalyze(@NotNull InterProceduralValueAnalysis interProceduralValueAnalysis);

    void reportDataFlowBug(@NotNull List<Report> list);

    /* compiled from: IIPAnalysisResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IIPAnalysisResultCollector$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IIPAnalysisResultCollector $this, @NotNull Continuation<? super Unit> continuation) {
            Object objFlush = IResultCollector.DefaultImpls.flush($this, continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }
    }
}
