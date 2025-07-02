package cn.sast.dataflow.analysis.constant;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;
import soot.Value;
import soot.toolkits.graph.DirectedBodyGraph;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: ConstantValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/analysis/constant/ConstantValues;", "", "graph", "Lsoot/toolkits/graph/DirectedBodyGraph;", "Lsoot/Unit;", "<init>", "(Lsoot/toolkits/graph/DirectedBodyGraph;)V", "analysis", "Lcn/sast/dataflow/analysis/constant/ConstantPropagation;", "getValueAt", "", "v", "Lsoot/Value;", "unit", "(Lsoot/Value;Lsoot/Unit;)Ljava/lang/Integer;", "corax-data-flow"})
/* loaded from: ConstantValues.class */
public final class ConstantValues {

    @NotNull
    private final ConstantPropagation analysis;

    public ConstantValues(@NotNull DirectedBodyGraph<Unit> directedBodyGraph) {
        Intrinsics.checkNotNullParameter(directedBodyGraph, "graph");
        this.analysis = new ConstantPropagation((DirectedGraph) directedBodyGraph);
        this.analysis.doAnalysis();
    }

    @Nullable
    public final Integer getValueAt(@NotNull Value v, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(unit, "unit");
        CPValue cpValue = ((FlowMap) this.analysis.getFlowBefore(unit)).computeValue(v);
        if (cpValue != CPValue.Companion.getUndef() && cpValue != CPValue.Companion.getNac()) {
            return Integer.valueOf(cpValue.value());
        }
        return null;
    }
}
