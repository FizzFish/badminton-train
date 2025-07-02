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
import kotlin.jvm.functions.FunctionN;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RuleQueries.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u009d\u0005\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b��\u0010\b*\u00020\t2ÿ\u0004\u0010\n\u001aú\u0004\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(!\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\"\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b('\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(*\u0012\u0004\u0012\u0002H\b0\u000b¢\u0006\u0002\u0010+J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020,0\u0007J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020,¨\u00060"}, d2 = {"Lcn/sast/framework/report/sqldelight/RuleQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "selectAll", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function28;", "", "Lkotlin/ParameterName;", "name", "short_description_en", "short_description_zh", "severity", "priority", "language", "precision", "recall", "likelihood", "impact", "technique", "analysis_scope", "performance", "", "configurable", "implemented", "static_analyzability", "c_allocated_target", "category_en", "category_zh", "rule_sort_number", "chapter_name_1", "chapter_name_2", "chapter_name_3", "chapter_name_4", "description_en", "description_zh", "document_en", "document_zh", "(Lkotlin/jvm/functions/FunctionN;)Lapp/cash/sqldelight/Query;", "Lcn/sast/framework/report/sqldelight/Rule;", "insert", "", "Rule", "corax-framework"})
/* loaded from: RuleQueries.class */
public final class RuleQueries extends TransacterImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RuleQueries(@NotNull SqlDriver driver) {
        super(driver);
        Intrinsics.checkNotNullParameter(driver, "driver");
    }

    @NotNull
    public final <T> Query<T> selectAll(@NotNull FunctionN<? extends T> functionN) {
        Intrinsics.checkNotNullParameter(functionN, "mapper");
        return QueryKt.Query(177633238, new String[]{"Rule"}, getDriver(), "Rule.sq", "selectAll", "SELECT Rule.name, Rule.short_description_en, Rule.short_description_zh, Rule.severity, Rule.priority, Rule.language, Rule.precision, Rule.recall, Rule.likelihood, Rule.impact, Rule.technique, Rule.analysis_scope, Rule.performance, Rule.configurable, Rule.implemented, Rule.static_analyzability, Rule.c_allocated_target, Rule.category_en, Rule.category_zh, Rule.rule_sort_number, Rule.chapter_name_1, Rule.chapter_name_2, Rule.chapter_name_3, Rule.chapter_name_4, Rule.description_en, Rule.description_zh, Rule.document_en, Rule.document_zh\nFROM Rule", (v1) -> {
            return selectAll$lambda$0(r6, v1);
        });
    }

    private static final Object selectAll$lambda$0(FunctionN $mapper, SqlCursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = cursor.getString(1);
        Intrinsics.checkNotNull(string2);
        String string3 = cursor.getString(2);
        Intrinsics.checkNotNull(string3);
        String string4 = cursor.getString(5);
        Intrinsics.checkNotNull(string4);
        String string5 = cursor.getString(17);
        Intrinsics.checkNotNull(string5);
        String string6 = cursor.getString(18);
        Intrinsics.checkNotNull(string6);
        String string7 = cursor.getString(24);
        Intrinsics.checkNotNull(string7);
        String string8 = cursor.getString(26);
        Intrinsics.checkNotNull(string8);
        String string9 = cursor.getString(27);
        Intrinsics.checkNotNull(string9);
        return $mapper.invoke(new Object[]{string, string2, string3, cursor.getString(3), cursor.getString(4), string4, cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getLong(13), cursor.getLong(14), cursor.getString(15), cursor.getString(16), string5, string6, cursor.getLong(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23), string7, cursor.getString(25), string8, string9});
    }

    @NotNull
    public final Query<Rule> selectAll() {
        return selectAll(new FunctionN<Rule>() { // from class: cn.sast.framework.report.sqldelight.RuleQueries.selectAll.2
            public final /* bridge */ /* synthetic */ Object invoke(Object[] args) {
                if (args.length != 28) {
                    throw new IllegalArgumentException("Expected 28 arguments");
                }
                return invoke((String) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4], (String) args[5], (String) args[6], (String) args[7], (String) args[8], (String) args[9], (String) args[10], (String) args[11], (String) args[12], (Long) args[13], (Long) args[14], (String) args[15], (String) args[16], (String) args[17], (String) args[18], (Long) args[19], (String) args[20], (String) args[21], (String) args[22], (String) args[23], (String) args[24], (String) args[25], (String) args[26], (String) args[27]);
            }

            public final Rule invoke(String name, String short_description_en, String short_description_zh, String severity, String priority, String language, String precision, String recall, String likelihood, String impact, String technique, String analysis_scope, String performance, Long configurable, Long implemented, String static_analyzability, String c_allocated_target, String category_en, String category_zh, Long rule_sort_number, String chapter_name_1, String chapter_name_2, String chapter_name_3, String chapter_name_4, String description_en, String description_zh, String document_en, String document_zh) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(short_description_en, "short_description_en");
                Intrinsics.checkNotNullParameter(short_description_zh, "short_description_zh");
                Intrinsics.checkNotNullParameter(language, "language");
                Intrinsics.checkNotNullParameter(category_en, "category_en");
                Intrinsics.checkNotNullParameter(category_zh, "category_zh");
                Intrinsics.checkNotNullParameter(description_en, "description_en");
                Intrinsics.checkNotNullParameter(document_en, "document_en");
                Intrinsics.checkNotNullParameter(document_zh, "document_zh");
                return new Rule(name, short_description_en, short_description_zh, severity, priority, language, precision, recall, likelihood, impact, technique, analysis_scope, performance, configurable, implemented, static_analyzability, c_allocated_target, category_en, category_zh, rule_sort_number, chapter_name_1, chapter_name_2, chapter_name_3, chapter_name_4, description_en, description_zh, document_en, document_zh);
            }
        });
    }

    public final void insert(@NotNull Rule Rule) {
        Intrinsics.checkNotNullParameter(Rule, "Rule");
        getDriver().execute(-1201750136, "INSERT OR IGNORE INTO Rule (name, short_description_en, short_description_zh, severity, priority, language, precision, recall, likelihood, impact, technique, analysis_scope, performance, configurable, implemented, static_analyzability, c_allocated_target, category_en, category_zh, rule_sort_number, chapter_name_1, chapter_name_2, chapter_name_3, chapter_name_4, description_en, description_zh, document_en, document_zh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 28, (v1) -> {
            return insert$lambda$1(r4, v1);
        });
        notifyQueries(-1201750136, RuleQueries::insert$lambda$2);
    }

    private static final Unit insert$lambda$1(Rule $Rule, SqlPreparedStatement $this$execute) {
        Intrinsics.checkNotNullParameter($this$execute, "$this$execute");
        $this$execute.bindString(0, $Rule.getName());
        $this$execute.bindString(1, $Rule.getShort_description_en());
        $this$execute.bindString(2, $Rule.getShort_description_zh());
        $this$execute.bindString(3, $Rule.getSeverity());
        $this$execute.bindString(4, $Rule.getPriority());
        $this$execute.bindString(5, $Rule.getLanguage());
        $this$execute.bindString(6, $Rule.getPrecision());
        $this$execute.bindString(7, $Rule.getRecall());
        $this$execute.bindString(8, $Rule.getLikelihood());
        $this$execute.bindString(9, $Rule.getImpact());
        $this$execute.bindString(10, $Rule.getTechnique());
        $this$execute.bindString(11, $Rule.getAnalysis_scope());
        $this$execute.bindString(12, $Rule.getPerformance());
        $this$execute.bindLong(13, $Rule.getConfigurable());
        $this$execute.bindLong(14, $Rule.getImplemented());
        $this$execute.bindString(15, $Rule.getStatic_analyzability());
        $this$execute.bindString(16, $Rule.getC_allocated_target());
        $this$execute.bindString(17, $Rule.getCategory_en());
        $this$execute.bindString(18, $Rule.getCategory_zh());
        $this$execute.bindLong(19, $Rule.getRule_sort_number());
        $this$execute.bindString(20, $Rule.getChapter_name_1());
        $this$execute.bindString(21, $Rule.getChapter_name_2());
        $this$execute.bindString(22, $Rule.getChapter_name_3());
        $this$execute.bindString(23, $Rule.getChapter_name_4());
        $this$execute.bindString(24, $Rule.getDescription_en());
        $this$execute.bindString(25, $Rule.getDescription_zh());
        $this$execute.bindString(26, $Rule.getDocument_en());
        $this$execute.bindString(27, $Rule.getDocument_zh());
        return Unit.INSTANCE;
    }

    private static final Unit insert$lambda$2(Function1 emit) {
        Intrinsics.checkNotNullParameter(emit, "emit");
        emit.invoke("Rule");
        return Unit.INSTANCE;
    }
}
