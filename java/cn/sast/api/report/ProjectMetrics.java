package cn.sast.api.report;

import cn.sast.common.OS;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProjectMetrics.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n��\n\u0002\u0010!\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010#\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� \u0087\u00012\u00020\u0001:\u0004\u0086\u0001\u0087\u0001B\u0085\u0002\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u001d\u001a\u00020\b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u001f\u001a\u00020\b¢\u0006\u0004\b \u0010!Bë\u0001\b\u0010\u0012\u0006\u0010\"\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\b\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\b\u0012\u0006\u0010\u001a\u001a\u00020\b\u0012\u0006\u0010\u001b\u001a\u00020\b\u0012\u0006\u0010\u001c\u001a\u00020\u000f\u0012\u0006\u0010\u001d\u001a\u00020\b\u0012\u0006\u0010\u001e\u001a\u00020\u000f\u0012\u0006\u0010\u001f\u001a\u00020\b\u0012\b\u0010#\u001a\u0004\u0018\u00010$¢\u0006\u0004\b \u0010%J\u0006\u0010_\u001a\u00020`J\u0011\u0010a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0003J\t\u0010c\u001a\u00020\bHÆ\u0003J\t\u0010d\u001a\u00020\bHÆ\u0003J\t\u0010e\u001a\u00020\bHÆ\u0003J\t\u0010f\u001a\u00020\bHÆ\u0003J\t\u0010g\u001a\u00020\bHÆ\u0003J\t\u0010h\u001a\u00020\bHÆ\u0003J\t\u0010i\u001a\u00020\u000fHÂ\u0003J\t\u0010j\u001a\u00020\bHÆ\u0003J\t\u0010k\u001a\u00020\u000fHÂ\u0003J\t\u0010l\u001a\u00020\bHÆ\u0003J\t\u0010m\u001a\u00020\bHÆ\u0003J\t\u0010n\u001a\u00020\bHÆ\u0003J\t\u0010o\u001a\u00020\u0016HÆ\u0003J\t\u0010p\u001a\u00020\u0016HÆ\u0003J\t\u0010q\u001a\u00020\u0016HÆ\u0003J\t\u0010r\u001a\u00020\bHÂ\u0003J\t\u0010s\u001a\u00020\bHÂ\u0003J\t\u0010t\u001a\u00020\bHÂ\u0003J\t\u0010u\u001a\u00020\u000fHÂ\u0003J\t\u0010v\u001a\u00020\bHÂ\u0003J\t\u0010w\u001a\u00020\u000fHÂ\u0003J\t\u0010x\u001a\u00020\bHÆ\u0003J\u0087\u0002\u0010y\u001a\u00020��2\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u000f2\b\b\u0002\u0010\u001f\u001a\u00020\bHÆ\u0001J\u0013\u0010z\u001a\u00020{2\b\u0010|\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010}\u001a\u00020\bHÖ\u0001J\t\u0010~\u001a\u00020\u0004HÖ\u0001J+\u0010\u007f\u001a\u00020`2\u0007\u0010\u0080\u0001\u001a\u00020��2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0001¢\u0006\u0003\b\u0085\u0001R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010'\"\u0004\b+\u0010)R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b0\u0010-\"\u0004\b1\u0010/R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b2\u0010-\"\u0004\b3\u0010/R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R\u001a\u0010\f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b6\u0010-\"\u0004\b7\u0010/R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b8\u0010-\"\u0004\b9\u0010/R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b<\u0010-\"\u0004\b=\u0010/R\u001a\u0010\u0013\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b>\u0010-\"\u0004\b?\u0010/R\u001a\u0010\u0014\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b@\u0010-\"\u0004\bA\u0010/R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bF\u0010C\"\u0004\bG\u0010ER\u001a\u0010\u0018\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bH\u0010C\"\u0004\bI\u0010ER\u0018\u0010\u0019\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\b\n��\u0012\u0004\bJ\u0010KR\u0018\u0010\u001a\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\b\n��\u0012\u0004\bL\u0010KR\u0018\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\b\n��\u0012\u0004\bM\u0010KR\u000e\u0010\u001c\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u0018\u0010\u001d\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\b\n��\u0012\u0004\bN\u0010KR\u000e\u0010\u001e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u001f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bO\u0010-\"\u0004\bP\u0010/R\"\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00040R8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\bS\u0010K\u001a\u0004\bT\u0010UR\"\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00040R8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\bW\u0010K\u001a\u0004\bX\u0010UR\"\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00040R8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\bZ\u0010K\u001a\u0004\b[\u0010UR\"\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00040R8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b]\u0010K\u001a\u0004\b^\u0010U¨\u0006\u0088\u0001"}, d2 = {"Lcn/sast/api/report/ProjectMetrics;", "", "command", "", "", "paths", "", "applicationClasses", "", "libraryClasses", "phantomClasses", "applicationMethods", "libraryMethods", "applicationMethodsHaveBody", "applicationMethodsHaveBodyRatio", "", "libraryMethodsHaveBody", "libraryMethodsHaveBodyRatio", "analyzedFiles", "appJavaFileCount", "appJavaLineCount", "totalFileNum", "", "totalAnySourceFileNum", "totalSourceFileNum", "_analyzedClasses", "_analyzedMethodEntries", "_analyzedApplicationMethods", "analyzedApplicationMethodsRatio", "_analyzedLibraryMethods", "analyzedLibraryMethodsRatio", "serializedReports", "<init>", "(Ljava/util/List;Ljava/util/List;IIIIIIFIFIIIJJJIIIFIFI)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;IIIIIIFIFIIIJJJIIIFIFILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCommand", "()Ljava/util/List;", "setCommand", "(Ljava/util/List;)V", "getPaths", "setPaths", "getApplicationClasses", "()I", "setApplicationClasses", "(I)V", "getLibraryClasses", "setLibraryClasses", "getPhantomClasses", "setPhantomClasses", "getApplicationMethods", "setApplicationMethods", "getLibraryMethods", "setLibraryMethods", "getApplicationMethodsHaveBody", "setApplicationMethodsHaveBody", "getLibraryMethodsHaveBody", "setLibraryMethodsHaveBody", "getAnalyzedFiles", "setAnalyzedFiles", "getAppJavaFileCount", "setAppJavaFileCount", "getAppJavaLineCount", "setAppJavaLineCount", "getTotalFileNum", "()J", "setTotalFileNum", "(J)V", "getTotalAnySourceFileNum", "setTotalAnySourceFileNum", "getTotalSourceFileNum", "setTotalSourceFileNum", "get_analyzedClasses$annotations", "()V", "get_analyzedMethodEntries$annotations", "get_analyzedApplicationMethods$annotations", "get_analyzedLibraryMethods$annotations", "getSerializedReports", "setSerializedReports", "analyzedClasses", "", "getAnalyzedClasses$annotations", "getAnalyzedClasses", "()Ljava/util/Set;", "analyzedMethodEntries", "getAnalyzedMethodEntries$annotations", "getAnalyzedMethodEntries", "analyzedApplicationMethods", "getAnalyzedApplicationMethods$annotations", "getAnalyzedApplicationMethods", "analyzedLibraryMethods", "getAnalyzedLibraryMethods$annotations", "getAnalyzedLibraryMethods", "process", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
/* loaded from: ProjectMetrics.class */
public final class ProjectMetrics {

