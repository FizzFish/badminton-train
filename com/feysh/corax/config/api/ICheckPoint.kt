package com.feysh.corax.config.api

import soot.SootClass
import soot.SootMethod
import soot.Type

public interface ICheckPoint {
   public val possibleTypes: Set<Type>
   public val possibleConstantValues: Set<String>

   public abstract fun SootMethod.hasSideEffect(): Boolean {
   }

   public abstract fun SootClass.isInstanceOf(parent: String): Boolean? {
   }

   public abstract fun SootClass.isInstanceOf(parent: SootClass): Boolean {
   }
}
