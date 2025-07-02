package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eJ\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH&J\b\u0010\t\u001a\u00020\nH&J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028��0\rH&¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IData;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "reference", "", "res", "", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IData$Builder;", "computeHash", "", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "Builder", "corax-data-flow"})
/* loaded from: IData.class */
public interface IData<V> extends IDiffAble<V> {

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��*\u0004\b\u0001\u0010\u00012\u00020\u0002J$\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&J\u0016\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IData$Builder;", "V", "", "union", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "build", "corax-data-flow"})
    /* loaded from: IData$Builder.class */
    public interface Builder<V> {
        void union(@NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull IData<V> iData);

        void cloneAndReNewObjects(@NotNull IReNew<V> iReNew);

        @NotNull
        IData<V> build();
    }

    void reference(@NotNull Collection<V> collection);

    @NotNull
    Builder<V> builder();

    int computeHash();

    @NotNull
    IData<V> cloneAndReNewObjects(@NotNull IReNew<V> iReNew);
}
