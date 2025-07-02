package cn.sast.framework.validator;

import cn.sast.api.report.ReportKt;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0080\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcn/sast/framework/validator/ActualBugAnnotationData;", "", "file", "Lcn/sast/common/IResFile;", "line", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "<init>", "(Lcn/sast/common/IResFile;ILcom/feysh/corax/config/api/CheckType;)V", "getFile", "()Lcn/sast/common/IResFile;", "getLine", "()I", "getCheckType", "()Lcom/feysh/corax/config/api/CheckType;", "toString", "", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "corax-framework"})
/* loaded from: ActualBugAnnotationData.class */
public final class ActualBugAnnotationData {

    @NotNull
    private final IResFile file;
    private final int line;

    @NotNull
    private final CheckType checkType;

    @NotNull
    public final IResFile component1() {
        return this.file;
    }

    public final int component2() {
        return this.line;
    }

    @NotNull
    public final CheckType component3() {
        return this.checkType;
    }

    @NotNull
    public final ActualBugAnnotationData copy(@NotNull IResFile file, int line, @NotNull CheckType checkType) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        return new ActualBugAnnotationData(file, line, checkType);
    }

    public static /* synthetic */ ActualBugAnnotationData copy$default(ActualBugAnnotationData actualBugAnnotationData, IResFile iResFile, int i, CheckType checkType, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            iResFile = actualBugAnnotationData.file;
        }
        if ((i2 & 2) != 0) {
            i = actualBugAnnotationData.line;
        }
        if ((i2 & 4) != 0) {
            checkType = actualBugAnnotationData.checkType;
        }
        return actualBugAnnotationData.copy(iResFile, i, checkType);
    }

    public int hashCode() {
        int result = this.file.hashCode();
        return (((result * 31) + Integer.hashCode(this.line)) * 31) + this.checkType.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActualBugAnnotationData)) {
            return false;
        }
        ActualBugAnnotationData actualBugAnnotationData = (ActualBugAnnotationData) other;
        return Intrinsics.areEqual(this.file, actualBugAnnotationData.file) && this.line == actualBugAnnotationData.line && Intrinsics.areEqual(this.checkType, actualBugAnnotationData.checkType);
    }

    public ActualBugAnnotationData(@NotNull IResFile file, int line, @NotNull CheckType checkType) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        this.file = file;
        this.line = line;
        this.checkType = checkType;
    }

    @NotNull
    public final IResFile getFile() {
        return this.file;
    }

    public final int getLine() {
        return this.line;
    }

    @NotNull
    public final CheckType getCheckType() {
        return this.checkType;
    }

    @NotNull
    public String toString() {
        this.checkType.getBugMessage();
        return this.file + ":" + this.line + " report a [" + ReportKt.getPerfectName(this.checkType) + "]";
    }
}
