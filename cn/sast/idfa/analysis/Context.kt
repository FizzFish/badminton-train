package cn.sast.idfa.analysis

import java.util.Comparator
import java.util.HashMap
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.NavigableSet
import java.util.TreeSet
import java.util.concurrent.atomic.AtomicInteger
import soot.toolkits.graph.DirectedGraph
import soot.toolkits.graph.PseudoTopologicalOrderer

public open class Context<M, N, A> : soot.Context, java.lang.Comparable<Context<M, N, A>> {
   public final var isAnalyzable: Boolean
      internal set

   public final var isAnalysed: Boolean
      private set

   public final var skipAnalysis: Boolean
      internal set

   public final var pathSensitiveEnable: Boolean = true
      internal set

   public final var iteratorCount: MutableMap<Any, Int>? = (new HashMap()) as java.util.Map
      internal set

   public final var widenNode: MutableSet<Pair<Any, Any>>? = (new HashSet()) as java.util.Set
      internal set

   public final var controlFlowGraph: DirectedGraph<Any>
      private set

   public final var entryValue: Any?
      private set

   public final var exitValue: Any?
      private set

   public final var id: Int
      private set

   public final var method: Any
      private set

   private final var orderedNodes: List<Any>?
   private final var inValues: MutableMap<Any, Any>?
   private final var edgeValues: MutableMap<Pair<Any, Any>, Any>?
   private final var callCalleeEdgeValues: MutableMap<Triple<Any, Any, Any>, Any>?

   public final var worklist: NavigableSet<Pair<Any, Any>>
      private set

   private final var callNode: Any?

   public final var bottomValue: Any?
      internal set

   public constructor(method: Any, cfg: DirectedGraph<Any>, reverse: Boolean, isAnalyzable: Boolean)  {
      this.isAnalyzable = isAnalyzable;
      this.id = count.getAndIncrement();
      this.method = (M)method;
      this.isAnalysed = false;
      this.controlFlowGraph = cfg;
      this.inValues = new LinkedHashMap<>(cfg.size());
      this.edgeValues = new LinkedHashMap<>(cfg.size() * 2);
      val var5: java.util.List = new PseudoTopologicalOrderer().newList(this.controlFlowGraph, reverse);
      val numbers: java.util.Map = new HashMap();
      var num: Int = 1;

      for (Object n : var5) {
         if (cfg.getSuccsOf(n).isEmpty()) {
            numbers.put(new Pair(n, n), num);
            num++;
         } else {
            for (Object succ : cfg.getSuccsOf(n)) {
               numbers.put(new Pair(n, succ), num);
               num++;
            }
         }
      }

      this.worklist = new TreeSet<>(new Comparator(Context::_init_$lambda$1$lambda$0) {
         {
            this.function = function;
         }
      });
      this.orderedNodes = var5;
      this.callCalleeEdgeValues = new LinkedHashMap<>(cfg.size() / 2);
   }

   public open operator fun compareTo(other: Context<Any, Any, Any>): Int {
      return other.id - this.id;
   }

   public fun getEdgeValue(node: Any, succ: Any): Any {
      val var10000: java.util.Map = this.edgeValues;
      var var3: Any = var10000.get(new Pair(node, succ));
      if (var3 == null) {
         var3 = this.bottomValue;
      }

      return (A)var3;
   }

   public fun setEdgeValue(node: Any, succ: Any, `val`: Any) {
      val var10000: java.util.Map = this.edgeValues;
      var10000.put(new Pair(node, succ), `val`);
   }

   public fun getValueBefore(node: Any): Any? {
      val var10000: java.util.Map = this.inValues;
      return (A)var10000.get(node);
   }

   public fun markAnalysed() {
      this.isAnalysed = true;
      this.callCalleeEdgeValues = null;
      this.edgeValues = null;
      this.inValues = null;
      this.iteratorCount = null;
      this.widenNode = null;
   }

   public fun setEntryValue(entryValue: Any) {
      this.entryValue = (A)entryValue;
   }

   public fun setExitValue(exitValue: Any) {
      this.exitValue = (A)exitValue;
   }

   public fun setValueBefore(node: Any, value: Any) {
      val var10000: java.util.Map = this.inValues;
      var10000.put(node, value);
   }

   public override fun toString(): String {
      return "${if (this.isAnalyzable) "" else "NN"} ${this.id} : + ${this.method}";
   }

   public fun initworklist() {
      this.isAnalysed = false;
      val var10000: java.util.List = this.orderedNodes;

      for (Object node : var10000) {
         val succs: java.util.List = this.controlFlowGraph.getSuccsOf(node);
         if (succs.isEmpty()) {
            this.worklist.add(new Pair(node, node));
            val var6: Any = this.bottomValue;
            this.setEdgeValue((N)node, (N)node, (A)var6);
         } else {
            for (Object succ : succs) {
               this.worklist.add(new Pair(node, succ));
               val var10003: Any = this.bottomValue;
               this.setEdgeValue((N)node, (N)succ, (A)var10003);
            }
         }
      }
   }

   public fun clearWorkList() {
      this.worklist.clear();
   }

   public fun addToWorklist(node: Any) {
      val succs: java.util.List = this.controlFlowGraph.getSuccsOf(node);
      if (succs.isEmpty()) {
         this.worklist.add(new Pair(node, node));
      } else {
         for (Object succ : succs) {
            this.worklist.add(new Pair(node, succ));
         }
      }
   }

   public fun setCallNode(callNode: Any) {
      this.callNode = (N)callNode;
   }

   public fun getCallNode(): Any? {
      return this.callNode;
   }

   public fun hasCallNode(): Boolean {
      return this.callNode != null;
   }

   public fun getCallEdgeValue(node: Any, callee: Any, entryValue: Any): Any? {
      val var10000: java.util.Map = this.callCalleeEdgeValues;
      return (A)var10000.get(new Triple(node, callee, entryValue));
   }

   public fun setCallEdgeValue(node: Any, callee: Any, entryValue: Any, out: Any) {
      val var10000: java.util.Map = this.callCalleeEdgeValues;
      var10000.put(new Triple(node, callee, entryValue), out);
   }

   @JvmStatic
   fun `_init_$lambda$1$lambda$0`(`$numbers`: java.util.Map, u: Pair, v: Pair): Int {
      val var10000: Any = `$numbers`.get(u);
      val var3: Int = (var10000 as java.lang.Number).intValue();
      val var10001: Any = `$numbers`.get(v);
      return var3 - (var10001 as java.lang.Number).intValue();
   }

   public companion object {
      public final val count: AtomicInteger

      public fun reset() {
         this.getCount().set(0);
      }
   }
}
