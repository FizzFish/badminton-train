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
import cn.sast.framework.report.sqldelight.controlFlow.Verify_file;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ControlFlowQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0002\u001e\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J@\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bJN\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0007\"\b\b��\u0010\u0011*\u00020\u001226\u0010\u0013\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u00110\u0014J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007J½\u0001\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0019\"\b\b��\u0010\u0011*\u00020\u00122¤\u0001\u0010\u0013\u001a\u009f\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00110\u001aJ\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0019J:\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b¨\u0006 "}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "id", "Lapp/cash/sqldelight/ExecutableQuery;", "", "__file_id", "_file_abs_path", "", "message_en", "message_zh", "__edge_from_region_id", "__edge_to_region_id", "verify_file", "T", "", "mapper", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lcn/sast/framework/report/sqldelight/controlFlow/Verify_file;", "selectAll", "Lapp/cash/sqldelight/Query;", "Lkotlin/Function7;", "Lcn/sast/framework/report/sqldelight/ControlFlow;", "insert", "", "IdQuery", "Verify_fileQuery", "corax-framework"})
/* loaded from: ControlFlowQueries.class */
public final class ControlFlowQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlFlowQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final ExecutableQuery<Long> id(long __file_id, @NotNull String _file_abs_path, @Nullable String message_en, @Nullable String message_zh, long __edge_from_region_id, long __edge_to_region_id) {
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        return new IdQuery(this, __file_id, _file_abs_path, message_en, message_zh, __edge_from_region_id, __edge_to_region_id, ControlFlowQueries::id$lambda$0);
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
    public final <T> Query<T> selectAll(@NotNull Function7<? super Long, ? super Long, ? super String, ? super String, ? super String, ? super Long, ? super Long, ? extends T> function7) {
        Intrinsics.checkNotNullParameter(function7, "mapper");
        return QueryKt.Query(590154801, new String[]{"ControlFlow"}, getDriver(), "ControlFlow.sq", "selectAll", "SELECT ControlFlow.id, ControlFlow.__file_id, ControlFlow._file_abs_path, ControlFlow.message_en, ControlFlow.message_zh, ControlFlow.__edge_from_region_id, ControlFlow.__edge_to_region_id\nFROM ControlFlow", (v1) -> {
            return selectAll$lambda$3(r6, v1);
        });
    }

    private static final Object selectAll$lambda$3(Function7 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        String string = cursor.getString(2);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(3);
        String string3 = cursor.getString(4);
        Long l3 = cursor.getLong(5);
        Intrinsics.checkNotNull(l3);
        Long l4 = cursor.getLong(6);
        Intrinsics.checkNotNull(l4);
        return $mapper.invoke(l, l2, string, string2, string3, l3, l4);
    }

    @NotNull
    public final Query<ControlFlow> selectAll() {
        return selectAll((v0, v1, v2, v3, v4, v5, v6) -> {
            return selectAll$lambda$4(v0, v1, v2, v3, v4, v5, v6);
        });
    }

    private static final ControlFlow selectAll$lambda$4(long id, long __file_id, String _file_abs_path, String message_en, String message_zh, long __edge_from_region_id, long __edge_to_region_id) {
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        return new ControlFlow(id, __file_id, _file_abs_path, message_en, message_zh, __edge_from_region_id, __edge_to_region_id);
    }

    public final void insert(long __file_id, @NotNull String _file_abs_path, @Nullable String message_en, @Nullable String message_zh, long __edge_from_region_id, long __edge_to_region_id) {
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        Transacter.DefaultImpls.transaction$default((Transacter) this, false, (v7) -> {
            return insert$lambda$6(r2, r3, r4, r5, r6, r7, r8, v7);
        }, 1, (Object) null);
        notifyQueries(1615921421, ControlFlowQueries::insert$lambda$7);
    }

