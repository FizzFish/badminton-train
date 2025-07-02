package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n��\u0018��2\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010��\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010��¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "", "caller", "callSite", "Lsoot/Unit;", "method", "Lsoot/SootMethod;", "deep", "", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;Lsoot/Unit;Lsoot/SootMethod;I)V", "getCaller", "()Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "getCallSite", "()Lsoot/Unit;", "getMethod", "()Lsoot/SootMethod;", "getDeep", "()I", "toString", "", "corax-data-flow"})
/* loaded from: CallStackContext.class */
public final class CallStackContext {

    @Nullable
    private final CallStackContext caller;

    @NotNull
    private final Unit callSite;

    @NotNull
    private final SootMethod method;
    private final int deep;

    public CallStackContext(@Nullable CallStackContext caller, @NotNull Unit callSite, @NotNull SootMethod method, int deep) {
        Intrinsics.checkNotNullParameter(callSite, "callSite");
        Intrinsics.checkNotNullParameter(method, "method");
        this.caller = caller;
        this.callSite = callSite;
        this.method = method;
        this.deep = deep;
    }

    @Nullable
    public final CallStackContext getCaller() {
        return this.caller;
    }

    @NotNull
    public final Unit getCallSite() {
        return this.callSite;
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    public final int getDeep() {
        return this.deep;
    }

    @NotNull
    public String toString() {
        CallStackContext callStackContext = this.caller;
        return "at " + (callStackContext != null ? callStackContext.method : null) + " line: " + this.callSite.getJavaSourceStartLineNumber() + ": " + this.callSite + " invoke -> " + this.method + "\n";
    }
}
