package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NotePath.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JD\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u000b¨\u0006 "}, d2 = {"Lcn/sast/framework/report/sqldelight/NotePath;", "", "__note_array_hash_id", "", "note_sequence", "note_stack_depth", "note_is_key_event", "__note_id", "<init>", "(JJLjava/lang/Long;Ljava/lang/Long;J)V", "get__note_array_hash_id", "()J", "getNote_sequence", "getNote_stack_depth", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getNote_is_key_event", "get__note_id", "component1", "component2", "component3", "component4", "component5", "copy", "(JJLjava/lang/Long;Ljava/lang/Long;J)Lcn/sast/framework/report/sqldelight/NotePath;", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: NotePath.class */
public final class NotePath {
    private final long __note_array_hash_id;
    private final long note_sequence;

    @Nullable
    private final Long note_stack_depth;

    @Nullable
    private final Long note_is_key_event;
    private final long __note_id;

    public final long component1() {
        return this.__note_array_hash_id;
    }

    public final long component2() {
        return this.note_sequence;
    }

    @Nullable
    public final Long component3() {
        return this.note_stack_depth;
    }

    @Nullable
    public final Long component4() {
        return this.note_is_key_event;
    }

    public final long component5() {
        return this.__note_id;
    }

    @NotNull
    public final NotePath copy(long __note_array_hash_id, long note_sequence, @Nullable Long note_stack_depth, @Nullable Long note_is_key_event, long __note_id) {
        return new NotePath(__note_array_hash_id, note_sequence, note_stack_depth, note_is_key_event, __note_id);
    }

    public static /* synthetic */ NotePath copy$default(NotePath notePath, long j, long j2, Long l, Long l2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = notePath.__note_array_hash_id;
        }
        if ((i & 2) != 0) {
            j2 = notePath.note_sequence;
        }
        if ((i & 4) != 0) {
            l = notePath.note_stack_depth;
        }
        if ((i & 8) != 0) {
            l2 = notePath.note_is_key_event;
        }
        if ((i & 16) != 0) {
            j3 = notePath.__note_id;
        }
        return notePath.copy(j, j2, l, l2, j3);
    }

    @NotNull
    public String toString() {
        long j = this.__note_array_hash_id;
        long j2 = this.note_sequence;
        Long l = this.note_stack_depth;
        Long l2 = this.note_is_key_event;
        long j3 = this.__note_id;
        return "NotePath(__note_array_hash_id=" + j + ", note_sequence=" + j + ", note_stack_depth=" + j2 + ", note_is_key_event=" + j + ", __note_id=" + l + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.__note_array_hash_id);
        return (((((((result * 31) + Long.hashCode(this.note_sequence)) * 31) + (this.note_stack_depth == null ? 0 : this.note_stack_depth.hashCode())) * 31) + (this.note_is_key_event == null ? 0 : this.note_is_key_event.hashCode())) * 31) + Long.hashCode(this.__note_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotePath)) {
            return false;
        }
        NotePath notePath = (NotePath) other;
        return this.__note_array_hash_id == notePath.__note_array_hash_id && this.note_sequence == notePath.note_sequence && Intrinsics.areEqual(this.note_stack_depth, notePath.note_stack_depth) && Intrinsics.areEqual(this.note_is_key_event, notePath.note_is_key_event) && this.__note_id == notePath.__note_id;
    }

    public NotePath(long __note_array_hash_id, long note_sequence, @Nullable Long note_stack_depth, @Nullable Long note_is_key_event, long __note_id) {
        this.__note_array_hash_id = __note_array_hash_id;
        this.note_sequence = note_sequence;
        this.note_stack_depth = note_stack_depth;
        this.note_is_key_event = note_is_key_event;
        this.__note_id = __note_id;
    }

    public final long get__note_array_hash_id() {
        return this.__note_array_hash_id;
    }

    public final long getNote_sequence() {
        return this.note_sequence;
    }

    @Nullable
    public final Long getNote_stack_depth() {
        return this.note_stack_depth;
    }

    @Nullable
    public final Long getNote_is_key_event() {
        return this.note_is_key_event;
    }

    public final long get__note_id() {
        return this.__note_id;
    }
}
