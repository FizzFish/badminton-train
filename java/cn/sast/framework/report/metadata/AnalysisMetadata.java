package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
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
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalysisMetadata.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� <2\u00020\u0001:\u0002<=BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0004\b\u000e\u0010\u000fBg\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0013J\u0006\u0010&\u001a\u00020\nJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010,\u001a\u00020\nHÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\r0\tHÆ\u0003J[\u0010.\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tHÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0003HÖ\u0001J\t\u00103\u001a\u00020\nHÖ\u0001J%\u00104\u001a\u0002052\u0006\u00106\u001a\u00020��2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0001¢\u0006\u0002\b;R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001e\u0010\u0017R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u001f\u0010\u0015\u001a\u0004\b \u0010!R\u001c\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\"\u0010\u0015\u001a\u0004\b#\u0010$R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t¢\u0006\b\n��\u001a\u0004\b%\u0010!¨\u0006>"}, d2 = {"Lcn/sast/framework/report/metadata/AnalysisMetadata;", "", "fileCount", "", "lineCount", "codeCoverage", "Lcn/sast/framework/report/metadata/Counter;", "numOfReportDir", "sourcePaths", "", "", "osName", "tools", "Lcn/sast/framework/report/metadata/Tool;", "<init>", "(IILcn/sast/framework/report/metadata/Counter;ILjava/util/List;Ljava/lang/String;Ljava/util/List;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIILcn/sast/framework/report/metadata/Counter;ILjava/util/List;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFileCount$annotations", "()V", "getFileCount", "()I", "getLineCount$annotations", "getLineCount", "getCodeCoverage$annotations", "getCodeCoverage", "()Lcn/sast/framework/report/metadata/Counter;", "getNumOfReportDir$annotations", "getNumOfReportDir", "getSourcePaths$annotations", "getSourcePaths", "()Ljava/util/List;", "getOsName$annotations", "getOsName", "()Ljava/lang/String;", "getTools", "toJson", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "Companion", "$serializer", "corax-framework"})
/* loaded from: AnalysisMetadata.class */
public final class AnalysisMetadata {
    private final int fileCount;
    private final int lineCount;

    @NotNull
    private final Counter codeCoverage;
    private final int numOfReportDir;

    @NotNull
    private final List<String> sourcePaths;

    @NotNull
    private final String osName;

