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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SchemaInfoQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/report/sqldelight/SchemaInfoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "key", "value_", "Lcn/sast/framework/report/sqldelight/SchemaInfo;", "insert", "", "SchemaInfo", "corax-framework"})
/* loaded from: SchemaInfoQueries.class */
public final class SchemaInfoQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchemaInfoQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function2<? super String, ? super String, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return QueryKt.Query(-1428765821, new String[]{"SchemaInfo"}, getDriver(), "SchemaInfo.sq", "selectAll", "SELECT SchemaInfo.key, SchemaInfo.value FROM SchemaInfo", (v1) -> {
            return selectAll$lambda$0(r6, v1);
        });
    }

    private static final Object selectAll$lambda$0(Function2 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Intrinsics.checkNotNull(string2);
        return $mapper.invoke(string, string2);
    }

    @NotNull
    public final Query<SchemaInfo> selectAll() {
        return selectAll(SchemaInfoQueries::selectAll$lambda$1);
    }

    private static final SchemaInfo selectAll$lambda$1(String key, String value_) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value_, "value_");
        return new SchemaInfo(key, value_);
    }

    public final void insert(@NotNull SchemaInfo SchemaInfo) {
        Intrinsics.checkNotNullParameter(SchemaInfo, "SchemaInfo");
        getDriver().execute(710466299, "INSERT OR IGNORE INTO SchemaInfo (key, value) VALUES (?, ?)", 2, (v1) -> {
            return insert$lambda$2(r4, v1);
        });
        notifyQueries(710466299, SchemaInfoQueries::insert$lambda$3);
    }

    private static final Unit insert$lambda$2(SchemaInfo $SchemaInfo, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $SchemaInfo.getKey());
        $this$execute.bindString(1, $SchemaInfo.getValue_());
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$3(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("SchemaInfo");
        return Unit.INSTANCE;
    }
}
