package cn.sast.framework;

import cn.sast.api.AnalyzerEnv;
import cn.sast.api.util.IMonitor;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;

/* compiled from: AnalyzeTaskRunner.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� +2\u00020\u0001:\u0003)*+B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0087\u0001\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122 \b\u0002\u0010\u001c\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001d2&\b\u0002\u0010\u001f\u001a \b\u0001\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010 2 \b\u0002\u0010\"\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001d¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0086@¢\u0006\u0002\u0010'J\u000e\u0010$\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0011¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016¨\u0006,"}, d2 = {"Lcn/sast/framework/AnalyzeTaskRunner;", "", "numThreads", "", "sootCtx", "Lcn/sast/framework/SootCtx;", "monitor", "Lcn/sast/api/util/IMonitor;", "<init>", "(ILcn/sast/framework/SootCtx;Lcn/sast/api/util/IMonitor;)V", "getNumThreads", "()I", "getSootCtx", "()Lcn/sast/framework/SootCtx;", "getMonitor", "()Lcn/sast/api/util/IMonitor;", "analysisPasses", "", "Lcn/sast/framework/entries/IEntryPointProvider;", "", "Lcn/sast/framework/AnalyzeTaskRunner$Analysis;", "getAnalysisPasses", "()Ljava/util/Map;", "registerAnalysis", "", "phaseName", "", "provider", "before", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "analysis", "Lkotlin/Function2;", "Lcn/sast/framework/AnalyzeTaskRunner$Env;", "after", "(Ljava/lang/String;Lcn/sast/framework/entries/IEntryPointProvider;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "run", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Env", "Analysis", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nAnalyzeTaskRunner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalyzeTaskRunner.kt\ncn/sast/framework/AnalyzeTaskRunner\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,152:1\n381#2,7:153\n1557#3:160\n1628#3,3:161\n1557#3:172\n1628#3,3:173\n16#4,8:164\n16#4,8:176\n*S KotlinDebug\n*F\n+ 1 AnalyzeTaskRunner.kt\ncn/sast/framework/AnalyzeTaskRunner\n*L\n49#1:153,7\n59#1:160\n59#1:161,3\n106#1:172\n106#1:173,3\n66#1:164,8\n113#1:176,8\n*E\n"})
/* loaded from: AnalyzeTaskRunner.class */
public final class AnalyzeTaskRunner {
    private final int numThreads;

    @NotNull
    private final SootCtx sootCtx;

    @NotNull
    private final IMonitor monitor;

    @NotNull
    private final Map<IEntryPointProvider, List<Analysis>> analysisPasses;
    public static Class<?> v3r14yJn1Class;
    public static final int mask1 = 65576;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(AnalyzeTaskRunner::logger$lambda$6);

    @NotNull
    private static final Lazy<Integer> mask$delegate = LazyKt.lazy(AnalyzeTaskRunner::mask_delegate$lambda$7);

    /* compiled from: AnalyzeTaskRunner.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "AnalyzeTaskRunner.kt", l = {67, 67, 75, 114, 114}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 2, 2, 4, 4}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1"}, n = {"this", "scope", "processAnalysisTimer", "afterAnalysisTimer", "allAnalyses", "this", "scope", "processAnalysisTimer", "afterAnalysisTimer", "allAnalyses", "$this$bracket$iv", "s$iv", "this", "scope", "processAnalysisTimer", "afterAnalysisTimer", "allAnalyses", "s1", "incremental", "$this$bracket$iv", "s$iv"}, m = "run", c = "cn.sast.framework.AnalyzeTaskRunner")
    /* renamed from: cn.sast.framework.AnalyzeTaskRunner$run$1, reason: invalid class name */
    /* loaded from: AnalyzeTaskRunner$run$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AnalyzeTaskRunner.this.run(null, (Continuation) this);
        }
    }

    public AnalyzeTaskRunner(int numThreads, @NotNull SootCtx sootCtx, @NotNull IMonitor monitor) {
        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
        Intrinsics.checkNotNullParameter(monitor, "monitor");
        this.numThreads = numThreads;
        this.sootCtx = sootCtx;
        this.monitor = monitor;
        this.analysisPasses = new LinkedHashMap();
    }

    public final int getNumThreads() {
        return this.numThreads;
    }

    @NotNull
    public final SootCtx getSootCtx() {
        return this.sootCtx;
    }

    @NotNull
    public final IMonitor getMonitor() {
        return this.monitor;
    }

    /* compiled from: AnalyzeTaskRunner.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JG\u0010\u001c\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0015¨\u0006$"}, d2 = {"Lcn/sast/framework/AnalyzeTaskRunner$Env;", "", "provider", "Lcn/sast/framework/entries/IEntryPointProvider;", "task", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "sootCtx", "Lcn/sast/framework/SootCtx;", "entries", "", "Lsoot/SootMethod;", "methodsMustAnalyze", "<init>", "(Lcn/sast/framework/entries/IEntryPointProvider;Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;Lcn/sast/framework/SootCtx;Ljava/util/Collection;Ljava/util/Collection;)V", "getProvider", "()Lcn/sast/framework/entries/IEntryPointProvider;", "getTask", "()Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getSootCtx", "()Lcn/sast/framework/SootCtx;", "getEntries", "()Ljava/util/Collection;", "getMethodsMustAnalyze", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: AnalyzeTaskRunner$Env.class */
    public static final class Env {

