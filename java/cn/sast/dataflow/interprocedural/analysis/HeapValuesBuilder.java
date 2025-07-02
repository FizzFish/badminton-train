package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\b��\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\"\b\u0002\u0010\u0006\u001a\u001c\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\r\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000eH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapValuesBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValuesBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "orig", "Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "map", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValues;Lkotlinx/collections/immutable/PersistentMap$Builder;)V", "getOrig", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "build", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "corax-data-flow"})
/* loaded from: HeapValuesBuilder.class */
public final class HeapValuesBuilder extends AbstractHeapValuesBuilder<IValue> {

    @NotNull
    private final HeapValues orig;

    public /* synthetic */ HeapValuesBuilder(HeapValues heapValues, PersistentMap.Builder builder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(heapValues, (i & 2) != 0 ? heapValues.getMap().builder() : builder);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapValuesBuilder
    @NotNull
    /* renamed from: getOrig, reason: merged with bridge method [inline-methods] */
    public AbstractHeapValues<IValue> getOrig2() {
        return this.orig;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeapValuesBuilder(@NotNull HeapValues orig, @NotNull PersistentMap.Builder<IValue, CompanionV<IValue>> builder) {
        super(orig, builder, null);
        Intrinsics.checkNotNullParameter(orig, "orig");
        Intrinsics.checkNotNullParameter(builder, "map");
        this.orig = orig;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    @NotNull
    public IHeapValues<IValue> build() {
        PersistentMap newMap = getMap().build();
        if (newMap == getOrig2().getMap()) {
            return getOrig2();
        }
        return new HeapValues(getMap().build());
    }
}
