package cn.sast.graph;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.toolkits.graph.DirectedGraph;
import soot.util.dot.DotGraph;
import soot.util.dot.DotGraphEdge;
import soot.util.dot.DotGraphNode;

/* compiled from: GraphPlot.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018�� \u0012*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\u0012B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u0011\u0010\u000e\u001a\u00020\r*\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00028��*\u00028\u0001H&¢\u0006\u0002\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcn/sast/graph/GraphPlot;", "C", "N", "", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "<init>", "(Lsoot/toolkits/graph/DirectedGraph;)V", "getCfg", "()Lsoot/toolkits/graph/DirectedGraph;", "plot", "Lsoot/util/dot/DotGraph;", "graphName", "", "getLabel", "(Ljava/lang/Object;)Ljava/lang/String;", "getNodeContainer", "(Ljava/lang/Object;)Ljava/lang/Object;", "Companion", "corax-api"})
/* loaded from: GraphPlot.class */
public abstract class GraphPlot<C, N> {

    @NotNull
    private final DirectedGraph<N> cfg;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(GraphPlot::logger$lambda$5);

    public abstract C getNodeContainer(N n);

    public GraphPlot(@NotNull DirectedGraph<N> directedGraph) {
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        this.cfg = directedGraph;
    }

    @NotNull
    public final DirectedGraph<N> getCfg() {
        return this.cfg;
    }

    public static /* synthetic */ DotGraph plot$default(GraphPlot graphPlot, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: plot");
        }
        if ((i & 1) != 0) {
            str = "DirectedGraph";
        }
        return graphPlot.plot(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final DotGraph plot(@NotNull String graphName) {
        Intrinsics.checkNotNullParameter(graphName, "graphName");
        DotGraph dot = new DotGraph(graphName);
        dot.setGraphAttribute("style", "filled");
        dot.setGraphAttribute("color", "lightgrey");
        dot.setGraphAttribute("rankdir", "LR");
        dot.setGraphAttribute("ranksep", "8");
        dot.setNodeShape("box");
        Map map = new LinkedHashMap();
        DotGraph it = dot.createSubGraph("cluster_start");
        it.setGraphLabel("START");
        it.setGraphAttribute("style", "filled");
        it.setGraphAttribute("color", "lightgrey");
        it.setNodeShape("box");
        Iterator it2 = this.cfg.iterator();
        Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
        while (it2.hasNext()) {
            Object node = it2.next();
            String nodeName = String.valueOf(node);
            Object nodeContainer = getNodeContainer(node);
            DotGraph dotGraph = (DotGraph) map.get(nodeContainer);
            if (dotGraph == null) {
                DotGraph it3 = dot.createSubGraph("cluster_" + nodeContainer);
                map.put(nodeContainer, it3);
                it3.setGraphLabel(String.valueOf(nodeContainer));
                it3.setGraphAttribute("style", "filled");
                it3.setGraphAttribute("color", "lightgrey");
                it3.setGraphAttribute("labeljust", "l");
                it3.setNodeShape("box");
                dotGraph = it3;
            }
            DotGraph sub = dotGraph;
            DotGraphNode fromNode = sub.drawNode(nodeName);
            fromNode.setHTMLLabel(getLabel(node));
            if (this.cfg.getHeads().contains(node)) {
                fromNode.setAttribute("color", "blue");
                DotGraphEdge startEdge = dot.drawEdge(it.getLabel(), nodeName);
                startEdge.setAttribute("color", "green");
            }
            for (Object succ : this.cfg.getSuccsOf(node)) {
                String succName = String.valueOf(succ);
                Object succNodeContainer = getNodeContainer(succ);
                DotGraph dotGraph2 = (DotGraph) map.get(succNodeContainer);
                if (dotGraph2 == null) {
                    DotGraph it4 = dot.createSubGraph("cluster_" + succNodeContainer);
                    map.put(succNodeContainer, it4);
                    it4.setGraphLabel(String.valueOf(succNodeContainer));
                    it4.setGraphAttribute("style", "filled");
                    it4.setGraphAttribute("color", "lightgrey");
                    it4.setGraphAttribute("labeljust", "l");
                    it4.setNodeShape("box");
                    dotGraph2 = it4;
                }
                DotGraph sub2 = dotGraph2;
                DotGraphNode nodeTo = sub2.drawNode(succName);
                nodeTo.setHTMLLabel(getLabel(succ));
                DotGraphEdge edge = dot.drawEdge(nodeName, succName);
                edge.setAttribute("color", "blue");
                if (Intrinsics.areEqual(nodeContainer, succNodeContainer)) {
                    edge.setAttribute("style", "dashed");
                }
            }
        }
        return dot;
    }

    @NotNull
    public String getLabel(N n) {
        return String.valueOf(n);
    }

    /* compiled from: GraphPlot.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/graph/GraphPlot$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-api"})
    /* loaded from: GraphPlot$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$5() {
        return Unit.INSTANCE;
    }
}
