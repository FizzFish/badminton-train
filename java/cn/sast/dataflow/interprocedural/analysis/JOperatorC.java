package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007H&¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/JOperatorC;", "V", "Lcn/sast/dataflow/interprocedural/analysis/JOperator;", "markEntry", "pop", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "popHV", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "corax-data-flow"})
/* loaded from: JOperatorC.class */
public interface JOperatorC<V> extends JOperator<V> {
    @NotNull
    JOperatorC<V> markEntry();

    @NotNull
    CompanionV<V> pop();

    @NotNull
    IHeapValues<V> popHV();
}
