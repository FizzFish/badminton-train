package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\r2\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\r2\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u001b\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0007¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ObjectValuesBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IData$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "orig", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ObjectValues;", "values", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/heapimpl/ObjectValues;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getValues", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "union", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "addAll", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "build", "toString", "", "corax-data-flow"})
/* loaded from: ObjectValuesBuilder.class */
public final class ObjectValuesBuilder implements IData.Builder<IValue> {

    @NotNull
    private final ObjectValues orig;

    @NotNull
    private final IHeapValues.Builder<IValue> values;

    public ObjectValuesBuilder(@NotNull ObjectValues orig, @NotNull IHeapValues.Builder<IValue> builder) {
        Intrinsics.checkNotNullParameter(orig, "orig");
        Intrinsics.checkNotNullParameter(builder, "values");
        this.orig = orig;
        this.values = builder;
    }

    @NotNull
    public final IHeapValues.Builder<IValue> getValues() {
        return this.values;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void union(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull IData<IValue> iData) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(iData, "that");
        if (!(iData instanceof ObjectValues)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.values.add(((ObjectValues) iData).getValues());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void cloneAndReNewObjects(@NotNull IReNew<IValue> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        this.values.cloneAndReNewObjects(iReNew);
    }

    public final void addAll(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "values");
        this.values.add(iHeapValues);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build, reason: merged with bridge method [inline-methods] */
    public IData<IValue> build2() {
        IHeapValues iHeapValuesBuild = this.values.build();
        if (Intrinsics.areEqual(this.orig.getValues(), iHeapValuesBuild)) {
            return this.orig;
        }
        return new ObjectValues(iHeapValuesBuild);
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.values);
    }
}
