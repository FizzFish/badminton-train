package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Note.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0082\u0001\u0010+\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0012R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u0014R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u001aR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001f\u0010\u001a¨\u00063"}, d2 = {"Lcn/sast/framework/report/sqldelight/Note;", "", "id", "", "kind", "", "display_hint", "__file_id", "_file_abs_path", "line", "column", "message_en", "message_zh", "__notices_region_id", "__func_region_id", "<init>", "(JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "getId", "()J", "getKind", "()Ljava/lang/String;", "getDisplay_hint", "get__file_id", "get_file_abs_path", "getLine", "getColumn", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMessage_en", "getMessage_zh", "get__notices_region_id", "get__func_region_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lcn/sast/framework/report/sqldelight/Note;", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: Note.class */
public final class Note {
    private final long id;

    @NotNull
    private final String kind;

    @NotNull
    private final String display_hint;
    private final long __file_id;

    @NotNull
    private final String _file_abs_path;
    private final long line;

    @Nullable
    private final Long column;

    @NotNull
    private final String message_en;

    @NotNull
    private final String message_zh;

    @Nullable
    private final Long __notices_region_id;

    @Nullable
    private final Long __func_region_id;

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.kind;
    }

    @NotNull
    public final String component3() {
        return this.display_hint;
    }

    public final long component4() {
        return this.__file_id;
    }

    @NotNull
    public final String component5() {
        return this._file_abs_path;
    }

    public final long component6() {
        return this.line;
    }

    @Nullable
    public final Long component7() {
        return this.column;
    }

    @NotNull
    public final String component8() {
        return this.message_en;
    }

    @NotNull
    public final String component9() {
        return this.message_zh;
    }

    @Nullable
    public final Long component10() {
        return this.__notices_region_id;
    }

    @Nullable
    public final Long component11() {
        return this.__func_region_id;
    }

    @NotNull
    public final Note copy(long id, @NotNull String kind, @NotNull String display_hint, long __file_id, @NotNull String _file_abs_path, long line, @Nullable Long column, @NotNull String message_en, @NotNull String message_zh, @Nullable Long __notices_region_id, @Nullable Long __func_region_id) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(display_hint, "display_hint");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(message_en, "message_en");
        Intrinsics.checkNotNullParameter(message_zh, "message_zh");
        return new Note(id, kind, display_hint, __file_id, _file_abs_path, line, column, message_en, message_zh, __notices_region_id, __func_region_id);
    }

    public static /* synthetic */ Note copy$default(Note note, long j, String str, String str2, long j2, String str3, long j3, Long l, String str4, String str5, Long l2, Long l3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = note.id;
        }
        if ((i & 2) != 0) {
            str = note.kind;
        }
        if ((i & 4) != 0) {
            str2 = note.display_hint;
        }
        if ((i & 8) != 0) {
            j2 = note.__file_id;
        }
        if ((i & 16) != 0) {
            str3 = note._file_abs_path;
        }
        if ((i & 32) != 0) {
            j3 = note.line;
        }
        if ((i & 64) != 0) {
            l = note.column;
        }
        if ((i & 128) != 0) {
            str4 = note.message_en;
        }
        if ((i & 256) != 0) {
            str5 = note.message_zh;
        }
        if ((i & 512) != 0) {
            l2 = note.__notices_region_id;
        }
        if ((i & 1024) != 0) {
            l3 = note.__func_region_id;
        }
        return note.copy(j, str, str2, j2, str3, j3, l, str4, str5, l2, l3);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        String str = this.kind;
        String str2 = this.display_hint;
        long j2 = this.__file_id;
        String str3 = this._file_abs_path;
        long j3 = this.line;
        Long l = this.column;
        String str4 = this.message_en;
        String str5 = this.message_zh;
        Long l2 = this.__notices_region_id;
        Long l3 = this.__func_region_id;
        return "Note(id=" + j + ", kind=" + j + ", display_hint=" + str + ", __file_id=" + str2 + ", _file_abs_path=" + j2 + ", line=" + j + ", column=" + str3 + ", message_en=" + j3 + ", message_zh=" + j + ", __notices_region_id=" + l + ", __func_region_id=" + str4 + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (((((((((((((((((((result * 31) + this.kind.hashCode()) * 31) + this.display_hint.hashCode()) * 31) + Long.hashCode(this.__file_id)) * 31) + this._file_abs_path.hashCode()) * 31) + Long.hashCode(this.line)) * 31) + (this.column == null ? 0 : this.column.hashCode())) * 31) + this.message_en.hashCode()) * 31) + this.message_zh.hashCode()) * 31) + (this.__notices_region_id == null ? 0 : this.__notices_region_id.hashCode())) * 31) + (this.__func_region_id == null ? 0 : this.__func_region_id.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Note)) {
            return false;
        }
        Note note = (Note) other;
        return this.id == note.id && Intrinsics.areEqual(this.kind, note.kind) && Intrinsics.areEqual(this.display_hint, note.display_hint) && this.__file_id == note.__file_id && Intrinsics.areEqual(this._file_abs_path, note._file_abs_path) && this.line == note.line && Intrinsics.areEqual(this.column, note.column) && Intrinsics.areEqual(this.message_en, note.message_en) && Intrinsics.areEqual(this.message_zh, note.message_zh) && Intrinsics.areEqual(this.__notices_region_id, note.__notices_region_id) && Intrinsics.areEqual(this.__func_region_id, note.__func_region_id);
    }

    public Note(long id, @NotNull String kind, @NotNull String display_hint, long __file_id, @NotNull String _file_abs_path, long line, @Nullable Long column, @NotNull String message_en, @NotNull String message_zh, @Nullable Long __notices_region_id, @Nullable Long __func_region_id) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(display_hint, "display_hint");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(message_en, "message_en");
        Intrinsics.checkNotNullParameter(message_zh, "message_zh");
        this.id = id;
        this.kind = kind;
        this.display_hint = display_hint;
        this.__file_id = __file_id;
        this._file_abs_path = _file_abs_path;
        this.line = line;
        this.column = column;
        this.message_en = message_en;
        this.message_zh = message_zh;
        this.__notices_region_id = __notices_region_id;
        this.__func_region_id = __func_region_id;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getKind() {
        return this.kind;
    }

    @NotNull
    public final String getDisplay_hint() {
        return this.display_hint;
    }

    public final long get__file_id() {
        return this.__file_id;
    }

    @NotNull
    public final String get_file_abs_path() {
        return this._file_abs_path;
    }

    public final long getLine() {
        return this.line;
    }

    @Nullable
    public final Long getColumn() {
        return this.column;
    }

    @NotNull
    public final String getMessage_en() {
        return this.message_en;
    }

    @NotNull
    public final String getMessage_zh() {
        return this.message_zh;
    }

    @Nullable
    public final Long get__notices_region_id() {
        return this.__notices_region_id;
    }

    @Nullable
    public final Long get__func_region_id() {
        return this.__func_region_id;
    }
}
