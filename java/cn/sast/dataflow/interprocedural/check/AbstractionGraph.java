package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.data.Abstraction;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: AbstractionGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0010)\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u001c\u001a\u00020\u001dJ@\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00022\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\rH\u0002J<\u0010\"\u001a\u00020\u001d2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\r2\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\rH\u0014J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020$H\u0016J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020$H\u0016J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00020$2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020$2\u0006\u0010'\u001a\u00020\u0002H\u0016J\b\u0010)\u001a\u00020*H\u0016J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020,H\u0096\u0002J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0002J\u000e\u00100\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0002R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR,\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\rX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R,\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t0\rX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\u0018¨\u00061"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/AbstractionGraph;", "Lsoot/toolkits/graph/DirectedGraph;", "Lsoot/jimple/infoflow/data/Abstraction;", "sink", "<init>", "(Lsoot/jimple/infoflow/data/Abstraction;)V", "getSink", "()Lsoot/jimple/infoflow/data/Abstraction;", "absChain", "Ljava/util/ArrayList;", "getAbsChain", "()Ljava/util/ArrayList;", "unitToSuccs", "Ljava/util/IdentityHashMap;", "getUnitToSuccs", "()Ljava/util/IdentityHashMap;", "setUnitToSuccs", "(Ljava/util/IdentityHashMap;)V", "unitToPreds", "getUnitToPreds", "setUnitToPreds", "mHeads", "getMHeads", "setMHeads", "(Ljava/util/ArrayList;)V", "mTails", "getMTails", "setMTails", "buildHeadsAndTails", "", "addEdge", "currentAbs", "target", "successors", "buildUnexceptionalEdges", "getHeads", "", "getTails", "getPredsOf", "s", "getSuccsOf", "size", "", "iterator", "", "isTail", "", "abs", "isHead", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nAbstractionGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractionGraph.kt\ncn/sast/dataflow/interprocedural/check/AbstractionGraph\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,239:1\n360#2,7:240\n360#2,7:247\n360#2,7:254\n*S KotlinDebug\n*F\n+ 1 AbstractionGraph.kt\ncn/sast/dataflow/interprocedural/check/AbstractionGraph\n*L\n60#1:240,7\n119#1:247,7\n123#1:254,7\n*E\n"})
/* loaded from: AbstractionGraph.class */
public abstract class AbstractionGraph implements DirectedGraph<Abstraction> {

    @NotNull
    private final Abstraction sink;

    @NotNull
    private final ArrayList<Abstraction> absChain;
    public IdentityHashMap<Abstraction, ArrayList<Abstraction>> unitToSuccs;
    public IdentityHashMap<Abstraction, ArrayList<Abstraction>> unitToPreds;
    public ArrayList<Abstraction> mHeads;
    public ArrayList<Abstraction> mTails;

    public AbstractionGraph(@NotNull Abstraction sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        this.absChain = new ArrayList<>();
        LinkedList abstractionQueue = new LinkedList();
        abstractionQueue.add(this.sink);
        Set set = Collections.newSetFromMap(new IdentityHashMap());
        while (!abstractionQueue.isEmpty()) {
            Object objRemove = abstractionQueue.remove(0);
            Intrinsics.checkNotNullExpressionValue(objRemove, "removeAt(...)");
            Abstraction abstraction = (Abstraction) objRemove;
            this.absChain.add(abstraction);
            if (abstraction.getSourceContext() != null) {
                boolean z = abstraction.getPredecessor() == null;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            } else if (set.add(abstraction.getPredecessor())) {
                abstractionQueue.add(abstraction.getPredecessor());
            }
            if (abstraction.getNeighbors() != null) {
                for (Abstraction nb : abstraction.getNeighbors()) {
                    if (set.add(nb)) {
                        abstractionQueue.add(nb);
                    }
                }
            }
        }
    }

    @NotNull
    public final Abstraction getSink() {
        return this.sink;
    }

    @NotNull
    public final ArrayList<Abstraction> getAbsChain() {
        return this.absChain;
    }

