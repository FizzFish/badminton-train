package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public enum class QOp {
   @JvmStatic
   fun getEntries(): EnumEntries<QOp> {
      return $ENTRIES;
   }
}
