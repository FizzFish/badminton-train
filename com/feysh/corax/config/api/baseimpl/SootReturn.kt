package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.MReturn
import soot.SootMethod
import soot.Type

public class SootReturn<T>(method: SootMethod) : IReturnT<T> {
   public final val method: SootMethod
   public open val expr: IExpr
   public final val type: Type

   init {
      this.method = method;
      this.expr = new IexLoad(MReturn.INSTANCE);
      val var10001: Type = this.method.getReturnType();
      this.type = var10001;
   }

   public override fun toString(): String {
      return "Return";
   }
}
