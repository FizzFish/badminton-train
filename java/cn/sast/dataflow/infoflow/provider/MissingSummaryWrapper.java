package cn.sast.dataflow.infoflow.provider;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;
import soot.jimple.infoflow.methodSummary.data.provider.IMethodSummaryProvider;
import soot.jimple.infoflow.methodSummary.taintWrappers.ReportMissingSummaryWrapper;

/* compiled from: MissingSummaryWrapper.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018��2\u00020\u0001B2\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0014R,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/MissingSummaryWrapper;", "Lsoot/jimple/infoflow/methodSummary/taintWrappers/ReportMissingSummaryWrapper;", "flows", "Lsoot/jimple/infoflow/methodSummary/data/provider/IMethodSummaryProvider;", "reportMissing", "Lkotlin/Function1;", "Lsoot/SootMethod;", "Lkotlin/ParameterName;", "name", "method", "", "<init>", "(Lsoot/jimple/infoflow/methodSummary/data/provider/IMethodSummaryProvider;Lkotlin/jvm/functions/Function1;)V", "getReportMissing", "()Lkotlin/jvm/functions/Function1;", "reportMissingMethod", "corax-data-flow"})
/* loaded from: MissingSummaryWrapper.class */
public final class MissingSummaryWrapper extends ReportMissingSummaryWrapper {

    @NotNull
    private final Function1<SootMethod, Unit> reportMissing;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingSummaryWrapper(@NotNull IMethodSummaryProvider flows, @NotNull Function1<? super SootMethod, Unit> function1) {
        super(flows);
        Intrinsics.checkNotNullParameter(flows, "flows");
        Intrinsics.checkNotNullParameter(function1, "reportMissing");
        this.reportMissing = function1;
    }

    @NotNull
    public final Function1<SootMethod, Unit> getReportMissing() {
        return this.reportMissing;
    }

    protected void reportMissingMethod(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.reportMissing.invoke(method);
        super.reportMissingMethod(method);
    }
}
