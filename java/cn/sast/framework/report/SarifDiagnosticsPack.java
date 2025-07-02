package cn.sast.framework.report;

import cn.sast.api.report.Report;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.JarMerger;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ReportConsumer;
import cn.sast.framework.report.SarifDiagnostics;
import cn.sast.framework.report.sarif.ArtifactLocation;
import cn.sast.framework.report.sarif.Description;
import cn.sast.framework.report.sarif.Run;
import cn.sast.framework.report.sarif.UriBase;
import cn.sast.framework.result.OutputType;
import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;

/* compiled from: SarifDiagnosticsPack.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018�� %2\u00020\u00012\u00020\u0002:\u0002$%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u001d\u001a\u00060\u001eR\u00020\u00012\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n��R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u001a¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsPack;", "Lcn/sast/framework/report/SarifDiagnostics;", "Ljava/io/Closeable;", "outputDir", "Lcn/sast/common/IResDirectory;", "sourceJarRootMapKey", "", "sourceJarRootMapValue", "sourceJarFileName", "type", "Lcn/sast/framework/result/OutputType;", "<init>", "(Lcn/sast/common/IResDirectory;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcn/sast/framework/result/OutputType;)V", "sourceJarPath", "Lcn/sast/common/IResFile;", "sourceJar", "Lcn/sast/common/JarMerger;", "init", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "originalUriBaseIds", "", "Lcn/sast/framework/report/sarif/UriBase;", "getOriginalUriBaseIds", "()Ljava/util/Map;", "entriesMap", "Ljava/util/concurrent/ConcurrentHashMap;", "getEntriesMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "getSarifDiagnosticsImpl", "Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "close", "SarifDiagnosticsPackImpl", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSarifDiagnosticsPack.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SarifDiagnosticsPack.kt\ncn/sast/framework/report/SarifDiagnosticsPack\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,92:1\n49#2,13:93\n62#2,11:108\n216#3,2:106\n*S KotlinDebug\n*F\n+ 1 SarifDiagnosticsPack.kt\ncn/sast/framework/report/SarifDiagnosticsPack\n*L\n71#1:93,13\n71#1:108,11\n72#1:106,2\n*E\n"})
/* loaded from: SarifDiagnosticsPack.class */
public final class SarifDiagnosticsPack extends SarifDiagnostics implements Closeable {

    @NotNull
    private final String sourceJarRootMapKey;

    @NotNull
    private final String sourceJarFileName;
    private IResFile sourceJarPath;
    private JarMerger sourceJar;

    @NotNull
    private final Map<String, UriBase> originalUriBaseIds;

