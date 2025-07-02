package cn.sast.coroutines.caffine.impl;

import cn.sast.api.util.PhaseIntervalTimerKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RecCoroutineCacheImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002*\u001c\u0010\u0003\u001a\u0004\b��\u0010\u0004\"\b\u0012\u0004\u0012\u0002H\u00040\u00052\b\u0012\u0004\u0012\u0002H\u00040\u0005*&\u0010\u0006\u001a\u0004\b��\u0010\u0004\"\b\u0012\u0004\u0012\u0002H\u0004`\u00072\u0012\u0012\u0004\u0012\u0002H\u00040\u0005j\b\u0012\u0004\u0012\u0002H\u0004`\u0007¨\u0006\b"}, d2 = {"pp", "", "Lcom/github/benmanes/caffeine/cache/stats/CacheStats;", "RecCoroutineCacheValue", "V", "Lkotlinx/coroutines/Deferred;", "CacheValue", "Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheValue;", "corax-api"})
/* loaded from: RecCoroutineCacheImplKt.class */
public final class RecCoroutineCacheImplKt {
    private static final double pp$n(double $this$pp_u24n) {
        return PhaseIntervalTimerKt.retainDecimalPlaces$default($this$pp_u24n, 2, null, 4, null);
    }

    @NotNull
    public static final String pp(@NotNull CacheStats $this$pp) {
        Intrinsics.checkNotNullParameter($this$pp, "<this>");
        double dPp$n = pp$n($this$pp.hitRate());
        double dPp$n2 = pp$n($this$pp.missRate());
        double dPp$n3 = pp$n($this$pp.averageLoadPenalty());
        pp$n($this$pp.loadFailureRate());
        return "hit:" + dPp$n + " miss:" + dPp$n + " penalty:" + dPp$n2 + " failure:" + dPp$n + " " + dPp$n3;
    }
}
