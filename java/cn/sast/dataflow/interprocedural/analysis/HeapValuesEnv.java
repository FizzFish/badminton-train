package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Unit;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\b&\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "", "node", "Lsoot/Unit;", "<init>", "(Lsoot/Unit;)V", "getNode", "()Lsoot/Unit;", "toString", "", "corax-data-flow"})
/* loaded from: HeapValuesEnv.class */
public abstract class HeapValuesEnv {

    @NotNull
    private final Unit node;

    public HeapValuesEnv(@NotNull Unit node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.node = node;
    }

    @NotNull
    public final Unit getNode() {
        return this.node;
    }

    @NotNull
    public String toString() {
        return "Env *" + this.node.getJavaSourceStartLineNumber() + " " + this.node;
    }
}
