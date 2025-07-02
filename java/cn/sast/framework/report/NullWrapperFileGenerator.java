package cn.sast.framework.report;

import cn.sast.api.report.IBugResInfo;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IWrapperFileGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/NullWrapperFileGenerator;", "Lcn/sast/framework/report/IWrapperFileGenerator;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "makeWrapperFile", "Lcn/sast/common/IResFile;", "fileWrapperOutPutDir", "Lcn/sast/common/IResDirectory;", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "corax-framework"})
/* loaded from: NullWrapperFileGenerator.class */
public final class NullWrapperFileGenerator implements IWrapperFileGenerator {

    @NotNull
    public static final NullWrapperFileGenerator INSTANCE = new NullWrapperFileGenerator();

    private NullWrapperFileGenerator() {
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @NotNull
    public String getInternalFileName(@NotNull IBugResInfo resInfo) {
        return IWrapperFileGenerator.DefaultImpls.getInternalFileName(this, resInfo);
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @NotNull
    public String getName() {
        return "null";
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @Nullable
    public IResFile makeWrapperFile(@NotNull IResDirectory fileWrapperOutPutDir, @NotNull IBugResInfo resInfo) {
        Intrinsics.checkNotNullParameter(fileWrapperOutPutDir, "fileWrapperOutPutDir");
        Intrinsics.checkNotNullParameter(resInfo, "resInfo");
        return null;
    }
}
