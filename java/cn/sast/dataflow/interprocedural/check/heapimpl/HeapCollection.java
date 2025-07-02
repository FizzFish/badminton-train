package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.HeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028��0\u0011H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/HeapCollection;", "E", "Lcn/sast/dataflow/interprocedural/analysis/HeapKVData;", "", "element", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "isValidKey", "", "key", "(Ljava/lang/Integer;)Ljava/lang/Boolean;", "getName", "", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "cloneAndReNewObjects", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "corax-data-flow"})
/* loaded from: HeapCollection.class */
public final class HeapCollection<E> extends HeapKVData<Integer, E> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeapCollection(@NotNull PersistentMap<Integer, ? extends IHeapValues<E>> persistentMap, @Nullable IHeapValues<E> iHeapValues) {
        super(persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(persistentMap, "element");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @Nullable
    public Boolean isValidKey(@Nullable Integer key) {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String getName() {
        return "Collection";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public IHeapKVData.Builder<Integer, E> builder2() {
        PersistentMap.Builder builder = getMap().builder();
        IHeapValues<E> unreferenced = getUnreferenced();
        return new HeapCollectionBuilder(builder, unreferenced != null ? unreferenced.builder() : null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<E> cloneAndReNewObjects(@NotNull IReNew<E> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        IHeapKVData.Builder b = builder2();
        b.cloneAndReNewObjects(iReNew);
        return b.build2();
    }
}
