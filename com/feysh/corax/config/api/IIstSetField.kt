package com.feysh.corax.config.api

public interface IIstSetField : IStmt {
   public val base: IExpr
   public val field: IClassField
   public val value: IExpr
}
