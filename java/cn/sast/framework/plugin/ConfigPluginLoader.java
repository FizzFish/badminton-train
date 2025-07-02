package cn.sast.framework.plugin;

import cn.sast.api.AnalyzerEnv;
import cn.sast.api.config.BuiltinAnalysisConfig;
import cn.sast.api.config.PreAnalysisConfig;
import cn.sast.api.config.SaConfig;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.AnalyzeTaskRunner;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.PluginDefinitions;
import com.feysh.corax.config.api.IConfigPluginExtension;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.serialization.modules.SerializersModule;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pf4j.ClassLoadingStrategy;
import org.pf4j.CompoundPluginLoader;
import org.pf4j.CompoundPluginRepository;
import org.pf4j.DefaultPluginLoader;
import org.pf4j.DefaultPluginManager;
import org.pf4j.DefaultPluginRepository;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginClassLoader;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginLoader;
import org.pf4j.PluginManager;
import org.pf4j.PluginRepository;
import org.pf4j.PluginWrapper;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

/* compiled from: ConfigPluginLoader.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018�� &2\u00020\u0001:\u0002&'B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u001a\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019J\u0018\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001fJ\b\u0010%\u001a\u00020\fH\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n��R\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, d2 = {"Lcn/sast/framework/plugin/ConfigPluginLoader;", "", "configDirs", "", "Lcn/sast/common/IResource;", "pluginsDirs", "Lcn/sast/common/IResDirectory;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getConfigDirs", "()Ljava/util/List;", "pluginManager", "Lcn/sast/framework/plugin/ConfigPluginLoader$PluginManager;", "getPluginManager", "()Lcn/sast/framework/plugin/ConfigPluginLoader$PluginManager;", "pluginManager$delegate", "Lkotlin/Lazy;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "serializersModule$delegate", "getConfigExtensions", "Lcom/feysh/corax/config/api/IConfigPluginExtension;", "pluginId", "", "loadFromName", "Lcn/sast/api/config/SaConfig;", "name", "searchCheckerUnits", "ymlConfig", "Lcn/sast/common/IResFile;", "checkerFilter", "Lcn/sast/framework/plugin/CheckerFilterByName;", "makeTemplateYml", "", "tempFile", "loadPlugin", "Companion", "PluginManager", "corax-framework"})
@SourceDebugExtension({"SMAP\nConfigPluginLoader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConfigPluginLoader.kt\ncn/sast/framework/plugin/ConfigPluginLoader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,197:1\n1#2:198\n774#3:199\n865#3,2:200\n1557#3:202\n1628#3,3:203\n*S KotlinDebug\n*F\n+ 1 ConfigPluginLoader.kt\ncn/sast/framework/plugin/ConfigPluginLoader\n*L\n133#1:199\n133#1:200,2\n180#1:202\n180#1:203,3\n*E\n"})
/* loaded from: ConfigPluginLoader.class */
public final class ConfigPluginLoader {

    @NotNull
    private final List<IResource> configDirs;

    @NotNull
    private final List<IResDirectory> pluginsDirs;

    @NotNull
    private final Lazy pluginManager$delegate;

