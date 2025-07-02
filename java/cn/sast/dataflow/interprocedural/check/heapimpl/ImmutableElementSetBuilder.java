package cn.sast.dataflow.interprocedural.check.heapimpl;

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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u0012\u0012\u0004\u0012\u0002H\u0001\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003B9\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00028��\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00028��0\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSetBuilder;", "E", "", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMapBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "build", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "corax-data-flow"})
/* loaded from: ImmutableElementSetBuilder.class */
public final class ImmutableElementSetBuilder<E> extends ImmutableElementHashMapBuilder<E, IValue> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmutableElementSetBuilder(@NotNull PersistentMap.Builder<E, IHeapValues<IValue>> builder, @Nullable IHeapValues.Builder<IValue> builder2) {
        super(builder, builder2);
        Intrinsics.checkNotNullParameter(builder, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMapBuilder, cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public ImmutableElementSet<E> build2() {
        PersistentMap persistentMapBuild = getMap().build();
        IHeapValues.Builder<IValue> unreferenced = getUnreferenced();
        return new ImmutableElementSet<>(persistentMapBuild, unreferenced != null ? unreferenced.build() : null);
    }
}
