package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IViaType
import com.feysh.corax.config.api.ViaProperty

internal class ViaSet(value: IExpr) : IAttribute<ViaProperty, java.util.Set<? extends IViaType>> {
   public open val value: IExpr

   init {
      this.value = value;
   }

   public override fun toString(): String {
      return "GetViaSet( ${this.getValue()} )";
   }
}
