package cn.sast.framework.report;

import cn.sast.api.report.IBugResInfo;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"}, d2 = {"Lcn/sast/framework/report/IWrapperFileGenerator;", "", "name", "", "getName", "()Ljava/lang/String;", "makeWrapperFile", "Lcn/sast/common/IResFile;", "fileWrapperOutPutDir", "Lcn/sast/common/IResDirectory;", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "getInternalFileName", "corax-framework"})
/* loaded from: IWrapperFileGenerator.class */
public interface IWrapperFileGenerator {
    @NotNull
    String getName();

    @Nullable
    IResFile makeWrapperFile(@NotNull IResDirectory iResDirectory, @NotNull IBugResInfo iBugResInfo);

    @NotNull
    String getInternalFileName(@NotNull IBugResInfo iBugResInfo);

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IWrapperFileGenerator$DefaultImpls.class */
    public static final class DefaultImpls {
        @NotNull
        public static String getInternalFileName(@NotNull IWrapperFileGenerator $this, @NotNull IBugResInfo resInfo) {
            Intrinsics.checkNotNullParameter(resInfo, "resInfo");
            return resInfo.getPath();
        }
    }
}
