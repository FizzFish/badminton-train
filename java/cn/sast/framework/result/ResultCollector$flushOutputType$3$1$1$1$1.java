package cn.sast.framework.result;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.report.SqliteDiagnostics;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "ResultCollector.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.result.ResultCollector$flushOutputType$3$1$1$1$1")
/* loaded from: ResultCollector$flushOutputType$3$1$1$1$1.class */
final class ResultCollector$flushOutputType$3$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SqliteDiagnostics $it;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultCollector$flushOutputType$3$1$1$1$1(SqliteDiagnostics $it, Continuation<? super ResultCollector$flushOutputType$3$1$1$1$1> continuation) {
        super(2, continuation);
        this.$it = $it;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new ResultCollector$flushOutputType$3$1$1$1$1(this.$it, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                SqliteDiagnostics.open$default(this.$it, null, 1, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
