package cn.sast.dataflow.analysis.deadcode;

import cn.sast.dataflow.analysis.IBugReporter;
import cn.sast.dataflow.analysis.constant.ConstantValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.builtin.checkers.DeadCodeChecker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.Body;
import soot.SootClass;
import soot.Unit;
import soot.Value;
import soot.jimple.IfStmt;
import soot.toolkits.graph.DirectedBodyGraph;

/* compiled from: DeadCode.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0016\u0010\u000b\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/analysis/deadcode/DeadCode;", "", "reporter", "Lcn/sast/dataflow/analysis/IBugReporter;", "<init>", "(Lcn/sast/dataflow/analysis/IBugReporter;)V", "analyze", "", "graph", "Lsoot/toolkits/graph/DirectedBodyGraph;", "Lsoot/Unit;", "findDeadCode", "findUnreachableBranch", "body", "Lsoot/Body;", "constantValues", "Lcn/sast/dataflow/analysis/constant/ConstantValues;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nDeadCode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeadCode.kt\ncn/sast/dataflow/analysis/deadcode/DeadCode\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,40:1\n808#2,11:41\n1863#2,2:52\n*S KotlinDebug\n*F\n+ 1 DeadCode.kt\ncn/sast/dataflow/analysis/deadcode/DeadCode\n*L\n24#1:41,11\n24#1:52,2\n*E\n"})
/* loaded from: DeadCode.class */
public final class DeadCode {

    @NotNull
    private final IBugReporter reporter;

    public DeadCode(@NotNull IBugReporter reporter) {
        Intrinsics.checkNotNullParameter(reporter, "reporter");
        this.reporter = reporter;
    }

    public final void analyze(@NotNull DirectedBodyGraph<Unit> directedBodyGraph) {
        Intrinsics.checkNotNullParameter(directedBodyGraph, "graph");
        findDeadCode(directedBodyGraph);
        ConstantValues constantValues = new ConstantValues(directedBodyGraph);
        Body body = directedBodyGraph.getBody();
        Intrinsics.checkNotNullExpressionValue(body, "getBody(...)");
        findUnreachableBranch(body, constantValues);
    }

    private final void findDeadCode(DirectedBodyGraph<Unit> directedBodyGraph) {
    }

    private final void findUnreachableBranch(Body body, ConstantValues constantValues) {
        Unit succOf;
        Iterable units = body.getUnits();
        Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
        Iterable $this$filterIsInstance$iv = units;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof IfStmt) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            Unit unit = (IfStmt) element$iv;
            Value condition = unit.getCondition();
            Intrinsics.checkNotNullExpressionValue(condition, "getCondition(...)");
            Integer valueAt = constantValues.getValueAt(condition, unit);
            if (valueAt != null && valueAt.intValue() == 1) {
                succOf = body.getUnits().getSuccOf(unit);
            } else {
                succOf = (valueAt != null && valueAt.intValue() == 0) ? (Unit) unit.getTarget() : null;
            }
            Unit unreachableBranch = succOf;
            if (unreachableBranch != null) {
                String guard = Intrinsics.areEqual(unreachableBranch, unit.getTarget()) ? unit.getCondition().toString() : "!(" + unit.getCondition() + ")";
                IBugReporter iBugReporter = this.reporter;
                CheckType checkType = (CheckType) DeadCodeChecker.UnreachableBranch.INSTANCE;
                SootClass declaringClass = body.getMethod().getDeclaringClass();
                Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
                iBugReporter.report(checkType, declaringClass, unreachableBranch, (v2) -> {
                    return findUnreachableBranch$lambda$1$lambda$0(r4, r5, v2);
                });
            }
        }
    }

    private static final kotlin.Unit findUnreachableBranch$lambda$1$lambda$0(String $guard, Unit $unreachableBranch, BugMessage.Env $this$report) {
        Intrinsics.checkNotNullParameter($this$report, "$this$report");
        $this$report.getArgs().put("guard", $guard);
        $this$report.getArgs().put("target", $unreachableBranch);
        return kotlin.Unit.INSTANCE;
    }
}
