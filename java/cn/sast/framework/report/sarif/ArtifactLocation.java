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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018��  2\u00020\u0001:\u0002\u001f B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\bHÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020��2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\r¨\u0006!"}, d2 = {"Lcn/sast/framework/report/sarif/ArtifactLocation;", "", "uri", "", "uriBaseId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUri", "()Ljava/lang/String;", "getUriBaseId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: ArtifactLocation.class */
public final class ArtifactLocation {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String uri;

    @NotNull
    private final String uriBaseId;

    @NotNull
    public final String component1() {
        return this.uri;
    }

    @NotNull
    public final String component2() {
        return this.uriBaseId;
    }

    @NotNull
    public final ArtifactLocation copy(@NotNull String uri, @NotNull String uriBaseId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(uriBaseId, "uriBaseId");
        return new ArtifactLocation(uri, uriBaseId);
    }

    public static /* synthetic */ ArtifactLocation copy$default(ArtifactLocation artifactLocation, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artifactLocation.uri;
        }
        if ((i & 2) != 0) {
            str2 = artifactLocation.uriBaseId;
        }
        return artifactLocation.copy(str, str2);
    }

    @NotNull
    public String toString() {
        return "ArtifactLocation(uri=" + this.uri + ", uriBaseId=" + this.uriBaseId + ")";
    }

    public int hashCode() {
        int result = this.uri.hashCode();
        return (result * 31) + this.uriBaseId.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArtifactLocation)) {
            return false;
        }
        ArtifactLocation artifactLocation = (ArtifactLocation) other;
        return Intrinsics.areEqual(this.uri, artifactLocation.uri) && Intrinsics.areEqual(this.uriBaseId, artifactLocation.uriBaseId);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/ArtifactLocation$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/ArtifactLocation;", "corax-framework"})
    /* loaded from: ArtifactLocation$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ArtifactLocation> serializer() {
            return ArtifactLocation$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(ArtifactLocation self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.uri);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.uriBaseId, "");
        if (z) {
            output.encodeStringElement(serialDesc, 1, self.uriBaseId);
        }
    }

    public /* synthetic */ ArtifactLocation(int seen0, String uri, String uriBaseId, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, ArtifactLocation$$serializer.INSTANCE.getDescriptor());
        }
        this.uri = uri;
        if ((seen0 & 2) == 0) {
            this.uriBaseId = "";
        } else {
            this.uriBaseId = uriBaseId;
        }
    }

    public ArtifactLocation(@NotNull String uri, @NotNull String uriBaseId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(uriBaseId, "uriBaseId");
        this.uri = uri;
        this.uriBaseId = uriBaseId;
    }

    public /* synthetic */ ArtifactLocation(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2);
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }

    @NotNull
    public final String getUriBaseId() {
        return this.uriBaseId;
    }
}
