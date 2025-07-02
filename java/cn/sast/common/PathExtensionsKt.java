package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathExtensions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��&\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u001a\u0012\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003\u001a\n\u0010\u0013\u001a\u00020\u0003*\u00020\u0001\u001a\n\u0010\u0014\u001a\u00020\r*\u00020\u0001\u001a\n\u0010\u0015\u001a\u00020\r*\u00020\u0001\"\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\"\u0015\u0010\u0007\u001a\u00020\u0005*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006\"\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0015\u0010\u0010\u001a\u00020\u0003*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"resolveDir", "Ljava/nio/file/Path;", "dir", "", "isRegularFile", "", "(Ljava/nio/file/Path;)Z", "isDirectory", "files", "", "getFiles", "(Ljava/nio/file/Path;)Ljava/util/List;", "replaceText", "", "sourceText", "replacementText", "text", "getText", "(Ljava/nio/file/Path;)Ljava/lang/String;", "getExtension", "deleteDirectoryRecursively", "deleteDirectoryContents", "corax-api"})
@SourceDebugExtension({"SMAP\nPathExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathExtensions.kt\ncn/sast/common/PathExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,94:1\n1#2:95\n*E\n"})
/* loaded from: PathExtensionsKt.class */
public final class PathExtensionsKt {
    @NotNull
    public static final Path resolveDir(@NotNull Path $this$resolveDir, @NotNull String dir) {
        Intrinsics.checkNotNullParameter($this$resolveDir, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        if (!isDirectory($this$resolveDir)) {
            throw new IllegalStateException(("Failed check: receiver.isDirectory, where receiver is: " + $this$resolveDir).toString());
        }
        if (!(dir.length() > 0)) {
            throw new IllegalStateException(("Failed check: dir.length > 0, where dir is: '" + dir + "'").toString());
        }
        Path resolvedDir = $this$resolveDir.resolve(dir);
        Intrinsics.checkNotNull(resolvedDir);
        if (isDirectory(resolvedDir)) {
            return resolvedDir;
        }
        throw new IllegalStateException(("Failed check: resolvedDir.isDirectory, where resolvedDir is: " + resolvedDir).toString());
    }

    public static final boolean isRegularFile(@NotNull Path $this$isRegularFile) {
        Intrinsics.checkNotNullParameter($this$isRegularFile, "<this>");
        return Files.isRegularFile($this$isRegularFile, new LinkOption[0]);
    }

    public static final boolean isDirectory(@NotNull Path $this$isDirectory) {
        Intrinsics.checkNotNullParameter($this$isDirectory, "<this>");
        return Files.isDirectory($this$isDirectory, new LinkOption[0]);
    }

    @NotNull
    public static final List<Path> getFiles(@NotNull Path $this$files) throws IOException {
        Intrinsics.checkNotNullParameter($this$files, "<this>");
        if (!isDirectory($this$files)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Stream<Path> list = Files.list($this$files);
        final Function1 function1 = new PropertyReference1Impl() { // from class: cn.sast.common.PathExtensionsKt$files$1
            public Object get(Object receiver0) {
                return Boolean.valueOf(PathExtensionsKt.isRegularFile((Path) receiver0));
            }
        };
        Object objCollect = list.filter(new Predicate(function1) { // from class: cn.sast.common.PathExtensionsKt$sam$java_util_function_Predicate$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function1, "function");
                this.function = function1;
            }

            @Override // java.util.function.Predicate
            public final /* synthetic */ boolean test(Object p0) {
                return ((Boolean) this.function.invoke(p0)).booleanValue();
            }
        }).collect(Collectors.toList());
        Intrinsics.checkNotNullExpressionValue(objCollect, "collect(...)");
        return (List) objCollect;
    }

    public static final void replaceText(@NotNull Path $this$replaceText, @NotNull String sourceText, @NotNull String replacementText) {
        Intrinsics.checkNotNullParameter($this$replaceText, "<this>");
        Intrinsics.checkNotNullParameter(sourceText, "sourceText");
        Intrinsics.checkNotNullParameter(replacementText, "replacementText");
        PathsKt.writeText$default($this$replaceText, StringsKt.replace$default(getText($this$replaceText), sourceText, replacementText, false, 4, (Object) null), (Charset) null, new OpenOption[0], 2, (Object) null);
    }

    @NotNull
    public static final String getText(@NotNull Path $this$text) throws IOException {
        Intrinsics.checkNotNullParameter($this$text, "<this>");
        if (!isRegularFile($this$text)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        List<String> allLines = Files.readAllLines($this$text);
        Intrinsics.checkNotNullExpressionValue(allLines, "readAllLines(...)");
        String strLineSeparator = System.lineSeparator();
        Intrinsics.checkNotNullExpressionValue(strLineSeparator, "lineSeparator(...)");
        return CollectionsKt.joinToString$default(allLines, strLineSeparator, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String getExtension(@NotNull Path $this$getExtension) {
        Intrinsics.checkNotNullParameter($this$getExtension, "<this>");
        String extension = FilenameUtils.getExtension($this$getExtension.getFileName().toString());
        Intrinsics.checkNotNullExpressionValue(extension, "getExtension(...)");
        return extension;
    }

    public static final void deleteDirectoryRecursively(@NotNull Path $this$deleteDirectoryRecursively) throws IOException {
        Intrinsics.checkNotNullParameter($this$deleteDirectoryRecursively, "<this>");
        LinkOption[] linkOptionArr = new LinkOption[0];
        if (!Files.exists($this$deleteDirectoryRecursively, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            return;
        }
        MoreFiles.deleteRecursively($this$deleteDirectoryRecursively, new RecursiveDeleteOption[]{RecursiveDeleteOption.ALLOW_INSECURE});
    }

    public static final void deleteDirectoryContents(@NotNull Path $this$deleteDirectoryContents) throws IOException {
        Intrinsics.checkNotNullParameter($this$deleteDirectoryContents, "<this>");
        LinkOption[] linkOptionArr = new LinkOption[0];
        if (!Files.exists($this$deleteDirectoryContents, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            return;
        }
        MoreFiles.deleteDirectoryContents($this$deleteDirectoryContents, new RecursiveDeleteOption[]{RecursiveDeleteOption.ALLOW_INSECURE});
    }
}
