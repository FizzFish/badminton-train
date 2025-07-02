package cn.sast.api.config;

import cn.sast.api.util.ComparatorUtilsKt;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.charleskorn.kaml.Yaml;
import com.charleskorn.kaml.YamlConfiguration;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
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
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerPriorityConfig.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��d\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� /2\u00020\u0001:\u0002/0B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007B;\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u0018\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016H\u0002J$\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00150\u00150\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0002J2\u0010\u001a\u001a \u0012\u0004\u0012\u00020\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u001b0\u001b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003J(\u0010\u001c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00150\u00150\u001e0\u001d2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010!\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\tHÖ\u0001J\t\u0010&\u001a\u00020\u0004HÖ\u0001J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020��2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010¨\u00061"}, d2 = {"Lcn/sast/api/config/CheckerPriorityConfig;", "", "categoryList", "", "", "severityList", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCategoryList$annotations", "()V", "getCategoryList", "()Ljava/util/List;", "getSeverityList$annotations", "getSeverityList", "getComparator", "Ljava/util/Comparator;", "Lcn/sast/api/config/ChapterFlat;", "Lkotlin/Comparator;", "sort", "kotlin.jvm.PlatformType", "chapters", "getSortTree", "", "getRuleWithSortNumber", "", "Lkotlin/collections/IndexedValue;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "Companion", "$serializer", "corax-api"})
@SourceDebugExtension({"SMAP\nCheckerPriorityConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerPriorityConfig.kt\ncn/sast/api/config/CheckerPriorityConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,52:1\n1187#2,2:53\n1261#2,4:55\n1187#2,2:59\n1261#2,4:61\n1485#2:65\n1510#2,3:66\n1513#2,3:76\n1246#2,2:81\n1485#2:83\n1510#2,3:84\n1513#2,3:94\n1246#2,2:99\n1557#2:101\n1628#2,3:102\n1249#2:105\n1249#2:106\n381#3,7:69\n462#3:79\n412#3:80\n381#3,7:87\n462#3:97\n412#3:98\n*S KotlinDebug\n*F\n+ 1 CheckerPriorityConfig.kt\ncn/sast/api/config/CheckerPriorityConfig\n*L\n16#1:53,2\n16#1:55,4\n17#1:59,2\n17#1:61,4\n31#1:65\n31#1:66,3\n31#1:76,3\n31#1:81,2\n32#1:83\n32#1:84,3\n32#1:94,3\n32#1:99,2\n33#1:101\n33#1:102,3\n32#1:105\n31#1:106\n31#1:69,7\n31#1:79\n31#1:80\n32#1:87,7\n32#1:97\n32#1:98\n*E\n"})
/* loaded from: CheckerPriorityConfig.class */
public final class CheckerPriorityConfig {

    @NotNull
    private final List<String> categoryList;

    @NotNull
    private final List<String> severityList;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(StringSerializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE)};

    @NotNull
    private static final Yaml yamlFormat = new Yaml((SerializersModule) null, (YamlConfiguration) null, 3, (DefaultConstructorMarker) null);

    @SerialName("category")
    public static /* synthetic */ void getCategoryList$annotations() {
    }

    @SerialName("severity")
    public static /* synthetic */ void getSeverityList$annotations() {
    }

    @NotNull
    public final List<String> component1() {
        return this.categoryList;
    }

    @NotNull
    public final List<String> component2() {
        return this.severityList;
    }

    @NotNull
    public final CheckerPriorityConfig copy(@NotNull List<String> list, @NotNull List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "categoryList");
        Intrinsics.checkNotNullParameter(list2, "severityList");
        return new CheckerPriorityConfig(list, list2);
    }

    public static /* synthetic */ CheckerPriorityConfig copy$default(CheckerPriorityConfig checkerPriorityConfig, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = checkerPriorityConfig.categoryList;
        }
        if ((i & 2) != 0) {
            list2 = checkerPriorityConfig.severityList;
        }
        return checkerPriorityConfig.copy(list, list2);
    }

    @NotNull
    public String toString() {
        return "CheckerPriorityConfig(categoryList=" + this.categoryList + ", severityList=" + this.severityList + ")";
    }

    public int hashCode() {
        int result = this.categoryList.hashCode();
        return (result * 31) + this.severityList.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckerPriorityConfig)) {
            return false;
        }
        CheckerPriorityConfig checkerPriorityConfig = (CheckerPriorityConfig) other;
        return Intrinsics.areEqual(this.categoryList, checkerPriorityConfig.categoryList) && Intrinsics.areEqual(this.severityList, checkerPriorityConfig.severityList);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(CheckerPriorityConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, serializationStrategyArr[0], self.categoryList);
        output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.severityList);
    }

    public /* synthetic */ CheckerPriorityConfig(int seen0, List categoryList, List severityList, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 3, CheckerPriorityConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.categoryList = categoryList;
        this.severityList = severityList;
    }

    public CheckerPriorityConfig(@NotNull List<String> list, @NotNull List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "categoryList");
        Intrinsics.checkNotNullParameter(list2, "severityList");
        this.categoryList = list;
        this.severityList = list2;
    }

    @NotNull
    public final List<String> getCategoryList() {
        return this.categoryList;
    }

    @NotNull
    public final List<String> getSeverityList() {
        return this.severityList;
    }

    private final Comparator<ChapterFlat> getComparator() {
        Iterable $this$associate$iv = CollectionsKt.withIndex(this.categoryList);
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        final Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            IndexedValue it = (IndexedValue) element$iv$iv;
            Pair pair = TuplesKt.to(it.getValue(), Integer.valueOf(it.getIndex()));
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        Iterable $this$associate$iv2 = CollectionsKt.withIndex(this.severityList);
        int capacity$iv2 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv2, 10)), 16);
        final Map destination$iv$iv2 = new LinkedHashMap(capacity$iv2);
        for (Object element$iv$iv2 : $this$associate$iv2) {
            IndexedValue it2 = (IndexedValue) element$iv$iv2;
            Pair pair2 = TuplesKt.to(it2.getValue(), Integer.valueOf(it2.getIndex()));
            destination$iv$iv2.put(pair2.getFirst(), pair2.getSecond());
        }
        return new Comparator() { // from class: cn.sast.api.config.CheckerPriorityConfig.getComparator.1
            @Override // java.util.Comparator
            public final int compare(ChapterFlat a, ChapterFlat b) {
                Integer numValueOf = Integer.valueOf(ComparatorUtilsKt.compareToNullable(destination$iv$iv.get(a.getCategory()), destination$iv$iv.get(b.getCategory())));
                int it3 = numValueOf.intValue();
                Integer num = it3 != 0 ? numValueOf : null;
                if (num != null) {
                    int it4 = num.intValue();
                    return it4;
                }
                Integer numValueOf2 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(destination$iv$iv2.get(a.getSeverity()), destination$iv$iv2.get(b.getSeverity())));
                int it5 = numValueOf2.intValue();
                Integer num2 = it5 != 0 ? numValueOf2 : null;
                if (num2 != null) {
                    int it6 = num2.intValue();
                    return it6;
                }
                Integer numValueOf3 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(a.getRuleId(), b.getRuleId()));
                int it7 = numValueOf3.intValue();
                Integer num3 = it7 != 0 ? numValueOf3 : null;
                if (num3 == null) {
                    return 0;
                }
                int it8 = num3.intValue();
                return it8;
            }
        };
    }

    private final List<ChapterFlat> sort(List<ChapterFlat> list) {
        return CollectionsKt.toList(CollectionsKt.toSortedSet(list, getComparator()));
    }

    @NotNull
    public final Map<String, Map<String, List<String>>> getSortTree(@NotNull List<ChapterFlat> list) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(list, "chapters");
        Iterable $this$groupBy$iv = sort(list);
        Map destination$iv$iv = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            ChapterFlat it = (ChapterFlat) element$iv$iv;
            String category = it.getCategory();
            Object value$iv$iv$iv = destination$iv$iv.get(category);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv.put(category, arrayList);
                obj2 = arrayList;
            } else {
                obj2 = value$iv$iv$iv;
            }
            List list$iv$iv = (List) obj2;
            list$iv$iv.add(element$iv$iv);
        }
        Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size()));
        Iterable $this$associateByTo$iv$iv$iv = destination$iv$iv.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            Map.Entry it1 = (Map.Entry) element$iv$iv$iv;
            Iterable $this$groupBy$iv2 = (Iterable) it1.getValue();
            Map destination$iv$iv3 = new LinkedHashMap();
            for (Object element$iv$iv2 : $this$groupBy$iv2) {
                ChapterFlat it2 = (ChapterFlat) element$iv$iv2;
                String severity = it2.getSeverity();
                Object value$iv$iv$iv2 = destination$iv$iv3.get(severity);
                if (value$iv$iv$iv2 == null) {
                    ArrayList arrayList2 = new ArrayList();
                    destination$iv$iv3.put(severity, arrayList2);
                    obj = arrayList2;
                } else {
                    obj = value$iv$iv$iv2;
                }
                List list$iv$iv2 = (List) obj;
                list$iv$iv2.add(element$iv$iv2);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv3.size()));
            Iterable $this$associateByTo$iv$iv$iv2 = destination$iv$iv3.entrySet();
            for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
                Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
                Object key2 = it$iv$iv2.getKey();
                Map.Entry it22 = (Map.Entry) element$iv$iv$iv2;
                Iterable $this$map$iv = (Iterable) it22.getValue();
                Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    ChapterFlat it3 = (ChapterFlat) item$iv$iv;
                    destination$iv$iv4.add(it3.getRuleId());
                }
                linkedHashMap.put(key2, (List) destination$iv$iv4);
            }
            destination$iv$iv2.put(key, linkedHashMap);
        }
        return destination$iv$iv2;
    }

    @NotNull
    public final Iterable<IndexedValue<ChapterFlat>> getRuleWithSortNumber(@NotNull List<ChapterFlat> list) {
        Intrinsics.checkNotNullParameter(list, "chapters");
        return CollectionsKt.withIndex(sort(list));
    }

    /* compiled from: CheckerPriorityConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lcn/sast/api/config/CheckerPriorityConfig$Companion;", "", "<init>", "()V", "yamlFormat", "Lcom/charleskorn/kaml/Yaml;", "deserialize", "Lcn/sast/api/config/CheckerPriorityConfig;", "checkerPriorityYamlFile", "Lcn/sast/common/IResFile;", "serializer", "Lkotlinx/serialization/KSerializer;", "corax-api"})
    /* loaded from: CheckerPriorityConfig$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<CheckerPriorityConfig> serializer() {
            return CheckerPriorityConfig$$serializer.INSTANCE;
        }

        @NotNull
        public final CheckerPriorityConfig deserialize(@NotNull IResFile checkerPriorityYamlFile) throws IOException {
            Intrinsics.checkNotNullParameter(checkerPriorityYamlFile, "checkerPriorityYamlFile");
            OpenOption[] openOptionArr = new OpenOption[0];
            InputStream inputStreamNewInputStream = Files.newInputStream(checkerPriorityYamlFile.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            InputStream inputStream = inputStreamNewInputStream;
            Throwable th = null;
            try {
                try {
                    InputStream it = inputStream;
                    CheckerPriorityConfig checkerPriorityConfig = (CheckerPriorityConfig) Yaml.decodeFromStream$default(CheckerPriorityConfig.yamlFormat, CheckerPriorityConfig.Companion.serializer(), it, (Charset) null, 4, (Object) null);
                    CloseableKt.closeFinally(inputStream, (Throwable) null);
                    return checkerPriorityConfig;
                } finally {
                }
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStream, th);
                throw th2;
            }
        }
    }
}
