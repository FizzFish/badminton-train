package cn.sast.framework.report.sarif;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\\\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� .2\u00020\u0001:\u0002-.BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0004\b\r\u0010\u000eB[\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\r\u0010\u0013J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0003JI\u0010\u001f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0010HÖ\u0001J\t\u0010$\u001a\u00020\u0006HÖ\u0001J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020��2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u0019¨\u0006/"}, d2 = {"Lcn/sast/framework/report/sarif/Run;", "", "tool", "Lcn/sast/framework/report/sarif/Tool;", "originalUriBaseIds", "", "", "Lcn/sast/framework/report/sarif/UriBase;", "results", "", "Lcn/sast/framework/report/sarif/Result;", "translations", "Lcn/sast/framework/report/sarif/TranslationToolComponent;", "<init>", "(Lcn/sast/framework/report/sarif/Tool;Ljava/util/Map;Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/framework/report/sarif/Tool;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTool", "()Lcn/sast/framework/report/sarif/Tool;", "getOriginalUriBaseIds", "()Ljava/util/Map;", "getResults", "()Ljava/util/List;", "getTranslations", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Run.class */
public final class Run {

    @NotNull
    private final Tool tool;

    @NotNull
    private final Map<String, UriBase> originalUriBaseIds;

    @NotNull
    private final List<Result> results;

    @NotNull
    private final List<TranslationToolComponent> translations;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, UriBase$$serializer.INSTANCE), new ArrayListSerializer(Result$$serializer.INSTANCE), new ArrayListSerializer(TranslationToolComponent$$serializer.INSTANCE)};

    @NotNull
    public final Tool component1() {
        return this.tool;
    }

    @NotNull
    public final Map<String, UriBase> component2() {
        return this.originalUriBaseIds;
    }

    @NotNull
    public final List<Result> component3() {
        return this.results;
    }

    @NotNull
    public final List<TranslationToolComponent> component4() {
        return this.translations;
    }

    @NotNull
    public final Run copy(@NotNull Tool tool, @NotNull Map<String, UriBase> map, @NotNull List<Result> list, @NotNull List<TranslationToolComponent> list2) {
        Intrinsics.checkNotNullParameter(tool, "tool");
        Intrinsics.checkNotNullParameter(map, "originalUriBaseIds");
        Intrinsics.checkNotNullParameter(list, "results");
        Intrinsics.checkNotNullParameter(list2, "translations");
        return new Run(tool, map, list, list2);
    }

    public static /* synthetic */ Run copy$default(Run run, Tool tool, Map map, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            tool = run.tool;
        }
        if ((i & 2) != 0) {
            map = run.originalUriBaseIds;
        }
        if ((i & 4) != 0) {
            list = run.results;
        }
        if ((i & 8) != 0) {
            list2 = run.translations;
        }
        return run.copy(tool, map, list, list2);
    }

    @NotNull
    public String toString() {
        return "Run(tool=" + this.tool + ", originalUriBaseIds=" + this.originalUriBaseIds + ", results=" + this.results + ", translations=" + this.translations + ")";
    }

    public int hashCode() {
        int result = this.tool.hashCode();
        return (((((result * 31) + this.originalUriBaseIds.hashCode()) * 31) + this.results.hashCode()) * 31) + this.translations.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Run)) {
            return false;
        }
        Run run = (Run) other;
        return Intrinsics.areEqual(this.tool, run.tool) && Intrinsics.areEqual(this.originalUriBaseIds, run.originalUriBaseIds) && Intrinsics.areEqual(this.results, run.results) && Intrinsics.areEqual(this.translations, run.translations);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/Run$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/Run;", "corax-framework"})
    /* loaded from: Run$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Run> serializer() {
            return Run$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Run self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, Tool$$serializer.INSTANCE, self.tool);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.originalUriBaseIds, MapsKt.emptyMap());
        if (z) {
            output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.originalUriBaseIds);
        }
        output.encodeSerializableElement(serialDesc, 2, serializationStrategyArr[2], self.results);
        output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.translations);
    }

    public /* synthetic */ Run(int seen0, Tool tool, Map originalUriBaseIds, List results, List translations, SerializationConstructorMarker serializationConstructorMarker) {
        if (13 != (13 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 13, Run$$serializer.INSTANCE.getDescriptor());
        }
        this.tool = tool;
        if ((seen0 & 2) == 0) {
            this.originalUriBaseIds = MapsKt.emptyMap();
        } else {
            this.originalUriBaseIds = originalUriBaseIds;
        }
        this.results = results;
        this.translations = translations;
    }

    public Run(@NotNull Tool tool, @NotNull Map<String, UriBase> map, @NotNull List<Result> list, @NotNull List<TranslationToolComponent> list2) {
        Intrinsics.checkNotNullParameter(tool, "tool");
        Intrinsics.checkNotNullParameter(map, "originalUriBaseIds");
        Intrinsics.checkNotNullParameter(list, "results");
        Intrinsics.checkNotNullParameter(list2, "translations");
        this.tool = tool;
        this.originalUriBaseIds = map;
        this.results = list;
        this.translations = list2;
    }

    public /* synthetic */ Run(Tool tool, Map map, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tool, (i & 2) != 0 ? MapsKt.emptyMap() : map, list, list2);
    }

    @NotNull
    public final Tool getTool() {
        return this.tool;
    }

    @NotNull
    public final Map<String, UriBase> getOriginalUriBaseIds() {
        return this.originalUriBaseIds;
    }

    @NotNull
    public final List<Result> getResults() {
        return this.results;
    }

    @NotNull
    public final List<TranslationToolComponent> getTranslations() {
        return this.translations;
    }
}
