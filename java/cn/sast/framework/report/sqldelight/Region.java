package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Region.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010JP\u0010\u001a\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010¨\u0006#"}, d2 = {"Lcn/sast/framework/report/sqldelight/Region;", "", "id", "", "__file_id", "start_line", "start_column", "end_line", "end_column", "<init>", "(JJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "getId", "()J", "get__file_id", "getStart_line", "getStart_column", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEnd_line", "getEnd_column", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lcn/sast/framework/report/sqldelight/Region;", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: Region.class */
public final class Region {
    private final long id;
    private final long __file_id;
    private final long start_line;

    @Nullable
    private final Long start_column;

    @Nullable
    private final Long end_line;

    @Nullable
    private final Long end_column;

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.__file_id;
    }

    public final long component3() {
        return this.start_line;
    }

    @Nullable
    public final Long component4() {
        return this.start_column;
    }

    @Nullable
    public final Long component5() {
        return this.end_line;
    }

    @Nullable
    public final Long component6() {
        return this.end_column;
    }

    @NotNull
    public final Region copy(long id, long __file_id, long start_line, @Nullable Long start_column, @Nullable Long end_line, @Nullable Long end_column) {
        return new Region(id, __file_id, start_line, start_column, end_line, end_column);
    }

    public static /* synthetic */ Region copy$default(Region region, long j, long j2, long j3, Long l, Long l2, Long l3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = region.id;
        }
        if ((i & 2) != 0) {
            j2 = region.__file_id;
        }
        if ((i & 4) != 0) {
            j3 = region.start_line;
        }
        if ((i & 8) != 0) {
            l = region.start_column;
        }
        if ((i & 16) != 0) {
            l2 = region.end_line;
        }
        if ((i & 32) != 0) {
            l3 = region.end_column;
        }
        return region.copy(j, j2, j3, l, l2, l3);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        long j2 = this.__file_id;
        long j3 = this.start_line;
        Long l = this.start_column;
        Long l2 = this.end_line;
        Long l3 = this.end_column;
        return "Region(id=" + j + ", __file_id=" + j + ", start_line=" + j2 + ", start_column=" + j + ", end_line=" + j3 + ", end_column=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (((((((((result * 31) + Long.hashCode(this.__file_id)) * 31) + Long.hashCode(this.start_line)) * 31) + (this.start_column == null ? 0 : this.start_column.hashCode())) * 31) + (this.end_line == null ? 0 : this.end_line.hashCode())) * 31) + (this.end_column == null ? 0 : this.end_column.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Region)) {
            return false;
        }
        Region region = (Region) other;
        return this.id == region.id && this.__file_id == region.__file_id && this.start_line == region.start_line && Intrinsics.areEqual(this.start_column, region.start_column) && Intrinsics.areEqual(this.end_line, region.end_line) && Intrinsics.areEqual(this.end_column, region.end_column);
    }

    public Region(long id, long __file_id, long start_line, @Nullable Long start_column, @Nullable Long end_line, @Nullable Long end_column) {
        this.id = id;
        this.__file_id = __file_id;
        this.start_line = start_line;
        this.start_column = start_column;
        this.end_line = end_line;
        this.end_column = end_column;
    }

    public final long getId() {
        return this.id;
    }

    public final long get__file_id() {
        return this.__file_id;
    }

    public final long getStart_line() {
        return this.start_line;
    }

    @Nullable
    public final Long getStart_column() {
        return this.start_column;
    }

    @Nullable
    public final Long getEnd_line() {
        return this.end_line;
    }

    @Nullable
    public final Long getEnd_column() {
        return this.end_column;
    }
}
