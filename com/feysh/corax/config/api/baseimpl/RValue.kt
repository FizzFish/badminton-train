package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IStmt

internal class RValue<T>(rvalue: IExpr) : AbstractLocalValue<T> {
   public open val rvalue: IExpr

   init {
      this.rvalue = rvalue;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getRvalue());
   }

   public override fun setValue(value: IExpr): IStmt {
      throw new IllegalStateException(("store $this is undefined behavior").toString());
   }
}
