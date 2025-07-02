package cn.sast.framework.report.sarif;

import cn.sast.api.config.ExtSettings;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� (2\u00020\u0001:\u0002()B'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tB?\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0006\u0010\u0016\u001a\u00020\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u001a\u001a\u00020��2\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020��2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006*"}, d2 = {"Lcn/sast/framework/report/sarif/SarifLog;", "", "schema", "", "version", "runs", "", "Lcn/sast/framework/report/sarif/Run;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSchema$annotations", "()V", "getSchema", "()Ljava/lang/String;", "getVersion", "getRuns", "()Ljava/util/List;", "toJson", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "Companion", "$serializer", "corax-framework"})
/* loaded from: SarifLog.class */
public final class SarifLog {

    @NotNull
    private final String schema;

    @NotNull
    private final String version;

    @NotNull
    private final List<Run> runs;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, new ArrayListSerializer(Run$$serializer.INSTANCE)};

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, SarifLog::jsonFormat$lambda$0, 1, (Object) null);

    @SerialName("$schema")
    public static /* synthetic */ void getSchema$annotations() {
    }

    @NotNull
    public final String component1() {
        return this.schema;
    }

    @NotNull
    public final String component2() {
        return this.version;
    }

    @NotNull
    public final List<Run> component3() {
        return this.runs;
    }

    @NotNull
    public final SarifLog copy(@JsonProperty("$schema") @NotNull String schema, @NotNull String version, @NotNull List<Run> list) {
        Intrinsics.checkNotNullParameter(schema, "schema");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(list, "runs");
        return new SarifLog(schema, version, list);
    }

    public static /* synthetic */ SarifLog copy$default(SarifLog sarifLog, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sarifLog.schema;
        }
        if ((i & 2) != 0) {
            str2 = sarifLog.version;
        }
        if ((i & 4) != 0) {
            list = sarifLog.runs;
        }
        return sarifLog.copy(str, str2, list);
    }

    @NotNull
    public String toString() {
        return "SarifLog(schema=" + this.schema + ", version=" + this.version + ", runs=" + this.runs + ")";
    }

    public int hashCode() {
        int result = this.schema.hashCode();
        return (((result * 31) + this.version.hashCode()) * 31) + this.runs.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SarifLog)) {
            return false;
        }
        SarifLog sarifLog = (SarifLog) other;
        return Intrinsics.areEqual(this.schema, sarifLog.schema) && Intrinsics.areEqual(this.version, sarifLog.version) && Intrinsics.areEqual(this.runs, sarifLog.runs);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(SarifLog self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.schema);
        output.encodeStringElement(serialDesc, 1, self.version);
        output.encodeSerializableElement(serialDesc, 2, serializationStrategyArr[2], self.runs);
    }

    public /* synthetic */ SarifLog(int seen0, String schema, String version, List runs, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (7 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 7, SarifLog$$serializer.INSTANCE.getDescriptor());
        }
        this.schema = schema;
        this.version = version;
        this.runs = runs;
    }

    public SarifLog(@JsonProperty("$schema") @NotNull String schema, @NotNull String version, @NotNull List<Run> list) {
        Intrinsics.checkNotNullParameter(schema, "schema");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(list, "runs");
        this.schema = schema;
        this.version = version;
        this.runs = list;
    }

    @NotNull
    public final String getSchema() {
        return this.schema;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    @NotNull
    public final List<Run> getRuns() {
        return this.runs;
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"Lcn/sast/framework/report/sarif/SarifLog$Companion;", "", "<init>", "()V", "jsonFormat", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/SarifLog;", "corax-framework"})
    /* loaded from: SarifLog$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<SarifLog> serializer() {
            return SarifLog$$serializer.INSTANCE;
        }
    }

    private static final Unit jsonFormat$lambda$0(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(ExtSettings.INSTANCE.getPrettyPrintJsonReport());
        $this$Json.setEncodeDefaults(false);
        return Unit.INSTANCE;
    }

    @NotNull
    public final String toJson() {
        return jsonFormat.encodeToString(Companion.serializer(), this);
    }
}
