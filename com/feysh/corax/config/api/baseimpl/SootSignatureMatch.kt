package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.utils.KFunctionType
import com.feysh.corax.config.api.utils.SootMethodDesc
import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.reflect.KCallable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import soot.Scene
import soot.SootMethod

@Serializable
@SerialName("SootSignatureMatch")
public class SootSignatureMatch(sm: SootMethodDesc, argumentCnt: Int?, actualType: KFunctionType?) : IMethodMatch {
   public final val sm: SootMethodDesc
   public open val argumentCnt: Int?
   public open val actualType: KFunctionType?

   @Transient
   public open val exception: Exception

   init {
      this.sm = sm;
      this.argumentCnt = argumentCnt;
      this.actualType = actualType;
      this.exception = new Exception();
   }

   public override fun matched(scene: Scene): List<SootMethod> {
      val var10000: SootMethod = scene.grabMethod(this.sm.getSignature());
      if (var10000 == null) {
         return CollectionsKt.emptyList();
      } else {
         if (this.getActualType() != null) {
            if (var10000.isConstructor() && this.getActualType() != KFunctionType.Constructor) {
               return CollectionsKt.emptyList();
            }

            if (var10000.isStatic() && this.getActualType() != KFunctionType.StaticMethod) {
               return CollectionsKt.emptyList();
            }

            if (!var10000.isStatic() && !var10000.isConstructor() && !var10000.isStaticInitializer() && this.getActualType() != KFunctionType.InstanceMethod) {
               return CollectionsKt.emptyList();
            }
         }

         return CollectionsKt.listOf(var10000);
      }
   }

   public override fun toString(): String {
      return "match sootMethod: ${this.sm}";
   }

   public companion object {
      public operator fun <R> invoke(function: () -> R): SootSignatureMatch {
         val call: KCallable = function as KCallable;
         val sootSig: java.lang.String = UtilsKt.getSootSignature(function as KCallable<?>);
         val var10000: SootMethodDesc = SootMethodDesc.Companion.invoke(sootSig);
         if (var10000 == null) {
            throw new IllegalStateException(("invalid soot signature: $sootSig").toString());
         } else {
            return new SootSignatureMatch(var10000, UtilsKt.getSootParamStringList(call).size(), UtilsKt.getFunctionType(call));
         }
      }

      public operator fun invoke(sootSig: String): SootSignatureMatch {
         val var10000: SootMethodDesc = SootMethodDesc.Companion.invoke(sootSig);
         if (var10000 == null) {
            throw new IllegalStateException(("invalid soot signature: $sootSig").toString());
         } else {
            return new SootSignatureMatch(var10000, var10000.getParameters().size(), null);
         }
      }

      public operator fun invoke(sm: SootMethod): SootSignatureMatch {
         return new SootSignatureMatch(SootMethodDesc.Companion.invoke(sm), sm.getParameterCount(), UtilsKt.getFunctionType(sm));
      }

      public fun serializer(): KSerializer<SootSignatureMatch> {
         return SootSignatureMatch.$serializer.INSTANCE as KSerializer<SootSignatureMatch>;
      }
   }
}
