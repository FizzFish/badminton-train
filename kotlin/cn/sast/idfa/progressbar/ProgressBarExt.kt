// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.progressbar

public final class ProgressBarExt public constructor(updateIntervalMillis: kotlin.Int = COMPILED_CODE, maxProgressLength: kotlin.Int = COMPILED_CODE) {
    public final var updateIntervalMillis: kotlin.Int /* compiled code */

    public final var maxProgressLength: kotlin.Int /* compiled code */

    public final fun getProgressBar(unitName: kotlin.String, initialMax: kotlin.Long, style: me.tongfei.progressbar.ProgressBarStyle = COMPILED_CODE, builder: me.tongfei.progressbar.ProgressBarBuilder.() -> kotlin.Unit = COMPILED_CODE): me.tongfei.progressbar.ProgressBar { /* compiled code */ }

    public open class DefaultProcessInfoRenderer public constructor(progressBar: me.tongfei.progressbar.ProgressBar, render: me.tongfei.progressbar.ProgressBarRenderer) : cn.sast.idfa.analysis.ProcessInfoView, me.tongfei.progressbar.ProgressBarRenderer {
        public final val progressBar: me.tongfei.progressbar.ProgressBar /* compiled code */

        private final val render: me.tongfei.progressbar.ProgressBarRenderer /* compiled code */

        public final var splitLines: kotlin.Long /* compiled code */

        public final val counter: java.util.concurrent.atomic.AtomicLong /* compiled code */

        public open val extraMessage: kotlin.String /* compiled code */
            public open get

        public open fun render(progressState: me.tongfei.progressbar.ProgressState, maxLength: kotlin.Int): kotlin.String { /* compiled code */ }

        public final fun step(): kotlin.Unit { /* compiled code */ }

        public final fun refresh(): kotlin.Unit { /* compiled code */ }

        public final fun close(): kotlin.Unit { /* compiled code */ }
    }
}

