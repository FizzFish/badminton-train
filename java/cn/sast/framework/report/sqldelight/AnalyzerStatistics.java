package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalyzerStatistics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b[\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001Bõ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\b\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\b\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\b¢\u0006\u0004\b!\u0010\"J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\bHÆ\u0003J\t\u0010I\u001a\u00020\bHÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\bHÆ\u0003J\t\u0010M\u001a\u00020\bHÆ\u0003J\t\u0010N\u001a\u00020\bHÆ\u0003J\u0010\u0010O\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u00101J\u0010\u0010P\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u00101J\u0010\u0010Q\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u00101J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0003HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\u0003HÆ\u0003J\t\u0010Y\u001a\u00020\u0003HÆ\u0003J\t\u0010Z\u001a\u00020\u0003HÆ\u0003J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\t\u0010\\\u001a\u00020\bHÆ\u0003J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\bHÆ\u0003J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\bHÆ\u0003J¶\u0002\u0010a\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010bJ\u0013\u0010c\u001a\u00020d2\b\u0010e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010f\u001a\u00020gHÖ\u0001J\t\u0010h\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b#\u0010$R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b%\u0010$R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b&\u0010$R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b'\u0010$R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b(\u0010)R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b*\u0010)R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b+\u0010$R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b,\u0010$R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b-\u0010)R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b.\u0010)R\u0011\u0010\u000e\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b/\u0010)R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u00102\u001a\u0004\b3\u00101R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u00102\u001a\u0004\b4\u00101R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b5\u0010$R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b6\u0010$R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b7\u0010$R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b8\u0010$R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b9\u0010$R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b:\u0010$R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b;\u0010$R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b<\u0010$R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b=\u0010$R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b>\u0010$R\u0011\u0010\u001c\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b?\u0010)R\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b@\u0010$R\u0011\u0010\u001e\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\bA\u0010)R\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\bB\u0010$R\u0011\u0010 \u001a\u00020\b¢\u0006\b\n��\u001a\u0004\bC\u0010)¨\u0006i"}, d2 = {"Lcn/sast/framework/report/sqldelight/AnalyzerStatistics;", "", "name", "", "corax_probe_version", "analyzer_version", "analysis_begin_date", "analysis_begin_timestamp", "", "analysis_escape_seconds", "analysis_escape_time", "analysis_end_date", "analysis_end_timestamp", "file_count", "line_count", "code_coverage_covered", "code_coverage_missed", "num_of_report_dir", "source_paths", "os_name", "command_json", "working_directory", "output_path", "project_root", "log_file", "enable_rules", "disable_rules", "failed_sources", "failed_sources_num", "successful_sources", "successful_sources_num", "skipped_sources", "skipped_sources_num", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;JJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getCorax_probe_version", "getAnalyzer_version", "getAnalysis_begin_date", "getAnalysis_begin_timestamp", "()J", "getAnalysis_escape_seconds", "getAnalysis_escape_time", "getAnalysis_end_date", "getAnalysis_end_timestamp", "getFile_count", "getLine_count", "getCode_coverage_covered", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCode_coverage_missed", "getNum_of_report_dir", "getSource_paths", "getOs_name", "getCommand_json", "getWorking_directory", "getOutput_path", "getProject_root", "getLog_file", "getEnable_rules", "getDisable_rules", "getFailed_sources", "getFailed_sources_num", "getSuccessful_sources", "getSuccessful_sources_num", "getSkipped_sources", "getSkipped_sources_num", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;JJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/String;J)Lcn/sast/framework/report/sqldelight/AnalyzerStatistics;", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: AnalyzerStatistics.class */
public final class AnalyzerStatistics {

    @NotNull
    private final String name;

    @NotNull
    private final String corax_probe_version;

    @NotNull
    private final String analyzer_version;

    @NotNull
    private final String analysis_begin_date;
    private final long analysis_begin_timestamp;
    private final long analysis_escape_seconds;

    @NotNull
    private final String analysis_escape_time;

    @NotNull
    private final String analysis_end_date;
    private final long analysis_end_timestamp;
    private final long file_count;
    private final long line_count;

    @Nullable
    private final Long code_coverage_covered;

    @Nullable
    private final Long code_coverage_missed;

    @Nullable
    private final Long num_of_report_dir;

