package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.check.heapimpl.FieldHeapKV;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0006\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028��0\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u0013H\u0016¨\u0006\u0014"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/FieldSpace;", "V", "Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV;", "clz", "Lsoot/RefType;", "fields", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lsoot/RefType;Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "getName", "", "cloneAndReNewObjects", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "corax-data-flow"})
/* loaded from: FieldSpace.class */
public final class FieldSpace<V> extends FieldHeapKV<V> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldSpace(@NotNull RefType clz, @NotNull PersistentMap<JFieldType, ? extends IHeapValues<V>> persistentMap, @Nullable IHeapValues<V> iHeapValues) {
        super(clz, persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(clz, "clz");
        Intrinsics.checkNotNullParameter(persistentMap, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public IHeapKVData.Builder<JFieldType, V> builder2() {
        RefType clz = getClz();
        PersistentMap.Builder builder = getMap().builder();
        IHeapValues<V> unreferenced = getUnreferenced();
        return new FieldSpaceBuilder(this, clz, builder, unreferenced != null ? unreferenced.builder() : null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String getName() {
        return "field(" + getClz() + ")";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<V> cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        IHeapKVData.Builder b = builder2();
        b.cloneAndReNewObjects(iReNew);
        return b.build2();
    }
}
