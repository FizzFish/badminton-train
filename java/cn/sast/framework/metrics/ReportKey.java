package cn.sast.framework.metrics;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MetricsMonitor.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018�� &2\u00020\u0001:\u0002%&B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB7\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J)\u0010\u0017\u001a\u00020��2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020��2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcn/sast/framework/metrics/ReportKey;", "", "category", "", "type", "size", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCategory", "()Ljava/lang/String;", "getType", "getSize", "()I", "setSize", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: ReportKey.class */
public final class ReportKey {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final String category;

    @NotNull
    private final String type;
    private int size;

    @Nullable
    public final String component1() {
        return this.category;
    }

    @NotNull
    public final String component2() {
        return this.type;
    }

    public final int component3() {
        return this.size;
    }

    @NotNull
    public final ReportKey copy(@Nullable String category, @NotNull String type, int size) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new ReportKey(category, type, size);
    }

    public static /* synthetic */ ReportKey copy$default(ReportKey reportKey, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = reportKey.category;
        }
        if ((i2 & 2) != 0) {
            str2 = reportKey.type;
        }
        if ((i2 & 4) != 0) {
            i = reportKey.size;
        }
        return reportKey.copy(str, str2, i);
    }

    @NotNull
    public String toString() {
        return "ReportKey(category=" + this.category + ", type=" + this.type + ", size=" + this.size + ")";
    }

    public int hashCode() {
        int result = this.category == null ? 0 : this.category.hashCode();
        return (((result * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.size);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportKey)) {
            return false;
        }
        ReportKey reportKey = (ReportKey) other;
        return Intrinsics.areEqual(this.category, reportKey.category) && Intrinsics.areEqual(this.type, reportKey.type) && this.size == reportKey.size;
    }

    /* compiled from: MetricsMonitor.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/metrics/ReportKey$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/metrics/ReportKey;", "corax-framework"})
    /* loaded from: ReportKey$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ReportKey> serializer() {
            return ReportKey$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(ReportKey self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || self.category != null;
        if (z) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.category);
        }
        output.encodeStringElement(serialDesc, 1, self.type);
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 2) || self.size != -1;
        if (z2) {
            output.encodeIntElement(serialDesc, 2, self.size);
        }
    }

    public /* synthetic */ ReportKey(int seen0, String category, String type, int size, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (2 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 2, ReportKey$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.category = null;
        } else {
            this.category = category;
        }
        this.type = type;
        if ((seen0 & 4) == 0) {
            this.size = -1;
        } else {
            this.size = size;
        }
    }

    public ReportKey(@Nullable String category, @NotNull String type, int size) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.category = category;
        this.type = type;
        this.size = size;
    }

    public /* synthetic */ ReportKey(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, str2, (i2 & 4) != 0 ? -1 : i);
    }

    @Nullable
    public final String getCategory() {
        return this.category;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }
}
