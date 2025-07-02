package cn.sast.framework;

import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import soot.Scene;

/* compiled from: SourceLocatorPlus.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0004"}, d2 = {"sootClassPathsCvt", "", "Lcn/sast/common/IResource;", "sourceDir", "corax-framework"})
@SourceDebugExtension({"SMAP\nSourceLocatorPlus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SourceLocatorPlus.kt\ncn/sast/framework/SourceLocatorPlusKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,116:1\n1628#2,3:117\n1797#2,3:120\n*S KotlinDebug\n*F\n+ 1 SourceLocatorPlus.kt\ncn/sast/framework/SourceLocatorPlusKt\n*L\n23#1:117,3\n27#1:120,3\n*E\n"})
/* loaded from: SourceLocatorPlusKt.class */
public final class SourceLocatorPlusKt {
    @NotNull
    public static final Set<IResource> sootClassPathsCvt(@NotNull Set<? extends IResource> set) {
        Intrinsics.checkNotNullParameter(set, "sourceDir");
        Set<? extends IResource> $this$mapTo$iv = set;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            IResource it = (IResource) item$iv;
            destination$iv.add(it.getAbsolutePath());
        }
        List $this$sootClassPathsCvt_u24lambda_u241 = CollectionsKt.toMutableList(destination$iv);
        String sootClassPath = Scene.v().getSootClassPath();
        Intrinsics.checkNotNullExpressionValue(sootClassPath, "getSootClassPath(...)");
        $this$sootClassPathsCvt_u24lambda_u241.addAll(StringsKt.split$default(sootClassPath, new String[]{File.pathSeparator}, false, 0, 6, (Object) null));
        List $this$fold$iv = $this$sootClassPathsCvt_u24lambda_u241;
        Set linkedHashSet = new LinkedHashSet();
        for (Object element$iv : $this$fold$iv) {
            Set r = linkedHashSet;
            String aPath = (String) element$iv;
            if (Intrinsics.areEqual("VIRTUAL_FS_FOR_JDK", aPath)) {
                Resource resource = Resource.INSTANCE;
                String property = System.getProperty("java.home");
                Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
                r.add(resource.of(property));
            } else {
                r.add(Resource.INSTANCE.of(aPath));
            }
            linkedHashSet = r;
        }
        Set srcTranslate = linkedHashSet;
        return srcTranslate;
    }
}
