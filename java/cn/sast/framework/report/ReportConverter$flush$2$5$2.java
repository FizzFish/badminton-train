package cn.sast.framework.report;

import cn.sast.common.ExceptionsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import mu.KLogger;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "ReportConverter.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.ReportConverter$flush$2$5$2")
/* loaded from: ReportConverter$flush$2$5$2.class */
final class ReportConverter$flush$2$5$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ IReportConsumer $consumer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ReportConverter$flush$2$5$2(IReportConsumer $consumer, Continuation<? super ReportConverter$flush$2$5$2> continuation) {
        super(2, continuation);
        this.$consumer = $consumer;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new ReportConverter$flush$2$5$2(this.$consumer, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                try {
                    this.$consumer.close();
                } catch (Throwable t) {
                    KLogger logger = ReportConverter.Companion.getLogger();
                    IReportConsumer iReportConsumer = this.$consumer;
                    logger.error(t, () -> {
                        return invokeSuspend$lambda$0(r2);
                    });
                    ExceptionsKt.checkCritical(t);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private static final Object invokeSuspend$lambda$0(IReportConsumer $consumer) {
        return "Failed to close " + $consumer.getType() + " consumer: " + $consumer;
    }
}
