package cn.sast.framework.result;

import cn.sast.api.report.IResultCollector;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisReportEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcn/sast/framework/result/IPreAnalysisResultCollector;", "Lcn/sast/api/report/IResultCollector;", "report", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "info", "Lcn/sast/framework/engine/PreAnalysisReportEnv;", "corax-framework"})
/* loaded from: IPreAnalysisResultCollector.class */
public interface IPreAnalysisResultCollector extends IResultCollector {
    void report(@NotNull CheckType checkType, @NotNull PreAnalysisReportEnv preAnalysisReportEnv);

    /* compiled from: ResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IPreAnalysisResultCollector$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IPreAnalysisResultCollector $this, @NotNull Continuation<? super Unit> continuation) {
            Object objFlush = IResultCollector.DefaultImpls.flush($this, continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }
    }
}
