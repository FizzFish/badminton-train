package cn.sast.framework.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.ICheckPoint;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SafeAnalyzeUtil.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006JU\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b��\u0010\t*\u00020\n\"\u0004\b\u0001\u0010\b2\u0006\u0010\f\u001a\u0002H\t2'\u0010\r\u001a#\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e¢\u0006\u0002\b\u0010H\u0086@R\u00020\u000b¢\u0006\u0002\u0010\u0011J8\u0010\u0012\u001a\u0004\u0018\u0001H\b\"\u0004\b��\u0010\b2\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014H\u0086@R\u00020\u0013¢\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lcn/sast/framework/util/SafeAnalyzeUtil;", "", "errorLimitCount", "", "errorCount", "<init>", "(II)V", "safeAnalyze", "T", "C", "Lcom/feysh/corax/config/api/ICheckPoint;", "Lkotlinx/coroutines/CoroutineScope;", "point", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lcom/feysh/corax/config/api/ICheckPoint;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "safeRunInSceneAsync", "Lcom/feysh/corax/config/api/CheckerUnit;", "Lkotlin/Function1;", "(Lcom/feysh/corax/config/api/CheckerUnit;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCheckerError", "", "t", "", "Lkotlin/Function0;", "corax-framework"})
@SourceDebugExtension({"SMAP\nSafeAnalyzeUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeAnalyzeUtil.kt\ncn/sast/framework/util/SafeAnalyzeUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,57:1\n1#2:58\n*E\n"})
/* loaded from: SafeAnalyzeUtil.class */
public final class SafeAnalyzeUtil {
    private final int errorLimitCount;
    private int errorCount;

    /* compiled from: SafeAnalyzeUtil.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SafeAnalyzeUtil.kt", l = {19}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2"}, n = {"this", "$context_receiver_0", "point"}, m = "safeAnalyze", c = "cn.sast.framework.util.SafeAnalyzeUtil")
    /* renamed from: cn.sast.framework.util.SafeAnalyzeUtil$safeAnalyze$1, reason: invalid class name */
    /* loaded from: SafeAnalyzeUtil$safeAnalyze$1.class */
    static final class AnonymousClass1<C extends ICheckPoint, T> extends ContinuationImpl {
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
            return SafeAnalyzeUtil.this.safeAnalyze(null, null, null, (Continuation) this);
        }
    }

    /* compiled from: SafeAnalyzeUtil.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SafeAnalyzeUtil.kt", l = {34}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"this", "$context_receiver_0"}, m = "safeRunInSceneAsync", c = "cn.sast.framework.util.SafeAnalyzeUtil")
    /* renamed from: cn.sast.framework.util.SafeAnalyzeUtil$safeRunInSceneAsync$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: SafeAnalyzeUtil$safeRunInSceneAsync$1.class */
    static final class C00451<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C00451(Continuation<? super C00451> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SafeAnalyzeUtil.this.safeRunInSceneAsync(null, null, (Continuation) this);
        }
    }

    public SafeAnalyzeUtil(int errorLimitCount, int errorCount) {
        this.errorLimitCount = errorLimitCount;
        this.errorCount = errorCount;
    }

    public /* synthetic */ SafeAnalyzeUtil(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.feysh.corax.config.api.ICheckPoint] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <C extends com.feysh.corax.config.api.ICheckPoint, T> java.lang.Object safeAnalyze(@org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r8, @org.jetbrains.annotations.NotNull C r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super C, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.util.SafeAnalyzeUtil.safeAnalyze(kotlinx.coroutines.CoroutineScope, com.feysh.corax.config.api.ICheckPoint, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object safeAnalyze$lambda$2$lambda$1(ICheckPoint $point) {
        return "When analyzing this location: " + $point + ", please file this bug to us";
    }

    private static final Unit safeAnalyze$lambda$2(Throwable $t, SafeAnalyzeUtil this$0, CoroutineScope $$context_receiver_0, ICheckPoint $point) {
        PreAnalysisImpl.Companion.getKLogger().error($t, () -> {
            return safeAnalyze$lambda$2$lambda$1(r2);
        });
        this$0.errorCount++;
        if (this$0.errorCount > this$0.errorLimitCount) {
            CoroutineScopeKt.cancel$default($$context_receiver_0, (CancellationException) null, 1, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <T> java.lang.Object safeRunInSceneAsync(@org.jetbrains.annotations.NotNull com.feysh.corax.config.api.CheckerUnit r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = r8
            boolean r0 = r0 instanceof cn.sast.framework.util.SafeAnalyzeUtil.C00451
            if (r0 == 0) goto L27
            r0 = r8
            cn.sast.framework.util.SafeAnalyzeUtil$safeRunInSceneAsync$1 r0 = (cn.sast.framework.util.SafeAnalyzeUtil.C00451) r0
            r11 = r0
            r0 = r11
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r11
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.framework.util.SafeAnalyzeUtil$safeRunInSceneAsync$1 r0 = new cn.sast.framework.util.SafeAnalyzeUtil$safeRunInSceneAsync$1
            r1 = r0
            r2 = r5
            r3 = r8
            r1.<init>(r3)
            r11 = r0
        L32:
            r0 = r11
            java.lang.Object r0 = r0.result
            r10 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r12 = r0
            r0 = r11
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L81;
                default: goto Lb1;
            }
        L58:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            r1 = r11
            r2 = r11
            r3 = r5
            r2.L$0 = r3     // Catch: java.lang.Exception -> L9c
            r2 = r11
            r3 = r6
            r2.L$1 = r3     // Catch: java.lang.Exception -> L9c
            r2 = r11
            r3 = 1
            r2.label = r3     // Catch: java.lang.Exception -> L9c
            java.lang.Object r0 = r0.invoke(r1)     // Catch: java.lang.Exception -> L9c
            r1 = r0
            r2 = r12
            if (r1 != r2) goto L9b
            r1 = r12
            return r1
        L81:
            r0 = r11
            java.lang.Object r0 = r0.L$1
            com.feysh.corax.config.api.CheckerUnit r0 = (com.feysh.corax.config.api.CheckerUnit) r0
            r6 = r0
            r0 = r11
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.util.SafeAnalyzeUtil r0 = (cn.sast.framework.util.SafeAnalyzeUtil) r0
            r5 = r0
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L9c
            r0 = r10
        L9b:
            return r0
        L9c:
            r9 = move-exception
            r0 = r5
            r1 = r9
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r9
            r3 = r6
            java.lang.Object r2 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return safeRunInSceneAsync$lambda$4(r2, r3);
            }
            r0.onCheckerError(r1, r2)
            r0 = 0
            return r0
        Lb1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.util.SafeAnalyzeUtil.safeRunInSceneAsync(com.feysh.corax.config.api.CheckerUnit, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object safeRunInSceneAsync$lambda$4$lambda$3(CheckerUnit $$context_receiver_0) {
        return "Occur a exception while call " + $$context_receiver_0 + ":runInSceneAsync, please file this bug to us";
    }

    private static final Unit safeRunInSceneAsync$lambda$4(Exception $t, CheckerUnit $$context_receiver_0) {
        PreAnalysisImpl.Companion.getKLogger().error($t, () -> {
            return safeRunInSceneAsync$lambda$4$lambda$3(r2);
        });
        return Unit.INSTANCE;
    }

    private final void onCheckerError(Throwable t, Function0<Unit> function0) throws Throwable {
        if (t instanceof CancellationException) {
            throw t;
        }
        if ((t instanceof Exception) || (t instanceof NotImplementedError)) {
            function0.invoke();
            return;
        }
        throw t;
    }
}
