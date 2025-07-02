package cn.sast.framework.report.metadata;

import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.OS;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jacoco.core.analysis.ICounter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MetadataGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018�� !2\u00020\u0001:\u0001!BY\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@¢\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u0018\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012X\u0082\u000e¢\u0006\u0002\n��¨\u0006\""}, d2 = {"Lcn/sast/framework/report/metadata/MetadataGenerator;", "", "projectRoot", "", "multipleProjectRoot", "", "outputPath", "Lcn/sast/common/IResDirectory;", "sourcePaths", "coveredCounter", "Lorg/jacoco/core/analysis/ICounter;", "successfulFiles", "", "Lcn/sast/common/IResFile;", "failedFiles", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcn/sast/common/IResDirectory;Ljava/util/List;Lorg/jacoco/core/analysis/ICounter;Ljava/util/Set;Ljava/util/Set;)V", "resultSourceFiles", "", "updateResultSourceMapping", "", "result", "source", "getMetadata", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateFailedMetaData", "generateMetaData", "fileCount", "", "lineCount", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nMetadataGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MetadataGenerator.kt\ncn/sast/framework/report/metadata/MetadataGenerator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,104:1\n1557#2:105\n1628#2,3:106\n1557#2:109\n1628#2,3:110\n1#3:113\n*S KotlinDebug\n*F\n+ 1 MetadataGenerator.kt\ncn/sast/framework/report/metadata/MetadataGenerator\n*L\n80#1:105\n80#1:106,3\n82#1:109\n82#1:110,3\n*E\n"})
/* loaded from: MetadataGenerator.class */
public final class MetadataGenerator {

    @Nullable
    private final String projectRoot;

    @NotNull
    private final List<String> multipleProjectRoot;

    @NotNull
    private final IResDirectory outputPath;

    @NotNull
    private final List<String> sourcePaths;

    @NotNull
    private final ICounter coveredCounter;

    @NotNull
    private final Set<IResFile> successfulFiles;

    @NotNull
    private final Set<IResFile> failedFiles;

    @NotNull
    private Map<String, String> resultSourceFiles;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(MetadataGenerator::logger$lambda$4);

    /* compiled from: MetadataGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "MetadataGenerator.kt", l = {53}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "fileCount", "lineCount", "task"}, m = "getMetadata", c = "cn.sast.framework.report.metadata.MetadataGenerator")
    /* renamed from: cn.sast.framework.report.metadata.MetadataGenerator$getMetadata$1, reason: invalid class name */
    /* loaded from: MetadataGenerator$getMetadata$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return MetadataGenerator.this.getMetadata(null, (Continuation) this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MetadataGenerator(@Nullable String projectRoot, @NotNull List<String> list, @NotNull IResDirectory outputPath, @NotNull List<String> list2, @NotNull ICounter coveredCounter, @NotNull Set<? extends IResFile> set, @NotNull Set<? extends IResFile> set2) {
        Intrinsics.checkNotNullParameter(list, "multipleProjectRoot");
        Intrinsics.checkNotNullParameter(outputPath, "outputPath");
        Intrinsics.checkNotNullParameter(list2, "sourcePaths");
        Intrinsics.checkNotNullParameter(coveredCounter, "coveredCounter");
        Intrinsics.checkNotNullParameter(set, "successfulFiles");
        Intrinsics.checkNotNullParameter(set2, "failedFiles");
        this.projectRoot = projectRoot;
        this.multipleProjectRoot = list;
        this.outputPath = outputPath;
        this.sourcePaths = list2;
        this.coveredCounter = coveredCounter;
        this.successfulFiles = set;
        this.failedFiles = set2;
        this.resultSourceFiles = new LinkedHashMap();
    }

    public final void updateResultSourceMapping(@NotNull String result, @NotNull String source) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(source, "source");
        this.resultSourceFiles.put(this.outputPath.resolve(result).getAbsolute().getNormalize().getPath().toString(), source);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getMetadata(@org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super cn.sast.framework.report.metadata.AnalysisMetadata> r14) throws java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.metadata.MetadataGenerator.getMetadata(cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object getMetadata$lambda$0() {
        return "Metadata: Java executable file path: " + OS.INSTANCE.getJavaExecutableFilePath();
    }

    @NotNull
    public final AnalysisMetadata generateFailedMetaData() {
        return generateMetaData(0, 0);
    }

    private final AnalysisMetadata generateMetaData(int fileCount, int lineCount) {
        Counter counter = new Counter(this.coveredCounter.getMissedCount(), this.coveredCounter.getCoveredCount());
        List listSorted = CollectionsKt.sorted(this.sourcePaths);
        String property = System.getProperty("os.name");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        String lowerCase = property.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        int size = this.failedFiles.size();
        Iterable $this$map$iv = this.failedFiles;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(Resource.INSTANCE.getOriginFileFromExpandPath((IResFile) item$iv$iv).getPathString());
        }
        List listSorted2 = CollectionsKt.sorted((List) destination$iv$iv);
        int size2 = this.successfulFiles.size();
        Iterable $this$map$iv2 = this.successfulFiles;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            destination$iv$iv2.add(Resource.INSTANCE.getOriginFileFromExpandPath((IResFile) item$iv$iv2).getPathString());
        }
        ArrayList arrayList = (List) destination$iv$iv2;
        int i = fileCount;
        int i2 = lineCount;
        Counter counter2 = counter;
        int i3 = 1;
        List list = listSorted;
        String str = lowerCase;
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to("corax", new Analyzer(new AnalyzerStatistics(size, listSorted2, size2, CollectionsKt.sorted(arrayList), ""), MapsKt.emptyMap())));
        List commandLine$default = OS.getCommandLine$default(OS.INSTANCE, null, false, 3, null);
        if (commandLine$default == null) {
            String it = System.getProperty("java.home");
            if (it != null) {
                i = i;
                i2 = i2;
                counter2 = counter2;
                i3 = 1;
                list = list;
                str = str;
                mapMapOf = mapMapOf;
                commandLine$default = CollectionsKt.listOf(it);
            } else {
                commandLine$default = null;
            }
            if (commandLine$default == null) {
                commandLine$default = CollectionsKt.emptyList();
            }
        }
        String string = this.outputPath.getAbsolute().getNormalize().getPath().toString();
        String str2 = this.projectRoot;
        if (str2 == null) {
            str2 = "";
        }
        List<String> list2 = this.multipleProjectRoot;
        SortedMap sortedMap = MapsKt.toSortedMap(this.resultSourceFiles);
        String property2 = System.getProperty("user.dir");
        Intrinsics.checkNotNullExpressionValue(property2, "getProperty(...)");
        return new AnalysisMetadata(i, i2, counter2, i3, list, str, CollectionsKt.listOf(new Tool(mapMapOf, commandLine$default, "corax", string, str2, list2, sortedMap, property2)));
    }

    /* compiled from: MetadataGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/metadata/MetadataGenerator$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: MetadataGenerator$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }
}
