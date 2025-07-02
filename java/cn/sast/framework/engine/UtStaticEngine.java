package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.IUTBotResultCollector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.PersistentSet;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.FileUtil;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;
import org.utbot.common.PathUtil;
import org.utbot.engine.Mocker;
import org.utbot.framework.UtSettings;
import org.utbot.framework.codegen.CodeGenerator;
import org.utbot.framework.codegen.domain.DomainKt;
import org.utbot.framework.codegen.domain.ForceStaticMocking;
import org.utbot.framework.codegen.domain.HangingTestsTimeout;
import org.utbot.framework.codegen.domain.NoStaticMocking;
import org.utbot.framework.codegen.domain.ParametrizedTestSource;
import org.utbot.framework.codegen.domain.RuntimeExceptionTestsBehaviour;
import org.utbot.framework.plugin.api.ClassId;
import org.utbot.framework.plugin.api.MockFramework;
import org.utbot.framework.plugin.api.MockStrategyApi;
import org.utbot.framework.plugin.api.TestCaseGenerator;
import org.utbot.framework.plugin.api.TreatOverflowAsError;
import org.utbot.framework.plugin.api.UtMethodTestSet;
import org.utbot.framework.plugin.api.util.UtContext;
import org.utbot.framework.plugin.services.JdkInfo;
import org.utbot.framework.plugin.services.JdkInfoDefaultProvider;
import org.utbot.framework.util.EngineUtilsKt;
import org.utbot.framework.util.SootUtilsKt;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.options.Options;

/* compiled from: UtStaticEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� E2\u00020\u0001:\u0001EB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,H\u0002J$\u0010-\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,2\u0006\u0010.\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100J$\u00102\u001a\u00020%2\u0006\u00103\u001a\u00020,2\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u00104\u001a\u00020\u0015J\b\u00105\u001a\u000206H\u0004J,\u00107\u001a\u00020%2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002062\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\r2\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020%2\u0006\u00108\u001a\u000209J&\u0010@\u001a\u00020%2\u0006\u00108\u001a\u0002092\u0006\u0010A\u001a\u00020B2\u0006\u0010=\u001a\u00020>H\u0086@¢\u0006\u0002\u0010CJ\u001e\u0010D\u001a\u00020%2\u0006\u00108\u001a\u0002092\u0006\u0010A\u001a\u00020B2\u0006\u0010=\u001a\u00020>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR0\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r@FX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\r8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010!\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006F"}, d2 = {"Lcn/sast/framework/engine/UtStaticEngine;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "utConfig", "Lcn/sast/framework/engine/UtStaticEngineConfiguration;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/framework/engine/UtStaticEngineConfiguration;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getUtConfig", "()Lcn/sast/framework/engine/UtStaticEngineConfiguration;", "fullyQualifiedNames", "", "", "classesToMockAlways", "getClassesToMockAlways", "()Ljava/util/Set;", "setClassesToMockAlways", "(Ljava/util/Set;)V", "classpath", "Lcn/sast/common/IResource;", "getClasspath", "useDefaultJavaClassPath", "", "getUseDefaultJavaClassPath", "()Z", "classLoader", "Ljava/net/URLClassLoader;", "getClassLoader", "()Ljava/net/URLClassLoader;", "classLoader$delegate", "Lkotlin/Lazy;", "dependencyPaths", "getDependencyPaths", "()Ljava/lang/String;", "saveToFile", "", "snippet", "outputPath", "initializeCodeGenerator", "Lorg/utbot/framework/codegen/CodeGenerator;", "testFramework", "classUnderTest", "Lorg/utbot/framework/plugin/api/ClassId;", "generateTest", "testClassname", "testCases", "", "Lorg/utbot/framework/plugin/api/UtMethodTestSet;", "generateTestsForClass", "classIdUnderTest", "output", "initializeGenerator", "Lorg/utbot/framework/plugin/api/TestCaseGenerator;", "runTest", "soot", "Lcn/sast/framework/SootCtx;", "test", "entries", "Lsoot/SootMethod;", "result", "Lcn/sast/framework/result/IUTBotResultCollector;", "initUt", "analyzeSuspend", "provider", "Lcn/sast/framework/entries/IEntryPointProvider;", "(Lcn/sast/framework/SootCtx;Lcn/sast/framework/entries/IEntryPointProvider;Lcn/sast/framework/result/IUTBotResultCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "analyze", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nUtStaticEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtStaticEngine.kt\ncn/sast/framework/engine/UtStaticEngine\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 UtContext.kt\norg/utbot/framework/plugin/api/util/UtContextKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,229:1\n1557#2:230\n1628#2,3:231\n1557#2:234\n1628#2,3:235\n1628#2,3:241\n1557#2:251\n1628#2,3:252\n74#3,3:238\n77#3,5:244\n13409#4,2:249\n37#5,2:255\n*S KotlinDebug\n*F\n+ 1 UtStaticEngine.kt\ncn/sast/framework/engine/UtStaticEngine\n*L\n152#1:230\n152#1:231,3\n161#1:234\n161#1:235,3\n167#1:241,3\n81#1:251\n81#1:252,3\n163#1:238,3\n163#1:244,5\n195#1:249,2\n81#1:255,2\n*E\n"})
/* loaded from: UtStaticEngine.class */
public class UtStaticEngine {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final UtStaticEngineConfiguration utConfig;

