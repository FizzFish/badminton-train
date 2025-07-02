package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public enum class UnOp {
   GetSet,
   GetBoolean,
   GetInt,
   GetLong,
   GetString,
   GetEnumName,
   ToLowerCase,
   Not,
   IsConstant
   @JvmStatic
   fun getEntries(): EnumEntries<UnOp> {
      return $ENTRIES;
   }
}
