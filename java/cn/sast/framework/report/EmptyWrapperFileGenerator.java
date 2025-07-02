package cn.sast.framework.report;

import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IWrapperFileGenerator;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcn/sast/framework/report/EmptyWrapperFileGenerator;", "Lcn/sast/framework/report/IWrapperFileGenerator;", "<init>", "()V", "logger", "Lmu/KLogger;", "name", "", "getName", "()Ljava/lang/String;", "makeWrapperFileContent", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "makeWrapperFile", "Lcn/sast/common/IResFile;", "fileWrapperOutPutDir", "Lcn/sast/common/IResDirectory;", "corax-framework"})
/* loaded from: EmptyWrapperFileGenerator.class */
public final class EmptyWrapperFileGenerator implements IWrapperFileGenerator {

    @NotNull
    public static final EmptyWrapperFileGenerator INSTANCE = new EmptyWrapperFileGenerator();

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(EmptyWrapperFileGenerator::logger$lambda$0);

    private EmptyWrapperFileGenerator() {
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @NotNull
    public String getInternalFileName(@NotNull IBugResInfo resInfo) {
        return IWrapperFileGenerator.DefaultImpls.getInternalFileName(this, resInfo);
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @NotNull
    public String getName() {
        return "empty";
    }

    private final String makeWrapperFileContent(IBugResInfo resInfo) {
        String strRepeat;
        if (resInfo instanceof ClassResInfo) {
            int maxLine = ((ClassResInfo) resInfo).getMaxLine();
            if (maxLine > 8000) {
                maxLine = 8000;
            }
            strRepeat = StringsKt.repeat("\n", maxLine);
        } else {
            strRepeat = "\n";
        }
        String text = strRepeat;
        return text;
    }

    @Override // cn.sast.framework.report.IWrapperFileGenerator
    @Nullable
    public IResFile makeWrapperFile(@NotNull IResDirectory fileWrapperOutPutDir, @NotNull IBugResInfo resInfo) {
        Intrinsics.checkNotNullParameter(fileWrapperOutPutDir, "fileWrapperOutPutDir");
        Intrinsics.checkNotNullParameter(resInfo, "resInfo");
        String fileName = getInternalFileName(resInfo);
        IResFile missingSourceFile = fileWrapperOutPutDir.resolve(getName()).resolve(fileName).toFile();
        if (missingSourceFile.getExists()) {
            if (missingSourceFile.isFile()) {
                return missingSourceFile;
            }
            logger.error(() -> {
                return makeWrapperFile$lambda$1(r1);
            });
            return null;
        }
        String text = makeWrapperFileContent(resInfo);
        try {
            IResource parent = missingSourceFile.getParent();
            if (parent != null) {
                parent.mkdirs();
            }
            ResourceKt.writeText$default(missingSourceFile, text, null, 2, null);
            return missingSourceFile.toFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final Object makeWrapperFile$lambda$1(IResFile $missingSourceFile) {
        return "duplicate folder exists " + $missingSourceFile;
    }
}
