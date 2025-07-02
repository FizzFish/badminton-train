package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.ICheckPoint;
import com.feysh.corax.config.api.IInvokeCheckPoint;
import com.feysh.corax.config.api.IMethodCheckPoint;
import com.feysh.corax.config.api.INodeWithRange;
import com.feysh.corax.config.api.IPreAnalysisInvokeConfig;
import com.feysh.corax.config.api.IPreAnalysisMethodConfig;
import com.feysh.corax.config.api.ISourceFileCheckPoint;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.report.Region;
import com.github.javaparser.Position;
import com.github.javaparser.ast.nodeTypes.NodeWithRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KCallable;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.tagkit.Host;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001J\b\u0010\f\u001a\u00020\rH&J\u000e\u0010\u000e\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0007X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcn/sast/api/config/PreAnalysisCoroutineScope;", "Lcom/feysh/corax/config/api/PreAnalysisApi;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "setScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "uninitializedScope", "", "processPreAnalysisUnits", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: PreAnalysisCoroutineScope.class */
public interface PreAnalysisCoroutineScope extends PreAnalysisApi {

    /* compiled from: PreAnalysisCoroutineScope.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {30, 30}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$0", "L$1", "L$2"}, n = {"$this", "$this", "$this$bracket$iv", "s$iv"}, m = "processPreAnalysisUnits", c = "cn.sast.api.config.PreAnalysisCoroutineScope$DefaultImpls")
    /* renamed from: cn.sast.api.config.PreAnalysisCoroutineScope$processPreAnalysisUnits$1, reason: invalid class name */
    /* loaded from: PreAnalysisCoroutineScope$processPreAnalysisUnits$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.processPreAnalysisUnits(null, (Continuation) this);
        }
    }

    @NotNull
    MainConfig getMainConfig();

    @NotNull
    CoroutineScope getScope();

    void setScope(@NotNull CoroutineScope coroutineScope);

    void uninitializedScope();

    @Nullable
    Object processPreAnalysisUnits(@NotNull Continuation<? super Unit> continuation);

    /* compiled from: PreAnalysisCoroutineScope.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$DefaultImpls\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,99:1\n16#2,8:100\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$DefaultImpls\n*L\n28#1:100,8\n*E\n"})
    /* loaded from: PreAnalysisCoroutineScope$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static <T> Job runInScene(@NotNull PreAnalysisCoroutineScope $this, @NotNull CheckerUnit $context_receiver_0, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
            Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
            Intrinsics.checkNotNullParameter(function1, "block");
            return PreAnalysisApi.DefaultImpls.runInScene($this, $context_receiver_0, function1);
        }

        @NotNull
        public static <T> PreAnalysisApi.Result<T> atMethod(@NotNull PreAnalysisCoroutineScope $this, @NotNull CheckerUnit $context_receiver_0, @NotNull KCallable<?> kCallable, @NotNull Function1<? super IPreAnalysisMethodConfig, Unit> function1, @NotNull Function2<? super IMethodCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
            Intrinsics.checkNotNullParameter(kCallable, "method");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(function2, "block");
            return PreAnalysisApi.DefaultImpls.atMethod($this, $context_receiver_0, kCallable, function1, function2);
        }

        @NotNull
        public static <T> PreAnalysisApi.Result<T> atInvoke(@NotNull PreAnalysisCoroutineScope $this, @NotNull CheckerUnit $context_receiver_0, @NotNull KCallable<?> kCallable, @NotNull Function1<? super IPreAnalysisInvokeConfig, Unit> function1, @NotNull Function2<? super IInvokeCheckPoint, ? super Continuation<? super T>, ? extends Object> function2) {
            Intrinsics.checkNotNullParameter($context_receiver_0, "$context_receiver_0");
            Intrinsics.checkNotNullParameter(kCallable, "callee");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(function2, "block");
            return PreAnalysisApi.DefaultImpls.atInvoke($this, $context_receiver_0, kCallable, function1, function2);
        }

        public static <P extends ICheckPoint & INodeWithRange> void report(@NotNull PreAnalysisCoroutineScope $this, @NotNull P p, @NotNull CheckType checkType, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(p, "$receiver");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(region, "region");
            Intrinsics.checkNotNullParameter(function1, "env");
            PreAnalysisApi.DefaultImpls.report($this, p, checkType, region, function1);
        }

        public static void report(@NotNull PreAnalysisCoroutineScope $this, @NotNull ISourceFileCheckPoint $receiver, @NotNull CheckType checkType, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter($receiver, "$receiver");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(region, "region");
            Intrinsics.checkNotNullParameter(function1, "env");
            PreAnalysisApi.DefaultImpls.report($this, $receiver, checkType, region, function1);
        }

        @Nullable
        public static Unit report(@NotNull PreAnalysisCoroutineScope $this, @NotNull ISourceFileCheckPoint $receiver, @NotNull CheckType checkType, @NotNull de.fraunhofer.aisec.cpg.sarif.Region cpgRegion, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter($receiver, "$receiver");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(cpgRegion, "cpgRegion");
            Intrinsics.checkNotNullParameter(function1, "env");
            return PreAnalysisApi.DefaultImpls.report($this, $receiver, checkType, cpgRegion, function1);
        }

        @Nullable
        public static Unit report(@NotNull PreAnalysisCoroutineScope $this, @NotNull ISourceFileCheckPoint $receiver, @NotNull CheckType checkType, @NotNull Position jpsStart, @Nullable Position jpsEnd, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter($receiver, "$receiver");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(jpsStart, "jpsStart");
            Intrinsics.checkNotNullParameter(function1, "env");
            return PreAnalysisApi.DefaultImpls.report($this, $receiver, checkType, jpsStart, jpsEnd, function1);
        }

        @Nullable
        public static Unit report(@NotNull PreAnalysisCoroutineScope $this, @NotNull ISourceFileCheckPoint $receiver, @NotNull CheckType checkType, @NotNull NodeWithRange<?> nodeWithRange, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter($receiver, "$receiver");
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(nodeWithRange, "regionNode");
            Intrinsics.checkNotNullParameter(function1, "env");
            return PreAnalysisApi.DefaultImpls.report($this, $receiver, checkType, nodeWithRange, function1);
        }

        public static void report(@NotNull PreAnalysisCoroutineScope $this, @NotNull CheckType checkType, @NotNull Host sootHost, @Nullable Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(sootHost, "sootHost");
            Intrinsics.checkNotNullParameter(function1, "env");
            PreAnalysisApi.DefaultImpls.report($this, checkType, sootHost, region, function1);
        }

        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static java.lang.Object processPreAnalysisUnits(@org.jetbrains.annotations.NotNull cn.sast.api.config.PreAnalysisCoroutineScope r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
            /*
                Method dump skipped, instructions count: 448
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.PreAnalysisCoroutineScope.DefaultImpls.processPreAnalysisUnits(cn.sast.api.config.PreAnalysisCoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
