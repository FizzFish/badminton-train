package cn.sast.framework.plugin;

import cn.sast.api.config.BuiltinAnalysisConfig;
import cn.sast.api.config.BuiltinAnalysisConfig$$serializer;
import cn.sast.api.config.MainConfigKt;
import cn.sast.api.config.PreAnalysisConfig;
import cn.sast.api.config.PreAnalysisConfig$$serializer;
import cn.sast.api.config.SaConfig;
import cn.sast.api.util.Kotlin_extKt;
import cn.sast.common.GLB;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.CheckersConfig;
import cn.sast.framework.plugin.PluginDefinitions;
import com.charleskorn.kaml.Yaml;
import com.feysh.corax.config.api.AIAnalysisUnit;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.ISootInitializeHandler;
import com.feysh.corax.config.api.PreAnalysisUnit;
import com.feysh.corax.config.api.SAOptions;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Main;
import soot.Scene;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;

/* compiled from: SAConfiguration.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018�� H2\u00020\u0001:\u0004FGHIBG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0004\b\r\u0010\u000eB[\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\u0007\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\r\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J+\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\u0004\b��\u0010\u0018*\u0006\u0012\u0002\b\u00030\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0002¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u0019H\u0002J+\u0010\u001f\u001a\u0004\u0018\u0001H\u0018\"\u0004\b��\u0010\u0018*\u0006\u0012\u0002\b\u00030\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0002¢\u0006\u0002\u0010\u001cJ\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0018\u0010&\u001a\u00020'2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0018\u00104\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\bJ\t\u00106\u001a\u00020\u0003HÂ\u0003J\t\u00107\u001a\u00020\u0005HÂ\u0003J\u001b\u00108\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007HÂ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\f0\tHÂ\u0003JI\u0010:\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0001J\u0013\u0010;\u001a\u00020\u00162\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0010HÖ\u0001J\t\u0010>\u001a\u00020\bHÖ\u0001J%\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020��2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0001¢\u0006\u0002\bER\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u000e¢\u0006\u0002\n��R&\u0010.\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u0004\u0012\u00020\u001e0/8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b0\u00101R&\u00102\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u0004\u0012\u00020\u001e0/8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b3\u00101¨\u0006J"}, d2 = {"Lcn/sast/framework/plugin/SAConfiguration;", "", "builtinAnalysisConfig", "Lcn/sast/api/config/BuiltinAnalysisConfig;", "preAnalysisConfig", "Lcn/sast/api/config/PreAnalysisConfig;", "configurations", "Ljava/util/LinkedHashMap;", "", "Ljava/util/LinkedHashSet;", "Lcn/sast/framework/plugin/ConfigSerializable;", "checkers", "Lcn/sast/framework/plugin/CheckersConfig;", "<init>", "(Lcn/sast/api/config/BuiltinAnalysisConfig;Lcn/sast/api/config/PreAnalysisConfig;Ljava/util/LinkedHashMap;Ljava/util/LinkedHashSet;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/api/config/BuiltinAnalysisConfig;Lcn/sast/api/config/PreAnalysisConfig;Ljava/util/LinkedHashMap;Ljava/util/LinkedHashSet;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "linkedHashCode", "sort", "", "getInstance", "T", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "clz", "Ljava/lang/Class;", "(Lcn/sast/framework/plugin/PluginDefinitions$Definition;Ljava/lang/Class;)Ljava/lang/Object;", "relateConfig", "Lcn/sast/framework/plugin/IConfig;", "get", "getCheckers", "Lcn/sast/framework/plugin/SAConfiguration$EnablesConfig;", "defs", "Lcn/sast/framework/plugin/PluginDefinitions;", "checkerFilter", "Lcn/sast/framework/plugin/CheckerFilterByName;", "filter", "Lcn/sast/api/config/SaConfig;", "serialize", "", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "out", "Lcn/sast/common/IResFile;", "relatedMap", "Ljava/util/IdentityHashMap;", "getRelatedMap$annotations", "()V", "disabled", "getDisabled$annotations", "supplementAndMerge", "ymlPath", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "EnablesConfig", "Compare", "Companion", "$serializer", "corax-framework"})
@SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,557:1\n126#2:558\n153#2,3:559\n126#2:645\n153#2,3:646\n1557#3:562\n1628#3,3:563\n1261#3,4:566\n1628#3,3:570\n230#3,2:573\n1628#3,3:575\n774#3:578\n865#3,2:579\n2632#3,3:581\n774#3:584\n865#3,2:585\n1611#3,9:587\n1863#3:596\n1864#3:598\n1620#3:599\n1611#3,9:600\n1863#3:609\n1864#3:611\n1620#3:612\n1611#3,9:613\n1863#3:622\n1864#3:624\n1620#3:625\n1611#3,9:626\n1863#3:635\n1864#3:637\n1620#3:638\n1279#3,2:639\n1293#3,4:641\n1557#3:649\n1628#3,3:650\n1#4:597\n1#4:610\n1#4:623\n1#4:636\n1#4:660\n487#5,7:653\n*S KotlinDebug\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration\n*L\n195#1:558\n195#1:559,3\n289#1:645\n289#1:646,3\n196#1:562\n196#1:563,3\n203#1:566,4\n204#1:570,3\n245#1:573,2\n262#1:575,3\n263#1:578\n263#1:579,2\n266#1:581,3\n270#1:584\n270#1:585,2\n274#1:587,9\n274#1:596\n274#1:598\n274#1:599\n275#1:600,9\n275#1:609\n275#1:611\n275#1:612\n276#1:613,9\n276#1:622\n276#1:624\n276#1:625\n277#1:626,9\n277#1:635\n277#1:637\n277#1:638\n288#1:639,2\n288#1:641,4\n336#1:649\n336#1:650,3\n274#1:597\n275#1:610\n276#1:623\n277#1:636\n418#1:653,7\n*E\n"})
/* loaded from: SAConfiguration.class */
public final class SAConfiguration {

    @NotNull
    private BuiltinAnalysisConfig builtinAnalysisConfig;

    @NotNull
    private PreAnalysisConfig preAnalysisConfig;

    @NotNull
    private LinkedHashMap<String, LinkedHashSet<ConfigSerializable>> configurations;

    @NotNull
    private LinkedHashSet<CheckersConfig> checkers;

    @NotNull
    private final IdentityHashMap<PluginDefinitions.Definition<?>, IConfig> relatedMap;

