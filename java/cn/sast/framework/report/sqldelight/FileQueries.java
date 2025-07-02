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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: FileQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ»\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\b\b��\u0010\u000e*\u00020\u000f2¢\u0001\u0010\u0010\u001a\u009d\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\u000e0\u0011J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00190\rJ.\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0017¨\u0006\u001d"}, d2 = {"Lcn/sast/framework/report/sqldelight/FileQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "id", "Lapp/cash/sqldelight/ExecutableQuery;", "", "file_raw_content_hash", "", "relative_path", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function7;", "Lkotlin/ParameterName;", "name", "lines", "encoding", "file_raw_content_size", "", "file_raw_content", "Lcn/sast/framework/report/sqldelight/File;", "insert", "", "IdQuery", "corax-framework"})
/* loaded from: FileQueries.class */
public final class FileQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final ExecutableQuery<Long> id(@NotNull String file_raw_content_hash, @NotNull String relative_path) {
        Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
        Intrinsics.checkNotNullParameter(relative_path, "relative_path");
        return new IdQuery(this, file_raw_content_hash, relative_path, FileQueries::id$lambda$0);
    }

    private static final long id$lambda$0(SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        return l.longValue();
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function7<? super Long, ? super String, ? super String, ? super Long, ? super String, ? super Long, ? super byte[], ? extends T> function7) {
        Intrinsics.checkNotNullParameter(function7, "mapper");
        return QueryKt.Query(-1345738410, new String[]{"File"}, getDriver(), "File.sq", "selectAll", "SELECT File.id, File.file_raw_content_hash, File.relative_path, File.lines, File.encoding, File.file_raw_content_size, File.file_raw_content\nFROM File", (v1) -> {
            return selectAll$lambda$1(r6, v1);
        });
    }

    private static final Object selectAll$lambda$1(Function7 $mapper, SqlCursor cursor) {
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
        Long l3 = cursor.getLong(5);
        Intrinsics.checkNotNull(l3);
        byte[] bytes = cursor.getBytes(6);
        Intrinsics.checkNotNull(bytes);
        return $mapper.invoke(l, string, string2, l2, string3, l3, bytes);
    }

    @NotNull
    public final Query<File> selectAll() {
        return selectAll((v0, v1, v2, v3, v4, v5, v6) -> {
            return selectAll$lambda$2(v0, v1, v2, v3, v4, v5, v6);
        });
    }

    private static final File selectAll$lambda$2(long id, String file_raw_content_hash, String relative_path, long lines, String encoding, long file_raw_content_size, byte[] file_raw_content) {
        Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
        Intrinsics.checkNotNullParameter(relative_path, "relative_path");
        Intrinsics.checkNotNullParameter(file_raw_content, "file_raw_content");
        return new File(id, file_raw_content_hash, relative_path, lines, encoding, file_raw_content_size, file_raw_content);
    }

    public final void insert(@NotNull String file_raw_content_hash, @NotNull String relative_path, long lines, long file_raw_content_size, @NotNull byte[] file_raw_content) {
        Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
        Intrinsics.checkNotNullParameter(relative_path, "relative_path");
        Intrinsics.checkNotNullParameter(file_raw_content, "file_raw_content");
        Transacter.DefaultImpls.transaction$default((Transacter) this, false, (v6) -> {
            return insert$lambda$4(r2, r3, r4, r5, r6, r7, v6);
        }, 1, (Object) null);
        notifyQueries(-204145144, FileQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(FileQueries this$0, String $file_raw_content_hash, String $relative_path, long $lines, long $file_raw_content_size, byte[] $file_raw_content, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.getDriver().execute(1385015225, "INSERT OR IGNORE INTO File(file_raw_content_hash, relative_path, lines, file_raw_content_size, file_raw_content)\n    VALUES (?, ?, ?, ?, ?)", 5, (v5) -> {
            return insert$lambda$4$lambda$3(r4, r5, r6, r7, r8, v5);
        });
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$4$lambda$3(String $file_raw_content_hash, String $relative_path, long $lines, long $file_raw_content_size, byte[] $file_raw_content, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $file_raw_content_hash);
        $this$execute.bindString(1, $relative_path);
        $this$execute.bindLong(2, Long.valueOf($lines));
        $this$execute.bindLong(3, Long.valueOf($file_raw_content_size));
        $this$execute.bindBytes(4, $file_raw_content);
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("File");
        return Unit.INSTANCE;
    }

    /* compiled from: FileQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028��0\b¢\u0006\u0004\b\n\u0010\u000bJ.\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0001\u0010\u00112\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00100\bH\u0016J\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\r¨\u0006\u0013"}, d2 = {"Lcn/sast/framework/report/sqldelight/FileQueries$IdQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "file_raw_content_hash", "", "relative_path", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/FileQueries;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getFile_raw_content_hash", "()Ljava/lang/String;", "getRelative_path", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "corax-framework"})
    /* loaded from: FileQueries$IdQuery.class */
    private final class IdQuery<T> extends ExecutableQuery<T> {

        @NotNull
        private final String file_raw_content_hash;

        @NotNull
        private final String relative_path;
        final /* synthetic */ FileQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdQuery(@NotNull FileQueries this$0, @NotNull String file_raw_content_hash, @NotNull String relative_path, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(file_raw_content_hash, "file_raw_content_hash");
            Intrinsics.checkNotNullParameter(relative_path, "relative_path");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
            this.file_raw_content_hash = file_raw_content_hash;
            this.relative_path = relative_path;
        }

        @NotNull
        public final String getFile_raw_content_hash() {
            return this.file_raw_content_hash;
        }

        @NotNull
        public final String getRelative_path() {
            return this.relative_path;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            FileQueries fileQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v3) -> {
                return execute$lambda$1(r2, r3, r4, v3);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$1(FileQueries this$0, Function1 $mapper, IdQuery this$1, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return this$0.getDriver().executeQuery(2042233115, "SELECT id FROM File WHERE file_raw_content_hash = ? AND relative_path = ?", $mapper, 2, (v1) -> {
                return execute$lambda$1$lambda$0(r5, v1);
            });
        }

        private static final Unit execute$lambda$1$lambda$0(IdQuery this$0, SqlPreparedStatement $this$executeQuery) {
            Intrinsics.checkNotNullParameter($this$executeQuery, "$this$executeQuery");
            $this$executeQuery.bindString(0, this$0.file_raw_content_hash);
            $this$executeQuery.bindString(1, this$0.relative_path);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "File.sq:id";
        }
    }
}
