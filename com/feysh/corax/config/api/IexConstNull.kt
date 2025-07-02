package com.feysh.corax.config.api

import com.feysh.corax.config.api.IIexConst.Type

public object IexConstNull : IIexConst {
   public open val const: Any
      public open get() {
         return IexConstNull.NULL.INSTANCE;
      }


   public open val type: Type
      public open get() {
         return IIexConst.Type.NULL;
      }


   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }

   public object NULL
}
