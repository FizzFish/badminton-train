package cn.sast.graph;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: NoBackEdgeDirectGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010#\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0\u00072\u0006\u0010\u000b\u001a\u00028��¢\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00028��2\u0006\u0010\u000f\u001a\u00028��¢\u0006\u0002\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00028��2\u0006\u0010\u000f\u001a\u00028��¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00028��¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00028��2\u0006\u0010\u000f\u001a\u00028��¢\u0006\u0002\u0010\u0010J\u001b\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00028��2\u0006\u0010\u000f\u001a\u00028��¢\u0006\u0002\u0010\u0013J\u0006\u0010\u001b\u001a\u00020\u0012R,\u0010\u0005\u001a \u0012\u0004\u0012\u00028��\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00070\u00060\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tX\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0019\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcn/sast/graph/NoBackEdgeDirectGraph;", "N", "", "<init>", "()V", "predView", "", "", "directedGraph", "Lcn/sast/graph/HashMutableDirectedGraph;", "getPredsTaskOf", "from", "(Ljava/lang/Object;)Ljava/util/Set;", "addEdge", "", "to", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeEdge", "", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getPredSize", "", "(Ljava/lang/Object;)I", "addEdgeSynchronized", "removeEdgeSynchronized", "isComplete", "()Z", "cleanUp", "heads", "", "getHeads", "()Ljava/util/List;", "corax-api"})
@SourceDebugExtension({"SMAP\nNoBackEdgeDirectGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NoBackEdgeDirectGraph.kt\ncn/sast/graph/NoBackEdgeDirectGraph\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"})
/* loaded from: NoBackEdgeDirectGraph.class */
public final class NoBackEdgeDirectGraph<N> {

    @NotNull
    private Map<N, Map<N, Set<N>>> predView = new HashMap();

    @NotNull
    private HashMutableDirectedGraph<N> directedGraph = new HashMutableDirectedGraph<>();

    @NotNull
    public final Set<N> getPredsTaskOf(N n) {
        Set predOfFrom;
        Set predTaskOfFrom = new LinkedHashSet();
        for (Object pred : this.directedGraph.getPredsOfAsSet(n)) {
            Map<N, Set<N>> map = this.predView.get(pred);
            if (map != null && (predOfFrom = map.get(n)) != null) {
                predTaskOfFrom.addAll(predOfFrom);
            }
        }
        return predTaskOfFrom;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean addEdge(N n, N n2) {
        if (Intrinsics.areEqual(n, n2)) {
            return false;
        }
        Set<N> predsTaskOf = getPredsTaskOf(n);
        if (predsTaskOf.contains(n2)) {
            return false;
        }
        predsTaskOf.add(n);
        this.directedGraph.addEdge(n, n2);
        LinkedList linkedList = new LinkedList();
        linkedList.add(n);
        HashSet hashSet = new HashSet();
        hashSet.add(n);
        while (!linkedList.isEmpty()) {
            Object objPoll = linkedList.poll();
            for (Object obj : this.directedGraph.getSuccsOfAsSet(objPoll)) {
                Map<N, Set<N>> map = this.predView.get(objPoll);
                if (map == null) {
                    map = new HashMap();
                    this.predView.put(objPoll, map);
                }
                Set<N> hashSet2 = map.get(obj);
                if (hashSet2 == null) {
                    hashSet2 = new HashSet();
                    map.put(obj, hashSet2);
                }
                if (!hashSet2.containsAll(predsTaskOf)) {
                    if (hashSet.add(obj)) {
                        linkedList.add(obj);
                    }
                    hashSet2.addAll(predsTaskOf);
                }
            }
        }
        return true;
    }

    public final void removeEdge(N n, N n2) {
        Map map;
        this.directedGraph.removeEdge(n, n2);
        Map it = this.predView.get(n);
        if (it != null) {
            it.remove(n2);
            map = it;
        } else {
            map = null;
        }
        Map predOfFrom = map;
        boolean z = predOfFrom != null && predOfFrom.isEmpty();
        if (z) {
            this.predView.remove(n);
        }
    }

    public final int getPredSize(N n) {
        int size;
        synchronized (this) {
            Set predTaskOfFrom = getPredsTaskOf(n);
            size = predTaskOfFrom.size();
        }
        return size;
    }

    public final boolean addEdgeSynchronized(N n, N n2) {
        boolean zAddEdge;
        synchronized (this) {
            zAddEdge = addEdge(n, n2);
        }
        return zAddEdge;
    }

    public final void removeEdgeSynchronized(N n, N n2) {
        synchronized (this) {
            removeEdge(n, n2);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean isComplete() {
        return this.predView.isEmpty() && this.directedGraph.size() == 0 && this.directedGraph.getHeads().isEmpty() && this.directedGraph.getTails().isEmpty();
    }

    public final void cleanUp() {
        this.predView.clear();
        this.directedGraph.clearAll();
    }

    @NotNull
    public final List<N> getHeads() {
        List<N> heads;
        synchronized (this) {
            heads = this.directedGraph.getHeads();
        }
        return heads;
    }
}
