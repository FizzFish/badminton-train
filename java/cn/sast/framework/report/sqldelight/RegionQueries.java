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
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RegionQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u000eJª\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b��\u0010\u0011*\u00020\u00122\u0091\u0001\u0010\u0013\u001a\u008c\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\r\u0012\u0004\u0012\u0002H\u00110\u0014J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010J9\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcn/sast/framework/report/sqldelight/RegionQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "id", "Lapp/cash/sqldelight/ExecutableQuery;", "", "__file_id", "start_line", "start_column", "end_line", "end_column", "(JJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lapp/cash/sqldelight/ExecutableQuery;", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "Lcn/sast/framework/report/sqldelight/Region;", "insert", "", "(JJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "IdQuery", "corax-framework"})
/* loaded from: RegionQueries.class */
public final class RegionQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegionQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final ExecutableQuery<Long> id(long __file_id, long start_line, @Nullable Long start_column, @Nullable Long end_line, @Nullable Long end_column) {
        return new IdQuery(this, __file_id, start_line, start_column, end_line, end_column, RegionQueries::id$lambda$0);
    }

    private static final long id$lambda$0(SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        return l.longValue();
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function6<? super Long, ? super Long, ? super Long, ? super Long, ? super Long, ? super Long, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return QueryKt.Query(-1166723042, new String[]{"Region"}, getDriver(), "Region.sq", "selectAll", "SELECT Region.id, Region.__file_id, Region.start_line, Region.start_column, Region.end_line, Region.end_column\nFROM Region", (v1) -> {
            return selectAll$lambda$1(r6, v1);
        });
    }

    private static final Object selectAll$lambda$1(Function6 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        Long l3 = cursor.getLong(2);
        Intrinsics.checkNotNull(l3);
        return $mapper.invoke(l, l2, l3, cursor.getLong(3), cursor.getLong(4), cursor.getLong(5));
    }

    @NotNull
    public final Query<Region> selectAll() {
        return selectAll((v0, v1, v2, v3, v4, v5) -> {
            return selectAll$lambda$2(v0, v1, v2, v3, v4, v5);
        });
    }

    private static final Region selectAll$lambda$2(long id, long __file_id, long start_line, Long start_column, Long end_line, Long end_column) {
        return new Region(id, __file_id, start_line, start_column, end_line, end_column);
    }

    public final void insert(long __file_id, long start_line, @Nullable Long start_column, @Nullable Long end_line, @Nullable Long end_column) {
        Transacter.DefaultImpls.transaction$default((Transacter) this, false, (v6) -> {
            return insert$lambda$4(r2, r3, r4, r5, r6, r7, v6);
        }, 1, (Object) null);
        notifyQueries(1577657408, RegionQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(RegionQueries this$0, long $__file_id, long $start_line, Long $start_column, Long $end_line, Long $end_column, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.getDriver().execute(5316593, "INSERT OR IGNORE INTO Region(__file_id, start_line, start_column, end_line, end_column)\n    VALUES (?, ?, ?, ?, ?)", 5, (v5) -> {
            return insert$lambda$4$lambda$3(r4, r5, r6, r7, r8, v5);
        });
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$4$lambda$3(long $__file_id, long $start_line, Long $start_column, Long $end_line, Long $end_column, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($__file_id));
        $this$execute.bindLong(1, Long.valueOf($start_line));
        $this$execute.bindLong(2, $start_column);
        $this$execute.bindLong(3, $end_line);
        $this$execute.bindLong(4, $end_column);
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("Region");
        return Unit.INSTANCE;
    }

    /* compiled from: RegionQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BI\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028��0\u000b¢\u0006\u0004\b\r\u0010\u000eJ.\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\u0004\b\u0001\u0010\u00192\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcn/sast/framework/report/sqldelight/RegionQueries$IdQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "__file_id", "", "start_line", "start_column", "end_line", "end_column", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/RegionQueries;JJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "get__file_id", "()J", "getStart_line", "getStart_column", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEnd_line", "getEnd_column", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: RegionQueries$IdQuery.class */
    private final class IdQuery<T> extends ExecutableQuery<T> {
        private final long __file_id;
        private final long start_line;

        @Nullable
        private final Long start_column;

        @Nullable
        private final Long end_line;

        @Nullable
        private final Long end_column;
        final /* synthetic */ RegionQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdQuery(RegionQueries this$0, long __file_id, @Nullable long start_line, @Nullable Long start_column, @Nullable Long end_line, @NotNull Long end_column, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
            this.__file_id = __file_id;
            this.start_line = start_line;
            this.start_column = start_column;
            this.end_line = end_line;
            this.end_column = end_column;
        }

        public final long get__file_id() {
            return this.__file_id;
        }

        public final long getStart_line() {
            return this.start_line;
        }

        @Nullable
        public final Long getStart_column() {
            return this.start_column;
        }

        @Nullable
        public final Long getEnd_line() {
            return this.end_line;
        }

        @Nullable
        public final Long getEnd_column() {
            return this.end_column;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            RegionQueries regionQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v3) -> {
                return execute$lambda$1(r2, r3, r4, v3);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$1(RegionQueries this$0, IdQuery this$1, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return this$0.getDriver().executeQuery((Integer) null, "SELECT id FROM Region WHERE __file_id = ? AND start_line = ? AND start_column " + (this$1.start_column == null ? "IS" : "=") + " ? AND end_line " + (this$1.end_line == null ? "IS" : "=") + " ? AND end_column " + (this$1.end_column == null ? "IS" : "=") + " ?", $mapper, 5, (v1) -> {
                return execute$lambda$1$lambda$0(r5, v1);
            });
        }

        private static final Unit execute$lambda$1$lambda$0(IdQuery this$0, SqlPreparedStatement $this$executeQuery) {
            Intrinsics.checkNotNullParameter($this$executeQuery, "$this$executeQuery");
            $this$executeQuery.bindLong(0, Long.valueOf(this$0.__file_id));
            $this$executeQuery.bindLong(1, Long.valueOf(this$0.start_line));
            $this$executeQuery.bindLong(2, this$0.start_column);
            $this$executeQuery.bindLong(3, this$0.end_line);
            $this$executeQuery.bindLong(4, this$0.end_column);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "Region.sq:id";
        }
    }
}
