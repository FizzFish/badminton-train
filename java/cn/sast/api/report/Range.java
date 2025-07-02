package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020��0\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020��H\u0096\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J1\u0010\u001a\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcn/sast/api/report/Range;", "", "Lcn/sast/api/report/IReportHashAble;", "start_line", "", "start_col", "end_line", "end_col", "<init>", "(IIII)V", "getStart_line", "()I", "getStart_col", "getEnd_line", "getEnd_col", "compareTo", "other", "reportHash", "", "c", "Lcn/sast/api/report/IReportHashCalculator;", "toString", "component1", "component2", "component3", "component4", "copy", "equals", "", "", "hashCode", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/Range\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: Range.class */
public final class Range implements Comparable<Range>, IReportHashAble {
    private final int start_line;
    private final int start_col;
    private final int end_line;
    private final int end_col;

    public final int component1() {
        return this.start_line;
    }

    public final int component2() {
        return this.start_col;
    }

    public final int component3() {
        return this.end_line;
    }

    public final int component4() {
        return this.end_col;
    }

    @NotNull
    public final Range copy(int start_line, int start_col, int end_line, int end_col) {
        return new Range(start_line, start_col, end_line, end_col);
    }

    public static /* synthetic */ Range copy$default(Range range, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = range.start_line;
        }
        if ((i5 & 2) != 0) {
            i2 = range.start_col;
        }
        if ((i5 & 4) != 0) {
            i3 = range.end_line;
        }
        if ((i5 & 8) != 0) {
            i4 = range.end_col;
        }
        return range.copy(i, i2, i3, i4);
    }

    public int hashCode() {
        int result = Integer.hashCode(this.start_line);
        return (((((result * 31) + Integer.hashCode(this.start_col)) * 31) + Integer.hashCode(this.end_line)) * 31) + Integer.hashCode(this.end_col);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Range)) {
            return false;
        }
        Range range = (Range) other;
        return this.start_line == range.start_line && this.start_col == range.start_col && this.end_line == range.end_line && this.end_col == range.end_col;
    }

    public Range(int start_line, int start_col, int end_line, int end_col) {
        this.start_line = start_line;
        this.start_col = start_col;
        this.end_line = end_line;
        this.end_col = end_col;
    }

    public final int getStart_line() {
        return this.start_line;
    }

    public final int getStart_col() {
        return this.start_col;
    }

    public final int getEnd_line() {
        return this.end_line;
    }

    public final int getEnd_col() {
        return this.end_col;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Range other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Integer numValueOf = Integer.valueOf(Intrinsics.compare(this.start_line, other.start_line));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        Integer numValueOf2 = Integer.valueOf(Intrinsics.compare(this.start_col, other.start_col));
        int it3 = numValueOf2.intValue();
        Integer num2 = it3 != 0 ? numValueOf2 : null;
        if (num2 != null) {
            int it4 = num2.intValue();
            return it4;
        }
        Integer numValueOf3 = Integer.valueOf(Intrinsics.compare(this.end_line, other.end_line));
        int it5 = numValueOf3.intValue();
        Integer num3 = it5 != 0 ? numValueOf3 : null;
        if (num3 != null) {
            int it6 = num3.intValue();
            return it6;
        }
        Integer numValueOf4 = Integer.valueOf(Intrinsics.compare(this.end_col, other.end_col));
        int it7 = numValueOf4.intValue();
        Integer num4 = it7 != 0 ? numValueOf4 : null;
        if (num4 == null) {
            return 0;
        }
        int it8 = num4.intValue();
        return it8;
    }

    @Override // cn.sast.api.report.IReportHashAble
    @NotNull
    public String reportHash(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return this.start_line + "," + this.start_col + "," + this.end_line + "," + this.end_col;
    }

    @NotNull
    public String toString() {
        return "[" + this.start_line + ":" + this.start_col + "," + this.end_line + ":" + this.end_col + "]";
    }
}
