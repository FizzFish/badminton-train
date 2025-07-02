package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.IfStmt;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.problems.AbstractInfoflowProblem;
import soot.jimple.infoflow.solver.cfg.BackwardsInfoflowCFG;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;
import soot.jimple.infoflow.solver.executors.InterruptableExecutor;
import soot.jimple.infoflow.solver.fastSolver.FastSolverLinkedNode;
import soot.jimple.infoflow.solver.fastSolver.IFDSSolver;
import soot.jimple.infoflow.solver.fastSolver.InfoflowSolver;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.UnitGraph;

/* compiled from: SparseInfoflowSolver.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0016\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J:\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/SparseInfoFlowSolver;", "Lsoot/jimple/infoflow/solver/fastSolver/InfoflowSolver;", "problem", "Lsoot/jimple/infoflow/problems/AbstractInfoflowProblem;", "executor", "Lsoot/jimple/infoflow/solver/executors/InterruptableExecutor;", "<init>", "(Lsoot/jimple/infoflow/problems/AbstractInfoflowProblem;Lsoot/jimple/infoflow/solver/executors/InterruptableExecutor;)V", "sparseCache", "Lcn/sast/dataflow/infoflow/svfa/CacheFlowGuide;", "getSparseCache", "()Lcn/sast/dataflow/infoflow/svfa/CacheFlowGuide;", "sparseCache$delegate", "Lkotlin/Lazy;", "isForward", "", "()Z", "isBackward", "propagate", "", "sourceVal", "Lsoot/jimple/infoflow/data/Abstraction;", "target", "Lsoot/Unit;", "targetVal", "relatedCallSite", "isUnbalancedReturn", "scheduleTarget", "Lsoot/jimple/infoflow/solver/fastSolver/IFDSSolver$ScheduleTarget;", "toString", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparseInfoflowSolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/SparseInfoFlowSolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,232:1\n1557#2:233\n1628#2,2:234\n1755#2,3:236\n1630#2:239\n1557#2:240\n1628#2,3:241\n1557#2:244\n1628#2,3:245\n*S KotlinDebug\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/SparseInfoFlowSolver\n*L\n184#1:233\n184#1:234,2\n192#1:236,3\n184#1:239\n200#1:240\n200#1:241,3\n203#1:244\n203#1:245,3\n*E\n"})
/* loaded from: SparseInfoFlowSolver.class */
public class SparseInfoFlowSolver extends InfoflowSolver {

    @NotNull
    private final Lazy sparseCache$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SparseInfoFlowSolver(@NotNull AbstractInfoflowProblem problem, @Nullable InterruptableExecutor executor) {
        super(problem, executor);
        Intrinsics.checkNotNullParameter(problem, "problem");
        this.sparseCache$delegate = LazyKt.lazy(() -> {
            return sparseCache_delegate$lambda$0(r1);
        });
    }

    private final CacheFlowGuide getSparseCache() {
        return (CacheFlowGuide) this.sparseCache$delegate.getValue();
    }

    private static final CacheFlowGuide sparseCache_delegate$lambda$0(SparseInfoFlowSolver this$0) {
        boolean trackControlFlowDependencies = this$0.getTabulationProblem().getManager().getConfig().getImplicitFlowMode().trackControlFlowDependencies();
        return new CacheFlowGuide(trackControlFlowDependencies);
    }

    private final boolean isForward() {
        return !(this.icfg instanceof BackwardsInfoflowCFG);
    }

