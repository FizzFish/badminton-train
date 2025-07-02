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
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RuleSetInfoQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JÖ\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2½\u0001\u0010\n\u001a¸\u0001\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017¨\u0006\u001b"}, d2 = {"Lcn/sast/framework/report/sqldelight/RuleSetInfoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function8;", "", "Lkotlin/ParameterName;", "name", "language", "description", "prefix", "id_pattern", "", "section_level", "version", "revision", "Lcn/sast/framework/report/sqldelight/RuleSetInfo;", "insert", "", "RuleSetInfo", "corax-framework"})
/* loaded from: RuleSetInfoQueries.class */
public final class RuleSetInfoQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RuleSetInfoQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function8<? super String, ? super String, ? super String, ? super String, ? super String, ? super Long, ? super String, ? super String, ? extends T> function8) {
        Intrinsics.checkNotNullParameter(function8, "mapper");
        return QueryKt.Query(1731641704, new String[]{"RuleSetInfo"}, getDriver(), "RuleSetInfo.sq", "selectAll", "SELECT RuleSetInfo.name, RuleSetInfo.language, RuleSetInfo.description, RuleSetInfo.prefix, RuleSetInfo.id_pattern, RuleSetInfo.section_level, RuleSetInfo.version, RuleSetInfo.revision\nFROM RuleSetInfo", (v1) -> {
            return selectAll$lambda$0(r6, v1);
        });
    }

    private static final Object selectAll$lambda$0(Function8 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Intrinsics.checkNotNull(string2);
        String string3 = cursor.getString(2);
        String string4 = cursor.getString(3);
        String string5 = cursor.getString(4);
        Long l = cursor.getLong(5);
        String string6 = cursor.getString(6);
        Intrinsics.checkNotNull(string6);
        String string7 = cursor.getString(7);
        Intrinsics.checkNotNull(string7);
        return $mapper.invoke(string, string2, string3, string4, string5, l, string6, string7);
    }

    @NotNull
    public final Query<RuleSetInfo> selectAll() {
        return selectAll(RuleSetInfoQueries::selectAll$lambda$1);
    }

    private static final RuleSetInfo selectAll$lambda$1(String name, String language, String description, String prefix, String id_pattern, Long section_level, String version, String revision) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(revision, "revision");
        return new RuleSetInfo(name, language, description, prefix, id_pattern, section_level, version, revision);
    }

    public final void insert(@NotNull RuleSetInfo RuleSetInfo) {
        Intrinsics.checkNotNullParameter(RuleSetInfo, "RuleSetInfo");
        getDriver().execute(1006841654, "INSERT OR IGNORE INTO RuleSetInfo (name, language, description, prefix, id_pattern, section_level, version, revision)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?)", 8, (v1) -> {
            return insert$lambda$2(r4, v1);
        });
        notifyQueries(1006841654, RuleSetInfoQueries::insert$lambda$3);
    }

    private static final Unit insert$lambda$2(RuleSetInfo $RuleSetInfo, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $RuleSetInfo.getName());
        $this$execute.bindString(1, $RuleSetInfo.getLanguage());
        $this$execute.bindString(2, $RuleSetInfo.getDescription());
        $this$execute.bindString(3, $RuleSetInfo.getPrefix());
        $this$execute.bindString(4, $RuleSetInfo.getId_pattern());
        $this$execute.bindLong(5, $RuleSetInfo.getSection_level());
        $this$execute.bindString(6, $RuleSetInfo.getVersion());
        $this$execute.bindString(7, $RuleSetInfo.getRevision());
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$3(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("RuleSetInfo");
        return Unit.INSTANCE;
    }
}
