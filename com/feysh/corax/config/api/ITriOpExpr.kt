package com.feysh.corax.config.api

public interface ITriOpExpr : IExpr {
   public val op: TriOp
   public val op1: IExpr
   public val op2: IExpr
   public val op3: IExpr
}