    @NotNull
    private Set<String> classesToMockAlways;

    @NotNull
    private final Lazy classLoader$delegate;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(UtStaticEngine::logger$lambda$12);

    public UtStaticEngine(@NotNull MainConfig mainConfig, @NotNull UtStaticEngineConfiguration utConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(utConfig, "utConfig");
        this.mainConfig = mainConfig;
        this.utConfig = utConfig;
        this.classesToMockAlways = this.utConfig.getClassesToMockAlways();
        this.classLoader$delegate = LazyKt.lazy(() -> {
            return classLoader_delegate$lambda$1(r1);
        });
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final UtStaticEngineConfiguration getUtConfig() {
        return this.utConfig;
    }

    /* compiled from: UtStaticEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/UtStaticEngine$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: UtStaticEngine$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$12() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<String> getClassesToMockAlways() {
        return this.classesToMockAlways;
    }

    public final void setClassesToMockAlways(@NotNull Set<String> set) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(set, "fullyQualifiedNames");
        for (String fullyQualifiedName : set) {
            try {
                Class.forName(fullyQualifiedName, false, getClassLoader());
            } catch (ClassNotFoundException e) {
                logger.error("", e);
            }
        }
        this.classesToMockAlways = set;
    }

    @NotNull
    public final Set<IResource> getClasspath() {
        return this.mainConfig.get_expand_class_path();
    }

    public final boolean getUseDefaultJavaClassPath() {
        return this.mainConfig.getUseDefaultJavaClassPath();
    }

    @NotNull
    public final URLClassLoader getClassLoader() {
        return (URLClassLoader) this.classLoader$delegate.getValue();
    }

    private static final URLClassLoader classLoader_delegate$lambda$1(UtStaticEngine this$0) {
        Iterable $this$map$iv = SetsKt.plus(this$0.getClasspath(), this$0.mainConfig.getProcessDir());
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IResource it = (IResource) item$iv$iv;
            destination$iv$iv.add(it.getUrl());
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        URL[] urls = (URL[]) $this$toTypedArray$iv.toArray(new URL[0]);
        return this$0.getUseDefaultJavaClassPath() ? new URLClassLoader(urls, ClassLoader.getSystemClassLoader()) : new URLClassLoader(urls, null);
    }

    @NotNull
    public final String getDependencyPaths() {
        String property = System.getProperty("java.class.path");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        return property;
    }

    public final void saveToFile(@NotNull String snippet, @Nullable String outputPath) throws IOException {
        Intrinsics.checkNotNullParameter(snippet, "snippet");
        if (outputPath != null) {
            Files.write(PathUtil.INSTANCE.toPath(outputPath), CollectionsKt.listOf(snippet), new OpenOption[0]);
        }
    }

    private final CodeGenerator initializeCodeGenerator(String testFramework, ClassId classUnderTest) {
        boolean generateWarningsForStaticMocking = this.utConfig.getForceStaticMocking() == ForceStaticMocking.FORCE && (this.utConfig.getStaticsMocking() instanceof NoStaticMocking);
        return new CodeGenerator(classUnderTest, (Map) null, false, DomainKt.testFrameworkByName(testFramework), (MockFramework) null, this.utConfig.getStaticsMocking(), this.utConfig.getForceStaticMocking(), generateWarningsForStaticMocking, this.utConfig.getCodegenLanguage(), (ParametrizedTestSource) null, (RuntimeExceptionTestsBehaviour) null, (HangingTestsTimeout) null, false, (String) null, 15894, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final String generateTest(@NotNull ClassId classUnderTest, @NotNull String testClassname, @NotNull List<UtMethodTestSet> list) {
        Intrinsics.checkNotNullParameter(classUnderTest, "classUnderTest");
        Intrinsics.checkNotNullParameter(testClassname, "testClassname");
        Intrinsics.checkNotNullParameter(list, "testCases");
        return initializeCodeGenerator(this.utConfig.getTestFramework(), classUnderTest).generateAsString(list, testClassname);
    }

    public final void generateTestsForClass(@NotNull ClassId classIdUnderTest, @NotNull List<UtMethodTestSet> list, @NotNull IResource output) throws UnsupportedOperationException, IOException {
        Intrinsics.checkNotNullParameter(classIdUnderTest, "classIdUnderTest");
        Intrinsics.checkNotNullParameter(list, "testCases");
        Intrinsics.checkNotNullParameter(output, "output");
        String testClassName = classIdUnderTest.getSimpleName() + "Test";
        String testClassBody = generateTest(classIdUnderTest, testClassName, list);
        if (logger.isTraceEnabled()) {
            logger.info(() -> {
                return generateTestsForClass$lambda$3(r1);
            });
        }
        File outputArgAsFile = output.getFile();
        if (!outputArgAsFile.exists()) {
            outputArgAsFile.mkdirs();
        }
        String outputDir = outputArgAsFile + File.separator;
        List packageNameAsList = CollectionsKt.dropLast(StringsKt.split$default(classIdUnderTest.getJvmName(), new char[]{'.'}, false, 0, 6, (Object) null), 1);
        String str = File.separator;
        Intrinsics.checkNotNullExpressionValue(str, "separator");
        Path path = Paths.get(outputDir + CollectionsKt.joinToString$default(packageNameAsList, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), new String[0]);
        path.toFile().mkdirs();
        saveToFile(testClassBody, path + File.separator + testClassName + ".java");
    }

    private static final Object generateTestsForClass$lambda$3(String $testClassBody) {
        return $testClassBody;
    }

    @NotNull
    protected final TestCaseGenerator initializeGenerator() {
        UtSettings.INSTANCE.setTreatOverflowAsError(this.utConfig.getTreatOverflowAsError() == TreatOverflowAsError.AS_ERROR);
        JdkInfo jdkInfo = new JdkInfoDefaultProvider().getInfo();
        Iterable $this$map$iv = this.mainConfig.getProcessDir();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IResource it = (IResource) item$iv$iv;
            destination$iv$iv.add(it.getPath());
        }
        Set<IResource> classpath = getClasspath();
        String str = File.pathSeparator;
        Intrinsics.checkNotNullExpressionValue(str, "pathSeparator");
        return new TestCaseGenerator((List) destination$iv$iv, CollectionsKt.joinToString$default(classpath, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, UtStaticEngine::initializeGenerator$lambda$5, 30, (Object) null), getDependencyPaths(), jdkInfo, (List) null, (Function0) null, false, false, 112, (DefaultConstructorMarker) null);
    }

    private static final CharSequence initializeGenerator$lambda$5(IResource it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getAbsolutePath();
    }

    public final void runTest(@NotNull SootCtx soot, @NotNull TestCaseGenerator test, @NotNull Set<? extends SootMethod> set, @NotNull IUTBotResultCollector result) {
        Intrinsics.checkNotNullParameter(soot, "soot");
        Intrinsics.checkNotNullParameter(test, "test");
        Intrinsics.checkNotNullParameter(set, "entries");
        Intrinsics.checkNotNullParameter(result, "result");
        Set<? extends SootMethod> $this$map$iv = set;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(EngineUtilsKt.getExecutableId((SootMethod) item$iv$iv));
        }
        List targetMethods = (List) destination$iv$iv;
        UtContext context$iv = new UtContext(getClassLoader());
        AutoCloseable utContext = UtContext.Companion.setUtContext(context$iv);
        try {
            try {
                MockStrategyApi mockStrategy = MockStrategyApi.NO_MOCKS;
                Iterable $this$mapTo$iv = SetsKt.plus(Mocker.Companion.getDefaultSuperClassesToMockAlwaysNames(), this.classesToMockAlways);
                Collection destination$iv = (Set) new LinkedHashSet();
                for (Object item$iv : $this$mapTo$iv) {
                    String it = (String) item$iv;
                    Intrinsics.checkNotNull(it);
                    destination$iv.add(new ClassId(it, (ClassId) null, false, 6, (DefaultConstructorMarker) null));
                }
                Set chosenClassesToMockAlways = (Set) destination$iv;
                List testCases = TestCaseGenerator.generate$default(test, targetMethods, mockStrategy, chosenClassesToMockAlways, 0L, (Function1) null, 24, (Object) null);
                logger.info(() -> {
                    return runTest$lambda$9$lambda$8(r1);
                });
                Unit unit = Unit.INSTANCE;
            } catch (Exception e$iv) {
                KotlinLogging.INSTANCE.logger("withUtContext").error(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$runTest$$inlined$withUtContext$1
                    public final Object invoke() {
                        return e$iv;
                    }
                });
                throw e$iv;
            }
        } finally {
            AutoCloseableKt.closeFinally(utContext, (Throwable) null);
        }
    }

    private static final Object runTest$lambda$9$lambda$8(List $testCases) {
        return "symbolic result: " + $testCases.size();
    }

    public final void initUt(@NotNull SootCtx soot) throws IOException {
        Intrinsics.checkNotNullParameter(soot, "soot");
        UtSettings.INSTANCE.setUseFuzzing(false);
        UtSettings.INSTANCE.setUseSandbox(false);
        UtSettings.INSTANCE.setUseConcreteExecution(false);
        UtSettings.INSTANCE.setUseCustomJavaDocTags(false);
        UtSettings.INSTANCE.setEnableSummariesGeneration(false);
        UtSettings.INSTANCE.setCheckNpeInNestedNotPrivateMethods(true);
        UtSettings.INSTANCE.setPreferredCexOption(false);
        UtSettings.INSTANCE.setUseAssembleModelGenerator(false);
        MainConfig mainConfig = this.mainConfig;
        PersistentSet<String> classpath = this.mainConfig.getClasspath();
        FileUtil fileUtil = FileUtil.INSTANCE;
        Class[] classesToLoad = SootUtilsKt.getClassesToLoad();
        String canonicalPath = fileUtil.isolateClassFiles((Class[]) Arrays.copyOf(classesToLoad, classesToLoad.length)).getCanonicalPath();
        Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
        mainConfig.setClasspath(classpath.add(canonicalPath));
        Scene scene = Scene.v();
        scene.setSootClassPath((String) null);
        Options optionsV = Options.v();
        Intrinsics.checkNotNullExpressionValue(optionsV, "v(...)");
        soot.configureSootClassPath(optionsV);
        for (Class cls : SootUtilsKt.getClassesToLoad()) {
            scene.addBasicClass(cls.getName(), 3);
            SootClass it = scene.forceResolve(cls.getName(), 3);
            it.setApplicationClass();
        }
    }

    @Nullable
    public final Object analyzeSuspend(@NotNull final SootCtx soot, @NotNull IEntryPointProvider provider, @NotNull final IUTBotResultCollector result, @NotNull Continuation<? super Unit> continuation) throws IOException {
        UtSettings.INSTANCE.setTreatOverflowAsError(false);
        final TestCaseGenerator test = initializeGenerator();
        initUt(soot);
        Object objCollect = provider.getIterator().collect(new FlowCollector() { // from class: cn.sast.framework.engine.UtStaticEngine.analyzeSuspend.2
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((IEntryPointProvider.AnalyzeTask) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(IEntryPointProvider.AnalyzeTask task, Continuation<? super Unit> continuation2) {
                Set entries = CollectionsKt.toMutableSet(task.getEntries());
                Set it = task.getAdditionalEntries();
                if (it != null) {
                    Boxing.boxBoolean(entries.addAll(it));
                }
                Scene.v().setEntryPoints(CollectionsKt.toList(entries));
                soot.constructCallGraph();
                LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(UtStaticEngine.logger);
                final String msg$iv = "Run symbolic analysis for task: " + task.getName();
                UtStaticEngine utStaticEngine = this;
                SootCtx sootCtx = soot;
                TestCaseGenerator testCaseGenerator = test;
                IUTBotResultCollector iUTBotResultCollector = result;
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyzeSuspend$2$emit$$inlined$bracket$default$1
                    public final Object invoke() {
                        return "Started: " + msg$iv;
                    }
                });
                final LocalDateTime startTime$iv = LocalDateTime.now();
                boolean alreadyLogged$iv = false;
                final Ref.ObjectRef res$iv = new Ref.ObjectRef();
                res$iv.element = Maybe.Companion.empty();
                try {
                    try {
                        utStaticEngine.runTest(sootCtx, testCaseGenerator, entries, iUTBotResultCollector);
                        res$iv.element = new Maybe(Unit.INSTANCE);
                        ((Maybe) res$iv.element).getOrThrow();
                        if (((Maybe) res$iv.element).getHasValue()) {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyzeSuspend$2$emit$$inlined$bracket$default$2
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                    String str = msg$iv;
                                    Result.Companion companion = Result.Companion;
                                    Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                    return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                                }
                            });
                        } else {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyzeSuspend$2$emit$$inlined$bracket$default$3
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                                }
                            });
                        }
                        return Unit.INSTANCE;
                    } finally {
                    }
                } catch (Throwable th) {
                    if (!alreadyLogged$iv) {
                        if (((Maybe) res$iv.element).getHasValue()) {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyzeSuspend$2$emit$$inlined$bracket$default$5
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                    String str = msg$iv;
                                    Result.Companion companion = Result.Companion;
                                    Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                    return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                                }
                            });
                        } else {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyzeSuspend$2$emit$$inlined$bracket$default$6
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                                }
                            });
                        }
                    }
                    throw th;
                }
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: UtStaticEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "UtStaticEngine.kt", l = {224}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "invokeSuspend", c = "cn.sast.framework.engine.UtStaticEngine$analyze$1")
    @SourceDebugExtension({"SMAP\nUtStaticEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtStaticEngine.kt\ncn/sast/framework/engine/UtStaticEngine$analyze$1\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,229:1\n49#2,24:230\n*S KotlinDebug\n*F\n+ 1 UtStaticEngine.kt\ncn/sast/framework/engine/UtStaticEngine$analyze$1\n*L\n223#1:230,24\n*E\n"})
    /* renamed from: cn.sast.framework.engine.UtStaticEngine$analyze$1, reason: invalid class name */
    /* loaded from: UtStaticEngine$analyze$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        int label;
        final /* synthetic */ SootCtx $soot;
        final /* synthetic */ IEntryPointProvider $provider;
        final /* synthetic */ IUTBotResultCollector $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SootCtx $soot, IEntryPointProvider $provider, IUTBotResultCollector $result, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$soot = $soot;
            this.$provider = $provider;
            this.$result = $result;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return UtStaticEngine.this.new AnonymousClass1(this.$soot, this.$provider, this.$result, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            int i;
            Ref.ObjectRef objectRef;
            Ref.ObjectRef res$iv;
            LocalDateTime startTime$iv;
            final String msg$iv;
            LoggerWithLogMethod $this$bracket_u24default$iv;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                try {
                    switch (this.label) {
                        case PointsToGraphKt.pathStrictMod /* 0 */:
                            ResultKt.throwOnFailure($result);
                            $this$bracket_u24default$iv = LoggingKt.info(UtStaticEngine.logger);
                            msg$iv = "Run symbolic analysis";
                            UtStaticEngine utStaticEngine = UtStaticEngine.this;
                            SootCtx sootCtx = this.$soot;
                            IEntryPointProvider iEntryPointProvider = this.$provider;
                            IUTBotResultCollector iUTBotResultCollector = this.$result;
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$1
                                public final Object invoke() {
                                    return "Started: " + msg$iv;
                                }
                            });
                            startTime$iv = LocalDateTime.now();
                            i = 0;
                            res$iv = new Ref.ObjectRef();
                            res$iv.element = Maybe.Companion.empty();
                            objectRef = res$iv;
                            this.L$0 = $this$bracket_u24default$iv;
                            this.L$1 = msg$iv;
                            this.L$2 = startTime$iv;
                            this.L$3 = res$iv;
                            this.L$4 = objectRef;
                            this.I$0 = 0;
                            this.label = 1;
                            if (utStaticEngine.analyzeSuspend(sootCtx, iEntryPointProvider, iUTBotResultCollector, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            i = this.I$0;
                            objectRef = (Ref.ObjectRef) this.L$4;
                            res$iv = (Ref.ObjectRef) this.L$3;
                            startTime$iv = (LocalDateTime) this.L$2;
                            msg$iv = (String) this.L$1;
                            $this$bracket_u24default$iv = (LoggerWithLogMethod) this.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef.element = new Maybe(Unit.INSTANCE);
                    ((Maybe) res$iv.element).getOrThrow();
                    if (((Maybe) res$iv.element).getHasValue()) {
                        final LocalDateTime localDateTime = startTime$iv;
                        final String str = msg$iv;
                        final Ref.ObjectRef objectRef2 = res$iv;
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$2
                            public final Object invoke() {
                                LocalDateTime localDateTime2 = localDateTime;
                                Intrinsics.checkNotNull(localDateTime2);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime2);
                                String str2 = str;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) objectRef2.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str2 + " " + "";
                            }
                        });
                    } else {
                        final LocalDateTime localDateTime2 = startTime$iv;
                        final String str2 = msg$iv;
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$3
                            public final Object invoke() {
                                LocalDateTime localDateTime3 = localDateTime2;
                                Intrinsics.checkNotNull(localDateTime3);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime3) + "): " + str2 + " <Nothing>";
                            }
                        });
                    }
                    return Unit.INSTANCE;
                } catch (Throwable t$iv) {
                    final LocalDateTime localDateTime3 = startTime$iv;
                    final String str3 = msg$iv;
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$4
                        public final Object invoke() {
                            LocalDateTime localDateTime4 = localDateTime3;
                            Intrinsics.checkNotNull(localDateTime4);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime4);
                            String str4 = str3;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(ResultKt.createFailure(t$iv));
                            return "Finished (in " + strElapsedSecFrom + "): " + str4 + " :: EXCEPTION :: " + "";
                        }
                    });
                    throw t$iv;
                }
            } catch (Throwable th) {
                if (i == 0) {
                    if (((Maybe) res$iv.element).getHasValue()) {
                        final LocalDateTime localDateTime4 = startTime$iv;
                        final String str4 = msg$iv;
                        final Ref.ObjectRef objectRef3 = res$iv;
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$5
                            public final Object invoke() {
                                LocalDateTime localDateTime5 = localDateTime4;
                                Intrinsics.checkNotNull(localDateTime5);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime5);
                                String str5 = str4;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(((Maybe) objectRef3.element).getOrThrow());
                                return "Finished (in " + strElapsedSecFrom + "): " + str5 + " " + "";
                            }
                        });
                    } else {
                        final LocalDateTime localDateTime5 = startTime$iv;
                        final String str5 = msg$iv;
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.engine.UtStaticEngine$analyze$1$invokeSuspend$$inlined$bracket$default$6
                            public final Object invoke() {
                                LocalDateTime localDateTime6 = localDateTime5;
                                Intrinsics.checkNotNull(localDateTime6);
                                return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime6) + "): " + str5 + " <Nothing>";
                            }
                        });
                    }
                }
                throw th;
            }
        }
    }

    public final void analyze(@NotNull SootCtx soot, @NotNull IEntryPointProvider provider, @NotNull IUTBotResultCollector result) {
        Intrinsics.checkNotNullParameter(soot, "soot");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(result, "result");
        BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(soot, provider, result, null), 1, (Object) null);
    }
}
