package cn.sast.framework.report;

import cn.sast.api.report.Report;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IReportConsumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReportConsumer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH¦@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcn/sast/framework/report/IFileReportConsumer;", "Lcn/sast/framework/report/IReportConsumer;", "flush", "", "reports", "", "Lcn/sast/api/report/Report;", "filename", "", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Ljava/util/List;Ljava/lang/String;Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-framework"})
/* loaded from: IFileReportConsumer.class */
public interface IFileReportConsumer extends IReportConsumer {
    @Nullable
    Object flush(@NotNull List<Report> list, @NotNull String str, @NotNull IProjectFileLocator iProjectFileLocator, @NotNull Continuation<? super Unit> continuation);

    /* compiled from: ReportConsumer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IFileReportConsumer$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object run(@NotNull IFileReportConsumer $this, @NotNull IProjectFileLocator locator, @NotNull Continuation<? super Unit> continuation) {
            Object objRun = IReportConsumer.DefaultImpls.run($this, locator, continuation);
            return objRun == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRun : Unit.INSTANCE;
        }
    }
}
