package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/JFieldNameType;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "fieldName", "", "type", "Lsoot/Type;", "<init>", "(Ljava/lang/String;Lsoot/Type;)V", "getFieldName", "()Ljava/lang/String;", "getType", "()Lsoot/Type;", "name", "getName", "toString", "hashCode", "", "equals", "", "other", "", "corax-data-flow"})
/* loaded from: JFieldNameType.class */
public final class JFieldNameType extends JFieldType {

    @NotNull
    private final String fieldName;

    @NotNull
    private final Type type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JFieldNameType(@NotNull String fieldName, @NotNull Type type) {
        super(null);
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Intrinsics.checkNotNullParameter(type, "type");
        this.fieldName = fieldName;
        this.type = type;
    }

    @NotNull
    public final String getFieldName() {
        return this.fieldName;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JFieldType
    @NotNull
    public Type getType() {
        return this.type;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JFieldType
    @NotNull
    public String getName() {
        return this.fieldName;
    }

    @NotNull
    public String toString() {
        return getName();
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(@Nullable Object other) {
        String name = getName();
        JFieldType jFieldType = other instanceof JFieldType ? (JFieldType) other : null;
        return Intrinsics.areEqual(name, jFieldType != null ? jFieldType.getName() : null);
    }
}
