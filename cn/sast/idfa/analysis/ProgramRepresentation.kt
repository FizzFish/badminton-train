package cn.sast.idfa.analysis

import soot.toolkits.graph.DirectedGraph

public interface ProgramRepresentation<M, N> {
   public abstract fun getControlFlowGraph(method: Any): DirectedGraph<Any>? {
   }

   public abstract fun getSummaryControlFlowGraph(method: Any): DirectedGraph<Any>? {
   }

   public abstract fun isCall(node: Any): Boolean {
   }

   public abstract fun isAnalyzable(method: Any): Boolean {
   }

   public abstract fun getCalleesOfCallAt(callerMethod: Any, callNode: Any): Set<Any> {
   }

   public abstract fun isSkipCall(node: Any): Boolean {
   }

   public abstract fun setOwnerStatement(u: Any, owner: Any) {
   }

   public abstract fun setOwnerStatement(g: Iterable<Any>, owner: Any) {
   }
}
