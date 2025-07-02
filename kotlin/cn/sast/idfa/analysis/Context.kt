// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public open class Context<M, N, A> : soot.Context, kotlin.Comparable<cn.sast.idfa.analysis.Context<M, N, A>> {
    public companion object {
        public final val count: java.util.concurrent.atomic.AtomicInteger /* compiled code */

        public final fun reset(): kotlin.Unit { /* compiled code */ }
    }

    public constructor(method: M, cfg: soot.toolkits.graph.DirectedGraph<N>, reverse: kotlin.Boolean, isAnalyzable: kotlin.Boolean) { /* compiled code */ }

    public final var isAnalyzable: kotlin.Boolean /* compiled code */

    public final var isAnalysed: kotlin.Boolean /* compiled code */
        private final set(value: kotlin.Boolean) {/* compiled code */ }

    public final var skipAnalysis: kotlin.Boolean /* compiled code */

    public final var pathSensitiveEnable: kotlin.Boolean /* compiled code */

    public final var iteratorCount: kotlin.collections.MutableMap<N, kotlin.Int>? /* compiled code */

    public final var widenNode: kotlin.collections.MutableSet<kotlin.Pair<N, N>>? /* compiled code */

    public final var controlFlowGraph: soot.toolkits.graph.DirectedGraph<N> /* compiled code */
        private final set(value: soot.toolkits.graph.DirectedGraph<N>) {/* compiled code */ }

    public final var entryValue: A? /* compiled code */
        private final set(value: A?) {/* compiled code */ }

    public final var exitValue: A? /* compiled code */
        private final set(value: A?) {/* compiled code */ }

    public final var id: kotlin.Int /* compiled code */
        private final set(value: kotlin.Int) {/* compiled code */ }

    public final var method: M /* compiled code */
        private final set(value: M) {/* compiled code */ }

    private final var orderedNodes: kotlin.collections.List<N>? /* compiled code */

    private final var inValues: kotlin.collections.MutableMap<N, A>? /* compiled code */

    private final var edgeValues: kotlin.collections.MutableMap<kotlin.Pair<N, N>, A>? /* compiled code */

    private final var callCalleeEdgeValues: kotlin.collections.MutableMap<kotlin.Triple<N, M, A>, A>? /* compiled code */

    public final var worklist: java.util.NavigableSet<kotlin.Pair<N, N>> /* compiled code */
        private final set(value: java.util.NavigableSet<kotlin.Pair<N, N>>) {/* compiled code */ }

    private final var callNode: N? /* compiled code */

    public final var bottomValue: A? /* compiled code */

    public open operator fun compareTo(other: cn.sast.idfa.analysis.Context<M, N, A>): kotlin.Int { /* compiled code */ }

    public final fun getEdgeValue(node: N, succ: N): A { /* compiled code */ }

    public final fun setEdgeValue(node: N, succ: N, `val`: A): kotlin.Unit { /* compiled code */ }

    public final fun getValueBefore(node: N): A? { /* compiled code */ }

    public final fun markAnalysed(): kotlin.Unit { /* compiled code */ }

    public final fun setEntryValue(entryValue: A): kotlin.Unit { /* compiled code */ }

    public final fun setExitValue(exitValue: A): kotlin.Unit { /* compiled code */ }

    public final fun setValueBefore(node: N, value: A): kotlin.Unit { /* compiled code */ }

    public open fun toString(): kotlin.String { /* compiled code */ }

    public final fun initworklist(): kotlin.Unit { /* compiled code */ }

    public final fun clearWorkList(): kotlin.Unit { /* compiled code */ }

    public final fun addToWorklist(node: N): kotlin.Unit { /* compiled code */ }

    public final fun setCallNode(callNode: N): kotlin.Unit { /* compiled code */ }

    public final fun getCallNode(): N? { /* compiled code */ }

    public final fun hasCallNode(): kotlin.Boolean { /* compiled code */ }

    public final fun getCallEdgeValue(node: N, callee: M, entryValue: A): A? { /* compiled code */ }

    public final fun setCallEdgeValue(node: N, callee: M, entryValue: A, out: A): kotlin.Unit { /* compiled code */ }
}

