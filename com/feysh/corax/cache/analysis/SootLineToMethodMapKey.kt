package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import soot.SootClass

public data class SootLineToMethodMapKey(sc: SootClass) : AnalysisKey(SootLineToMethodMapFactory.INSTANCE.getKey()) {
   public final val sc: SootClass

   init {
      this.sc = sc;
   }

   public operator fun component1(): SootClass {
      return this.sc;
   }

   public fun copy(sc: SootClass = this.sc): SootLineToMethodMapKey {
      return new SootLineToMethodMapKey(sc);
   }

   public override fun toString(): String {
      return "SootLineToMethodMapKey(sc=${this.sc})";
   }

   public override fun hashCode(): Int {
      return this.sc.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootLineToMethodMapKey) {
         return false;
      } else {
         return this.sc == (other as SootLineToMethodMapKey).sc;
      }
   }
}
