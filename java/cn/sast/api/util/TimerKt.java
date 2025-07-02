package cn.sast.api.util;

import cn.sast.api.util.PhaseIntervalTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Timer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\u0004\u0018\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0086\bø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0006"}, d2 = {"bracket", "T", "Lcn/sast/api/util/PhaseIntervalTimer;", "block", "Lkotlin/Function0;", "(Lcn/sast/api/util/PhaseIntervalTimer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "corax-api"})
/* loaded from: TimerKt.class */
public final class TimerKt {
    public static final <T> T bracket(@Nullable PhaseIntervalTimer phaseIntervalTimer, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        if (phaseIntervalTimer == null) {
            return (T) function0.invoke();
        }
        PhaseIntervalTimer.Snapshot snapshotStart = phaseIntervalTimer.start();
        try {
            T t = (T) function0.invoke();
            InlineMarker.finallyStart(1);
            phaseIntervalTimer.stop(snapshotStart);
            InlineMarker.finallyEnd(1);
            return t;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            phaseIntervalTimer.stop(snapshotStart);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
}
