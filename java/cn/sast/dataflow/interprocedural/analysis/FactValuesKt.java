package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.G;
import soot.RefType;
import soot.Type;
import soot.dexpler.typing.UntypedIntOrFloatConstant;
import soot.dexpler.typing.UntypedLongOrDoubleConstant;
import soot.jimple.ClassConstant;
import soot.jimple.Constant;
import soot.jimple.DoubleConstant;
import soot.jimple.FloatConstant;
import soot.jimple.IntConstant;
import soot.jimple.LongConstant;
import soot.jimple.NullConstant;
import soot.jimple.StringConstant;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��X\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0016\u001a\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u001b\u001a\u00020\u001c*\u00020\u00052\u0014\u0010\u001d\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u001c0\u001eH\u0086\bø\u0001��\u001a\u0011\u0010 \u001a\u0004\u0018\u00010\u0001*\u00020\u0005¢\u0006\u0002\u0010!\"\u0014\u0010��\u001a\u00020\u0001X\u0086D¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"leastExpr", "", "getLeastExpr", "()Z", "getBooleanValue", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "checkType", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Boolean;", "getIntValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Integer;", "getLongValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Long;", "getByteValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Byte;", "getFloatValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Float;", "getDoubleValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Double;", "getStringValue", "", "getClassValue", "Lsoot/Type;", "getAnyValue", "", "res", "Lkotlin/Function1;", "", "isNull", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;)Ljava/lang/Boolean;", "corax-data-flow"})
/* loaded from: FactValuesKt.class */
public final class FactValuesKt {
    private static final boolean leastExpr = true;

    public static final boolean getLeastExpr() {
        return leastExpr;
    }

