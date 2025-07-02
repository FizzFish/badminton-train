package cn.sast.idfa.analysis

public open class ProcessInfoView public constructor(metrics: cn.sast.idfa.analysis.UsefulMetrics = COMPILED_CODE) {
    public companion object {
        public final val globalProcessInfo: cn.sast.idfa.analysis.ProcessInfoView /* compiled code */
    }

    public final val metrics: cn.sast.idfa.analysis.UsefulMetrics /* compiled code */

    public final val maxUsedMemory: java.util.concurrent.atomic.AtomicLong /* compiled code */

    public open val kotlin.Long.memFmt: kotlin.String /* compiled code */
        public open get

    public open val jvmMemoryUsedText: kotlin.String /* compiled code */
        public open get

    public open val maxUsedMemoryText: kotlin.String /* compiled code */
        public open get

    public open val jvmMemoryCommittedText: kotlin.String /* compiled code */
        public open get

    public open val jvmMemoryMaxText: kotlin.String /* compiled code */
        public open get

    public open val jvmMemoryUsageText: kotlin.String /* compiled code */
        public open get

    public open val freeMemoryText: kotlin.String /* compiled code */
        public open get

    public open val cpuLoadText: kotlin.String /* compiled code */
        public open get

    public open val processInfoText: kotlin.String /* compiled code */
        public open get

    public final fun updateStat(): kotlin.Unit { /* compiled code */ }
}

