package cn.sast.framework.engine;

import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.IUnitCheckPoint;
import com.feysh.corax.config.api.report.Region;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.Value;
import soot.ValueBox;
import soot.jimple.Expr;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00160\u0018H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001e\u001a\u00020\u001f8PX\u0090\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcn/sast/framework/engine/UnitCheckPoint;", "Lcom/feysh/corax/config/api/IUnitCheckPoint;", "Lcn/sast/framework/engine/CheckPoint;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "unit", "Lsoot/Unit;", "sootMethod", "Lsoot/SootMethod;", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;Lsoot/Unit;Lsoot/SootMethod;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getUnit", "()Lsoot/Unit;", "getSootMethod", "()Lsoot/SootMethod;", "region", "Lcom/feysh/corax/config/api/report/Region;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "eachExpr", "", "block", "Lkotlin/Function1;", "Lsoot/jimple/Expr;", "file", "Lcn/sast/api/report/IBugResInfo;", "getFile", "()Lcn/sast/api/report/IBugResInfo;", "env", "Lcn/sast/api/report/DefaultEnv;", "getEnv$corax_framework", "()Lcn/sast/api/report/DefaultEnv;", "env$delegate", "Lkotlin/Lazy;", "corax-framework"})
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/UnitCheckPoint\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,760:1\n1863#2,2:761\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/UnitCheckPoint\n*L\n662#1:761,2\n*E\n"})
/* loaded from: UnitCheckPoint.class */
public final class UnitCheckPoint extends CheckPoint implements IUnitCheckPoint {

    @NotNull
    private final SootInfoCache info;

    @NotNull
    private final Unit unit;

    @NotNull
    private final SootMethod sootMethod;

    @NotNull
    private final IBugResInfo file;

    @NotNull
    private final Lazy env$delegate;

    @NotNull
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @NotNull
    public Unit getUnit() {
        return this.unit;
    }

    @NotNull
    public final SootMethod getSootMethod() {
        return this.sootMethod;
    }

    public UnitCheckPoint(@NotNull SootInfoCache info, @NotNull Unit unit, @NotNull SootMethod sootMethod) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(sootMethod, "sootMethod");
        this.info = info;
        this.unit = unit;
        this.sootMethod = sootMethod;
        SootClass declaringClass = this.sootMethod.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        this.file = new ClassResInfo(declaringClass);
        this.env$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
            return env_delegate$lambda$1(r2);
        });
    }

    @NotNull
    public Region getRegion() {
        Region regionInvoke = Region.Companion.invoke(this.info, getUnit());
        return regionInvoke == null ? Region.Companion.getERROR() : regionInvoke;
    }

    public void eachExpr(@NotNull Function1<? super Expr, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        Iterable useAndDefBoxes = getUnit().getUseAndDefBoxes();
        Intrinsics.checkNotNullExpressionValue(useAndDefBoxes, "getUseAndDefBoxes(...)");
        Iterable $this$forEach$iv = useAndDefBoxes;
        for (Object element$iv : $this$forEach$iv) {
            ValueBox box = (ValueBox) element$iv;
            Value value = box.getValue();
            if (value instanceof Expr) {
                function1.invoke(value);
            }
        }
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public IBugResInfo getFile() {
        return this.file;
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public DefaultEnv getEnv$corax_framework() {
        return (DefaultEnv) this.env$delegate.getValue();
    }

    private static final DefaultEnv env_delegate$lambda$1(UnitCheckPoint this$0) {
        return new DefaultEnv(this$0.getRegion().getMutable(), null, null, null, this$0.sootMethod, null, this$0.sootMethod.getDeclaringClass(), null, this$0.sootMethod, 174, null);
    }
}
