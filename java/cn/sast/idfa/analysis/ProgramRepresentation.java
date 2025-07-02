package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: ProgramRepresentation.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u001d\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\u0007J\u001d\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00028��H&¢\u0006\u0002\u0010\fJ#\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000f2\u0006\u0010\u0010\u001a\u00028��2\u0006\u0010\u0011\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\fJ\u001d\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028��H&¢\u0006\u0002\u0010\u0018J#\u0010\u0014\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a2\u0006\u0010\u0017\u001a\u00028��H&¢\u0006\u0002\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcn/sast/idfa/analysis/ProgramRepresentation;", "M", "N", "", "getControlFlowGraph", "Lsoot/toolkits/graph/DirectedGraph;", "method", "(Ljava/lang/Object;)Lsoot/toolkits/graph/DirectedGraph;", "getSummaryControlFlowGraph", "isCall", "", "node", "(Ljava/lang/Object;)Z", "isAnalyzable", "getCalleesOfCallAt", "", "callerMethod", "callNode", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Set;", "isSkipCall", "setOwnerStatement", "", "u", "owner", "(Ljava/lang/Object;Ljava/lang/Object;)V", "g", "", "(Ljava/lang/Iterable;Ljava/lang/Object;)V", "corax-idfa-framework"})
/* loaded from: ProgramRepresentation.class */
public interface ProgramRepresentation<M, N> {
    @Nullable
    DirectedGraph<N> getControlFlowGraph(M m);

    @Nullable
    DirectedGraph<N> getSummaryControlFlowGraph(M m);

    boolean isCall(N n);

    boolean isAnalyzable(M m);

    @NotNull
    Set<M> getCalleesOfCallAt(M m, N n);

    boolean isSkipCall(N n);

    void setOwnerStatement(N n, M m);

    void setOwnerStatement(@NotNull Iterable<? extends N> iterable, M m);
}
