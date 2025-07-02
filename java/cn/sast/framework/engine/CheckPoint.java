package cn.sast.framework.engine;

import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.ICheckPoint;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.Constant;
import soot.jimple.StringConstant;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0016J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0007\u001a\u00020\u0005*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X \u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015*\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u0015*\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcn/sast/framework/engine/CheckPoint;", "Lcom/feysh/corax/config/api/ICheckPoint;", "<init>", "()V", "hasSideEffect", "", "Lsoot/SootMethod;", "isInstanceOf", "Lsoot/SootClass;", "parent", "", "(Lsoot/SootClass;Ljava/lang/String;)Ljava/lang/Boolean;", "file", "Lcn/sast/api/report/IBugResInfo;", "getFile", "()Lcn/sast/api/report/IBugResInfo;", "env", "Lcn/sast/api/report/DefaultEnv;", "getEnv$corax_framework", "()Lcn/sast/api/report/DefaultEnv;", "possibleTypes", "", "Lsoot/Type;", "Lsoot/Value;", "getPossibleTypes", "(Lsoot/Value;)Ljava/util/Set;", "possibleConstantValues", "getPossibleConstantValues", "toString", "corax-framework"})
/* loaded from: CheckPoint.class */
public abstract class CheckPoint implements ICheckPoint {
    @NotNull
    public abstract IBugResInfo getFile();

    @NotNull
    public abstract DefaultEnv getEnv$corax_framework();

    public boolean hasSideEffect(@NotNull SootMethod $this$hasSideEffect) {
        Intrinsics.checkNotNullParameter($this$hasSideEffect, "<this>");
        return true;
    }

    @Nullable
    public Boolean isInstanceOf(@NotNull SootClass $this$isInstanceOf, @NotNull String parent) {
        Intrinsics.checkNotNullParameter($this$isInstanceOf, "<this>");
        Intrinsics.checkNotNullParameter(parent, "parent");
        SootClass parentClass = Scene.v().getSootClassUnsafe(parent, false);
        if (parentClass == null) {
            return null;
        }
        return Boolean.valueOf(isInstanceOf($this$isInstanceOf, parentClass));
    }

    public boolean isInstanceOf(@NotNull SootClass $this$isInstanceOf, @NotNull SootClass parent) {
        Intrinsics.checkNotNullParameter($this$isInstanceOf, "<this>");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return Scene.v().getOrMakeFastHierarchy().canStoreType($this$isInstanceOf.getType(), parent.getType());
    }

    @NotNull
    public Set<Type> getPossibleTypes(@NotNull Value $this$possibleTypes) {
        Intrinsics.checkNotNullParameter($this$possibleTypes, "<this>");
        if ($this$possibleTypes instanceof Constant) {
            return SetsKt.setOf(((Constant) $this$possibleTypes).getType());
        }
        if (!($this$possibleTypes instanceof Local)) {
            return SetsKt.emptySet();
        }
        if (!Scene.v().hasPointsToAnalysis()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Set<Type> setPossibleTypes = Scene.v().getPointsToAnalysis().reachingObjects((Local) $this$possibleTypes).possibleTypes();
        return setPossibleTypes == null ? SetsKt.emptySet() : setPossibleTypes;
    }

    @NotNull
    public Set<String> getPossibleConstantValues(@NotNull Value $this$possibleConstantValues) {
        Intrinsics.checkNotNullParameter($this$possibleConstantValues, "<this>");
        if ($this$possibleConstantValues instanceof StringConstant) {
            return SetsKt.setOf(((StringConstant) $this$possibleConstantValues).value);
        }
        if (!($this$possibleConstantValues instanceof Local)) {
            return SetsKt.emptySet();
        }
        if (!Scene.v().hasPointsToAnalysis()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Set<String> setPossibleStringConstants = Scene.v().getPointsToAnalysis().reachingObjects((Local) $this$possibleConstantValues).possibleStringConstants();
        return setPossibleStringConstants == null ? SetsKt.emptySet() : setPossibleStringConstants;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r5 = this;
            r0 = r5
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r1 = r5
            cn.sast.api.report.IBugResInfo r1 = r1.getFile()
            r2 = r5
            boolean r2 = r2 instanceof com.feysh.corax.config.api.INodeWithRange
            if (r2 == 0) goto L19
            r2 = r5
            com.feysh.corax.config.api.INodeWithRange r2 = (com.feysh.corax.config.api.INodeWithRange) r2
            goto L1a
        L19:
            r2 = 0
        L1a:
            r3 = r2
            if (r3 == 0) goto L2e
            com.feysh.corax.config.api.report.Region r2 = r2.getRegion()
            r3 = r2
            if (r3 == 0) goto L2e
            java.lang.String r2 = r2.toString()
            r3 = r2
            if (r3 != 0) goto L31
        L2e:
        L2f:
            java.lang.String r2 = ""
        L31:
            java.lang.String r0 = r0 + " at " + r1 + ":" + r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.CheckPoint.toString():java.lang.String");
    }
}
