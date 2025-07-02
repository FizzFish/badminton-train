package cn.sast.framework.report.coverage;

import cn.sast.api.report.ClassResInfo;
import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import cn.sast.framework.report.IProjectFileLocator;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.jacoco.report.InputStreamSourceFileLocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;

/* compiled from: JacocoSourceLoator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lcn/sast/framework/report/coverage/JacocoSourceLocator;", "Lorg/jacoco/report/InputStreamSourceFileLocator;", "sourceLocator", "Lcn/sast/framework/report/IProjectFileLocator;", "encoding", "", "tabWidth", "", "<init>", "(Lcn/sast/framework/report/IProjectFileLocator;Ljava/lang/String;I)V", "getSourceStream", "Ljava/io/InputStream;", "path", "corax-framework"})
@SourceDebugExtension({"SMAP\nJacocoSourceLoator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JacocoSourceLoator.kt\ncn/sast/framework/report/coverage/JacocoSourceLocator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,38:1\n1#2:39\n*E\n"})
/* loaded from: JacocoSourceLocator.class */
public final class JacocoSourceLocator extends InputStreamSourceFileLocator {

    @NotNull
    private final IProjectFileLocator sourceLocator;

    public /* synthetic */ JacocoSourceLocator(IProjectFileLocator iProjectFileLocator, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(iProjectFileLocator, (i2 & 2) != 0 ? "utf-8" : str, (i2 & 4) != 0 ? 4 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JacocoSourceLocator(@NotNull IProjectFileLocator sourceLocator, @NotNull String encoding, int tabWidth) {
        super(encoding, tabWidth);
        Intrinsics.checkNotNullParameter(sourceLocator, "sourceLocator");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        this.sourceLocator = sourceLocator;
    }

    @Nullable
    protected InputStream getSourceStream(@NotNull String path) throws IOException {
        IResFile src;
        Intrinsics.checkNotNullParameter(path, "path");
        IResFile it = (IResFile) SequencesKt.firstOrNull(this.sourceLocator.findFromFileIndexMap(StringsKt.split$default(path, new char[]{'/', '\\'}, false, 0, 6, (Object) null), AbstractFileIndexer.Companion.getDefaultClassCompareMode()));
        if (it != null) {
            OpenOption[] openOptionArr = new OpenOption[0];
            InputStream inputStreamNewInputStream = Files.newInputStream(it.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            return inputStreamNewInputStream;
        }
        String ext = FilenameUtils.getExtension(path);
        if (!ResourceKt.getJavaExtensions().contains(ext)) {
            return null;
        }
        Intrinsics.checkNotNull(ext);
        String mayClassName = StringsKt.replace$default(StringsKt.replace$default(StringsKt.removeSuffix(StringsKt.removeSuffix(path, ext), "."), "/", ".", false, 4, (Object) null), "\\", ".", false, 4, (Object) null);
        SootClass sc = Scene.v().getSootClassUnsafe(mayClassName, false);
        if (sc == null || (src = IProjectFileLocator.DefaultImpls.get$default(this.sourceLocator, ClassResInfo.Companion.of(sc), null, 2, null)) == null) {
            return null;
        }
        OpenOption[] openOptionArr2 = new OpenOption[0];
        InputStream inputStreamNewInputStream2 = Files.newInputStream(src.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr2, openOptionArr2.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream2, "newInputStream(...)");
        return inputStreamNewInputStream2;
    }
}