    @NotNull
    private final IdentityHashMap<PluginDefinitions.Definition<?>, IConfig> disabled;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new LinkedHashSetSerializer(ConfigSerializable.Companion.serializer())), new LinkedHashSetSerializer(CheckersConfig$$serializer.INSTANCE)};

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SAConfiguration::logger$lambda$31);

    @Transient
    private static /* synthetic */ void getRelatedMap$annotations() {
    }

    @Transient
    private static /* synthetic */ void getDisabled$annotations() {
    }

    private final BuiltinAnalysisConfig component1() {
        return this.builtinAnalysisConfig;
    }

    private final PreAnalysisConfig component2() {
        return this.preAnalysisConfig;
    }

    private final LinkedHashMap<String, LinkedHashSet<ConfigSerializable>> component3() {
        return this.configurations;
    }

    private final LinkedHashSet<CheckersConfig> component4() {
        return this.checkers;
    }

    @NotNull
    public final SAConfiguration copy(@NotNull BuiltinAnalysisConfig builtinAnalysisConfig, @NotNull PreAnalysisConfig preAnalysisConfig, @NotNull LinkedHashMap<String, LinkedHashSet<ConfigSerializable>> linkedHashMap, @NotNull LinkedHashSet<CheckersConfig> linkedHashSet) {
        Intrinsics.checkNotNullParameter(builtinAnalysisConfig, "builtinAnalysisConfig");
        Intrinsics.checkNotNullParameter(preAnalysisConfig, "preAnalysisConfig");
        Intrinsics.checkNotNullParameter(linkedHashMap, "configurations");
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkers");
        return new SAConfiguration(builtinAnalysisConfig, preAnalysisConfig, linkedHashMap, linkedHashSet);
    }

    public static /* synthetic */ SAConfiguration copy$default(SAConfiguration sAConfiguration, BuiltinAnalysisConfig builtinAnalysisConfig, PreAnalysisConfig preAnalysisConfig, LinkedHashMap linkedHashMap, LinkedHashSet linkedHashSet, int i, Object obj) {
        if ((i & 1) != 0) {
            builtinAnalysisConfig = sAConfiguration.builtinAnalysisConfig;
        }
        if ((i & 2) != 0) {
            preAnalysisConfig = sAConfiguration.preAnalysisConfig;
        }
        if ((i & 4) != 0) {
            linkedHashMap = sAConfiguration.configurations;
        }
        if ((i & 8) != 0) {
            linkedHashSet = sAConfiguration.checkers;
        }
        return sAConfiguration.copy(builtinAnalysisConfig, preAnalysisConfig, linkedHashMap, linkedHashSet);
    }

    @NotNull
    public String toString() {
        return "SAConfiguration(builtinAnalysisConfig=" + this.builtinAnalysisConfig + ", preAnalysisConfig=" + this.preAnalysisConfig + ", configurations=" + this.configurations + ", checkers=" + this.checkers + ")";
    }

    public int hashCode() {
        int result = this.builtinAnalysisConfig.hashCode();
        return (((((result * 31) + this.preAnalysisConfig.hashCode()) * 31) + this.configurations.hashCode()) * 31) + this.checkers.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SAConfiguration)) {
            return false;
        }
        SAConfiguration sAConfiguration = (SAConfiguration) other;
        return Intrinsics.areEqual(this.builtinAnalysisConfig, sAConfiguration.builtinAnalysisConfig) && Intrinsics.areEqual(this.preAnalysisConfig, sAConfiguration.preAnalysisConfig) && Intrinsics.areEqual(this.configurations, sAConfiguration.configurations) && Intrinsics.areEqual(this.checkers, sAConfiguration.checkers);
    }

    public SAConfiguration() {
        this((BuiltinAnalysisConfig) null, (PreAnalysisConfig) null, (LinkedHashMap) null, (LinkedHashSet) null, 15, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(SAConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.builtinAnalysisConfig, new BuiltinAnalysisConfig((List) null, (List) null, 0, 0, 15, (DefaultConstructorMarker) null));
        if (z) {
            output.encodeSerializableElement(serialDesc, 0, BuiltinAnalysisConfig$$serializer.INSTANCE, self.builtinAnalysisConfig);
        }
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.preAnalysisConfig, new PreAnalysisConfig(0, 0, 0, (Map) null, 0, 31, (DefaultConstructorMarker) null));
        if (z2) {
            output.encodeSerializableElement(serialDesc, 1, PreAnalysisConfig$$serializer.INSTANCE, self.preAnalysisConfig);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.configurations, new LinkedHashMap());
        if (z3) {
            output.encodeSerializableElement(serialDesc, 2, serializationStrategyArr[2], self.configurations);
        }
        boolean z4 = output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.checkers, new LinkedHashSet());
        if (z4) {
            output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.checkers);
        }
    }

    public /* synthetic */ SAConfiguration(int seen0, BuiltinAnalysisConfig builtinAnalysisConfig, PreAnalysisConfig preAnalysisConfig, LinkedHashMap configurations, LinkedHashSet checkers, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, SAConfiguration$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.builtinAnalysisConfig = new BuiltinAnalysisConfig((List) null, (List) null, 0, 0, 15, (DefaultConstructorMarker) null);
        } else {
            this.builtinAnalysisConfig = builtinAnalysisConfig;
        }
        if ((seen0 & 2) == 0) {
            this.preAnalysisConfig = new PreAnalysisConfig(0, 0, 0, (Map) null, 0, 31, (DefaultConstructorMarker) null);
        } else {
            this.preAnalysisConfig = preAnalysisConfig;
        }
        if ((seen0 & 4) == 0) {
            this.configurations = new LinkedHashMap<>();
        } else {
            this.configurations = configurations;
        }
        if ((seen0 & 8) == 0) {
            this.checkers = new LinkedHashSet<>();
        } else {
            this.checkers = checkers;
        }
        this.relatedMap = new IdentityHashMap<>();
        this.disabled = new IdentityHashMap<>();
    }

    public SAConfiguration(@NotNull BuiltinAnalysisConfig builtinAnalysisConfig, @NotNull PreAnalysisConfig preAnalysisConfig, @NotNull LinkedHashMap<String, LinkedHashSet<ConfigSerializable>> linkedHashMap, @NotNull LinkedHashSet<CheckersConfig> linkedHashSet) {
        Intrinsics.checkNotNullParameter(builtinAnalysisConfig, "builtinAnalysisConfig");
        Intrinsics.checkNotNullParameter(preAnalysisConfig, "preAnalysisConfig");
        Intrinsics.checkNotNullParameter(linkedHashMap, "configurations");
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkers");
        this.builtinAnalysisConfig = builtinAnalysisConfig;
        this.preAnalysisConfig = preAnalysisConfig;
        this.configurations = linkedHashMap;
        this.checkers = linkedHashSet;
        this.relatedMap = new IdentityHashMap<>();
        this.disabled = new IdentityHashMap<>();
    }

    public /* synthetic */ SAConfiguration(BuiltinAnalysisConfig builtinAnalysisConfig, PreAnalysisConfig preAnalysisConfig, LinkedHashMap linkedHashMap, LinkedHashSet linkedHashSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new BuiltinAnalysisConfig((List) null, (List) null, 0, 0, 15, (DefaultConstructorMarker) null) : builtinAnalysisConfig, (i & 2) != 0 ? new PreAnalysisConfig(0, 0, 0, (Map) null, 0, 31, (DefaultConstructorMarker) null) : preAnalysisConfig, (i & 4) != 0 ? new LinkedHashMap() : linkedHashMap, (i & 8) != 0 ? new LinkedHashSet() : linkedHashSet);
    }

    private final int linkedHashCode() {
        List hash = new ArrayList();
        List list = hash;
        Map $this$map$iv = this.configurations;
        Collection destination$iv$iv = new ArrayList($this$map$iv.size());
        for (Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
            destination$iv$iv.add(CollectionsKt.toList(item$iv$iv.getValue()));
        }
        list.add(Integer.valueOf(((List) destination$iv$iv).hashCode()));
        List list2 = hash;
        Iterable $this$map$iv2 = CollectionsKt.toList(this.checkers);
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            CheckersConfig it = (CheckersConfig) item$iv$iv2;
            destination$iv$iv2.add(CollectionsKt.toList(it.getCheckTypes()));
        }
        list2.add(Integer.valueOf(((List) destination$iv$iv2).hashCode()));
        return hash.hashCode();
    }

    public final boolean sort() {
        int old = linkedHashCode();
        Iterable $this$associateTo$iv = CollectionsKt.sortedWith(MapsKt.toList(this.configurations), new Comparator() { // from class: cn.sast.framework.plugin.SAConfiguration$sort$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it = (Pair) t;
                Pair it2 = (Pair) t2;
                return ComparisonsKt.compareValues((Comparable) it.getFirst(), (Comparable) it2.getFirst());
            }
        });
        Map destination$iv = new LinkedHashMap();
        for (Object element$iv : $this$associateTo$iv) {
            Pair it = (Pair) element$iv;
            Pair pair = TuplesKt.to(it.getFirst(), SAConfigurationKt.getSort((LinkedHashSet) it.getSecond()));
            destination$iv.put(pair.getFirst(), pair.getSecond());
        }
        this.configurations = (LinkedHashMap) destination$iv;
        Iterable $this$mapTo$iv = CollectionsKt.sortedWith(CollectionsKt.toList(this.checkers), new Comparator() { // from class: cn.sast.framework.plugin.SAConfiguration$sort$$inlined$compareBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                CheckersConfig it2 = (CheckersConfig) t;
                CheckersConfig it3 = (CheckersConfig) t2;
                return ComparisonsKt.compareValues(it2, it3);
            }
        });
        Collection destination$iv2 = new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            destination$iv2.add(((CheckersConfig) item$iv).sort());
        }
        this.checkers = (LinkedHashSet) destination$iv2;
        return linkedHashCode() != old;
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B]\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020��H\u0086\u0002J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fHÆ\u0003J_\u0010$\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0012R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0017R&\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006,"}, d2 = {"Lcn/sast/framework/plugin/SAConfiguration$EnablesConfig;", "", "aiAnalysisUnits", "", "Lcom/feysh/corax/config/api/AIAnalysisUnit;", "preAnalysisUnits", "Lcom/feysh/corax/config/api/PreAnalysisUnit;", "sootConfig", "Lcom/feysh/corax/config/api/ISootInitializeHandler;", "checkTypes", "Lcom/feysh/corax/config/api/CheckType;", "def2config", "", "Lcom/feysh/corax/config/api/CheckerUnit;", "Lcn/sast/framework/plugin/IConfig;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;)V", "getAiAnalysisUnits", "()Ljava/util/List;", "getPreAnalysisUnits", "getSootConfig", "getCheckTypes", "setCheckTypes", "(Ljava/util/List;)V", "getDef2config", "()Ljava/util/Map;", "setDef2config", "(Ljava/util/Map;)V", "plusAssign", "", "b", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: SAConfiguration$EnablesConfig.class */
    public static final class EnablesConfig {

        @NotNull
        private final List<AIAnalysisUnit> aiAnalysisUnits;

        @NotNull
        private final List<PreAnalysisUnit> preAnalysisUnits;

        @NotNull
        private final List<ISootInitializeHandler> sootConfig;

        @NotNull
        private List<CheckType> checkTypes;

        @NotNull
        private Map<CheckerUnit, IConfig> def2config;

        @NotNull
        public final List<AIAnalysisUnit> component1() {
            return this.aiAnalysisUnits;
        }

        @NotNull
        public final List<PreAnalysisUnit> component2() {
            return this.preAnalysisUnits;
        }

        @NotNull
        public final List<ISootInitializeHandler> component3() {
            return this.sootConfig;
        }

        @NotNull
        public final List<CheckType> component4() {
            return this.checkTypes;
        }

        @NotNull
        public final Map<CheckerUnit, IConfig> component5() {
            return this.def2config;
        }

        @NotNull
        public final EnablesConfig copy(@NotNull List<AIAnalysisUnit> list, @NotNull List<PreAnalysisUnit> list2, @NotNull List<ISootInitializeHandler> list3, @NotNull List<CheckType> list4, @NotNull Map<CheckerUnit, IConfig> map) {
            Intrinsics.checkNotNullParameter(list, "aiAnalysisUnits");
            Intrinsics.checkNotNullParameter(list2, "preAnalysisUnits");
            Intrinsics.checkNotNullParameter(list3, "sootConfig");
            Intrinsics.checkNotNullParameter(list4, "checkTypes");
            Intrinsics.checkNotNullParameter(map, "def2config");
            return new EnablesConfig(list, list2, list3, list4, map);
        }

        public static /* synthetic */ EnablesConfig copy$default(EnablesConfig enablesConfig, List list, List list2, List list3, List list4, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                list = enablesConfig.aiAnalysisUnits;
            }
            if ((i & 2) != 0) {
                list2 = enablesConfig.preAnalysisUnits;
            }
            if ((i & 4) != 0) {
                list3 = enablesConfig.sootConfig;
            }
            if ((i & 8) != 0) {
                list4 = enablesConfig.checkTypes;
            }
            if ((i & 16) != 0) {
                map = enablesConfig.def2config;
            }
            return enablesConfig.copy(list, list2, list3, list4, map);
        }

        @NotNull
        public String toString() {
            return "EnablesConfig(aiAnalysisUnits=" + this.aiAnalysisUnits + ", preAnalysisUnits=" + this.preAnalysisUnits + ", sootConfig=" + this.sootConfig + ", checkTypes=" + this.checkTypes + ", def2config=" + this.def2config + ")";
        }

        public int hashCode() {
            int result = this.aiAnalysisUnits.hashCode();
            return (((((((result * 31) + this.preAnalysisUnits.hashCode()) * 31) + this.sootConfig.hashCode()) * 31) + this.checkTypes.hashCode()) * 31) + this.def2config.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnablesConfig)) {
                return false;
            }
            EnablesConfig enablesConfig = (EnablesConfig) other;
            return Intrinsics.areEqual(this.aiAnalysisUnits, enablesConfig.aiAnalysisUnits) && Intrinsics.areEqual(this.preAnalysisUnits, enablesConfig.preAnalysisUnits) && Intrinsics.areEqual(this.sootConfig, enablesConfig.sootConfig) && Intrinsics.areEqual(this.checkTypes, enablesConfig.checkTypes) && Intrinsics.areEqual(this.def2config, enablesConfig.def2config);
        }

        public EnablesConfig() {
            this(null, null, null, null, null, 31, null);
        }

        public EnablesConfig(@NotNull List<AIAnalysisUnit> list, @NotNull List<PreAnalysisUnit> list2, @NotNull List<ISootInitializeHandler> list3, @NotNull List<CheckType> list4, @NotNull Map<CheckerUnit, IConfig> map) {
            Intrinsics.checkNotNullParameter(list, "aiAnalysisUnits");
            Intrinsics.checkNotNullParameter(list2, "preAnalysisUnits");
            Intrinsics.checkNotNullParameter(list3, "sootConfig");
            Intrinsics.checkNotNullParameter(list4, "checkTypes");
            Intrinsics.checkNotNullParameter(map, "def2config");
            this.aiAnalysisUnits = list;
            this.preAnalysisUnits = list2;
            this.sootConfig = list3;
            this.checkTypes = list4;
            this.def2config = map;
        }

        public /* synthetic */ EnablesConfig(List list, List list2, List list3, List list4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new ArrayList() : list, (i & 2) != 0 ? new ArrayList() : list2, (i & 4) != 0 ? new ArrayList() : list3, (i & 8) != 0 ? new ArrayList() : list4, (i & 16) != 0 ? new IdentityHashMap() : map);
        }

        @NotNull
        public final List<AIAnalysisUnit> getAiAnalysisUnits() {
            return this.aiAnalysisUnits;
        }

        @NotNull
        public final List<PreAnalysisUnit> getPreAnalysisUnits() {
            return this.preAnalysisUnits;
        }

        @NotNull
        public final List<ISootInitializeHandler> getSootConfig() {
            return this.sootConfig;
        }

        @NotNull
        public final List<CheckType> getCheckTypes() {
            return this.checkTypes;
        }

        public final void setCheckTypes(@NotNull List<CheckType> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.checkTypes = list;
        }

        @NotNull
        public final Map<CheckerUnit, IConfig> getDef2config() {
            return this.def2config;
        }

        public final void setDef2config(@NotNull Map<CheckerUnit, IConfig> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.def2config = map;
        }

        public final void plusAssign(@NotNull EnablesConfig b) {
            Intrinsics.checkNotNullParameter(b, "b");
            CollectionsKt.addAll(this.aiAnalysisUnits, b.aiAnalysisUnits);
            CollectionsKt.addAll(this.preAnalysisUnits, b.preAnalysisUnits);
            CollectionsKt.addAll(this.sootConfig, b.sootConfig);
        }
    }

    private final <T> T getInstance(PluginDefinitions.Definition<?> definition, Class<T> cls) {
        if (cls.isInstance(definition.getSingleton())) {
            return (T) definition.getSingleton();
        }
        return null;
    }

    private final IConfig relateConfig(PluginDefinitions.Definition<?> definition) {
        IConfig iConfig = this.relatedMap.get(definition);
        if (iConfig != null) {
            return iConfig;
        }
        throw new IllegalStateException((definition + " not relate to a config").toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> T get(PluginDefinitions.Definition<?> definition, Class<T> cls) {
        IConfig iConfigRelateConfig = relateConfig(definition);
        IOptional iOptional = iConfigRelateConfig instanceof IOptional ? (IOptional) iConfigRelateConfig : null;
        boolean z = (iOptional == null || iOptional.getEnable()) ? false : true;
        if (z) {
            this.disabled.put(definition, iConfigRelateConfig);
            return null;
        }
        if (definition instanceof PluginDefinitions.CheckTypeDefinition) {
            CheckersConfig checkersConfig = new CheckersConfig(((PluginDefinitions.CheckTypeDefinition) definition).getSingleton().getChecker());
            for (T t : this.checkers) {
                if (Intrinsics.areEqual(((CheckersConfig) t).getName(), checkersConfig.getName())) {
                    if (!((CheckersConfig) t).getEnable()) {
                        return null;
                    }
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        return (T) getInstance(definition, cls);
    }

    private final EnablesConfig getCheckers(PluginDefinitions defs, CheckerFilterByName checkerFilter) {
        boolean z;
        EnablesConfig res = new EnablesConfig(null, null, null, null, null, 31, null);
        Iterable checkerUnits = defs.getCheckerUnitDefinition(CheckerUnit.class);
        Iterable sootInitializeHandlers = defs.getISootInitializeHandlerDefinition(ISootInitializeHandler.class);
        Iterable checkTypes = defs.getCheckTypeDefinition(CheckType.class);
        Set<String> enables = checkerFilter != null ? checkerFilter.getEnables() : null;
        if (checkerFilter != null) {
            enables = checkerFilter.getEnables();
            Set oldNames = checkerFilter.getRenameMap().keySet();
            Iterable $this$mapTo$iv = checkTypes;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object item$iv : $this$mapTo$iv) {
                PluginDefinitions.CheckTypeDefinition it = (PluginDefinitions.CheckTypeDefinition) item$iv;
                destination$iv.add(it.getDefaultConfig().getCheckType());
            }
            Set existsCheckerNames = (Set) destination$iv;
            Set<String> $this$filter$iv = enables;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it2 = (String) element$iv$iv;
                if ((existsCheckerNames.contains(it2) || oldNames.contains(it2)) ? false : true) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List notExistsCheckerNames = (List) destination$iv$iv;
            if (!notExistsCheckerNames.isEmpty()) {
                logger.warn(() -> {
                    return getCheckers$lambda$9(r1);
                });
                Set<String> $this$none$iv = enables;
                if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                    Iterator it3 = $this$none$iv.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z = true;
                            break;
                        }
                        Object element$iv = it3.next();
                        String it4 = (String) element$iv;
                        if (existsCheckerNames.contains(it4)) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    throw new IllegalStateException("No checker type are enabled".toString());
                }
            }
            Set $this$filter$iv2 = existsCheckerNames;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                String it5 = (String) element$iv$iv2;
                if (!enables.contains(it5)) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            List disabledCheckers = (List) destination$iv$iv2;
            logger.debug(() -> {
                return getCheckers$lambda$12(r1);
            });
        }
        List<PreAnalysisUnit> preAnalysisUnits = res.getPreAnalysisUnits();
        Iterable $this$mapNotNull$iv = checkerUnits;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            PluginDefinitions.CheckerUnitDefinition unit = (PluginDefinitions.CheckerUnitDefinition) element$iv$iv$iv;
            PreAnalysisUnit preAnalysisUnit = (PreAnalysisUnit) get(unit, PreAnalysisUnit.class);
            if (preAnalysisUnit != null) {
                destination$iv$iv3.add(preAnalysisUnit);
            }
        }
        preAnalysisUnits.addAll((List) destination$iv$iv3);
        List<AIAnalysisUnit> aiAnalysisUnits = res.getAiAnalysisUnits();
        Iterable $this$mapNotNull$iv2 = checkerUnits;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
            PluginDefinitions.CheckerUnitDefinition unit2 = (PluginDefinitions.CheckerUnitDefinition) element$iv$iv$iv2;
            AIAnalysisUnit aIAnalysisUnit = (AIAnalysisUnit) get(unit2, AIAnalysisUnit.class);
            if (aIAnalysisUnit != null) {
                destination$iv$iv4.add(aIAnalysisUnit);
            }
        }
        aiAnalysisUnits.addAll((List) destination$iv$iv4);
        List<ISootInitializeHandler> sootConfig = res.getSootConfig();
        Iterable $this$mapNotNull$iv3 = sootInitializeHandlers;
        Collection destination$iv$iv5 = new ArrayList();
        for (Object element$iv$iv$iv3 : $this$mapNotNull$iv3) {
            PluginDefinitions.ISootInitializeHandlerDefinition unit3 = (PluginDefinitions.ISootInitializeHandlerDefinition) element$iv$iv$iv3;
            ISootInitializeHandler iSootInitializeHandler = (ISootInitializeHandler) get(unit3, ISootInitializeHandler.class);
            if (iSootInitializeHandler != null) {
                destination$iv$iv5.add(iSootInitializeHandler);
            }
        }
        sootConfig.addAll((List) destination$iv$iv5);
        List<CheckType> checkTypes2 = res.getCheckTypes();
        Iterable $this$mapNotNull$iv4 = checkTypes;
        Collection destination$iv$iv6 = new ArrayList();
        for (Object element$iv$iv$iv4 : $this$mapNotNull$iv4) {
            PluginDefinitions.CheckTypeDefinition unit4 = (PluginDefinitions.CheckTypeDefinition) element$iv$iv$iv4;
            CheckType checkType = enables != null ? enables.contains(unit4.getDefaultConfig().getCheckType()) ? (CheckType) getInstance(unit4, CheckType.class) : null : (CheckType) get(unit4, CheckType.class);
            if (checkType != null) {
                destination$iv$iv6.add(checkType);
            }
        }
        checkTypes2.addAll((List) destination$iv$iv6);
        Iterable $this$associateWith$iv = checkerUnits;
        LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv3 : $this$associateWith$iv) {
            PluginDefinitions.CheckerUnitDefinition unit5 = (PluginDefinitions.CheckerUnitDefinition) element$iv$iv3;
            result$iv.put(element$iv$iv3, (CheckerUnit) get(unit5, CheckerUnit.class));
        }
        Map def2checkerUnit = Kotlin_extKt.nonNullValue(result$iv);
        Map<CheckerUnit, IConfig> def2config = res.getDef2config();
        Collection destination$iv$iv7 = new ArrayList(def2checkerUnit.size());
        for (Map.Entry item$iv$iv : def2checkerUnit.entrySet()) {
            PluginDefinitions.CheckerUnitDefinition def = (PluginDefinitions.CheckerUnitDefinition) item$iv$iv.getKey();
            CheckerUnit unit6 = (CheckerUnit) item$iv$iv.getValue();
            destination$iv$iv7.add(TuplesKt.to(unit6, relateConfig(def)));
        }
        MapsKt.putAll(def2config, (List) destination$iv$iv7);
        return res;
    }

    private static final Object getCheckers$lambda$9(List $notExistsCheckerNames) {
        return "\nThese check types named " + $notExistsCheckerNames + " cannot be found in analysis-config\n";
    }

    private static final Object getCheckers$lambda$12(List $disabledCheckers) {
        return "\nThese check types " + $disabledCheckers + " are not enabled\n";
    }

    @NotNull
    public final SaConfig filter(@NotNull PluginDefinitions defs, @Nullable CheckerFilterByName checkerFilter) {
        ISootInitializeHandler iSootInitializeHandler;
        Intrinsics.checkNotNullParameter(defs, "defs");
        final EnablesConfig enableDefinitions = getCheckers(defs, checkerFilter);
        logger.info(() -> {
            return filter$lambda$19(r1, r2);
        });
        logger.info(() -> {
            return filter$lambda$20(r1, r2);
        });
        logger.info(() -> {
            return filter$lambda$21(r1, r2);
        });
        logger.info(() -> {
            return filter$lambda$22(r1, r2);
        });
        if (enableDefinitions.getSootConfig().size() == 1) {
            iSootInitializeHandler = (ISootInitializeHandler) CollectionsKt.first(enableDefinitions.getSootConfig());
        } else {
            iSootInitializeHandler = new ISootInitializeHandler() { // from class: cn.sast.framework.plugin.SAConfiguration$filter$sootConfigMerge$1
                public void configure(Options options) {
                    Intrinsics.checkNotNullParameter(options, "options");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.configure(options);
                    }
                }

                public void configure(Scene scene) {
                    Intrinsics.checkNotNullParameter(scene, "scene");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.configure(scene);
                    }
                }

                public void configureAfterSceneInit(Scene scene, Options options) {
                    Intrinsics.checkNotNullParameter(scene, "scene");
                    Intrinsics.checkNotNullParameter(options, "options");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.configureAfterSceneInit(scene, options);
                    }
                }

                public void configure(Main main) {
                    Intrinsics.checkNotNullParameter(main, "main");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.configure(main);
                    }
                }

                public void onBeforeCallGraphConstruction(Scene scene, Options options) {
                    Intrinsics.checkNotNullParameter(scene, "scene");
                    Intrinsics.checkNotNullParameter(options, "options");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.onBeforeCallGraphConstruction(scene, options);
                    }
                }

                public void onAfterCallGraphConstruction(CallGraph cg, Scene scene, Options options) {
                    Intrinsics.checkNotNullParameter(cg, "cg");
                    Intrinsics.checkNotNullParameter(scene, "scene");
                    Intrinsics.checkNotNullParameter(options, "options");
                    Iterable $this$forEach$iv = enableDefinitions.getSootConfig();
                    for (Object element$iv : $this$forEach$iv) {
                        ISootInitializeHandler it = (ISootInitializeHandler) element$iv;
                        it.onAfterCallGraphConstruction(cg, scene, options);
                    }
                }

                public String toString() {
                    return "SootConfigMerge-" + enableDefinitions.getSootConfig();
                }
            };
        }
        ISootInitializeHandler sootConfigMerge = iSootInitializeHandler;
        GLB glb = GLB.INSTANCE;
        Iterable $this$map$iv = defs.getCheckTypeDefinition(CheckType.class);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PluginDefinitions.CheckTypeDefinition it = (PluginDefinitions.CheckTypeDefinition) item$iv$iv;
            destination$iv$iv.add(it.getSingleton());
        }
        glb.plusAssign((List) destination$iv$iv);
        return new SaConfig(this.builtinAnalysisConfig, this.preAnalysisConfig, ExtensionsKt.toPersistentSet(CollectionsKt.plus(enableDefinitions.getPreAnalysisUnits(), enableDefinitions.getAiAnalysisUnits())), sootConfigMerge, CollectionsKt.toSet(enableDefinitions.getCheckTypes()));
    }

    private static final Object filter$lambda$19(EnablesConfig $enableDefinitions, PluginDefinitions $defs) {
        return "Num of effective PreAnalysisUnit is " + $enableDefinitions.getPreAnalysisUnits().size() + "/" + $defs.getPreAnalysisUnit(PreAnalysisUnit.class).size();
    }

    private static final Object filter$lambda$20(EnablesConfig $enableDefinitions, PluginDefinitions $defs) {
        return "Num of effective AIAnalysisUnit is " + $enableDefinitions.getAiAnalysisUnits().size() + "/" + $defs.getAIAnalysisUnit(AIAnalysisUnit.class).size();
    }

    private static final Object filter$lambda$21(EnablesConfig $enableDefinitions, PluginDefinitions $defs) {
        return "Num of effective ISootInitializeHandler is " + $enableDefinitions.getSootConfig().size() + "/" + $defs.getISootInitializeHandlerDefinition(ISootInitializeHandler.class).size();
    }

    private static final Object filter$lambda$22(EnablesConfig $enableDefinitions, PluginDefinitions $defs) {
        return "Num of effective CheckType is " + $enableDefinitions.getCheckTypes().size() + "/" + $defs.getCheckTypeDefinition(CheckType.class).size();
    }

    public final void serialize(@NotNull SerializersModule serializersModule, @NotNull IResFile out) {
        Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
        Intrinsics.checkNotNullParameter(out, "out");
        Yaml yml = new Yaml(serializersModule, MainConfigKt.getYamlConfiguration());
        try {
            OpenOption[] openOptionArr = new OpenOption[0];
            OutputStream outputStreamNewOutputStream = Files.newOutputStream(out.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
            OutputStream outputStream = outputStreamNewOutputStream;
            Throwable th = null;
            try {
                try {
                    OutputStream it = outputStream;
                    Yaml.encodeToStream$default(yml, Companion.serializer(), this, it, (Charset) null, 8, (Object) null);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream, (Throwable) null);
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStream, th);
                    throw th2;
                }
            } finally {
            }
        } catch (Exception e) {
            Files.delete(out.getPath());
            throw e;
        }
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\"\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u001e\u0010\f\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH&J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcn/sast/framework/plugin/SAConfiguration$Compare;", "", "self", "Lcn/sast/framework/plugin/SAConfiguration;", "<init>", "(Lcn/sast/framework/plugin/SAConfiguration;)V", "getSelf", "()Lcn/sast/framework/plugin/SAConfiguration;", "existsHandler", "", "Lcn/sast/framework/plugin/IConfig;", "thatConfig", "multipleHandler", "multipleThat", "", "notExistsHandler", "type", "", "miss", "Lcn/sast/framework/plugin/ConfigSerializable;", "checker", "Lcn/sast/framework/plugin/CheckersConfig;", "Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "compare", "that", "corax-framework"})
    @SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration$Compare\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,557:1\n774#2:558\n865#2,2:559\n774#2:561\n865#2,2:562\n1368#2:564\n1454#2,2:565\n774#2:567\n865#2,2:568\n1456#2,3:570\n*S KotlinDebug\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration$Compare\n*L\n373#1:558\n373#1:559,2\n386#1:561\n386#1:562,2\n388#1:564\n388#1:565,2\n388#1:567\n388#1:568,2\n388#1:570,3\n*E\n"})
    /* loaded from: SAConfiguration$Compare.class */
    private static abstract class Compare {

        @NotNull
        private final SAConfiguration self;

        public abstract void multipleHandler(@NotNull IConfig iConfig, @NotNull Collection<? extends IConfig> collection);

        public Compare(@NotNull SAConfiguration self) {
            Intrinsics.checkNotNullParameter(self, "self");
            this.self = self;
        }

        @NotNull
        public final SAConfiguration getSelf() {
            return this.self;
        }

        public void existsHandler(@NotNull IConfig self, @NotNull IConfig thatConfig) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(thatConfig, "thatConfig");
        }

        public void notExistsHandler(@NotNull String type, @NotNull ConfigSerializable miss) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(miss, "miss");
        }

        public void notExistsHandler(@NotNull CheckersConfig checker, @NotNull CheckersConfig.CheckTypeConfig miss) {
            Intrinsics.checkNotNullParameter(checker, "checker");
            Intrinsics.checkNotNullParameter(miss, "miss");
        }

        public final void compare(@NotNull SAConfiguration that) {
            Intrinsics.checkNotNullParameter(that, "that");
            for (Map.Entry configSerializable : this.self.configurations.entrySet()) {
                Iterator it = ((LinkedHashSet) configSerializable.getValue()).iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    Object next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                    ConfigSerializable config = (ConfigSerializable) next;
                    LinkedHashSet linkedHashSet = (LinkedHashSet) that.configurations.get(configSerializable.getKey());
                    Iterable $this$filter$iv = linkedHashSet != null ? linkedHashSet : SetsKt.emptySet();
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        ConfigSerializable it2 = (ConfigSerializable) element$iv$iv;
                        if (Intrinsics.areEqual(it2.getName(), config.getName())) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    List matched = (List) destination$iv$iv;
                    if (!(!matched.isEmpty())) {
                        notExistsHandler((String) configSerializable.getKey(), config);
                    } else if (matched.size() > 1) {
                        multipleHandler(config, matched);
                    } else {
                        existsHandler(config, (IConfig) CollectionsKt.first(matched));
                    }
                }
            }
            Iterator it3 = this.self.checkers.iterator();
            Intrinsics.checkNotNullExpressionValue(it3, "iterator(...)");
            while (it3.hasNext()) {
                Object next2 = it3.next();
                Intrinsics.checkNotNullExpressionValue(next2, "next(...)");
                CheckersConfig checker = (CheckersConfig) next2;
                Iterable $this$filter$iv2 = that.checkers;
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv2) {
                    CheckersConfig it4 = (CheckersConfig) element$iv$iv2;
                    if (Intrinsics.areEqual(it4.getName(), checker.getName())) {
                        destination$iv$iv2.add(element$iv$iv2);
                    }
                }
                Iterable checkerMatches = (List) destination$iv$iv2;
                Iterator<CheckersConfig.CheckTypeConfig> it5 = checker.getCheckTypes().iterator();
                Intrinsics.checkNotNullExpressionValue(it5, "iterator(...)");
                while (it5.hasNext()) {
                    CheckersConfig.CheckTypeConfig next3 = it5.next();
                    Intrinsics.checkNotNullExpressionValue(next3, "next(...)");
                    CheckersConfig.CheckTypeConfig checkTypeConfig = next3;
                    Iterable<CheckersConfig> $this$flatMap$iv = checkerMatches;
                    Collection destination$iv$iv3 = new ArrayList();
                    for (CheckersConfig checkersConfig : $this$flatMap$iv) {
                        Iterable $this$filter$iv3 = checkersConfig.getCheckTypes();
                        Collection destination$iv$iv4 = new ArrayList();
                        for (Object element$iv$iv3 : $this$filter$iv3) {
                            CheckersConfig.CheckTypeConfig it6 = (CheckersConfig.CheckTypeConfig) element$iv$iv3;
                            if (Intrinsics.areEqual(it6.getCheckType(), checkTypeConfig.getCheckType())) {
                                destination$iv$iv4.add(element$iv$iv3);
                            }
                        }
                        Iterable list$iv$iv = (List) destination$iv$iv4;
                        CollectionsKt.addAll(destination$iv$iv3, list$iv$iv);
                    }
                    List matched2 = (List) destination$iv$iv3;
                    if (!(!matched2.isEmpty())) {
                        notExistsHandler(checker, checkTypeConfig);
                    } else if (matched2.size() > 1) {
                        multipleHandler(checkTypeConfig, matched2);
                    } else {
                        existsHandler(checkTypeConfig, (IConfig) CollectionsKt.first(matched2));
                    }
                }
            }
        }
    }

    public final boolean supplementAndMerge(@NotNull final PluginDefinitions defs, @Nullable final String ymlPath) {
        Intrinsics.checkNotNullParameter(defs, "defs");
        int hashOld = hashCode();
        SAConfiguration defaultConfig = Companion.getDefaultConfig(defs);
        final Lazy head = LazyKt.lazy(SAConfiguration::supplementAndMerge$lambda$26);
        new Compare() { // from class: cn.sast.framework.plugin.SAConfiguration.supplementAndMerge.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(SAConfiguration.this);
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void existsHandler(IConfig self, IConfig thatConfig) throws IllegalAccessException, IllegalArgumentException {
                SAOptions options;
                Intrinsics.checkNotNullParameter(self, "self");
                Intrinsics.checkNotNullParameter(thatConfig, "thatConfig");
                Map definitions = SAConfiguration.supplementAndMerge$getAndRelate(defs, SAConfiguration.this, self, thatConfig);
                if ((self instanceof IFieldOptions) && (options = ((IFieldOptions) self).getOptions()) != null) {
                    Iterator it = definitions.entrySet().iterator();
                    while (it.hasNext()) {
                        PluginDefinitions.Definition definition = (PluginDefinitions.Definition) ((Map.Entry) it.next()).getKey();
                        definition.setOptions(options);
                    }
                }
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void multipleHandler(IConfig self, Collection<? extends IConfig> collection) {
                Intrinsics.checkNotNullParameter(self, "self");
                Intrinsics.checkNotNullParameter(collection, "multipleThat");
                throw new IllegalStateException(("Please remove duplicate definitions: " + collection + " from your plugin directory").toString());
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void notExistsHandler(String type, ConfigSerializable miss) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(miss, "miss");
                head.getValue();
                SAConfiguration.logger.warn(() -> {
                    return notExistsHandler$lambda$0(r1, r2);
                });
            }

            private static final Object notExistsHandler$lambda$0(ConfigSerializable $miss, String $type) {
                return "There is a configuration " + $miss.getName() + " of type " + $type + " defined, but there is no corresponding definition in the plugin directory.";
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void notExistsHandler(CheckersConfig checker, CheckersConfig.CheckTypeConfig miss) {
                Intrinsics.checkNotNullParameter(checker, "checker");
                Intrinsics.checkNotNullParameter(miss, "miss");
                head.getValue();
                SAConfiguration.logger.warn(() -> {
                    return notExistsHandler$lambda$1(r1, r2);
                });
            }

            private static final Object notExistsHandler$lambda$1(CheckersConfig.CheckTypeConfig $miss, CheckersConfig $checker) {
                return "There is a configuration " + $miss + " of checker " + $checker.getName() + " defined, but there is no corresponding definition in the plugin directory.";
            }
        }.compare(defaultConfig);
        new Compare(defaultConfig) { // from class: cn.sast.framework.plugin.SAConfiguration.supplementAndMerge.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(defaultConfig);
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void multipleHandler(IConfig self, Collection<? extends IConfig> collection) {
                Intrinsics.checkNotNullParameter(self, "self");
                Intrinsics.checkNotNullParameter(collection, "multipleThat");
                throw new IllegalStateException(("Please remove duplicate configurations: " + collection + " from " + ymlPath).toString());
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void notExistsHandler(String type, ConfigSerializable miss) {
                Object obj;
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(miss, "miss");
                if (ymlPath != null) {
                    head.getValue();
                    KLogger kLogger = SAConfiguration.logger;
                    String str = ymlPath;
                    kLogger.warn(() -> {
                        return notExistsHandler$lambda$0(r1, r2, r3);
                    });
                }
                Map $this$getOrPut$iv = this.configurations;
                Object value$iv = $this$getOrPut$iv.get(type);
                if (value$iv == null) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    $this$getOrPut$iv.put(type, linkedHashSet);
                    obj = linkedHashSet;
                } else {
                    obj = value$iv;
                }
                LinkedHashSet configsInYml = (LinkedHashSet) obj;
                configsInYml.add(miss);
                SAConfiguration.supplementAndMerge$getAndRelate(defs, this, miss, miss);
            }

            private static final Object notExistsHandler$lambda$0(ConfigSerializable $miss, String $type, String $ymlPath) {
                return "There is an definition \"" + $miss + "\" of type " + $type + ", but it is not configured in configuration: " + $ymlPath + ".";
            }

            @Override // cn.sast.framework.plugin.SAConfiguration.Compare
            public void notExistsHandler(CheckersConfig checker, CheckersConfig.CheckTypeConfig miss) {
                Intrinsics.checkNotNullParameter(checker, "checker");
                Intrinsics.checkNotNullParameter(miss, "miss");
                if (ymlPath != null) {
                    head.getValue();
                    KLogger kLogger = SAConfiguration.logger;
                    String str = ymlPath;
                    kLogger.warn(() -> {
                        return notExistsHandler$lambda$2(r1, r2);
                    });
                }
                checker.getCheckTypes().add(miss);
                if (!this.checkers.contains(checker)) {
                    this.checkers.add(checker);
                }
                SAConfiguration.supplementAndMerge$getAndRelate(defs, this, miss, miss);
            }

            private static final Object notExistsHandler$lambda$2(CheckersConfig.CheckTypeConfig $miss, String $ymlPath) {
                return "There is an definition \"" + $miss + "\", but it is not configured in configuration: " + $ymlPath + ".";
            }
        }.compare(this);
        if (head.isInitialized()) {
            logger.warn(SAConfiguration::supplementAndMerge$lambda$30);
        }
        int hashNew = hashCode();
        return hashNew != hashOld;
    }

    private static final Object supplementAndMerge$lambda$26$lambda$25() {
        return "/--------------------- config information view ---------------------";
    }

    private static final Unit supplementAndMerge$lambda$26() {
        logger.warn(SAConfiguration::supplementAndMerge$lambda$26$lambda$25);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<PluginDefinitions.Definition<?>, IConfig> supplementAndMerge$getAndRelate(PluginDefinitions $defs, SAConfiguration this$0, IConfig self, IConfig thatConfig) {
        Map $this$filterKeys$iv = $defs.getDefaultConfigs();
        LinkedHashMap result$iv = new LinkedHashMap();
        for (Map.Entry entry$iv : $this$filterKeys$iv.entrySet()) {
            PluginDefinitions.Definition<?> it = entry$iv.getKey();
            if (it.getDefaultConfig() == thatConfig) {
                result$iv.put(entry$iv.getKey(), entry$iv.getValue());
            }
        }
        LinkedHashMap definitions = result$iv;
        if (!(!definitions.isEmpty())) {
            throw new IllegalStateException(("internal error. empty definition. config: " + self + " and " + thatConfig).toString());
        }
        if (!(definitions.size() == 1)) {
            throw new IllegalStateException(("internal error. multiple definitions: " + definitions + ". config: " + self + " and " + thatConfig).toString());
        }
        this$0.relatedMap.put(CollectionsKt.first(definitions.keySet()), self);
        return definitions;
    }

    private static final Object supplementAndMerge$lambda$30() {
        return "\\--------------------- config information view ---------------------";
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0019\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/plugin/SAConfiguration$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getDefaultConfig", "Lcn/sast/framework/plugin/SAConfiguration;", "defs", "Lcn/sast/framework/plugin/PluginDefinitions;", "deserialize", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "in1", "Lcn/sast/common/IResFile;", "UnitTypeName", "", "Ljava/lang/Class;", "getUnitTypeName", "(Ljava/lang/Class;)Ljava/lang/String;", "serializer", "Lkotlinx/serialization/KSerializer;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,557:1\n774#2:558\n865#2,2:559\n381#3,7:561\n381#3,7:568\n*S KotlinDebug\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration$Companion\n*L\n511#1:558\n511#1:559,2\n523#1:561,7\n527#1:568,7\n*E\n"})
    /* loaded from: SAConfiguration$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<SAConfiguration> serializer() {
            return SAConfiguration$$serializer.INSTANCE;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        @NotNull
        public final SAConfiguration getDefaultConfig(@NotNull PluginDefinitions defs) throws NoWhenBranchMatchedException {
            CheckersConfig checkersConfig;
            Object obj;
            Object obj2;
            Intrinsics.checkNotNullParameter(defs, "defs");
            Map defaultConfigMap = defs.getDefaultConfigs();
            LinkedHashMap configurations = new LinkedHashMap();
            LinkedHashSet checkers = new LinkedHashSet();
            for (Map.Entry<PluginDefinitions.Definition<?>, IConfig> entry : defaultConfigMap.entrySet()) {
                PluginDefinitions.Definition def = entry.getKey();
                IConfig config = entry.getValue();
                if (!Intrinsics.areEqual(config, def.getDefaultConfig())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (def instanceof PluginDefinitions.CheckTypeDefinition) {
                    CheckType checkType = ((PluginDefinitions.CheckTypeDefinition) def).getSingleton();
                    CheckersConfig checkerConfig = new CheckersConfig(checkType.getChecker());
                    LinkedHashSet $this$filter$iv = checkers;
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        CheckersConfig it = (CheckersConfig) element$iv$iv;
                        if (Intrinsics.areEqual(it.getName(), checkerConfig.getName())) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    List existsCheckers = (List) destination$iv$iv;
                    if (existsCheckers.isEmpty()) {
                        checkers.add(checkerConfig);
                        checkersConfig = checkerConfig;
                    } else {
                        if (existsCheckers.size() != 1) {
                            throw new IllegalStateException(("Please fix duplicate IChecker::name of checker definitions: " + checkType.getChecker() + " and " + existsCheckers).toString());
                        }
                        checkersConfig = (CheckersConfig) CollectionsKt.first(existsCheckers);
                    }
                    CheckersConfig checkerConfigExists = checkersConfig;
                    checkerConfigExists.getCheckTypes().add(((PluginDefinitions.CheckTypeDefinition) def).getDefaultConfig());
                } else if (def instanceof PluginDefinitions.CheckerUnitDefinition) {
                    LinkedHashMap $this$getOrPut$iv = configurations;
                    String unitTypeName = getUnitTypeName(((PluginDefinitions.CheckerUnitDefinition) def).getType());
                    Object value$iv = $this$getOrPut$iv.get(unitTypeName);
                    if (value$iv == null) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        $this$getOrPut$iv.put(unitTypeName, linkedHashSet);
                        obj = linkedHashSet;
                    } else {
                        obj = value$iv;
                    }
                    LinkedHashSet configsInYml = (LinkedHashSet) obj;
                    configsInYml.add(((PluginDefinitions.CheckerUnitDefinition) def).getDefaultConfig());
                } else {
                    if (!(def instanceof PluginDefinitions.ISootInitializeHandlerDefinition)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    LinkedHashMap $this$getOrPut$iv2 = configurations;
                    String unitTypeName2 = getUnitTypeName(((PluginDefinitions.ISootInitializeHandlerDefinition) def).getType());
                    Object value$iv2 = $this$getOrPut$iv2.get(unitTypeName2);
                    if (value$iv2 == null) {
                        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                        $this$getOrPut$iv2.put(unitTypeName2, linkedHashSet2);
                        obj2 = linkedHashSet2;
                    } else {
                        obj2 = value$iv2;
                    }
                    LinkedHashSet configsInYml2 = (LinkedHashSet) obj2;
                    configsInYml2.add(((PluginDefinitions.ISootInitializeHandlerDefinition) def).getDefaultConfig());
                }
            }
            return new SAConfiguration((BuiltinAnalysisConfig) null, (PreAnalysisConfig) null, configurations, checkers, 3, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final SAConfiguration deserialize(@NotNull SerializersModule serializersModule, @NotNull IResFile in1) {
            Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
            Intrinsics.checkNotNullParameter(in1, "in1");
            Yaml yml = new Yaml(serializersModule, MainConfigKt.getYamlConfiguration());
            OpenOption[] openOptionArr = new OpenOption[0];
            InputStream inputStreamNewInputStream = Files.newInputStream(in1.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            InputStream inputStream = inputStreamNewInputStream;
            Throwable th = null;
            try {
                try {
                    InputStream it = inputStream;
                    SAConfiguration sAConfiguration = (SAConfiguration) Yaml.decodeFromStream$default(yml, SAConfiguration.Companion.serializer(), it, (Charset) null, 4, (Object) null);
                    CloseableKt.closeFinally(inputStream, (Throwable) null);
                    return sAConfiguration;
                } finally {
                }
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStream, th);
                throw th2;
            }
        }

        @NotNull
        public final String getUnitTypeName(@NotNull Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "<this>");
            String simpleName = cls.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            return simpleName;
        }
    }

    private static final Unit logger$lambda$31() {
        return Unit.INSTANCE;
    }
}
