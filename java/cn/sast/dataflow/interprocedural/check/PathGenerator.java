package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.MutableDirectedGraph;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J4\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u0013J+\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0\u00132\u0006\u0010\u0015\u001a\u00028��2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u0006*\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\n*\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGenerator;", "P", "", "<init>", "()V", "shouldExplore", "", "getShouldExplore", "(Ljava/lang/Object;)Z", "preds", "", "getPreds", "(Ljava/lang/Object;)Ljava/util/Collection;", "flush", "", "", "g", "Lsoot/toolkits/graph/DirectedGraph;", "heads", "", "getHeads", "sink", "Lsoot/toolkits/graph/MutableDirectedGraph;", "(Ljava/lang/Object;Lsoot/toolkits/graph/MutableDirectedGraph;)Ljava/util/Set;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGenerator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,213:1\n1279#2,2:214\n1293#2,4:216\n*S KotlinDebug\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGenerator\n*L\n31#1:214,2\n31#1:216,4\n*E\n"})
/* loaded from: PathGenerator.class */
public abstract class PathGenerator<P> {
    public abstract boolean getShouldExplore(@NotNull P p);

    @NotNull
    public abstract Collection<P> getPreds(@NotNull P p);

    @NotNull
    public final Map<P, List<P>> flush(@NotNull DirectedGraph<P> directedGraph, @NotNull Set<? extends P> set) {
        Intrinsics.checkNotNullParameter(directedGraph, "g");
        Intrinsics.checkNotNullParameter(set, "heads");
        Set<? extends P> $this$associateWith$iv = set;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            Map map = result$iv;
            LinkedList pQueue = new LinkedList();
            pQueue.add(element$iv$iv);
            List event = new ArrayList();
            Set visit = new LinkedHashSet();
            while (!pQueue.isEmpty()) {
                Object from = pQueue.remove(0);
                Intrinsics.checkNotNullExpressionValue(from, "removeAt(...)");
                event.add(from);
                Iterator it = directedGraph.getSuccsOf(from).iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object to = it.next();
                        Intrinsics.checkNotNull(to);
                        event.add(to);
                        if (visit.add(to)) {
                            pQueue.add(to);
                            break;
                        }
                    }
                }
            }
            map.put(element$iv$iv, PathGeneratorKt.getRemoveAdjacentDuplicates(event));
        }
        Map r = result$iv;
        return r;
    }

    public static /* synthetic */ Set getHeads$default(PathGenerator pathGenerator, Object obj, MutableDirectedGraph mutableDirectedGraph, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHeads");
        }
        if ((i & 2) != 0) {
            mutableDirectedGraph = null;
        }
        return pathGenerator.getHeads(obj, mutableDirectedGraph);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Set<P> getHeads(@NotNull P p, @Nullable MutableDirectedGraph<P> mutableDirectedGraph) {
        Intrinsics.checkNotNullParameter(p, "sink");
        Set heads = new LinkedHashSet();
        LinkedList pQueue = new LinkedList();
        pQueue.add(p);
        Set visit = new LinkedHashSet();
        while (!pQueue.isEmpty()) {
            Object node = pQueue.remove(0);
            Intrinsics.checkNotNullExpressionValue(node, "removeAt(...)");
            if (getShouldExplore(node)) {
                if (mutableDirectedGraph != null && !mutableDirectedGraph.containsNode(node)) {
                    mutableDirectedGraph.addNode(node);
                }
                Collection preds = getPreds(node);
                if (preds.isEmpty()) {
                    heads.add(node);
                }
                for (Object pred : preds) {
                    if (getShouldExplore(pred)) {
                        if (mutableDirectedGraph != null) {
                            if (!mutableDirectedGraph.containsNode(pred)) {
                                mutableDirectedGraph.addNode(pred);
                            }
                            mutableDirectedGraph.addEdge(pred, node);
                        }
                        if (visit.add(pred)) {
                            pQueue.add(pred);
                        }
                    }
                }
            }
        }
        return heads;
    }
}
