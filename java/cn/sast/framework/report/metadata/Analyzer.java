package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalysisMetadata.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� %2\u00020\u0001:\u0002$%B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bB;\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u0016\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\nHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020��2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Lcn/sast/framework/report/metadata/Analyzer;", "", "analyzerStatistics", "Lcn/sast/framework/report/metadata/AnalyzerStatistics;", "checkers", "", "", "<init>", "(Lcn/sast/framework/report/metadata/AnalyzerStatistics;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/framework/report/metadata/AnalyzerStatistics;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAnalyzerStatistics$annotations", "()V", "getAnalyzerStatistics", "()Lcn/sast/framework/report/metadata/AnalyzerStatistics;", "getCheckers", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Analyzer.class */
public final class Analyzer {

    @NotNull
    private final AnalyzerStatistics analyzerStatistics;

    @NotNull
    private final Map<String, String> checkers;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE)};

    @SerialName("analyzer_statistics")
    public static /* synthetic */ void getAnalyzerStatistics$annotations() {
    }

    @NotNull
    public final AnalyzerStatistics component1() {
        return this.analyzerStatistics;
    }

    @NotNull
    public final Map<String, String> component2() {
        return this.checkers;
    }

    @NotNull
    public final Analyzer copy(@NotNull AnalyzerStatistics analyzerStatistics, @NotNull Map<String, String> map) {
        Intrinsics.checkNotNullParameter(analyzerStatistics, "analyzerStatistics");
        Intrinsics.checkNotNullParameter(map, "checkers");
        return new Analyzer(analyzerStatistics, map);
    }

    public static /* synthetic */ Analyzer copy$default(Analyzer analyzer, AnalyzerStatistics analyzerStatistics, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            analyzerStatistics = analyzer.analyzerStatistics;
        }
        if ((i & 2) != 0) {
            map = analyzer.checkers;
        }
        return analyzer.copy(analyzerStatistics, map);
    }

    @NotNull
    public String toString() {
        return "Analyzer(analyzerStatistics=" + this.analyzerStatistics + ", checkers=" + this.checkers + ")";
    }

    public int hashCode() {
        int result = this.analyzerStatistics.hashCode();
        return (result * 31) + this.checkers.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Analyzer)) {
            return false;
        }
        Analyzer analyzer = (Analyzer) other;
        return Intrinsics.areEqual(this.analyzerStatistics, analyzer.analyzerStatistics) && Intrinsics.areEqual(this.checkers, analyzer.checkers);
    }

    /* compiled from: AnalysisMetadata.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/metadata/Analyzer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/metadata/Analyzer;", "corax-framework"})
    /* loaded from: Analyzer$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Analyzer> serializer() {
            return Analyzer$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Analyzer self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, AnalyzerStatistics$$serializer.INSTANCE, self.analyzerStatistics);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.checkers, MapsKt.emptyMap());
        if (z) {
            output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.checkers);
        }
    }

    public /* synthetic */ Analyzer(int seen0, AnalyzerStatistics analyzerStatistics, Map checkers, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, Analyzer$$serializer.INSTANCE.getDescriptor());
        }
        this.analyzerStatistics = analyzerStatistics;
        if ((seen0 & 2) == 0) {
            this.checkers = MapsKt.emptyMap();
        } else {
            this.checkers = checkers;
        }
    }

    public Analyzer(@NotNull AnalyzerStatistics analyzerStatistics, @NotNull Map<String, String> map) {
        Intrinsics.checkNotNullParameter(analyzerStatistics, "analyzerStatistics");
        Intrinsics.checkNotNullParameter(map, "checkers");
        this.analyzerStatistics = analyzerStatistics;
        this.checkers = map;
    }

    public /* synthetic */ Analyzer(AnalyzerStatistics analyzerStatistics, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(analyzerStatistics, (i & 2) != 0 ? MapsKt.emptyMap() : map);
    }

    @NotNull
    public final AnalyzerStatistics getAnalyzerStatistics() {
        return this.analyzerStatistics;
    }

    @NotNull
    public final Map<String, String> getCheckers() {
        return this.checkers;
    }
}
