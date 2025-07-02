package cn.sast.framework.report.coverage;

import cn.sast.api.report.CoverData;
import cn.sast.api.report.CoverInst;
import cn.sast.api.report.CoverTaint;
import cn.sast.api.report.ICoverageCollector;
import cn.sast.common.IResDirectory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IProjectFileLocator;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CoverageCompnment.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018��2\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u001dJ\"\u0010\u0011\u001a\u00020\u000e*\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u001fJ.\u0010 \u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006!"}, d2 = {"Lcn/sast/framework/report/coverage/JacocoCompoundCoverage;", "Lcn/sast/api/report/ICoverageCollector;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "taintCoverage", "Lcn/sast/framework/report/coverage/Coverage;", "executionCoverage", "enableCoveredTaint", "", "<init>", "(Lcn/sast/framework/report/IProjectFileLocator;Lcn/sast/framework/report/coverage/Coverage;Lcn/sast/framework/report/coverage/Coverage;Z)V", "getEnableCoveredTaint", "()Z", "cover", "", "coverInfo", "Lcn/sast/api/report/CoverData;", "flush", "output", "Lcn/sast/common/IResDirectory;", "sourceEncoding", "Ljava/nio/charset/Charset;", "(Lcn/sast/common/IResDirectory;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoveredLineCounter", "Lorg/jacoco/core/analysis/ICounter;", "allSourceFiles", "", "Lcn/sast/common/IResFile;", "encoding", "(Ljava/util/Set;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "out", "(Lcn/sast/framework/report/coverage/Coverage;Lcn/sast/common/IResDirectory;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copy", "corax-framework"})
/* loaded from: JacocoCompoundCoverage.class */
public final class JacocoCompoundCoverage implements ICoverageCollector {

    @NotNull
    private final IProjectFileLocator locator;

    @NotNull
    private final Coverage taintCoverage;

    @NotNull
    private final Coverage executionCoverage;
    private final boolean enableCoveredTaint;

    /* compiled from: CoverageCompnment.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "CoverageCompnment.kt", l = {62}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"allSourceFiles"}, m = "getCoveredLineCounter", c = "cn.sast.framework.report.coverage.JacocoCompoundCoverage")
    /* renamed from: cn.sast.framework.report.coverage.JacocoCompoundCoverage$getCoveredLineCounter$1, reason: invalid class name */
    /* loaded from: JacocoCompoundCoverage$getCoveredLineCounter$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return JacocoCompoundCoverage.this.getCoveredLineCounter(null, null, (Continuation) this);
        }
    }

    public JacocoCompoundCoverage(@NotNull IProjectFileLocator locator, @NotNull Coverage taintCoverage, @NotNull Coverage executionCoverage, boolean enableCoveredTaint) {
        Intrinsics.checkNotNullParameter(locator, "locator");
        Intrinsics.checkNotNullParameter(taintCoverage, "taintCoverage");
        Intrinsics.checkNotNullParameter(executionCoverage, "executionCoverage");
        this.locator = locator;
        this.taintCoverage = taintCoverage;
        this.executionCoverage = executionCoverage;
        this.enableCoveredTaint = enableCoveredTaint;
    }

    public /* synthetic */ JacocoCompoundCoverage(IProjectFileLocator iProjectFileLocator, Coverage coverage, Coverage coverage2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iProjectFileLocator, (i & 2) != 0 ? new TaintCoverage() : coverage, (i & 4) != 0 ? new Coverage() : coverage2, (i & 8) != 0 ? false : z);
    }

    @Override // cn.sast.api.report.ICoverageCollector
    public boolean getEnableCoveredTaint() {
        return this.enableCoveredTaint;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // cn.sast.api.report.ICoverageCollector
    public void cover(@NotNull CoverData coverInfo) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(coverInfo, "coverInfo");
        if (coverInfo instanceof CoverTaint) {
            this.taintCoverage.coverByQueue(((CoverTaint) coverInfo).getClassName(), ((CoverTaint) coverInfo).getLineNumber());
        } else {
            if (!(coverInfo instanceof CoverInst)) {
                throw new NoWhenBranchMatchedException();
            }
            this.executionCoverage.coverByQueue(((CoverInst) coverInfo).getClassName(), ((CoverInst) coverInfo).getLineNumber());
        }
    }

    /* compiled from: CoverageCompnment.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "CoverageCompnment.kt", l = {56, 57}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"execTask"}, m = "invokeSuspend", c = "cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2")
    /* renamed from: cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2, reason: invalid class name */
    /* loaded from: JacocoCompoundCoverage$flush$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ IResDirectory $output;
        final /* synthetic */ Charset $sourceEncoding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(IResDirectory $output, Charset $sourceEncoding, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$output = $output;
            this.$sourceEncoding = $sourceEncoding;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = JacocoCompoundCoverage.this.new AnonymousClass2(this.$output, this.$sourceEncoding, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x00b9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r15 = r0
                r0 = r10
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L24;
                    case 1: goto L8f;
                    case 2: goto Lbc;
                    default: goto Lc6;
                }
            L24:
                r0 = r11
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r10
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                r12 = r0
                r0 = r12
                r1 = 0
                r2 = 0
                cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2$taintTask$1 r3 = new cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2$taintTask$1
                r4 = r3
                r5 = r10
                cn.sast.framework.report.coverage.JacocoCompoundCoverage r5 = cn.sast.framework.report.coverage.JacocoCompoundCoverage.this
                r6 = r10
                cn.sast.common.IResDirectory r6 = r6.$output
                r7 = r10
                java.nio.charset.Charset r7 = r7.$sourceEncoding
                r8 = 0
                r4.<init>(r5, r6, r7, r8)
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                r4 = 3
                r5 = 0
                kotlinx.coroutines.Deferred r0 = kotlinx.coroutines.BuildersKt.async$default(r0, r1, r2, r3, r4, r5)
                r13 = r0
                r0 = r12
                r1 = 0
                r2 = 0
                cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2$execTask$1 r3 = new cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2$execTask$1
                r4 = r3
                r5 = r10
                cn.sast.framework.report.coverage.JacocoCompoundCoverage r5 = cn.sast.framework.report.coverage.JacocoCompoundCoverage.this
                r6 = r10
                cn.sast.common.IResDirectory r6 = r6.$output
                r7 = r10
                java.nio.charset.Charset r7 = r7.$sourceEncoding
                r8 = 0
                r4.<init>(r5, r6, r7, r8)
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                r4 = 3
                r5 = 0
                kotlinx.coroutines.Deferred r0 = kotlinx.coroutines.BuildersKt.async$default(r0, r1, r2, r3, r4, r5)
                r14 = r0
                r0 = r13
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r10
                r3 = r14
                r2.L$0 = r3
                r2 = r10
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = r0.await(r1)
                r1 = r0
                r2 = r15
                if (r1 != r2) goto L9d
                r1 = r15
                return r1
            L8f:
                r0 = r10
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.Deferred r0 = (kotlinx.coroutines.Deferred) r0
                r14 = r0
                r0 = r11
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r11
            L9d:
                r0 = r14
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r10
                r3 = 0
                r2.L$0 = r3
                r2 = r10
                r3 = 2
                r2.label = r3
                java.lang.Object r0 = r0.await(r1)
                r1 = r0
                r2 = r15
                if (r1 != r2) goto Lc1
                r1 = r15
                return r1
            Lbc:
                r0 = r11
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r11
            Lc1:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Lc6:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.JacocoCompoundCoverage.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // cn.sast.api.report.ICoverageCollector
    @Nullable
    public Object flush(@NotNull IResDirectory output, @NotNull Charset sourceEncoding, @NotNull Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(output, sourceEncoding, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @Override // cn.sast.api.report.ICoverageCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getCoveredLineCounter(@org.jetbrains.annotations.NotNull java.util.Set<? extends cn.sast.common.IResFile> r8, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super org.jacoco.core.analysis.ICounter> r10) {
        /*
            r7 = this;
            r0 = r10
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.JacocoCompoundCoverage.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r10
            cn.sast.framework.report.coverage.JacocoCompoundCoverage$getCoveredLineCounter$1 r0 = (cn.sast.framework.report.coverage.JacocoCompoundCoverage.AnonymousClass1) r0
            r12 = r0
            r0 = r12
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r12
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.framework.report.coverage.JacocoCompoundCoverage$getCoveredLineCounter$1 r0 = new cn.sast.framework.report.coverage.JacocoCompoundCoverage$getCoveredLineCounter$1
            r1 = r0
            r2 = r7
            r3 = r10
            r1.<init>(r3)
            r12 = r0
        L32:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L80;
                default: goto L98;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            cn.sast.framework.report.coverage.Coverage r0 = r0.executionCoverage
            r1 = r7
            cn.sast.framework.report.IProjectFileLocator r1 = r1.locator
            r2 = r9
            r3 = r12
            r4 = r12
            r5 = r8
            r4.L$0 = r5
            r4 = r12
            r5 = 1
            r4.label = r5
            java.lang.Object r0 = r0.calculateSourceCoverage(r1, r2, r3)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L90
            r1 = r13
            return r1
        L80:
            r0 = r12
            java.lang.Object r0 = r0.L$0
            java.util.Set r0 = (java.util.Set) r0
            r8 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        L90:
            cn.sast.framework.report.coverage.SourceCoverage r0 = (cn.sast.framework.report.coverage.SourceCoverage) r0
            r1 = r8
            org.jacoco.core.internal.analysis.CounterImpl r0 = r0.calculateCoveredRatio(r1)
            return r0
        L98:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.JacocoCompoundCoverage.getCoveredLineCounter(java.util.Set, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object flush(@NotNull Coverage $this$flush, @NotNull IResDirectory out, @NotNull Charset sourceEncoding, @NotNull Continuation<? super Unit> continuation) {
        Object objFlushCoverage = $this$flush.flushCoverage(this.locator, out, sourceEncoding, continuation);
        return objFlushCoverage == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushCoverage : Unit.INSTANCE;
    }

    public static /* synthetic */ JacocoCompoundCoverage copy$default(JacocoCompoundCoverage jacocoCompoundCoverage, IProjectFileLocator iProjectFileLocator, Coverage coverage, Coverage coverage2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            iProjectFileLocator = jacocoCompoundCoverage.locator;
        }
        if ((i & 2) != 0) {
            coverage = jacocoCompoundCoverage.taintCoverage;
        }
        if ((i & 4) != 0) {
            coverage2 = jacocoCompoundCoverage.executionCoverage;
        }
        if ((i & 8) != 0) {
            z = jacocoCompoundCoverage.getEnableCoveredTaint();
        }
        return jacocoCompoundCoverage.copy(iProjectFileLocator, coverage, coverage2, z);
    }

    @NotNull
    public final JacocoCompoundCoverage copy(@NotNull IProjectFileLocator locator, @NotNull Coverage taintCoverage, @NotNull Coverage executionCoverage, boolean enableCoveredTaint) {
        Intrinsics.checkNotNullParameter(locator, "locator");
        Intrinsics.checkNotNullParameter(taintCoverage, "taintCoverage");
        Intrinsics.checkNotNullParameter(executionCoverage, "executionCoverage");
        return new JacocoCompoundCoverage(locator, taintCoverage, executionCoverage, enableCoveredTaint);
    }
}
