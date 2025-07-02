package com.feysh.corax.cache.analysis.defuse;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import soot.IdentityUnit;
import soot.Local;
import soot.Timers;
import soot.Trap;
import soot.Unit;
import soot.ValueBox;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.ExceptionalGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.graph.ExceptionalGraph.ExceptionDest;
import soot.toolkits.scalar.ForwardFlowAnalysis;
import soot.toolkits.scalar.LocalDefs;
import soot.toolkits.scalar.FlowAnalysis.Flow;
import soot.toolkits.scalar.SimpleLocalDefs.FlowAnalysisMode;

public class SimpleLocalDefs implements LocalDefs {
   private final LocalDefs def;
   private Map<Local, Integer> localNumberMap;

   public SimpleLocalDefs(UnitGraph graph) {
      this(graph, FlowAnalysisMode.Automatic);
   }

   public SimpleLocalDefs(UnitGraph graph, FlowAnalysisMode mode) {
      this(graph, graph.getBody().getLocals(), mode);
   }

   public SimpleLocalDefs(DirectedGraph<Unit> graph, Collection<Local> locals, FlowAnalysisMode mode) {
      this(graph, locals.toArray(new Local[locals.size()]), mode);
   }

   protected SimpleLocalDefs(DirectedGraph<Unit> graph, Local[] locals, boolean omitSSA) {
      this(graph, locals, omitSSA ? FlowAnalysisMode.OmitSSA : FlowAnalysisMode.Automatic);
   }

   protected SimpleLocalDefs(DirectedGraph<Unit> graph, Local[] locals, FlowAnalysisMode mode) {
      boolean time = Options.v().time();
      if (time) {
         Timers.v().defsTimer.start();
      }

      this.localNumberMap = this.number2index(locals);
      this.def = this.init(graph, locals, mode);
      this.localNumberMap = null;
      if (time) {
         Timers.v().defsTimer.end();
      }
   }

   protected void restoreNumbers(Local[] locals, int[] oldNumbers) {
      for (int i = 0; i < oldNumbers.length; i++) {
         locals[i].setNumber(oldNumbers[i]);
      }
   }

   protected Map<Local, Integer> number2index(Local[] locals) {
      int N = locals.length;
      Map<Local, Integer> localNumberMap = Maps.newHashMapWithExpectedSize(N);

      for (int i = 0; i < N; i++) {
         localNumberMap.put(locals[i], i);
      }

      return localNumberMap;
   }

   protected LocalDefs init(DirectedGraph<Unit> graph, Local[] locals, FlowAnalysisMode mode) {
      List<Unit>[] unitList = new List[locals.length];
      Arrays.fill(unitList, Collections.emptyList());
      boolean omitSSA = mode == FlowAnalysisMode.OmitSSA;
      boolean doFlowAnalsis = omitSSA;
      int units = 0;

      for (Unit unit : graph) {
         for (ValueBox box : unit.getDefBoxes()) {
            if (box.getValue() instanceof Local l) {
               int lno = this.getLocalNumber(l);
               switch (unitList[lno].size()) {
                  case 0:
                     unitList[lno] = Collections.singletonList(unit);
                     if (omitSSA) {
                        units++;
                     }
                     break;
                  case 1:
                     if (!omitSSA) {
                        units++;
                     }

                     unitList[lno] = new ArrayList<>(unitList[lno]);
                     doFlowAnalsis = true;
                  default:
                     unitList[lno].add(unit);
                     units++;
               }
            }
         }
      }

      return (LocalDefs)(doFlowAnalsis && mode != FlowAnalysisMode.FlowInsensitive
         ? new SimpleLocalDefs.FlowAssignment(graph, locals, unitList, units, omitSSA)
         : new SimpleLocalDefs.StaticSingleAssignment(locals, unitList));
   }

   protected int getLocalNumber(Local l) {
      return this.localNumberMap.get(l);
   }

   public List<Unit> getDefsOfAt(Local l, Unit s) {
      return this.def.getDefsOfAt(l, s);
   }

   public List<Unit> getDefsOf(Local l) {
      return this.def.getDefsOf(l);
   }

   public class FlowAssignment extends ForwardFlowAnalysis<Unit, SimpleLocalDefs.FlowAssignment.FlowBitSet> implements LocalDefs {
      final Map<Local, Integer> locals;
      final List<Unit>[] unitList;
      final int[] localRange;
      final Unit[] universe;
      private Map<Unit, Integer> indexOfUnit;

      public FlowAssignment(DirectedGraph<Unit> graph, Local[] locals, List<Unit>[] unitList, int units, boolean omitSSA) {
         super(graph);
         this.unitList = unitList;
         this.universe = new Unit[units];
         this.indexOfUnit = new HashMap<>(units);
         int N = locals.length;
         this.locals = new HashMap<>(N * 3 / 2 + 7);
         this.localRange = new int[N + 1];
         int j = 0;

         for (int i = 0; i < N; this.localRange[++i] = j) {
            List<Unit> currUnitList = unitList[i];
            if (currUnitList != null && !currUnitList.isEmpty()) {
               this.localRange[i + 1] = j;
               this.locals.put(locals[i], i);
               if (currUnitList.size() >= 2) {
                  for (Unit u : currUnitList) {
                     this.indexOfUnit.put(u, j);
                     this.universe[j++] = u;
                  }
               } else if (omitSSA) {
                  this.universe[j++] = currUnitList.get(0);
               }
            }
         }

         assert this.localRange[N] == units;

         this.doAnalysis();
         this.indexOfUnit = null;
      }

