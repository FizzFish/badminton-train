package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.G;
import soot.RefType;
import soot.SootField;
import soot.Type;

/* compiled from: WNumber.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001aB\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00050\u00032\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00070\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"getValueFromObject", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallCBImpl;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "obj", "Lcn/sast/dataflow/interprocedural/analysis/V;", "type", "Lsoot/Type;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWNumber.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WNumber.kt\ncn/sast/dataflow/interprocedural/override/lang/WNumberKt\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,263:1\n44#2:264\n*S KotlinDebug\n*F\n+ 1 WNumber.kt\ncn/sast/dataflow/interprocedural/override/lang/WNumberKt\n*L\n35#1:264\n*E\n"})
/* loaded from: WNumberKt.class */
public final class WNumberKt {
    @NotNull
    public static final IOpCalculator<IValue> getValueFromObject(@NotNull ICallCBImpl<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCBImpl, @NotNull IHeapValues<IValue> iHeapValues, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(iCallCBImpl, "<this>");
        Intrinsics.checkNotNullParameter(iHeapValues, "obj");
        Intrinsics.checkNotNullParameter(type, "type");
        IOpCalculator c = iCallCBImpl.getHf().resolveOp(iCallCBImpl.getEnv(), iHeapValues);
        c.resolve((v2, v3, v4) -> {
            return getValueFromObject$lambda$0(r1, r2, v2, v3, v4);
        });
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean getValueFromObject$lambda$0(Type $type, ICallCBImpl $this_getValueFromObject, IOpCalculator $this$getValue, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Object doubleValue;
        Intrinsics.checkNotNullParameter($this$getValue, "$this$getValue");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV numObj = companionVArr[0];
        if (Intrinsics.areEqual($type, G.v().soot_ByteType()) || Intrinsics.areEqual($type, G.v().soot_CharType()) || Intrinsics.areEqual($type, G.v().soot_BooleanType()) || Intrinsics.areEqual($type, G.v().soot_ShortType()) || Intrinsics.areEqual($type, G.v().soot_IntType())) {
            doubleValue = FactValuesKt.getIntValue((IValue) numObj.getValue(), false);
        } else if (Intrinsics.areEqual($type, G.v().soot_LongType())) {
            doubleValue = FactValuesKt.getLongValue((IValue) numObj.getValue(), false);
        } else if (Intrinsics.areEqual($type, G.v().soot_FloatType())) {
            doubleValue = FactValuesKt.getFloatValue((IValue) numObj.getValue(), false);
        } else if (Intrinsics.areEqual($type, G.v().soot_DoubleType())) {
            doubleValue = FactValuesKt.getDoubleValue((IValue) numObj.getValue(), false);
        } else {
            return false;
        }
        Object num = doubleValue;
        if (num == null) {
            IFact.Builder.DefaultImpls.assignNewExpr$default((IFact.Builder) $this_getValueFromObject.getOut(), $this_getValueFromObject.getEnv(), "@num", $this_getValueFromObject.getHf().empty().plus((CompanionV<IValue>) numObj), false, 8, null);
            RefType type = ((IValue) numObj.getValue()).getType();
            RefType refType = type instanceof RefType ? type : null;
            if (refType == null) {
                return false;
            }
            RefType boxedPrimRefType = refType;
            SootField field = boxedPrimRefType.getSootClass().getFieldByNameUnsafe("value");
            if (field == null) {
                return false;
            }
            IFact.Builder builder = (IFact.Builder) $this_getValueFromObject.getOut();
            HookEnv env = $this_getValueFromObject.getEnv();
            FieldUtil fieldUtil = FieldUtil.INSTANCE;
            IFact.Builder.DefaultImpls.getField$default(builder, env, "@value", "@num", new JSootFieldType(field), false, 16, null);
            IHeapValues value = ((IFact.Builder) $this_getValueFromObject.getOut()).getTargets("@value");
            ((IFact.Builder) $this_getValueFromObject.getOut()).kill("@value");
            ((IFact.Builder) $this_getValueFromObject.getOut()).kill("@num");
            res.add(value);
            return true;
        }
        res.add(numObj);
        return true;
    }
}
