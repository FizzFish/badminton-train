package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: Context.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0016\u0018�� b*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u00042\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030��0\u0005:\u0001bB/\b\u0016\u0012\u0006\u0010\u0006\u001a\u00028��\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ#\u0010C\u001a\u00020\u001b2\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020��H\u0096\u0002J\u001b\u0010E\u001a\u00028\u00022\u0006\u0010F\u001a\u00028\u00012\u0006\u0010G\u001a\u00028\u0001¢\u0006\u0002\u0010HJ#\u0010I\u001a\u00020J2\u0006\u0010F\u001a\u00028\u00012\u0006\u0010G\u001a\u00028\u00012\u0006\u0010K\u001a\u00028\u0002¢\u0006\u0002\u0010LJ\u0015\u0010M\u001a\u0004\u0018\u00018\u00022\u0006\u0010F\u001a\u00028\u0001¢\u0006\u0002\u0010NJ\u0006\u0010O\u001a\u00020JJ\u0013\u0010P\u001a\u00020J2\u0006\u0010*\u001a\u00028\u0002¢\u0006\u0002\u0010BJ\u0013\u0010Q\u001a\u00020J2\u0006\u0010.\u001a\u00028\u0002¢\u0006\u0002\u0010BJ\u001b\u0010R\u001a\u00020J2\u0006\u0010F\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00028\u0002¢\u0006\u0002\u0010SJ\b\u0010T\u001a\u00020UH\u0016J\u0006\u0010V\u001a\u00020JJ\u0006\u0010W\u001a\u00020JJ\u0013\u0010X\u001a\u00020J2\u0006\u0010F\u001a\u00028\u0001¢\u0006\u0002\u0010BJ\u0013\u0010Y\u001a\u00020J2\u0006\u0010>\u001a\u00028\u0001¢\u0006\u0002\u0010BJ\r\u0010Z\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010,J\u0006\u0010[\u001a\u00020\nJ%\u0010\\\u001a\u0004\u0018\u00018\u00022\u0006\u0010F\u001a\u00028\u00012\u0006\u0010]\u001a\u00028��2\u0006\u0010*\u001a\u00028\u0002¢\u0006\u0002\u0010^J+\u0010_\u001a\u00020J2\u0006\u0010F\u001a\u00028\u00012\u0006\u0010]\u001a\u00028��2\u0006\u0010*\u001a\u00028\u00022\u0006\u0010`\u001a\u00028\u0002¢\u0006\u0002\u0010aR\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u000eR\u001a\u0010\u0013\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0010R\u001a\u0010\u0016\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R(\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR.\u0010 \u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\"\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R*\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\b@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b(\u0010)R$\u0010*\u001a\u0004\u0018\u00018\u00022\b\u0010\u0011\u001a\u0004\u0018\u00018\u0002@BX\u0086\u000e¢\u0006\n\n\u0002\u0010-\u001a\u0004\b+\u0010,R$\u0010.\u001a\u0004\u0018\u00018\u00022\b\u0010\u0011\u001a\u0004\u0018\u00018\u0002@BX\u0086\u000e¢\u0006\n\n\u0002\u0010-\u001a\u0004\b/\u0010,R\u001e\u00100\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b1\u00102R \u0010\u0006\u001a\u00028��2\u0006\u0010\u0011\u001a\u00028��@BX\u0086\u000e¢\u0006\n\n\u0002\u0010-\u001a\u0004\b3\u0010,R\u0016\u00104\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u000105X\u0082\u000e¢\u0006\u0002\n��R\u001c\u00106\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n��R(\u00107\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\"\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n��R.\u00108\u001a\"\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u000209\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n��RB\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\"0:2\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010\"0:@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b<\u0010=R\u0012\u0010>\u001a\u0004\u0018\u00018\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u0010-R\u001e\u0010?\u001a\u0004\u0018\u00018\u0002X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b@\u0010,\"\u0004\bA\u0010B¨\u0006c"}, d2 = {"Lcn/sast/idfa/analysis/Context;", "M", "N", "A", "Lsoot/Context;", "", "method", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "reverse", "", "isAnalyzable", "<init>", "(Ljava/lang/Object;Lsoot/toolkits/graph/DirectedGraph;ZZ)V", "()Z", "setAnalyzable", "(Z)V", "value", "isAnalysed", "skipAnalysis", "getSkipAnalysis", "setSkipAnalysis", "pathSensitiveEnable", "getPathSensitiveEnable", "setPathSensitiveEnable", "iteratorCount", "", "", "getIteratorCount", "()Ljava/util/Map;", "setIteratorCount", "(Ljava/util/Map;)V", "widenNode", "", "Lkotlin/Pair;", "getWidenNode", "()Ljava/util/Set;", "setWidenNode", "(Ljava/util/Set;)V", "controlFlowGraph", "getControlFlowGraph", "()Lsoot/toolkits/graph/DirectedGraph;", "entryValue", "getEntryValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "exitValue", "getExitValue", "id", "getId", "()I", "getMethod", "orderedNodes", "", "inValues", "edgeValues", "callCalleeEdgeValues", "Lkotlin/Triple;", "Ljava/util/NavigableSet;", "worklist", "getWorklist", "()Ljava/util/NavigableSet;", "callNode", "bottomValue", "getBottomValue", "setBottomValue", "(Ljava/lang/Object;)V", "compareTo", "other", "getEdgeValue", "node", "succ", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "setEdgeValue", "", "val", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getValueBefore", "(Ljava/lang/Object;)Ljava/lang/Object;", "markAnalysed", "setEntryValue", "setExitValue", "setValueBefore", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "", "initworklist", "clearWorkList", "addToWorklist", "setCallNode", "getCallNode", "hasCallNode", "getCallEdgeValue", "callee", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "setCallEdgeValue", "out", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "Companion", "corax-idfa-framework"})
/* loaded from: Context.class */
public class Context<M, N, A> implements soot.Context, Comparable<Context<M, N, A>> {
    private boolean isAnalyzable;
    private boolean isAnalysed;
    private boolean skipAnalysis;
    private boolean pathSensitiveEnable;

