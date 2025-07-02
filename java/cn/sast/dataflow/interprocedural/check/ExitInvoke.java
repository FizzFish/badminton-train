package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ExitInvoke;", "", "invoke", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;)V", "getInvoke", "()Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
/* loaded from: ExitInvoke.class */
public final class ExitInvoke {

    @NotNull
    private final InvokeEdgePath invoke;

    @NotNull
    public final InvokeEdgePath component1() {
        return this.invoke;
    }

    @NotNull
    public final ExitInvoke copy(@NotNull InvokeEdgePath invoke) {
        Intrinsics.checkNotNullParameter(invoke, "invoke");
        return new ExitInvoke(invoke);
    }

    public static /* synthetic */ ExitInvoke copy$default(ExitInvoke exitInvoke, InvokeEdgePath invokeEdgePath, int i, Object obj) {
        if ((i & 1) != 0) {
            invokeEdgePath = exitInvoke.invoke;
        }
        return exitInvoke.copy(invokeEdgePath);
    }

    @NotNull
    public String toString() {
        return "ExitInvoke(invoke=" + this.invoke + ")";
    }

    public int hashCode() {
        return this.invoke.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ExitInvoke) && Intrinsics.areEqual(this.invoke, ((ExitInvoke) other).invoke);
    }

    public ExitInvoke(@NotNull InvokeEdgePath invoke) {
        Intrinsics.checkNotNullParameter(invoke, "invoke");
        this.invoke = invoke;
    }

    @NotNull
    public final InvokeEdgePath getInvoke() {
        return this.invoke;
    }
}
