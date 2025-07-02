package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.ITaintType
import com.feysh.corax.config.api.TaintProperty

internal class TaintSet(value: IExpr) : IAttribute<TaintProperty, java.util.Set<? extends ITaintType>> {
   public open val value: IExpr

   init {
      this.value = value;
   }

   public override fun toString(): String {
      return "GetTaintSet( ${this.getValue()} )";
   }
}
