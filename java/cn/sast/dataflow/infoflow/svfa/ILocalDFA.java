package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import soot.Unit;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018��2\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H&J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H&¨\u0006\t"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/ILocalDFA;", "", "getUsesOfAt", "", "Lsoot/Unit;", "ap", "Lcn/sast/dataflow/infoflow/svfa/AP;", "stmt", "getDefUsesOfAt", "corax-data-flow"})
/* loaded from: ILocalDFA.class */
public interface ILocalDFA {
    @NotNull
    List<Unit> getUsesOfAt(@NotNull AP ap, @NotNull Unit unit);

    @NotNull
    List<Unit> getDefUsesOfAt(@NotNull AP ap, @NotNull Unit unit);
}