    @NotNull
    private final String source_paths;

    @NotNull
    private final String os_name;

    @NotNull
    private final String command_json;

    @NotNull
    private final String working_directory;

    @NotNull
    private final String output_path;

    @NotNull
    private final String project_root;

    @NotNull
    private final String log_file;

    @NotNull
    private final String enable_rules;

    @NotNull
    private final String disable_rules;

    @NotNull
    private final String failed_sources;
    private final long failed_sources_num;

    @NotNull
    private final String successful_sources;
    private final long successful_sources_num;

    @NotNull
    private final String skipped_sources;
    private final long skipped_sources_num;

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.corax_probe_version;
    }

    @NotNull
    public final String component3() {
        return this.analyzer_version;
    }

    @NotNull
    public final String component4() {
        return this.analysis_begin_date;
    }

    public final long component5() {
        return this.analysis_begin_timestamp;
    }

    public final long component6() {
        return this.analysis_escape_seconds;
    }

    @NotNull
    public final String component7() {
        return this.analysis_escape_time;
    }

    @NotNull
    public final String component8() {
        return this.analysis_end_date;
    }

    public final long component9() {
        return this.analysis_end_timestamp;
    }

    public final long component10() {
        return this.file_count;
    }

    public final long component11() {
        return this.line_count;
    }

    @Nullable
    public final Long component12() {
        return this.code_coverage_covered;
    }

    @Nullable
    public final Long component13() {
        return this.code_coverage_missed;
    }

    @Nullable
    public final Long component14() {
        return this.num_of_report_dir;
    }

    @NotNull
    public final String component15() {
        return this.source_paths;
    }

    @NotNull
    public final String component16() {
        return this.os_name;
    }

    @NotNull
    public final String component17() {
        return this.command_json;
    }

    @NotNull
    public final String component18() {
        return this.working_directory;
    }

    @NotNull
    public final String component19() {
        return this.output_path;
    }

    @NotNull
    public final String component20() {
        return this.project_root;
    }

    @NotNull
    public final String component21() {
        return this.log_file;
    }

    @NotNull
    public final String component22() {
        return this.enable_rules;
    }

    @NotNull
    public final String component23() {
        return this.disable_rules;
    }

    @NotNull
    public final String component24() {
        return this.failed_sources;
    }

    public final long component25() {
        return this.failed_sources_num;
    }

    @NotNull
    public final String component26() {
        return this.successful_sources;
    }

    public final long component27() {
        return this.successful_sources_num;
    }

    @NotNull
    public final String component28() {
        return this.skipped_sources;
    }

    public final long component29() {
        return this.skipped_sources_num;
    }

    @NotNull
    public final AnalyzerStatistics copy(@NotNull String name, @NotNull String corax_probe_version, @NotNull String analyzer_version, @NotNull String analysis_begin_date, long analysis_begin_timestamp, long analysis_escape_seconds, @NotNull String analysis_escape_time, @NotNull String analysis_end_date, long analysis_end_timestamp, long file_count, long line_count, @Nullable Long code_coverage_covered, @Nullable Long code_coverage_missed, @Nullable Long num_of_report_dir, @NotNull String source_paths, @NotNull String os_name, @NotNull String command_json, @NotNull String working_directory, @NotNull String output_path, @NotNull String project_root, @NotNull String log_file, @NotNull String enable_rules, @NotNull String disable_rules, @NotNull String failed_sources, long failed_sources_num, @NotNull String successful_sources, long successful_sources_num, @NotNull String skipped_sources, long skipped_sources_num) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(corax_probe_version, "corax_probe_version");
        Intrinsics.checkNotNullParameter(analyzer_version, "analyzer_version");
        Intrinsics.checkNotNullParameter(analysis_begin_date, "analysis_begin_date");
        Intrinsics.checkNotNullParameter(analysis_escape_time, "analysis_escape_time");
        Intrinsics.checkNotNullParameter(analysis_end_date, "analysis_end_date");
        Intrinsics.checkNotNullParameter(source_paths, "source_paths");
        Intrinsics.checkNotNullParameter(os_name, "os_name");
        Intrinsics.checkNotNullParameter(command_json, "command_json");
        Intrinsics.checkNotNullParameter(working_directory, "working_directory");
        Intrinsics.checkNotNullParameter(output_path, "output_path");
        Intrinsics.checkNotNullParameter(project_root, "project_root");
        Intrinsics.checkNotNullParameter(log_file, "log_file");
        Intrinsics.checkNotNullParameter(enable_rules, "enable_rules");
        Intrinsics.checkNotNullParameter(disable_rules, "disable_rules");
        Intrinsics.checkNotNullParameter(failed_sources, "failed_sources");
        Intrinsics.checkNotNullParameter(successful_sources, "successful_sources");
        Intrinsics.checkNotNullParameter(skipped_sources, "skipped_sources");
        return new AnalyzerStatistics(name, corax_probe_version, analyzer_version, analysis_begin_date, analysis_begin_timestamp, analysis_escape_seconds, analysis_escape_time, analysis_end_date, analysis_end_timestamp, file_count, line_count, code_coverage_covered, code_coverage_missed, num_of_report_dir, source_paths, os_name, command_json, working_directory, output_path, project_root, log_file, enable_rules, disable_rules, failed_sources, failed_sources_num, successful_sources, successful_sources_num, skipped_sources, skipped_sources_num);
    }

    public static /* synthetic */ AnalyzerStatistics copy$default(AnalyzerStatistics analyzerStatistics, String str, String str2, String str3, String str4, long j, long j2, String str5, String str6, long j3, long j4, long j5, Long l, Long l2, Long l3, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, long j6, String str17, long j7, String str18, long j8, int i, Object obj) {
        if ((i & 1) != 0) {
            str = analyzerStatistics.name;
        }
        if ((i & 2) != 0) {
            str2 = analyzerStatistics.corax_probe_version;
        }
        if ((i & 4) != 0) {
            str3 = analyzerStatistics.analyzer_version;
        }
        if ((i & 8) != 0) {
            str4 = analyzerStatistics.analysis_begin_date;
        }
        if ((i & 16) != 0) {
            j = analyzerStatistics.analysis_begin_timestamp;
        }
        if ((i & 32) != 0) {
            j2 = analyzerStatistics.analysis_escape_seconds;
        }
        if ((i & 64) != 0) {
            str5 = analyzerStatistics.analysis_escape_time;
        }
        if ((i & 128) != 0) {
            str6 = analyzerStatistics.analysis_end_date;
        }
        if ((i & 256) != 0) {
            j3 = analyzerStatistics.analysis_end_timestamp;
        }
        if ((i & 512) != 0) {
            j4 = analyzerStatistics.file_count;
        }
        if ((i & 1024) != 0) {
            j5 = analyzerStatistics.line_count;
        }
        if ((i & 2048) != 0) {
            l = analyzerStatistics.code_coverage_covered;
        }
        if ((i & 4096) != 0) {
            l2 = analyzerStatistics.code_coverage_missed;
        }
        if ((i & 8192) != 0) {
            l3 = analyzerStatistics.num_of_report_dir;
        }
        if ((i & 16384) != 0) {
            str7 = analyzerStatistics.source_paths;
        }
        if ((i & 32768) != 0) {
            str8 = analyzerStatistics.os_name;
        }
        if ((i & 65536) != 0) {
            str9 = analyzerStatistics.command_json;
        }
        if ((i & 131072) != 0) {
            str10 = analyzerStatistics.working_directory;
        }
        if ((i & 262144) != 0) {
            str11 = analyzerStatistics.output_path;
        }
        if ((i & 524288) != 0) {
            str12 = analyzerStatistics.project_root;
        }
        if ((i & 1048576) != 0) {
            str13 = analyzerStatistics.log_file;
        }
        if ((i & 2097152) != 0) {
            str14 = analyzerStatistics.enable_rules;
        }
        if ((i & 4194304) != 0) {
            str15 = analyzerStatistics.disable_rules;
        }
        if ((i & 8388608) != 0) {
            str16 = analyzerStatistics.failed_sources;
        }
        if ((i & 16777216) != 0) {
            j6 = analyzerStatistics.failed_sources_num;
        }
        if ((i & 33554432) != 0) {
            str17 = analyzerStatistics.successful_sources;
        }
        if ((i & 67108864) != 0) {
            j7 = analyzerStatistics.successful_sources_num;
        }
        if ((i & 134217728) != 0) {
            str18 = analyzerStatistics.skipped_sources;
        }
        if ((i & 268435456) != 0) {
            j8 = analyzerStatistics.skipped_sources_num;
        }
        return analyzerStatistics.copy(str, str2, str3, str4, j, j2, str5, str6, j3, j4, j5, l, l2, l3, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, j6, str17, j7, str18, j8);
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.corax_probe_version;
        String str3 = this.analyzer_version;
        String str4 = this.analysis_begin_date;
        long j = this.analysis_begin_timestamp;
        long j2 = this.analysis_escape_seconds;
        String str5 = this.analysis_escape_time;
        String str6 = this.analysis_end_date;
        long j3 = this.analysis_end_timestamp;
        long j4 = this.file_count;
        long j5 = this.line_count;
        Long l = this.code_coverage_covered;
        Long l2 = this.code_coverage_missed;
        Long l3 = this.num_of_report_dir;
        String str7 = this.source_paths;
        String str8 = this.os_name;
        String str9 = this.command_json;
        String str10 = this.working_directory;
        String str11 = this.output_path;
        String str12 = this.project_root;
        String str13 = this.log_file;
        String str14 = this.enable_rules;
        String str15 = this.disable_rules;
        String str16 = this.failed_sources;
        long j6 = this.failed_sources_num;
        String str17 = this.successful_sources;
        long j7 = this.successful_sources_num;
        String str18 = this.skipped_sources;
        long j8 = this.skipped_sources_num;
        return "AnalyzerStatistics(name=" + str + ", corax_probe_version=" + str2 + ", analyzer_version=" + str3 + ", analysis_begin_date=" + str4 + ", analysis_begin_timestamp=" + j + ", analysis_escape_seconds=" + str + ", analysis_escape_time=" + j2 + ", analysis_end_date=" + str + ", analysis_end_timestamp=" + str5 + ", file_count=" + str6 + ", line_count=" + j3 + ", code_coverage_covered=" + str + ", code_coverage_missed=" + j4 + ", num_of_report_dir=" + str + ", source_paths=" + j5 + ", os_name=" + str + ", command_json=" + l + ", working_directory=" + l2 + ", output_path=" + l3 + ", project_root=" + str7 + ", log_file=" + str8 + ", enable_rules=" + str9 + ", disable_rules=" + str10 + ", failed_sources=" + str11 + ", failed_sources_num=" + str12 + ", successful_sources=" + str13 + ", successful_sources_num=" + str14 + ", skipped_sources=" + str15 + ", skipped_sources_num=" + str16 + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((result * 31) + this.corax_probe_version.hashCode()) * 31) + this.analyzer_version.hashCode()) * 31) + this.analysis_begin_date.hashCode()) * 31) + Long.hashCode(this.analysis_begin_timestamp)) * 31) + Long.hashCode(this.analysis_escape_seconds)) * 31) + this.analysis_escape_time.hashCode()) * 31) + this.analysis_end_date.hashCode()) * 31) + Long.hashCode(this.analysis_end_timestamp)) * 31) + Long.hashCode(this.file_count)) * 31) + Long.hashCode(this.line_count)) * 31) + (this.code_coverage_covered == null ? 0 : this.code_coverage_covered.hashCode())) * 31) + (this.code_coverage_missed == null ? 0 : this.code_coverage_missed.hashCode())) * 31) + (this.num_of_report_dir == null ? 0 : this.num_of_report_dir.hashCode())) * 31) + this.source_paths.hashCode()) * 31) + this.os_name.hashCode()) * 31) + this.command_json.hashCode()) * 31) + this.working_directory.hashCode()) * 31) + this.output_path.hashCode()) * 31) + this.project_root.hashCode()) * 31) + this.log_file.hashCode()) * 31) + this.enable_rules.hashCode()) * 31) + this.disable_rules.hashCode()) * 31) + this.failed_sources.hashCode()) * 31) + Long.hashCode(this.failed_sources_num)) * 31) + this.successful_sources.hashCode()) * 31) + Long.hashCode(this.successful_sources_num)) * 31) + this.skipped_sources.hashCode()) * 31) + Long.hashCode(this.skipped_sources_num);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalyzerStatistics)) {
            return false;
        }
        AnalyzerStatistics analyzerStatistics = (AnalyzerStatistics) other;
        return Intrinsics.areEqual(this.name, analyzerStatistics.name) && Intrinsics.areEqual(this.corax_probe_version, analyzerStatistics.corax_probe_version) && Intrinsics.areEqual(this.analyzer_version, analyzerStatistics.analyzer_version) && Intrinsics.areEqual(this.analysis_begin_date, analyzerStatistics.analysis_begin_date) && this.analysis_begin_timestamp == analyzerStatistics.analysis_begin_timestamp && this.analysis_escape_seconds == analyzerStatistics.analysis_escape_seconds && Intrinsics.areEqual(this.analysis_escape_time, analyzerStatistics.analysis_escape_time) && Intrinsics.areEqual(this.analysis_end_date, analyzerStatistics.analysis_end_date) && this.analysis_end_timestamp == analyzerStatistics.analysis_end_timestamp && this.file_count == analyzerStatistics.file_count && this.line_count == analyzerStatistics.line_count && Intrinsics.areEqual(this.code_coverage_covered, analyzerStatistics.code_coverage_covered) && Intrinsics.areEqual(this.code_coverage_missed, analyzerStatistics.code_coverage_missed) && Intrinsics.areEqual(this.num_of_report_dir, analyzerStatistics.num_of_report_dir) && Intrinsics.areEqual(this.source_paths, analyzerStatistics.source_paths) && Intrinsics.areEqual(this.os_name, analyzerStatistics.os_name) && Intrinsics.areEqual(this.command_json, analyzerStatistics.command_json) && Intrinsics.areEqual(this.working_directory, analyzerStatistics.working_directory) && Intrinsics.areEqual(this.output_path, analyzerStatistics.output_path) && Intrinsics.areEqual(this.project_root, analyzerStatistics.project_root) && Intrinsics.areEqual(this.log_file, analyzerStatistics.log_file) && Intrinsics.areEqual(this.enable_rules, analyzerStatistics.enable_rules) && Intrinsics.areEqual(this.disable_rules, analyzerStatistics.disable_rules) && Intrinsics.areEqual(this.failed_sources, analyzerStatistics.failed_sources) && this.failed_sources_num == analyzerStatistics.failed_sources_num && Intrinsics.areEqual(this.successful_sources, analyzerStatistics.successful_sources) && this.successful_sources_num == analyzerStatistics.successful_sources_num && Intrinsics.areEqual(this.skipped_sources, analyzerStatistics.skipped_sources) && this.skipped_sources_num == analyzerStatistics.skipped_sources_num;
    }

    public AnalyzerStatistics(@NotNull String name, @NotNull String corax_probe_version, @NotNull String analyzer_version, @NotNull String analysis_begin_date, long analysis_begin_timestamp, long analysis_escape_seconds, @NotNull String analysis_escape_time, @NotNull String analysis_end_date, long analysis_end_timestamp, long file_count, long line_count, @Nullable Long code_coverage_covered, @Nullable Long code_coverage_missed, @Nullable Long num_of_report_dir, @NotNull String source_paths, @NotNull String os_name, @NotNull String command_json, @NotNull String working_directory, @NotNull String output_path, @NotNull String project_root, @NotNull String log_file, @NotNull String enable_rules, @NotNull String disable_rules, @NotNull String failed_sources, long failed_sources_num, @NotNull String successful_sources, long successful_sources_num, @NotNull String skipped_sources, long skipped_sources_num) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(corax_probe_version, "corax_probe_version");
        Intrinsics.checkNotNullParameter(analyzer_version, "analyzer_version");
        Intrinsics.checkNotNullParameter(analysis_begin_date, "analysis_begin_date");
        Intrinsics.checkNotNullParameter(analysis_escape_time, "analysis_escape_time");
        Intrinsics.checkNotNullParameter(analysis_end_date, "analysis_end_date");
        Intrinsics.checkNotNullParameter(source_paths, "source_paths");
        Intrinsics.checkNotNullParameter(os_name, "os_name");
        Intrinsics.checkNotNullParameter(command_json, "command_json");
        Intrinsics.checkNotNullParameter(working_directory, "working_directory");
        Intrinsics.checkNotNullParameter(output_path, "output_path");
        Intrinsics.checkNotNullParameter(project_root, "project_root");
        Intrinsics.checkNotNullParameter(log_file, "log_file");
        Intrinsics.checkNotNullParameter(enable_rules, "enable_rules");
        Intrinsics.checkNotNullParameter(disable_rules, "disable_rules");
        Intrinsics.checkNotNullParameter(failed_sources, "failed_sources");
        Intrinsics.checkNotNullParameter(successful_sources, "successful_sources");
        Intrinsics.checkNotNullParameter(skipped_sources, "skipped_sources");
        this.name = name;
        this.corax_probe_version = corax_probe_version;
        this.analyzer_version = analyzer_version;
        this.analysis_begin_date = analysis_begin_date;
        this.analysis_begin_timestamp = analysis_begin_timestamp;
        this.analysis_escape_seconds = analysis_escape_seconds;
        this.analysis_escape_time = analysis_escape_time;
        this.analysis_end_date = analysis_end_date;
        this.analysis_end_timestamp = analysis_end_timestamp;
        this.file_count = file_count;
        this.line_count = line_count;
        this.code_coverage_covered = code_coverage_covered;
        this.code_coverage_missed = code_coverage_missed;
        this.num_of_report_dir = num_of_report_dir;
        this.source_paths = source_paths;
        this.os_name = os_name;
        this.command_json = command_json;
        this.working_directory = working_directory;
        this.output_path = output_path;
        this.project_root = project_root;
        this.log_file = log_file;
        this.enable_rules = enable_rules;
        this.disable_rules = disable_rules;
        this.failed_sources = failed_sources;
        this.failed_sources_num = failed_sources_num;
        this.successful_sources = successful_sources;
        this.successful_sources_num = successful_sources_num;
        this.skipped_sources = skipped_sources;
        this.skipped_sources_num = skipped_sources_num;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getCorax_probe_version() {
        return this.corax_probe_version;
    }

    @NotNull
    public final String getAnalyzer_version() {
        return this.analyzer_version;
    }

    @NotNull
    public final String getAnalysis_begin_date() {
        return this.analysis_begin_date;
    }

    public final long getAnalysis_begin_timestamp() {
        return this.analysis_begin_timestamp;
    }

    public final long getAnalysis_escape_seconds() {
        return this.analysis_escape_seconds;
    }

    @NotNull
    public final String getAnalysis_escape_time() {
        return this.analysis_escape_time;
    }

    @NotNull
    public final String getAnalysis_end_date() {
        return this.analysis_end_date;
    }

    public final long getAnalysis_end_timestamp() {
        return this.analysis_end_timestamp;
    }

    public final long getFile_count() {
        return this.file_count;
    }

    public final long getLine_count() {
        return this.line_count;
    }

    @Nullable
    public final Long getCode_coverage_covered() {
        return this.code_coverage_covered;
    }

    @Nullable
    public final Long getCode_coverage_missed() {
        return this.code_coverage_missed;
    }

    @Nullable
    public final Long getNum_of_report_dir() {
        return this.num_of_report_dir;
    }

    @NotNull
    public final String getSource_paths() {
        return this.source_paths;
    }

    @NotNull
    public final String getOs_name() {
        return this.os_name;
    }

    @NotNull
    public final String getCommand_json() {
        return this.command_json;
    }

    @NotNull
    public final String getWorking_directory() {
        return this.working_directory;
    }

    @NotNull
    public final String getOutput_path() {
        return this.output_path;
    }

    @NotNull
    public final String getProject_root() {
        return this.project_root;
    }

    @NotNull
    public final String getLog_file() {
        return this.log_file;
    }

    @NotNull
    public final String getEnable_rules() {
        return this.enable_rules;
    }

    @NotNull
    public final String getDisable_rules() {
        return this.disable_rules;
    }

    @NotNull
    public final String getFailed_sources() {
        return this.failed_sources;
    }

    public final long getFailed_sources_num() {
        return this.failed_sources_num;
    }

    @NotNull
    public final String getSuccessful_sources() {
        return this.successful_sources;
    }

    public final long getSuccessful_sources_num() {
        return this.successful_sources_num;
    }

    @NotNull
    public final String getSkipped_sources() {
        return this.skipped_sources;
    }

    public final long getSkipped_sources_num() {
        return this.skipped_sources_num;
    }
}
