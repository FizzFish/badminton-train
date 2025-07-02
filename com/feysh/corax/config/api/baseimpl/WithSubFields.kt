package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.SubFields

public class WithSubFields(base: IExpr) : IWithSubFieldsT {
   public final val base: IExpr
   public open val expr: IExpr

   init {
      this.base = base;
      this.expr = IexGetFieldExpr.Companion.invoke(this.base, SubFields.INSTANCE);
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getExpr());
   }
}