    @NotNull
    private final List<Tool> tools;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), null, new ArrayListSerializer(Tool$$serializer.INSTANCE)};

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, AnalysisMetadata::jsonFormat$lambda$0, 1, (Object) null);

    @SerialName("file_count")
    public static /* synthetic */ void getFileCount$annotations() {
    }

    @SerialName("line_count")
    public static /* synthetic */ void getLineCount$annotations() {
    }

    @SerialName("code_coverage")
    public static /* synthetic */ void getCodeCoverage$annotations() {
    }

    @SerialName("num_of_report_dir")
    public static /* synthetic */ void getNumOfReportDir$annotations() {
    }

    @SerialName("source_paths")
    public static /* synthetic */ void getSourcePaths$annotations() {
    }

    @SerialName("os_name")
    public static /* synthetic */ void getOsName$annotations() {
    }

    public final int component1() {
        return this.fileCount;
    }

    public final int component2() {
        return this.lineCount;
    }

    @NotNull
    public final Counter component3() {
        return this.codeCoverage;
    }

    public final int component4() {
        return this.numOfReportDir;
    }

    @NotNull
    public final List<String> component5() {
        return this.sourcePaths;
    }

    @NotNull
    public final String component6() {
        return this.osName;
    }

    @NotNull
    public final List<Tool> component7() {
        return this.tools;
    }

    @NotNull
    public final AnalysisMetadata copy(int fileCount, int lineCount, @NotNull Counter codeCoverage, int numOfReportDir, @NotNull List<String> list, @NotNull String osName, @NotNull List<Tool> list2) {
        Intrinsics.checkNotNullParameter(codeCoverage, "codeCoverage");
        Intrinsics.checkNotNullParameter(list, "sourcePaths");
        Intrinsics.checkNotNullParameter(osName, "osName");
        Intrinsics.checkNotNullParameter(list2, "tools");
        return new AnalysisMetadata(fileCount, lineCount, codeCoverage, numOfReportDir, list, osName, list2);
    }

    public static /* synthetic */ AnalysisMetadata copy$default(AnalysisMetadata analysisMetadata, int i, int i2, Counter counter, int i3, List list, String str, List list2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = analysisMetadata.fileCount;
        }
        if ((i4 & 2) != 0) {
            i2 = analysisMetadata.lineCount;
        }
        if ((i4 & 4) != 0) {
            counter = analysisMetadata.codeCoverage;
        }
        if ((i4 & 8) != 0) {
            i3 = analysisMetadata.numOfReportDir;
        }
        if ((i4 & 16) != 0) {
            list = analysisMetadata.sourcePaths;
        }
        if ((i4 & 32) != 0) {
            str = analysisMetadata.osName;
        }
        if ((i4 & 64) != 0) {
            list2 = analysisMetadata.tools;
        }
        return analysisMetadata.copy(i, i2, counter, i3, list, str, list2);
    }

    @NotNull
    public String toString() {
        return "AnalysisMetadata(fileCount=" + this.fileCount + ", lineCount=" + this.lineCount + ", codeCoverage=" + this.codeCoverage + ", numOfReportDir=" + this.numOfReportDir + ", sourcePaths=" + this.sourcePaths + ", osName=" + this.osName + ", tools=" + this.tools + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.fileCount);
        return (((((((((((result * 31) + Integer.hashCode(this.lineCount)) * 31) + this.codeCoverage.hashCode()) * 31) + Integer.hashCode(this.numOfReportDir)) * 31) + this.sourcePaths.hashCode()) * 31) + this.osName.hashCode()) * 31) + this.tools.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalysisMetadata)) {
            return false;
        }
        AnalysisMetadata analysisMetadata = (AnalysisMetadata) other;
        return this.fileCount == analysisMetadata.fileCount && this.lineCount == analysisMetadata.lineCount && Intrinsics.areEqual(this.codeCoverage, analysisMetadata.codeCoverage) && this.numOfReportDir == analysisMetadata.numOfReportDir && Intrinsics.areEqual(this.sourcePaths, analysisMetadata.sourcePaths) && Intrinsics.areEqual(this.osName, analysisMetadata.osName) && Intrinsics.areEqual(this.tools, analysisMetadata.tools);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(AnalysisMetadata self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeIntElement(serialDesc, 0, self.fileCount);
        output.encodeIntElement(serialDesc, 1, self.lineCount);
        output.encodeSerializableElement(serialDesc, 2, Counter$$serializer.INSTANCE, self.codeCoverage);
        output.encodeIntElement(serialDesc, 3, self.numOfReportDir);
        output.encodeSerializableElement(serialDesc, 4, serializationStrategyArr[4], self.sourcePaths);
        output.encodeStringElement(serialDesc, 5, self.osName);
        output.encodeSerializableElement(serialDesc, 6, serializationStrategyArr[6], self.tools);
    }

    public /* synthetic */ AnalysisMetadata(int seen0, int fileCount, int lineCount, Counter codeCoverage, int numOfReportDir, List sourcePaths, String osName, List tools, SerializationConstructorMarker serializationConstructorMarker) {
        if (127 != (127 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 127, AnalysisMetadata$$serializer.INSTANCE.getDescriptor());
        }
        this.fileCount = fileCount;
        this.lineCount = lineCount;
        this.codeCoverage = codeCoverage;
        this.numOfReportDir = numOfReportDir;
        this.sourcePaths = sourcePaths;
        this.osName = osName;
        this.tools = tools;
    }

    public AnalysisMetadata(int fileCount, int lineCount, @NotNull Counter codeCoverage, int numOfReportDir, @NotNull List<String> list, @NotNull String osName, @NotNull List<Tool> list2) {
        Intrinsics.checkNotNullParameter(codeCoverage, "codeCoverage");
        Intrinsics.checkNotNullParameter(list, "sourcePaths");
        Intrinsics.checkNotNullParameter(osName, "osName");
        Intrinsics.checkNotNullParameter(list2, "tools");
        this.fileCount = fileCount;
        this.lineCount = lineCount;
        this.codeCoverage = codeCoverage;
        this.numOfReportDir = numOfReportDir;
        this.sourcePaths = list;
        this.osName = osName;
        this.tools = list2;
    }

    public final int getFileCount() {
        return this.fileCount;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    @NotNull
    public final Counter getCodeCoverage() {
        return this.codeCoverage;
    }

    public final int getNumOfReportDir() {
        return this.numOfReportDir;
    }

    @NotNull
    public final List<String> getSourcePaths() {
        return this.sourcePaths;
    }

    @NotNull
    public final String getOsName() {
        return this.osName;
    }

    @NotNull
    public final List<Tool> getTools() {
        return this.tools;
    }

    /* compiled from: AnalysisMetadata.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"Lcn/sast/framework/report/metadata/AnalysisMetadata$Companion;", "", "<init>", "()V", "jsonFormat", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "corax-framework"})
    /* loaded from: AnalysisMetadata$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<AnalysisMetadata> serializer() {
            return AnalysisMetadata$$serializer.INSTANCE;
        }
    }

    private static final Unit jsonFormat$lambda$0(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        $this$Json.setEncodeDefaults(false);
        return Unit.INSTANCE;
    }

    @NotNull
    public final String toJson() {
        return jsonFormat.encodeToString(Companion.serializer(), this);
    }
}
