package cn.sast.framework.report.coverage;

import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CoverageCompnment.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/report/coverage/TaintCoverage;", "Lcn/sast/framework/report/coverage/Coverage;", "<init>", "()V", "copyResource", "", "name", "", "to", "Lcn/sast/common/IResFile;", "changeColor", "reportOutputRoot", "Lcn/sast/common/IResDirectory;", "flushCoverage", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "outputDir", "encoding", "Ljava/nio/charset/Charset;", "(Lcn/sast/framework/report/IProjectFileLocator;Lcn/sast/common/IResDirectory;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-framework"})
@SourceDebugExtension({"SMAP\nCoverageCompnment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoverageCompnment.kt\ncn/sast/framework/report/coverage/TaintCoverage\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n1#2:73\n*E\n"})
/* loaded from: TaintCoverage.class */
public final class TaintCoverage extends Coverage {

    /* compiled from: CoverageCompnment.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "CoverageCompnment.kt", l = {30}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"this", "outputDir"}, m = "flushCoverage", c = "cn.sast.framework.report.coverage.TaintCoverage")
    /* renamed from: cn.sast.framework.report.coverage.TaintCoverage$flushCoverage$1, reason: invalid class name */
    /* loaded from: TaintCoverage$flushCoverage$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return TaintCoverage.this.flushCoverage(null, null, null, (Continuation) this);
        }
    }

    private final void copyResource(String name, IResFile to) throws IOException {
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(to.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream it = outputStream;
                InputStream resourceAsStream = TaintCoverage.class.getResourceAsStream(name);
                Intrinsics.checkNotNull(resourceAsStream);
                ByteStreamsKt.copyTo$default(resourceAsStream, it, 0, 2, (Object) null);
                CloseableKt.closeFinally(outputStream, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStream, th);
            throw th2;
        }
    }

    public final void changeColor(@NotNull IResDirectory reportOutputRoot) throws IOException {
        Intrinsics.checkNotNullParameter(reportOutputRoot, "reportOutputRoot");
        copyResource("/jacoco/taint-report.css", reportOutputRoot.resolve("jacoco-resources").resolve("report.css").toFile());
        copyResource("/jacoco/greenbar.gif", reportOutputRoot.resolve("jacoco-resources").resolve("redbar.gif").toFile());
        copyResource("/jacoco/redbar.gif", reportOutputRoot.resolve("jacoco-resources").resolve("greenbar.gif").toFile());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @Override // cn.sast.framework.report.coverage.Coverage
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object flushCoverage(@org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r9, @org.jetbrains.annotations.NotNull cn.sast.common.IResDirectory r10, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = r12
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.TaintCoverage.AnonymousClass1
            if (r0 == 0) goto L29
            r0 = r12
            cn.sast.framework.report.coverage.TaintCoverage$flushCoverage$1 r0 = (cn.sast.framework.report.coverage.TaintCoverage.AnonymousClass1) r0
            r16 = r0
            r0 = r16
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r16
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L35
        L29:
            cn.sast.framework.report.coverage.TaintCoverage$flushCoverage$1 r0 = new cn.sast.framework.report.coverage.TaintCoverage$flushCoverage$1
            r1 = r0
            r2 = r8
            r3 = r12
            r1.<init>(r3)
            r16 = r0
        L35:
            r0 = r16
            java.lang.Object r0 = r0.result
            r15 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r17 = r0
            r0 = r16
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L85;
                default: goto Lb0;
            }
        L5c:
            r0 = r15
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r16
            r5 = r16
            r6 = r8
            r5.L$0 = r6
            r5 = r16
            r6 = r10
            r5.L$1 = r6
            r5 = r16
            r6 = 1
            r5.label = r6
            java.lang.Object r0 = super.flushCoverage(r1, r2, r3, r4)
            r1 = r0
            r2 = r17
            if (r1 != r2) goto L9e
            r1 = r17
            return r1
        L85:
            r0 = r16
            java.lang.Object r0 = r0.L$1
            cn.sast.common.IResDirectory r0 = (cn.sast.common.IResDirectory) r0
            r10 = r0
            r0 = r16
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.report.coverage.TaintCoverage r0 = (cn.sast.framework.report.coverage.TaintCoverage) r0
            r8 = r0
            r0 = r15
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r15
        L9e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r13 = r0
            r0 = 0
            r14 = r0
            r0 = r8
            r1 = r10
            r0.changeColor(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lb0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.TaintCoverage.flushCoverage(cn.sast.framework.report.IProjectFileLocator, cn.sast.common.IResDirectory, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
