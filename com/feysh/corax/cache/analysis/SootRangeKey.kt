package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import soot.SootMethod

public data class SootRangeKey(sootMethod: SootMethod) : AnalysisKey(SootRangeFactory.INSTANCE.getKey()) {
   public final val sootMethod: SootMethod

   init {
      this.sootMethod = sootMethod;
   }

   public operator fun component1(): SootMethod {
      return this.sootMethod;
   }

   public fun copy(sootMethod: SootMethod = this.sootMethod): SootRangeKey {
      return new SootRangeKey(sootMethod);
   }

   public override fun toString(): String {
      return "SootRangeKey(sootMethod=${this.sootMethod})";
   }

   public override fun hashCode(): Int {
      return this.sootMethod.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootRangeKey) {
         return false;
      } else {
         return this.sootMethod == (other as SootRangeKey).sootMethod;
      }
   }
}
