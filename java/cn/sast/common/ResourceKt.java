package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Resource.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��h\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010$\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u001a>\u0010\u0017\u001a\u0002H\u0018\"\u0004\b��\u0010\u0018*\u00020\u00142'\u0010\u0019\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00010\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\u00180\u001a¢\u0006\u0002\u0010\u001f\u001a\n\u0010 \u001a\u00020!*\u00020\u0014\u001a\u001c\u0010\"\u001a\u00020#*\u00020\u00142\u0006\u0010��\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u001a\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u0001\u001a\u0018\u0010*\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000f2\b\b\u0002\u0010+\u001a\u00020\n\u001a$\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020#00\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\n*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0016\u0010\u000e\u001a\u00020\n*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\n*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010\"\u0016\u0010\u0012\u001a\u00020\n*\u00020\u000f8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\"\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010%X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n��¨\u00061"}, d2 = {"text", "", "Ljava/net/URL;", "getText", "(Ljava/net/URL;)Ljava/lang/String;", "javaExtensions", "", "getJavaExtensions", "()Ljava/util/List;", "zipLike", "", "Ljava/nio/file/Path;", "getZipLike", "(Ljava/nio/file/Path;)Z", "isJarScheme", "Ljava/net/URI;", "(Ljava/net/URI;)Z", "isFileScheme", "isJrtScheme", "readText", "Lcn/sast/common/IResFile;", "charset", "Ljava/nio/charset/Charset;", "lineSequence", "T", "apply", "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "Lkotlin/ParameterName;", "name", "seq", "(Lcn/sast/common/IResFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readAllBytes", "", "writeText", "", "escapes", "", "urlEscapePattern", "Ljava/util/regex/Pattern;", "uriOf", "uri", "uriToPath", "getRootFsPath", "findCacheFromDeskOrCreate", "input", "output", "creator", "Lkotlin/Function0;", "corax-api"})
@SourceDebugExtension({"SMAP\nResource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Resource.kt\ncn/sast/common/ResourceKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,177:1\n37#1:179\n38#1:180\n1#2:178\n*S KotlinDebug\n*F\n+ 1 Resource.kt\ncn/sast/common/ResourceKt\n*L\n142#1:179\n146#1:180\n*E\n"})
/* loaded from: ResourceKt.class */
public final class ResourceKt {

    @NotNull
    private static final List<String> javaExtensions = CollectionsKt.listOf(new String[]{"java", "kt", "kts", "scala", "groovy", "jsp"});

    @NotNull
    private static final Map<String, String> escapes = MapsKt.mapOf(new Pair[]{TuplesKt.to("[", "%5B"), TuplesKt.to("]", "%5D"), TuplesKt.to("{", "%7B"), TuplesKt.to("}", "%7D"), TuplesKt.to("<", "%3C"), TuplesKt.to(">", "%3E"), TuplesKt.to("#", "%23"), TuplesKt.to("?", "%3F"), TuplesKt.to("@", "%40"), TuplesKt.to(" ", "%20")});

    @NotNull
    private static final Pattern urlEscapePattern;

    @NotNull
    public static final String getText(@NotNull URL $this$text) {
        Intrinsics.checkNotNullParameter($this$text, "<this>");
        return new String(TextStreamsKt.readBytes($this$text), Charsets.UTF_8);
    }

    @NotNull
    public static final List<String> getJavaExtensions() {
        return javaExtensions;
    }

    static {
        Pattern patternCompile = Pattern.compile("([\\[\\]{}<>#?@ ])");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(...)");
        urlEscapePattern = patternCompile;
    }

    public static final boolean getZipLike(@NotNull Path $this$zipLike) {
        Intrinsics.checkNotNullParameter($this$zipLike, "<this>");
        return ResourceImplKt.getZipExtensions().contains(PathsKt.getExtension($this$zipLike));
    }

    public static final boolean isJarScheme(@NotNull URI $this$isJarScheme) {
        Intrinsics.checkNotNullParameter($this$isJarScheme, "<this>");
        String it = $this$isJarScheme.getScheme();
        return Intrinsics.areEqual(it, "jar") || StringsKt.equals(it, "zip", true);
    }

    public static final boolean isFileScheme(@NotNull URI $this$isFileScheme) {
        Intrinsics.checkNotNullParameter($this$isFileScheme, "<this>");
        return Intrinsics.areEqual($this$isFileScheme.getScheme(), "file");
    }

    public static final boolean isJrtScheme(@NotNull URI $this$isJrtScheme) {
        Intrinsics.checkNotNullParameter($this$isJrtScheme, "<this>");
        return Intrinsics.areEqual($this$isJrtScheme.getScheme(), "jrt");
    }

    public static /* synthetic */ String readText$default(IResFile iResFile, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(iResFile, charset);
    }

    @NotNull
    public static final String readText(@NotNull IResFile $this$readText, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream($this$readText.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
        Throwable th = null;
        try {
            try {
                InputStreamReader rd = inputStreamReader;
                String text = TextStreamsKt.readText(rd);
                CloseableKt.closeFinally(inputStreamReader, (Throwable) null);
                return text;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(inputStreamReader, th);
            throw th2;
        }
    }

    public static final <T> T lineSequence(@NotNull IResFile iResFile, @NotNull Function1<? super Sequence<String>, ? extends T> function1) throws IOException {
        Intrinsics.checkNotNullParameter(iResFile, "<this>");
        Intrinsics.checkNotNullParameter(function1, "apply");
        OpenOption[] openOptionArr = new OpenOption[0];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(iResFile.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), Charsets.UTF_8), 8192);
        Throwable th = null;
        try {
            try {
                T t = (T) function1.invoke(TextStreamsKt.lineSequence(bufferedReader));
                CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                return t;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(bufferedReader, th);
            throw th2;
        }
    }

    @NotNull
    public static final byte[] readAllBytes(@NotNull IResFile $this$readAllBytes) throws IOException {
        Intrinsics.checkNotNullParameter($this$readAllBytes, "<this>");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream($this$readAllBytes.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        InputStream inputStream = inputStreamNewInputStream;
        try {
            InputStream it = inputStream;
            byte[] allBytes = it.readAllBytes();
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            Intrinsics.checkNotNullExpressionValue(allBytes, "use(...)");
            return allBytes;
        } catch (Throwable th) {
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            throw th;
        }
    }

    public static /* synthetic */ void writeText$default(IResFile iResFile, String str, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(iResFile, str, charset);
    }

    public static final void writeText(@NotNull IResFile $this$writeText, @NotNull String text, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeText, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream($this$writeText.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), charset);
        Throwable th = null;
        try {
            try {
                OutputStreamWriter it = outputStreamWriter;
                it.write(text);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStreamWriter, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStreamWriter, th);
            throw th2;
        }
    }

    @NotNull
    public static final URI uriOf(@NotNull String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Matcher m = urlEscapePattern.matcher(uri);
        Intrinsics.checkNotNullExpressionValue(m, "matcher(...)");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, escapes.get(m.group(1)));
        }
        m.appendTail(sb);
        String escaped = new URI(sb.toString()).toASCIIString();
        return new URI(escaped);
    }

    public static /* synthetic */ Path uriToPath$default(URI uri, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return uriToPath(uri, z);
    }

    @NotNull
    public static final Path uriToPath(@NotNull URI uri, boolean getRootFsPath) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            if (!(!getRootFsPath)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Path path = Paths.get(uri);
            Intrinsics.checkNotNullExpressionValue(path, "get(...)");
            return path;
        }
        if (!(!Intrinsics.areEqual(uri.getScheme(), "jrt"))) {
            throw new IllegalStateException(("unsupported uri: " + uri + " with a JRT scheme.").toString());
        }
        String spec = uri.getSchemeSpecificPart();
        Intrinsics.checkNotNull(spec);
        int sep = StringsKt.lastIndexOf$default(spec, "!/", 0, false, 6, (Object) null);
        if (sep == -1) {
            if (!(!getRootFsPath)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Path path2 = Paths.get(uriOf(spec));
            Intrinsics.checkNotNull(path2);
            return path2;
        }
        String zipPath = spec.substring(0, sep);
        Intrinsics.checkNotNullExpressionValue(zipPath, "substring(...)");
        String entry = spec.substring(sep + 1);
        Intrinsics.checkNotNullExpressionValue(entry, "substring(...)");
        Path archive = uriToPath$default(uriOf(zipPath), false, 2, null);
        if (getRootFsPath) {
            return archive;
        }
        Path path3 = Resource.INSTANCE.getZipFileSystem(archive).getPath(entry, new String[0]);
        Intrinsics.checkNotNull(path3);
        return path3;
    }

    @NotNull
    public static final Path findCacheFromDeskOrCreate(@NotNull Path input, @NotNull Path output, @NotNull Function0<Unit> function0) throws IOException {
        File flag;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(function0, "creator");
        String md5 = Resource.INSTANCE.fileOf(input).getMd5();
        Path normal = output.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(normal, "toAbsolutePath(...)");
        Path parent = normal.getParent();
        if (parent != null) {
            Path pathResolve = parent.resolve("." + StringsKt.substringBeforeLast$default(PathsKt.getName(output), ".", (String) null, 2, (Object) null) + ".successful");
            if (pathResolve != null && (flag = pathResolve.toFile()) != null) {
                if (flag.exists() && Intrinsics.areEqual(md5, FilesKt.readText$default(flag, (Charset) null, 1, (Object) null))) {
                    LinkOption[] linkOptionArr = new LinkOption[0];
                    if (Files.exists(output, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                        return output;
                    }
                } else {
                    Files.deleteIfExists(output);
                }
                function0.invoke();
                FilesKt.writeText$default(flag, md5, (Charset) null, 2, (Object) null);
                return output;
            }
        }
        throw new IllegalStateException(("bad output paths: " + output).toString());
    }
}
