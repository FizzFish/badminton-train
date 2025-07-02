package cn.sast.framework.validator;

import cn.sast.api.report.ReportKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\b\u0080\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/validator/RowCheckType;", "Lcn/sast/framework/validator/RowType;", "type", "Lcom/feysh/corax/config/api/CheckType;", "<init>", "(Lcom/feysh/corax/config/api/CheckType;)V", "getType", "()Lcom/feysh/corax/config/api/CheckType;", "toString", "", "component1", "copy", "equals", "", "other", "", "hashCode", "", "corax-framework"})
/* loaded from: RowCheckType.class */
public final class RowCheckType extends RowType {

    @NotNull
    private final CheckType type;

    @NotNull
    public final CheckType component1() {
        return this.type;
    }

    @NotNull
    public final RowCheckType copy(@NotNull CheckType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new RowCheckType(type);
    }

    public static /* synthetic */ RowCheckType copy$default(RowCheckType rowCheckType, CheckType checkType, int i, Object obj) {
        if ((i & 1) != 0) {
            checkType = rowCheckType.type;
        }
        return rowCheckType.copy(checkType);
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RowCheckType) && Intrinsics.areEqual(this.type, ((RowCheckType) other).type);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RowCheckType(@NotNull CheckType type) {
        super(null);
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
    }

    @Override // cn.sast.framework.validator.RowType
    @NotNull
    public CheckType getType() {
        return this.type;
    }

    @NotNull
    public String toString() {
        return ReportKt.getPerfectName(getType());
    }
}
