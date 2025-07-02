package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IExpr

internal class BoolExpr(expr: IExpr) : IBoolExpr {
   public open val expr: IExpr

   init {
      this.expr = expr;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getExpr());
   }
}
