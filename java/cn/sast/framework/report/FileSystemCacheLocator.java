package cn.sast.framework.report;

import cn.sast.common.FileSystemLocator;
import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\bH\u0002J$\u0010\u0011\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016R5\u0010\u0004\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0005¢\u0006\u000e\n��\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/report/FileSystemCacheLocator;", "", "<init>", "()V", "cache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lkotlin/Pair;", "Lcn/sast/common/IResource;", "Lcn/sast/common/FileSystemLocator$TraverseMode;", "Lkotlinx/coroutines/Deferred;", "Lcn/sast/framework/report/FileIndexer;", "getCache$annotations", "getCache", "()Lcom/github/benmanes/caffeine/cache/LoadingCache;", "getFileIndexer", "res", "traverseMode", "getIndexer", "Lcn/sast/framework/report/FileIndexerBuilder;", "", "(Ljava/util/Set;Lcn/sast/common/FileSystemLocator$TraverseMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clear", "", "corax-framework"})
@SourceDebugExtension({"SMAP\nJavaSourceLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/FileSystemCacheLocator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n1557#2:491\n1628#2,3:492\n1863#2,2:495\n*S KotlinDebug\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/FileSystemCacheLocator\n*L\n312#1:491\n312#1:492,3\n312#1:495,2\n*E\n"})
/* loaded from: FileSystemCacheLocator.class */
public final class FileSystemCacheLocator {

    @NotNull
    public static final FileSystemCacheLocator INSTANCE = new FileSystemCacheLocator();

    @NotNull
    private static final LoadingCache<Pair<IResource, FileSystemLocator.TraverseMode>, Deferred<FileIndexer>> cache;

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {313}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"union"}, m = "getIndexer", c = "cn.sast.framework.report.FileSystemCacheLocator")
    /* renamed from: cn.sast.framework.report.FileSystemCacheLocator$getIndexer$1, reason: invalid class name */
    /* loaded from: FileSystemCacheLocator$getIndexer$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileSystemCacheLocator.this.getIndexer(null, null, (Continuation) this);
        }
    }

    public static /* synthetic */ void getCache$annotations() {
    }

    private FileSystemCacheLocator() {
    }

    @NotNull
    public final LoadingCache<Pair<IResource, FileSystemLocator.TraverseMode>, Deferred<FileIndexer>> getCache() {
        return cache;
    }

    static {
        Caffeine caffeineNewBuilder = Caffeine.newBuilder();
        final Function1 function1 = FileSystemCacheLocator::cache$lambda$0;
        LoadingCache<Pair<IResource, FileSystemLocator.TraverseMode>, Deferred<FileIndexer>> loadingCacheBuild = caffeineNewBuilder.build(new CacheLoader(function1) { // from class: cn.sast.framework.report.JavaSourceLocatorKt$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
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
        cache = loadingCacheBuild;
    }

    private static final Deferred cache$lambda$0(Pair pair) {
        IResource root = (IResource) pair.component1();
        FileSystemLocator.TraverseMode traverseMode = (FileSystemLocator.TraverseMode) pair.component2();
        return BuildersKt.async$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new FileSystemCacheLocator$cache$1$1(root, traverseMode, null), 3, (Object) null);
    }

    private final Deferred<FileIndexer> getFileIndexer(IResource res, FileSystemLocator.TraverseMode traverseMode) {
        Object obj = cache.get(TuplesKt.to(res, traverseMode));
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (Deferred) obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getIndexer(@org.jetbrains.annotations.NotNull java.util.Set<? extends cn.sast.common.IResource> r6, @org.jetbrains.annotations.NotNull cn.sast.common.FileSystemLocator.TraverseMode r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super cn.sast.framework.report.FileIndexerBuilder> r8) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.FileSystemCacheLocator.getIndexer(java.util.Set, cn.sast.common.FileSystemLocator$TraverseMode, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void clear() {
        cache.cleanUp();
    }
}
