package com.feysh.corax.config.api

public interface IIexGetField : IExpr {
   public val base: IExpr
   public val accessPath: List<IClassField>
}
