package com.feysh.corax.cache.analysis.cpg

import de.fraunhofer.aisec.cpg.frontends.Language
import de.fraunhofer.aisec.cpg.frontends.java.JavaLanguage
import kotlin.enums.EnumEntries

public enum class Lang(language: Language<*>) {
   JAVA((new JavaLanguage()) as Language<?>)
   public final val language: Language<*>

   init {
      this.language = language;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<Lang> {
      return $ENTRIES;
   }
}
