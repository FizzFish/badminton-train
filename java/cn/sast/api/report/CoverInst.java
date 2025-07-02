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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcn/sast/api/report/CoverInst;", "Lcn/sast/api/report/CoverSootCode;", "method", "Lsoot/SootMethod;", "unit", "Lsoot/Unit;", "<init>", "(Lsoot/SootMethod;Lsoot/Unit;)V", "getMethod", "()Lsoot/SootMethod;", "getUnit", "()Lsoot/Unit;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-api"})
/* loaded from: CoverInst.class */
public final class CoverInst extends CoverSootCode {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final Unit unit;

    @NotNull
    public final SootMethod component1() {
        return this.method;
    }

    @NotNull
    public final Unit component2() {
        return this.unit;
    }

    @NotNull
    public final CoverInst copy(@NotNull SootMethod method, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new CoverInst(method, unit);
    }

    public static /* synthetic */ CoverInst copy$default(CoverInst coverInst, SootMethod sootMethod, Unit unit, int i, Object obj) {
        if ((i & 1) != 0) {
            sootMethod = coverInst.method;
        }
        if ((i & 2) != 0) {
            unit = coverInst.unit;
        }
        return coverInst.copy(sootMethod, unit);
    }

    @NotNull
    public String toString() {
        return "CoverInst(method=" + this.method + ", unit=" + this.unit + ")";
    }

    public int hashCode() {
        int result = this.method.hashCode();
        return (result * 31) + this.unit.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CoverInst)) {
            return false;
        }
        CoverInst coverInst = (CoverInst) other;
        return Intrinsics.areEqual(this.method, coverInst.method) && Intrinsics.areEqual(this.unit, coverInst.unit);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoverInst(@NotNull SootMethod method, @NotNull Unit unit) {
        super(method, unit, null);
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.method = method;
        this.unit = unit;
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
}
