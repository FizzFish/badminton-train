package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.Unit;
import soot.jimple.IdentityStmt;
import soot.jimple.ParameterRef;
import soot.jimple.ThisRef;

/* compiled from: AJimpleInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\u001a\u0014\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getParameterUnit", "Lsoot/Unit;", "Lsoot/Body;", "i", "", "corax-data-flow"})
/* loaded from: AJimpleInterProceduralAnalysisKt.class */
public final class AJimpleInterProceduralAnalysisKt {
    @Nullable
    public static final Unit getParameterUnit(@NotNull Body $this$getParameterUnit, int i) {
        Intrinsics.checkNotNullParameter($this$getParameterUnit, "<this>");
        Iterator it = $this$getParameterUnit.getUnits().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            IdentityStmt identityStmt = (Unit) it.next();
            if (identityStmt instanceof IdentityStmt) {
                ParameterRef rightOp = identityStmt.getRightOp();
                if (rightOp instanceof ParameterRef) {
                    if (rightOp.getIndex() == i) {
                        return identityStmt;
                    }
                } else if ((rightOp instanceof ThisRef) && -1 == i) {
                    return identityStmt;
                }
            }
        }
        return null;
    }
}
