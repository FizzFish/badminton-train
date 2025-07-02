package cn.sast.idfa.analysis

import cn.sast.framework.graph.PseudoTopologicalOrderer
import kotlin.Comparator
import kotlin.Pair
import kotlin.Triple
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import java.util.concurrent.atomic.AtomicInteger
import java.util.NavigableSet
import java.util.TreeSet
import soot.toolkits.graph.DirectedGraph

/**
 * Context of data-flow analysis for a single method.
 */
open class Context<M, N, A>(
    method: M,
    cfg: DirectedGraph<N>,
    reverse: Boolean,
    isAnalyzable: Boolean
) : soot.Context, Comparable<Context<M, N, A>> {

    var isAnalyzable: Boolean = isAnalyzable
    var isAnalysed: Boolean = false
        private set
    var skipAnalysis: Boolean = false
    var pathSensitiveEnable: Boolean = true

    var iteratorCount: MutableMap<N, Int>? = HashMap()
    var widenNode: MutableSet<Pair<N, N>>? = HashSet()

    var controlFlowGraph: DirectedGraph<N> = cfg
        private set

    var entryValue: A? = null
        private set

    var exitValue: A? = null
        private set

    var id: Int = count.getAndIncrement()
        private set

    var method: M = method
        private set

    private var orderedNodes: List<N>? = null
    private var inValues: MutableMap<N, A>? = LinkedHashMap(cfg.size())
    private var edgeValues: MutableMap<Pair<N, N>, A>? = LinkedHashMap(cfg.size() * 2)
    private var callCalleeEdgeValues: MutableMap<Triple<N, M, A>, A>? = LinkedHashMap(cfg.size() / 2)

    var worklist: NavigableSet<Pair<N, N>> = TreeSet()
        private set

    private lateinit var numbers: Map<Pair<N, N>, Int>

    private var callNode: N? = null
    var bottomValue: A? = null

    init {
        val orderer = PseudoTopologicalOrderer<N>()
        val nodes = orderer.newList(controlFlowGraph, reverse)
        orderedNodes = nodes
        val numMap = HashMap<Pair<N, N>, Int>()
        var num = 1
        for (n in nodes) {
            val succs = cfg.getSuccsOf(n)
            if (succs.isEmpty()) {
                numMap[Pair(n, n)] = num++
            } else {
                for (succ in cfg.getSuccsOf(n)) {
                    numMap[Pair(n, succ)] = num++
                }
            }
        }
        numbers = numMap
        worklist = TreeSet(Comparator { u, v ->
            numbers[u]!! - numbers[v]!!
        })
    }

    override fun compareTo(other: Context<M, N, A>): Int = other.id - id

    fun getEdgeValue(node: N, succ: N): A {
        val map = edgeValues!!
        return map[Pair(node, succ)] ?: bottomValue!!
    }

    fun setEdgeValue(node: N, succ: N, value: A) {
        edgeValues!![Pair(node, succ)] = value
    }

    fun getValueBefore(node: N): A? = inValues!![node]

    fun markAnalysed() {
        isAnalysed = true
        callCalleeEdgeValues = null
        edgeValues = null
        inValues = null
        iteratorCount = null
        widenNode = null
    }

    fun setEntryValue(entryValue: A) { this.entryValue = entryValue }
    fun setExitValue(exitValue: A) { this.exitValue = exitValue }
    fun setValueBefore(node: N, value: A) { inValues!![node] = value }

    override fun toString(): String = (if (isAnalyzable) "" else "NN") + " " + id + " : + " + method

    fun initworklist() {
        isAnalysed = false
        for (n in orderedNodes!!) {
            val succs = controlFlowGraph.getSuccsOf(n)
            if (succs.isEmpty()) {
                worklist.add(Pair(n, n))
                setEdgeValue(n, n, bottomValue!!)
            } else {
                for (succ in succs) {
                    worklist.add(Pair(n, succ))
                    setEdgeValue(n, succ, bottomValue!!)
                }
            }
        }
    }

    fun clearWorkList() = worklist.clear()

    fun addToWorklist(node: N) {
        val succs = controlFlowGraph.getSuccsOf(node)
        if (succs.isEmpty()) {
            worklist.add(Pair(node, node))
        } else {
            for (succ in succs) {
                worklist.add(Pair(node, succ))
            }
        }
    }

    fun setCallNode(callNode: N) { this.callNode = callNode }
    fun getCallNode(): N? = callNode
    fun hasCallNode(): Boolean = callNode != null

    fun getCallEdgeValue(node: N, callee: M, entryValue: A): A? =
        callCalleeEdgeValues!![Triple(node, callee, entryValue)]

    fun setCallEdgeValue(node: N, callee: M, entryValue: A, out: A) {
        callCalleeEdgeValues!![Triple(node, callee, entryValue)] = out
    }

    companion object {
        val count: AtomicInteger = AtomicInteger(0)
        fun reset() { count.set(0) }
    }
}


