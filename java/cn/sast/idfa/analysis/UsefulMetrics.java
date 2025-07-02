package cn.sast.idfa.analysis;

import cn.sast.api.util.PhaseIntervalTimerKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import io.quarkus.runtime.ShutdownContext;
import io.quarkus.runtime.StartupContext;
import io.quarkus.smallrye.metrics.runtime.SmallRyeMetricsRecorder;
import io.smallrye.metrics.MetricRegistries;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import mu.KLogger;
import mu.KotlinLogging;
import org.eclipse.microprofile.metrics.Gauge;
import org.eclipse.microprofile.metrics.Metric;
import org.eclipse.microprofile.metrics.MetricID;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.jimple.infoflow.util.ThreadUtils;

/* compiled from: UsefulMetrics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��d\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018�� ?2\u00020\u0001:\u0001?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u0004\u0018\u00010\u000bJ\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0012R\u0019\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0012R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000f¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0012R\"\u0010!\u001a\u0004\u0018\u00010\u0010*\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0018\u0010$\u001a\u00020%*\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R \u0010$\u001a\u00020%*\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010(R \u0010)\u001a\u00020%*\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010(R\u001e\u0010,\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b-\u0010.R\u001e\u00100\u001a\u00020/2\u0006\u0010+\u001a\u00020/@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b1\u00102R\u001e\u00103\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b4\u0010.R\u001e\u00106\u001a\u0002052\u0006\u0010+\u001a\u000205@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b6\u00107R\u001e\u00108\u001a\u0002052\u0006\u0010+\u001a\u000205@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b8\u00107R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n��¨\u0006@"}, d2 = {"Lcn/sast/idfa/analysis/UsefulMetrics;", "", "<init>", "()V", "registryBASE", "Lorg/eclipse/microprofile/metrics/MetricRegistry;", "getRegistryBASE", "()Lorg/eclipse/microprofile/metrics/MetricRegistry;", "registryVENDOR", "getRegistryVENDOR", "tenuredGenPool", "Ljava/lang/management/MemoryPoolMXBean;", "getTenuredGenPool", "()Ljava/lang/management/MemoryPoolMXBean;", "jvmMemoryUsed", "Lorg/eclipse/microprofile/metrics/Gauge;", "", "getJvmMemoryUsed", "()Lorg/eclipse/microprofile/metrics/Gauge;", "jvmMemoryCommitted", "getJvmMemoryCommitted", "jvmMemoryMax", "getJvmMemoryMax", "freePhysicalSize", "getFreePhysicalSize", "cpuSystemCpuLoad", "", "getCpuSystemCpuLoad", "getMetricAndTestGauge", "Lorg/eclipse/microprofile/metrics/Metric;", "metricID", "Lorg/eclipse/microprofile/metrics/MetricID;", "findTenuredGenPool", "memSize", "getMemSize", "(Lorg/eclipse/microprofile/metrics/Gauge;)Ljava/lang/Long;", "memFmt", "", "getMemFmt", "(J)Ljava/lang/String;", "(Lorg/eclipse/microprofile/metrics/Gauge;)Ljava/lang/String;", "loadFmt", "getLoadFmt", "value", "warningThreshold", "getWarningThreshold", "()J", "", "memoryThresholdExceededPercentage", "getMemoryThresholdExceededPercentage", "()F", "memoryUsed", "getMemoryUsed", "", "isMemoryThresholdTriggered", "()Z", "isLongTermThresholdTriggered", "thread", "Ljava/lang/Thread;", "reset", "", "setWarningThreshold", "percentage", "Companion", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nUsefulMetrics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UsefulMetrics.kt\ncn/sast/idfa/analysis/UsefulMetrics\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,207:1\n1#2:208\n*E\n"})
/* loaded from: UsefulMetrics.class */
public final class UsefulMetrics {

    @Nullable
    private final MetricRegistry registryBASE = MetricRegistries.get(MetricRegistry.Type.BASE);

    @Nullable
    private final MetricRegistry registryVENDOR = MetricRegistries.get(MetricRegistry.Type.VENDOR);

    @Nullable
    private final MemoryPoolMXBean tenuredGenPool = findTenuredGenPool();

    @Nullable
    private final Gauge<Long> jvmMemoryUsed;

