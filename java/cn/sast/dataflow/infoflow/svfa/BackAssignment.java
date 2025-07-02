package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import soot.Unit;
import soot.Value;
import soot.jimple.Stmt;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.scalar.FlowAnalysis;
import soot.toolkits.scalar.ForwardFlowAnalysis;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018��2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BI\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012$\u0010\t\u001a \u0012\u0004\u0012\u00020\u000b\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u00070\n¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0015\u001a\u00020\u0003H\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0002H\u0014J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0003H\u0014J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0002H\u0014J \u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0014J \u0010%\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0014J \u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u0003H\u0014J\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R/\u0010\t\u001a \u0012\u0004\u0012\u00020\u000b\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u00070\n¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006,"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/BackAssignment;", "Lsoot/toolkits/scalar/ForwardFlowAnalysis;", "Lsoot/Unit;", "Lcn/sast/dataflow/infoflow/svfa/FlowFact;", "graph", "Lsoot/toolkits/graph/DirectedGraph;", "paramAndThis", "", "Lsoot/Value;", "unit2locals", "", "Lsoot/jimple/Stmt;", "Lkotlin/Pair;", "Lcn/sast/dataflow/infoflow/svfa/AP;", "Lcn/sast/dataflow/infoflow/svfa/ValueLocation;", "<init>", "(Lsoot/toolkits/graph/DirectedGraph;Ljava/util/Set;Ljava/util/Map;)V", "getParamAndThis", "()Ljava/util/Set;", "getUnit2locals", "()Ljava/util/Map;", "newInitialFlow", "omissible", "", "u", "copy", "", "source", "dest", "getFlow", "Lsoot/toolkits/scalar/FlowAnalysis$Flow;", "from", "to", "mergeInto", "succNode", "inout", "in1", "merge", "in2", "out", "flowThrough", "infact", "unit", "getAfter", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparsePropgrateAnalyze.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/BackAssignment\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 extensions.kt\nkotlinx/collections/immutable/ExtensionsKt\n*L\n1#1,420:1\n1863#2:421\n1864#2:423\n1279#2,2:424\n1293#2,4:426\n327#3:422\n362#3:430\n*S KotlinDebug\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/BackAssignment\n*L\n265#1:421\n265#1:423\n271#1:424,2\n271#1:426,4\n266#1:422\n271#1:430\n*E\n"})
/* loaded from: BackAssignment.class */
final class BackAssignment extends ForwardFlowAnalysis<Unit, FlowFact> {

    @NotNull
    private final Set<Value> paramAndThis;

    @NotNull
    private final Map<Stmt, Set<Pair<AP, ValueLocation>>> unit2locals;

    @NotNull
    public final Set<Value> getParamAndThis() {
        return this.paramAndThis;
    }

    @NotNull
    public final Map<Stmt, Set<Pair<AP, ValueLocation>>> getUnit2locals() {
        return this.unit2locals;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BackAssignment(@NotNull DirectedGraph<Unit> directedGraph, @NotNull Set<Value> set, @NotNull Map<Stmt, ? extends Set<Pair<AP, ValueLocation>>> map) {
        super(directedGraph);
        Intrinsics.checkNotNullParameter(directedGraph, "graph");
        Intrinsics.checkNotNullParameter(set, "paramAndThis");
        Intrinsics.checkNotNullParameter(map, "unit2locals");
        this.paramAndThis = set;
        this.unit2locals = map;
        doAnalysis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: newInitialFlow, reason: merged with bridge method [inline-methods] */
    public FlowFact m153newInitialFlow() {
        return new FlowFact();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean omissible(@NotNull Unit u) {
        Intrinsics.checkNotNullParameter(u, "u");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copy(@NotNull FlowFact source, @NotNull FlowFact dest) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(dest, "dest");
        if (dest != source) {
            dest.setData(source.getData());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public FlowAnalysis.Flow getFlow(@NotNull Unit from, @NotNull Unit to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        FlowAnalysisOp flowAnalysisOp = FlowAnalysisOp.INSTANCE;
        DirectedGraph<Unit> directedGraph = this.graph;
        Intrinsics.checkNotNullExpressionValue(directedGraph, "graph");
        return flowAnalysisOp.getFlow(directedGraph, from, to);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mergeInto(@NotNull Unit succNode, @NotNull FlowFact inout, @NotNull FlowFact in1) {
        Intrinsics.checkNotNullParameter(succNode, "succNode");
        Intrinsics.checkNotNullParameter(inout, "inout");
        Intrinsics.checkNotNullParameter(in1, "in1");
        FlowAnalysisOp.INSTANCE.mergeInto(succNode, inout, in1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void merge(@NotNull FlowFact in1, @NotNull FlowFact in2, @NotNull FlowFact out) {
        Intrinsics.checkNotNullParameter(in1, "in1");
        Intrinsics.checkNotNullParameter(in2, "in2");
        Intrinsics.checkNotNullParameter(out, "out");
        throw new UnsupportedOperationException("BackAssignment.merge should never be called");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void flowThrough(@NotNull FlowFact infact, @NotNull Unit unit, @NotNull FlowFact out) {
        Intrinsics.checkNotNullParameter(infact, "infact");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(out, "out");
        copy(infact, out);
        if (unit instanceof Stmt) {
            Iterable locals = (Set) this.unit2locals.get(unit);
            if (locals != null) {
                PersistentMap data = out.getData();
                Iterable $this$forEach$iv = locals;
                for (Object element$iv : $this$forEach$iv) {
                    Pair pair = (Pair) element$iv;
                    AP ap = (AP) pair.component1();
                    PersistentMap $this$plus$iv = data;
                    Pair pair$iv = TuplesKt.to(ap.getValue(), ExtensionsKt.persistentHashSetOf(new VFNode[]{new VFNode(ap.getValue(), unit)}));
                    data = $this$plus$iv.put(pair$iv.getFirst(), pair$iv.getSecond());
                }
                out.setData(data);
            }
            if (this.graph.getHeads().contains(unit)) {
                PersistentMap $this$plus$iv2 = out.getData();
                Iterable $this$associateWith$iv = this.paramAndThis;
                LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
                for (Object element$iv$iv : $this$associateWith$iv) {
                    Value it = (Value) element$iv$iv;
                    result$iv.put(element$iv$iv, ExtensionsKt.persistentHashSetOf(new VFNode[]{new VFNode(it, unit)}));
                }
                Map map$iv = ExtensionsKt.toPersistentMap(result$iv);
                out.setData(ExtensionsKt.putAll($this$plus$iv2, map$iv));
            }
        }
    }

    @NotNull
    public final Map<Unit, FlowFact> getAfter() {
        Map<Unit, FlowFact> map = this.unitToAfterFlow;
        Intrinsics.checkNotNullExpressionValue(map, "unitToAfterFlow");
        return map;
    }
}
