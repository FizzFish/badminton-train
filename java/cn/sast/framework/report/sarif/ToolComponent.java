package cn.sast.framework.report.sarif;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� )2\u00020\u0001:\u0002()B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nBI\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J7\u0010\u001a\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\fHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020��2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006*"}, d2 = {"Lcn/sast/framework/report/sarif/ToolComponent;", "", "name", "", "organization", "version", "rules", "", "Lcn/sast/framework/report/sarif/Rule;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getOrganization", "getVersion", "getRules", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: ToolComponent.class */
public final class ToolComponent {

    @NotNull
    private final String name;

    @NotNull
    private final String organization;

    @NotNull
    private final String version;

    @NotNull
    private final List<Rule> rules;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new ArrayListSerializer(Rule$$serializer.INSTANCE)};

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.organization;
    }

    @NotNull
    public final String component3() {
        return this.version;
    }

    @NotNull
    public final List<Rule> component4() {
        return this.rules;
    }

    @NotNull
    public final ToolComponent copy(@NotNull String name, @NotNull String organization, @NotNull String version, @NotNull List<Rule> list) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(organization, "organization");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(list, "rules");
        return new ToolComponent(name, organization, version, list);
    }

    public static /* synthetic */ ToolComponent copy$default(ToolComponent toolComponent, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = toolComponent.name;
        }
        if ((i & 2) != 0) {
            str2 = toolComponent.organization;
        }
        if ((i & 4) != 0) {
            str3 = toolComponent.version;
        }
        if ((i & 8) != 0) {
            list = toolComponent.rules;
        }
        return toolComponent.copy(str, str2, str3, list);
    }

    @NotNull
    public String toString() {
        return "ToolComponent(name=" + this.name + ", organization=" + this.organization + ", version=" + this.version + ", rules=" + this.rules + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((((result * 31) + this.organization.hashCode()) * 31) + this.version.hashCode()) * 31) + this.rules.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolComponent)) {
            return false;
        }
        ToolComponent toolComponent = (ToolComponent) other;
        return Intrinsics.areEqual(this.name, toolComponent.name) && Intrinsics.areEqual(this.organization, toolComponent.organization) && Intrinsics.areEqual(this.version, toolComponent.version) && Intrinsics.areEqual(this.rules, toolComponent.rules);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/ToolComponent$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/ToolComponent;", "corax-framework"})
    /* loaded from: ToolComponent$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ToolComponent> serializer() {
            return ToolComponent$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(ToolComponent self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.name);
        output.encodeStringElement(serialDesc, 1, self.organization);
        output.encodeStringElement(serialDesc, 2, self.version);
        output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.rules);
    }

    public /* synthetic */ ToolComponent(int seen0, String name, String organization, String version, List rules, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (15 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 15, ToolComponent$$serializer.INSTANCE.getDescriptor());
        }
        this.name = name;
        this.organization = organization;
        this.version = version;
        this.rules = rules;
    }

    public ToolComponent(@NotNull String name, @NotNull String organization, @NotNull String version, @NotNull List<Rule> list) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(organization, "organization");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(list, "rules");
        this.name = name;
        this.organization = organization;
        this.version = version;
        this.rules = list;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getOrganization() {
        return this.organization;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    @NotNull
    public final List<Rule> getRules() {
        return this.rules;
    }
}
