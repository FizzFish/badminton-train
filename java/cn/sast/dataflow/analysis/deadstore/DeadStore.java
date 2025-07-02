package cn.sast.dataflow.analysis.deadstore;

import cn.sast.dataflow.analysis.IBugReporter;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Unit;
import soot.toolkits.graph.DirectedBodyGraph;

/* compiled from: DeadStore.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/analysis/deadstore/DeadStore;", "", "reporter", "Lcn/sast/dataflow/analysis/IBugReporter;", "<init>", "(Lcn/sast/dataflow/analysis/IBugReporter;)V", "analyze", "", "graph", "Lsoot/toolkits/graph/DirectedBodyGraph;", "Lsoot/Unit;", "corax-data-flow"})
/* loaded from: DeadStore.class */
public final class DeadStore {

    @NotNull
    private final IBugReporter reporter;

    public DeadStore(@NotNull IBugReporter reporter) {
        Intrinsics.checkNotNullParameter(reporter, "reporter");
        this.reporter = reporter;
    }

    public final void analyze(@NotNull DirectedBodyGraph<Unit> directedBodyGraph) {
        Intrinsics.checkNotNullParameter(directedBodyGraph, "graph");
    }
}