    private static final Unit insert$lambda$6(ControlFlowQueries this$0, long $__file_id, String $_file_abs_path, String $message_en, String $message_zh, long $__edge_from_region_id, long $__edge_to_region_id, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.getDriver().execute(-1877672578, "INSERT OR IGNORE INTO ControlFlow(__file_id, _file_abs_path, message_en, message_zh, __edge_from_region_id, __edge_to_region_id)\n    VALUES (?, ?, ?, ?, ?, ?)", 6, (v6) -> {
            return insert$lambda$6$lambda$5(r4, r5, r6, r7, r8, r9, v6);
        });
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$6$lambda$5(long $__file_id, String $_file_abs_path, String $message_en, String $message_zh, long $__edge_from_region_id, long $__edge_to_region_id, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($__file_id));
        $this$execute.bindString(1, $_file_abs_path);
        $this$execute.bindString(2, $message_en);
        $this$execute.bindString(3, $message_zh);
        $this$execute.bindLong(4, Long.valueOf($__edge_from_region_id));
        $this$execute.bindLong(5, Long.valueOf($__edge_to_region_id));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$7(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("ControlFlow");
        return Unit.INSTANCE;
    }

    /* compiled from: ControlFlowQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BO\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00028��0\r¢\u0006\u0004\b\u000f\u0010\u0010J.\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001a\"\u0004\b\u0001\u0010\u001b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u001a0\rH\u0016J\b\u0010\u001c\u001a\u00020\u0007H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowQueries$IdQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "__file_id", "", "_file_abs_path", "", "message_en", "message_zh", "__edge_from_region_id", "__edge_to_region_id", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/ControlFlowQueries;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLkotlin/jvm/functions/Function1;)V", "get__file_id", "()J", "get_file_abs_path", "()Ljava/lang/String;", "getMessage_en", "getMessage_zh", "get__edge_from_region_id", "get__edge_to_region_id", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "corax-framework"})
    /* loaded from: ControlFlowQueries$IdQuery.class */
    private final class IdQuery<T> extends ExecutableQuery<T> {
        private final long __file_id;

        @NotNull
        private final String _file_abs_path;

        @Nullable
        private final String message_en;

        @Nullable
        private final String message_zh;
        private final long __edge_from_region_id;
        private final long __edge_to_region_id;
        final /* synthetic */ ControlFlowQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdQuery(ControlFlowQueries this$0, @NotNull long __file_id, @Nullable String _file_abs_path, @Nullable String message_en, String message_zh, long __edge_from_region_id, @NotNull long __edge_to_region_id, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
            this.__file_id = __file_id;
            this._file_abs_path = _file_abs_path;
            this.message_en = message_en;
            this.message_zh = message_zh;
            this.__edge_from_region_id = __edge_from_region_id;
            this.__edge_to_region_id = __edge_to_region_id;
        }

        public final long get__file_id() {
            return this.__file_id;
        }

        @NotNull
        public final String get_file_abs_path() {
            return this._file_abs_path;
        }

        @Nullable
        public final String getMessage_en() {
            return this.message_en;
        }

        @Nullable
        public final String getMessage_zh() {
            return this.message_zh;
        }

        public final long get__edge_from_region_id() {
            return this.__edge_from_region_id;
        }

        public final long get__edge_to_region_id() {
            return this.__edge_to_region_id;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            ControlFlowQueries controlFlowQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v3) -> {
                return execute$lambda$1(r2, r3, r4, v3);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$1(ControlFlowQueries this$0, IdQuery this$1, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return this$0.getDriver().executeQuery((Integer) null, "SELECT id FROM ControlFlow WHERE __file_id = ? AND _file_abs_path = ? AND message_en " + (this$1.message_en == null ? "IS" : "=") + " ? AND message_zh " + (this$1.message_zh == null ? "IS" : "=") + " ? AND __edge_from_region_id = ? AND __edge_to_region_id = ?", $mapper, 6, (v1) -> {
                return execute$lambda$1$lambda$0(r5, v1);
            });
        }

        private static final Unit execute$lambda$1$lambda$0(IdQuery this$0, SqlPreparedStatement $this$executeQuery) {
            Intrinsics.checkNotNullParameter($this$executeQuery, "$this$executeQuery");
            $this$executeQuery.bindLong(0, Long.valueOf(this$0.__file_id));
            $this$executeQuery.bindString(1, this$0._file_abs_path);
            $this$executeQuery.bindString(2, this$0.message_en);
            $this$executeQuery.bindString(3, this$0.message_zh);
            $this$executeQuery.bindLong(4, Long.valueOf(this$0.__edge_from_region_id));
            $this$executeQuery.bindLong(5, Long.valueOf(this$0.__edge_to_region_id));
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "ControlFlow.sq:id";
        }
    }

    /* compiled from: ControlFlowQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowQueries$Verify_fileQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/ControlFlowQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: ControlFlowQueries$Verify_fileQuery.class */
    private final class Verify_fileQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ ControlFlowQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_fileQuery(@NotNull ControlFlowQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            ControlFlowQueries controlFlowQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(ControlFlowQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 2004744159, "SELECT id, __file_id FROM ControlFlow WHERE __file_id NOT IN (SELECT id FROM File)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "ControlFlow.sq:verify_file";
        }
    }
}
