package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.common.IResFile;
import cn.sast.common.SafeDomParserFactory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.EntryPointCreatorFactory;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.component.HybridUnReachThenComponent;
import cn.sast.framework.entries.custom.CustomEntryProvider;
import cn.sast.framework.entries.custom.HybridCustomThenComponent;
import cn.sast.framework.entries.java.UnReachableEntryProvider;
import cn.sast.framework.entries.javaee.JavaEeEntryProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ProjectFileLocator;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.options.FlagOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import soot.jimple.infoflow.InfoflowConfiguration;

/* compiled from: JavaOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� %2\u00020\u00012\u00020\u0002:\u0001%B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u0018\u0010\u0015¨\u0006&"}, d2 = {"Lcn/sast/cli/command/JavaOptions;", "Lcn/sast/cli/command/TargetOptions;", "Lcn/sast/cli/command/IClassAnalyzeOptionGroup;", "<init>", "()V", "customEntryPoint", "", "", "getCustomEntryPoint", "()Ljava/util/List;", "customEntryPoint$delegate", "Lkotlin/properties/ReadOnlyProperty;", "infoFlowConfig", "Lsoot/jimple/infoflow/InfoflowConfiguration;", "getInfoFlowConfig", "()Lsoot/jimple/infoflow/InfoflowConfiguration;", "infoFlowConfig$delegate", "Lkotlin/Lazy;", "makeComponentDummyMain", "", "getMakeComponentDummyMain", "()Z", "makeComponentDummyMain$delegate", "disableJavaEEComponent", "getDisableJavaEEComponent", "disableJavaEEComponent$delegate", "getProvider", "Lcn/sast/framework/entries/IEntryPointProvider;", "sootCtx", "Lcn/sast/framework/SootCtx;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "configureMainConfig", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "initSoot", "Companion", "corax-cli"})
/* loaded from: JavaOptions.class */
public final class JavaOptions extends TargetOptions implements IClassAnalyzeOptionGroup {

    @NotNull
    private final ReadOnlyProperty customEntryPoint$delegate;

    @NotNull
    private final Lazy infoFlowConfig$delegate;

    @NotNull
    private final ReadOnlyProperty makeComponentDummyMain$delegate;

