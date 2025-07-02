package cn.sast.framework.report.sqldelight.diagnostic;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Verify_file.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ$\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/report/sqldelight/diagnostic/Verify_file;", "", "id", "", "__file_id", "<init>", "(JLjava/lang/Long;)V", "getId", "()J", "get__file_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(JLjava/lang/Long;)Lcn/sast/framework/report/sqldelight/diagnostic/Verify_file;", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: Verify_file.class */
public final class Verify_file {
    private final long id;

    @Nullable
    private final Long __file_id;

    public final long component1() {
        return this.id;
    }

    @Nullable
    public final Long component2() {
        return this.__file_id;
    }

    @NotNull
    public final Verify_file copy(long id, @Nullable Long __file_id) {
        return new Verify_file(id, __file_id);
    }

    public static /* synthetic */ Verify_file copy$default(Verify_file verify_file, long j, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            j = verify_file.id;
        }
        if ((i & 2) != 0) {
            l = verify_file.__file_id;
        }
        return verify_file.copy(j, l);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        Long l = this.__file_id;
        return "Verify_file(id=" + j + ", __file_id=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (result * 31) + (this.__file_id == null ? 0 : this.__file_id.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Verify_file)) {
            return false;
        }
        Verify_file verify_file = (Verify_file) other;
        return this.id == verify_file.id && Intrinsics.areEqual(this.__file_id, verify_file.__file_id);
    }

    public Verify_file(long id, @Nullable Long __file_id) {
        this.id = id;
        this.__file_id = __file_id;
    }

    public final long getId() {
        return this.id;
    }

    @Nullable
    public final Long get__file_id() {
        return this.__file_id;
    }
}
