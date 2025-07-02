package com.feysh.corax.config.api

import com.feysh.corax.config.api.IIexConst.Type

public object IexConstTrue : IIexConst {
   public open val const: Boolean
      public open get() {
         return true;
      }


   public open val type: Type
      public open get() {
         return IIexConst.Type.Boolean;
      }


   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }
}
