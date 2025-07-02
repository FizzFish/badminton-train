package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.HeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0016\u0018��*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B5\u0012\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\u0010\u0013\u001a\u0004\u0018\u00018��H\u0016¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u001aH\u0016¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapKVData;", "fields", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "equals", "", "other", "hashCode", "", "getName", "", "isValidKey", "key", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "cloneAndReNewObjects", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "builder", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMapBuilder;", "corax-data-flow"})
/* loaded from: ImmutableElementHashMap.class */
public class ImmutableElementHashMap<K, V> extends HeapKVData<K, V> {
    public ImmutableElementHashMap() {
        this(null, null, 3, null);
    }

    public /* synthetic */ ImmutableElementHashMap(PersistentMap persistentMap, IHeapValues iHeapValues, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExtensionsKt.persistentHashMapOf() : persistentMap, (i & 2) != 0 ? null : iHeapValues);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmutableElementHashMap(@NotNull PersistentMap<K, ? extends IHeapValues<V>> persistentMap, @Nullable IHeapValues<V> iHeapValues) {
        super(persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(persistentMap, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public boolean equals(@Nullable Object other) {
        return super.equals(other) && (other instanceof ImmutableElementHashMap);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public int hashCode() {
        return super.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String getName() {
        return "ImmHashMap";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @Nullable
    public Boolean isValidKey(@Nullable K k) {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<V> cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        ImmutableElementHashMapBuilder b = builder2();
        b.cloneAndReNewObjects(iReNew);
        return b.build2();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public ImmutableElementHashMapBuilder<K, V> builder2() {
        PersistentMap.Builder builder = getMap().builder();
        IHeapValues<V> unreferenced = getUnreferenced();
        return new ImmutableElementHashMapBuilder<>(builder, unreferenced != null ? unreferenced.builder() : null);
    }
}
