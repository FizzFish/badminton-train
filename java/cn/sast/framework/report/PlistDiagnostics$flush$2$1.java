package cn.sast.framework.report;

import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.dd.plist.NSDictionary;
import com.dd.plist.XMLPropertyListWriter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PlistDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PlistDiagnostics.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.PlistDiagnostics$flush$2$1")
/* loaded from: PlistDiagnostics$flush$2$1.class */
final class PlistDiagnostics$flush$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ NSDictionary $it;
    final /* synthetic */ IResource $fullPath;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlistDiagnostics$flush$2$1(NSDictionary $it, IResource $fullPath, Continuation<? super PlistDiagnostics$flush$2$1> continuation) {
        super(2, continuation);
        this.$it = $it;
        this.$fullPath = $fullPath;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new PlistDiagnostics$flush$2$1(this.$it, this.$fullPath, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                XMLPropertyListWriter.write(this.$it, this.$fullPath.getPath());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
