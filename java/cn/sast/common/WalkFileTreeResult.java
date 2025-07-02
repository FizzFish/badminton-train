package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.nio.file.Path;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: FileSystemLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\t\u0018��2\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lcn/sast/common/WalkFileTreeResult;", "", "root", "Ljava/nio/file/Path;", "files", "", "dirs", "<init>", "(Ljava/nio/file/Path;Ljava/util/List;Ljava/util/List;)V", "getRoot", "()Ljava/nio/file/Path;", "getFiles", "()Ljava/util/List;", "getDirs", "corax-api"})
/* loaded from: WalkFileTreeResult.class */
public final class WalkFileTreeResult {

    @NotNull
    private final Path root;

    @NotNull
    private final List<Path> files;

    @NotNull
    private final List<Path> dirs;

    /* JADX WARN: Multi-variable type inference failed */
    public WalkFileTreeResult(@NotNull Path root, @NotNull List<? extends Path> list, @NotNull List<? extends Path> list2) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(list, "files");
        Intrinsics.checkNotNullParameter(list2, "dirs");
        this.root = root;
        this.files = list;
        this.dirs = list2;
    }

    @NotNull
    public final Path getRoot() {
        return this.root;
    }

    @NotNull
    public final List<Path> getFiles() {
        return this.files;
    }

    @NotNull
    public final List<Path> getDirs() {
        return this.dirs;
    }
}
