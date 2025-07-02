package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResourceImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��D\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0005\u001a\u0016\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u001c\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u0001\u001a\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\u0010\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0001\u001a\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0001\u001a\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0001\"\u0011\u0010��\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"tempDirectoryPath", "", "getTempDirectoryPath", "()Ljava/lang/String;", "sAstTempDirectory", "Ljava/nio/file/Path;", "getSAstTempDirectory", "()Ljava/nio/file/Path;", "zipExtensions", "", "getZipExtensions", "()Ljava/util/List;", "createFileSystem", "Ljava/nio/file/FileSystem;", "uri", "Ljava/net/URI;", "path", "calculate", "fis", "Ljava/io/InputStream;", "algorithm", "", "Lcn/sast/common/IResFile;", "splitGlobPath", "Lkotlin/Pair;", "Lcn/sast/common/IResource;", "p", "globPaths", "pattern", "globPath", "corax-api"})
@SourceDebugExtension({"SMAP\nResourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/ResourceImplKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,730:1\n1#2:731\n1557#3:732\n1628#3,3:733\n1734#3,3:736\n1557#3:739\n1628#3,3:740\n1557#3:743\n1628#3,3:744\n1368#3:747\n1454#3,5:748\n*S KotlinDebug\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/ResourceImplKt\n*L\n681#1:732\n681#1:733,3\n682#1:736,3\n694#1:739\n694#1:740,3\n696#1:743\n696#1:744,3\n697#1:747\n697#1:748,5\n*E\n"})
/* loaded from: ResourceImplKt.class */
public final class ResourceImplKt {

    @NotNull
    private static final String tempDirectoryPath;

    @NotNull
    private static final List<String> zipExtensions;

    @NotNull
    public static final String getTempDirectoryPath() {
        return tempDirectoryPath;
    }

    static {
        String property = System.getProperty("java.io.tmpdir");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        tempDirectoryPath = property;
        zipExtensions = CollectionsKt.listOf(new String[]{"zip", "jar", "war", "apk", "aar"});
    }

    @NotNull
    public static final Path getSAstTempDirectory() {
        Path it = Paths.get(tempDirectoryPath, "0a-sast-corax-can-delete", new SimpleDateFormat("YYYY.MM.DD.HH.mm").format(Long.valueOf(System.currentTimeMillis())));
        it.toFile().mkdirs();
        Intrinsics.checkNotNullExpressionValue(it, "also(...)");
        return it;
    }

    @NotNull
    public static final List<String> getZipExtensions() {
        return zipExtensions;
    }