    public static /* synthetic */ Boolean getBooleanValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getBooleanValue(iValue, z);
    }

    @Nullable
    public static final Boolean getBooleanValue(@NotNull IValue $this$getBooleanValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getBooleanValue, "<this>");
        if (checkType && $this$getBooleanValue.typeIsConcrete() && !Intrinsics.areEqual($this$getBooleanValue.getType(), G.v().soot_BooleanType())) {
            return null;
        }
        ConstVal constVal = $this$getBooleanValue instanceof ConstVal ? (ConstVal) $this$getBooleanValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        IntConstant intConstant = v instanceof IntConstant ? (IntConstant) v : null;
        if (intConstant == null) {
            return null;
        }
        int num = intConstant.value;
        return Boolean.valueOf(num == 1);
    }

    public static /* synthetic */ Integer getIntValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getIntValue(iValue, z);
    }

    @Nullable
    public static final Integer getIntValue(@NotNull IValue $this$getIntValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getIntValue, "<this>");
        if (checkType && $this$getIntValue.typeIsConcrete() && !Intrinsics.areEqual($this$getIntValue.getType(), G.v().soot_IntType())) {
            return null;
        }
        ConstVal constVal = $this$getIntValue instanceof ConstVal ? (ConstVal) $this$getIntValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        IntConstant intConstant = v instanceof IntConstant ? (IntConstant) v : null;
        if (intConstant != null) {
            return Integer.valueOf(intConstant.value);
        }
        return null;
    }

    public static /* synthetic */ Long getLongValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getLongValue(iValue, z);
    }

    @Nullable
    public static final Long getLongValue(@NotNull IValue $this$getLongValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getLongValue, "<this>");
        if (checkType && $this$getLongValue.typeIsConcrete() && !Intrinsics.areEqual($this$getLongValue.getType(), G.v().soot_LongType())) {
            return null;
        }
        ConstVal constVal = $this$getLongValue instanceof ConstVal ? (ConstVal) $this$getLongValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        LongConstant longConstant = v instanceof LongConstant ? (LongConstant) v : null;
        if (longConstant != null) {
            return Long.valueOf(longConstant.value);
        }
        return null;
    }

    public static /* synthetic */ Byte getByteValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getByteValue(iValue, z);
    }

    @Nullable
    public static final Byte getByteValue(@NotNull IValue $this$getByteValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getByteValue, "<this>");
        if (checkType && $this$getByteValue.typeIsConcrete() && !Intrinsics.areEqual($this$getByteValue.getType(), G.v().soot_ByteType())) {
            return null;
        }
        ConstVal constVal = $this$getByteValue instanceof ConstVal ? (ConstVal) $this$getByteValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        IntConstant intConstant = v instanceof IntConstant ? (IntConstant) v : null;
        if (intConstant != null) {
            return Byte.valueOf((byte) intConstant.value);
        }
        return null;
    }

    public static /* synthetic */ Float getFloatValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getFloatValue(iValue, z);
    }

    @Nullable
    public static final Float getFloatValue(@NotNull IValue $this$getFloatValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getFloatValue, "<this>");
        if (checkType && $this$getFloatValue.typeIsConcrete() && !Intrinsics.areEqual($this$getFloatValue.getType(), G.v().soot_FloatType())) {
            return null;
        }
        ConstVal constVal = $this$getFloatValue instanceof ConstVal ? (ConstVal) $this$getFloatValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        FloatConstant floatConstant = v instanceof FloatConstant ? (FloatConstant) v : null;
        if (floatConstant != null) {
            return Float.valueOf(floatConstant.value);
        }
        return null;
    }

    public static /* synthetic */ Double getDoubleValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getDoubleValue(iValue, z);
    }

    @Nullable
    public static final Double getDoubleValue(@NotNull IValue $this$getDoubleValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getDoubleValue, "<this>");
        if (checkType && $this$getDoubleValue.typeIsConcrete() && !Intrinsics.areEqual($this$getDoubleValue.getType(), G.v().soot_DoubleType())) {
            return null;
        }
        ConstVal constVal = $this$getDoubleValue instanceof ConstVal ? (ConstVal) $this$getDoubleValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        DoubleConstant doubleConstant = v instanceof DoubleConstant ? (DoubleConstant) v : null;
        if (doubleConstant != null) {
            return Double.valueOf(doubleConstant.value);
        }
        return null;
    }

    public static /* synthetic */ String getStringValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getStringValue(iValue, z);
    }

    @Nullable
    public static final String getStringValue(@NotNull IValue $this$getStringValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getStringValue, "<this>");
        if (checkType && $this$getStringValue.typeIsConcrete() && !Intrinsics.areEqual($this$getStringValue.getType(), RefType.v("java.lang.String"))) {
            return null;
        }
        ConstVal constVal = $this$getStringValue instanceof ConstVal ? (ConstVal) $this$getStringValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        StringConstant stringConstant = v instanceof StringConstant ? (StringConstant) v : null;
        if (stringConstant == null) {
            return null;
        }
        String str = stringConstant.value;
        if (str == null) {
            return null;
        }
        return str;
    }

    public static /* synthetic */ Type getClassValue$default(IValue iValue, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return getClassValue(iValue, z);
    }

    @Nullable
    public static final Type getClassValue(@NotNull IValue $this$getClassValue, boolean checkType) {
        Intrinsics.checkNotNullParameter($this$getClassValue, "<this>");
        if (checkType && $this$getClassValue.typeIsConcrete() && !Intrinsics.areEqual($this$getClassValue.getType(), RefType.v("java.lang.Class"))) {
            return null;
        }
        ConstVal constVal = $this$getClassValue instanceof ConstVal ? (ConstVal) $this$getClassValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        ClassConstant classConstant = v instanceof ClassConstant ? (ClassConstant) v : null;
        if (classConstant == null) {
            return null;
        }
        Type sootType = classConstant.toSootType();
        if (sootType == null) {
            return null;
        }
        return sootType;
    }

    public static final void getAnyValue(@NotNull IValue $this$getAnyValue, @NotNull Function1<Object, Unit> function1) {
        Intrinsics.checkNotNullParameter($this$getAnyValue, "<this>");
        Intrinsics.checkNotNullParameter(function1, "res");
        ConstVal constVal = $this$getAnyValue instanceof ConstVal ? (ConstVal) $this$getAnyValue : null;
        Constant v = constVal != null ? constVal.getV() : null;
        if (v != null) {
            if (!(v instanceof IntConstant)) {
                if (!(v instanceof StringConstant)) {
                    if (!(v instanceof LongConstant)) {
                        if (!(v instanceof NullConstant)) {
                            if (!(v instanceof DoubleConstant)) {
                                if (!(v instanceof FloatConstant)) {
                                    if (!(v instanceof ClassConstant)) {
                                        if (!(v instanceof UntypedIntOrFloatConstant)) {
                                            if (v instanceof UntypedLongOrDoubleConstant) {
                                                function1.invoke(Long.valueOf(((UntypedLongOrDoubleConstant) v).value));
                                                return;
                                            }
                                            return;
                                        }
                                        function1.invoke(Integer.valueOf(((UntypedIntOrFloatConstant) v).value));
                                        return;
                                    }
                                    function1.invoke(((ClassConstant) v).value);
                                    return;
                                }
                                function1.invoke(Float.valueOf(((FloatConstant) v).value));
                                return;
                            }
                            function1.invoke(Double.valueOf(((DoubleConstant) v).value));
                            return;
                        }
                        function1.invoke((Object) null);
                        return;
                    }
                    function1.invoke(Long.valueOf(((LongConstant) v).value));
                    return;
                }
                function1.invoke(((StringConstant) v).value);
                return;
            }
            function1.invoke(Integer.valueOf(((IntConstant) v).value));
        }
    }

    @Nullable
    public static final Boolean isNull(@NotNull IValue $this$isNull) {
        Intrinsics.checkNotNullParameter($this$isNull, "<this>");
        if ($this$isNull instanceof ConstVal) {
            return Boolean.valueOf(((ConstVal) $this$isNull).getV() instanceof NullConstant);
        }
        return null;
    }
}
