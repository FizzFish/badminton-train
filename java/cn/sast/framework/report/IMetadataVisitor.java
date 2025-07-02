package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.metadata.AnalysisMetadata;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReportConsumer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/IMetadataVisitor;", "", "visit", "", "analysisMetadata", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "corax-framework"})
/* loaded from: IMetadataVisitor.class */
public interface IMetadataVisitor {
    void visit(@NotNull AnalysisMetadata analysisMetadata);
}
