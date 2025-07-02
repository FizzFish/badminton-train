package com.feysh.corax.config.api.utils

import kotlin.enums.EnumEntries

public enum class KFunctionType {
   Constructor,
   InstanceMethod,
   StaticMethod
   @JvmStatic
   fun getEntries(): EnumEntries<KFunctionType> {
      return $ENTRIES;
   }
}
