package cn.sast.framework.report;

import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.TransactionWithoutReturn;
import cn.sast.api.config.ChapterFlat;
import cn.sast.api.config.CheckerInfo;
import cn.sast.api.config.CheckerInfoGenResult;
import cn.sast.api.config.CheckerPriorityConfig;
import cn.sast.api.config.Tag;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.sqldelight.Database;
import cn.sast.framework.report.sqldelight.Rule;
import cn.sast.framework.report.sqldelight.RuleMapping;
import cn.sast.framework.report.sqldelight.RuleMappingQueries;
import cn.sast.framework.report.sqldelight.RuleQueries;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SqliteDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u001f2\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n��R&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u000eX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcn/sast/framework/report/RuleAndRuleMapping;", "", "checkerInfo", "Lcn/sast/api/config/CheckerInfoGenResult;", "ruleSortYaml", "Ljava/nio/file/Path;", "<init>", "(Lcn/sast/api/config/CheckerInfoGenResult;Ljava/nio/file/Path;)V", "rules", "", "Lcn/sast/framework/report/sqldelight/Rule;", "ruleMapping", "Lcn/sast/framework/report/sqldelight/RuleMapping;", "id2checkerMap", "", "", "getId2checkerMap", "()Ljava/util/Map;", "setId2checkerMap", "(Ljava/util/Map;)V", "parseRuleAndRuleMapping", "Lkotlin/Pair;", "getCurrentRuleMappingSet", "row", "Lcn/sast/api/config/CheckerInfo;", "name", "insert", "", "database", "Lcn/sast/framework/report/sqldelight/Database;", "filter", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSqliteDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/RuleAndRuleMapping\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,813:1\n1202#2,2:814\n1230#2,4:816\n1202#2,2:820\n1230#2,4:822\n1628#2,2:826\n1630#2:829\n1628#2,3:830\n774#2:833\n865#2,2:834\n1863#2,2:836\n774#2:838\n865#2,2:839\n1863#2,2:841\n1#3:828\n*S KotlinDebug\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/RuleAndRuleMapping\n*L\n137#1:814,2\n137#1:816,4\n147#1:820,2\n147#1:822,4\n151#1:826,2\n151#1:829\n194#1:830,3\n208#1:833\n208#1:834,2\n208#1:836,2\n209#1:838\n209#1:839,2\n209#1:841,2\n*E\n"})
/* loaded from: RuleAndRuleMapping.class */
public final class RuleAndRuleMapping {

    @NotNull
    private final CheckerInfoGenResult checkerInfo;

    @NotNull
    private final Path ruleSortYaml;

    @NotNull
    private final Set<Rule> rules;

    @NotNull
    private final Set<RuleMapping> ruleMapping;

    @NotNull
    private Map<String, Rule> id2checkerMap;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(RuleAndRuleMapping::logger$lambda$12);

