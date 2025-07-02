package cn.sast.dataflow.infoflow.provider;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: MethodSummaryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0004"}, d2 = {"findAllOverrideMethodsOfMethod", "", "Lsoot/SootMethod;", "method", "corax-data-flow"})
/* loaded from: MethodSummaryProviderKt.class */
public final class MethodSummaryProviderKt {
    @NotNull
    public static final Set<SootMethod> findAllOverrideMethodsOfMethod(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        SootClass sootClass = method.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(sootClass, "getDeclaringClass(...)");
        Set<SootMethod> setResolveAbstractDispatch = Scene.v().getFastHierarchy().resolveAbstractDispatch(sootClass, method);
        Intrinsics.checkNotNullExpressionValue(setResolveAbstractDispatch, "resolveAbstractDispatch(...)");
        return setResolveAbstractDispatch;
    }
}
