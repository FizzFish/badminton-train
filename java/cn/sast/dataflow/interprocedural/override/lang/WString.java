package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
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
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.ArraySpace;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import soot.ArrayType;
import soot.ByteType;
import soot.G;
import soot.Local;
import soot.RefType;
import soot.SootField;
import soot.Type;
import soot.jimple.AnyNewExpr;
import soot.jimple.Constant;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.NewArrayExpr;

/* compiled from: WString.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� .2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001.B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010*\u001a\u00020+*\u00100,j\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`-H\u0016R\u001b\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u001b\u0010\f\u001a\n \b*\u0004\u0018\u00010\r0\r¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\n \b*\u0004\u0018\u00010\u00120\u0012¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u0017¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u0017¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u0019R\u001b\u0010\u001e\u001a\n \b*\u0004\u0018\u00010\u001f0\u001f¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\n \b*\u0004\u0018\u00010$0$¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\n \b*\u0004\u0018\u00010$0$¢\u0006\n\n\u0002\u0010'\u001a\u0004\b)\u0010&¨\u0006/"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WString;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "classType", "Lsoot/RefType;", "kotlin.jvm.PlatformType", "getClassType", "()Lsoot/RefType;", "Lsoot/RefType;", "byteType", "Lsoot/ByteType;", "getByteType", "()Lsoot/ByteType;", "Lsoot/ByteType;", "arrayType", "Lsoot/ArrayType;", "getArrayType", "()Lsoot/ArrayType;", "Lsoot/ArrayType;", "valueField", "Lsoot/SootField;", "getValueField", "()Lsoot/SootField;", "coderField", "getCoderField", "hashField", "getHashField", "sizeLocal", "Lsoot/Local;", "getSizeLocal", "()Lsoot/Local;", "Lsoot/Local;", "newValueExpr", "Lsoot/jimple/NewArrayExpr;", "getNewValueExpr", "()Lsoot/jimple/NewArrayExpr;", "Lsoot/jimple/NewArrayExpr;", "newStringExpr", "getNewStringExpr", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WString.class */
public final class WString implements SummaryHandlePackage<IValue> {
    private final RefType classType = RefType.v("java.lang.String");
    private final ByteType byteType = G.v().soot_ByteType();
    private final ArrayType arrayType = ArrayType.v(this.byteType, 1);

    @NotNull
    private final SootField valueField;

    @NotNull
    private final SootField coderField;

    @NotNull
    private final SootField hashField;
    private final Local sizeLocal;
    private final NewArrayExpr newValueExpr;
    private final NewArrayExpr newStringExpr;
    private static final byte LATIN1_BYTE = 0;

    @NotNull
    private static final IntConstant LATIN1;

    @NotNull
    private static final IntConstant UTF16;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final byte UTF16_BYTE = 1;

    public WString() {
        Type type = this.arrayType;
        Intrinsics.checkNotNullExpressionValue(type, "arrayType");
        this.valueField = SootUtilsKt.getOrMakeField("java.lang.String", "value", type);
        Type type2 = this.byteType;
        Intrinsics.checkNotNullExpressionValue(type2, "byteType");
        this.coderField = SootUtilsKt.getOrMakeField("java.lang.String", "coder", type2);
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        this.hashField = SootUtilsKt.getOrMakeField("java.lang.String", "hash", typeSoot_IntType);
        this.sizeLocal = Jimple.v().newLocal("size", G.v().soot_IntType());
        this.newValueExpr = Jimple.v().newNewArrayExpr(this.arrayType, this.sizeLocal);
        this.newStringExpr = Jimple.v().newNewArrayExpr(this.arrayType, this.sizeLocal);
    }

    public final RefType getClassType() {
        return this.classType;
    }

    public final ByteType getByteType() {
        return this.byteType;
    }

    public final ArrayType getArrayType() {
        return this.arrayType;
    }

    @NotNull
    public final SootField getValueField() {
        return this.valueField;
    }

    @NotNull
    public final SootField getCoderField() {
        return this.coderField;
    }

    @NotNull
    public final SootField getHashField() {
        return this.hashField;
    }