      protected boolean omissible(Unit u) {
         List<ValueBox> defs = u.getDefBoxes();
         if (!defs.isEmpty()) {
            for (ValueBox vb : defs) {
               if (vb.getValue() instanceof Local l) {
                  int lno = SimpleLocalDefs.this.getLocalNumber(l);
                  return this.localRange[lno] == this.localRange[lno + 1];
               }
            }
         }

         return true;
      }

      protected Flow getFlow(Unit from, Unit to) {
         if (to instanceof IdentityUnit && this.graph instanceof ExceptionalGraph<Unit> g && !g.getExceptionalPredsOf(to).isEmpty()) {
            for (ExceptionDest<Unit> exd : g.getExceptionDests(from)) {
               Trap trap = exd.getTrap();
               if (trap != null && trap.getHandlerUnit() == to) {
                  return Flow.IN;
               }
            }
         }

         return Flow.OUT;
      }

      protected void flowThrough(SimpleLocalDefs.FlowAssignment.FlowBitSet in, Unit unit, SimpleLocalDefs.FlowAssignment.FlowBitSet out) {
         this.copy(in, out);

         for (ValueBox vb : unit.getDefBoxes()) {
            if (vb.getValue() instanceof Local l) {
               int lno = SimpleLocalDefs.this.getLocalNumber(l);
               int from = this.localRange[lno];
               int to = this.localRange[1 + lno];
               if (from != to) {
                  assert from <= to;

                  if (to - from == 1) {
                     out.set(from);
                  } else {
                     out.clear(from, to);
                     out.set(this.indexOfUnit.get(unit));
                  }
               }
            }
         }
      }

      protected void copy(SimpleLocalDefs.FlowAssignment.FlowBitSet source, SimpleLocalDefs.FlowAssignment.FlowBitSet dest) {
         if (dest != source) {
            dest.clear();
            dest.or(source);
         }
      }

      protected SimpleLocalDefs.FlowAssignment.FlowBitSet newInitialFlow() {
         return new SimpleLocalDefs.FlowAssignment.FlowBitSet();
      }

      protected void mergeInto(Unit succNode, SimpleLocalDefs.FlowAssignment.FlowBitSet inout, SimpleLocalDefs.FlowAssignment.FlowBitSet in) {
         inout.or(in);
      }

      protected void merge(
         SimpleLocalDefs.FlowAssignment.FlowBitSet in1, SimpleLocalDefs.FlowAssignment.FlowBitSet in2, SimpleLocalDefs.FlowAssignment.FlowBitSet out
      ) {
         throw new UnsupportedOperationException("should never be called");
      }

      public List<Unit> getDefsOfAt(Local l, Unit s) {
         Integer lno = this.locals.get(l);
         if (lno == null) {
            return Collections.emptyList();
         } else {
            int from = this.localRange[lno];
            int to = this.localRange[lno + 1];

            assert from <= to;

            if (from == to) {
               assert this.unitList[lno].size() == 1;

               return this.unitList[lno];
            } else {
               return ((SimpleLocalDefs.FlowAssignment.FlowBitSet)this.getFlowBefore(s)).asList(from, to);
            }
         }
      }

      public List<Unit> getDefsOf(Local l) {
         List<Unit> defs = new ArrayList<>();

         for (Unit u : this.graph) {
            List<Unit> defsOf = this.getDefsOfAt(l, u);
            if (defsOf != null) {
               defs.addAll(defsOf);
            }
         }

         return defs;
      }

      class FlowBitSet extends BitSet {
         private static final long serialVersionUID = -8348696077189400377L;

         FlowBitSet() {
            super(FlowAssignment.this.universe.length);
         }

         List<Unit> asList(int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex < fromIndex || FlowAssignment.this.universe.length < toIndex) {
               throw new IndexOutOfBoundsException();
            } else if (fromIndex == toIndex) {
               return Collections.emptyList();
            } else if (fromIndex != toIndex - 1) {
               int i = this.nextSetBit(fromIndex);
               if (i < 0 || i >= toIndex) {
                  return Collections.emptyList();
               } else if (i == toIndex - 1) {
                  return Collections.singletonList(FlowAssignment.this.universe[i]);
               } else {
                  List<Unit> elements = new ArrayList<>(toIndex - i);

                  do {
                     int endOfRun = Math.min(toIndex, this.nextClearBit(i + 1));

                     do {
                        elements.add(FlowAssignment.this.universe[i++]);
                     } while (i < endOfRun);

                     if (i >= toIndex) {
                        break;
                     }

                     i = this.nextSetBit(i + 1);
                  } while (i >= 0 && i < toIndex);

                  return elements;
               }
            } else {
               return this.get(fromIndex) ? Collections.singletonList(FlowAssignment.this.universe[fromIndex]) : Collections.emptyList();
            }
         }
      }
   }

   public static class StaticSingleAssignment implements LocalDefs {
      final Map<Local, List<Unit>> result;

      public StaticSingleAssignment(Local[] locals, List<Unit>[] unitList) {
         int N = locals.length;

         assert N == unitList.length;

         this.result = new HashMap<>(N * 3 / 2 + 7);

         for (int i = 0; i < N; i++) {
            List<Unit> curr = unitList[i];
            if (!curr.isEmpty()) {
               assert curr.size() == 1;

               this.result.put(locals[i], curr);
            }
         }
      }

      public List<Unit> getDefsOfAt(Local l, Unit s) {
         List<Unit> lst = this.result.get(l);
         return lst != null ? lst : Collections.emptyList();
      }

      public List<Unit> getDefsOf(Local l) {
         return this.getDefsOfAt(l, null);
      }
   }
}
