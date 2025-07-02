package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration

public data class CPGCompilationUnitKey(cpgKeyConfiguration: CPGKeyConfiguration) : AnalysisKey(CompilationUnitOfCPGDataFactory.INSTANCE.getKey()) {
   public final val cpgKeyConfiguration: CPGKeyConfiguration

   init {
      this.cpgKeyConfiguration = cpgKeyConfiguration;
   }

   public operator fun component1(): CPGKeyConfiguration {
      return this.cpgKeyConfiguration;
   }

   public fun copy(cpgKeyConfiguration: CPGKeyConfiguration = this.cpgKeyConfiguration): CPGCompilationUnitKey {
      return new CPGCompilationUnitKey(cpgKeyConfiguration);
   }

   public override fun toString(): String {
      return "CPGCompilationUnitKey(cpgKeyConfiguration=${this.cpgKeyConfiguration})";
   }

   public override fun hashCode(): Int {
      return this.cpgKeyConfiguration.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CPGCompilationUnitKey) {
         return false;
      } else {
         return this.cpgKeyConfiguration == (other as CPGCompilationUnitKey).cpgKeyConfiguration;
      }
   }
}
