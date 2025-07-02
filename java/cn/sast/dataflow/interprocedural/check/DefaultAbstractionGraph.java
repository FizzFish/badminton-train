package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;
import soot.util.dot.DotGraph;
import soot.util.dot.DotGraphEdge;
import soot.util.dot.DotGraphNode;

/* compiled from: AbstractionGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/DefaultAbstractionGraph;", "Lcn/sast/dataflow/interprocedural/check/AbstractionGraph;", "sink", "Lsoot/jimple/infoflow/data/Abstraction;", "<init>", "(Lsoot/jimple/infoflow/data/Abstraction;)V", "plot", "Lsoot/util/dot/DotGraph;", "cfg", "Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "corax-data-flow"})
/* loaded from: DefaultAbstractionGraph.class */
public final class DefaultAbstractionGraph extends AbstractionGraph {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultAbstractionGraph(@NotNull Abstraction sink) {
        super(sink);
        Intrinsics.checkNotNullParameter(sink, "sink");
        int size = getAbsChain().size();
        setUnitToSuccs(new IdentityHashMap<>((size * 2) + 1));
        setUnitToPreds(new IdentityHashMap<>((size * 2) + 1));
        buildUnexceptionalEdges(getUnitToPreds(), getUnitToSuccs());
        buildHeadsAndTails();
    }

    @NotNull
    public final DotGraph plot(@NotNull IInfoflowCFG cfg) {
        DotGraphNode dotGraphNode;
        DotGraphNode dotGraphNode2;
        Stmt it;
        Intrinsics.checkNotNullParameter(cfg, "cfg");
        DotGraph dot = new DotGraph("AbsGraph");
        dot.setGraphAttribute("style", "filled");
        dot.setGraphAttribute("color", "lightgrey");
        dot.setGraphAttribute("rankdir", "LR");
        dot.setGraphAttribute("ranksep", "8");
        dot.setNodeShape("box");
        Map map = new LinkedHashMap();
        Iterator<Abstraction> it2 = getAbsChain().iterator();
        Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
        while (it2.hasNext()) {
            Abstraction next = it2.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            Abstraction a = next;
            String name = String.valueOf(System.identityHashCode(a));
            if (a.getCurrentStmt() == null) {
                DotGraphNode node = dot.drawNode(name);
                node.setHTMLLabel(AbstractionGraphKt.getLabel(a, cfg));
                dotGraphNode = node;
            } else {
                SootMethod m = (SootMethod) cfg.getMethodOf(a.getCurrentStmt());
                DotGraph dotGraph = (DotGraph) map.get(m);
                if (dotGraph == null) {
                    DotGraph it3 = dot.createSubGraph("cluster_" + System.identityHashCode(m));
                    map.put(m, it3);
                    it3.setGraphLabel(m.toString());
                    it3.setGraphAttribute("style", "filled");
                    it3.setGraphAttribute("color", "lightgrey");
                    it3.setNodeShape("box");
                    dotGraph = it3;
                }
                DotGraph sub = dotGraph;
                DotGraphNode node2 = sub.drawNode(name);
                node2.setHTMLLabel(AbstractionGraphKt.getLabel(a, cfg));
                dotGraphNode = node2;
            }
            DotGraphNode from = dotGraphNode;
            for (Abstraction s : getSuccsOf(a)) {
                String nameTo = String.valueOf(System.identityHashCode(s));
                if (s.getCurrentStmt() == null) {
                    DotGraphNode nodeTo = dot.drawNode(nameTo);
                    nodeTo.setHTMLLabel(AbstractionGraphKt.getLabel(s, cfg));
                    dotGraphNode2 = nodeTo;
                } else {
                    SootMethod m2 = (SootMethod) cfg.getMethodOf(s.getCurrentStmt());
                    DotGraph dotGraph2 = (DotGraph) map.get(m2);
                    if (dotGraph2 == null) {
                        DotGraph it4 = dot.createSubGraph("cluster_" + System.identityHashCode(m2));
                        map.put(m2, it4);
                        it4.setGraphLabel(m2.toString());
                        it4.setGraphAttribute("style", "filled");
                        it4.setGraphAttribute("color", "lightgrey");
                        it4.setNodeShape("box");
                        dotGraph2 = it4;
                    }
                    DotGraph sub2 = dotGraph2;
                    DotGraphNode nodeTo2 = sub2.drawNode(nameTo);
                    nodeTo2.setHTMLLabel(AbstractionGraphKt.getLabel(s, cfg));
                    dotGraphNode2 = nodeTo2;
                }
                DotGraphNode to = dotGraphNode2;
                DotGraphEdge edge = dot.drawEdge(name, nameTo);
                edge.setAttribute("color", "green");
                if (!Intrinsics.areEqual(s.getCorrespondingCallSite(), s.getCurrentStmt()) && (it = s.getCorrespondingCallSite()) != null) {
                    if (Intrinsics.areEqual(it, a.getCurrentStmt())) {
                        edge.setAttribute("color", "red");
                    } else {
                        edge.setAttribute("style", "dashed");
                        edge.setAttribute("color", "black");
                    }
                }
                if (isTail(s)) {
                    to.setAttribute("color", "red");
                }
            }
            if (isHead(a)) {
                from.setAttribute("color", "blue");
            }
        }
        return dot;
    }
}
