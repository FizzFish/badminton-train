package cn.sast.framework.report.sqldelight;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.TransactionWithReturn;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: AbsoluteFilePathQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007JN\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0014\"\b\b��\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012¨\u0006\u0019"}, d2 = {"Lcn/sast/framework/report/sqldelight/AbsoluteFilePathQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_absolute_file_path", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "file_abs_path", "", "__file_id", "Lcn/sast/framework/report/sqldelight/AbsoluteFilePath;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "AbsoluteFilePath", "Verify_absolute_file_pathQuery", "corax-framework"})
/* loaded from: AbsoluteFilePathQueries.class */
public final class AbsoluteFilePathQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbsoluteFilePathQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_absolute_file_path(@NotNull Function2<? super String, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_absolute_file_pathQuery(this, (v1) -> {
            return verify_absolute_file_path$lambda$0(r3, v1);
        });
    }

    private static final Object verify_absolute_file_path$lambda$0(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        Long l = cursor.getLong(1);
        Intrinsics.checkNotNull(l);
        return $mapper.invoke(string, l);
    }

    @NotNull
    public final ExecutableQuery<AbsoluteFilePath> verify_absolute_file_path() {
        return verify_absolute_file_path((v0, v1) -> {
            return verify_absolute_file_path$lambda$1(v0, v1);
        });
    }

    private static final AbsoluteFilePath verify_absolute_file_path$lambda$1(String file_abs_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_abs_path, "file_abs_path");
        return new AbsoluteFilePath(file_abs_path, __file_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function2<? super String, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return QueryKt.Query(77310330, new String[]{"AbsoluteFilePath"}, getDriver(), "AbsoluteFilePath.sq", "selectAll", "SELECT AbsoluteFilePath.file_abs_path, AbsoluteFilePath.__file_id\nFROM AbsoluteFilePath", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        Long l = cursor.getLong(1);
        Intrinsics.checkNotNull(l);
        return $mapper.invoke(string, l);
    }

    @NotNull
    public final Query<AbsoluteFilePath> selectAll() {
        return selectAll((v0, v1) -> {
            return selectAll$lambda$3(v0, v1);
        });
    }

    private static final AbsoluteFilePath selectAll$lambda$3(String file_abs_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_abs_path, "file_abs_path");
        return new AbsoluteFilePath(file_abs_path, __file_id);
    }

    public final void insert(@NotNull AbsoluteFilePath AbsoluteFilePath) {
        Intrinsics.checkNotNullParameter(AbsoluteFilePath, "AbsoluteFilePath");
        getDriver().execute(365662308, "INSERT OR IGNORE INTO AbsoluteFilePath (file_abs_path, __file_id) VALUES (?, ?)", 2, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(365662308, AbsoluteFilePathQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(AbsoluteFilePath $AbsoluteFilePath, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $AbsoluteFilePath.getFile_abs_path());
        $this$execute.bindLong(1, Long.valueOf($AbsoluteFilePath.get__file_id()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("AbsoluteFilePath");
        return Unit.INSTANCE;
    }

    /* compiled from: AbsoluteFilePathQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/AbsoluteFilePathQueries$Verify_absolute_file_pathQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/AbsoluteFilePathQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: AbsoluteFilePathQueries$Verify_absolute_file_pathQuery.class */
    private final class Verify_absolute_file_pathQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ AbsoluteFilePathQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_absolute_file_pathQuery(@NotNull AbsoluteFilePathQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            AbsoluteFilePathQueries absoluteFilePathQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(AbsoluteFilePathQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -1503538388, "SELECT AbsoluteFilePath.file_abs_path, AbsoluteFilePath.__file_id FROM AbsoluteFilePath WHERE __file_id NOT IN (SELECT id FROM File)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "AbsoluteFilePath.sq:verify_absolute_file_path";
        }
    }
}
