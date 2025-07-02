package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.infoflow.collect.MyConcurrentHashMap;
import soot.jimple.infoflow.problems.AbstractInfoflowProblem;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import soot.util.queue.ChunkedQueue;
import soot.util.queue.QueueReader;

/* compiled from: SparseInfoflowSolver.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��4\n��\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u0002H\u00022\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b¢\u0006\u0002\u0010\t\u001a(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\u00042\u0006\u0010\u000e\u001a\u00020\u000b\"'\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\b0\u0010*\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"getGoThrough", "", "N", "M", "Lsoot/jimple/toolkits/ide/icfg/BiDiInterproceduralCFG;", "from", "to", "skipNodes", "", "(Lsoot/jimple/toolkits/ide/icfg/BiDiInterproceduralCFG;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Set;)Ljava/util/Set;", "getReachSet", "Lsoot/Unit;", "icfg", "Lsoot/SootMethod;", "target", "activationUnitsToCallSites", "Lsoot/jimple/infoflow/collect/MyConcurrentHashMap;", "Lsoot/jimple/infoflow/problems/AbstractInfoflowProblem;", "getActivationUnitsToCallSites", "(Lsoot/jimple/infoflow/problems/AbstractInfoflowProblem;)Lsoot/jimple/infoflow/collect/MyConcurrentHashMap;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparseInfoflowSolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/SparseInfoflowSolverKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,232:1\n381#2,7:233\n774#3:240\n865#3,2:241\n1557#3:243\n1628#3,3:244\n*S KotlinDebug\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/SparseInfoflowSolverKt\n*L\n77#1:233,7\n86#1:240\n86#1:241,2\n86#1:243\n86#1:244,3\n*E\n"})
/* loaded from: SparseInfoflowSolverKt.class */
public final class SparseInfoflowSolverKt {
    public static /* synthetic */ Set getGoThrough$default(BiDiInterproceduralCFG biDiInterproceduralCFG, Object obj, Object obj2, Set set, int i, Object obj3) {
        if ((i & 4) != 0) {
            set = SetsKt.emptySet();
        }
        return getGoThrough(biDiInterproceduralCFG, obj, obj2, set);
    }

    @NotNull
    public static final <M, N> Set<N> getGoThrough(@NotNull BiDiInterproceduralCFG<N, M> biDiInterproceduralCFG, N n, N n2, @NotNull Set<? extends N> set) {
        Object obj;
        Intrinsics.checkNotNullParameter(biDiInterproceduralCFG, "<this>");
        Intrinsics.checkNotNullParameter(set, "skipNodes");
        if (Intrinsics.areEqual(n, n2)) {
            return SetsKt.mutableSetOf(new Object[]{n});
        }
        Queue workList = new LinkedList();
        RefCntUnit startNode = new RefCntUnit(n, 1);
        workList.add(startNode);
        HashMap set2 = new HashMap();
        set2.put(startNode, startNode);
        while (!workList.isEmpty()) {
            RefCntUnit cur = (RefCntUnit) workList.poll();
            Object curNode = cur.getU();
            if (!set.contains(curNode)) {
                for (Object succ : biDiInterproceduralCFG.getSuccsOf(curNode)) {
                    RefCntUnit key = new RefCntUnit(succ, 1);
                    HashMap $this$getOrPut$iv = set2;
                    Object value$iv = $this$getOrPut$iv.get(key);
                    if (value$iv == null) {
                        $this$getOrPut$iv.put(key, key);
                        obj = key;
                    } else {
                        obj = value$iv;
                    }
                    RefCntUnit next = (RefCntUnit) obj;
                    if (next == key && !Intrinsics.areEqual(succ, n2)) {
                        workList.offer(next);
                    }
                    Intrinsics.checkNotNull(cur);
                    next.add(cur);
                }
            }
            cur.dec();
        }
        Iterable iterableValues = set2.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            RefCntUnit it = (RefCntUnit) element$iv$iv;
            if (it.getCnt() != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            RefCntUnit it2 = (RefCntUnit) item$iv$iv;
            destination$iv$iv2.add(it2.getU());
        }
        return CollectionsKt.toMutableSet((List) destination$iv$iv2);
    }

    @NotNull
    public static final Set<Unit> getReachSet(@NotNull BiDiInterproceduralCFG<Unit, SootMethod> biDiInterproceduralCFG, @NotNull Unit target) {
        Intrinsics.checkNotNullParameter(biDiInterproceduralCFG, "icfg");
        Intrinsics.checkNotNullParameter(target, "target");
        final ChunkedQueue reaches = new ChunkedQueue();
        HashSet reachSet = new HashSet();
        reachSet.add(target);
        QueueReader reader = reaches.reader();
        reachSet.forEach(new Consumer() { // from class: cn.sast.dataflow.infoflow.svfa.SparseInfoflowSolverKt.getReachSet.1
            @Override // java.util.function.Consumer
            public final void accept(Unit o) {
                Intrinsics.checkNotNullParameter(o, "o");
                reaches.add(o);
            }
        });
        while (reader.hasNext()) {
            for (Unit s : biDiInterproceduralCFG.getSuccsOf(reader.next())) {
                if (reachSet.add(s)) {
                    reaches.add(s);
                }
            }
        }
        return reachSet;
    }

    @NotNull
    public static final MyConcurrentHashMap<Unit, Set<Unit>> getActivationUnitsToCallSites(@NotNull AbstractInfoflowProblem $this$activationUnitsToCallSites) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter($this$activationUnitsToCallSites, "<this>");
        Field field = AbstractInfoflowProblem.class.getDeclaredField("activationUnitsToCallSites");
        field.setAccessible(true);
        Object obj = field.get($this$activationUnitsToCallSites);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type soot.jimple.infoflow.collect.MyConcurrentHashMap<soot.Unit, kotlin.collections.Set<soot.Unit>>");
        return (MyConcurrentHashMap) obj;
    }
}
