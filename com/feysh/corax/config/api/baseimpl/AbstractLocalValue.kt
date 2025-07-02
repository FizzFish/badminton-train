package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.ILocalValue
import com.feysh.corax.config.api.IStmt

internal abstract class AbstractLocalValue<T> : ILocalValue<T> {
   public abstract fun setValue(value: IExpr): IStmt {
   }
}
