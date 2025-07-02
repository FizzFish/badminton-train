package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
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

/* compiled from: BuiltinAnalysisConfig.kt */
@SerialName("BuiltinAnalysisConfig")
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� -2\u00020\u0001:\u0002,-B;\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nBK\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000eJ\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J=\u0010\u001e\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\t\u0010#\u001a\u00020\u0004HÖ\u0001J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020��2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0017¨\u0006."}, d2 = {"Lcn/sast/api/config/BuiltinAnalysisConfig;", "", "unusedDetectorSootSigPatternBlackList", "", "", "unusedDetectorMethodSigBlackList", "maximumUnusedFieldReportsEachClass", "", "maximumUnusedMethodReportsEachClass", "<init>", "(Ljava/util/List;Ljava/util/List;II)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;IILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUnusedDetectorSootSigPatternBlackList$annotations", "()V", "getUnusedDetectorSootSigPatternBlackList", "()Ljava/util/List;", "getUnusedDetectorMethodSigBlackList$annotations", "getUnusedDetectorMethodSigBlackList", "getMaximumUnusedFieldReportsEachClass$annotations", "getMaximumUnusedFieldReportsEachClass", "()I", "getMaximumUnusedMethodReportsEachClass$annotations", "getMaximumUnusedMethodReportsEachClass", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
/* loaded from: BuiltinAnalysisConfig.class */
public final class BuiltinAnalysisConfig {

    @NotNull
    private final List<String> unusedDetectorSootSigPatternBlackList;

