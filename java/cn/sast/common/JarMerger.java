package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.google.common.collect.ImmutableSortedMap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.Map;
import java.util.function.Predicate;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JarMerger.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� )2\u00020\u0001:\u0003'()B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0003J:\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007J.\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00032\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0003J\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0010H\u0016J\u001a\u0010\u001f\u001a\u00020\u00102\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060!J\u0018\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001dH\u0002J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$H\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n��¨\u0006*"}, d2 = {"Lcn/sast/common/JarMerger;", "Ljava/io/Closeable;", "jarFile", "Ljava/nio/file/Path;", "filter", "Ljava/util/function/Predicate;", "", "<init>", "(Ljava/nio/file/Path;Ljava/util/function/Predicate;)V", "buffer", "", "jarOutputStream", "Lorg/apache/commons/compress/archivers/jar/JarArchiveOutputStream;", "toSystemIndependentPath", "path", "addDirectory", "", "directory", "filterOverride", "transformer", "Lcn/sast/common/JarMerger$Transformer;", "relocator", "Lcn/sast/common/JarMerger$Relocator;", "addJar", "file", "addFile", "entryPath", "addEntry", "input", "Ljava/io/InputStream;", "close", "setManifestProperties", "properties", "", "write", "entry", "Lorg/apache/commons/compress/archivers/jar/JarArchiveEntry;", "from", "setEntryAttributes", "Transformer", "Relocator", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nJarMerger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JarMerger.kt\ncn/sast/common/JarMerger\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,200:1\n1#2:201\n216#3,2:202\n*S KotlinDebug\n*F\n+ 1 JarMerger.kt\ncn/sast/common/JarMerger\n*L\n148#1:202,2\n*E\n"})
/* loaded from: JarMerger.class */
public final class JarMerger implements Closeable {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final Predicate<String> filter;

    @NotNull
    private final byte[] buffer;

    @NotNull
    private final JarArchiveOutputStream jarOutputStream;

    @NotNull
    private static final FileTime fileTime;

    /* compiled from: JarMerger.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcn/sast/common/JarMerger$Relocator;", "", "relocate", "", "entryPath", "corax-api"})
    /* loaded from: JarMerger$Relocator.class */
    public interface Relocator {
        @NotNull
        String relocate(@NotNull String str);
    }

    /* compiled from: JarMerger.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcn/sast/common/JarMerger$Transformer;", "", "filter", "Ljava/io/InputStream;", "entryPath", "", "input", "corax-api"})
    /* loaded from: JarMerger$Transformer.class */
    public interface Transformer {
        @Nullable
        InputStream filter(@NotNull String str, @NotNull InputStream inputStream);
    }

    @JvmOverloads
    public JarMerger(@NotNull Path jarFile, @Nullable Predicate<String> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(jarFile, "jarFile");
        this.filter = predicate;
        this.buffer = new byte[8192];
        Files.createDirectories(jarFile.getParent(), new FileAttribute[0]);
        this.jarOutputStream = new JarArchiveOutputStream(new BufferedOutputStream(Files.newOutputStream(jarFile, new OpenOption[0])));
    }

    public /* synthetic */ JarMerger(Path path, Predicate predicate, int i, DefaultConstructorMarker defaultConstructorMarker) throws IOException {
        this(path, (i & 2) != 0 ? null : predicate);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public JarMerger(@NotNull Path jarFile) throws IOException {
        this(jarFile, null, 2, null);
        Intrinsics.checkNotNullParameter(jarFile, "jarFile");
    }

    @NotNull
    public final String toSystemIndependentPath(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        String filePath = path.toString();
        if (!Intrinsics.areEqual(path.getFileSystem().getSeparator(), "/")) {
            String separator = path.getFileSystem().getSeparator();
            Intrinsics.checkNotNullExpressionValue(separator, "getSeparator(...)");
            return StringsKt.replace$default(filePath, separator, "/", false, 4, (Object) null);
        }
        return filePath;
    }

    public static /* synthetic */ void addDirectory$default(JarMerger jarMerger, Path path, Predicate predicate, Transformer transformer, Relocator relocator, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            predicate = jarMerger.filter;
        }
        if ((i & 4) != 0) {
            transformer = null;
        }
        if ((i & 8) != 0) {
            relocator = null;
        }
        jarMerger.addDirectory(path, predicate, transformer, relocator);
    }

