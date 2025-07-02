package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: File.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JQ\u0010\u001f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017¨\u0006&"}, d2 = {"Lcn/sast/framework/report/sqldelight/File;", "", "id", "", "file_raw_content_hash", "", "relative_path", "lines", "encoding", "file_raw_content_size", "file_raw_content", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;J[B)V", "getId", "()J", "getFile_raw_content_hash", "()Ljava/lang/String;", "getRelative_path", "getLines", "getEncoding", "getFile_raw_content_size", "getFile_raw_content", "()[B", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: File.class */
public final class File {
    private final long id;

    @NotNull
    private final String file_raw_content_hash;

    @NotNull
    private final String relative_path;
    private final long lines;

    @Nullable
    private final String encoding;
    private final long file_raw_content_size;

    @NotNull
    private final byte[] file_raw_content;

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.file_raw_content_hash;
    }

    @NotNull
    public final String component3() {
        return this.relative_path;
    }

    public final long component4() {
        return this.lines;
    }

    @Nullable
    public final String component5() {
        return this.encoding;
    }

    public final long component6() {
        return this.file_raw_content_size;
    }

    @NotNull
    public final byte[] component7() {
        return this.file_raw_content;
    }

    @NotNull
    public final File copy(long id, @NotNull String file_raw_content_hash, @NotNull String relative_path, long lines, @Nullable String encoding, long file_raw_content_size, @NotNull byte[] file_raw_content) {
        Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
        Intrinsics.checkNotNullParameter(relative_path, "relative_path");
        Intrinsics.checkNotNullParameter(file_raw_content, "file_raw_content");
        return new File(id, file_raw_content_hash, relative_path, lines, encoding, file_raw_content_size, file_raw_content);
    }

    public static /* synthetic */ File copy$default(File file, long j, String str, String str2, long j2, String str3, long j3, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            j = file.id;
        }
        if ((i & 2) != 0) {
            str = file.file_raw_content_hash;
        }
        if ((i & 4) != 0) {
            str2 = file.relative_path;
        }
        if ((i & 8) != 0) {
            j2 = file.lines;
        }
        if ((i & 16) != 0) {
            str3 = file.encoding;
        }
        if ((i & 32) != 0) {
            j3 = file.file_raw_content_size;
        }
        if ((i & 64) != 0) {
            bArr = file.file_raw_content;
        }
        return file.copy(j, str, str2, j2, str3, j3, bArr);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        String str = this.file_raw_content_hash;
        String str2 = this.relative_path;
        long j2 = this.lines;
        String str3 = this.encoding;
        long j3 = this.file_raw_content_size;
        Arrays.toString(this.file_raw_content);
        return "File(id=" + j + ", file_raw_content_hash=" + j + ", relative_path=" + str + ", lines=" + str2 + ", encoding=" + j2 + ", file_raw_content_size=" + j + ", file_raw_content=" + str3 + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (((((((((((result * 31) + this.file_raw_content_hash.hashCode()) * 31) + this.relative_path.hashCode()) * 31) + Long.hashCode(this.lines)) * 31) + (this.encoding == null ? 0 : this.encoding.hashCode())) * 31) + Long.hashCode(this.file_raw_content_size)) * 31) + Arrays.hashCode(this.file_raw_content);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof File)) {
            return false;
        }
        File file = (File) other;
        return this.id == file.id && Intrinsics.areEqual(this.file_raw_content_hash, file.file_raw_content_hash) && Intrinsics.areEqual(this.relative_path, file.relative_path) && this.lines == file.lines && Intrinsics.areEqual(this.encoding, file.encoding) && this.file_raw_content_size == file.file_raw_content_size && Intrinsics.areEqual(this.file_raw_content, file.file_raw_content);
    }

    public File(long id, @NotNull String file_raw_content_hash, @NotNull String relative_path, long lines, @Nullable String encoding, long file_raw_content_size, @NotNull byte[] file_raw_content) {
        Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
        Intrinsics.checkNotNullParameter(relative_path, "relative_path");
        Intrinsics.checkNotNullParameter(file_raw_content, "file_raw_content");
        this.id = id;
        this.file_raw_content_hash = file_raw_content_hash;
        this.relative_path = relative_path;
        this.lines = lines;
        this.encoding = encoding;
        this.file_raw_content_size = file_raw_content_size;
        this.file_raw_content = file_raw_content;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getFile_raw_content_hash() {
        return this.file_raw_content_hash;
    }

    @NotNull
    public final String getRelative_path() {
        return this.relative_path;
    }

    public final long getLines() {
        return this.lines;
    }

    @Nullable
    public final String getEncoding() {
        return this.encoding;
    }

    public final long getFile_raw_content_size() {
        return this.file_raw_content_size;
    }

    @NotNull
    public final byte[] getFile_raw_content() {
        return this.file_raw_content;
    }
}
