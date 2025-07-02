package com.feysh.corax.config.builtin.standard

import com.feysh.corax.config.api.IStandard
import kotlin.enums.EnumEntries

public enum class BuiltinGeneralStandard(realName: String, desc: String) : IStandard {
   FEYSH("feysh", "feysh standard"),
   CWE("cwe", "Common Weakness Enumeration. https://cwe.mitre.org/index.html"),
   CERT("cert", "SEI CERT Oracle Coding Standard for Java. https://wiki.sei.cmu.edu/confluence/display/java")
   public open val realName: String
   public open val desc: String

   init {
      this.realName = realName;
      this.desc = desc;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<BuiltinGeneralStandard> {
      return $ENTRIES;
   }
}
