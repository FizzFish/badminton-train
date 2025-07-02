package cn.sast.framework.entries.apk;

import cn.sast.api.config.MainConfig;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.utils.PhantomValueForType;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xmlpull.v1.XmlPullParserException;
import soot.Body;
import soot.Local;
import soot.LocalGenerator;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.infoflow.InfoflowConfiguration;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;
import soot.jimple.infoflow.android.SetupApplication;
import soot.jimple.infoflow.android.entryPointCreators.AndroidEntryPointCreator;
import soot.jimple.infoflow.android.entryPointCreators.components.ComponentEntryPointCollection;
import soot.jimple.infoflow.android.manifest.IManifestHandler;
import soot.jimple.infoflow.handlers.PreAnalysisHandler;
import soot.jimple.infoflow.ipc.IIPCManager;
import soot.jimple.infoflow.methodSummary.data.provider.IMethodSummaryProvider;
import soot.jimple.infoflow.methodSummary.data.summary.ClassMethodSummaries;
import soot.jimple.infoflow.methodSummary.taintWrappers.SummaryTaintWrapper;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinitionProvider;

/* compiled from: ApkLifeCycleComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� U2\u00020\u0001:\u0002UVB\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015B!\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u001aJ\u0012\u0010F\u001a\u00020G2\n\u0010H\u001a\u00060BR\u00020��J.\u0010I\u001a\u00020G*\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010L\u001a\u00020\u00032\f\u0010M\u001a\b\u0012\u0004\u0012\u00020O0NH\u0086@¢\u0006\u0002\u0010PR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b!\u0010\"R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b#\u0010\"\"\u0004\b$\u0010%R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b&\u0010\"R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b'\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b,\u0010\"\"\u0004\b-\u0010%R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b.\u0010/R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b0\u0010/R\u0011\u0010\u0010\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b1\u0010/R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b2\u0010\"R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b5\u0010\"R\u001c\u00106\u001a\u0004\u0018\u000107X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001b\u0010<\u001a\u00020\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\b=\u0010>R\u001f\u0010A\u001a\u00060BR\u00020��8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010@\u001a\u0004\bC\u0010DR\u001a\u0010Q\u001a\b\u0012\u0004\u0012\u00020K0R8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010T¨\u0006W"}, d2 = {"Lcn/sast/framework/entries/apk/ApkLifeCycleComponent;", "Lcn/sast/framework/entries/IEntryPointProvider;", "targetAPKFile", "", "androidPlatformDir", "oneComponentAtATime", "", "ignoreFlowsInSystemPackages", "enableCallbacks", "callbacksFile", "callbackAnalyzer", "Lcn/sast/framework/entries/apk/CallbackAnalyzerType;", "filterThreadCallbacks", "maxCallbacksPerComponent", "", "callbackAnalysisTimeout", "maxCallbackAnalysisDepth", "serializeCallbacks", "iccModel", "iccResultsPurify", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;Lcn/sast/framework/entries/apk/CallbackAnalyzerType;ZIIIZLjava/lang/String;Z)V", "c", "Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "(Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;Lcn/sast/api/config/MainConfig;Ljava/lang/String;)V", "getTargetAPKFile", "()Ljava/lang/String;", "setTargetAPKFile", "(Ljava/lang/String;)V", "getAndroidPlatformDir", "setAndroidPlatformDir", "getOneComponentAtATime", "()Z", "getIgnoreFlowsInSystemPackages", "setIgnoreFlowsInSystemPackages", "(Z)V", "getEnableCallbacks", "getCallbacksFile", "getCallbackAnalyzer", "()Lcn/sast/framework/entries/apk/CallbackAnalyzerType;", "setCallbackAnalyzer", "(Lcn/sast/framework/entries/apk/CallbackAnalyzerType;)V", "getFilterThreadCallbacks", "setFilterThreadCallbacks", "getMaxCallbacksPerComponent", "()I", "getCallbackAnalysisTimeout", "getMaxCallbackAnalysisDepth", "getSerializeCallbacks", "getIccModel", "setIccModel", "getIccResultsPurify", "taintWrapper", "Lsoot/jimple/infoflow/methodSummary/taintWrappers/SummaryTaintWrapper;", "getTaintWrapper", "()Lsoot/jimple/infoflow/methodSummary/taintWrappers/SummaryTaintWrapper;", "setTaintWrapper", "(Lsoot/jimple/infoflow/methodSummary/taintWrappers/SummaryTaintWrapper;)V", "config", "getConfig", "()Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;", "config$delegate", "Lkotlin/Lazy;", "delegateSetupApplication", "Lcn/sast/framework/entries/apk/ApkLifeCycleComponent$WSetupApplication;", "getDelegateSetupApplication", "()Lcn/sast/framework/entries/apk/ApkLifeCycleComponent$WSetupApplication;", "delegateSetupApplication$delegate", "injectStubDroidHierarchy", "", "analyzer", "emit", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "name", "entries", "", "Lsoot/SootMethod;", "(Lkotlinx/coroutines/flow/FlowCollector;Ljava/lang/String;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "WSetupApplication", "corax-framework"})
/* loaded from: ApkLifeCycleComponent.class */
public final class ApkLifeCycleComponent implements IEntryPointProvider {

