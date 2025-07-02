package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.BinOp
import com.feysh.corax.config.api.IBinOpExpr
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IModelExpressionVisitor

public class BinOpExpr(op: BinOp, op1: IExpr, op2: IExpr) : IBinOpExpr {
   public open val op: BinOp
   public open val op1: IExpr
   public open val op2: IExpr

   init {
      this.op = op;
      this.op1 = op1;
      this.op2 = op2;
   }

   public override fun toString(): String {
      return "${this.getOp()}( ${this.getOp1()}, ${this.getOp2()} )";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
