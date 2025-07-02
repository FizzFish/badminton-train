package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ReportConverter;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "ReportConverter.kt", l = {264, 281}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0"}, n = {"$mainConfig", "$outputDir", "$locator", "classFoundSourceFiles", "classNotFoundSourceFile", "$mainConfig"}, m = "invokeSuspend$getAnalysisMetadata", c = "cn.sast.framework.report.ReportConverter$flush$2")
/* loaded from: ReportConverter$flush$2$getAnalysisMetadata$1.class */
final class ReportConverter$flush$2$getAnalysisMetadata$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    /* synthetic */ Object result;
    int label;

    ReportConverter$flush$2$getAnalysisMetadata$1(Continuation<? super ReportConverter$flush$2$getAnalysisMetadata$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return ReportConverter.AnonymousClass2.invokeSuspend$getAnalysisMetadata(null, null, null, null, null, null, null, (Continuation) this);
    }
}
