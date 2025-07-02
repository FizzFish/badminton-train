package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassMatch
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import soot.Scene
import soot.SootClass

@Serializable
@SerialName("FullClassMatch")
public class FullClassMatch(className: String) : IClassMatch {
   public final val className: String
   public open val simpleName: List<String>

   init {
      this.className = className;
      this.simpleName = CollectionsKt.listOf(StringsKt.substringAfterLast$default(this.className, ".", null, 2, null));
   }

   public constructor(sc: SootClass)  {
      val var10001: java.lang.String = sc.getName();
      this(var10001);
   }

   public override fun matched(scene: Scene): List<SootClass> {
      val var10000: SootClass = Scene.v().getSootClassUnsafe(this.className, false);
      return if (var10000 == null) CollectionsKt.emptyList() else CollectionsKt.listOf(var10000);
   }

   public override fun toString(): String {
      return this.className;
   }

   public companion object {
      public fun serializer(): KSerializer<FullClassMatch> {
         return FullClassMatch.$serializer.INSTANCE as KSerializer<FullClassMatch>;
      }
   }
}
