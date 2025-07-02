package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import com.feysh.corax.cache.AnalysisDataFactory.Key
import soot.tagkit.Host

public data class SootHostExtInfoKey(host: Host, factoryKey: Key<SootHostExtend?>) : AnalysisKey(factoryKey) {
   public final val host: Host
   public open val factoryKey: Key<SootHostExtend?>

   init {
      this.host = host;
      this.factoryKey = factoryKey;
   }

   public operator fun component1(): Host {
      return this.host;
   }

   public operator fun component2(): Key<SootHostExtend?> {
      return this.factoryKey;
   }

   public fun copy(host: Host = this.host, factoryKey: Key<SootHostExtend?> = this.factoryKey): SootHostExtInfoKey {
      return new SootHostExtInfoKey(host, factoryKey);
   }

   public override fun toString(): String {
      return "SootHostExtInfoKey(host=${this.host}, factoryKey=${this.factoryKey})";
   }

   public override fun hashCode(): Int {
      return this.host.hashCode() * 31 + this.factoryKey.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootHostExtInfoKey) {
         return false;
      } else {
         val var2: SootHostExtInfoKey = other as SootHostExtInfoKey;
         if (!(this.host == (other as SootHostExtInfoKey).host)) {
            return false;
         } else {
            return this.factoryKey == var2.factoryKey;
         }
      }
   }
}
