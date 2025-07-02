package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IIFact;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.Context;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000bJ\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005H&J$\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0��H&¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IFact;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IProblemIteratorTerminal;", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Builder", "corax-data-flow"})
/* loaded from: IFact.class */
public interface IFact<V> extends IProblemIteratorTerminal<V>, IIFact<V> {
    @NotNull
    Builder<V> builder();

    void diff(@NotNull IDiff<V> iDiff, @NotNull IFact<V> iFact);

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IFact$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static <V> IHeapValues<V> getTargets(@NotNull IFact<V> iFact, @NotNull Object slot) {
            Intrinsics.checkNotNullParameter(slot, "slot");
            return IIFact.DefaultImpls.getTargets(iFact, slot);
        }

        public static <V> boolean isValid(@NotNull IFact<V> iFact) {
            return IIFact.DefaultImpls.isValid(iFact);
        }

        @Nullable
        public static <V> IHeapValues<V> getArrayLength(@NotNull IFact<V> iFact, V v) {
            return IIFact.DefaultImpls.getArrayLength(iFact, v);
        }

        @Nullable
        public static <V> IArrayHeapKV<Integer, V> getArray(@NotNull IFact<V> iFact, V v) {
            return IIFact.DefaultImpls.getArray(iFact, v);
        }

        @NotNull
        public static <V> IHeapValues<V> getOfSootValue(@NotNull IFact<V> iFact, @NotNull HeapValuesEnv env, @NotNull Value value, @NotNull Type valueType) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(valueType, "valueType");
            return IIFact.DefaultImpls.getOfSootValue(iFact, env, value, valueType);
        }
    }

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��~\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018��*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0007J9\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00012\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0010H&¢\u0006\u0002\u0010\u0011J \u0010\u0012\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH&J(\u0010\u0015\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J0\u0010\u0019\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH&J6\u0010\u001e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H&J:\u0010\"\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001dH&J2\u0010%\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH&J.\u0010&\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH&J2\u0010'\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020\u001dH&J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\rH&J\u0016\u0010*\u001a\u00020\u00042\f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00010,H&J>\u0010-\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u00010,2\u0006\u00102\u001a\u00020\u001dH&J\u0010\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\rH&J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00028\u00010,H&J\u0010\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u000208H&J0\u00109\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010:\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J0\u0010;\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\b\u0010:\u001a\u0004\u0018\u00010\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH&J*\u0010<\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\b\u0010:\u001a\u0004\u0018\u00010\u0016H&J*\u0010<\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00162\b\u0010:\u001a\u0004\u0018\u00010\u0016H&J@\u0010=\u001a\u00020\u00042\u001e\u0010>\u001a\u001a\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020@\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010,0?2\u0006\u0010A\u001a\u0002082\u0006\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020@H&J\b\u0010D\u001a\u00020\u0004H&¨\u0006E"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "copyValueData", "", "from", "to", "(Ljava/lang/Object;Ljava/lang/Object;)V", "setValueData", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "v", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "data", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IData;)V", "assignLocal", "lhs", "rhs", "assignLocalSootValue", "Lsoot/Value;", "valueType", "Lsoot/Type;", "assignNewExpr", "allocSite", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "append", "", "assignNewArray", "type", "Lsoot/ArrayType;", "size", "assignFieldSootValue", "field", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "setField", "setFieldNew", "getField", "newSummaryField", "summarizeTargetFields", "union", "that", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "updateIntraEdge", "ctx", "Lsoot/Context;", "calleeCtx", "callEdgeValue", "hasReturnValue", "kill", "slot", "build", "addCalledMethod", "sm", "Lsoot/SootMethod;", "setArraySootValue", "index", "setArrayValueNew", "getArrayValue", "callEntryFlowFunction", "context", "Lcn/sast/idfa/analysis/Context;", "Lsoot/Unit;", "callee", "node", "succ", "gc", "corax-data-flow"})
    /* loaded from: IFact$Builder.class */
    public interface Builder<V> extends IIFact<V> {
        void copyValueData(V v, V v2);

        void setValueData(@NotNull HeapValuesEnv heapValuesEnv, V v, @NotNull Object obj, @Nullable IData<V> iData);

        void assignLocal(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Object obj2);

        void assignLocalSootValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Value value, @NotNull Type type);

        void assignNewExpr(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull IHeapValues<V> iHeapValues, boolean z);

        void assignNewArray(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull IHeapValues<V> iHeapValues, @NotNull ArrayType arrayType, @NotNull Value value);

        void assignFieldSootValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull JFieldType jFieldType, @NotNull Value value, @NotNull Type type, boolean z);

        void setField(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull JFieldType jFieldType, @NotNull Object obj2, boolean z);

        void setFieldNew(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull JFieldType jFieldType, @NotNull IHeapValues<V> iHeapValues);

        void getField(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Object obj2, @NotNull JFieldType jFieldType, boolean z);

        void summarizeTargetFields(@NotNull Object obj);

        void union(@NotNull IFact<V> iFact);

        @Nullable
        IHeapValues<V> updateIntraEdge(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Context context, @NotNull Context context2, @NotNull IFact<V> iFact, boolean z);

        void kill(@NotNull Object obj);

        @NotNull
        IFact<V> build();

        void addCalledMethod(@NotNull SootMethod sootMethod);

        void setArraySootValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Value value, @NotNull Value value2, @NotNull Type type);

        void setArrayValueNew(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @Nullable Value value, @NotNull IHeapValues<V> iHeapValues);

        boolean getArrayValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Object obj2, @Nullable Value value);

        boolean getArrayValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj, @NotNull Value value, @Nullable Value value2);

        void callEntryFlowFunction(@NotNull cn.sast.idfa.analysis.Context<SootMethod, Unit, IFact<V>> context, @NotNull SootMethod sootMethod, @NotNull Unit unit, @NotNull Unit unit2);

        void gc();

        /* compiled from: IFact.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
        /* loaded from: IFact$Builder$DefaultImpls.class */
        public static final class DefaultImpls {
            @NotNull
            public static <V> IHeapValues<V> getTargets(@NotNull Builder<V> builder, @NotNull Object slot) {
                Intrinsics.checkNotNullParameter(slot, "slot");
                return IIFact.DefaultImpls.getTargets(builder, slot);
            }

            public static <V> boolean isValid(@NotNull Builder<V> builder) {
                return IIFact.DefaultImpls.isValid(builder);
            }

            @Nullable
            public static <V> IHeapValues<V> getArrayLength(@NotNull Builder<V> builder, V v) {
                return IIFact.DefaultImpls.getArrayLength(builder, v);
            }

            @Nullable
            public static <V> IArrayHeapKV<Integer, V> getArray(@NotNull Builder<V> builder, V v) {
                return IIFact.DefaultImpls.getArray(builder, v);
            }

            @NotNull
            public static <V> IHeapValues<V> getOfSootValue(@NotNull Builder<V> builder, @NotNull HeapValuesEnv env, @NotNull Value value, @NotNull Type valueType) {
                Intrinsics.checkNotNullParameter(env, "env");
                Intrinsics.checkNotNullParameter(value, "value");
                Intrinsics.checkNotNullParameter(valueType, "valueType");
                return IIFact.DefaultImpls.getOfSootValue(builder, env, value, valueType);
            }

            public static /* synthetic */ void assignNewExpr$default(Builder builder, HeapValuesEnv heapValuesEnv, Object obj, IHeapValues iHeapValues, boolean z, int i, Object obj2) {
                if (obj2 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: assignNewExpr");
                }
                if ((i & 8) != 0) {
                    z = false;
                }
                builder.assignNewExpr(heapValuesEnv, obj, iHeapValues, z);
            }

            public static /* synthetic */ void assignFieldSootValue$default(Builder builder, HeapValuesEnv heapValuesEnv, Object obj, JFieldType jFieldType, Value value, Type type, boolean z, int i, Object obj2) {
                if (obj2 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: assignFieldSootValue");
                }
                if ((i & 32) != 0) {
                    z = false;
                }
                builder.assignFieldSootValue(heapValuesEnv, obj, jFieldType, value, type, z);
            }

            public static /* synthetic */ void setField$default(Builder builder, HeapValuesEnv heapValuesEnv, Object obj, JFieldType jFieldType, Object obj2, boolean z, int i, Object obj3) {
                if (obj3 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setField");
                }
                if ((i & 16) != 0) {
                    z = false;
                }
                builder.setField(heapValuesEnv, obj, jFieldType, obj2, z);
            }

            public static /* synthetic */ void getField$default(Builder builder, HeapValuesEnv heapValuesEnv, Object obj, Object obj2, JFieldType jFieldType, boolean z, int i, Object obj3) {
                if (obj3 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getField");
                }
                if ((i & 16) != 0) {
                    z = true;
                }
                builder.getField(heapValuesEnv, obj, obj2, jFieldType, z);
            }
        }
    }
}
