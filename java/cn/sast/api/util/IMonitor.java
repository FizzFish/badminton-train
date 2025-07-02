package cn.sast.api.util;

import cn.sast.api.report.ProjectMetrics;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: IMonitor.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcn/sast/api/util/IMonitor;", "", "projectMetrics", "Lcn/sast/api/report/ProjectMetrics;", "getProjectMetrics", "()Lcn/sast/api/report/ProjectMetrics;", "timer", "Lcn/sast/api/util/Timer;", "phase", "", "corax-api"})
/* loaded from: IMonitor.class */
public interface IMonitor {
    @NotNull
    ProjectMetrics getProjectMetrics();

    @NotNull
    Timer timer(@NotNull String str);
}
