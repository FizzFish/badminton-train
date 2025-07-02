package cn.sast.common;

import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.path.PathsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.FileUtilKt;
import soot.util.SharedCloseable;

/* compiled from: ResourceImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\t\bÆ\u0002\u0018��2\u00020\u0001:\u0004_`abB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\"2\u0006\u0010$\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020'J\u000e\u0010+\u001a\u00020,2\u0006\u0010.\u001a\u00020/J\u000e\u0010+\u001a\u00020,2\u0006\u0010$\u001a\u00020\u0007J\u000e\u00100\u001a\u0004\u0018\u00010'*\u00020'H\u0002J\u0012\u00104\u001a\u00020/2\n\u00105\u001a\u0006\u0012\u0002\b\u000306J\u0012\u00107\u001a\u00020,2\n\u00105\u001a\u0006\u0012\u0002\b\u000306J\u0014\u00108\u001a\u00020\u0007*\u00020\u00072\u0006\u00109\u001a\u00020\u0005H\u0002J\b\u0010<\u001a\u00020;H\u0002J\b\u0010=\u001a\u00020;H\u0002J\u000e\u0010B\u001a\u00020@2\u0006\u0010$\u001a\u00020\u0007J\u0016\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0005J\u000e\u0010+\u001a\u00020,2\u0006\u0010$\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0007J\u000e\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0005J\u000e\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0007J\u001a\u0010K\u001a\b\u0012\u0004\u0012\u00020,0\"2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013J\u000e\u0010P\u001a\u00020,2\u0006\u0010Q\u001a\u00020,J\u000e\u0010R\u001a\u00020,2\u0006\u0010S\u001a\u00020,J\u0010\u0010T\u001a\u00020(2\u0006\u0010Q\u001a\u00020,H\u0002J\u0012\u0010U\u001a\u0004\u0018\u00010,2\u0006\u0010V\u001a\u00020,H\u0002J\u0012\u0010W\u001a\u0004\u0018\u00010\u00072\u0006\u0010X\u001a\u00020,H\u0002J\u0006\u0010Y\u001a\u00020ZJ\u001e\u0010[\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u00072\u0006\u0010]\u001a\u00020\u00052\u0006\u0010^\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\rX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n��R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n��\u001a\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\"0!X\u0082\u0004¢\u0006\u0002\n��R)\u0010%\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&\u0012\u0004\u0012\u00020\u00070!¢\u0006\b\n��\u001a\u0004\b)\u0010*R\u001a\u00101\u001a\u0004\u0018\u00010\u0005*\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n��RP\u0010>\u001aB\u0012\f\u0012\n ?*\u0004\u0018\u00010\u00070\u0007\u0012\f\u0012\n ?*\u0004\u0018\u00010@0@ ?* \u0012\f\u0012\n ?*\u0004\u0018\u00010\u00070\u0007\u0012\f\u0012\n ?*\u0004\u0018\u00010@0@\u0018\u00010!0!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010ARP\u0010M\u001aB\u0012\f\u0012\n ?*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n ?*\u0004\u0018\u00010\u00010\u0001 ?* \u0012\f\u0012\n ?*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n ?*\u0004\u0018\u00010\u00010\u0001\u0018\u00010N0NX\u0082\u0004¢\u0006\u0004\n\u0002\u0010O¨\u0006c"}, d2 = {"Lcn/sast/common/Resource;", "", "<init>", "()V", "EXTRACT_DIR_NAME", "", "zipExtractOutputDir", "Ljava/nio/file/Path;", "getZipExtractOutputDir", "()Ljava/nio/file/Path;", "setZipExtractOutputDir", "(Ljava/nio/file/Path;)V", "newFileSystemEnv", "", "getNewFileSystemEnv", "()Ljava/util/Map;", "setNewFileSystemEnv", "(Ljava/util/Map;)V", "fileSystemEncodings", "", "getFileSystemEncodings", "()Ljava/util/List;", "setFileSystemEncodings", "(Ljava/util/List;)V", "logger", "Lmu/KLogger;", "PATH_CACHE_CAPACITY", "", "archivePathToZip", "Lcn/sast/common/SharedZipFileCacheWrapper;", "getArchivePathToZip", "()Lcn/sast/common/SharedZipFileCacheWrapper;", "archivePathToEntriesCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "", "getEntriesUnsafe", "path", "uriToPathCached", "Lkotlin/Pair;", "Ljava/net/URI;", "", "getUriToPathCached", "()Lcom/github/benmanes/caffeine/cache/LoadingCache;", "of", "Lcn/sast/common/IResource;", "uri", "url", "Ljava/net/URL;", "extractJarName", "entry", "getEntry", "(Ljava/net/URI;)Ljava/lang/String;", "locateClass", "clazz", "Ljava/lang/Class;", "locateAllClass", "extract", "scheme", "zipFsProvider", "Ljava/nio/file/spi/FileSystemProvider;", "getZipFsProvider", "findZipFsProvider", "archiveSystemCache", "kotlin.jvm.PlatformType", "Ljava/nio/file/FileSystem;", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "getZipFileSystem", "archivePath", "archive", "dirOf", "Lcn/sast/common/IResDirectory;", "directory", "fileOf", "Lcn/sast/common/IResFile;", "file", "multipleOf", "paths", "jarCacheBuilder", "Lcom/github/benmanes/caffeine/cache/Caffeine;", "Lcom/github/benmanes/caffeine/cache/Caffeine;", "getOriginFileFromExpandPath", "p", "getOriginFileFromExpandAbsPath", "absolute", "isUnderExtractDir", "getOriginFileFromExpandAbsolutePath", "abs", "findFileMappingInZip", "nFile", "clean", "", "extractZipToFolder", "archiveFile", "entryPrefix", "destFolder", "ExpandResKey", "ResourceBasic", "ResFileImpl", "ResDirectoryImpl", "corax-api"})
@SourceDebugExtension({"SMAP\nResourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource\n+ 2 Resource.kt\ncn/sast/common/ResourceKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,730:1\n36#2:731\n36#2:734\n36#2:736\n1#3:732\n1#3:733\n1#3:735\n1#3:737\n1#3:747\n1368#4:738\n1454#4,5:739\n1628#4,3:744\n*S KotlinDebug\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource\n*L\n152#1:731\n197#1:734\n212#1:736\n152#1:732\n197#1:735\n212#1:737\n301#1:738\n301#1:739,5\n302#1:744,3\n*E\n"})
/* loaded from: Resource.class */
public final class Resource {

    @NotNull
    public static final String EXTRACT_DIR_NAME = "archive-extract";

