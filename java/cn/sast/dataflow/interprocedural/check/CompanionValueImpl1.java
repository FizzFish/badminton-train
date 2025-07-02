package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b��\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u00020\u0004B\u001b\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ$\u0010\f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010\r\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001e\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u0003H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/CompanionValueImpl1;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcn/sast/dataflow/interprocedural/check/PathCompanionV;", "value", "path", "Lcn/sast/dataflow/interprocedural/check/IPath;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcn/sast/dataflow/interprocedural/check/IPath;)V", "getPath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "union", "other", "toString", "", "copy", "updateValue", "corax-data-flow"})
/* loaded from: CompanionValueImpl1.class */
public final class CompanionValueImpl1 extends CompanionV<IValue> implements PathCompanionV {

    @NotNull
    private final IPath path;

    @Override // cn.sast.dataflow.interprocedural.check.PathCompanionV
    @NotNull
    public IPath getPath() {
        return this.path;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompanionValueImpl1(@NotNull IValue value, @NotNull IPath path) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
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
        return new CompanionValueImpl1(getValue(), path);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    @NotNull
    public String toString() {
        return "<" + getValue() + ">";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.CompanionV
    @NotNull
    public CompanionV<IValue> copy(@NotNull IValue updateValue) {
        Intrinsics.checkNotNullParameter(updateValue, "updateValue");
        return new CompanionValueImpl1(updateValue, getPath());
    }
}
