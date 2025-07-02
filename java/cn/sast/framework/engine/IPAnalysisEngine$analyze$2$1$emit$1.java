package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: IPAnalysisEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@DebugMetadata(f = "IPAnalysisEngine.kt", l = {290}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "emit", c = "cn.sast.framework.engine.IPAnalysisEngine$analyze$2$1")
/* loaded from: IPAnalysisEngine$analyze$2$1$emit$1.class */
final class IPAnalysisEngine$analyze$2$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int I$0;
    /* synthetic */ Object result;
    final /* synthetic */ IPAnalysisEngine$analyze$2$1<T> this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    IPAnalysisEngine$analyze$2$1$emit$1(IPAnalysisEngine$analyze$2$1<? super T> iPAnalysisEngine$analyze$2$1, Continuation<? super IPAnalysisEngine$analyze$2$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = iPAnalysisEngine$analyze$2$1;
    }

    public final Object invokeSuspend(Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((IEntryPointProvider.AnalyzeTask) null, (Continuation<? super Unit>) this);
    }
}