    @Nullable
    private final Gauge<Long> jvmMemoryCommitted;

    @Nullable
    private final Gauge<Long> jvmMemoryMax;

    @Nullable
    private final Gauge<Long> freePhysicalSize;

    @Nullable
    private final Gauge<Double> cpuSystemCpuLoad;
    private volatile long warningThreshold;
    private volatile float memoryThresholdExceededPercentage;
    private volatile long memoryUsed;
    private volatile boolean isMemoryThresholdTriggered;
    private volatile boolean isLongTermThresholdTriggered;

    @Nullable
    private Thread thread;

    @NotNull
    private static final UsefulMetrics metrics;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(UsefulMetrics::logger$lambda$8);

    public UsefulMetrics() {
        Gauge<Long> gauge;
        Gauge<Long> gauge2;
        Gauge<Long> gauge3;
        UsefulMetrics usefulMetrics = this;
        final MemoryPoolMXBean it = this.tenuredGenPool;
        if (it != null) {
            usefulMetrics = usefulMetrics;
            gauge = new Gauge() { // from class: cn.sast.idfa.analysis.UsefulMetrics$jvmMemoryUsed$1$1
                /* renamed from: getValue, reason: merged with bridge method [inline-methods] */
                public final Long m461getValue() {
                    return Long.valueOf(it.getUsage().getUsed());
                }
            };
        } else {
            gauge = null;
        }
        usefulMetrics.jvmMemoryUsed = gauge;
        UsefulMetrics usefulMetrics2 = this;
        final MemoryPoolMXBean it2 = this.tenuredGenPool;
        if (it2 != null) {
            usefulMetrics2 = usefulMetrics2;
            gauge2 = new Gauge() { // from class: cn.sast.idfa.analysis.UsefulMetrics$jvmMemoryCommitted$1$1
                /* renamed from: getValue, reason: merged with bridge method [inline-methods] */
                public final Long m459getValue() {
                    return Long.valueOf(it2.getUsage().getCommitted());
                }
            };
        } else {
            gauge2 = null;
        }
        usefulMetrics2.jvmMemoryCommitted = gauge2;
        UsefulMetrics usefulMetrics3 = this;
        final MemoryPoolMXBean it3 = this.tenuredGenPool;
        if (it3 != null) {
            usefulMetrics3 = usefulMetrics3;
            gauge3 = new Gauge() { // from class: cn.sast.idfa.analysis.UsefulMetrics$jvmMemoryMax$1$1
                /* renamed from: getValue, reason: merged with bridge method [inline-methods] */
                public final Long m460getValue() {
                    return Long.valueOf(it3.getUsage().getMax());
                }
            };
        } else {
            gauge3 = null;
        }
        usefulMetrics3.jvmMemoryMax = gauge3;
        MetricRegistry metricRegistry = this.registryVENDOR;
        Metric metricAndTestGauge = metricRegistry != null ? getMetricAndTestGauge(metricRegistry, new MetricID("memory.freePhysicalSize")) : null;
        this.freePhysicalSize = metricAndTestGauge instanceof Gauge ? (Gauge) metricAndTestGauge : null;
        MetricRegistry metricRegistry2 = this.registryVENDOR;
        Metric metricAndTestGauge2 = metricRegistry2 != null ? getMetricAndTestGauge(metricRegistry2, new MetricID("cpu.systemCpuLoad")) : null;
        this.cpuSystemCpuLoad = metricAndTestGauge2 instanceof Gauge ? (Gauge) metricAndTestGauge2 : null;
        this.warningThreshold = -1L;
        this.memoryThresholdExceededPercentage = -1.0f;
        this.memoryUsed = -1L;
    }

    @Nullable
    public final MetricRegistry getRegistryBASE() {
        return this.registryBASE;
    }

    @Nullable
    public final MetricRegistry getRegistryVENDOR() {
        return this.registryVENDOR;
    }

    @Nullable
    public final MemoryPoolMXBean getTenuredGenPool() {
        return this.tenuredGenPool;
    }

    @Nullable
    public final Gauge<Long> getJvmMemoryUsed() {
        return this.jvmMemoryUsed;
    }

