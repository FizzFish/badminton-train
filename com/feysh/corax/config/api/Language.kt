package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public enum class Language {
   ZH,
   EN
   @JvmStatic
   fun getEntries(): EnumEntries<Language> {
      return $ENTRIES;
   }
}
