package cn.sast.framework;

import cn.sast.api.config.MainConfig;
import cn.sast.common.FileSystemLocator;
import cn.sast.common.IResFile;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import cn.sast.framework.report.ProjectFileLocator;
import com.feysh.corax.cache.XOptional;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.FoundFile;
import soot.IFoundFile;
import soot.Singletons;
import soot.SourceLocator;

/* compiled from: SourceLocatorPlus.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n��R\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r0\tX\u0082\u0004¢\u0006\u0002\n��R\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006!"}, d2 = {"Lcn/sast/framework/SourceLocatorPlus;", "Lsoot/SourceLocator;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "cacheClassNameMap", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Ljava/nio/file/Path;", "", "cacheClassLookMap", "Lcom/feysh/corax/cache/XOptional;", "Lsoot/FoundFile;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "getLocator", "()Lcn/sast/framework/report/ProjectFileLocator;", "locator$delegate", "Lkotlin/Lazy;", "update", "", "getClassNameOf", "cls", "Lcn/sast/common/IResFile;", "isInvalidClassFile", "", "fileName", "lookupInClassPath", "Lsoot/IFoundFile;", "lookupInArchive", "archivePath", "corax-framework"})
@SourceDebugExtension({"SMAP\nSourceLocatorPlus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceLocatorPlus.kt\ncn/sast/framework/SourceLocatorPlus\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,116:1\n1#2:117\n183#3,2:118\n1628#4,3:120\n*S KotlinDebug\n*F\n+ 1 SourceLocatorPlus.kt\ncn/sast/framework/SourceLocatorPlus\n*L\n69#1:118,2\n77#1:120,3\n*E\n"})
/* loaded from: SourceLocatorPlus.class */
public final class SourceLocatorPlus extends SourceLocator {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final LoadingCache<Path, String> cacheClassNameMap;

    @NotNull
    private final LoadingCache<String, XOptional<FoundFile>> cacheClassLookMap;

    @NotNull
    private final Lazy locator$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SourceLocatorPlus(@NotNull MainConfig mainConfig) {
        super((Singletons.Global) null);
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.mainConfig = mainConfig;
        Caffeine $this$cacheClassNameMap_u24lambda_u240 = Caffeine.newBuilder().softValues();
        $this$cacheClassNameMap_u24lambda_u240.initialCapacity(5000);
        final Function1 function1 = SourceLocatorPlus::cacheClassNameMap$lambda$2;
        LoadingCache<Path, String> loadingCacheBuild = $this$cacheClassNameMap_u24lambda_u240.build(new CacheLoader(function1) { // from class: cn.sast.framework.SourceLocatorPlusKt$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
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
        this.cacheClassNameMap = loadingCacheBuild;
        Caffeine $this$cacheClassLookMap_u24lambda_u243 = Caffeine.newBuilder().softValues();
        $this$cacheClassLookMap_u24lambda_u243.initialCapacity(5000);
        final Function1 function12 = (v1) -> {
            return cacheClassLookMap$lambda$7(r2, v1);
        };
        LoadingCache<String, XOptional<FoundFile>> loadingCacheBuild2 = $this$cacheClassLookMap_u24lambda_u243.build(new CacheLoader(function12) { // from class: cn.sast.framework.SourceLocatorPlusKt$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function12, "function");
                this.function = function12;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild2, "build(...)");
        this.cacheClassLookMap = loadingCacheBuild2;
        this.locator$delegate = LazyKt.lazy(() -> {
            return locator_delegate$lambda$10(r1);
        });
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    private static final String cacheClassNameMap$lambda$2(Path cls) throws IOException {
        Intrinsics.checkNotNullParameter(cls, "cls");
        try {
            OpenOption[] openOptionArr = new OpenOption[0];
            InputStream inputStreamNewInputStream = Files.newInputStream(cls, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            InputStream inputStream = inputStreamNewInputStream;
            try {
                InputStream inputStream2 = inputStream;
                String nameOfClassUnsafe = SourceLocator.getNameOfClassUnsafe(inputStream2);
                if (nameOfClassUnsafe == null) {
                    return null;
                }
                String str = StringsKt.replace$default(nameOfClassUnsafe, '.', '/', false, 4, (Object) null) + ".class";
                CloseableKt.closeFinally(inputStream, (Throwable) null);
                return str;
            } finally {
                CloseableKt.closeFinally(inputStream, (Throwable) null);
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static final XOptional cacheClassLookMap$lambda$7(SourceLocatorPlus this$0, String fileName) {
        Object obj;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Sequence $this$firstOrNull$iv = this$0.getLocator().findFromFileIndexMap(StringsKt.split$default(fileName, new String[]{"/"}, false, 0, 6, (Object) null), AbstractFileIndexer.Companion.getDefaultClassCompareMode());
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            if (this$0.isInvalidClassFile(fileName, (IResFile) element$iv)) {
                obj = element$iv;
                break;
            }
        }
        IResFile it2 = (IResFile) obj;
        if (it2 != null) {
            return XOptional.Companion.of(new FoundFile(it2.getPath()));
        }
        return null;
    }

    @NotNull
    public final ProjectFileLocator getLocator() {
        return (ProjectFileLocator) this.locator$delegate.getValue();
    }

    private static final ProjectFileLocator locator_delegate$lambda$10(SourceLocatorPlus this$0) {
        Iterable $this$mapTo$iv = this$0.mainConfig.getSoot_process_dir();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            destination$iv.add(Resource.INSTANCE.of((String) item$iv));
        }
        Set append = SourceLocatorPlusKt.sootClassPathsCvt((Set) destination$iv);
        ProjectFileLocator it = new ProjectFileLocator(this$0.mainConfig.getMonitor(), append, null, FileSystemLocator.TraverseMode.IndexArchive, false);
        it.update();
        return it;
    }

    public final void update() {
    }

    @Nullable
    public final String getClassNameOf(@NotNull IResFile cls) {
        Intrinsics.checkNotNullParameter(cls, "cls");
        return (String) this.cacheClassNameMap.get(cls.getPath());
    }

    public final boolean isInvalidClassFile(@NotNull String fileName, @NotNull IResFile cls) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(cls, "cls");
        String realClsName = getClassNameOf(cls);
        if (realClsName == null) {
            return false;
        }
        return Intrinsics.areEqual(realClsName, fileName);
    }

    @Nullable
    public IFoundFile lookupInClassPath(@NotNull String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        if (Intrinsics.areEqual("LinearLayout.class", fileName)) {
            return null;
        }
        XOptional it = (XOptional) this.cacheClassLookMap.get(fileName);
        if (it != null) {
            return (IFoundFile) it.getValue();
        }
        IFoundFile it2 = super.lookupInClassPath(fileName);
        if (it2 != null) {
            return it2;
        }
        return null;
    }

    @Nullable
    protected IFoundFile lookupInArchive(@NotNull String archivePath, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(archivePath, "archivePath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        XOptional it = (XOptional) this.cacheClassLookMap.get(fileName);
        if (it != null) {
            return (IFoundFile) it.getValue();
        }
        return null;
    }
}