    @Nullable
    private List<String> command;

    @NotNull
    private List<String> paths;
    private int applicationClasses;
    private int libraryClasses;
    private int phantomClasses;
    private int applicationMethods;
    private int libraryMethods;
    private int applicationMethodsHaveBody;
    private float applicationMethodsHaveBodyRatio;
    private int libraryMethodsHaveBody;
    private float libraryMethodsHaveBodyRatio;
    private int analyzedFiles;
    private int appJavaFileCount;
    private int appJavaLineCount;
    private long totalFileNum;
    private long totalAnySourceFileNum;
    private long totalSourceFileNum;
    private int _analyzedClasses;
    private int _analyzedMethodEntries;
    private int _analyzedApplicationMethods;
    private float analyzedApplicationMethodsRatio;
    private int _analyzedLibraryMethods;
    private float analyzedLibraryMethodsRatio;
    private int serializedReports;

    @NotNull
    private final Set<String> analyzedClasses;

    @NotNull
    private final Set<String> analyzedMethodEntries;

    @NotNull
    private final Set<String> analyzedApplicationMethods;

    @NotNull
    private final Set<String> analyzedLibraryMethods;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(StringSerializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};

    @SerialName("analyzedClasses")
    private static /* synthetic */ void get_analyzedClasses$annotations() {
    }

    @SerialName("analyzedMethodEntries")
    private static /* synthetic */ void get_analyzedMethodEntries$annotations() {
    }

    @SerialName("analyzedApplicationMethods")
    private static /* synthetic */ void get_analyzedApplicationMethods$annotations() {
    }

    @SerialName("analyzedLibraryMethods")
    private static /* synthetic */ void get_analyzedLibraryMethods$annotations() {
    }

    @Transient
    public static /* synthetic */ void getAnalyzedClasses$annotations() {
    }

    @Transient
    public static /* synthetic */ void getAnalyzedMethodEntries$annotations() {
    }

    @Transient
    public static /* synthetic */ void getAnalyzedApplicationMethods$annotations() {
    }

    @Transient
    public static /* synthetic */ void getAnalyzedLibraryMethods$annotations() {
    }

    @Nullable
    public final List<String> component1() {
        return this.command;
    }

    @NotNull
    public final List<String> component2() {
        return this.paths;
    }

    public final int component3() {
        return this.applicationClasses;
    }

    public final int component4() {
        return this.libraryClasses;
    }

    public final int component5() {
        return this.phantomClasses;
    }

    public final int component6() {
        return this.applicationMethods;
    }

    public final int component7() {
        return this.libraryMethods;
    }

    public final int component8() {
        return this.applicationMethodsHaveBody;
    }

    private final float component9() {
        return this.applicationMethodsHaveBodyRatio;
    }

    public final int component10() {
        return this.libraryMethodsHaveBody;
    }

    private final float component11() {
        return this.libraryMethodsHaveBodyRatio;
    }

    public final int component12() {
        return this.analyzedFiles;
    }

    public final int component13() {
        return this.appJavaFileCount;
    }

    public final int component14() {
        return this.appJavaLineCount;
    }

