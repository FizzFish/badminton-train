package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Diagnostic.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001Bµ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJâ\u0001\u0010C\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020IHÖ\u0001J\t\u0010J\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001cR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b!\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b$\u0010\u001cR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b%\u0010\u001cR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b&\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b'\u0010\u001cR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b(\u0010\u001cR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b)\u0010\u001cR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b*\u0010\u001cR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b+\u0010\u001cR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b,\u0010\u001cR\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b-\u0010\u001aR\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010 \u001a\u0004\b.\u0010\u001fR\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010 \u001a\u0004\b/\u0010\u001f¨\u0006K"}, d2 = {"Lcn/sast/framework/report/sqldelight/Diagnostic;", "", "id", "", "rule_name", "", "_rule_short_description_zh", "__file_id", "_file_abs_path", "_line", "_column", "_message_en", "_message_zh", "severity", "precision", "likelihood", "impact", "technique", "analysis_scope", "line_content", "__note_array_hash_id", "__control_flow_array_hash_id", "__macro_note_set_hash_id", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)V", "getId", "()J", "getRule_name", "()Ljava/lang/String;", "get_rule_short_description_zh", "get__file_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "get_file_abs_path", "get_line", "get_column", "get_message_en", "get_message_zh", "getSeverity", "getPrecision", "getLikelihood", "getImpact", "getTechnique", "getAnalysis_scope", "getLine_content", "get__note_array_hash_id", "get__control_flow_array_hash_id", "get__macro_note_set_hash_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)Lcn/sast/framework/report/sqldelight/Diagnostic;", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: Diagnostic.class */
public final class Diagnostic {
    private final long id;

    @NotNull
    private final String rule_name;

    @Nullable
    private final String _rule_short_description_zh;

    @Nullable
    private final Long __file_id;

    @NotNull
    private final String _file_abs_path;
    private final long _line;
    private final long _column;

    @NotNull
    private final String _message_en;

    @NotNull
    private final String _message_zh;

    @Nullable
    private final String severity;

    @Nullable
    private final String precision;

    @Nullable
    private final String likelihood;

    @Nullable
    private final String impact;

    @Nullable
    private final String technique;

    @Nullable
    private final String analysis_scope;

    @Nullable
    private final String line_content;
    private final long __note_array_hash_id;

    @Nullable
    private final Long __control_flow_array_hash_id;