    @Nullable
    public final Gauge<Long> getJvmMemoryCommitted() {
        return this.jvmMemoryCommitted;
    }

    @Nullable
    public final Gauge<Long> getJvmMemoryMax() {
        return this.jvmMemoryMax;
    }

    @Nullable
    public final Gauge<Long> getFreePhysicalSize() {
        return this.freePhysicalSize;
    }

    @Nullable
    public final Gauge<Double> getCpuSystemCpuLoad() {
        return this.cpuSystemCpuLoad;
    }

    @Nullable
    public final Metric getMetricAndTestGauge(@NotNull MetricRegistry $this$getMetricAndTestGauge, @NotNull MetricID metricID) {
        Intrinsics.checkNotNullParameter($this$getMetricAndTestGauge, "<this>");
        Intrinsics.checkNotNullParameter(metricID, "metricID");
        Gauge metric = $this$getMetricAndTestGauge.getMetric(metricID);
        if (metric == null || !(metric instanceof Gauge)) {
            return null;
        }
        try {
            metric.getValue();
            return metric;
        } catch (Error e) {
            logger.error("Accessing Metric " + metricID + " throw an Error:", e);
            return null;
        } catch (Exception e2) {
            logger.error("Accessing Metric " + metricID + " throw an Exception:", e2);
            return null;
        }
    }

