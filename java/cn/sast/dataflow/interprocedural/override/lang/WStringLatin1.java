package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.Type;
import soot.jimple.Constant;
import soot.jimple.IntConstant;

/* compiled from: WStringLatin1.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \n2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringLatin1;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WStringLatin1.class */
public final class WStringLatin1 implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: WStringLatin1.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringLatin1$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WStringLatin1;", "corax-data-flow"})
    /* loaded from: WStringLatin1$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WStringLatin1 v() {
            return new WStringLatin1();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.lang.StringLatin1: char charAt(byte[],int)>", true));
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.lang.StringLatin1: char charAt(byte[],int)>", true));
        $this$register.evalCall("<java.lang.StringLatin1: int indexOf(byte[],int,int)>", WStringLatin1::register$lambda$1);
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.lang.StringLatin1: java.lang.String newString(byte[],int,int)>", true));
        $this$register.evalCall("<java.lang.StringLatin1: boolean equals(byte[],byte[])>", WStringLatin1::register$lambda$3);
        $this$register.evalCall("<java.lang.StringLatin1: int hashCode(byte[])>", WStringLatin1::register$lambda$5);
    }

    private static final Unit register$lambda$1(CalleeCBImpl.EvalCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IHeapValues valueP = $this$ret.arg(0);
        IHeapValues chP = $this$ret.arg(1);
        IHeapValues fromIndexP = $this$ret.arg(2);
        IOpCalculator valueObjectOp = $this$ret.getHf().resolveOp($this$ret.getEnv(), valueP, chP, fromIndexP);
        valueObjectOp.resolve((v1, v2, v3) -> {
            return register$lambda$1$lambda$0(r1, v1, v2, v3);
        });
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        valueObjectOp.putSummaryIfNotConcrete(typeSoot_IntType, "return");
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$ret.getOut(), $this$ret.getEnv(), $this$ret.getHf().getVg().getRETURN_LOCAL(), valueObjectOp.getRes().build(), false, 8, null);
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_ret, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV value = companionVArr[0];
        CompanionV ch = companionVArr[1];
        CompanionV fromIndex = companionVArr[2];
        Integer intValue = FactValuesKt.getIntValue((IValue) ch.getValue(), true);
        if (intValue == null) {
            return false;
        }
        int chV = intValue.intValue();
        if (!WStringLatin1Kt.canEncode(chV)) {
            AbstractHeapFactory<IValue> hf = $this_ret.getHf();
            HookEnv env = $this_ret.getEnv();
            AbstractHeapFactory<IValue> hf2 = $this_ret.getHf();
            Constant constantV = IntConstant.v(-1);
            Intrinsics.checkNotNullExpressionValue(constantV, "v(...)");
            Type typeSoot_IntType = G.v().soot_IntType();
            Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
            res.add(hf.push((HeapValuesEnv) env, (HookEnv) hf2.newConstVal2(constantV, typeSoot_IntType)).markOfReturnValueOfMethod($this_ret).pop());
            return true;
        }
        IData valueData = $this_ret.getOut().getValueData(value.getValue(), BuiltInModelT.Array);
        IArrayHeapKV iArrayHeapKV = valueData instanceof IArrayHeapKV ? (IArrayHeapKV) valueData : null;
        if (iArrayHeapKV == null) {
            return false;
        }
        IArrayHeapKV arr = iArrayHeapKV;
        IValue[] arrRaw = (IValue[]) arr.getArray($this_ret.getHf());
        if (arrRaw == null) {
            return false;
        }
        int max = arrRaw.length;
        Integer intValue2 = FactValuesKt.getIntValue((IValue) fromIndex.getValue(), true);
        if (intValue2 == null) {
            return false;
        }
        int fromIndexV = intValue2.intValue();
        if (fromIndexV < 0) {
            fromIndexV = 0;
        } else if (fromIndexV >= max) {
            AbstractHeapFactory<IValue> hf3 = $this_ret.getHf();
            HookEnv env2 = $this_ret.getEnv();
            AbstractHeapFactory<IValue> hf4 = $this_ret.getHf();
            Constant constantV2 = IntConstant.v(-1);
            Intrinsics.checkNotNullExpressionValue(constantV2, "v(...)");
            Type typeSoot_IntType2 = G.v().soot_IntType();
            Intrinsics.checkNotNullExpressionValue(typeSoot_IntType2, "soot_IntType(...)");
            res.add(hf3.push((HeapValuesEnv) env2, (HookEnv) hf4.newConstVal2(constantV2, typeSoot_IntType2)).markOfReturnValueOfMethod($this_ret).pop());
            return true;
        }
        if (max - fromIndexV > 100) {
            return false;
        }
        byte c = (byte) chV;
        for (int i = fromIndexV; i < max; i++) {
            Byte byteValue$default = FactValuesKt.getByteValue$default(arrRaw[i], false, 1, null);
            if (byteValue$default == null) {
                return false;
            }
            byte ca = byteValue$default.byteValue();
            if (ca == c) {
                AbstractHeapFactory<IValue> hf5 = $this_ret.getHf();
                HookEnv env3 = $this_ret.getEnv();
                AbstractHeapFactory<IValue> hf6 = $this_ret.getHf();
                Constant constantV3 = IntConstant.v(i);
                Intrinsics.checkNotNullExpressionValue(constantV3, "v(...)");
                Type typeSoot_IntType3 = G.v().soot_IntType();
                Intrinsics.checkNotNullExpressionValue(typeSoot_IntType3, "soot_IntType(...)");
                res.add(hf5.push((HeapValuesEnv) env3, (HookEnv) hf6.newConstVal2(constantV3, typeSoot_IntType3)).markOfReturnValueOfMethod($this_ret).pop());
                return true;
            }
        }
        AbstractHeapFactory<IValue> hf7 = $this_ret.getHf();
        HookEnv env4 = $this_ret.getEnv();
        AbstractHeapFactory<IValue> hf8 = $this_ret.getHf();
        Constant constantV4 = IntConstant.v(-1);
        Intrinsics.checkNotNullExpressionValue(constantV4, "v(...)");
        Type typeSoot_IntType4 = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType4, "soot_IntType(...)");
        res.add(hf7.push((HeapValuesEnv) env4, (HookEnv) hf8.newConstVal2(constantV4, typeSoot_IntType4)).markOfReturnValueOfMethod($this_ret).pop());
        return true;
    }

    private static final Unit register$lambda$3(CalleeCBImpl.EvalCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IHeapValues valueP = $this$ret.arg(0);
        IHeapValues otherP = $this$ret.arg(1);
        IOpCalculator equalsOp = $this$ret.getHf().resolveOp($this$ret.getEnv(), valueP, otherP);
        equalsOp.resolve((v1, v2, v3) -> {
            return register$lambda$3$lambda$2(r1, v1, v2, v3);
        });
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
        equalsOp.putSummaryIfNotConcrete(typeSoot_BooleanType, "return");
        $this$ret.setReturn(equalsOp.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$3$lambda$2(CalleeCBImpl.EvalCall $this_ret, IOpCalculator $this$valueCmp, IHeapValues.Builder ret, CompanionV[] companionVArr) {
        byte[] strB;
        Intrinsics.checkNotNullParameter($this$valueCmp, "$this$valueCmp");
        Intrinsics.checkNotNullParameter(ret, "ret");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV lop = companionVArr[0];
        CompanionV rop = companionVArr[1];
        if (!Intrinsics.areEqual(lop.getValue(), rop.getValue())) {
            byte[] strA = WStringKt.getByteArray($this_ret, (IValue) lop.getValue());
            if (strA == null || (strB = WStringKt.getByteArray($this_ret, (IValue) rop.getValue())) == null) {
                return false;
            }
            ret.add($this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) $this_ret.getHf().toConstVal(Boolean.valueOf(Arrays.equals(strA, strB)))).pop());
            return true;
        }
        $this$valueCmp.getRes().add($this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) $this_ret.getHf().toConstVal(true)).popHV());
        return true;
    }

    private static final Unit register$lambda$5(CalleeCBImpl.EvalCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IHeapValues valueP = $this$ret.arg(0);
        IOpCalculator valueOp = $this$ret.getHf().resolveOp($this$ret.getEnv(), valueP);
        valueOp.resolve((v1, v2, v3) -> {
            return register$lambda$5$lambda$4(r1, v1, v2, v3);
        });
        valueOp.putSummaryIfNotConcrete($this$ret.getHf().getVg().getBYTE_ARRAY_TYPE(), "return");
        $this$ret.setReturn(valueOp.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$5$lambda$4(CalleeCBImpl.EvalCall $this_ret, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV value = companionVArr[0];
        byte[] bArray = WStringKt.getByteArray($this_ret, (IValue) value.getValue());
        if (bArray == null) {
            return false;
        }
        int hash = new String(bArray, Charsets.UTF_8).hashCode();
        res.add($this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) $this_ret.getHf().toConstVal(Integer.valueOf(hash))).markOfStringLatin1Hash(value).pop());
        return true;
    }
}
