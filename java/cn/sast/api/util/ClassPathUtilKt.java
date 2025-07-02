package cn.sast.api.util;

import cn.sast.api.report.ClassResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ModuleUtil;
import soot.SootClass;

/* compiled from: ClassPathUtil.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a\u001a\u0010��\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"classSplit", "Lkotlin/Pair;", "", "cp", "Lcn/sast/api/report/ClassResInfo;", "getSourcePathModule", "c", "Lsoot/SootClass;", "corax-api"})
/* loaded from: ClassPathUtilKt.class */
public final class ClassPathUtilKt {
    @NotNull
    public static final Pair<String, String> classSplit(@NotNull ClassResInfo cp) {
        Intrinsics.checkNotNullParameter(cp, "cp");
        return SootUtilsKt.classSplit(cp.getSc());
    }

    @Nullable
    public static final String getSourcePathModule(@NotNull SootClass c) {
        Intrinsics.checkNotNullParameter(c, "c");
        String sourcePathFromAnnotation = SootUtilsKt.getSourcePathFromAnnotation(c);
        if (sourcePathFromAnnotation == null) {
            return null;
        }
        String sourcePath = sourcePathFromAnnotation;
        ModuleUtil.ModuleClassNameWrapper wrapper = ModuleUtil.v().makeWrapper(c.getName());
        if (wrapper.getModuleName() != null) {
            sourcePath = wrapper.getModuleName() + "/" + sourcePath;
        }
        return sourcePath;
    }
}
