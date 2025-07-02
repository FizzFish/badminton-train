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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� #2\u00020\u0001:\u0002\"#B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020��2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010¨\u0006$"}, d2 = {"Lcn/sast/framework/report/sarif/PhysicalLocation;", "", "artifactLocation", "Lcn/sast/framework/report/sarif/ArtifactLocation;", "region", "Lcn/sast/framework/report/sarif/Region;", "<init>", "(Lcn/sast/framework/report/sarif/ArtifactLocation;Lcn/sast/framework/report/sarif/Region;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/framework/report/sarif/ArtifactLocation;Lcn/sast/framework/report/sarif/Region;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getArtifactLocation", "()Lcn/sast/framework/report/sarif/ArtifactLocation;", "getRegion", "()Lcn/sast/framework/report/sarif/Region;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: PhysicalLocation.class */
public final class PhysicalLocation {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final ArtifactLocation artifactLocation;

    @NotNull
    private final Region region;

    @NotNull
    public final ArtifactLocation component1() {
        return this.artifactLocation;
    }

    @NotNull
    public final Region component2() {
        return this.region;
    }

    @NotNull
    public final PhysicalLocation copy(@NotNull ArtifactLocation artifactLocation, @NotNull Region region) {
        Intrinsics.checkNotNullParameter(artifactLocation, "artifactLocation");
        Intrinsics.checkNotNullParameter(region, "region");
        return new PhysicalLocation(artifactLocation, region);
    }

    public static /* synthetic */ PhysicalLocation copy$default(PhysicalLocation physicalLocation, ArtifactLocation artifactLocation, Region region, int i, Object obj) {
        if ((i & 1) != 0) {
            artifactLocation = physicalLocation.artifactLocation;
        }
        if ((i & 2) != 0) {
            region = physicalLocation.region;
        }
        return physicalLocation.copy(artifactLocation, region);
    }

    @NotNull
    public String toString() {
        return "PhysicalLocation(artifactLocation=" + this.artifactLocation + ", region=" + this.region + ")";
    }

    public int hashCode() {
        int result = this.artifactLocation.hashCode();
        return (result * 31) + this.region.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhysicalLocation)) {
            return false;
        }
        PhysicalLocation physicalLocation = (PhysicalLocation) other;
        return Intrinsics.areEqual(this.artifactLocation, physicalLocation.artifactLocation) && Intrinsics.areEqual(this.region, physicalLocation.region);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/PhysicalLocation$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/PhysicalLocation;", "corax-framework"})
    /* loaded from: PhysicalLocation$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<PhysicalLocation> serializer() {
            return PhysicalLocation$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(PhysicalLocation self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, ArtifactLocation$$serializer.INSTANCE, self.artifactLocation);
        output.encodeSerializableElement(serialDesc, 1, Region$$serializer.INSTANCE, self.region);
    }

    public /* synthetic */ PhysicalLocation(int seen0, ArtifactLocation artifactLocation, Region region, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 3, PhysicalLocation$$serializer.INSTANCE.getDescriptor());
        }
        this.artifactLocation = artifactLocation;
        this.region = region;
    }

    public PhysicalLocation(@NotNull ArtifactLocation artifactLocation, @NotNull Region region) {
        Intrinsics.checkNotNullParameter(artifactLocation, "artifactLocation");
        Intrinsics.checkNotNullParameter(region, "region");
        this.artifactLocation = artifactLocation;
        this.region = region;
    }

    @NotNull
    public final ArtifactLocation getArtifactLocation() {
        return this.artifactLocation;
    }

    @NotNull
    public final Region getRegion() {
        return this.region;
    }
}
