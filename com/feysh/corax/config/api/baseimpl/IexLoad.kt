package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IIexLoad
import com.feysh.corax.config.api.IModelExpressionVisitor
import com.feysh.corax.config.api.MLocal

public class IexLoad(op: MLocal) : IIexLoad {
   public open val op: MLocal

   init {
      this.op = op;
   }

   public override fun toString(): String {
      return "load(${this.getOp()})";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
