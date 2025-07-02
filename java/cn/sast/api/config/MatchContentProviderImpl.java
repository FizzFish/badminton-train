package cn.sast.api.config;

import cn.sast.api.config.MainConfig;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/* compiled from: MatchContentProviderImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0012\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001c"}, d2 = {"Lcn/sast/api/config/MatchContentProviderImpl;", "Lcn/sast/api/config/MatchContentProvider;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getRelativePath", "", "path", "Ljava/nio/file/Path;", "getSourceOfClassMember", "declaringClass", "Lsoot/SootClass;", "getClassPath", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassPathMatch$MatchTarget;", "classpath", "get", "Lcom/feysh/corax/config/api/rules/ProcessRule$FileMatch$MatchTarget;", "file", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch$MatchTarget;", "sc", "sm", "Lsoot/SootMethod;", "sf", "Lsoot/SootField;", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nMatchContentProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MatchContentProviderImpl.kt\ncn/sast/api/config/MatchContentProviderImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,80:1\n1#2:81\n*E\n"})
/* loaded from: MatchContentProviderImpl.class */
public final class MatchContentProviderImpl implements MatchContentProvider {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(MatchContentProviderImpl::logger$lambda$3);

    public MatchContentProviderImpl(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.mainConfig = mainConfig;
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @Nullable
    public final String getRelativePath(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            Resource resource = Resource.INSTANCE;
            Path absolutePath = path.toAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
            Path pathNormalize = absolutePath.normalize();
            Intrinsics.checkNotNullExpressionValue(pathNormalize, "normalize(...)");
            String absPath = resource.of(pathNormalize).toString();
            MainConfig.RelativePath relativePath = this.mainConfig.tryGetRelativePathFromAbsolutePath(absPath);
            String relativePath2 = relativePath.getRelativePath();
            if (relativePath.getPrefix().length() > 0) {
                return relativePath2;
            }
            return null;
        } catch (Exception e) {
            logger.warn(() -> {
                return getRelativePath$lambda$0(r1, r2);
            });
            return null;
        }
    }

    private static final Object getRelativePath$lambda$0(Path $path, Exception $e) {
        return "Invalid path: [" + $path + "], e: " + $e.getMessage();
    }

    private final String getSourceOfClassMember(SootClass declaringClass) {
        Path it = AnalysisCache.G.INSTANCE.class2SourceFile(declaringClass);
        if (it != null) {
            return getRelativePath(it);
        }
        return null;
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassPathMatch.MatchTarget getClassPath(@NotNull Path classpath) {
        Intrinsics.checkNotNullParameter(classpath, "classpath");
        return new ProcessRule.ClassPathMatch.MatchTarget(getRelativePath(classpath));
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.FileMatch.MatchTarget get(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return new ProcessRule.FileMatch.MatchTarget(getRelativePath(file));
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootClass sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        return new ProcessRule.ClassMemberMatch.MatchTarget(sc.getName(), getSourceOfClassMember(sc), (String) null, (String) null, (String) null, (String) null);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootMethod sm) {
        Intrinsics.checkNotNullParameter(sm, "sm");
        String name = sm.getDeclaringClass().getName();
        SootClass declaringClass = sm.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        return new ProcessRule.ClassMemberMatch.MatchTarget(name, getSourceOfClassMember(declaringClass), sm.getSignature(), sm.getName(), (String) null, (String) null);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootField sf) {
        Intrinsics.checkNotNullParameter(sf, "sf");
        String name = sf.getDeclaringClass().getName();
        SootClass declaringClass = sf.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        return new ProcessRule.ClassMemberMatch.MatchTarget(name, getSourceOfClassMember(declaringClass), (String) null, (String) null, sf.getSignature(), sf.getName());
    }

    /* compiled from: MatchContentProviderImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/api/config/MatchContentProviderImpl$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-api"})
    /* loaded from: MatchContentProviderImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$3() {
        return Unit.INSTANCE;
    }
}
