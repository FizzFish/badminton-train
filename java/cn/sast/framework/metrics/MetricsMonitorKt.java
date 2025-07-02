package cn.sast.framework.metrics;

import cn.sast.api.util.PhaseIntervalTimerKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MetricsMonitor.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��4\n��\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010��\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H��¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH��\u001a\u0018\u0010\u000b\u001a\u00020\f*\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nH��\u001a\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0001\"\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0015"}, d2 = {"timeSub", "", "time", "begin", "(Ljava/lang/Long;J)Ljava/lang/Long;", "fmt", "", "", "postfix", "scale", "", "inMemGB", "", "getDateStringFromMillis", "duration", "Lkotlin/time/Duration;", "getDateStringFromMillis-LRDsOJo", "(J)Ljava/lang/String;", "dateFormat", "Ljava/text/SimpleDateFormat;", "beginMillis", "corax-framework"})
@SourceDebugExtension({"SMAP\nMetricsMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MetricsMonitor.kt\ncn/sast/framework/metrics/MetricsMonitorKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,249:1\n1#2:250\n*E\n"})
/* loaded from: MetricsMonitorKt.class */
public final class MetricsMonitorKt {

    @NotNull
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    @Nullable
    public static final Long timeSub(@Nullable Long time, long begin) {
        if (time != null) {
            long it = time.longValue();
            Long l = (it > begin ? 1 : (it == begin ? 0 : -1)) >= 0 ? time : null;
            if (l != null) {
                long it2 = l.longValue();
                return Long.valueOf(it2 - begin);
            }
        }
        return null;
    }

    @NotNull
    public static final String fmt(@NotNull Number $this$fmt, @NotNull String postfix, int scale) {
        Intrinsics.checkNotNullParameter($this$fmt, "<this>");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf($this$fmt.doubleValue())};
        String str = String.format("%." + scale + "f" + postfix, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public static /* synthetic */ String fmt$default(Number number, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 2;
        }
        return fmt(number, str, i);
    }

    public static final double inMemGB(@Nullable Number $this$inMemGB, int scale) {
        if ($this$inMemGB != null) {
            return PhaseIntervalTimerKt.retainDecimalPlaces$default($this$inMemGB.doubleValue(), scale, null, 4, null);
        }
        return -1.0d;
    }

    public static /* synthetic */ double inMemGB$default(Number number, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 3;
        }
        return inMemGB(number, i);
    }

    @NotNull
    /* renamed from: getDateStringFromMillis-LRDsOJo, reason: not valid java name */
    public static final String m307getDateStringFromMillisLRDsOJo(long duration) {
        return getDateStringFromMillis(Duration.getInWholeMilliseconds-impl(duration));
    }

    @NotNull
    public static final String getDateStringFromMillis(long beginMillis) {
        Date date = new Date(beginMillis);
        String str = dateFormat.format(date);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