    @Nullable
    public final MemoryPoolMXBean findTenuredGenPool() {
        List<MemoryPoolMXBean> usablePools = new ArrayList();
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getType() == MemoryType.HEAP && pool.isUsageThresholdSupported()) {
                if (Intrinsics.areEqual(pool.getName(), "Tenured Gen")) {
                    Intrinsics.checkNotNull(pool);
                    usablePools.add(0, pool);
                } else {
                    Intrinsics.checkNotNull(pool);
                    usablePools.add(pool);
                }
            }
        }
        if (!usablePools.isEmpty()) {
            for (MemoryPoolMXBean pool2 : usablePools) {
                try {
                    MemoryUsage it = pool2.getUsage();
                    it.getInit();
                    it.getUsed();
                    it.getCommitted();
                    it.getMax();
                    return pool2;
                } catch (Error e) {
                    logger.error("Getting MemoryUsage from " + pool2.getName() + " throw an Error", e);
                } catch (Exception e2) {
                    logger.error("Getting MemoryUsage from " + pool2.getName() + " throw an Exception", e2);
                }
            }
        }
        logger.error("Could not find tenured space");
        return null;
    }

    @Nullable
    public Long getMemSize(@Nullable Gauge<Long> gauge) {
        if (gauge == null) {
            return null;
        }
        Object value = gauge.getValue();
        Long it = (Long) value;
        return (Long) ((it.longValue() > 0L ? 1 : (it.longValue() == 0L ? 0 : -1)) >= 0 ? value : null);
    }

    @NotNull
    public String getMemFmt(long $this$memFmt) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf((($this$memFmt / 1024.0d) / 1024) / 1024)};
        String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @NotNull
    public String getMemFmt(@Nullable Gauge<Long> gauge) {
        Long memSize = getMemSize(gauge);
        if (memSize != null) {
            String memFmt = getMemFmt(memSize.longValue());
            if (memFmt != null) {
                return memFmt;
            }
        }
        return "?";
    }

    @NotNull
    public String getLoadFmt(@Nullable Gauge<Double> gauge) {
        if (gauge == null) {
            return "?";
        }
        Object value = gauge.getValue();
        Double it = (Double) value;
        Double d = (Double) ((it.doubleValue() > 0.0d ? 1 : (it.doubleValue() == 0.0d ? 0 : -1)) >= 0 ? value : null);
        if (d == null) {
            return "?";
        }
        double v = d.doubleValue();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf(v * 100)};
        String str = String.format("%3.0f%%", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final long getWarningThreshold() {
        return this.warningThreshold;
    }

    public final float getMemoryThresholdExceededPercentage() {
        return this.memoryThresholdExceededPercentage;
    }

    public final long getMemoryUsed() {
        return this.memoryUsed;
    }

    public final boolean isMemoryThresholdTriggered() {
        return this.isMemoryThresholdTriggered;
    }

    public final boolean isLongTermThresholdTriggered() {
        return this.isLongTermThresholdTriggered;
    }

    public final void reset() {
        Thread it = this.thread;
        if (it != null) {
            this.thread = null;
            if (!it.isInterrupted()) {
                it.interrupt();
            }
        }
        this.warningThreshold = -1L;
        this.memoryThresholdExceededPercentage = -1.0f;
        this.memoryUsed = -1L;
        this.isMemoryThresholdTriggered = false;
        this.isLongTermThresholdTriggered = false;
    }

    public final void setWarningThreshold(double percentage) {
        Gauge jvmMemoryMax;
        if (this.thread != null || percentage < 0.01d) {
            reset();
            return;
        }
        final Gauge jvmMemoryUsed = this.jvmMemoryUsed;
        if (jvmMemoryUsed == null || (jvmMemoryMax = this.jvmMemoryMax) == null) {
            return;
        }
        final Long maxMemory = (Long) jvmMemoryMax.getValue();
        this.warningThreshold = (long) (maxMemory.longValue() * percentage);
        final AtomicLong memoryThresholdReachedPrevNanoTime = new AtomicLong(-1L);
        Thread it = ThreadUtils.createGenericThread(new Runnable() { // from class: cn.sast.idfa.analysis.UsefulMetrics.setWarningThreshold.1
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                while (true) {
                    Long usedMemory = (Long) jvmMemoryUsed.getValue();
                    long warningThreshold = this.getWarningThreshold();
                    this.memoryUsed = usedMemory.longValue();
                    if (warningThreshold > 0) {
                        this.isMemoryThresholdTriggered = usedMemory.longValue() >= warningThreshold;
                        this.memoryThresholdExceededPercentage = (float) ((usedMemory.longValue() - warningThreshold) / (maxMemory.longValue() - warningThreshold));
                        AtomicLong atomicLong = memoryThresholdReachedPrevNanoTime;
                        final UsefulMetrics usefulMetrics = this;
                        long firstThresholdReachedNanoTime = atomicLong.updateAndGet(new LongUnaryOperator() { // from class: cn.sast.idfa.analysis.UsefulMetrics$setWarningThreshold$1$firstThresholdReachedNanoTime$1
                            @Override // java.util.function.LongUnaryOperator
                            public final long applyAsLong(long it2) {
                                if (usefulMetrics.isMemoryThresholdTriggered()) {
                                    return it2 > 0 ? it2 : PhaseIntervalTimerKt.currentNanoTime();
                                }
                                return -1L;
                            }
                        });
                        this.isLongTermThresholdTriggered = firstThresholdReachedNanoTime > 0 && PhaseIntervalTimerKt.currentNanoTime() - firstThresholdReachedNanoTime > 5000;
                    }
                    Intrinsics.checkNotNull(usedMemory);
                    long missing = warningThreshold - usedMemory.longValue();
                    if (missing <= 0) {
                        Thread.sleep(300L);
                    } else {
                        try {
                            long wait = (long) ((missing / warningThreshold) * 1000);
                            Thread.sleep(wait);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }, "Low memory monitor", true);
        it.setPriority(1);
        it.start();
        this.thread = it;
    }

    /* compiled from: UsefulMetrics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcn/sast/idfa/analysis/UsefulMetrics$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "metrics", "Lcn/sast/idfa/analysis/UsefulMetrics;", "getMetrics", "()Lcn/sast/idfa/analysis/UsefulMetrics;", "corax-idfa-framework"})
    /* loaded from: UsefulMetrics$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final UsefulMetrics getMetrics() {
            return UsefulMetrics.metrics;
        }
    }

    private static final Unit logger$lambda$8() {
        return Unit.INSTANCE;
    }

    static {
        SmallRyeMetricsRecorder recorder = new SmallRyeMetricsRecorder();
        StartupContext startUp = new StartupContext();
        Object value = startUp.getValue("io.quarkus.runtime.ShutdownContext");
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type io.quarkus.runtime.ShutdownContext");
        ShutdownContext shutdown = (ShutdownContext) value;
        recorder.registerMicrometerJvmMetrics(shutdown);
        recorder.registerBaseMetrics();
        recorder.registerVendorMetrics();
        metrics = new UsefulMetrics();
    }
}
