package cn.sast.dataflow.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.Body;
import soot.Local;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethodRef;
import soot.Type;
import soot.Value;
import soot.jimple.IdentityRef;
import soot.jimple.IdentityStmt;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.ParameterRef;
import soot.jimple.ThisRef;
import soot.jimple.infoflow.data.SootMethodAndClass;
import soot.jimple.infoflow.util.SootMethodRepresentationParser;

/* compiled from: SootUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��T\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u001e\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\t0\u0007*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0\u0007*\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f\u001a\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\t\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0\u0007*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"!\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\u0007*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010\"!\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0\u0007*\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006 "}, d2 = {"sootSignatureToRef", "Lsoot/SootMethodRef;", "signature", "", "isStatic", "", "argToOpAndType", "Lkotlin/Pair;", "Lsoot/Local;", "Lsoot/Type;", "Lsoot/Body;", "index", "", "thisLocalAndType", "Lsoot/RefType;", "getThisLocalAndType", "(Lsoot/Body;)Lkotlin/Pair;", "argToIdentityRef", "Lsoot/jimple/IdentityRef;", "thisIdentityRef", "Lsoot/jimple/ThisRef;", "getThisIdentityRef", "Lsoot/Value;", "Lsoot/jimple/InvokeExpr;", "thisOpAndType", "getThisOpAndType", "(Lsoot/jimple/InvokeExpr;)Lkotlin/Pair;", "getOrMakeField", "Lsoot/SootField;", "sootClass", "fieldName", "sootFieldType", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSootUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootUtils.kt\ncn/sast/dataflow/util/SootUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,59:1\n1#2:60\n*E\n"})
/* loaded from: SootUtilsKt.class */
public final class SootUtilsKt {
    @NotNull
    public static final SootMethodRef sootSignatureToRef(@NotNull String signature, boolean isStatic) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        SootMethodAndClass smac = SootMethodRepresentationParser.v().parseSootMethodString(signature);
        SootClass sc = Scene.v().getSootClass(smac.getClassName());
        Intrinsics.checkNotNull(sc);
        Scene sceneV = Scene.v();
        String methodName = smac.getMethodName();
        List parameters = smac.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
        List<Type> listConvertParameterTypes = cn.sast.api.util.SootUtilsKt.convertParameterTypes(parameters);
        Type typeUnsafe = Scene.v().getTypeUnsafe(smac.getReturnType(), true);
        Intrinsics.checkNotNull(typeUnsafe);
        SootMethodRef sootMethodRefMakeMethodRef = sceneV.makeMethodRef(sc, methodName, listConvertParameterTypes, typeUnsafe, isStatic);
        Intrinsics.checkNotNullExpressionValue(sootMethodRefMakeMethodRef, "makeMethodRef(...)");
        return sootMethodRefMakeMethodRef;
    }

    @NotNull
    public static final Pair<Local, Type> argToOpAndType(@NotNull Body $this$argToOpAndType, int index) {
        Intrinsics.checkNotNullParameter($this$argToOpAndType, "<this>");
        if (index == -1) {
            return TuplesKt.to($this$argToOpAndType.getThisLocal(), $this$argToOpAndType.getMethod().getDeclaringClass().getType());
        }
        boolean z = 0 <= index && index < $this$argToOpAndType.getMethod().getParameterCount();
        if (z) {
            return TuplesKt.to($this$argToOpAndType.getParameterLocal(index), $this$argToOpAndType.getMethod().getParameterType(index));
        }
        throw new IllegalStateException(($this$argToOpAndType + " parameterCount: " + $this$argToOpAndType.getMethod().getParameterCount() + ", but index: " + index).toString());
    }

    @NotNull
    public static final Pair<Local, RefType> getThisLocalAndType(@NotNull Body $this$thisLocalAndType) {
        Intrinsics.checkNotNullParameter($this$thisLocalAndType, "<this>");
        Pair it = argToOpAndType($this$thisLocalAndType, -1);
        Object first = it.getFirst();
        Object second = it.getSecond();
        Intrinsics.checkNotNull(second, "null cannot be cast to non-null type soot.RefType");
        return TuplesKt.to(first, (RefType) second);
    }

    @NotNull
    public static final Pair<IdentityRef, Type> argToIdentityRef(@NotNull Body $this$argToIdentityRef, int index) {
        Intrinsics.checkNotNullParameter($this$argToIdentityRef, "<this>");
        if (index != -1) {
            boolean z = 0 <= index && index < $this$argToIdentityRef.getMethod().getParameterCount();
            if (z) {
                Object obj = $this$argToIdentityRef.getParameterRefs().get(index);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type soot.jimple.ParameterRef");
                ParameterRef it = (ParameterRef) obj;
                return TuplesKt.to(it, it.getType());
            }
            throw new IllegalStateException(($this$argToIdentityRef + " parameterCount: " + $this$argToIdentityRef.getMethod().getParameterCount() + ", but index: " + index).toString());
        }
        IdentityStmt thisUnit = $this$argToIdentityRef.getThisUnit();
        Intrinsics.checkNotNull(thisUnit, "null cannot be cast to non-null type soot.jimple.IdentityStmt");
        ThisRef rightOp = thisUnit.getRightOp();
        Intrinsics.checkNotNull(rightOp, "null cannot be cast to non-null type soot.jimple.ThisRef");
        ThisRef it2 = rightOp;
        return TuplesKt.to(it2, it2.getType());
    }

    @NotNull
    public static final Pair<ThisRef, RefType> getThisIdentityRef(@NotNull Body $this$thisIdentityRef) {
        Intrinsics.checkNotNullParameter($this$thisIdentityRef, "<this>");
        ThisRef thisRef = (IdentityRef) argToIdentityRef($this$thisIdentityRef, -1).getFirst();
        Intrinsics.checkNotNull(thisRef, "null cannot be cast to non-null type soot.jimple.ThisRef");
        RefType type = thisRef.getType();
        Intrinsics.checkNotNull(type, "null cannot be cast to non-null type soot.RefType");
        return TuplesKt.to(thisRef, type);
    }

    @NotNull
    public static final Pair<Value, Type> argToOpAndType(@NotNull InvokeExpr $this$argToOpAndType, int index) {
        Intrinsics.checkNotNullParameter($this$argToOpAndType, "<this>");
        if (index == -1 && ($this$argToOpAndType instanceof InstanceInvokeExpr)) {
            Local base = ((InstanceInvokeExpr) $this$argToOpAndType).getBase();
            Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
            return TuplesKt.to(base, ((InstanceInvokeExpr) $this$argToOpAndType).getMethodRef().getDeclaringClass().getType());
        }
        boolean z = 0 <= index && index < $this$argToOpAndType.getArgCount();
        if (z) {
            return TuplesKt.to($this$argToOpAndType.getArg(index), $this$argToOpAndType.getMethodRef().getParameterType(index));
        }
        throw new IllegalStateException(($this$argToOpAndType + " parameterCount: " + $this$argToOpAndType.getMethodRef() + ", but index is: " + index).toString());
    }

    @NotNull
    public static final Pair<Value, Type> getThisOpAndType(@NotNull InvokeExpr $this$thisOpAndType) {
        Intrinsics.checkNotNullParameter($this$thisOpAndType, "<this>");
        return argToOpAndType($this$thisOpAndType, -1);
    }

    @NotNull
    public static final SootField getOrMakeField(@NotNull String sootClass, @NotNull String fieldName, @NotNull Type sootFieldType) {
        Intrinsics.checkNotNullParameter(sootClass, "sootClass");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Intrinsics.checkNotNullParameter(sootFieldType, "sootFieldType");
        SootClass it = Scene.v().getSootClass(sootClass);
        SootField fieldByName = it.declaresFieldByName(fieldName) ? it.getFieldByName(fieldName) : it.getOrAddField(new SootField(fieldName, sootFieldType));
        Intrinsics.checkNotNullExpressionValue(fieldByName, "let(...)");
        return fieldByName;
    }
}
