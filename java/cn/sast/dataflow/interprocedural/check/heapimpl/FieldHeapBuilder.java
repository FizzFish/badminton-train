package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;

/* compiled from: FIeldHeap.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapBuilder;", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "clz", "Lsoot/RefType;", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lsoot/RefType;Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getClz", "()Lsoot/RefType;", "corax-data-flow"})
/* loaded from: FieldHeapBuilder.class */
public abstract class FieldHeapBuilder<V> extends HeapDataBuilder<JFieldType, V> {

    @NotNull
    private final RefType clz;

    @NotNull
    public final RefType getClz() {
        return this.clz;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldHeapBuilder(@NotNull RefType clz, @NotNull PersistentMap.Builder<JFieldType, IHeapValues<V>> builder, @Nullable IHeapValues.Builder<V> builder2) {
        super(builder, builder2);
        Intrinsics.checkNotNullParameter(clz, "clz");
        Intrinsics.checkNotNullParameter(builder, "fields");
        this.clz = clz;
    }
}
