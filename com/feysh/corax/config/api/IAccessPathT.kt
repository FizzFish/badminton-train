package com.feysh.corax.config.api

public interface IAccessPathT<T> : ILocalT<T> {
   public val base: IExpr
   public val field: IClassField
}