    @NotNull
    private final ReadOnlyProperty disableJavaEEComponent$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(JavaOptions.class, "customEntryPoint", "getCustomEntryPoint()Ljava/util/List;", 0)), Reflection.property1(new PropertyReference1Impl(JavaOptions.class, "makeComponentDummyMain", "getMakeComponentDummyMain()Z", 0)), Reflection.property1(new PropertyReference1Impl(JavaOptions.class, "disableJavaEEComponent", "getDisableJavaEEComponent()Z", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(JavaOptions::logger$lambda$1);

    public JavaOptions() {
        super("Java Target Options");
        this.customEntryPoint$delegate = OptionWithValuesKt.help(OptionWithValuesKt.multiple$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, "method signature, signature file", false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 506, (Object) null), (List) null, false, 3, (Object) null), "Sets the entry point method(s) for analyze.").provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.infoFlowConfig$delegate = LazyKt.lazy(JavaOptions::infoFlowConfig_delegate$lambda$0);
        this.makeComponentDummyMain$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "Simple entry point creator that builds a sequential list of method invocations. Each method is invoked only once.", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.disableJavaEEComponent$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--disable-javaee-component"}, "disable create the JavaEE lifecycle component methods", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[2]);
    }

    private final List<String> getCustomEntryPoint() {
        return (List) this.customEntryPoint$delegate.getValue(this, $$delegatedProperties[0]);
    }

    @Override // cn.sast.cli.command.IClassAnalyzeOptionGroup
    @NotNull
    /* renamed from: getInfoFlowConfig */
    public InfoflowConfiguration mo56getInfoFlowConfig() {
        return (InfoflowConfiguration) this.infoFlowConfig$delegate.getValue();
    }

    private static final InfoflowConfiguration infoFlowConfig_delegate$lambda$0() {
        return new InfoflowConfiguration();
    }

    private final boolean getMakeComponentDummyMain() {
        return ((Boolean) this.makeComponentDummyMain$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    private final boolean getDisableJavaEEComponent() {
        return ((Boolean) this.disableJavaEEComponent$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    @Override // cn.sast.cli.command.TargetOptions
    @NotNull
    public IEntryPointProvider getProvider(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator) {
        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
        Intrinsics.checkNotNullParameter(locator, "locator");
        Set entries = (Set) EntryPointCreatorFactory.INSTANCE.getEntryPointFromArgs(getCustomEntryPoint()).invoke();
        if (getMakeComponentDummyMain()) {
            if (!entries.isEmpty()) {
                return new HybridCustomThenComponent(sootCtx, entries);
            }
            return new HybridUnReachThenComponent(sootCtx);
        }
        if (!entries.isEmpty()) {
            return new CustomEntryProvider(entries);
        }
        if (getDisableJavaEEComponent()) {
            return new UnReachableEntryProvider(sootCtx, null, 2, null);
        }
        return (IEntryPointProvider) BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(locator, sootCtx, null), 1, (Object) null);
    }

    /* compiled from: JavaOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/entries/javaee/JavaEeEntryProvider;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "JavaOptions.kt", l = {64}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.cli.command.JavaOptions$getProvider$1")
    @SourceDebugExtension({"SMAP\nJavaOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaOptions.kt\ncn/sast/cli/command/JavaOptions$getProvider$1\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,117:1\n547#2,2:118\n*S KotlinDebug\n*F\n+ 1 JavaOptions.kt\ncn/sast/cli/command/JavaOptions$getProvider$1\n*L\n64#1:118,2\n*E\n"})
    /* renamed from: cn.sast.cli.command.JavaOptions$getProvider$1, reason: invalid class name */
    /* loaded from: JavaOptions$getProvider$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super JavaEeEntryProvider>, Object> {
        int label;
        final /* synthetic */ ProjectFileLocator $locator;
        final /* synthetic */ SootCtx $sootCtx;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ProjectFileLocator $locator, SootCtx $sootCtx, Continuation<? super AnonymousClass1> $completion) {
            super(2, $completion);
            this.$locator = $locator;
            this.$sootCtx = $sootCtx;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
            return new AnonymousClass1(this.$locator, this.$sootCtx, $completion);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super JavaEeEntryProvider> p2) {
            return create(p1, p2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) throws IOException {
            Object byFileExtension;
            boolean z;
            NodeList eles;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    byFileExtension = this.$locator.getByFileExtension("xml", (Continuation) this);
                    if (byFileExtension == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    byFileExtension = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Sequence $this$filterTo$iv = (Sequence) byFileExtension;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object element$iv : $this$filterTo$iv) {
                IResFile file = (IResFile) element$iv;
                OpenOption[] openOptionArr = new OpenOption[0];
                InputStream inputStreamNewInputStream = Files.newInputStream(file.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
                Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
                InputStream inputStream = inputStreamNewInputStream;
                Throwable th = null;
                try {
                    try {
                        InputStream it = inputStream;
                        try {
                            Document doc = SafeDomParserFactory.INSTANCE.createDocumentBuilder(false).parse(it);
                            eles = doc.getElementsByTagName("bean");
                        } catch (Exception e) {
                            JavaOptions.Companion.getLogger().debug(() -> {
                                return invokeSuspend$lambda$2$lambda$1$lambda$0(r1, r2);
                            });
                        }
                        if (eles == null || eles.getLength() <= 0) {
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(inputStream, (Throwable) null);
                            z = false;
                        } else {
                            CloseableKt.closeFinally(inputStream, (Throwable) null);
                            z = true;
                        }
                        if (z) {
                            destination$iv.add(element$iv);
                        }
                    } finally {
                    }
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStream, th);
                    throw th2;
                }
            }
            Set beanXmls = (Set) destination$iv;
            if (beanXmls.size() > 15) {
                KLogger logger = JavaOptions.Companion.getLogger();
                SootCtx sootCtx = this.$sootCtx;
                logger.debug(() -> {
                    return invokeSuspend$lambda$4(r1, r2);
                });
                JavaOptions.Companion.getLogger().info(() -> {
                    return invokeSuspend$lambda$5(r1);
                });
            } else {
                KLogger logger2 = JavaOptions.Companion.getLogger();
                SootCtx sootCtx2 = this.$sootCtx;
                logger2.info(() -> {
                    return invokeSuspend$lambda$7(r1, r2);
                });
            }
            return new JavaEeEntryProvider(this.$sootCtx, beanXmls);
        }

        private static final Object invokeSuspend$lambda$2$lambda$1$lambda$0(IResFile $file, Exception $e) {
            return "Can't parse xml document " + $file + ", e: " + $e.getMessage();
        }

        private static final Object invokeSuspend$lambda$4(Set $beanXmls, SootCtx $sootCtx) {
            return "found spring-beans xml: " + CollectionsKt.joinToString$default($beanXmls, ",\n\t", "[", "]", 0, (CharSequence) null, (v1) -> {
                return invokeSuspend$lambda$4$lambda$3(r6, v1);
            }, 24, (Object) null);
        }

        private static final CharSequence invokeSuspend$lambda$4$lambda$3(SootCtx $sootCtx, IResFile it) {
            return $sootCtx.getMainConfig().tryGetRelativePath(it).getRelativePath();
        }

        private static final Object invokeSuspend$lambda$5(Set $beanXmls) {
            return "found " + $beanXmls.size() + " spring-beans xml";
        }

        private static final Object invokeSuspend$lambda$7(Set $beanXmls, SootCtx $sootCtx) {
            return "found spring-beans xml: " + CollectionsKt.joinToString$default($beanXmls, ",\n\t", "[", "]", 0, (CharSequence) null, (v1) -> {
                return invokeSuspend$lambda$7$lambda$6(r6, v1);
            }, 24, (Object) null);
        }

        private static final CharSequence invokeSuspend$lambda$7$lambda$6(SootCtx $sootCtx, IResFile it) {
            return $sootCtx.getMainConfig().tryGetRelativePath(it).getRelativePath();
        }
    }

    @Override // cn.sast.cli.command.TargetOptions
    public void configureMainConfig(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
    }

    @Override // cn.sast.cli.command.TargetOptions
    public void initSoot(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator) throws IllegalAccessException, NoWhenBranchMatchedException, NoSuchFieldException, IOException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
        Intrinsics.checkNotNullParameter(locator, "locator");
        sootCtx.configureSoot();
        sootCtx.constructSoot(locator);
    }

    /* compiled from: JavaOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/cli/command/JavaOptions$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-cli"})
    /* loaded from: JavaOptions$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return JavaOptions.logger;
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }
}