    @NotNull
    private String targetAPKFile;

    @NotNull
    private String androidPlatformDir;
    private final boolean oneComponentAtATime;
    private boolean ignoreFlowsInSystemPackages;
    private final boolean enableCallbacks;

    @NotNull
    private final String callbacksFile;

    @NotNull
    private CallbackAnalyzerType callbackAnalyzer;
    private boolean filterThreadCallbacks;
    private final int maxCallbacksPerComponent;
    private final int callbackAnalysisTimeout;
    private final int maxCallbackAnalysisDepth;
    private final boolean serializeCallbacks;

    @Nullable
    private String iccModel;
    private final boolean iccResultsPurify;

    @Nullable
    private SummaryTaintWrapper taintWrapper;

    @NotNull
    private final Lazy config$delegate;

    @NotNull
    private final Lazy delegateSetupApplication$delegate;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ApkLifeCycleComponent::logger$lambda$2);

    public ApkLifeCycleComponent(@NotNull String targetAPKFile, @NotNull String androidPlatformDir, boolean oneComponentAtATime, boolean ignoreFlowsInSystemPackages, boolean enableCallbacks, @NotNull String callbacksFile, @NotNull CallbackAnalyzerType callbackAnalyzer, boolean filterThreadCallbacks, int maxCallbacksPerComponent, int callbackAnalysisTimeout, int maxCallbackAnalysisDepth, boolean serializeCallbacks, @Nullable String iccModel, boolean iccResultsPurify) {
        Intrinsics.checkNotNullParameter(targetAPKFile, "targetAPKFile");
        Intrinsics.checkNotNullParameter(androidPlatformDir, "androidPlatformDir");
        Intrinsics.checkNotNullParameter(callbacksFile, "callbacksFile");
        Intrinsics.checkNotNullParameter(callbackAnalyzer, "callbackAnalyzer");
        this.targetAPKFile = targetAPKFile;
        this.androidPlatformDir = androidPlatformDir;
        this.oneComponentAtATime = oneComponentAtATime;
        this.ignoreFlowsInSystemPackages = ignoreFlowsInSystemPackages;
        this.enableCallbacks = enableCallbacks;
        this.callbacksFile = callbacksFile;
        this.callbackAnalyzer = callbackAnalyzer;
        this.filterThreadCallbacks = filterThreadCallbacks;
        this.maxCallbacksPerComponent = maxCallbacksPerComponent;
        this.callbackAnalysisTimeout = callbackAnalysisTimeout;
        this.maxCallbackAnalysisDepth = maxCallbackAnalysisDepth;
        this.serializeCallbacks = serializeCallbacks;
        this.iccModel = iccModel;
        this.iccResultsPurify = iccResultsPurify;
        this.config$delegate = LazyKt.lazy(() -> {
            return config_delegate$lambda$0(r1);
        });
        this.delegateSetupApplication$delegate = LazyKt.lazy(() -> {
            return delegateSetupApplication_delegate$lambda$1(r1);
        });
    }

    public /* synthetic */ ApkLifeCycleComponent(String str, String str2, boolean z, boolean z2, boolean z3, String str3, CallbackAnalyzerType callbackAnalyzerType, boolean z4, int i, int i2, int i3, boolean z5, String str4, boolean z6, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i4 & 4) != 0 ? true : z, (i4 & 8) != 0 ? false : z2, (i4 & 16) != 0 ? true : z3, (i4 & 32) != 0 ? "" : str3, (i4 & 64) != 0 ? CallbackAnalyzerType.Default : callbackAnalyzerType, (i4 & 128) != 0 ? true : z4, (i4 & 256) != 0 ? 100 : i, (i4 & 512) != 0 ? 0 : i2, (i4 & 1024) != 0 ? -1 : i3, (i4 & 2048) != 0 ? false : z5, (i4 & 4096) != 0 ? null : str4, (i4 & 8192) != 0 ? true : z6);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    @NotNull
    public final String getTargetAPKFile() {
        return this.targetAPKFile;
    }

    public final void setTargetAPKFile(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.targetAPKFile = str;
    }

    @NotNull
    public final String getAndroidPlatformDir() {
        return this.androidPlatformDir;
    }

    public final void setAndroidPlatformDir(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.androidPlatformDir = str;
    }

    public final boolean getOneComponentAtATime() {
        return this.oneComponentAtATime;
    }

    public final boolean getIgnoreFlowsInSystemPackages() {
        return this.ignoreFlowsInSystemPackages;
    }

    public final void setIgnoreFlowsInSystemPackages(boolean z) {
        this.ignoreFlowsInSystemPackages = z;
    }

    public final boolean getEnableCallbacks() {
        return this.enableCallbacks;
    }

    @NotNull
    public final String getCallbacksFile() {
        return this.callbacksFile;
    }

    @NotNull
    public final CallbackAnalyzerType getCallbackAnalyzer() {
        return this.callbackAnalyzer;
    }

    public final void setCallbackAnalyzer(@NotNull CallbackAnalyzerType callbackAnalyzerType) {
        Intrinsics.checkNotNullParameter(callbackAnalyzerType, "<set-?>");
        this.callbackAnalyzer = callbackAnalyzerType;
    }

    public final boolean getFilterThreadCallbacks() {
        return this.filterThreadCallbacks;
    }

    public final void setFilterThreadCallbacks(boolean z) {
        this.filterThreadCallbacks = z;
    }

    public final int getMaxCallbacksPerComponent() {
        return this.maxCallbacksPerComponent;
    }

    public final int getCallbackAnalysisTimeout() {
        return this.callbackAnalysisTimeout;
    }

    public final int getMaxCallbackAnalysisDepth() {
        return this.maxCallbackAnalysisDepth;
    }

    public final boolean getSerializeCallbacks() {
        return this.serializeCallbacks;
    }

    @Nullable
    public final String getIccModel() {
        return this.iccModel;
    }

    public final void setIccModel(@Nullable String str) {
        this.iccModel = str;
    }

    public final boolean getIccResultsPurify() {
        return this.iccResultsPurify;
    }

    @Nullable
    public final SummaryTaintWrapper getTaintWrapper() {
        return this.taintWrapper;
    }

    public final void setTaintWrapper(@Nullable SummaryTaintWrapper summaryTaintWrapper) {
        this.taintWrapper = summaryTaintWrapper;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ApkLifeCycleComponent(@NotNull InfoflowAndroidConfiguration c, @NotNull MainConfig mainConfig, @NotNull String targetAPKFile) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(targetAPKFile, "targetAPKFile");
        String androidPlatformDir = mainConfig.getAndroidPlatformDir();
        Intrinsics.checkNotNull(androidPlatformDir);
        boolean oneComponentAtATime = c.getOneComponentAtATime();
        boolean ignoreFlowsInSystemPackages = c.getIgnoreFlowsInSystemPackages();
        boolean enableCallbacks = c.getCallbackConfig().getEnableCallbacks();
        String callbacksFile = c.getCallbackConfig().getCallbacksFile();
        Intrinsics.checkNotNullExpressionValue(callbacksFile, "getCallbacksFile(...)");
        InfoflowAndroidConfiguration.CallbackAnalyzer callbackAnalyzer = c.getCallbackConfig().getCallbackAnalyzer();
        Intrinsics.checkNotNullExpressionValue(callbackAnalyzer, "getCallbackAnalyzer(...)");
        this(targetAPKFile, androidPlatformDir, oneComponentAtATime, ignoreFlowsInSystemPackages, enableCallbacks, callbacksFile, ApkLifeCycleComponentKt.getConvert(callbackAnalyzer), c.getCallbackConfig().getFilterThreadCallbacks(), c.getCallbackConfig().getMaxCallbacksPerComponent(), c.getCallbackConfig().getCallbackAnalysisTimeout(), c.getCallbackConfig().getMaxAnalysisCallbackDepth(), c.getCallbackConfig().isSerializeCallbacks(), c.getIccConfig().getIccModel(), c.getIccConfig().isIccResultsPurifyEnabled());
    }

    /* compiled from: ApkLifeCycleComponent.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/entries/apk/ApkLifeCycleComponent$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: ApkLifeCycleComponent$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }

    /* compiled from: ApkLifeCycleComponent.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n��\b\u0086\u0004\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0016J\u001a\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0018\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0016H\u0014R\u0011\u0010\u000f\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/entries/apk/ApkLifeCycleComponent$WSetupApplication;", "Lsoot/jimple/infoflow/android/SetupApplication;", "config", "Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;", "<init>", "(Lcn/sast/framework/entries/apk/ApkLifeCycleComponent;Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration;)V", "lifecycleMethods", "", "Lsoot/SootMethod;", "clearCallBackCache", "", "parseAppResources", "calculateCallbacks", "sourcesAndSinks", "Lsoot/jimple/infoflow/sourcesSinks/definitions/ISourceSinkDefinitionProvider;", "entryPoint", "Lsoot/SootClass;", "getEntryPoint", "()Lsoot/SootMethod;", "createEntryPointCreator", "Lsoot/jimple/infoflow/android/entryPointCreators/AndroidEntryPointCreator;", "components", "", "corax-framework"})
    /* loaded from: ApkLifeCycleComponent$WSetupApplication.class */
    public final class WSetupApplication extends SetupApplication {
        final /* synthetic */ ApkLifeCycleComponent this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WSetupApplication(@NotNull ApkLifeCycleComponent this$0, InfoflowAndroidConfiguration config) {
            super(config, (IIPCManager) null);
            Intrinsics.checkNotNullParameter(config, "config");
            this.this$0 = this$0;
        }

        @Nullable
        public final Set<SootMethod> lifecycleMethods() {
            Set lifecycleMethods = null;
            if (this.entryPointCreator != null) {
                ComponentEntryPointCollection entryPoints = this.entryPointCreator.getComponentToEntryPointInfo();
                Intrinsics.checkNotNullExpressionValue(entryPoints, "getComponentToEntryPointInfo(...)");
                Collection lifecycleMethods2 = entryPoints.getLifecycleMethods();
                Intrinsics.checkNotNullExpressionValue(lifecycleMethods2, "getLifecycleMethods(...)");
                lifecycleMethods = CollectionsKt.toSet(lifecycleMethods2);
            }
            return lifecycleMethods;
        }

        public final void clearCallBackCache() {
            this.callbackMethods.clear();
            this.fragmentClasses.clear();
        }

        public void parseAppResources() throws XmlPullParserException, IOException {
            super.parseAppResources();
        }

        public final void calculateCallbacks(@Nullable ISourceSinkDefinitionProvider sourcesAndSinks, @Nullable SootClass entryPoint) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            Method it = SetupApplication.class.getDeclaredMethod("calculateCallbacks", ISourceSinkDefinitionProvider.class, SootClass.class);
            it.setAccessible(true);
            it.invoke(this, sourcesAndSinks, entryPoint);
        }

        @NotNull
        public final SootMethod getEntryPoint() {
            SootMethod generatedMainMethod = this.entryPointCreator.getGeneratedMainMethod();
            Intrinsics.checkNotNullExpressionValue(generatedMainMethod, "getGeneratedMainMethod(...)");
            return generatedMainMethod;
        }

        @NotNull
        protected AndroidEntryPointCreator createEntryPointCreator(@Nullable final Set<SootClass> set) {
            final IManifestHandler iManifestHandler = this.manifest;
            return new AndroidEntryPointCreator(set, iManifestHandler) { // from class: cn.sast.framework.entries.apk.ApkLifeCycleComponent$WSetupApplication$createEntryPointCreator$1
                private final PhantomValueForType p;

                {
                    super(iManifestHandler, set);
                    this.p = new PhantomValueForType(null, 1, null);
                }

                protected Value getValueForType(Type tp, Set<SootClass> set2, Set<? extends SootClass> set3, Set<Local> set4, boolean ignoreExcludes) {
                    Intrinsics.checkNotNullParameter(tp, "tp");
                    PhantomValueForType phantomValueForType = this.p;
                    Body body = this.body;
                    Intrinsics.checkNotNullExpressionValue(body, "body");
                    LocalGenerator localGenerator = this.generator;
                    Intrinsics.checkNotNullExpressionValue(localGenerator, "generator");
                    return phantomValueForType.getValueForType(body, localGenerator, tp);
                }
            };
        }
    }

    @NotNull
    public final InfoflowAndroidConfiguration getConfig() {
        return (InfoflowAndroidConfiguration) this.config$delegate.getValue();
    }

    private static final InfoflowAndroidConfiguration config_delegate$lambda$0(ApkLifeCycleComponent this$0) {
        InfoflowAndroidConfiguration config = new InfoflowAndroidConfiguration();
        config.setIgnoreFlowsInSystemPackages(this$0.ignoreFlowsInSystemPackages);
        config.setSootIntegrationMode(InfoflowConfiguration.SootIntegrationMode.CreateNewInstance);
        config.setCallgraphAlgorithm((InfoflowConfiguration.CallgraphAlgorithm) null);
        InfoflowAndroidConfiguration.IccConfiguration iccConfig = config.getIccConfig();
        iccConfig.setIccModel(this$0.iccModel);
        iccConfig.setIccResultsPurify(this$0.iccResultsPurify);
        InfoflowAndroidConfiguration.AnalysisFileConfiguration analysisFileConfig = config.getAnalysisFileConfig();
        analysisFileConfig.setTargetAPKFile(this$0.targetAPKFile);
        InfoflowAndroidConfiguration.CallbackConfiguration callbackConfig = config.getCallbackConfig();
        callbackConfig.setEnableCallbacks(this$0.enableCallbacks);
        callbackConfig.setCallbacksFile(this$0.callbacksFile);
        callbackConfig.setCallbackAnalyzer(ApkLifeCycleComponentKt.getConvert(this$0.callbackAnalyzer));
        callbackConfig.setFilterThreadCallbacks(this$0.filterThreadCallbacks);
        callbackConfig.setMaxCallbacksPerComponent(this$0.maxCallbacksPerComponent);
        callbackConfig.setCallbackAnalysisTimeout(this$0.callbackAnalysisTimeout);
        callbackConfig.setMaxAnalysisCallbackDepth(this$0.maxCallbackAnalysisDepth);
        callbackConfig.setSerializeCallbacks(this$0.serializeCallbacks);
        IResource targetFile = Resource.INSTANCE.of(this$0.targetAPKFile);
        if (!targetFile.getExists()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {targetFile.getAbsolutePath()};
            String str = String.format("Target APK file %s does not exist", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            throw new IllegalStateException(str.toString());
        }
        return config;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WSetupApplication getDelegateSetupApplication() {
        return (WSetupApplication) this.delegateSetupApplication$delegate.getValue();
    }

    private static final WSetupApplication delegateSetupApplication_delegate$lambda$1(ApkLifeCycleComponent this$0) {
        this$0.getConfig().getAnalysisFileConfig().setAndroidPlatformDir(this$0.androidPlatformDir);
        WSetupApplication wSetupApplication = new WSetupApplication(this$0, this$0.getConfig());
        this$0.getConfig().getAnalysisFileConfig().setAndroidPlatformDir("unused");
        return wSetupApplication;
    }

    public final void injectStubDroidHierarchy(@NotNull WSetupApplication analyzer) {
        Intrinsics.checkNotNullParameter(analyzer, "analyzer");
        SummaryTaintWrapper taintWrapper = this.taintWrapper;
        if (taintWrapper == null) {
            return;
        }
        final IMethodSummaryProvider provider = taintWrapper.getProvider();
        analyzer.addPreprocessor(new PreAnalysisHandler() { // from class: cn.sast.framework.entries.apk.ApkLifeCycleComponent.injectStubDroidHierarchy.1
            public void onBeforeCallgraphConstruction() {
                ClassMethodSummaries summaries;
                for (String className : provider.getAllClassesWithSummaries()) {
                    SootClass sc = Scene.v().forceResolve(className, 2);
                    if (sc.isPhantom() && (summaries = provider.getClassFlows(className)) != null) {
                        if (summaries.hasInterfaceInfo()) {
                            if (summaries.isInterface()) {
                                sc.setModifiers(sc.getModifiers() | 512);
                            } else {
                                sc.setModifiers(sc.getModifiers() & (-513));
                            }
                        }
                        if (summaries.hasSuperclass()) {
                            String superclassName = summaries.getSuperClass();
                            SootClass scSuperclass = Scene.v().forceResolve(superclassName, 2);
                            sc.setSuperclass(scSuperclass);
                        }
                        if (summaries.hasInterfaces()) {
                            for (String intfName : summaries.getInterfaces()) {
                                SootClass scIntf = Scene.v().forceResolve(intfName, 2);
                                if (!sc.implementsInterface(intfName)) {
                                    sc.addInterface(scIntf);
                                }
                            }
                        }
                    }
                }
            }

            public void onAfterCallgraphConstruction() {
            }
        });
    }

    @Nullable
    public final Object emit(@NotNull FlowCollector<? super IEntryPointProvider.AnalyzeTask> flowCollector, @NotNull String name, @NotNull Set<? extends SootMethod> set, @NotNull Continuation<? super Unit> continuation) {
        Object objEmit = flowCollector.emit(new IEntryPointProvider.AnalyzeTask(name, set, this) { // from class: cn.sast.framework.entries.apk.ApkLifeCycleComponent.emit.2
            private final String name;
            private final Set<SootMethod> entries;
            private final Set<SootMethod> additionalEntries;
            private final Set<SootClass> components;

            {
                this.name = name;
                this.entries = set;
                this.additionalEntries = this.getDelegateSetupApplication().lifecycleMethods();
                this.components = this.getDelegateSetupApplication().getEntrypointClasses();
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            public Set<SootMethod> getMethodsMustAnalyze() {
                return IEntryPointProvider.AnalyzeTask.DefaultImpls.getMethodsMustAnalyze(this);
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            public String getName() {
                return this.name;
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            public Set<SootMethod> getEntries() {
                return this.entries;
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            public Set<SootMethod> getAdditionalEntries() {
                return this.additionalEntries;
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            /* renamed from: getComponents */
            public Set<SootClass> mo276getComponents() {
                return this.components;
            }

            @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
            public void needConstructCallGraph(SootCtx sootCtx) {
                Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
                sootCtx.showPta();
            }
        }, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return FlowKt.flow(new ApkLifeCycleComponent$iterator$1(this, null));
    }
}