        @NotNull
        private final IEntryPointProvider provider;

        @NotNull
        private final IEntryPointProvider.AnalyzeTask task;

        @NotNull
        private final SootCtx sootCtx;

        @NotNull
        private final Collection<SootMethod> entries;

        @NotNull
        private final Collection<SootMethod> methodsMustAnalyze;

        @NotNull
        public final IEntryPointProvider component1() {
            return this.provider;
        }

        @NotNull
        public final IEntryPointProvider.AnalyzeTask component2() {
            return this.task;
        }

        @NotNull
        public final SootCtx component3() {
            return this.sootCtx;
        }

        @NotNull
        public final Collection<SootMethod> component4() {
            return this.entries;
        }

        @NotNull
        public final Collection<SootMethod> component5() {
            return this.methodsMustAnalyze;
        }

        @NotNull
        public final Env copy(@NotNull IEntryPointProvider provider, @NotNull IEntryPointProvider.AnalyzeTask task, @NotNull SootCtx sootCtx, @NotNull Collection<? extends SootMethod> collection, @NotNull Collection<? extends SootMethod> collection2) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
            Intrinsics.checkNotNullParameter(collection, "entries");
            Intrinsics.checkNotNullParameter(collection2, "methodsMustAnalyze");
            return new Env(provider, task, sootCtx, collection, collection2);
        }

        public static /* synthetic */ Env copy$default(Env env, IEntryPointProvider iEntryPointProvider, IEntryPointProvider.AnalyzeTask analyzeTask, SootCtx sootCtx, Collection collection, Collection collection2, int i, Object obj) {
            if ((i & 1) != 0) {
                iEntryPointProvider = env.provider;
            }
            if ((i & 2) != 0) {
                analyzeTask = env.task;
            }
            if ((i & 4) != 0) {
                sootCtx = env.sootCtx;
            }
            if ((i & 8) != 0) {
                collection = env.entries;
            }
            if ((i & 16) != 0) {
                collection2 = env.methodsMustAnalyze;
            }
            return env.copy(iEntryPointProvider, analyzeTask, sootCtx, collection, collection2);
        }

        @NotNull
        public String toString() {
            return "Env(provider=" + this.provider + ", task=" + this.task + ", sootCtx=" + this.sootCtx + ", entries=" + this.entries + ", methodsMustAnalyze=" + this.methodsMustAnalyze + ")";
        }

