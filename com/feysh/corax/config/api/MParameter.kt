package com.feysh.corax.config.api

public class MParameter(index: Int) : MLocal() {
   public final val index: Int

   init {
      this.index = index;
   }

   public override fun toString(): String {
      return if (this.index == -1) "this" else "p${this.index}";
   }
}
