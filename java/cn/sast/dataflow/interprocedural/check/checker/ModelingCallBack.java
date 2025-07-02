package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.StmtModelingEnv;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.PostCallStmtInfo;
import cn.sast.dataflow.interprocedural.check.PrevCallStmtInfo;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.InterproceduralCFG;
import cn.sast.idfa.check.ICallCB;
import cn.sast.idfa.check.IPostCB;
import cn.sast.idfa.check.IPrevCB;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.IStmt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016JX\u0010\u000e\u001a\u000e\u0012\b\u0012\u00060\u0010j\u0002`\u0011\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u00152\u0006\u0010\u0016\u001a\u00020\u00172\"\u0010\u0018\u001a\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001b0\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/ModelingCallBack;", "", "method", "Lsoot/SootMethod;", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "<init>", "(Lsoot/SootMethod;Lcom/feysh/corax/config/api/IStmt;)V", "getMethod", "()Lsoot/SootMethod;", "getStmt", "()Lcom/feysh/corax/config/api/IStmt;", "toString", "", "model", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "summaryCtxCalleeSite", "Lcn/sast/idfa/check/ICallCB;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "corax-data-flow"})
/* loaded from: ModelingCallBack.class */
public final class ModelingCallBack {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final IStmt stmt;

    public ModelingCallBack(@NotNull SootMethod method, @NotNull IStmt stmt) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        this.method = method;
        this.stmt = stmt;
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final IStmt getStmt() {
        return this.stmt;
    }

    @NotNull
    public String toString() {
        return this.method + ":  " + this.stmt;
    }

    @Nullable
    public final IFact<IValue> model(@NotNull InterproceduralCFG icfg, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HookEnv env, @NotNull ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB) {
        HookEnv stmtModelingEnv;
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iCallCB, "summaryCtxCalleeSite");
        if (iCallCB instanceof IPrevCB) {
            stmtModelingEnv = new StmtModelingEnv(env.getNode(), new PrevCallStmtInfo(this.stmt, this.method));
        } else if (iCallCB instanceof IPostCB) {
            stmtModelingEnv = new StmtModelingEnv(env.getNode(), new PostCallStmtInfo(this.stmt, env.getNode()));
        } else {
            stmtModelingEnv = env;
        }
        HeapValuesEnv envWithStmt = stmtModelingEnv;
        FactModeling m = new FactModeling(abstractHeapFactory, envWithStmt, iCallCB, iCallCB.getOut());
        IModelStmtVisitor stmtVisitor = m.getVisitor();
        this.stmt.accept(stmtVisitor);
        return null;
    }
}
