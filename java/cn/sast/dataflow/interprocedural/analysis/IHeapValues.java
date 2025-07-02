package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.collections.immutable.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��f\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001-J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006H&J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0��H¦\u0002J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\tH¦\u0002J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0015H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0\u0018H&J6\u0010\u0019\u001a\u00020\u00042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028��0\u00182\u001e\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t0\u001cH&J<\u0010\u001d\u001a\u00020\u00042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028��0\u00182$\u0010\u001b\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t0\u001e0\u001cH&J\b\u0010\u001f\u001a\u00020\u0015H&J\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010$2\u0006\u0010%\u001a\u00020\u0015H&J\u0017\u0010&\u001a\u0004\u0018\u00010\u000b2\u0006\u0010%\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010'J\u0015\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t0)H¦\u0002J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010+\u001a\b\u0012\u0004\u0012\u00028��0,H&R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t0\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00028��0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006."}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "reference", "", "res", "", "plus", "rhs", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "size", "", "getSize", "()I", "values", "Lkotlinx/collections/immutable/ImmutableSet;", "getValues", "()Lkotlinx/collections/immutable/ImmutableSet;", "valuesCompanion", "getValuesCompanion", "isNotEmpty", "", "isEmpty", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "map", "c", "transform", "Lkotlin/Function1;", "flatMap", "", "isSingle", "single", "getSingle", "()Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "getAllIntValue", "", "must", "getMaxInt", "(Z)Ljava/lang/Integer;", "iterator", "", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "Builder", "corax-data-flow"})
/* loaded from: IHeapValues.class */
public interface IHeapValues<V> extends IDiffAble<V> {

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b\u0001\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010��2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010��2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&J\u0016\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fH&¨\u0006\u0010"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "V", "", "isEmpty", "", "isNotEmpty", "add", "elements", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "element", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "build", "cloneAndReNewObjects", "", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "corax-data-flow"})
    /* loaded from: IHeapValues$Builder.class */
    public interface Builder<V> {
        boolean isEmpty();

        boolean isNotEmpty();

        @NotNull
        Builder<V> add(@NotNull IHeapValues<V> iHeapValues);

        @NotNull
        Builder<V> add(@NotNull CompanionV<V> companionV);

        @NotNull
        IHeapValues<V> build();

        void cloneAndReNewObjects(@NotNull IReNew<V> iReNew);
    }

    void reference(@NotNull Collection<V> collection);

    @NotNull
    IHeapValues<V> plus(@NotNull IHeapValues<V> iHeapValues);

    @NotNull
    IHeapValues<V> plus(@NotNull CompanionV<V> companionV);

    int getSize();

    @NotNull
    ImmutableSet<V> getValues();

    @NotNull
    ImmutableSet<CompanionV<V>> getValuesCompanion();

    boolean isNotEmpty();

    boolean isEmpty();

    @NotNull
    Builder<V> builder();

    void map(@NotNull Builder<V> builder, @NotNull Function1<? super CompanionV<V>, ? extends CompanionV<V>> function1);

    void flatMap(@NotNull Builder<V> builder, @NotNull Function1<? super CompanionV<V>, ? extends Collection<? extends CompanionV<V>>> function1);

    boolean isSingle();

    @NotNull
    CompanionV<V> getSingle();

    @Nullable
    Set<Integer> getAllIntValue(boolean z);

    @Nullable
    Integer getMaxInt(boolean z);

    @NotNull
    Iterator<CompanionV<V>> iterator();

    @NotNull
    IHeapValues<V> cloneAndReNewObjects(@NotNull IReNew<V> iReNew);

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IHeapValues$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <V> Integer getMaxInt(@NotNull IHeapValues<V> iHeapValues, boolean must) {
            Set<Integer> allIntValue = iHeapValues.getAllIntValue(must);
            if (allIntValue != null) {
                return (Integer) CollectionsKt.maxOrNull(allIntValue);
            }
            return null;
        }
    }
}
