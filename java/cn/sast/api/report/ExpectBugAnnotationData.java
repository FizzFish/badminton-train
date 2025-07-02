package cn.sast.api.report;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018��*\u0004\b��\u0010\u00012\u00020\u0002:\u0001$B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00028��\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u001c\u001a\u00028��HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JF\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028��0��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00028��2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\b\u001a\u00028��¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcn/sast/api/report/ExpectBugAnnotationData;", "BugT", "", "file", "Lcn/sast/common/IResFile;", "line", "", "column", "bug", "kind", "Lcn/sast/api/report/ExpectBugAnnotationData$Kind;", "<init>", "(Lcn/sast/common/IResFile;IILjava/lang/Object;Lcn/sast/api/report/ExpectBugAnnotationData$Kind;)V", "getFile", "()Lcn/sast/common/IResFile;", "getLine", "()I", "getColumn", "getBug", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getKind", "()Lcn/sast/api/report/ExpectBugAnnotationData$Kind;", "toString", "", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcn/sast/common/IResFile;IILjava/lang/Object;Lcn/sast/api/report/ExpectBugAnnotationData$Kind;)Lcn/sast/api/report/ExpectBugAnnotationData;", "equals", "", "other", "hashCode", "Kind", "corax-api"})
/* loaded from: ExpectBugAnnotationData.class */
public final class ExpectBugAnnotationData<BugT> {

    @NotNull
    private final IResFile file;
    private final int line;
    private final int column;
    private final BugT bug;

    @NotNull
    private final Kind kind;

    @NotNull
    public final IResFile component1() {
        return this.file;
    }

    public final int component2() {
        return this.line;
    }

    public final int component3() {
        return this.column;
    }

    public final BugT component4() {
        return this.bug;
    }

    @NotNull
    public final Kind component5() {
        return this.kind;
    }

    @NotNull
    public final ExpectBugAnnotationData<BugT> copy(@NotNull IResFile file, int line, int column, BugT bugt, @NotNull Kind kind) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(kind, "kind");
        return new ExpectBugAnnotationData<>(file, line, column, bugt, kind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExpectBugAnnotationData copy$default(ExpectBugAnnotationData expectBugAnnotationData, IResFile iResFile, int i, int i2, Object obj, Kind kind, int i3, Object obj2) {
        if ((i3 & 1) != 0) {
            iResFile = expectBugAnnotationData.file;
        }
        if ((i3 & 2) != 0) {
            i = expectBugAnnotationData.line;
        }
        if ((i3 & 4) != 0) {
            i2 = expectBugAnnotationData.column;
        }
        BugT bugt = obj;
        if ((i3 & 8) != 0) {
            bugt = expectBugAnnotationData.bug;
        }
        if ((i3 & 16) != 0) {
            kind = expectBugAnnotationData.kind;
        }
        return expectBugAnnotationData.copy(iResFile, i, i2, bugt, kind);
    }

    public int hashCode() {
        int result = this.file.hashCode();
        return (((((((result * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.column)) * 31) + (this.bug == null ? 0 : this.bug.hashCode())) * 31) + this.kind.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpectBugAnnotationData)) {
            return false;
        }
        ExpectBugAnnotationData expectBugAnnotationData = (ExpectBugAnnotationData) other;
        return Intrinsics.areEqual(this.file, expectBugAnnotationData.file) && this.line == expectBugAnnotationData.line && this.column == expectBugAnnotationData.column && Intrinsics.areEqual(this.bug, expectBugAnnotationData.bug) && this.kind == expectBugAnnotationData.kind;
    }

    public ExpectBugAnnotationData(@NotNull IResFile file, int line, int column, BugT bugt, @NotNull Kind kind) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(kind, "kind");
        this.file = file;
        this.line = line;
        this.column = column;
        this.bug = bugt;
        this.kind = kind;
    }

    @NotNull
    public final IResFile getFile() {
        return this.file;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getColumn() {
        return this.column;
    }

    public final BugT getBug() {
        return this.bug;
    }

    @NotNull
    public final Kind getKind() {
        return this.kind;
    }

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/api/report/ExpectBugAnnotationData$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "Expect", "Escape", "corax-api"})
    /* loaded from: ExpectBugAnnotationData$Kind.class */
    public enum Kind {
        Expect,
        Escape;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    @NotNull
    public String toString() {
        return "file: " + this.file + ":" + this.line + ":" + this.column + " kind: " + this.kind + " a bug: " + this.bug;
    }
}
