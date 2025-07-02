package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b��\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u00020\u0004B'\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003H\u0016J$\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\b\u001a\u00060\u0002j\u0002`\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/CompanionValueOfConst;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcn/sast/dataflow/interprocedural/check/PathCompanionV;", "value", "path", "Lcn/sast/dataflow/interprocedural/check/IPath;", "attr", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcn/sast/dataflow/interprocedural/check/IPath;Lcn/sast/dataflow/interprocedural/analysis/IValue;)V", "getPath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "getAttr", "()Lcn/sast/dataflow/interprocedural/analysis/IValue;", "copy", "updateValue", "union", "other", "toString", "", "equals", "", "", "computeHash", "", "hashCode", "corax-data-flow"})
/* loaded from: CompanionValueOfConst.class */
public final class CompanionValueOfConst extends CompanionV<IValue> implements PathCompanionV {

    @NotNull
    private final IPath path;

    @NotNull
    private final IValue attr;

    @Override // cn.sast.dataflow.interprocedural.check.PathCompanionV
    @NotNull
    public IPath getPath() {
        return this.path;
    }

    @NotNull
    public final IValue getAttr() {
        return this.attr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompanionValueOfConst(@NotNull IValue value, @NotNull IPath path, @NotNull IValue attr) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(attr, "attr");
        this.path = path;
        this.attr = attr;
        if (value instanceof ConstVal) {
        } else {
            throw new IllegalStateException(String.valueOf(value).toString());
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    @NotNull
    public CompanionV<IValue> copy(@NotNull IValue updateValue) {
        Intrinsics.checkNotNullParameter(updateValue, "updateValue");
        return new CompanionValueOfConst(updateValue, getPath(), this.attr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    @NotNull
    public CompanionV<IValue> union(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "other");
        if (!Intrinsics.areEqual(getValue(), companionV.getValue())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        IPath path = MergePath.Companion.v(new HeapValuesEnvImpl(getPath()), getPath(), ((PathCompanionV) companionV).getPath());
        return new CompanionValueOfConst(getValue(), path, this.attr);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    @NotNull
    public String toString() {
        return "<v=" + getValue() + ", attr=" + this.attr + ">";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    public boolean equals(@Nullable Object other) {
        if (super.equals(other) && (other instanceof CompanionValueOfConst)) {
            return Intrinsics.areEqual(this.attr, ((CompanionValueOfConst) other).attr);
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    public int computeHash() {
        int result = super.computeHash();
        return (31 * result) + this.attr.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    public int hashCode() {
        return super.hashCode();
    }
}
