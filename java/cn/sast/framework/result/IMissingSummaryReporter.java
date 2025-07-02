package cn.sast.framework.result;

import cn.sast.api.report.IResultCollector;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;

/* compiled from: MissingSummaryReporter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/result/IMissingSummaryReporter;", "Lcn/sast/api/report/IResultCollector;", "reportMissingMethod", "", "method", "Lsoot/SootMethod;", "corax-framework"})
/* loaded from: IMissingSummaryReporter.class */
public interface IMissingSummaryReporter extends IResultCollector {
    void reportMissingMethod(@NotNull SootMethod sootMethod);

    /* compiled from: MissingSummaryReporter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IMissingSummaryReporter$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IMissingSummaryReporter $this, @NotNull Continuation<? super Unit> continuation) {
            Object objFlush = IResultCollector.DefaultImpls.flush($this, continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }

        public static void reportMissingMethod(@NotNull IMissingSummaryReporter $this, @NotNull SootMethod method) {
            Intrinsics.checkNotNullParameter(method, "method");
        }
    }
}
