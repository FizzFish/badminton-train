package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IStmt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;
import soot.Value;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PostCallStmtInfo;", "Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "node", "Lsoot/Unit;", "<init>", "(Lcom/feysh/corax/config/api/IStmt;Lsoot/Unit;)V", "getNode", "()Lsoot/Unit;", "getParameterNameByIndex", "", "index", "Lcom/feysh/corax/config/api/MLocal;", "filter", "Lkotlin/Function1;", "", "Lsoot/Value;", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathCompanion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PostCallStmtInfo\n+ 2 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,994:1\n310#2:995\n303#2:996\n1#3:997\n*S KotlinDebug\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PostCallStmtInfo\n*L\n495#1:995\n503#1:996\n503#1:997\n*E\n"})
/* loaded from: PostCallStmtInfo.class */
public final class PostCallStmtInfo extends ModelingStmtInfo {

    @NotNull
    private final Unit node;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PostCallStmtInfo(@NotNull IStmt stmt, @NotNull Unit node) {
        super(stmt);
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        Intrinsics.checkNotNullParameter(node, "node");
        this.node = node;
    }

    @NotNull
    public final Unit getNode() {
        return this.node;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    @Override // cn.sast.dataflow.interprocedural.check.ModelingStmtInfo
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getParameterNameByIndex(@org.jetbrains.annotations.NotNull com.feysh.corax.config.api.MLocal r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<java.lang.Object, java.lang.Boolean> r5) {
        /*
            r3 = this;
            r0 = r4
            java.lang.String r1 = "index"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r5
            java.lang.String r1 = "filter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            r6 = r0
            r0 = r6
            boolean r0 = r0 instanceof com.feysh.corax.config.api.MParameter
            if (r0 == 0) goto L6d
            r0 = r3
            r1 = r4
            com.feysh.corax.config.api.MParameter r1 = (com.feysh.corax.config.api.MParameter) r1
            int r1 = r1.getIndex()
            soot.Value r0 = r0.getParameterNameByIndex(r1)
            r1 = r0
            if (r1 == 0) goto L65
            r9 = r0
            r0 = r5
            r1 = r9
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L3c
            r0 = r9
            goto L3d
        L3c:
            r0 = 0
        L3d:
            r1 = r0
            if (r1 == 0) goto L65
            r9 = r0
            r0 = r9
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r3
            java.lang.Integer r0 = r0.getFirstParamIndex()
            if (r0 != 0) goto L5f
            r0 = r3
            r1 = r4
            com.feysh.corax.config.api.MParameter r1 = (com.feysh.corax.config.api.MParameter) r1
            int r1 = r1.getIndex()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.setFirstParamIndex(r1)
        L5f:
            r0 = r9
            goto L67
        L65:
            r0 = 0
        L67:
            java.io.Serializable r0 = (java.io.Serializable) r0
            goto Ldf
        L6d:
            r0 = r6
            boolean r0 = r0 instanceof com.feysh.corax.config.api.MReturn
            if (r0 == 0) goto Lbf
            r0 = r3
            soot.Unit r0 = r0.node
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            boolean r0 = r0 instanceof soot.jimple.DefinitionStmt
            if (r0 == 0) goto L8d
            r0 = r8
            soot.jimple.DefinitionStmt r0 = (soot.jimple.DefinitionStmt) r0
            goto L8e
        L8d:
            r0 = 0
        L8e:
            r1 = r0
            if (r1 == 0) goto L9a
            soot.Value r0 = r0.getLeftOp()
            goto L9c
        L9a:
            r0 = 0
        L9c:
            r1 = r0
            if (r1 == 0) goto Lbc
            r8 = r0
            r0 = r5
            r1 = r8
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Lb8
            r0 = r8
            goto Lbe
        Lb8:
            r0 = 0
            goto Lbe
        Lbc:
            r0 = 0
        Lbe:
            return r0
        Lbf:
            r0 = r4
            r7 = r0
            r0 = r5
            r1 = r7
            java.lang.Object r0 = r0.invoke(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Ld8
            r0 = r7
            goto Ld9
        Ld8:
            r0 = 0
        Ld9:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.io.Serializable r0 = (java.io.Serializable) r0
        Ldf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.PostCallStmtInfo.getParameterNameByIndex(com.feysh.corax.config.api.MLocal, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    @Override // cn.sast.dataflow.interprocedural.check.ModelingStmtInfo
    @Nullable
    public Value getParameterNameByIndex(int index) {
        Stmt stmt = this.node;
        InvokeExpr invokeExpr = ((stmt instanceof Stmt ? stmt : null) == null || !stmt.containsInvokeExpr()) ? null : stmt.getInvokeExpr();
        if (invokeExpr == null) {
            return null;
        }
        InvokeExpr iee = invokeExpr;
        List names = iee.getArgs();
        if (index == -1 && (iee instanceof InstanceInvokeExpr)) {
            return ((InstanceInvokeExpr) iee).getBase();
        }
        if (index < 0 || index >= names.size()) {
            return null;
        }
        return (Value) names.get(index);
    }
}
