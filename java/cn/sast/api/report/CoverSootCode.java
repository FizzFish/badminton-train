package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;

/* compiled from: ICoverageCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018��2\u00020\u0001B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0082\u0001\u0002\u0018\u0019¨\u0006\u001a"}, d2 = {"Lcn/sast/api/report/CoverSootCode;", "Lcn/sast/api/report/CoverData;", "method", "Lsoot/SootMethod;", "unit", "Lsoot/Unit;", "<init>", "(Lsoot/SootMethod;Lsoot/Unit;)V", "getMethod", "()Lsoot/SootMethod;", "getUnit", "()Lsoot/Unit;", "clazz", "Lsoot/SootClass;", "getClazz", "()Lsoot/SootClass;", "className", "", "getClassName", "()Ljava/lang/String;", "lineNumber", "", "getLineNumber", "()I", "Lcn/sast/api/report/CoverInst;", "Lcn/sast/api/report/CoverTaint;", "corax-api"})
/* loaded from: CoverSootCode.class */
public abstract class CoverSootCode extends CoverData {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final Unit unit;

    public /* synthetic */ CoverSootCode(SootMethod method, Unit unit, DefaultConstructorMarker $constructor_marker) {
        this(method, unit);
    }

    private CoverSootCode(SootMethod method, Unit unit) {
        super(null);
        this.method = method;
        this.unit = unit;
    }

    @NotNull
    public SootMethod getMethod() {
        return this.method;
    }

    @NotNull
    public Unit getUnit() {
        return this.unit;
    }

    @NotNull
    public final SootClass getClazz() {
        SootClass declaringClass = getMethod().getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        return declaringClass;
    }

    @NotNull
    public final String getClassName() {
        String name = getClazz().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    public final int getLineNumber() {
        return getUnit().getJavaSourceStartLineNumber();
    }
}