    @Nullable
    private final Long __macro_note_set_hash_id;

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.rule_name;
    }

    @Nullable
    public final String component3() {
        return this._rule_short_description_zh;
    }

    @Nullable
    public final Long component4() {
        return this.__file_id;
    }

    @NotNull
    public final String component5() {
        return this._file_abs_path;
    }

    public final long component6() {
        return this._line;
    }

    public final long component7() {
        return this._column;
    }

    @NotNull
    public final String component8() {
        return this._message_en;
    }

    @NotNull
    public final String component9() {
        return this._message_zh;
    }

    @Nullable
    public final String component10() {
        return this.severity;
    }

    @Nullable
    public final String component11() {
        return this.precision;
    }

    @Nullable
    public final String component12() {
        return this.likelihood;
    }

    @Nullable
    public final String component13() {
        return this.impact;
    }

    @Nullable
    public final String component14() {
        return this.technique;
    }

    @Nullable
    public final String component15() {
        return this.analysis_scope;
    }

    @Nullable
    public final String component16() {
        return this.line_content;
    }

    public final long component17() {
        return this.__note_array_hash_id;
    }

    @Nullable
    public final Long component18() {
        return this.__control_flow_array_hash_id;
    }

    @Nullable
    public final Long component19() {
        return this.__macro_note_set_hash_id;
    }

    @NotNull
    public final Diagnostic copy(long id, @NotNull String rule_name, @Nullable String _rule_short_description_zh, @Nullable Long __file_id, @NotNull String _file_abs_path, long _line, long _column, @NotNull String _message_en, @NotNull String _message_zh, @Nullable String severity, @Nullable String precision, @Nullable String likelihood, @Nullable String impact, @Nullable String technique, @Nullable String analysis_scope, @Nullable String line_content, long __note_array_hash_id, @Nullable Long __control_flow_array_hash_id, @Nullable Long __macro_note_set_hash_id) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(_message_en, "_message_en");
        Intrinsics.checkNotNullParameter(_message_zh, "_message_zh");
        return new Diagnostic(id, rule_name, _rule_short_description_zh, __file_id, _file_abs_path, _line, _column, _message_en, _message_zh, severity, precision, likelihood, impact, technique, analysis_scope, line_content, __note_array_hash_id, __control_flow_array_hash_id, __macro_note_set_hash_id);
    }

    public static /* synthetic */ Diagnostic copy$default(Diagnostic diagnostic, long j, String str, String str2, Long l, String str3, long j2, long j3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, long j4, Long l2, Long l3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = diagnostic.id;
        }
        if ((i & 2) != 0) {
            str = diagnostic.rule_name;
        }
        if ((i & 4) != 0) {
            str2 = diagnostic._rule_short_description_zh;
        }
        if ((i & 8) != 0) {
            l = diagnostic.__file_id;
        }
        if ((i & 16) != 0) {
            str3 = diagnostic._file_abs_path;
        }
        if ((i & 32) != 0) {
            j2 = diagnostic._line;
        }
        if ((i & 64) != 0) {
            j3 = diagnostic._column;
        }
        if ((i & 128) != 0) {
            str4 = diagnostic._message_en;
        }
        if ((i & 256) != 0) {
            str5 = diagnostic._message_zh;
        }
        if ((i & 512) != 0) {
            str6 = diagnostic.severity;
        }
        if ((i & 1024) != 0) {
            str7 = diagnostic.precision;
        }
        if ((i & 2048) != 0) {
            str8 = diagnostic.likelihood;
        }
        if ((i & 4096) != 0) {
            str9 = diagnostic.impact;
        }
        if ((i & 8192) != 0) {
            str10 = diagnostic.technique;
        }
        if ((i & 16384) != 0) {
            str11 = diagnostic.analysis_scope;
        }
        if ((i & 32768) != 0) {
            str12 = diagnostic.line_content;
        }
        if ((i & 65536) != 0) {
            j4 = diagnostic.__note_array_hash_id;
        }
        if ((i & 131072) != 0) {
            l2 = diagnostic.__control_flow_array_hash_id;
        }
        if ((i & 262144) != 0) {
            l3 = diagnostic.__macro_note_set_hash_id;
        }
        return diagnostic.copy(j, str, str2, l, str3, j2, j3, str4, str5, str6, str7, str8, str9, str10, str11, str12, j4, l2, l3);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        String str = this.rule_name;
        String str2 = this._rule_short_description_zh;
        Long l = this.__file_id;
        String str3 = this._file_abs_path;
        long j2 = this._line;
        long j3 = this._column;
        String str4 = this._message_en;
        String str5 = this._message_zh;
        String str6 = this.severity;
        String str7 = this.precision;
        String str8 = this.likelihood;
        String str9 = this.impact;
        String str10 = this.technique;
        String str11 = this.analysis_scope;
        String str12 = this.line_content;
        long j4 = this.__note_array_hash_id;
        Long l2 = this.__control_flow_array_hash_id;
        Long l3 = this.__macro_note_set_hash_id;
        return "Diagnostic(id=" + j + ", rule_name=" + j + ", _rule_short_description_zh=" + str + ", __file_id=" + str2 + ", _file_abs_path=" + l + ", _line=" + str3 + ", _column=" + j2 + ", _message_en=" + j + ", _message_zh=" + j3 + ", severity=" + j + ", precision=" + str4 + ", likelihood=" + str5 + ", impact=" + str6 + ", technique=" + str7 + ", analysis_scope=" + str8 + ", line_content=" + str9 + ", __note_array_hash_id=" + str10 + ", __control_flow_array_hash_id=" + str11 + ", __macro_note_set_hash_id=" + str12 + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (((((((((((((((((((((((((((((((((((result * 31) + this.rule_name.hashCode()) * 31) + (this._rule_short_description_zh == null ? 0 : this._rule_short_description_zh.hashCode())) * 31) + (this.__file_id == null ? 0 : this.__file_id.hashCode())) * 31) + this._file_abs_path.hashCode()) * 31) + Long.hashCode(this._line)) * 31) + Long.hashCode(this._column)) * 31) + this._message_en.hashCode()) * 31) + this._message_zh.hashCode()) * 31) + (this.severity == null ? 0 : this.severity.hashCode())) * 31) + (this.precision == null ? 0 : this.precision.hashCode())) * 31) + (this.likelihood == null ? 0 : this.likelihood.hashCode())) * 31) + (this.impact == null ? 0 : this.impact.hashCode())) * 31) + (this.technique == null ? 0 : this.technique.hashCode())) * 31) + (this.analysis_scope == null ? 0 : this.analysis_scope.hashCode())) * 31) + (this.line_content == null ? 0 : this.line_content.hashCode())) * 31) + Long.hashCode(this.__note_array_hash_id)) * 31) + (this.__control_flow_array_hash_id == null ? 0 : this.__control_flow_array_hash_id.hashCode())) * 31) + (this.__macro_note_set_hash_id == null ? 0 : this.__macro_note_set_hash_id.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Diagnostic)) {
            return false;
        }
        Diagnostic diagnostic = (Diagnostic) other;
        return this.id == diagnostic.id && Intrinsics.areEqual(this.rule_name, diagnostic.rule_name) && Intrinsics.areEqual(this._rule_short_description_zh, diagnostic._rule_short_description_zh) && Intrinsics.areEqual(this.__file_id, diagnostic.__file_id) && Intrinsics.areEqual(this._file_abs_path, diagnostic._file_abs_path) && this._line == diagnostic._line && this._column == diagnostic._column && Intrinsics.areEqual(this._message_en, diagnostic._message_en) && Intrinsics.areEqual(this._message_zh, diagnostic._message_zh) && Intrinsics.areEqual(this.severity, diagnostic.severity) && Intrinsics.areEqual(this.precision, diagnostic.precision) && Intrinsics.areEqual(this.likelihood, diagnostic.likelihood) && Intrinsics.areEqual(this.impact, diagnostic.impact) && Intrinsics.areEqual(this.technique, diagnostic.technique) && Intrinsics.areEqual(this.analysis_scope, diagnostic.analysis_scope) && Intrinsics.areEqual(this.line_content, diagnostic.line_content) && this.__note_array_hash_id == diagnostic.__note_array_hash_id && Intrinsics.areEqual(this.__control_flow_array_hash_id, diagnostic.__control_flow_array_hash_id) && Intrinsics.areEqual(this.__macro_note_set_hash_id, diagnostic.__macro_note_set_hash_id);
    }

    public Diagnostic(long id, @NotNull String rule_name, @Nullable String _rule_short_description_zh, @Nullable Long __file_id, @NotNull String _file_abs_path, long _line, long _column, @NotNull String _message_en, @NotNull String _message_zh, @Nullable String severity, @Nullable String precision, @Nullable String likelihood, @Nullable String impact, @Nullable String technique, @Nullable String analysis_scope, @Nullable String line_content, long __note_array_hash_id, @Nullable Long __control_flow_array_hash_id, @Nullable Long __macro_note_set_hash_id) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(_message_en, "_message_en");
        Intrinsics.checkNotNullParameter(_message_zh, "_message_zh");
        this.id = id;
        this.rule_name = rule_name;
        this._rule_short_description_zh = _rule_short_description_zh;
        this.__file_id = __file_id;
        this._file_abs_path = _file_abs_path;
        this._line = _line;
        this._column = _column;
        this._message_en = _message_en;
        this._message_zh = _message_zh;
        this.severity = severity;
        this.precision = precision;
        this.likelihood = likelihood;
        this.impact = impact;
        this.technique = technique;
        this.analysis_scope = analysis_scope;
        this.line_content = line_content;
        this.__note_array_hash_id = __note_array_hash_id;
        this.__control_flow_array_hash_id = __control_flow_array_hash_id;
        this.__macro_note_set_hash_id = __macro_note_set_hash_id;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getRule_name() {
        return this.rule_name;
    }

    @Nullable
    public final String get_rule_short_description_zh() {
        return this._rule_short_description_zh;
    }

    @Nullable
    public final Long get__file_id() {
        return this.__file_id;
    }

    @NotNull
    public final String get_file_abs_path() {
        return this._file_abs_path;
    }

    public final long get_line() {
        return this._line;
    }

    public final long get_column() {
        return this._column;
    }

    @NotNull
    public final String get_message_en() {
        return this._message_en;
    }

    @NotNull
    public final String get_message_zh() {
        return this._message_zh;
    }

    @Nullable
    public final String getSeverity() {
        return this.severity;
    }

    @Nullable
    public final String getPrecision() {
        return this.precision;
    }

    @Nullable
    public final String getLikelihood() {
        return this.likelihood;
    }

    @Nullable
    public final String getImpact() {
        return this.impact;
    }

    @Nullable
    public final String getTechnique() {
        return this.technique;
    }

    @Nullable
    public final String getAnalysis_scope() {
        return this.analysis_scope;
    }

    @Nullable
    public final String getLine_content() {
        return this.line_content;
    }

    public final long get__note_array_hash_id() {
        return this.__note_array_hash_id;
    }

    @Nullable
    public final Long get__control_flow_array_hash_id() {
        return this.__control_flow_array_hash_id;
    }

    @Nullable
    public final Long get__macro_note_set_hash_id() {
        return this.__macro_note_set_hash_id;
    }
}
