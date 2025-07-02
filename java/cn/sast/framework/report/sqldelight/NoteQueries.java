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
import cn.sast.framework.report.sqldelight.note.Verify_file;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NoteQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001:\u0002$%B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jg\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0014JN\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0007\"\b\b��\u0010\u0016*\u00020\u001726\u0010\u0018\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00160\u0019J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007J\u0093\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001e\"\b\b��\u0010\u0016*\u00020\u00172ú\u0001\u0010\u0018\u001aõ\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H\u00160\u001fJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020 0\u001eJa\u0010!\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010#¨\u0006&"}, d2 = {"Lcn/sast/framework/report/sqldelight/NoteQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "id", "Lapp/cash/sqldelight/ExecutableQuery;", "", "kind", "", "display_hint", "__file_id", "_file_abs_path", "line", "column", "message_en", "message_zh", "__notices_region_id", "__func_region_id", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lapp/cash/sqldelight/ExecutableQuery;", "verify_file", "T", "", "mapper", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lcn/sast/framework/report/sqldelight/note/Verify_file;", "selectAll", "Lapp/cash/sqldelight/Query;", "Lkotlin/Function11;", "Lcn/sast/framework/report/sqldelight/Note;", "insert", "", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "IdQuery", "Verify_fileQuery", "corax-framework"})
/* loaded from: NoteQueries.class */
public final class NoteQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoteQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final ExecutableQuery<Long> id(@NotNull String kind, @NotNull String display_hint, long __file_id, @NotNull String _file_abs_path, long line, @Nullable Long column, @NotNull String message_en, @NotNull String message_zh, @Nullable Long __notices_region_id, @Nullable Long __func_region_id) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(display_hint, "display_hint");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(message_en, "message_en");
        Intrinsics.checkNotNullParameter(message_zh, "message_zh");
        return new IdQuery(this, kind, display_hint, __file_id, _file_abs_path, line, column, message_en, message_zh, __notices_region_id, __func_region_id, NoteQueries::id$lambda$0);
    }

    private static final long id$lambda$0(SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        return l.longValue();
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_file(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_fileQuery(this, (v1) -> {
            return verify_file$lambda$1(r3, v1);
        });
    }

    private static final Object verify_file$lambda$1(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final ExecutableQuery<Verify_file> verify_file() {
        return verify_file((v0, v1) -> {
            return verify_file$lambda$2(v0, v1);
        });
    }

    private static final Verify_file verify_file$lambda$2(long id, long __file_id) {
        return new Verify_file(id, __file_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function11<? super Long, ? super String, ? super String, ? super Long, ? super String, ? super Long, ? super Long, ? super String, ? super String, ? super Long, ? super Long, ? extends T> function11) {
        Intrinsics.checkNotNullParameter(function11, "mapper");
        return QueryKt.Query(1298079392, new String[]{"Note"}, getDriver(), "Note.sq", "selectAll", "SELECT Note.id, Note.kind, Note.display_hint, Note.__file_id, Note._file_abs_path, Note.line, Note.column, Note.message_en, Note.message_zh, Note.__notices_region_id, Note.__func_region_id\nFROM Note", (v1) -> {
            return selectAll$lambda$3(r6, v1);
        });
    }

    private static final Object selectAll$lambda$3(Function11 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        String string = cursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(2);
        Intrinsics.checkNotNull(string2);
        Long l2 = cursor.getLong(3);
        Intrinsics.checkNotNull(l2);
        String string3 = cursor.getString(4);
        Intrinsics.checkNotNull(string3);
        Long l3 = cursor.getLong(5);
        Intrinsics.checkNotNull(l3);
        Long l4 = cursor.getLong(6);
        String string4 = cursor.getString(7);
        Intrinsics.checkNotNull(string4);
        String string5 = cursor.getString(8);
        Intrinsics.checkNotNull(string5);
        return $mapper.invoke(l, string, string2, l2, string3, l3, l4, string4, string5, cursor.getLong(9), cursor.getLong(10));
    }

    @NotNull
    public final Query<Note> selectAll() {
        return selectAll((v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10) -> {
            return selectAll$lambda$4(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
        });
    }

    private static final Note selectAll$lambda$4(long id, String kind, String display_hint, long __file_id, String _file_abs_path, long line, Long column, String message_en, String message_zh, Long __notices_region_id, Long __func_region_id) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(display_hint, "display_hint");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(message_en, "message_en");
        Intrinsics.checkNotNullParameter(message_zh, "message_zh");
        return new Note(id, kind, display_hint, __file_id, _file_abs_path, line, column, message_en, message_zh, __notices_region_id, __func_region_id);
    }

    public final void insert(@NotNull String kind, @NotNull String display_hint, long __file_id, @NotNull String _file_abs_path, long line, @Nullable Long column, @NotNull String message_en, @NotNull String message_zh, @Nullable Long __notices_region_id, @Nullable Long __func_region_id) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(display_hint, "display_hint");
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Intrinsics.checkNotNullParameter(message_en, "message_en");
        Intrinsics.checkNotNullParameter(message_zh, "message_zh");
        Transacter.DefaultImpls.transaction$default((Transacter) this, false, (v11) -> {
            return insert$lambda$6(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, v11);
        }, 1, (Object) null);
        notifyQueries(-681403138, NoteQueries::insert$lambda$7);
    }

    private static final Unit insert$lambda$6(NoteQueries this$0, String $kind, String $display_hint, long $__file_id, String $_file_abs_path, long $line, Long $column, String $message_en, String $message_zh, Long $__notices_region_id, Long $__func_region_id, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.getDriver().execute(-1993383633, "INSERT OR IGNORE INTO Note(kind, display_hint, __file_id, _file_abs_path, line, column, message_en, message_zh, __notices_region_id, __func_region_id)\n    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 10, (v10) -> {
            return insert$lambda$6$lambda$5(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, v10);
        });
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$6$lambda$5(String $kind, String $display_hint, long $__file_id, String $_file_abs_path, long $line, Long $column, String $message_en, String $message_zh, Long $__notices_region_id, Long $__func_region_id, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $kind);
        $this$execute.bindString(1, $display_hint);
        $this$execute.bindLong(2, Long.valueOf($__file_id));
        $this$execute.bindString(3, $_file_abs_path);
        $this$execute.bindLong(4, Long.valueOf($line));
        $this$execute.bindLong(5, $column);
        $this$execute.bindString(6, $message_en);
        $this$execute.bindString(7, $message_zh);
        $this$execute.bindLong(8, $__notices_region_id);
        $this$execute.bindLong(9, $__func_region_id);
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$7(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("Note");
        return Unit.INSTANCE;
    }

    /* compiled from: NoteQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003Bq\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028��0\u0011¢\u0006\u0004\b\u0013\u0010\u0014J.\u0010#\u001a\b\u0012\u0004\u0012\u0002H%0$\"\u0004\b\u0001\u0010%2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H%0$0\u0011H\u0016J\b\u0010&\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b \u0010\u0016R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b!\u0010\u001dR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\"\u0010\u001d¨\u0006'"}, d2 = {"Lcn/sast/framework/report/sqldelight/NoteQueries$IdQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "kind", "", "display_hint", "__file_id", "", "_file_abs_path", "line", "column", "message_en", "message_zh", "__notices_region_id", "__func_region_id", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/NoteQueries;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "getKind", "()Ljava/lang/String;", "getDisplay_hint", "get__file_id", "()J", "get_file_abs_path", "getLine", "getColumn", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMessage_en", "getMessage_zh", "get__notices_region_id", "get__func_region_id", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "corax-framework"})
    /* loaded from: NoteQueries$IdQuery.class */
    private final class IdQuery<T> extends ExecutableQuery<T> {

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
        final /* synthetic */ NoteQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdQuery(@NotNull NoteQueries this$0, @NotNull String kind, String display_hint, @NotNull long __file_id, String _file_abs_path, @Nullable long line, @NotNull Long column, @NotNull String message_en, @Nullable String message_zh, @Nullable Long __notices_region_id, @NotNull Long __func_region_id, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(kind, "kind");
            Intrinsics.checkNotNullParameter(display_hint, "display_hint");
            Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
            Intrinsics.checkNotNullParameter(message_en, "message_en");
            Intrinsics.checkNotNullParameter(message_zh, "message_zh");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
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

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            NoteQueries noteQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v3) -> {
                return execute$lambda$1(r2, r3, r4, v3);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$1(NoteQueries this$0, IdQuery this$1, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return this$0.getDriver().executeQuery((Integer) null, StringsKt.trimMargin$default("\n          |SELECT id FROM Note WHERE kind = ? AND display_hint = ? AND __file_id = ? AND _file_abs_path = ? AND line = ? AND column " + (this$1.column == null ? "IS" : "=") + " ?\n          |    AND message_en = ? AND message_zh = ? AND __notices_region_id " + (this$1.__notices_region_id == null ? "IS" : "=") + " ? AND __func_region_id " + (this$1.__func_region_id == null ? "IS" : "=") + " ?\n          ", (String) null, 1, (Object) null), $mapper, 10, (v1) -> {
                return execute$lambda$1$lambda$0(r5, v1);
            });
        }

        private static final Unit execute$lambda$1$lambda$0(IdQuery this$0, SqlPreparedStatement $this$executeQuery) {
            Intrinsics.checkNotNullParameter($this$executeQuery, "$this$executeQuery");
            $this$executeQuery.bindString(0, this$0.kind);
            $this$executeQuery.bindString(1, this$0.display_hint);
            $this$executeQuery.bindLong(2, Long.valueOf(this$0.__file_id));
            $this$executeQuery.bindString(3, this$0._file_abs_path);
            $this$executeQuery.bindLong(4, Long.valueOf(this$0.line));
            $this$executeQuery.bindLong(5, this$0.column);
            $this$executeQuery.bindString(6, this$0.message_en);
            $this$executeQuery.bindString(7, this$0.message_zh);
            $this$executeQuery.bindLong(8, this$0.__notices_region_id);
            $this$executeQuery.bindLong(9, this$0.__func_region_id);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "Note.sq:id";
        }
    }

    /* compiled from: NoteQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/NoteQueries$Verify_fileQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/NoteQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: NoteQueries$Verify_fileQuery.class */
    private final class Verify_fileQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ NoteQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_fileQuery(@NotNull NoteQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            NoteQueries noteQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(NoteQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 1014184654, "SELECT id, __file_id FROM Note WHERE __file_id NOT IN (SELECT id FROM File)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "Note.sq:verify_file";
        }
    }
}
