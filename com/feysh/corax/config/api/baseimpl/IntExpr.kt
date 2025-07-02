package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIntExpr

internal class IntExpr(expr: IExpr) : IIntExpr {
   public open val expr: IExpr

   init {
      this.expr = expr;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getExpr());
   }
}
