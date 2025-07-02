package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HeapKVData.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028��¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\f\u001a\b\u0012\u0004\u0012\u00028��0��2\u0011\u0010\r\u001a\r\u0012\t\u0012\u00078��¢\u0006\u0002\b\u000e0��H&J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u0015\u001a\u00028��H&¢\u0006\u0002\u0010\u0016R\u0013\u0010\u0003\u001a\u00028��¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "V", "", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "hashCode", "", "Ljava/lang/Integer;", "union", "other", "Lkotlin/UnsafeVariance;", "toString", "", "equals", "", "computeHash", "copy", "updateValue", "(Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "corax-data-flow"})
/* loaded from: CompanionV.class */
public abstract class CompanionV<V> {
    private final V value;

    @Nullable
    private Integer hashCode;

    @NotNull
    public abstract CompanionV<V> union(@NotNull CompanionV<V> companionV);

    @NotNull
    public abstract CompanionV<V> copy(V v);

    public CompanionV(V v) {
        this.value = v;
    }

    public final V getValue() {
        return this.value;
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.value);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof CompanionV) {
            return Intrinsics.areEqual(this.value, ((CompanionV) other).value);
        }
        return false;
    }

    public int computeHash() {
        V v = this.value;
        return (v != null ? v.hashCode() : 0) + 23342879;
    }

    public int hashCode() {
        Integer h = this.hashCode;
        if (h == null) {
            h = Integer.valueOf(computeHash());
            this.hashCode = h;
        }
        return h.intValue();
    }
}
