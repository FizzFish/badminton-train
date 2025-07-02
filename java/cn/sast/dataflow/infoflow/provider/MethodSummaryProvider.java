package cn.sast.dataflow.infoflow.provider;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.api.config.PreAnalysisCoroutineScopeKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.jimple.infoflow.methodSummary.data.provider.AbstractMethodSummaryProvider;
import soot.jimple.infoflow.methodSummary.data.summary.ClassMethodSummaries;
import soot.jimple.infoflow.methodSummary.data.summary.ClassSummaries;
import soot.jimple.infoflow.methodSummary.data.summary.MethodSummaries;
import soot.util.MultiMap;

/* compiled from: MethodSummaryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018�� 62\u00020\u0001:\u00016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0016J \u0010\u001d\u001a\u0004\u0018\u00010\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010 \u001a\u00020\u000eH\u0016J\u0014\u0010\"\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000eH\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\u001c\u0010&\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\b\u0010'\u001a\u0004\u0018\u00010\u000eH\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u001eH\u0016J\u000e\u00104\u001a\u00020)H\u0086@¢\u0006\u0002\u00105R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101¨\u00067"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/MethodSummaryProvider;", "Lsoot/jimple/infoflow/methodSummary/data/provider/AbstractMethodSummaryProvider;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "preAnalysisImpl", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/api/config/PreAnalysisCoroutineScope;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getPreAnalysisImpl", "()Lcn/sast/api/config/PreAnalysisCoroutineScope;", "loadedClasses", "", "", "classSummaries", "Lsoot/jimple/infoflow/methodSummary/data/summary/ClassSummaries;", "getClassSummaries", "()Lsoot/jimple/infoflow/methodSummary/data/summary/ClassSummaries;", "subsigMethodsWithSummaries", "getSubsigMethodsWithSummaries", "()Ljava/util/Set;", "setSubsigMethodsWithSummaries", "(Ljava/util/Set;)V", "getSupportedClasses", "getAllClassesWithSummaries", "supportsClass", "", "clazz", "getMethodFlows", "Lsoot/jimple/infoflow/methodSummary/data/summary/ClassMethodSummaries;", "className", "methodSignature", "classes", "getClassFlows", "mayHaveSummaryForMethod", "subsig", "getSummaries", "isMethodExcluded", "subSignature", "addSubsigsForMethod", "", "read", "Lsoot/jimple/infoflow/methodSummary/data/summary/MethodSummaries;", "addMethodSummaries", "newSummaries", "aiCheckerImpl", "Lcn/sast/dataflow/infoflow/provider/ModelingConfigImpl;", "getAiCheckerImpl", "()Lcn/sast/dataflow/infoflow/provider/ModelingConfigImpl;", "aiCheckerImpl$delegate", "Lkotlin/Lazy;", "initialize", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-data-flow"})
/* loaded from: MethodSummaryProvider.class */
public class MethodSummaryProvider extends AbstractMethodSummaryProvider {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final PreAnalysisCoroutineScope preAnalysisImpl;

    @NotNull
    private final Set<String> loadedClasses;

    @NotNull
    private final ClassSummaries classSummaries;

    @NotNull
    private Set<String> subsigMethodsWithSummaries;

    @NotNull
    private final Lazy aiCheckerImpl$delegate;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(MethodSummaryProvider::logger$lambda$2);

