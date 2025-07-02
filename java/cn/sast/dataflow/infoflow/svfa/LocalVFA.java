package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import soot.NullType;
import soot.Timers;
import soot.Unit;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.AssignStmt;
import soot.jimple.BinopExpr;
import soot.jimple.Expr;
import soot.jimple.IdentityStmt;
import soot.jimple.IfStmt;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.ParameterRef;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;
import soot.jimple.ThisRef;
import soot.jimple.infoflow.util.BaseSelector;
import soot.jimple.internal.JimpleLocal;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� %2\u00020\u0001:\u0001%B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u000b\u001a\u00020\f\"\u0004\b��\u0010\r*\u00020\u000eH\u0002JN\u0010\u000f\u001a\u00020\f\"\u0004\b��\u0010\r2\u0006\u0010\u0010\u001a\u00020\u001126\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\r0\u0013H\u0002J:\u0010\u001a\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n��¨\u0006&"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/LocalVFA;", "Lcn/sast/dataflow/infoflow/svfa/ILocalDFA;", "graph", "Lsoot/toolkits/graph/DirectedGraph;", "Lsoot/Unit;", "trackControlFlowDependencies", "", "<init>", "(Lsoot/toolkits/graph/DirectedGraph;Z)V", "getTrackControlFlowDependencies", "()Z", "traverse", "", "R", "Lsoot/jimple/Expr;", "collectStmtInfo", "stmt", "Lsoot/jimple/Stmt;", "addValueToInfoMap", "Lkotlin/Function2;", "Lsoot/Value;", "Lkotlin/ParameterName;", "name", "v", "Lcn/sast/dataflow/infoflow/svfa/ValueLocation;", "loc", "init", "Lkotlin/Pair;", "", "Lcn/sast/dataflow/infoflow/svfa/FlowFact;", "getDefUsesOfAt", "", "ap", "Lcn/sast/dataflow/infoflow/svfa/AP;", "getUsesOfAt", "uses", "defuses", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparsePropgrateAnalyze.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/LocalVFA\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,420:1\n13409#2,2:421\n808#3,11:423\n1279#3,2:434\n1293#3,4:436\n1557#3:440\n1628#3,3:441\n1557#3:444\n1628#3,3:445\n*S KotlinDebug\n*F\n+ 1 SparsePropgrateAnalyze.kt\ncn/sast/dataflow/infoflow/svfa/LocalVFA\n*L\n313#1:421,2\n372#1:423,11\n372#1:434,2\n372#1:436,4\n392#1:440\n392#1:441,3\n398#1:444\n398#1:445,3\n*E\n"})
/* loaded from: LocalVFA.class */
public final class LocalVFA implements ILocalDFA {
    private final boolean trackControlFlowDependencies;

    @NotNull
    private final Map<Unit, FlowFact> uses;

    @NotNull
    private final Map<Unit, FlowFact> defuses;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Value returnVoidFake = new JimpleLocal("returnVoidFake", NullType.v());

    @NotNull
    private static final Value entryFake = new JimpleLocal("entryFake", NullType.v());

    public LocalVFA(@NotNull DirectedGraph<Unit> directedGraph, boolean trackControlFlowDependencies) {
        Intrinsics.checkNotNullParameter(directedGraph, "graph");
        this.trackControlFlowDependencies = trackControlFlowDependencies;
        boolean time = Options.v().time();
        if (time) {
            Timers.v().defsTimer.start();
        }
        Pair<Map<Unit, FlowFact>, Map<Unit, FlowFact>> pairInit = init(directedGraph);
        Map uses = (Map) pairInit.component1();
        Map defuses = (Map) pairInit.component2();
        this.uses = uses;
        this.defuses = defuses;
        if (!time) {
            return;
        }
        Timers.v().defsTimer.end();
    }

    public final boolean getTrackControlFlowDependencies() {
        return this.trackControlFlowDependencies;
    }

