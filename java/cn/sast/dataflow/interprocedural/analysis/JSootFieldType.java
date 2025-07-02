package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootField;
import soot.Type;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/JSootFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "sootField", "Lsoot/SootField;", "<init>", "(Lsoot/SootField;)V", "getSootField", "()Lsoot/SootField;", "type", "Lsoot/Type;", "getType", "()Lsoot/Type;", "name", "", "getName", "()Ljava/lang/String;", "hashCode", "", "equals", "", "other", "", "toString", "corax-data-flow"})
/* loaded from: JSootFieldType.class */
public final class JSootFieldType extends JFieldType {

    @NotNull
    private final SootField sootField;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JSootFieldType(@NotNull SootField sootField) {
        super(null);
        Intrinsics.checkNotNullParameter(sootField, "sootField");
        this.sootField = sootField;
    }

    @NotNull
    public final SootField getSootField() {
        return this.sootField;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JFieldType
    @NotNull
    public Type getType() {
        Type type = this.sootField.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return type;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JFieldType
    @NotNull
    public String getName() {
        String name = this.sootField.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(@Nullable Object other) {
        String name = getName();
        JFieldType jFieldType = other instanceof JFieldType ? (JFieldType) other : null;
        return Intrinsics.areEqual(name, jFieldType != null ? jFieldType.getName() : null);
    }

    @NotNull
    public String toString() {
        return getName();
    }
}
