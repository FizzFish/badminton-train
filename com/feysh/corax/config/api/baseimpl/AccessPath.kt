package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr

public class AccessPath<T>(base: IExpr, field: IClassField) : IAccessPathT<T> {
   public open val base: IExpr
   public open val field: IClassField

   public open val expr: IExpr
      public open get() {
         return IexGetFieldExpr.Companion.invoke(this.getBase(), this.getField());
      }


   init {
      this.base = base;
      this.field = field;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.getExpr());
   }
}
