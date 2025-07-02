package cn.sast.framework;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.lang.reflect.Field;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Scene;

/* compiled from: SootCtx.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001e\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"excludedPackages", "Ljava/util/LinkedList;", "", "Lsoot/Scene;", "getExcludedPackages", "(Lsoot/Scene;)Ljava/util/LinkedList;", "sootCtx", "Lcn/sast/framework/SootCtx;", "Lcn/sast/api/config/MainConfig;", "getSootCtx", "(Lcn/sast/api/config/MainConfig;)Lcn/sast/framework/SootCtx;", "corax-framework"})
/* loaded from: SootCtxKt.class */
public final class SootCtxKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final LinkedList<String> getExcludedPackages(Scene $this$excludedPackages) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field field = $this$excludedPackages.getClass().getDeclaredField("excludedPackages");
        field.setAccessible(true);
        Object obj = field.get($this$excludedPackages);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.LinkedList<kotlin.String>");
        return (LinkedList) obj;
    }

    @NotNull
    public static final SootCtx getSootCtx(@NotNull MainConfig $this$sootCtx) {
        Intrinsics.checkNotNullParameter($this$sootCtx, "<this>");
        return new SootCtx($this$sootCtx);
    }
}
