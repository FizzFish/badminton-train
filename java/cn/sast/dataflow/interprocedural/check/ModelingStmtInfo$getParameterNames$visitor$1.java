package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.AttributeName;
import com.feysh.corax.config.api.ClassField;
import com.feysh.corax.config.api.IBinOpExpr;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.IIexGetField;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.IIstSetField;
import com.feysh.corax.config.api.IIstStoreLocal;
import com.feysh.corax.config.api.IModelExpressionVisitor;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.IQOpExpr;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.ITriOpExpr;
import com.feysh.corax.config.api.IUnOpExpr;
import com.feysh.corax.config.api.SubFields;
import com.feysh.corax.config.api.TaintProperty;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��M\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0010H\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0013H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0014H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0015H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0016H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0017H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0018H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"cn/sast/dataflow/interprocedural/check/ModelingStmtInfo$getParameterNames$visitor$1", "Lcom/feysh/corax/config/api/IModelStmtVisitor;", "", "Lcom/feysh/corax/config/api/IModelExpressionVisitor;", "result", "", "", "getResult", "()Ljava/util/Set;", "setResult", "(Ljava/util/Set;)V", "default", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "visit", "Lcom/feysh/corax/config/api/IIstSetField;", "Lcom/feysh/corax/config/api/IIstStoreLocal;", "expr", "Lcom/feysh/corax/config/api/IExpr;", "Lcom/feysh/corax/config/api/IIexGetField;", "Lcom/feysh/corax/config/api/IIexLoad;", "Lcom/feysh/corax/config/api/IUnOpExpr;", "Lcom/feysh/corax/config/api/IBinOpExpr;", "Lcom/feysh/corax/config/api/ITriOpExpr;", "Lcom/feysh/corax/config/api/IQOpExpr;", "corax-data-flow"})
/* loaded from: ModelingStmtInfo$getParameterNames$visitor$1.class */
public final class ModelingStmtInfo$getParameterNames$visitor$1 implements IModelStmtVisitor<Unit>, IModelExpressionVisitor<Unit> {
    private Set<Object> result = new LinkedHashSet();
    final /* synthetic */ ModelingStmtInfo this$0;
    final /* synthetic */ Function1<Object, Boolean> $filter;

    ModelingStmtInfo$getParameterNames$visitor$1(ModelingStmtInfo $receiver, Function1<Object, Boolean> function1) {
        this.this$0 = $receiver;
        this.$filter = function1;
    }

    public void visit(IIexConst expr) {
        IModelExpressionVisitor.DefaultImpls.visit(this, expr);
    }

    /* renamed from: default, reason: not valid java name */
    public /* bridge */ /* synthetic */ Object m208default(IStmt stmt) {
        m206default(stmt);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m210visit(IIstSetField stmt) {
        visit(stmt);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m211visit(IIstStoreLocal stmt) {
        visit(stmt);
        return Unit.INSTANCE;
    }

    /* renamed from: default, reason: not valid java name */
    public /* bridge */ /* synthetic */ Object m209default(IExpr expr) {
        m207default(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m212visit(IIexGetField expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m213visit(IIexLoad expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m214visit(IUnOpExpr expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m215visit(IBinOpExpr expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m216visit(ITriOpExpr expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m217visit(IQOpExpr expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    /* renamed from: visit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m218visit(IIexConst expr) {
        visit(expr);
        return Unit.INSTANCE;
    }

    public final Set<Object> getResult() {
        return this.result;
    }

    public final void setResult(Set<Object> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.result = set;
    }

    /* renamed from: default, reason: not valid java name */
    public void m206default(IStmt stmt) {
        Intrinsics.checkNotNullParameter(stmt, "stmt");
    }

    public void visit(IIstSetField stmt) {
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        stmt.getValue().accept(this);
    }

    public void visit(IIstStoreLocal stmt) {
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        stmt.getValue().accept(this);
    }

    /* renamed from: default, reason: not valid java name */
    public void m207default(IExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
    }

    public void visit(IIexGetField expr) {
        Object string;
        Intrinsics.checkNotNullParameter(expr, "expr");
        List acp = CollectionsKt.toMutableList(expr.getAccessPath());
        AttributeName attributeName = (IClassField) CollectionsKt.lastOrNull(acp);
        if ((attributeName instanceof AttributeName) && Intrinsics.areEqual(attributeName.getName(), "isConstant")) {
            CollectionsKt.removeLastOrNull(acp);
        }
        if (attributeName instanceof TaintProperty) {
            CollectionsKt.removeLastOrNull(acp);
        }
        IIexLoad base = expr.getBase();
        if (base instanceof IIexLoad) {
            string = this.this$0.getParameterNameByIndex(base.getOp(), this.$filter);
            if (string == null) {
                return;
            }
        } else {
            string = base.toString();
        }
        Object baseStr = string;
        if (acp.isEmpty()) {
            this.result.add(baseStr.toString());
        } else {
            this.result.add(baseStr + "." + CollectionsKt.joinToString$default(acp, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, ModelingStmtInfo$getParameterNames$visitor$1::visit$lambda$0, 30, (Object) null));
        }
    }

    private static final CharSequence visit$lambda$0(IClassField it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it instanceof SubFields ? "*" : it instanceof ClassField ? ((ClassField) it).getFieldName() : it.toString();
    }

    public void visit(IIexLoad expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        Object it = this.this$0.getParameterNameByIndex(expr.getOp(), this.$filter);
        if (it != null) {
            this.result.add(it);
        }
    }

    public void visit(IUnOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        expr.getOp1().accept(this);
    }

    public void visit(IBinOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        expr.getOp1().accept(this);
        expr.getOp2().accept(this);
    }

    public void visit(ITriOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        expr.getOp1().accept(this);
        expr.getOp2().accept(this);
        expr.getOp3().accept(this);
    }

    public void visit(IQOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        expr.getOp1().accept(this);
        expr.getOp2().accept(this);
        expr.getOp3().accept(this);
        expr.getOp4().accept(this);
    }
}
