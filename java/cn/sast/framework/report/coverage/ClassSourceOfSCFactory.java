package cn.sast.framework.report.coverage;

import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.util.SootUtils;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.cache.AnalysisCacheKt;
import com.feysh.corax.cache.AnalysisDataFactory;
import com.feysh.corax.cache.XOptional;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.LoadingCache;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClassSourceOfSC.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005R:\u0010\u0006\u001a(\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b0\u0007j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\tX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\rX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcn/sast/framework/report/coverage/ClassSourceOfSCFactory;", "Lcom/feysh/corax/cache/AnalysisDataFactory;", "Lcn/sast/framework/report/coverage/ClassSourceInfo;", "Lcn/sast/framework/report/coverage/ClassSourceOfSCKey;", "<init>", "()V", "cache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lcom/feysh/corax/cache/XOptional;", "Lcom/feysh/corax/cache/XLoadingCache;", "getCache", "()Lcom/github/benmanes/caffeine/cache/LoadingCache;", "key", "Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "getKey", "()Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "corax-framework"})
/* loaded from: ClassSourceOfSCFactory.class */
public final class ClassSourceOfSCFactory implements AnalysisDataFactory<ClassSourceInfo, ClassSourceOfSCKey> {

    @NotNull
    public static final ClassSourceOfSCFactory INSTANCE = new ClassSourceOfSCFactory();

    @NotNull
    private static final LoadingCache<ClassSourceOfSCKey, XOptional<ClassSourceInfo>> cache = AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), new CacheLoader() { // from class: cn.sast.framework.report.coverage.ClassSourceOfSCFactory$cache$1
        public final ClassSourceInfo load(ClassSourceOfSCKey key2) {
            IResFile classSource = SootUtils.INSTANCE.getClassSourceFromSoot(key2.getClassName());
            if (classSource == null || !classSource.getExists() || !classSource.isFile()) {
                return null;
            }
            return new ClassSourceInfo(key2.getClassName(), ResourceKt.readAllBytes(classSource), classSource);
        }
    });

    @NotNull
    private static final AnalysisDataFactory.Key<ClassSourceInfo> key = new AnalysisDataFactory.Key<ClassSourceInfo>() { // from class: cn.sast.framework.report.coverage.ClassSourceOfSCFactory$key$1
    };

    private ClassSourceOfSCFactory() {
    }

    @NotNull
    public LoadingCache<ClassSourceOfSCKey, XOptional<ClassSourceInfo>> getCache() {
        return cache;
    }

    static {
        AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
    }

    @NotNull
    public AnalysisDataFactory.Key<ClassSourceInfo> getKey() {
        return key;
    }
}
