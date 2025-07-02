package cn.sast.api.config;

import cn.sast.api.incremental.IncrementalAnalyze;
import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.charleskorn.kaml.AmbiguousQuoteStyle;
import com.charleskorn.kaml.MultiLineStringStyle;
import com.charleskorn.kaml.PolymorphismStyle;
import com.charleskorn.kaml.SequenceStyle;
import com.charleskorn.kaml.SingleLineStringStyle;
import com.charleskorn.kaml.Yaml;
import com.charleskorn.kaml.YamlConfiguration;
import com.charleskorn.kaml.YamlNamingStrategy;
import com.feysh.corax.config.api.IAnalysisDepends;
import com.feysh.corax.config.api.PhantomAnalysisDepends;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.modules.PolymorphicModuleBuilder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuilder;
import org.jetbrains.annotations.NotNull;

/* compiled from: MainConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��@\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\u0002\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\u000f\u001a\u00020\r*\u00020\u000e\u001a\u0012\u0010\u0010\u001a\u00020\u0011*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013\u001a \u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0011\"\u0011\u0010��\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "yamlConfiguration", "Lcom/charleskorn/kaml/YamlConfiguration;", "getYamlConfiguration", "()Lcom/charleskorn/kaml/YamlConfiguration;", "yamlFormat", "Lcom/charleskorn/kaml/Yaml;", "getYamlFormat", "()Lcom/charleskorn/kaml/Yaml;", "simpleIAnalysisDepends", "Lcom/feysh/corax/config/api/IAnalysisDepends;", "Lcn/sast/api/config/MainConfig;", "interProceduralAnalysisDepends", "skipResourceInArchive", "", "res", "Lcn/sast/common/IResource;", "checkerInfoDir", "Lcn/sast/common/IResDirectory;", "configDirs", "", "stopOnError", "corax-api"})
@SourceDebugExtension({"SMAP\nMainConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainConfig.kt\ncn/sast/api/config/MainConfigKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,463:1\n1#2:464\n31#3,2:465\n254#3,9:467\n33#3:476\n*S KotlinDebug\n*F\n+ 1 MainConfig.kt\ncn/sast/api/config/MainConfigKt\n*L\n51#1:465,2\n52#1:467,9\n51#1:476\n*E\n"})
/* loaded from: MainConfigKt.class */
public final class MainConfigKt {

    @NotNull
    private static final SerializersModule serializersModule;

    @NotNull
    private static final YamlConfiguration yamlConfiguration;

    @NotNull
    private static final Yaml yamlFormat;

    @NotNull
    public static final SerializersModule getSerializersModule() {
        return serializersModule;
    }

    static {
        SerializersModuleBuilder builder$iv = new SerializersModuleBuilder();
        KClass baseClass$iv = Reflection.getOrCreateKotlinClass(Type.class);
        PolymorphicModuleBuilder builder$iv2 = new PolymorphicModuleBuilder(baseClass$iv, (KSerializer) null);
        builder$iv2.subclass(Reflection.getOrCreateKotlinClass(Class.class), ClassSerializer.INSTANCE);
        builder$iv2.buildTo(builder$iv);
        serializersModule = builder$iv.build();
        yamlConfiguration = new YamlConfiguration(true, false, (String) null, PolymorphismStyle.Tag, (String) null, 0, 200, SequenceStyle.Block, (SingleLineStringStyle) null, (MultiLineStringStyle) null, (AmbiguousQuoteStyle) null, 0, false, (YamlNamingStrategy) null, 16182, (DefaultConstructorMarker) null);
        yamlFormat = new Yaml(serializersModule, yamlConfiguration);
    }

    @NotNull
    public static final YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

    @NotNull
    public static final Yaml getYamlFormat() {
        return yamlFormat;
    }

    @NotNull
    public static final IAnalysisDepends simpleIAnalysisDepends(@NotNull MainConfig $this$simpleIAnalysisDepends) {
        Intrinsics.checkNotNullParameter($this$simpleIAnalysisDepends, "<this>");
        IncrementalAnalyze incrementAnalyze = $this$simpleIAnalysisDepends.getIncrementAnalyze();
        IncrementalAnalyzeByChangeFiles incrementalAnalyzeByChangeFiles = incrementAnalyze instanceof IncrementalAnalyzeByChangeFiles ? (IncrementalAnalyzeByChangeFiles) incrementAnalyze : null;
        IAnalysisDepends analysisDepends = incrementalAnalyzeByChangeFiles != null ? incrementalAnalyzeByChangeFiles.getSimpleDeclAnalysisDependsGraph() : null;
        if (analysisDepends == null) {
            analysisDepends = (IAnalysisDepends) PhantomAnalysisDepends.INSTANCE;
        }
        return analysisDepends;
    }

    @NotNull
    public static final IAnalysisDepends interProceduralAnalysisDepends(@NotNull MainConfig $this$interProceduralAnalysisDepends) {
        Intrinsics.checkNotNullParameter($this$interProceduralAnalysisDepends, "<this>");
        IncrementalAnalyze incrementAnalyze = $this$interProceduralAnalysisDepends.getIncrementAnalyze();
        IncrementalAnalyzeByChangeFiles incrementalAnalyzeByChangeFiles = incrementAnalyze instanceof IncrementalAnalyzeByChangeFiles ? (IncrementalAnalyzeByChangeFiles) incrementAnalyze : null;
        IAnalysisDepends analysisDepends = incrementalAnalyzeByChangeFiles != null ? incrementalAnalyzeByChangeFiles.getInterProceduralAnalysisDependsGraph() : null;
        if (analysisDepends == null) {
            analysisDepends = (IAnalysisDepends) PhantomAnalysisDepends.INSTANCE;
        }
        return analysisDepends;
    }

    public static final boolean skipResourceInArchive(@NotNull MainConfig $this$skipResourceInArchive, @NotNull IResource res) {
        Intrinsics.checkNotNullParameter($this$skipResourceInArchive, "<this>");
        Intrinsics.checkNotNullParameter(res, "res");
        if (!res.isJarScheme()) {
            return false;
        }
        try {
            if ($this$skipResourceInArchive.getSourcePathZFS().contains(Resource.INSTANCE.getZipFileSystem(res.getSchemePath()))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public static /* synthetic */ IResDirectory checkerInfoDir$default(List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return checkerInfoDir(list, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0152  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final cn.sast.common.IResDirectory checkerInfoDir(@org.jetbrains.annotations.NotNull java.util.List<? extends cn.sast.common.IResource> r4, boolean r5) {
        /*
            Method dump skipped, instructions count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.MainConfigKt.checkerInfoDir(java.util.List, boolean):cn.sast.common.IResDirectory");
    }
}
