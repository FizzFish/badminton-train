package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.data.AbstractionAtSink;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;

/* compiled from: InterProceduralValueAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/Sanitizer;", "", "cfg", "Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "absAndSink", "Lsoot/jimple/infoflow/data/AbstractionAtSink;", "<init>", "(Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;Lsoot/jimple/infoflow/data/AbstractionAtSink;)V", "getCfg", "()Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "getAbsAndSink", "()Lsoot/jimple/infoflow/data/AbstractionAtSink;", "doAnalysis", "", "corax-data-flow"})
/* loaded from: Sanitizer.class */
public final class Sanitizer {

    @NotNull
    private final IInfoflowCFG cfg;

    @NotNull
    private final AbstractionAtSink absAndSink;

    public Sanitizer(@NotNull IInfoflowCFG cfg, @NotNull AbstractionAtSink absAndSink) {
        Intrinsics.checkNotNullParameter(cfg, "cfg");
        Intrinsics.checkNotNullParameter(absAndSink, "absAndSink");
        this.cfg = cfg;
        this.absAndSink = absAndSink;
    }

    @NotNull
    public final IInfoflowCFG getCfg() {
        return this.cfg;
    }

    @NotNull
    public final AbstractionAtSink getAbsAndSink() {
        return this.absAndSink;
    }

    public final void doAnalysis() {
        Abstraction abstraction = this.absAndSink.getAbstraction();
        Intrinsics.checkNotNullExpressionValue(abstraction, "getAbstraction(...)");
        new DefaultAbstractionGraph(abstraction);
    }
}
