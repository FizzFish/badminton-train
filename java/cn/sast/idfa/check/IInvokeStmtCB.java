package cn.sast.idfa.check;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.jimple.InvokeExpr;

/* compiled from: CheckerManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/idfa/check/IInvokeStmtCB;", "V", "R", "Lcn/sast/idfa/check/ICallerSiteCB;", "invokeExpr", "Lsoot/jimple/InvokeExpr;", "getInvokeExpr", "()Lsoot/jimple/InvokeExpr;", "corax-idfa-framework"})
/* loaded from: IInvokeStmtCB.class */
public interface IInvokeStmtCB<V, R> extends ICallerSiteCB<V, R> {
    @NotNull
    InvokeExpr getInvokeExpr();

    /* compiled from: CheckerManager.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IInvokeStmtCB$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static <V, R> InvokeExpr getInvokeExpr(@NotNull IInvokeStmtCB<V, R> iInvokeStmtCB) {
            InvokeExpr invokeExpr = iInvokeStmtCB.getStmt().getInvokeExpr();
            Intrinsics.checkNotNullExpressionValue(invokeExpr, "getInvokeExpr(...)");
            return invokeExpr;
        }
    }
}
