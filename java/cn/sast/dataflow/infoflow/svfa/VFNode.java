package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;
import soot.Value;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/VFNode;", "", "ap", "Lsoot/Value;", "stmt", "Lsoot/Unit;", "<init>", "(Lsoot/Value;Lsoot/Unit;)V", "getAp", "()Lsoot/Value;", "getStmt", "()Lsoot/Unit;", "hashCode", "", "equals", "", "other", "toString", "", "corax-data-flow"})
/* loaded from: VFNode.class */
public final class VFNode {

    @NotNull
    private final Value ap;

    @NotNull
    private final Unit stmt;

    public VFNode(@NotNull Value ap, @NotNull Unit stmt) {
        Intrinsics.checkNotNullParameter(ap, "ap");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        this.ap = ap;
        this.stmt = stmt;
    }

    @NotNull
    public final Value getAp() {
        return this.ap;
    }

    @NotNull
    public final Unit getStmt() {
        return this.stmt;
    }

    public int hashCode() {
        return Objects.hash(this.ap, this.stmt);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VFNode) && Intrinsics.areEqual(this.ap, ((VFNode) other).ap) && Intrinsics.areEqual(this.stmt, ((VFNode) other).stmt);
    }

    @NotNull
    public String toString() {
        return this.ap + " " + this.stmt;
    }
}
