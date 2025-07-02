package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MacroExpansion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/report/sqldelight/MacroExpansion;", "", "__macro_note_set_hash_id", "", "__macro_note_id", "<init>", "(JJ)V", "get__macro_note_set_hash_id", "()J", "get__macro_note_id", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: MacroExpansion.class */
public final class MacroExpansion {
    private final long __macro_note_set_hash_id;
    private final long __macro_note_id;

    public final long component1() {
        return this.__macro_note_set_hash_id;
    }

    public final long component2() {
        return this.__macro_note_id;
    }

    @NotNull
    public final MacroExpansion copy(long __macro_note_set_hash_id, long __macro_note_id) {
        return new MacroExpansion(__macro_note_set_hash_id, __macro_note_id);
    }

    public static /* synthetic */ MacroExpansion copy$default(MacroExpansion macroExpansion, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = macroExpansion.__macro_note_set_hash_id;
        }
        if ((i & 2) != 0) {
            j2 = macroExpansion.__macro_note_id;
        }
        return macroExpansion.copy(j, j2);
    }

    @NotNull
    public String toString() {
        long j = this.__macro_note_set_hash_id;
        long j2 = this.__macro_note_id;
        return "MacroExpansion(__macro_note_set_hash_id=" + j + ", __macro_note_id=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.__macro_note_set_hash_id);
        return (result * 31) + Long.hashCode(this.__macro_note_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MacroExpansion)) {
            return false;
        }
        MacroExpansion macroExpansion = (MacroExpansion) other;
        return this.__macro_note_set_hash_id == macroExpansion.__macro_note_set_hash_id && this.__macro_note_id == macroExpansion.__macro_note_id;
    }

    public MacroExpansion(long __macro_note_set_hash_id, long __macro_note_id) {
        this.__macro_note_set_hash_id = __macro_note_set_hash_id;
        this.__macro_note_id = __macro_note_id;
    }

    public final long get__macro_note_set_hash_id() {
        return this.__macro_note_set_hash_id;
    }

    public final long get__macro_note_id() {
        return this.__macro_note_id;
    }
}
