package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.EncodeDefault;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerInfo.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010%\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� a2\u00020\u0001:\u0002`aBÝ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aBû\u0001\b\u0010\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\u0019\u0010\u001fJ\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\u0015\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u0015\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u0015\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u0015\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010Q\u001a\u0004\u0018\u00010\u0018HÆ\u0003¢\u0006\u0002\u0010;Jú\u0001\u0010R\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010SJ\u0013\u0010T\u001a\u00020\u00182\b\u0010U\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010V\u001a\u00020\u001cHÖ\u0001J\t\u0010W\u001a\u00020\u0003HÖ\u0001J%\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020��2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^H\u0001¢\u0006\u0002\b_R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\"\u0010!R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b#\u0010!R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b$\u0010!R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b%\u0010!R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b&\u0010!R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n��\u001a\u0004\b'\u0010(R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n��\u001a\u0004\b)\u0010(R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n��\u001a\u0004\b*\u0010(R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n��\u001a\u0004\b+\u0010(R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n��\u001a\u0004\b,\u0010-R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b.\u0010/\u001a\u0004\b0\u0010!R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b1\u0010/\u001a\u0004\b2\u0010!R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b3\u0010/\u001a\u0004\b4\u0010!R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b5\u0010/\u001a\u0004\b6\u0010!R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b7\u0010/\u001a\u0004\b8\u0010!R \u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\b9\u0010/\u001a\u0004\b:\u0010;R\u0013\u0010=\u001a\u0004\u0018\u00010>8F¢\u0006\u0006\u001a\u0004\b?\u0010@¨\u0006b"}, d2 = {"Lcn/sast/api/config/CheckerInfo;", "", "type", "", "format_version", "analyzer", "language", "checker_id", "severity", "category", "", "name", "abstract", "description", "", "tags", "", "Lcn/sast/api/config/Tag;", "impact", "likelihood", "precision", "reCall", "impl", "implemented", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType", "()Ljava/lang/String;", "getFormat_version", "getAnalyzer", "getLanguage", "getChecker_id", "getSeverity", "getCategory", "()Ljava/util/Map;", "getName", "getAbstract", "getDescription", "getTags", "()Ljava/util/List;", "getImpact$annotations", "()V", "getImpact", "getLikelihood$annotations", "getLikelihood", "getPrecision$annotations", "getPrecision", "getReCall$annotations", "getReCall", "getImpl$annotations", "getImpl", "getImplemented$annotations", "getImplemented", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "chapterFlat", "Lcn/sast/api/config/ChapterFlat;", "getChapterFlat", "()Lcn/sast/api/config/ChapterFlat;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcn/sast/api/config/CheckerInfo;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nCheckerInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerInfo.kt\ncn/sast/api/config/CheckerInfo\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n1#2:50\n*E\n"})
/* loaded from: CheckerInfo.class */
public final class CheckerInfo {

    @NotNull
    private final String type;

    @NotNull
    private final String format_version;

    @NotNull
    private final String analyzer;

    @NotNull
    private final String language;

    @NotNull
    private final String checker_id;

    @NotNull
    private final String severity;

    @NotNull
    private final Map<String, String> category;

    @NotNull
    private final Map<String, String> name;

    /* renamed from: abstract, reason: not valid java name */
    @NotNull
    private final Map<String, String> f0abstract;

    @NotNull
    private final Map<String, String> description;

    @NotNull
    private final List<Tag> tags;

    @Nullable
    private final String impact;

    @Nullable
    private final String likelihood;

    @Nullable
    private final String precision;

    @Nullable
    private final String reCall;

    @Nullable
    private final String impl;

    @Nullable
    private final Boolean implemented;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), new ArrayListSerializer(Tag$$serializer.INSTANCE), null, null, null, null, null, null};

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getImpact$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getLikelihood$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getPrecision$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getReCall$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getImpl$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getImplemented$annotations() {
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.format_version;
    }

    @NotNull
    public final String component3() {
        return this.analyzer;
    }

    @NotNull
    public final String component4() {
        return this.language;
    }

    @NotNull
    public final String component5() {
        return this.checker_id;
    }

    @NotNull
    public final String component6() {
        return this.severity;
    }

    @NotNull
    public final Map<String, String> component7() {
        return this.category;
    }

    @NotNull
    public final Map<String, String> component8() {
        return this.name;
    }

    @NotNull
    public final Map<String, String> component9() {
        return this.f0abstract;
    }

    @NotNull
    public final Map<String, String> component10() {
        return this.description;
    }

    @NotNull
    public final List<Tag> component11() {
        return this.tags;
    }

    @Nullable
    public final String component12() {
        return this.impact;
    }

    @Nullable
    public final String component13() {
        return this.likelihood;
    }

    @Nullable
    public final String component14() {
        return this.precision;
    }

    @Nullable
    public final String component15() {
        return this.reCall;
    }

    @Nullable
    public final String component16() {
        return this.impl;
    }

    @Nullable
    public final Boolean component17() {
        return this.implemented;
    }

    @NotNull
    public final CheckerInfo copy(@NotNull String type, @NotNull String format_version, @NotNull String analyzer, @NotNull String language, @NotNull String checker_id, @NotNull String severity, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @NotNull Map<String, String> map3, @NotNull Map<String, String> map4, @NotNull List<Tag> list, @Nullable String impact, @Nullable String likelihood, @Nullable String precision, @Nullable String reCall, @Nullable String impl, @Nullable Boolean implemented) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(format_version, "format_version");
        Intrinsics.checkNotNullParameter(analyzer, "analyzer");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(checker_id, "checker_id");
        Intrinsics.checkNotNullParameter(severity, "severity");
        Intrinsics.checkNotNullParameter(map, "category");
        Intrinsics.checkNotNullParameter(map2, "name");
        Intrinsics.checkNotNullParameter(map3, "abstract");
        Intrinsics.checkNotNullParameter(map4, "description");
        Intrinsics.checkNotNullParameter(list, "tags");
        return new CheckerInfo(type, format_version, analyzer, language, checker_id, severity, map, map2, map3, map4, list, impact, likelihood, precision, reCall, impl, implemented);
    }

    public static /* synthetic */ CheckerInfo copy$default(CheckerInfo checkerInfo, String str, String str2, String str3, String str4, String str5, String str6, Map map, Map map2, Map map3, Map map4, List list, String str7, String str8, String str9, String str10, String str11, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = checkerInfo.type;
        }
        if ((i & 2) != 0) {
            str2 = checkerInfo.format_version;
        }
        if ((i & 4) != 0) {
            str3 = checkerInfo.analyzer;
        }
        if ((i & 8) != 0) {
            str4 = checkerInfo.language;
        }
        if ((i & 16) != 0) {
            str5 = checkerInfo.checker_id;
        }
        if ((i & 32) != 0) {
            str6 = checkerInfo.severity;
        }
        if ((i & 64) != 0) {
            map = checkerInfo.category;
        }
        if ((i & 128) != 0) {
            map2 = checkerInfo.name;
        }
        if ((i & 256) != 0) {
            map3 = checkerInfo.f0abstract;
        }
        if ((i & 512) != 0) {
            map4 = checkerInfo.description;
        }
        if ((i & 1024) != 0) {
            list = checkerInfo.tags;
        }
        if ((i & 2048) != 0) {
            str7 = checkerInfo.impact;
        }
        if ((i & 4096) != 0) {
            str8 = checkerInfo.likelihood;
        }
        if ((i & 8192) != 0) {
            str9 = checkerInfo.precision;
        }
        if ((i & 16384) != 0) {
            str10 = checkerInfo.reCall;
        }
        if ((i & 32768) != 0) {
            str11 = checkerInfo.impl;
        }
        if ((i & 65536) != 0) {
            bool = checkerInfo.implemented;
        }
        return checkerInfo.copy(str, str2, str3, str4, str5, str6, map, map2, map3, map4, list, str7, str8, str9, str10, str11, bool);
    }

    @NotNull
    public String toString() {
        return "CheckerInfo(type=" + this.type + ", format_version=" + this.format_version + ", analyzer=" + this.analyzer + ", language=" + this.language + ", checker_id=" + this.checker_id + ", severity=" + this.severity + ", category=" + this.category + ", name=" + this.name + ", abstract=" + this.f0abstract + ", description=" + this.description + ", tags=" + this.tags + ", impact=" + this.impact + ", likelihood=" + this.likelihood + ", precision=" + this.precision + ", reCall=" + this.reCall + ", impl=" + this.impl + ", implemented=" + this.implemented + ")";
    }

    public int hashCode() {
        int result = this.type.hashCode();
        return (((((((((((((((((((((((((((((((result * 31) + this.format_version.hashCode()) * 31) + this.analyzer.hashCode()) * 31) + this.language.hashCode()) * 31) + this.checker_id.hashCode()) * 31) + this.severity.hashCode()) * 31) + this.category.hashCode()) * 31) + this.name.hashCode()) * 31) + this.f0abstract.hashCode()) * 31) + this.description.hashCode()) * 31) + this.tags.hashCode()) * 31) + (this.impact == null ? 0 : this.impact.hashCode())) * 31) + (this.likelihood == null ? 0 : this.likelihood.hashCode())) * 31) + (this.precision == null ? 0 : this.precision.hashCode())) * 31) + (this.reCall == null ? 0 : this.reCall.hashCode())) * 31) + (this.impl == null ? 0 : this.impl.hashCode())) * 31) + (this.implemented == null ? 0 : this.implemented.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckerInfo)) {
            return false;
        }
        CheckerInfo checkerInfo = (CheckerInfo) other;
        return Intrinsics.areEqual(this.type, checkerInfo.type) && Intrinsics.areEqual(this.format_version, checkerInfo.format_version) && Intrinsics.areEqual(this.analyzer, checkerInfo.analyzer) && Intrinsics.areEqual(this.language, checkerInfo.language) && Intrinsics.areEqual(this.checker_id, checkerInfo.checker_id) && Intrinsics.areEqual(this.severity, checkerInfo.severity) && Intrinsics.areEqual(this.category, checkerInfo.category) && Intrinsics.areEqual(this.name, checkerInfo.name) && Intrinsics.areEqual(this.f0abstract, checkerInfo.f0abstract) && Intrinsics.areEqual(this.description, checkerInfo.description) && Intrinsics.areEqual(this.tags, checkerInfo.tags) && Intrinsics.areEqual(this.impact, checkerInfo.impact) && Intrinsics.areEqual(this.likelihood, checkerInfo.likelihood) && Intrinsics.areEqual(this.precision, checkerInfo.precision) && Intrinsics.areEqual(this.reCall, checkerInfo.reCall) && Intrinsics.areEqual(this.impl, checkerInfo.impl) && Intrinsics.areEqual(this.implemented, checkerInfo.implemented);
    }

    /* compiled from: CheckerInfo.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/config/CheckerInfo$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/config/CheckerInfo;", "corax-api"})
    /* loaded from: CheckerInfo$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<CheckerInfo> serializer() {
            return CheckerInfo$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(CheckerInfo self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.type);
        output.encodeStringElement(serialDesc, 1, self.format_version);
        output.encodeStringElement(serialDesc, 2, self.analyzer);
        output.encodeStringElement(serialDesc, 3, self.language);
        output.encodeStringElement(serialDesc, 4, self.checker_id);
        output.encodeStringElement(serialDesc, 5, self.severity);
        output.encodeSerializableElement(serialDesc, 6, serializationStrategyArr[6], self.category);
        output.encodeSerializableElement(serialDesc, 7, serializationStrategyArr[7], self.name);
        output.encodeSerializableElement(serialDesc, 8, serializationStrategyArr[8], self.f0abstract);
        output.encodeSerializableElement(serialDesc, 9, serializationStrategyArr[9], self.description);
        output.encodeSerializableElement(serialDesc, 10, serializationStrategyArr[10], self.tags);
        output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.impact);
        output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.likelihood);
        output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.precision);
        output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.reCall);
        output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.impl);
        output.encodeNullableSerializableElement(serialDesc, 16, BooleanSerializer.INSTANCE, self.implemented);
    }

    public /* synthetic */ CheckerInfo(int seen0, String type, String format_version, String analyzer, String language, String checker_id, String severity, Map category, Map name, Map map, Map description, List tags, String impact, String likelihood, String precision, String reCall, String impl, Boolean implemented, SerializationConstructorMarker serializationConstructorMarker) {
        if (2047 != (2047 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 2047, CheckerInfo$$serializer.INSTANCE.getDescriptor());
        }
        this.type = type;
        this.format_version = format_version;
        this.analyzer = analyzer;
        this.language = language;
        this.checker_id = checker_id;
        this.severity = severity;
        this.category = category;
        this.name = name;
        this.f0abstract = map;
        this.description = description;
        this.tags = tags;
        if ((seen0 & 2048) == 0) {
            this.impact = null;
        } else {
            this.impact = impact;
        }
        if ((seen0 & 4096) == 0) {
            this.likelihood = null;
        } else {
            this.likelihood = likelihood;
        }
        if ((seen0 & 8192) == 0) {
            this.precision = null;
        } else {
            this.precision = precision;
        }
        if ((seen0 & 16384) == 0) {
            this.reCall = null;
        } else {
            this.reCall = reCall;
        }
        if ((seen0 & 32768) == 0) {
            this.impl = null;
        } else {
            this.impl = impl;
        }
        if ((seen0 & 65536) == 0) {
            this.implemented = null;
        } else {
            this.implemented = implemented;
        }
    }

    public CheckerInfo(@NotNull String type, @NotNull String format_version, @NotNull String analyzer, @NotNull String language, @NotNull String checker_id, @NotNull String severity, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @NotNull Map<String, String> map3, @NotNull Map<String, String> map4, @NotNull List<Tag> list, @Nullable String impact, @Nullable String likelihood, @Nullable String precision, @Nullable String reCall, @Nullable String impl, @Nullable Boolean implemented) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(format_version, "format_version");
        Intrinsics.checkNotNullParameter(analyzer, "analyzer");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(checker_id, "checker_id");
        Intrinsics.checkNotNullParameter(severity, "severity");
        Intrinsics.checkNotNullParameter(map, "category");
        Intrinsics.checkNotNullParameter(map2, "name");
        Intrinsics.checkNotNullParameter(map3, "abstract");
        Intrinsics.checkNotNullParameter(map4, "description");
        Intrinsics.checkNotNullParameter(list, "tags");
        this.type = type;
        this.format_version = format_version;
        this.analyzer = analyzer;
        this.language = language;
        this.checker_id = checker_id;
        this.severity = severity;
        this.category = map;
        this.name = map2;
        this.f0abstract = map3;
        this.description = map4;
        this.tags = list;
        this.impact = impact;
        this.likelihood = likelihood;
        this.precision = precision;
        this.reCall = reCall;
        this.impl = impl;
        this.implemented = implemented;
    }

    public /* synthetic */ CheckerInfo(String str, String str2, String str3, String str4, String str5, String str6, Map map, Map map2, Map map3, Map map4, List list, String str7, String str8, String str9, String str10, String str11, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, map, map2, map3, map4, list, (i & 2048) != 0 ? null : str7, (i & 4096) != 0 ? null : str8, (i & 8192) != 0 ? null : str9, (i & 16384) != 0 ? null : str10, (i & 32768) != 0 ? null : str11, (i & 65536) != 0 ? null : bool);
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final String getFormat_version() {
        return this.format_version;
    }

    @NotNull
    public final String getAnalyzer() {
        return this.analyzer;
    }

    @NotNull
    public final String getLanguage() {
        return this.language;
    }

    @NotNull
    public final String getChecker_id() {
        return this.checker_id;
    }

    @NotNull
    public final String getSeverity() {
        return this.severity;
    }

    @NotNull
    public final Map<String, String> getCategory() {
        return this.category;
    }

    @NotNull
    public final Map<String, String> getName() {
        return this.name;
    }

    @NotNull
    public final Map<String, String> getAbstract() {
        return this.f0abstract;
    }

    @NotNull
    public final Map<String, String> getDescription() {
        return this.description;
    }

    @NotNull
    public final List<Tag> getTags() {
        return this.tags;
    }

    @Nullable
    public final String getImpact() {
        return this.impact;
    }

    @Nullable
    public final String getLikelihood() {
        return this.likelihood;
    }

    @Nullable
    public final String getPrecision() {
        return this.precision;
    }

    @Nullable
    public final String getReCall() {
        return this.reCall;
    }

    @Nullable
    public final String getImpl() {
        return this.impl;
    }

    @Nullable
    public final Boolean getImplemented() {
        return this.implemented;
    }

    @Nullable
    public final ChapterFlat getChapterFlat() {
        String it = this.category.get("zh-CN");
        if (it != null) {
            return new ChapterFlat(it, this.severity, this.checker_id);
        }
        return null;
    }
}
