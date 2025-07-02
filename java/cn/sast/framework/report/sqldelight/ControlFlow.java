package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ControlFlow.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JS\u0010\u001d\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcn/sast/framework/report/sqldelight/ControlFlow;", "", "id", "", "__file_id", "_file_abs_path", "", "message_en", "message_zh", "__edge_from_region_id", "__edge_to_region_id", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getId", "()J", "get__file_id", "get_file_abs_path", "()Ljava/lang/String;", "getMessage_en", "getMessage_zh", "get__edge_from_region_id", "get__edge_to_region_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: ControlFlow.class */
public final class ControlFlow {
    private final long id;
    private final long __file_id;

    @NotNull
    private final String _file_abs_path;

    @Nullable
    private final String message_en;

    @Nullable
    private final String message_zh;
    private final long __edge_from_region_id;
    private final long __edge_to_region_id;

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.__file_id;
    }

    @NotNull
    public final String component3() {
        return this._file_abs_path;
    }

    @Nullable
    public final String component4() {
        return this.message_en;
    }

    @Nullable
    public final String component5() {
        return this.message_zh;
    }

    public final long component6() {
        return this.__edge_from_region_id;
    }

    public final long component7() {
        return this.__edge_to_region_id;
    }

    @NotNull
    public final ControlFlow copy(long id, long __file_id, @NotNull String _file_abs_path, @Nullable String message_en, @Nullable String message_zh, long __edge_from_region_id, long __edge_to_region_id) {
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        return new ControlFlow(id, __file_id, _file_abs_path, message_en, message_zh, __edge_from_region_id, __edge_to_region_id);
    }

    public static /* synthetic */ ControlFlow copy$default(ControlFlow controlFlow, long j, long j2, String str, String str2, String str3, long j3, long j4, int i, Object obj) {
        if ((i & 1) != 0) {
            j = controlFlow.id;
        }
        if ((i & 2) != 0) {
            j2 = controlFlow.__file_id;
        }
        if ((i & 4) != 0) {
            str = controlFlow._file_abs_path;
        }
        if ((i & 8) != 0) {
            str2 = controlFlow.message_en;
        }
        if ((i & 16) != 0) {
            str3 = controlFlow.message_zh;
        }
        if ((i & 32) != 0) {
            j3 = controlFlow.__edge_from_region_id;
        }
        if ((i & 64) != 0) {
            j4 = controlFlow.__edge_to_region_id;
        }
        return controlFlow.copy(j, j2, str, str2, str3, j3, j4);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        long j2 = this.__file_id;
        String str = this._file_abs_path;
        String str2 = this.message_en;
        String str3 = this.message_zh;
        long j3 = this.__edge_from_region_id;
        long j4 = this.__edge_to_region_id;
        return "ControlFlow(id=" + j + ", __file_id=" + j + ", _file_abs_path=" + j2 + ", message_en=" + j + ", message_zh=" + str + ", __edge_from_region_id=" + str2 + ", __edge_to_region_id=" + str3 + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (((((((((((result * 31) + Long.hashCode(this.__file_id)) * 31) + this._file_abs_path.hashCode()) * 31) + (this.message_en == null ? 0 : this.message_en.hashCode())) * 31) + (this.message_zh == null ? 0 : this.message_zh.hashCode())) * 31) + Long.hashCode(this.__edge_from_region_id)) * 31) + Long.hashCode(this.__edge_to_region_id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ControlFlow)) {
            return false;
        }
        ControlFlow controlFlow = (ControlFlow) other;
        return this.id == controlFlow.id && this.__file_id == controlFlow.__file_id && Intrinsics.areEqual(this._file_abs_path, controlFlow._file_abs_path) && Intrinsics.areEqual(this.message_en, controlFlow.message_en) && Intrinsics.areEqual(this.message_zh, controlFlow.message_zh) && this.__edge_from_region_id == controlFlow.__edge_from_region_id && this.__edge_to_region_id == controlFlow.__edge_to_region_id;
    }

    public ControlFlow(long id, long __file_id, @NotNull String _file_abs_path, @Nullable String message_en, @Nullable String message_zh, long __edge_from_region_id, long __edge_to_region_id) {
        Intrinsics.checkNotNullParameter(_file_abs_path, "_file_abs_path");
        this.id = id;
        this.__file_id = __file_id;
        this._file_abs_path = _file_abs_path;
        this.message_en = message_en;
        this.message_zh = message_zh;
        this.__edge_from_region_id = __edge_from_region_id;
        this.__edge_to_region_id = __edge_to_region_id;
    }

    public final long getId() {
        return this.id;
    }

    public final long get__file_id() {
        return this.__file_id;
    }

    @NotNull
    public final String get_file_abs_path() {
        return this._file_abs_path;
    }

    @Nullable
    public final String getMessage_en() {
        return this.message_en;
    }

    @Nullable
    public final String getMessage_zh() {
        return this.message_zh;
    }

    public final long get__edge_from_region_id() {
        return this.__edge_from_region_id;
    }

    public final long get__edge_to_region_id() {
        return this.__edge_to_region_id;
    }
}
