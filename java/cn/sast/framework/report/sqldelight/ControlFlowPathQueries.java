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

/* compiled from: ControlFlowPathQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jc\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2K\u0010\n\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007Jc\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0014\"\b\b��\u0010\b*\u00020\t2K\u0010\n\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012¨\u0006\u0019"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowPathQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_control_flow", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "__control_flow_array_hash_id", "control_flow_sequence", "__control_flow_id", "Lcn/sast/framework/report/sqldelight/ControlFlowPath;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "ControlFlowPath", "Verify_control_flowQuery", "corax-framework"})
/* loaded from: ControlFlowPathQueries.class */
public final class ControlFlowPathQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlFlowPathQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_control_flow(@NotNull Function3<? super Long, ? super Long, ? super Long, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return new Verify_control_flowQuery(this, (v1) -> {
            return verify_control_flow$lambda$0(r3, v1);
        });
    }

    private static final Object verify_control_flow$lambda$0(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        Long l3 = cursor.getLong(2);
        Intrinsics.checkNotNull(l3);
        return $mapper.invoke(l, l2, l3);
    }

    @NotNull
    public final ExecutableQuery<ControlFlowPath> verify_control_flow() {
        return verify_control_flow((v0, v1, v2) -> {
            return verify_control_flow$lambda$1(v0, v1, v2);
        });
    }

    private static final ControlFlowPath verify_control_flow$lambda$1(long __control_flow_array_hash_id, long control_flow_sequence, long __control_flow_id) {
        return new ControlFlowPath(__control_flow_array_hash_id, control_flow_sequence, __control_flow_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function3<? super Long, ? super Long, ? super Long, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return QueryKt.Query(-180228340, new String[]{"ControlFlowPath"}, getDriver(), "ControlFlowPath.sq", "selectAll", "SELECT ControlFlowPath.__control_flow_array_hash_id, ControlFlowPath.control_flow_sequence, ControlFlowPath.__control_flow_id\nFROM ControlFlowPath", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        Long l3 = cursor.getLong(2);
        Intrinsics.checkNotNull(l3);
        return $mapper.invoke(l, l2, l3);
    }

    @NotNull
    public final Query<ControlFlowPath> selectAll() {
        return selectAll((v0, v1, v2) -> {
            return selectAll$lambda$3(v0, v1, v2);
        });
    }

    private static final ControlFlowPath selectAll$lambda$3(long __control_flow_array_hash_id, long control_flow_sequence, long __control_flow_id) {
        return new ControlFlowPath(__control_flow_array_hash_id, control_flow_sequence, __control_flow_id);
    }

    public final void insert(@NotNull ControlFlowPath ControlFlowPath) {
        Intrinsics.checkNotNullParameter(ControlFlowPath, "ControlFlowPath");
        getDriver().execute(-947157998, "INSERT OR IGNORE INTO ControlFlowPath (__control_flow_array_hash_id, control_flow_sequence, __control_flow_id)\nVALUES (?, ?, ?)", 3, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(-947157998, ControlFlowPathQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(ControlFlowPath $ControlFlowPath, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($ControlFlowPath.get__control_flow_array_hash_id()));
        $this$execute.bindLong(1, Long.valueOf($ControlFlowPath.getControl_flow_sequence()));
        $this$execute.bindLong(2, Long.valueOf($ControlFlowPath.get__control_flow_id()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("ControlFlowPath");
        return Unit.INSTANCE;
    }

    /* compiled from: ControlFlowPathQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowPathQueries$Verify_control_flowQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/ControlFlowPathQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: ControlFlowPathQueries$Verify_control_flowQuery.class */
    private final class Verify_control_flowQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ ControlFlowPathQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_control_flowQuery(@NotNull ControlFlowPathQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            ControlFlowPathQueries controlFlowPathQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(ControlFlowPathQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), 1682752654, "SELECT ControlFlowPath.__control_flow_array_hash_id, ControlFlowPath.control_flow_sequence, ControlFlowPath.__control_flow_id FROM ControlFlowPath WHERE __control_flow_id NOT IN (SELECT id FROM ControlFlow)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "ControlFlowPath.sq:verify_control_flow";
        }
    }
}
