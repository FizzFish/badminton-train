package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;

/* compiled from: ICoverageCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcn/sast/api/report/CoverTaint;", "Lcn/sast/api/report/CoverSootCode;", "method", "Lsoot/SootMethod;", "unit", "Lsoot/Unit;", "value", "", "<init>", "(Lsoot/SootMethod;Lsoot/Unit;Ljava/lang/Object;)V", "getMethod", "()Lsoot/SootMethod;", "getUnit", "()Lsoot/Unit;", "getValue", "()Ljava/lang/Object;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-api"})
/* loaded from: CoverTaint.class */
public final class CoverTaint extends CoverSootCode {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final Unit unit;

    @NotNull
    private final Object value;

    @NotNull
    public final SootMethod component1() {
        return this.method;
    }

    @NotNull
    public final Unit component2() {
        return this.unit;
    }

    @NotNull
    public final Object component3() {
        return this.value;
    }

    @NotNull
    public final CoverTaint copy(@NotNull SootMethod method, @NotNull Unit unit, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(value, "value");
        return new CoverTaint(method, unit, value);
    }

    public static /* synthetic */ CoverTaint copy$default(CoverTaint coverTaint, SootMethod sootMethod, Unit unit, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            sootMethod = coverTaint.method;
        }
        if ((i & 2) != 0) {
            unit = coverTaint.unit;
        }
        if ((i & 4) != 0) {
            obj = coverTaint.value;
        }
        return coverTaint.copy(sootMethod, unit, obj);
    }

    @NotNull
    public String toString() {
        return "CoverTaint(method=" + this.method + ", unit=" + this.unit + ", value=" + this.value + ")";
    }

    public int hashCode() {
        int result = this.method.hashCode();
        return (((result * 31) + this.unit.hashCode()) * 31) + this.value.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CoverTaint)) {
            return false;
        }
        CoverTaint coverTaint = (CoverTaint) other;
        return Intrinsics.areEqual(this.method, coverTaint.method) && Intrinsics.areEqual(this.unit, coverTaint.unit) && Intrinsics.areEqual(this.value, coverTaint.value);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoverTaint(@NotNull SootMethod method, @NotNull Unit unit, @NotNull Object value) {
        super(method, unit, null);
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(value, "value");
        this.method = method;
        this.unit = unit;
        this.value = value;
    }

    @Override // cn.sast.api.report.CoverSootCode
    @NotNull
    public SootMethod getMethod() {
        return this.method;
    }

    @Override // cn.sast.api.report.CoverSootCode
    @NotNull
    public Unit getUnit() {
        return this.unit;
    }

    @NotNull
    public final Object getValue() {
        return this.value;
    }
}
