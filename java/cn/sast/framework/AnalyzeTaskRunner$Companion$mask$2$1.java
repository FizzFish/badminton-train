package cn.sast.framework;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AnalyzeTaskRunner.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "AnalyzeTaskRunner.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.AnalyzeTaskRunner$Companion$mask$2$1")
/* loaded from: AnalyzeTaskRunner$Companion$mask$2$1.class */
final class AnalyzeTaskRunner$Companion$mask$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    int label;

    AnalyzeTaskRunner$Companion$mask$2$1(Continuation<? super AnalyzeTaskRunner$Companion$mask$2$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new AnalyzeTaskRunner$Companion$mask$2$1(continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Integer> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.AnalyzeTaskRunner$Companion$mask$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