    @NotNull
    private final List<String> unusedDetectorMethodSigBlackList;
    private final int maximumUnusedFieldReportsEachClass;
    private final int maximumUnusedMethodReportsEachClass;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(StringSerializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE), null, null};

    @SerialName("unused_detector_soot_sig_pattern_blacklist")
    public static /* synthetic */ void getUnusedDetectorSootSigPatternBlackList$annotations() {
    }

    @SerialName("unused_detector_method_sig_blacklist")
    public static /* synthetic */ void getUnusedDetectorMethodSigBlackList$annotations() {
    }

    @SerialName("maximum_unused_field_reports_each_class")
    public static /* synthetic */ void getMaximumUnusedFieldReportsEachClass$annotations() {
    }

    @SerialName("maximum_unused_method_reports_each_class")
    public static /* synthetic */ void getMaximumUnusedMethodReportsEachClass$annotations() {
    }

    @NotNull
    public final List<String> component1() {
        return this.unusedDetectorSootSigPatternBlackList;
    }

    @NotNull
    public final List<String> component2() {
        return this.unusedDetectorMethodSigBlackList;
    }

    public final int component3() {
        return this.maximumUnusedFieldReportsEachClass;
    }

    public final int component4() {
        return this.maximumUnusedMethodReportsEachClass;
    }

    @NotNull
    public final BuiltinAnalysisConfig copy(@NotNull List<String> list, @NotNull List<String> list2, int maximumUnusedFieldReportsEachClass, int maximumUnusedMethodReportsEachClass) {
        Intrinsics.checkNotNullParameter(list, "unusedDetectorSootSigPatternBlackList");
        Intrinsics.checkNotNullParameter(list2, "unusedDetectorMethodSigBlackList");
        return new BuiltinAnalysisConfig(list, list2, maximumUnusedFieldReportsEachClass, maximumUnusedMethodReportsEachClass);
    }

    public static /* synthetic */ BuiltinAnalysisConfig copy$default(BuiltinAnalysisConfig builtinAnalysisConfig, List list, List list2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = builtinAnalysisConfig.unusedDetectorSootSigPatternBlackList;
        }
        if ((i3 & 2) != 0) {
            list2 = builtinAnalysisConfig.unusedDetectorMethodSigBlackList;
        }
        if ((i3 & 4) != 0) {
            i = builtinAnalysisConfig.maximumUnusedFieldReportsEachClass;
        }
        if ((i3 & 8) != 0) {
            i2 = builtinAnalysisConfig.maximumUnusedMethodReportsEachClass;
        }
        return builtinAnalysisConfig.copy(list, list2, i, i2);
    }

    @NotNull
    public String toString() {
        return "BuiltinAnalysisConfig(unusedDetectorSootSigPatternBlackList=" + this.unusedDetectorSootSigPatternBlackList + ", unusedDetectorMethodSigBlackList=" + this.unusedDetectorMethodSigBlackList + ", maximumUnusedFieldReportsEachClass=" + this.maximumUnusedFieldReportsEachClass + ", maximumUnusedMethodReportsEachClass=" + this.maximumUnusedMethodReportsEachClass + ")";
    }

    public int hashCode() {
        int result = this.unusedDetectorSootSigPatternBlackList.hashCode();
        return (((((result * 31) + this.unusedDetectorMethodSigBlackList.hashCode()) * 31) + Integer.hashCode(this.maximumUnusedFieldReportsEachClass)) * 31) + Integer.hashCode(this.maximumUnusedMethodReportsEachClass);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltinAnalysisConfig)) {
            return false;
        }
        BuiltinAnalysisConfig builtinAnalysisConfig = (BuiltinAnalysisConfig) other;
        return Intrinsics.areEqual(this.unusedDetectorSootSigPatternBlackList, builtinAnalysisConfig.unusedDetectorSootSigPatternBlackList) && Intrinsics.areEqual(this.unusedDetectorMethodSigBlackList, builtinAnalysisConfig.unusedDetectorMethodSigBlackList) && this.maximumUnusedFieldReportsEachClass == builtinAnalysisConfig.maximumUnusedFieldReportsEachClass && this.maximumUnusedMethodReportsEachClass == builtinAnalysisConfig.maximumUnusedMethodReportsEachClass;
    }

    public BuiltinAnalysisConfig() {
        this((List) null, (List) null, 0, 0, 15, (DefaultConstructorMarker) null);
    }

    /* compiled from: BuiltinAnalysisConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/config/BuiltinAnalysisConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/config/BuiltinAnalysisConfig;", "corax-api"})
    /* loaded from: BuiltinAnalysisConfig$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<BuiltinAnalysisConfig> serializer() {
            return BuiltinAnalysisConfig$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(BuiltinAnalysisConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.unusedDetectorSootSigPatternBlackList, CollectionsKt.listOf(new String[]{"<.*: void start\\(.*BundleContext\\)>", "<.*: void stop\\(.*BundleContext\\)>", "<.*\\.R(\\$.*)?: .*>"}));
        if (z) {
            output.encodeSerializableElement(serialDesc, 0, serializationStrategyArr[0], self.unusedDetectorSootSigPatternBlackList);
        }
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.unusedDetectorMethodSigBlackList, CollectionsKt.listOf(new String[]{"org.osgi.framework.BundleActivator: void start(BundleContext context)", "org.osgi.framework.BundleActivator: void stop(BundleContext context)"}));
        if (z2) {
            output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.unusedDetectorMethodSigBlackList);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 2) || self.maximumUnusedFieldReportsEachClass != 10;
        if (z3) {
            output.encodeIntElement(serialDesc, 2, self.maximumUnusedFieldReportsEachClass);
        }
        boolean z4 = output.shouldEncodeElementDefault(serialDesc, 3) || self.maximumUnusedMethodReportsEachClass != 40;
        if (z4) {
            output.encodeIntElement(serialDesc, 3, self.maximumUnusedMethodReportsEachClass);
        }
    }

    public /* synthetic */ BuiltinAnalysisConfig(int seen0, List unusedDetectorSootSigPatternBlackList, List unusedDetectorMethodSigBlackList, int maximumUnusedFieldReportsEachClass, int maximumUnusedMethodReportsEachClass, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, BuiltinAnalysisConfig$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.unusedDetectorSootSigPatternBlackList = CollectionsKt.listOf(new String[]{"<.*: void start\\(.*BundleContext\\)>", "<.*: void stop\\(.*BundleContext\\)>", "<.*\\.R(\\$.*)?: .*>"});
        } else {
            this.unusedDetectorSootSigPatternBlackList = unusedDetectorSootSigPatternBlackList;
        }
        if ((seen0 & 2) == 0) {
            this.unusedDetectorMethodSigBlackList = CollectionsKt.listOf(new String[]{"org.osgi.framework.BundleActivator: void start(BundleContext context)", "org.osgi.framework.BundleActivator: void stop(BundleContext context)"});
        } else {
            this.unusedDetectorMethodSigBlackList = unusedDetectorMethodSigBlackList;
        }
        if ((seen0 & 4) == 0) {
            this.maximumUnusedFieldReportsEachClass = 10;
        } else {
            this.maximumUnusedFieldReportsEachClass = maximumUnusedFieldReportsEachClass;
        }
        if ((seen0 & 8) == 0) {
            this.maximumUnusedMethodReportsEachClass = 40;
        } else {
            this.maximumUnusedMethodReportsEachClass = maximumUnusedMethodReportsEachClass;
        }
    }

    public BuiltinAnalysisConfig(@NotNull List<String> list, @NotNull List<String> list2, int maximumUnusedFieldReportsEachClass, int maximumUnusedMethodReportsEachClass) {
        Intrinsics.checkNotNullParameter(list, "unusedDetectorSootSigPatternBlackList");
        Intrinsics.checkNotNullParameter(list2, "unusedDetectorMethodSigBlackList");
        this.unusedDetectorSootSigPatternBlackList = list;
        this.unusedDetectorMethodSigBlackList = list2;
        this.maximumUnusedFieldReportsEachClass = maximumUnusedFieldReportsEachClass;
        this.maximumUnusedMethodReportsEachClass = maximumUnusedMethodReportsEachClass;
    }

    public /* synthetic */ BuiltinAnalysisConfig(List list, List list2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? CollectionsKt.listOf(new String[]{"<.*: void start\\(.*BundleContext\\)>", "<.*: void stop\\(.*BundleContext\\)>", "<.*\\.R(\\$.*)?: .*>"}) : list, (i3 & 2) != 0 ? CollectionsKt.listOf(new String[]{"org.osgi.framework.BundleActivator: void start(BundleContext context)", "org.osgi.framework.BundleActivator: void stop(BundleContext context)"}) : list2, (i3 & 4) != 0 ? 10 : i, (i3 & 8) != 0 ? 40 : i2);
    }

    @NotNull
    public final List<String> getUnusedDetectorSootSigPatternBlackList() {
        return this.unusedDetectorSootSigPatternBlackList;
    }

    @NotNull
    public final List<String> getUnusedDetectorMethodSigBlackList() {
        return this.unusedDetectorMethodSigBlackList;
    }

    public final int getMaximumUnusedFieldReportsEachClass() {
        return this.maximumUnusedFieldReportsEachClass;
    }

    public final int getMaximumUnusedMethodReportsEachClass() {
        return this.maximumUnusedMethodReportsEachClass;
    }
}
