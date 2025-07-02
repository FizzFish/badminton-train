package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: FileSystemsOperations.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0004\u0018�� \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcn/sast/common/FileSystemsOperations;", "", "<init>", "()V", "Companion", "corax-api"})
/* loaded from: FileSystemsOperations.class */
public final class FileSystemsOperations {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: FileSystemsOperations.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0003J \u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u001e\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0007¨\u0006\u0015"}, d2 = {"Lcn/sast/common/FileSystemsOperations$Companion;", "", "<init>", "()V", "copyPath", "Ljava/nio/file/Path;", "it", "src", "dest", "mapToDestination", "path", "srcDir", "destDir", "copyDirRecursivelyToDirInDifferentFileSystem", "", "dir", "copyDirContentsRecursivelyToDirInDifferentFileSystem", "copyFilesToDirInDifferentFileSystem", "files", "", "copyDirContentsRecursivelyToDirInSameFileSystem", "corax-api"})
    @SourceDebugExtension({"SMAP\nFileSystemsOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystemsOperations.kt\ncn/sast/common/FileSystemsOperations$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,84:1\n1863#2,2:85\n1863#2,2:87\n*S KotlinDebug\n*F\n+ 1 FileSystemsOperations.kt\ncn/sast/common/FileSystemsOperations$Companion\n*L\n62#1:85,2\n69#1:87,2\n*E\n"})
    /* loaded from: FileSystemsOperations$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Path copyPath(Path it, Path src, Path dest) throws IOException {
            boolean zIsDirectory = Files.isDirectory(src, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory) {
                throw new AssertionError("Assertion failed");
            }
            boolean zIsDirectory2 = Files.isDirectory(dest, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory2) {
                throw new AssertionError("Assertion failed");
            }
            Path itInDest = mapToDestination(it, src, dest);
            if (!Intrinsics.areEqual(itInDest, dest)) {
                boolean z = !Files.exists(itInDest, new LinkOption[0]);
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
                if (Files.isDirectory(it, new LinkOption[0])) {
                    Files.createDirectory(itInDest, new FileAttribute[0]);
                } else if (Files.isRegularFile(it, new LinkOption[0])) {
                    Files.copy(it, itInDest, new CopyOption[0]);
                } else {
                    if (_Assertions.ENABLED) {
                        throw new AssertionError("Assertion failed");
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return itInDest;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Path mapToDestination(Path path, Path srcDir, Path destDir) {
            String string = srcDir.relativize(path).toString();
            String separator = srcDir.getFileSystem().getSeparator();
            Intrinsics.checkNotNullExpressionValue(separator, "getSeparator(...)");
            String separator2 = destDir.getFileSystem().getSeparator();
            Intrinsics.checkNotNullExpressionValue(separator2, "getSeparator(...)");
            Path pathResolve = destDir.resolve(StringsKt.replace$default(string, separator, separator2, false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
            return pathResolve;
        }

        @JvmStatic
        public final void copyDirRecursivelyToDirInDifferentFileSystem(@NotNull Path dir, @NotNull Path dest) {
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intrinsics.checkNotNullParameter(dest, "dest");
            boolean zIsDirectory = Files.isDirectory(dir, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory) {
                throw new AssertionError("Assertion failed");
            }
            boolean zIsDirectory2 = Files.isDirectory(dest, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory2) {
                throw new AssertionError("Assertion failed");
            }
            boolean z = !Intrinsics.areEqual(dir.getFileSystem(), dest.getFileSystem());
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            boolean z2 = dir.getParent() != null;
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError("Assertion failed");
            }
            Files.walk(dir, new FileVisitOption[0]).forEach(new FileSystemsOperations$sam$java_util_function_Consumer$0((v2) -> {
                return copyDirRecursivelyToDirInDifferentFileSystem$lambda$0(r1, r2, v2);
            }));
        }

        private static final Unit copyDirRecursivelyToDirInDifferentFileSystem$lambda$0(Path $dir, Path $dest, Path it) throws IOException {
            Companion companion = FileSystemsOperations.Companion;
            Intrinsics.checkNotNull(it);
            Path parent = $dir.getParent();
            Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
            companion.copyPath(it, parent, $dest);
            return Unit.INSTANCE;
        }

        @JvmStatic
        public final void copyDirContentsRecursivelyToDirInDifferentFileSystem(@NotNull Path dir, @NotNull Path dest) {
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intrinsics.checkNotNullParameter(dest, "dest");
            boolean zIsDirectory = Files.isDirectory(dir, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory) {
                throw new AssertionError("Assertion failed");
            }
            boolean zIsDirectory2 = Files.isDirectory(dest, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory2) {
                throw new AssertionError("Assertion failed");
            }
            boolean z = !Intrinsics.areEqual(dir.getFileSystem(), dest.getFileSystem());
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            Files.walk(dir, new FileVisitOption[0]).forEach(new FileSystemsOperations$sam$java_util_function_Consumer$0((v2) -> {
                return copyDirContentsRecursivelyToDirInDifferentFileSystem$lambda$1(r1, r2, v2);
            }));
        }

        private static final Unit copyDirContentsRecursivelyToDirInDifferentFileSystem$lambda$1(Path $dir, Path $dest, Path it) throws IOException {
            Companion companion = FileSystemsOperations.Companion;
            Intrinsics.checkNotNull(it);
            companion.copyPath(it, $dir, $dest);
            return Unit.INSTANCE;
        }

        @JvmStatic
        public final void copyFilesToDirInDifferentFileSystem(@NotNull List<? extends Path> list, @NotNull Path dest) throws IOException {
            Intrinsics.checkNotNullParameter(list, "files");
            Intrinsics.checkNotNullParameter(dest, "dest");
            boolean zIsDirectory = Files.isDirectory(dest, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory) {
                throw new AssertionError("Assertion failed");
            }
            List<? extends Path> $this$forEach$iv = list;
            for (Object element$iv : $this$forEach$iv) {
                Path it = (Path) element$iv;
                boolean zIsRegularFile = Files.isRegularFile(it, new LinkOption[0]);
                if (_Assertions.ENABLED && !zIsRegularFile) {
                    throw new AssertionError("Assertion failed");
                }
                boolean z = it.getParent() != null;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
                boolean zIsDirectory2 = Files.isDirectory(it.getParent(), new LinkOption[0]);
                if (_Assertions.ENABLED && !zIsDirectory2) {
                    throw new AssertionError("Assertion failed");
                }
                boolean z2 = !Intrinsics.areEqual(it.getFileSystem(), dest.getFileSystem());
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Assertion failed");
                }
            }
            List<? extends Path> $this$forEach$iv2 = list;
            for (Object element$iv2 : $this$forEach$iv2) {
                Path it2 = (Path) element$iv2;
                Companion companion = FileSystemsOperations.Companion;
                Path parent = it2.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
                companion.copyPath(it2, parent, dest);
            }
        }

        @JvmStatic
        public final void copyDirContentsRecursivelyToDirInSameFileSystem(@NotNull Path dir, @NotNull Path dest) {
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intrinsics.checkNotNullParameter(dest, "dest");
            boolean zIsDirectory = Files.isDirectory(dir, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory) {
                throw new AssertionError("Assertion failed");
            }
            boolean zIsDirectory2 = Files.isDirectory(dest, new LinkOption[0]);
            if (_Assertions.ENABLED && !zIsDirectory2) {
                throw new AssertionError("Assertion failed");
            }
            boolean zAreEqual = Intrinsics.areEqual(dir.getFileSystem(), dest.getFileSystem());
            if (_Assertions.ENABLED && !zAreEqual) {
                throw new AssertionError("Assertion failed");
            }
            Files.walk(dir, new FileVisitOption[0]).forEach(new FileSystemsOperations$sam$java_util_function_Consumer$0((v2) -> {
                return copyDirContentsRecursivelyToDirInSameFileSystem$lambda$4(r1, r2, v2);
            }));
        }

        private static final Unit copyDirContentsRecursivelyToDirInSameFileSystem$lambda$4(Path $dir, Path $dest, Path it) throws IOException {
            Companion companion = FileSystemsOperations.Companion;
            Intrinsics.checkNotNull(it);
            companion.copyPath(it, $dir, $dest);
            return Unit.INSTANCE;
        }
    }

    @JvmStatic
    private static final Path copyPath(Path it, Path src, Path dest) {
        return Companion.copyPath(it, src, dest);
    }

    @JvmStatic
    private static final Path mapToDestination(Path path, Path srcDir, Path destDir) {
        return Companion.mapToDestination(path, srcDir, destDir);
    }

    @JvmStatic
    public static final void copyDirRecursivelyToDirInDifferentFileSystem(@NotNull Path dir, @NotNull Path dest) {
        Companion.copyDirRecursivelyToDirInDifferentFileSystem(dir, dest);
    }

    @JvmStatic
    public static final void copyDirContentsRecursivelyToDirInDifferentFileSystem(@NotNull Path dir, @NotNull Path dest) {
        Companion.copyDirContentsRecursivelyToDirInDifferentFileSystem(dir, dest);
    }

    @JvmStatic
    public static final void copyFilesToDirInDifferentFileSystem(@NotNull List<? extends Path> list, @NotNull Path dest) throws IOException {
        Companion.copyFilesToDirInDifferentFileSystem(list, dest);
    }

    @JvmStatic
    public static final void copyDirContentsRecursivelyToDirInSameFileSystem(@NotNull Path dir, @NotNull Path dest) {
        Companion.copyDirContentsRecursivelyToDirInSameFileSystem(dir, dest);
    }
}
