package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ImmutableSet;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.RefLikeType;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.Constant;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��l\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018�� -*\u0004\b��\u0010\u00012\u00020\u0002:\u0001-J)\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b2\u0006\u0010\t\u001a\u00028��2\n\u0010\n\u001a\u00060\u0002j\u0002`\u000bH&¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H&J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0017H&J\b\u0010\u0019\u001a\u00020\u0017H\u0016J \u0010\u001a\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0002H&J\u001d\u0010\u001d\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010\u001e\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u001fJ#\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00028��\u0018\u00010!2\u0006\u0010\u001e\u001a\u00028��H\u0016¢\u0006\u0002\u0010#J&\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,¨\u0006."}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "V", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getValueData", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "v", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "(Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IData;", "getTargets", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "slot", "getTargetsUnsafe", "getSlots", "", "getCalledMethods", "Lkotlinx/collections/immutable/ImmutableSet;", "Lsoot/SootMethod;", "isBottom", "", "isTop", "isValid", "getOfSlot", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "getArrayLength", "array", "(Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getArray", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/IArrayHeapKV;", "", "(Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/heapimpl/IArrayHeapKV;", "getOfSootValue", "value", "Lsoot/Value;", "valueType", "Lsoot/Type;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "getCallStack", "()Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "Companion", "corax-data-flow"})
/* loaded from: IIFact.class */
public interface IIFact<V> {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @NotNull
    AbstractHeapFactory<V> getHf();

    @Nullable
    IData<V> getValueData(V v, @NotNull Object obj);

    @NotNull
    IHeapValues<V> getTargets(@NotNull Object obj);

    @Nullable
    IHeapValues<V> getTargetsUnsafe(@NotNull Object obj);

    @NotNull
    /* renamed from: getSlots */
    Set<Object> mo173getSlots();

    @NotNull
    /* renamed from: getCalledMethods */
    ImmutableSet<SootMethod> mo174getCalledMethods();

    boolean isBottom();

    boolean isTop();

    boolean isValid();

    @Nullable
    IHeapValues<V> getOfSlot(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Object obj);

    @Nullable
    IHeapValues<V> getArrayLength(V v);

    @Nullable
    IArrayHeapKV<Integer, V> getArray(V v);

    @NotNull
    IHeapValues<V> getOfSootValue(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Value value, @NotNull Type type);

    @NotNull
    CallStackContext getCallStack();

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IIFact$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: IIFact$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final KLogger logger = KotlinLogging.INSTANCE.logger(Companion::logger$lambda$0);

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return logger;
        }

        private static final Unit logger$lambda$0() {
            return Unit.INSTANCE;
        }
    }

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IIFact$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static <V> IHeapValues<V> getTargets(@NotNull IIFact<V> iIFact, @NotNull Object slot) {
            Intrinsics.checkNotNullParameter(slot, "slot");
            IHeapValues<V> targetsUnsafe = iIFact.getTargetsUnsafe(slot);
            return targetsUnsafe == null ? iIFact.getHf().empty() : targetsUnsafe;
        }

        public static <V> boolean isValid(@NotNull IIFact<V> iIFact) {
            return (iIFact.isTop() || iIFact.isBottom()) ? false : true;
        }

        @Nullable
        public static <V> IHeapValues<V> getArrayLength(@NotNull IIFact<V> iIFact, V v) {
            IArrayHeapKV<Integer, V> array = iIFact.getArray(v);
            if (array != null) {
                return array.getArrayLength();
            }
            return null;
        }

        @Nullable
        public static <V> IArrayHeapKV<Integer, V> getArray(@NotNull IIFact<V> iIFact, V v) {
            IData<V> valueData = iIFact.getValueData(v, BuiltInModelT.Array);
            if (valueData instanceof IArrayHeapKV) {
                return (IArrayHeapKV) valueData;
            }
            return null;
        }

        @NotNull
        public static <V> IHeapValues<V> getOfSootValue(@NotNull IIFact<V> iIFact, @NotNull HeapValuesEnv env, @NotNull Value value, @NotNull Type valueType) {
            Type type;
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(valueType, "valueType");
            if (value instanceof Constant) {
                if (((Constant) value).getType() instanceof RefLikeType) {
                    type = ((Constant) value).getType();
                } else {
                    type = valueType;
                }
                Type type2 = type;
                AbstractHeapFactory<V> hf = iIFact.getHf();
                Intrinsics.checkNotNull(type2);
                return JOperatorV.DefaultImpls.markOfConstant$default(hf.push(env, (HeapValuesEnv) iIFact.getHf().newConstVal2((Constant) value, type2)), (Constant) value, null, 2, null).popHV();
            }
            if (value instanceof Local) {
                return iIFact.getTargets(value);
            }
            Function0 function0 = () -> {
                return getOfSootValue$lambda$0(r2);
            };
            throw new IllegalStateException(function0.toString());
        }

        private static String getOfSootValue$lambda$0(Value $value) {
            return "error soot.Value: " + $value;
        }
    }
}
