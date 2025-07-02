// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public final class UsefulMetrics public constructor() {
    public companion object {
        private final val logger: mu.KLogger /* compiled code */

        public final val metrics: cn.sast.idfa.analysis.UsefulMetrics /* compiled code */
    }

    public final val registryBASE: org.eclipse.microprofile.metrics.MetricRegistry? /* compiled code */

    public final val registryVENDOR: org.eclipse.microprofile.metrics.MetricRegistry? /* compiled code */

    public final val tenuredGenPool: java.lang.management.MemoryPoolMXBean? /* compiled code */

    public final val jvmMemoryUsed: org.eclipse.microprofile.metrics.Gauge<kotlin.Long>? /* compiled code */

    public final val jvmMemoryCommitted: org.eclipse.microprofile.metrics.Gauge<kotlin.Long>? /* compiled code */

    public final val jvmMemoryMax: org.eclipse.microprofile.metrics.Gauge<kotlin.Long>? /* compiled code */

    public final val freePhysicalSize: org.eclipse.microprofile.metrics.Gauge<kotlin.Long>? /* compiled code */

    public final val cpuSystemCpuLoad: org.eclipse.microprofile.metrics.Gauge<kotlin.Double>? /* compiled code */

    public open val org.eclipse.microprofile.metrics.Gauge<kotlin.Long>?.memSize: kotlin.Long? /* compiled code */
        public open get

    public open val kotlin.Long.memFmt: kotlin.String /* compiled code */
        public open get

    public open val org.eclipse.microprofile.metrics.Gauge<kotlin.Long>?.memFmt: kotlin.String /* compiled code */
        public open get

    public open val org.eclipse.microprofile.metrics.Gauge<kotlin.Double>?.loadFmt: kotlin.String /* compiled code */
        public open get

    public final var warningThreshold: kotlin.Long /* compiled code */
        private final set(value: kotlin.Long) {/* compiled code */ }

    public final var memoryThresholdExceededPercentage: kotlin.Float /* compiled code */
        private final set(value: kotlin.Float) {/* compiled code */ }

    public final var memoryUsed: kotlin.Long /* compiled code */
        private final set(value: kotlin.Long) {/* compiled code */ }

    public final var isMemoryThresholdTriggered: kotlin.Boolean /* compiled code */
        private final set(value: kotlin.Boolean) {/* compiled code */ }

    public final var isLongTermThresholdTriggered: kotlin.Boolean /* compiled code */
        private final set(value: kotlin.Boolean) {/* compiled code */ }

    private final var thread: java.lang.Thread? /* compiled code */

    public final fun org.eclipse.microprofile.metrics.MetricRegistry.getMetricAndTestGauge(metricID: org.eclipse.microprofile.metrics.MetricID): org.eclipse.microprofile.metrics.Metric? { /* compiled code */ }

    public final fun findTenuredGenPool(): java.lang.management.MemoryPoolMXBean? { /* compiled code */ }

    public final fun reset(): kotlin.Unit { /* compiled code */ }

    public final fun setWarningThreshold(percentage: kotlin.Double): kotlin.Unit { /* compiled code */ }
}

