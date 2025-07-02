package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIstStoreLocal
import com.feysh.corax.config.api.IModelStmtVisitor
import com.feysh.corax.config.api.MLocal

internal class IstStoreLocal(local: MLocal, value: IExpr) : IIstStoreLocal {
   public open val local: MLocal
   public open val value: IExpr

   init {
      this.local = local;
      this.value = value;
   }

   public override fun <TResult> accept(visitor: IModelStmtVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }

   public override fun toString(): String {
      return "assign ${this.getLocal()} = ${this.getValue()}";
   }
}