    public RuleAndRuleMapping(@NotNull CheckerInfoGenResult checkerInfo, @NotNull Path ruleSortYaml) {
        Intrinsics.checkNotNullParameter(checkerInfo, "checkerInfo");
        Intrinsics.checkNotNullParameter(ruleSortYaml, "ruleSortYaml");
        this.checkerInfo = checkerInfo;
        this.ruleSortYaml = ruleSortYaml;
        this.id2checkerMap = new LinkedHashMap();
        Pair rulesAndRuleMappingsPair = parseRuleAndRuleMapping(this.ruleSortYaml);
        this.rules = (Set) rulesAndRuleMappingsPair.getFirst();
        this.ruleMapping = (Set) rulesAndRuleMappingsPair.getSecond();
        Iterable $this$associateBy$iv = this.rules;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            Rule it = (Rule) element$iv$iv;
            destination$iv$iv.put(it.getName(), element$iv$iv);
        }
        this.id2checkerMap = destination$iv$iv;
    }

    @NotNull
    public final Map<String, Rule> getId2checkerMap() {
        return this.id2checkerMap;
    }

    public final void setId2checkerMap(@NotNull Map<String, Rule> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.id2checkerMap = map;
    }

    private final Pair<Set<Rule>, Set<RuleMapping>> parseRuleAndRuleMapping(Path ruleSortYaml) {
        Map map;
        Long lValueOf;
        LinkOption[] linkOptionArr = new LinkOption[0];
        if (Files.exists(ruleSortYaml, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            Iterable $this$associateBy$iv = CheckerPriorityConfig.Companion.deserialize(Resource.INSTANCE.fileOf(ruleSortYaml)).getRuleWithSortNumber(this.checkerInfo.getChapters());
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
            Map destination$iv$iv = new LinkedHashMap(capacity$iv);
            for (IndexedValue indexedValue : $this$associateBy$iv) {
                IndexedValue it = indexedValue;
                destination$iv$iv.put(((ChapterFlat) it.getValue()).getRuleId(), indexedValue);
            }
            map = destination$iv$iv;
        } else {
            logger.warn(() -> {
                return parseRuleAndRuleMapping$lambda$1(r1);
            });
            map = null;
        }
        Map ruleWithSortNumber = map;
        LinkedHashSet rows = this.checkerInfo.getCheckerInfoList();
        Set ruleMappings = new LinkedHashSet();
        LinkedHashSet $this$mapTo$iv = rows;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            CheckerInfo row = (CheckerInfo) item$iv;
            String name = row.getChecker_id();
            IndexedValue ruleSortNumber = ruleWithSortNumber != null ? (IndexedValue) ruleWithSortNumber.get(name) : null;
            if (ruleWithSortNumber != null && ruleSortNumber == null) {
                logger.warn(() -> {
                    return parseRuleAndRuleMapping$lambda$5$lambda$3(r1, r2, r3);
                });
            }
            ChapterFlat chapterFlat = row.getChapterFlat();
            ruleMappings.addAll(getCurrentRuleMappingSet(row, name));
            String str = name;
            String str2 = row.getName().get("en-US");
            if (str2 == null) {
                str2 = "None";
            }
            String str3 = row.getName().get("zh-CN");
            if (str3 == null) {
                str3 = "None";
            }
            String severity = row.getSeverity();
            String str4 = null;
            String str5 = "Java";
            String precision = row.getPrecision();
            String reCall = row.getReCall();
            String likelihood = row.getLikelihood();
            String impact = row.getImpact();
            String impl = row.getImpl();
            String str6 = "";
            String str7 = "";
            Long l = 1L;
            Boolean implemented = row.getImplemented();
            if (implemented != null) {
                boolean it2 = implemented.booleanValue();
                str = str;
                str2 = str2;
                str3 = str3;
                severity = severity;
                str4 = null;
                str5 = "Java";
                precision = precision;
                reCall = reCall;
                likelihood = likelihood;
                impact = impact;
                impl = impl;
                str6 = "";
                str7 = "";
                l = l;
                lValueOf = Long.valueOf(it2 ? 1L : 0L);
            } else {
                lValueOf = null;
            }
            String str8 = row.getCategory().get("en-US");
            if (str8 == null) {
                str8 = "None";
            }
            String str9 = row.getCategory().get("zh-CN");
            if (str9 == null) {
                str9 = "None";
            }
            Long lValueOf2 = Long.valueOf(ruleSortNumber != null ? ruleSortNumber.getIndex() : rows.size() + 1);
            String category = chapterFlat != null ? chapterFlat.getCategory() : null;
            String severity2 = chapterFlat != null ? chapterFlat.getSeverity() : null;
            String ruleId = chapterFlat != null ? chapterFlat.getRuleId() : null;
            String str10 = row.getAbstract().get("en-US");
            if (str10 == null) {
                str10 = "None";
            }
            String str11 = row.getAbstract().get("zh-CN");
            if (str11 == null) {
                str11 = "None";
            }
            String str12 = row.getDescription().get("en-US");
            if (str12 == null) {
                str12 = "None";
            }
            String str13 = row.getDescription().get("zh-CN");
            if (str13 == null) {
                str13 = "None";
            }
            String str14 = ruleId;
            String str15 = severity2;
            String str16 = category;
            String str17 = str9;
            String str18 = str8;
            Long l2 = lValueOf;
            Long l3 = l;
            String str19 = str7;
            String str20 = str6;
            String str21 = impl;
            String str22 = impact;
            String str23 = likelihood;
            String str24 = reCall;
            String str25 = precision;
            String str26 = str5;
            String str27 = str4;
            String str28 = severity;
            String str29 = str3;
            String str30 = str2;
            String str31 = str;
            destination$iv.add(new Rule(str31, str30, str29, str28, str27, str26, str25, str24, str23, str22, str21, str20, str19, l3, l2, null, null, str18, str17, lValueOf2, str16, str15, str14, null, str10, str11, str12, str13));
        }
        Set rules = (Set) destination$iv;
        return new Pair<>(rules, ruleMappings);
    }

    private static final Object parseRuleAndRuleMapping$lambda$1(Path $ruleSortYaml) {
        return "The ruleSortYaml " + $ruleSortYaml + " is not exists";
    }

    private static final Object parseRuleAndRuleMapping$lambda$5$lambda$3(CheckerInfo $row, String $name, Path $ruleSortYaml) {
        return "can't find the category '" + $row.getCategory().get("zh-CN") + "' of " + $name + " in " + $ruleSortYaml;
    }

    private final Set<RuleMapping> getCurrentRuleMappingSet(CheckerInfo row, String name) {
        Iterable $this$mapTo$iv = row.getTags();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            Tag it = (Tag) item$iv;
            destination$iv.add(new RuleMapping(name, it.getStandard(), it.getRule()));
        }
        return (Set) destination$iv;
    }

    public final void insert(@NotNull Database database) {
        Intrinsics.checkNotNullParameter(database, "database");
        insert(database, null);
    }

    public final void insert(@NotNull Database database, @Nullable Set<String> set) {
        Set set2;
        Intrinsics.checkNotNullParameter(database, "database");
        if (set != null) {
            set2 = !set.isEmpty() ? set : null;
        } else {
            set2 = null;
        }
        Set f = set2;
        Transacter.DefaultImpls.transaction$default(database, false, (v3) -> {
            return insert$lambda$11(r2, r3, r4, v3);
        }, 1, (Object) null);
    }

    private static final Unit insert$lambda$11(RuleAndRuleMapping this$0, Database $database, Set $f, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        Iterable $this$filter$iv = this$0.rules;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Rule it = (Rule) element$iv$iv;
            if ($f != null ? $f.contains(it.getName()) : true) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        RuleQueries ruleQueries = $database.getRuleQueries();
        for (Object element$iv : $this$forEach$iv) {
            Rule p0 = (Rule) element$iv;
            ruleQueries.insert(p0);
        }
        Iterable $this$filter$iv2 = this$0.ruleMapping;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            RuleMapping it2 = (RuleMapping) element$iv$iv2;
            if ($f != null ? $f.contains(it2.getRule_name()) : true) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable $this$forEach$iv2 = (List) destination$iv$iv2;
        RuleMappingQueries ruleMappingQueries = $database.getRuleMappingQueries();
        for (Object element$iv2 : $this$forEach$iv2) {
            RuleMapping p02 = (RuleMapping) element$iv2;
            ruleMappingQueries.insert(p02);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/RuleAndRuleMapping$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: RuleAndRuleMapping$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$12() {
        return Unit.INSTANCE;
    }
}
