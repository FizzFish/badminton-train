package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.Context;
import soot.Local;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b&\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J$\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028��0\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001eH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028��0 H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006!"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AbstractBOTTOM;", "V", "Lcn/sast/dataflow/interprocedural/analysis/InValidFact;", "<init>", "()V", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "isBottom", "", "isTop", "isValid", "toString", "", "equals", "other", "", "hashCode", "", "getOfSlot", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "slot", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "corax-data-flow"})
/* loaded from: AbstractBOTTOM.class */
public abstract class AbstractBOTTOM<V> extends InValidFact<V> {
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public AbstractHeapFactory<V> getHf() throws NotImplementedError {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isBottom() {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isTop() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.InValidFact, cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isValid() {
        return false;
    }

    @NotNull
    public String toString() {
        return "IFact: BOTTOM";
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof IFact) && isBottom() && ((IFact) other).isBottom();
    }

    public int hashCode() {
        return 2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getOfSlot(@NotNull HeapValuesEnv env, @NotNull Object slot) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(slot, "slot");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IFact<V> iFact) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iFact, "that");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact
    @NotNull
    public IFact.Builder<V> builder() {
        return new IFact.Builder<V>(this) { // from class: cn.sast.dataflow.interprocedural.analysis.AbstractBOTTOM.builder.1
            final /* synthetic */ AbstractBOTTOM<V> this$0;

            {
                this.this$0 = this;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IArrayHeapKV<Integer, V> getArray(V v) {
                return IFact.Builder.DefaultImpls.getArray(this, v);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public CallStackContext getCallStack() throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void assignLocal(HeapValuesEnv env, Object lhs, Object rhs) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void assignNewExpr(HeapValuesEnv env, Object lhs, IHeapValues<V> iHeapValues, boolean append) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void setField(HeapValuesEnv env, Object lhs, JFieldType field, Object rhs, boolean append) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(field, "field");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void setFieldNew(HeapValuesEnv env, Object lhs, JFieldType field, IHeapValues<V> iHeapValues) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(field, "field");
                Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void getField(HeapValuesEnv env, Object lhs, Object rhs, JFieldType field, boolean newSummaryField) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                Intrinsics.checkNotNullParameter(field, "field");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void summarizeTargetFields(Object lhs) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void union(IFact<V> iFact) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(iFact, "that");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public IHeapValues<V> updateIntraEdge(HeapValuesEnv env, Context ctx, Context calleeCtx, IFact<V> iFact, boolean hasReturnValue) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(ctx, "ctx");
                Intrinsics.checkNotNullParameter(calleeCtx, "calleeCtx");
                Intrinsics.checkNotNullParameter(iFact, "callEdgeValue");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void kill(Object slot) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(slot, "slot");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            /* renamed from: getSlots */
            public Set<Local> mo173getSlots() throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public IFact<V> build() {
                return this.this$0;
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void addCalledMethod(SootMethod sm) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(sm, "sm");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            /* renamed from: getCalledMethods */
            public ImmutableSet<SootMethod> mo174getCalledMethods() throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IHeapValues<V> getTargets(Object slot) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(slot, "slot");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public boolean isBottom() {
                return true;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public boolean isTop() {
                return false;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public boolean isValid() {
                return false;
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void setArraySootValue(HeapValuesEnv env, Object lhs, Value index, Value rhs, Type valueType) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(index, "index");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                Intrinsics.checkNotNullParameter(valueType, "valueType");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void setArrayValueNew(HeapValuesEnv env, Object lhs, Value index, IHeapValues<V> iHeapValues) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public boolean getArrayValue(HeapValuesEnv env, Object lhs, Object rhs, Value index) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public boolean getArrayValue(HeapValuesEnv env, Object lhs, Value rhs, Value index) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void gc() throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void callEntryFlowFunction(cn.sast.idfa.analysis.Context<SootMethod, Unit, IFact<V>> context, SootMethod callee, Unit node, Unit succ) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(callee, "callee");
                Intrinsics.checkNotNullParameter(node, "node");
                Intrinsics.checkNotNullParameter(succ, "succ");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void assignNewArray(HeapValuesEnv env, Object lhs, IHeapValues<V> iHeapValues, ArrayType type, Value size) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(size, "size");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IData<V> getValueData(V v, Object mt) {
                Intrinsics.checkNotNullParameter(mt, "mt");
                return null;
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IHeapValues<V> getOfSlot(HeapValuesEnv env, Object slot) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(slot, "slot");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void setValueData(HeapValuesEnv env, V v, Object mt, IData<V> iData) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(mt, "mt");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IHeapValues<V> getArrayLength(V v) throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void assignLocalSootValue(HeapValuesEnv env, Object lhs, Value rhs, Type valueType) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                Intrinsics.checkNotNullParameter(valueType, "valueType");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void assignFieldSootValue(HeapValuesEnv env, Object lhs, JFieldType field, Value rhs, Type valueType, boolean append) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(lhs, "lhs");
                Intrinsics.checkNotNullParameter(field, "field");
                Intrinsics.checkNotNullParameter(rhs, "rhs");
                Intrinsics.checkNotNullParameter(valueType, "valueType");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IHeapValues<V> getOfSootValue(HeapValuesEnv env, Value value, Type valueType) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(value, "value");
                Intrinsics.checkNotNullParameter(valueType, "valueType");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public AbstractHeapFactory<V> getHf() throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
            public void copyValueData(V v, V v2) throws NotImplementedError {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
            public IHeapValues<V> getTargetsUnsafe(Object slot) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(slot, "slot");
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }
        };
    }
}
