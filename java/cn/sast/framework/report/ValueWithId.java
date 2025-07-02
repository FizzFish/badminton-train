package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SqliteDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0082\b\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028��¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u000e\u001a\u00028��HÆ\u0003¢\u0006\u0002\u0010\u000bJ(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028��0��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028��HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00028��¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcn/sast/framework/report/ValueWithId;", "T", "", "id", "", "value", "<init>", "(JLjava/lang/Object;)V", "getId", "()J", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(JLjava/lang/Object;)Lcn/sast/framework/report/ValueWithId;", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: ValueWithId.class */
final class ValueWithId<T> {
    private final long id;
    private final T value;

    public final long component1() {
        return this.id;
    }

    public final T component2() {
        return this.value;
    }

    @NotNull
    public final ValueWithId<T> copy(long id, T t) {
        return new ValueWithId<>(id, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ValueWithId copy$default(ValueWithId valueWithId, long j, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            j = valueWithId.id;
        }
        T t = obj;
        if ((i & 2) != 0) {
            t = valueWithId.value;
        }
        return valueWithId.copy(j, t);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        T t = this.value;
        return "ValueWithId(id=" + j + ", value=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (result * 31) + (this.value == null ? 0 : this.value.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ValueWithId)) {
            return false;
        }
        ValueWithId valueWithId = (ValueWithId) other;
        return this.id == valueWithId.id && Intrinsics.areEqual(this.value, valueWithId.value);
    }

    public ValueWithId(long id, T t) {
        this.id = id;
        this.value = t;
    }

    public final long getId() {
        return this.id;
    }

    public final T getValue() {
        return this.value;
    }
}
