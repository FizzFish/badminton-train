package com.feysh.corax.config.api

import soot.Unit
import soot.jimple.Expr

public interface IUnitCheckPoint : IClassMemberCheckPoint {
   public val unit: Unit

   public abstract fun eachExpr(block: (Expr) -> kotlin.Unit) {
   }
}
