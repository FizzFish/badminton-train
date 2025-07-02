package cn.sast.dataflow.interprocedural.override.lang.util;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.OverrideModel;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.IntType;
import soot.Type;
import soot.jimple.Constant;
import soot.jimple.IntConstant;

/* compiled from: WArrayList.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u00102\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\f\u001a\u00020\r*\u00100\u000ej\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\u000fH\u0016R\u001b\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WArrayList;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "intType", "Lsoot/IntType;", "kotlin.jvm.PlatformType", "getIntType", "()Lsoot/IntType;", "Lsoot/IntType;", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWArrayList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/WArrayList\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,344:1\n1863#2,2:345\n*S KotlinDebug\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/WArrayList\n*L\n47#1:345,2\n*E\n"})
/* loaded from: WArrayList.class */
public final class WArrayList implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final IntType intType = G.v().soot_IntType();

    public final IntType getIntType() {
        return this.intType;
    }

    /* compiled from: WArrayList.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WArrayList$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/util/WArrayList;", "corax-data-flow"})
    /* loaded from: WArrayList$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WArrayList v() {
            return new WArrayList();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        Iterable $this$forEach$iv = CollectionsKt.listOf(new String[]{"<java.util.ArrayList: void <init>()>", "<java.util.ArrayList: void <init>(int)>"});
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            $this$register.evalCallAtCaller(it, WArrayList::register$lambda$2$lambda$1);
        }
        $this$register.evalCallAtCaller("<java.util.ArrayList: void clear()>", WArrayList::register$lambda$4);
        $this$register.evalCallAtCaller("<java.util.ArrayList: java.lang.Object get(int)>", (v1) -> {
            return register$lambda$6(r2, v1);
        });
        $this$register.evalCall("<java.util.ArrayList: boolean add(java.lang.Object)>", WArrayList::register$lambda$8);
        $this$register.evalCallAtCaller("<java.util.ArrayList: java.lang.Object remove(int)>", WArrayList::register$lambda$10);
    }

    private static final IHeapValues<IValue> register$mapGetModel(ACheckCallAnalysis $this_register, IData<IValue> iData, IValue key) {
        if (Intrinsics.areEqual(FactValuesKt.isNull(key), true)) {
        }
        if (Intrinsics.areEqual(key.getType(), G.v().soot_IntType())) {
            Integer keyIndex = FactValuesKt.getIntValue(key, false);
            Intrinsics.checkNotNull(iData, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.override.lang.util.ListSpace");
            return ((ListSpace) iData).get($this_register.getHf(), keyIndex);
        }
        return null;
    }

    private static final Unit register$lambda$2$lambda$1(CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues self = $this$evalCallAtCaller.getThis();
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), self);
        calculator.resolve((v1, v2, v3) -> {
            return register$lambda$2$lambda$1$lambda$0(r1, v1, v2, v3);
        });
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$2$lambda$1$lambda$0(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, IOpCalculator $this$resolve, IHeapValues.Builder builder, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(builder, "<unused var>");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV self = companionVArr[0];
        $this_evalCallAtCaller.getOut().setValueData($this_evalCallAtCaller.getEnv(), self.getValue(), OverrideModel.ArrayList, new ListSpace(null, null, 3, null));
        return true;
    }

    private static final Unit register$lambda$4(CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues self = $this$evalCallAtCaller.getThis();
        if (!self.isSingle()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), self);
        calculator.resolve((v1, v2, v3) -> {
            return register$lambda$4$lambda$3(r1, v1, v2, v3);
        });
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$4$lambda$3(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, IOpCalculator $this$resolve, IHeapValues.Builder builder, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(builder, "<unused var>");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV self = companionVArr[0];
        $this_evalCallAtCaller.getOut().setValueData($this_evalCallAtCaller.getEnv(), self.getValue(), OverrideModel.ArrayList, new ListSpace(null, null, 3, null));
        return true;
    }

    private static final Unit register$lambda$6(ACheckCallAnalysis $this_register, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues self = $this$evalCallAtCaller.getThis();
        IHeapValues key = $this$evalCallAtCaller.arg(0);
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), self, key);
        calculator.resolve((v2, v3, v4) -> {
            return register$lambda$6$lambda$5(r1, r2, v2, v3, v4);
        });
        if (!calculator.isFullySimplified()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        $this$evalCallAtCaller.setReturn(calculator.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$6$lambda$5(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, ACheckCallAnalysis $this_register, IOpCalculator $this$get, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$get, "$this$get");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV self = companionVArr[0];
        CompanionV key = companionVArr[1];
        IData list = $this_evalCallAtCaller.getOut().getValueData(self.getValue(), OverrideModel.ArrayList);
        if (list == null) {
            return false;
        }
        IHeapValues<IValue> iHeapValuesRegister$mapGetModel = register$mapGetModel($this_register, list, (IValue) key.getValue());
        if (iHeapValuesRegister$mapGetModel == null) {
            return false;
        }
        res.add(iHeapValuesRegister$mapGetModel);
        return true;
    }

    private static final Unit register$lambda$8(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues self = $this$evalCall.getThis();
        IHeapValues value = $this$evalCall.arg(0);
        IOpCalculator calculator = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), self);
        calculator.resolve((v2, v3, v4) -> {
            return register$lambda$8$lambda$7(r1, r2, v2, v3, v4);
        });
        if (!calculator.isFullySimplified()) {
            $this$evalCall.setEvalAble(false);
            return Unit.INSTANCE;
        }
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
        calculator.putSummaryIfNotConcrete(typeSoot_BooleanType, "return");
        $this$evalCall.setReturn(calculator.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [cn.sast.dataflow.interprocedural.override.lang.util.ListSpaceBuilder] */
    private static final boolean register$lambda$8$lambda$7(CalleeCBImpl.EvalCall $this_evalCall, IHeapValues $value, IOpCalculator $this$add, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$add, "$this$add");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV self = companionVArr[0];
        IData valueData = $this_evalCall.getOut().getValueData(self.getValue(), OverrideModel.ArrayList);
        ListSpace listSpace = valueData instanceof ListSpace ? (ListSpace) valueData : null;
        if (listSpace == null) {
            return false;
        }
        ListSpace list = listSpace;
        ?? Builder2 = list.builder2();
        Builder2.add($value);
        $this_evalCall.getOut().setValueData($this_evalCall.getEnv(), self.getValue(), OverrideModel.ArrayList, Builder2.build2());
        AbstractHeapFactory<IValue> hf = $this_evalCall.getHf();
        HookEnv env = $this_evalCall.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_evalCall.getHf();
        Constant constantV = IntConstant.v(1);
        Intrinsics.checkNotNullExpressionValue(constantV, "v(...)");
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
        res.add(hf.push((HeapValuesEnv) env, (HookEnv) hf2.newConstVal2(constantV, typeSoot_BooleanType)).popHV());
        return true;
    }

    private static final Unit register$lambda$10(CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues self = $this$evalCallAtCaller.getThis();
        IHeapValues index = $this$evalCallAtCaller.arg(0);
        if (!self.isSingle()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), self, index);
        calculator.resolve((v1, v2, v3) -> {
            return register$lambda$10$lambda$9(r1, v1, v2, v3);
        });
        if (!calculator.isFullySimplified()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        calculator.putSummaryIfNotConcrete($this$evalCallAtCaller.getHf().getVg().getOBJECT_TYPE(), "return");
        $this$evalCallAtCaller.setReturn(calculator.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [cn.sast.dataflow.interprocedural.override.lang.util.ListSpaceBuilder] */
    private static final boolean register$lambda$10$lambda$9(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, IOpCalculator $this$add, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$add, "$this$add");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV self = companionVArr[0];
        CompanionV index = companionVArr[1];
        IData valueData = $this_evalCallAtCaller.getOut().getValueData(self.getValue(), OverrideModel.ArrayList);
        ListSpace listSpace = valueData instanceof ListSpace ? (ListSpace) valueData : null;
        if (listSpace == null) {
            return false;
        }
        ListSpace list = listSpace;
        Integer indexConstant = FactValuesKt.getIntValue((IValue) index.getValue(), true);
        ?? Builder2 = list.builder2();
        IHeapValues resValue = Builder2.remove($this_evalCallAtCaller.getHf(), indexConstant);
        $this_evalCallAtCaller.getOut().setValueData($this_evalCallAtCaller.getEnv(), self.getValue(), OverrideModel.ArrayList, Builder2.build2());
        res.add(resValue);
        return true;
    }
}
