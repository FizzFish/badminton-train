package cn.sast.framework.report;

import cn.sast.common.IResDirectory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IReportConsumer;
import cn.sast.framework.result.OutputType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReportConsumer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018��2\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/report/ReportConsumer;", "Lcn/sast/framework/report/IReportConsumer;", "type", "Lcn/sast/framework/result/OutputType;", "outputDir", "Lcn/sast/common/IResDirectory;", "<init>", "(Lcn/sast/framework/result/OutputType;Lcn/sast/common/IResDirectory;)V", "getType", "()Lcn/sast/framework/result/OutputType;", "getOutputDir", "()Lcn/sast/common/IResDirectory;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "getMetadata", "()Lcn/sast/framework/report/ReportConsumer$MetaData;", "init", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MetaData", "corax-framework"})
/* loaded from: ReportConsumer.class */
public abstract class ReportConsumer implements IReportConsumer {

    @NotNull
    private final OutputType type;

    @NotNull
    private final IResDirectory outputDir;

    @NotNull
    public abstract MetaData getMetadata();

    @Override // cn.sast.framework.report.IReportConsumer
    @Nullable
    public Object init(@NotNull Continuation<? super Unit> continuation) {
        return init$suspendImpl(this, continuation);
    }

    public ReportConsumer(@NotNull OutputType type, @NotNull IResDirectory outputDir) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        this.type = type;
        this.outputDir = outputDir;
        this.outputDir.mkdirs();
    }

    @Override // cn.sast.framework.report.IReportConsumer
    @NotNull
    public OutputType getType() {
        return this.type;
    }

    @NotNull
    public final IResDirectory getOutputDir() {
        return this.outputDir;
    }

    @Override // cn.sast.framework.report.IReportConsumer
    @Nullable
    public Object run(@NotNull IProjectFileLocator locator, @NotNull Continuation<? super Unit> continuation) {
        return IReportConsumer.DefaultImpls.run(this, locator, continuation);
    }

    /* compiled from: ReportConsumer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/report/ReportConsumer$MetaData;", "", "toolName", "", "toolVersion", "analyzerName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getToolName", "()Ljava/lang/String;", "getToolVersion", "getAnalyzerName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: ReportConsumer$MetaData.class */
    public static final class MetaData {

        @NotNull
        private final String toolName;

        @NotNull
        private final String toolVersion;

        @NotNull
        private final String analyzerName;

        @NotNull
        public final String component1() {
            return this.toolName;
        }

        @NotNull
        public final String component2() {
            return this.toolVersion;
        }

        @NotNull
        public final String component3() {
            return this.analyzerName;
        }

        @NotNull
        public final MetaData copy(@NotNull String toolName, @NotNull String toolVersion, @NotNull String analyzerName) {
            Intrinsics.checkNotNullParameter(toolName, "toolName");
            Intrinsics.checkNotNullParameter(toolVersion, "toolVersion");
            Intrinsics.checkNotNullParameter(analyzerName, "analyzerName");
            return new MetaData(toolName, toolVersion, analyzerName);
        }

        public static /* synthetic */ MetaData copy$default(MetaData metaData, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = metaData.toolName;
            }
            if ((i & 2) != 0) {
                str2 = metaData.toolVersion;
            }
            if ((i & 4) != 0) {
                str3 = metaData.analyzerName;
            }
            return metaData.copy(str, str2, str3);
        }

        @NotNull
        public String toString() {
            return "MetaData(toolName=" + this.toolName + ", toolVersion=" + this.toolVersion + ", analyzerName=" + this.analyzerName + ")";
        }

        public int hashCode() {
            int result = this.toolName.hashCode();
            return (((result * 31) + this.toolVersion.hashCode()) * 31) + this.analyzerName.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MetaData)) {
                return false;
            }
            MetaData metaData = (MetaData) other;
            return Intrinsics.areEqual(this.toolName, metaData.toolName) && Intrinsics.areEqual(this.toolVersion, metaData.toolVersion) && Intrinsics.areEqual(this.analyzerName, metaData.analyzerName);
        }

        public MetaData(@NotNull String toolName, @NotNull String toolVersion, @NotNull String analyzerName) {
            Intrinsics.checkNotNullParameter(toolName, "toolName");
            Intrinsics.checkNotNullParameter(toolVersion, "toolVersion");
            Intrinsics.checkNotNullParameter(analyzerName, "analyzerName");
            this.toolName = toolName;
            this.toolVersion = toolVersion;
            this.analyzerName = analyzerName;
        }

        @NotNull
        public final String getToolName() {
            return this.toolName;
        }

        @NotNull
        public final String getToolVersion() {
            return this.toolVersion;
        }

        @NotNull
        public final String getAnalyzerName() {
            return this.analyzerName;
        }
    }

    static /* synthetic */ Object init$suspendImpl(ReportConsumer $this, Continuation<? super Unit> continuation) {
        $this.outputDir.deleteDirectoryContents();
        $this.outputDir.mkdirs();
        return Unit.INSTANCE;
    }
}
