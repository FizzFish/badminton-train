package cn.sast.cli.command.tools;

import cn.sast.api.config.ChapterFlat;
import cn.sast.api.config.CheckerInfo;
import cn.sast.api.config.CheckerInfoGenResult;
import cn.sast.api.config.CheckerPriorityConfig;
import cn.sast.api.config.MainConfig;
import cn.sast.api.config.MainConfigKt;
import cn.sast.cli.command.FySastCliKt;
import cn.sast.common.IResDirectory;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.ConfigPluginLoader;
import cn.sast.framework.plugin.PluginDefinitions;
import cn.sast.framework.report.RuleAndRuleMapping;
import cn.sast.framework.report.SQLiteDB;
import cn.sast.framework.report.SqliteDiagnostics;
import com.charleskorn.kaml.Yaml;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.Language;
import com.github.ajalt.mordant.rendering.Theme;
import com.github.doyaaaaaken.kotlincsv.client.CsvFileReader;
import com.github.doyaaaaaken.kotlincsv.client.CsvReader;
import com.github.doyaaaaaken.kotlincsv.dsl.CsvReaderDslKt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JvmStreamsKt;
import kotlinx.serialization.modules.SerializersModule;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerInfoGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��|\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\u0018�� Z2\u00020\u0001:\u0002YZB-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0018\u0010'\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0016\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u0016\u0010+\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0003H\u0002J\u0018\u0010.\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010/\u001a\u000200H\u0002J6\u00101\u001a\u00020)2\u0006\u0010$\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020-0\u00032\u0006\u00103\u001a\u0002002\u0006\u00104\u001a\u000200H\u0002J$\u00105\u001a\u0002062\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004082\u0006\u00109\u001a\u00020\u0004H\u0002J,\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00012\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004082\u0006\u00109\u001a\u00020\u0004H\u0002JB\u0010<\u001a\u0004\u0018\u00010\u00042\u0006\u0010;\u001a\u00020\u00012\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004082\u0006\u00109\u001a\u00020\u00042\u0012\b\u0002\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010>H\u0002JJ\u0010?\u001a.\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020B0Aj\b\u0012\u0004\u0012\u00020B`C\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`C0@2\u0016\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`CJ.\u0010D\u001a\u00020)2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040F2\u0016\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`CH\u0002J\u0019\u0010G\u001a\u0004\u0018\u0001062\b\u0010H\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010IJ,\u0010J\u001a\u00020)2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004082\u0006\u0010&\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010L\u001a\u0002062\u0006\u0010M\u001a\u00020\u0004H\u0002J\u0010\u0010N\u001a\u00020)2\u0006\u0010O\u001a\u000206H\u0002J\u000e\u0010P\u001a\u00020)2\u0006\u0010Q\u001a\u00020RJ\u0018\u0010S\u001a\u00020)2\u0006\u0010T\u001a\u00020R2\b\b\u0002\u0010O\u001a\u000206J\u001e\u0010U\u001a\u00020)2\u0006\u0010T\u001a\u00020R2\u000e\u0010V\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010FJ\u0010\u0010W\u001a\u00020R2\b\b\u0002\u0010O\u001a\u000206J\u0016\u0010X\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040FH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0012\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u001c\u0010\u0014\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u001c\u0010\u0016\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000fR\u001c\u0010\u0018\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000fR\u001c\u0010\u001a\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000fR\u001c\u0010\u001c\u001a\n \r*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000fR\u0011\u0010\u001e\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010!X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010!X\u0082\u0004¢\u0006\u0002\n��¨\u0006[²\u0006\u0010\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0003X\u008a\u0084\u0002²\u0006\u001a\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`CX\u008a\u0084\u0002"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoGenerator;", "", "language", "", "", "output", "Ljava/nio/file/Path;", "checkerInfoResRoot", "pluginDefinitions", "Lcn/sast/framework/plugin/PluginDefinitions;", "<init>", "(Ljava/util/List;Ljava/nio/file/Path;Ljava/nio/file/Path;Lcn/sast/framework/plugin/PluginDefinitions;)V", "checkerInfoJsonOutPath", "kotlin.jvm.PlatformType", "getCheckerInfoJsonOutPath", "()Ljava/nio/file/Path;", "checkerInfoSqliteDBOutPath", "getCheckerInfoSqliteDBOutPath", "ruleChaptersYamlOutPath", "getRuleChaptersYamlOutPath", "ruleChaptersSortNumberYamlOutPath", "getRuleChaptersSortNumberYamlOutPath", "descriptionsPath", "getDescriptionsPath", "checkerInfoCsvPath", "getCheckerInfoCsvPath", "categoryLanguageMapCsv", "getCategoryLanguageMapCsv", "standardNameMappingJson", "getStandardNameMappingJson", "ruleSortPath", "getRuleSortPath", "errors", "", "warnings", "getSuffixOfLang", "lang", "getMarkdown", "checkerId", "getAbstraction", "dumpExistsCheckerIds", "", "existsCheckerIds", "validateBugMessageLanguage", "checkTypes", "Lcom/feysh/corax/config/api/CheckType;", "getMessage", "msg", "Lcn/sast/cli/command/tools/CheckerInfoGenerator$HintEnum;", "validateDescription", "suffix", "kind", "phantom", "isValid", "", "row", "", "key", "getValueFromRow", "file", "getValueFromRowOrNull", "defaultValue", "Lkotlin/Function0;", "getCheckerInfoList", "Lkotlin/Pair;", "Ljava/util/LinkedHashSet;", "Lcn/sast/api/config/CheckerInfo;", "Lkotlin/collections/LinkedHashSet;", "dumpCheckerInfoJsonFile", "checkerIdInCsv", "", "toBoolean", "value", "(Ljava/lang/String;)Ljava/lang/Boolean;", "validateLanguageMapToContext", "map", "containsChinese", "context", "checkAndAbort", "abortOnError", "dumpRuleChaptersYaml", "checkerInfoGenResult", "Lcn/sast/api/config/CheckerInfoGenResult;", "dumpCheckerInfoJson", "checkerInfo", "dumpRuleAndRuleMappingDB", "rules", "getCheckerInfo", "validateCheckerIdAliasName", "HintEnum", "Companion", "corax-cli"})
@SourceDebugExtension({"SMAP\nCheckerInfoGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerInfoGenerator.kt\ncn/sast/cli/command/tools/CheckerInfoGenerator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 6 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,476:1\n1863#2:477\n1864#2:479\n1202#2,2:480\n1230#2,4:482\n1279#2,2:486\n1293#2,4:488\n1628#2,3:505\n1557#2:515\n1628#2,3:516\n1557#2:519\n1628#2,3:520\n774#2:523\n865#2,2:524\n774#2:526\n865#2,2:527\n1557#2:529\n1628#2,3:530\n1557#2:533\n1628#2,3:534\n1863#2,2:537\n1187#2,2:539\n1261#2,4:541\n1279#2,2:566\n1293#2,4:568\n1279#2,2:572\n1293#2,4:574\n1279#2,2:578\n1293#2,4:580\n1279#2,2:584\n1293#2,4:586\n1611#2,9:593\n1863#2:602\n1864#2:604\n1620#2:605\n1557#2:606\n1628#2,3:607\n1557#2:614\n1628#2,3:615\n1053#2:618\n1628#2,3:619\n1#3:478\n1#3:603\n77#4:492\n97#4,5:493\n126#4:552\n153#4,3:553\n77#4:590\n97#4,2:591\n99#4,3:610\n535#5:498\n520#5,6:499\n535#5:508\n520#5,6:509\n535#5:545\n520#5,6:546\n381#5,7:557\n1317#6:556\n1318#6:564\n1317#6:565\n1318#6:613\n*S KotlinDebug\n*F\n+ 1 CheckerInfoGenerator.kt\ncn/sast/cli/command/tools/CheckerInfoGenerator\n*L\n77#1:477\n77#1:479\n115#1:480,2\n115#1:482,4\n117#1:486,2\n117#1:488,4\n121#1:505,3\n129#1:515\n129#1:516,3\n142#1:519\n142#1:520,3\n285#1:523\n285#1:524,2\n286#1:526\n286#1:527,2\n287#1:529\n287#1:530,3\n288#1:533\n288#1:534,3\n315#1:537,2\n352#1:539,2\n352#1:541,4\n191#1:566,2\n191#1:568,4\n200#1:572,2\n200#1:574,4\n206#1:578,2\n206#1:580,4\n216#1:584,2\n216#1:586,4\n246#1:593,9\n246#1:602\n246#1:604\n246#1:605\n246#1:606\n246#1:607,3\n389#1:614\n389#1:615,3\n389#1:618\n393#1:619,3\n246#1:603\n118#1:492\n118#1:493,5\n439#1:552\n439#1:553,3\n226#1:590\n226#1:591,2\n226#1:610,3\n120#1:498\n120#1:499,6\n122#1:508\n122#1:509,6\n434#1:545\n434#1:546,6\n168#1:557,7\n166#1:556\n166#1:564\n180#1:565\n180#1:613\n*E\n"})
/* loaded from: CheckerInfoGenerator.class */
public final class CheckerInfoGenerator {

