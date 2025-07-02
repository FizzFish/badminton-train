package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import java.util.Map;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalysisMetadata.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� =2\u00020\u0001:\u0002<=Bk\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fB\u008f\u0001\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000e\u0010\u0014J\u0015\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\u0004HÆ\u0003J\t\u0010*\u001a\u00020\u0004HÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0004HÆ\u0003J}\u0010.\u001a\u00020��2\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\r\u001a\u00020\u0004HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0011HÖ\u0001J\t\u00103\u001a\u00020\u0004HÖ\u0001J%\u00104\u001a\u0002052\u0006\u00106\u001a\u00020��2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0001¢\u0006\u0002\b;R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001aR\u001c\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u001e\u0010\u001c\u001a\u0004\b\u001f\u0010\u001aR\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b \u0010\u001c\u001a\u0004\b!\u0010\u0018R(\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u0016R\u001c\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b$\u0010\u001c\u001a\u0004\b%\u0010\u001a¨\u0006>"}, d2 = {"Lcn/sast/framework/report/metadata/Tool;", "", "analyzers", "", "", "Lcn/sast/framework/report/metadata/Analyzer;", "command", "", "name", "outputPath", "projectRoot", "multipleProjectRoot", "resultSourceFiles", "workingDirectory", "<init>", "(Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAnalyzers", "()Ljava/util/Map;", "getCommand", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "getOutputPath$annotations", "()V", "getOutputPath", "getProjectRoot$annotations", "getProjectRoot", "getMultipleProjectRoot$annotations", "getMultipleProjectRoot", "getResultSourceFiles$annotations", "getResultSourceFiles", "getWorkingDirectory$annotations", "getWorkingDirectory", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Tool.class */
public final class Tool {

    @NotNull
    private final Map<String, Analyzer> analyzers;

    @NotNull
    private final List<String> command;

    @NotNull
    private final String name;

    @NotNull
    private final String outputPath;

    @NotNull
    private final String projectRoot;

    @NotNull
    private final List<String> multipleProjectRoot;

    @NotNull
    private final Map<String, String> resultSourceFiles;

    @NotNull
    private final String workingDirectory;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {new LinkedHashMapSerializer(StringSerializer.INSTANCE, Analyzer$$serializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE), null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), null};

    @SerialName("output_path")
    public static /* synthetic */ void getOutputPath$annotations() {
    }

    @SerialName("project_root")
    public static /* synthetic */ void getProjectRoot$annotations() {
    }

    @SerialName("multiple_project_root")
    public static /* synthetic */ void getMultipleProjectRoot$annotations() {
    }

    @SerialName("result_source_files")
    public static /* synthetic */ void getResultSourceFiles$annotations() {
    }

    @SerialName("working_directory")
    public static /* synthetic */ void getWorkingDirectory$annotations() {
    }

    @NotNull
    public final Map<String, Analyzer> component1() {
        return this.analyzers;
    }

    @NotNull
    public final List<String> component2() {
        return this.command;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final String component4() {
        return this.outputPath;
    }

    @NotNull
    public final String component5() {
        return this.projectRoot;
    }

    @NotNull
    public final List<String> component6() {
        return this.multipleProjectRoot;
    }

    @NotNull
    public final Map<String, String> component7() {
        return this.resultSourceFiles;
    }

    @NotNull
    public final String component8() {
        return this.workingDirectory;
    }

    @NotNull
    public final Tool copy(@NotNull Map<String, Analyzer> map, @NotNull List<String> list, @NotNull String name, @NotNull String outputPath, @NotNull String projectRoot, @NotNull List<String> list2, @NotNull Map<String, String> map2, @NotNull String workingDirectory) {
        Intrinsics.checkNotNullParameter(map, "analyzers");
        Intrinsics.checkNotNullParameter(list, "command");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(outputPath, "outputPath");
        Intrinsics.checkNotNullParameter(projectRoot, "projectRoot");
        Intrinsics.checkNotNullParameter(list2, "multipleProjectRoot");
        Intrinsics.checkNotNullParameter(map2, "resultSourceFiles");
        Intrinsics.checkNotNullParameter(workingDirectory, "workingDirectory");
        return new Tool(map, list, name, outputPath, projectRoot, list2, map2, workingDirectory);
    }

    public static /* synthetic */ Tool copy$default(Tool tool, Map map, List list, String str, String str2, String str3, List list2, Map map2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            map = tool.analyzers;
        }
        if ((i & 2) != 0) {
            list = tool.command;
        }
        if ((i & 4) != 0) {
            str = tool.name;
        }
        if ((i & 8) != 0) {
            str2 = tool.outputPath;
        }
        if ((i & 16) != 0) {
            str3 = tool.projectRoot;
        }
        if ((i & 32) != 0) {
            list2 = tool.multipleProjectRoot;
        }
        if ((i & 64) != 0) {
            map2 = tool.resultSourceFiles;
        }
        if ((i & 128) != 0) {
            str4 = tool.workingDirectory;
        }
        return tool.copy(map, list, str, str2, str3, list2, map2, str4);
    }

    @NotNull
    public String toString() {
        return "Tool(analyzers=" + this.analyzers + ", command=" + this.command + ", name=" + this.name + ", outputPath=" + this.outputPath + ", projectRoot=" + this.projectRoot + ", multipleProjectRoot=" + this.multipleProjectRoot + ", resultSourceFiles=" + this.resultSourceFiles + ", workingDirectory=" + this.workingDirectory + ")";
    }

    public int hashCode() {
        int result = this.analyzers.hashCode();
        return (((((((((((((result * 31) + this.command.hashCode()) * 31) + this.name.hashCode()) * 31) + this.outputPath.hashCode()) * 31) + this.projectRoot.hashCode()) * 31) + this.multipleProjectRoot.hashCode()) * 31) + this.resultSourceFiles.hashCode()) * 31) + this.workingDirectory.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tool)) {
            return false;
        }
        Tool tool = (Tool) other;
        return Intrinsics.areEqual(this.analyzers, tool.analyzers) && Intrinsics.areEqual(this.command, tool.command) && Intrinsics.areEqual(this.name, tool.name) && Intrinsics.areEqual(this.outputPath, tool.outputPath) && Intrinsics.areEqual(this.projectRoot, tool.projectRoot) && Intrinsics.areEqual(this.multipleProjectRoot, tool.multipleProjectRoot) && Intrinsics.areEqual(this.resultSourceFiles, tool.resultSourceFiles) && Intrinsics.areEqual(this.workingDirectory, tool.workingDirectory);
    }

    /* compiled from: AnalysisMetadata.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/metadata/Tool$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/metadata/Tool;", "corax-framework"})
    /* loaded from: Tool$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Tool> serializer() {
            return Tool$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Tool self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, serializationStrategyArr[0], self.analyzers);
        output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.command);
        output.encodeStringElement(serialDesc, 2, self.name);
        output.encodeStringElement(serialDesc, 3, self.outputPath);
        output.encodeStringElement(serialDesc, 4, self.projectRoot);
        output.encodeSerializableElement(serialDesc, 5, serializationStrategyArr[5], self.multipleProjectRoot);
        output.encodeSerializableElement(serialDesc, 6, serializationStrategyArr[6], self.resultSourceFiles);
        output.encodeStringElement(serialDesc, 7, self.workingDirectory);
    }

    public /* synthetic */ Tool(int seen0, Map analyzers, List command, String name, String outputPath, String projectRoot, List multipleProjectRoot, Map resultSourceFiles, String workingDirectory, SerializationConstructorMarker serializationConstructorMarker) {
        if (255 != (255 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 255, Tool$$serializer.INSTANCE.getDescriptor());
        }
        this.analyzers = analyzers;
        this.command = command;
        this.name = name;
        this.outputPath = outputPath;
        this.projectRoot = projectRoot;
        this.multipleProjectRoot = multipleProjectRoot;
        this.resultSourceFiles = resultSourceFiles;
        this.workingDirectory = workingDirectory;
    }

    public Tool(@NotNull Map<String, Analyzer> map, @NotNull List<String> list, @NotNull String name, @NotNull String outputPath, @NotNull String projectRoot, @NotNull List<String> list2, @NotNull Map<String, String> map2, @NotNull String workingDirectory) {
        Intrinsics.checkNotNullParameter(map, "analyzers");
        Intrinsics.checkNotNullParameter(list, "command");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(outputPath, "outputPath");
        Intrinsics.checkNotNullParameter(projectRoot, "projectRoot");
        Intrinsics.checkNotNullParameter(list2, "multipleProjectRoot");
        Intrinsics.checkNotNullParameter(map2, "resultSourceFiles");
        Intrinsics.checkNotNullParameter(workingDirectory, "workingDirectory");
        this.analyzers = map;
        this.command = list;
        this.name = name;
        this.outputPath = outputPath;
        this.projectRoot = projectRoot;
        this.multipleProjectRoot = list2;
        this.resultSourceFiles = map2;
        this.workingDirectory = workingDirectory;
    }

    @NotNull
    public final Map<String, Analyzer> getAnalyzers() {
        return this.analyzers;
    }

    @NotNull
    public final List<String> getCommand() {
        return this.command;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getOutputPath() {
        return this.outputPath;
    }

    @NotNull
    public final String getProjectRoot() {
        return this.projectRoot;
    }

    @NotNull
    public final List<String> getMultipleProjectRoot() {
        return this.multipleProjectRoot;
    }

    @NotNull
    public final Map<String, String> getResultSourceFiles() {
        return this.resultSourceFiles;
    }

    @NotNull
    public final String getWorkingDirectory() {
        return this.workingDirectory;
    }
}
