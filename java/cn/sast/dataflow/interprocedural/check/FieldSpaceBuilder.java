package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.check.heapimpl.FieldHeapBuilder;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BG\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\n0\b\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0012H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/FieldSpaceBuilder;", "V", "Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapBuilder;", "orig", "Lcn/sast/dataflow/interprocedural/check/FieldSpace;", "clz", "Lsoot/RefType;", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/FieldSpace;Lsoot/RefType;Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getOrig", "()Lcn/sast/dataflow/interprocedural/check/FieldSpace;", "build", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "corax-data-flow"})
/* loaded from: FieldSpaceBuilder.class */
public final class FieldSpaceBuilder<V> extends FieldHeapBuilder<V> {

    @NotNull
    private final FieldSpace<V> orig;

    @NotNull
    public final FieldSpace<V> getOrig() {
        return this.orig;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldSpaceBuilder(@NotNull FieldSpace<V> fieldSpace, @NotNull RefType clz, @NotNull PersistentMap.Builder<JFieldType, IHeapValues<V>> builder, @Nullable IHeapValues.Builder<V> builder2) {
        super(clz, builder, builder2);
        Intrinsics.checkNotNullParameter(fieldSpace, "orig");
        Intrinsics.checkNotNullParameter(clz, "clz");
        Intrinsics.checkNotNullParameter(builder, "fields");
        this.orig = fieldSpace;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public IData<V> build2() {
        PersistentMap newMap = getMap().build();
        IHeapValues.Builder<V> unreferenced = getUnreferenced();
        IHeapValues newUn = unreferenced != null ? unreferenced.build() : null;
        if (newMap == this.orig.getMap() && newUn == this.orig.getUnreferenced()) {
            return this.orig;
        }
        return new FieldSpace(getClz(), newMap, newUn);
    }
}