    @NotNull
    private final ConcurrentHashMap<String, IResFile> entriesMap;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SarifDiagnosticsPack::logger$lambda$4);

    /* compiled from: SarifDiagnosticsPack.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SarifDiagnosticsPack.kt", l = {30}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "init", c = "cn.sast.framework.report.SarifDiagnosticsPack")
    /* renamed from: cn.sast.framework.report.SarifDiagnosticsPack$init$1, reason: invalid class name */
    /* loaded from: SarifDiagnosticsPack$init$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SarifDiagnosticsPack.this.init((Continuation) this);
        }
    }

    public /* synthetic */ SarifDiagnosticsPack(IResDirectory iResDirectory, String str, String str2, String str3, OutputType outputType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iResDirectory, (i & 2) != 0 ? "SRCROOT" : str, (i & 4) != 0 ? "%SRCROOT%" : str2, (i & 8) != 0 ? "src_root" : str3, (i & 16) != 0 ? OutputType.SarifPackSrc : outputType);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SarifDiagnosticsPack(@NotNull IResDirectory outputDir, @NotNull String sourceJarRootMapKey, @NotNull String sourceJarRootMapValue, @NotNull String sourceJarFileName, @NotNull OutputType type) {
        super(outputDir, type);
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        Intrinsics.checkNotNullParameter(sourceJarRootMapKey, "sourceJarRootMapKey");
        Intrinsics.checkNotNullParameter(sourceJarRootMapValue, "sourceJarRootMapValue");
        Intrinsics.checkNotNullParameter(sourceJarFileName, "sourceJarFileName");
        Intrinsics.checkNotNullParameter(type, "type");
        this.sourceJarRootMapKey = sourceJarRootMapKey;
        this.sourceJarFileName = sourceJarFileName;
        this.originalUriBaseIds = MapsKt.mapOf(TuplesKt.to(this.sourceJarRootMapKey, new UriBase(sourceJarRootMapValue, new Description("Should replace " + sourceJarRootMapValue + " with file:///{absolute-uncompressed-path-of-" + this.sourceJarFileName + ".jar}/" + this.sourceJarFileName + "/"))));
        this.entriesMap = new ConcurrentHashMap<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // cn.sast.framework.report.ReportConsumer, cn.sast.framework.report.IReportConsumer
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object init(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.io.IOException {
        /*
            r8 = this;
            r0 = r9
            boolean r0 = r0 instanceof cn.sast.framework.report.SarifDiagnosticsPack.AnonymousClass1
            if (r0 == 0) goto L24
            r0 = r9
            cn.sast.framework.report.SarifDiagnosticsPack$init$1 r0 = (cn.sast.framework.report.SarifDiagnosticsPack.AnonymousClass1) r0
            r11 = r0
            r0 = r11
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L24
            r0 = r11
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L2e
        L24:
            cn.sast.framework.report.SarifDiagnosticsPack$init$1 r0 = new cn.sast.framework.report.SarifDiagnosticsPack$init$1
            r1 = r0
            r2 = r8
            r3 = r9
            r1.<init>(r3)
            r11 = r0
        L2e:
            r0 = r11
            java.lang.Object r0 = r0.result
            r10 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r12 = r0
            r0 = r11
            int r0 = r0.label
            switch(r0) {
                case 0: goto L54;
                case 1: goto L70;
                default: goto Ld7;
            }
        L54:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            r1 = r11
            r2 = r11
            r3 = r8
            r2.L$0 = r3
            r2 = r11
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = super.init(r1)
            r1 = r0
            r2 = r12
            if (r1 != r2) goto L7d
            r1 = r12
            return r1
        L70:
            r0 = r11
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.report.SarifDiagnosticsPack r0 = (cn.sast.framework.report.SarifDiagnosticsPack) r0
            r8 = r0
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r10
        L7d:
            r0 = r8
            r1 = r8
            cn.sast.common.IResDirectory r1 = r1.getOutputDir()
            r2 = r8
            java.lang.String r2 = r2.sourceJarFileName
            java.lang.String r2 = r2 + ".jar"
            cn.sast.common.IResource r1 = r1.resolve(r2)
            cn.sast.common.IResFile r1 = r1.toFile()
            r0.sourceJarPath = r1
            r0 = r8
            cn.sast.common.IResFile r0 = r0.sourceJarPath
            r1 = r0
            if (r1 != 0) goto La8
        La2:
            java.lang.String r0 = "sourceJarPath"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = 0
        La8:
            java.nio.file.Path r0 = r0.getPath()
            boolean r0 = java.nio.file.Files.deleteIfExists(r0)
            r0 = r8
            cn.sast.common.JarMerger r1 = new cn.sast.common.JarMerger
            r2 = r1
            r3 = r8
            cn.sast.common.IResFile r3 = r3.sourceJarPath
            r4 = r3
            if (r4 != 0) goto Lc5
        Lbf:
            java.lang.String r3 = "sourceJarPath"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r3 = 0
        Lc5:
            java.nio.file.Path r3 = r3.getPath()
            r4 = 0
            r5 = 2
            r6 = 0
            r2.<init>(r3, r4, r5, r6)
            r0.sourceJar = r1
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ld7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SarifDiagnosticsPack.init(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Map<String, UriBase> getOriginalUriBaseIds() {
        return this.originalUriBaseIds;
    }

    @NotNull
    public final ConcurrentHashMap<String, IResFile> getEntriesMap() {
        return this.entriesMap;
    }

    /* compiled from: SarifDiagnosticsPack.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\b\u0096\u0004\u0018��2\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016R\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsPack$SarifDiagnosticsPackImpl;", "Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "Lcn/sast/framework/report/SarifDiagnostics;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "<init>", "(Lcn/sast/framework/report/SarifDiagnosticsPack;Lcn/sast/framework/report/ReportConsumer$MetaData;Lcn/sast/framework/report/IProjectFileLocator;)V", "file2uri", "", "Lcn/sast/common/IResFile;", "getFile2uri", "(Lcn/sast/common/IResFile;)Ljava/lang/String;", "getArtifactLocation", "Lcn/sast/framework/report/sarif/ArtifactLocation;", "file", "getRun", "Lcn/sast/framework/report/sarif/Run;", "reports", "", "Lcn/sast/api/report/Report;", "corax-framework"})
    /* loaded from: SarifDiagnosticsPack$SarifDiagnosticsPackImpl.class */
    public class SarifDiagnosticsPackImpl extends SarifDiagnostics.SarifDiagnosticsImpl {
        final /* synthetic */ SarifDiagnosticsPack this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SarifDiagnosticsPackImpl(@NotNull SarifDiagnosticsPack this$0, @NotNull ReportConsumer.MetaData metadata, IProjectFileLocator locator) {
            super(this$0, metadata, locator);
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            Intrinsics.checkNotNullParameter(locator, "locator");
            this.this$0 = this$0;
        }

        @Override // cn.sast.framework.report.SarifDiagnostics.SarifDiagnosticsImpl
        @NotNull
        public String getFile2uri(@NotNull IResFile $this$file2uri) {
            Intrinsics.checkNotNullParameter($this$file2uri, "<this>");
            String entry = getAbsPathMapToFolder($this$file2uri);
            this.this$0.getEntriesMap().putIfAbsent(entry, $this$file2uri);
            return entry;
        }

        @Override // cn.sast.framework.report.SarifDiagnostics.SarifDiagnosticsImpl
        @NotNull
        public ArtifactLocation getArtifactLocation(@NotNull IResFile file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return ArtifactLocation.copy$default(super.getArtifactLocation(file), null, this.this$0.sourceJarRootMapKey, 1, null);
        }

        @Override // cn.sast.framework.report.SarifDiagnostics.SarifDiagnosticsImpl
        @NotNull
        public Run getRun(@NotNull List<Report> list) {
            Intrinsics.checkNotNullParameter(list, "reports");
            return Run.copy$default(super.getRun(list), null, this.this$0.getOriginalUriBaseIds(), null, null, 13, null);
        }
    }

    @Override // cn.sast.framework.report.SarifDiagnostics
    @NotNull
    public SarifDiagnostics.SarifDiagnosticsImpl getSarifDiagnosticsImpl(@NotNull ReportConsumer.MetaData metadata, @NotNull IProjectFileLocator locator) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(locator, "locator");
        return new SarifDiagnosticsPackImpl(this, metadata, locator);
    }

    @Override // cn.sast.framework.report.SarifDiagnostics, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Ref.IntRef errorCnt = new Ref.IntRef();
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
        final String msg$iv = getType() + ": Compressing ...";
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsPack$close$$inlined$bracket$default$1
            public final Object invoke() {
                return "Started: " + msg$iv;
            }
        });
        final LocalDateTime startTime$iv = LocalDateTime.now();
        boolean alreadyLogged$iv = false;
        final Ref.ObjectRef res$iv = new Ref.ObjectRef();
        res$iv.element = Maybe.Companion.empty();
        try {
            try {
                Map $this$forEach$iv = this.entriesMap;
                for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                    String entry = element$iv.getKey();
                    IResFile file = element$iv.getValue();
                    try {
                        JarMerger jarMerger = this.sourceJar;
                        if (jarMerger == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("sourceJar");
                            jarMerger = null;
                        }
                        jarMerger.addFile(entry, file.getPath());
                    } catch (Exception e) {
                        errorCnt.element++;
                        if (errorCnt.element < 5) {
                            logger.warn(e, SarifDiagnosticsPack::close$lambda$2$lambda$1$lambda$0);
                        }
                    }
                }
                res$iv.element = new Maybe(Unit.INSTANCE);
                ((Maybe) res$iv.element).getOrThrow();
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsPack$close$$inlined$bracket$default$2
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsPack$close$$inlined$bracket$default$3
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
                if (errorCnt.element > 0) {
                    logger.warn(() -> {
                        return close$lambda$3(r1, r2);
                    });
                }
                JarMerger jarMerger2 = this.sourceJar;
                if (jarMerger2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sourceJar");
                    jarMerger2 = null;
                }
                jarMerger2.close();
            } catch (Throwable th) {
                if (!alreadyLogged$iv) {
                    if (((Maybe) res$iv.element).getHasValue()) {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsPack$close$$inlined$bracket$default$5
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                String str = msg$iv;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                            }
                        });
                    } else {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsPack$close$$inlined$bracket$default$6
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                            }
                        });
                    }
                }
                throw th;
            }
        } finally {
        }
    }

    private static final Object close$lambda$2$lambda$1$lambda$0() {
        return "An error occurred";
    }

    private static final Object close$lambda$3(SarifDiagnosticsPack this$0, Ref.IntRef $errorCnt) {
        return this$0.getType() + ": A total of " + $errorCnt.element + " errors were generated";
    }

    /* compiled from: SarifDiagnosticsPack.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsPack$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-framework"})
    /* loaded from: SarifDiagnosticsPack$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return SarifDiagnosticsPack.logger;
        }
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }
}
