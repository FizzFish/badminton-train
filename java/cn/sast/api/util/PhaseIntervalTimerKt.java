package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PhaseIntervalTimer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��$\n��\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0004\n��\n\u0002\u0010\t\n\u0002\b\u0002\u001a \u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u0006\u0010\t\u001a\u00020\n\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"retainDecimalPlaces", "", "value", "scale", "", "roundingMode", "Ljava/math/RoundingMode;", "nanoTimeInSeconds", "", "currentNanoTime", "", "CONVERT_TO_SECONDS", "corax-api"})
@SourceDebugExtension({"SMAP\nPhaseIntervalTimer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PhaseIntervalTimer.kt\ncn/sast/api/util/PhaseIntervalTimerKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,124:1\n1#2:125\n*E\n"})
/* loaded from: PhaseIntervalTimerKt.class */
public final class PhaseIntervalTimerKt {
    public static final double CONVERT_TO_SECONDS = 1.0E9d;

    public static /* synthetic */ double retainDecimalPlaces$default(double d, int i, RoundingMode roundingMode, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            roundingMode = RoundingMode.HALF_EVEN;
        }
        return retainDecimalPlaces(d, i, roundingMode);
    }

    public static final double retainDecimalPlaces(double value, int scale, @NotNull RoundingMode roundingMode) {
        Intrinsics.checkNotNullParameter(roundingMode, "roundingMode");
        return !(!Double.isInfinite(value) && !Double.isNaN(value)) ? value : new BigDecimal(value).setScale(scale, roundingMode).doubleValue();
    }

    public static final double nanoTimeInSeconds(@Nullable Number $this$nanoTimeInSeconds, int scale) {
        if ($this$nanoTimeInSeconds != null) {
            Double dValueOf = Double.valueOf($this$nanoTimeInSeconds.doubleValue());
            double it = dValueOf.doubleValue();
            Double d = !Double.isInfinite(it) && !Double.isNaN(it) ? dValueOf : null;
            if (d != null) {
                return retainDecimalPlaces$default(d.doubleValue() / 1.0E9d, scale, null, 4, null);
            }
        }
        return -1.0d;
    }

    public static /* synthetic */ double nanoTimeInSeconds$default(Number number, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 3;
        }
        return nanoTimeInSeconds(number, i);
    }

    public static final long currentNanoTime() {
        return System.nanoTime();
    }
}
