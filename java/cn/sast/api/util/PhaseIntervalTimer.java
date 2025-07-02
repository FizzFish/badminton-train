package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PhaseIntervalTimer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018�� *2\u00020\u0001:\u0002)*B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010$\u001a\u00020#J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020#J\b\u0010(\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R$\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR$\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0014\u0010\nR\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!X\u0082\u000e¢\u0006\u0002\n��¨\u0006+"}, d2 = {"Lcn/sast/api/util/PhaseIntervalTimer;", "", "<init>", "()V", "id", "Lkotlinx/atomicfu/AtomicInt;", "value", "", "startTime", "getStartTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "endTime", "getEndTime", "_elapsedTime", "prefix", "", "getPrefix", "()Ljava/lang/String;", "elapsedTime", "getElapsedTime", "phaseTimerActiveCount", "phaseStartCount", "getPhaseStartCount", "()Lkotlinx/atomicfu/AtomicInt;", "phaseAverageElapsedTime", "", "getPhaseAverageElapsedTime", "()Ljava/lang/Double;", "ranges", "", "Lcn/sast/api/util/TimeRange;", "queue", "Ljava/util/TreeMap;", "", "Lcn/sast/api/util/PhaseIntervalTimer$Snapshot;", "start", "stop", "", "snapshot", "toString", "Snapshot", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nPhaseIntervalTimer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PhaseIntervalTimer.kt\ncn/sast/api/util/PhaseIntervalTimer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,124:1\n1#2:125\n*E\n"})
/* loaded from: PhaseIntervalTimer.class */
public class PhaseIntervalTimer {

    @Nullable
    private Long startTime;

    @Nullable
    private Long endTime;

    @Nullable
    private volatile Long _elapsedTime;

    @Nullable
    private Long elapsedTime;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(PhaseIntervalTimer::logger$lambda$9);

    @NotNull
    private final AtomicInt id = AtomicFU.atomic(0);

    @NotNull
    private AtomicInt phaseTimerActiveCount = AtomicFU.atomic(0);

    @NotNull
    private AtomicInt phaseStartCount = AtomicFU.atomic(0);

    @NotNull
    private List<TimeRange> ranges = new LinkedList();

    @NotNull
    private TreeMap<Integer, Snapshot> queue = new TreeMap<>();

    /* compiled from: PhaseIntervalTimer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0019\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000e\u0010\r\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b\u000eJ\u001d\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcn/sast/api/util/PhaseIntervalTimer$Snapshot;", "", "startTime", "", "id", "", "<init>", "(JI)V", "getStartTime", "()J", "getId$corax_api", "()I", "component1", "component2", "component2$corax_api", "copy", "equals", "", "other", "hashCode", "toString", "", "corax-api"})
    /* loaded from: PhaseIntervalTimer$Snapshot.class */
    public static final class Snapshot {
        private final long startTime;
        private final int id;

        public final long component1() {
            return this.startTime;
        }

        public final int component2$corax_api() {
            return this.id;
        }

        @NotNull
        public final Snapshot copy(long startTime, int id) {
            return new Snapshot(startTime, id);
        }

        public static /* synthetic */ Snapshot copy$default(Snapshot snapshot, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = snapshot.startTime;
            }
            if ((i2 & 2) != 0) {
                i = snapshot.id;
            }
            return snapshot.copy(j, i);
        }