    public final long component15() {
        return this.totalFileNum;
    }

    public final long component16() {
        return this.totalAnySourceFileNum;
    }

    public final long component17() {
        return this.totalSourceFileNum;
    }

    private final int component18() {
        return this._analyzedClasses;
    }

    private final int component19() {
        return this._analyzedMethodEntries;
    }

    private final int component20() {
        return this._analyzedApplicationMethods;
    }

    private final float component21() {
        return this.analyzedApplicationMethodsRatio;
    }

    private final int component22() {
        return this._analyzedLibraryMethods;
    }

    private final float component23() {
        return this.analyzedLibraryMethodsRatio;
    }

    public final int component24() {
        return this.serializedReports;
    }

    @NotNull
    public final ProjectMetrics copy(@Nullable List<String> list, @NotNull List<String> list2, int applicationClasses, int libraryClasses, int phantomClasses, int applicationMethods, int libraryMethods, int applicationMethodsHaveBody, float applicationMethodsHaveBodyRatio, int libraryMethodsHaveBody, float libraryMethodsHaveBodyRatio, int analyzedFiles, int appJavaFileCount, int appJavaLineCount, long totalFileNum, long totalAnySourceFileNum, long totalSourceFileNum, int _analyzedClasses, int _analyzedMethodEntries, int _analyzedApplicationMethods, float analyzedApplicationMethodsRatio, int _analyzedLibraryMethods, float analyzedLibraryMethodsRatio, int serializedReports) {
        Intrinsics.checkNotNullParameter(list2, "paths");
        return new ProjectMetrics(list, list2, applicationClasses, libraryClasses, phantomClasses, applicationMethods, libraryMethods, applicationMethodsHaveBody, applicationMethodsHaveBodyRatio, libraryMethodsHaveBody, libraryMethodsHaveBodyRatio, analyzedFiles, appJavaFileCount, appJavaLineCount, totalFileNum, totalAnySourceFileNum, totalSourceFileNum, _analyzedClasses, _analyzedMethodEntries, _analyzedApplicationMethods, analyzedApplicationMethodsRatio, _analyzedLibraryMethods, analyzedLibraryMethodsRatio, serializedReports);
    }

    public static /* synthetic */ ProjectMetrics copy$default(ProjectMetrics projectMetrics, List list, List list2, int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, float f2, int i8, int i9, int i10, long j, long j2, long j3, int i11, int i12, int i13, float f3, int i14, float f4, int i15, int i16, Object obj) {
        if ((i16 & 1) != 0) {
            list = projectMetrics.command;
        }
        if ((i16 & 2) != 0) {
            list2 = projectMetrics.paths;
        }
        if ((i16 & 4) != 0) {
            i = projectMetrics.applicationClasses;
        }
        if ((i16 & 8) != 0) {
            i2 = projectMetrics.libraryClasses;
        }
        if ((i16 & 16) != 0) {
            i3 = projectMetrics.phantomClasses;
        }
        if ((i16 & 32) != 0) {
            i4 = projectMetrics.applicationMethods;
        }
        if ((i16 & 64) != 0) {
            i5 = projectMetrics.libraryMethods;
        }
        if ((i16 & 128) != 0) {
            i6 = projectMetrics.applicationMethodsHaveBody;
        }
        if ((i16 & 256) != 0) {
            f = projectMetrics.applicationMethodsHaveBodyRatio;
        }
        if ((i16 & 512) != 0) {
            i7 = projectMetrics.libraryMethodsHaveBody;
        }
        if ((i16 & 1024) != 0) {
            f2 = projectMetrics.libraryMethodsHaveBodyRatio;
        }
        if ((i16 & 2048) != 0) {
            i8 = projectMetrics.analyzedFiles;
        }
        if ((i16 & 4096) != 0) {
            i9 = projectMetrics.appJavaFileCount;
        }
        if ((i16 & 8192) != 0) {
            i10 = projectMetrics.appJavaLineCount;
        }
        if ((i16 & 16384) != 0) {
            j = projectMetrics.totalFileNum;
        }
        if ((i16 & 32768) != 0) {
            j2 = projectMetrics.totalAnySourceFileNum;
        }
        if ((i16 & 65536) != 0) {
            j3 = projectMetrics.totalSourceFileNum;
        }
        if ((i16 & 131072) != 0) {
            i11 = projectMetrics._analyzedClasses;
        }
        if ((i16 & 262144) != 0) {
            i12 = projectMetrics._analyzedMethodEntries;
        }
        if ((i16 & 524288) != 0) {
            i13 = projectMetrics._analyzedApplicationMethods;
        }
        if ((i16 & 1048576) != 0) {
            f3 = projectMetrics.analyzedApplicationMethodsRatio;
        }
        if ((i16 & 2097152) != 0) {
            i14 = projectMetrics._analyzedLibraryMethods;
        }
        if ((i16 & 4194304) != 0) {
            f4 = projectMetrics.analyzedLibraryMethodsRatio;
        }
        if ((i16 & 8388608) != 0) {
            i15 = projectMetrics.serializedReports;
        }
        return projectMetrics.copy(list, list2, i, i2, i3, i4, i5, i6, f, i7, f2, i8, i9, i10, j, j2, j3, i11, i12, i13, f3, i14, f4, i15);
    }

