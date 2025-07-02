package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapBuilder;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.ArraySpace;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import org.jetbrains.annotations.NotNull;
import soot.ArrayType;
import soot.G;
import soot.NullType;
import soot.PrimType;
import soot.RefType;
import soot.Type;

/* compiled from: WSystem.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u00102\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\f\u001a\u00020\r*\u00100\u000ej\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\u000fH\u0016R\u001b\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WSystem;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "arrayType", "Lsoot/ArrayType;", "kotlin.jvm.PlatformType", "getArrayType", "()Lsoot/ArrayType;", "Lsoot/ArrayType;", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WSystem.class */
public final class WSystem implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final ArrayType arrayType = ArrayType.v(G.v().soot_ByteType(), 1);

    public final ArrayType getArrayType() {
        return this.arrayType;
    }

    /* compiled from: WSystem.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WSystem$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WSystem;", "corax-data-flow"})
    /* loaded from: WSystem$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WSystem v() {
            return new WSystem();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        String identityHashCodeSignature = UtilsKt.getSootSignature(WSystem$register$identityHashCodeSignature$1.INSTANCE);
        $this$register.evalCall(identityHashCodeSignature, WSystem::register$lambda$1);
        $this$register.evalCall(UtilsKt.getSootSignature(AnonymousClass2.INSTANCE), WSystem::register$lambda$4);
    }

    private static final Unit register$lambda$1(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues obj = $this$evalCall.arg(0);
        IOpCalculator unop = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), obj);
        unop.resolve((v1, v2, v3) -> {
            return register$lambda$1$lambda$0(r1, v1, v2, v3);
        });
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        unop.putSummaryIfNotConcrete(typeSoot_IntType, "return");
        $this$evalCall.setReturn(unop.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_evalCall, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV instance = companionVArr[0];
        res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Integer.valueOf(instance.hashCode()))).markOfReturnValueOfMethod($this_evalCall).pop());
        return true;
    }

    /* compiled from: WSystem.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.WSystem$register$2, reason: invalid class name */
    /* loaded from: WSystem$register$2.class */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function5<Object, Integer, Object, Integer, Integer, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(5, System.class, "arraycopy", "arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V", 0);
        }

        public final void invoke(Object p0, int p1, Object p2, int p3, int p4) {
            System.arraycopy(p0, p1, p2, p3, p4);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4, Object p5) {
            invoke(p1, ((Number) p2).intValue(), p3, ((Number) p4).intValue(), ((Number) p5).intValue());
            return Unit.INSTANCE;
        }
    }

    private static final Unit register$lambda$4(CalleeCBImpl.EvalCall $this$evalCall) {
        IArrayHeapKV iArrayHeapKVV;
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues srcP = $this$evalCall.arg(0);
        IHeapValues srcPosP = $this$evalCall.arg(1);
        IHeapValues destP = $this$evalCall.arg(2);
        IHeapValues destPosP = $this$evalCall.arg(3);
        IHeapValues lengthP = $this$evalCall.arg(4);
        IOpCalculator op = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), srcP, srcPosP, destP, destPosP, lengthP);
        boolean append = (srcP.isSingle() && srcPosP.isSingle() && destP.isSingle() && destPosP.isSingle() && lengthP.isSingle()) ? false : true;
        IFact orig = $this$evalCall.getOut().build();
        op.resolve((v2, v3, v4) -> {
            return register$lambda$4$lambda$2(r1, r2, v2, v3, v4);
        });
        if (!op.isFullySimplified()) {
            IFact.Builder builder = orig.builder();
            boolean multiDst = !destP.isSingle();
            for (CompanionV dst : destP) {
                ArrayType type = dst.getValue().getType();
                ArrayType arrayType = type instanceof ArrayType ? type : null;
                if (arrayType != null) {
                    ArrayType dstType = arrayType;
                    if (multiDst) {
                        iArrayHeapKVV = builder.getArray(dst.getValue());
                        if (iArrayHeapKVV == null) {
                            AbstractHeapFactory<IValue> hf = $this$evalCall.getHf();
                            HookEnv env = $this$evalCall.getEnv();
                            AbstractHeapFactory<IValue> hf2 = $this$evalCall.getHf();
                            HookEnv env2 = $this$evalCall.getEnv();
                            Type typeSoot_IntType = G.v().soot_IntType();
                            Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
                            IHeapValues length = hf.push((HeapValuesEnv) env, (HookEnv) hf2.newSummaryVal(env2, typeSoot_IntType, "arraySize")).popHV();
                            iArrayHeapKVV = ArraySpace.Companion.v($this$evalCall.getHf(), $this$evalCall.getEnv(), ExtensionsKt.persistentHashMapOf(), $this$evalCall.getHf().empty(), dstType, (IHeapValues<IValue>) length);
                        }
                    } else {
                        AbstractHeapFactory<IValue> hf3 = $this$evalCall.getHf();
                        HookEnv env3 = $this$evalCall.getEnv();
                        AbstractHeapFactory<IValue> hf4 = $this$evalCall.getHf();
                        HookEnv env4 = $this$evalCall.getEnv();
                        Type typeSoot_IntType2 = G.v().soot_IntType();
                        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType2, "soot_IntType(...)");
                        IHeapValues length2 = hf3.push((HeapValuesEnv) env3, (HookEnv) hf4.newSummaryVal(env4, typeSoot_IntType2, "arraySize")).popHV();
                        iArrayHeapKVV = ArraySpace.Companion.v($this$evalCall.getHf(), $this$evalCall.getEnv(), ExtensionsKt.persistentHashMapOf(), $this$evalCall.getHf().empty(), dstType, (IHeapValues<IValue>) length2);
                    }
                    IArrayHeapKV dstArr = iArrayHeapKVV;
                    IHeapKVData.Builder<Integer, IValue> builder2 = dstArr.builder2();
                    ArrayHeapBuilder arrayHeapBuilder = builder2 instanceof ArrayHeapBuilder ? (ArrayHeapBuilder) builder2 : null;
                    if (arrayHeapBuilder != null) {
                        ArrayHeapBuilder cb = arrayHeapBuilder;
                        cb.clearAllIndex();
                        for (CompanionV src : srcP) {
                            IArrayHeapKV arrSrc = builder.getArray(src.getValue());
                            if (arrSrc == null) {
                                Type baseType = src.getValue().getType();
                                if ((baseType instanceof PrimType) || (baseType instanceof RefType) || (baseType instanceof NullType)) {
                                    AbstractHeapFactory<IValue> hf5 = $this$evalCall.getHf();
                                    HookEnv env5 = $this$evalCall.getEnv();
                                    AbstractHeapFactory<IValue> hf6 = $this$evalCall.getHf();
                                    HookEnv env6 = $this$evalCall.getEnv();
                                    Type typeMakeArrayType = src.getValue().getType().makeArrayType();
                                    Intrinsics.checkNotNullExpressionValue(typeMakeArrayType, "makeArrayType(...)");
                                    IHeapValues summary = hf5.push((HeapValuesEnv) env5, (HookEnv) hf6.newSummaryVal(env6, typeMakeArrayType, "arraySize")).popHV();
                                    AbstractHeapFactory<IValue> hf7 = $this$evalCall.getHf();
                                    HookEnv env7 = $this$evalCall.getEnv();
                                    AbstractHeapFactory<IValue> hf8 = $this$evalCall.getHf();
                                    HookEnv env8 = $this$evalCall.getEnv();
                                    Type typeSoot_IntType3 = G.v().soot_IntType();
                                    Intrinsics.checkNotNullExpressionValue(typeSoot_IntType3, "soot_IntType(...)");
                                    IHeapValues length3 = hf7.push((HeapValuesEnv) env7, (HookEnv) hf8.newSummaryVal(env8, typeSoot_IntType3, "summary")).popHV();
                                    arrSrc = ArraySpace.Companion.v($this$evalCall.getHf(), $this$evalCall.getEnv(), ExtensionsKt.persistentHashMapOf(), (IHeapValues<IValue>) summary, $this$evalCall.getHf().getVg().getOBJ_ARRAY_TYPE(), (IHeapValues<IValue>) length3);
                                    builder.setValueData($this$evalCall.getEnv(), src.getValue(), BuiltInModelT.Array, arrSrc);
                                }
                            }
                            cb.set((IHeapValuesFactory) $this$evalCall.getHf(), (HeapValuesEnv) $this$evalCall.getEnv(), (Integer) null, (IHeapValues) arrSrc.getElement($this$evalCall.getHf()), true);
                        }
                        builder.setValueData($this$evalCall.getEnv(), dst.getValue(), BuiltInModelT.Array, cb.build2());
                    }
                }
            }
            $this$evalCall.setOut((IFact.Builder<IValue>) builder);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$4$lambda$2(CalleeCBImpl.EvalCall $this_evalCall, boolean $append, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        IArrayHeapKV arrDest;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV src = companionVArr[0];
        CompanionV srcPos = companionVArr[1];
        CompanionV dest = companionVArr[2];
        CompanionV destPos = companionVArr[3];
        CompanionV length = companionVArr[4];
        IArrayHeapKV arrSrc = $this_evalCall.getOut().getArray(src.getValue());
        if (arrSrc == null || (arrDest = $this_evalCall.getOut().getArray(dest.getValue())) == null) {
            return false;
        }
        Integer intValue = FactValuesKt.getIntValue((IValue) srcPos.getValue(), true);
        if (intValue == null) {
            return false;
        }
        int intSrcPos = intValue.intValue();
        Integer intValue2 = FactValuesKt.getIntValue((IValue) destPos.getValue(), true);
        if (intValue2 == null) {
            return false;
        }
        int intDestPos = intValue2.intValue();
        Integer intValue3 = FactValuesKt.getIntValue((IValue) length.getValue(), true);
        if (intValue3 == null) {
            return false;
        }
        int intLength = intValue3.intValue();
        if (intLength >= 20) {
            return false;
        }
        IHeapKVData.Builder b = arrDest.builder2();
        for (int i = 0; i < intLength; i++) {
            IHeapValues s = arrSrc.get($this_evalCall.getHf(), Integer.valueOf(i + intSrcPos));
            if (s == null) {
                return false;
            }
            IHeapValues sf = $this_evalCall.getHf().push($this_evalCall.getEnv(), (IHeapValues<IValue>) s).dataElementCopyToSequenceElement(s).pop();
            b.set($this_evalCall.getHf(), $this_evalCall.getEnv(), Integer.valueOf(i + intDestPos), sf, $append);
        }
        $this_evalCall.getOut().setValueData($this_evalCall.getEnv(), dest.getValue(), BuiltInModelT.Array, b.build2());
        return true;
    }
}
