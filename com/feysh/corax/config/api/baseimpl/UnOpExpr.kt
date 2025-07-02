package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IModelExpressionVisitor
import com.feysh.corax.config.api.IUnOpExpr
import com.feysh.corax.config.api.UnOp

public class UnOpExpr(op: UnOp, op1: IExpr) : IUnOpExpr {
   public open val op: UnOp
   public open val op1: IExpr

   init {
      this.op = op;
      this.op1 = op1;
   }

   public override fun toString(): String {
      return "${this.getOp()}( ${this.getOp1()} )";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
