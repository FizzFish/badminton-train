package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.MGlobal
import com.feysh.corax.config.api.MParameter
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAIAnalysisBaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/Parameter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1477:1\n1#2:1478\n*E\n"])
public class Parameter<T>(index: Int) : IParameterT<T> {
   public open val index: Int
   public open val expr: IExpr

   init {
      this.index = index;
      if (this.getIndex() < -2) {
         throw new IllegalStateException(("invalid argument index: ${this.getIndex()} ").toString());
      } else {
         this.expr = new IexLoad(if (this.getIndex() == -2) MGlobal.INSTANCE else new MParameter(this.getIndex()));
      }
   }

   public override fun toString(): String {
      return if (this.getIndex() == -1) "this" else (if (this.getIndex() == -2) "global" else "arg${this.getIndex()}");
   }
}
