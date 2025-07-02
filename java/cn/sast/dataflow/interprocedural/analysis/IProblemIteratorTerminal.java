package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.FixPointStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;
import soot.Unit;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J6\u0010\u0003\u001a\u00020\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\t0\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0��H&¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IProblemIteratorTerminal;", "V", "", "hasChange", "Lcn/sast/idfa/analysis/FixPointStatus;", "context", "Lcn/sast/idfa/analysis/Context;", "Lsoot/SootMethod;", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "new", "corax-data-flow"})
/* loaded from: IProblemIteratorTerminal.class */
public interface IProblemIteratorTerminal<V> {
    @NotNull
    FixPointStatus hasChange(@NotNull Context<SootMethod, Unit, IFact<V>> context, @NotNull IProblemIteratorTerminal<V> iProblemIteratorTerminal);
}
