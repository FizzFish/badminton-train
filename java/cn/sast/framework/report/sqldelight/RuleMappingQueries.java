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

/* compiled from: RuleMappingQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jg\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2O\u0010\n\u001aK\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007Jg\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0014\"\b\b��\u0010\b*\u00020\t2O\u0010\n\u001aK\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012¨\u0006\u0019"}, d2 = {"Lcn/sast/framework/report/sqldelight/RuleMappingQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "verify_rule_name", "Lapp/cash/sqldelight/ExecutableQuery;", "T", "", "mapper", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "rule_name", "standard_name", "standard_rule", "Lcn/sast/framework/report/sqldelight/RuleMapping;", "selectAll", "Lapp/cash/sqldelight/Query;", "insert", "", "RuleMapping", "Verify_rule_nameQuery", "corax-framework"})
/* loaded from: RuleMappingQueries.class */
public final class RuleMappingQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RuleMappingQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> ExecutableQuery<T> verify_rule_name(@NotNull Function3<? super String, ? super String, ? super String, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return new Verify_rule_nameQuery(this, (v1) -> {
            return verify_rule_name$lambda$0(r3, v1);
        });
    }

    private static final Object verify_rule_name$lambda$0(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        return $mapper.invoke(string, cursor.getString(1), cursor.getString(2));
    }

    @NotNull
    public final ExecutableQuery<RuleMapping> verify_rule_name() {
        return verify_rule_name(RuleMappingQueries::verify_rule_name$lambda$1);
    }

    private static final RuleMapping verify_rule_name$lambda$1(String rule_name, String standard_name, String standard_rule) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        return new RuleMapping(rule_name, standard_name, standard_rule);
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull Function3<? super String, ? super String, ? super String, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return QueryKt.Query(1383017866, new String[]{"RuleMapping"}, getDriver(), "RuleMapping.sq", "selectAll", "SELECT RuleMapping.rule_name, RuleMapping.standard_name, RuleMapping.standard_rule\nFROM RuleMapping", (v1) -> {
            return selectAll$lambda$2(r6, v1);
        });
    }

    private static final Object selectAll$lambda$2(Function3 $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        return $mapper.invoke(string, cursor.getString(1), cursor.getString(2));
    }

    @NotNull
    public final Query<RuleMapping> selectAll() {
        return selectAll(RuleMappingQueries::selectAll$lambda$3);
    }

    private static final RuleMapping selectAll$lambda$3(String rule_name, String standard_name, String standard_rule) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        return new RuleMapping(rule_name, standard_name, standard_rule);
    }

    public final void insert(@NotNull RuleMapping RuleMapping) {
        Intrinsics.checkNotNullParameter(RuleMapping, "RuleMapping");
        getDriver().execute(-1057683884, "INSERT OR IGNORE INTO RuleMapping (rule_name, standard_name, standard_rule)\nVALUES (?, ?, ?)", 3, (v1) -> {
            return insert$lambda$4(r4, v1);
        });
        notifyQueries(-1057683884, RuleMappingQueries::insert$lambda$5);
    }

    private static final Unit insert$lambda$4(RuleMapping $RuleMapping, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $RuleMapping.getRule_name());
        $this$execute.bindString(1, $RuleMapping.getStandard_name());
        $this$execute.bindString(2, $RuleMapping.getStandard_rule());
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$5(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("RuleMapping");
        return Unit.INSTANCE;
    }

    /* compiled from: RuleMappingQueries.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0082\u0004\u0018��*\n\b��\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028��0\u0005¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/RuleMappingQueries$Verify_rule_nameQuery;", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcn/sast/framework/report/sqldelight/RuleMappingQueries;Lkotlin/jvm/functions/Function1;)V", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "corax-framework"})
    /* loaded from: RuleMappingQueries$Verify_rule_nameQuery.class */
    private final class Verify_rule_nameQuery<T> extends ExecutableQuery<T> {
        final /* synthetic */ RuleMappingQueries this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Verify_rule_nameQuery(@NotNull RuleMappingQueries this$0, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = this$0;
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            Transacter transacter = this.this$0;
            RuleMappingQueries ruleMappingQueries = this.this$0;
            return (QueryResult) Transacter.DefaultImpls.transactionWithResult$default(transacter, false, (v2) -> {
                return execute$lambda$0(r2, r3, v2);
            }, 1, (Object) null);
        }

        private static final QueryResult execute$lambda$0(RuleMappingQueries this$0, Function1 $mapper, TransactionWithReturn $this$transactionWithResult) {
            Intrinsics.checkNotNullParameter($this$transactionWithResult, "$this$transactionWithResult");
            return SqlDriver.DefaultImpls.executeQuery$default(this$0.getDriver(), -976712076, "SELECT RuleMapping.rule_name, RuleMapping.standard_name, RuleMapping.standard_rule FROM RuleMapping WHERE rule_name NOT IN (SELECT name FROM Rule)", $mapper, 0, (Function1) null, 16, (Object) null);
        }

        @NotNull
        public String toString() {
            return "RuleMapping.sq:verify_rule_name";
        }
    }
}
