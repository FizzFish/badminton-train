package com.feysh.corax.config.api

public interface IChecker {
   public open val simpleName: String
      public open get() {
      }


   public val report: IRule
   public val standards: Set<IRule>

   public open val desc: String
      public open get() {
      }


   public open fun validate() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun getSimpleName(`$this`: IChecker): java.lang.String {
         val var10000: java.lang.String = `$this`.getClass().getSimpleName();
         return var10000;
      }

      @JvmStatic
      fun getDesc(`$this`: IChecker): java.lang.String {
         return `$this`.getReport().getDesc();
      }

      @JvmStatic
      fun validate(`$this`: IChecker) {
      }
   }
}