        public int hashCode() {
            int result = this.provider.hashCode();
            return (((((((result * 31) + this.task.hashCode()) * 31) + this.sootCtx.hashCode()) * 31) + this.entries.hashCode()) * 31) + this.methodsMustAnalyze.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Env)) {
                return false;
            }
            Env env = (Env) other;
            return Intrinsics.areEqual(this.provider, env.provider) && Intrinsics.areEqual(this.task, env.task) && Intrinsics.areEqual(this.sootCtx, env.sootCtx) && Intrinsics.areEqual(this.entries, env.entries) && Intrinsics.areEqual(this.methodsMustAnalyze, env.methodsMustAnalyze);
        }

        public Env(@NotNull IEntryPointProvider provider, @NotNull IEntryPointProvider.AnalyzeTask task, @NotNull SootCtx sootCtx, @NotNull Collection<? extends SootMethod> collection, @NotNull Collection<? extends SootMethod> collection2) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
            Intrinsics.checkNotNullParameter(collection, "entries");
            Intrinsics.checkNotNullParameter(collection2, "methodsMustAnalyze");
            this.provider = provider;
            this.task = task;
            this.sootCtx = sootCtx;
            this.entries = collection;
            this.methodsMustAnalyze = collection2;
        }

        @NotNull
        public final IEntryPointProvider getProvider() {
            return this.provider;
        }

        @NotNull
        public final IEntryPointProvider.AnalyzeTask getTask() {
            return this.task;
        }

        @NotNull
        public final SootCtx getSootCtx() {
            return this.sootCtx;
        }

        @NotNull
        public final Collection<SootMethod> getEntries() {
            return this.entries;
        }

        @NotNull
        public final Collection<SootMethod> getMethodsMustAnalyze() {
            return this.methodsMustAnalyze;
        }
    }

    @NotNull
    public final Map<IEntryPointProvider, List<Analysis>> getAnalysisPasses() {
        return this.analysisPasses;
    }

    /* compiled from: AnalyzeTaskRunner.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012 \b\u0002\u0010\u0004\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005\u0012&\b\u0002\u0010\b\u001a \b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t\u0012 \b\u0002\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J&\u0010\u0018\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J,\u0010\u0019\u001a \b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J&\u0010\u001a\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0084\u0001\u0010\u001b\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032 \b\u0002\u0010\u0004\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00052&\b\u0002\u0010\b\u001a \b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t2 \b\u0002\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR+\u0010\u0004\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R1\u0010\b\u001a \b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R+\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011¨\u0006#"}, d2 = {"Lcn/sast/framework/AnalyzeTaskRunner$Analysis;", "", "phaseName", "", "before", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "analysis", "Lkotlin/Function2;", "Lcn/sast/framework/AnalyzeTaskRunner$Env;", "after", "<init>", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "getPhaseName", "()Ljava/lang/String;", "getBefore", "()Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function1;", "getAnalysis", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "getAfter", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)Lcn/sast/framework/AnalyzeTaskRunner$Analysis;", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: AnalyzeTaskRunner$Analysis.class */
    public static final class Analysis {

        @NotNull
        private final String phaseName;

        @Nullable
        private final Function1<Continuation<? super Unit>, Object> before;

        @Nullable
        private final Function2<Env, Continuation<? super Unit>, Object> analysis;

        @Nullable
        private final Function1<Continuation<? super Unit>, Object> after;

        @NotNull
        public final String component1() {
            return this.phaseName;
        }

        @Nullable
        public final Function1<Continuation<? super Unit>, Object> component2() {
            return this.before;
        }

        @Nullable
        public final Function2<Env, Continuation<? super Unit>, Object> component3() {
            return this.analysis;
        }

        @Nullable
        public final Function1<Continuation<? super Unit>, Object> component4() {
            return this.after;
        }

        @NotNull
        public final Analysis copy(@NotNull String phaseName, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function1, @Nullable Function2<? super Env, ? super Continuation<? super Unit>, ? extends Object> function2, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function12) {
            Intrinsics.checkNotNullParameter(phaseName, "phaseName");
            return new Analysis(phaseName, function1, function2, function12);
        }

        public static /* synthetic */ Analysis copy$default(Analysis analysis, String str, Function1 function1, Function2 function2, Function1 function12, int i, Object obj) {
            if ((i & 1) != 0) {
                str = analysis.phaseName;
            }
            if ((i & 2) != 0) {
                function1 = analysis.before;
            }
            if ((i & 4) != 0) {
                function2 = analysis.analysis;
            }
            if ((i & 8) != 0) {
                function12 = analysis.after;
            }
            return analysis.copy(str, function1, function2, function12);
        }

        @NotNull
        public String toString() {
            return "Analysis(phaseName=" + this.phaseName + ", before=" + this.before + ", analysis=" + this.analysis + ", after=" + this.after + ")";
        }

        public int hashCode() {
            int result = this.phaseName.hashCode();
            return (((((result * 31) + (this.before == null ? 0 : this.before.hashCode())) * 31) + (this.analysis == null ? 0 : this.analysis.hashCode())) * 31) + (this.after == null ? 0 : this.after.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Analysis)) {
                return false;
            }
            Analysis analysis = (Analysis) other;
            return Intrinsics.areEqual(this.phaseName, analysis.phaseName) && Intrinsics.areEqual(this.before, analysis.before) && Intrinsics.areEqual(this.analysis, analysis.analysis) && Intrinsics.areEqual(this.after, analysis.after);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Analysis(@NotNull String phaseName, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function1, @Nullable Function2<? super Env, ? super Continuation<? super Unit>, ? extends Object> function2, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function12) {
            Intrinsics.checkNotNullParameter(phaseName, "phaseName");
            this.phaseName = phaseName;
            this.before = function1;
            this.analysis = function2;
            this.after = function12;
        }

        public /* synthetic */ Analysis(String str, Function1 function1, Function2 function2, Function1 function12, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : function1, (i & 4) != 0 ? null : function2, (i & 8) != 0 ? null : function12);
        }

        @NotNull
        public final String getPhaseName() {
            return this.phaseName;
        }

        @Nullable
        public final Function1<Continuation<? super Unit>, Object> getBefore() {
            return this.before;
        }

        @Nullable
        public final Function2<Env, Continuation<? super Unit>, Object> getAnalysis() {
            return this.analysis;
        }

        @Nullable
        public final Function1<Continuation<? super Unit>, Object> getAfter() {
            return this.after;
        }
    }

    public static /* synthetic */ void registerAnalysis$default(AnalyzeTaskRunner analyzeTaskRunner, String str, IEntryPointProvider iEntryPointProvider, Function1 function1, Function2 function2, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        if ((i & 8) != 0) {
            function2 = null;
        }
        if ((i & 16) != 0) {
            function12 = null;
        }
        analyzeTaskRunner.registerAnalysis(str, iEntryPointProvider, function1, function2, function12);
    }

    public final void registerAnalysis(@NotNull String phaseName, @NotNull IEntryPointProvider provider, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function1, @Nullable Function2<? super Env, ? super Continuation<? super Unit>, ? extends Object> function2, @Nullable Function1<? super Continuation<? super Unit>, ? extends Object> function12) {
        Object obj;
        Intrinsics.checkNotNullParameter(phaseName, "phaseName");
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (!AnalyzerEnv.INSTANCE.getShouldV3r14y() || AnalyzerEnv.INSTANCE.getBvs1n3ss().get() == 0 || (Companion.getMask() & mask1) == 65576) {
            Map $this$getOrPut$iv = this.analysisPasses;
            Object value$iv = $this$getOrPut$iv.get(provider);
            if (value$iv == null) {
                ArrayList arrayList = new ArrayList();
                $this$getOrPut$iv.put(provider, arrayList);
                obj = arrayList;
            } else {
                obj = value$iv;
            }
            ((List) obj).add(new Analysis(phaseName, function1, function2, function12));
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0405 A[LOOP:2: B:50:0x03fb->B:52:0x0405, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object run(@org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 1378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.AnalyzeTaskRunner.run(kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object run$lambda$3(IEntryPointProvider $provider) {
        return "do analysis with provider: " + $provider;
    }

    /* compiled from: AnalyzeTaskRunner.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "AnalyzeTaskRunner.kt", l = {120}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.AnalyzeTaskRunner$run$9")
    /* renamed from: cn.sast.framework.AnalyzeTaskRunner$run$9, reason: invalid class name */
    /* loaded from: AnalyzeTaskRunner$run$9.class */
    static final class AnonymousClass9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;

        AnonymousClass9(Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass9 = AnalyzeTaskRunner.this.new AnonymousClass9(continuation);
            anonymousClass9.L$0 = value;
            return anonymousClass9;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (AnalyzeTaskRunner.this.run($this$coroutineScope, (Continuation) this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Nullable
    public final Object run(@NotNull Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass9(null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* compiled from: AnalyzeTaskRunner.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0086.¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR!\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\rX\u0086T¢\u0006\u0002\n��¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/AnalyzeTaskRunner$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "v3r14yJn1Class", "Ljava/lang/Class;", "getV3r14yJn1Class", "()Ljava/lang/Class;", "setV3r14yJn1Class", "(Ljava/lang/Class;)V", "mask", "", "getMask$annotations", "getMask", "()I", "mask$delegate", "Lkotlin/Lazy;", "mask1", "corax-framework"})
    /* loaded from: AnalyzeTaskRunner$Companion.class */
    public static final class Companion {
        public static /* synthetic */ void getMask$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Class<?> getV3r14yJn1Class() {
            Class<?> cls = AnalyzeTaskRunner.v3r14yJn1Class;
            if (cls != null) {
                return cls;
            }
            Intrinsics.throwUninitializedPropertyAccessException("v3r14yJn1Class");
            return null;
        }

        public final void setV3r14yJn1Class(@NotNull Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "<set-?>");
            AnalyzeTaskRunner.v3r14yJn1Class = cls;
        }

        public final int getMask() {
            return ((Number) AnalyzeTaskRunner.mask$delegate.getValue()).intValue();
        }
    }

    private static final Unit logger$lambda$6() {
        return Unit.INSTANCE;
    }

    private static final int mask_delegate$lambda$7() {
        return ((Number) BuildersKt.runBlocking$default((CoroutineContext) null, new AnalyzeTaskRunner$Companion$mask$2$1(null), 1, (Object) null)).intValue();
    }
}
