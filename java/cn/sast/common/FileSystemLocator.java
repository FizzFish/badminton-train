package cn.sast.common;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FileSystemLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018�� \u001c2\u00020\u0001:\u0002\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001d"}, d2 = {"Lcn/sast/common/FileSystemLocator;", "", "<init>", "()V", "visitedArchive", "", "Ljava/nio/file/Path;", "process", "", "path", "traverseMode", "Lcn/sast/common/FileSystemLocator$TraverseMode;", "(Ljava/nio/file/Path;Lcn/sast/common/FileSystemLocator$TraverseMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visit", "res", "Lcn/sast/common/WalkFileTreeResult;", "(Lcn/sast/common/WalkFileTreeResult;Lcn/sast/common/FileSystemLocator$TraverseMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "traverseArchive", "archiveLike", "Lcn/sast/common/IResFile;", "visitFile", "file", "visitArchive", "", "visitDirectory", "dir", "Lcn/sast/common/IResDirectory;", "TraverseMode", "Companion", "corax-api"})
/* loaded from: FileSystemLocator.class */
public abstract class FileSystemLocator {

    @NotNull
    private final Set<Path> visitedArchive;

    @NotNull
    private static final LoadingCache<Path, Deferred<WalkFileTreeResult>> walkTreeCache;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(FileSystemLocator::logger$lambda$3);

    /* compiled from: FileSystemLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "FileSystemLocator.kt", l = {29, 29}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"traverseMode"}, m = "process", c = "cn.sast.common.FileSystemLocator")
    /* renamed from: cn.sast.common.FileSystemLocator$process$1, reason: invalid class name */
    /* loaded from: FileSystemLocator$process$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileSystemLocator.this.process(null, null, (Continuation) this);
        }
    }

    public FileSystemLocator() {
        Set<Path> setSynchronizedSet = Collections.synchronizedSet(new HashSet(1000));
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet, "synchronizedSet(...)");
        this.visitedArchive = setSynchronizedSet;
    }

    /* compiled from: FileSystemLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcn/sast/common/FileSystemLocator$TraverseMode;", "", "processArchive", "", "<init>", "(Ljava/lang/String;IZ)V", "getProcessArchive", "()Z", "Default", "IndexArchive", "RecursivelyIndexArchive", "corax-api"})
    /* loaded from: FileSystemLocator$TraverseMode.class */
    public enum TraverseMode {
        Default(false),
        IndexArchive(true),
        RecursivelyIndexArchive(true);

        private final boolean processArchive;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        TraverseMode(boolean processArchive) {
            this.processArchive = processArchive;
        }

        public final boolean getProcessArchive() {
            return this.processArchive;
        }

