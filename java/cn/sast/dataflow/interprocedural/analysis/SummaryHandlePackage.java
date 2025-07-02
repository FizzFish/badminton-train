package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: ACheckCallAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u0004*\f0\u0005j\b\u0012\u0004\u0012\u00028��`\u0006H&¨\u0006\u0007"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "V", "", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "corax-data-flow"})
/* loaded from: SummaryHandlePackage.class */
public interface SummaryHandlePackage<V> {
    void register(@NotNull ACheckCallAnalysis aCheckCallAnalysis);
}
