package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisKey
import java.nio.file.Path

public data class CompilationUnitAnalysisKey(sourceFile: Path) : AnalysisKey(CompilationUnitAnalysisDataFactory.INSTANCE.getKey()) {
   public final val sourceFile: Path

   init {
      this.sourceFile = sourceFile;
   }

   public operator fun component1(): Path {
      return this.sourceFile;
   }

   public fun copy(sourceFile: Path = this.sourceFile): CompilationUnitAnalysisKey {
      return new CompilationUnitAnalysisKey(sourceFile);
   }

   public override fun toString(): String {
      return "CompilationUnitAnalysisKey(sourceFile=${this.sourceFile})";
   }

   public override fun hashCode(): Int {
      return this.sourceFile.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CompilationUnitAnalysisKey) {
         return false;
      } else {
         return this.sourceFile == (other as CompilationUnitAnalysisKey).sourceFile;
      }
   }
}
