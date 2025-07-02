package cn.sast.framework.report.coverage;

import cn.sast.common.IResDirectory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CoverageCompnment.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "CoverageCompnment.kt", l = {55}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.coverage.JacocoCompoundCoverage$flush$2$execTask$1")
/* loaded from: JacocoCompoundCoverage$flush$2$execTask$1.class */
final class JacocoCompoundCoverage$flush$2$execTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ JacocoCompoundCoverage this$0;
    final /* synthetic */ IResDirectory $output;
    final /* synthetic */ Charset $sourceEncoding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JacocoCompoundCoverage$flush$2$execTask$1(JacocoCompoundCoverage $receiver, IResDirectory $output, Charset $sourceEncoding, Continuation<? super JacocoCompoundCoverage$flush$2$execTask$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$output = $output;
        this.$sourceEncoding = $sourceEncoding;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new JacocoCompoundCoverage$flush$2$execTask$1(this.this$0, this.$output, this.$sourceEncoding, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (this.this$0.flush(this.this$0.executionCoverage, this.$output.resolve("code-coverage").toDirectory(), this.$sourceEncoding, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
