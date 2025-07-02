package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0016\u0018��*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B1\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMapBuilder;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder;", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "build", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "corax-data-flow"})
/* loaded from: ImmutableElementHashMapBuilder.class */
public class ImmutableElementHashMapBuilder<K, V> extends HeapDataBuilder<K, V> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmutableElementHashMapBuilder(@NotNull PersistentMap.Builder<K, IHeapValues<V>> builder, @Nullable IHeapValues.Builder<V> builder2) {
        super(builder, builder2);
        Intrinsics.checkNotNullParameter(builder, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public ImmutableElementHashMap<K, V> build2() {
        PersistentMap persistentMapBuild = getMap().build();
        IHeapValues.Builder<V> unreferenced = getUnreferenced();
        return new ImmutableElementHashMap<>(persistentMapBuild, unreferenced != null ? unreferenced.build() : null);
    }
}
