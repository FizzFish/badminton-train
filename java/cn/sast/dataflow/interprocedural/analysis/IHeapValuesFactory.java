package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0016J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tH&¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "V", "", "empty", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "emptyBuilder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "single", "v", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "corax-data-flow"})
/* loaded from: IHeapValuesFactory.class */
public interface IHeapValuesFactory<V> {
    @NotNull
    IHeapValues<V> empty();

    @NotNull
    IHeapValues.Builder<V> emptyBuilder();

    @NotNull
    IHeapValues<V> single(@NotNull CompanionV<V> companionV);

    /* compiled from: AbstractHeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IHeapValuesFactory$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static <V> IHeapValues.Builder<V> emptyBuilder(@NotNull IHeapValuesFactory<V> iHeapValuesFactory) {
            return iHeapValuesFactory.empty().builder();
        }
    }
}
