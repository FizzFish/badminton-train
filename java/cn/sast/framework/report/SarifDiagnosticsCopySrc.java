package cn.sast.framework.report;

import cn.sast.api.report.Report;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
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
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;

/* compiled from: SarifDiagnosticsCopySrc.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018�� !2\u00020\u00012\u00020\u0002:\u0002 !B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001c\u0010\u0018\u001a\u00060\u0019R\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsCopySrc;", "Lcn/sast/framework/report/SarifDiagnostics;", "Ljava/io/Closeable;", "outputDir", "Lcn/sast/common/IResDirectory;", "sourceJarRootMapKey", "", "sourceJarRootMapValue", "sourceJarFileName", "type", "Lcn/sast/framework/result/OutputType;", "<init>", "(Lcn/sast/common/IResDirectory;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcn/sast/framework/result/OutputType;)V", "sourceRoot", "originalUriBaseIds", "", "Lcn/sast/framework/report/sarif/UriBase;", "getOriginalUriBaseIds", "()Ljava/util/Map;", "entriesMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcn/sast/common/IResFile;", "getEntriesMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "getSarifDiagnosticsImpl", "Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "close", "", "SarifDiagnosticsPackImpl", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSarifDiagnosticsCopySrc.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SarifDiagnosticsCopySrc.kt\ncn/sast/framework/report/SarifDiagnosticsCopySrc\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,85:1\n49#2,13:86\n62#2,11:101\n216#3,2:99\n*S KotlinDebug\n*F\n+ 1 SarifDiagnosticsCopySrc.kt\ncn/sast/framework/report/SarifDiagnosticsCopySrc\n*L\n62#1:86,13\n62#1:101,11\n63#1:99,2\n*E\n"})
/* loaded from: SarifDiagnosticsCopySrc.class */
public final class SarifDiagnosticsCopySrc extends SarifDiagnostics implements Closeable {

    @NotNull
    private final String sourceJarRootMapKey;

    @NotNull
    private final String sourceJarFileName;

    @NotNull
    private final IResDirectory sourceRoot;

    @NotNull
    private final Map<String, UriBase> originalUriBaseIds;

