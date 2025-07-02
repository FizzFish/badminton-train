package cn.sast.dataflow.interprocedural.override.lang.util;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.Type;

/* compiled from: WArrays.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \n2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WArrays;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WArrays.class */
public final class WArrays implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: WArrays.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/WArrays$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/util/WArrays;", "corax-data-flow"})
    /* loaded from: WArrays$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WArrays v() {
            return new WArrays();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        $this$register.evalCall("<java.util.Arrays: boolean equals(byte[],byte[])>", WArrays::register$lambda$1);
        $this$register.registerWrapper(SootUtilsKt.sootSignatureToRef("<java.util.Arrays: byte[] copyOfRange(byte[],int,int)>", true));
    }

    private static final Unit register$lambda$1(CalleeCBImpl.EvalCall $this$evalCall) {
        Intrinsics.checkNotNullParameter($this$evalCall, "$this$evalCall");
        IHeapValues a1v = $this$evalCall.arg(0);
        IHeapValues a2v = $this$evalCall.arg(1);
        IOpCalculator binop = $this$evalCall.getHf().resolveOp($this$evalCall.getEnv(), a1v, a2v);
        binop.resolve((v1, v2, v3) -> {
            return register$lambda$1$lambda$0(r1, v1, v2, v3);
        });
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
        binop.putSummaryIfNotConcrete(typeSoot_BooleanType, "return");
        IFact.Builder.DefaultImpls.assignNewExpr$default($this$evalCall.getOut(), $this$evalCall.getEnv(), $this$evalCall.getHf().getVg().getRETURN_LOCAL(), binop.getRes().build(), false, 8, null);
        return Unit.INSTANCE;
    }

    private static final Boolean register$lambda$1$contentEqual(CalleeCBImpl.EvalCall $this_evalCall, IArrayHeapKV<Integer, IValue> iArrayHeapKV, IArrayHeapKV<Integer, IValue> iArrayHeapKV2) {
        byte[] b;
        byte[] a = iArrayHeapKV.getByteArray($this_evalCall.getHf());
        if (a == null || (b = iArrayHeapKV2.getByteArray($this_evalCall.getHf())) == null) {
            return null;
        }
        return Boolean.valueOf(Arrays.equals(a, b));
    }

    private static final boolean register$lambda$1$lambda$0(CalleeCBImpl.EvalCall $this_evalCall, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV ca1 = companionVArr[0];
        CompanionV ca2 = companionVArr[1];
        IValue a1 = (IValue) ca1.getValue();
        IValue a2 = (IValue) ca2.getValue();
        if (a1.isNullConstant() || a2.isNullConstant()) {
            res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(false)).markOfArrayContentEqualsBoolResult().pop());
            return true;
        }
        IArrayHeapKV arrayA1 = $this_evalCall.getOut().getArray(a1);
        IArrayHeapKV arrayA2 = $this_evalCall.getOut().getArray(a2);
        if (arrayA1 == null || arrayA2 == null) {
            return false;
        }
        Boolean boolRegister$lambda$1$contentEqual = register$lambda$1$contentEqual($this_evalCall, arrayA1, arrayA2);
        if (boolRegister$lambda$1$contentEqual == null) {
            return false;
        }
        boolean eq = boolRegister$lambda$1$contentEqual.booleanValue();
        res.add($this_evalCall.getHf().push((HeapValuesEnv) $this_evalCall.getEnv(), (HookEnv) $this_evalCall.getHf().toConstVal(Boolean.valueOf(eq))).markOfArrayContentEqualsBoolResult().pop());
        return true;
    }
}
