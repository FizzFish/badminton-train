package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import soot.ByteType;
import soot.DoubleType;
import soot.FloatType;
import soot.G;
import soot.IntType;
import soot.IntegerType;
import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.ShortType;
import soot.SootField;
import soot.Type;
import soot.jimple.AnyNewExpr;
import soot.jimple.Constant;
import soot.jimple.Jimple;
import soot.jimple.NewExpr;
import soot.jimple.NumericConstant;
import soot.jimple.StringConstant;

/* compiled from: WNumber.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018�� \u001d2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J:\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J4\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\rJ,\u0010\u0019\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\rJ(\u0010\u001a\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ(\u0010\u001b\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u001c\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u001e"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WNumber;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "registerToValue", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "clzName", "", "valueFieldType", "Lsoot/PrimType;", "valueField", "Lsoot/SootField;", "toType", "parseString", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;", "hint", "sIdx", "", "radixIdx", "resType", "parseStringFloating", "registerValueOf", "registerEquals", "register", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWNumber.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WNumber.kt\ncn/sast/dataflow/interprocedural/override/lang/WNumber\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,263:1\n44#2:264\n44#2:265\n44#2:266\n*S KotlinDebug\n*F\n+ 1 WNumber.kt\ncn/sast/dataflow/interprocedural/override/lang/WNumber\n*L\n62#1:264\n146#1:265\n178#1:266\n*E\n"})
/* loaded from: WNumber.class */
public final class WNumber implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: WNumber.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WNumber$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WNumber;", "corax-data-flow"})
    /* loaded from: WNumber$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WNumber v() {
            return new WNumber();
        }
    }

    private final void registerToValue(ACheckCallAnalysis $this$registerToValue, String clzName, PrimType valueFieldType, SootField valueField, PrimType toType) {
        Function1 handle = (v2) -> {
            return registerToValue$lambda$1(r0, r1, v2);
        };
        $this$registerToValue.evalCall("<" + clzName + ": " + toType + " " + toType + "Value()>", handle);
        String string = toType.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        $this$registerToValue.evalCall("<" + clzName + ": " + toType + " to" + StringsKt.capitalize(string) + "()>", handle);
    }

    private static final Unit registerToValue$lambda$1(SootField $valueField, PrimType $toType, CalleeCBImpl.EvalCall evalCall) {
        Intrinsics.checkNotNullParameter(evalCall, "<this>");
        IFact.Builder<IValue> out = evalCall.getOut();
        HookEnv env = evalCall.getEnv();
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        IFact.Builder.DefaultImpls.getField$default(out, env, "value", -1, new JSootFieldType($valueField), false, 16, null);
        IHeapValues value = evalCall.getOut().getTargets("value");
        IOpCalculator valueOp = evalCall.getHf().resolveOp(evalCall.getEnv(), value);
        valueOp.resolve((v2, v3, v4) -> {
            return registerToValue$lambda$1$lambda$0(r1, r2, v2, v3, v4);
        });
        valueOp.putSummaryIfNotConcrete((Type) $toType, evalCall.getHf().getVg().getRETURN_LOCAL());
        IFact.Builder.DefaultImpls.assignNewExpr$default(evalCall.getOut(), evalCall.getEnv(), evalCall.getHf().getVg().getRETURN_LOCAL(), valueOp.getRes().build(), false, 8, null);
        evalCall.getOut().kill("value");
        return Unit.INSTANCE;
    }

    private static final boolean registerToValue$lambda$1$lambda$0(PrimType $toType, CalleeCBImpl.EvalCall $this, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        ConstVal constVal = op instanceof ConstVal ? (ConstVal) op : null;
        Constant v = constVal != null ? constVal.getV() : null;
        NumericConstant numericConstant = v instanceof NumericConstant ? (NumericConstant) v : null;
        if (numericConstant == null) {
            return false;
        }
        NumericConstant nc = numericConstant;
        Constant c = SootUtilsKt.castTo(nc, (Type) $toType);
        if (c == null) {
            return false;
        }
        res.add($this.getHf().push((HeapValuesEnv) $this.getEnv(), (HookEnv) $this.getHf().newConstVal2(c, (Type) $toType)).markOfCastTo($toType).pop());
        return true;
    }

    @NotNull
    public final IOpCalculator<IValue> parseString(@NotNull CalleeCBImpl.EvalCall $this$parseString, @NotNull String hint, int sIdx, int radixIdx, @NotNull PrimType resType) {
        Intrinsics.checkNotNullParameter($this$parseString, "<this>");
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(resType, "resType");
        IHeapValues value = $this$parseString.arg(sIdx);
        IHeapValues radixParam = $this$parseString.arg(radixIdx);
        IOpCalculator valueOp = $this$parseString.getHf().resolveOp($this$parseString.getEnv(), value, radixParam);
        valueOp.resolve((v3, v4, v5) -> {
            return parseString$lambda$2(r1, r2, r3, v3, v4, v5);
        });
        valueOp.putSummaryIfNotConcrete((Type) resType, "return");
        return valueOp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean parseString$lambda$2(CalleeCBImpl.EvalCall $this_parseString, PrimType $resType, String $hint, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        String strDecodeToString;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        CompanionV cradix = companionVArr[1];
        IValue str = (IValue) cop.getValue();
        IValue radix = (IValue) cradix.getValue();
        Integer intValue = FactValuesKt.getIntValue(radix, true);
        if (intValue == null) {
            return false;
        }
        int radixNm = intValue.intValue();
        if (str instanceof ConstVal) {
            StringConstant v = ((ConstVal) str).getV();
            StringConstant stringConstant = v instanceof StringConstant ? v : null;
            if (stringConstant == null) {
                return false;
            }
            strDecodeToString = stringConstant.value;
            if (strDecodeToString == null) {
                return false;
            }
        } else {
            byte[] byteArray = WStringKt.getByteArray($this_parseString, str);
            if (byteArray == null) {
                return false;
            }
            strDecodeToString = StringsKt.decodeToString(byteArray);
        }
        String sc = strDecodeToString;
        try {
            Constant constantCvtNumericConstant = SootUtilsKt.cvtNumericConstant(sc, radixNm, (Type) $resType);
            if (constantCvtNumericConstant == null) {
                return false;
            }
            res.add($this_parseString.getHf().push((HeapValuesEnv) $this_parseString.getEnv(), (HookEnv) $this_parseString.getHf().newConstVal2(constantCvtNumericConstant, (Type) $resType)).markOfParseString($hint, cop).pop());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @NotNull
    public final IOpCalculator<IValue> parseStringFloating(@NotNull CalleeCBImpl.EvalCall $this$parseStringFloating, @NotNull String hint, int sIdx, @NotNull PrimType resType) {
        Intrinsics.checkNotNullParameter($this$parseStringFloating, "<this>");
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(resType, "resType");
        IHeapValues value = $this$parseStringFloating.arg(sIdx);
        IOpCalculator valueOp = $this$parseStringFloating.getHf().resolveOp($this$parseStringFloating.getEnv(), value);
        valueOp.resolve((v3, v4, v5) -> {
            return parseStringFloating$lambda$3(r1, r2, r3, v3, v4, v5);
        });
        valueOp.putSummaryIfNotConcrete((Type) resType, "return");
        return valueOp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean parseStringFloating$lambda$3(CalleeCBImpl.EvalCall $this_parseStringFloating, PrimType $resType, String $hint, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        String strDecodeToString;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue str = (IValue) cop.getValue();
        if (str instanceof ConstVal) {
            StringConstant v = ((ConstVal) str).getV();
            StringConstant stringConstant = v instanceof StringConstant ? v : null;
            if (stringConstant == null) {
                return false;
            }
            strDecodeToString = stringConstant.value;
            if (strDecodeToString == null) {
                return false;
            }
        } else {
            byte[] byteArray = WStringKt.getByteArray($this_parseStringFloating, str);
            if (byteArray == null) {
                return false;
            }
            strDecodeToString = StringsKt.decodeToString(byteArray);
        }
        String sc = strDecodeToString;
        try {
            Constant constantCvtNumericConstant = SootUtilsKt.cvtNumericConstant(sc, -1, (Type) $resType);
            if (constantCvtNumericConstant == null) {
                return false;
            }
            res.add($this_parseStringFloating.getHf().push((HeapValuesEnv) $this_parseStringFloating.getEnv(), (HookEnv) $this_parseStringFloating.getHf().newConstVal2(constantCvtNumericConstant, (Type) $resType)).markOfParseString($hint, cop).pop());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public final void registerValueOf(@NotNull ACheckCallAnalysis $this$registerValueOf, @NotNull String clzName, @NotNull PrimType valueFieldType) {
        Intrinsics.checkNotNullParameter($this$registerValueOf, "<this>");
        Intrinsics.checkNotNullParameter(clzName, "clzName");
        Intrinsics.checkNotNullParameter(valueFieldType, "valueFieldType");
        SootField valueField = cn.sast.dataflow.util.SootUtilsKt.getOrMakeField(clzName, "value", (Type) valueFieldType);
        NewExpr newExpr = Jimple.v().newNewExpr(RefType.v(clzName));
        if (!(valueFieldType instanceof IntegerType) && !(valueFieldType instanceof LongType) && !(valueFieldType instanceof FloatType) && !(valueFieldType instanceof DoubleType)) {
            throw new IllegalStateException(("error type of " + valueFieldType).toString());
        }
        $this$registerValueOf.evalCall("<" + clzName + ": " + clzName + " valueOf(" + valueFieldType + ")>", (v2) -> {
            return registerValueOf$lambda$4(r2, r3, v2);
        });
        $this$registerValueOf.registerWrapper("<" + clzName + ": " + clzName + " valueOf(java.lang.String)>", true);
        String string = valueFieldType.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        $this$registerValueOf.registerWrapper("<" + clzName + ": " + valueFieldType + " parse" + StringsKt.capitalize(string) + "(java.lang.String)>", true);
        if ((valueFieldType instanceof FloatType) || (valueFieldType instanceof DoubleType)) {
            String string2 = valueFieldType.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            $this$registerValueOf.evalCall("<" + clzName + ": " + valueFieldType + " parse" + StringsKt.capitalize(string2) + "(java.lang.String)>", (v3) -> {
                return registerValueOf$lambda$5(r2, r3, r4, v3);
            });
        } else {
            String string3 = valueFieldType.toString();
            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
            $this$registerValueOf.evalCall("<" + clzName + ": " + valueFieldType + " parse" + StringsKt.capitalize(string3) + "(java.lang.String,int)>", (v3) -> {
                return registerValueOf$lambda$6(r2, r3, r4, v3);
            });
            $this$registerValueOf.evalCall("<" + clzName + ": " + clzName + " valueOf(java.lang.String,int)>", (v5) -> {
                return registerValueOf$lambda$7(r2, r3, r4, r5, r6, v5);
            });
        }
    }

    private static final Unit registerValueOf$lambda$4(NewExpr $newExpr, SootField $valueField, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        AbstractHeapFactory<IValue> hf = $this$evalCall.getHf();
        HookEnv env = $this$evalCall.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this$evalCall.getHf();
        AnyNewExprEnv newEnv = $this$evalCall.getNewEnv();
        Intrinsics.checkNotNull($newExpr);
        IHeapValues returnValue = hf.push((HeapValuesEnv) env, (HookEnv) hf2.anyNewVal(newEnv, (AnyNewExpr) $newExpr)).markOfNewExpr((AnyNewExpr) $newExpr).popHV();
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$evalCall.getOut(), $this$evalCall.getEnv(), $this$evalCall.getHf().getVg().getRETURN_LOCAL(), returnValue, false, 8, null);
        IFact.Builder<IValue> out = $this$evalCall.getOut();
        HookEnv env2 = $this$evalCall.getEnv();
        String return_local = $this$evalCall.getHf().getVg().getRETURN_LOCAL();
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        IFact.Builder.DefaultImpls.setField$default(out, env2, return_local, new JSootFieldType($valueField), 0, false, 16, null);
        return Unit.INSTANCE;
    }

    private static final Unit registerValueOf$lambda$5(WNumber this$0, String $clzName, PrimType $valueFieldType, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IOpCalculator eval = this$0.parseStringFloating($this$evalCall, $clzName, 0, $valueFieldType);
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$evalCall.getOut(), $this$evalCall.getEnv(), $this$evalCall.getHf().getVg().getRETURN_LOCAL(), eval.getRes().build(), false, 8, null);
        return Unit.INSTANCE;
    }

    private static final Unit registerValueOf$lambda$6(WNumber this$0, String $clzName, PrimType $valueFieldType, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IOpCalculator eval = this$0.parseString($this$evalCall, $clzName, 0, 1, $valueFieldType);
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$evalCall.getOut(), $this$evalCall.getEnv(), $this$evalCall.getHf().getVg().getRETURN_LOCAL(), eval.getRes().build(), false, 8, null);
        return Unit.INSTANCE;
    }

    private static final Unit registerValueOf$lambda$7(NewExpr $newExpr, WNumber this$0, String $clzName, PrimType $valueFieldType, SootField $valueField, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        AbstractHeapFactory<IValue> hf = $this$evalCall.getHf();
        HookEnv env = $this$evalCall.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this$evalCall.getHf();
        AnyNewExprEnv newEnv = $this$evalCall.getNewEnv();
        Intrinsics.checkNotNull($newExpr);
        IHeapValues returnValue = hf.push((HeapValuesEnv) env, (HookEnv) hf2.anyNewVal(newEnv, (AnyNewExpr) $newExpr)).markOfNewExpr((AnyNewExpr) $newExpr).popHV();
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$evalCall.getOut(), $this$evalCall.getEnv(), $this$evalCall.getHf().getVg().getRETURN_LOCAL(), returnValue, false, 8, null);
        IOpCalculator eval = this$0.parseString($this$evalCall, $clzName, 0, 1, $valueFieldType);
        IFact.Builder<IValue> out = $this$evalCall.getOut();
        HookEnv env2 = $this$evalCall.getEnv();
        String return_local = $this$evalCall.getHf().getVg().getRETURN_LOCAL();
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        out.setFieldNew(env2, return_local, new JSootFieldType($valueField), eval.getRes().build());
        return Unit.INSTANCE;
    }

    public final void registerEquals(@NotNull ACheckCallAnalysis $this$registerEquals, @NotNull String clzName, @NotNull SootField valueField) {
        Intrinsics.checkNotNullParameter($this$registerEquals, "<this>");
        Intrinsics.checkNotNullParameter(clzName, "clzName");
        Intrinsics.checkNotNullParameter(valueField, "valueField");
        RefType.v(clzName);
        $this$registerEquals.registerWrapper("<" + clzName + ": boolean equals(java.lang.Object)>", false);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        List<Pair> classes = CollectionsKt.listOf(new Pair[]{TuplesKt.to("java.lang.Integer", G.v().soot_IntType()), TuplesKt.to("java.lang.Long", G.v().soot_LongType()), TuplesKt.to("java.lang.Short", G.v().soot_ShortType()), TuplesKt.to("java.lang.Byte", G.v().soot_ByteType()), TuplesKt.to("java.lang.Float", G.v().soot_FloatType()), TuplesKt.to("java.lang.Double", G.v().soot_DoubleType())});
        for (Pair pair : classes) {
            String c = (String) pair.component1();
            Type type = (PrimType) pair.component2();
            $this$register.registerClassAllWrapper(c);
            Intrinsics.checkNotNull(type);
            SootField valueField = cn.sast.dataflow.util.SootUtilsKt.getOrMakeField(c, "value", type);
            ByteType byteTypeSoot_ByteType = G.v().soot_ByteType();
            Intrinsics.checkNotNullExpressionValue(byteTypeSoot_ByteType, "soot_ByteType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) byteTypeSoot_ByteType);
            ShortType shortTypeSoot_ShortType = G.v().soot_ShortType();
            Intrinsics.checkNotNullExpressionValue(shortTypeSoot_ShortType, "soot_ShortType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) shortTypeSoot_ShortType);
            IntType intTypeSoot_IntType = G.v().soot_IntType();
            Intrinsics.checkNotNullExpressionValue(intTypeSoot_IntType, "soot_IntType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) intTypeSoot_IntType);
            LongType longTypeSoot_LongType = G.v().soot_LongType();
            Intrinsics.checkNotNullExpressionValue(longTypeSoot_LongType, "soot_LongType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) longTypeSoot_LongType);
            FloatType floatTypeSoot_FloatType = G.v().soot_FloatType();
            Intrinsics.checkNotNullExpressionValue(floatTypeSoot_FloatType, "soot_FloatType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) floatTypeSoot_FloatType);
            DoubleType doubleTypeSoot_DoubleType = G.v().soot_DoubleType();
            Intrinsics.checkNotNullExpressionValue(doubleTypeSoot_DoubleType, "soot_DoubleType(...)");
            registerToValue($this$register, c, type, valueField, (PrimType) doubleTypeSoot_DoubleType);
            registerValueOf($this$register, c, type);
            registerEquals($this$register, c, valueField);
        }
    }
}
