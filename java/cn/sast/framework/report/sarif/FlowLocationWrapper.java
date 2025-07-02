package cn.sast.framework.report.sarif;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� \u001e2\u00020\u0001:\u0002\u001d\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020��2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/report/sarif/FlowLocationWrapper;", "", "location", "Lcn/sast/framework/report/sarif/FlowLocation;", "<init>", "(Lcn/sast/framework/report/sarif/FlowLocation;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/framework/report/sarif/FlowLocation;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLocation", "()Lcn/sast/framework/report/sarif/FlowLocation;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: FlowLocationWrapper.class */
public final class FlowLocationWrapper {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final FlowLocation location;

    @NotNull
    public final FlowLocation component1() {
        return this.location;
    }

    @NotNull
    public final FlowLocationWrapper copy(@NotNull FlowLocation location) {
        Intrinsics.checkNotNullParameter(location, "location");
        return new FlowLocationWrapper(location);
    }

    public static /* synthetic */ FlowLocationWrapper copy$default(FlowLocationWrapper flowLocationWrapper, FlowLocation flowLocation, int i, Object obj) {
        if ((i & 1) != 0) {
            flowLocation = flowLocationWrapper.location;
        }
        return flowLocationWrapper.copy(flowLocation);
    }

    @NotNull
    public String toString() {
        return "FlowLocationWrapper(location=" + this.location + ")";
    }

    public int hashCode() {
        return this.location.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FlowLocationWrapper) && Intrinsics.areEqual(this.location, ((FlowLocationWrapper) other).location);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/FlowLocationWrapper$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/FlowLocationWrapper;", "corax-framework"})
    /* loaded from: FlowLocationWrapper$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<FlowLocationWrapper> serializer() {
            return FlowLocationWrapper$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ FlowLocationWrapper(int seen0, FlowLocation location, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, FlowLocationWrapper$$serializer.INSTANCE.getDescriptor());
        }
        this.location = location;
    }

    public FlowLocationWrapper(@NotNull FlowLocation location) {
        Intrinsics.checkNotNullParameter(location, "location");
        this.location = location;
    }

    @NotNull
    public final FlowLocation getLocation() {
        return this.location;
    }
}
