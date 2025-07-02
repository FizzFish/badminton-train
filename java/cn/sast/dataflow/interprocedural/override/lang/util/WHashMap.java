package cn.sast.dataflow.interprocedural.override.lang.util;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.ArraySpace;
import cn.sast.dataflow.interprocedural.check.ArraySpaceBuilder;
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
import kotlinx.collections.immutable.ExtensionsKt;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.Type;

/* compiled from: WHashMap.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \n2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WHashMap;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWHashMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WHashMap.kt\ncn/sast/dataflow/interprocedural/override/lang/util/WHashMap\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,182:1\n1863#2,2:183\n*S KotlinDebug\n*F\n+ 1 WHashMap.kt\ncn/sast/dataflow/interprocedural/override/lang/util/WHashMap\n*L\n49#1:183,2\n*E\n"})
/* loaded from: WHashMap.class */
public final class WHashMap implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: WHashMap.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WHashMap$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/util/WHashMap;", "corax-data-flow"})
    /* loaded from: WHashMap$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WHashMap v() {
            return new WHashMap();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        Iterable $this$forEach$iv = CollectionsKt.listOf(new String[]{"<java.util.HashMap: void <init>()>", "<java.util.HashMap: void <init>(int)>", "<java.util.HashMap: void <init>(int, float)>"});
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            $this$register.evalCallAtCaller(it, WHashMap::register$lambda$2$lambda$1);
        }
        $this$register.evalCallAtCaller("<java.util.HashMap: void clear()>", WHashMap::register$lambda$4);
        $this$register.evalCallAtCaller("<java.util.HashMap: java.lang.Object get(java.lang.Object)>", (v1) -> {
            return register$lambda$6(r2, v1);
        });
        $this$register.evalCallAtCaller("<java.util.HashMap: java.lang.Object getOrDefault(java.lang.Object,java.lang.Object)>", (v1) -> {
            return register$lambda$8(r2, v1);
        });
        $this$register.evalCall("<java.util.HashMap: java.lang.Object put(java.lang.Object,java.lang.Object)>", WHashMap::register$lambda$10);
    }

    private static final IHeapValues<IValue> register$mapGetModel(ACheckCallAnalysis $this_register, IData<IValue> iData, IValue key) {
        String keyStr;
        if (Intrinsics.areEqual(FactValuesKt.isNull(key), true)) {
        }
        if (!Intrinsics.areEqual(key.getType(), $this_register.getHf().getVg().getSTRING_TYPE()) || (keyStr = FactValuesKt.getStringValue(key, false)) == null) {
            return null;
        }
        Intrinsics.checkNotNull(iData, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.ArraySpace");
        return ((ArraySpace) iData).get((IHeapValuesFactory) $this_register.getHf(), Integer.valueOf(Math.abs(keyStr.hashCode())));
    }

    private static final Unit register$lambda$2$lambda$1(CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues self = $this$evalCallAtCaller.arg(-1);
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
        AbstractHeapFactory<IValue> hf = $this_evalCallAtCaller.getHf();
        HookEnv env = $this_evalCallAtCaller.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_evalCallAtCaller.getHf();
        HookEnv env2 = $this_evalCallAtCaller.getEnv();
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        IHeapValues length = hf.push((HeapValuesEnv) env, (HookEnv) hf2.newSummaryVal(env2, typeSoot_IntType, "mapSize")).popHV();
        ArraySpace map = ArraySpace.Companion.v($this_evalCallAtCaller.getHf(), $this_evalCallAtCaller.getEnv(), ExtensionsKt.persistentHashMapOf(), $this_evalCallAtCaller.getHf().empty(), $this_evalCallAtCaller.getHf().getVg().getOBJ_ARRAY_TYPE(), (IHeapValues<IValue>) length);
        $this_evalCallAtCaller.getOut().setValueData($this_evalCallAtCaller.getEnv(), self.getValue(), OverrideModel.HashMap, map);
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
        AbstractHeapFactory<IValue> hf = $this_evalCallAtCaller.getHf();
        HookEnv env = $this_evalCallAtCaller.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_evalCallAtCaller.getHf();
        HookEnv env2 = $this_evalCallAtCaller.getEnv();
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        IHeapValues length = hf.push((HeapValuesEnv) env, (HookEnv) hf2.newSummaryVal(env2, typeSoot_IntType, "mapSize")).popHV();
        ArraySpace map = ArraySpace.Companion.v($this_evalCallAtCaller.getHf(), $this_evalCallAtCaller.getEnv(), ExtensionsKt.persistentHashMapOf(), $this_evalCallAtCaller.getHf().empty(), $this_evalCallAtCaller.getHf().getVg().getOBJ_ARRAY_TYPE(), (IHeapValues<IValue>) length);
        $this_evalCallAtCaller.getOut().setValueData($this_evalCallAtCaller.getEnv(), self.getValue(), OverrideModel.HashMap, map);
        return true;
    }

    private static final Unit register$lambda$6(ACheckCallAnalysis $this_register, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues map = $this$evalCallAtCaller.getThis();
        IHeapValues key = $this$evalCallAtCaller.arg(0);
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), map, key);
        if (!map.isSingle()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        calculator.resolve((v2, v3, v4) -> {
            return register$lambda$6$lambda$5(r1, r2, v2, v3, v4);
        });
        if (!calculator.isFullySimplified() || calculator.getRes().isEmpty()) {
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
        CompanionV map = companionVArr[0];
        CompanionV key = companionVArr[1];
        IData mapData = $this_evalCallAtCaller.getOut().getValueData(map.getValue(), OverrideModel.HashMap);
        if (mapData == null) {
            return false;
        }
        IHeapValues<IValue> iHeapValuesRegister$mapGetModel = register$mapGetModel($this_register, mapData, (IValue) key.getValue());
        if (iHeapValuesRegister$mapGetModel == null) {
            return false;
        }
        res.add(iHeapValuesRegister$mapGetModel);
        return true;
    }

    private static final Unit register$lambda$8(ACheckCallAnalysis $this_register, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues map = $this$evalCallAtCaller.getThis();
        if (!map.isSingle()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        IHeapValues key = $this$evalCallAtCaller.arg(0);
        IHeapValues defaultValue = $this$evalCallAtCaller.arg(1);
        IOpCalculator calculator = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), map, key, defaultValue);
        calculator.resolve((v2, v3, v4) -> {
            return register$lambda$8$lambda$7(r1, r2, v2, v3, v4);
        });
        if (!calculator.isFullySimplified() || calculator.getRes().isEmpty()) {
            $this$evalCallAtCaller.setEvalAble(false);
            return Unit.INSTANCE;
        }
        $this$evalCallAtCaller.setReturn(calculator.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$8$lambda$7(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, ACheckCallAnalysis $this_register, IOpCalculator $this$get, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$get, "$this$get");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV map = companionVArr[0];
        CompanionV key = companionVArr[1];
        CompanionV defaultValue = companionVArr[2];
        IData mapData = $this_evalCallAtCaller.getOut().getValueData(map.getValue(), OverrideModel.HashMap);
        if (mapData == null) {
            return false;
        }
        IHeapValues<IValue> iHeapValuesRegister$mapGetModel = register$mapGetModel($this_register, mapData, (IValue) key.getValue());
        if (iHeapValuesRegister$mapGetModel == null) {
            iHeapValuesRegister$mapGetModel = $this_evalCallAtCaller.getHf().single(defaultValue);
        }
        res.add(iHeapValuesRegister$mapGetModel);
        return true;
    }

    private static final Unit register$lambda$10(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues map = $this$evalCall.getThis();
        if (!map.isSingle()) {
            $this$evalCall.setEvalAble(false);
            return Unit.INSTANCE;
        }
        IHeapValues key = $this$evalCall.arg(0);
        IHeapValues value = $this$evalCall.arg(1);
        IOpCalculator calculator = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), map, key);
        calculator.resolve((v2, v3, v4) -> {
            return register$lambda$10$lambda$9(r1, r2, v2, v3, v4);
        });
        if (!calculator.isFullySimplified()) {
            $this$evalCall.setEvalAble(false);
            return Unit.INSTANCE;
        }
        $this$evalCall.setReturn(calculator.getRes().build());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean register$lambda$10$lambda$9(CalleeCBImpl.EvalCall $this_evalCall, IHeapValues $value, IOpCalculator $this$put, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$put, "$this$put");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV map = companionVArr[0];
        CompanionV key = companionVArr[1];
        IData mapData = $this_evalCall.getOut().getValueData(map.getValue(), OverrideModel.HashMap);
        if (mapData == null) {
            return false;
        }
        ArraySpaceBuilder builder = ((ArraySpace) mapData).builder2();
        if (Intrinsics.areEqual(((IValue) key.getValue()).getType(), $this_evalCall.getHf().getVg().getSTRING_TYPE())) {
            String keyStr = FactValuesKt.getStringValue((IValue) key.getValue(), false);
            if (keyStr == null) {
                builder.set((IHeapValuesFactory) $this_evalCall.getHf(), (HeapValuesEnv) $this_evalCall.getEnv(), (Integer) null, $value, true);
            } else {
                builder.set((IHeapValuesFactory) $this_evalCall.getHf(), (HeapValuesEnv) $this_evalCall.getEnv(), Integer.valueOf(Math.abs(keyStr.hashCode())), $value, true);
            }
            $this_evalCall.getOut().setValueData($this_evalCall.getEnv(), map.getValue(), OverrideModel.HashMap, builder.build2());
            res.add(((ArraySpace) mapData).getElement($this_evalCall.getHf()));
            return true;
        }
        builder.set((IHeapValuesFactory) $this_evalCall.getHf(), (HeapValuesEnv) $this_evalCall.getEnv(), (Integer) null, $value, true);
        return true;
    }
}
