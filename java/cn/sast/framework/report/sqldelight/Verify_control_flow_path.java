package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Verify_control_flow_path.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/report/sqldelight/Verify_control_flow_path;", "", "id", "", "__control_flow_array_hash_id", "<init>", "(JJ)V", "getId", "()J", "get__control_flow_array_hash_id", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: Verify_control_flow_path.class */
public final class Verify_control_flow_path {
    private final long id;
    private final long __control_flow_array_hash_id;

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.__control_flow_array_hash_id;
    }

    @NotNull
    public final Verify_control_flow_path copy(long id, long __control_flow_array_hash_id) {
        return new Verify_control_flow_path(id, __control_flow_array_hash_id);
    }

    public static /* synthetic */ Verify_control_flow_path copy$default(Verify_control_flow_path verify_control_flow_path, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = verify_control_flow_path.id;
        }
        if ((i & 2) != 0) {
            j2 = verify_control_flow_path.__control_flow_array_hash_id;
        }
        return verify_control_flow_path.copy(j, j2);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        long j2 = this.__control_flow_array_hash_id;
        return "Verify_control_flow_path(id=" + j + ", __control_flow_array_hash_id=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (result * 31) + Long.hashCode(this.__control_flow_array_hash_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Verify_control_flow_path)) {
            return false;
        }
        Verify_control_flow_path verify_control_flow_path = (Verify_control_flow_path) other;
        return this.id == verify_control_flow_path.id && this.__control_flow_array_hash_id == verify_control_flow_path.__control_flow_array_hash_id;
    }

    public Verify_control_flow_path(long id, long __control_flow_array_hash_id) {
        this.id = id;
        this.__control_flow_array_hash_id = __control_flow_array_hash_id;
    }

    public final long getId() {
        return this.id;
    }

    public final long get__control_flow_array_hash_id() {
        return this.__control_flow_array_hash_id;
    }
}
