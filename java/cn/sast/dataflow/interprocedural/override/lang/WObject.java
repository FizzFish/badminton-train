package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.ArrayType;
import soot.G;
import soot.PrimType;
import soot.RefType;
import soot.Type;
import soot.jimple.ClassConstant;
import soot.jimple.Constant;

/* compiled from: WObject.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u00102\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\f\u001a\u00020\r*\u00100\u000ej\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\u000fH\u0016R\u001b\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WObject;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "jClassType", "Lsoot/RefType;", "kotlin.jvm.PlatformType", "getJClassType", "()Lsoot/RefType;", "Lsoot/RefType;", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WObject.class */
public final class WObject implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final RefType jClassType = RefType.v("java.lang.Class");

    /* compiled from: WObject.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WObject$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WObject;", "corax-data-flow"})
    /* loaded from: WObject$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WObject v() {
            return new WObject();
        }
    }

    public final RefType getJClassType() {
        return this.jClassType;
    }

    /* compiled from: WObject.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.WObject$register$1, reason: invalid class name */
    /* loaded from: WObject$register$1.class */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Object, Class<?>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, Object.class, "getClass", "getClass()Ljava/lang/Class;", 0);
        }

        /* renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Class<?> m244invoke(Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.getClass();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        $this$register.evalCall(UtilsKt.getSootSignature(AnonymousClass1.INSTANCE), (v1) -> {
            return register$lambda$1(r2, v1);
        });
        $this$register.evalCall(UtilsKt.getSootSignature(AnonymousClass3.INSTANCE), WObject::register$lambda$3);
        String hashCodeSignature = UtilsKt.getSootSignature(WObject$register$hashCodeSignature$1.INSTANCE);
        $this$register.evalCall(hashCodeSignature, WObject::register$lambda$5);
    }

    private static final Unit register$lambda$1(WObject this$0, CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues obj = $this$evalCall.arg(-1);
        IOpCalculator unop = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), obj);
        unop.resolve((v2, v3, v4) -> {
            return register$lambda$1$lambda$0(r1, r2, v2, v3, v4);
        });
        Type type = this$0.jClassType;
        Intrinsics.checkNotNullExpressionValue(type, "jClassType");
        unop.putSummaryIfNotConcrete(type, "return");
        $this$evalCall.setReturn(unop.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_evalCall, WObject this$0, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        ClassConstant classConstantFromType;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        if (!op.typeIsConcrete()) {
            return false;
        }
        Type type = op.getType();
        if ((type instanceof RefType) || (type instanceof ArrayType) || (type instanceof PrimType)) {
            classConstantFromType = ClassConstant.fromType(type);
        } else {
            classConstantFromType = null;
        }
        if (classConstantFromType != null) {
            ClassConstant c = classConstantFromType;
            AbstractHeapFactory<IValue> hf = $this_evalCall.getHf();
            HookEnv env = $this_evalCall.getEnv();
            Type type2 = this$0.jClassType;
            Intrinsics.checkNotNullExpressionValue(type2, "jClassType");
            res.add(hf.push((HeapValuesEnv) env, (HookEnv) $this_evalCall.getHf().newConstVal2((Constant) c, type2)).markOfGetClass(cop).pop());
            return true;
        }
        return false;
    }

    /* compiled from: WObject.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.WObject$register$3, reason: invalid class name */
    /* loaded from: WObject$register$3.class */
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<Object, Object, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(2, Object.class, "equals", "equals(Ljava/lang/Object;)Z", 0);
        }

        /* renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m246invoke(Object p0, Object p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(p0.equals(p1));
        }
    }

    private static final Unit register$lambda$3(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues selfO = $this$evalCall.arg(-1);
        IHeapValues thatO = $this$evalCall.arg(0);
        IOpCalculator strObjectOp = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), selfO, thatO);
        strObjectOp.resolve((v1, v2, v3) -> {
            return register$lambda$3$lambda$2(r1, v1, v2, v3);
        });
        $this$evalCall.setReturn(strObjectOp.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$3$lambda$2(CalleeCBImpl.EvalCall $this_evalCall, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV th1s = companionVArr[0];
        CompanionV that = companionVArr[1];
        if (!((IValue) that.getValue()).isNullConstant()) {
            boolean eq = Intrinsics.areEqual(th1s.getValue(), that.getValue());
            res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Boolean.valueOf(eq))).markOfObjectEqualsResult(th1s, that).pop());
            return true;
        }
        res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(false)).markOfObjectEqualsResult(th1s, that).pop());
        return true;
    }

    private static final Unit register$lambda$5(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues obj = $this$evalCall.arg(0);
        IOpCalculator unop = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), obj);
        unop.resolve((v1, v2, v3) -> {
            return register$lambda$5$lambda$4(r1, v1, v2, v3);
        });
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        unop.putSummaryIfNotConcrete(typeSoot_IntType, "return");
        $this$evalCall.setReturn(unop.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$5$lambda$4(CalleeCBImpl.EvalCall $this_evalCall, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV instance = companionVArr[0];
        res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Integer.valueOf(instance.hashCode()))).markOfReturnValueOfMethod($this_evalCall).pop());
        return true;
    }
}
