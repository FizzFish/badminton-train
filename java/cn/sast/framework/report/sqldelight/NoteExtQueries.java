package cn.sast.framework.report.sqldelight;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
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

/* compiled from: NoteExtQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jc\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2K\u0010\n\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/report/sqldelight/NoteExtQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "__note_id", "", "attr_name", "attr_value", "Lcn/sast/framework/report/sqldelight/NoteExt;", "insert", "", "NoteExt", "corax-framework"})
/* loaded from: NoteExtQueries.class */
public final class NoteExtQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoteExtQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function3<? super Long, ? super String, ? super String, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return QueryKt.Query(313069709, new String[]{"NoteExt"}, getDriver(), "NoteExt.sq", "selectAll", "SELECT NoteExt.__note_id, NoteExt.attr_name, NoteExt.attr_value\nFROM NoteExt", (v1) -> {
            return selectAll$lambda$0(r6, v1);
        });
    }

    private static final Object selectAll$lambda$0(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Long l = cursor.getLong(0);
        Intrinsics.checkNotNull(l);
        String string = cursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(2);
        Intrinsics.checkNotNull(string2);
        return $mapper.invoke(l, string, string2);
    }

    @NotNull
    public final Query<NoteExt> selectAll() {
        return selectAll((v0, v1, v2) -> {
            return selectAll$lambda$1(v0, v1, v2);
        });
    }

    private static final NoteExt selectAll$lambda$1(long __note_id, String attr_name, String attr_value) {
        Intrinsics.checkNotNullParameter(attr_name, "attr_name");
        Intrinsics.checkNotNullParameter(attr_value, "attr_value");
        return new NoteExt(__note_id, attr_name, attr_value);
    }

    public final void insert(@NotNull NoteExt NoteExt) {
        Intrinsics.checkNotNullParameter(NoteExt, "NoteExt");
        getDriver().execute(-1109476815, "INSERT OR IGNORE INTO NoteExt (__note_id, attr_name, attr_value)\nVALUES (?, ?, ?)", 3, (v1) -> {
            return insert$lambda$2(r4, v1);
        });
        notifyQueries(-1109476815, NoteExtQueries::insert$lambda$3);
    }

    private static final Unit insert$lambda$2(NoteExt $NoteExt, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindLong(0, Long.valueOf($NoteExt.get__note_id()));
        $this$execute.bindString(1, $NoteExt.getAttr_name());
        $this$execute.bindString(2, $NoteExt.getAttr_value());
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$3(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("NoteExt");
        return Unit.INSTANCE;
    }
}
