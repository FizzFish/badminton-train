package cn.sast.framework.report;

import cn.sast.api.config.MainConfig;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ReportConverter;
import cn.sast.framework.report.coverage.JacocoCompoundCoverage;
import cn.sast.framework.report.metadata.AnalysisMetadata;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "ReportConverter.kt", l = {294}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.ReportConverter$flush$2$5$analysisMetadata$1$1")
/* loaded from: ReportConverter$flush$2$5$analysisMetadata$1$1.class */
final class ReportConverter$flush$2$5$analysisMetadata$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AnalysisMetadata>, Object> {
    int label;
    final /* synthetic */ JacocoCompoundCoverage $coverage;
    final /* synthetic */ Set<IResFile> $allSourceFiles;
    final /* synthetic */ MainConfig $mainConfig;
    final /* synthetic */ IResDirectory $outputDir;
    final /* synthetic */ IProjectFileLocator $locator;
    final /* synthetic */ Set<IResFile> $classFoundSourceFiles;
    final /* synthetic */ Set<IResFile> $classNotFoundSourceFile;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ReportConverter$flush$2$5$analysisMetadata$1$1(JacocoCompoundCoverage $coverage, Set<? extends IResFile> set, MainConfig $mainConfig, IResDirectory $outputDir, IProjectFileLocator $locator, Set<IResFile> set2, Set<? extends IResFile> set3, Continuation<? super ReportConverter$flush$2$5$analysisMetadata$1$1> continuation) {
        super(2, continuation);
        this.$coverage = $coverage;
        this.$allSourceFiles = set;
        this.$mainConfig = $mainConfig;
        this.$outputDir = $outputDir;
        this.$locator = $locator;
        this.$classFoundSourceFiles = set2;
        this.$classNotFoundSourceFile = set3;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new ReportConverter$flush$2$5$analysisMetadata$1$1(this.$coverage, this.$allSourceFiles, this.$mainConfig, this.$outputDir, this.$locator, this.$classFoundSourceFiles, this.$classNotFoundSourceFile, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super AnalysisMetadata> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) throws InterruptedException {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object objInvokeSuspend$getAnalysisMetadata = ReportConverter.AnonymousClass2.invokeSuspend$getAnalysisMetadata(this.$coverage, this.$allSourceFiles, this.$mainConfig, this.$outputDir, this.$locator, this.$classFoundSourceFiles, this.$classNotFoundSourceFile, (Continuation) this);
                return objInvokeSuspend$getAnalysisMetadata == coroutine_suspended ? coroutine_suspended : objInvokeSuspend$getAnalysisMetadata;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
