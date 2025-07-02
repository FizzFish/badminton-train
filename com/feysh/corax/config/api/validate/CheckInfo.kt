package com.feysh.corax.config.api.validate

import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IBoolExpr

public class CheckInfo(checkExpr: IBoolExpr, check: CheckType) {
   public final val checkExpr: IBoolExpr
   public final val check: CheckType

   init {
      this.checkExpr = checkExpr;
      this.check = check;
   }

   public override fun toString(): String {
      return "report bug ${this.check} if ${this.checkExpr}";
   }
}
