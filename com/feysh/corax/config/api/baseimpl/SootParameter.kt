package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.MGlobal
import com.feysh.corax.config.api.MParameter
import kotlin.jvm.internal.SourceDebugExtension
import soot.RefType
import soot.Scene
import soot.SootMethod
import soot.Type

@SourceDebugExtension(["SMAP\nAIAnalysisBaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/SootParameter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1477:1\n1#2:1478\n*E\n"])
public class SootParameter<T>(index: Int, method: SootMethod) : IParameterT<T> {
   public open val index: Int
   public final val method: SootMethod
   public open val expr: IExpr

   public final val type: Type
      public final get() {
         val var1: Type;
         if (this.getIndex() == -1) {
            val var10000: RefType = this.method.getDeclaringClass().getType();
            var1 = var10000 as Type;
         } else if (this.getIndex() == -2) {
            val var2: RefType = Scene.v().getObjectType();
            var1 = var2 as Type;
         } else {
            var1 = this.method.getParameterType(this.getIndex());
         }

         return var1;
      }


   init {
      this.index = index;
      this.method = method;
      if (this.getIndex() < -2) {
         throw new IllegalStateException(("invalid argument index: ${this.getIndex()} ").toString());
      } else if (this.getIndex() >= 0 && this.getIndex() >= this.method.getParameterCount()) {
         throw new ConfigException(TuplesKt.to(this, this.method));
      } else if (this.getIndex() == -1 && this.method.isStatic()) {
         throw new ConfigException(TuplesKt.to(this, this.method));
      } else {
         this.expr = new IexLoad(if (this.getIndex() == -2) MGlobal.INSTANCE else new MParameter(this.getIndex()));
      }
   }

   public override fun toString(): String {
      return if (this.getIndex() == -1) "this" else (if (this.getIndex() == -2) "global" else "arg${this.getIndex()}");
   }
}
