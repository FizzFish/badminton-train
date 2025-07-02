package com.feysh.corax.config.api

public sealed interface IExpr {
   public abstract fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
   }
}
