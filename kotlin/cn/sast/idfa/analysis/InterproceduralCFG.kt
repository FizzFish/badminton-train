// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public final class InterproceduralCFG public constructor() : cn.sast.idfa.analysis.ProgramRepresentation<soot.SootMethod, soot.Unit> {
    private final val unitToOwner: kotlin.collections.MutableMap<soot.Unit, soot.SootMethod> /* compiled code */

    private final val cfgCacheSummary: com.github.benmanes.caffeine.cache.LoadingCache<soot.SootMethod, soot.toolkits.graph.DirectedGraph<soot.Unit>> /* compiled code */

    private final val cfgCache: com.github.benmanes.caffeine.cache.LoadingCache<soot.SootMethod, soot.toolkits.graph.DirectedGraph<soot.Unit>> /* compiled code */

    private final val liveLocalCache: com.github.benmanes.caffeine.cache.LoadingCache<soot.toolkits.graph.DirectedBodyGraph<soot.Unit>, soot.toolkits.scalar.LiveLocals> /* compiled code */

    public final val delegateICFG: soot.jimple.toolkits.ide.icfg.AbstractJimpleBasedICFG /* compiled code */
        public final get

    public open fun getControlFlowGraph(method: soot.SootMethod): soot.toolkits.graph.DirectedGraph<soot.Unit> { /* compiled code */ }

    public open fun getSummaryControlFlowGraph(method: soot.SootMethod): soot.toolkits.graph.DirectedGraph<soot.Unit> { /* compiled code */ }

    public open fun isCall(node: soot.Unit): kotlin.Boolean { /* compiled code */ }

    public open fun getCalleesOfCallAt(callerMethod: soot.SootMethod, callNode: soot.Unit): kotlin.collections.Set<soot.SootMethod> { /* compiled code */ }

    public final fun getMethodOf(node: soot.Unit): soot.SootMethod { /* compiled code */ }

    public open fun setOwnerStatement(u: soot.Unit, owner: soot.SootMethod): kotlin.Unit { /* compiled code */ }

    public open fun setOwnerStatement(g: kotlin.collections.Iterable<soot.Unit>, owner: soot.SootMethod): kotlin.Unit { /* compiled code */ }

    public open fun isAnalyzable(method: soot.SootMethod): kotlin.Boolean { /* compiled code */ }

    public final fun isFallThroughSuccessor(unit: soot.Unit, succ: soot.Unit): kotlin.Boolean { /* compiled code */ }

    public final fun isCallStmt(unit: soot.Unit): kotlin.Boolean { /* compiled code */ }

    public final fun getCalleesOfCallAt(unit: soot.Unit): kotlin.collections.Collection<soot.SootMethod> { /* compiled code */ }

    public final fun getPredsOf(unit: soot.Unit): kotlin.collections.List<soot.Unit> { /* compiled code */ }

    public final fun hasPredAsLookupSwitchStmt(unit: soot.Unit): kotlin.Boolean { /* compiled code */ }

    public final fun getPredAsLookupSwitchStmt(unit: soot.Unit): soot.Unit? { /* compiled code */ }

    public final fun getIdentityStmt(method: soot.SootMethod): soot.jimple.IdentityStmt { /* compiled code */ }

    public open fun isSkipCall(node: soot.Unit): kotlin.Boolean { /* compiled code */ }

    public final fun getNonLiveLocals(ug: soot.toolkits.graph.DirectedBodyGraph<soot.Unit>, unit: soot.Unit): kotlin.collections.List<soot.Local> { /* compiled code */ }
}

