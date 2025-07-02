package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SchemaInfo.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcn/sast/framework/report/sqldelight/SchemaInfo;", "", "key", "", "value_", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getValue_", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: SchemaInfo.class */
public final class SchemaInfo {

    @NotNull
    private final String key;

    @NotNull
    private final String value_;

    @NotNull
    public final String component1() {
        return this.key;
    }

    @NotNull
    public final String component2() {
        return this.value_;
    }

    @NotNull
    public final SchemaInfo copy(@NotNull String key, @NotNull String value_) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value_, "value_");
        return new SchemaInfo(key, value_);
    }

    public static /* synthetic */ SchemaInfo copy$default(SchemaInfo schemaInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = schemaInfo.key;
        }
        if ((i & 2) != 0) {
            str2 = schemaInfo.value_;
        }
        return schemaInfo.copy(str, str2);
    }

    @NotNull
    public String toString() {
        return "SchemaInfo(key=" + this.key + ", value_=" + this.value_ + ")";
    }

    public int hashCode() {
        int result = this.key.hashCode();
        return (result * 31) + this.value_.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SchemaInfo)) {
            return false;
        }
        SchemaInfo schemaInfo = (SchemaInfo) other;
        return Intrinsics.areEqual(this.key, schemaInfo.key) && Intrinsics.areEqual(this.value_, schemaInfo.value_);
    }

    public SchemaInfo(@NotNull String key, @NotNull String value_) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value_, "value_");
        this.key = key;
        this.value_ = value_;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final String getValue_() {
        return this.value_;
    }
}
