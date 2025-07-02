package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.HeapValues;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesBuilder;
import cn.sast.dataflow.interprocedural.analysis.IDiff;
import cn.sast.dataflow.interprocedural.analysis.IDiffAble;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u0018��2\u0016\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001BO\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012 \u0010\u0006\u001a\u001c\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00050\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000f\u001a\u00020\u00102\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00122\u0010\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\u001c\u0010\u001c\u001a\u0016\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001dH\u0016R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u001e"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ObjectKeyHashMap;", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "keys", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "fields", "Lkotlinx/collections/immutable/PersistentMap;", "unreferenced", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getKeys", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "values", "getValues", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "", "computeHash", "", "hashCode", "equals", "", "other", "builder", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMapBuilder;", "corax-data-flow"})
/* loaded from: ObjectKeyHashMap.class */
public final class ObjectKeyHashMap extends ImmutableElementHashMap<IValue, IValue> {

    @NotNull
    private final IHeapValues<IValue> keys;

    @NotNull
    public final IHeapValues<IValue> getKeys() {
        return this.keys;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObjectKeyHashMap(@NotNull IHeapValues<IValue> iHeapValues, @NotNull PersistentMap<IValue, ? extends IHeapValues<IValue>> persistentMap, @Nullable IHeapValues<IValue> iHeapValues2) {
        super(persistentMap, iHeapValues2);
        Intrinsics.checkNotNullParameter(iHeapValues, "keys");
        Intrinsics.checkNotNullParameter(persistentMap, "fields");
        this.keys = iHeapValues;
    }

    @NotNull
    public final IHeapValues<IValue> getValues() {
        HeapValuesBuilder it = new HeapValues(null, 1, null).builder();
        for (IHeapValues v : getMap().values()) {
            it.add(v);
        }
        return it.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<IValue> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
        if (iDiffAble instanceof ObjectKeyHashMap) {
            this.keys.diff(iDiff, ((ObjectKeyHashMap) iDiffAble).keys);
            getValues().diff(iDiff, ((ObjectKeyHashMap) iDiffAble).getValues());
        }
        super.diff(iDiff, iDiffAble);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        int result = (31 * 1) + super.computeHash();
        return result;
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public int hashCode() {
        return super.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public boolean equals(@Nullable Object other) {
        if (super.equals(other) && (other instanceof ObjectKeyHashMap)) {
            return Intrinsics.areEqual(this.keys, ((ObjectKeyHashMap) other).keys);
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public ImmutableElementHashMapBuilder<IValue, IValue> builder2() {
        IHeapValues.Builder<IValue> builder = this.keys.builder();
        PersistentMap.Builder builder2 = getMap().builder();
        IHeapValues<IValue> unreferenced = getUnreferenced();
        return new ObjectKeyHashMapBuilder(builder, builder2, unreferenced != null ? unreferenced.builder() : null);
    }
}
