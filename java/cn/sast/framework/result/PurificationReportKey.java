package cn.sast.framework.result;

import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcn/sast/framework/result/PurificationReportKey;", "", "bugResFile", "Lcn/sast/api/report/IBugResInfo;", "line", "", "checkName", "", "firstEvent", "Lcn/sast/api/report/BugPathEvent;", "<init>", "(Lcn/sast/api/report/IBugResInfo;ILjava/lang/String;Lcn/sast/api/report/BugPathEvent;)V", "getBugResFile", "()Lcn/sast/api/report/IBugResInfo;", "getLine", "()I", "getCheckName", "()Ljava/lang/String;", "getFirstEvent", "()Lcn/sast/api/report/BugPathEvent;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "corax-framework"})
/* loaded from: PurificationReportKey.class */
public final class PurificationReportKey {

    @NotNull
    private final IBugResInfo bugResFile;
    private final int line;

    @NotNull
    private final String checkName;

    @NotNull
    private final BugPathEvent firstEvent;

    @NotNull
    public final IBugResInfo component1() {
        return this.bugResFile;
    }

    public final int component2() {
        return this.line;
    }

    @NotNull
    public final String component3() {
        return this.checkName;
    }

    @NotNull
    public final BugPathEvent component4() {
        return this.firstEvent;
    }

    @NotNull
    public final PurificationReportKey copy(@NotNull IBugResInfo bugResFile, int line, @NotNull String checkName, @NotNull BugPathEvent firstEvent) {
        Intrinsics.checkNotNullParameter(bugResFile, "bugResFile");
        Intrinsics.checkNotNullParameter(checkName, "checkName");
        Intrinsics.checkNotNullParameter(firstEvent, "firstEvent");
        return new PurificationReportKey(bugResFile, line, checkName, firstEvent);
    }

    public static /* synthetic */ PurificationReportKey copy$default(PurificationReportKey purificationReportKey, IBugResInfo iBugResInfo, int i, String str, BugPathEvent bugPathEvent, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            iBugResInfo = purificationReportKey.bugResFile;
        }
        if ((i2 & 2) != 0) {
            i = purificationReportKey.line;
        }
        if ((i2 & 4) != 0) {
            str = purificationReportKey.checkName;
        }
        if ((i2 & 8) != 0) {
            bugPathEvent = purificationReportKey.firstEvent;
        }
        return purificationReportKey.copy(iBugResInfo, i, str, bugPathEvent);
    }

    @NotNull
    public String toString() {
        return "PurificationReportKey(bugResFile=" + this.bugResFile + ", line=" + this.line + ", checkName=" + this.checkName + ", firstEvent=" + this.firstEvent + ")";
    }

    public int hashCode() {
        int result = this.bugResFile.hashCode();
        return (((((result * 31) + Integer.hashCode(this.line)) * 31) + this.checkName.hashCode()) * 31) + this.firstEvent.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PurificationReportKey)) {
            return false;
        }
        PurificationReportKey purificationReportKey = (PurificationReportKey) other;
        return Intrinsics.areEqual(this.bugResFile, purificationReportKey.bugResFile) && this.line == purificationReportKey.line && Intrinsics.areEqual(this.checkName, purificationReportKey.checkName) && Intrinsics.areEqual(this.firstEvent, purificationReportKey.firstEvent);
    }

    public PurificationReportKey(@NotNull IBugResInfo bugResFile, int line, @NotNull String checkName, @NotNull BugPathEvent firstEvent) {
        Intrinsics.checkNotNullParameter(bugResFile, "bugResFile");
        Intrinsics.checkNotNullParameter(checkName, "checkName");
        Intrinsics.checkNotNullParameter(firstEvent, "firstEvent");
        this.bugResFile = bugResFile;
        this.line = line;
        this.checkName = checkName;
        this.firstEvent = firstEvent;
    }

    @NotNull
    public final IBugResInfo getBugResFile() {
        return this.bugResFile;
    }

    public final int getLine() {
        return this.line;
    }

    @NotNull
    public final String getCheckName() {
        return this.checkName;
    }

    @NotNull
    public final BugPathEvent getFirstEvent() {
        return this.firstEvent;
    }
}