    @Nullable
    private Map<N, Integer> iteratorCount;

    @Nullable
    private Set<Pair<N, N>> widenNode;

    @NotNull
    private DirectedGraph<N> controlFlowGraph;

    @Nullable
    private A entryValue;

    @Nullable
    private A exitValue;
    private int id;
    private M method;

    @Nullable
    private List<? extends N> orderedNodes;

    @Nullable
    private Map<N, A> inValues;

    @Nullable
    private Map<Pair<N, N>, A> edgeValues;

    @Nullable
    private Map<Triple<N, M, A>, A> callCalleeEdgeValues;

    @NotNull
    private NavigableSet<Pair<N, N>> worklist;

    @Nullable
    private N callNode;

    @Nullable
    private A bottomValue;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final AtomicInteger count = new AtomicInteger(0);

    public final boolean isAnalyzable() {
        return this.isAnalyzable;
    }

    public final void setAnalyzable(boolean z) {
        this.isAnalyzable = z;
    }

    public final boolean isAnalysed() {
        return this.isAnalysed;
    }

    public final boolean getSkipAnalysis() {
        return this.skipAnalysis;
    }

    public final void setSkipAnalysis(boolean z) {
        this.skipAnalysis = z;
    }

    public final boolean getPathSensitiveEnable() {
        return this.pathSensitiveEnable;
    }

    public final void setPathSensitiveEnable(boolean z) {
        this.pathSensitiveEnable = z;
    }

    @Nullable
    public final Map<N, Integer> getIteratorCount() {
        return this.iteratorCount;
    }

    public final void setIteratorCount(@Nullable Map<N, Integer> map) {
        this.iteratorCount = map;
    }

    @Nullable
    public final Set<Pair<N, N>> getWidenNode() {
        return this.widenNode;
    }

    public final void setWidenNode(@Nullable Set<Pair<N, N>> set) {
        this.widenNode = set;
    }

    @NotNull
    public final DirectedGraph<N> getControlFlowGraph() {
        return this.controlFlowGraph;
    }

    @Nullable
    public final A getEntryValue() {
        return this.entryValue;
    }

    @Nullable
    public final A getExitValue() {
        return this.exitValue;
    }

    public final int getId() {
        return this.id;
    }

    public final M getMethod() {
        return this.method;
    }

    @NotNull
    public final NavigableSet<Pair<N, N>> getWorklist() {
        return this.worklist;
    }

    @Nullable
    public final A getBottomValue() {
        return this.bottomValue;
    }

    public final void setBottomValue(@Nullable A a) {
        this.bottomValue = a;
    }

    public Context(M m, @NotNull DirectedGraph<N> directedGraph, boolean reverse, boolean isAnalyzable) {
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        this.pathSensitiveEnable = true;
        this.iteratorCount = new HashMap();
        this.widenNode = new HashSet();
        this.isAnalyzable = isAnalyzable;
        this.id = count.getAndIncrement();
        this.method = m;
        this.isAnalysed = false;
        this.controlFlowGraph = directedGraph;
        this.inValues = new LinkedHashMap(directedGraph.size());
        this.edgeValues = new LinkedHashMap(directedGraph.size() * 2);
        List it = new soot.toolkits.graph.PseudoTopologicalOrderer().newList(this.controlFlowGraph, reverse);
        Map numbers = new HashMap();
        int num = 1;
        for (Object n : it) {
            List succs = directedGraph.getSuccsOf(n);
            if (succs.isEmpty()) {
                numbers.put(new Pair(n, n), Integer.valueOf(num));
                num++;
            } else {
                for (Object succ : directedGraph.getSuccsOf(n)) {
                    numbers.put(new Pair(n, succ), Integer.valueOf(num));
                    num++;
                }
            }
        }
        final Function2 function2 = (v1, v2) -> {
            return _init_$lambda$1$lambda$0(r3, v1, v2);
        };
        this.worklist = new TreeSet(new Comparator(function2) { // from class: cn.sast.idfa.analysis.Context$sam$java_util_Comparator$0
            private final /* synthetic */ Function2 function;

            {
                Intrinsics.checkNotNullParameter(function2, "function");
                this.function = function2;
            }

            @Override // java.util.Comparator
            public final /* synthetic */ int compare(Object p0, Object p1) {
                return ((Number) this.function.invoke(p0, p1)).intValue();
            }
        });
        this.orderedNodes = it;
        this.callCalleeEdgeValues = new LinkedHashMap(directedGraph.size() / 2);
    }

