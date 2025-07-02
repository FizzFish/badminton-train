package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public enum class TriOp {
   ITE
   @JvmStatic
   fun getEntries(): EnumEntries<TriOp> {
      return $ENTRIES;
   }
}
