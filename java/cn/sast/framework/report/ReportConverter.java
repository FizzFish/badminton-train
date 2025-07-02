package cn.sast.framework.report;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.ScanFilter;
import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.ProjectMetrics;
import cn.sast.api.report.Report;
import cn.sast.api.util.IMonitor;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.coverage.JacocoCompoundCoverage;
import cn.sast.idfa.progressbar.ProgressBarExt;
import com.feysh.corax.config.api.report.Region;
import com.feysh.corax.config.api.rules.ProcessRule;
import com.github.ajalt.mordant.rendering.Theme;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��b\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� #2\u00020\u0001:\u0001#B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eH\u0002J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010\u0012J>\u0010\u0013\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0011H\u0002JJ\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006$"}, d2 = {"Lcn/sast/framework/report/ReportConverter;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "progressBarExt", "Lcn/sast/idfa/progressbar/ProgressBarExt;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/idfa/progressbar/ProgressBarExt;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "filterSourceFiles", "", "Lcn/sast/common/IResFile;", "sources", "", "findAllSourceFiles", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportSourceFileWhichClassNotFound", "Lkotlin/Pair;", "", "allSourceFiles", "outputDir", "Lcn/sast/common/IResDirectory;", "flush", "", "coverage", "Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;", "consumers", "", "Lcn/sast/framework/report/IReportConsumer;", "reports", "Lcn/sast/api/report/Report;", "(Lcn/sast/api/config/MainConfig;Lcn/sast/framework/report/IProjectFileLocator;Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;Ljava/util/List;Ljava/util/Collection;Lcn/sast/common/IResDirectory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nReportConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,391:1\n774#2:392\n865#2,2:393\n774#2:395\n865#2,2:396\n1279#2,2:398\n1293#2,4:400\n1053#2:404\n*S KotlinDebug\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter\n*L\n53#1:392\n53#1:393,2\n83#1:395\n83#1:396,2\n84#1:398,2\n84#1:400,4\n96#1:404\n*E\n"})
/* loaded from: ReportConverter.class */
public final class ReportConverter {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final ProgressBarExt progressBarExt;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ReportConverter::logger$lambda$7);

    /* compiled from: ReportConverter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ReportConverter.kt", l = {69}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2"}, n = {"this", "locator", "allSourceFiles"}, m = "findAllSourceFiles", c = "cn.sast.framework.report.ReportConverter")
    /* renamed from: cn.sast.framework.report.ReportConverter$findAllSourceFiles$1, reason: invalid class name */
    /* loaded from: ReportConverter$findAllSourceFiles$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ReportConverter.this.findAllSourceFiles(null, (Continuation) this);
        }
    }

    public ReportConverter(@NotNull MainConfig mainConfig, @NotNull ProgressBarExt progressBarExt) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(progressBarExt, "progressBarExt");
        this.mainConfig = mainConfig;
        this.progressBarExt = progressBarExt;
    }

    public /* synthetic */ ReportConverter(MainConfig mainConfig, ProgressBarExt progressBarExt, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mainConfig, (i & 2) != 0 ? new ProgressBarExt(0, 0, 3, null) : progressBarExt);
    }

    public static final /* synthetic */ Object access$findAllSourceFiles(ReportConverter $this, IProjectFileLocator locator, Continuation $completion) {
        return $this.findAllSourceFiles(locator, $completion);
    }

    public static final /* synthetic */ Set access$filterSourceFiles(ReportConverter $this, Collection sources) {
        return $this.filterSourceFiles(sources);
    }

    public static final /* synthetic */ Pair access$reportSourceFileWhichClassNotFound(ReportConverter $this, Set allSourceFiles, IResDirectory outputDir, IProjectFileLocator locator) {
        return $this.reportSourceFileWhichClassNotFound(allSourceFiles, outputDir, locator);
    }

    public static final /* synthetic */ ProgressBarExt access$getProgressBarExt$p(ReportConverter $this) {
        return $this.progressBarExt;
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<IResFile> filterSourceFiles(Collection<? extends IResFile> collection) {
        boolean z;
        Collection<? extends IResFile> $this$filter$iv = collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            IResFile it = (IResFile) element$iv$iv;
            if (it == null) {
                z = false;
            } else {
                z = ((Intrinsics.areEqual(it.getExtension(), "kts") && StringsKt.contains(it.getName(), "gradle", true)) || StringsKt.contains(it.getName(), "package-info", true) || ScanFilter.getActionOf$default(this.mainConfig.getScanFilter(), (String) null, it.getPath(), (String) null, 4, (Object) null) == ProcessRule.ScanAction.Skip) ? false : true;
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (Set) CollectionsKt.filterNotNullTo((List) destination$iv$iv, new LinkedHashSet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object findAllSourceFiles(cn.sast.framework.report.IProjectFileLocator r7, kotlin.coroutines.Continuation<? super java.util.Set<? extends cn.sast.common.IResFile>> r8) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ReportConverter.findAllSourceFiles(cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean findAllSourceFiles$lambda$1(ReportConverter this$0, IResFile it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.mainConfig.getAutoAppSrcInZipScheme() || it.isFileScheme();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Set<IResFile>, Set<IResFile>> reportSourceFileWhichClassNotFound(Set<? extends IResFile> set, IResDirectory outputDir, IProjectFileLocator locator) throws IOException {
        IMonitor monitor = this.mainConfig.getMonitor();
        if (monitor != null) {
            ProjectMetrics projectMetrics = monitor.getProjectMetrics();
            if (projectMetrics != null) {
                projectMetrics.setTotalSourceFileNum(set.size());
            }
        }
        Collection applicationClasses = Scene.v().getApplicationClasses();
        Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
        Iterable libraryClasses = Scene.v().getLibraryClasses();
        Intrinsics.checkNotNullExpressionValue(libraryClasses, "getLibraryClasses(...)");
        Iterable $this$filter$iv = CollectionsKt.plus(applicationClasses, libraryClasses);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (!((SootClass) element$iv$iv).isPhantom()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable allNonPhantomClasses = (List) destination$iv$iv;
        Iterable $this$associateWith$iv = allNonPhantomClasses;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv2 : $this$associateWith$iv) {
            SootClass it = (SootClass) element$iv$iv2;
            Intrinsics.checkNotNull(it);
            result$iv.put(element$iv$iv2, locator.get(new ClassResInfo(it), NullWrapperFileGenerator.INSTANCE));
        }
        Map foundSourceCodes = result$iv;
        Set classFoundSourceFiles = (Set) CollectionsKt.filterNotNullTo(foundSourceCodes.values(), new LinkedHashSet());
        Set classNotFoundSourceFile = SetsKt.minus(set, classFoundSourceFiles);
        IResFile nfd = outputDir.resolve("source_files_which_class_not_found.txt").toFile();
        if (!classNotFoundSourceFile.isEmpty()) {
            logger.warn(() -> {
                return reportSourceFileWhichClassNotFound$lambda$4(r1, r2);
            });
            nfd.mkdirs();
            Path path = nfd.getPath();
            OpenOption[] openOptionArr = new OpenOption[0];
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), Charsets.UTF_8);
            Throwable th = null;
            try {
                try {
                    OutputStreamWriter writer = outputStreamWriter;
                    Set $this$sortedBy$iv = classNotFoundSourceFile;
                    for (IResFile file : CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.framework.report.ReportConverter$reportSourceFileWhichClassNotFound$lambda$6$$inlined$sortedBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            IResFile it2 = (IResFile) t;
                            IResFile it3 = (IResFile) t2;
                            return ComparisonsKt.compareValues(it2.toString(), it3.toString());
                        }
                    })) {
                        writer.write(file + "\n");
                    }
                    writer.flush();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStreamWriter, (Throwable) null);
                } finally {
                }
            } catch (Throwable th2) {
                CloseableKt.closeFinally(outputStreamWriter, th);
                throw th2;
            }
        } else {
            Files.deleteIfExists(nfd.getPath());
        }
        return TuplesKt.to(classFoundSourceFiles, classNotFoundSourceFile);
    }

    private static final Object reportSourceFileWhichClassNotFound$lambda$4(Set $classNotFoundSourceFile, IResFile $nfd) {
        return Theme.Companion.getDefault().getWarning().invoke("Incomplete analysis! The num of " + $classNotFoundSourceFile.size() + " source files not found any class!!! check: " + $nfd.getAbsolute().getNormalize());
    }

    /* compiled from: ReportConverter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "ReportConverter.kt", l = {119, 125, 217, 352, 362}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$13", "I$0", "I$1"}, n = {"$this$coroutineScope", "$this$coroutineScope", "allSourceFiles", "classFoundSourceFiles", "classNotFoundSourceFile", "$this$coroutineScope", "allSourceFiles", "classFoundSourceFiles", "classNotFoundSourceFile", "deCompileUnitMap", "locatorNew", "$this$coroutineScope", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "analysisMetadata", "progressBar", "worker", "alreadyLogged$iv", "$this$coroutineScope", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "analysisMetadata", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "consumer", "alreadyLogged$iv", "alreadyLogged$iv"}, m = "invokeSuspend", c = "cn.sast.framework.report.ReportConverter$flush$2")
    @SourceDebugExtension({"SMAP\nReportConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter$flush$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,391:1\n1863#2,2:392\n1863#2,2:394\n1498#2:396\n1528#2,3:397\n1531#2,3:407\n1557#2:410\n1628#2,3:411\n1863#2,2:440\n1557#2:464\n1628#2,3:465\n381#3,7:400\n49#4,13:414\n49#4,13:427\n62#4,11:442\n62#4,11:453\n*S KotlinDebug\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter$flush$2\n*L\n125#1:392,2\n217#1:394,2\n219#1:396\n219#1:397,3\n219#1:407,3\n290#1:410\n290#1:411,3\n359#1:440,2\n275#1:464\n275#1:465,3\n219#1:400,7\n290#1:414,13\n358#1:427,13\n358#1:442,11\n290#1:453,11\n*E\n"})
    /* renamed from: cn.sast.framework.report.ReportConverter$flush$2, reason: invalid class name */
    /* loaded from: ReportConverter$flush$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        int I$0;
        int I$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ IProjectFileLocator $locator;
        final /* synthetic */ IResDirectory $outputDir;
        final /* synthetic */ List<IReportConsumer> $consumers;
        final /* synthetic */ Collection<Report> $reports;
        final /* synthetic */ JacocoCompoundCoverage $coverage;
        final /* synthetic */ MainConfig $mainConfig;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(IProjectFileLocator $locator, IResDirectory $outputDir, List<? extends IReportConsumer> list, Collection<Report> collection, JacocoCompoundCoverage $coverage, MainConfig $mainConfig, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$locator = $locator;
            this.$outputDir = $outputDir;
            this.$consumers = list;
            this.$reports = collection;
            this.$coverage = $coverage;
            this.$mainConfig = $mainConfig;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = ReportConverter.this.new AnonymousClass2(this.$locator, this.$outputDir, this.$consumers, this.$reports, this.$coverage, this.$mainConfig, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: Types fix failed
            java.lang.NullPointerException
            */
        /* JADX WARN: Failed to apply debug info
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r24v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r24v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r24v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r25v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r25v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r25v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r34v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r34v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r34v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r36v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r41v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r41v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r42v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r42v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r45v1 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r45v2 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Failed to calculate best type for var: r47v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Not initialized variable reg: 24, insn: 0x092b: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r24 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:455), block:B:122:0x092b */
        /* JADX WARN: Not initialized variable reg: 24, insn: 0x095e: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r24 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:461), block:B:129:0x095e */
        /* JADX WARN: Not initialized variable reg: 24, insn: 0x0979: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r24 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:463), block:B:130:0x0979 */
        /* JADX WARN: Not initialized variable reg: 25, insn: 0x0936: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r25 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:122:0x092b */
        /* JADX WARN: Not initialized variable reg: 25, insn: 0x0969: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r25 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:129:0x095e */
        /* JADX WARN: Not initialized variable reg: 25, insn: 0x0984: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r25 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:130:0x0979 */
        /* JADX WARN: Not initialized variable reg: 34, insn: 0x0934: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r34 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:122:0x092b */
        /* JADX WARN: Not initialized variable reg: 34, insn: 0x0967: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r34 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:129:0x095e */
        /* JADX WARN: Not initialized variable reg: 34, insn: 0x0982: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r34 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:130:0x0979 */
        /* JADX WARN: Not initialized variable reg: 35, insn: 0x094b: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r35 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('alreadyLogged$iv' boolean)]) A[TRY_LEAVE], block:B:125:0x094b */
        /* JADX WARN: Not initialized variable reg: 36, insn: 0x0950: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r36 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('res$iv' kotlin.jvm.internal.Ref$ObjectRef)]) (LINE:460), block:B:127:0x0950 */
        /* JADX WARN: Not initialized variable reg: 41, insn: 0x0856: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r41 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:450), block:B:107:0x0856 */
        /* JADX WARN: Not initialized variable reg: 41, insn: 0x0871: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r41 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:452), block:B:108:0x0871 */
        /* JADX WARN: Not initialized variable reg: 42, insn: 0x0861: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r42 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:107:0x0856 */
        /* JADX WARN: Not initialized variable reg: 42, insn: 0x087c: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r42 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:108:0x0871 */
        /* JADX WARN: Not initialized variable reg: 45, insn: 0x085f: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r45 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:107:0x0856 */
        /* JADX WARN: Not initialized variable reg: 45, insn: 0x087a: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r45 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:108:0x0871 */
        /* JADX WARN: Not initialized variable reg: 46, insn: 0x0843: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r46 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('alreadyLogged$iv' boolean)]) A[TRY_LEAVE], block:B:103:0x0843 */
        /* JADX WARN: Not initialized variable reg: 47, insn: 0x0848: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r47 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('res$iv' kotlin.jvm.internal.Ref$ObjectRef)]) (LINE:449), block:B:105:0x0848 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x07b6 -> B:75:0x060d). Please report as a decompilation issue!!! */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instructions count: 2466
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ReportConverter.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final Object invokeSuspend$lambda$0() {
            return "report consumers is empty!";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final <T> void invokeSuspend$convertLineNumberMap(List<T> list, ConcurrentMap<IBugResInfo, Unit> concurrentMap, Function1<? super T, ? extends IBugResInfo> function1, Function2<? super T, ? super Unit, ? extends T> function2) {
            int i = 0;
            for (Object data : list) {
                int index = i;
                i++;
                IBugResInfo clazz = (IBugResInfo) function1.invoke(data);
                if (clazz != null && concurrentMap.get(clazz) != null) {
                    Unit deCompileUnit = Unit.INSTANCE;
                    list.set(index, function2.invoke(data, deCompileUnit));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Region invokeSuspend$toTextareaLine(Region $this$invokeSuspend_u24toTextareaLine, Unit deCompileUnit) {
            return $this$invokeSuspend_u24toTextareaLine;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$toTextareaLine$8(List<BugPathEvent> list, ConcurrentMap<IBugResInfo, Unit> concurrentMap) {
            invokeSuspend$convertLineNumberMap(list, concurrentMap, AnonymousClass2::invokeSuspend$toTextareaLine$8$lambda$5, AnonymousClass2::invokeSuspend$toTextareaLine$8$lambda$7);
        }

        private static final IBugResInfo invokeSuspend$toTextareaLine$8$lambda$5(BugPathEvent data) {
            return data.getClassname();
        }

        private static final BugPathEvent invokeSuspend$toTextareaLine$8$lambda$7(BugPathEvent data, Unit deCompileUnit) {
            Region it = invokeSuspend$toTextareaLine(data.getRegion(), deCompileUnit);
            return BugPathEvent.copy$default(data, null, null, it, null, 11, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0155 A[LOOP:0: B:22:0x014b->B:24:0x0155, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x01fa  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x021a  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0237  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static final java.lang.Object invokeSuspend$getAnalysisMetadata(cn.sast.framework.report.coverage.JacocoCompoundCoverage r10, java.util.Set<? extends cn.sast.common.IResFile> r11, cn.sast.api.config.MainConfig r12, cn.sast.common.IResDirectory r13, cn.sast.framework.report.IProjectFileLocator r14, java.util.Set<cn.sast.common.IResFile> r15, java.util.Set<? extends cn.sast.common.IResFile> r16, kotlin.coroutines.Continuation<? super cn.sast.framework.report.metadata.AnalysisMetadata> r17) throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 602
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ReportConverter.AnonymousClass2.invokeSuspend$getAnalysisMetadata(cn.sast.framework.report.coverage.JacocoCompoundCoverage, java.util.Set, cn.sast.api.config.MainConfig, cn.sast.common.IResDirectory, cn.sast.framework.report.IProjectFileLocator, java.util.Set, java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private static final Object invokeSuspend$getAnalysisMetadata$lambda$9() {
            return "An error occurred when get CoveredLineCounter";
        }

        private static final Deferred invokeSuspend$lambda$17$lambda$12(CoroutineScope $$this$coroutineScope, JacocoCompoundCoverage $coverage, Set $allSourceFiles, MainConfig $mainConfig, IResDirectory $outputDir, IProjectFileLocator $locator, Set $classFoundSourceFiles, Set $classNotFoundSourceFile) {
            return BuildersKt.async$default($$this$coroutineScope, (CoroutineContext) null, CoroutineStart.LAZY, new ReportConverter$flush$2$5$analysisMetadata$1$1($coverage, $allSourceFiles, $mainConfig, $outputDir, $locator, $classFoundSourceFiles, $classNotFoundSourceFile, null), 1, (Object) null);
        }

        private static final Object invokeSuspend$lambda$17$lambda$16$lambda$15$lambda$13(IReportConsumer $consumer) {
            return "OOM!. While visit analysisMetadata. Consumer: " + $consumer.getType();
        }

        private static final Object invokeSuspend$lambda$17$lambda$16$lambda$15$lambda$14(IReportConsumer $consumer) {
            return "Failed to visit analysisMetadata. Consumer: " + $consumer.getType();
        }
    }

    @Nullable
    public final Object flush(@NotNull MainConfig mainConfig, @NotNull IProjectFileLocator locator, @NotNull JacocoCompoundCoverage coverage, @NotNull List<? extends IReportConsumer> list, @NotNull Collection<Report> collection, @NotNull IResDirectory outputDir, @NotNull Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(locator, outputDir, list, collection, coverage, mainConfig, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* compiled from: ReportConverter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/framework/report/ReportConverter$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-framework"})
    /* loaded from: ReportConverter$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return ReportConverter.logger;
        }
    }

    private static final Unit logger$lambda$7() {
        return Unit.INSTANCE;
    }
}
