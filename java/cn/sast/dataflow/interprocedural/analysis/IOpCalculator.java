package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Ji\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0��2Y\u0010\u0004\u001aU\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0��\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��0\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u001f\u0012\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000b0\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH&¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "V", "Lcn/sast/dataflow/interprocedural/analysis/ICalculator;", "resolve", "fx", "Lkotlin/Function3;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "Lkotlin/ParameterName;", "name", "res", "", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "op", "", "Lkotlin/ExtensionFunctionType;", "corax-data-flow"})
/* loaded from: IOpCalculator.class */
public interface IOpCalculator<V> extends ICalculator<V> {
    @NotNull
    IOpCalculator<V> resolve(@NotNull Function3<? super IOpCalculator<V>, ? super IHeapValues.Builder<V>, ? super CompanionV<V>[], Boolean> function3);
}
