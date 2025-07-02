package cn.sast.api.report;

import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH\u0016¨\u0006\r"}, d2 = {"Lcn/sast/api/report/IReportHashCalculator;", "", "from", "", "clazz", "Lsoot/SootClass;", "method", "Lsoot/SootMethod;", "fromAbsPath", "absolutePath", "Lcn/sast/common/IResource;", "fromPath", "path", "corax-api"})
/* loaded from: IReportHashCalculator.class */
public interface IReportHashCalculator {
    @NotNull
    String from(@NotNull SootClass sootClass);

    @NotNull
    String from(@NotNull SootMethod sootMethod);

    @NotNull
    String fromAbsPath(@NotNull IResource iResource);

    @NotNull
    String fromPath(@NotNull IResource iResource);

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IReportHashCalculator$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static String fromPath(@NotNull IReportHashCalculator $this, @NotNull IResource path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return $this.fromAbsPath(path.getAbsolute().getNormalize());
        }
    }
}
