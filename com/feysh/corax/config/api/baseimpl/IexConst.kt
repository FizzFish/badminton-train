package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IIexConst
import com.feysh.corax.config.api.IModelExpressionVisitor
import com.feysh.corax.config.api.IIexConst.Type

public class IexConst(const: Any, type: Type) : IIexConst {
   public open val const: Any
   public open val type: Type

   init {
      this.const = var1;
      this.type = type;
   }

   public override fun toString(): String {
      return "IexConst(${this.getConst()})";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
