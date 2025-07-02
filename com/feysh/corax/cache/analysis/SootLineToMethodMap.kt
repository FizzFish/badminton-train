package com.feysh.corax.cache.analysis

import java.util.TreeMap

public data class SootLineToMethodMap(map: TreeMap<Int, SootMethodAndRange>) {
   public final val map: TreeMap<Int, SootMethodAndRange>

   init {
      this.map = map;
   }

   public operator fun component1(): TreeMap<Int, SootMethodAndRange> {
      return this.map;
   }

   public fun copy(map: TreeMap<Int, SootMethodAndRange> = this.map): SootLineToMethodMap {
      return new SootLineToMethodMap(map);
   }

   public override fun toString(): String {
      return "SootLineToMethodMap(map=${this.map})";
   }

   public override fun hashCode(): Int {
      return this.map.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootLineToMethodMap) {
         return false;
      } else {
         return this.map == (other as SootLineToMethodMap).map;
      }
   }
}
