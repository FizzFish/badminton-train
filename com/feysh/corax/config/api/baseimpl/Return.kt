package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.MReturn

public class Return<T> : IReturnT<T> {
   public open val expr: IExpr = (new IexLoad(MReturn.INSTANCE)) as IExpr

   public override fun toString(): String {
      return "Return";
   }
}
