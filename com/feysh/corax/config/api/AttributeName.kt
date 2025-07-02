package com.feysh.corax.config.api

public class AttributeName(name: String, default: IIexConst? = null) : IClassField {
   public final val name: String
   public final val default: IIexConst?

   init {
      this.name = name;
      this.default = var2;
   }

   public override fun toString(): String {
      return this.name;
   }
}
