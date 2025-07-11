package cn.sast.dataflow.interprocedural.check

public data class ExitInvoke(invoke: InvokeEdgePath) {
   public final val invoke: InvokeEdgePath

   init {
      this.invoke = invoke;
   }

   public operator fun component1(): InvokeEdgePath {
      return this.invoke;
   }

   public fun copy(invoke: InvokeEdgePath = this.invoke): ExitInvoke {
      return new ExitInvoke(invoke);
   }

   public override fun toString(): String {
      return "ExitInvoke(invoke=${this.invoke})";
   }

   public override fun hashCode(): Int {
      return this.invoke.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ExitInvoke) {
         return false;
      } else {
         return this.invoke == (other as ExitInvoke).invoke;
      }
   }
}
