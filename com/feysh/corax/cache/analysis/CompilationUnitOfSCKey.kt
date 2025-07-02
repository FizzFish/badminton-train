package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.javaparser.ParseResult
import com.github.javaparser.ast.CompilationUnit
import soot.SootClass

public data class CompilationUnitOfSCKey(sc: SootClass, factoryKey: Key<ParseResult<CompilationUnit>?>) : AnalysisKey(factoryKey) {
   public final val sc: SootClass
   public open val factoryKey: Key<ParseResult<CompilationUnit>?>

   init {
      this.sc = sc;
      this.factoryKey = factoryKey;
   }

   public operator fun component1(): SootClass {
      return this.sc;
   }

   public operator fun component2(): Key<ParseResult<CompilationUnit>?> {
      return this.factoryKey;
   }

   public fun copy(sc: SootClass = this.sc, factoryKey: Key<ParseResult<CompilationUnit>?> = this.factoryKey): CompilationUnitOfSCKey {
      return new CompilationUnitOfSCKey(sc, factoryKey);
   }

   public override fun toString(): String {
      return "CompilationUnitOfSCKey(sc=${this.sc}, factoryKey=${this.factoryKey})";
   }

   public override fun hashCode(): Int {
      return this.sc.hashCode() * 31 + this.factoryKey.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CompilationUnitOfSCKey) {
         return false;
      } else {
         val var2: CompilationUnitOfSCKey = other as CompilationUnitOfSCKey;
         if (!(this.sc == (other as CompilationUnitOfSCKey).sc)) {
            return false;
         } else {
            return this.factoryKey == var2.factoryKey;
         }
      }
   }
}
