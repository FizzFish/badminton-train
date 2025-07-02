package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.OutputType;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReportConsumer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u000e\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010\fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"}, d2 = {"Lcn/sast/framework/report/IReportConsumer;", "Ljava/io/Closeable;", "type", "Lcn/sast/framework/result/OutputType;", "getType", "()Lcn/sast/framework/result/OutputType;", "init", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "run", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-framework"})
/* loaded from: IReportConsumer.class */
public interface IReportConsumer extends Closeable {
    @NotNull
    OutputType getType();

    @Nullable
    Object init(@NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object run(@NotNull IProjectFileLocator iProjectFileLocator, @NotNull Continuation<? super Unit> continuation);

    /* compiled from: ReportConsumer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IReportConsumer$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object run(@NotNull IReportConsumer $this, @NotNull IProjectFileLocator locator, @NotNull Continuation<? super Unit> continuation) {
            return Unit.INSTANCE;
        }
    }
}
