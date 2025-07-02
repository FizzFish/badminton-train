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

/* compiled from: MacroExpansionQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007JN\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\b0\u0013\"\b\b��\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011¨\u0006\u0018"}, d2 = {"Lcn/sast/framework/report/sqldelight/MacroExpansionQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_note", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "__macro_note_set_hash_id", "__macro_note_id", "Lcn/sast/framework/report/sqldelight/MacroExpansion;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "MacroExpansion", "Verify_noteQuery", "corax-framework"})
/* loaded from: MacroExpansionQueries.class */
public final class MacroExpansionQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MacroExpansionQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_note(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return new Verify_noteQuery(this, (v1) -> {
            return verify_note$lambda$0(r3, v1);
        });
    }

    private static final Object verify_note$lambda$0(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final ExecutableQuery<MacroExpansion> verify_note() {
        return verify_note((v0, v1) -> {
            return verify_note$lambda$1(v0, v1);
        });
    }

    private static final MacroExpansion verify_note$lambda$1(long __macro_note_set_hash_id, long __macro_note_id) {
        return new MacroExpansion(__macro_note_set_hash_id, __macro_note_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function2<? super Long, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return QueryKt.Query(1160581151, new String[]{"MacroExpansion"}, getDriver(), "MacroExpansion.sq", "selectAll", "SELECT MacroExpansion.__macro_note_set_hash_id, MacroExpansion.__macro_note_id\nFROM MacroExpansion", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return $mapper.invoke(l, l2);
    }

    @NotNull
    public final Query<MacroExpansion> selectAll() {
        return selectAll((v0, v1) -> {
            return selectAll$lambda$3(v0, v1);
        });
    }

    private static final MacroExpansion selectAll$lambda$3(long __macro_note_set_hash_id, long __macro_note_id) {
        return new MacroExpansion(__macro_note_set_hash_id, __macro_note_id);
    }

    public final void insert(@NotNull MacroExpansion MacroExpansion) {
        Intrinsics.checkNotNullParameter(MacroExpansion, "MacroExpansion");
        getDriver().execute(-1356123169, "INSERT OR IGNORE INTO MacroExpansion (__macro_note_set_hash_id, __macro_note_id)\nVALUES (?, ?)", 2, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(-1356123169, MacroExpansionQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(MacroExpansion $MacroExpansion, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($MacroExpansion.get__macro_note_set_hash_id()));
        $this$execute.bindLong(1, Long.valueOf($MacroExpansion.get__macro_note_id()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("MacroExpansion");
        return Unit.INSTANCE;
    }

    /* compiled from: MacroExpansionQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/MacroExpansionQueries$Verify_noteQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/MacroExpansionQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: MacroExpansionQueries$Verify_noteQuery.class */
    private final class Verify_noteQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ MacroExpansionQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_noteQuery(@NotNull MacroExpansionQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            MacroExpansionQueries macroExpansionQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(MacroExpansionQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -555923005, "SELECT MacroExpansion.__macro_note_set_hash_id, MacroExpansion.__macro_note_id FROM MacroExpansion WHERE __macro_note_id NOT IN (SELECT id FROM Note)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "MacroExpansion.sq:verify_note";
        }
    }
}
