package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004H&J=\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0��\"\u0004\b\u0001\u0010\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0\u000b2\b\u0010\f\u001a\u0004\u0018\u0001H\u0006H&¢\u0006\u0002\u0010\rJ=\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0��\"\u0004\b\u0001\u0010\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028��0\u000b2\b\u0010\f\u001a\u0004\u0018\u0001H\u0006H&¢\u0006\u0002\u0010\rJ$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\n\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0004H&J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028��0\u000bH&J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0004H&¨\u0006\u0016"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "V", "Lcn/sast/dataflow/interprocedural/analysis/JOperator;", "pop", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "setKVValue", "K", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "lhs", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "key", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "getKVValue", "rhs", "assignLocal", "rhsValue", "markOfArrayLength", "dataElementCopyToSequenceElement", "sourceElement", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "corax-data-flow"})
/* loaded from: JOperatorHV.class */
public interface JOperatorHV<V> extends JOperator<V> {
    @NotNull
    IHeapValues<V> pop();

    @NotNull
    <K> JOperatorHV<V> setKVValue(@NotNull Object obj, @NotNull CompanionV<V> companionV, @Nullable K k);

    @NotNull
    <K> JOperatorHV<V> getKVValue(@NotNull Object obj, @NotNull CompanionV<V> companionV, @Nullable K k);

    @NotNull
    JOperatorHV<V> assignLocal(@NotNull Object obj, @NotNull IHeapValues<V> iHeapValues);

    @NotNull
    JOperatorHV<V> markOfArrayLength(@NotNull CompanionV<V> companionV);

    @NotNull
    JOperatorHV<V> dataElementCopyToSequenceElement(@NotNull IHeapValues<IValue> iHeapValues);
}
