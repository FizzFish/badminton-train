package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import soot.jimple.DefinitionStmt;
import soot.jimple.ReturnStmt;
import soot.jimple.Stmt;
import soot.jimple.SwitchStmt;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JIdentityStmt;
import soot.jimple.internal.JIfStmt;
import soot.jimple.internal.JInvokeStmt;
import soot.jimple.internal.JRetStmt;
import soot.jimple.internal.JReturnStmt;
import soot.jimple.internal.JReturnVoidStmt;
import soot.jimple.internal.JThrowStmt;

/* compiled from: AJimpleInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0014H&J\u0016\u0010\u0015\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\u0016H&J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0\u00162\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0015\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u001eH\u0016R\u0012\u0010\u0004\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/TraversalContext;", "V", "A", "", "voidValue", "getVoidValue", "()Ljava/lang/Object;", "traverseAssignStmt", "", "current", "Lsoot/jimple/internal/JAssignStmt;", "traverseIdentityStmt", "Lsoot/jimple/internal/JIdentityStmt;", "traverseIfStmt", "Lsoot/jimple/internal/JIfStmt;", "traverseInvokeStmt", "Lsoot/jimple/internal/JInvokeStmt;", "traverseSwitchStmt", "Lsoot/jimple/SwitchStmt;", "traverseThrowStmt", "Lsoot/jimple/internal/JThrowStmt;", "processResult", "Lcn/sast/dataflow/interprocedural/analysis/MethodResult;", "symbolicSuccess", "stmt", "Lsoot/jimple/ReturnStmt;", "offerState", "state", "(Ljava/lang/Object;)V", "traverseStmt", "Lsoot/jimple/Stmt;", "corax-data-flow"})
/* loaded from: TraversalContext.class */
public interface TraversalContext<V, A> {
    V getVoidValue();

    void traverseAssignStmt(@NotNull JAssignStmt jAssignStmt);

    void traverseIdentityStmt(@NotNull JIdentityStmt jIdentityStmt);

    void traverseIfStmt(@NotNull JIfStmt jIfStmt);

    void traverseInvokeStmt(@NotNull JInvokeStmt jInvokeStmt);

    void traverseSwitchStmt(@NotNull SwitchStmt switchStmt);

    void traverseThrowStmt(@NotNull JThrowStmt jThrowStmt);

    void processResult(@NotNull MethodResult<V> methodResult);

    @NotNull
    MethodResult<V> symbolicSuccess(@NotNull ReturnStmt returnStmt);

    void offerState(A a);

    void traverseStmt(@NotNull Stmt stmt);

    /* compiled from: AJimpleInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: TraversalContext$DefaultImpls.class */
    public static final class DefaultImpls {
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
        public static <V, A> void traverseStmt(@NotNull TraversalContext<V, A> traversalContext, @NotNull Stmt current) throws NotImplementedError {
            Intrinsics.checkNotNullParameter(current, "current");
            if (!(current instanceof JAssignStmt)) {
                if (!(current instanceof JIdentityStmt)) {
                    if (!(current instanceof JIfStmt)) {
                        if (!(current instanceof JInvokeStmt)) {
                            if (!(current instanceof SwitchStmt)) {
                                if (!(current instanceof JReturnStmt)) {
                                    if (!(current instanceof JReturnVoidStmt)) {
                                        if (current instanceof JRetStmt) {
                                            throw new IllegalStateException(("This one should be already removed by Soot: " + current).toString());
                                        }
                                        if (!(current instanceof JThrowStmt)) {
                                            if (!(current instanceof DefinitionStmt)) {
                                                throw new IllegalStateException(("Unsupported: " + Reflection.getOrCreateKotlinClass(current.getClass())).toString());
                                            }
                                            throw new NotImplementedError("An operation is not implemented: " + String.valueOf(current));
                                        }
                                        traversalContext.traverseThrowStmt((JThrowStmt) current);
                                        return;
                                    }
                                    traversalContext.processResult(new SymbolicSuccess(traversalContext.getVoidValue()));
                                    return;
                                }
                                traversalContext.processResult(traversalContext.symbolicSuccess((ReturnStmt) current));
                                return;
                            }
                            traversalContext.traverseSwitchStmt((SwitchStmt) current);
                            return;
                        }
                        traversalContext.traverseInvokeStmt((JInvokeStmt) current);
                        return;
                    }
                    traversalContext.traverseIfStmt((JIfStmt) current);
                    return;
                }
                traversalContext.traverseIdentityStmt((JIdentityStmt) current);
                return;
            }
            traversalContext.traverseAssignStmt((JAssignStmt) current);
        }
    }
}
