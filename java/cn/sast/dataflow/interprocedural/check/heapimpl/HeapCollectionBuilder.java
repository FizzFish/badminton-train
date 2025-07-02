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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\fH\u0016¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/HeapCollectionBuilder;", "E", "Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder;", "", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "build", "Lcn/sast/dataflow/interprocedural/check/heapimpl/HeapCollection;", "corax-data-flow"})
/* loaded from: HeapCollectionBuilder.class */
public final class HeapCollectionBuilder<E> extends HeapDataBuilder<Integer, E> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeapCollectionBuilder(@NotNull PersistentMap.Builder<Integer, IHeapValues<E>> builder, @Nullable IHeapValues.Builder<E> builder2) {
        super(builder, builder2);
        Intrinsics.checkNotNullParameter(builder, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public HeapCollection<E> build2() {
        PersistentMap persistentMapBuild = getMap().build();
        IHeapValues.Builder<E> unreferenced = getUnreferenced();
        return new HeapCollection<>(persistentMapBuild, unreferenced != null ? unreferenced.build() : null);
    }
}
