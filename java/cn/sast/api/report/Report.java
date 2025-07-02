package cn.sast.api.report;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.util.ComparatorUtilsKt;
import cn.sast.common.GLB;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IChecker;
import com.feysh.corax.config.api.IRule;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JvmStreamsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010��\n\u0002\b\u0005\b\u0086\b\u0018�� e2\b\u0012\u0004\u0012\u00020��0\u00012\u00020\u0002:\u0002efBË\u0001\b��\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018\u0012\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0018¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020BH\u0016J\u001e\u0010C\u001a\u00020D2\u0006\u0010A\u001a\u00020B2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\n0\u0018H\u0002J\u001e\u0010F\u001a\u00020D2\u0006\u0010A\u001a\u00020B2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\n0\u0018H\u0002J(\u0010G\u001a\u00020D2\u0006\u0010A\u001a\u00020B2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\n0\u00182\b\b\u0002\u0010H\u001a\u00020IH\u0002J\u0016\u0010@\u001a\u00020\n2\u0006\u0010J\u001a\u00020B2\u0006\u0010K\u001a\u00020LJ\u0011\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020��H\u0096\u0002J\t\u0010P\u001a\u00020\u0004HÆ\u0003J\t\u0010Q\u001a\u00020\u0006HÆ\u0003J\u0015\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0003J\t\u0010S\u001a\u00020\nHÆ\u0003J\t\u0010T\u001a\u00020\nHÆ\u0003J\t\u0010U\u001a\u00020\nHÆ\u0003J\u000f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010[\u001a\u00020\u0016HÆ\u0003J\u000f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018HÆ\u0003J\u000f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018HÆ\u0003J\u000f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018HÆ\u0003J\u000f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0018HÆ\u0003JÛ\u0001\u0010`\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0018HÆ\u0001J\u0013\u0010a\u001a\u00020I2\b\u0010O\u001a\u0004\u0018\u00010bHÖ\u0003J\t\u0010c\u001a\u00020NHÖ\u0001J\t\u0010d\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b!\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b#\u0010$R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n��\u001a\u0004\b%\u0010&R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b'\u0010(R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b)\u0010(R\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b*\u0010(R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n��\u001a\u0004\b+\u0010,R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b-\u0010(R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b.\u0010(R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b/\u0010(R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b0\u0010(R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n��\u001a\u0004\b1\u00102R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b3\u00104\"\u0004\b5\u00106R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b7\u00104\"\u0004\b8\u00106R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b9\u00104\"\u0004\b:\u00106R \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0018X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b;\u00104\"\u0004\b<\u00106R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00040>8F¢\u0006\u0006\u001a\u0004\b?\u0010,¨\u0006g"}, d2 = {"Lcn/sast/api/report/Report;", "", "Lcn/sast/api/report/IReportHashAble;", "bugResFile", "Lcn/sast/api/report/IBugResInfo;", "region", "Lcom/feysh/corax/config/api/report/Region;", "message", "", "Lcom/feysh/corax/config/api/Language;", "", "check_name", "detector_name", "type", "standard", "", "Lcom/feysh/corax/config/api/IRule;", "severity", "analyzer_name", "category", "analyzer_result_file_path", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "pathEvents", "", "Lcn/sast/api/report/BugPathEvent;", "bug_path_positions", "Lcn/sast/api/report/BugPathPosition;", "notes", "macro_expansions", "Lcn/sast/api/report/MacroExpansion;", "<init>", "(Lcn/sast/api/report/IBugResInfo;Lcom/feysh/corax/config/api/report/Region;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/feysh/corax/config/api/CheckType;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getBugResFile", "()Lcn/sast/api/report/IBugResInfo;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "getMessage", "()Ljava/util/Map;", "getCheck_name", "()Ljava/lang/String;", "getDetector_name", "getType", "getStandard", "()Ljava/util/Set;", "getSeverity", "getAnalyzer_name", "getCategory", "getAnalyzer_result_file_path", "getCheckType", "()Lcom/feysh/corax/config/api/CheckType;", "getPathEvents", "()Ljava/util/List;", "setPathEvents", "(Ljava/util/List;)V", "getBug_path_positions", "setBug_path_positions", "getNotes", "setNotes", "getMacro_expansions", "setMacro_expansions", "classes", "", "getClasses", "reportHash", "c", "Lcn/sast/api/report/IReportHashCalculator;", "getReportHashContextFree", "", "ret", "getReportHashPathSensitive", "getReportHashDiagnosticMessage", "onlyHeadTail", "", "hashCalculator", "hashType", "Lcn/sast/api/report/Report$HashType;", "compareTo", "", "other", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "", "hashCode", "toString", "Companion", "HashType", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/Report\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1628#2,3:452\n1628#2,3:455\n1628#2,3:458\n1628#2,3:461\n1#3:464\n*S KotlinDebug\n*F\n+ 1 Report.kt\ncn/sast/api/report/Report\n*L\n330#1:452,3\n331#1:455,3\n332#1:458,3\n333#1:461,3\n*E\n"})
/* loaded from: Report.class */
public final class Report implements Comparable<Report>, IReportHashAble {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final IBugResInfo bugResFile;

    @NotNull
    private final Region region;

    @NotNull
    private final Map<Language, String> message;

    @NotNull
    private final String check_name;

    @NotNull
    private final String detector_name;

    @NotNull
    private final String type;

    @NotNull
    private final Set<IRule> standard;

    @Nullable
    private final String severity;

    @Nullable
    private final String analyzer_name;

    @Nullable
    private final String category;

    @Nullable
    private final String analyzer_result_file_path;

    @NotNull
    private final CheckType checkType;

    @NotNull
    private List<BugPathEvent> pathEvents;

    @NotNull
    private List<BugPathPosition> bug_path_positions;

    @NotNull
    private List<BugPathEvent> notes;

    @NotNull
    private List<MacroExpansion> macro_expansions;

    @NotNull
    private static final Map<String, String> deprecatedRuleCategoryMap;
    private static int hashVersion;

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: Report$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HashType.values().length];
            try {
                iArr[HashType.CONTEXT_FREE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[HashType.PATH_SENSITIVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[HashType.DIAGNOSTIC_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public final IBugResInfo component1() {
        return this.bugResFile;
    }

    @NotNull
    public final Region component2() {
        return this.region;
    }

    @NotNull
    public final Map<Language, String> component3() {
        return this.message;
    }

    @NotNull
    public final String component4() {
        return this.check_name;
    }

    @NotNull
    public final String component5() {
        return this.detector_name;
    }

    @NotNull
    public final String component6() {
        return this.type;
    }

    @NotNull
    public final Set<IRule> component7() {
        return this.standard;
    }

    @Nullable
    public final String component8() {
        return this.severity;
    }

    @Nullable
    public final String component9() {
        return this.analyzer_name;
    }

    @Nullable
    public final String component10() {
        return this.category;
    }

    @Nullable
    public final String component11() {
        return this.analyzer_result_file_path;
    }

    @NotNull
    public final CheckType component12() {
        return this.checkType;
    }

    @NotNull
    public final List<BugPathEvent> component13() {
        return this.pathEvents;
    }

    @NotNull
    public final List<BugPathPosition> component14() {
        return this.bug_path_positions;
    }

    @NotNull
    public final List<BugPathEvent> component15() {
        return this.notes;
    }

    @NotNull
    public final List<MacroExpansion> component16() {
        return this.macro_expansions;
    }

    @NotNull
    public final Report copy(@NotNull IBugResInfo bugResFile, @NotNull Region region, @NotNull Map<Language, String> map, @NotNull String check_name, @NotNull String detector_name, @NotNull String type, @NotNull Set<? extends IRule> set, @Nullable String severity, @Nullable String analyzer_name, @Nullable String category, @Nullable String analyzer_result_file_path, @NotNull CheckType checkType, @NotNull List<BugPathEvent> list, @NotNull List<BugPathPosition> list2, @NotNull List<BugPathEvent> list3, @NotNull List<MacroExpansion> list4) {
        Intrinsics.checkNotNullParameter(bugResFile, "bugResFile");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(check_name, "check_name");
        Intrinsics.checkNotNullParameter(detector_name, "detector_name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(set, "standard");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(list, "pathEvents");
        Intrinsics.checkNotNullParameter(list2, "bug_path_positions");
        Intrinsics.checkNotNullParameter(list3, "notes");
        Intrinsics.checkNotNullParameter(list4, "macro_expansions");
        return new Report(bugResFile, region, map, check_name, detector_name, type, set, severity, analyzer_name, category, analyzer_result_file_path, checkType, list, list2, list3, list4);
    }

    public static /* synthetic */ Report copy$default(Report report, IBugResInfo iBugResInfo, Region region, Map map, String str, String str2, String str3, Set set, String str4, String str5, String str6, String str7, CheckType checkType, List list, List list2, List list3, List list4, int i, Object obj) {
        if ((i & 1) != 0) {
            iBugResInfo = report.bugResFile;
        }
        if ((i & 2) != 0) {
            region = report.region;
        }
        if ((i & 4) != 0) {
            map = report.message;
        }
        if ((i & 8) != 0) {
            str = report.check_name;
        }
        if ((i & 16) != 0) {
            str2 = report.detector_name;
        }
        if ((i & 32) != 0) {
            str3 = report.type;
        }
        if ((i & 64) != 0) {
            set = report.standard;
        }
        if ((i & 128) != 0) {
            str4 = report.severity;
        }
        if ((i & 256) != 0) {
            str5 = report.analyzer_name;
        }
        if ((i & 512) != 0) {
            str6 = report.category;
        }
        if ((i & 1024) != 0) {
            str7 = report.analyzer_result_file_path;
        }
        if ((i & 2048) != 0) {
            checkType = report.checkType;
        }
        if ((i & 4096) != 0) {
            list = report.pathEvents;
        }
        if ((i & 8192) != 0) {
            list2 = report.bug_path_positions;
        }
        if ((i & 16384) != 0) {
            list3 = report.notes;
        }
        if ((i & 32768) != 0) {
            list4 = report.macro_expansions;
        }
        return report.copy(iBugResInfo, region, map, str, str2, str3, set, str4, str5, str6, str7, checkType, list, list2, list3, list4);
    }

    @NotNull
    public String toString() {
        return "Report(bugResFile=" + this.bugResFile + ", region=" + this.region + ", message=" + this.message + ", check_name=" + this.check_name + ", detector_name=" + this.detector_name + ", type=" + this.type + ", standard=" + this.standard + ", severity=" + this.severity + ", analyzer_name=" + this.analyzer_name + ", category=" + this.category + ", analyzer_result_file_path=" + this.analyzer_result_file_path + ", checkType=" + this.checkType + ", pathEvents=" + this.pathEvents + ", bug_path_positions=" + this.bug_path_positions + ", notes=" + this.notes + ", macro_expansions=" + this.macro_expansions + ")";
    }

    public int hashCode() {
        int result = this.bugResFile.hashCode();
        return (((((((((((((((((((((((((((((result * 31) + this.region.hashCode()) * 31) + this.message.hashCode()) * 31) + this.check_name.hashCode()) * 31) + this.detector_name.hashCode()) * 31) + this.type.hashCode()) * 31) + this.standard.hashCode()) * 31) + (this.severity == null ? 0 : this.severity.hashCode())) * 31) + (this.analyzer_name == null ? 0 : this.analyzer_name.hashCode())) * 31) + (this.category == null ? 0 : this.category.hashCode())) * 31) + (this.analyzer_result_file_path == null ? 0 : this.analyzer_result_file_path.hashCode())) * 31) + this.checkType.hashCode()) * 31) + this.pathEvents.hashCode()) * 31) + this.bug_path_positions.hashCode()) * 31) + this.notes.hashCode()) * 31) + this.macro_expansions.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Report)) {
            return false;
        }
        Report report = (Report) other;
        return Intrinsics.areEqual(this.bugResFile, report.bugResFile) && Intrinsics.areEqual(this.region, report.region) && Intrinsics.areEqual(this.message, report.message) && Intrinsics.areEqual(this.check_name, report.check_name) && Intrinsics.areEqual(this.detector_name, report.detector_name) && Intrinsics.areEqual(this.type, report.type) && Intrinsics.areEqual(this.standard, report.standard) && Intrinsics.areEqual(this.severity, report.severity) && Intrinsics.areEqual(this.analyzer_name, report.analyzer_name) && Intrinsics.areEqual(this.category, report.category) && Intrinsics.areEqual(this.analyzer_result_file_path, report.analyzer_result_file_path) && Intrinsics.areEqual(this.checkType, report.checkType) && Intrinsics.areEqual(this.pathEvents, report.pathEvents) && Intrinsics.areEqual(this.bug_path_positions, report.bug_path_positions) && Intrinsics.areEqual(this.notes, report.notes) && Intrinsics.areEqual(this.macro_expansions, report.macro_expansions);
    }

    public Report(@NotNull IBugResInfo bugResFile, @NotNull Region region, @NotNull Map<Language, String> map, @NotNull String check_name, @NotNull String detector_name, @NotNull String type, @NotNull Set<? extends IRule> set, @Nullable String severity, @Nullable String analyzer_name, @Nullable String category, @Nullable String analyzer_result_file_path, @NotNull CheckType checkType, @NotNull List<BugPathEvent> list, @NotNull List<BugPathPosition> list2, @NotNull List<BugPathEvent> list3, @NotNull List<MacroExpansion> list4) {
        Intrinsics.checkNotNullParameter(bugResFile, "bugResFile");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(check_name, "check_name");
        Intrinsics.checkNotNullParameter(detector_name, "detector_name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(set, "standard");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(list, "pathEvents");
        Intrinsics.checkNotNullParameter(list2, "bug_path_positions");
        Intrinsics.checkNotNullParameter(list3, "notes");
        Intrinsics.checkNotNullParameter(list4, "macro_expansions");
        this.bugResFile = bugResFile;
        this.region = region;
        this.message = map;
        this.check_name = check_name;
        this.detector_name = detector_name;
        this.type = type;
        this.standard = set;
        this.severity = severity;
        this.analyzer_name = analyzer_name;
        this.category = category;
        this.analyzer_result_file_path = analyzer_result_file_path;
        this.checkType = checkType;
        this.pathEvents = list;
        this.bug_path_positions = list2;
        this.notes = list3;
        this.macro_expansions = list4;
        GLB.INSTANCE.plusAssign(this.checkType);
        this.pathEvents.add(new BugPathEvent(this.message, this.bugResFile, this.region, null, 8, null));
    }

    public /* synthetic */ Report(IBugResInfo iBugResInfo, Region region, Map map, String str, String str2, String str3, Set set, String str4, String str5, String str6, String str7, CheckType checkType, List list, List list2, List list3, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iBugResInfo, region, map, str, str2, str3, set, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? null : str5, (i & 512) != 0 ? null : str6, (i & 1024) != 0 ? null : str7, checkType, (i & 4096) != 0 ? new ArrayList() : list, (i & 8192) != 0 ? new ArrayList() : list2, (i & 16384) != 0 ? new ArrayList() : list3, (i & 32768) != 0 ? new ArrayList() : list4);
    }

    @NotNull
    public final IBugResInfo getBugResFile() {
        return this.bugResFile;
    }

    @NotNull
    public final Region getRegion() {
        return this.region;
    }

    @NotNull
    public final Map<Language, String> getMessage() {
        return this.message;
    }

    @NotNull
    public final String getCheck_name() {
        return this.check_name;
    }

    @NotNull
    public final String getDetector_name() {
        return this.detector_name;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final Set<IRule> getStandard() {
        return this.standard;
    }

    @Nullable
    public final String getSeverity() {
        return this.severity;
    }

    @Nullable
    public final String getAnalyzer_name() {
        return this.analyzer_name;
    }

    @Nullable
    public final String getCategory() {
        return this.category;
    }

    @Nullable
    public final String getAnalyzer_result_file_path() {
        return this.analyzer_result_file_path;
    }

    @NotNull
    public final CheckType getCheckType() {
        return this.checkType;
    }

    @NotNull
    public final List<BugPathEvent> getPathEvents() {
        return this.pathEvents;
    }

    public final void setPathEvents(@NotNull List<BugPathEvent> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.pathEvents = list;
    }

    @NotNull
    public final List<BugPathPosition> getBug_path_positions() {
        return this.bug_path_positions;
    }

    public final void setBug_path_positions(@NotNull List<BugPathPosition> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.bug_path_positions = list;
    }

    @NotNull
    public final List<BugPathEvent> getNotes() {
        return this.notes;
    }

    public final void setNotes(@NotNull List<BugPathEvent> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.notes = list;
    }

    @NotNull
    public final List<MacroExpansion> getMacro_expansions() {
        return this.macro_expansions;
    }

    public final void setMacro_expansions(@NotNull List<MacroExpansion> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.macro_expansions = list;
    }

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J@\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\f\u0010 \u001a\u00020\u0005*\u00020\u0011H\u0002R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0018X\u0082\u0004¢\u0006\b\n��\u0012\u0004\b\u0019\u0010\u0003R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcn/sast/api/report/Report$Companion;", "", "<init>", "()V", "getFinalReportCheckerName", "", "checker", "Lcom/feysh/corax/config/api/IChecker;", "of", "Lcn/sast/api/report/Report;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "file", "Lcn/sast/api/report/IBugResInfo;", "region", "Lcom/feysh/corax/config/api/report/Region;", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "pathEvents", "", "Lcn/sast/api/report/BugPathEvent;", "deprecatedRuleCategoryMap", "", "getDeprecatedRuleCategoryMap$annotations", "hashVersion", "", "getHashVersion", "()I", "setHashVersion", "(I)V", "categoryStr", "corax-api"})
    @SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/Report$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1611#2,9:452\n1863#2:461\n1864#2:463\n1620#2:464\n1#3:462\n1#3:465\n*S KotlinDebug\n*F\n+ 1 Report.kt\ncn/sast/api/report/Report$Companion\n*L\n278#1:452,9\n278#1:461\n278#1:463\n278#1:464\n278#1:462\n*E\n"})
    /* loaded from: Report$Companion.class */
    public static final class Companion {
        private static /* synthetic */ void getDeprecatedRuleCategoryMap$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        private final String getFinalReportCheckerName(IChecker checker) {
            String name = checker.getClass().getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            return name;
        }

        public static /* synthetic */ Report of$default(Companion companion, SootInfoCache sootInfoCache, IBugResInfo iBugResInfo, Region region, CheckType checkType, BugMessage.Env env, List list, int i, Object obj) {
            if ((i & 32) != 0) {
                list = CollectionsKt.emptyList();
            }
            return companion.of(sootInfoCache, iBugResInfo, region, checkType, env, list);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0135  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final cn.sast.api.report.Report of(@org.jetbrains.annotations.Nullable com.feysh.corax.cache.analysis.SootInfoCache r22, @org.jetbrains.annotations.NotNull cn.sast.api.report.IBugResInfo r23, @org.jetbrains.annotations.NotNull com.feysh.corax.config.api.report.Region r24, @org.jetbrains.annotations.NotNull com.feysh.corax.config.api.CheckType r25, @org.jetbrains.annotations.NotNull com.feysh.corax.config.api.BugMessage.Env r26, @org.jetbrains.annotations.NotNull java.util.List<cn.sast.api.report.BugPathEvent> r27) {
            /*
                Method dump skipped, instructions count: 354
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.report.Report.Companion.of(com.feysh.corax.cache.analysis.SootInfoCache, cn.sast.api.report.IBugResInfo, com.feysh.corax.config.api.report.Region, com.feysh.corax.config.api.CheckType, com.feysh.corax.config.api.BugMessage$Env, java.util.List):cn.sast.api.report.Report");
        }

        public final int getHashVersion() {
            return Report.hashVersion;
        }

        public final void setHashVersion(int i) {
            Report.hashVersion = i;
        }

        private final String categoryStr(CheckType $this$categoryStr) {
            switch (getHashVersion()) {
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    String id = (String) CheckType2StringKind.RuleDotTYName.getConvert().invoke($this$categoryStr);
                    String str = (String) Report.deprecatedRuleCategoryMap.get(id);
                    return str == null ? "unknown" : str;
                case 2:
                    return (String) CheckType2StringKind.Companion.getCheckType2StringKind().getConvert().invoke($this$categoryStr);
                default:
                    throw new IllegalStateException(("Bad hash version: " + getHashVersion()).toString());
            }
        }
    }

    static {
        Companion companion = Companion;
        Json jsonFormat = JsonKt.Json$default((Json) null, Report::deprecatedRuleCategoryMap$lambda$46$lambda$44, 1, (Object) null);
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("deprecated_rule_category.json");
        Intrinsics.checkNotNull(resourceAsStream);
        InputStream it = resourceAsStream;
        try {
            Map<String, String> map = (Map) JvmStreamsKt.decodeFromStream(jsonFormat, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), it);
            CloseableKt.closeFinally(it, (Throwable) null);
            deprecatedRuleCategoryMap = map;
            int it2 = ExtSettings.INSTANCE.getHashVersion();
            boolean z = 1 <= it2 && it2 < 3;
            if (z) {
                hashVersion = it2;
                return;
            }
            throw new IllegalStateException(("Bad hash version: " + it2).toString());
        } catch (Throwable th) {
            CloseableKt.closeFinally(it, (Throwable) null);
            throw th;
        }
    }

    private static final Unit deprecatedRuleCategoryMap$lambda$46$lambda$44(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<IBugResInfo> getClasses() {
        Set ret = SetsKt.mutableSetOf(new IBugResInfo[]{this.bugResFile});
        Iterable $this$mapTo$iv = this.pathEvents;
        for (Object item$iv : $this$mapTo$iv) {
            BugPathEvent it = (BugPathEvent) item$iv;
            ret.add(it.getClassname());
        }
        Iterable $this$mapTo$iv2 = this.bug_path_positions;
        for (Object item$iv2 : $this$mapTo$iv2) {
            BugPathPosition it2 = (BugPathPosition) item$iv2;
            ret.add(it2.getClassname());
        }
        Iterable $this$mapTo$iv3 = this.notes;
        for (Object item$iv3 : $this$mapTo$iv3) {
            BugPathEvent it3 = (BugPathEvent) item$iv3;
            ret.add(it3.getClassname());
        }
        Iterable $this$mapTo$iv4 = this.macro_expansions;
        for (Object item$iv4 : $this$mapTo$iv4) {
            MacroExpansion it4 = (MacroExpansion) item$iv4;
            ret.add(it4.getClassname());
        }
        return ret;
    }

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcn/sast/api/report/Report$HashType;", "", "<init>", "(Ljava/lang/String;I)V", "CONTEXT_FREE", "PATH_SENSITIVE", "DIAGNOSTIC_MESSAGE", "corax-api"})
    /* loaded from: Report$HashType.class */
    public enum HashType {
        CONTEXT_FREE,
        PATH_SENSITIVE,
        DIAGNOSTIC_MESSAGE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<HashType> getEntries() {
            return $ENTRIES;
        }
    }

    @Override // cn.sast.api.report.IReportHashAble
    @NotNull
    public String reportHash(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return this.bugResFile.reportHash(c) + ":" + this.region + " " + this.check_name + "," + this.detector_name + "," + this.type + "," + this.category + "," + this.severity + "," + this.analyzer_name + "} ";
    }

    private final void getReportHashContextFree(IReportHashCalculator c, List<String> list) {
        list.add(reportHash(c));
    }

    private final void getReportHashPathSensitive(IReportHashCalculator c, List<String> list) {
        BugPathEvent last = (BugPathEvent) CollectionsKt.lastOrNull(this.pathEvents);
        if (last != null) {
            list.add(last.reportHash(c));
        }
    }

    static /* synthetic */ void getReportHashDiagnosticMessage$default(Report report, IReportHashCalculator iReportHashCalculator, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        report.getReportHashDiagnosticMessage(iReportHashCalculator, list, z);
    }

    private final void getReportHashDiagnosticMessage(IReportHashCalculator c, List<String> list, boolean onlyHeadTail) {
        if (onlyHeadTail) {
            for (BugPathEvent be : this.pathEvents) {
                list.add(be.reportHashWithMessage(c));
            }
            return;
        }
        BugPathEvent it = (BugPathEvent) CollectionsKt.firstOrNull(this.pathEvents);
        if (it != null) {
            list.add(it.reportHashWithMessage(c));
        }
        BugPathEvent bugPathEvent = (BugPathEvent) CollectionsKt.lastOrNull(this.pathEvents);
        if (bugPathEvent != null) {
            BugPathEvent it2 = this.pathEvents.size() >= 2 ? bugPathEvent : null;
            if (it2 != null) {
                list.add(it2.reportHashWithMessage(c));
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @NotNull
    public final String reportHash(@NotNull IReportHashCalculator hashCalculator, @NotNull HashType hashType) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(hashCalculator, "hashCalculator");
        Intrinsics.checkNotNullParameter(hashType, "hashType");
        List ret = new ArrayList();
        switch (WhenMappings.$EnumSwitchMapping$0[hashType.ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                getReportHashContextFree(hashCalculator, ret);
                break;
            case 2:
                getReportHashContextFree(hashCalculator, ret);
                getReportHashPathSensitive(hashCalculator, ret);
                break;
            case 3:
                getReportHashContextFree(hashCalculator, ret);
                getReportHashDiagnosticMessage$default(this, hashCalculator, ret, false, 4, null);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return ReportKt.toHex(ReportKt.md5(CollectionsKt.joinToString$default(ret, "|", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)));
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Report other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Integer numValueOf = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.analyzer_name, other.analyzer_name));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        Integer numValueOf2 = Integer.valueOf(this.bugResFile.compareTo(other.bugResFile));
        int it3 = numValueOf2.intValue();
        Integer num2 = it3 != 0 ? numValueOf2 : null;
        if (num2 != null) {
            int it4 = num2.intValue();
            return it4;
        }
        Integer numValueOf3 = Integer.valueOf(Intrinsics.compare(this.region.startLine, other.region.startLine));
        int it5 = numValueOf3.intValue();
        Integer num3 = it5 != 0 ? numValueOf3 : null;
        if (num3 != null) {
            int it6 = num3.intValue();
            return it6;
        }
        Integer numValueOf4 = Integer.valueOf(ComparatorUtilsKt.compareToMap(MapsKt.toSortedMap(this.message), MapsKt.toSortedMap(other.message)));
        int it7 = numValueOf4.intValue();
        Integer num4 = it7 != 0 ? numValueOf4 : null;
        if (num4 != null) {
            int it8 = num4.intValue();
            return it8;
        }
        Integer numValueOf5 = Integer.valueOf(this.check_name.compareTo(other.check_name));
        int it9 = numValueOf5.intValue();
        Integer num5 = it9 != 0 ? numValueOf5 : null;
        if (num5 != null) {
            int it10 = num5.intValue();
            return it10;
        }
        Integer numValueOf6 = Integer.valueOf(this.detector_name.compareTo(other.detector_name));
        int it11 = numValueOf6.intValue();
        Integer num6 = it11 != 0 ? numValueOf6 : null;
        if (num6 != null) {
            int it12 = num6.intValue();
            return it12;
        }
        Integer numValueOf7 = Integer.valueOf(this.type.compareTo(other.type));
        int it13 = numValueOf7.intValue();
        Integer num7 = it13 != 0 ? numValueOf7 : null;
        if (num7 != null) {
            int it14 = num7.intValue();
            return it14;
        }
        Integer numValueOf8 = Integer.valueOf(new Comparator() { // from class: cn.sast.api.report.Report$compareTo$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                CheckType it15 = (CheckType) t;
                CheckType it16 = (CheckType) t2;
                return ComparisonsKt.compareValues(it15.getSerializerName(), it16.getSerializerName());
            }
        }.compare(this.checkType, other.checkType));
        int it15 = numValueOf8.intValue();
        Integer num8 = it15 != 0 ? numValueOf8 : null;
        if (num8 != null) {
            int it16 = num8.intValue();
            return it16;
        }
        Integer numValueOf9 = Integer.valueOf(ComparatorUtilsKt.compareTo(new Comparator() { // from class: cn.sast.api.report.Report$compareTo$$inlined$compareBy$2
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                IRule it17 = (IRule) t;
                IRule it18 = (IRule) t2;
                return ComparisonsKt.compareValues(it17.getRealName(), it18.getRealName());
            }
        }, this.standard, other.standard));
        int it17 = numValueOf9.intValue();
        Integer num9 = it17 != 0 ? numValueOf9 : null;
        if (num9 != null) {
            int it18 = num9.intValue();
            return it18;
        }
        Integer numValueOf10 = Integer.valueOf(Intrinsics.compare(this.region.startColumn, other.region.startColumn));
        int it19 = numValueOf10.intValue();
        Integer num10 = it19 != 0 ? numValueOf10 : null;
        if (num10 != null) {
            int it20 = num10.intValue();
            return it20;
        }
        Integer numValueOf11 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.severity, other.severity));
        int it21 = numValueOf11.intValue();
        Integer num11 = it21 != 0 ? numValueOf11 : null;
        if (num11 != null) {
            int it22 = num11.intValue();
            return it22;
        }
        Integer numValueOf12 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.category, other.category));
        int it23 = numValueOf12.intValue();
        Integer num12 = it23 != 0 ? numValueOf12 : null;
        if (num12 != null) {
            int it24 = num12.intValue();
            return it24;
        }
        Integer numValueOf13 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.analyzer_result_file_path, other.analyzer_result_file_path));
        int it25 = numValueOf13.intValue();
        Integer num13 = it25 != 0 ? numValueOf13 : null;
        if (num13 != null) {
            int it26 = num13.intValue();
            return it26;
        }
        Integer numValueOf14 = Integer.valueOf(ComparatorUtilsKt.compareToCollection(this.pathEvents, other.pathEvents));
        int it27 = numValueOf14.intValue();
        Integer num14 = it27 != 0 ? numValueOf14 : null;
        if (num14 != null) {
            int it28 = num14.intValue();
            return it28;
        }
        Integer numValueOf15 = Integer.valueOf(ComparatorUtilsKt.compareToCollection(this.bug_path_positions, other.bug_path_positions));
        int it29 = numValueOf15.intValue();
        Integer num15 = it29 != 0 ? numValueOf15 : null;
        if (num15 != null) {
            int it30 = num15.intValue();
            return it30;
        }
        Integer numValueOf16 = Integer.valueOf(ComparatorUtilsKt.compareToCollection(this.notes, other.notes));
        int it31 = numValueOf16.intValue();
        Integer num16 = it31 != 0 ? numValueOf16 : null;
        if (num16 != null) {
            int it32 = num16.intValue();
            return it32;
        }
        Integer numValueOf17 = Integer.valueOf(ComparatorUtilsKt.compareToCollection(this.macro_expansions, other.macro_expansions));
        int it33 = numValueOf17.intValue();
        Integer num17 = it33 != 0 ? numValueOf17 : null;
        if (num17 == null) {
            return 0;
        }
        int it34 = num17.intValue();
        return it34;
    }
}
