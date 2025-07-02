package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b&\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J$\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028��0\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001eH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AbstractTOP;", "V", "Lcn/sast/dataflow/interprocedural/analysis/InValidFact;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "isBottom", "", "isTop", "isValid", "toString", "", "equals", "other", "", "hashCode", "", "getOfSlot", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "slot", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "corax-data-flow"})
/* loaded from: AbstractTOP.class */
public abstract class AbstractTOP<V> extends InValidFact<V> {

    @NotNull
    private final AbstractHeapFactory<V> hf;

    public AbstractTOP(@NotNull AbstractHeapFactory<V> abstractHeapFactory) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        this.hf = abstractHeapFactory;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public AbstractHeapFactory<V> getHf() {
        return this.hf;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isBottom() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isTop() {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.InValidFact, cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isValid() {
        return false;
    }

    @NotNull
    public String toString() {
        return "IFact: TOP";
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof IFact) && isTop() && ((IFact) other).isTop();
    }

    public int hashCode() {
        return 1;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getOfSlot(@NotNull HeapValuesEnv env, @NotNull Object slot) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(slot, "slot");
        return getHf().empty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IFact<V> iFact) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iFact, "that");
    }
}
