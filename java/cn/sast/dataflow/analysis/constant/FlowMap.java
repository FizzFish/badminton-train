package cn.sast.dataflow.analysis.constant;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DivExpr;
import soot.jimple.EqExpr;
import soot.jimple.GeExpr;
import soot.jimple.GtExpr;
import soot.jimple.IntConstant;
import soot.jimple.LeExpr;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.SubExpr;

/* compiled from: FlowMap.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\"\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018�� \u001d2\u00020\u0001:\u0001\u001dB\u001f\b\u0007\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0004H\u0086\u0002J\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0005J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020��J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u0013\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R&\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007¨\u0006\u001e"}, d2 = {"Lcn/sast/dataflow/analysis/constant/FlowMap;", "", "delegateMap", "", "Lsoot/Local;", "Lcn/sast/dataflow/analysis/constant/CPValue;", "<init>", "(Ljava/util/Map;)V", "getDelegateMap", "()Ljava/util/Map;", "setDelegateMap", "get", "local", "put", "value", "keySet", "", "copyFrom", "", "flowMap", "computeValue", "sootValue", "Lsoot/Value;", "equals", "other", "hashCode", "", "toString", "", "Companion", "corax-data-flow"})
/* loaded from: FlowMap.class */
public final class FlowMap {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private Map<Local, CPValue> delegateMap;

    @JvmOverloads
    public FlowMap(@NotNull Map<Local, CPValue> map) {
        Intrinsics.checkNotNullParameter(map, "delegateMap");
        this.delegateMap = map;
    }

    public /* synthetic */ FlowMap(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new HashMap() : map);
    }

    @NotNull
    public final Map<Local, CPValue> getDelegateMap() {
        return this.delegateMap;
    }

    public final void setDelegateMap(@NotNull Map<Local, CPValue> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.delegateMap = map;
    }

    @JvmOverloads
    public FlowMap() {
        this(null, 1, null);
    }

    @NotNull
    public final CPValue get(@NotNull Local local) {
        Intrinsics.checkNotNullParameter(local, "local");
        Map<Local, CPValue> map = this.delegateMap;
        final Function1 function1 = FlowMap::get$lambda$0;
        CPValue cPValueComputeIfAbsent = map.computeIfAbsent(local, new Function(function1) { // from class: cn.sast.dataflow.analysis.constant.FlowMap$sam$java_util_function_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function1, "function");
                this.function = function1;
            }

            @Override // java.util.function.Function
            public final /* synthetic */ Object apply(Object p0) {
                return this.function.invoke(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(cPValueComputeIfAbsent, "computeIfAbsent(...)");
        return cPValueComputeIfAbsent;
    }

    private static final CPValue get$lambda$0(Local l) {
        Intrinsics.checkNotNullParameter(l, "l");
        return CPValue.Companion.getUndef();
    }

    @Nullable
    public final CPValue put(@NotNull Local local, @NotNull CPValue value) {
        Intrinsics.checkNotNullParameter(local, "local");
        Intrinsics.checkNotNullParameter(value, "value");
        return this.delegateMap.put(local, value);
    }

    @NotNull
    public final Set<Local> keySet() {
        return this.delegateMap.keySet();
    }

    public final boolean copyFrom(@NotNull FlowMap flowMap) {
        Intrinsics.checkNotNullParameter(flowMap, "flowMap");
        this.delegateMap.putAll(flowMap.delegateMap);
        return Intrinsics.areEqual(flowMap.delegateMap, this.delegateMap);
    }

    @NotNull
    public final CPValue computeValue(@NotNull Value sootValue) {
        Intrinsics.checkNotNullParameter(sootValue, "sootValue");
        if (sootValue instanceof Local) {
            return get((Local) sootValue);
        }
        if (sootValue instanceof IntConstant) {
            return CPValue.Companion.makeConstant(((IntConstant) sootValue).value);
        }
        if (sootValue instanceof BinopExpr) {
            Value op1 = ((BinopExpr) sootValue).getOp1();
            Intrinsics.checkNotNull(op1);
            CPValue op1Val = computeValue(op1);
            Value op2 = ((BinopExpr) sootValue).getOp2();
            Intrinsics.checkNotNull(op2);
            CPValue op2Val = computeValue(op2);
            if (op1Val == CPValue.Companion.getUndef() && op2Val == CPValue.Companion.getUndef()) {
                return CPValue.Companion.getUndef();
            }
            if (op1Val == CPValue.Companion.getUndef() || op2Val == CPValue.Companion.getUndef()) {
                return CPValue.Companion.getNac();
            }
            if (op1Val == CPValue.Companion.getNac() || op2Val == CPValue.Companion.getNac()) {
                return CPValue.Companion.getNac();
            }
            try {
                BinopExpr binopExpr = (BinopExpr) sootValue;
                if (binopExpr instanceof AddExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() + op2Val.value());
                }
                if (binopExpr instanceof SubExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() - op2Val.value());
                }
                if (binopExpr instanceof MulExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() * op2Val.value());
                }
                if (binopExpr instanceof DivExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() / op2Val.value());
                }
                if (binopExpr instanceof EqExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() == op2Val.value());
                }
                if (binopExpr instanceof NeExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() != op2Val.value());
                }
                if (binopExpr instanceof GeExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() >= op2Val.value());
                }
                if (binopExpr instanceof GtExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() > op2Val.value());
                }
                if (binopExpr instanceof LeExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() <= op2Val.value());
                }
                if (binopExpr instanceof LtExpr) {
                    return CPValue.Companion.makeConstant(op1Val.value() < op2Val.value());
                }
                return CPValue.Companion.getNac();
            } catch (ArithmeticException e) {
                return CPValue.Companion.getNac();
            }
        }
        return CPValue.Companion.getNac();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        FlowMap flowMap = (FlowMap) other;
        return Intrinsics.areEqual(this.delegateMap, flowMap.delegateMap);
    }

    public int hashCode() {
        return Objects.hash(this.delegateMap);
    }

    @NotNull
    public String toString() {
        return this.delegateMap.toString();
    }

    /* compiled from: FlowMap.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/analysis/constant/FlowMap$Companion;", "", "<init>", "()V", "meet", "Lcn/sast/dataflow/analysis/constant/FlowMap;", "map1", "map2", "corax-data-flow"})
    /* loaded from: FlowMap$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final FlowMap meet(@NotNull FlowMap map1, @NotNull FlowMap map2) {
            Intrinsics.checkNotNullParameter(map1, "map1");
            Intrinsics.checkNotNullParameter(map2, "map2");
            FlowMap resultMap = new FlowMap(null, 1, null);
            Set<Local> localSet = new HashSet();
            localSet.addAll(map1.keySet());
            localSet.addAll(map2.keySet());
            for (Local local : localSet) {
                CPValue v1 = map1.get(local);
                CPValue v2 = map2.get(local);
                CPValue meetVal = CPValue.Companion.meetValue(v1, v2);
                resultMap.put(local, meetVal);
            }
            return resultMap;
        }
    }
}
