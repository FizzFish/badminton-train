package com.feysh.corax.config.api

import soot.SootMethod
import soot.tagkit.VisibilityAnnotationTag

public interface IMethodCheckPoint : IClassMemberCheckPoint {
   public val sootMethod: SootMethod
   public val visibilityAnnotationTag: VisibilityAnnotationTag?

   public abstract fun eachUnit(block: (IUnitCheckPoint) -> Unit) {
   }
}