    @NotNull
    public static final FileSystem createFileSystem(@NotNull URI uri) throws IOException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Map env = MapsKt.toMutableMap(Resource.INSTANCE.getNewFileSystemEnv());
        ZipException zipException = null;
        List encodings = CollectionsKt.toMutableList(Resource.INSTANCE.getFileSystemEncodings());
        if (encodings.isEmpty()) {
            encodings.add("UTF-8");
        }
        for (IndexedValue encodingI : CollectionsKt.withIndex(encodings)) {
            String encoding = (String) encodingI.getValue();
            env.put("encoding", encoding);
            try {
                FileSystem fileSystemNewFileSystem = FileSystems.newFileSystem(uri, (Map<String, ?>) env);
                Intrinsics.checkNotNullExpressionValue(fileSystemNewFileSystem, "newFileSystem(...)");
                return fileSystemNewFileSystem;
            } catch (ZipException e) {
                zipException = e;
            }
        }
        ZipException zipException2 = zipException;
        Intrinsics.checkNotNull(zipException2);
        throw zipException2;
    }

    @NotNull
    public static final FileSystem createFileSystem(@NotNull Path path) throws ZipException {
        Intrinsics.checkNotNullParameter(path, "path");
        Map env = MapsKt.toMutableMap(Resource.INSTANCE.getNewFileSystemEnv());
        ZipException zipException = null;
        List encodings = CollectionsKt.toMutableList(Resource.INSTANCE.getFileSystemEncodings());
        if (encodings.isEmpty()) {
            encodings.add("UTF-8");
        }
        for (IndexedValue encodingI : CollectionsKt.withIndex(encodings)) {
            String encoding = (String) encodingI.getValue();
            env.put("encoding", encoding);
            try {
                FileSystem fileSystemNewFileSystem = FileSystems.newFileSystem(path, env);
                Intrinsics.checkNotNullExpressionValue(fileSystemNewFileSystem, "newFileSystem(...)");
                return fileSystemNewFileSystem;
            } catch (ZipException e) {
                zipException = e;
            }
        }
        ZipException zipException2 = zipException;
        Intrinsics.checkNotNull(zipException2);
        throw zipException2;
    }

    @NotNull
    public static final String calculate(@NotNull InputStream fis, @NotNull String algorithm) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(fis, "fis");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        byte[] buffer = new byte[8192];
        MessageDigest md = MessageDigest.getInstance(algorithm);
        while (true) {
            int it = fis.read(buffer);
            if (it == -1) {
                byte[] bArrDigest = md.digest();
                Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
                return ArraysKt.joinToString$default(bArrDigest, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v0) -> {
                    return calculate$lambda$2(v0);
                }, 30, (Object) null);
            }
            md.update(buffer, 0, it);
        }
    }

    private static final CharSequence calculate$lambda$2(byte it) {
        Object[] objArr = {Byte.valueOf(it)};
        String str = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @NotNull
    public static final String calculate(@NotNull byte[] $this$calculate, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter($this$calculate, "<this>");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream($this$calculate);
        Throwable th = null;
        try {
            try {
                ByteArrayInputStream it = byteArrayInputStream;
                String strCalculate = calculate(it, algorithm);
                CloseableKt.closeFinally(byteArrayInputStream, (Throwable) null);
                return strCalculate;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(byteArrayInputStream, th);
            throw th2;
        }
    }

    @NotNull
    public static final String calculate(@NotNull Path $this$calculate, @NotNull String algorithm) throws IOException {
        Intrinsics.checkNotNullParameter($this$calculate, "<this>");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream($this$calculate, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        InputStream inputStream = inputStreamNewInputStream;
        Throwable th = null;
        try {
            try {
                InputStream fis = inputStream;
                String strCalculate = calculate(fis, algorithm);
                CloseableKt.closeFinally(inputStream, (Throwable) null);
                return strCalculate;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(inputStream, th);
            throw th2;
        }
    }

    @NotNull
    public static final String calculate(@NotNull IResFile $this$calculate, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter($this$calculate, "<this>");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        return calculate($this$calculate.getPath(), algorithm);
    }

    @NotNull
    public static final Pair<IResource, String> splitGlobPath(@NotNull String p) {
        IResource r;
        Intrinsics.checkNotNullParameter(p, "p");
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(p, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null);
        List glob = new ArrayList();
        while (true) {
            try {
                Result.Companion companion = Result.Companion;
                r = Resource.INSTANCE.of(strReplace$default);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (!r.getExists()) {
                Result.constructor-impl(Unit.INSTANCE);
                if (!Intrinsics.areEqual(strReplace$default, "./")) {
                    int index = StringsKt.lastIndexOf$default(strReplace$default, "/", 0, false, 6, (Object) null);
                    if (index == -1) {
                        glob.add(0, strReplace$default);
                        strReplace$default = "./";
                    } else {
                        String strSubstring = strReplace$default.substring(index + 1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        glob.add(0, strSubstring);
                        String strSubstring2 = strReplace$default.substring(0, index);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        strReplace$default = strSubstring2;
                    }
                } else {
                    throw new IllegalStateException(("\"" + p + "\" is a invalid path").toString());
                }
            } else {
                if (glob.isEmpty()) {
                    return TuplesKt.to(r, (Object) null);
                }
                return TuplesKt.to(r, CollectionsKt.joinToString$default(glob, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            }
        }
    }

    @NotNull
    public static final List<IResource> globPaths(@NotNull IResource path, @NotNull String pattern) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        final PathMatcher pathMatcher = path.getPath().getFileSystem().getPathMatcher("glob:" + pattern);
        final List r = new ArrayList();
        final Path p = path.getPath().normalize();
        Files.walkFileTree(p, new SimpleFileVisitor<Path>() { // from class: cn.sast.common.ResourceImplKt.globPaths.1
            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
            public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
                Intrinsics.checkNotNullParameter(filePath, "filePath");
                Intrinsics.checkNotNullParameter(attrs, "attrs");
                Path diff = p.relativize(filePath);
                Intrinsics.checkNotNullExpressionValue(diff, "relativize(...)");
                Path subPath = diff.subpath(0, diff.getNameCount());
                if (pathMatcher.matches(subPath)) {
                    r.add(Resource.INSTANCE.of(filePath));
                }
                return FileVisitResult.CONTINUE;
            }

            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                Intrinsics.checkNotNullParameter(dir, "dir");
                Intrinsics.checkNotNullParameter(attrs, "attrs");
                return visitFile(dir, attrs);
            }
        });
        return r;
    }

    @Nullable
    public static final List<IResource> globPaths(@NotNull String p) throws IOException {
        boolean z;
        Intrinsics.checkNotNullParameter(p, "p");
        Iterable $this$map$iv = StringsKt.split$default(p, new String[]{File.pathSeparator}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(globPath((String) item$iv$iv));
        }
        Iterable r = (List) destination$iv$iv;
        Iterable $this$all$iv = r;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                Object element$iv = it.next();
                if (!(((List) element$iv) == null)) {
                    z = false;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        List it2 = CollectionsKt.flatten(CollectionsKt.filterNotNull(r));
        if (!it2.isEmpty()) {
            return it2;
        }
        return null;
    }

    @Nullable
    public static final List<IResource> globPath(@NotNull String p) throws IOException {
        List listListOf;
        Iterable iterableGlobPaths;
        Intrinsics.checkNotNullParameter(p, "p");
        File file = new File(p);
        if (!file.exists()) {
            List<String> parts = StringsKt.split$default(p, new String[]{"!"}, false, 0, 6, (Object) null);
            List collect = new ArrayList();
            boolean first = true;
            for (String part : parts) {
                if (first) {
                    listListOf = CollectionsKt.listOf(part);
                } else {
                    Iterable $this$map$iv = collect;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        IResource it = (IResource) item$iv$iv;
                        destination$iv$iv.add(it + "!" + part);
                    }
                    listListOf = (List) destination$iv$iv;
                }
                List walkFiles = listListOf;
                first = false;
                List $this$map$iv2 = walkFiles;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    String it2 = (String) item$iv$iv2;
                    destination$iv$iv2.add(splitGlobPath(it2));
                }
                Iterable splitFileAndGlob = (List) destination$iv$iv2;
                Iterable $this$flatMap$iv = splitFileAndGlob;
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv : $this$flatMap$iv) {
                    Pair o = (Pair) element$iv$iv;
                    String g = (String) o.getSecond();
                    if (g == null) {
                        iterableGlobPaths = CollectionsKt.listOf(o.getFirst());
                    } else {
                        iterableGlobPaths = globPaths((IResource) o.getFirst(), g);
                    }
                    Iterable list$iv$iv = iterableGlobPaths;
                    CollectionsKt.addAll(destination$iv$iv3, list$iv$iv);
                }
                List matchedFiles = (List) destination$iv$iv3;
                collect = CollectionsKt.toMutableList(matchedFiles);
            }
            if (!collect.isEmpty()) {
                return collect;
            }
            return null;
        }
        return CollectionsKt.listOf(Resource.INSTANCE.of(p));
    }
}
