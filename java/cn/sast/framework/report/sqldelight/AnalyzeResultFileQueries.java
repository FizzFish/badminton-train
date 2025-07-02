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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnalyzeResultFileQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Je\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2M\u0010\n\u001aI\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007Je\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\b0\u0015\"\b\b��\u0010\b*\u00020\t2M\u0010\n\u001aI\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013¨\u0006\u001a"}, d2 = {"Lcn/sast/framework/report/sqldelight/AnalyzeResultFileQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_file", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "file_name", "file_path", "", "__file_id", "Lcn/sast/framework/report/sqldelight/AnalyzerResultFile;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "AnalyzerResultFile", "Verify_fileQuery", "corax-framework"})
/* loaded from: AnalyzeResultFileQueries.class */
public final class AnalyzeResultFileQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnalyzeResultFileQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_file(@NotNull Function3<? super String, ? super String, ? super Long, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return new Verify_fileQuery(this, (v1) -> {
            return verify_file$lambda$0(r3, v1);
        });
    }

    private static final Object verify_file$lambda$0(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Long l = cursor.getLong(2);
        Intrinsics.checkNotNull(l);
        return $mapper.invoke(string, string2, l);
    }

    @NotNull
    public final ExecutableQuery<AnalyzerResultFile> verify_file() {
        return verify_file((v0, v1, v2) -> {
            return verify_file$lambda$1(v0, v1, v2);
        });
    }

    private static final AnalyzerResultFile verify_file$lambda$1(String file_name, String file_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_name, "file_name");
        return new AnalyzerResultFile(file_name, file_path, __file_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function3<? super String, ? super String, ? super Long, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return QueryKt.Query(1331798327, new String[]{"AnalyzerResultFile"}, getDriver(), "AnalyzeResultFile.sq", "selectAll", "SELECT AnalyzerResultFile.file_name, AnalyzerResultFile.file_path, AnalyzerResultFile.__file_id\nFROM AnalyzerResultFile", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Long l = cursor.getLong(2);
        Intrinsics.checkNotNull(l);
        return $mapper.invoke(string, string2, l);
    }

    @NotNull
    public final Query<AnalyzerResultFile> selectAll() {
        return selectAll((v0, v1, v2) -> {
            return selectAll$lambda$3(v0, v1, v2);
        });
    }

    private static final AnalyzerResultFile selectAll$lambda$3(String file_name, String file_path, long __file_id) {
        Intrinsics.checkNotNullParameter(file_name, "file_name");
        return new AnalyzerResultFile(file_name, file_path, __file_id);
    }

    public final void insert(@NotNull AnalyzerResultFile AnalyzerResultFile) {
        Intrinsics.checkNotNullParameter(AnalyzerResultFile, "AnalyzerResultFile");
        getDriver().execute(397277639, "INSERT OR IGNORE INTO AnalyzerResultFile (file_name, file_path, __file_id)\nVALUES (?, ?, ?)", 3, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(397277639, AnalyzeResultFileQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(AnalyzerResultFile $AnalyzerResultFile, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $AnalyzerResultFile.getFile_name());
        $this$execute.bindString(1, $AnalyzerResultFile.getFile_path());
        $this$execute.bindLong(2, Long.valueOf($AnalyzerResultFile.get__file_id()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("AnalyzerResultFile");
        return Unit.INSTANCE;
    }

    /* compiled from: AnalyzeResultFileQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/AnalyzeResultFileQueries$Verify_fileQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/AnalyzeResultFileQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: AnalyzeResultFileQueries$Verify_fileQuery.class */
    private final class Verify_fileQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ AnalyzeResultFileQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_fileQuery(@NotNull AnalyzeResultFileQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            AnalyzeResultFileQueries analyzeResultFileQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(AnalyzeResultFileQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -1649108507, "SELECT AnalyzerResultFile.file_name, AnalyzerResultFile.file_path, AnalyzerResultFile.__file_id FROM AnalyzerResultFile WHERE __file_id NOT IN (SELECT id FROM File)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "AnalyzeResultFile.sq:verify_file";
        }
    }
}