        @NotNull
        public String toString() {
            long j = this.startTime;
            int i = this.id;
            return "Snapshot(startTime=" + j + ", id=" + j + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.startTime);
            return (result * 31) + Integer.hashCode(this.id);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Snapshot)) {
                return false;
            }
            Snapshot snapshot = (Snapshot) other;
            return this.startTime == snapshot.startTime && this.id == snapshot.id;
        }

        public Snapshot(long startTime, int id) {
            this.startTime = startTime;
            this.id = id;
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final int getId$corax_api() {
            return this.id;
        }
    }

    @Nullable
    public final Long getStartTime() {
        return this.startTime;
    }

    @Nullable
    public final Long getEndTime() {
        return this.endTime;
    }

    @NotNull
    public String getPrefix() {
        return "";
    }

    @Nullable
    public final Long getElapsedTime() {
        if (this.phaseTimerActiveCount.getValue() != 0) {
            logger.error(() -> {
                return _get_elapsedTime_$lambda$0(r1);
            });
        }
        if (this.ranges.size() != 0) {
            logger.error(() -> {
                return _get_elapsedTime_$lambda$1(r1);
            });
        }
        if (this.queue.size() != 0) {
            logger.error(() -> {
                return _get_elapsedTime_$lambda$2(r1);
            });
        }
        return this._elapsedTime;
    }

    private static final Object _get_elapsedTime_$lambda$0(PhaseIntervalTimer this$0) {
        return this$0.getPrefix() + "internal error: phaseTimerCount is not zero";
    }

    private static final Object _get_elapsedTime_$lambda$1(PhaseIntervalTimer this$0) {
        return this$0.getPrefix() + "internal error: ranges is not empty";
    }

    private static final Object _get_elapsedTime_$lambda$2(PhaseIntervalTimer this$0) {
        return this$0.getPrefix() + "internal error: queue is not empty";
    }

    @NotNull
    public final AtomicInt getPhaseStartCount() {
        return this.phaseStartCount;
    }

    @Nullable
    public final Double getPhaseAverageElapsedTime() {
        Integer numValueOf = Integer.valueOf(this.phaseStartCount.getValue());
        Integer num = numValueOf.intValue() > 0 ? numValueOf : null;
        if (num == null) {
            return null;
        }
        int c = num.intValue();
        if (getElapsedTime() != null) {
            Double dValueOf = Double.valueOf(r0.longValue());
            double it = dValueOf.doubleValue();
            Double d = !Double.isInfinite(it) && !Double.isNaN(it) ? dValueOf : null;
            if (d != null) {
                return Double.valueOf(d.doubleValue() / c);
            }
        }
        return null;
    }

    @NotNull
    public final Snapshot start() {
        Snapshot result = new Snapshot(PhaseIntervalTimerKt.currentNanoTime(), this.id.getAndIncrement());
        if (this.startTime == null) {
            this.startTime = Long.valueOf(result.getStartTime());
        }
        synchronized (this.queue) {
            this.queue.put(Integer.valueOf(result.getId$corax_api()), result);
            this.phaseTimerActiveCount.getAndIncrement();
        }
        this.phaseStartCount.getAndIncrement();
        return result;
    }

    public final void stop(@NotNull Snapshot snapshot) {
        Snapshot snapshotRemove;
        long min;
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        TimeRange timeRange = new TimeRange(snapshot.getStartTime(), PhaseIntervalTimerKt.currentNanoTime());
        Long l = this.endTime;
        this.endTime = Long.valueOf(Math.max(l != null ? l.longValue() : timeRange.getMax(), timeRange.getMax()));
        synchronized (this.queue) {
            int cur = this.phaseTimerActiveCount.decrementAndGet();
            this.ranges.add(timeRange);
            this.ranges = TimeRange.Companion.sortAndMerge(this.ranges);
            if (cur >= 0) {
                Integer lower = this.queue.lowerKey(Integer.valueOf(snapshot.getId$corax_api()));
                if (lower == null) {
                    int i = 0;
                    Long l2 = this._elapsedTime;
                    long elapsedTime = l2 != null ? l2.longValue() : 0L;
                    Map.Entry higher = this.queue.higherEntry(Integer.valueOf(snapshot.getId$corax_api()));
                    if (higher == null) {
                        min = ((TimeRange) CollectionsKt.last(this.ranges)).getMax();
                    } else if (timeRange.getMax() <= higher.getValue().getStartTime()) {
                        min = timeRange.getMax();
                    } else {
                        min = timeRange.getMin();
                    }
                    long noSnapshotBefore = min;
                    while (i < this.ranges.size() && this.ranges.get(i).getMax() <= noSnapshotBefore) {
                        int i2 = i;
                        int i3 = i - 1;
                        TimeRange e = this.ranges.remove(i2);
                        elapsedTime += e.getMax() - e.getMin();
                        i = i3 + 1;
                    }
                    this._elapsedTime = Long.valueOf(elapsedTime);
                }
                snapshotRemove = this.queue.remove(Integer.valueOf(snapshot.getId$corax_api()));
            } else {
                logger.error(PhaseIntervalTimer::stop$lambda$8$lambda$7);
                snapshotRemove = Unit.INSTANCE;
            }
        }
    }

    private static final Object stop$lambda$8$lambda$7() {
        return "internal error: phaseTimerCount is negative";
    }

    @NotNull
    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf(PhaseIntervalTimerKt.nanoTimeInSeconds$default(this._elapsedTime, 0, 1, null))};
        String str = String.format("elapsed time: %.2fs", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* compiled from: PhaseIntervalTimer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/api/util/PhaseIntervalTimer$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-api"})
    /* loaded from: PhaseIntervalTimer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$9() {
        return Unit.INSTANCE;
    }
}
