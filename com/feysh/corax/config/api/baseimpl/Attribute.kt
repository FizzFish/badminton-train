package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr

public class Attribute<K extends IClassField, V>(value: IExpr) : IAttribute<K, V> {
   public open val value: IExpr

   init {
      this.value = value;
   }
}