    @JvmOverloads
    public final void addDirectory(@NotNull final Path directory, @Nullable final Predicate<String> predicate, @Nullable Transformer transformer, @Nullable final Relocator relocator) throws IOException {
        Intrinsics.checkNotNullParameter(directory, "directory");
        final ImmutableSortedMap.Builder candidateFiles = ImmutableSortedMap.naturalOrder();
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() { // from class: cn.sast.common.JarMerger.addDirectory.1
            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                Intrinsics.checkNotNullParameter(file, "file");
                Intrinsics.checkNotNullParameter(attrs, "attrs");
                JarMerger jarMerger = JarMerger.this;
                Path pathRelativize = directory.relativize(file);
                Intrinsics.checkNotNullExpressionValue(pathRelativize, "relativize(...)");
                String entryPath = jarMerger.toSystemIndependentPath(pathRelativize);
                if (predicate != null && !predicate.test(entryPath)) {
                    return FileVisitResult.CONTINUE;
                }
                if (relocator != null) {
                    entryPath = relocator.relocate(entryPath);
                }
                candidateFiles.put(entryPath, file);
                return FileVisitResult.CONTINUE;
            }
        });
        Map mapBuild = candidateFiles.build();
        Intrinsics.checkNotNull(mapBuild);
        for (Map.Entry entry : mapBuild.entrySet()) {
            String entryPath = (String) entry.getKey();
            Path value = (Path) entry.getValue();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(value, new OpenOption[0]));
            Throwable th = null;
            try {
                try {
                    BufferedInputStream is = bufferedInputStream;
                    if (transformer != null) {
                        Intrinsics.checkNotNull(entryPath);
                        InputStream is2 = transformer.filter(entryPath, is);
                        if (is2 != null) {
                            write(new JarArchiveEntry(entryPath), is2);
                        }
                    } else {
                        write(new JarArchiveEntry(entryPath), is);
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(bufferedInputStream, (Throwable) null);
                } finally {
                }
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedInputStream, th);
                throw th2;
            }
        }
    }

    @JvmOverloads
    public final void addDirectory(@NotNull Path directory, @Nullable Predicate<String> predicate, @Nullable Transformer transformer) throws IOException {
        Intrinsics.checkNotNullParameter(directory, "directory");
        addDirectory$default(this, directory, predicate, transformer, null, 8, null);
    }

    @JvmOverloads
    public final void addDirectory(@NotNull Path directory, @Nullable Predicate<String> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(directory, "directory");
        addDirectory$default(this, directory, predicate, null, null, 12, null);
    }

    @JvmOverloads
    public final void addDirectory(@NotNull Path directory) throws IOException {
        Intrinsics.checkNotNullParameter(directory, "directory");
        addDirectory$default(this, directory, null, null, null, 14, null);
    }

    public static /* synthetic */ void addJar$default(JarMerger jarMerger, Path path, Predicate predicate, Relocator relocator, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            predicate = jarMerger.filter;
        }
        if ((i & 4) != 0) {
            relocator = null;
        }
        jarMerger.addJar(path, predicate, relocator);
    }

    @JvmOverloads
    public final void addJar(@NotNull Path file, @Nullable Predicate<String> predicate, @Nullable Relocator relocator) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        JarArchiveInputStream jarArchiveInputStream = (Closeable) new JarArchiveInputStream(Files.newInputStream(file, new OpenOption[0]));
        Throwable th = null;
        try {
            try {
                JarArchiveInputStream zis = jarArchiveInputStream;
                while (true) {
                    ZipArchiveEntry entry = zis.getNextZipEntry();
                    if (entry != null) {
                        if (!entry.isDirectory()) {
                            String name = entry.getName();
                            if (predicate == null || predicate.test(name)) {
                                if (relocator != null) {
                                    Intrinsics.checkNotNull(name);
                                    name = relocator.relocate(name);
                                }
                                JarArchiveEntry newEntry = new JarArchiveEntry(name);
                                newEntry.setMethod(entry.getMethod());
                                if (newEntry.getMethod() == ZipMethod.STORED.getCode()) {
                                    newEntry.setSize(entry.getSize());
                                    newEntry.setCompressedSize(entry.getCompressedSize());
                                    newEntry.setCrc(entry.getCrc());
                                }
                                newEntry.setLastModifiedTime(FileTime.fromMillis(0L));
                                write(newEntry, (InputStream) zis);
                            }
                        }
                    } else {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(jarArchiveInputStream, (Throwable) null);
                        return;
                    }
                }
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(jarArchiveInputStream, th);
            throw th2;
        }
    }

    @JvmOverloads
    public final void addJar(@NotNull Path file, @Nullable Predicate<String> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        addJar$default(this, file, predicate, null, 4, null);
    }

    @JvmOverloads
    public final void addJar(@NotNull Path file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        addJar$default(this, file, null, null, 6, null);
    }

    public final void addFile(@NotNull String entryPath, @NotNull Path file) throws IOException {
        Intrinsics.checkNotNullParameter(entryPath, "entryPath");
        Intrinsics.checkNotNullParameter(file, "file");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file, new OpenOption[0]));
        Throwable th = null;
        try {
            try {
                BufferedInputStream is = bufferedInputStream;
                write(new JarArchiveEntry(entryPath), is);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedInputStream, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(bufferedInputStream, th);
            throw th2;
        }
    }

    public final void addEntry(@NotNull String entryPath, @NotNull InputStream input) throws IOException {
        Intrinsics.checkNotNullParameter(entryPath, "entryPath");
        Intrinsics.checkNotNullParameter(input, "input");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
        Throwable th = null;
        try {
            try {
                BufferedInputStream is = bufferedInputStream;
                write(new JarArchiveEntry(entryPath), is);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedInputStream, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(bufferedInputStream, th);
            throw th2;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.jarOutputStream.close();
    }

    public final void setManifestProperties(@NotNull Map<String, String> map) throws IOException {
        Intrinsics.checkNotNullParameter(map, "properties");
        Manifest manifest = new Manifest();
        Attributes global = manifest.getMainAttributes();
        Intrinsics.checkNotNull(global);
        global.put(Attributes.Name.MANIFEST_VERSION, "1.0.0");
        for (Map.Entry element$iv : map.entrySet()) {
            String attributeName = element$iv.getKey();
            String attributeValue = element$iv.getValue();
            global.put(new Attributes.Name(attributeName), attributeValue);
        }
        ArchiveEntry jarArchiveEntry = new JarArchiveEntry("META-INF/MANIFEST.MF");
        setEntryAttributes(jarArchiveEntry);
        this.jarOutputStream.putArchiveEntry(jarArchiveEntry);
        try {
            manifest.write((OutputStream) this.jarOutputStream);
            this.jarOutputStream.closeArchiveEntry();
        } catch (Throwable th) {
            this.jarOutputStream.closeArchiveEntry();
            throw th;
        }
    }

    private final void write(JarArchiveEntry entry, InputStream from) throws IOException {
        setEntryAttributes(entry);
        this.jarOutputStream.putArchiveEntry((ArchiveEntry) entry);
        while (true) {
            int count = from.read(this.buffer);
            if (count != -1) {
                this.jarOutputStream.write(this.buffer, 0, count);
            } else {
                this.jarOutputStream.closeArchiveEntry();
                return;
            }
        }
    }

    private final void setEntryAttributes(JarArchiveEntry entry) {
        entry.setLastModifiedTime(fileTime);
        entry.setLastAccessTime(fileTime);
        entry.setCreationTime(fileTime);
    }

    /* compiled from: JarMerger.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/common/JarMerger$Companion;", "", "<init>", "()V", "fileTime", "Ljava/nio/file/attribute/FileTime;", "getFileTime", "()Ljava/nio/file/attribute/FileTime;", "corax-api"})
    /* loaded from: JarMerger$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final FileTime getFileTime() {
            return JarMerger.fileTime;
        }
    }

    static {
        FileTime fileTimeFromMillis = FileTime.fromMillis(0L);
        Intrinsics.checkNotNullExpressionValue(fileTimeFromMillis, "fromMillis(...)");
        fileTime = fileTimeFromMillis;
    }
}
