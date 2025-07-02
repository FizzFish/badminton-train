package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import soot.Type;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H&J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H&R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ICalculator;", "V", "", "res", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "getRes", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "isFullySimplified", "", "putSummaryValue", "", "type", "Lsoot/Type;", "special", "putSummaryIfNotConcrete", "corax-data-flow"})
/* loaded from: ICalculator.class */
public interface ICalculator<V> {
    @NotNull
    IHeapValues.Builder<V> getRes();

    boolean isFullySimplified();

    void putSummaryValue(@NotNull Type type, @NotNull Object obj);

    void putSummaryIfNotConcrete(@NotNull Type type, @NotNull Object obj);
}
