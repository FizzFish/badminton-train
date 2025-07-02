package com.feysh.corax.config.api

public interface IUnOpExpr : IExpr {
   public val op: UnOp
   public val op1: IExpr
}
