package com.feysh.corax.config.api

public interface IBinOpExpr : IExpr {
   public val op: BinOp
   public val op1: IExpr
   public val op2: IExpr
}