    @NotNull
    private final Lazy serializersModule$delegate;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ConfigPluginLoader::logger$lambda$13);

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigPluginLoader(@NotNull List<? extends IResource> list, @NotNull List<? extends IResDirectory> list2) {
        Intrinsics.checkNotNullParameter(list, "configDirs");
        Intrinsics.checkNotNullParameter(list2, "pluginsDirs");
        this.configDirs = list;
        this.pluginsDirs = list2;
        this.pluginManager$delegate = LazyKt.lazy(() -> {
            return pluginManager_delegate$lambda$0(r1);
        });
        this.serializersModule$delegate = LazyKt.lazy(() -> {
            return serializersModule_delegate$lambda$1(r1);
        });
    }

    @NotNull
    public final List<IResource> getConfigDirs() {
        return this.configDirs;
    }

    /* compiled from: ConfigPluginLoader.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/plugin/ConfigPluginLoader$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: ConfigPluginLoader$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$13() {
        return Unit.INSTANCE;
    }

    /* compiled from: ConfigPluginLoader.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R'\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcn/sast/framework/plugin/ConfigPluginLoader$PluginManager;", "Lorg/pf4j/DefaultPluginManager;", "importPaths", "", "Ljava/nio/file/Path;", "classLoadStrategy", "Lorg/pf4j/ClassLoadingStrategy;", "<init>", "(Ljava/util/List;Lorg/pf4j/ClassLoadingStrategy;)V", "createPluginDescriptorFinder", "Lorg/pf4j/PluginDescriptorFinder;", "createPluginRepository", "Lorg/pf4j/PluginRepository;", "createPluginLoader", "Lorg/pf4j/PluginLoader;", "startPlugins", "", "pluginToReflections", "", "Lorg/pf4j/PluginWrapper;", "Lorg/reflections/Reflections;", "getPluginToReflections", "()Ljava/util/Map;", "pluginToReflections$delegate", "Lkotlin/Lazy;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nConfigPluginLoader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConfigPluginLoader.kt\ncn/sast/framework/plugin/ConfigPluginLoader$PluginManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,197:1\n1863#2,2:198\n1279#2,2:204\n1293#2,4:206\n126#3:200\n153#3,3:201\n*S KotlinDebug\n*F\n+ 1 ConfigPluginLoader.kt\ncn/sast/framework/plugin/ConfigPluginLoader$PluginManager\n*L\n65#1:198,2\n81#1:204,2\n81#1:206,4\n81#1:200\n81#1:201,3\n*E\n"})
    /* loaded from: ConfigPluginLoader$PluginManager.class */
    public static final class PluginManager extends DefaultPluginManager {

        @NotNull
        private final ClassLoadingStrategy classLoadStrategy;

        @NotNull
        private final Lazy pluginToReflections$delegate;

        public /* synthetic */ PluginManager(List list, ClassLoadingStrategy classLoadingStrategy, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i & 2) != 0 ? ClassLoadingStrategy.ADP : classLoadingStrategy);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PluginManager(@NotNull List<? extends Path> list, @NotNull ClassLoadingStrategy classLoadStrategy) {
            super(list);
            Intrinsics.checkNotNullParameter(list, "importPaths");
            Intrinsics.checkNotNullParameter(classLoadStrategy, "classLoadStrategy");
            this.classLoadStrategy = classLoadStrategy;
            this.pluginToReflections$delegate = LazyKt.lazy(() -> {
                return pluginToReflections_delegate$lambda$3(r1);
            });
        }

        @NotNull
        protected PluginDescriptorFinder createPluginDescriptorFinder() {
            return new ManifestPluginDescriptorFinder();
        }

        @Nullable
        protected PluginRepository createPluginRepository() {
            return new CompoundPluginRepository().add(new DefaultPluginRepository(getPluginsRoots()), new BooleanSupplier() { // from class: cn.sast.framework.plugin.ConfigPluginLoader$PluginManager$createPluginRepository$1
                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return this.this$0.isNotDevelopment();
                }
            });
        }

        @Nullable
        protected PluginLoader createPluginLoader() {
            return new CompoundPluginLoader().add(new DefaultPluginLoader() { // from class: cn.sast.framework.plugin.ConfigPluginLoader$PluginManager$createPluginLoader$1
                {
                    super((PluginManager) this.this$0);
                }

                protected PluginClassLoader createPluginClassLoader(Path pluginPath, PluginDescriptor pluginDescriptor) {
                    Intrinsics.checkNotNullParameter(pluginPath, "pluginPath");
                    Intrinsics.checkNotNullParameter(pluginDescriptor, "pluginDescriptor");
                    return new PluginClassLoader(this.pluginManager, pluginDescriptor, getClass().getClassLoader(), this.this$0.classLoadStrategy);
                }
            }, new BooleanSupplier() { // from class: cn.sast.framework.plugin.ConfigPluginLoader$PluginManager$createPluginLoader$2
                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return this.this$0.isNotDevelopment();
                }
            });
        }

        public void startPlugins() throws ClassNotFoundException {
            Iterable plugins = getPlugins();
            Intrinsics.checkNotNullExpressionValue(plugins, "getPlugins(...)");
            Iterable $this$forEach$iv = plugins;
            for (Object element$iv : $this$forEach$iv) {
                PluginWrapper it = (PluginWrapper) element$iv;
                try {
                    ClassLoader pluginClassLoader = it.getPluginClassLoader();
                    byte[] bArrDecode = Base64.getDecoder().decode("Y29tLmRpZm9jZC5WZXJpZnlKTkk=");
                    Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
                    Class clz = pluginClassLoader.loadClass(StringsKt.decodeToString(bArrDecode));
                    AnalyzeTaskRunner.Companion.setV3r14yJn1Class(clz);
                } catch (Exception e) {
                }
                PluginDefinitions.Companion companion = PluginDefinitions.Companion;
                String pluginId = it.getPluginId();
                Intrinsics.checkNotNullExpressionValue(pluginId, "getPluginId(...)");
                if (companion.checkCommercial(pluginId)) {
                    AnalyzerEnv.INSTANCE.getBvs1n3ss().getAndAdd(1);
                }
            }
            super.startPlugins();
        }

        @NotNull
        public final Map<PluginWrapper, Reflections> getPluginToReflections() {
            return (Map) this.pluginToReflections$delegate.getValue();
        }

        private static final Map pluginToReflections_delegate$lambda$3(PluginManager this$0) {
            Map $this$map$iv = this$0.plugins;
            Intrinsics.checkNotNullExpressionValue($this$map$iv, "plugins");
            Collection destination$iv$iv = new ArrayList($this$map$iv.size());
            for (Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
                destination$iv$iv.add((PluginWrapper) item$iv$iv.getValue());
            }
            Iterable $this$associateWith$iv = (List) destination$iv$iv;
            LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
            for (Object element$iv$iv : $this$associateWith$iv) {
                LinkedHashMap linkedHashMap = result$iv;
                PluginWrapper plugin = (PluginWrapper) element$iv$iv;
                ClassLoader pluginClassLoader = plugin.getPluginClassLoader();
                Intrinsics.checkNotNull(pluginClassLoader, "null cannot be cast to non-null type java.net.URLClassLoader");
                URLClassLoader classLoader = (URLClassLoader) pluginClassLoader;
                ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
                URL[] uRLs = classLoader.getURLs();
                Intrinsics.checkNotNullExpressionValue(uRLs, "getURLs(...)");
                linkedHashMap.put(element$iv$iv, new Reflections(configurationBuilder.addUrls(ArraysKt.toList(uRLs)).addClassLoaders(new ClassLoader[]{classLoader})));
            }
            return result$iv;
        }
    }

    @NotNull
    public final PluginManager getPluginManager() {
        return (PluginManager) this.pluginManager$delegate.getValue();
    }

    private static final PluginManager pluginManager_delegate$lambda$0(ConfigPluginLoader this$0) {
        return this$0.loadPlugin();
    }

    private final SerializersModule getSerializersModule() {
        return (SerializersModule) this.serializersModule$delegate.getValue();
    }

    private static final SerializersModule serializersModule_delegate$lambda$1(ConfigPluginLoader this$0) {
        return PluginDefinitions.Companion.getSerializersModule(this$0.getPluginManager());
    }

    private final List<IConfigPluginExtension> getConfigExtensions(String pluginId) {
        List extensions;
        if (pluginId != null) {
            extensions = getPluginManager().getExtensions(IConfigPluginExtension.class, pluginId);
            Intrinsics.checkNotNullExpressionValue(extensions, "getExtensions(...)");
        } else {
            extensions = getPluginManager().getExtensions(IConfigPluginExtension.class);
            Intrinsics.checkNotNullExpressionValue(extensions, "getExtensions(...)");
        }
        List configExtensions = extensions;
        logger.info(() -> {
            return getConfigExtensions$lambda$2(r1);
        });
        return configExtensions;
    }

    private static final Object getConfigExtensions$lambda$2(List $configExtensions) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf($configExtensions.size()), IConfigPluginExtension.class.getName()};
        String str = String.format("Found %d extensions for extension point '%s'", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @NotNull
    public final SaConfig loadFromName(@Nullable String pluginId, @Nullable String name) {
        IConfigPluginExtension iConfigPluginExtension;
        if (name == null) {
            logger.info(() -> {
                return loadFromName$lambda$3(r1);
            });
        }
        List configExtensions = getConfigExtensions(pluginId);
        if (!(!configExtensions.isEmpty())) {
            throw new IllegalStateException(("not found IConfigPluginExtension in: " + this.pluginsDirs).toString());
        }
        List<IResDirectory> list = this.pluginsDirs;
        String str = File.pathSeparator;
        Intrinsics.checkNotNullExpressionValue(str, "pathSeparator");
        String ps = CollectionsKt.joinToString$default(list, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        if (name != null) {
            List $this$filter$iv = configExtensions;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                IConfigPluginExtension it = (IConfigPluginExtension) element$iv$iv;
                if (Intrinsics.areEqual(it.getName(), name)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List choose = (List) destination$iv$iv;
            if (choose.isEmpty()) {
                throw new IllegalStateException(("your choose: " + name + " not found in plugins dir: " + ps).toString());
            }
            if (choose.size() != 1) {
                throw new IllegalStateException(("dup choose: " + name + " in plugins dir: " + ps + ". choose: " + choose).toString());
            }
            iConfigPluginExtension = (IConfigPluginExtension) CollectionsKt.first(choose);
        } else {
            if (configExtensions.size() != 1) {
                throw new IllegalStateException(("you need choose which one of names: [ \n\t" + CollectionsKt.joinToString$default(configExtensions, ",\n\t", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v1) -> {
                    return loadFromName$lambda$5(r8, v1);
                }, 30, (Object) null) + " ]").toString());
            }
            iConfigPluginExtension = (IConfigPluginExtension) CollectionsKt.first(configExtensions);
        }
        IConfigPluginExtension config = iConfigPluginExtension;
        logger.info(() -> {
            return loadFromName$lambda$7(r1);
        });
        return new SaConfig(null, null, ExtensionsKt.toImmutableSet(config.getUnits()), config.getSootConfig(), null, 3, null);
    }

    private static final Object loadFromName$lambda$3(ConfigPluginLoader this$0) {
        return "Automatically search for the SA-Config under path '" + this$0.pluginsDirs + "', with the requirement that there can only exist one config.";
    }

    private static final CharSequence loadFromName$lambda$5(String $ps, IConfigPluginExtension it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getName() + "@" + $ps;
    }

    private static final Object loadFromName$lambda$7(IConfigPluginExtension $config) {
        return "use config method for entry: " + $config.getName() + " in " + Resource.INSTANCE.locateAllClass($config.getClass());
    }

    @NotNull
    public final SaConfig searchCheckerUnits(@NotNull IResFile ymlConfig, @Nullable CheckerFilterByName checkerFilter) {
        Intrinsics.checkNotNullParameter(ymlConfig, "ymlConfig");
        SAConfiguration configFromYml = SAConfiguration.Companion.deserialize(getSerializersModule(), ymlConfig);
        boolean needNormalize = configFromYml.sort();
        PluginDefinitions defs = PluginDefinitions.Companion.load(getPluginManager());
        boolean hasChange = configFromYml.supplementAndMerge(defs, ymlConfig.toString());
        IResource dir = ymlConfig.getParent();
        if (dir != null && (needNormalize || hasChange)) {
            String name = StringsKt.dropLast(StringsKt.substringBeforeLast$default(ymlConfig.getName(), ymlConfig.getExtension(), (String) null, 2, (Object) null), 1);
            IResFile normalizedConfig = dir.resolve(name + ".normalize.yml").toFile();
            configFromYml.sort();
            configFromYml.serialize(getSerializersModule(), normalizedConfig);
            logger.info(() -> {
                return searchCheckerUnits$lambda$9$lambda$8(r1);
            });
        }
        return configFromYml.filter(defs, checkerFilter);
    }

    private static final Object searchCheckerUnits$lambda$9$lambda$8(IResFile $normalizedConfig) {
        return "Serialized a normalized SA-Configuration yml file: " + $normalizedConfig;
    }

    public final void makeTemplateYml(@NotNull IResFile tempFile) {
        Intrinsics.checkNotNullParameter(tempFile, "tempFile");
        PluginDefinitions defs = PluginDefinitions.Companion.load(getPluginManager());
        SAConfiguration emptyYaml = new SAConfiguration((BuiltinAnalysisConfig) null, (PreAnalysisConfig) null, (LinkedHashMap) null, (LinkedHashSet) null, 15, (DefaultConstructorMarker) null);
        emptyYaml.supplementAndMerge(defs, null);
        emptyYaml.sort();
        emptyYaml.serialize(getSerializersModule(), tempFile);
        logger.info(() -> {
            return makeTemplateYml$lambda$10(r1);
        });
    }

    private static final Object makeTemplateYml$lambda$10(IResFile $tempFile) {
        return "Serialized template SA-Configuration file: " + $tempFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PluginManager loadPlugin() throws ClassNotFoundException {
        logger.info(() -> {
            return loadPlugin$lambda$11(r1);
        });
        Iterable $this$map$iv = this.pluginsDirs;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IResDirectory it = (IResDirectory) item$iv$iv;
            destination$iv$iv.add(it.getPath());
        }
        PluginManager pluginManager = new PluginManager((List) destination$iv$iv, null, 2, null);
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        List plugins = pluginManager.getPlugins();
        if (plugins.isEmpty()) {
            throw new IllegalStateException("no config plugin found".toString());
        }
        return pluginManager;
    }

    private static final Object loadPlugin$lambda$11(ConfigPluginLoader this$0) {
        return "Plugin directory: " + this$0.pluginsDirs;
    }
}
