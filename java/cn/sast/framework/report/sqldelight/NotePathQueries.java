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
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: NotePathQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0091\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2y\u0010\n\u001au\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007J\u0091\u0001\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\b0\u0016\"\b\b��\u0010\b*\u00020\t2y\u0010\n\u001au\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014¨\u0006\u001b"}, d2 = {"Lcn/sast/framework/report/sqldelight/NotePathQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_note", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function5;", "", "Lkotlin/ParameterName;", "name", "__note_array_hash_id", "note_sequence", "note_stack_depth", "note_is_key_event", "__note_id", "Lcn/sast/framework/report/sqldelight/NotePath;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "NotePath", "Verify_noteQuery", "corax-framework"})
/* loaded from: NotePathQueries.class */
public final class NotePathQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotePathQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_note(@NotNull Function5<? super Long, ? super Long, ? super Long, ? super Long, ? super Long, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "mapper");
        return new Verify_noteQuery(this, (v1) -> {
            return verify_note$lambda$0(r3, v1);
        });
    }

    private static final Object verify_note$lambda$0(Function5 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        Long l3 = cursor.getLong(2);
        Long l4 = cursor.getLong(3);
        Long l5 = cursor.getLong(4);
        Intrinsics.checkNotNull(l5);
        return $mapper.invoke(l, l2, l3, l4, l5);
    }

    @NotNull
    public final ExecutableQuery<NotePath> verify_note() {
        return verify_note((v0, v1, v2, v3, v4) -> {
            return verify_note$lambda$1(v0, v1, v2, v3, v4);
        });
    }

    private static final NotePath verify_note$lambda$1(long __note_array_hash_id, long note_sequence, Long note_stack_depth, Long note_is_key_event, long __note_id) {
        return new NotePath(__note_array_hash_id, note_sequence, note_stack_depth, note_is_key_event, __note_id);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function5<? super Long, ? super Long, ? super Long, ? super Long, ? super Long, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "mapper");
        return QueryKt.Query(-1170787845, new String[]{"NotePath"}, getDriver(), "NotePath.sq", "selectAll", "SELECT NotePath.__note_array_hash_id, NotePath.note_sequence, NotePath.note_stack_depth, NotePath.note_is_key_event, NotePath.__note_id\nFROM NotePath", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function5 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        Long l2 = cursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        Long l3 = cursor.getLong(2);
        Long l4 = cursor.getLong(3);
        Long l5 = cursor.getLong(4);
        Intrinsics.checkNotNull(l5);
        return $mapper.invoke(l, l2, l3, l4, l5);
    }

    @NotNull
    public final Query<NotePath> selectAll() {
        return selectAll((v0, v1, v2, v3, v4) -> {
            return selectAll$lambda$3(v0, v1, v2, v3, v4);
        });
    }

    private static final NotePath selectAll$lambda$3(long __note_array_hash_id, long note_sequence, Long note_stack_depth, Long note_is_key_event, long __note_id) {
        return new NotePath(__note_array_hash_id, note_sequence, note_stack_depth, note_is_key_event, __note_id);
    }

    public final void insert(@NotNull NotePath NotePath) {
        Intrinsics.checkNotNullParameter(NotePath, "NotePath");
        getDriver().execute(-476332157, "INSERT OR IGNORE INTO NotePath (__note_array_hash_id, note_sequence, note_stack_depth, note_is_key_event, __note_id)\nVALUES (?, ?, ?, ?, ?)", 5, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(-476332157, NotePathQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(NotePath $NotePath, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($NotePath.get__note_array_hash_id()));
        $this$execute.bindLong(1, Long.valueOf($NotePath.getNote_sequence()));
        $this$execute.bindLong(2, $NotePath.getNote_stack_depth());
        $this$execute.bindLong(3, $NotePath.getNote_is_key_event());
        $this$execute.bindLong(4, Long.valueOf($NotePath.get__note_id()));
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("NotePath");
        return Unit.INSTANCE;
    }

    /* compiled from: NotePathQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/NotePathQueries$Verify_noteQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/NotePathQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: NotePathQueries$Verify_noteQuery.class */
    private final class Verify_noteQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ NotePathQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_noteQuery(@NotNull NotePathQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            NotePathQueries notePathQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(NotePathQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -1676993121, "SELECT NotePath.__note_array_hash_id, NotePath.note_sequence, NotePath.note_stack_depth, NotePath.note_is_key_event, NotePath.__note_id FROM NotePath WHERE __note_id NOT IN (SELECT id FROM Note)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "NotePath.sq:verify_note";
        }
    }
}
