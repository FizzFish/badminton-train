package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IModelExpressionVisitor
import com.feysh.corax.config.api.ITriOpExpr
import com.feysh.corax.config.api.TriOp

public class ITEExpr(op1: IExpr, op2: IExpr, op3: IExpr) : ITriOpExpr {
   public open val op1: IExpr
   public open val op2: IExpr
   public open val op3: IExpr
   public open val op: TriOp

   init {
      this.op1 = op1;
      this.op2 = op2;
      this.op3 = op3;
      this.op = TriOp.ITE;
   }

   public override fun toString(): String {
      return "ITE(${this.getOp1()}, ${this.getOp2()}, ${this.getOp3()})";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
