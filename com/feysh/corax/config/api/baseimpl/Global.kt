package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IGlobal
import com.feysh.corax.config.api.MGlobal

public data object Global : IGlobal {
   public open val expr: IExpr = (new IexLoad(MGlobal.INSTANCE)) as IExpr

   public override fun toString(): String {
      return "Global";
   }

   public override fun hashCode(): Int {
      return 1101842269;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is Global;
      }
   }
}
