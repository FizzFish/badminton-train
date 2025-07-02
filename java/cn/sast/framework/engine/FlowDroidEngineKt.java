package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.jimple.infoflow.AbstractInfoflow;
import soot.jimple.infoflow.InfoflowConfiguration;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;
import soot.jimple.infoflow.sourcesSinks.manager.ISourceSinkManager;

/* compiled from: FlowDroidEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002\u001a\"\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¨\u0006\n"}, d2 = {"fix", "", "Lsoot/jimple/infoflow/InfoflowConfiguration;", "runAnalysisReflect", "Lsoot/jimple/infoflow/AbstractInfoflow;", "sourcesSinks", "Lsoot/jimple/infoflow/sourcesSinks/manager/ISourceSinkManager;", "additionalSeeds", "", "", "corax-framework"})
/* loaded from: FlowDroidEngineKt.class */
public final class FlowDroidEngineKt {
    public static final void fix(@NotNull InfoflowConfiguration $this$fix) {
        Intrinsics.checkNotNullParameter($this$fix, "<this>");
        KotlinLogging kotlinLogging = KotlinLogging.INSTANCE;
        String name = InfoflowConfiguration.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        KLogger logger = kotlinLogging.logger(name);
        if (($this$fix instanceof InfoflowAndroidConfiguration) && ((InfoflowAndroidConfiguration) $this$fix).getSourceSinkConfig().getEnableLifecycleSources() && ((InfoflowAndroidConfiguration) $this$fix).getIccConfig().isIccEnabled()) {
            logger.warn("ICC model specified, automatically disabling lifecycle sources");
            ((InfoflowAndroidConfiguration) $this$fix).getSourceSinkConfig().setEnableLifecycleSources(false);
        }
    }

    public static final void runAnalysisReflect(@NotNull AbstractInfoflow $this$runAnalysisReflect, @NotNull ISourceSinkManager sourcesSinks, @Nullable Set<String> set) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter($this$runAnalysisReflect, "<this>");
        Intrinsics.checkNotNullParameter(sourcesSinks, "sourcesSinks");
        Method it = AbstractInfoflow.class.getDeclaredMethod("runAnalysis", ISourceSinkManager.class, Set.class);
        it.setAccessible(true);
        it.invoke($this$runAnalysisReflect, sourcesSinks, set);
    }
}
