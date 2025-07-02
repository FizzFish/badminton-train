package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.apk.ApkLifeCycleComponent;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ProjectFileLocator;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.options.FlagOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionTransformContext;
import com.github.ajalt.clikt.parameters.options.OptionWithValues;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.FileKt;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;

/* compiled from: AndroidOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018��2\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010$\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010 \u001a\u00020!*\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Lcn/sast/cli/command/AndroidOptions;", "Lcn/sast/cli/command/TargetOptions;", "Lcn/sast/cli/command/IClassAnalyzeOptionGroup;", "<init>", "()V", "androidPlatformDir", "Ljava/io/File;", "getAndroidPlatformDir", "()Ljava/io/File;", "androidPlatformDir$delegate", "Lkotlin/properties/ReadOnlyProperty;", "oneComponentAtATime", "", "getOneComponentAtATime", "()Z", "oneComponentAtATime$delegate", "infoFlowConfig", "Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;", "getInfoFlowConfig", "()Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;", "infoFlowConfig$delegate", "Lkotlin/Lazy;", "getProvider", "Lcn/sast/framework/entries/IEntryPointProvider;", "sootCtx", "Lcn/sast/framework/SootCtx;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "configureMainConfig", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "targetApkFile", "", "getTargetApkFile", "(Lcn/sast/api/config/MainConfig;)Ljava/lang/String;", "initSoot", "corax-cli"})
@SourceDebugExtension({"SMAP\nAndroidOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidOptions.kt\ncn/sast/cli/command/AndroidOptions\n+ 2 Validate.kt\ncom/github/ajalt/clikt/parameters/options/ValidateKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,94:1\n65#2,5:95\n25#2:100\n1#3:101\n*S KotlinDebug\n*F\n+ 1 AndroidOptions.kt\ncn/sast/cli/command/AndroidOptions\n*L\n20#1:95,5\n20#1:100\n*E\n"})
/* loaded from: AndroidOptions.class */
public final class AndroidOptions extends TargetOptions implements IClassAnalyzeOptionGroup {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(AndroidOptions.class, "androidPlatformDir", "getAndroidPlatformDir()Ljava/io/File;", 0)), Reflection.property1(new PropertyReference1Impl(AndroidOptions.class, "oneComponentAtATime", "getOneComponentAtATime()Z", 0))};

    @NotNull
    private final ReadOnlyProperty androidPlatformDir$delegate;

    @NotNull
    private final ReadOnlyProperty oneComponentAtATime$delegate;

    @NotNull
    private final Lazy infoFlowConfig$delegate;

    public AndroidOptions() {
        super("Android Target Options");
        OptionWithValues $this$check_u24default$iv = OptionWithValuesKt.required(FileKt.file$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "Sets the android platform directory or path of android.jar. \nThe value of environment variable \"ANDROID_JARS\" is also accepted.", (String) null, false, "ANDROID_JARS", (Map) null, (CompletionCandidates) null, (String) null, false, 492, (Object) null), true, false, false, false, false, false, 62, (Object) null));
        this.androidPlatformDir$delegate = OptionWithValues.DefaultImpls.copy$default($this$check_u24default$iv, $this$check_u24default$iv.getTransformValue(), $this$check_u24default$iv.getTransformEach(), $this$check_u24default$iv.getTransformAll(), new Function2<OptionTransformContext, File, Unit>() { // from class: cn.sast.cli.command.AndroidOptions$special$$inlined$check$default$1
            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
            public final void invoke(@NotNull OptionTransformContext $this$copy, File file) throws KotlinNothingValueException {
                Intrinsics.checkNotNullParameter($this$copy, "$this$copy");
                if (file != null) {
                    File it = file;
                    boolean value$iv = it.isDirectory() | Intrinsics.areEqual(FilesKt.getExtension(it), "jar");
                    if (value$iv) {
                        return;
                    }
                    $this$copy.fail(file.toString());
                    throw new KotlinNothingValueException();
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) throws KotlinNothingValueException {
                invoke((OptionTransformContext) p1, (File) p2);
                return Unit.INSTANCE;
            }
        }, (Set) null, (Function1) null, (IntRange) null, (Function1) null, false, (Map) null, (String) null, (String) null, (Regex) null, (CompletionCandidates) null, (Set) null, false, false, false, 262128, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.oneComponentAtATime$delegate = OptionWithValuesKt.help(FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null), new String[0], false, (String) null, 6, (Object) null), "Set if analysis shall be performed on one entry of (Android component/Web application) at a time").provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.infoFlowConfig$delegate = LazyKt.lazy(() -> {
            return infoFlowConfig_delegate$lambda$5(r1);
        });
    }

    private final File getAndroidPlatformDir() {
        return (File) this.androidPlatformDir$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final boolean getOneComponentAtATime() {
        return ((Boolean) this.oneComponentAtATime$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    @Override // cn.sast.cli.command.IClassAnalyzeOptionGroup
    @NotNull
    /* renamed from: getInfoFlowConfig, reason: merged with bridge method [inline-methods] */
    public InfoflowAndroidConfiguration mo56getInfoFlowConfig() {
        return (InfoflowAndroidConfiguration) this.infoFlowConfig$delegate.getValue();
    }

    private static final InfoflowAndroidConfiguration infoFlowConfig_delegate$lambda$5(AndroidOptions this$0) {
        InfoflowAndroidConfiguration infoConfig = new InfoflowAndroidConfiguration();
        InfoflowAndroidConfiguration.AnalysisFileConfiguration $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241 = infoConfig.getAnalysisFileConfig();
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241.setTargetAPKFile("unused");
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241.setSourceSinkFile("unused");
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241.setAndroidPlatformDir("unused");
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241.setAdditionalClasspath("unused");
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u241.setOutputFile("unused");
        InfoflowAndroidConfiguration.CallbackConfiguration $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242 = infoConfig.getCallbackConfig();
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setEnableCallbacks(true);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setCallbackAnalyzer(InfoflowAndroidConfiguration.CallbackAnalyzer.Default);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setFilterThreadCallbacks(true);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setMaxCallbacksPerComponent(100);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setCallbackAnalysisTimeout(0);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setMaxAnalysisCallbackDepth(-1);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setSerializeCallbacks(false);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u242.setCallbacksFile("");
        infoConfig.getSourceSinkConfig();
        InfoflowAndroidConfiguration.IccConfiguration $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u244 = infoConfig.getIccConfig();
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u244.setIccModel((String) null);
        $this$infoFlowConfig_delegate_u24lambda_u245_u24lambda_u244.setIccResultsPurify(true);
        infoConfig.setOneComponentAtATime(this$0.getOneComponentAtATime());
        return infoConfig;
    }

    @Override // cn.sast.cli.command.TargetOptions
    @NotNull
    public IEntryPointProvider getProvider(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator) {
        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
        Intrinsics.checkNotNullParameter(locator, "locator");
        MainConfig mainConfig = sootCtx.getMainConfig();
        String targetApkFile = getTargetApkFile(mainConfig);
        return new ApkLifeCycleComponent(mo56getInfoFlowConfig(), mainConfig, targetApkFile);
    }

    @Override // cn.sast.cli.command.TargetOptions
    public void configureMainConfig(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        mainConfig.setAndroidPlatformDir(getAndroidPlatformDir().getAbsolutePath());
    }

    private final String getTargetApkFile(MainConfig $this$targetApkFile) {
        Iterable processDir = $this$targetApkFile.getProcessDir();
        if (!(processDir.size() == 1)) {
            throw new IllegalStateException(("process: " + $this$targetApkFile.getProcessDir() + " must be a single apk file").toString());
        }
        String targetApkFile = ((IResource) CollectionsKt.first(processDir)).getAbsolutePath();
        return targetApkFile;
    }

    @Override // cn.sast.cli.command.TargetOptions
    public void initSoot(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator) throws IllegalAccessException, NoWhenBranchMatchedException, NoSuchFieldException, IOException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
        Intrinsics.checkNotNullParameter(locator, "locator");
        MainConfig mainConfig = sootCtx.getMainConfig();
        String targetApkFile = getTargetApkFile(mainConfig);
        String androidJar = mainConfig.getAndroidJarClasspath(targetApkFile);
        if (androidJar == null) {
            throw new IllegalStateException(("could not find android jar in androidPlatformDir: " + mainConfig.getAndroidPlatformDir() + " with apk: " + targetApkFile).toString());
        }
        mainConfig.setClasspath(mainConfig.getClasspath().add(androidJar));
        sootCtx.configureSoot();
        sootCtx.constructSoot(locator);
    }
}