    private static final int _init_$lambda$1$lambda$0(Map $numbers, Pair u, Pair v) {
        Object obj = $numbers.get(u);
        Intrinsics.checkNotNull(obj);
        int iIntValue = ((Number) obj).intValue();
        Object obj2 = $numbers.get(v);
        Intrinsics.checkNotNull(obj2);
        return iIntValue - ((Number) obj2).intValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Context<M, N, A> context) {
        Intrinsics.checkNotNullParameter(context, "other");
        return context.id - this.id;
    }

    public final A getEdgeValue(N n, N n2) {
        Map<Pair<N, N>, A> map = this.edgeValues;
        Intrinsics.checkNotNull(map);
        A a = map.get(new Pair(n, n2));
        if (a != null) {
            return a;
        }
        A a2 = this.bottomValue;
        Intrinsics.checkNotNull(a2);
        return a2;
    }

    public final void setEdgeValue(N n, N n2, A a) {
        Map<Pair<N, N>, A> map = this.edgeValues;
        Intrinsics.checkNotNull(map);
        map.put(new Pair<>(n, n2), a);
    }

    @Nullable
    public final A getValueBefore(N n) {
        Map<N, A> map = this.inValues;
        Intrinsics.checkNotNull(map);
        return map.get(n);
    }

    public final void markAnalysed() {
        this.isAnalysed = true;
        this.callCalleeEdgeValues = null;
        this.edgeValues = null;
        this.inValues = null;
        this.iteratorCount = null;
        this.widenNode = null;
    }

    public final void setEntryValue(A a) {
        this.entryValue = a;
    }

    public final void setExitValue(A a) {
        this.exitValue = a;
    }

    public final void setValueBefore(N n, A a) {
        Map<N, A> map = this.inValues;
        Intrinsics.checkNotNull(map);
        map.put(n, a);
    }

    @NotNull
    public String toString() {
        return (this.isAnalyzable ? "" : "NN") + " " + this.id + " : + " + this.method;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initworklist() {
        this.isAnalysed = false;
        List<? extends N> list = this.orderedNodes;
        Intrinsics.checkNotNull(list);
        for (N n : list) {
            List succs = this.controlFlowGraph.getSuccsOf(n);
            if (succs.isEmpty()) {
                this.worklist.add(new Pair(n, n));
                A a = this.bottomValue;
                Intrinsics.checkNotNull(a);
                setEdgeValue(n, n, a);
            } else {
                for (Object succ : succs) {
                    this.worklist.add(new Pair(n, succ));
                    A a2 = this.bottomValue;
                    Intrinsics.checkNotNull(a2);
                    setEdgeValue(n, succ, a2);
                }
            }
        }
    }

    public final void clearWorkList() {
        this.worklist.clear();
    }

    public final void addToWorklist(N n) {
        List succs = this.controlFlowGraph.getSuccsOf(n);
        if (succs.isEmpty()) {
            this.worklist.add(new Pair(n, n));
            return;
        }
        for (Object succ : succs) {
            this.worklist.add(new Pair(n, succ));
        }
    }

    public final void setCallNode(N n) {
        this.callNode = n;
    }

    @Nullable
    public final N getCallNode() {
        return this.callNode;
    }

    public final boolean hasCallNode() {
        return this.callNode != null;
    }

    @Nullable
    public final A getCallEdgeValue(N n, M m, A a) {
        Map<Triple<N, M, A>, A> map = this.callCalleeEdgeValues;
        Intrinsics.checkNotNull(map);
        return map.get(new Triple(n, m, a));
    }

    public final void setCallEdgeValue(N n, M m, A a, A a2) {
        Map<Triple<N, M, A>, A> map = this.callCalleeEdgeValues;
        Intrinsics.checkNotNull(map);
        map.put(new Triple<>(n, m, a), a2);
    }

    /* compiled from: Context.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcn/sast/idfa/analysis/Context$Companion;", "", "<init>", "()V", "count", "Ljava/util/concurrent/atomic/AtomicInteger;", "getCount", "()Ljava/util/concurrent/atomic/AtomicInteger;", "reset", "", "corax-idfa-framework"})
    /* loaded from: Context$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final AtomicInteger getCount() {
            return Context.count;
        }

        public final void reset() {
            getCount().set(0);
        }
    }
}
