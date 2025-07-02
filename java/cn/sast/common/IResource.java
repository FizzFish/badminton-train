package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Resource.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bv\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001J\u0010\u0010/\u001a\u00020��2\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u00100\u001a\u000201H&J\b\u00102\u001a\u000203H&J\u0010\u00104\u001a\u00020��2\u0006\u00105\u001a\u000203H&J\b\u00106\u001a\u000207H&J\b\u0010:\u001a\u000207H\u0016J\b\u0010;\u001a\u000207H\u0016J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030=H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u0012\u0010\u0011\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0012\u0010\u0012\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0012\u0010\u0013\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\tR\u0012\u0010\u0015\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0012\u0010\u001a\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000fR\u0012\u0010\u001b\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000fR\u0012\u0010\u001c\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u0012\u0010\u001d\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0005R\u0012\u0010\u001f\u001a\u00020 X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\tR\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u0004\u0018\u00010��X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0017R\u0014\u00108\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\u000fR\u0012\u0010>\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\t\u0082\u0001\u000331@¨\u0006A"}, d2 = {"Lcn/sast/common/IResource;", "", "path", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;", "name", "", "getName", "()Ljava/lang/String;", "extension", "getExtension", "exists", "", "getExists", "()Z", "isFile", "isDirectory", "isRegularFile", "absolutePath", "getAbsolutePath", "absolute", "getAbsolute", "()Lcn/sast/common/IResource;", "normalize", "getNormalize", "isFileScheme", "isJrtScheme", "isJarScheme", "schemePath", "getSchemePath", "uri", "Ljava/net/URI;", "getUri", "()Ljava/net/URI;", "url", "Ljava/net/URL;", "getUrl", "()Ljava/net/URL;", "zipEntry", "getZipEntry", "file", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "parent", "getParent", "resolve", "toFile", "Lcn/sast/common/IResFile;", "toDirectory", "Lcn/sast/common/IResDirectory;", "expandRes", "outPut", "mkdirs", "", "zipLike", "getZipLike", "deleteDirectoryRecursively", "deleteDirectoryContents", "seq", "Lkotlin/sequences/Sequence;", "pathString", "getPathString", "Lcn/sast/common/Resource$ResourceBasic;", "corax-api"})
/* loaded from: IResource.class */
public interface IResource extends Comparable<IResource> {
    @NotNull
    Path getPath();

    @NotNull
    String getName();

    @NotNull
    String getExtension();

    boolean getExists();

    boolean isFile();

    boolean isDirectory();

    boolean isRegularFile();

    @NotNull
    String getAbsolutePath();

    @NotNull
    IResource getAbsolute();

    @NotNull
    IResource getNormalize();

    boolean isFileScheme();

    boolean isJrtScheme();

    boolean isJarScheme();

    @NotNull
    Path getSchemePath();

    @NotNull
    URI getUri();

    @NotNull
    URL getUrl();

    @Nullable
    String getZipEntry();

    @NotNull
    File getFile() throws UnsupportedOperationException;

    @Nullable
    IResource getParent();

    @NotNull
    IResource resolve(@NotNull String str);

    @NotNull
    IResFile toFile();

    @NotNull
    IResDirectory toDirectory();

    @NotNull
    IResource expandRes(@NotNull IResDirectory iResDirectory);

    void mkdirs();

    boolean getZipLike();

    void deleteDirectoryRecursively() throws IOException;

    void deleteDirectoryContents() throws IOException;

    @NotNull
    Sequence<Path> seq();

    @NotNull
    String getPathString();

    /* compiled from: Resource.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IResource$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean getZipLike(@NotNull IResource $this) {
            return ResourceKt.getZipLike($this.getPath());
        }

        public static void deleteDirectoryRecursively(@NotNull IResource $this) throws IOException {
            PathExtensionsKt.deleteDirectoryRecursively($this.getPath());
        }

        public static void deleteDirectoryContents(@NotNull IResource $this) throws IOException {
            PathExtensionsKt.deleteDirectoryContents($this.getPath());
        }
    }
}
