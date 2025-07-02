package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapBuilder;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\b\u0016\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001BC\b��\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\t0\u0007\u0012\u0012\u0010\n\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ArraySpaceBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "orig", "Lcn/sast/dataflow/interprocedural/check/ArraySpace;", "element", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/ArraySpace;Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getOrig", "()Lcn/sast/dataflow/interprocedural/check/ArraySpace;", "build", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "corax-data-flow"})
/* loaded from: ArraySpaceBuilder.class */
public class ArraySpaceBuilder extends ArrayHeapBuilder<IValue> {

    @NotNull
    private final ArraySpace orig;

    @NotNull
    public final ArraySpace getOrig() {
        return this.orig;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArraySpaceBuilder(@NotNull ArraySpace orig, @NotNull PersistentMap.Builder<Integer, IHeapValues<IValue>> builder, @Nullable IHeapValues.Builder<IValue> builder2) {
        super(builder, builder2, orig.getType(), orig.getAllSize().builder(), orig.getSize(), orig.getInitializedValue());
        Intrinsics.checkNotNullParameter(orig, "orig");
        Intrinsics.checkNotNullParameter(builder, "element");
        this.orig = orig;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public IData<IValue> build2() {
        PersistentMap newMap = getMap().build();
        IHeapValues.Builder<IValue> unreferenced = getUnreferenced();
        IHeapValues newUn = unreferenced != null ? unreferenced.build() : null;
        IHeapValues newAllSize = getAllSize().build();
        if (newMap == this.orig.getMap() && newUn == this.orig.getUnreferenced() && newAllSize == this.orig.getAllSize() && getInitializedValue() == this.orig.getInitializedValue()) {
            return this.orig;
        }
        return new ArraySpace(newMap, newUn, getType(), newAllSize, getSize(), getInitializedValue());
    }
}
