package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AJimpleInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028��¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00028��HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0��2\b\b\u0002\u0010\u0003\u001a\u00028��HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0003\u001a\u00028��¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/SymbolicSuccess;", "V", "Lcn/sast/dataflow/interprocedural/analysis/MethodResult;", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/SymbolicSuccess;", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
/* loaded from: SymbolicSuccess.class */
public final class SymbolicSuccess<V> extends MethodResult<V> {
    private final V value;

    public final V component1() {
        return this.value;
    }

    @NotNull
    public final SymbolicSuccess<V> copy(V v) {
        return new SymbolicSuccess<>(v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SymbolicSuccess copy$default(SymbolicSuccess symbolicSuccess, Object obj, int i, Object obj2) {
        V v = obj;
        if ((i & 1) != 0) {
            v = symbolicSuccess.value;
        }
        return symbolicSuccess.copy(v);
    }

    @NotNull
    public String toString() {
        return "SymbolicSuccess(value=" + this.value + ")";
    }

    public int hashCode() {
        if (this.value == null) {
            return 0;
        }
        return this.value.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SymbolicSuccess) && Intrinsics.areEqual(this.value, ((SymbolicSuccess) other).value);
    }

    public SymbolicSuccess(V v) {
        super(null);
        this.value = v;
    }

    public final V getValue() {
        return this.value;
    }
}
