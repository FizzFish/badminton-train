package cn.sast.api.config;

import cn.sast.common.ResourceImplKt;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreAnalysisConfig.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� 72\u00020\u0001:\u000267BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bBQ\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001e\u001a\u00020\b¢\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u0015\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007HÂ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003JG\u0010)\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010*\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\bHÖ\u0001J%\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020��2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0001¢\u0006\u0002\b5R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\"\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0018\u0010\u0011R\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013R\"\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u001c\u0010\u0011¨\u00068"}, d2 = {"Lcn/sast/api/config/PreAnalysisConfig;", "", "cancelAnalysisInErrorCount", "", "largeFileSize", "largeFileSemaphorePermits", "_fileExtensionToSizeThreshold", "", "", "maximumFileSizeThresholdWarnings", "<init>", "(IIILjava/util/Map;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIILjava/util/Map;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCancelAnalysisInErrorCount$annotations", "()V", "getCancelAnalysisInErrorCount", "()I", "getLargeFileSize$annotations", "getLargeFileSize", "getLargeFileSemaphorePermits$annotations", "getLargeFileSemaphorePermits", "get_fileExtensionToSizeThreshold$annotations", "getMaximumFileSizeThresholdWarnings$annotations", "getMaximumFileSizeThresholdWarnings", "fileExtensionToSizeThreshold", "getFileExtensionToSizeThreshold$annotations", "getSizeThreshold", "extension", "(Ljava/lang/String;)Ljava/lang/Integer;", "fileSizeThresholdExceeded", "", "fileSize", "", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
@SerialName("PreAnalysisConfig")
@SourceDebugExtension({"SMAP\nPreAnalysisConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisConfig.kt\ncn/sast/api/config/PreAnalysisConfig\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n126#2:59\n153#2,2:60\n155#2:66\n153#2,3:68\n1557#3:62\n1628#3,3:63\n1#4:67\n*S KotlinDebug\n*F\n+ 1 PreAnalysisConfig.kt\ncn/sast/api/config/PreAnalysisConfig\n*L\n50#1:59\n50#1:60,2\n50#1:66\n50#1:68,3\n50#1:62\n50#1:63,3\n*E\n"})
/* loaded from: PreAnalysisConfig.class */
public final class PreAnalysisConfig {
    private final int cancelAnalysisInErrorCount;
    private final int largeFileSize;
    private final int largeFileSemaphorePermits;

    @NotNull
    private final Map<String, Integer> _fileExtensionToSizeThreshold;
    private final int maximumFileSizeThresholdWarnings;

    @NotNull
    private final Map<String, Integer> fileExtensionToSizeThreshold;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, IntSerializer.INSTANCE), null};

    @SerialName("cancel_analysis_in_error_count")
    public static /* synthetic */ void getCancelAnalysisInErrorCount$annotations() {
    }

    @SerialName("large_file_size")
    public static /* synthetic */ void getLargeFileSize$annotations() {
    }

    @SerialName("large_file_semaphore_permits")
    public static /* synthetic */ void getLargeFileSemaphorePermits$annotations() {
    }

    @SerialName("file_extension_to_size_threshold")
    private static /* synthetic */ void get_fileExtensionToSizeThreshold$annotations() {
    }

    @SerialName("maximum_file_size_threshold_warnings")
    public static /* synthetic */ void getMaximumFileSizeThresholdWarnings$annotations() {
    }

    @Transient
    private static /* synthetic */ void getFileExtensionToSizeThreshold$annotations() {
    }

    public final int component1() {
        return this.cancelAnalysisInErrorCount;
    }

    public final int component2() {
        return this.largeFileSize;
    }

    public final int component3() {
        return this.largeFileSemaphorePermits;
    }

    private final Map<String, Integer> component4() {
        return this._fileExtensionToSizeThreshold;
    }

    public final int component5() {
        return this.maximumFileSizeThresholdWarnings;
    }

    @NotNull
    public final PreAnalysisConfig copy(int cancelAnalysisInErrorCount, int largeFileSize, int largeFileSemaphorePermits, @NotNull Map<String, Integer> map, int maximumFileSizeThresholdWarnings) {
        Intrinsics.checkNotNullParameter(map, "_fileExtensionToSizeThreshold");
        return new PreAnalysisConfig(cancelAnalysisInErrorCount, largeFileSize, largeFileSemaphorePermits, map, maximumFileSizeThresholdWarnings);
    }

    public static /* synthetic */ PreAnalysisConfig copy$default(PreAnalysisConfig preAnalysisConfig, int i, int i2, int i3, Map map, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = preAnalysisConfig.cancelAnalysisInErrorCount;
        }
        if ((i5 & 2) != 0) {
            i2 = preAnalysisConfig.largeFileSize;
        }
        if ((i5 & 4) != 0) {
            i3 = preAnalysisConfig.largeFileSemaphorePermits;
        }
        if ((i5 & 8) != 0) {
            map = preAnalysisConfig._fileExtensionToSizeThreshold;
        }
        if ((i5 & 16) != 0) {
            i4 = preAnalysisConfig.maximumFileSizeThresholdWarnings;
        }
        return preAnalysisConfig.copy(i, i2, i3, map, i4);
    }

    @NotNull
    public String toString() {
        return "PreAnalysisConfig(cancelAnalysisInErrorCount=" + this.cancelAnalysisInErrorCount + ", largeFileSize=" + this.largeFileSize + ", largeFileSemaphorePermits=" + this.largeFileSemaphorePermits + ", _fileExtensionToSizeThreshold=" + this._fileExtensionToSizeThreshold + ", maximumFileSizeThresholdWarnings=" + this.maximumFileSizeThresholdWarnings + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.cancelAnalysisInErrorCount);
        return (((((((result * 31) + Integer.hashCode(this.largeFileSize)) * 31) + Integer.hashCode(this.largeFileSemaphorePermits)) * 31) + this._fileExtensionToSizeThreshold.hashCode()) * 31) + Integer.hashCode(this.maximumFileSizeThresholdWarnings);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreAnalysisConfig)) {
            return false;
        }
        PreAnalysisConfig preAnalysisConfig = (PreAnalysisConfig) other;
        return this.cancelAnalysisInErrorCount == preAnalysisConfig.cancelAnalysisInErrorCount && this.largeFileSize == preAnalysisConfig.largeFileSize && this.largeFileSemaphorePermits == preAnalysisConfig.largeFileSemaphorePermits && Intrinsics.areEqual(this._fileExtensionToSizeThreshold, preAnalysisConfig._fileExtensionToSizeThreshold) && this.maximumFileSizeThresholdWarnings == preAnalysisConfig.maximumFileSizeThresholdWarnings;
    }

    public PreAnalysisConfig() {
        this(0, 0, 0, (Map) null, 0, 31, (DefaultConstructorMarker) null);
    }

    /* compiled from: PreAnalysisConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/config/PreAnalysisConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/config/PreAnalysisConfig;", "corax-api"})
    /* loaded from: PreAnalysisConfig$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<PreAnalysisConfig> serializer() {
            return PreAnalysisConfig$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(PreAnalysisConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || self.cancelAnalysisInErrorCount != 10;
        if (z) {
            output.encodeIntElement(serialDesc, 0, self.cancelAnalysisInErrorCount);
        }
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 1) || self.largeFileSize != 512000;
        if (z2) {
            output.encodeIntElement(serialDesc, 1, self.largeFileSize);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 2) || self.largeFileSemaphorePermits != 3;
        if (z3) {
            output.encodeIntElement(serialDesc, 2, self.largeFileSemaphorePermits);
        }
        boolean z4 = output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self._fileExtensionToSizeThreshold, MapsKt.mapOf(new Pair[]{TuplesKt.to(CollectionsKt.joinToString$default(ResourceKt.getJavaExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 1048576), TuplesKt.to(CollectionsKt.joinToString$default(ResourceImplKt.getZipExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), -1), TuplesKt.to(CollectionsKt.joinToString$default(CollectionsKt.listOf(new String[]{"html", "htm", "adoc", "gradle", "properties", "config", "cnf", "txt", "json", "xml", "yml", "yaml", "toml", "ini"}), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 5242880), TuplesKt.to("*", 5242880)}));
        if (z4) {
            output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self._fileExtensionToSizeThreshold);
        }
        boolean z5 = output.shouldEncodeElementDefault(serialDesc, 4) || self.maximumFileSizeThresholdWarnings != 20;
        if (z5) {
            output.encodeIntElement(serialDesc, 4, self.maximumFileSizeThresholdWarnings);
        }
    }

    public /* synthetic */ PreAnalysisConfig(int seen0, int cancelAnalysisInErrorCount, int largeFileSize, int largeFileSemaphorePermits, Map _fileExtensionToSizeThreshold, int maximumFileSizeThresholdWarnings, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, PreAnalysisConfig$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.cancelAnalysisInErrorCount = 10;
        } else {
            this.cancelAnalysisInErrorCount = cancelAnalysisInErrorCount;
        }
        if ((seen0 & 2) == 0) {
            this.largeFileSize = 512000;
        } else {
            this.largeFileSize = largeFileSize;
        }
        if ((seen0 & 4) == 0) {
            this.largeFileSemaphorePermits = 3;
        } else {
            this.largeFileSemaphorePermits = largeFileSemaphorePermits;
        }
        if ((seen0 & 8) == 0) {
            this._fileExtensionToSizeThreshold = MapsKt.mapOf(new Pair[]{TuplesKt.to(CollectionsKt.joinToString$default(ResourceKt.getJavaExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 1048576), TuplesKt.to(CollectionsKt.joinToString$default(ResourceImplKt.getZipExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), -1), TuplesKt.to(CollectionsKt.joinToString$default(CollectionsKt.listOf(new String[]{"html", "htm", "adoc", "gradle", "properties", "config", "cnf", "txt", "json", "xml", "yml", "yaml", "toml", "ini"}), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 5242880), TuplesKt.to("*", 5242880)});
        } else {
            this._fileExtensionToSizeThreshold = _fileExtensionToSizeThreshold;
        }
        if ((seen0 & 16) == 0) {
            this.maximumFileSizeThresholdWarnings = 20;
        } else {
            this.maximumFileSizeThresholdWarnings = maximumFileSizeThresholdWarnings;
        }
        Map $this$map$iv = this._fileExtensionToSizeThreshold;
        Collection destination$iv$iv = new ArrayList($this$map$iv.size());
        for (Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
            String k = item$iv$iv.getKey();
            int v = item$iv$iv.getValue().intValue();
            Iterable<String> $this$map$iv2 = StringsKt.split$default(k, new String[]{","}, false, 0, 6, (Object) null);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (String it : $this$map$iv2) {
                destination$iv$iv2.add(TuplesKt.to(it, Integer.valueOf(v)));
            }
            destination$iv$iv.add((List) destination$iv$iv2);
        }
        this.fileExtensionToSizeThreshold = MapsKt.toMap(CollectionsKt.flatten((List) destination$iv$iv));
    }

    public PreAnalysisConfig(int cancelAnalysisInErrorCount, int largeFileSize, int largeFileSemaphorePermits, @NotNull Map<String, Integer> map, int maximumFileSizeThresholdWarnings) {
        Intrinsics.checkNotNullParameter(map, "_fileExtensionToSizeThreshold");
        this.cancelAnalysisInErrorCount = cancelAnalysisInErrorCount;
        this.largeFileSize = largeFileSize;
        this.largeFileSemaphorePermits = largeFileSemaphorePermits;
        this._fileExtensionToSizeThreshold = map;
        this.maximumFileSizeThresholdWarnings = maximumFileSizeThresholdWarnings;
        Map $this$map$iv = this._fileExtensionToSizeThreshold;
        Collection destination$iv$iv = new ArrayList($this$map$iv.size());
        for (Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
            String k = item$iv$iv.getKey();
            int v = item$iv$iv.getValue().intValue();
            Iterable<String> $this$map$iv2 = StringsKt.split$default(k, new String[]{","}, false, 0, 6, (Object) null);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (String it : $this$map$iv2) {
                destination$iv$iv2.add(TuplesKt.to(it, Integer.valueOf(v)));
            }
            destination$iv$iv.add((List) destination$iv$iv2);
        }
        this.fileExtensionToSizeThreshold = MapsKt.toMap(CollectionsKt.flatten((List) destination$iv$iv));
    }

    public /* synthetic */ PreAnalysisConfig(int i, int i2, int i3, Map map, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 10 : i, (i5 & 2) != 0 ? 512000 : i2, (i5 & 4) != 0 ? 3 : i3, (i5 & 8) != 0 ? MapsKt.mapOf(new Pair[]{TuplesKt.to(CollectionsKt.joinToString$default(ResourceKt.getJavaExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 1048576), TuplesKt.to(CollectionsKt.joinToString$default(ResourceImplKt.getZipExtensions(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), -1), TuplesKt.to(CollectionsKt.joinToString$default(CollectionsKt.listOf(new String[]{"html", "htm", "adoc", "gradle", "properties", "config", "cnf", "txt", "json", "xml", "yml", "yaml", "toml", "ini"}), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), 5242880), TuplesKt.to("*", 5242880)}) : map, (i5 & 16) != 0 ? 20 : i4);
    }

    public final int getCancelAnalysisInErrorCount() {
        return this.cancelAnalysisInErrorCount;
    }

    public final int getLargeFileSize() {
        return this.largeFileSize;
    }

    public final int getLargeFileSemaphorePermits() {
        return this.largeFileSemaphorePermits;
    }

    public final int getMaximumFileSizeThresholdWarnings() {
        return this.maximumFileSizeThresholdWarnings;
    }

    @Nullable
    public final Integer getSizeThreshold(@NotNull String extension) {
        Intrinsics.checkNotNullParameter(extension, "extension");
        Integer num = this.fileExtensionToSizeThreshold.get(extension);
        if (num == null) {
            num = this.fileExtensionToSizeThreshold.get("*");
        }
        if (num == null) {
            return null;
        }
        Integer num2 = num;
        int it = num2.intValue();
        if (it > 0) {
            return num2;
        }
        return null;
    }

    public final boolean fileSizeThresholdExceeded(@NotNull String extension, long fileSize) {
        Intrinsics.checkNotNullParameter(extension, "extension");
        Integer sizeThreshold = getSizeThreshold(extension);
        if (sizeThreshold == null) {
            return false;
        }
        int sizeThreshold2 = sizeThreshold.intValue();
        return fileSize > ((long) sizeThreshold2);
    }
}
