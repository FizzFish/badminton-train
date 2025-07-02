package cn.sast.framework.entries.apk;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import soot.SootClass;

/* compiled from: ApkLifeCycleComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "ApkLifeCycleComponent.kt", l = {284, 291}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "J$0", "J$1", "J$0"}, n = {"$this$flow", "entryPointWorkList", "callbackDuration", "callbackDurationTotal", "callbackDurationTotal"}, m = "invokeSuspend", c = "cn.sast.framework.entries.apk.ApkLifeCycleComponent$iterator$1")
/* loaded from: ApkLifeCycleComponent$iterator$1.class */
final class ApkLifeCycleComponent$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    Object L$1;
    long J$0;
    long J$1;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ ApkLifeCycleComponent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ApkLifeCycleComponent$iterator$1(ApkLifeCycleComponent $receiver, Continuation<? super ApkLifeCycleComponent$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> apkLifeCycleComponent$iterator$1 = new ApkLifeCycleComponent$iterator$1(this.this$0, continuation);
        apkLifeCycleComponent$iterator$1.L$0 = value;
        return apkLifeCycleComponent$iterator$1;
    }

    public final Object invoke(FlowCollector<? super IEntryPointProvider.AnalyzeTask> flowCollector, Continuation<? super Unit> continuation) {
        return create(flowCollector, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e1 A[Catch: IOException -> 0x0210, XmlPullParserException -> 0x0238, TRY_LEAVE, TryCatch #3 {IOException -> 0x0210, XmlPullParserException -> 0x0238, blocks: (B:19:0x00b4, B:21:0x00bf, B:22:0x00cc, B:27:0x00e1, B:34:0x0190, B:35:0x019e, B:42:0x0202, B:33:0x018a, B:41:0x01fc), top: B:55:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0260 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 650
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.entries.apk.ApkLifeCycleComponent$iterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final Object invokeSuspend$lambda$0(long $duration, SootClass $entryPoint) {
        double d = $duration / 1.0E9d;
        return "\nCollecting callbacks took " + d + " seconds for component " + d;
    }

    private static final Object invokeSuspend$lambda$1(long $callbackDurationSeconds) {
        return "Collecting callbacks and building a call graph took " + $callbackDurationSeconds + " seconds";
    }
}
