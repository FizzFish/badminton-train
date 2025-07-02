package com.feysh.corax.config.api

public class CustomAttributeID<T>(name: String, default: IIexConst? = null) {
   public final val name: String
   public final val default: IIexConst?
   public final val attributeName: AttributeName

   init {
      this.name = name;
      this.default = var2;
      this.attributeName = new AttributeName(this.name, this.default);
   }
}
