package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import soot.IdentityUnit;
import soot.Trap;
import soot.Unit;
import soot.Value;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.ExceptionalGraph;
import soot.toolkits.scalar.FlowAnalysis;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ$\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/FlowAnalysisOp;", "", "<init>", "()V", "mergeInto", "", "succNode", "Lsoot/Unit;", "inout", "Lcn/sast/dataflow/infoflow/svfa/FlowFact;", "in1", "getFlow", "Lsoot/toolkits/scalar/FlowAnalysis$Flow;", "graph", "Lsoot/toolkits/graph/DirectedGraph;", "from", "to", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparsePropgrateAnalyze.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/FlowAnalysisOp\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,420:1\n1863#2,2:421\n1863#2,2:423\n*S KotlinDebug\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/FlowAnalysisOp\n*L\n130#1:421,2\n133#1:423,2\n*E\n"})
/* loaded from: FlowAnalysisOp.class */
public final class FlowAnalysisOp {

    @NotNull
    public static final FlowAnalysisOp INSTANCE = new FlowAnalysisOp();

    private FlowAnalysisOp() {
    }

    public final void mergeInto(@NotNull Unit succNode, @NotNull FlowFact inout, @NotNull FlowFact in1) {
        Intrinsics.checkNotNullParameter(succNode, "succNode");
        Intrinsics.checkNotNullParameter(inout, "inout");
        Intrinsics.checkNotNullParameter(in1, "in1");
        Function1 fx = (v2) -> {
            return mergeInto$lambda$0(r0, r1, v2);
        };
        Iterable $this$forEach$iv = inout.getData().keySet();
        for (Object element$iv : $this$forEach$iv) {
            Value it = (Value) element$iv;
            fx.invoke(it);
        }
        Iterable $this$forEach$iv2 = in1.getData().keySet();
        for (Object element$iv2 : $this$forEach$iv2) {
            Value it2 = (Value) element$iv2;
            fx.invoke(it2);
        }
    }

    private static final kotlin.Unit mergeInto$lambda$0(FlowFact $inout, FlowFact $in1, Value k) {
        Intrinsics.checkNotNullParameter(k, "k");
        PersistentSet persistentSetPersistentHashSetOf = (PersistentSet) $inout.getData().get(k);
        if (persistentSetPersistentHashSetOf == null) {
            persistentSetPersistentHashSetOf = ExtensionsKt.persistentHashSetOf();
        }
        PersistentSet u = persistentSetPersistentHashSetOf;
        Iterable iterable = (PersistentSet) $in1.getData().get(k);
        PersistentSet set = ExtensionsKt.plus(u, iterable != null ? iterable : ExtensionsKt.persistentHashSetOf());
        $inout.setData($inout.getData().put(k, set));
        return kotlin.Unit.INSTANCE;
    }

    @NotNull
    public final FlowAnalysis.Flow getFlow(@NotNull DirectedGraph<Unit> directedGraph, @NotNull Unit from, @NotNull Unit to) {
        Intrinsics.checkNotNullParameter(directedGraph, "graph");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        if ((to instanceof IdentityUnit) && (directedGraph instanceof ExceptionalGraph)) {
            ExceptionalGraph g = (ExceptionalGraph) directedGraph;
            List exceptionalPredsOf = g.getExceptionalPredsOf(to);
            Intrinsics.checkNotNullExpressionValue(exceptionalPredsOf, "getExceptionalPredsOf(...)");
            if (!exceptionalPredsOf.isEmpty()) {
                for (ExceptionalGraph.ExceptionDest exd : g.getExceptionDests(from)) {
                    Trap trap = exd.getTrap();
                    if (trap != null && trap.getHandlerUnit() == to) {
                        return FlowAnalysis.Flow.IN;
                    }
                }
            }
        }
        return FlowAnalysis.Flow.OUT;
    }
}
