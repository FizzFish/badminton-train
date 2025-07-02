package cn.sast.framework.util;

import cn.sast.common.IResFile;
import cn.sast.common.JarMerger;
import cn.sast.common.Resource;
import cn.sast.common.ResourceImplKt;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.googlecode.d2j.dex.Dex2jar;
import com.googlecode.d2j.dex.DexExceptionHandler;
import com.googlecode.d2j.reader.BaseDexFileReader;
import com.googlecode.d2j.reader.MultiDexFileReader;
import com.googlecode.dex2jar.tools.BaksmaliBaseDexExceptionHandler;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.optionals.OptionalsKt;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;
import soot.ClassSource;
import soot.DexClassSource;
import soot.FoundFile;
import soot.IFoundFile;
import soot.ModuleUtil;
import soot.SourceLocator;
import soot.asm.AsmClassSource;

/* compiled from: SootUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jz\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\tJ\u001c\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0007\u001a\u00020\u0005J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020!R \u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00160\u0015X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\""}, d2 = {"Lcn/sast/framework/util/SootUtils;", "", "<init>", "()V", "dex2jar", "Ljava/nio/file/Path;", "dexSource", "output", "notHandleException", "", "reuseReg", "debugInfo", "optmizeSynchronized", "printIR", "noCode", "skipExceptions", "dontSanitizeNames", "computeFrames", "topoLogicalSort", "", "sootClass2classFileCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Ljava/util/Optional;", "lookupInArchive", "Lcn/sast/common/IResFile;", "archivePath", "fileName", "", "getClassSourceFromSoot", "className", "logger", "Lmu/KLogger;", "cleanUp", "", "corax-framework"})
@SourceDebugExtension({"SMAP\nSootUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootUtils.kt\ncn/sast/framework/util/SootUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReportConverter.kt\ncn/sast/framework/report/ReportConverterKt\n+ 4 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,158:1\n1#2:159\n38#3,3:160\n38#3,3:163\n38#3,3:166\n38#3,3:169\n38#3,3:172\n49#4,24:175\n*S KotlinDebug\n*F\n+ 1 SootUtils.kt\ncn/sast/framework/util/SootUtils\n*L\n122#1:160,3\n123#1:163,3\n124#1:166,3\n125#1:169,3\n141#1:172,3\n41#1:175,24\n*E\n"})
/* loaded from: SootUtils.class */
public final class SootUtils {

    @NotNull
    public static final SootUtils INSTANCE = new SootUtils();

    @NotNull
    private static final LoadingCache<Path, Optional<Path>> sootClass2classFileCache;

    @NotNull
    private static final KLogger logger;

    private SootUtils() {
    }

