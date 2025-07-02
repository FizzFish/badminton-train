package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.report.Region;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.Stmt;

/* compiled from: ProgramStateContext.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018��2\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;", "Lcn/sast/api/report/DefaultEnv;", "region", "Lcom/feysh/corax/config/api/report/Region$Mutable;", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "callSiteStmt", "Lsoot/jimple/Stmt;", "container1", "Lsoot/SootMethod;", "callee1", "guard", "Lcom/feysh/corax/config/api/IExpr;", "<init>", "(Lcom/feysh/corax/config/api/report/Region$Mutable;Lcn/sast/api/report/IBugResInfo;Lsoot/jimple/Stmt;Lsoot/SootMethod;Lsoot/SootMethod;Lcom/feysh/corax/config/api/IExpr;)V", "getResInfo", "()Lcn/sast/api/report/IBugResInfo;", "getCallSiteStmt", "()Lsoot/jimple/Stmt;", "getContainer1", "()Lsoot/SootMethod;", "getCallee1", "getGuard", "()Lcom/feysh/corax/config/api/IExpr;", "callSite", "Lsoot/Unit;", "getCallSite", "()Lsoot/Unit;", "setCallSite", "(Lsoot/Unit;)V", "container", "getContainer", "setContainer", "(Lsoot/SootMethod;)V", "corax-data-flow"})
/* loaded from: ProgramStateContext.class */
public abstract class ProgramStateContext extends DefaultEnv {

    @NotNull
    private final IBugResInfo resInfo;

    @NotNull
    private final Stmt callSiteStmt;

    @NotNull
    private final SootMethod container1;

    @NotNull
    private final SootMethod callee1;

    @NotNull
    private final IExpr guard;

    @Nullable
    private Unit callSite;

    @Nullable
    private SootMethod container;

    @NotNull
    public final IBugResInfo getResInfo() {
        return this.resInfo;
    }

    @NotNull
    public final Stmt getCallSiteStmt() {
        return this.callSiteStmt;
    }

    @NotNull
    public final SootMethod getContainer1() {
        return this.container1;
    }

    @NotNull
    public final SootMethod getCallee1() {
        return this.callee1;
    }

    @NotNull
    public final IExpr getGuard() {
        return this.guard;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgramStateContext(@NotNull Region.Mutable region, @NotNull IBugResInfo resInfo, @NotNull Stmt callSiteStmt, @NotNull SootMethod container1, @NotNull SootMethod callee1, @NotNull IExpr guard) {
        super(region, null, null, null, null, null, null, null, null, 510, null);
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(resInfo, "resInfo");
        Intrinsics.checkNotNullParameter(callSiteStmt, "callSiteStmt");
        Intrinsics.checkNotNullParameter(container1, "container1");
        Intrinsics.checkNotNullParameter(callee1, "callee1");
        Intrinsics.checkNotNullParameter(guard, "guard");
        this.resInfo = resInfo;
        this.callSiteStmt = callSiteStmt;
        this.container1 = container1;
        this.callee1 = callee1;
        this.guard = guard;
        this.callSite = this.callSiteStmt;
        this.container = this.container1;
    }

    @Override // cn.sast.api.report.DefaultEnv
    @Nullable
    public Unit getCallSite() {
        return this.callSite;
    }

    @Override // cn.sast.api.report.DefaultEnv
    public void setCallSite(@Nullable Unit unit) {
        this.callSite = unit;
    }

    @Override // cn.sast.api.report.DefaultEnv
    @Nullable
    public SootMethod getContainer() {
        return this.container;
    }

    @Override // cn.sast.api.report.DefaultEnv
    public void setContainer(@Nullable SootMethod sootMethod) {
        this.container = sootMethod;
    }
}
