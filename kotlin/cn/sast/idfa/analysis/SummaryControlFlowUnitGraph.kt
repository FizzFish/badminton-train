// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public open class SummaryControlFlowUnitGraph public constructor(method: soot.SootMethod, icfg: cn.sast.idfa.analysis.InterproceduralCFG) : soot.toolkits.graph.DirectedGraph<soot.Unit> {
    public final val method: soot.SootMethod /* compiled code */

    public final val icfg: cn.sast.idfa.analysis.InterproceduralCFG /* compiled code */

    public final val jimp: soot.jimple.Jimple /* compiled code */

    public final val body: soot.jimple.JimpleBody /* platform type */ /* compiled code */

    public final var graph: soot.toolkits.graph.UnitGraph /* compiled code */

    public open operator fun iterator(): kotlin.collections.MutableIterator<soot.Unit> { /* compiled code */ }

    public open fun getHeads(): kotlin.collections.List<soot.Unit> { /* compiled code */ }

    public open fun getTails(): kotlin.collections.List<soot.Unit> { /* compiled code */ }

    public open fun getPredsOf(s: soot.Unit): kotlin.collections.List<soot.Unit> { /* compiled code */ }

    public open fun getSuccsOf(s: soot.Unit): kotlin.collections.List<soot.Unit> { /* compiled code */ }

    public open fun size(): kotlin.Int { /* compiled code */ }
}

