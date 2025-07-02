package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.ProcessInfoView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J6\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/engine/PreAnalysis;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "analyzeInScene", "", "soot", "Lcn/sast/framework/SootCtx;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "resultCollector", "Lcn/sast/framework/result/IPreAnalysisResultCollector;", "monitor", "Lcn/sast/framework/metrics/MetricsMonitor;", "(Lcn/sast/framework/SootCtx;Lcn/sast/framework/report/IProjectFileLocator;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/framework/result/IPreAnalysisResultCollector;Lcn/sast/framework/metrics/MetricsMonitor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nPreAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysis.kt\ncn/sast/framework/engine/PreAnalysis\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,36:1\n49#2,13:37\n62#2,11:54\n1557#3:50\n1628#3,3:51\n*S KotlinDebug\n*F\n+ 1 PreAnalysis.kt\ncn/sast/framework/engine/PreAnalysis\n*L\n28#1:37,13\n28#1:54,11\n31#1:50\n31#1:51,3\n*E\n"})
/* loaded from: PreAnalysis.class */
public final class PreAnalysis {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(PreAnalysis::logger$lambda$4);

    /* compiled from: PreAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PreAnalysis.kt", l = {30}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, n = {"monitor", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "analysisImpl", "alreadyLogged$iv"}, m = "analyzeInScene", c = "cn.sast.framework.engine.PreAnalysis")
    /* renamed from: cn.sast.framework.engine.PreAnalysis$analyzeInScene$1, reason: invalid class name */
    /* loaded from: PreAnalysis$analyzeInScene$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int I$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return PreAnalysis.this.analyzeInScene(null, null, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: PreAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/PreAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: PreAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    public PreAnalysis(@NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.mainConfig = mainConfig;
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeInScene(@org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r10, @org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r11, @org.jetbrains.annotations.NotNull com.feysh.corax.cache.analysis.SootInfoCache r12, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IPreAnalysisResultCollector r13, @org.jetbrains.annotations.NotNull cn.sast.framework.metrics.MetricsMonitor r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instructions count: 768
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysis.analyzeInScene(cn.sast.framework.SootCtx, cn.sast.framework.report.IProjectFileLocator, com.feysh.corax.cache.analysis.SootInfoCache, cn.sast.framework.result.IPreAnalysisResultCollector, cn.sast.framework.metrics.MetricsMonitor, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object analyzeInScene$lambda$0() {
        return "Before PreAnalysis: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    private static final Object analyzeInScene$lambda$3() {
        return "After PreAnalysis: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }
}
