package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIstSetField
import com.feysh.corax.config.api.IModelStmtVisitor

internal class IstSetField(base: IExpr, field: IClassField, value: IExpr) : IIstSetField {
   public open val base: IExpr
   public open val field: IClassField
   public open val value: IExpr

   init {
      this.base = base;
      this.field = field;
      this.value = value;
   }

   public override fun <TResult> accept(visitor: IModelStmtVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }

   public override fun toString(): String {
      return "assign ${this.getBase()}.${this.getField()} = ${this.getValue()}";
   }
}
