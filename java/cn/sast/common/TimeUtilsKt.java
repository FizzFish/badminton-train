package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import org.jetbrains.annotations.NotNull;

/* compiled from: TimeUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0014\u0010��\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"prettyPrintTime", "", "Ljava/time/LocalDateTime;", "c", "Ljava/time/temporal/ChronoUnit;", "corax-api"})
/* loaded from: TimeUtilsKt.class */
public final class TimeUtilsKt {
    public static /* synthetic */ String prettyPrintTime$default(LocalDateTime localDateTime, ChronoUnit chronoUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            chronoUnit = ChronoUnit.SECONDS;
        }
        return prettyPrintTime(localDateTime, chronoUnit);
    }

    @NotNull
    public static final String prettyPrintTime(@NotNull LocalDateTime $this$prettyPrintTime, @NotNull ChronoUnit c) {
        Intrinsics.checkNotNullParameter($this$prettyPrintTime, "<this>");
        Intrinsics.checkNotNullParameter(c, "c");
        long duration = DurationKt.toDuration(c.between($this$prettyPrintTime, LocalDateTime.now()), DurationUnit.SECONDS);
        String str = Duration.toString-impl(duration);
        long j = Duration.getInWholeSeconds-impl(duration);
        Intrinsics.checkNotNullExpressionValue(c.name().toLowerCase(Locale.ROOT), "toLowerCase(...)");
        return str + " (" + j + str + ")";
    }
}
