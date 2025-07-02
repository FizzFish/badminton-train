package com.feysh.corax.config.builtin.standard

import com.feysh.corax.config.api.IRule
import com.feysh.corax.config.api.IStandard
import kotlin.enums.EnumEntries

public enum class BuiltinCWERules(realName: String, desc: String) : IRule {
   CWE561_DeadCode("cwe-561", "Contains dead code, which can never be executed."),
   CWE563_UnusedVariable("cwe-563", "Assignment to Variable without Use")
   public open val realName: String
   public open val desc: String
   public open val standard: IStandard

   init {
      this.realName = realName;
      this.desc = desc;
      this.standard = BuiltinGeneralStandard.CWE;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<BuiltinCWERules> {
      return $ENTRIES;
   }
}
