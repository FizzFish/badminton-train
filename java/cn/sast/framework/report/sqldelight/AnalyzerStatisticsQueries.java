package cn.sast.framework.report.sqldelight;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.FunctionN;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnalyzerStatisticsQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0092\u0005\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2ô\u0004\u0010\n\u001aï\u0004\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\b0\u000b¢\u0006\u0002\u0010,J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020-0\u0007J\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020-¨\u00061"}, d2 = {"Lcn/sast/framework/report/sqldelight/AnalyzerStatisticsQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function29;", "", "Lkotlin/ParameterName;", "name", "corax_probe_version", "analyzer_version", "analysis_begin_date", "", "analysis_begin_timestamp", "analysis_escape_seconds", "analysis_escape_time", "analysis_end_date", "analysis_end_timestamp", "file_count", "line_count", "code_coverage_covered", "code_coverage_missed", "num_of_report_dir", "source_paths", "os_name", "command_json", "working_directory", "output_path", "project_root", "log_file", "enable_rules", "disable_rules", "failed_sources", "failed_sources_num", "successful_sources", "successful_sources_num", "skipped_sources", "skipped_sources_num", "(Lkotlin/jvm/functions/FunctionN;)Lapp/cash/sqldelight/Query;", "Lcn/sast/framework/report/sqldelight/AnalyzerStatistics;", "insert", "", "AnalyzerStatistics", "corax-framework"})
/* loaded from: AnalyzerStatisticsQueries.class */
public final class AnalyzerStatisticsQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnalyzerStatisticsQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull FunctionN<? extends T> functionN) {
        Intrinsics.checkNotNullParameter(functionN, "mapper");
        return QueryKt.Query(2087699017, new String[]{"AnalyzerStatistics"}, getDriver(), "AnalyzerStatistics.sq", "selectAll", "SELECT AnalyzerStatistics.name, AnalyzerStatistics.corax_probe_version, AnalyzerStatistics.analyzer_version, AnalyzerStatistics.analysis_begin_date, AnalyzerStatistics.analysis_begin_timestamp, AnalyzerStatistics.analysis_escape_seconds, AnalyzerStatistics.analysis_escape_time, AnalyzerStatistics.analysis_end_date, AnalyzerStatistics.analysis_end_timestamp, AnalyzerStatistics.file_count, AnalyzerStatistics.line_count, AnalyzerStatistics.code_coverage_covered, AnalyzerStatistics.code_coverage_missed, AnalyzerStatistics.num_of_report_dir, AnalyzerStatistics.source_paths, AnalyzerStatistics.os_name, AnalyzerStatistics.command_json, AnalyzerStatistics.working_directory, AnalyzerStatistics.output_path, AnalyzerStatistics.project_root, AnalyzerStatistics.log_file, AnalyzerStatistics.enable_rules, AnalyzerStatistics.disable_rules, AnalyzerStatistics.failed_sources, AnalyzerStatistics.failed_sources_num, AnalyzerStatistics.successful_sources, AnalyzerStatistics.successful_sources_num, AnalyzerStatistics.skipped_sources, AnalyzerStatistics.skipped_sources_num\nFROM AnalyzerStatistics", (v1) -> {
            return selectAll$lambda$0(r6, v1);
        });
    }

    private static final Object selectAll$lambda$0(FunctionN $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Intrinsics.checkNotNull(string2);
        String string3 = cursor.getString(2);
        Intrinsics.checkNotNull(string3);
        String string4 = cursor.getString(3);
        Intrinsics.checkNotNull(string4);
        Long l = cursor.getLong(4);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(5);
        Intrinsics.checkNotNull(l2);
        String string5 = cursor.getString(6);
        Intrinsics.checkNotNull(string5);
        String string6 = cursor.getString(7);
        Intrinsics.checkNotNull(string6);
        Long l3 = cursor.getLong(8);
        Intrinsics.checkNotNull(l3);
        Long l4 = cursor.getLong(9);
        Intrinsics.checkNotNull(l4);
        Long l5 = cursor.getLong(10);
        Intrinsics.checkNotNull(l5);
        String string7 = cursor.getString(14);
        Intrinsics.checkNotNull(string7);
        String string8 = cursor.getString(15);
        Intrinsics.checkNotNull(string8);
        String string9 = cursor.getString(16);
        Intrinsics.checkNotNull(string9);
        String string10 = cursor.getString(17);
        Intrinsics.checkNotNull(string10);
        String string11 = cursor.getString(18);
        Intrinsics.checkNotNull(string11);
        String string12 = cursor.getString(19);
        Intrinsics.checkNotNull(string12);
        String string13 = cursor.getString(20);
        Intrinsics.checkNotNull(string13);
        String string14 = cursor.getString(21);
        Intrinsics.checkNotNull(string14);
        String string15 = cursor.getString(22);
        Intrinsics.checkNotNull(string15);
        String string16 = cursor.getString(23);
        Intrinsics.checkNotNull(string16);
        Long l6 = cursor.getLong(24);
        Intrinsics.checkNotNull(l6);
        String string17 = cursor.getString(25);
        Intrinsics.checkNotNull(string17);
        Long l7 = cursor.getLong(26);
        Intrinsics.checkNotNull(l7);
        String string18 = cursor.getString(27);
        Intrinsics.checkNotNull(string18);
        Long l8 = cursor.getLong(28);
        Intrinsics.checkNotNull(l8);
        return $mapper.invoke(new Object[]{string, string2, string3, string4, l, l2, string5, string6, l3, l4, l5, cursor.getLong(11), cursor.getLong(12), cursor.getLong(13), string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, l6, string17, l7, string18, l8});
    }

    @NotNull
    public final Query<AnalyzerStatistics> selectAll() {
        return selectAll(new FunctionN<AnalyzerStatistics>() { // from class: cn.sast.framework.report.sqldelight.AnalyzerStatisticsQueries.selectAll.2
            public final /* bridge */ /* synthetic */ Object invoke(Object[] args) {
                if (args.length != 29) {
                    throw new IllegalArgumentException("Expected 29 arguments");
                }
                return invoke((String) args[0], (String) args[1], (String) args[2], (String) args[3], ((Number) args[4]).longValue(), ((Number) args[5]).longValue(), (String) args[6], (String) args[7], ((Number) args[8]).longValue(), ((Number) args[9]).longValue(), ((Number) args[10]).longValue(), (Long) args[11], (Long) args[12], (Long) args[13], (String) args[14], (String) args[15], (String) args[16], (String) args[17], (String) args[18], (String) args[19], (String) args[20], (String) args[21], (String) args[22], (String) args[23], ((Number) args[24]).longValue(), (String) args[25], ((Number) args[26]).longValue(), (String) args[27], ((Number) args[28]).longValue());
            }

            public final AnalyzerStatistics invoke(String name, String corax_probe_version, String analyzer_version, String analysis_begin_date, long analysis_begin_timestamp, long analysis_escape_seconds, String analysis_escape_time, String analysis_end_date, long analysis_end_timestamp, long file_count, long line_count, Long code_coverage_covered, Long code_coverage_missed, Long num_of_report_dir, String source_paths, String os_name, String command_json, String working_directory, String output_path, String project_root, String log_file, String enable_rules, String disable_rules, String failed_sources, long failed_sources_num, String successful_sources, long successful_sources_num, String skipped_sources, long skipped_sources_num) {
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
        });
    }

    public final void insert(@NotNull AnalyzerStatistics AnalyzerStatistics) {
        Intrinsics.checkNotNullParameter(AnalyzerStatistics, "AnalyzerStatistics");
        getDriver().execute(402637301, "INSERT OR IGNORE INTO AnalyzerStatistics (name, corax_probe_version, analyzer_version, analysis_begin_date, analysis_begin_timestamp, analysis_escape_seconds, analysis_escape_time, analysis_end_date, analysis_end_timestamp, file_count, line_count, code_coverage_covered, code_coverage_missed, num_of_report_dir, source_paths, os_name, command_json, working_directory, output_path, project_root, log_file, enable_rules, disable_rules, failed_sources, failed_sources_num, successful_sources, successful_sources_num, skipped_sources, skipped_sources_num)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 29, (v1) -> {
            return insert$lambda$1(r4, v1);
        });
        notifyQueries(402637301, AnalyzerStatisticsQueries::insert$lambda$2);
    }

    private static final Unit insert$lambda$1(AnalyzerStatistics $AnalyzerStatistics, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $AnalyzerStatistics.getName());
        $this$execute.bindString(1, $AnalyzerStatistics.getCorax_probe_version());
        $this$execute.bindString(2, $AnalyzerStatistics.getAnalyzer_version());
        $this$execute.bindString(3, $AnalyzerStatistics.getAnalysis_begin_date());
        $this$execute.bindLong(4, Long.valueOf($AnalyzerStatistics.getAnalysis_begin_timestamp()));
        $this$execute.bindLong(5, Long.valueOf($AnalyzerStatistics.getAnalysis_escape_seconds()));
        $this$execute.bindString(6, $AnalyzerStatistics.getAnalysis_escape_time());
        $this$execute.bindString(7, $AnalyzerStatistics.getAnalysis_end_date());
        $this$execute.bindLong(8, Long.valueOf($AnalyzerStatistics.getAnalysis_end_timestamp()));
        $this$execute.bindLong(9, Long.valueOf($AnalyzerStatistics.getFile_count()));
        $this$execute.bindLong(10, Long.valueOf($AnalyzerStatistics.getLine_count()));
        $this$execute.bindLong(11, $AnalyzerStatistics.getCode_coverage_covered());
        $this$execute.bindLong(12, $AnalyzerStatistics.getCode_coverage_missed());
        $this$execute.bindLong(13, $AnalyzerStatistics.getNum_of_report_dir());
        $this$execute.bindString(14, $AnalyzerStatistics.getSource_paths());
        $this$execute.bindString(15, $AnalyzerStatistics.getOs_name());
        $this$execute.bindString(16, $AnalyzerStatistics.getCommand_json());
        $this$execute.bindString(17, $AnalyzerStatistics.getWorking_directory());
        $this$execute.bindString(18, $AnalyzerStatistics.getOutput_path());
        $this$execute.bindString(19, $AnalyzerStatistics.getProject_root());
        $this$execute.bindString(20, $AnalyzerStatistics.getLog_file());
        $this$execute.bindString(21, $AnalyzerStatistics.getEnable_rules());
        $this$execute.bindString(22, $AnalyzerStatistics.getDisable_rules());
        $this$execute.bindString(23, $AnalyzerStatistics.getFailed_sources());
        $this$execute.bindLong(24, Long.valueOf($AnalyzerStatistics.getFailed_sources_num()));
        $this$execute.bindString(25, $AnalyzerStatistics.getSuccessful_sources());
        $this$execute.bindLong(26, Long.valueOf($AnalyzerStatistics.getSuccessful_sources_num()));
        $this$execute.bindString(27, $AnalyzerStatistics.getSkipped_sources());
        $this$execute.bindLong(28, Long.valueOf($AnalyzerStatistics.getSkipped_sources_num()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$2(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("AnalyzerStatistics");
        return Unit.INSTANCE;
    }
}
