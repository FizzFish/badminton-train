package cn.sast.framework.report.sqldelight;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.TransactionWithReturn;
import app.cash.sqldelight.TransactionWithoutReturn;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.sqldelight.diagnostic.Verify_file;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DiagnosticQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\b\u0018��2\u00020\u0001:\u0006456789B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0097\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0018JN\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0007\"\b\b��\u0010\u001a*\u00020\u001b26\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u001a0\u001dJ\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020 0\u0007JP\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0007\"\b\b��\u0010\u001a*\u00020\u001b28\u0010\u001c\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u001a0\u001dJ\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0007JN\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0007\"\b\b��\u0010\u001a*\u00020\u001b26\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u0002H\u001a0\u001dJ\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0007JN\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0007\"\b\b��\u0010\u001a*\u00020\u001b26\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u0002H\u001a0\u001dJ\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0007JN\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0007\"\b\b��\u0010\u001a*\u00020\u001b26\u0010\u001c\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u001a0\u001dJ\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0007JË\u0003\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u001a0*\"\b\b��\u0010\u001a*\u00020\u001b2²\u0003\u0010\u001c\u001a\u00ad\u0003\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u001a0+J\f\u0010)\u001a\b\u0012\u0004\u0012\u0002000*J±\u0001\u00101\u001a\u0002022\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u00103¨\u0006:"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "id", "Lapp/cash/sqldelight/ExecutableQuery;", "", "rule_name", "", "_rule_short_description_zh", "__file_id", "_file_abs_path", "severity", "precision", "likelihood", "impact", "technique", "analysis_scope", "line_content", "__note_array_hash_id", "__control_flow_array_hash_id", "__macro_note_set_hash_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)Lapp/cash/sqldelight/ExecutableQuery;", "verify_rule_name", "T", "", "mapper", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lcn/sast/framework/report/sqldelight/Verify_rule_name;", "verify_file", "Lcn/sast/framework/report/sqldelight/diagnostic/Verify_file;", "verify_note_path", "Lcn/sast/framework/report/sqldelight/Verify_note_path;", "verify_control_flow_path", "Lcn/sast/framework/report/sqldelight/Verify_control_flow_path;", "verify_macro", "Lcn/sast/framework/report/sqldelight/Verify_macro;", "selectAll", "Lapp/cash/sqldelight/Query;", "Lkotlin/Function19;", "_line", "_column", "_message_en", "_message_zh", "Lcn/sast/framework/report/sqldelight/Diagnostic;", "insert", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)V", "IdQuery", "Verify_rule_nameQuery", "Verify_fileQuery", "Verify_note_pathQuery", "Verify_control_flow_pathQuery", "Verify_macroQuery", "corax-framework"})
/* loaded from: DiagnosticQueries.class */
public final class DiagnosticQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final ExecutableQuery<Long> id(@NotNull String rule_name, @Nullable String _rule_short_description_zh, @Nullable Long __file_id, @NotNull String _file_abs_path, @Nullable String severity, @Nullable String precision, @Nullable String likelihood, @Nullable String impact, @Nullable String technique, @Nullable String analysis_scope, @Nullable String line_content, long __note_array_hash_id, @Nullable Long __control_flow_array_hash_id, @Nullable Long __macro_note_set_hash_id) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        return new IdQuery(this, rule_name, _rule_short_description_zh, __file_id, _file_abs_path, severity, precision, likelihood, impact, technique, analysis_scope, line_content, __note_array_hash_id, __control_flow_array_hash_id, __macro_note_set_hash_id, DiagnosticQueries::id$lambda$0);
    }

    private static final long id$lambda$0(SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        return l.longValue();
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_rule_name(@NotNull Function2<? super Long, ? super String, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_rule_nameQuery(this, (v1) -> {
            return verify_rule_name$lambda$1(r3, v1);
        });
    }

    private static final Object verify_rule_name$lambda$1(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        String string = cursor.getString(1);
        Intrinsics.checkNotNull(string);
        return $mapper.invoke(l, string);
    }

    @NotNull
    public final ExecutableQuery<Verify_rule_name> verify_rule_name() {
        return verify_rule_name((v0, v1) -> {
            return verify_rule_name$lambda$2(v0, v1);
        });
    }

    private static final Verify_rule_name verify_rule_name$lambda$2(long id, String rule_name) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        return new Verify_rule_name(id, rule_name);
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_file(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_fileQuery(this, (v1) -> {
            return verify_file$lambda$3(r3, v1);
        });
    }

    private static final Object verify_file$lambda$3(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        return $mapper.invoke(l, cursor.getLong(1));
    }

    @NotNull
    public final ExecutableQuery<Verify_file> verify_file() {
        return verify_file((v0, v1) -> {
            return verify_file$lambda$4(v0, v1);
        });
    }

    private static final Verify_file verify_file$lambda$4(long id, Long __file_id) {
        return new Verify_file(id, __file_id);
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_note_path(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_note_pathQuery(this, (v1) -> {
            return verify_note_path$lambda$5(r3, v1);
        });
    }

    private static final Object verify_note_path$lambda$5(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final ExecutableQuery<Verify_note_path> verify_note_path() {
        return verify_note_path((v0, v1) -> {
            return verify_note_path$lambda$6(v0, v1);
        });
    }

    private static final Verify_note_path verify_note_path$lambda$6(long id, long __note_array_hash_id) {
        return new Verify_note_path(id, __note_array_hash_id);
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_control_flow_path(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_control_flow_pathQuery(this, (v1) -> {
            return verify_control_flow_path$lambda$7(r3, v1);
        });
    }

    private static final Object verify_control_flow_path$lambda$7(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final ExecutableQuery<Verify_control_flow_path> verify_control_flow_path() {
        return verify_control_flow_path((v0, v1) -> {
            return verify_control_flow_path$lambda$8(v0, v1);
        });
    }

    private static final Verify_control_flow_path verify_control_flow_path$lambda$8(long id, long __control_flow_array_hash_id) {
        return new Verify_control_flow_path(id, __control_flow_array_hash_id);
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_macro(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_macroQuery(this, (v1) -> {
            return verify_macro$lambda$9(r3, v1);
        });
    }

    private static final Object verify_macro$lambda$9(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final ExecutableQuery<Verify_macro> verify_macro() {
        return verify_macro((v0, v1) -> {
            return verify_macro$lambda$10(v0, v1);
        });
    }

    private static final Verify_macro verify_macro$lambda$10(long id, long __macro_note_set_hash_id) {
        return new Verify_macro(id, __macro_note_set_hash_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function19<? super Long, ? super String, ? super String, ? super Long, ? super String, ? super Long, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? super Long, ? extends T> function19) {
        Intrinsics.checkNotNullParameter(function19, "mapper");
        return QueryKt.Query(2051098827, new String[]{"Diagnostic"}, getDriver(), "Diagnostic.sq", "selectAll", "SELECT Diagnostic.id, Diagnostic.rule_name, Diagnostic._rule_short_description_zh, Diagnostic.__file_id, Diagnostic._file_abs_path, Diagnostic._line, Diagnostic._column, Diagnostic._message_en, Diagnostic._message_zh, Diagnostic.severity, Diagnostic.precision, Diagnostic.likelihood, Diagnostic.impact, Diagnostic.technique, Diagnostic.analysis_scope, Diagnostic.line_content, Diagnostic.__note_array_hash_id, Diagnostic.__control_flow_array_hash_id, Diagnostic.__macro_note_set_hash_id\nFROM Diagnostic", (v1) -> {
            return selectAll$lambda$11(r6, v1);
        });
    }

    private static final Object selectAll$lambda$11(Function19 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        String string = cursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(2);
        Long l2 = cursor.getLong(3);
        String string3 = cursor.getString(4);
        Intrinsics.checkNotNull(string3);
        Long l3 = cursor.getLong(5);
        Intrinsics.checkNotNull(l3);
        Long l4 = cursor.getLong(6);
        Intrinsics.checkNotNull(l4);
        String string4 = cursor.getString(7);
        Intrinsics.checkNotNull(string4);
        String string5 = cursor.getString(8);
        Intrinsics.checkNotNull(string5);
        String string6 = cursor.getString(9);
        String string7 = cursor.getString(10);
        String string8 = cursor.getString(11);
        String string9 = cursor.getString(12);
        String string10 = cursor.getString(13);
        String string11 = cursor.getString(14);
        String string12 = cursor.getString(15);
        Long l5 = cursor.getLong(16);
        Intrinsics.checkNotNull(l5);
        return $mapper.invoke(l, string, string2, l2, string3, l3, l4, string4, string5, string6, string7, string8, string9, string10, string11, string12, l5, cursor.getLong(17), cursor.getLong(18));
    }

    @NotNull
    public final Query<Diagnostic> selectAll() {
        return selectAll((v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18) -> {
            return selectAll$lambda$12(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18);
        });
    }

    private static final Diagnostic selectAll$lambda$12(long id, String rule_name, String _rule_short_description_zh, Long __file_id, String _file_abs_path, long _line, long _column, String _message_en, String _message_zh, String severity, String precision, String likelihood, String impact, String technique, String analysis_scope, String line_content, long __note_array_hash_id, Long __control_flow_array_hash_id, Long __macro_note_set_hash_id) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(_message_en, "_message_en");
        Intrinsics.checkNotNullParameter(_message_zh, "_message_zh");
        return new Diagnostic(id, rule_name, _rule_short_description_zh, __file_id, _file_abs_path, _line, _column, _message_en, _message_zh, severity, precision, likelihood, impact, technique, analysis_scope, line_content, __note_array_hash_id, __control_flow_array_hash_id, __macro_note_set_hash_id);
    }

    public final void insert(@NotNull String rule_name, @Nullable String _rule_short_description_zh, @Nullable Long __file_id, @NotNull String _file_abs_path, long _line, long _column, @NotNull String _message_en, @NotNull String _message_zh, @Nullable String severity, @Nullable String precision, @Nullable String likelihood, @Nullable String impact, @Nullable String technique, @Nullable String analysis_scope, @Nullable String line_content, long __note_array_hash_id, @Nullable Long __control_flow_array_hash_id, @Nullable Long __macro_note_set_hash_id) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(_message_en, "_message_en");
        Intrinsics.checkNotNullParameter(_message_zh, "_message_zh");
        Transacter.DefaultImpls.transaction$default((Transacter) this, false, (v19) -> {
            return insert$lambda$14(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, v19);
        }, 1, (Object) null);
        notifyQueries(949328563, DiagnosticQueries::insert$lambda$15);
    }

    private static final Unit insert$lambda$14(DiagnosticQueries this$0, String $rule_name, String $_rule_short_description_zh, Long $__file_id, String $_file_abs_path, long $_line, long $_column, String $_message_en, String $_message_zh, String $severity, String $precision, String $likelihood, String $impact, String $technique, String $analysis_scope, String $line_content, long $__note_array_hash_id, Long $__control_flow_array_hash_id, Long $__macro_note_set_hash_id, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.getDriver().execute(1771685284, "INSERT OR IGNORE INTO Diagnostic(rule_name, _rule_short_description_zh,\n                                     __file_id, _file_abs_path,\n                                     _line, _column, _message_en, _message_zh,\n                                     severity, precision, likelihood, impact, technique, analysis_scope,\n                                     line_content,\n                                     __note_array_hash_id, __control_flow_array_hash_id, __macro_note_set_hash_id\n--                                      issue_context_kind, issue_context, issue_hash_func_offset\n                                     )\n    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n--     ?, ?, ?\n    )", 18, (v18) -> {
            return insert$lambda$14$lambda$13(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, v18);
        });
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$14$lambda$13(String $rule_name, String $_rule_short_description_zh, Long $__file_id, String $_file_abs_path, long $_line, long $_column, String $_message_en, String $_message_zh, String $severity, String $precision, String $likelihood, String $impact, String $technique, String $analysis_scope, String $line_content, long $__note_array_hash_id, Long $__control_flow_array_hash_id, Long $__macro_note_set_hash_id, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $rule_name);
        $this$execute.bindString(1, $_rule_short_description_zh);
        $this$execute.bindLong(2, $__file_id);
        $this$execute.bindString(3, $_file_abs_path);
        $this$execute.bindLong(4, Long.valueOf($_line));
        $this$execute.bindLong(5, Long.valueOf($_column));
        $this$execute.bindString(6, $_message_en);
        $this$execute.bindString(7, $_message_zh);
        $this$execute.bindString(8, $severity);
        $this$execute.bindString(9, $precision);
        $this$execute.bindString(10, $likelihood);
        $this$execute.bindString(11, $impact);
        $this$execute.bindString(12, $technique);
        $this$execute.bindString(13, $analysis_scope);
        $this$execute.bindString(14, $line_content);
        $this$execute.bindLong(15, Long.valueOf($__note_array_hash_id));
        $this$execute.bindLong(16, $__control_flow_array_hash_id);
        $this$execute.bindLong(17, $__macro_note_set_hash_id);
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$15(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("Diagnostic");
        return Unit.INSTANCE;
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B¡\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00028��0\u0015¢\u0006\u0004\b\u0017\u0010\u0018J.\u0010+\u001a\b\u0012\u0004\u0012\u0002H-0,\"\u0004\b\u0001\u0010-2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0,0\u0015H\u0016J\b\u0010.\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001aR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001f\u0010\u001aR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b \u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b!\u0010\u001aR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\"\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b#\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b$\u0010\u001aR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b%\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b'\u0010(R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b)\u0010\u001dR\u0015\u0010\u0013\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b*\u0010\u001d¨\u0006/"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$IdQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "rule_name", "", "_rule_short_description_zh", "__file_id", "", "_file_abs_path", "severity", "precision", "likelihood", "impact", "technique", "analysis_scope", "line_content", "__note_array_hash_id", "__control_flow_array_hash_id", "__macro_note_set_hash_id", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "getRule_name", "()Ljava/lang/String;", "get_rule_short_description_zh", "get__file_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "get_file_abs_path", "getSeverity", "getPrecision", "getLikelihood", "getImpact", "getTechnique", "getAnalysis_scope", "getLine_content", "get__note_array_hash_id", "()J", "get__control_flow_array_hash_id", "get__macro_note_set_hash_id", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "corax-framework"})
    /* loaded from: DiagnosticQueries$IdQuery.class */
    private final class IdQuery<T> extends ExecutableQuery<T> {

        @NotNull
        private final String rule_name;

        @Nullable
        private final String _rule_short_description_zh;

        @Nullable
        private final Long __file_id;

        @NotNull
        private final String _file_abs_path;

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
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdQuery(@NotNull DiagnosticQueries this$0, @Nullable String rule_name, @Nullable String _rule_short_description_zh, @NotNull Long __file_id, @Nullable String _file_abs_path, @Nullable String severity, @Nullable String precision, @Nullable String likelihood, @Nullable String impact, @Nullable String technique, @Nullable String analysis_scope, String line_content, @Nullable long __note_array_hash_id, @Nullable Long __control_flow_array_hash_id, @NotNull Long __macro_note_set_hash_id, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(rule_name, "rule_name");
            Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
            this.rule_name = rule_name;
            this._rule_short_description_zh = _rule_short_description_zh;
            this.__file_id = __file_id;
            this._file_abs_path = _file_abs_path;
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

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v3) -> {
                return execute$lambda$1(r2, r3, r4, v3);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$1(DiagnosticQueries this$0, IdQuery this$1, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return this$0.getDriver().executeQuery((Integer) null, StringsKt.trimMargin$default("\n          |SELECT id FROM Diagnostic WHERE rule_name = ? AND _rule_short_description_zh " + (this$1._rule_short_description_zh == null ? "IS" : "=") + " ? AND __file_id " + (this$1.__file_id == null ? "IS" : "=") + " ? AND _file_abs_path = ?\n          |    AND severity " + (this$1.severity == null ? "IS" : "=") + " ? AND precision " + (this$1.precision == null ? "IS" : "=") + " ? AND likelihood " + (this$1.likelihood == null ? "IS" : "=") + " ? AND impact " + (this$1.impact == null ? "IS" : "=") + " ? AND technique " + (this$1.technique == null ? "IS" : "=") + " ? AND analysis_scope " + (this$1.analysis_scope == null ? "IS" : "=") + " ? AND line_content " + (this$1.line_content == null ? "IS" : "=") + " ?\n          |    AND __note_array_hash_id = ? AND __control_flow_array_hash_id " + (this$1.__control_flow_array_hash_id == null ? "IS" : "=") + " ? AND __macro_note_set_hash_id " + (this$1.__macro_note_set_hash_id == null ? "IS" : "=") + " ?\n          ", (String) null, 1, (Object) null), $mapper, 14, (v1) -> {
                return execute$lambda$1$lambda$0(r5, v1);
            });
        }

        private static final Unit execute$lambda$1$lambda$0(IdQuery this$0, SqlPreparedStatement $this$executeQuery) {
            Intrinsics.checkNotNullParameter($this$executeQuery, "$this$executeQuery");
            $this$executeQuery.bindString(0, this$0.rule_name);
            $this$executeQuery.bindString(1, this$0._rule_short_description_zh);
            $this$executeQuery.bindLong(2, this$0.__file_id);
            $this$executeQuery.bindString(3, this$0._file_abs_path);
            $this$executeQuery.bindString(4, this$0.severity);
            $this$executeQuery.bindString(5, this$0.precision);
            $this$executeQuery.bindString(6, this$0.likelihood);
            $this$executeQuery.bindString(7, this$0.impact);
            $this$executeQuery.bindString(8, this$0.technique);
            $this$executeQuery.bindString(9, this$0.analysis_scope);
            $this$executeQuery.bindString(10, this$0.line_content);
            $this$executeQuery.bindLong(11, Long.valueOf(this$0.__note_array_hash_id));
            $this$executeQuery.bindLong(12, this$0.__control_flow_array_hash_id);
            $this$executeQuery.bindLong(13, this$0.__macro_note_set_hash_id);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:id";
        }
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$Verify_rule_nameQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: DiagnosticQueries$Verify_rule_nameQuery.class */
    private final class Verify_rule_nameQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_rule_nameQuery(@NotNull DiagnosticQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(DiagnosticQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 264932435, "SELECT id, rule_name FROM Diagnostic WHERE rule_name NOT IN (SELECT name FROM Rule)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:verify_rule_name";
        }
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$Verify_fileQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: DiagnosticQueries$Verify_fileQuery.class */
    private final class Verify_fileQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_fileQuery(@NotNull DiagnosticQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(DiagnosticQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 2056148857, "SELECT id, __file_id FROM Diagnostic WHERE __file_id NOT IN (SELECT id FROM File)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:verify_file";
        }
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$Verify_note_pathQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: DiagnosticQueries$Verify_note_pathQuery.class */
    private final class Verify_note_pathQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_note_pathQuery(@NotNull DiagnosticQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(DiagnosticQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -1475379785, "SELECT id, __note_array_hash_id FROM Diagnostic WHERE __note_array_hash_id NOT IN (SELECT __note_array_hash_id FROM NotePath)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:verify_note_path";
        }
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$Verify_control_flow_pathQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: DiagnosticQueries$Verify_control_flow_pathQuery.class */
    private final class Verify_control_flow_pathQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_control_flow_pathQuery(@NotNull DiagnosticQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(DiagnosticQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -545866471, "SELECT id, __control_flow_array_hash_id FROM Diagnostic WHERE __control_flow_array_hash_id IS NOT NULL AND __control_flow_array_hash_id NOT IN (SELECT __control_flow_array_hash_id FROM ControlFlowPath)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:verify_control_flow_path";
        }
    }

    /* compiled from: DiagnosticQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/DiagnosticQueries$Verify_macroQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/DiagnosticQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: DiagnosticQueries$Verify_macroQuery.class */
    private final class Verify_macroQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ DiagnosticQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_macroQuery(@NotNull DiagnosticQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            DiagnosticQueries diagnosticQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(DiagnosticQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 996722865, "SELECT id, __macro_note_set_hash_id FROM Diagnostic WHERE __macro_note_set_hash_id IS NOT NULL AND __macro_note_set_hash_id NOT IN (SELECT __macro_note_set_hash_id FROM MacroExpansion)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Diagnostic.sq:verify_macro";
        }
    }
}
