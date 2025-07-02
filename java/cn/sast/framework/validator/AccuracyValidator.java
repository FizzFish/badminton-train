package cn.sast.framework.validator;

import cn.sast.api.config.MainConfig;
import cn.sast.api.report.ExpectBugAnnotationData;
import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IProjectFileLocator;
import cn.sast.framework.result.ResultCollector;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.SerializersKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��j\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018��2\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@¢\u0006\u0002\u0010\u001bJ \u0010\u001e\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u00100\u001f2\u0006\u0010 \u001a\u00020!H\u0002J*\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020$H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n��R%\u0010\r\u001a\u00020\f*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u00100\u000e8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n��¨\u0006("}, d2 = {"Lcn/sast/framework/validator/AccuracyValidator;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "logger", "Lmu/KLogger;", "extensions", "", "", "str", "", "Lcn/sast/api/report/ExpectBugAnnotationData;", "Lcn/sast/framework/validator/ExpectBugType;", "getStr", "(Ljava/util/Set;)Ljava/lang/String;", "rules", "Lcom/feysh/corax/config/api/rules/ProcessRule$FileMatch;", "makeScore", "Lcn/sast/framework/validator/AccuracyValidator$Result;", "result", "Lcn/sast/framework/result/ResultCollector;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Lcn/sast/framework/result/ResultCollector;Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pattern", "Ljava/util/regex/Pattern;", "parseFile", "", "file", "Lcn/sast/common/IResFile;", "getLineAndColumn", "Lkotlin/Triple;", "", "text", "index", "Result", "corax-framework"})
@SourceDebugExtension({"SMAP\nPrecisionMeasurement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,423:1\n1557#2:424\n1628#2,3:425\n1053#2:429\n24#3:428\n1#4:430\n*S KotlinDebug\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator\n*L\n86#1:424\n86#1:425,3\n81#1:429\n86#1:428\n*E\n"})
/* loaded from: AccuracyValidator.class */
public final class AccuracyValidator {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final KLogger logger;

    @NotNull
    private final List<String> extensions;

    @NotNull
    private final List<ProcessRule.FileMatch> rules;

    @NotNull
    private final Pattern pattern;

