package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbsoluteFilePath.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/report/sqldelight/AbsoluteFilePath;", "", "file_abs_path", "", "__file_id", "", "<init>", "(Ljava/lang/String;J)V", "getFile_abs_path", "()Ljava/lang/String;", "get__file_id", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: AbsoluteFilePath.class */
public final class AbsoluteFilePath {

    @NotNull
    private final String file_abs_path;
    private final long __file_id;

    @NotNull
    public final String component1() {
        return this.file_abs_path;
    }

    public final long component2() {
        return this.__file_id;
    }

    @NotNull
    public final AbsoluteFilePath copy(@NotNull String file_abs_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_abs_path, "file_abs_path");
        return new AbsoluteFilePath(file_abs_path, __file_id);
    }

    public static /* synthetic */ AbsoluteFilePath copy$default(AbsoluteFilePath absoluteFilePath, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = absoluteFilePath.file_abs_path;
        }
        if ((i & 2) != 0) {
            j = absoluteFilePath.__file_id;
        }
        return absoluteFilePath.copy(str, j);
    }

    @NotNull
    public String toString() {
        return "AbsoluteFilePath(file_abs_path=" + this.file_abs_path + ", __file_id=" + this.__file_id + ")";
    }

    public int hashCode() {
        int result = this.file_abs_path.hashCode();
        return (result * 31) + Long.hashCode(this.__file_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbsoluteFilePath)) {
            return false;
        }
        AbsoluteFilePath absoluteFilePath = (AbsoluteFilePath) other;
        return Intrinsics.areEqual(this.file_abs_path, absoluteFilePath.file_abs_path) && this.__file_id == absoluteFilePath.__file_id;
    }

    public AbsoluteFilePath(@NotNull String file_abs_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_abs_path, "file_abs_path");
        this.file_abs_path = file_abs_path;
        this.__file_id = __file_id;
    }

    @NotNull
    public final String getFile_abs_path() {
        return this.file_abs_path;
    }

    public final long get__file_id() {
        return this.__file_id;
    }
}
