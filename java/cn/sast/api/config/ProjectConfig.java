package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.charleskorn.kaml.Yaml;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProjectConfig.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� &2\u00020\u0001:\u0002&'B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020��2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR&\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n��\u0012\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lcn/sast/api/config/ProjectConfig;", "", "processRegex", "Lcn/sast/api/config/ProcessRegex;", "<init>", "(Lcn/sast/api/config/ProcessRegex;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcn/sast/api/config/ProcessRegex;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getProcessRegex$annotations", "()V", "getProcessRegex", "()Lcn/sast/api/config/ProcessRegex;", "ymlFile", "Ljava/io/File;", "getYmlFile$annotations", "getYmlFile", "()Ljava/io/File;", "setYmlFile", "(Ljava/io/File;)V", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "Companion", "$serializer", "corax-api"})
/* loaded from: ProjectConfig.class */
public final class ProjectConfig {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final ProcessRegex processRegex;

    @Nullable
    private File ymlFile;

    @NotNull
    public static final String RECORD_FILE_NAME = "scan-classifier-info.json";

    @SerialName("process-regex")
    public static /* synthetic */ void getProcessRegex$annotations() {
    }

    @Transient
    public static /* synthetic */ void getYmlFile$annotations() {
    }

    @NotNull
    public final ProcessRegex component1() {
        return this.processRegex;
    }

    @NotNull
    public final ProjectConfig copy(@NotNull ProcessRegex processRegex) {
        Intrinsics.checkNotNullParameter(processRegex, "processRegex");
        return new ProjectConfig(processRegex);
    }

    public static /* synthetic */ ProjectConfig copy$default(ProjectConfig projectConfig, ProcessRegex processRegex, int i, Object obj) {
        if ((i & 1) != 0) {
            processRegex = projectConfig.processRegex;
        }
        return projectConfig.copy(processRegex);
    }

    @NotNull
    public String toString() {
        return "ProjectConfig(processRegex=" + this.processRegex + ")";
    }

    public int hashCode() {
        return this.processRegex.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ProjectConfig) && Intrinsics.areEqual(this.processRegex, ((ProjectConfig) other).processRegex);
    }

    public ProjectConfig() {
        this((ProcessRegex) null, 1, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(ProjectConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z = output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.processRegex, new ProcessRegex((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null));
        if (z) {
            output.encodeSerializableElement(serialDesc, 0, ProcessRegex$$serializer.INSTANCE, self.processRegex);
        }
    }

    public /* synthetic */ ProjectConfig(int seen0, ProcessRegex processRegex, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, ProjectConfig$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.processRegex = new ProcessRegex((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
        } else {
            this.processRegex = processRegex;
        }
        this.ymlFile = null;
    }

    public ProjectConfig(@NotNull ProcessRegex processRegex) {
        Intrinsics.checkNotNullParameter(processRegex, "processRegex");
        this.processRegex = processRegex;
    }

    public /* synthetic */ ProjectConfig(ProcessRegex processRegex, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ProcessRegex((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null) : processRegex);
    }

    @NotNull
    public final ProcessRegex getProcessRegex() {
        return this.processRegex;
    }

    @Nullable
    public final File getYmlFile() {
        return this.ymlFile;
    }

    public final void setYmlFile(@Nullable File file) {
        this.ymlFile = file;
    }

    /* compiled from: ProjectConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lcn/sast/api/config/ProjectConfig$Companion;", "", "<init>", "()V", "RECORD_FILE_NAME", "", "load", "Lcn/sast/api/config/ProjectConfig;", "yamlFile", "Ljava/io/File;", "serializer", "Lkotlinx/serialization/KSerializer;", "corax-api"})
    @SourceDebugExtension({"SMAP\nProjectConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProjectConfig.kt\ncn/sast/api/config/ProjectConfig$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"})
    /* loaded from: ProjectConfig$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<ProjectConfig> serializer() {
            return ProjectConfig$$serializer.INSTANCE;
        }

        @NotNull
        public final ProjectConfig load(@NotNull File yamlFile) {
            Intrinsics.checkNotNullParameter(yamlFile, "yamlFile");
            Object objDecodeFromString = Yaml.Companion.getDefault().decodeFromString(serializer(), FilesKt.readText$default(yamlFile, (Charset) null, 1, (Object) null));
            ProjectConfig it = (ProjectConfig) objDecodeFromString;
            it.setYmlFile(yamlFile);
            return (ProjectConfig) objDecodeFromString;
        }
    }
}
