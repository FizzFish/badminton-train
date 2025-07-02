package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalysisMetadata.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� /2\u00020\u0001:\u0002./B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bBU\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003JG\u0010 \u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020��2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0011R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0017\u0010\u0013\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001a¨\u00060"}, d2 = {"Lcn/sast/framework/report/metadata/AnalyzerStatistics;", "", "failed", "", "failedSources", "", "", "successful", "successfulSources", "version", "<init>", "(ILjava/util/List;ILjava/util/List;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/util/List;ILjava/util/List;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFailed", "()I", "getFailedSources$annotations", "()V", "getFailedSources", "()Ljava/util/List;", "getSuccessful", "getSuccessfulSources$annotations", "getSuccessfulSources", "getVersion", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: AnalyzerStatistics.class */
public final class AnalyzerStatistics {
    private final int failed;

    @NotNull
    private final List<String> failedSources;
    private final int successful;

    @NotNull
    private final List<String> successfulSources;

    @NotNull
    private final String version;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, new ArrayListSerializer(StringSerializer.INSTANCE), null, new ArrayListSerializer(StringSerializer.INSTANCE), null};

    @SerialName("failed_sources")
    public static /* synthetic */ void getFailedSources$annotations() {
    }

    @SerialName("successful_sources")
    public static /* synthetic */ void getSuccessfulSources$annotations() {
    }

    public final int component1() {
        return this.failed;
    }

    @NotNull
    public final List<String> component2() {
        return this.failedSources;
    }

    public final int component3() {
        return this.successful;
    }

    @NotNull
    public final List<String> component4() {
        return this.successfulSources;
    }

    @NotNull
    public final String component5() {
        return this.version;
    }

    @NotNull
    public final AnalyzerStatistics copy(int failed, @NotNull List<String> list, int successful, @NotNull List<String> list2, @NotNull String version) {
        Intrinsics.checkNotNullParameter(list, "failedSources");
        Intrinsics.checkNotNullParameter(list2, "successfulSources");
        Intrinsics.checkNotNullParameter(version, "version");
        return new AnalyzerStatistics(failed, list, successful, list2, version);
    }

    public static /* synthetic */ AnalyzerStatistics copy$default(AnalyzerStatistics analyzerStatistics, int i, List list, int i2, List list2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = analyzerStatistics.failed;
        }
        if ((i3 & 2) != 0) {
            list = analyzerStatistics.failedSources;
        }
        if ((i3 & 4) != 0) {
            i2 = analyzerStatistics.successful;
        }
        if ((i3 & 8) != 0) {
            list2 = analyzerStatistics.successfulSources;
        }
        if ((i3 & 16) != 0) {
            str = analyzerStatistics.version;
        }
        return analyzerStatistics.copy(i, list, i2, list2, str);
    }

    @NotNull
    public String toString() {
        return "AnalyzerStatistics(failed=" + this.failed + ", failedSources=" + this.failedSources + ", successful=" + this.successful + ", successfulSources=" + this.successfulSources + ", version=" + this.version + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.failed);
        return (((((((result * 31) + this.failedSources.hashCode()) * 31) + Integer.hashCode(this.successful)) * 31) + this.successfulSources.hashCode()) * 31) + this.version.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalyzerStatistics)) {
            return false;
        }
        AnalyzerStatistics analyzerStatistics = (AnalyzerStatistics) other;
        return this.failed == analyzerStatistics.failed && Intrinsics.areEqual(this.failedSources, analyzerStatistics.failedSources) && this.successful == analyzerStatistics.successful && Intrinsics.areEqual(this.successfulSources, analyzerStatistics.successfulSources) && Intrinsics.areEqual(this.version, analyzerStatistics.version);
    }

    /* compiled from: AnalysisMetadata.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/metadata/AnalyzerStatistics$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/metadata/AnalyzerStatistics;", "corax-framework"})
    /* loaded from: AnalyzerStatistics$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<AnalyzerStatistics> serializer() {
            return AnalyzerStatistics$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(AnalyzerStatistics self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeIntElement(serialDesc, 0, self.failed);
        output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.failedSources);
        output.encodeIntElement(serialDesc, 2, self.successful);
        output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.successfulSources);
        output.encodeStringElement(serialDesc, 4, self.version);
    }

    public /* synthetic */ AnalyzerStatistics(int seen0, int failed, List failedSources, int successful, List successfulSources, String version, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (31 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 31, AnalyzerStatistics$$serializer.INSTANCE.getDescriptor());
        }
        this.failed = failed;
        this.failedSources = failedSources;
        this.successful = successful;
        this.successfulSources = successfulSources;
        this.version = version;
    }

    public AnalyzerStatistics(int failed, @NotNull List<String> list, int successful, @NotNull List<String> list2, @NotNull String version) {
        Intrinsics.checkNotNullParameter(list, "failedSources");
        Intrinsics.checkNotNullParameter(list2, "successfulSources");
        Intrinsics.checkNotNullParameter(version, "version");
        this.failed = failed;
        this.failedSources = list;
        this.successful = successful;
        this.successfulSources = list2;
        this.version = version;
    }

    public final int getFailed() {
        return this.failed;
    }

    @NotNull
    public final List<String> getFailedSources() {
        return this.failedSources;
    }

    public final int getSuccessful() {
        return this.successful;
    }

    @NotNull
    public final List<String> getSuccessfulSources() {
        return this.successfulSources;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }
}