    @NotNull
    private static final LoadingCache<Path, Set<String>> archivePathToEntriesCache;

    @NotNull
    private static final LoadingCache<Pair<URI, Boolean>, Path> uriToPathCached;

    @Nullable
    private static FileSystemProvider zipFsProvider;
    private static final LoadingCache<Path, FileSystem> archiveSystemCache;
    private static final Caffeine<Object, Object> jarCacheBuilder;

    @NotNull
    public static final Resource INSTANCE = new Resource();

    @NotNull
    private static Path zipExtractOutputDir = ResourceImplKt.getSAstTempDirectory();

    @NotNull
    private static Map<String, ? extends Object> newFileSystemEnv = MapsKt.mapOf(new Pair[]{TuplesKt.to("create", "true"), TuplesKt.to("zipinfo-time", "false")});

    @NotNull
    private static List<String> fileSystemEncodings = CollectionsKt.listOf(new String[]{"UTF-8", "GBK"});

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(Resource::logger$lambda$0);
    private static final int PATH_CACHE_CAPACITY = 50000;

    @NotNull
    private static final SharedZipFileCacheWrapper archivePathToZip = new SharedZipFileCacheWrapper(5, PATH_CACHE_CAPACITY);

    private Resource() {
    }

    @NotNull
    public final Path getZipExtractOutputDir() {
        return zipExtractOutputDir;
    }

    public final void setZipExtractOutputDir(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<set-?>");
        zipExtractOutputDir = path;
    }

