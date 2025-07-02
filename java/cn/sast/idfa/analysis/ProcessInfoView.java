package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import org.eclipse.microprofile.metrics.Gauge;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProcessInfoView.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0016\u0018�� $2\u00020\u0001:\u0001$B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u00020\r*\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u0014\u0010\u001c\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015R\u0014\u0010\u001e\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u0014\u0010 \u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0015R\u0014\u0010\"\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0015¨\u0006%"}, d2 = {"Lcn/sast/idfa/analysis/ProcessInfoView;", "", "metrics", "Lcn/sast/idfa/analysis/UsefulMetrics;", "<init>", "(Lcn/sast/idfa/analysis/UsefulMetrics;)V", "getMetrics", "()Lcn/sast/idfa/analysis/UsefulMetrics;", "maxUsedMemory", "Ljava/util/concurrent/atomic/AtomicLong;", "getMaxUsedMemory", "()Ljava/util/concurrent/atomic/AtomicLong;", "memFmt", "", "", "getMemFmt", "(J)Ljava/lang/String;", "updateStat", "", "jvmMemoryUsedText", "getJvmMemoryUsedText", "()Ljava/lang/String;", "maxUsedMemoryText", "getMaxUsedMemoryText", "jvmMemoryCommittedText", "getJvmMemoryCommittedText", "jvmMemoryMaxText", "getJvmMemoryMaxText", "jvmMemoryUsageText", "getJvmMemoryUsageText", "freeMemoryText", "getFreeMemoryText", "cpuLoadText", "getCpuLoadText", "processInfoText", "getProcessInfoText", "Companion", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nProcessInfoView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessInfoView.kt\ncn/sast/idfa/analysis/ProcessInfoView\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
/* loaded from: ProcessInfoView.class */
public class ProcessInfoView {

    @NotNull
    private final UsefulMetrics metrics;

    @NotNull
    private final AtomicLong maxUsedMemory;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final ProcessInfoView globalProcessInfo = new ProcessInfoView(null, 1, null);

    public ProcessInfoView() {
        this(null, 1, null);
    }

    public ProcessInfoView(@NotNull UsefulMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        this.metrics = metrics;
        this.maxUsedMemory = new AtomicLong(0L);
    }

    public /* synthetic */ ProcessInfoView(UsefulMetrics usefulMetrics, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? UsefulMetrics.Companion.getMetrics() : usefulMetrics);
    }

    @NotNull
    public final UsefulMetrics getMetrics() {
        return this.metrics;
    }

    @NotNull
    public final AtomicLong getMaxUsedMemory() {
        return this.maxUsedMemory;
    }

    @NotNull
    public String getMemFmt(long $this$memFmt) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Double.valueOf((($this$memFmt / 1024.0d) / 1024) / 1024)};
        String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final void updateStat() {
        Long l;
        Gauge<Long> jvmMemoryUsed = this.metrics.getJvmMemoryUsed();
        if (jvmMemoryUsed != null && (l = (Long) jvmMemoryUsed.getValue()) != null) {
            final long usedMemory = l.longValue();
            this.maxUsedMemory.updateAndGet(new LongUnaryOperator() { // from class: cn.sast.idfa.analysis.ProcessInfoView$updateStat$1$1
                @Override // java.util.function.LongUnaryOperator
                public final long applyAsLong(long it) {
                    return it < usedMemory ? usedMemory : it;
                }
            });
        }
    }

    @NotNull
    public String getJvmMemoryUsedText() {
        updateStat();
        UsefulMetrics $this$_get_jvmMemoryUsedText__u24lambda_u241 = this.metrics;
        return $this$_get_jvmMemoryUsedText__u24lambda_u241.getMemFmt($this$_get_jvmMemoryUsedText__u24lambda_u241.getJvmMemoryUsed()) + (this.metrics.isLongTermThresholdTriggered() ? "(JVM mem)" : "") + (this.metrics.isMemoryThresholdTriggered() ? "!" : "");
    }

    @NotNull
    public String getMaxUsedMemoryText() {
        return getMemFmt(this.maxUsedMemory.get());
    }

    @NotNull
    public String getJvmMemoryCommittedText() {
        UsefulMetrics $this$_get_jvmMemoryCommittedText__u24lambda_u242 = this.metrics;
        return $this$_get_jvmMemoryCommittedText__u24lambda_u242.getMemFmt($this$_get_jvmMemoryCommittedText__u24lambda_u242.getJvmMemoryCommitted());
    }

    @NotNull
    public String getJvmMemoryMaxText() {
        UsefulMetrics $this$_get_jvmMemoryMaxText__u24lambda_u243 = this.metrics;
        return $this$_get_jvmMemoryMaxText__u24lambda_u243.getMemFmt($this$_get_jvmMemoryMaxText__u24lambda_u243.getJvmMemoryMax()) + "G";
    }

    @NotNull
    public String getJvmMemoryUsageText() {
        return getJvmMemoryUsedText() + "/" + getMaxUsedMemoryText() + "/" + getJvmMemoryCommittedText() + "/" + getJvmMemoryMaxText();
    }

    @NotNull
    public String getFreeMemoryText() {
        String m;
        UsefulMetrics $this$_get_freeMemoryText__u24lambda_u244 = this.metrics;
        Long phy = $this$_get_freeMemoryText__u24lambda_u244.getMemSize($this$_get_freeMemoryText__u24lambda_u244.getFreePhysicalSize());
        return "free:" + ((phy == null || (m = getMemFmt(phy.longValue())) == null) ? null : m + "G") + ((phy == null || ((double) phy.longValue()) >= 8.05306368E8d) ? "" : "(low memory warning!)");
    }

    @NotNull
    public String getCpuLoadText() {
        UsefulMetrics $this$_get_cpuLoadText__u24lambda_u246 = this.metrics;
        String value = $this$_get_cpuLoadText__u24lambda_u246.getLoadFmt($this$_get_cpuLoadText__u24lambda_u246.getCpuSystemCpuLoad());
        return "cpu:" + value;
    }

    @NotNull
    public String getProcessInfoText() {
        return getJvmMemoryUsageText() + " " + getFreeMemoryText();
    }

    /* compiled from: ProcessInfoView.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/idfa/analysis/ProcessInfoView$Companion;", "", "<init>", "()V", "globalProcessInfo", "Lcn/sast/idfa/analysis/ProcessInfoView;", "getGlobalProcessInfo", "()Lcn/sast/idfa/analysis/ProcessInfoView;", "corax-idfa-framework"})
    /* loaded from: ProcessInfoView$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ProcessInfoView getGlobalProcessInfo() {
            return ProcessInfoView.globalProcessInfo;
        }
    }
}
