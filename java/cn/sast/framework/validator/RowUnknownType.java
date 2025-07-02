package cn.sast.framework.validator;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\b\u0080\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0003H\u0016J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/framework/validator/RowUnknownType;", "Lcn/sast/framework/validator/RowType;", "type", "", "<init>", "(Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "toString", "component1", "copy", "equals", "", "other", "", "hashCode", "", "corax-framework"})
/* loaded from: RowUnknownType.class */
public final class RowUnknownType extends RowType {

    @NotNull
    private final String type;

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final RowUnknownType copy(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new RowUnknownType(type);
    }

    public static /* synthetic */ RowUnknownType copy$default(RowUnknownType rowUnknownType, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rowUnknownType.type;
        }
        return rowUnknownType.copy(str);
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RowUnknownType) && Intrinsics.areEqual(this.type, ((RowUnknownType) other).type);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RowUnknownType(@NotNull String type) {
        super(null);
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
    }

    @Override // cn.sast.framework.validator.RowType
    @NotNull
    public String getType() {
        return this.type;
    }

    @NotNull
    public String toString() {
        return getType();
    }
}
