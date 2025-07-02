package cn.sast.idfa.analysis

public interface ProgramRepresentation<M, N> {
    public abstract fun getControlFlowGraph(method: M): soot.toolkits.graph.DirectedGraph<N>?

    public abstract fun getSummaryControlFlowGraph(method: M): soot.toolkits.graph.DirectedGraph<N>?

    public abstract fun isCall(node: N): kotlin.Boolean

    public abstract fun isAnalyzable(method: M): kotlin.Boolean

    public abstract fun getCalleesOfCallAt(callerMethod: M, callNode: N): kotlin.collections.Set<M>

    public abstract fun isSkipCall(node: N): kotlin.Boolean

    public abstract fun setOwnerStatement(u: N, owner: M): kotlin.Unit

    public abstract fun setOwnerStatement(g: kotlin.collections.Iterable<N>, owner: M): kotlin.Unit
}

