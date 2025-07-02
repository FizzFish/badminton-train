package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.HeapFactoryKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl;
import cn.sast.dataflow.interprocedural.check.checker.CheckerModelingKt;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap;
import cn.sast.dataflow.util.SootUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.G;
import soot.SootField;
import soot.Type;
import soot.jimple.Constant;
import soot.jimple.StringConstant;

/* compiled from: WString.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��&\n��\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010��\u001a\u0004\u0018\u00010\u0001*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u00022\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0007\u001a:\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u00022\u0010\u0010\n\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00070\u0003¨\u0006\u000b"}, d2 = {"getByteArray", "", "Lcn/sast/dataflow/interprocedural/check/callback/ICallCBImpl;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "strValueObject", "Lcn/sast/dataflow/interprocedural/analysis/V;", "getStringFromObject", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "obj", "corax-data-flow"})
/* loaded from: WStringKt.class */
public final class WStringKt {
    @Nullable
    public static final byte[] getByteArray(@NotNull ICallCBImpl<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCBImpl, @NotNull IValue strValueObject) {
        Intrinsics.checkNotNullParameter(iCallCBImpl, "<this>");
        Intrinsics.checkNotNullParameter(strValueObject, "strValueObject");
        IData<IValue> valueData = iCallCBImpl.getOut().getValueData(strValueObject, BuiltInModelT.Array);
        IArrayHeapKV iArrayHeapKV = valueData instanceof IArrayHeapKV ? (IArrayHeapKV) valueData : null;
        if (iArrayHeapKV != null) {
            return iArrayHeapKV.getByteArray(iCallCBImpl.getHf());
        }
        return null;
    }

    @NotNull
    public static final IOpCalculator<IValue> getStringFromObject(@NotNull ICallCBImpl<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCBImpl, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iCallCBImpl, "<this>");
        Intrinsics.checkNotNullParameter(iHeapValues, "obj");
        Type typeSoot_ByteType = G.v().soot_ByteType();
        Type typeV = ArrayType.v(typeSoot_ByteType, 1);
        Intrinsics.checkNotNull(typeSoot_ByteType);
        SootField coderField = SootUtilsKt.getOrMakeField("java.lang.String", "coder", typeSoot_ByteType);
        Intrinsics.checkNotNull(typeV);
        SootField valueField = SootUtilsKt.getOrMakeField("java.lang.String", "value", typeV);
        IHeapValues bytes = HeapFactoryKt.getValueField(iCallCBImpl, iHeapValues, valueField);
        IHeapValues coder = HeapFactoryKt.getValueField(iCallCBImpl, iHeapValues, coderField);
        IOpCalculator c = iCallCBImpl.getHf().resolveOp(iCallCBImpl.getEnv(), bytes, coder);
        c.resolve((v1, v2, v3) -> {
            return getStringFromObject$lambda$0(r1, v1, v2, v3);
        });
        IOpCalculator c2 = iCallCBImpl.getHf().resolveOp(iCallCBImpl.getEnv(), iHeapValues);
        c2.resolve((v1, v2, v3) -> {
            return getStringFromObject$lambda$2(r1, v1, v2, v3);
        });
        c.getRes().add(c2.getRes().build());
        c.getRes().add(iHeapValues);
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean getStringFromObject$lambda$0(ICallCBImpl $this_getStringFromObject, IOpCalculator $this$getStr, IHeapValues.Builder res, CompanionV[] companionVArr) {
        String str;
        Intrinsics.checkNotNullParameter($this$getStr, "$this$getStr");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV companionV = companionVArr[0];
        CompanionV coder = companionVArr[1];
        Byte byteValue = FactValuesKt.getByteValue((IValue) coder.getValue(), true);
        if (byteValue == null) {
            return false;
        }
        byte coderInt = byteValue.byteValue();
        byte[] byteArray = getByteArray($this_getStringFromObject, (IValue) companionV.getValue());
        if (byteArray == null) {
            return false;
        }
        if (coderInt == WString.Companion.getLATIN1_BYTE()) {
            str = new String(byteArray, Charsets.UTF_8);
        } else {
            str = new String(byteArray, Charsets.UTF_16);
        }
        String str2 = str;
        AbstractHeapFactory<IValue> hf = $this_getStringFromObject.getHf();
        HookEnv env = $this_getStringFromObject.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_getStringFromObject.getHf();
        Constant constantV = StringConstant.v(str2);
        Intrinsics.checkNotNullExpressionValue(constantV, "v(...)");
        res.add(hf.push((HeapValuesEnv) env, (HookEnv) hf2.newConstVal2(constantV, $this_getStringFromObject.getHf().getVg().getSTRING_TYPE())).dataSequenceToSeq(companionV).popHV());
        return true;
    }

    private static final boolean getStringFromObject$lambda$2(ICallCBImpl $this_getStringFromObject, IOpCalculator $this$getStr, IHeapValues.Builder res, CompanionV[] companionVArr) {
        IHeapValues it;
        Intrinsics.checkNotNullParameter($this$getStr, "$this$getStr");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV obj = companionVArr[0];
        Object valueData = ((IFact.Builder) $this_getStringFromObject.getOut()).getValueData(obj.getValue(), CheckerModelingKt.getKeyAttribute());
        ImmutableElementHashMap attributeMap = valueData instanceof ImmutableElementHashMap ? (ImmutableElementHashMap) valueData : null;
        IHeapValues.Builder set = $this_getStringFromObject.getHf().emptyBuilder();
        if (attributeMap != null && (it = attributeMap.get($this_getStringFromObject.getHf(), "str-fragment")) != null) {
            for (CompanionV v : it) {
                if (v.getValue() instanceof ConstVal) {
                    set.add(it);
                }
            }
        }
        res.add(set.build());
        return true;
    }
}
