package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ControlFlowPath.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlowPath;", "", "__control_flow_array_hash_id", "", "control_flow_sequence", "__control_flow_id", "<init>", "(JJJ)V", "get__control_flow_array_hash_id", "()J", "getControl_flow_sequence", "get__control_flow_id", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: ControlFlowPath.class */
public final class ControlFlowPath {
    private final long __control_flow_array_hash_id;
    private final long control_flow_sequence;
    private final long __control_flow_id;

    public final long component1() {
        return this.__control_flow_array_hash_id;
    }

    public final long component2() {
        return this.control_flow_sequence;
    }

    public final long component3() {
        return this.__control_flow_id;
    }

    @NotNull
    public final ControlFlowPath copy(long __control_flow_array_hash_id, long control_flow_sequence, long __control_flow_id) {
        return new ControlFlowPath(__control_flow_array_hash_id, control_flow_sequence, __control_flow_id);
    }

    public static /* synthetic */ ControlFlowPath copy$default(ControlFlowPath controlFlowPath, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = controlFlowPath.__control_flow_array_hash_id;
        }
        if ((i & 2) != 0) {
            j2 = controlFlowPath.control_flow_sequence;
        }
        if ((i & 4) != 0) {
            j3 = controlFlowPath.__control_flow_id;
        }
        return controlFlowPath.copy(j, j2, j3);
    }

    @NotNull
    public String toString() {
        long j = this.__control_flow_array_hash_id;
        long j2 = this.control_flow_sequence;
        long j3 = this.__control_flow_id;
        return "ControlFlowPath(__control_flow_array_hash_id=" + j + ", control_flow_sequence=" + j + ", __control_flow_id=" + j2 + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.__control_flow_array_hash_id);
        return (((result * 31) + Long.hashCode(this.control_flow_sequence)) * 31) + Long.hashCode(this.__control_flow_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ControlFlowPath)) {
            return false;
        }
        ControlFlowPath controlFlowPath = (ControlFlowPath) other;
        return this.__control_flow_array_hash_id == controlFlowPath.__control_flow_array_hash_id && this.control_flow_sequence == controlFlowPath.control_flow_sequence && this.__control_flow_id == controlFlowPath.__control_flow_id;
    }

    public ControlFlowPath(long __control_flow_array_hash_id, long control_flow_sequence, long __control_flow_id) {
        this.__control_flow_array_hash_id = __control_flow_array_hash_id;
        this.control_flow_sequence = control_flow_sequence;
        this.__control_flow_id = __control_flow_id;
    }

    public final long get__control_flow_array_hash_id() {
        return this.__control_flow_array_hash_id;
    }

    public final long getControl_flow_sequence() {
        return this.control_flow_sequence;
    }

    public final long get__control_flow_id() {
        return this.__control_flow_id;
    }
}
