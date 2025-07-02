package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public data class MethodConfig(at: com.feysh.corax.config.api.MethodConfig.CheckCall) {
   public final var at: com.feysh.corax.config.api.MethodConfig.CheckCall
      internal set

   init {
      this.at = at;
   }

   public operator fun component1(): com.feysh.corax.config.api.MethodConfig.CheckCall {
      return this.at;
   }

   public fun copy(at: com.feysh.corax.config.api.MethodConfig.CheckCall = this.at): MethodConfig {
      return new MethodConfig(at);
   }

   public override fun toString(): String {
      return "MethodConfig(at=${this.at})";
   }

   public override fun hashCode(): Int {
      return this.at.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is MethodConfig) {
         return false;
      } else {
         return this.at === (other as MethodConfig).at;
      }
   }

   public enum class CheckCall {
      PrevCallInCaller,
      EvalCallInCaller,
      PrevCallInCallee,
      EvalCallInCallee,
      PostCallInCallee,
      PostCallInCaller
      @JvmStatic
      fun getEntries(): EnumEntries<MethodConfig.CheckCall> {
         return $ENTRIES;
      }
   }
}
