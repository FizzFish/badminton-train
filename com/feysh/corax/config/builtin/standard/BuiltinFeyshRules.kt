package com.feysh.corax.config.builtin.standard

import com.feysh.corax.config.api.IRule
import com.feysh.corax.config.api.IStandard
import kotlin.enums.EnumEntries

public enum class BuiltinFeyshRules(realName: String, desc: String) : IRule {
   DeadCode("feysh.java.dead-code", BuiltinCWERules.CWE561_DeadCode.getDesc()),
   DefineUnused("feysh.java.define-unused", BuiltinCWERules.CWE563_UnusedVariable.getDesc()),
   DeadStore("feysh.java.dead-store", "cwe-563")
   public open val realName: String
   public open val desc: String
   public open val standard: IStandard

   init {
      this.realName = realName;
      this.desc = desc;
      this.standard = BuiltinGeneralStandard.FEYSH;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<BuiltinFeyshRules> {
      return $ENTRIES;
   }
}
