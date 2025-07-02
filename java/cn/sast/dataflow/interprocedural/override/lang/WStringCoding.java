package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.ArraySpace;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.Local;
import soot.RefType;
import soot.SootField;
import soot.Type;
import soot.jimple.AnyNewExpr;
import soot.jimple.Constant;
import soot.jimple.Jimple;
import soot.jimple.NewArrayExpr;
import soot.jimple.NewExpr;

/* compiled from: WStringCoding.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� +2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001+B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010'\u001a\u00020(*\u00100)j\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`*H\u0016R\u001b\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\n \n*\u0004\u0018\u00010\u000f0\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u0014X\u0086D¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\n \n*\u0004\u0018\u00010\u00180\u0018¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\n \n*\u0004\u0018\u00010\u001d0\u001d¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010!\u001a\u00020\"¢\u0006\b\n��\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\"¢\u0006\b\n��\u001a\u0004\b&\u0010$¨\u0006,"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringCoding;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;)V", "sizeLocal", "Lsoot/Local;", "kotlin.jvm.PlatformType", "getSizeLocal", "()Lsoot/Local;", "Lsoot/Local;", "newValueExpr", "Lsoot/jimple/NewArrayExpr;", "getNewValueExpr", "()Lsoot/jimple/NewArrayExpr;", "Lsoot/jimple/NewArrayExpr;", "clzStringCodingResult", "", "getClzStringCodingResult", "()Ljava/lang/String;", "StringCodingResultType", "Lsoot/RefType;", "getStringCodingResultType", "()Lsoot/RefType;", "Lsoot/RefType;", "newExprStringCodingResult", "Lsoot/jimple/NewExpr;", "getNewExprStringCodingResult", "()Lsoot/jimple/NewExpr;", "Lsoot/jimple/NewExpr;", "stringCodingResultValueField", "Lsoot/SootField;", "getStringCodingResultValueField", "()Lsoot/SootField;", "stringCodingResultCoderField", "getStringCodingResultCoderField", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWStringCoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WStringCoding.kt\ncn/sast/dataflow/interprocedural/override/lang/WStringCoding\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,112:1\n44#2:113\n44#2:114\n*S KotlinDebug\n*F\n+ 1 WStringCoding.kt\ncn/sast/dataflow/interprocedural/override/lang/WStringCoding\n*L\n93#1:113\n100#1:114\n*E\n"})
/* loaded from: WStringCoding.class */
public final class WStringCoding implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final Local sizeLocal;
    private final NewArrayExpr newValueExpr;

    @NotNull
    private final String clzStringCodingResult;
    private final RefType StringCodingResultType;
    private final NewExpr newExprStringCodingResult;

    @NotNull
    private final SootField stringCodingResultValueField;

    @NotNull
    private final SootField stringCodingResultCoderField;

    /* compiled from: WStringCoding.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringCoding$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WStringCoding;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "corax-data-flow"})
    /* loaded from: WStringCoding$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WStringCoding v(@NotNull IVGlobal vg) {
            Intrinsics.checkNotNullParameter(vg, "vg");
            return new WStringCoding(vg);
        }
    }

    public WStringCoding(@NotNull IVGlobal vg) {
        Intrinsics.checkNotNullParameter(vg, "vg");
        this.sizeLocal = Jimple.v().newLocal("size", G.v().soot_IntType());
        this.newValueExpr = Jimple.v().newNewArrayExpr(vg.getBYTE_ARRAY_TYPE(), this.sizeLocal);
        this.clzStringCodingResult = "java.lang.StringCoding$Result";
        this.StringCodingResultType = RefType.v(this.clzStringCodingResult);
        this.newExprStringCodingResult = Jimple.v().newNewExpr(this.StringCodingResultType);
        this.stringCodingResultValueField = SootUtilsKt.getOrMakeField(this.clzStringCodingResult, "value", vg.getBYTE_ARRAY_TYPE());
        String str = this.clzStringCodingResult;
        Type typeSoot_ByteType = G.v().soot_ByteType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_ByteType, "soot_ByteType(...)");
        this.stringCodingResultCoderField = SootUtilsKt.getOrMakeField(str, "coder", typeSoot_ByteType);
    }

    public final Local getSizeLocal() {
        return this.sizeLocal;
    }

    public final NewArrayExpr getNewValueExpr() {
        return this.newValueExpr;
    }

    @NotNull
    public final String getClzStringCodingResult() {
        return this.clzStringCodingResult;
    }

    public final RefType getStringCodingResultType() {
        return this.StringCodingResultType;
    }

    public final NewExpr getNewExprStringCodingResult() {
        return this.newExprStringCodingResult;
    }

    @NotNull
    public final SootField getStringCodingResultValueField() {
        return this.stringCodingResultValueField;
    }

    @NotNull
    public final SootField getStringCodingResultCoderField() {
        return this.stringCodingResultCoderField;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        $this$register.evalCall("<java.lang.StringCoding: byte[] encode(byte,byte[])>", (v1) -> {
            return register$lambda$1(r2, v1);
        });
        $this$register.evalCall("<java.lang.StringCoding: java.lang.StringCoding$Result decode(byte[],int,int)>", (v1) -> {
            return register$lambda$3(r2, v1);
        });
    }

    private static final Unit register$lambda$1(WStringCoding this$0, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues coderP = $this$evalCall.arg(0);
        IHeapValues valP = $this$evalCall.arg(1);
        IOpCalculator encodeOp = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), coderP, valP);
        encodeOp.resolve((v2, v3, v4) -> {
            return register$lambda$1$lambda$0(r1, r2, v2, v3, v4);
        });
        encodeOp.putSummaryIfNotConcrete($this$evalCall.getHf().getVg().getBYTE_ARRAY_TYPE(), "return");
        $this$evalCall.setReturn(encodeOp.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_evalCall, WStringCoding this$0, IOpCalculator $this$encode, IHeapValues.Builder ret, CompanionV[] companionVArr) {
        byte[] array;
        String str;
        Intrinsics.checkNotNullParameter($this$encode, "$this$encode");
        Intrinsics.checkNotNullParameter(ret, "ret");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV coder = companionVArr[0];
        CompanionV companionV = companionVArr[1];
        Byte byteValue = FactValuesKt.getByteValue((IValue) coder.getValue(), true);
        if (byteValue == null) {
            return false;
        }
        byte coderInt = byteValue.byteValue();
        if (WStringKt.getByteArray($this_evalCall, (IValue) companionV.getValue()) == null) {
            return false;
        }
        IData valueData = $this_evalCall.getOut().getValueData(companionV.getValue(), BuiltInModelT.Array);
        IArrayHeapKV arrayData = valueData instanceof IArrayHeapKV ? (IArrayHeapKV) valueData : null;
        if (arrayData == null || (array = arrayData.getByteArray($this_evalCall.getHf())) == null) {
            return false;
        }
        if (coderInt == WString.Companion.getLATIN1_BYTE()) {
            str = new String(array, Charsets.UTF_8);
        } else {
            str = new String(array, Charsets.UTF_16);
        }
        String str2 = str;
        byte[] returnArr = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(returnArr, "getBytes(...)");
        AbstractHeapFactory<IValue> hf = $this_evalCall.getHf();
        HookEnv env = $this_evalCall.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_evalCall.getHf();
        AnyNewExprEnv newEnv = $this_evalCall.getNewEnv();
        AnyNewExpr anyNewExpr = this$0.newValueExpr;
        Intrinsics.checkNotNullExpressionValue(anyNewExpr, "newValueExpr");
        JOperatorV<IValue> jOperatorVPush = hf.push((HeapValuesEnv) env, (HookEnv) hf2.anyNewVal(newEnv, anyNewExpr));
        AnyNewExpr anyNewExpr2 = this$0.newValueExpr;
        Intrinsics.checkNotNullExpressionValue(anyNewExpr2, "newValueExpr");
        CompanionV newValue = jOperatorVPush.markOfNewExpr(anyNewExpr2).pop();
        ret.add(newValue);
        IHeapValues arraySize = $this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Integer.valueOf(returnArr.length))).markArraySizeOf(companionV).popHV();
        ArraySpace newArray = ArraySpace.Companion.v($this_evalCall.getHf(), $this_evalCall.getEnv(), (CompanionV<IValue>) companionV, ArraysKt.toTypedArray(returnArr), $this_evalCall.getHf().getVg().getBYTE_ARRAY_TYPE(), (IHeapValues<IValue>) arraySize);
        $this_evalCall.getOut().setValueData($this_evalCall.getEnv(), newValue.getValue(), BuiltInModelT.Array, newArray);
        return true;
    }

    private static final Unit register$lambda$3(WStringCoding this$0, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues baP = $this$evalCall.arg(0);
        IHeapValues offP = $this$evalCall.arg(1);
        IHeapValues lenP = $this$evalCall.arg(2);
        IOpCalculator decodeOp = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), baP, offP, lenP);
        decodeOp.resolve((v2, v3, v4) -> {
            return register$lambda$3$lambda$2(r1, r2, v2, v3, v4);
        });
        Type type = this$0.StringCodingResultType;
        Intrinsics.checkNotNullExpressionValue(type, "StringCodingResultType");
        decodeOp.putSummaryIfNotConcrete(type, "return");
        $this$evalCall.setReturn(decodeOp.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$3$lambda$2(CalleeCBImpl.EvalCall $this_evalCall, WStringCoding this$0, IOpCalculator $this$encode, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$encode, "$this$encode");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV companionV = companionVArr[0];
        CompanionV off = companionVArr[1];
        CompanionV len = companionVArr[2];
        Integer intValue = FactValuesKt.getIntValue((IValue) off.getValue(), true);
        if (intValue == null) {
            return false;
        }
        int offInt = intValue.intValue();
        Integer intValue2 = FactValuesKt.getIntValue((IValue) len.getValue(), true);
        if (intValue2 == null) {
            return false;
        }
        int lenInt = intValue2.intValue();
        byte[] byteArray = WStringKt.getByteArray($this_evalCall, (IValue) companionV.getValue());
        if (byteArray == null) {
            return false;
        }
        try {
            byte[] newValueArray = new String(byteArray, offInt, lenInt, Charsets.UTF_8).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(newValueArray, "getBytes(...)");
            AbstractHeapFactory<IValue> hf = $this_evalCall.getHf();
            HookEnv env = $this_evalCall.getEnv();
            AbstractHeapFactory<IValue> hf2 = $this_evalCall.getHf();
            AnyNewExprEnv newEnv = $this_evalCall.getNewEnv();
            AnyNewExpr anyNewExpr = this$0.newExprStringCodingResult;
            Intrinsics.checkNotNullExpressionValue(anyNewExpr, "newExprStringCodingResult");
            JOperatorV<IValue> jOperatorVPush = hf.push((HeapValuesEnv) env, (HookEnv) hf2.anyNewVal(newEnv, anyNewExpr));
            AnyNewExpr anyNewExpr2 = this$0.newExprStringCodingResult;
            Intrinsics.checkNotNullExpressionValue(anyNewExpr2, "newExprStringCodingResult");
            CompanionV newResult = jOperatorVPush.markOfNewExpr(anyNewExpr2).pop();
            AbstractHeapFactory<IValue> hf3 = $this_evalCall.getHf();
            HookEnv env2 = $this_evalCall.getEnv();
            AbstractHeapFactory<IValue> hf4 = $this_evalCall.getHf();
            AnyNewExprEnv newEnv2 = $this_evalCall.getNewEnv();
            AnyNewExpr anyNewExpr3 = this$0.newValueExpr;
            Intrinsics.checkNotNullExpressionValue(anyNewExpr3, "newValueExpr");
            JOperatorV<IValue> jOperatorVPush2 = hf3.push((HeapValuesEnv) env2, (HookEnv) hf4.anyNewVal(newEnv2, anyNewExpr3));
            AnyNewExpr anyNewExpr4 = this$0.newValueExpr;
            Intrinsics.checkNotNullExpressionValue(anyNewExpr4, "newValueExpr");
            CompanionV newValue = jOperatorVPush2.markOfNewExpr(anyNewExpr4).pop();
            AbstractHeapFactory<IValue> hf5 = $this_evalCall.getHf();
            HookEnv env3 = $this_evalCall.getEnv();
            AbstractHeapFactory<IValue> hf6 = $this_evalCall.getHf();
            Constant latin1 = WString.Companion.getLATIN1();
            Type typeSoot_ByteType = G.v().soot_ByteType();
            Intrinsics.checkNotNullExpressionValue(typeSoot_ByteType, "soot_ByteType(...)");
            CompanionV newCoder = JOperatorV.DefaultImpls.markOfConstant$default(hf5.push((HeapValuesEnv) env3, (HookEnv) hf6.newConstVal2(latin1, typeSoot_ByteType)), WString.Companion.getLATIN1(), null, 2, null).pop();
            IHeapValues arraySize = $this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Integer.valueOf(newValueArray.length))).markArraySizeOf(companionV).popHV();
            ArraySpace newArray = ArraySpace.Companion.v($this_evalCall.getHf(), $this_evalCall.getEnv(), (CompanionV<IValue>) companionV, ArraysKt.toTypedArray(newValueArray), $this_evalCall.getHf().getVg().getBYTE_ARRAY_TYPE(), (IHeapValues<IValue>) arraySize);
            $this_evalCall.getOut().setValueData($this_evalCall.getEnv(), newValue.getValue(), BuiltInModelT.Array, newArray);
            IFact.Builder<IValue> out = $this_evalCall.getOut();
            Intrinsics.checkNotNull(out, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder");
            HookEnv env4 = $this_evalCall.getEnv();
            IHeapValues<IValue> iHeapValuesPlus = $this_evalCall.getHf().empty().plus((CompanionV<IValue>) newResult);
            FieldUtil fieldUtil = FieldUtil.INSTANCE;
            SootField field$iv = this$0.stringCodingResultValueField;
            ((PointsToGraphBuilder) out).assignField((HeapValuesEnv) env4, (IHeapValues) iHeapValuesPlus, (JFieldType) new JSootFieldType(field$iv), (IHeapValues) $this_evalCall.getHf().empty().plus((CompanionV<IValue>) newValue), false);
            IFact.Builder<IValue> out2 = $this_evalCall.getOut();
            Intrinsics.checkNotNull(out2, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder");
            HookEnv env5 = $this_evalCall.getEnv();
            IHeapValues<IValue> iHeapValuesPlus2 = $this_evalCall.getHf().empty().plus((CompanionV<IValue>) newResult);
            FieldUtil fieldUtil2 = FieldUtil.INSTANCE;
            SootField field$iv2 = this$0.stringCodingResultCoderField;
            ((PointsToGraphBuilder) out2).assignField((HeapValuesEnv) env5, (IHeapValues) iHeapValuesPlus2, (JFieldType) new JSootFieldType(field$iv2), (IHeapValues) $this_evalCall.getHf().empty().plus((CompanionV<IValue>) newCoder), false);
            res.add(newResult);
            return true;
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }
}
