package cn.sast.framework.result;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.IResultCollector;
import cn.sast.api.report.Report;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.infoflow.provider.BugTypeProvider;
import cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector;
import cn.sast.framework.engine.PreAnalysisReportEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.metrics.MetricsMonitor;
import cn.sast.framework.report.IReportConsumer;
import cn.sast.framework.report.PlistDiagnostics;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.report.ReportConverter;
import cn.sast.framework.report.SarifDiagnostics;
import cn.sast.framework.report.SarifDiagnosticsCopySrc;
import cn.sast.framework.report.SarifDiagnosticsPack;
import cn.sast.framework.report.SqliteDiagnostics;
import cn.sast.framework.report.coverage.JacocoCompoundCoverage;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.CheckType;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.jimple.infoflow.collect.ConcurrentHashSet;
import soot.jimple.infoflow.data.AbstractionAtSink;
import soot.jimple.infoflow.results.DataFlowResult;
import soot.jimple.infoflow.results.InfoflowResults;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� c2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\u0001cBy\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0016\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\f\u00104\u001a\b\u0012\u0004\u0012\u00020/05J\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0018\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010\n\u001a\u00020GH\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010C\u001a\u00020/H\u0016J\u0016\u0010H\u001a\u00020D2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0IH\u0002J\u0010\u0010H\u001a\u00020D2\u0006\u0010C\u001a\u00020/H\u0002J\u0016\u0010J\u001a\u00020D2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0IH\u0016J\u000e\u0010K\u001a\u00020DH\u0096@¢\u0006\u0002\u0010LJ\u0010\u0010M\u001a\u00020D2\u0006\u0010N\u001a\u00020OH\u0016J\u0018\u0010P\u001a\u00020D2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0016J\u0018\u0010U\u001a\u00020\u00162\u0006\u0010V\u001a\u00020R2\u0006\u0010W\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\u00020D2\u0006\u0010Z\u001a\u00020[H\u0016J\u0016\u0010\\\u001a\u00020D2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0\u0011H\u0016J\b\u0010]\u001a\u00020DH\u0016J\u001e\u0010_\u001a\u00020D2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@¢\u0006\u0002\u0010`J\b\u0010a\u001a\u00020bH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n��\u001a\u0004\b \u0010!R\u0011\u0010\u001b\u001a\u00020\u0016¢\u0006\b\n��\u001a\u0004\b\"\u0010#R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n��\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0082\u0004¢\u0006\u0002\n��R\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020301X\u0082\u0004¢\u0006\u0002\n��R\u001a\u00107\u001a\u000208X\u0086.¢\u0006\u000e\n��\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001b\u0010=\u001a\u00020>8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\b?\u0010@R\u000e\u0010^\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n��¨\u0006d"}, d2 = {"Lcn/sast/framework/result/ResultCollector;", "Lcn/sast/framework/result/IFlowDroidResultCollector;", "Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "Lcn/sast/framework/result/IUTBotResultCollector;", "Lcn/sast/framework/result/IMissingSummaryReporter;", "Lcn/sast/framework/result/IPreAnalysisResultCollector;", "Lcn/sast/framework/result/IBuiltInAnalysisCollector;", "Lcn/sast/framework/result/IReportsVisitor;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "outputDir", "Lcn/sast/common/IResDirectory;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "collectors", "", "Lcn/sast/api/report/IResultCollector;", "outputTypes", "Lcn/sast/framework/result/OutputType;", "serializeTaintPath", "", "resultConverter", "Lcn/sast/framework/result/ResultConverter;", "coverage", "Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;", "flushCoverage", "monitor", "Lcn/sast/framework/metrics/MetricsMonitor;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/common/IResDirectory;Lcn/sast/framework/report/ProjectFileLocator;Ljava/util/List;Ljava/util/List;ZLcn/sast/framework/result/ResultConverter;Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;ZLcn/sast/framework/metrics/MetricsMonitor;)V", "getCoverage", "()Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;", "getFlushCoverage", "()Z", "getMonitor", "()Lcn/sast/framework/metrics/MetricsMonitor;", "collectorsFlowDroid", "collectorsUTBot", "collectorsDataFlow", "collectorsIFIChecker", "collectorsFlowSensitive", "reportsVisitor", "missingSummaryReporter", "reports", "Lsoot/jimple/infoflow/collect/ConcurrentHashSet;", "Lcn/sast/api/report/Report;", "purificationReports", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcn/sast/framework/result/PurificationReportKey;", "Ljava/util/concurrent/atomic/AtomicInteger;", "getReports", "", "getCollectors", "preAnalysis", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "getPreAnalysis", "()Lcn/sast/api/config/PreAnalysisCoroutineScope;", "setPreAnalysis", "(Lcn/sast/api/config/PreAnalysisCoroutineScope;)V", "bugTypeProvider", "Lcn/sast/dataflow/infoflow/provider/BugTypeProvider;", "getBugTypeProvider", "()Lcn/sast/dataflow/infoflow/provider/BugTypeProvider;", "bugTypeProvider$delegate", "Lkotlin/Lazy;", "report", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "Lcn/sast/framework/engine/PreAnalysisReportEnv;", "addReport", "", "accept", "flush", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportMissingMethod", "method", "Lsoot/SootMethod;", "onResultsAvailable", "cfg", "Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "results", "Lsoot/jimple/infoflow/results/InfoflowResults;", "onResultAvailable", "icfg", "abs", "Lsoot/jimple/infoflow/data/AbstractionAtSink;", "afterAnalyze", "analysis", "Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis;", "reportDataFlowBug", "addUtState", "flushing", "flushOutputType", "(Lcn/sast/api/config/MainConfig;Lcn/sast/framework/metrics/MetricsMonitor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoverageReportWriter", "Lcn/sast/framework/report/IReportConsumer;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nResultCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResultCollector.kt\ncn/sast/framework/result/ResultCollector\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n*L\n1#1,284:1\n808#2,11:285\n808#2,11:296\n808#2,11:307\n808#2,11:318\n808#2,11:329\n808#2,11:340\n808#2,11:351\n1863#2,2:362\n1863#2,2:364\n1863#2,2:370\n1557#2:372\n1628#2,3:373\n1863#2,2:376\n1863#2,2:378\n1755#2,3:380\n1863#2,2:383\n1863#2,2:385\n1863#2,2:387\n1557#2:389\n1628#2,3:390\n1#3:366\n1#3:369\n72#4,2:367\n*S KotlinDebug\n*F\n+ 1 ResultCollector.kt\ncn/sast/framework/result/ResultCollector\n*L\n90#1:285,11\n91#1:296,11\n92#1:307,11\n93#1:318,11\n94#1:329,11\n95#1:340,11\n97#1:351,11\n118#1:362,2\n123#1:364,2\n150#1:370,2\n158#1:372\n158#1:373,3\n166#1:376,2\n172#1:378,2\n180#1:380,3\n187#1:383,2\n196#1:385,2\n205#1:387,2\n231#1:389\n231#1:390,3\n132#1:369\n132#1:367,2\n*E\n"})
/* loaded from: ResultCollector.class */
public final class ResultCollector implements IFlowDroidResultCollector, IIPAnalysisResultCollector, IUTBotResultCollector, IMissingSummaryReporter, IPreAnalysisResultCollector, IBuiltInAnalysisCollector, IReportsVisitor {

    @NotNull
    private final MainConfig mainConfig;

    @Nullable
    private final SootInfoCache info;

    @NotNull
    private final IResDirectory outputDir;

    @NotNull
    private final ProjectFileLocator locator;

    @NotNull
    private final List<IResultCollector> collectors;

    @NotNull
    private final List<OutputType> outputTypes;
    private final boolean serializeTaintPath;

    @NotNull
    private final ResultConverter resultConverter;

    @NotNull
    private final JacocoCompoundCoverage coverage;
    private final boolean flushCoverage;

    @NotNull
    private final MetricsMonitor monitor;

    @NotNull
    private final List<IFlowDroidResultCollector> collectorsFlowDroid;

    @NotNull
    private final List<IUTBotResultCollector> collectorsUTBot;

    @NotNull
    private final List<IIPAnalysisResultCollector> collectorsDataFlow;

    @NotNull
    private final List<IPreAnalysisResultCollector> collectorsIFIChecker;

    @NotNull
    private final List<IBuiltInAnalysisCollector> collectorsFlowSensitive;

    @NotNull
    private final List<IReportsVisitor> reportsVisitor;

    @NotNull
    private final List<IMissingSummaryReporter> missingSummaryReporter;

    @NotNull
    private final ConcurrentHashSet<Report> reports;

    @NotNull
    private final ConcurrentHashMap<PurificationReportKey, AtomicInteger> purificationReports;
    public PreAnalysisCoroutineScope preAnalysis;

    @NotNull
    private final Lazy bugTypeProvider$delegate;
    private boolean flushing;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ResultCollector::logger$lambda$21);

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: ResultCollector$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OutputType.values().length];
            try {
                iArr[OutputType.PLIST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[OutputType.SARIF.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[OutputType.SarifPackSrc.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[OutputType.SarifCopySrc.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[OutputType.SQLITE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[OutputType.Coverage.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ResultCollector.kt", l = {160, 161, 162}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$0"}, n = {"jobs2", "job3", "job3"}, m = "flush", c = "cn.sast.framework.result.ResultCollector")
    /* renamed from: cn.sast.framework.result.ResultCollector$flush$1, reason: invalid class name */
    /* loaded from: ResultCollector$flush$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ResultCollector.this.flush((Continuation) this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResultCollector(@NotNull MainConfig mainConfig, @Nullable SootInfoCache info, @NotNull IResDirectory outputDir, @NotNull ProjectFileLocator locator, @NotNull List<? extends IResultCollector> list, @NotNull List<? extends OutputType> list2, boolean serializeTaintPath, @NotNull ResultConverter resultConverter, @NotNull JacocoCompoundCoverage coverage, boolean flushCoverage, @NotNull MetricsMonitor monitor) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        Intrinsics.checkNotNullParameter(locator, "locator");
        Intrinsics.checkNotNullParameter(list, "collectors");
        Intrinsics.checkNotNullParameter(list2, "outputTypes");
        Intrinsics.checkNotNullParameter(resultConverter, "resultConverter");
        Intrinsics.checkNotNullParameter(coverage, "coverage");
        Intrinsics.checkNotNullParameter(monitor, "monitor");
        this.mainConfig = mainConfig;
        this.info = info;
        this.outputDir = outputDir;
        this.locator = locator;
        this.collectors = list;
        this.outputTypes = list2;
        this.serializeTaintPath = serializeTaintPath;
        this.resultConverter = resultConverter;
        this.coverage = coverage;
        this.flushCoverage = flushCoverage;
        this.monitor = monitor;
        Iterable $this$filterIsInstance$iv = this.collectors;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof IFlowDroidResultCollector) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        this.collectorsFlowDroid = (List) destination$iv$iv;
        Iterable $this$filterIsInstance$iv2 = this.collectors;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filterIsInstance$iv2) {
            if (element$iv$iv2 instanceof IUTBotResultCollector) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        this.collectorsUTBot = (List) destination$iv$iv2;
        Iterable $this$filterIsInstance$iv3 = this.collectors;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filterIsInstance$iv3) {
            if (element$iv$iv3 instanceof IIPAnalysisResultCollector) {
                destination$iv$iv3.add(element$iv$iv3);
            }
        }
        this.collectorsDataFlow = (List) destination$iv$iv3;
        Iterable $this$filterIsInstance$iv4 = this.collectors;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv4 : $this$filterIsInstance$iv4) {
            if (element$iv$iv4 instanceof IPreAnalysisResultCollector) {
                destination$iv$iv4.add(element$iv$iv4);
            }
        }
        this.collectorsIFIChecker = (List) destination$iv$iv4;
        Iterable $this$filterIsInstance$iv5 = this.collectors;
        Collection destination$iv$iv5 = new ArrayList();
        for (Object element$iv$iv5 : $this$filterIsInstance$iv5) {
            if (element$iv$iv5 instanceof IBuiltInAnalysisCollector) {
                destination$iv$iv5.add(element$iv$iv5);
            }
        }
        this.collectorsFlowSensitive = (List) destination$iv$iv5;
        Iterable $this$filterIsInstance$iv6 = this.collectors;
        Collection destination$iv$iv6 = new ArrayList();
        for (Object element$iv$iv6 : $this$filterIsInstance$iv6) {
            if (element$iv$iv6 instanceof IReportsVisitor) {
                destination$iv$iv6.add(element$iv$iv6);
            }
        }
        this.reportsVisitor = (List) destination$iv$iv6;
        Iterable $this$filterIsInstance$iv7 = this.collectors;
        Collection destination$iv$iv7 = new ArrayList();
        for (Object element$iv$iv7 : $this$filterIsInstance$iv7) {
            if (element$iv$iv7 instanceof IMissingSummaryReporter) {
                destination$iv$iv7.add(element$iv$iv7);
            }
        }
        this.missingSummaryReporter = (List) destination$iv$iv7;
        this.reports = new ConcurrentHashSet<>();
        this.purificationReports = new ConcurrentHashMap<>();
        this.bugTypeProvider$delegate = LazyKt.lazy(() -> {
            return bugTypeProvider_delegate$lambda$2(r1);
        });
    }

    public /* synthetic */ ResultCollector(MainConfig mainConfig, SootInfoCache sootInfoCache, IResDirectory iResDirectory, ProjectFileLocator projectFileLocator, List list, List list2, boolean z, ResultConverter resultConverter, JacocoCompoundCoverage jacocoCompoundCoverage, boolean z2, MetricsMonitor metricsMonitor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mainConfig, sootInfoCache, iResDirectory, projectFileLocator, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? true : z, (i & 128) != 0 ? new ResultConverter(sootInfoCache) : resultConverter, (i & 256) != 0 ? new JacocoCompoundCoverage(projectFileLocator, null, null, false, 14, null) : jacocoCompoundCoverage, (i & 512) != 0 ? false : z2, metricsMonitor);
    }

    public static final /* synthetic */ MainConfig access$getMainConfig$p(ResultCollector $this) {
        return $this.mainConfig;
    }

    @NotNull
    public final JacocoCompoundCoverage getCoverage() {
        return this.coverage;
    }

    public final boolean getFlushCoverage() {
        return this.flushCoverage;
    }

    @NotNull
    public final MetricsMonitor getMonitor() {
        return this.monitor;
    }

    @NotNull
    public final Set<Report> getReports() {
        ResultCollector $this$getReports_u24lambda_u240 = this;
        $this$getReports_u24lambda_u240.flushing = true;
        return $this$getReports_u24lambda_u240.reports;
    }

    @NotNull
    public final List<IResultCollector> getCollectors() {
        return this.collectors;
    }

    @NotNull
    public final PreAnalysisCoroutineScope getPreAnalysis() {
        PreAnalysisCoroutineScope preAnalysisCoroutineScope = this.preAnalysis;
        if (preAnalysisCoroutineScope != null) {
            return preAnalysisCoroutineScope;
        }
        Intrinsics.throwUninitializedPropertyAccessException("preAnalysis");
        return null;
    }

    public final void setPreAnalysis(@NotNull PreAnalysisCoroutineScope preAnalysisCoroutineScope) {
        Intrinsics.checkNotNullParameter(preAnalysisCoroutineScope, "<set-?>");
        this.preAnalysis = preAnalysisCoroutineScope;
    }

    private final BugTypeProvider getBugTypeProvider() {
        return (BugTypeProvider) this.bugTypeProvider$delegate.getValue();
    }

    private static final BugTypeProvider bugTypeProvider_delegate$lambda$2(ResultCollector this$0) {
        Thread.interrupted();
        BugTypeProvider $this$bugTypeProvider_delegate_u24lambda_u242_u24lambda_u241 = new BugTypeProvider(this$0.mainConfig, this$0.getPreAnalysis());
        $this$bugTypeProvider_delegate_u24lambda_u242_u24lambda_u241.init();
        return $this$bugTypeProvider_delegate_u24lambda_u242_u24lambda_u241;
    }

    @Override // cn.sast.framework.result.IPreAnalysisResultCollector
    public void report(@NotNull CheckType checkType, @NotNull PreAnalysisReportEnv info) {
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(info, "info");
        Iterable $this$forEach$iv = this.collectorsIFIChecker;
        for (Object element$iv : $this$forEach$iv) {
            IPreAnalysisResultCollector it = (IPreAnalysisResultCollector) element$iv;
            it.report(checkType, info);
        }
        addReport(this.resultConverter.getReport(checkType, info));
    }

    @Override // cn.sast.framework.result.IBuiltInAnalysisCollector
    public void report(@NotNull Report report) {
        Intrinsics.checkNotNullParameter(report, "report");
        Iterable $this$forEach$iv = this.collectorsFlowSensitive;
        for (Object element$iv : $this$forEach$iv) {
            IBuiltInAnalysisCollector it = (IBuiltInAnalysisCollector) element$iv;
            it.report(report);
        }
        addReport(report);
    }

    private final void addReport(Collection<Report> collection) {
        if (!(!this.flushing)) {
            throw new IllegalArgumentException("internal error: emit bug reports when flush report".toString());
        }
        for (final Report report : collection) {
            if (this.mainConfig.isEnable(report.getCheckType())) {
                PurificationReportKey key = new PurificationReportKey(report.getBugResFile(), report.getRegion().startLine, report.getCheck_name(), (BugPathEvent) CollectionsKt.first(report.getPathEvents()));
                ConcurrentMap $this$getOrPut$iv = this.purificationReports;
                AtomicInteger atomicIntegerPutIfAbsent = $this$getOrPut$iv.get(key);
                if (atomicIntegerPutIfAbsent == null) {
                    AtomicInteger atomicInteger = new AtomicInteger(0);
                    atomicIntegerPutIfAbsent = $this$getOrPut$iv.putIfAbsent(key, atomicInteger);
                    if (atomicIntegerPutIfAbsent == null) {
                        atomicIntegerPutIfAbsent = atomicInteger;
                    }
                }
                AtomicInteger count = atomicIntegerPutIfAbsent;
                count.getAndUpdate(new IntUnaryOperator() { // from class: cn.sast.framework.result.ResultCollector.addReport.2
                    @Override // java.util.function.IntUnaryOperator
                    public final int applyAsInt(int it) {
                        if (it <= 5) {
                            if (ResultCollector.this.reports.add(report)) {
                                return it + 1;
                            }
                            return it;
                        }
                        return it;
                    }
                });
            }
        }
    }

    private final void addReport(Report report) {
        addReport(CollectionsKt.listOf(report));
    }

    @Override // cn.sast.framework.result.IReportsVisitor
    public void accept(@NotNull Collection<Report> collection) {
        Intrinsics.checkNotNullParameter(collection, "reports");
        Iterable $this$forEach$iv = this.reportsVisitor;
        for (Object element$iv : $this$forEach$iv) {
            IReportsVisitor it = (IReportsVisitor) element$iv;
            it.accept(collection);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @Override // cn.sast.api.report.IResultCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object flush(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.result.ResultCollector.flush(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // cn.sast.framework.result.IMissingSummaryReporter
    public void reportMissingMethod(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Iterable $this$forEach$iv = this.missingSummaryReporter;
        for (Object element$iv : $this$forEach$iv) {
            IMissingSummaryReporter it = (IMissingSummaryReporter) element$iv;
            it.reportMissingMethod(method);
        }
    }

    public void onResultsAvailable(@NotNull IInfoflowCFG cfg, @NotNull InfoflowResults results) {
        Intrinsics.checkNotNullParameter(cfg, "cfg");
        Intrinsics.checkNotNullParameter(results, "results");
        Iterable $this$forEach$iv = this.collectorsFlowDroid;
        for (Object element$iv : $this$forEach$iv) {
            IFlowDroidResultCollector it = (IFlowDroidResultCollector) element$iv;
            it.onResultsAvailable(cfg, results);
        }
        Set resultSet = results.getResultSet();
        if (resultSet == null) {
            resultSet = SetsKt.emptySet();
        }
        for (Object obj : resultSet) {
            Intrinsics.checkNotNullExpressionValue(obj, "next(...)");
            DataFlowResult result = (DataFlowResult) obj;
            addReport(this.resultConverter.getReport(cfg, result, getBugTypeProvider(), this.serializeTaintPath));
        }
    }

    public boolean onResultAvailable(@NotNull IInfoflowCFG icfg, @NotNull AbstractionAtSink abs) {
        boolean z;
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Intrinsics.checkNotNullParameter(abs, "abs");
        Iterable $this$any$iv = this.collectorsFlowDroid;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    IFlowDroidResultCollector it2 = (IFlowDroidResultCollector) element$iv;
                    if (!it2.onResultAvailable(icfg, abs)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        boolean stop = z;
        return !stop;
    }

    @Override // cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector
    public void afterAnalyze(@NotNull InterProceduralValueAnalysis analysis) {
        Intrinsics.checkNotNullParameter(analysis, "analysis");
        Iterable $this$forEach$iv = this.collectorsDataFlow;
        for (Object element$iv : $this$forEach$iv) {
            IIPAnalysisResultCollector it = (IIPAnalysisResultCollector) element$iv;
            it.afterAnalyze(analysis);
        }
    }

    @Override // cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector
    public void reportDataFlowBug(@NotNull List<Report> list) {
        Intrinsics.checkNotNullParameter(list, "reports");
        Iterable $this$forEach$iv = this.collectorsDataFlow;
        for (Object element$iv : $this$forEach$iv) {
            IIPAnalysisResultCollector it = (IIPAnalysisResultCollector) element$iv;
            it.reportDataFlowBug(list);
        }
        addReport(list);
    }

    @Override // cn.sast.framework.result.IUTBotResultCollector
    public void addUtState() {
        Iterable $this$forEach$iv = this.collectorsUTBot;
        for (Object element$iv : $this$forEach$iv) {
            IUTBotResultCollector it = (IUTBotResultCollector) element$iv;
            it.addUtState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public final Object flushOutputType(final MainConfig mainConfig, final MetricsMonitor monitor, Continuation<? super Unit> continuation) throws NoWhenBranchMatchedException {
        SqliteDiagnostics coverageReportWriter;
        Set outputTypesLocal = CollectionsKt.toMutableSet(this.outputTypes);
        if (outputTypesLocal.isEmpty()) {
            logger.warn(ResultCollector::flushOutputType$lambda$18);
            outputTypesLocal.add(OutputType.PLIST);
            outputTypesLocal.add(OutputType.SARIF);
        }
        if (this.flushCoverage) {
            outputTypesLocal.add(OutputType.Coverage);
        }
        ReportConverter reportConverter = new ReportConverter(mainConfig, null, 2, null);
        ProjectFileLocator projectFileLocator = this.locator;
        JacocoCompoundCoverage jacocoCompoundCoverage = this.coverage;
        Set $this$map$iv = outputTypesLocal;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            OutputType type = (OutputType) item$iv$iv;
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    coverageReportWriter = new PlistDiagnostics(mainConfig, this.info, this.outputDir.resolve(type.getDisplayName()).toDirectory());
                    break;
                case 2:
                    coverageReportWriter = new SarifDiagnostics(this.outputDir.resolve(type.getDisplayName()).toDirectory(), null, 2, null);
                    break;
                case 3:
                    coverageReportWriter = new SarifDiagnosticsPack(this.outputDir.resolve(type.getDisplayName()).toDirectory(), null, null, null, null, 30, null);
                    break;
                case 4:
                    coverageReportWriter = new SarifDiagnosticsCopySrc(this.outputDir.resolve(type.getDisplayName()).toDirectory(), null, null, null, null, 30, null);
                    break;
                case 5:
                    SqliteDiagnostics sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics = flushOutputType$newSqliteDiagnostics(mainConfig, this, monitor);
                    monitor.addAnalyzeFinishHook(new Thread(new Runnable() { // from class: cn.sast.framework.result.ResultCollector$flushOutputType$3$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            SqliteDiagnostics sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics2 = ResultCollector.flushOutputType$newSqliteDiagnostics(mainConfig, this, monitor);
                            try {
                                SqliteDiagnostics it = sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics2;
                                BuildersKt.runBlocking$default((CoroutineContext) null, new ResultCollector$flushOutputType$3$1$1$1$1(it, null), 1, (Object) null);
                                it.writeAnalyzerResultFiles();
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics2, (Throwable) null);
                            } catch (Throwable th) {
                                CloseableKt.closeFinally(sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics2, (Throwable) null);
                                throw th;
                            }
                        }
                    }));
                    coverageReportWriter = sqliteDiagnosticsFlushOutputType$newSqliteDiagnostics;
                    break;
                case 6:
                    coverageReportWriter = getCoverageReportWriter();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            destination$iv$iv.add(coverageReportWriter);
        }
        Object objFlush = reportConverter.flush(mainConfig, projectFileLocator, jacocoCompoundCoverage, (List) destination$iv$iv, getReports(), this.outputDir, continuation);
        return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
    }

    private static final Object flushOutputType$lambda$18() {
        return "not special any output types! Will use PLIST and SARIF formats and SQLITE for generating report";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SqliteDiagnostics flushOutputType$newSqliteDiagnostics(MainConfig $mainConfig, ResultCollector this$0, MetricsMonitor $monitor) {
        return Companion.newSqliteDiagnostics($mainConfig, this$0.info, this$0.outputDir, $monitor);
    }

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��#\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001��\b\n\u0018��2\u00020\u0001J\u000e\u0010\u0006\u001a\u00020\u0007H\u0096@¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u0007H\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000e"}, d2 = {"cn/sast/framework/result/ResultCollector$getCoverageReportWriter$1", "Lcn/sast/framework/report/IReportConsumer;", "type", "Lcn/sast/framework/result/OutputType;", "getType", "()Lcn/sast/framework/result/OutputType;", "init", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "run", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "corax-framework"})
    @SourceDebugExtension({"SMAP\nResultCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResultCollector.kt\ncn/sast/framework/result/ResultCollector$getCoverageReportWriter$1\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,284:1\n49#2,13:285\n62#2,11:306\n16#3,8:298\n*S KotlinDebug\n*F\n+ 1 ResultCollector.kt\ncn/sast/framework/result/ResultCollector$getCoverageReportWriter$1\n*L\n262#1:285,13\n262#1:306,11\n263#1:298,8\n*E\n"})
    /* renamed from: cn.sast.framework.result.ResultCollector$getCoverageReportWriter$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ResultCollector$getCoverageReportWriter$1.class */
    public static final class C00441 implements IReportConsumer {
        C00441() {
        }

        @Override // cn.sast.framework.report.IReportConsumer
        public OutputType getType() {
            return OutputType.Coverage;
        }

        @Override // cn.sast.framework.report.IReportConsumer
        public Object init(Continuation<? super Unit> continuation) {
            return Unit.INSTANCE;
        }

        /*  JADX ERROR: Types fix failed
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
            */
        /* JADX WARN: Failed to apply debug info
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r12v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r12v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r12v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r12v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r15v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Failed to calculate best type for var: r15v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r15v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r15v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r17v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x02cb: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:308), block:B:43:0x02cb */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x02fd: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:314), block:B:50:0x02fd */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x0317: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:316), block:B:51:0x0317 */
        /* JADX WARN: Not initialized variable reg: 12, insn: 0x02d5: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:43:0x02cb */
        /* JADX WARN: Not initialized variable reg: 12, insn: 0x0307: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:50:0x02fd */
        /* JADX WARN: Not initialized variable reg: 12, insn: 0x0321: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:51:0x0317 */
        /* JADX WARN: Not initialized variable reg: 15, insn: 0x02d3: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:43:0x02cb */
        /* JADX WARN: Not initialized variable reg: 15, insn: 0x0305: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:50:0x02fd */
        /* JADX WARN: Not initialized variable reg: 15, insn: 0x031f: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:51:0x0317 */
        /* JADX WARN: Not initialized variable reg: 16, insn: 0x02ea: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r16 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('alreadyLogged$iv' boolean)]) A[TRY_LEAVE], block:B:46:0x02ea */
        /* JADX WARN: Not initialized variable reg: 17, insn: 0x02ef: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r17 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('res$iv' kotlin.jvm.internal.Ref$ObjectRef)]) (LINE:313), block:B:48:0x02ef */
        @Override // cn.sast.framework.report.IReportConsumer
        public java.lang.Object run(cn.sast.framework.report.IProjectFileLocator r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
            /*
                Method dump skipped, instructions count: 847
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.result.ResultCollector.C00441.run(cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    private final IReportConsumer getCoverageReportWriter() {
        return new C00441();
    }

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/result/ResultCollector$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "newSqliteDiagnostics", "Lcn/sast/framework/report/SqliteDiagnostics;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "outputDir", "Lcn/sast/common/IResDirectory;", "monitor", "Lcn/sast/framework/metrics/MetricsMonitor;", "corax-framework"})
    /* loaded from: ResultCollector$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return ResultCollector.logger;
        }

        @NotNull
        public final SqliteDiagnostics newSqliteDiagnostics(@NotNull final MainConfig mainConfig, @Nullable final SootInfoCache info, @NotNull IResDirectory outputDir, @Nullable final MetricsMonitor monitor) {
            Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
            Intrinsics.checkNotNullParameter(outputDir, "outputDir");
            final IResDirectory directory = outputDir.resolve(OutputType.SQLITE.getDisplayName()).toDirectory();
            return new SqliteDiagnostics(info, monitor, directory) { // from class: cn.sast.framework.result.ResultCollector$Companion$newSqliteDiagnostics$1
                {
                    super(this.$mainConfig, info, directory, monitor, null, 16, null);
                }

                @Override // cn.sast.framework.report.SqliteDiagnostics
                public Charset getSourceEncoding(IResFile $this$sourceEncoding) {
                    Intrinsics.checkNotNullParameter($this$sourceEncoding, "<this>");
                    return this.$mainConfig.getSourceEncoding();
                }
            };
        }
    }

    private static final Unit logger$lambda$21() {
        return Unit.INSTANCE;
    }
}