    public static /* synthetic */ Path dex2jar$default(SootUtils sootUtils, Path path, Path path2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = true;
        }
        if ((i & 32) != 0) {
            z4 = true;
        }
        if ((i & 64) != 0) {
            z5 = false;
        }
        if ((i & 128) != 0) {
            z6 = false;
        }
        if ((i & 256) != 0) {
            z7 = false;
        }
        if ((i & 512) != 0) {
            z8 = false;
        }
        if ((i & 1024) != 0) {
            z9 = true;
        }
        if ((i & 2048) != 0) {
            z10 = true;
        }
        return sootUtils.dex2jar(path, path2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10);
    }

    @NotNull
    public final Path dex2jar(@NotNull Path dexSource, @NotNull Path output, boolean notHandleException, boolean reuseReg, boolean debugInfo, boolean optmizeSynchronized, boolean printIR, boolean noCode, boolean skipExceptions, boolean dontSanitizeNames, boolean computeFrames, boolean topoLogicalSort) throws Exception {
        Intrinsics.checkNotNullParameter(dexSource, "dexSource");
        Intrinsics.checkNotNullParameter(output, "output");
        ResourceKt.findCacheFromDeskOrCreate(dexSource, output, () -> {
            return dex2jar$lambda$2(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        });
        return output;
    }

    private static final Unit dex2jar$lambda$2(Path $dexSource, Path $output, boolean $notHandleException, boolean $reuseReg, boolean $debugInfo, boolean $optmizeSynchronized, boolean $printIR, boolean $noCode, boolean $skipExceptions, boolean $dontSanitizeNames, boolean $computeFrames, boolean $topoLogicalSort) {
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(logger);
        final String msg$iv = "dex2jar " + $dexSource.toString() + " -> " + $output;
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.util.SootUtils$dex2jar$lambda$2$$inlined$bracket$default$1
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
                BaseDexFileReader reader = MultiDexFileReader.open(Files.readAllBytes($dexSource));
                Intrinsics.checkNotNullExpressionValue(reader, "open(...)");
                BaksmaliBaseDexExceptionHandler handler = $notHandleException ? null : new BaksmaliBaseDexExceptionHandler();
                Dex2jar it = Dex2jar.from(reader).withExceptionHandler((DexExceptionHandler) handler).reUseReg($reuseReg);
                if ($topoLogicalSort) {
                    it.topoLogicalSort();
                }
                it.skipDebug(!$debugInfo).optimizeSynchronized($optmizeSynchronized).printIR($printIR).noCode($noCode).skipExceptions($skipExceptions).dontSanitizeNames($dontSanitizeNames).computeFrames($computeFrames).to($output);
                res$iv.element = new Maybe(Unit.INSTANCE);
                ((Maybe) res$iv.element).getOrThrow();
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.util.SootUtils$dex2jar$lambda$2$$inlined$bracket$default$2
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
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.util.SootUtils$dex2jar$lambda$2$$inlined$bracket$default$3
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
                return Unit.INSTANCE;
            } finally {
            }
        } catch (Throwable th) {
            if (!alreadyLogged$iv) {
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.util.SootUtils$dex2jar$lambda$2$$inlined$bracket$default$5
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
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.util.SootUtils$dex2jar$lambda$2$$inlined$bracket$default$6
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

    @NotNull
    public final Path dex2jar(@NotNull Set<? extends Path> set, @NotNull Path output) throws Exception {
        JarMerger jarMerger;
        Intrinsics.checkNotNullParameter(set, "dexSource");
        Intrinsics.checkNotNullParameter(output, "output");
        if (set.size() == 1) {
            OpenOption[] openOptionArr = new OpenOption[0];
            InputStream inputStreamNewInputStream = Files.newInputStream((Path) CollectionsKt.first(set), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            jarMerger = inputStreamNewInputStream;
            Throwable th = null;
            try {
                try {
                    InputStream it = jarMerger;
                    Dex2jar.from(it).to(output);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(jarMerger, (Throwable) null);
                } finally {
                }
            } finally {
            }
        } else {
            jarMerger = new JarMerger(output, null, 2, null);
            Throwable th2 = null;
            try {
                try {
                    JarMerger merger = jarMerger;
                    for (Path dex : set) {
                        Path part = Files.createTempFile(ResourceImplKt.getSAstTempDirectory(), PathsKt.getName(dex), ".jar", new FileAttribute[0]);
                        SootUtils sootUtils = INSTANCE;
                        Intrinsics.checkNotNull(part);
                        Path jar = dex2jar$default(sootUtils, dex, part, false, false, false, false, false, false, false, true, false, false, 3580, null);
                        JarMerger.addJar$default(merger, jar, null, null, 6, null);
                        Files.deleteIfExists(part);
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(jarMerger, (Throwable) null);
                } finally {
                }
            } finally {
            }
        }
        return output;
    }

    static {
        Caffeine caffeineNewBuilder = Caffeine.newBuilder();
        final Function1 function1 = SootUtils::sootClass2classFileCache$lambda$6;
        LoadingCache<Path, Optional<Path>> loadingCacheBuild = caffeineNewBuilder.build(new CacheLoader(function1) { // from class: cn.sast.framework.util.SootUtils$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function1, "function");
                this.function = function1;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild, "build(...)");
        sootClass2classFileCache = loadingCacheBuild;
        logger = KotlinLogging.INSTANCE.logger(SootUtils::logger$lambda$7);
    }

    private static final Optional sootClass2classFileCache$lambda$6(Path dexSource) {
        try {
            Path pathResolve = Resource.INSTANCE.getZipExtractOutputDir().resolve("dex2jar");
            Intrinsics.checkNotNull(dexSource);
            Path output = pathResolve.resolve(StringsKt.substringBeforeLast$default(PathsKt.getName(dexSource), ".", (String) null, 2, (Object) null) + "-" + Math.abs(dexSource.toString().hashCode() + 1) + ".jar");
            SootUtils sootUtils = INSTANCE;
            Intrinsics.checkNotNull(output);
            Path r = dex2jar$default(sootUtils, dexSource, output, false, false, false, false, false, false, false, false, false, false, 3580, null);
            return Optional.of(r);
        } catch (Exception e) {
            logger.warn(e, () -> {
                return sootClass2classFileCache$lambda$6$lambda$5(r2);
            });
            return Optional.ofNullable(null);
        }
    }

    private static final Object sootClass2classFileCache$lambda$6$lambda$5(Path $dexSource) {
        return "failed to convert dex: " + $dexSource + " to jar file.";
    }

    private final IResFile lookupInArchive(IResFile archivePath, String fileName) {
        Set entryNames = archivePath.getEntries();
        if (entryNames.contains(fileName)) {
            return Resource.INSTANCE.fileOf(Resource.INSTANCE.archivePath(archivePath.getPath(), fileName));
        }
        return null;
    }

    @Nullable
    public final IResFile getClassSourceFromSoot(@NotNull String className) {
        IResFile iResFileLookupInArchive;
        Intrinsics.checkNotNullParameter(className, "className");
        ClassSource clazz = SourceLocator.v().getClassSource(className);
        if (clazz == null) {
            return null;
        }
        if (!(clazz instanceof AsmClassSource)) {
            if (clazz instanceof DexClassSource) {
                if (ModuleUtil.module_mode()) {
                    iResFileLookupInArchive = null;
                } else {
                    String clsFile = StringsKt.replace$default(className, '.', '/', false, 4, (Object) null) + ".class";
                    Field it$iv = clazz.getClass().getDeclaredField("path");
                    it$iv.setAccessible(true);
                    Object obj = it$iv.get(clazz);
                    if (!(obj instanceof File)) {
                        obj = null;
                    }
                    File dex = (File) obj;
                    if (dex == null) {
                        return null;
                    }
                    LoadingCache<Path, Optional<Path>> loadingCache = sootClass2classFileCache;
                    Path path = dex.toPath();
                    Intrinsics.checkNotNullExpressionValue(path, "toPath(...)");
                    Path absolutePath = path.toAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
                    Object obj2 = loadingCache.get(absolutePath.normalize());
                    Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                    Path jar = (Path) OptionalsKt.getOrNull((Optional) obj2);
                    if (jar == null) {
                        return null;
                    }
                    iResFileLookupInArchive = lookupInArchive(Resource.INSTANCE.fileOf(jar), clsFile);
                }
            } else {
                return null;
            }
        } else {
            Field it$iv2 = clazz.getClass().getDeclaredField("foundFile");
            it$iv2.setAccessible(true);
            Object obj3 = it$iv2.get(clazz);
            if (!(obj3 instanceof IFoundFile)) {
                obj3 = null;
            }
            FoundFile foundFile = (IFoundFile) obj3;
            FoundFile foundFile2 = foundFile instanceof FoundFile ? foundFile : null;
            if (foundFile2 == null) {
                return null;
            }
            FoundFile foundFile3 = foundFile2;
            Field it$iv3 = foundFile3.getClass().getDeclaredField("path");
            it$iv3.setAccessible(true);
            Object obj4 = it$iv3.get(foundFile3);
            if (!(obj4 instanceof Path)) {
                obj4 = null;
            }
            Path path2 = (Path) obj4;
            Field it$iv4 = foundFile3.getClass().getDeclaredField("file");
            it$iv4.setAccessible(true);
            Object obj5 = it$iv4.get(foundFile3);
            if (!(obj5 instanceof File)) {
                obj5 = null;
            }
            File zipFile = (File) obj5;
            Field it$iv5 = foundFile3.getClass().getDeclaredField("entryName");
            it$iv5.setAccessible(true);
            Object obj6 = it$iv5.get(foundFile3);
            if (!(obj6 instanceof String)) {
                obj6 = null;
            }
            String entryName = (String) obj6;
            if (path2 != null) {
                iResFileLookupInArchive = Resource.INSTANCE.of(path2).toFile();
            } else if (zipFile != null && entryName != null) {
                Resource resource = Resource.INSTANCE;
                Path path3 = zipFile.toPath();
                Intrinsics.checkNotNullExpressionValue(path3, "toPath(...)");
                iResFileLookupInArchive = resource.fileOf(path3).resolve(entryName).toFile();
            } else {
                return null;
            }
        }
        IResFile clazzPath = iResFileLookupInArchive;
        return clazzPath;
    }

    private static final Unit logger$lambda$7() {
        return Unit.INSTANCE;
    }

    public final void cleanUp() {
        sootClass2classFileCache.cleanUp();
    }
}
