package com.feysh.corax.cache.analysis

import soot.SootMethod

public data class SootMethodAndRange(sm: SootMethod, range: Pair<Int, Int>) {
   public final val sm: SootMethod
   public final val range: Pair<Int, Int>

   init {
      this.sm = sm;
      this.range = range;
   }

   public operator fun component1(): SootMethod {
      return this.sm;
   }

   public operator fun component2(): Pair<Int, Int> {
      return this.range;
   }

   public fun copy(sm: SootMethod = this.sm, range: Pair<Int, Int> = this.range): SootMethodAndRange {
      return new SootMethodAndRange(sm, range);
   }

   public override fun toString(): String {
      return "SootMethodAndRange(sm=${this.sm}, range=${this.range})";
   }

   public override fun hashCode(): Int {
      return this.sm.hashCode() * 31 + this.range.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootMethodAndRange) {
         return false;
      } else {
         val var2: SootMethodAndRange = other as SootMethodAndRange;
         if (!(this.sm == (other as SootMethodAndRange).sm)) {
            return false;
         } else {
            return this.range == var2.range;
         }
      }
   }
}