    static {
        LoadingCache<Path, Set<String>> loadingCacheBuild = Caffeine.newBuilder().expireAfterAccess(Duration.ofSeconds(12L)).build(new CacheLoader<Path, Set<? extends String>>() { // from class: cn.sast.common.Resource$archivePathToEntriesCache$1
            public Set<String> load(Path archivePath) throws Exception {
                String name;
                Intrinsics.checkNotNullParameter(archivePath, "archivePath");
                SharedCloseable sharedCloseable = (AutoCloseable) Resource.INSTANCE.getArchivePathToZip().getRef(archivePath);
                try {
                    SharedCloseable archive = sharedCloseable;
                    Set ret = new HashSet();
                    Enumeration it = archive.get().getEntries();
                    Intrinsics.checkNotNullExpressionValue(it, "getEntries(...)");
                    while (it.hasMoreElements()) {
                        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) it.nextElement();
                        if (zipArchiveEntry != null && (name = zipArchiveEntry.getName()) != null) {
                            if (!StringsKt.contains$default(name, "..", false, 2, (Object) null)) {
                                ret.add(name);
                            }
                        }
                    }
                    Set<String> setUnmodifiableSet = Collections.unmodifiableSet(ret);
                    Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(...)");
                    AutoCloseableKt.closeFinally(sharedCloseable, (Throwable) null);
                    return setUnmodifiableSet;
                } catch (Throwable th) {
                    AutoCloseableKt.closeFinally(sharedCloseable, (Throwable) null);
                    throw th;
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild, "build(...)");
        archivePathToEntriesCache = loadingCacheBuild;
        Caffeine caffeineSoftValues = Caffeine.newBuilder().initialCapacity(5).maximumSize(5000L).softValues();
        final Function1 function1 = Resource::uriToPathCached$lambda$1;
        LoadingCache<Pair<URI, Boolean>, Path> loadingCacheBuild2 = caffeineSoftValues.build(new CacheLoader(function1) { // from class: cn.sast.common.ResourceImplKt$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function1, "function");
                this.function = function1;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild2, "build(...)");
        uriToPathCached = loadingCacheBuild2;
        archiveSystemCache = Caffeine.newBuilder().weakValues().build(new CacheLoader() { // from class: cn.sast.common.Resource$archiveSystemCache$1
            public final FileSystem load(Path archive) throws URISyntaxException {
                Intrinsics.checkNotNull(archive);
                String extension = PathsKt.getExtension(archive);
                if ((extension.length() == 0) || !ResourceImplKt.getZipExtensions().contains(extension)) {
                    throw new URISyntaxException("Illegal extension in " + archive, extension);
                }
                return ResourceImplKt.createFileSystem(archive);
            }
        });
        jarCacheBuilder = Caffeine.newBuilder().initialCapacity(4);
    }

    @NotNull
    public final Map<String, Object> getNewFileSystemEnv() {
        return newFileSystemEnv;
    }

    public final void setNewFileSystemEnv(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        newFileSystemEnv = map;
    }

    @NotNull
    public final List<String> getFileSystemEncodings() {
        return fileSystemEncodings;
    }

    public final void setFileSystemEncodings(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        fileSystemEncodings = list;
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final SharedZipFileCacheWrapper getArchivePathToZip() {
        return archivePathToZip;
    }

    @NotNull
    public final Set<String> getEntriesUnsafe(@NotNull Path path) throws Exception {
        Intrinsics.checkNotNullParameter(path, "path");
        LoadingCache<Path, Set<String>> loadingCache = archivePathToEntriesCache;
        Path absolutePath = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
        Object obj = loadingCache.get(absolutePath.normalize());
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (Set) obj;
    }

    @NotNull
    public final LoadingCache<Pair<URI, Boolean>, Path> getUriToPathCached() {
        return uriToPathCached;
    }

    private static final Path uriToPathCached$lambda$1(Pair args) {
        URI uri = (URI) args.component1();
        boolean getRootFsPath = ((Boolean) args.component2()).booleanValue();
        return ResourceKt.uriToPath(uri, getRootFsPath);
    }

    @NotNull
    public final IResource of(@NotNull URI uri) {
        ResourceBasic resourceBasic;
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            Path path = Paths.get(uri);
            Intrinsics.checkNotNullExpressionValue(path, "get(...)");
            return new ResourceBasic(path);
        }
        String it$iv = uri.getScheme();
        if (Intrinsics.areEqual(it$iv, "jar") || StringsKt.equals(it$iv, "zip", true)) {
            String spec = uri.getSchemeSpecificPart();
            Intrinsics.checkNotNull(spec);
            int sep = StringsKt.lastIndexOf$default(spec, "!/", 0, false, 6, (Object) null);
            if (sep == -1) {
                throw new IllegalArgumentException("URI: " + uri + " does not contain path info ex. jar:file:/c:/foo.zip!/BAR");
            }
            String strSubstring = spec.substring(0, sep);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            Path path2 = Paths.get(ResourceKt.uriOf(strSubstring));
            Intrinsics.checkNotNullExpressionValue(path2, "get(...)");
            FileSystem system = getZipFileSystem(path2);
            String strSubstring2 = spec.substring(sep + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            Path path3 = system.getPath(strSubstring2, new String[0]);
            Intrinsics.checkNotNull(path3);
            resourceBasic = new ResourceBasic(path3);
        } else {
            try {
                FileSystems.getFileSystem(uri);
            } catch (FileSystemNotFoundException e) {
                ResourceImplKt.createFileSystem(uri);
            }
            Path path4 = Paths.get(uri);
            Intrinsics.checkNotNullExpressionValue(path4, "get(...)");
            resourceBasic = new ResourceBasic(path4);
        }
        return resourceBasic;
    }

    @NotNull
    public final IResource of(@NotNull URL url) throws URISyntaxException {
        Intrinsics.checkNotNullParameter(url, "url");
        URI uri = url.toURI();
        Intrinsics.checkNotNullExpressionValue(uri, "toURI(...)");
        return of(uri);
    }

    @NotNull
    public final IResource of(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new ResourceBasic(path);
    }

    private final URI extractJarName(URI $this$extractJarName) {
        String it = $this$extractJarName.getSchemeSpecificPart();
        Intrinsics.checkNotNull(it);
        int index = StringsKt.lastIndexOf$default(it, "!/", 0, false, 6, (Object) null);
        if (index == -1) {
            return null;
        }
        String strSubstring = it.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return ResourceKt.uriOf(strSubstring);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getEntry(URI $this$entry) {
        String it = $this$entry.getSchemeSpecificPart();
        Intrinsics.checkNotNull(it);
        int index = StringsKt.lastIndexOf$default(it, "!/", 0, false, 6, (Object) null);
        if (index == -1) {
            return null;
        }
        String strSubstring = it.substring(index + 2, it.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public final URL locateClass(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        String path = FileUtilKt.toClassFilePath(cls);
        URL resource = cls.getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException(("No such file: " + path).toString());
        }
        return resource;
    }

    @NotNull
    public final IResource locateAllClass(@NotNull Class<?> cls) throws URISyntaxException {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        String path = FileUtilKt.toClassFilePath(cls);
        URL resource = locateClass(cls);
        URI $this$isJarScheme$iv = resource.toURI();
        Intrinsics.checkNotNullExpressionValue($this$isJarScheme$iv, "toURI(...)");
        String it$iv = $this$isJarScheme$iv.getScheme();
        if (!(Intrinsics.areEqual(it$iv, "jar") || StringsKt.equals(it$iv, "zip", true))) {
            String fullPath = StringsKt.removeSuffix(StringsKt.replace$default(StringsKt.replace$default(of(resource).getAbsolute().getNormalize().toString(), "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null), path);
            return of(fullPath);
        }
        URI uri = resource.toURI();
        Intrinsics.checkNotNullExpressionValue(uri, "toURI(...)");
        URI jarLocation = extractJarName(uri);
        if (jarLocation == null) {
            throw new IllegalStateException(("internal error: resource=" + resource).toString());
        }
        return of(jarLocation);
    }

    private final Path extract(Path $this$extract, String scheme) {
        String schemeFinal = Intrinsics.areEqual(scheme, "zip") ? "jar" : scheme;
        if (Intrinsics.areEqual(schemeFinal, "jar")) {
            URI $this$isJarScheme$iv = $this$extract.toUri();
            Intrinsics.checkNotNullExpressionValue($this$isJarScheme$iv, "toUri(...)");
            String it$iv = $this$isJarScheme$iv.getScheme();
            if (Intrinsics.areEqual(it$iv, "jar") || StringsKt.equals(it$iv, "zip", true)) {
                return extract(of($this$extract).expandRes(dirOf(zipExtractOutputDir)).getPath(), scheme);
            }
        }
        return $this$extract;
    }

    private final FileSystemProvider getZipFsProvider() {
        FileSystemProvider result = zipFsProvider;
        if (result == null) {
            result = findZipFsProvider();
            zipFsProvider = result;
        }
        return result;
    }

    private final FileSystemProvider findZipFsProvider() {
        for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
            if (Intrinsics.areEqual(provider.getScheme(), "jar")) {
                Intrinsics.checkNotNull(provider);
                return provider;
            }
            continue;
        }
        throw new ProviderNotFoundException("Provider not found");
    }

    @NotNull
    public final FileSystem getZipFileSystem(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        LoadingCache<Path, FileSystem> loadingCache = archiveSystemCache;
        Path absolutePath = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
        Object obj = loadingCache.get(absolutePath.normalize());
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (FileSystem) obj;
    }

    @NotNull
    public final Path archivePath(@NotNull Path archive, @NotNull String entry) {
        Path path;
        Intrinsics.checkNotNullParameter(archive, "archive");
        Intrinsics.checkNotNullParameter(entry, "entry");
        int index = StringsKt.indexOf$default(entry, "!", 0, false, 6, (Object) null);
        if (index != -1) {
            Path first = archivePath(archive, StringsKt.substringBefore$default(entry, "!", (String) null, 2, (Object) null));
            String end = entry.substring(index + 1);
            Intrinsics.checkNotNullExpressionValue(end, "substring(...)");
            return archivePath(first, end);
        }
        if (StringsKt.startsWith$default(entry, "/", false, 2, (Object) null)) {
            path = getZipFileSystem(archive).getPath(entry, new String[0]);
        } else {
            path = getZipFileSystem(archive).getPath("/" + entry, new String[0]);
        }
        Path path2 = path;
        Intrinsics.checkNotNull(path2);
        return path2;
    }

    @NotNull
    public final IResource of(@NotNull String path) {
        Path pathArchivePath;
        Intrinsics.checkNotNullParameter(path, "path");
        int index = StringsKt.indexOf$default(path, "!", 0, false, 6, (Object) null);
        if (index == -1) {
            pathArchivePath = Paths.get(path, new String[0]);
        } else {
            String archive = path.substring(0, index);
            Intrinsics.checkNotNullExpressionValue(archive, "substring(...)");
            String entry = path.substring(index + 1);
            Intrinsics.checkNotNullExpressionValue(entry, "substring(...)");
            Path path2 = Paths.get(archive, new String[0]);
            Intrinsics.checkNotNullExpressionValue(path2, "get(...)");
            pathArchivePath = archivePath(path2, entry);
        }
        Path p = pathArchivePath;
        Intrinsics.checkNotNull(p);
        return new ResourceBasic(p);
    }

    @NotNull
    public final IResDirectory dirOf(@NotNull String directory) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        return of(directory).toDirectory();
    }

    @NotNull
    public final IResDirectory dirOf(@NotNull Path directory) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        return of(directory).toDirectory();
    }

    @NotNull
    public final IResFile fileOf(@NotNull String file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return of(file).toFile();
    }

    @NotNull
    public final IResFile fileOf(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return of(file).toFile();
    }

    @NotNull
    public final Set<IResource> multipleOf(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "paths");
        List<String> $this$flatMap$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            String it = (String) element$iv$iv;
            String str = File.pathSeparator;
            Intrinsics.checkNotNullExpressionValue(str, "pathSeparator");
            Iterable list$iv$iv = StringsKt.contains$default(it, str, false, 2, (Object) null) ? StringsKt.split$default(it, new String[]{File.pathSeparator}, false, 0, 6, (Object) null) : CollectionsKt.listOf(it);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable flat = (List) destination$iv$iv;
        Iterable $this$mapTo$iv = flat;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            destination$iv.add(INSTANCE.of((String) item$iv));
        }
        return (Set) destination$iv;
    }

    /* compiled from: ResourceImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018�� \u001c2\u00020\u0001:\u0001\u001cB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u0010\u001a\u00020\u0007H��¢\u0006\u0002\b\u0011J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcn/sast/common/Resource$ExpandResKey;", "", "jarPath", "Ljava/nio/file/Path;", "entry", "", "outPut", "Lcn/sast/common/IResDirectory;", "<init>", "(Ljava/nio/file/Path;Ljava/lang/String;Lcn/sast/common/IResDirectory;)V", "getJarPath", "()Ljava/nio/file/Path;", "getEntry", "()Ljava/lang/String;", "getOutPut", "()Lcn/sast/common/IResDirectory;", "getTemplate", "getTemplate$corax_api", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "corax-api"})
    /* loaded from: Resource$ExpandResKey.class */
    public static final class ExpandResKey {

        @NotNull
        private final Path jarPath;

        @NotNull
        private final String entry;

        @NotNull
        private final IResDirectory outPut;

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private static final ConcurrentHashMap<IResDirectory, IResource> mappingUnzipDirToZip = new ConcurrentHashMap<>();
        private static final LoadingCache<ExpandResKey, IResDirectory> jarTempCache = Resource.jarCacheBuilder.build(new CacheLoader() { // from class: cn.sast.common.Resource$ExpandResKey$Companion$jarTempCache$1
            public final IResDirectory load(Resource.ExpandResKey key) {
                String name = key.getJarPath().getFileName() + "-" + Math.abs(key.getJarPath().hashCode());
                IResDirectory it = key.getOutPut().resolve(Resource.EXTRACT_DIR_NAME).resolve(name).toDirectory();
                it.mkdirs();
                IResource j = Resource.INSTANCE.of(key.getJarPath());
                ConcurrentHashMap<IResDirectory, IResource> mappingUnzipDirToZip$corax_api = Resource.ExpandResKey.Companion.getMappingUnzipDirToZip$corax_api();
                IResource originFileFromExpandAbsolutePath = Resource.INSTANCE.getOriginFileFromExpandAbsolutePath(j);
                if (originFileFromExpandAbsolutePath == null) {
                    originFileFromExpandAbsolutePath = j;
                }
                mappingUnzipDirToZip$corax_api.put(it, originFileFromExpandAbsolutePath);
                return it;
            }
        });

        @NotNull
        public final Path component1() {
            return this.jarPath;
        }

        @NotNull
        public final String component2() {
            return this.entry;
        }

        @NotNull
        public final IResDirectory component3() {
            return this.outPut;
        }

        @NotNull
        public final ExpandResKey copy(@NotNull Path jarPath, @NotNull String entry, @NotNull IResDirectory outPut) {
            Intrinsics.checkNotNullParameter(jarPath, "jarPath");
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            return new ExpandResKey(jarPath, entry, outPut);
        }

        public static /* synthetic */ ExpandResKey copy$default(ExpandResKey expandResKey, Path path, String str, IResDirectory iResDirectory, int i, Object obj) {
            if ((i & 1) != 0) {
                path = expandResKey.jarPath;
            }
            if ((i & 2) != 0) {
                str = expandResKey.entry;
            }
            if ((i & 4) != 0) {
                iResDirectory = expandResKey.outPut;
            }
            return expandResKey.copy(path, str, iResDirectory);
        }

        @NotNull
        public String toString() {
            return "ExpandResKey(jarPath=" + this.jarPath + ", entry=" + this.entry + ", outPut=" + this.outPut + ")";
        }

        public int hashCode() {
            int result = this.jarPath.hashCode();
            return (((result * 31) + this.entry.hashCode()) * 31) + this.outPut.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExpandResKey)) {
                return false;
            }
            ExpandResKey expandResKey = (ExpandResKey) other;
            return Intrinsics.areEqual(this.jarPath, expandResKey.jarPath) && Intrinsics.areEqual(this.entry, expandResKey.entry) && Intrinsics.areEqual(this.outPut, expandResKey.outPut);
        }

        public ExpandResKey(@NotNull Path jarPath, @NotNull String entry, @NotNull IResDirectory outPut) {
            Intrinsics.checkNotNullParameter(jarPath, "jarPath");
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            this.jarPath = jarPath;
            this.entry = entry;
            this.outPut = outPut;
        }

        @NotNull
        public final Path getJarPath() {
            return this.jarPath;
        }

        @NotNull
        public final String getEntry() {
            return this.entry;
        }

        @NotNull
        public final IResDirectory getOutPut() {
            return this.outPut;
        }

        @NotNull
        public final IResDirectory getTemplate$corax_api() {
            Object obj = jarTempCache.get(this);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (IResDirectory) obj;
        }

        /* compiled from: ResourceImpl.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tRV\u0010\n\u001aB\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00060\u0006 \r* \u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u000b0\u000bX\u0080\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcn/sast/common/Resource$ExpandResKey$Companion;", "", "<init>", "()V", "mappingUnzipDirToZip", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcn/sast/common/IResDirectory;", "Lcn/sast/common/IResource;", "getMappingUnzipDirToZip$corax_api", "()Ljava/util/concurrent/ConcurrentHashMap;", "jarTempCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lcn/sast/common/Resource$ExpandResKey;", "kotlin.jvm.PlatformType", "getJarTempCache$corax_api", "()Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "corax-api"})
        /* loaded from: Resource$ExpandResKey$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            public final ConcurrentHashMap<IResDirectory, IResource> getMappingUnzipDirToZip$corax_api() {
                return ExpandResKey.mappingUnzipDirToZip;
            }

            public final LoadingCache<ExpandResKey, IResDirectory> getJarTempCache$corax_api() {
                return ExpandResKey.jarTempCache;
            }
        }
    }

    @NotNull
    public final IResource getOriginFileFromExpandPath(@NotNull IResource p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return getOriginFileFromExpandAbsPath(p.getAbsolute().getNormalize());
    }

    @NotNull
    public final IResource getOriginFileFromExpandAbsPath(@NotNull IResource absolute) {
        Intrinsics.checkNotNullParameter(absolute, "absolute");
        IResource originFileFromExpandAbsolutePath = INSTANCE.getOriginFileFromExpandAbsolutePath(absolute);
        return originFileFromExpandAbsolutePath == null ? absolute : originFileFromExpandAbsolutePath;
    }

    private final boolean isUnderExtractDir(IResource p) {
        return p.isFileScheme() && StringsKt.contains$default(p.toString(), EXTRACT_DIR_NAME, false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IResource getOriginFileFromExpandAbsolutePath(IResource abs) {
        if (isUnderExtractDir(abs)) {
            Path found = findFileMappingInZip(abs);
            if (found != null) {
                return of(found);
            }
            return null;
        }
        String entry = abs.getZipEntry();
        if (entry != null) {
            IResource schemePath = of(abs.getSchemePath());
            if (isUnderExtractDir(schemePath)) {
                IResource originFileFromExpandAbsolutePath = getOriginFileFromExpandAbsolutePath(schemePath);
                if (originFileFromExpandAbsolutePath != null) {
                    return originFileFromExpandAbsolutePath.resolve(entry);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:11:0x0070
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    private final java.nio.file.Path findFileMappingInZip(cn.sast.common.IResource r5) {
        /*
            r4 = this;
            r0 = r5
            boolean r0 = r0.isFileScheme()
            if (r0 != 0) goto L19
            java.lang.String r0 = "Check failed."
            r7 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r7
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L19:
            r0 = r5
            java.nio.file.Path r0 = r0.getPath()
            r6 = r0
            cn.sast.common.Resource$ExpandResKey$Companion r0 = cn.sast.common.Resource.ExpandResKey.Companion
            java.util.concurrent.ConcurrentHashMap r0 = r0.getMappingUnzipDirToZip$corax_api()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            r7 = r0
        L34:
            r0 = r7
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lb2
            r0 = r7
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r8 = r0
            r0 = r8
            java.lang.Object r0 = r0.getKey()
            cn.sast.common.IResDirectory r0 = (cn.sast.common.IResDirectory) r0
            r9 = r0
            r0 = r8
            java.lang.Object r0 = r0.getValue()
            cn.sast.common.IResource r0 = (cn.sast.common.IResource) r0
            r10 = r0
            r0 = r6
            r1 = r9
            java.nio.file.Path r1 = r1.getPath()
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L34
        L71:
            r0 = r6
            r1 = r9
            java.nio.file.Path r1 = r1.getPath()     // Catch: java.lang.Exception -> L9d
            int r1 = r1.getNameCount()     // Catch: java.lang.Exception -> L9d
            r2 = r6
            int r2 = r2.getNameCount()     // Catch: java.lang.Exception -> L9d
            java.nio.file.Path r0 = r0.subpath(r1, r2)     // Catch: java.lang.Exception -> L9d
            r11 = r0
            r0 = r10
            r1 = r11
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L9d
            cn.sast.common.IResource r0 = r0.resolve(r1)     // Catch: java.lang.Exception -> L9d
            java.nio.file.Path r0 = r0.getPath()     // Catch: java.lang.Exception -> L9d
            return r0
        L9d:
            r11 = move-exception
            mu.KLogger r0 = cn.sast.common.Resource.logger
            r1 = r5
            r2 = r11
            java.nio.file.Path r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return findFileMappingInZip$lambda$8(r1, r2);
            }
            r0.warn(r1)
            goto L34
        Lb2:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.Resource.findFileMappingInZip(cn.sast.common.IResource):java.nio.file.Path");
    }

    private static final Object findFileMappingInZip$lambda$8(IResource $nFile, Exception $e) {
        return "failed to get findFileMappingInZip of " + $nFile + ". e: " + $e.getMessage();
    }

    public final void clean() {
        ResourceBasic.Companion.getJarInnerResourceCache$corax_api().cleanUp();
        ExpandResKey.Companion.getJarTempCache$corax_api().cleanUp();
        ExpandResKey.Companion.getMappingUnzipDirToZip$corax_api().clear();
        archiveSystemCache.cleanUp();
    }

    @NotNull
    public final Path extractZipToFolder(@NotNull Path archiveFile, @NotNull String entryPrefix, @NotNull Path destFolder) throws IOException {
        Intrinsics.checkNotNullParameter(archiveFile, "archiveFile");
        Intrinsics.checkNotNullParameter(entryPrefix, "entryPrefix");
        Intrinsics.checkNotNullParameter(destFolder, "destFolder");
        String entryPrefixFixed = StringsKt.removePrefix(entryPrefix, "/");
        int errNo = 0;
        SharedCloseable sharedCloseable = (AutoCloseable) archivePathToZip.getRef(archiveFile);
        try {
            SharedCloseable archiveRef = sharedCloseable;
            ZipFile archive = archiveRef.get();
            Enumeration entries = archive.getEntries();
            Intrinsics.checkNotNull(entries);
            Iterator it = CollectionsKt.iterator(entries);
            while (it.hasNext()) {
                ZipArchiveEntry entry = (ZipArchiveEntry) it.next();
                String name = entry.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                String entryName = StringsKt.removePrefix(name, "/");
                if (StringsKt.startsWith$default(entryName, entryPrefixFixed, false, 2, (Object) null)) {
                    if (StringsKt.indexOf$default(entryName, "..", 0, false, 6, (Object) null) != -1) {
                        logger.warn(() -> {
                            return extractZipToFolder$lambda$14$lambda$9(r1, r2);
                        });
                    } else {
                        try {
                            Path destFile = destFolder.resolve(entryName);
                            if (entry.isDirectory()) {
                                Files.createDirectories(destFile, new FileAttribute[0]);
                            } else {
                                Path it2 = destFile.getParent();
                                if (it2 != null) {
                                    LinkOption[] linkOptionArr = new LinkOption[0];
                                    if (!Files.exists(it2, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                                        Files.createDirectories(it2, new FileAttribute[0]);
                                    }
                                }
                                InputStream inputStream = archive.getInputStream(entry);
                                Throwable th = null;
                                try {
                                    try {
                                        InputStream it3 = inputStream;
                                        Intrinsics.checkNotNull(it3);
                                        Files.write(destFile, ByteStreamsKt.readBytes(it3), new OpenOption[0]);
                                        Set p = OS.INSTANCE.getPosixFilePermissions();
                                        Path posixFilePermissions = p != null ? Files.setPosixFilePermissions(destFile, p) : null;
                                        CloseableKt.closeFinally(inputStream, (Throwable) null);
                                    } finally {
                                    }
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(inputStream, th);
                                    throw th2;
                                }
                            }
                        } catch (InvalidPathException e) {
                            int i = errNo;
                            errNo = i + 1;
                            if (i < 3) {
                                logger.error(e, () -> {
                                    return extractZipToFolder$lambda$14$lambda$10(r2, r3);
                                });
                            }
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(sharedCloseable, (Throwable) null);
            Path pathResolve = destFolder.resolve(entryPrefix);
            Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
            return pathResolve;
        } catch (Throwable th3) {
            AutoCloseableKt.closeFinally(sharedCloseable, (Throwable) null);
            throw th3;
        }
    }

    private static final Object extractZipToFolder$lambda$14$lambda$9(Path $archiveFile, String $entryName) {
        return "skipping bad zip entry: `" + $archiveFile + "!/" + $entryName + "`. (zip slip)";
    }

    private static final Object extractZipToFolder$lambda$14$lambda$10(String $entryName, Path $destFolder) {
        return "Illegal entry name [" + $entryName + "] in " + $destFolder;
    }

    /* compiled from: ResourceImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\b\u0016\u0018�� M2\u00020\u0001:\u0001MB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u0002022\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000204H\u0016J\u0011\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0001H\u0096\u0002J\u0010\u00108\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0014H\u0016J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u000202H\u0016J\b\u0010E\u001a\u00020\u0014H\u0016J\u000e\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00030IH\u0016J\u0013\u0010J\u001a\u00020\u00182\b\u00107\u001a\u0004\u0018\u00010KH\u0096\u0002J\b\u0010L\u001a\u000206H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0016R\u0014\u0010!\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001aR\u0014\u0010\"\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001aR\u0014\u0010#\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001aR\u0014\u0010$\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0007R\u0014\u0010&\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0016R\u001d\u0010(\u001a\u0004\u0018\u00010\u00018VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\r\u001a\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u0010<\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\u0016R\u0014\u0010>\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010*R\u0014\u0010@\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010*R\u0010\u0010B\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010C\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0004\n\u0002\u0010DR\u0014\u0010F\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0016¨\u0006N"}, d2 = {"Lcn/sast/common/Resource$ResourceBasic;", "Lcn/sast/common/IResource;", "path", "Ljava/nio/file/Path;", "<init>", "(Ljava/nio/file/Path;)V", "getPath", "()Ljava/nio/file/Path;", "uri", "Ljava/net/URI;", "getUri", "()Ljava/net/URI;", "uri$delegate", "Lkotlin/Lazy;", "url", "Ljava/net/URL;", "getUrl", "()Ljava/net/URL;", "url$delegate", "zipEntry", "", "getZipEntry", "()Ljava/lang/String;", "exists", "", "getExists", "()Z", "isFile", "isFile$delegate", "isDirectory", "isRegularFile", "extension", "getExtension", "isFileScheme", "isJrtScheme", "isJarScheme", "schemePath", "getSchemePath", "name", "getName", "parent", "getParent", "()Lcn/sast/common/IResource;", "parent$delegate", "file", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "expandRes", "outPut", "Lcn/sast/common/IResDirectory;", "mkdirs", "", "compareTo", "", "other", "resolve", "toFile", "Lcn/sast/common/IResFile;", "toDirectory", "absolutePath", "getAbsolutePath", "absolute", "getAbsolute", "normalize", "getNormalize", "_string", "_hash", "Ljava/lang/Integer;", "toString", "pathString", "getPathString", "seq", "Lkotlin/sequences/Sequence;", "equals", "", "hashCode", "Companion", "corax-api"})
    @SourceDebugExtension({"SMAP\nResourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource$ResourceBasic\n+ 2 Resource.kt\ncn/sast/common/ResourceKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,730:1\n37#2:731\n38#2:732\n36#2:733\n37#2,2:736\n1#3:734\n1#3:735\n*S KotlinDebug\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource$ResourceBasic\n*L\n426#1:731\n427#1:732\n428#1:733\n515#1:736,2\n428#1:734\n*E\n"})
    /* loaded from: Resource$ResourceBasic.class */
    public static class ResourceBasic implements IResource {

        @NotNull
        private final Path path;

        @NotNull
        private final Lazy uri$delegate;

        @NotNull
        private final Lazy url$delegate;

        @NotNull
        private final Lazy isFile$delegate;

        @NotNull
        private final Lazy parent$delegate;

        @Nullable
        private String _string;

        @Nullable
        private Integer _hash;

        @NotNull
        public static final Companion Companion = new Companion(null);
        private static final LoadingCache<ExpandResKey, IResource> jarInnerResourceCache = Resource.jarCacheBuilder.build(new CacheLoader() { // from class: cn.sast.common.Resource$ResourceBasic$Companion$jarInnerResourceCache$1
            public final IResource load(Resource.ExpandResKey resInJar) throws IOException {
                try {
                    Path path = Resource.INSTANCE.extractZipToFolder(resInJar.getJarPath(), resInJar.getEntry(), resInJar.getTemplate$corax_api().getPath());
                    return Resource.INSTANCE.of(path);
                } catch (IOException e) {
                    ExceptionsKt.checkCritical(e);
                    throw e;
                }
            }
        });

        public ResourceBasic(@NotNull Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            this.path = path;
            this.uri$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
                return uri_delegate$lambda$0(r2);
            });
            this.url$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
                return url_delegate$lambda$1(r2);
            });
            this.isFile$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
                return isFile_delegate$lambda$2(r2);
            });
            this.parent$delegate = LazyKt.lazy(() -> {
                return parent_delegate$lambda$4(r1);
            });
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public final Path getPath() {
            return this.path;
        }

        @Override // cn.sast.common.IResource
        public boolean getZipLike() {
            return IResource.DefaultImpls.getZipLike(this);
        }

        @Override // cn.sast.common.IResource
        public void deleteDirectoryRecursively() throws IOException {
            IResource.DefaultImpls.deleteDirectoryRecursively(this);
        }

        @Override // cn.sast.common.IResource
        public void deleteDirectoryContents() throws IOException {
            IResource.DefaultImpls.deleteDirectoryContents(this);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public URI getUri() {
            Object value = this.uri$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (URI) value;
        }

        private static final URI uri_delegate$lambda$0(ResourceBasic this$0) {
            return this$0.path.toUri();
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public URL getUrl() {
            Object value = this.url$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (URL) value;
        }

        private static final URL url_delegate$lambda$1(ResourceBasic this$0) {
            return this$0.getUri().toURL();
        }

        @Override // cn.sast.common.IResource
        @Nullable
        public String getZipEntry() {
            if (isJarScheme()) {
                return Resource.INSTANCE.getEntry(getUri());
            }
            return null;
        }

        @Override // cn.sast.common.IResource
        public boolean getExists() {
            return Files.exists(this.path, new LinkOption[0]);
        }

        @Override // cn.sast.common.IResource
        public boolean isFile() {
            return ((Boolean) this.isFile$delegate.getValue()).booleanValue();
        }

        private static final boolean isFile_delegate$lambda$2(ResourceBasic this$0) {
            return Files.isRegularFile(this$0.path, new LinkOption[0]);
        }

        @Override // cn.sast.common.IResource
        public boolean isDirectory() {
            return Files.isDirectory(this.path, new LinkOption[0]);
        }

        @Override // cn.sast.common.IResource
        public boolean isRegularFile() {
            return Files.isRegularFile(this.path, new LinkOption[0]);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public String getExtension() {
            return PathsKt.getExtension(this.path);
        }

        @Override // cn.sast.common.IResource
        public boolean isFileScheme() {
            URI $this$isFileScheme$iv = getUri();
            return Intrinsics.areEqual($this$isFileScheme$iv.getScheme(), "file");
        }

        @Override // cn.sast.common.IResource
        public boolean isJrtScheme() {
            URI $this$isJrtScheme$iv = getUri();
            return Intrinsics.areEqual($this$isJrtScheme$iv.getScheme(), "jrt");
        }

        @Override // cn.sast.common.IResource
        public boolean isJarScheme() {
            URI $this$isJarScheme$iv = getUri();
            String it$iv = $this$isJarScheme$iv.getScheme();
            return Intrinsics.areEqual(it$iv, "jar") || StringsKt.equals(it$iv, "zip", true);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public Path getSchemePath() {
            if (isFileScheme() || isJrtScheme()) {
                return this.path;
            }
            Object obj = Resource.INSTANCE.getUriToPathCached().get(TuplesKt.to(getUri(), true));
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (Path) obj;
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public String getName() {
            return PathsKt.getName(this.path);
        }

        @Override // cn.sast.common.IResource
        @Nullable
        public IResource getParent() {
            return (IResource) this.parent$delegate.getValue();
        }

        private static final IResource parent_delegate$lambda$4(ResourceBasic this$0) {
            Path parent = this$0.path.getParent();
            if (parent == null && this$0.isJarScheme()) {
                return Resource.INSTANCE.of(Resource.INSTANCE.archivePath(this$0.getSchemePath(), "/"));
            }
            if (parent != null) {
                return Resource.INSTANCE.of(parent);
            }
            return null;
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public File getFile() {
            File file = this.path.toFile();
            Intrinsics.checkNotNullExpressionValue(file, "toFile(...)");
            return file;
        }

        @NotNull
        public final IResource expandRes(@NotNull IResDirectory outPut, @NotNull String zipEntry) {
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            Intrinsics.checkNotNullParameter(zipEntry, "zipEntry");
            Path absolutePath = getSchemePath().toAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
            Path pathNormalize = absolutePath.normalize();
            Intrinsics.checkNotNullExpressionValue(pathNormalize, "normalize(...)");
            ExpandResKey key = new ExpandResKey(pathNormalize, zipEntry, outPut.getAbsolute().getNormalize());
            Object obj = jarInnerResourceCache.get(key);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (IResource) obj;
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResource expandRes(@NotNull IResDirectory outPut) {
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            String zipEntry = getZipEntry();
            if (zipEntry != null) {
                return expandRes(outPut, zipEntry);
            }
            return this;
        }

        @Override // cn.sast.common.IResource
        public void mkdirs() {
            if (!getExists() || isDirectory()) {
                toDirectory().mkdirs();
            } else {
                toFile().mkdirs();
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull IResource other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return toString().compareTo(other.toString());
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResource resolve(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (isFile() && getZipLike()) {
                return new ResourceBasic(Resource.INSTANCE.archivePath(this.path, name));
            }
            Path pathResolve = this.path.resolve(name);
            Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
            return new ResourceBasic(pathResolve);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResFile toFile() {
            return new ResFileImpl(this.path);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResDirectory toDirectory() {
            return new ResDirectoryImpl(this.path);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public String getAbsolutePath() {
            return this.path.toAbsolutePath().toString();
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResource getAbsolute() {
            Path absolutePath = this.path.toAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
            Path pathNormalize = absolutePath.normalize();
            Intrinsics.checkNotNullExpressionValue(pathNormalize, "normalize(...)");
            return new ResourceBasic(pathNormalize);
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public IResource getNormalize() {
            Path pathNormalize = this.path.normalize();
            Intrinsics.checkNotNullExpressionValue(pathNormalize, "normalize(...)");
            return new ResourceBasic(pathNormalize);
        }

        @NotNull
        public String toString() {
            String str = this._string;
            if (str != null) {
                return str;
            }
            String str2 = isFileScheme() ? this.path.toString() : CollectionsKt.joinToString$default(CollectionsKt.asReversed(SequencesKt.toList(seq())), "!", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            this._string = str2;
            return str2;
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public String getPathString() {
            return toString();
        }

        @Override // cn.sast.common.IResource
        @NotNull
        public Sequence<Path> seq() {
            return isFileScheme() ? SequencesKt.sequenceOf(new Path[]{this.path}) : SequencesKt.generateSequence(this.path, ResourceBasic::seq$lambda$5);
        }

        private static final Path seq$lambda$5(Path it) {
            Intrinsics.checkNotNullParameter(it, "it");
            URI curUri = it.toUri();
            Intrinsics.checkNotNull(curUri);
            if (Intrinsics.areEqual(curUri.getScheme(), "file") || Intrinsics.areEqual(curUri.getScheme(), "jrt")) {
                return null;
            }
            return (Path) Resource.INSTANCE.getUriToPathCached().get(TuplesKt.to(curUri, true));
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof ResourceBasic) {
                return Intrinsics.areEqual(this.path, ((ResourceBasic) other).path);
            }
            return false;
        }

        public int hashCode() {
            Integer hash = this._hash;
            if (hash != null) {
                return hash.intValue();
            }
            Integer hash2 = Integer.valueOf(2234 + this.path.hashCode());
            this._hash = hash2;
            return hash2.intValue();
        }

        /* compiled from: ResourceImpl.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003RV\u0010\u0004\u001aB\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b \u0007* \u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b\u0018\u00010\u00050\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcn/sast/common/Resource$ResourceBasic$Companion;", "", "<init>", "()V", "jarInnerResourceCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lcn/sast/common/Resource$ExpandResKey;", "kotlin.jvm.PlatformType", "Lcn/sast/common/IResource;", "getJarInnerResourceCache$corax_api", "()Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "corax-api"})
        /* loaded from: Resource$ResourceBasic$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            public final LoadingCache<ExpandResKey, IResource> getJarInnerResourceCache$corax_api() {
                return ResourceBasic.jarInnerResourceCache;
            }
        }
    }

    /* compiled from: ResourceImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0014\u001a\u00020\u0002H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0016H\u0016R\u0014\u0010\u0007\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcn/sast/common/Resource$ResFileImpl;", "Lcn/sast/common/Resource$ResourceBasic;", "Lcn/sast/common/IResFile;", "path", "Ljava/nio/file/Path;", "<init>", "(Ljava/nio/file/Path;)V", "absolute", "getAbsolute", "()Lcn/sast/common/IResFile;", "normalize", "getNormalize", "entries", "", "", "getEntries", "()Ljava/util/Set;", "md5", "getMd5", "()Ljava/lang/String;", "toFile", "toDirectory", "Lcn/sast/common/IResDirectory;", "mkdirs", "", "expandRes", "outPut", "corax-api"})
    /* loaded from: Resource$ResFileImpl.class */
    public static final class ResFileImpl extends ResourceBasic implements IResFile {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResFileImpl(@NotNull Path path) {
            super(path);
            Intrinsics.checkNotNullParameter(path, "path");
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResFile getAbsolute() {
            return super.getAbsolute().toFile();
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResFile getNormalize() {
            return super.getNormalize().toFile();
        }

        @Override // cn.sast.common.IResFile
        @NotNull
        public Set<String> getEntries() {
            Set<String> setEmptySet;
            try {
                setEmptySet = Resource.INSTANCE.getEntriesUnsafe(getPath());
            } catch (NoSuchFileException e) {
                Resource.logger.warn(() -> {
                    return _get_entries_$lambda$0(r1, r2);
                });
                setEmptySet = SetsKt.emptySet();
            } catch (Exception e2) {
                Resource.logger.warn(() -> {
                    return _get_entries_$lambda$1(r1, r2);
                });
                Resource.logger.debug(e2, () -> {
                    return _get_entries_$lambda$2(r2);
                });
                setEmptySet = SetsKt.emptySet();
            }
            return setEmptySet;
        }

        private static final Object _get_entries_$lambda$0(ResFileImpl this$0, NoSuchFileException $e) {
            return "no such file: " + this$0.getPath() + ". e: " + $e.getMessage();
        }

        private static final Object _get_entries_$lambda$1(ResFileImpl this$0, Exception $e) {
            return "failed to get entries of " + this$0.getPath() + ". e: " + $e.getMessage();
        }

        private static final Object _get_entries_$lambda$2(ResFileImpl this$0) {
            return "failed to get entries of " + this$0.getPath() + ".";
        }

        @Override // cn.sast.common.IResFile
        @NotNull
        public String getMd5() {
            return ResourceImplKt.calculate(this, "MD5");
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResFile toFile() {
            return this;
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResDirectory toDirectory() {
            Path parent = getPath().getParent();
            Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
            return new ResDirectoryImpl(parent);
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        public void mkdirs() {
            Resource resource = Resource.INSTANCE;
            Path parent = getPath().getParent();
            Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
            resource.of(parent).mkdirs();
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResFile expandRes(@NotNull IResDirectory outPut) {
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            return super.expandRes(outPut).toFile();
        }
    }

    /* compiled from: ResourceImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0002H\u0016R\u0014\u0010\u0007\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lcn/sast/common/Resource$ResDirectoryImpl;", "Lcn/sast/common/Resource$ResourceBasic;", "Lcn/sast/common/IResDirectory;", "path", "Ljava/nio/file/Path;", "<init>", "(Ljava/nio/file/Path;)V", "absolute", "getAbsolute", "()Lcn/sast/common/IResDirectory;", "normalize", "getNormalize", "listPathEntries", "", "Lcn/sast/common/IResource;", "glob", "", "expandRes", "outPut", "mkdirs", "", "toFile", "Lcn/sast/common/IResFile;", "toDirectory", "corax-api"})
    @SourceDebugExtension({"SMAP\nResourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource$ResDirectoryImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,730:1\n1557#2:731\n1628#2,3:732\n*S KotlinDebug\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/Resource$ResDirectoryImpl\n*L\n603#1:731\n603#1:732,3\n*E\n"})
    /* loaded from: Resource$ResDirectoryImpl.class */
    public static final class ResDirectoryImpl extends ResourceBasic implements IResDirectory {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ResDirectoryImpl(@NotNull Path path) {
            super(path);
            Intrinsics.checkNotNullParameter(path, "path");
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResDirectory getAbsolute() {
            return super.getAbsolute().toDirectory();
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResDirectory getNormalize() {
            return super.getNormalize().toDirectory();
        }

        @Override // cn.sast.common.IResDirectory
        @NotNull
        public List<IResource> listPathEntries(@NotNull String glob) {
            Intrinsics.checkNotNullParameter(glob, "glob");
            if (!getExists() || isFile()) {
                return CollectionsKt.emptyList();
            }
            Iterable $this$map$iv = PathsKt.listDirectoryEntries(getPath(), glob);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Path it = (Path) item$iv$iv;
                destination$iv$iv.add(Resource.INSTANCE.of(it));
            }
            return (List) destination$iv$iv;
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResDirectory expandRes(@NotNull IResDirectory outPut) {
            Intrinsics.checkNotNullParameter(outPut, "outPut");
            return super.expandRes(outPut).toDirectory();
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        public void mkdirs() {
            if (!isFileScheme()) {
                throw new IllegalStateException(("mkdirs is not support of " + this).toString());
            }
            FileUtils.forceMkdir(getPath().toFile());
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResFile toFile() {
            return Resource.INSTANCE.fileOf(getPath());
        }

        @Override // cn.sast.common.Resource.ResourceBasic, cn.sast.common.IResource
        @NotNull
        public IResDirectory toDirectory() {
            return this;
        }
    }
}