    private final boolean isBackward() {
        return this.icfg instanceof BackwardsInfoflowCFG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void propagate(@NotNull Abstraction sourceVal, @NotNull Unit target, @NotNull Abstraction targetVal, @Nullable Unit relatedCallSite, boolean isUnbalancedReturn, @NotNull IFDSSolver.ScheduleTarget scheduleTarget) {
        List<Unit> success;
        List list;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(sourceVal, "sourceVal");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(targetVal, "targetVal");
        Intrinsics.checkNotNullParameter(scheduleTarget, "scheduleTarget");
        if (targetVal.getAccessPath().getPlainValue() == null || Intrinsics.areEqual(targetVal.getAccessPath(), this.zeroValue.getAccessPath())) {
            super.propagate((FastSolverLinkedNode) sourceVal, target, (FastSolverLinkedNode) targetVal, relatedCallSite, isUnbalancedReturn, scheduleTarget);
            return;
        }
        SootMethod method = (SootMethod) this.icfg.getMethodOf(target);
        DirectedGraph orCreateUnitGraph = this.icfg.getOrCreateUnitGraph(method);
        Intrinsics.checkNotNull(orCreateUnitGraph, "null cannot be cast to non-null type soot.toolkits.graph.UnitGraph");
        UnitGraph unitGraph = (UnitGraph) orCreateUnitGraph;
        AP ap = AP.Companion.get(targetVal);
        if (this.icfg instanceof BackwardsInfoflowCFG) {
            success = getSparseCache().getSuccess(false, ap, target, unitGraph);
        } else {
            success = getSparseCache().getSuccess(true, ap, target, unitGraph);
        }
        Iterable uses = CollectionsKt.toSet(success);
        if (!targetVal.isAbstractionActive()) {
            if (isForward()) {
                Iterable $this$map$iv = uses;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    Unit use = (Unit) item$iv$iv;
                    Abstraction toVal = targetVal;
                    BiDiInterproceduralCFG biDiInterproceduralCFG = this.icfg;
                    Intrinsics.checkNotNullExpressionValue(biDiInterproceduralCFG, "icfg");
                    Set throughUnits = SparseInfoflowSolverKt.getGoThrough$default(biDiInterproceduralCFG, target, use, null, 4, null);
                    throughUnits.remove(use);
                    if (throughUnits.contains(targetVal.getActivationUnit())) {
                        toVal = targetVal.getActiveCopy();
                    }
                    AbstractInfoflowProblem tabulationProblem = getTabulationProblem();
                    Intrinsics.checkNotNullExpressionValue(tabulationProblem, "getTabulationProblem(...)");
                    Iterable callSites = (Set) SparseInfoflowSolverKt.getActivationUnitsToCallSites(tabulationProblem).get(targetVal.getActivationUnit());
                    if (callSites != null) {
                        Iterable $this$any$iv = callSites;
                        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                            Iterator it = $this$any$iv.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Object element$iv = it.next();
                                    Unit it2 = (Unit) element$iv;
                                    if (throughUnits.contains(it2)) {
                                        z2 = true;
                                        break;
                                    }
                                } else {
                                    z2 = false;
                                    break;
                                }
                            }
                        } else {
                            z2 = false;
                        }
                        z = z2;
                    } else {
                        z = false;
                    }
                    if (z) {
                        toVal = targetVal.getActiveCopy();
                    }
                    destination$iv$iv.add(TuplesKt.to(use, toVal));
                }
                list = (List) destination$iv$iv;
            } else {
                Iterable $this$map$iv2 = uses;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    Unit it3 = (Unit) item$iv$iv2;
                    destination$iv$iv2.add(TuplesKt.to(it3, targetVal));
                }
                list = (List) destination$iv$iv2;
            }
        } else {
            Iterable $this$map$iv3 = uses;
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
            for (Object item$iv$iv3 : $this$map$iv3) {
                Unit it4 = (Unit) item$iv$iv3;
                destination$iv$iv3.add(TuplesKt.to(it4, targetVal));
            }
            list = (List) destination$iv$iv3;
        }
        List<Pair> propagates = list;
        for (Pair pair : propagates) {
            Unit useUnit = (Unit) pair.component1();
            FastSolverLinkedNode fastSolverLinkedNode = (Abstraction) pair.component2();
            Unit turnUnit = fastSolverLinkedNode.getTurnUnit();
            if (turnUnit != null) {
                BiDiInterproceduralCFG biDiInterproceduralCFG2 = this.icfg;
                Intrinsics.checkNotNullExpressionValue(biDiInterproceduralCFG2, "icfg");
                if (SparseInfoflowSolverKt.getGoThrough(biDiInterproceduralCFG2, target, useUnit, SetsKt.setOf(turnUnit)).contains(useUnit) || (this.icfg.isCallStmt(useUnit) && Intrinsics.areEqual(sourceVal, targetVal))) {
                }
            }
            if (isBackward()) {
                IInfoflowCFG.UnitContainer dominator = getTabulationProblem().getManager().getICFG().getDominatorOf(useUnit);
                Intrinsics.checkNotNullExpressionValue(dominator, "getDominatorOf(...)");
                if (dominator.getUnit() != null && (dominator.getUnit() instanceof IfStmt)) {
                    super.propagate((FastSolverLinkedNode) sourceVal, useUnit, fastSolverLinkedNode.deriveNewAbstractionWithDominator(dominator.getUnit()), relatedCallSite, isUnbalancedReturn, scheduleTarget);
                }
            }
            super.propagate((FastSolverLinkedNode) sourceVal, useUnit, fastSolverLinkedNode, relatedCallSite, isUnbalancedReturn, scheduleTarget);
        }
    }

    @NotNull
    public String toString() {
        return isForward() ? "forward" : "backward";
    }
}