    @NotNull
    public String toString() {
        List<String> list = this.command;
        List<String> list2 = this.paths;
        int i = this.applicationClasses;
        int i2 = this.libraryClasses;
        int i3 = this.phantomClasses;
        int i4 = this.applicationMethods;
        int i5 = this.libraryMethods;
        int i6 = this.applicationMethodsHaveBody;
        float f = this.applicationMethodsHaveBodyRatio;
        int i7 = this.libraryMethodsHaveBody;
        float f2 = this.libraryMethodsHaveBodyRatio;
        int i8 = this.analyzedFiles;
        int i9 = this.appJavaFileCount;
        int i10 = this.appJavaLineCount;
        long j = this.totalFileNum;
        long j2 = this.totalAnySourceFileNum;
        long j3 = this.totalSourceFileNum;
        int i11 = this._analyzedClasses;
        int i12 = this._analyzedMethodEntries;
        int i13 = this._analyzedApplicationMethods;
        float f3 = this.analyzedApplicationMethodsRatio;
        int i14 = this._analyzedLibraryMethods;
        float f4 = this.analyzedLibraryMethodsRatio;
        int i15 = this.serializedReports;
        return "ProjectMetrics(command=" + list + ", paths=" + list2 + ", applicationClasses=" + i + ", libraryClasses=" + i2 + ", phantomClasses=" + i3 + ", applicationMethods=" + i4 + ", libraryMethods=" + i5 + ", applicationMethodsHaveBody=" + i6 + ", applicationMethodsHaveBodyRatio=" + f + ", libraryMethodsHaveBody=" + i7 + ", libraryMethodsHaveBodyRatio=" + f2 + ", analyzedFiles=" + i8 + ", appJavaFileCount=" + i9 + ", appJavaLineCount=" + i10 + ", totalFileNum=" + j + ", totalAnySourceFileNum=" + list + ", totalSourceFileNum=" + j2 + ", _analyzedClasses=" + list + ", _analyzedMethodEntries=" + j3 + ", _analyzedApplicationMethods=" + list + ", analyzedApplicationMethodsRatio=" + i11 + ", _analyzedLibraryMethods=" + i12 + ", analyzedLibraryMethodsRatio=" + i13 + ", serializedReports=" + f3 + ")";
    }