    @NotNull
    private final List<String> language;

    @NotNull
    private final Path output;

    @NotNull
    private final Path checkerInfoResRoot;

    @NotNull
    private final PluginDefinitions pluginDefinitions;

    @NotNull
    private final List<Object> errors;

    @NotNull
    private final List<Object> warnings;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckerInfoGenerator::logger$lambda$66);

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, CheckerInfoGenerator::jsonFormat$lambda$67, 1, (Object) null);

    @NotNull
    private static final Yaml yamlFormat = new Yaml((SerializersModule) null, MainConfigKt.getYamlConfiguration(), 1, (DefaultConstructorMarker) null);

    @NotNull
    private static final KSerializer<List<CheckerInfo>> infoSerializer = BuiltinSerializersKt.ListSerializer(CheckerInfo.Companion.serializer());

    /* compiled from: CheckerInfoGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: CheckerInfoGenerator$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HintEnum.values().length];
            try {
                iArr[HintEnum.PHANTOM_MARKDOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[HintEnum.PHANTOM_ABSTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[HintEnum.MARKDOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[HintEnum.ABSTRACT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CheckerInfoGenerator(@NotNull List<String> language, @NotNull Path output, @NotNull Path checkerInfoResRoot, @NotNull PluginDefinitions pluginDefinitions) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(checkerInfoResRoot, "checkerInfoResRoot");
        Intrinsics.checkNotNullParameter(pluginDefinitions, "pluginDefinitions");
        this.language = language;
        this.output = output;
        this.checkerInfoResRoot = checkerInfoResRoot;
        this.pluginDefinitions = pluginDefinitions;
        this.errors = new ArrayList();
        this.warnings = new ArrayList();
    }

    private final Path getCheckerInfoJsonOutPath() {
        return this.checkerInfoResRoot.resolve("checker_info.json").normalize();
    }

    private final Path getCheckerInfoSqliteDBOutPath() {
        return this.checkerInfoResRoot.resolve("checker_info.db").normalize();
    }

    private final Path getRuleChaptersYamlOutPath() {
        return this.checkerInfoResRoot.resolve("rule_chapters.yaml").normalize();
    }

    private final Path getRuleChaptersSortNumberYamlOutPath() {
        return this.checkerInfoResRoot.resolve("rule_chapters_sort_number.yaml").normalize();
    }

    private final Path getDescriptionsPath() {
        return this.checkerInfoResRoot.resolve("descriptions");
    }

    private final Path getCheckerInfoCsvPath() {
        return this.checkerInfoResRoot.resolve(MainConfig.CHECKER_INFO_CSV_NAME);
    }

    private final Path getCategoryLanguageMapCsv() {
        return this.checkerInfoResRoot.resolve("category.translation.csv");
    }

    private final Path getStandardNameMappingJson() {
        return this.checkerInfoResRoot.resolve("checker_info_ruleset_to_server_standard_name_mapping.json");
    }

    @NotNull
    public final Path getRuleSortPath() {
        Path pathNormalize = this.checkerInfoResRoot.resolve(MainConfig.RULE_SORT_YAML).normalize();
        Intrinsics.checkNotNullExpressionValue(pathNormalize, "normalize(...)");
        return pathNormalize;
    }

    private final String getSuffixOfLang(String lang) {
        return "." + lang;
    }

    private final Path getMarkdown(String checkerId, String lang) {
        Path pathResolve = getDescriptionsPath().resolve(checkerId + getSuffixOfLang(lang) + ".md");
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    private final Path getAbstraction(String checkerId, String lang) {
        Path pathResolve = getDescriptionsPath().resolve(checkerId + getSuffixOfLang(lang) + ".txt");
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    private final void dumpExistsCheckerIds(List<String> existsCheckerIds) throws IOException {
        Path pathResolve = this.output.resolve("exists-checker-id.json");
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream outputStream2 = outputStream;
                JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), existsCheckerIds, outputStream2);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStream, th);
            throw th2;
        }
    }

    private final void validateBugMessageLanguage(List<? extends CheckType> checkTypes) {
        List<? extends CheckType> $this$forEach$iv = checkTypes;
        for (Object element$iv : $this$forEach$iv) {
            CheckType it = (CheckType) element$iv;
            if (!(it.getBugMessage().containsKey(Language.EN) && it.getBugMessage().containsKey(Language.ZH))) {
                throw new IllegalStateException(("Missing language ZH or EN: " + it).toString());
            }
        }
    }

    /* compiled from: CheckerInfoGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoGenerator$HintEnum;", "", "<init>", "(Ljava/lang/String;I)V", "PHANTOM_MARKDOWN", "PHANTOM_ABSTRACT", "MARKDOWN", "ABSTRACT", "corax-cli"})
    /* loaded from: CheckerInfoGenerator$HintEnum.class */
    public enum HintEnum {
        PHANTOM_MARKDOWN,
        PHANTOM_ABSTRACT,
        MARKDOWN,
        ABSTRACT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<HintEnum> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    private final String getMessage(String lang, HintEnum msg) throws NoWhenBranchMatchedException {
        switch (WhenMappings.$EnumSwitchMapping$0[msg.ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                return Intrinsics.areEqual(lang, "zh-CN") ? "请添加此规则对应的描述或修复建议到" : "Please add a description or remediation suggestion for this rule at";
            case 2:
                return Intrinsics.areEqual(lang, "zh-CN") ? "请添加此规则对应的规则的摘要" : "Please add a abstract short-description of the rule";
            case 3:
                return Intrinsics.areEqual(lang, "zh-CN") ? "规则的markdown格式的漏洞描述文档" : "Description & Fix Suggestions for Vulnerabilities in Markdown Format";
            case 4:
                return Intrinsics.areEqual(lang, "zh-CN") ? "规则的摘要描述" : "Abstract Short-Description of Rule";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void validateDescription(String lang, String suffix, List<? extends CheckType> existsCheckerIds, HintEnum kind, HintEnum phantom) throws IOException {
        Path markdown = getDescriptionsPath();
        Intrinsics.checkNotNull(markdown);
        Iterable $this$associateBy$iv = PathsKt.listDirectoryEntries(markdown, "*" + suffix);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            destination$iv$iv.put(PathsKt.getName((Path) element$iv$iv), element$iv$iv);
        }
        SortedMap existsMarkdowns = MapsKt.toSortedMap(destination$iv$iv);
        List<? extends CheckType> $this$associateWith$iv = existsCheckerIds;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv2 : $this$associateWith$iv) {
            result$iv.put(element$iv$iv2, CollectionsKt.listOf(CheckerInfoGeneratorKt.getId((CheckType) element$iv$iv2)));
        }
        Map checkTypeToAllPossibleIds = result$iv;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it = checkTypeToAllPossibleIds.entrySet().iterator();
        while (it.hasNext()) {
            Iterable list$iv$iv = (Iterable) ((Map.Entry) it.next()).getValue();
            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
        }
        List allPossibleIds = (List) destination$iv$iv2;
        SortedMap $this$filter$iv = existsMarkdowns;
        Map destination$iv$iv3 = new LinkedHashMap();
        for (Map.Entry element$iv$iv3 : $this$filter$iv.entrySet()) {
            Object key = element$iv$iv3.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
            if (!allPossibleIds.contains(StringsKt.removeSuffix((String) key, suffix))) {
                destination$iv$iv3.put(element$iv$iv3.getKey(), element$iv$iv3.getValue());
            }
        }
        Iterable redundancy = destination$iv$iv3.keySet();
        Iterable iterableKeySet = existsMarkdowns.keySet();
        Intrinsics.checkNotNullExpressionValue(iterableKeySet, "<get-keys>(...)");
        Iterable $this$mapTo$iv = iterableKeySet;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            String it2 = (String) item$iv;
            Intrinsics.checkNotNull(it2);
            destination$iv.add(StringsKt.removeSuffix(it2, suffix));
        }
        Set existsCheckerIdFromDescFiles = (Set) destination$iv;
        Map destination$iv$iv4 = new LinkedHashMap();
        for (Map.Entry element$iv$iv4 : checkTypeToAllPossibleIds.entrySet()) {
            if (CollectionsKt.intersect((Iterable) element$iv$iv4.getValue(), existsCheckerIdFromDescFiles).isEmpty()) {
                destination$iv$iv4.put(element$iv$iv4.getKey(), element$iv$iv4.getValue());
            }
        }
        Iterable lack = CollectionsKt.toSet(destination$iv$iv4.keySet());
        Path it3 = this.output.resolve("descriptions");
        Intrinsics.checkNotNull(it3);
        LinkOption[] linkOptionArr = new LinkOption[0];
        if (!Files.exists(it3, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            Files.createDirectories(it3, new FileAttribute[0]);
        }
        Path pathResolve = it3.resolve("redundancy_md.json");
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        try {
            OutputStream outputStream2 = outputStream;
            JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.SetSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), redundancy, outputStream2);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            Iterable $this$map$iv = lack;
            Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv5.add(CheckerInfoGeneratorKt.getId((CheckType) item$iv$iv) + suffix);
            }
            List<String> lackDescriptionFile = (List) destination$iv$iv5;
            Path pathResolve2 = it3.resolve("lack_md.json");
            Intrinsics.checkNotNullExpressionValue(pathResolve2, "resolve(...)");
            OpenOption[] openOptionArr2 = new OpenOption[0];
            OutputStream outputStreamNewOutputStream2 = Files.newOutputStream(pathResolve2, (OpenOption[]) Arrays.copyOf(openOptionArr2, openOptionArr2.length));
            Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream2, "newOutputStream(...)");
            OutputStreamWriter it4 = outputStreamNewOutputStream2;
            Throwable th = null;
            try {
                try {
                    OutputStream outputStream3 = it4;
                    JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), lackDescriptionFile, outputStream3);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(it4, (Throwable) null);
                    for (String lackFileName : lackDescriptionFile) {
                        Path pathResolve3 = getDescriptionsPath().resolve(lackFileName);
                        Intrinsics.checkNotNullExpressionValue(pathResolve3, "resolve(...)");
                        Path absolutePath = pathResolve3.toAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
                        Path lackFilePath = absolutePath.normalize();
                        this.errors.add("Required: The necessary file for \"" + getMessage(lang, kind) + "\" do not exist: " + lackFilePath);
                        Path desc = it3.resolve(lackFileName);
                        Intrinsics.checkNotNull(desc);
                        LinkOption[] linkOptionArr2 = new LinkOption[0];
                        if (!Files.exists(desc, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                            OpenOption[] openOptionArr3 = new OpenOption[0];
                            it4 = new OutputStreamWriter(Files.newOutputStream(desc, (OpenOption[]) Arrays.copyOf(openOptionArr3, openOptionArr3.length)), Charsets.UTF_8);
                            Throwable th2 = null;
                            try {
                                try {
                                    it4.write(getMessage(lang, phantom) + "\n\nRequired: " + lackFilePath + "\n");
                                    Unit unit3 = Unit.INSTANCE;
                                    CloseableKt.closeFinally(it4, (Throwable) null);
                                } finally {
                                }
                            } finally {
                            }
                        }
                    }
                    List<Object> list = this.warnings;
                    Iterable $this$map$iv2 = redundancy;
                    Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        destination$iv$iv6.add("Redundant \"" + getMessage(lang, kind) + "\": " + ((String) item$iv$iv2));
                    }
                    list.addAll((List) destination$iv$iv6);
                } finally {
                }
            } finally {
            }
        } catch (Throwable th3) {
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            throw th3;
        }
    }

    private final boolean isValid(Map<String, String> row, String key) {
        String it = row.get(key);
        String str = it;
        if (!(str == null || str.length() == 0)) {
            if (StringsKt.trim(it).toString().length() > 0) {
                return true;
            }
        }
        return false;
    }

    private final String getValueFromRow(Object file, Map<String, String> row, String key) {
        if (!isValid(row, key)) {
            throw new IllegalStateException(("failed to get a value of attribute '" + key + "' in " + row + " at " + file).toString());
        }
        String str = row.get(key);
        Intrinsics.checkNotNull(str);
        return str;
    }

    static /* synthetic */ String getValueFromRowOrNull$default(CheckerInfoGenerator checkerInfoGenerator, Object obj, Map map, String str, Function0 function0, int i, Object obj2) {
        if ((i & 8) != 0) {
            function0 = null;
        }
        return checkerInfoGenerator.getValueFromRowOrNull(obj, map, str, function0);
    }

    private final String getValueFromRowOrNull(Object file, Map<String, String> row, String key, Function0<String> defaultValue) {
        if (!isValid(row, key)) {
            if (defaultValue != null) {
                return (String) defaultValue.invoke();
            }
            this.warnings.add("failed to get a value of attribute '" + key + "' in " + row + " at " + file);
            return null;
        }
        String str = row.get(key);
        Intrinsics.checkNotNull(str);
        return str;
    }

    @NotNull
    public final Pair<LinkedHashSet<CheckerInfo>, LinkedHashSet<String>> getCheckerInfoList(@NotNull LinkedHashSet<String> existsCheckerIds) throws IOException {
        Intrinsics.checkNotNullParameter(existsCheckerIds, "existsCheckerIds");
        Map categoryLanguageMap = new LinkedHashMap();
        CsvReader csvReaderCsvReader$default = CsvReaderDslKt.csvReader$default((Function1) null, 1, (Object) null);
        File file = getCategoryLanguageMapCsv().toFile();
        Intrinsics.checkNotNullExpressionValue(file, "toFile(...)");
        csvReaderCsvReader$default.open(file, (v2) -> {
            return getCheckerInfoList$lambda$18(r2, r3, v2);
        });
        Path standardNameMappingJson = getStandardNameMappingJson();
        Intrinsics.checkNotNullExpressionValue(standardNameMappingJson, "<get-standardNameMappingJson>(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream(standardNameMappingJson, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        InputStream inputStream = inputStreamNewInputStream;
        try {
            InputStream i = inputStream;
            Map standardNames = (Map) JvmStreamsKt.decodeFromStream(jsonFormat, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), i);
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            LinkedHashSet checkerIdInCsv = new LinkedHashSet();
            LinkedHashSet checkerInfos = new LinkedHashSet();
            Set standardCheckExists = new LinkedHashSet();
            CsvReader csvReaderCsvReader$default2 = CsvReaderDslKt.csvReader$default((Function1) null, 1, (Object) null);
            File file2 = getCheckerInfoCsvPath().toFile();
            Intrinsics.checkNotNullExpressionValue(file2, "toFile(...)");
            csvReaderCsvReader$default2.open(file2, (v7) -> {
                return getCheckerInfoList$lambda$33(r2, r3, r4, r5, r6, r7, r8, v7);
            });
            Set notExistsStandardName = SetsKt.minus(standardNames.keySet(), standardCheckExists);
            if (notExistsStandardName.isEmpty()) {
                return TuplesKt.to(checkerInfos, checkerIdInCsv);
            }
            throw new IllegalStateException(("The rule set names in JSON file '" + getStandardNameMappingJson() + "': " + notExistsStandardName + " are missing from the '" + getCheckerInfoCsvPath() + "' and need to be added.").toString());
        } catch (Throwable th) {
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            throw th;
        }
    }

    private static final Unit getCheckerInfoList$lambda$18(CheckerInfoGenerator this$0, Map $categoryLanguageMap, CsvFileReader $this$open) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$open, "$this$open");
        Sequence $this$forEach$iv = $this$open.readAllWithHeaderAsSequence();
        for (Object element$iv : $this$forEach$iv) {
            Map row = (Map) element$iv;
            Path categoryLanguageMapCsv = this$0.getCategoryLanguageMapCsv();
            Intrinsics.checkNotNullExpressionValue(categoryLanguageMapCsv, "<get-categoryLanguageMapCsv>(...)");
            String lang = this$0.getValueFromRow(categoryLanguageMapCsv, row, "lang");
            Object value$iv = $categoryLanguageMap.get(lang);
            if (value$iv == null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                $categoryLanguageMap.put(lang, linkedHashMap);
                obj = linkedHashMap;
            } else {
                obj = value$iv;
            }
            ((Map) obj).putAll(row);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0517  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final kotlin.Unit getCheckerInfoList$lambda$33(java.util.Set r28, cn.sast.cli.command.tools.CheckerInfoGenerator r29, java.util.LinkedHashSet r30, java.util.LinkedHashSet r31, java.util.Map r32, java.util.LinkedHashSet r33, java.util.Map r34, com.github.doyaaaaaken.kotlincsv.client.CsvFileReader r35) {
        /*
            Method dump skipped, instructions count: 1863
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.cli.command.tools.CheckerInfoGenerator.getCheckerInfoList$lambda$33(java.util.Set, cn.sast.cli.command.tools.CheckerInfoGenerator, java.util.LinkedHashSet, java.util.LinkedHashSet, java.util.Map, java.util.LinkedHashSet, java.util.Map, com.github.doyaaaaaken.kotlincsv.client.CsvFileReader):kotlin.Unit");
    }

    private static final String getCheckerInfoList$lambda$33$lambda$32$lambda$30() {
        return null;
    }

    private static final String getCheckerInfoList$lambda$33$lambda$32$lambda$31() {
        return null;
    }

    private final void dumpCheckerInfoJsonFile(Set<String> checkerIdInCsv, LinkedHashSet<String> existsCheckerIds) throws IOException {
        Set<String> $this$filter$iv = checkerIdInCsv;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!existsCheckerIds.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable redundancyIdInCsv = (List) destination$iv$iv;
        LinkedHashSet<String> $this$filter$iv2 = existsCheckerIds;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (!checkerIdInCsv.contains(it2)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable lackIdInCsv = (List) destination$iv$iv2;
        List<Object> list = this.warnings;
        Iterable $this$map$iv = redundancyIdInCsv;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it3 = (String) item$iv$iv;
            destination$iv$iv3.add("Redundant rule '" + it3 + "' in checker_info.csv that not define in the plugin");
        }
        list.addAll((List) destination$iv$iv3);
        List<Object> list2 = this.errors;
        Iterable $this$map$iv2 = lackIdInCsv;
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            String it4 = (String) item$iv$iv2;
            destination$iv$iv4.add("Rule '" + it4 + "' define in the plugin that not declare in the checker_info.csv");
        }
        list2.addAll((List) destination$iv$iv4);
        Path pathResolve = this.output.resolve("redundancy_checker_csv_row.json");
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream outputStream2 = outputStream;
                JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), redundancyIdInCsv, outputStream2);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
                Path pathResolve2 = this.output.resolve("lack_checker_csv_row.json");
                Intrinsics.checkNotNullExpressionValue(pathResolve2, "resolve(...)");
                OpenOption[] openOptionArr2 = new OpenOption[0];
                OutputStream outputStreamNewOutputStream2 = Files.newOutputStream(pathResolve2, (OpenOption[]) Arrays.copyOf(openOptionArr2, openOptionArr2.length));
                Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream2, "newOutputStream(...)");
                OutputStream outputStream3 = outputStreamNewOutputStream2;
                try {
                    OutputStream outputStream4 = outputStream3;
                    JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), lackIdInCsv, outputStream4);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream3, (Throwable) null);
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStream3, (Throwable) null);
                    throw th2;
                }
            } finally {
            }
        } catch (Throwable th3) {
            CloseableKt.closeFinally(outputStream, th);
            throw th3;
        }
    }

    private final Boolean toBoolean(String value) {
        if (value != null && !Intrinsics.areEqual(value, "无")) {
            String upperCase = value.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            if (!Intrinsics.areEqual(upperCase, "NONE")) {
                if (!Intrinsics.areEqual(value, "否")) {
                    String upperCase2 = value.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                    if (!Intrinsics.areEqual(upperCase2, "FALSE")) {
                        if (!Intrinsics.areEqual(value, "是")) {
                            String upperCase3 = value.toUpperCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                            if (!Intrinsics.areEqual(upperCase3, "TRUE")) {
                                return null;
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return null;
    }

    private final void validateLanguageMapToContext(Map<String, String> map, String checkerId, String key) {
        Iterable $this$forEach$iv = map.entrySet();
        for (Object element$iv : $this$forEach$iv) {
            Map.Entry entry = (Map.Entry) element$iv;
            String lang = (String) entry.getKey();
            String context = (String) entry.getValue();
            if (Intrinsics.areEqual("en-US", lang) && containsChinese(context)) {
                this.warnings.add("The " + key + " of checker with id: " + checkerId + " is invalid in en-US context!");
            }
            if (Intrinsics.areEqual("zh-CN", lang) && !containsChinese(context)) {
                this.warnings.add("The " + key + " of checker with id: " + checkerId + " is invalid in zh-CN context!");
            }
        }
    }

    private final boolean containsChinese(String context) {
        return Pattern.compile("[\\u4e00-\\u9fa5]").matcher(context).find();
    }

    private final void checkAndAbort(boolean abortOnError) {
        if (abortOnError) {
            if (!this.warnings.isEmpty()) {
                logger.warn(() -> {
                    return checkAndAbort$lambda$43(r1);
                });
            }
            if (!this.errors.isEmpty()) {
                logger.error(() -> {
                    return checkAndAbort$lambda$45(r1);
                });
                System.exit(5);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
            this.warnings.clear();
            this.errors.clear();
        }
    }

    private static final CharSequence checkAndAbort$lambda$43$lambda$42(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Theme.Companion.getDefault().getWarning().invoke("W: " + it);
    }

    private static final Object checkAndAbort$lambda$43(CheckerInfoGenerator this$0) {
        return "发现 " + Theme.Companion.getDefault().getWarning().invoke(String.valueOf(this$0.warnings.size())) + " 个问题, \n\t" + CollectionsKt.joinToString$default(this$0.warnings, "\n\t", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, CheckerInfoGenerator::checkAndAbort$lambda$43$lambda$42, 30, (Object) null);
    }

    private static final CharSequence checkAndAbort$lambda$45$lambda$44(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Theme.Companion.getDefault().getDanger().invoke("E: " + it);
    }

    private static final Object checkAndAbort$lambda$45(CheckerInfoGenerator this$0) {
        return "发现 " + Theme.Companion.getDefault().getDanger().invoke(String.valueOf(this$0.errors.size())) + " 个严重错误, \n\t" + CollectionsKt.joinToString$default(this$0.errors, "\n\t", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, CheckerInfoGenerator::checkAndAbort$lambda$45$lambda$44, 30, (Object) null);
    }

    public final void dumpRuleChaptersYaml(@NotNull CheckerInfoGenResult checkerInfoGenResult) throws IOException {
        Intrinsics.checkNotNullParameter(checkerInfoGenResult, "checkerInfoGenResult");
        CheckerPriorityConfig checkerPriorityConfig = CheckerPriorityConfig.Companion.deserialize(Resource.INSTANCE.fileOf(getRuleSortPath()));
        List chapters = checkerInfoGenResult.getChapters();
        Map chapterTree = checkerPriorityConfig.getSortTree(chapters);
        Iterable $this$associate$iv = checkerPriorityConfig.getRuleWithSortNumber(chapters);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (IndexedValue element$iv$iv : $this$associate$iv) {
            IndexedValue it = element$iv$iv;
            Pair pair = TuplesKt.to(Integer.valueOf(it.getIndex()), ((ChapterFlat) it.getValue()).toString());
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        Path ruleChaptersYamlOutPath = getRuleChaptersYamlOutPath();
        Intrinsics.checkNotNullExpressionValue(ruleChaptersYamlOutPath, "<get-ruleChaptersYamlOutPath>(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(ruleChaptersYamlOutPath, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream it2 = outputStream;
                Yaml.encodeToStream$default(yamlFormat, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ArrayListSerializer(StringSerializer.INSTANCE))), chapterTree, it2, (Charset) null, 8, (Object) null);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
                Path ruleChaptersSortNumberYamlOutPath = getRuleChaptersSortNumberYamlOutPath();
                Intrinsics.checkNotNullExpressionValue(ruleChaptersSortNumberYamlOutPath, "<get-ruleChaptersSortNumberYamlOutPath>(...)");
                OpenOption[] openOptionArr2 = new OpenOption[0];
                OutputStream outputStreamNewOutputStream2 = Files.newOutputStream(ruleChaptersSortNumberYamlOutPath, (OpenOption[]) Arrays.copyOf(openOptionArr2, openOptionArr2.length));
                Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream2, "newOutputStream(...)");
                outputStream = outputStreamNewOutputStream2;
                Throwable th2 = null;
                try {
                    try {
                        OutputStream it3 = outputStream;
                        Yaml.encodeToStream$default(yamlFormat, new LinkedHashMapSerializer(IntSerializer.INSTANCE, StringSerializer.INSTANCE), destination$iv$iv, it3, (Charset) null, 8, (Object) null);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream, (Throwable) null);
                        logger.info(() -> {
                            return dumpRuleChaptersYaml$lambda$49(r1);
                        });
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    private static final Object dumpRuleChaptersYaml$lambda$49(CheckerInfoGenerator this$0) {
        return "Successfully! File: " + this$0.getRuleChaptersYamlOutPath() + " has been generated.";
    }

    public static /* synthetic */ void dumpCheckerInfoJson$default(CheckerInfoGenerator checkerInfoGenerator, CheckerInfoGenResult checkerInfoGenResult, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = true;
        }
        checkerInfoGenerator.dumpCheckerInfoJson(checkerInfoGenResult, z);
    }

    public final void dumpCheckerInfoJson(@NotNull CheckerInfoGenResult checkerInfo, boolean abortOnError) throws IOException {
        Intrinsics.checkNotNullParameter(checkerInfo, "checkerInfo");
        dumpExistsCheckerIds(CollectionsKt.toList(checkerInfo.getExistsCheckerIds()));
        dumpCheckerInfoJsonFile(checkerInfo.getCheckerIdInCsv(), checkerInfo.getExistsCheckerIds());
        Path checkerInfoJsonOutPath = getCheckerInfoJsonOutPath();
        Intrinsics.checkNotNullExpressionValue(checkerInfoJsonOutPath, "<get-checkerInfoJsonOutPath>(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(checkerInfoJsonOutPath, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream outputStream2 = outputStream;
                JvmStreamsKt.encodeToStream(jsonFormat, infoSerializer, CollectionsKt.toList(checkerInfo.getCheckerInfoList()), outputStream2);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
                dumpRuleChaptersYaml(checkerInfo);
                checkAndAbort(abortOnError);
                logger.info(() -> {
                    return dumpCheckerInfoJson$lambda$51(r1);
                });
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStream, th);
            throw th2;
        }
    }

    private static final Object dumpCheckerInfoJson$lambda$51(CheckerInfoGenerator this$0) {
        return "Successfully! File: " + this$0.getCheckerInfoJsonOutPath() + " has been generated.";
    }

    public final void dumpRuleAndRuleMappingDB(@NotNull CheckerInfoGenResult checkerInfo, @Nullable Set<String> rules) throws IOException {
        Intrinsics.checkNotNullParameter(checkerInfo, "checkerInfo");
        Path dbPath = getCheckerInfoSqliteDBOutPath();
        Intrinsics.checkNotNull(dbPath);
        Files.deleteIfExists(dbPath);
        RuleAndRuleMapping ruleAndRuleMapping = new RuleAndRuleMapping(checkerInfo, getRuleSortPath());
        SQLiteDB sQLiteDBOpenDataBase$default = SqliteDiagnostics.Companion.openDataBase$default(SqliteDiagnostics.Companion, dbPath.toString(), null, 2, null);
        Throwable th = null;
        try {
            try {
                SQLiteDB it = sQLiteDBOpenDataBase$default;
                it.createSchema();
                ruleAndRuleMapping.insert(it.getDatabase(), rules);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(sQLiteDBOpenDataBase$default, (Throwable) null);
                logger.info(() -> {
                    return dumpRuleAndRuleMappingDB$lambda$53(r1);
                });
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(sQLiteDBOpenDataBase$default, th);
            throw th2;
        }
    }

    private static final Object dumpRuleAndRuleMappingDB$lambda$53(Path $dbPath) {
        return "Successfully! File: " + $dbPath + " has been generated.";
    }

    public static /* synthetic */ CheckerInfoGenResult getCheckerInfo$default(CheckerInfoGenerator checkerInfoGenerator, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return checkerInfoGenerator.getCheckerInfo(z);
    }

    @NotNull
    public final CheckerInfoGenResult getCheckerInfo(boolean abortOnError) throws IOException {
        Lazy checkTypes$delegate = LazyKt.lazy(() -> {
            return getCheckerInfo$lambda$56(r0);
        });
        Lazy existsCheckerIds$delegate = LazyKt.lazy(() -> {
            return getCheckerInfo$lambda$61(r0);
        });
        validateBugMessageLanguage(getCheckerInfo$lambda$57(checkTypes$delegate));
        for (String lang : this.language) {
            validateDescription(lang, getSuffixOfLang(lang) + ".md", getCheckerInfo$lambda$57(checkTypes$delegate), HintEnum.MARKDOWN, HintEnum.PHANTOM_MARKDOWN);
            validateDescription(lang, getSuffixOfLang(lang) + ".txt", getCheckerInfo$lambda$57(checkTypes$delegate), HintEnum.ABSTRACT, HintEnum.PHANTOM_ABSTRACT);
        }
        checkAndAbort(abortOnError);
        Pair<LinkedHashSet<CheckerInfo>, LinkedHashSet<String>> checkerInfoList = getCheckerInfoList(getCheckerInfo$lambda$62(existsCheckerIds$delegate));
        LinkedHashSet checkerInfoList2 = (LinkedHashSet) checkerInfoList.component1();
        LinkedHashSet checkerIdInCsv = (LinkedHashSet) checkerInfoList.component2();
        checkAndAbort(abortOnError);
        validateCheckerIdAliasName(getCheckerInfo$lambda$62(existsCheckerIds$delegate));
        checkAndAbort(abortOnError);
        return new CheckerInfoGenResult(checkerInfoList2, new LinkedHashSet(getCheckerInfo$lambda$57(checkTypes$delegate)), getCheckerInfo$lambda$62(existsCheckerIds$delegate), checkerIdInCsv);
    }

    private static final List<CheckType> getCheckerInfo$lambda$57(Lazy<? extends List<? extends CheckType>> $checkTypes$delegate) {
        return (List) $checkTypes$delegate.getValue();
    }

    private static final List getCheckerInfo$lambda$56(CheckerInfoGenerator this$0) {
        Iterable $this$map$iv = this$0.pluginDefinitions.getCheckTypeDefinition(CheckType.class);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PluginDefinitions.CheckTypeDefinition it = (PluginDefinitions.CheckTypeDefinition) item$iv$iv;
            destination$iv$iv.add(it.getSingleton());
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.cli.command.tools.CheckerInfoGenerator$getCheckerInfo$lambda$56$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                CheckType it2 = (CheckType) t;
                CheckType it3 = (CheckType) t2;
                return ComparisonsKt.compareValues(CheckerInfoGeneratorKt.getId(it2), CheckerInfoGeneratorKt.getId(it3));
            }
        });
    }

    private static final LinkedHashSet<String> getCheckerInfo$lambda$62(Lazy<? extends LinkedHashSet<String>> $existsCheckerIds$delegate) {
        return (LinkedHashSet) $existsCheckerIds$delegate.getValue();
    }

    private static final LinkedHashSet getCheckerInfo$lambda$61(Lazy $checkTypes$delegate) {
        Iterable $this$mapTo$iv = getCheckerInfo$lambda$57($checkTypes$delegate);
        Collection destination$iv = new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            CheckType it = (CheckType) item$iv;
            destination$iv.add(CheckerInfoGeneratorKt.getId(it));
        }
        LinkedHashSet existsCheckerIds = (LinkedHashSet) destination$iv;
        if (!(!existsCheckerIds.isEmpty())) {
            throw new IllegalStateException("Internal error! Failed to find any CheckType".toString());
        }
        logger.info(() -> {
            return getCheckerInfo$lambda$61$lambda$60(r1);
        });
        return existsCheckerIds;
    }

    private static final Object getCheckerInfo$lambda$61$lambda$60(LinkedHashSet $existsCheckerIds) {
        return "Found " + $existsCheckerIds.size() + " CheckTypes";
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [cn.sast.cli.command.tools.CheckerInfoGenerator$validateCheckerIdAliasName$renameMap$1] */
    private final void validateCheckerIdAliasName(Set<String> existsCheckerIds) throws IOException {
        Gson gson = new Gson();
        Path pathResolve = this.checkerInfoResRoot.resolve(FySastCliKt.MAPPING_FILE_NAME);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        Object objFromJson = gson.fromJson(PathsKt.readText(pathResolve, Charsets.UTF_8), new TypeToken<Map<String, ? extends String>>() { // from class: cn.sast.cli.command.tools.CheckerInfoGenerator$validateCheckerIdAliasName$renameMap$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
        Map renameMap = (Map) objFromJson;
        Map destination$iv$iv = new LinkedHashMap();
        for (Map.Entry element$iv$iv : renameMap.entrySet()) {
            if (!existsCheckerIds.contains(element$iv$iv.getValue())) {
                destination$iv$iv.put(element$iv$iv.getKey(), element$iv$iv.getValue());
            }
        }
        Path pathResolve2 = this.output.resolve(StringsKt.substringBeforeLast$default(FySastCliKt.MAPPING_FILE_NAME, ".", (String) null, 2, (Object) null) + ".no-such-alias-names.json");
        Intrinsics.checkNotNullExpressionValue(pathResolve2, "resolve(...)");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve2, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream outputStream2 = outputStream;
                JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), destination$iv$iv, outputStream2);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
                List<Object> list = this.warnings;
                Collection destination$iv$iv2 = new ArrayList(destination$iv$iv.size());
                for (Map.Entry item$iv$iv : destination$iv$iv.entrySet()) {
                    destination$iv$iv2.add("checker_id alias " + item$iv$iv.getKey() + " -> " + item$iv$iv.getValue() + ". the target " + item$iv$iv.getValue() + " is not exists. check: checker_name_mapping.json");
                }
                list.addAll((List) destination$iv$iv2);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStream, th);
            throw th2;
        }
    }

    /* compiled from: CheckerInfoGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoGenerator$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "jsonFormat", "Lkotlinx/serialization/json/Json;", "getJsonFormat", "()Lkotlinx/serialization/json/Json;", "yamlFormat", "Lcom/charleskorn/kaml/Yaml;", "getYamlFormat", "()Lcom/charleskorn/kaml/Yaml;", "infoSerializer", "Lkotlinx/serialization/KSerializer;", "", "Lcn/sast/api/config/CheckerInfo;", "getInfoSerializer", "()Lkotlinx/serialization/KSerializer;", "createCheckerInfoGenerator", "Lcn/sast/cli/command/tools/CheckerInfoGenerator;", "pl", "Lcn/sast/framework/plugin/ConfigPluginLoader;", "language", "", "stopOnError", "", "corax-cli"})
    @SourceDebugExtension({"SMAP\nCheckerInfoGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerInfoGenerator.kt\ncn/sast/cli/command/tools/CheckerInfoGenerator$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,476:1\n1#2:477\n*E\n"})
    /* loaded from: CheckerInfoGenerator$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Json getJsonFormat() {
            return CheckerInfoGenerator.jsonFormat;
        }

        @NotNull
        public final Yaml getYamlFormat() {
            return CheckerInfoGenerator.yamlFormat;
        }

        @NotNull
        public final KSerializer<List<CheckerInfo>> getInfoSerializer() {
            return CheckerInfoGenerator.infoSerializer;
        }

        public static /* synthetic */ CheckerInfoGenerator createCheckerInfoGenerator$default(Companion companion, ConfigPluginLoader configPluginLoader, List list, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                list = CollectionsKt.listOf(new String[]{"zh-CN", "en-US"});
            }
            if ((i & 4) != 0) {
                z = true;
            }
            return companion.createCheckerInfoGenerator(configPluginLoader, list, z);
        }

        @Nullable
        public final CheckerInfoGenerator createCheckerInfoGenerator(@NotNull ConfigPluginLoader pl, @NotNull List<String> language, boolean stopOnError) throws IOException {
            Path checkerInfoDir;
            Intrinsics.checkNotNullParameter(pl, "pl");
            Intrinsics.checkNotNullParameter(language, "language");
            IResDirectory iResDirectoryCheckerInfoDir = MainConfigKt.checkerInfoDir(pl.getConfigDirs(), stopOnError);
            if (iResDirectoryCheckerInfoDir == null || (checkerInfoDir = iResDirectoryCheckerInfoDir.getPath()) == null) {
                if (!(!stopOnError)) {
                    throw new IllegalStateException("get checkerInfoDir return null".toString());
                }
                return null;
            }
            Path it = checkerInfoDir.resolve("checker_info_generator_logs");
            Intrinsics.checkNotNull(it);
            LinkOption[] linkOptionArr = new LinkOption[0];
            if (!Files.exists(it, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                Files.createDirectories(it, new FileAttribute[0]);
            }
            Path it2 = it.normalize();
            CheckerInfoGenerator.logger.info(() -> {
                return createCheckerInfoGenerator$lambda$4$lambda$3(r1);
            });
            PluginDefinitions pluginDefinitions = PluginDefinitions.Companion.load(pl.getPluginManager());
            Intrinsics.checkNotNull(it2);
            return new CheckerInfoGenerator(language, it2, checkerInfoDir, pluginDefinitions);
        }

        private static final Object createCheckerInfoGenerator$lambda$4$lambda$3(Path $it) {
            return "The tools output path is: " + $it;
        }
    }

    private static final Unit logger$lambda$66() {
        return Unit.INSTANCE;
    }

    private static final Unit jsonFormat$lambda$67(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }
}
