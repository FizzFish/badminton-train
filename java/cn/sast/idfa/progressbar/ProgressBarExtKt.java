package cn.sast.idfa.progressbar;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.idfa.progressbar.ProgressBarExt;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarRenderer;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProgressBarExt.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"�� \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\u001aQ\u0010��\u001a\u0002H\u0001\"\b\b��\u0010\u0001*\u00020\u0002*\u00020\u000326\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\u0010\n\u001a\n\u0010��\u001a\u00020\u000b*\u00020\u0003¨\u0006\f"}, d2 = {"wrapper", "Render", "Lme/tongfei/progressbar/ProgressBarRenderer;", "Lme/tongfei/progressbar/ProgressBar;", "newRenderWrapper", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "processBar", "render", "(Lme/tongfei/progressbar/ProgressBar;Lkotlin/jvm/functions/Function2;)Lme/tongfei/progressbar/ProgressBarRenderer;", "Lcn/sast/idfa/progressbar/ProgressBarExt$DefaultProcessInfoRenderer;", "corax-idfa-framework"})
/* loaded from: ProgressBarExtKt.class */
public final class ProgressBarExtKt {
    @NotNull
    public static final <Render extends ProgressBarRenderer> Render wrapper(@NotNull ProgressBar $this$wrapper, @NotNull Function2<? super ProgressBar, ? super ProgressBarRenderer, ? extends Render> function2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter($this$wrapper, "<this>");
        Intrinsics.checkNotNullParameter(function2, "newRenderWrapper");
        Field it = ProgressBar.class.getDeclaredField("action");
        it.setAccessible(true);
        Object action = it.get($this$wrapper);
        Field declaredField = action.getClass().getDeclaredField("renderer");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(action);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type me.tongfei.progressbar.ProgressBarRenderer");
        ProgressBarRenderer render = (ProgressBarRenderer) obj;
        Render render2 = (Render) function2.invoke($this$wrapper, render);
        declaredField.set(action, render2);
        return render2;
    }

    @NotNull
    public static final ProgressBarExt.DefaultProcessInfoRenderer wrapper(@NotNull ProgressBar $this$wrapper) {
        Intrinsics.checkNotNullParameter($this$wrapper, "<this>");
        return (ProgressBarExt.DefaultProcessInfoRenderer) wrapper($this$wrapper, ProgressBarExtKt::wrapper$lambda$2);
    }

    private static final ProgressBarExt.DefaultProcessInfoRenderer wrapper$lambda$2(ProgressBar processBar, ProgressBarRenderer render) {
        Intrinsics.checkNotNullParameter(processBar, "processBar");
        Intrinsics.checkNotNullParameter(render, "render");
        return new ProgressBarExt.DefaultProcessInfoRenderer(processBar, render);
    }
}
