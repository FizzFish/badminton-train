package cn.sast.api.report;

import cn.sast.api.util.ComparatorUtilsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.report.Region;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020��H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcn/sast/api/report/BugPathPosition;", "", "classname", "Lcn/sast/api/report/IBugResInfo;", "region", "Lcom/feysh/corax/config/api/report/Region;", "<init>", "(Lcn/sast/api/report/IBugResInfo;Lcom/feysh/corax/config/api/report/Region;)V", "getClassname", "()Lcn/sast/api/report/IBugResInfo;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "compareTo", "", "other", "toString", "", "component1", "component2", "copy", "equals", "", "", "hashCode", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/BugPathPosition\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: BugPathPosition.class */
public final class BugPathPosition implements Comparable<BugPathPosition> {

    @NotNull
    private final IBugResInfo classname;

    @Nullable
    private final Region region;

    @NotNull
    public final IBugResInfo component1() {
        return this.classname;
    }

    @Nullable
    public final Region component2() {
        return this.region;
    }

    @NotNull
    public final BugPathPosition copy(@NotNull IBugResInfo classname, @Nullable Region region) {
        Intrinsics.checkNotNullParameter(classname, "classname");
        return new BugPathPosition(classname, region);
    }

    public static /* synthetic */ BugPathPosition copy$default(BugPathPosition bugPathPosition, IBugResInfo iBugResInfo, Region region, int i, Object obj) {
        if ((i & 1) != 0) {
            iBugResInfo = bugPathPosition.classname;
        }
        if ((i & 2) != 0) {
            region = bugPathPosition.region;
        }
        return bugPathPosition.copy(iBugResInfo, region);
    }

    public int hashCode() {
        int result = this.classname.hashCode();
        return (result * 31) + (this.region == null ? 0 : this.region.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BugPathPosition)) {
            return false;
        }
        BugPathPosition bugPathPosition = (BugPathPosition) other;
        return Intrinsics.areEqual(this.classname, bugPathPosition.classname) && Intrinsics.areEqual(this.region, bugPathPosition.region);
    }

    public BugPathPosition(@NotNull IBugResInfo classname, @Nullable Region region) {
        Intrinsics.checkNotNullParameter(classname, "classname");
        this.classname = classname;
        this.region = region;
    }

    @NotNull
    public final IBugResInfo getClassname() {
        return this.classname;
    }

    @Nullable
    public final Region getRegion() {
        return this.region;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull BugPathPosition other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Integer numValueOf = Integer.valueOf(this.classname.compareTo(other.classname));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        Integer numValueOf2 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.region, other.region));
        int it3 = numValueOf2.intValue();
        Integer num2 = it3 != 0 ? numValueOf2 : null;
        if (num2 == null) {
            return 0;
        }
        int it4 = num2.intValue();
        return it4;
    }

    @NotNull
    public String toString() {
        return this.classname + " " + this.region;
    }
}