    @NotNull
    public final IdentityHashMap<Abstraction, ArrayList<Abstraction>> getUnitToSuccs() {
        IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap = this.unitToSuccs;
        if (identityHashMap != null) {
            return identityHashMap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("unitToSuccs");
        return null;
    }

    public final void setUnitToSuccs(@NotNull IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap) {
        Intrinsics.checkNotNullParameter(identityHashMap, "<set-?>");
        this.unitToSuccs = identityHashMap;
    }

    @NotNull
    public final IdentityHashMap<Abstraction, ArrayList<Abstraction>> getUnitToPreds() {
        IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap = this.unitToPreds;
        if (identityHashMap != null) {
            return identityHashMap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("unitToPreds");
        return null;
    }

    public final void setUnitToPreds(@NotNull IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap) {
        Intrinsics.checkNotNullParameter(identityHashMap, "<set-?>");
        this.unitToPreds = identityHashMap;
    }

    @NotNull
    public final ArrayList<Abstraction> getMHeads() {
        ArrayList<Abstraction> arrayList = this.mHeads;
        if (arrayList != null) {
            return arrayList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mHeads");
        return null;
    }

    public final void setMHeads(@NotNull ArrayList<Abstraction> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mHeads = arrayList;
    }

    @NotNull
    public final ArrayList<Abstraction> getMTails() {
        ArrayList<Abstraction> arrayList = this.mTails;
        if (arrayList != null) {
            return arrayList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mTails");
        return null;
    }

    public final void setMTails(@NotNull ArrayList<Abstraction> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mTails = arrayList;
    }

    public final void buildHeadsAndTails() {
        setMTails(new ArrayList<>());
        setMHeads(new ArrayList<>());
        Iterator<Abstraction> it = this.absChain.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Abstraction next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            Abstraction s = next;
            ArrayList succs = getUnitToSuccs().get(s);
            ArrayList arrayList = succs;
            if (arrayList == null || arrayList.isEmpty()) {
                getMTails().add(s);
            }
            ArrayList preds = getUnitToPreds().get(s);
            ArrayList arrayList2 = preds;
            if (arrayList2 == null || arrayList2.isEmpty()) {
                getMHeads().add(s);
            }
        }
    }

    private final void addEdge(Abstraction currentAbs, Abstraction target, ArrayList<Abstraction> arrayList, IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap) {
        int i;
        ArrayList<Abstraction> $this$indexOfFirst$iv = arrayList;
        int index$iv = 0;
        Iterator<Abstraction> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Abstraction it2 = (Abstraction) item$iv;
                if (it2 == target) {
                    i = index$iv;
                    break;
                }
                index$iv++;
            } else {
                i = -1;
                break;
            }
        }
        if (i == -1) {
            arrayList.add(target);
            ArrayList preds = identityHashMap.get(target);
            if (preds == null) {
                preds = new ArrayList();
                identityHashMap.put(target, preds);
            }
            preds.add(currentAbs);
        }
    }

    protected void buildUnexceptionalEdges(@NotNull IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap, @NotNull IdentityHashMap<Abstraction, ArrayList<Abstraction>> identityHashMap2) {
        Intrinsics.checkNotNullParameter(identityHashMap, "unitToSuccs");
        Intrinsics.checkNotNullParameter(identityHashMap2, "unitToPreds");
        Iterator unitIt = this.absChain.iterator();
        Intrinsics.checkNotNullExpressionValue(unitIt, "iterator(...)");
        Abstraction nextAbs = unitIt.hasNext() ? unitIt.next() : null;
        while (nextAbs != null) {
            Abstraction currentAbs = nextAbs;
            nextAbs = unitIt.hasNext() ? unitIt.next() : null;
            ArrayList successors = new ArrayList();
            if (currentAbs.getPredecessor() != null) {
                Abstraction predecessor = currentAbs.getPredecessor();
                Intrinsics.checkNotNullExpressionValue(predecessor, "getPredecessor(...)");
                addEdge(currentAbs, predecessor, successors, identityHashMap2);
                Set<Abstraction> it = currentAbs.getPredecessor().getNeighbors();
                if (it != null) {
                    for (Abstraction targetBox : it) {
                        Intrinsics.checkNotNull(targetBox);
                        addEdge(currentAbs, targetBox, successors, identityHashMap2);
                    }
                }
            }
            if (!successors.isEmpty()) {
                successors.trimToSize();
                identityHashMap.put(currentAbs, successors);
            }
        }
    }

    @NotNull
    public List<Abstraction> getHeads() {
        return getMHeads();
    }

    @NotNull
    public List<Abstraction> getTails() {
        return getMTails();
    }

    @NotNull
    public List<Abstraction> getPredsOf(@NotNull Abstraction s) {
        Intrinsics.checkNotNullParameter(s, "s");
        ArrayList<Abstraction> arrayList = getUnitToPreds().get(s);
        return arrayList != null ? arrayList : CollectionsKt.emptyList();
    }

    @NotNull
    public List<Abstraction> getSuccsOf(@NotNull Abstraction s) {
        Intrinsics.checkNotNullParameter(s, "s");
        ArrayList<Abstraction> arrayList = getUnitToSuccs().get(s);
        return arrayList != null ? arrayList : CollectionsKt.emptyList();
    }

    public int size() {
        return this.absChain.size();
    }

    @NotNull
    public Iterator<Abstraction> iterator() {
        Iterator<Abstraction> it = this.absChain.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        return it;
    }

    public final boolean isTail(@NotNull Abstraction abs) {
        int i;
        Intrinsics.checkNotNullParameter(abs, "abs");
        List $this$indexOfFirst$iv = getTails();
        int index$iv = 0;
        Iterator<Abstraction> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Abstraction it2 = (Abstraction) item$iv;
                if (it2 == abs) {
                    i = index$iv;
                    break;
                }
                index$iv++;
            } else {
                i = -1;
                break;
            }
        }
        return i != -1;
    }

    public final boolean isHead(@NotNull Abstraction abs) {
        int i;
        Intrinsics.checkNotNullParameter(abs, "abs");
        List $this$indexOfFirst$iv = getHeads();
        int index$iv = 0;
        Iterator<Abstraction> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Abstraction it2 = (Abstraction) item$iv;
                if (it2 == abs) {
                    i = index$iv;
                    break;
                }
                index$iv++;
            } else {
                i = -1;
                break;
            }
        }
        return i != -1;
    }
}
