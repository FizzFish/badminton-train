package com.feysh.corax.config.api.baseimpl

public class ConfigException(value: Any) : Exception {
   public final val value: Any

   init {
      this.value = value;
   }

   public override fun toString(): String {
      val var10000: Array<StackTraceElement> = this.getStackTrace();
      return "${ArraysKt.firstOrNull(var10000)} `${this.value}` is incorrect";
   }
}
