package cn.sast.framework.result;

import cn.sast.api.report.IResultCollector;
import cn.sast.api.report.Report;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0004H&¨\u0006\u0005"}, d2 = {"Lcn/sast/framework/result/IBuiltInAnalysisCollector;", "Lcn/sast/api/report/IResultCollector;", "report", "", "Lcn/sast/api/report/Report;", "corax-framework"})
/* loaded from: IBuiltInAnalysisCollector.class */
public interface IBuiltInAnalysisCollector extends IResultCollector {
    void report(@NotNull Report report);

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IBuiltInAnalysisCollector$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IBuiltInAnalysisCollector $this, @NotNull Continuation<? super Unit> continuation) {
            Object objFlush = IResultCollector.DefaultImpls.flush($this, continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }
    }
}
