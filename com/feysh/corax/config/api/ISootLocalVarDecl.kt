package com.feysh.corax.config.api

import soot.Local
import soot.Type

public interface ISootLocalVarDecl<T> : ILocalVarDecl<T> {
   public val local: Local

   public open val name: String?
      public open get() {
      }


   public open val type: Type
      public open get() {
      }


   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <T> getName(`$this`: ISootLocalVarDecl<T>): java.lang.String? {
         return `$this`.getLocal().getName();
      }

      @JvmStatic
      fun <T> getType(`$this`: ISootLocalVarDecl<T>): Type {
         val var10000: Type = `$this`.getLocal().getType();
         return var10000;
      }
   }
}
