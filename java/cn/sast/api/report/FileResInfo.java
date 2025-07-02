package cn.sast.api.report;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0005\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\u0011\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0019\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcn/sast/api/report/FileResInfo;", "Lcn/sast/api/report/IBugResInfo;", "sourcePath", "Lcn/sast/common/IResFile;", "<init>", "(Lcn/sast/common/IResFile;)V", "getSourcePath", "()Lcn/sast/common/IResFile;", "abs", "getAbs", "abs$delegate", "Lkotlin/Lazy;", "reportFileName", "", "getReportFileName", "()Ljava/lang/String;", "reportHash", "c", "Lcn/sast/api/report/IReportHashCalculator;", "hashCode", "", "equals", "", "other", "", "path", "getPath", "compareTo", "toString", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/FileResInfo\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: FileResInfo.class */
public final class FileResInfo extends IBugResInfo {

    @NotNull
    private final IResFile sourcePath;

    @NotNull
    private final Lazy abs$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileResInfo(@NotNull IResFile sourcePath) {
        super(null);
        Intrinsics.checkNotNullParameter(sourcePath, "sourcePath");
        this.sourcePath = sourcePath;
        this.abs$delegate = LazyKt.lazy(() -> {
            return abs_delegate$lambda$0(r1);
        });
    }

    @NotNull
    public final IResFile getSourcePath() {
        return this.sourcePath;
    }

    @NotNull
    public final IResFile getAbs() {
        return (IResFile) this.abs$delegate.getValue();
    }

    private static final IResFile abs_delegate$lambda$0(FileResInfo this$0) {
        return this$0.sourcePath.getAbsolute().getNormalize();
    }

    @Override // cn.sast.api.report.IBugResInfo
    @NotNull
    public String getReportFileName() {
        return this.sourcePath.getName();
    }

    @Override // cn.sast.api.report.IReportHashAble
    @NotNull
    public String reportHash(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return c.fromAbsPath(getAbs());
    }

    public int hashCode() {
        return getAbs().toString().hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (other instanceof FileResInfo) {
            return Intrinsics.areEqual(getAbs().toString(), ((FileResInfo) other).getAbs().toString());
        }
        return false;
    }

    @Override // cn.sast.api.report.IBugResInfo
    @NotNull
    public String getPath() {
        return StringsKt.replace$default(getAbs().toString(), ":", "-", false, 4, (Object) null);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull IBugResInfo other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!(other instanceof FileResInfo)) {
            String simpleName = getClass().getSimpleName();
            String simpleName2 = other.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName2, "getSimpleName(...)");
            return simpleName.compareTo(simpleName2);
        }
        Integer numValueOf = Integer.valueOf(this.sourcePath.compareTo(((FileResInfo) other).sourcePath));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num == null) {
            return 0;
        }
        int it2 = num.intValue();
        return it2;
    }

    @NotNull
    public String toString() {
        return "FileResInfo(file=" + getAbs() + ")";
    }
}
