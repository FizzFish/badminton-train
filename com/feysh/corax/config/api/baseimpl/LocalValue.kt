package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IGlobal
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.MParameter
import com.feysh.corax.config.api.MReturn
import com.feysh.corax.config.api.SubFields

internal class LocalValue<T>(iLocalT: ILocalT<Any>) : AbstractLocalValue<T> {
   public final val iLocalT: ILocalT<Any>

   public open val rvalue: IExpr
      public open get() {
         return this.iLocalT.getExpr();
      }


   init {
      this.iLocalT = iLocalT;
   }

   public override fun setValue(value: IExpr): IStmt {
      val var10000: IStmt;
      if (this.iLocalT is IParameterT) {
         if ((this.iLocalT as IParameterT).getIndex() == -2) {
            throw new IllegalStateException("Global instance can't be store!!!".toString());
         }

         var10000 = new IstStoreLocal(new MParameter((this.iLocalT as IParameterT).getIndex()), value);
      } else if (this.iLocalT is IReturnT) {
         var10000 = new IstStoreLocal(MReturn.INSTANCE, value);
      } else if (this.iLocalT is IWithSubFieldsT) {
         var10000 = new IstSetField((this.iLocalT as IWithSubFieldsT).getExpr(), SubFields.INSTANCE, value);
      } else {
         if (this.iLocalT !is IAccessPathT) {
            if (this.iLocalT is IGlobal) {
               throw new IllegalStateException("not support store global static variable!".toString());
            }

            throw new NoWhenBranchMatchedException();
         }

         var10000 = new IstSetField((this.iLocalT as IAccessPathT).getBase(), (this.iLocalT as IAccessPathT).getField(), value);
      }

      return var10000;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(this.iLocalT);
   }
}
