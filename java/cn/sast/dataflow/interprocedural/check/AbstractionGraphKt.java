package cn.sast.dataflow.interprocedural.check;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import soot.jimple.Stmt;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;

/* compiled from: AbstractionGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0012\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getLabel", "", "Lsoot/jimple/infoflow/data/Abstraction;", "cfg", "Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "corax-data-flow"})
/* loaded from: AbstractionGraphKt.class */
public final class AbstractionGraphKt {
    @NotNull
    public static final String getLabel(@NotNull Abstraction $this$getLabel, @NotNull IInfoflowCFG cfg) {
        Intrinsics.checkNotNullParameter($this$getLabel, "<this>");
        Intrinsics.checkNotNullParameter(cfg, "cfg");
        StringBuffer sb = new StringBuffer("\"");
        Stmt it = $this$getLabel.getCorrespondingCallSite();
        if (it == null || sb.append(StringEscapeUtils.escapeHtml4("callSite: " + it.getJavaSourceStartLineNumber() + " " + it)) == null) {
            sb.append("correspondingCallSite null");
        }
        sb.append("\n");
        Stmt it2 = $this$getLabel.getCurrentStmt();
        if (it2 == null || sb.append(StringEscapeUtils.escapeHtml4($this$getLabel.getAccessPath() + "  " + it2.getJavaSourceStartLineNumber() + " " + it2)) == null) {
            sb.append(StringEscapeUtils.escapeHtml4($this$getLabel.getAccessPath() + "  currentStmt: null"));
        }
        return sb + "\"";
    }
}