    public AccuracyValidator(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.mainConfig = mainConfig;
        this.logger = KotlinLogging.INSTANCE.logger(AccuracyValidator::logger$lambda$0);
        this.extensions = CollectionsKt.plus(ResourceKt.getJavaExtensions(), CollectionsKt.listOf(new String[]{"yml", "txt", "gradle", "kts", "cnf", "conf", "config", "xml", "properties"}));
        ProcessRule processRule = ProcessRule.INSTANCE;
        Iterable $this$map$iv = CollectionsKt.listOf(new String[]{"build", "out", "target", ".idea", ".git"});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add("(-)path=/" + it + "/");
        }
        Iterable ruleItems$iv = (List) destination$iv$iv;
        Iterable $this$map$iv$iv = ruleItems$iv;
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv : $this$map$iv$iv) {
            String it$iv = (String) item$iv$iv$iv;
            destination$iv$iv$iv.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.FileMatch.class)), it$iv));
        }
        this.rules = (List) destination$iv$iv$iv;
        Pattern patternCompile = Pattern.compile("(?<escape>(!?))\\$ *(?<name>((`([^(`\\r\\n)])+`)|([a-zA-Z$_]+[a-zA-Z0-9$_.-]*)))", 8);
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(...)");
        this.pattern = patternCompile;
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    /* compiled from: PrecisionMeasurement.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\b\u0018��2\u00020\u0001B×\u0001\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003\u0012\"\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003\u0012\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003\u0012\"\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010'\u001a\u00020\u0007H\u0016J%\u0010(\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003HÆ\u0003J%\u0010)\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003HÆ\u0003J%\u0010*\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003HÆ\u0003J%\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003HÆ\u0003J\t\u0010,\u001a\u00020\rHÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J\t\u0010.\u001a\u00020\rHÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003J\t\u00100\u001a\u00020\rHÆ\u0003J\t\u00101\u001a\u00020\u0013HÆ\u0003J\t\u00102\u001a\u00020\u0013HÆ\u0003J\t\u00103\u001a\u00020\u0013HÆ\u0003Jñ\u0001\u00104\u001a\u00020��2$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u00032$\b\u0002\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u00032$\b\u0002\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u00032$\b\u0002\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013HÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u00020\rHÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R-\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u0019R-\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0019R-\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b \u0010\u001eR\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\r¢\u0006\b\n��\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n��\u001a\u0004\b#\u0010$R\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n��\u001a\u0004\b%\u0010$R\u0011\u0010\u0015\u001a\u00020\u0013¢\u0006\b\n��\u001a\u0004\b&\u0010$¨\u00069"}, d2 = {"Lcn/sast/framework/validator/AccuracyValidator$Result;", "", "TP", "", "Lcn/sast/framework/validator/RowType;", "", "Lcn/sast/api/report/ExpectBugAnnotationData;", "", "Lcn/sast/framework/validator/ExpectBugType;", "FN", "TN", "FP", "allTp", "", "allFn", "allTn", "allFp", "allTotal", "allTpr", "", "allFpr", "score", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;IIIIIFFF)V", "getTP", "()Ljava/util/Map;", "getFN", "getTN", "getFP", "getAllTp", "()I", "getAllFn", "getAllTn", "getAllFp", "getAllTotal", "getAllTpr", "()F", "getAllFpr", "getScore", "toString", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "corax-framework"})
    /* loaded from: AccuracyValidator$Result.class */
    public static final class Result {

        @NotNull
        private final Map<RowType, Set<ExpectBugAnnotationData<String>>> TP;

        @NotNull
        private final Map<RowType, Set<ExpectBugAnnotationData<String>>> FN;

        @NotNull
        private final Map<RowType, Set<ExpectBugAnnotationData<String>>> TN;

        @NotNull
        private final Map<RowType, Set<ExpectBugAnnotationData<String>>> FP;
        private final int allTp;
        private final int allFn;
        private final int allTn;
        private final int allFp;
        private final int allTotal;
        private final float allTpr;
        private final float allFpr;
        private final float score;

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> component1() {
            return this.TP;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> component2() {
            return this.FN;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> component3() {
            return this.TN;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> component4() {
            return this.FP;
        }

        public final int component5() {
            return this.allTp;
        }

        public final int component6() {
            return this.allFn;
        }

        public final int component7() {
            return this.allTn;
        }

        public final int component8() {
            return this.allFp;
        }

        public final int component9() {
            return this.allTotal;
        }

        public final float component10() {
            return this.allTpr;
        }

        public final float component11() {
            return this.allFpr;
        }

        public final float component12() {
            return this.score;
        }

        @NotNull
        public final Result copy(@NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map2, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map3, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map4, int allTp, int allFn, int allTn, int allFp, int allTotal, float allTpr, float allFpr, float score) {
            Intrinsics.checkNotNullParameter(map, "TP");
            Intrinsics.checkNotNullParameter(map2, "FN");
            Intrinsics.checkNotNullParameter(map3, "TN");
            Intrinsics.checkNotNullParameter(map4, "FP");
            return new Result(map, map2, map3, map4, allTp, allFn, allTn, allFp, allTotal, allTpr, allFpr, score);
        }

        public static /* synthetic */ Result copy$default(Result result, Map map, Map map2, Map map3, Map map4, int i, int i2, int i3, int i4, int i5, float f, float f2, float f3, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                map = result.TP;
            }
            if ((i6 & 2) != 0) {
                map2 = result.FN;
            }
            if ((i6 & 4) != 0) {
                map3 = result.TN;
            }
            if ((i6 & 8) != 0) {
                map4 = result.FP;
            }
            if ((i6 & 16) != 0) {
                i = result.allTp;
            }
            if ((i6 & 32) != 0) {
                i2 = result.allFn;
            }
            if ((i6 & 64) != 0) {
                i3 = result.allTn;
            }
            if ((i6 & 128) != 0) {
                i4 = result.allFp;
            }
            if ((i6 & 256) != 0) {
                i5 = result.allTotal;
            }
            if ((i6 & 512) != 0) {
                f = result.allTpr;
            }
            if ((i6 & 1024) != 0) {
                f2 = result.allFpr;
            }
            if ((i6 & 2048) != 0) {
                f3 = result.score;
            }
            return result.copy(map, map2, map3, map4, i, i2, i3, i4, i5, f, f2, f3);
        }

        public int hashCode() {
            int result = this.TP.hashCode();
            return (((((((((((((((((((((result * 31) + this.FN.hashCode()) * 31) + this.TN.hashCode()) * 31) + this.FP.hashCode()) * 31) + Integer.hashCode(this.allTp)) * 31) + Integer.hashCode(this.allFn)) * 31) + Integer.hashCode(this.allTn)) * 31) + Integer.hashCode(this.allFp)) * 31) + Integer.hashCode(this.allTotal)) * 31) + Float.hashCode(this.allTpr)) * 31) + Float.hashCode(this.allFpr)) * 31) + Float.hashCode(this.score);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.TP, result.TP) && Intrinsics.areEqual(this.FN, result.FN) && Intrinsics.areEqual(this.TN, result.TN) && Intrinsics.areEqual(this.FP, result.FP) && this.allTp == result.allTp && this.allFn == result.allFn && this.allTn == result.allTn && this.allFp == result.allFp && this.allTotal == result.allTotal && Float.compare(this.allTpr, result.allTpr) == 0 && Float.compare(this.allFpr, result.allFpr) == 0 && Float.compare(this.score, result.score) == 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Result(@NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map2, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map3, @NotNull Map<RowType, ? extends Set<ExpectBugAnnotationData<String>>> map4, int allTp, int allFn, int allTn, int allFp, int allTotal, float allTpr, float allFpr, float score) {
            Intrinsics.checkNotNullParameter(map, "TP");
            Intrinsics.checkNotNullParameter(map2, "FN");
            Intrinsics.checkNotNullParameter(map3, "TN");
            Intrinsics.checkNotNullParameter(map4, "FP");
            this.TP = map;
            this.FN = map2;
            this.TN = map3;
            this.FP = map4;
            this.allTp = allTp;
            this.allFn = allFn;
            this.allTn = allTn;
            this.allFp = allFp;
            this.allTotal = allTotal;
            this.allTpr = allTpr;
            this.allFpr = allFpr;
            this.score = score;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> getTP() {
            return this.TP;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> getFN() {
            return this.FN;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> getTN() {
            return this.TN;
        }

        @NotNull
        public final Map<RowType, Set<ExpectBugAnnotationData<String>>> getFP() {
            return this.FP;
        }

        public final int getAllTp() {
            return this.allTp;
        }

        public final int getAllFn() {
            return this.allFn;
        }

        public final int getAllTn() {
            return this.allTn;
        }

        public final int getAllFp() {
            return this.allFp;
        }

        public final int getAllTotal() {
            return this.allTotal;
        }

        public final float getAllTpr() {
            return this.allTpr;
        }

        public final float getAllFpr() {
            return this.allFpr;
        }

        public final float getScore() {
            return this.score;
        }

        @NotNull
        public String toString() {
            return "TP: " + this.allTp + ", FN: " + this.allFn + ", TN: " + this.allTn + ", FP: " + this.allFp + ", allTotal: " + this.allTotal + ", TPR:" + PrecisionMeasurementKt.getFm(this.allTpr) + ", FPR:" + PrecisionMeasurementKt.getFm(this.allFpr) + ", Score: " + PrecisionMeasurementKt.getFm(this.score);
        }
    }

    @NotNull
    public final String getStr(@NotNull Set<ExpectBugAnnotationData<String>> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        Set<ExpectBugAnnotationData<String>> $this$sortedBy$iv = set;
        return "\"" + CollectionsKt.joinToString$default(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.framework.validator.AccuracyValidator$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                ExpectBugAnnotationData it = (ExpectBugAnnotationData) t;
                ExpectBugAnnotationData it2 = (ExpectBugAnnotationData) t2;
                return ComparisonsKt.compareValues(Integer.valueOf(it.getLine()), Integer.valueOf(it2.getLine()));
            }
        }), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v1) -> {
            return _get_str_$lambda$3(r6, v1);
        }, 30, (Object) null) + "\"";
    }

    private static final CharSequence _get_str_$lambda$3(AccuracyValidator this$0, ExpectBugAnnotationData bugAnnotationData) {
        Intrinsics.checkNotNullParameter(bugAnnotationData, "bugAnnotationData");
        return "file: " + this$0.mainConfig.tryGetRelativePath(bugAnnotationData.getFile()).getRelativePath() + ":" + bugAnnotationData.getLine() + ":" + bugAnnotationData.getColumn() + " kind: " + bugAnnotationData.getKind() + " a bug: " + bugAnnotationData.getBug();
    }

    /* compiled from: PrecisionMeasurement.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/validator/AccuracyValidator$Result;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "PrecisionMeasurement.kt", l = {103, 117}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$3", "L$0"}, n = {"projectFileLocator", "expectedResults", "destination$iv$iv", "expectedResults"}, m = "invokeSuspend", c = "cn.sast.framework.validator.AccuracyValidator$makeScore$2")
    @SourceDebugExtension({"SMAP\nPrecisionMeasurement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator$makeScore$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,423:1\n1381#2:424\n1469#2,5:425\n1454#2,5:430\n1863#2:435\n1557#2:436\n1628#2,3:437\n1557#2:440\n1628#2,3:441\n1557#2:444\n1628#2,3:445\n1863#2:449\n1864#2:457\n1864#2:458\n1863#2,2:501\n1628#2,3:503\n1628#2,3:506\n1628#2,3:509\n1628#2,3:512\n1053#2:515\n1#3:448\n381#4,7:450\n381#4,7:459\n381#4,7:466\n381#4,7:473\n381#4,7:480\n381#4,7:487\n381#4,7:494\n*S KotlinDebug\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator$makeScore$2\n*L\n102#1:424\n102#1:425,5\n136#1:430,5\n160#1:435\n162#1:436\n162#1:437,3\n163#1:440\n163#1:441,3\n164#1:444\n164#1:445,3\n178#1:449\n178#1:457\n160#1:458\n244#1:501,2\n252#1:503,3\n253#1:506,3\n254#1:509,3\n255#1:512,3\n276#1:515\n179#1:450,7\n201#1:459,7\n205#1:466,7\n219#1:473,7\n224#1:480,7\n233#1:487,7\n237#1:494,7\n*E\n"})
    /* renamed from: cn.sast.framework.validator.AccuracyValidator$makeScore$2, reason: invalid class name */
    /* loaded from: AccuracyValidator$makeScore$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ ResultCollector $result;
        final /* synthetic */ IProjectFileLocator $locator;

        /* compiled from: PrecisionMeasurement.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
        /* renamed from: cn.sast.framework.validator.AccuracyValidator$makeScore$2$WhenMappings */
        /* loaded from: AccuracyValidator$makeScore$2$WhenMappings.class */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ExpectBugAnnotationData.Kind.values().length];
                try {
                    iArr[ExpectBugAnnotationData.Kind.Expect.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ExpectBugAnnotationData.Kind.Escape.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ResultCollector $result, IProjectFileLocator $locator, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$result = $result;
            this.$locator = $locator;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return AccuracyValidator.this.new AnonymousClass2(this.$result, this.$locator, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Result> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        /* JADX WARN: Removed duplicated region for block: B:135:0x0a5f A[LOOP:9: B:133:0x0a55->B:135:0x0a5f, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x0ab6 A[LOOP:10: B:137:0x0aac->B:139:0x0ab6, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:143:0x0b09 A[LOOP:11: B:141:0x0aff->B:143:0x0b09, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:147:0x0b5c A[LOOP:12: B:145:0x0b52->B:147:0x0b5c, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:151:0x0baf A[LOOP:13: B:149:0x0ba5->B:151:0x0baf, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0c7c  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x0c84  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x0da1  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0187  */
        /* JADX WARN: Removed duplicated region for block: B:217:0x0f73  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0272  */
        /* JADX WARN: Removed duplicated region for block: B:231:0x0fbc  */
        /* JADX WARN: Removed duplicated region for block: B:256:0x119f  */
        /* JADX WARN: Removed duplicated region for block: B:257:0x11a3  */
        /* JADX WARN: Removed duplicated region for block: B:260:0x11bd  */
        /* JADX WARN: Removed duplicated region for block: B:261:0x11c1  */
        /* JADX WARN: Removed duplicated region for block: B:264:0x133d  */
        /* JADX WARN: Removed duplicated region for block: B:265:0x1341  */
        /* JADX WARN: Removed duplicated region for block: B:268:0x1359  */
        /* JADX WARN: Removed duplicated region for block: B:269:0x135e  */
        /* JADX WARN: Removed duplicated region for block: B:272:0x1367  */
        /* JADX WARN: Removed duplicated region for block: B:273:0x139f  */
        /* JADX WARN: Removed duplicated region for block: B:276:0x13f2  */
        /* JADX WARN: Removed duplicated region for block: B:277:0x13f6  */
        /* JADX WARN: Removed duplicated region for block: B:280:0x140e  */
        /* JADX WARN: Removed duplicated region for block: B:281:0x1413  */
        /* JADX WARN: Removed duplicated region for block: B:284:0x141c  */
        /* JADX WARN: Removed duplicated region for block: B:285:0x1454  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x03a5  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0691  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x00b6  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r16) throws kotlin.NoWhenBranchMatchedException, java.io.IOException {
            /*
                Method dump skipped, instructions count: 5267
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.validator.AccuracyValidator.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final Job invokeSuspend$lambda$1$lambda$0(AccuracyValidator this$0, Set $expectedResults, IResFile file) {
            return BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new AccuracyValidator$makeScore$2$1$1$1(this$0, file, $expectedResults, null), 3, (Object) null);
        }

        private static final Object invokeSuspend$lambda$15(ExpectBugAnnotationData $expect) {
            return "not exists this type: " + $expect;
        }

        private static final Object invokeSuspend$lambda$18(ExpectBugAnnotationData $expect) {
            return "FP: " + $expect;
        }

        private static final Object invokeSuspend$lambda$20(ExpectBugAnnotationData $expect) {
            return "FN: " + $expect;
        }
    }

    @Nullable
    public final Object makeScore(@NotNull ResultCollector result, @NotNull IProjectFileLocator locator, @NotNull Continuation<? super Result> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(result, locator, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<ExpectBugAnnotationData<String>> parseFile(IResFile file) {
        String str;
        try {
            str = new String(ResourceKt.readAllBytes(file), Charsets.UTF_8);
        } catch (IOException e) {
            this.logger.error("read config file " + file + " failed");
            str = null;
        }
        String text = str;
        if (text == null) {
            return SetsKt.emptySet();
        }
        Matcher matcher = this.pattern.matcher(text);
        Intrinsics.checkNotNullExpressionValue(matcher, "matcher(...)");
        Set res = new LinkedHashSet();
        while (matcher.find()) {
            String strGroup = matcher.group("escape");
            Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
            boolean escape = strGroup.length() > 0;
            String strGroup2 = matcher.group("name");
            Intrinsics.checkNotNullExpressionValue(strGroup2, "group(...)");
            String name = StringsKt.removeSuffix(StringsKt.removeSurrounding(strGroup2, "`"), "--");
            int startIndex = matcher.start();
            Triple<Integer, Integer, Integer> lineAndColumn = getLineAndColumn(text, startIndex);
            int start = ((Number) lineAndColumn.component1()).intValue();
            int line = ((Number) lineAndColumn.component2()).intValue();
            int col = ((Number) lineAndColumn.component3()).intValue();
            String strSubstring = text.substring(start);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String lineText = StringsKt.substringBefore$default(strSubstring, "\n", (String) null, 2, (Object) null);
            String it = lineText.substring(0, startIndex - start);
            Intrinsics.checkNotNullExpressionValue(it, "substring(...)");
            if ((StringsKt.contains$default(it, "//", false, 2, (Object) null) || StringsKt.contains$default(it, "<!--", false, 2, (Object) null)) || Intrinsics.areEqual(file.getExtension(), "properties")) {
                IResFile absolute = file.getAbsolute();
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String lowerCase = name.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                ExpectBugAnnotationData it2 = new ExpectBugAnnotationData(absolute, line, col, lowerCase, escape ? ExpectBugAnnotationData.Kind.Escape : ExpectBugAnnotationData.Kind.Expect);
                this.logger.trace(() -> {
                    return parseFile$lambda$7$lambda$6(r1);
                });
                res.add(it2);
            }
        }
        return res;
    }

    private static final Object parseFile$lambda$7$lambda$6(ExpectBugAnnotationData $it) {
        return $it;
    }

    private final Triple<Integer, Integer, Integer> getLineAndColumn(String text, int index) {
        int line = 1;
        int col = 0;
        int start = 0;
        for (int i = 0; i < index; i++) {
            if (text.charAt(i) == '\n') {
                line++;
                start = i + 1;
                col = 0;
            } else {
                col++;
            }
        }
        return new Triple<>(Integer.valueOf(start), Integer.valueOf(line), Integer.valueOf(col));
    }
}