    public int hashCode() {
        int result = this.command == null ? 0 : this.command.hashCode();
        return (((((((((((((((((((((((((((((((((((((((((((((result * 31) + this.paths.hashCode()) * 31) + Integer.hashCode(this.applicationClasses)) * 31) + Integer.hashCode(this.libraryClasses)) * 31) + Integer.hashCode(this.phantomClasses)) * 31) + Integer.hashCode(this.applicationMethods)) * 31) + Integer.hashCode(this.libraryMethods)) * 31) + Integer.hashCode(this.applicationMethodsHaveBody)) * 31) + Float.hashCode(this.applicationMethodsHaveBodyRatio)) * 31) + Integer.hashCode(this.libraryMethodsHaveBody)) * 31) + Float.hashCode(this.libraryMethodsHaveBodyRatio)) * 31) + Integer.hashCode(this.analyzedFiles)) * 31) + Integer.hashCode(this.appJavaFileCount)) * 31) + Integer.hashCode(this.appJavaLineCount)) * 31) + Long.hashCode(this.totalFileNum)) * 31) + Long.hashCode(this.totalAnySourceFileNum)) * 31) + Long.hashCode(this.totalSourceFileNum)) * 31) + Integer.hashCode(this._analyzedClasses)) * 31) + Integer.hashCode(this._analyzedMethodEntries)) * 31) + Integer.hashCode(this._analyzedApplicationMethods)) * 31) + Float.hashCode(this.analyzedApplicationMethodsRatio)) * 31) + Integer.hashCode(this._analyzedLibraryMethods)) * 31) + Float.hashCode(this.analyzedLibraryMethodsRatio)) * 31) + Integer.hashCode(this.serializedReports);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectMetrics)) {
            return false;
        }
        ProjectMetrics projectMetrics = (ProjectMetrics) other;
        return Intrinsics.areEqual(this.command, projectMetrics.command) && Intrinsics.areEqual(this.paths, projectMetrics.paths) && this.applicationClasses == projectMetrics.applicationClasses && this.libraryClasses == projectMetrics.libraryClasses && this.phantomClasses == projectMetrics.phantomClasses && this.applicationMethods == projectMetrics.applicationMethods && this.libraryMethods == projectMetrics.libraryMethods && this.applicationMethodsHaveBody == projectMetrics.applicationMethodsHaveBody && Float.compare(this.applicationMethodsHaveBodyRatio, projectMetrics.applicationMethodsHaveBodyRatio) == 0 && this.libraryMethodsHaveBody == projectMetrics.libraryMethodsHaveBody && Float.compare(this.libraryMethodsHaveBodyRatio, projectMetrics.libraryMethodsHaveBodyRatio) == 0 && this.analyzedFiles == projectMetrics.analyzedFiles && this.appJavaFileCount == projectMetrics.appJavaFileCount && this.appJavaLineCount == projectMetrics.appJavaLineCount && this.totalFileNum == projectMetrics.totalFileNum && this.totalAnySourceFileNum == projectMetrics.totalAnySourceFileNum && this.totalSourceFileNum == projectMetrics.totalSourceFileNum && this._analyzedClasses == projectMetrics._analyzedClasses && this._analyzedMethodEntries == projectMetrics._analyzedMethodEntries && this._analyzedApplicationMethods == projectMetrics._analyzedApplicationMethods && Float.compare(this.analyzedApplicationMethodsRatio, projectMetrics.analyzedApplicationMethodsRatio) == 0 && this._analyzedLibraryMethods == projectMetrics._analyzedLibraryMethods && Float.compare(this.analyzedLibraryMethodsRatio, projectMetrics.analyzedLibraryMethodsRatio) == 0 && this.serializedReports == projectMetrics.serializedReports;
    }

    public ProjectMetrics() {
        this((List) null, (List) null, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0f, 0, 0, 0, 0L, 0L, 0L, 0, 0, 0, 0.0f, 0, 0.0f, 0, 16777215, (DefaultConstructorMarker) null);
    }

    /* compiled from: ProjectMetrics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/report/ProjectMetrics$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/report/ProjectMetrics;", "corax-api"})
    /* loaded from: ProjectMetrics$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ProjectMetrics> serializer() {
            return ProjectMetrics$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(ProjectMetrics self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.command, OS.getCommandLine$default(OS.INSTANCE, null, false, 3, null));
        if (z) {
            output.encodeNullableSerializableElement(serialDesc, 0, serializationStrategyArr[0], self.command);
        }
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.paths, new ArrayList());
        if (z2) {
            output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.paths);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 2) || self.applicationClasses != -1;
        if (z3) {
            output.encodeIntElement(serialDesc, 2, self.applicationClasses);
        }
        boolean z4 = output.shouldEncodeElementDefault(serialDesc, 3) || self.libraryClasses != -1;
        if (z4) {
            output.encodeIntElement(serialDesc, 3, self.libraryClasses);
        }
        boolean z5 = output.shouldEncodeElementDefault(serialDesc, 4) || self.phantomClasses != -1;
        if (z5) {
            output.encodeIntElement(serialDesc, 4, self.phantomClasses);
        }
        boolean z6 = output.shouldEncodeElementDefault(serialDesc, 5) || self.applicationMethods != -1;
        if (z6) {
            output.encodeIntElement(serialDesc, 5, self.applicationMethods);
        }
        boolean z7 = output.shouldEncodeElementDefault(serialDesc, 6) || self.libraryMethods != -1;
        if (z7) {
            output.encodeIntElement(serialDesc, 6, self.libraryMethods);
        }
        boolean z8 = output.shouldEncodeElementDefault(serialDesc, 7) || self.applicationMethodsHaveBody != -1;
        if (z8) {
            output.encodeIntElement(serialDesc, 7, self.applicationMethodsHaveBody);
        }
        boolean z9 = output.shouldEncodeElementDefault(serialDesc, 8) || Float.compare(self.applicationMethodsHaveBodyRatio, -1.0f) != 0;
        if (z9) {
            output.encodeFloatElement(serialDesc, 8, self.applicationMethodsHaveBodyRatio);
        }
        boolean z10 = output.shouldEncodeElementDefault(serialDesc, 9) || self.libraryMethodsHaveBody != -1;
        if (z10) {
            output.encodeIntElement(serialDesc, 9, self.libraryMethodsHaveBody);
        }
        boolean z11 = output.shouldEncodeElementDefault(serialDesc, 10) || Float.compare(self.libraryMethodsHaveBodyRatio, -1.0f) != 0;
        if (z11) {
            output.encodeFloatElement(serialDesc, 10, self.libraryMethodsHaveBodyRatio);
        }
        boolean z12 = output.shouldEncodeElementDefault(serialDesc, 11) || self.analyzedFiles != -1;
        if (z12) {
            output.encodeIntElement(serialDesc, 11, self.analyzedFiles);
        }
        boolean z13 = output.shouldEncodeElementDefault(serialDesc, 12) || self.appJavaFileCount != -1;
        if (z13) {
            output.encodeIntElement(serialDesc, 12, self.appJavaFileCount);
        }
        boolean z14 = output.shouldEncodeElementDefault(serialDesc, 13) || self.appJavaLineCount != -1;
        if (z14) {
            output.encodeIntElement(serialDesc, 13, self.appJavaLineCount);
        }
        boolean z15 = output.shouldEncodeElementDefault(serialDesc, 14) || self.totalFileNum != -1;
        if (z15) {
            output.encodeLongElement(serialDesc, 14, self.totalFileNum);
        }
        boolean z16 = output.shouldEncodeElementDefault(serialDesc, 15) || self.totalAnySourceFileNum != -1;
        if (z16) {
            output.encodeLongElement(serialDesc, 15, self.totalAnySourceFileNum);
        }
        boolean z17 = output.shouldEncodeElementDefault(serialDesc, 16) || self.totalSourceFileNum != -1;
        if (z17) {
            output.encodeLongElement(serialDesc, 16, self.totalSourceFileNum);
        }
        boolean z18 = output.shouldEncodeElementDefault(serialDesc, 17) || self._analyzedClasses != -1;
        if (z18) {
            output.encodeIntElement(serialDesc, 17, self._analyzedClasses);
        }
        boolean z19 = output.shouldEncodeElementDefault(serialDesc, 18) || self._analyzedMethodEntries != -1;
        if (z19) {
            output.encodeIntElement(serialDesc, 18, self._analyzedMethodEntries);
        }
        boolean z20 = output.shouldEncodeElementDefault(serialDesc, 19) || self._analyzedApplicationMethods != -1;
        if (z20) {
            output.encodeIntElement(serialDesc, 19, self._analyzedApplicationMethods);
        }
        boolean z21 = output.shouldEncodeElementDefault(serialDesc, 20) || Float.compare(self.analyzedApplicationMethodsRatio, -1.0f) != 0;
        if (z21) {
            output.encodeFloatElement(serialDesc, 20, self.analyzedApplicationMethodsRatio);
        }
        boolean z22 = output.shouldEncodeElementDefault(serialDesc, 21) || self._analyzedLibraryMethods != -1;
        if (z22) {
            output.encodeIntElement(serialDesc, 21, self._analyzedLibraryMethods);
        }
        boolean z23 = output.shouldEncodeElementDefault(serialDesc, 22) || Float.compare(self.analyzedLibraryMethodsRatio, -1.0f) != 0;
        if (z23) {
            output.encodeFloatElement(serialDesc, 22, self.analyzedLibraryMethodsRatio);
        }
        boolean z24 = output.shouldEncodeElementDefault(serialDesc, 23) || self.serializedReports != -1;
        if (z24) {
            output.encodeIntElement(serialDesc, 23, self.serializedReports);
        }
    }

    public /* synthetic */ ProjectMetrics(int seen0, List command, List paths, int applicationClasses, int libraryClasses, int phantomClasses, int applicationMethods, int libraryMethods, int applicationMethodsHaveBody, float applicationMethodsHaveBodyRatio, int libraryMethodsHaveBody, float libraryMethodsHaveBodyRatio, int analyzedFiles, int appJavaFileCount, int appJavaLineCount, long totalFileNum, long totalAnySourceFileNum, long totalSourceFileNum, int _analyzedClasses, int _analyzedMethodEntries, int _analyzedApplicationMethods, float analyzedApplicationMethodsRatio, int _analyzedLibraryMethods, float analyzedLibraryMethodsRatio, int serializedReports, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, ProjectMetrics$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.command = OS.getCommandLine$default(OS.INSTANCE, null, false, 3, null);
        } else {
            this.command = command;
        }
        if ((seen0 & 2) == 0) {
            this.paths = new ArrayList();
        } else {
            this.paths = paths;
        }
        if ((seen0 & 4) == 0) {
            this.applicationClasses = -1;
        } else {
            this.applicationClasses = applicationClasses;
        }
        if ((seen0 & 8) == 0) {
            this.libraryClasses = -1;
        } else {
            this.libraryClasses = libraryClasses;
        }
        if ((seen0 & 16) == 0) {
            this.phantomClasses = -1;
        } else {
            this.phantomClasses = phantomClasses;
        }
        if ((seen0 & 32) == 0) {
            this.applicationMethods = -1;
        } else {
            this.applicationMethods = applicationMethods;
        }
        if ((seen0 & 64) == 0) {
            this.libraryMethods = -1;
        } else {
            this.libraryMethods = libraryMethods;
        }
        if ((seen0 & 128) == 0) {
            this.applicationMethodsHaveBody = -1;
        } else {
            this.applicationMethodsHaveBody = applicationMethodsHaveBody;
        }
        if ((seen0 & 256) == 0) {
            this.applicationMethodsHaveBodyRatio = -1.0f;
        } else {
            this.applicationMethodsHaveBodyRatio = applicationMethodsHaveBodyRatio;
        }
        if ((seen0 & 512) == 0) {
            this.libraryMethodsHaveBody = -1;
        } else {
            this.libraryMethodsHaveBody = libraryMethodsHaveBody;
        }
        if ((seen0 & 1024) == 0) {
            this.libraryMethodsHaveBodyRatio = -1.0f;
        } else {
            this.libraryMethodsHaveBodyRatio = libraryMethodsHaveBodyRatio;
        }
        if ((seen0 & 2048) == 0) {
            this.analyzedFiles = -1;
        } else {
            this.analyzedFiles = analyzedFiles;
        }
        if ((seen0 & 4096) == 0) {
            this.appJavaFileCount = -1;
        } else {
            this.appJavaFileCount = appJavaFileCount;
        }
        if ((seen0 & 8192) == 0) {
            this.appJavaLineCount = -1;
        } else {
            this.appJavaLineCount = appJavaLineCount;
        }
        if ((seen0 & 16384) == 0) {
            this.totalFileNum = -1L;
        } else {
            this.totalFileNum = totalFileNum;
        }
        if ((seen0 & 32768) == 0) {
            this.totalAnySourceFileNum = -1L;
        } else {
            this.totalAnySourceFileNum = totalAnySourceFileNum;
        }
        if ((seen0 & 65536) == 0) {
            this.totalSourceFileNum = -1L;
        } else {
            this.totalSourceFileNum = totalSourceFileNum;
        }
        if ((seen0 & 131072) == 0) {
            this._analyzedClasses = -1;
        } else {
            this._analyzedClasses = _analyzedClasses;
        }
        if ((seen0 & 262144) == 0) {
            this._analyzedMethodEntries = -1;
        } else {
            this._analyzedMethodEntries = _analyzedMethodEntries;
        }
        if ((seen0 & 524288) == 0) {
            this._analyzedApplicationMethods = -1;
        } else {
            this._analyzedApplicationMethods = _analyzedApplicationMethods;
        }
        if ((seen0 & 1048576) == 0) {
            this.analyzedApplicationMethodsRatio = -1.0f;
        } else {
            this.analyzedApplicationMethodsRatio = analyzedApplicationMethodsRatio;
        }
        if ((seen0 & 2097152) == 0) {
            this._analyzedLibraryMethods = -1;
        } else {
            this._analyzedLibraryMethods = _analyzedLibraryMethods;
        }
        if ((seen0 & 4194304) == 0) {
            this.analyzedLibraryMethodsRatio = -1.0f;
        } else {
            this.analyzedLibraryMethodsRatio = analyzedLibraryMethodsRatio;
        }
        if ((seen0 & 8388608) == 0) {
            this.serializedReports = -1;
        } else {
            this.serializedReports = serializedReports;
        }
        Set<String> setSynchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet, "synchronizedSet(...)");
        this.analyzedClasses = setSynchronizedSet;
        Set<String> setSynchronizedSet2 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet2, "synchronizedSet(...)");
        this.analyzedMethodEntries = setSynchronizedSet2;
        Set<String> setSynchronizedSet3 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet3, "synchronizedSet(...)");
        this.analyzedApplicationMethods = setSynchronizedSet3;
        Set<String> setSynchronizedSet4 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet4, "synchronizedSet(...)");
        this.analyzedLibraryMethods = setSynchronizedSet4;
    }

    public ProjectMetrics(@Nullable List<String> list, @NotNull List<String> list2, int applicationClasses, int libraryClasses, int phantomClasses, int applicationMethods, int libraryMethods, int applicationMethodsHaveBody, float applicationMethodsHaveBodyRatio, int libraryMethodsHaveBody, float libraryMethodsHaveBodyRatio, int analyzedFiles, int appJavaFileCount, int appJavaLineCount, long totalFileNum, long totalAnySourceFileNum, long totalSourceFileNum, int _analyzedClasses, int _analyzedMethodEntries, int _analyzedApplicationMethods, float analyzedApplicationMethodsRatio, int _analyzedLibraryMethods, float analyzedLibraryMethodsRatio, int serializedReports) {
        Intrinsics.checkNotNullParameter(list2, "paths");
        this.command = list;
        this.paths = list2;
        this.applicationClasses = applicationClasses;
        this.libraryClasses = libraryClasses;
        this.phantomClasses = phantomClasses;
        this.applicationMethods = applicationMethods;
        this.libraryMethods = libraryMethods;
        this.applicationMethodsHaveBody = applicationMethodsHaveBody;
        this.applicationMethodsHaveBodyRatio = applicationMethodsHaveBodyRatio;
        this.libraryMethodsHaveBody = libraryMethodsHaveBody;
        this.libraryMethodsHaveBodyRatio = libraryMethodsHaveBodyRatio;
        this.analyzedFiles = analyzedFiles;
        this.appJavaFileCount = appJavaFileCount;
        this.appJavaLineCount = appJavaLineCount;
        this.totalFileNum = totalFileNum;
        this.totalAnySourceFileNum = totalAnySourceFileNum;
        this.totalSourceFileNum = totalSourceFileNum;
        this._analyzedClasses = _analyzedClasses;
        this._analyzedMethodEntries = _analyzedMethodEntries;
        this._analyzedApplicationMethods = _analyzedApplicationMethods;
        this.analyzedApplicationMethodsRatio = analyzedApplicationMethodsRatio;
        this._analyzedLibraryMethods = _analyzedLibraryMethods;
        this.analyzedLibraryMethodsRatio = analyzedLibraryMethodsRatio;
        this.serializedReports = serializedReports;
        Set<String> setSynchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet, "synchronizedSet(...)");
        this.analyzedClasses = setSynchronizedSet;
        Set<String> setSynchronizedSet2 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet2, "synchronizedSet(...)");
        this.analyzedMethodEntries = setSynchronizedSet2;
        Set<String> setSynchronizedSet3 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet3, "synchronizedSet(...)");
        this.analyzedApplicationMethods = setSynchronizedSet3;
        Set<String> setSynchronizedSet4 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet4, "synchronizedSet(...)");
        this.analyzedLibraryMethods = setSynchronizedSet4;
    }

    public /* synthetic */ ProjectMetrics(List list, List list2, int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, float f2, int i8, int i9, int i10, long j, long j2, long j3, int i11, int i12, int i13, float f3, int i14, float f4, int i15, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        this((i16 & 1) != 0 ? OS.getCommandLine$default(OS.INSTANCE, null, false, 3, null) : list, (i16 & 2) != 0 ? new ArrayList() : list2, (i16 & 4) != 0 ? -1 : i, (i16 & 8) != 0 ? -1 : i2, (i16 & 16) != 0 ? -1 : i3, (i16 & 32) != 0 ? -1 : i4, (i16 & 64) != 0 ? -1 : i5, (i16 & 128) != 0 ? -1 : i6, (i16 & 256) != 0 ? -1.0f : f, (i16 & 512) != 0 ? -1 : i7, (i16 & 1024) != 0 ? -1.0f : f2, (i16 & 2048) != 0 ? -1 : i8, (i16 & 4096) != 0 ? -1 : i9, (i16 & 8192) != 0 ? -1 : i10, (i16 & 16384) != 0 ? -1L : j, (i16 & 32768) != 0 ? -1L : j2, (i16 & 65536) != 0 ? -1L : j3, (i16 & 131072) != 0 ? -1 : i11, (i16 & 262144) != 0 ? -1 : i12, (i16 & 524288) != 0 ? -1 : i13, (i16 & 1048576) != 0 ? -1.0f : f3, (i16 & 2097152) != 0 ? -1 : i14, (i16 & 4194304) != 0 ? -1.0f : f4, (i16 & 8388608) != 0 ? -1 : i15);
    }

    @Nullable
    public final List<String> getCommand() {
        return this.command;
    }

    public final void setCommand(@Nullable List<String> list) {
        this.command = list;
    }

    @NotNull
    public final List<String> getPaths() {
        return this.paths;
    }

    public final void setPaths(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.paths = list;
    }

    public final int getApplicationClasses() {
        return this.applicationClasses;
    }

    public final void setApplicationClasses(int i) {
        this.applicationClasses = i;
    }

    public final int getLibraryClasses() {
        return this.libraryClasses;
    }

    public final void setLibraryClasses(int i) {
        this.libraryClasses = i;
    }

    public final int getPhantomClasses() {
        return this.phantomClasses;
    }

    public final void setPhantomClasses(int i) {
        this.phantomClasses = i;
    }

    public final int getApplicationMethods() {
        return this.applicationMethods;
    }

    public final void setApplicationMethods(int i) {
        this.applicationMethods = i;
    }

    public final int getLibraryMethods() {
        return this.libraryMethods;
    }

    public final void setLibraryMethods(int i) {
        this.libraryMethods = i;
    }

    public final int getApplicationMethodsHaveBody() {
        return this.applicationMethodsHaveBody;
    }

    public final void setApplicationMethodsHaveBody(int i) {
        this.applicationMethodsHaveBody = i;
    }

    public final int getLibraryMethodsHaveBody() {
        return this.libraryMethodsHaveBody;
    }

    public final void setLibraryMethodsHaveBody(int i) {
        this.libraryMethodsHaveBody = i;
    }

    public final int getAnalyzedFiles() {
        return this.analyzedFiles;
    }

    public final void setAnalyzedFiles(int i) {
        this.analyzedFiles = i;
    }

    public final int getAppJavaFileCount() {
        return this.appJavaFileCount;
    }

    public final void setAppJavaFileCount(int i) {
        this.appJavaFileCount = i;
    }

    public final int getAppJavaLineCount() {
        return this.appJavaLineCount;
    }

    public final void setAppJavaLineCount(int i) {
        this.appJavaLineCount = i;
    }

    public final long getTotalFileNum() {
        return this.totalFileNum;
    }

    public final void setTotalFileNum(long j) {
        this.totalFileNum = j;
    }

    public final long getTotalAnySourceFileNum() {
        return this.totalAnySourceFileNum;
    }

    public final void setTotalAnySourceFileNum(long j) {
        this.totalAnySourceFileNum = j;
    }

    public final long getTotalSourceFileNum() {
        return this.totalSourceFileNum;
    }

    public final void setTotalSourceFileNum(long j) {
        this.totalSourceFileNum = j;
    }

    public final int getSerializedReports() {
        return this.serializedReports;
    }

    public final void setSerializedReports(int i) {
        this.serializedReports = i;
    }

    @NotNull
    public final Set<String> getAnalyzedClasses() {
        return this.analyzedClasses;
    }

    @NotNull
    public final Set<String> getAnalyzedMethodEntries() {
        return this.analyzedMethodEntries;
    }

    @NotNull
    public final Set<String> getAnalyzedApplicationMethods() {
        return this.analyzedApplicationMethods;
    }

    @NotNull
    public final Set<String> getAnalyzedLibraryMethods() {
        return this.analyzedLibraryMethods;
    }

    public final void process() {
        this._analyzedClasses = this.analyzedClasses.size();
        this._analyzedApplicationMethods = this.analyzedApplicationMethods.size();
        this._analyzedLibraryMethods = this.analyzedLibraryMethods.size();
        this._analyzedMethodEntries = this.analyzedMethodEntries.size();
        if (this.applicationMethods != 0) {
            this.applicationMethodsHaveBodyRatio = this.applicationMethodsHaveBody / this.applicationMethods;
            this.analyzedApplicationMethodsRatio = this._analyzedApplicationMethods / this.applicationMethods;
        }
        if (this.libraryMethods != 0) {
            this.libraryMethodsHaveBodyRatio = this.libraryMethodsHaveBody / this.libraryMethods;
            this.analyzedLibraryMethodsRatio = this._analyzedLibraryMethods / this.libraryMethods;
        }
    }
}
