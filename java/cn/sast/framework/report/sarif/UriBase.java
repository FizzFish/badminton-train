package cn.sast.framework.report.sarif;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� \"2\u00020\u0001:\u0002!\"B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020��2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lcn/sast/framework/report/sarif/UriBase;", "", "uri", "", "description", "Lcn/sast/framework/report/sarif/Description;", "<init>", "(Ljava/lang/String;Lcn/sast/framework/report/sarif/Description;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcn/sast/framework/report/sarif/Description;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUri", "()Ljava/lang/String;", "getDescription", "()Lcn/sast/framework/report/sarif/Description;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: UriBase.class */
public final class UriBase {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String uri;

    @Nullable
    private final Description description;

    @NotNull
    public final String component1() {
        return this.uri;
    }

    @Nullable
    public final Description component2() {
        return this.description;
    }

    @NotNull
    public final UriBase copy(@NotNull String uri, @Nullable Description description) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new UriBase(uri, description);
    }

    public static /* synthetic */ UriBase copy$default(UriBase uriBase, String str, Description description, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uriBase.uri;
        }
        if ((i & 2) != 0) {
            description = uriBase.description;
        }
        return uriBase.copy(str, description);
    }

    @NotNull
    public String toString() {
        return "UriBase(uri=" + this.uri + ", description=" + this.description + ")";
    }

    public int hashCode() {
        int result = this.uri.hashCode();
        return (result * 31) + (this.description == null ? 0 : this.description.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UriBase)) {
            return false;
        }
        UriBase uriBase = (UriBase) other;
        return Intrinsics.areEqual(this.uri, uriBase.uri) && Intrinsics.areEqual(this.description, uriBase.description);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/UriBase$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/UriBase;", "corax-framework"})
    /* loaded from: UriBase$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<UriBase> serializer() {
            return UriBase$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(UriBase self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.uri);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || self.description != null;
        if (z) {
            output.encodeNullableSerializableElement(serialDesc, 1, Description$$serializer.INSTANCE, self.description);
        }
    }

    public /* synthetic */ UriBase(int seen0, String uri, Description description, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, UriBase$$serializer.INSTANCE.getDescriptor());
        }
        this.uri = uri;
        if ((seen0 & 2) == 0) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public UriBase(@NotNull String uri, @Nullable Description description) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.uri = uri;
        this.description = description;
    }

    public /* synthetic */ UriBase(String str, Description description, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : description);
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }

    @Nullable
    public final Description getDescription() {
        return this.description;
    }
}
