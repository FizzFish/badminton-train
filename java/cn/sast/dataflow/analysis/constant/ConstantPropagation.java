package cn.sast.dataflow.analysis.constant;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Local;
import soot.Unit;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.scalar.ForwardFlowAnalysis;

/* compiled from: ConstantPropagation.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b��\u0018��2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0003H\u0014J\b\u0010\r\u001a\u00020\u0003H\u0014J \u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0014J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0014J\b\u0010\u0014\u001a\u00020\tH\u0016¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/analysis/constant/ConstantPropagation;", "Lsoot/toolkits/scalar/ForwardFlowAnalysis;", "Lsoot/Unit;", "Lcn/sast/dataflow/analysis/constant/FlowMap;", "graph", "Lsoot/toolkits/graph/DirectedGraph;", "<init>", "(Lsoot/toolkits/graph/DirectedGraph;)V", "flowThrough", "", "in", "d", "out", "newInitialFlow", "merge", "in1", "in2", "copy", "source", "dest", "doAnalysis", "corax-data-flow"})
/* loaded from: ConstantPropagation.class */
public final class ConstantPropagation extends ForwardFlowAnalysis<Unit, FlowMap> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstantPropagation(@NotNull DirectedGraph<Unit> directedGraph) {
        super(directedGraph);
        Intrinsics.checkNotNullParameter(directedGraph, "graph");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void flowThrough(@NotNull FlowMap in, @NotNull Unit d, @NotNull FlowMap out) {
        Intrinsics.checkNotNullParameter(in, "in");
        Intrinsics.checkNotNullParameter(d, "d");
        Intrinsics.checkNotNullParameter(out, "out");
        copy(in, out);
        if (d instanceof AssignStmt) {
            Value lVal = ((AssignStmt) d).getLeftOp();
            if (lVal instanceof Local) {
                Value rightVal = ((AssignStmt) d).getRightOp();
                Intrinsics.checkNotNull(rightVal);
                CPValue rightCPValue = in.computeValue(rightVal);
                out.put((Local) lVal, rightCPValue);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: newInitialFlow, reason: merged with bridge method [inline-methods] */
    public FlowMap m108newInitialFlow() {
        return new FlowMap(null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void merge(@NotNull FlowMap in1, @NotNull FlowMap in2, @NotNull FlowMap out) {
        Intrinsics.checkNotNullParameter(in1, "in1");
        Intrinsics.checkNotNullParameter(in2, "in2");
        Intrinsics.checkNotNullParameter(out, "out");
        FlowMap meet = FlowMap.Companion.meet(in1, in2);
        copy(meet, out);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copy(@NotNull FlowMap source, @NotNull FlowMap dest) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.copyFrom(source);
    }

    public void doAnalysis() {
        super.doAnalysis();
    }
}
