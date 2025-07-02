package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SparseInfoflowSolver.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028��\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0��J\u0006\u0010\u0014\u001a\u00020\u0012J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0013\u0010\u0003\u001a\u00028��¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0��0\u0010X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/RefCntUnit;", "N", "", "u", "cnt", "", "<init>", "(Ljava/lang/Object;I)V", "getU", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getCnt", "()I", "setCnt", "(I)V", "ref", "Ljava/util/Queue;", "add", "", "prev", "dec", "hashCode", "equals", "", "other", "toString", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSparseInfoflowSolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/RefCntUnit\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,232:1\n1863#2,2:233\n*S KotlinDebug\n*F\n+ 1 SparseInfoflowSolver.kt\ncn/sast/dataflow/infoflow/svfa/RefCntUnit\n*L\n38#1:233,2\n*E\n"})
/* loaded from: RefCntUnit.class */
public final class RefCntUnit<N> {
    private final N u;
    private int cnt;

    @NotNull
    private final Queue<RefCntUnit<N>> ref = new LinkedList();

    public RefCntUnit(N n, int cnt) {
        this.u = n;
        this.cnt = cnt;
    }

    public final N getU() {
        return this.u;
    }

    public final int getCnt() {
        return this.cnt;
    }

    public final void setCnt(int i) {
        this.cnt = i;
    }

    public final void add(@NotNull RefCntUnit<N> refCntUnit) {
        Intrinsics.checkNotNullParameter(refCntUnit, "prev");
        this.ref.add(refCntUnit);
        refCntUnit.cnt++;
    }

    public final void dec() {
        this.cnt--;
        if (this.cnt == 0) {
            Iterable $this$forEach$iv = this.ref;
            for (Object element$iv : $this$forEach$iv) {
                RefCntUnit it = (RefCntUnit) element$iv;
                it.dec();
            }
        }
        if (!(this.cnt >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public int hashCode() {
        N n = this.u;
        if (n != null) {
            return n.hashCode();
        }
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof RefCntUnit) {
            return Intrinsics.areEqual(((RefCntUnit) other).u, this.u);
        }
        return false;
    }

    @NotNull
    public String toString() {
        return this.u + " " + this.cnt;
    }
}