        @NotNull
        public static EnumEntries<TraverseMode> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object process(@org.jetbrains.annotations.NotNull java.nio.file.Path r8, @org.jetbrains.annotations.NotNull cn.sast.common.FileSystemLocator.TraverseMode r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            r0 = r10
            boolean r0 = r0 instanceof cn.sast.common.FileSystemLocator.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r10
            cn.sast.common.FileSystemLocator$process$1 r0 = (cn.sast.common.FileSystemLocator.AnonymousClass1) r0
            r13 = r0
            r0 = r13
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r13
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.common.FileSystemLocator$process$1 r0 = new cn.sast.common.FileSystemLocator$process$1
            r1 = r0
            r2 = r7
            r3 = r10
            r1.<init>(r3)
            r13 = r0
        L32:
            r0 = r13
            java.lang.Object r0 = r0.result
            r12 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r14 = r0
            r0 = r13
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L8e;
                case 2: goto Lcf;
                default: goto Ldb;
            }
        L5c:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            r11 = r0
            cn.sast.common.FileSystemLocator$Companion r0 = cn.sast.common.FileSystemLocator.Companion
            r1 = r8
            kotlinx.coroutines.Deferred r0 = r0.getWalkTreeResultSafe(r1)
            r1 = r13
            r2 = r13
            r3 = r9
            r2.L$0 = r3
            r2 = r13
            r3 = r11
            r2.L$1 = r3
            r2 = r13
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.await(r1)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto La8
            r1 = r14
            return r1
        L8e:
            r0 = r13
            java.lang.Object r0 = r0.L$1
            cn.sast.common.FileSystemLocator r0 = (cn.sast.common.FileSystemLocator) r0
            r11 = r0
            r0 = r13
            java.lang.Object r0 = r0.L$0
            cn.sast.common.FileSystemLocator$TraverseMode r0 = (cn.sast.common.FileSystemLocator.TraverseMode) r0
            r9 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        La8:
            r1 = r11
            r2 = r0; r0 = r1; r1 = r2; 
            cn.sast.common.WalkFileTreeResult r1 = (cn.sast.common.WalkFileTreeResult) r1
            r2 = r9
            r3 = r13
            r4 = r13
            r5 = 0
            r4.L$0 = r5
            r4 = r13
            r5 = 0
            r4.L$1 = r5
            r4 = r13
            r5 = 2
            r4.label = r5
            java.lang.Object r0 = r0.visit(r1, r2, r3)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Ld6
            r1 = r14
            return r1
        Lcf:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Ld6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ldb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.FileSystemLocator.process(java.nio.file.Path, cn.sast.common.FileSystemLocator$TraverseMode, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object visit(WalkFileTreeResult res, TraverseMode traverseMode, Continuation<? super Unit> continuation) {
        List archives = new ArrayList();
        for (Path it : res.getFiles()) {
            IResFile file = Resource.INSTANCE.fileOf(it);
            visitFile(file);
            if (traverseMode.getProcessArchive() && file.getZipLike()) {
                archives.add(file);
            }
        }
        for (Path dir : res.getDirs()) {
            visitDirectory(Resource.INSTANCE.dirOf(dir));
        }
        if (!archives.isEmpty()) {
            Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(archives, this, traverseMode, null), continuation);
            return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* compiled from: FileSystemLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "FileSystemLocator.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.common.FileSystemLocator$visit$2")
    @SourceDebugExtension({"SMAP\nFileSystemLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystemLocator.kt\ncn/sast/common/FileSystemLocator$visit$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,193:1\n1863#2,2:194\n*S KotlinDebug\n*F\n+ 1 FileSystemLocator.kt\ncn/sast/common/FileSystemLocator$visit$2\n*L\n47#1:194,2\n*E\n"})
    /* renamed from: cn.sast.common.FileSystemLocator$visit$2, reason: invalid class name */
    /* loaded from: FileSystemLocator$visit$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ List<IResFile> $archives;
        final /* synthetic */ FileSystemLocator this$0;
        final /* synthetic */ TraverseMode $traverseMode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<IResFile> list, FileSystemLocator $receiver, TraverseMode $traverseMode, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$archives = list;
            this.this$0 = $receiver;
            this.$traverseMode = $traverseMode;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$archives, this.this$0, this.$traverseMode, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Iterable $this$forEach$iv = this.$archives;
                    FileSystemLocator fileSystemLocator = this.this$0;
                    TraverseMode traverseMode = this.$traverseMode;
                    for (Object element$iv : $this$forEach$iv) {
                        IResFile archiveLike = (IResFile) element$iv;
                        if (fileSystemLocator.visitedArchive.add(archiveLike.getPath()) && fileSystemLocator.visitArchive(archiveLike)) {
                            BuildersKt.launch$default($this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new FileSystemLocator$visit$2$1$1(traverseMode, fileSystemLocator, archiveLike, null), 3, (Object) null);
                        }
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WalkFileTreeResult traverseArchive(IResFile archiveLike) {
        List files = new ArrayList();
        List dirs = new ArrayList();
        files.add(archiveLike.getPath());
        int errorCnt = 0;
        Set entries = archiveLike.getEntries();
        Iterator<String> it = entries.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String entry = it.next();
            try {
                Path innerPath = Resource.INSTANCE.archivePath(archiveLike.getPath(), entry);
                if (PathExtensionsKt.isDirectory(innerPath)) {
                    dirs.add(innerPath);
                } else {
                    files.add(innerPath);
                }
            } catch (Exception e) {
                logger.error(() -> {
                    return traverseArchive$lambda$0(r1, r2, r3);
                });
                logger.debug(e, () -> {
                    return traverseArchive$lambda$1(r2, r3);
                });
                int i = errorCnt;
                errorCnt++;
                if (i >= 10) {
                    logger.error(() -> {
                        return traverseArchive$lambda$2(r1);
                    });
                    break;
                }
            }
        }
        return new WalkFileTreeResult(archiveLike.getPath(), files, dirs);
    }

    private static final Object traverseArchive$lambda$0(IResFile $archiveLike, String $entry, Exception $e) {
        return "invalid inner zip file: `" + $archiveLike + "!/" + $entry + "` " + $e.getClass() + " " + $e.getMessage();
    }

    private static final Object traverseArchive$lambda$1(IResFile $archiveLike, String $entry) {
        return "invalid inner zip file: `" + $archiveLike + "!/" + $entry + "`";
    }

    private static final Object traverseArchive$lambda$2(IResFile $archiveLike) {
        return "Skip invalid zip file: `" + $archiveLike + "`";
    }

    public void visitFile(@NotNull IResFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
    }

    public boolean visitArchive(@NotNull IResFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            if (Intrinsics.areEqual(file.getExtension(), "apk") || MainConfig.Companion.getExcludeFiles().contains(file.getName()) || file.resolve("java.base/module-info.java").getExists() || file.resolve("jdk.zipfs/module-info.java").getExists()) {
                return false;
            }
            if (!file.getZipLike() || !file.resolve("AndroidManifest.xml").getExists()) {
                return true;
            }
            if (!Intrinsics.areEqual(file.getExtension(), "aar")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public void visitDirectory(@NotNull IResDirectory dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
    }

    /* compiled from: FileSystemLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0010"}, d2 = {"Lcn/sast/common/FileSystemLocator$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getWalkTreeResultSafe", "Lkotlinx/coroutines/Deferred;", "Lcn/sast/common/WalkFileTreeResult;", "path", "Ljava/nio/file/Path;", "walkTreeCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "visit", "clear", "", "corax-api"})
    /* loaded from: FileSystemLocator$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Deferred<WalkFileTreeResult> getWalkTreeResultSafe(@NotNull Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            Path absolutePath = path.toAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
            Path normalPath = absolutePath.normalize();
            Object obj = FileSystemLocator.walkTreeCache.get(normalPath);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return (Deferred) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final WalkFileTreeResult visit(final Path path) throws IOException {
            final List files = new ArrayList();
            final List dirs = new ArrayList();
            final Ref.BooleanRef containsSelf = new Ref.BooleanRef();
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() { // from class: cn.sast.common.FileSystemLocator$Companion$visit$1
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Intrinsics.checkNotNullParameter(file, "file");
                    Intrinsics.checkNotNullParameter(attrs, "attrs");
                    if (Intrinsics.areEqual(file, path)) {
                        containsSelf.element = true;
                    }
                    files.add(file);
                    return FileVisitResult.CONTINUE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:9:0x007c, code lost:
                
                    if (java.nio.file.Files.exists(r0, (java.nio.file.LinkOption[]) java.util.Arrays.copyOf(r0, r0.length)) != false) goto L10;
                 */
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.nio.file.FileVisitResult preVisitDirectory(java.nio.file.Path r7, java.nio.file.attribute.BasicFileAttributes r8) throws java.io.IOException {
                    /*
                        r6 = this;
                        r0 = r7
                        java.lang.String r1 = "dir"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                        r0 = r7
                        java.lang.String r0 = kotlin.io.path.PathsKt.getName(r0)
                        r9 = r0
                        r0 = r9
                        java.lang.String r1 = "java."
                        r2 = 0
                        r3 = 2
                        r4 = 0
                        boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4)
                        if (r0 != 0) goto L23
                        r0 = r9
                        java.lang.String r1 = "jdk."
                        r2 = 0
                        r3 = 2
                        r4 = 0
                        boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4)
                        if (r0 == 0) goto L83
                    L23:
                        r0 = r7
                        java.nio.file.Path r0 = r0.getParent()
                        r10 = r0
                        r0 = r10
                        java.lang.String r1 = "java.base"
                        java.nio.file.Path r0 = r0.resolve(r1)
                        r1 = r0
                        java.lang.String r2 = "resolve(...)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                        r11 = r0
                        r0 = 0
                        java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]
                        r12 = r0
                        r0 = r11
                        r1 = r12
                        r2 = r12
                        int r2 = r2.length
                        java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
                        java.nio.file.LinkOption[] r1 = (java.nio.file.LinkOption[]) r1
                        boolean r0 = java.nio.file.Files.exists(r0, r1)
                        if (r0 != 0) goto L7f
                        r0 = r10
                        java.lang.String r1 = "jdk.zipfs"
                        java.nio.file.Path r0 = r0.resolve(r1)
                        r1 = r0
                        java.lang.String r2 = "resolve(...)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                        r11 = r0
                        r0 = 0
                        java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]
                        r12 = r0
                        r0 = r11
                        r1 = r12
                        r2 = r12
                        int r2 = r2.length
                        java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
                        java.nio.file.LinkOption[] r1 = (java.nio.file.LinkOption[]) r1
                        boolean r0 = java.nio.file.Files.exists(r0, r1)
                        if (r0 == 0) goto L83
                    L7f:
                        java.nio.file.FileVisitResult r0 = java.nio.file.FileVisitResult.SKIP_SUBTREE
                        return r0
                    L83:
                        r0 = r6
                        r1 = r7
                        r2 = r8
                        java.nio.file.FileVisitResult r0 = super.preVisitDirectory(r1, r2)
                        r1 = r0
                        java.lang.String r2 = "preVisitDirectory(...)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.FileSystemLocator$Companion$visit$1.preVisitDirectory(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes):java.nio.file.FileVisitResult");
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    Intrinsics.checkNotNullParameter(dir, "dir");
                    if (Intrinsics.areEqual(dir, path)) {
                        containsSelf.element = true;
                    }
                    dirs.add(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            if (!containsSelf.element) {
                dirs.add(path);
            }
            return new WalkFileTreeResult(path, files, dirs);
        }

        public final void clear() {
            FileSystemLocator.walkTreeCache.cleanUp();
        }
    }

    private static final Unit logger$lambda$3() {
        return Unit.INSTANCE;
    }

    static {
        LoadingCache<Path, Deferred<WalkFileTreeResult>> loadingCacheBuild = Caffeine.newBuilder().expireAfterAccess(Duration.ofSeconds(15L)).build(new CacheLoader<Path, Deferred<? extends WalkFileTreeResult>>() { // from class: cn.sast.common.FileSystemLocator$Companion$walkTreeCache$1
            public Deferred<WalkFileTreeResult> load(Path path) {
                Intrinsics.checkNotNullParameter(path, "path");
                return BuildersKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new FileSystemLocator$Companion$walkTreeCache$1$load$1(path, null), 3, (Object) null);
            }
        });
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild, "build(...)");
        walkTreeCache = loadingCacheBuild;
    }
}