    /* compiled from: SparsePropgrateAnalyze.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/LocalVFA$Companion;", "", "<init>", "()V", "returnVoidFake", "Lsoot/Value;", "getReturnVoidFake", "()Lsoot/Value;", "entryFake", "getEntryFake", "corax-data-flow"})
    /* loaded from: LocalVFA$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Value getReturnVoidFake() {
            return LocalVFA.returnVoidFake;
        }

        @NotNull
        public final Value getEntryFake() {
            return LocalVFA.entryFake;
        }
    }

    private final <R> void traverse(Expr $this$traverse) {
    }

    private final <R> void collectStmtInfo(Stmt stmt, Function2<? super Value, ? super ValueLocation, ? extends R> function2) {
        if (stmt instanceof AssignStmt) {
            ArrayRef leftOp = ((AssignStmt) stmt).getLeftOp();
            Value right = ((AssignStmt) stmt).getRightOp();
            Object[] rightValues = BaseSelector.selectBaseList(right, true);
            if (leftOp instanceof ArrayRef) {
                Value base = leftOp.getBase();
                Intrinsics.checkNotNullExpressionValue(base, "getBase(...)");
                function2.invoke(base, ValueLocation.Right);
            } else {
                Intrinsics.checkNotNull(leftOp);
                function2.invoke(leftOp, ValueLocation.Left);
            }
            Intrinsics.checkNotNull(rightValues);
            for (Object element$iv : rightValues) {
                Intrinsics.checkNotNull(element$iv);
                function2.invoke(element$iv, ValueLocation.Right);
            }
        } else if (stmt instanceof IdentityStmt) {
            Value rightOp = ((IdentityStmt) stmt).getRightOp();
            if (rightOp instanceof ParameterRef) {
                Value leftOp2 = ((IdentityStmt) stmt).getLeftOp();
                Intrinsics.checkNotNullExpressionValue(leftOp2, "getLeftOp(...)");
                function2.invoke(leftOp2, ValueLocation.ParamAndThis);
            } else if (rightOp instanceof ThisRef) {
                Value leftOp3 = ((IdentityStmt) stmt).getLeftOp();
                Intrinsics.checkNotNullExpressionValue(leftOp3, "getLeftOp(...)");
                function2.invoke(leftOp3, ValueLocation.ParamAndThis);
            }
        } else if (!(stmt instanceof InvokeStmt)) {
            if (stmt instanceof IfStmt) {
                if (this.trackControlFlowDependencies) {
                    BinopExpr condition = ((IfStmt) stmt).getCondition();
                    Intrinsics.checkNotNull(condition, "null cannot be cast to non-null type soot.jimple.BinopExpr");
                    BinopExpr cond = condition;
                    Value op1 = cond.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    function2.invoke(op1, ValueLocation.Right);
                    Value op2 = cond.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    function2.invoke(op2, ValueLocation.Right);
                    return;
                }
                return;
            }
            if (stmt instanceof ReturnStmt) {
                Value op = ((ReturnStmt) stmt).getOp();
                Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                function2.invoke(op, ValueLocation.Right);
                return;
            } else if (stmt instanceof ReturnVoidStmt) {
                function2.invoke(returnVoidFake, ValueLocation.Right);
                return;
            }
        }
        InvokeExpr ie = stmt.containsInvokeExpr() ? stmt.getInvokeExpr() : null;
        if (ie != null) {
            if (ie instanceof InstanceInvokeExpr) {
                Value base2 = ((InstanceInvokeExpr) ie).getBase();
                Intrinsics.checkNotNullExpressionValue(base2, "getBase(...)");
                function2.invoke(base2, ValueLocation.Arg);
            }
            int argCount = ie.getArgCount();
            for (int i = 0; i < argCount; i++) {
                Value arg = ie.getArg(i);
                Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                function2.invoke(arg, ValueLocation.Arg);
            }
        }
    }

    private final Pair<Map<Unit, FlowFact>, Map<Unit, FlowFact>> init(DirectedGraph<Unit> directedGraph) {
        Set paramAndThis = new LinkedHashSet();
        Iterable $this$filterIsInstance$iv = (Iterable) directedGraph;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof Stmt) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$associateWith$iv = (List) destination$iv$iv;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv2 : $this$associateWith$iv) {
            Map map = result$iv;
            Stmt stmt = (Stmt) element$iv$iv2;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            collectStmtInfo(stmt, (v2, v3) -> {
                return init$lambda$2$lambda$1(r2, r3, v2, v3);
            });
            map.put(element$iv$iv2, linkedHashSet);
        }
        Map unit2locals = result$iv;
        return TuplesKt.to(new FlowAssignment(directedGraph, paramAndThis, unit2locals).getBefore(), new BackAssignment(directedGraph, paramAndThis, unit2locals).getAfter());
    }

    private static final kotlin.Unit init$lambda$2$lambda$1(Set $paramAndThis, Set $apAndLoc, Value value, ValueLocation valueLocation) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(valueLocation, "valueLocation");
        AP ap = AP.Companion.get(value);
        if (ap != null) {
            if (valueLocation == ValueLocation.ParamAndThis) {
                $paramAndThis.add(ap.getValue());
            }
            $apAndLoc.add(TuplesKt.to(ap, valueLocation));
        }
        return kotlin.Unit.INSTANCE;
    }

    @Override // cn.sast.dataflow.infoflow.svfa.ILocalDFA
    @NotNull
    public List<Unit> getDefUsesOfAt(@NotNull AP ap, @NotNull Unit stmt) {
        Intrinsics.checkNotNullParameter(ap, "ap");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        FlowFact fact = this.defuses.get(stmt);
        if (fact == null) {
            return CollectionsKt.emptyList();
        }
        PersistentSet persistentSetPersistentHashSetOf = (PersistentSet) fact.getData().get(ap.getValue());
        if (persistentSetPersistentHashSetOf == null) {
            persistentSetPersistentHashSetOf = ExtensionsKt.persistentHashSetOf();
        }
        PersistentSet use = persistentSetPersistentHashSetOf;
        Iterable $this$map$iv = (Iterable) use;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            VFNode it = (VFNode) item$iv$iv;
            destination$iv$iv.add(it.getStmt());
        }
        return (List) destination$iv$iv;
    }

    @Override // cn.sast.dataflow.infoflow.svfa.ILocalDFA
    @NotNull
    public List<Unit> getUsesOfAt(@NotNull AP ap, @NotNull Unit stmt) {
        Intrinsics.checkNotNullParameter(ap, "ap");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        FlowFact fact = this.uses.get(stmt);
        if (fact == null) {
            return CollectionsKt.emptyList();
        }
        PersistentSet persistentSetPersistentHashSetOf = (PersistentSet) fact.getData().get(ap.getValue());
        if (persistentSetPersistentHashSetOf == null) {
            persistentSetPersistentHashSetOf = ExtensionsKt.persistentHashSetOf();
        }
        PersistentSet use = persistentSetPersistentHashSetOf;
        Iterable $this$map$iv = (Iterable) use;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            VFNode it = (VFNode) item$iv$iv;
            destination$iv$iv.add(it.getStmt());
        }
        return (List) destination$iv$iv;
    }
}
