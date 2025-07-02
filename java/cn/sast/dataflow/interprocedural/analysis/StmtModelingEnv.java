package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.ModelingStmtInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Unit;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/StmtModelingEnv;", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "u", "Lsoot/Unit;", "info", "Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "<init>", "(Lsoot/Unit;Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;)V", "getInfo", "()Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "corax-data-flow"})
/* loaded from: StmtModelingEnv.class */
public final class StmtModelingEnv extends HeapValuesEnv {

    @NotNull
    private final ModelingStmtInfo info;

    @NotNull
    public final ModelingStmtInfo getInfo() {
        return this.info;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StmtModelingEnv(@NotNull Unit u, @NotNull ModelingStmtInfo info) {
        super(u);
        Intrinsics.checkNotNullParameter(u, "u");
        Intrinsics.checkNotNullParameter(info, "info");
        this.info = info;
    }
}