    public final Local getSizeLocal() {
        return this.sizeLocal;
    }

    public final NewArrayExpr getNewValueExpr() {
        return this.newValueExpr;
    }

    public final NewArrayExpr getNewStringExpr() {
        return this.newStringExpr;
    }

    /* compiled from: WString.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WString$Companion;", "", "<init>", "()V", "LATIN1_BYTE", "", "getLATIN1_BYTE", "()B", "UTF16_BYTE", "getUTF16_BYTE", "LATIN1", "Lsoot/jimple/IntConstant;", "getLATIN1", "()Lsoot/jimple/IntConstant;", "UTF16", "getUTF16", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WString;", "corax-data-flow"})
    /* loaded from: WString$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final byte getLATIN1_BYTE() {
            return WString.LATIN1_BYTE;
        }

        public final byte getUTF16_BYTE() {
            return WString.UTF16_BYTE;
        }

        @NotNull
        public final IntConstant getLATIN1() {
            return WString.LATIN1;
        }

        @NotNull
        public final IntConstant getUTF16() {
            return WString.UTF16;
        }

        @NotNull
        public final WString v() {
            return new WString();
        }
    }

    static {
        IntConstant intConstantV = IntConstant.v(0);
        Intrinsics.checkNotNullExpressionValue(intConstantV, "v(...)");
        LATIN1 = intConstantV;
        IntConstant intConstantV2 = IntConstant.v(1);
        Intrinsics.checkNotNullExpressionValue(intConstantV2, "v(...)");
        UTF16 = intConstantV2;
    }

    /* compiled from: WString.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.WString$register$1, reason: invalid class name */
    /* loaded from: WString$register$1.class */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, String.class, "hashCode", "hashCode()I", 0);
        }

        public final Integer invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Integer.valueOf(p0.hashCode());
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        $this$register.evalCall(UtilsKt.getSootSignature(AnonymousClass1.INSTANCE), WString::register$lambda$1);
        ArrayType arrayType = this.arrayType;
        Intrinsics.checkNotNullExpressionValue(arrayType, "arrayType");
        $this$register.postCallAtCaller("<java.lang.String: byte[] getBytes()>", (v2) -> {
            return register$getValueElement$lambda$3(r0, r1, v2);
        });
        ArrayType arrayType2 = this.arrayType;
        Intrinsics.checkNotNullExpressionValue(arrayType2, "arrayType");
        $this$register.postCallAtCaller("<java.lang.String: byte[] getBytes(java.lang.String)>", (v2) -> {
            return register$getValueElement$lambda$3(r0, r1, v2);
        });
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.lang.String: boolean equals(java.lang.Object)>", false));
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.lang.String: char charAt(int)>", false));
        $this$register.evalCall("<java.lang.String: boolean isLatin1()>", WString::register$lambda$4);
        $this$register.evalCall("<java.lang.String: byte coder()>", WString::register$lambda$5);
    }

    private static final Unit register$lambda$1(CalleeCBImpl.EvalCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IHeapValues th1sOp = $this$ret.arg(-1);
        IOpCalculator c = WStringKt.getStringFromObject($this$ret, th1sOp);
        c.putSummaryIfNotConcrete($this$ret.getHf().getVg().getSTRING_TYPE(), "return");
        IOpCalculator strOp = $this$ret.getHf().resolveOp($this$ret.getEnv(), c.getRes().build());
        strOp.resolve((v1, v2, v3) -> {
            return register$lambda$1$lambda$0(r1, v1, v2, v3);
        });
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$ret.getOut(), $this$ret.getEnv(), $this$ret.getHf().getVg().getRETURN_LOCAL(), strOp.getRes().build(), false, 8, null);
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_ret, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        String string;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV str = companionVArr[0];
        Object value = str.getValue();
        ConstVal constVal = value instanceof ConstVal ? (ConstVal) value : null;
        if (constVal == null || (string = FactValuesKt.getStringValue(constVal, true)) == null) {
            return false;
        }
        AbstractHeapFactory<IValue> hf = $this_ret.getHf();
        Constant constantV = IntConstant.v(string.hashCode());
        Intrinsics.checkNotNullExpressionValue(constantV, "v(...)");
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        IValue r = hf.newConstVal2(constantV, typeSoot_IntType);
        res.add($this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) r).markOfReturnValueOfMethod($this_ret).pop());
        return true;
    }

    private static final Unit register$getValueElement$lambda$3(WString this$0, ArrayType $returnType, CallerSiteCBImpl.PostCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IHeapValues th1sOp = $this$ret.getThis();
        IOpCalculator strObjectOp = $this$ret.getHf().resolveOp($this$ret.getEnv(), th1sOp);
        strObjectOp.resolve((v3, v4, v5) -> {
            return register$getValueElement$lambda$3$lambda$2(r1, r2, r3, v3, v4, v5);
        });
        $this$ret.setReturn($this$ret.getReturn().plus(strObjectOp.getRes().build()));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$getValueElement$lambda$3$lambda$2(CallerSiteCBImpl.PostCall $this_ret, WString this$0, ArrayType $returnType, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV th1s = companionVArr[0];
        AbstractHeapFactory<IValue> hf = $this_ret.getHf();
        AnyNewExprEnv newEnv = $this_ret.getNewEnv();
        AnyNewExpr anyNewExpr = this$0.newValueExpr;
        Intrinsics.checkNotNullExpressionValue(anyNewExpr, "newValueExpr");
        IValue newValue = hf.anyNewVal(newEnv, anyNewExpr);
        res.add($this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) newValue).dataSequenceToSeq(th1s).pop());
        if (!(th1s.getValue() instanceof ConstVal)) {
            IData strArray = $this_ret.getOut().getValueData(th1s.getValue(), BuiltInModelT.Array);
            $this_ret.getOut().setValueData($this_ret.getEnv(), newValue, BuiltInModelT.Array, strArray);
            return true;
        }
        String stringValue$default = FactValuesKt.getStringValue$default((IValue) th1s.getValue(), false, 1, null);
        if (stringValue$default == null) {
            return false;
        }
        byte[] str = stringValue$default.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(str, "getBytes(...)");
        if (str == null) {
            return false;
        }
        IHeapValues len = $this_ret.getHf().push((HeapValuesEnv) $this_ret.getEnv(), (HookEnv) $this_ret.getHf().toConstVal(Integer.valueOf(str.length))).popHV();
        $this_ret.getOut().setValueData($this_ret.getEnv(), newValue, BuiltInModelT.Array, ArraySpace.Companion.v($this_ret.getHf(), $this_ret.getEnv(), (CompanionV<IValue>) th1s, ArraysKt.toTypedArray(str), $returnType, (IHeapValues<IValue>) len));
        return true;
    }

    private static final Unit register$lambda$4(CalleeCBImpl.EvalCall $this$ret) throws NotImplementedError {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IValue r = $this$ret.getHf().toConstVal(true);
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$ret.getOut(), $this$ret.getEnv(), $this$ret.getHf().getVg().getRETURN_LOCAL(), $this$ret.getHf().push((HeapValuesEnv) $this$ret.getEnv(), (HookEnv) r).markOfReturnValueOfMethod($this$ret).popHV(), false, 8, null);
        $this$ret.getOut().build();
        return Unit.INSTANCE;
    }

    private static final Unit register$lambda$5(CalleeCBImpl.EvalCall $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        IFact.Builder<IValue> out = $this$ret.getOut();
        HookEnv env = $this$ret.getEnv();
        String return_local = $this$ret.getHf().getVg().getRETURN_LOCAL();
        AbstractHeapFactory<IValue> hf = $this$ret.getHf();
        HookEnv env2 = $this$ret.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this$ret.getHf();
        Constant constant = LATIN1;
        Type typeSoot_ByteType = G.v().soot_ByteType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_ByteType, "soot_ByteType(...)");
        IFact.Builder.DefaultImpls.assignNewExpr$default(out, env, return_local, hf.push((HeapValuesEnv) env2, (HookEnv) hf2.newConstVal2(constant, typeSoot_ByteType)).markOfReturnValueOfMethod($this$ret).popHV(), false, 8, null);
        return Unit.INSTANCE;
    }
}
