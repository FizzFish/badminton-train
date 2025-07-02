package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public interface IIexConst : IExpr {
   public val const: Any
   public val type: com.feysh.corax.config.api.IIexConst.Type

   public enum class Type {
      NULL,
      Boolean,
      Short,
      Int,
      Long,
      Float,
      Double,
      Class,
      String,
      TaintSet,
      ViaSet,
      EmptyElement
      @JvmStatic
      fun getEntries(): EnumEntries<IIexConst.Type> {
         return $ENTRIES;
      }
   }
}
