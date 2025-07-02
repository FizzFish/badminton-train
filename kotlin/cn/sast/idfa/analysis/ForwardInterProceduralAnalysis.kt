// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public abstract class ForwardInterProceduralAnalysis<M, N, A, R, CTX : cn.sast.idfa.analysis.Context<M, N, A>> public constructor(name: kotlin.String = COMPILED_CODE) : cn.sast.idfa.analysis.InterProceduralAnalysis<M, N, A> {
    public companion object {
        private final var logger: mu.KLogger /* compiled code */
    }

    public final val name: kotlin.String /* compiled code */

    private final var stopWatch: com.google.common.base.Stopwatch? /* compiled code */

    protected final var timeOutDuration: kotlin.Long /* compiled code */

    protected final var timeOutOn: kotlin.Boolean /* compiled code */

    private final var usedTime: kotlin.Double /* compiled code */

    public final var isTimeout: kotlin.Boolean /* compiled code */
        private final set(value: kotlin.Boolean) {/* compiled code */ }

    private final var limitedAnalytics: kotlin.Boolean /* compiled code */

    public final val progressBarExt: cn.sast.idfa.progressbar.ProgressBarExt /* compiled code */

    public open var numberThreads: kotlin.Int /* compiled code */

    public open var staticFieldTrackingMode: cn.sast.api.config.StaticFieldTrackingMode /* compiled code */

    public final val reachableMethods: soot.jimple.infoflow.collect.ConcurrentHashSet<M> /* compiled code */

    public final var directedGraph: cn.sast.graph.HashMutableDirectedGraph<M>? /* compiled code */

    public open val progressBarVolume: kotlin.Int /* compiled code */

    private final val contextStateId: java.util.concurrent.atomic.AtomicLong /* compiled code */

    public final lateinit var cache: com.feysh.corax.cache.coroutines.RecCoroutineLoadingCache<cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<M, N, A>, CTX> /* compiled code */

    public final var dataFlowInterProceduralCalleeTimeOut: kotlin.Int /* compiled code */

    public final var dataFlowInterProceduralCalleeDepChainMaxNum: kotlin.Long /* compiled code */

    private final val bottom: A /* compiled code */
        private final get

    private final var transformStmtTotalCount: java.util.concurrent.atomic.AtomicInteger /* compiled code */

    private final val curAnalysingMethods: kotlin.collections.MutableSet<M> /* compiled code */

    public open fun me.tongfei.progressbar.ProgressBar.wrapperCustom(): cn.sast.idfa.progressbar.ProgressBarExt.DefaultProcessInfoRenderer { /* compiled code */ }

    public open fun cacheConfig(): kotlin.Unit { /* compiled code */ }

    public abstract fun makeContext(method: M, cfg: soot.toolkits.graph.DirectedGraph<N>, entryValue: A, reverse: kotlin.Boolean, isAnalyzable: kotlin.Boolean): CTX

    public open fun newContext(cfg: soot.toolkits.graph.DirectedGraph<N>, method: M, entryValue: A, isAnalyzable: kotlin.Boolean): CTX { /* compiled code */ }

    public open suspend fun computeInValue(context: CTX, node: N): A { /* compiled code */ }

    public open suspend fun computeEntryValue(context: CTX): A { /* compiled code */ }

    public open fun computeExitValue(context: CTX): A { /* compiled code */ }

    public open fun initCallEdgeValue(currentContext: CTX, node: N, callee: M, callSiteValue: A, inValue: A): A { /* compiled code */ }

    public open suspend fun evalCall(context: CTX, callee: M, node: N, succ: N, inValue: A): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>? { /* compiled code */ }

    public abstract fun recursiveCallFlowFunction(context: CTX, callee: M, node: N, succ: N, inValue: A, siteValue: A, isAnalyzable: kotlin.Boolean): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>

    public abstract fun failedInvokeResult(context: CTX, callee: M, node: N, succ: N, inValue: A, siteValue: A, isAnalyzable: kotlin.Boolean): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>

    public final fun init(scope: kotlinx.coroutines.CoroutineScope): kotlin.Unit { /* compiled code */ }

    public open suspend fun processCallCoroutine(currentContext: CTX, callee: M, node: N, succ: N, callSiteValue: A): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?> { /* compiled code */ }

    public abstract fun skip(callee: M): kotlin.Unit

    public open suspend fun postCallAtCallSite(context: CTX, node: N, succ: N, in1: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?> { /* compiled code */ }

    public open suspend fun prevCallFunction(context: CTX, callee: M, node: N, succ: N, callSiteValue: A): A { /* compiled code */ }

    public open suspend fun processAndReturnResult(context: CTX, node: N, succ: N, callees: kotlin.collections.Set<M>, callSiteValue: A): kotlin.collections.Map<M, kotlinx.coroutines.Deferred<cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>>> { /* compiled code */ }

    public open suspend fun CTX.processContent(): kotlin.Unit { /* compiled code */ }

    public open suspend fun interProceduralAnalyze(key: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<M, N, A>, currentContext: CTX): CTX { /* compiled code */ }

    public final suspend fun processMethod(method: M): kotlin.Unit { /* compiled code */ }

    public open fun doAnalyze(scope: kotlinx.coroutines.CoroutineScope, entries: kotlin.collections.Collection<M>): kotlin.Pair<kotlinx.coroutines.Job, cn.sast.idfa.progressbar.ProgressBarExt.DefaultProcessInfoRenderer?> { /* compiled code */ }

    public open fun doAnalysis(entries: kotlin.collections.Collection<M>): kotlin.Unit { /* compiled code */ }

    public abstract suspend fun normalFlowFunction(context: CTX, node: N, succ: N, inValue: A, isNegativeBranch: java.util.concurrent.atomic.AtomicBoolean): A

    public open fun callLocalFlowFunction(context: cn.sast.idfa.analysis.Context<soot.SootMethod, soot.Unit, A>, node: soot.Unit, succ: soot.Unit, callSiteValue: A): A { /* compiled code */ }

    public abstract fun callEntryFlowFunction(context: CTX, callee: M, node: N, succ: N, inValue: A): A

    public abstract fun callExitFlowFunction(context: CTX, siteValue: A, callee: M, callEdgeValue: A, calleeCtx: CTX, node: N, succ: N, isAnalyzable: kotlin.Boolean): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<M, A, R?>

    public open suspend fun returnFlowFunction(context: CTX, node: N, returnValue: A): A { /* compiled code */ }

    public open fun wideningFunction(context: CTX, node: N, succ: N, `in`: A): A? { /* compiled code */ }

    public open fun hasChange(context: CTX, node: N, succ: N, old: A, new: A): cn.sast.idfa.analysis.FixPointStatus { /* compiled code */ }

    public abstract fun isAnalyzable(callee: M, in1: A): kotlin.Boolean

    public open fun resolveTargets(callerMethod: M, node: N): kotlin.collections.Set<M> { /* compiled code */ }

    public abstract fun getCfg(method: M, isAnalyzable: kotlin.Boolean): soot.toolkits.graph.DirectedGraph<N>

    public final data class FactKey<M, N, A> public constructor(`in`: A, method: M, isAnalyzable: kotlin.Boolean) {
        public final val `in`: A /* compiled code */

        public final val method: M /* compiled code */

        public final val isAnalyzable: kotlin.Boolean /* compiled code */

        public final val accessTimes: java.util.concurrent.atomic.AtomicInteger /* compiled code */

        public final operator fun component1(): A { /* compiled code */ }

        public final operator fun component2(): M { /* compiled code */ }

        public final operator fun component3(): kotlin.Boolean { /* compiled code */ }

        public open operator fun equals(other: kotlin.Any?): kotlin.Boolean { /* compiled code */ }

        public open fun hashCode(): kotlin.Int { /* compiled code */ }

        public open fun toString(): kotlin.String { /* compiled code */ }
    }

    public final data class InvokeResult<M, A, R> public constructor(callee: M, callSiteOutAbstract: A, resultValue: R?) {
        public final val callee: M /* compiled code */

        public final val callSiteOutAbstract: A /* compiled code */

        public final val resultValue: R? /* compiled code */

        public final operator fun component1(): M { /* compiled code */ }

        public final operator fun component2(): A { /* compiled code */ }

        public final operator fun component3(): R? { /* compiled code */ }

        public open operator fun equals(other: kotlin.Any?): kotlin.Boolean { /* compiled code */ }

        public open fun hashCode(): kotlin.Int { /* compiled code */ }

        public open fun toString(): kotlin.String { /* compiled code */ }
    }
}