    /* compiled from: MethodSummaryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/MethodSummaryProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: MethodSummaryProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    public MethodSummaryProvider(@NotNull MainConfig mainConfig, @NotNull PreAnalysisCoroutineScope preAnalysisImpl) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(preAnalysisImpl, "preAnalysisImpl");
        this.mainConfig = mainConfig;
        this.preAnalysisImpl = preAnalysisImpl;
        this.loadedClasses = new LinkedHashSet();
        this.classSummaries = new ClassSummaries();
        this.subsigMethodsWithSummaries = new LinkedHashSet();
        this.aiCheckerImpl$delegate = LazyKt.lazy(() -> {
            return aiCheckerImpl_delegate$lambda$1(r1);
        });
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final PreAnalysisCoroutineScope getPreAnalysisImpl() {
        return this.preAnalysisImpl;
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final ClassSummaries getClassSummaries() {
        return this.classSummaries;
    }

    @NotNull
    public final Set<String> getSubsigMethodsWithSummaries() {
        return this.subsigMethodsWithSummaries;
    }

    public final void setSubsigMethodsWithSummaries(@NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.subsigMethodsWithSummaries = set;
    }

    @NotNull
    public Set<String> getSupportedClasses() {
        return this.loadedClasses;
    }

    @NotNull
    public Set<String> getAllClassesWithSummaries() {
        return this.loadedClasses;
    }

    public boolean supportsClass(@NotNull String clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return this.loadedClasses.contains(clazz);
    }

    @Nullable
    public ClassMethodSummaries getMethodFlows(@NotNull String className, @NotNull String methodSignature) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        ClassMethodSummaries classSummaries = getClassSummaries(className);
        if (classSummaries != null) {
            return classSummaries.filterForMethod(methodSignature);
        }
        return null;
    }

    @Nullable
    public ClassSummaries getMethodFlows(@NotNull Set<String> set, @NotNull String methodSignature) {
        Intrinsics.checkNotNullParameter(set, "classes");
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        return this.classSummaries.filterForMethod(set, methodSignature);
    }

    @Nullable
    public ClassMethodSummaries getClassFlows(@Nullable String clazz) {
        return this.classSummaries.getClassSummaries(clazz);
    }

    public boolean mayHaveSummaryForMethod(@NotNull String subsig) {
        Intrinsics.checkNotNullParameter(subsig, "subsig");
        return this.subsigMethodsWithSummaries.contains(subsig);
    }

    @NotNull
    public ClassSummaries getSummaries() {
        return this.classSummaries;
    }

    public boolean isMethodExcluded(@Nullable String className, @Nullable String subSignature) {
        ClassMethodSummaries summaries = getClassSummaries(className);
        return summaries != null && summaries.getMethodSummaries().isExcluded(subSignature);
    }

    @Nullable
    public ClassMethodSummaries getClassSummaries(@Nullable String className) {
        return this.classSummaries.getClassSummaries(className);
    }

    protected void addSubsigsForMethod(@NotNull MethodSummaries read) {
        Intrinsics.checkNotNullParameter(read, "read");
        MultiMap flows = read.getFlows();
        MultiMap clears = read.getClears();
        if (flows != null) {
            Set<String> set = this.subsigMethodsWithSummaries;
            Set setKeySet = flows.keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet, "keySet(...)");
            set.addAll(setKeySet);
        }
        if (clears != null) {
            Set<String> set2 = this.subsigMethodsWithSummaries;
            Set setKeySet2 = clears.keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet2, "keySet(...)");
            set2.addAll(setKeySet2);
        }
    }

    public void addMethodSummaries(@NotNull ClassMethodSummaries newSummaries) {
        Intrinsics.checkNotNullParameter(newSummaries, "newSummaries");
        synchronized (this) {
            MethodSummaries methodSummaries = newSummaries.getMethodSummaries();
            Intrinsics.checkNotNullExpressionValue(methodSummaries, "getMethodSummaries(...)");
            addSubsigsForMethod(methodSummaries);
            this.classSummaries.merge(newSummaries);
            Set<String> set = this.loadedClasses;
            String className = newSummaries.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
            set.add(className);
        }
    }

    private final ModelingConfigImpl getAiCheckerImpl() {
        return (ModelingConfigImpl) this.aiCheckerImpl$delegate.getValue();
    }

    private static final ModelingConfigImpl aiCheckerImpl_delegate$lambda$1(MethodSummaryProvider this$0) {
        return new ModelingConfigImpl(this$0, this$0.preAnalysisImpl);
    }

    @Nullable
    public final Object initialize(@NotNull Continuation<? super Unit> continuation) {
        Object objProcessAIAnalysisUnits = PreAnalysisCoroutineScopeKt.processAIAnalysisUnits(getAiCheckerImpl(), this.preAnalysisImpl, continuation);
        return objProcessAIAnalysisUnits == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objProcessAIAnalysisUnits : Unit.INSTANCE;
    }
}
