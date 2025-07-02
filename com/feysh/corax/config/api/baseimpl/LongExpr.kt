package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.ILongExpr

internal class LongExpr(expr: IExpr) : ILongExpr {
   public open val expr: IExpr

   init {
      this.expr = expr;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getExpr());
   }
}
