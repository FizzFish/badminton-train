package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import soot.Local
import soot.Unit
import soot.toolkits.graph.DirectedGraph
import soot.toolkits.graph.UnitGraph
import soot.toolkits.scalar.SimpleLocalDefs.FlowAnalysisMode
import soot.util.Chain

public data class LocalDefsAnalysis(graph: DirectedGraph<Unit>, locals: Set<Local>, mode: FlowAnalysisMode) : AnalysisKey(
      LocalDefsAnalysisFactory.INSTANCE.getKey()
   ) {
   public final val graph: DirectedGraph<Unit>
   public final val locals: Set<Local>
   public final val mode: FlowAnalysisMode

   init {
      this.graph = graph;
      this.locals = locals;
      this.mode = mode;
   }

   public constructor(graph: UnitGraph) : this(graph, FlowAnalysisMode.Automatic)
   public constructor(graph: UnitGraph, mode: FlowAnalysisMode)  {
      val var10001: DirectedGraph = graph as DirectedGraph;
      val var10002: Chain = graph.getBody().getLocals();
      this(var10001, CollectionsKt.toSet(var10002 as java.lang.Iterable), mode);
   }

   public operator fun component1(): DirectedGraph<Unit> {
      return this.graph;
   }

   public operator fun component2(): Set<Local> {
      return this.locals;
   }

   public operator fun component3(): FlowAnalysisMode {
      return this.mode;
   }

   public fun copy(graph: DirectedGraph<Unit> = this.graph, locals: Set<Local> = this.locals, mode: FlowAnalysisMode = this.mode): LocalDefsAnalysis {
      return new LocalDefsAnalysis(graph, locals, mode);
   }

   public override fun toString(): String {
      return "LocalDefsAnalysis(graph=${this.graph}, locals=${this.locals}, mode=${this.mode})";
   }

   public override fun hashCode(): Int {
      return (this.graph.hashCode() * 31 + this.locals.hashCode()) * 31 + this.mode.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is LocalDefsAnalysis) {
         return false;
      } else {
         val var2: LocalDefsAnalysis = other as LocalDefsAnalysis;
         if (!(this.graph == (other as LocalDefsAnalysis).graph)) {
            return false;
         } else if (!(this.locals == var2.locals)) {
            return false;
         } else {
            return this.mode === var2.mode;
         }
      }
   }
}
