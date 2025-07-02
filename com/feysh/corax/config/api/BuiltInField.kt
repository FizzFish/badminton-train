package com.feysh.corax.config.api

public sealed class BuiltInField protected constructor(name: String) : IClassField {
   public final val name: String

   init {
      this.name = name;
   }

   public override fun toString(): String {
      return this.name;
   }
}