    @NotNull
    private final ConcurrentHashMap<String, IResFile> entriesMap;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SarifDiagnosticsCopySrc::logger$lambda$4);

    public /* synthetic */ SarifDiagnosticsCopySrc(IResDirectory iResDirectory, String str, String str2, String str3, OutputType outputType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iResDirectory, (i & 2) != 0 ? "SRCROOT" : str, (i & 4) != 0 ? "%SRCROOT%" : str2, (i & 8) != 0 ? "src_root" : str3, (i & 16) != 0 ? OutputType.SarifCopySrc : outputType);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SarifDiagnosticsCopySrc(@NotNull IResDirectory outputDir, @NotNull String sourceJarRootMapKey, @NotNull String sourceJarRootMapValue, @NotNull String sourceJarFileName, @NotNull OutputType type) {
        super(outputDir, type);
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        Intrinsics.checkNotNullParameter(sourceJarRootMapKey, "sourceJarRootMapKey");
        Intrinsics.checkNotNullParameter(sourceJarRootMapValue, "sourceJarRootMapValue");
        Intrinsics.checkNotNullParameter(sourceJarFileName, "sourceJarFileName");
        Intrinsics.checkNotNullParameter(type, "type");
        this.sourceJarRootMapKey = sourceJarRootMapKey;
        this.sourceJarFileName = sourceJarFileName;
        this.sourceRoot = outputDir.resolve(this.sourceJarFileName).toDirectory();
        this.originalUriBaseIds = MapsKt.mapOf(TuplesKt.to(this.sourceJarRootMapKey, new UriBase(sourceJarRootMapValue, new Description("The path " + sourceJarRootMapValue + " should be replaced with path where be mapped to the virtual path " + this.sourceRoot.getPath().toUri()))));
        this.entriesMap = new ConcurrentHashMap<>();
    }

    @NotNull
    public final Map<String, UriBase> getOriginalUriBaseIds() {
        return this.originalUriBaseIds;
    }

    @NotNull
    public final ConcurrentHashMap<String, IResFile> getEntriesMap() {
        return this.entriesMap;
    }

    /* compiled from: SarifDiagnosticsCopySrc.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\b\u0096\u0004\u0018��2\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016R\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsCopySrc$SarifDiagnosticsPackImpl;", "Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "Lcn/sast/framework/report/SarifDiagnostics;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "<init>", "(Lcn/sast/framework/report/SarifDiagnosticsCopySrc;Lcn/sast/framework/report/ReportConsumer$MetaData;Lcn/sast/framework/report/IProjectFileLocator;)V", "file2uri", "", "Lcn/sast/common/IResFile;", "getFile2uri", "(Lcn/sast/common/IResFile;)Ljava/lang/String;", "getArtifactLocation", "Lcn/sast/framework/report/sarif/ArtifactLocation;", "file", "getRun", "Lcn/sast/framework/report/sarif/Run;", "reports", "", "Lcn/sast/api/report/Report;", "corax-framework"})
    /* loaded from: SarifDiagnosticsCopySrc$SarifDiagnosticsPackImpl.class */
    public class SarifDiagnosticsPackImpl extends SarifDiagnostics.SarifDiagnosticsImpl {
        final /* synthetic */ SarifDiagnosticsCopySrc this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SarifDiagnosticsPackImpl(@NotNull SarifDiagnosticsCopySrc this$0, @NotNull ReportConsumer.MetaData metadata, IProjectFileLocator locator) {
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
    public void close() {
        Path parent;
        Ref.IntRef errorCnt = new Ref.IntRef();
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
        final String msg$iv = getType() + ": copying ...";
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$1
            public final Object invoke() {
                return "Started: " + msg$iv;
            }
        });
        final LocalDateTime startTime$iv = LocalDateTime.now();
        final Ref.ObjectRef res$iv = new Ref.ObjectRef();
        res$iv.element = Maybe.Companion.empty();
        try {
            try {
                Map $this$forEach$iv = this.entriesMap;
                for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                    String entry = element$iv.getKey();
                    IResFile file = element$iv.getValue();
                    Path target = this.sourceRoot.resolve(entry).getPath();
                    try {
                        parent = target.getParent();
                    } catch (Exception e) {
                        errorCnt.element++;
                        if (errorCnt.element < 5) {
                            logger.warn(e, SarifDiagnosticsCopySrc::close$lambda$2$lambda$1$lambda$0);
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    if (parent == null) {
                        throw new IllegalStateException(("output not allow here: " + target).toString());
                    }
                    LinkOption[] linkOptionArr = new LinkOption[0];
                    if (!Files.exists(parent, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                        Files.createDirectories(parent, new FileAttribute[0]);
                    }
                    Files.copy(file.getPath(), target, new CopyOption[0]);
                }
                res$iv.element = new Maybe(Unit.INSTANCE);
                ((Maybe) res$iv.element).getOrThrow();
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$2
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
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$3
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
            } catch (Throwable t$iv) {
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$4
                    public final Object invoke() {
                        LocalDateTime localDateTime = startTime$iv;
                        Intrinsics.checkNotNull(localDateTime);
                        String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                        String str = msg$iv;
                        Result.Companion companion = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(t$iv));
                        return "Finished (in " + strElapsedSecFrom + "): " + str + " :: EXCEPTION :: " + "";
                    }
                });
                throw t$iv;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$5
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
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.report.SarifDiagnosticsCopySrc$close$$inlined$bracket$default$6
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
    }

    private static final Object close$lambda$2$lambda$1$lambda$0() {
        return "An error occurred";
    }

    private static final Object close$lambda$3(SarifDiagnosticsCopySrc this$0, Ref.IntRef $errorCnt) {
        return this$0.getType() + ": A total of " + $errorCnt.element + " errors were generated";
    }

    /* compiled from: SarifDiagnosticsCopySrc.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/framework/report/SarifDiagnosticsCopySrc$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-framework"})
    /* loaded from: SarifDiagnosticsCopySrc$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return SarifDiagnosticsCopySrc.logger;
        }
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }
}
