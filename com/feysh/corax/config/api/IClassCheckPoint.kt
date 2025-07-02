package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.UtilsKt
import soot.SootClass

public interface IClassCheckPoint : IClassMemberCheckPoint {
   public val sootClass: SootClass
   public val className: String

   public open val superClasses: Sequence<SootClass>
      public open get() {
      }


   public open val superInterfaces: Sequence<SootClass>
      public open get() {
      }


   public abstract fun eachMethod(block: (IMethodCheckPoint) -> Unit) {
   }

   public abstract fun eachField(block: (IFieldCheckPoint) -> Unit) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun getSuperClasses(`$this`: IClassCheckPoint): Sequence<SootClass> {
         return UtilsKt.getSuperClasses(`$this`.getSootClass());
      }

      @JvmStatic
      fun getSuperInterfaces(`$this`: IClassCheckPoint): Sequence<SootClass> {
         return UtilsKt.getSuperInterfaces(`$this`.getSootClass());
      }
   }
}
