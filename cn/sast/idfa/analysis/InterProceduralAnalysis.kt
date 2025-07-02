package cn.sast.idfa.analysis

public abstract class InterProceduralAnalysis<M, N, A> {
   protected final val reverse: Boolean

   open fun InterProceduralAnalysis(reverse: Boolean) {
      this.reverse = reverse;
      Context.Companion.reset();
   }

   public abstract fun boundaryValue(entryPoint: Any): Any {
   }

   public abstract fun copy(src: Any): Any {
   }

   public abstract fun doAnalysis(entries: Collection<Any>) {
   }

   public abstract fun meet(op1: Any, op2: Any): Any {
   }

   public abstract fun shallowMeet(op1: Any, op2: Any): Any {
   }

   public abstract fun merge(local: Any, ret: Any): Any {
   }

   public abstract fun programRepresentation(): ProgramRepresentation<Any, Any> {
   }

   public abstract fun bottomValue(): Any {
   }
}
