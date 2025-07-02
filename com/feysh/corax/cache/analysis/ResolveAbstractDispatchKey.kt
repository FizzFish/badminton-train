package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import soot.SootClass
import soot.SootMethodRef

public data class ResolveAbstractDispatchKey(baseType: SootClass, sourceMethodRef: SootMethodRef) : AnalysisKey(ResolveAbstractDispatchFactory.INSTANCE.getKey()) {
   public final val baseType: SootClass
   public final val sourceMethodRef: SootMethodRef

   init {
      this.baseType = baseType;
      this.sourceMethodRef = sourceMethodRef;
   }

   public operator fun component1(): SootClass {
      return this.baseType;
   }

   public operator fun component2(): SootMethodRef {
      return this.sourceMethodRef;
   }

   public fun copy(baseType: SootClass = this.baseType, sourceMethodRef: SootMethodRef = this.sourceMethodRef): ResolveAbstractDispatchKey {
      return new ResolveAbstractDispatchKey(baseType, sourceMethodRef);
   }

   public override fun toString(): String {
      return "ResolveAbstractDispatchKey(baseType=${this.baseType}, sourceMethodRef=${this.sourceMethodRef})";
   }

   public override fun hashCode(): Int {
      return this.baseType.hashCode() * 31 + this.sourceMethodRef.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ResolveAbstractDispatchKey) {
         return false;
      } else {
         val var2: ResolveAbstractDispatchKey = other as ResolveAbstractDispatchKey;
         if (!(this.baseType == (other as ResolveAbstractDispatchKey).baseType)) {
            return false;
         } else {
            return this.sourceMethodRef == var2.sourceMethodRef;
         }
      }
   }
}
