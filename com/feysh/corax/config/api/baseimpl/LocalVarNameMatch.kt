package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.ILocalVarMatch
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import soot.Body
import soot.Local
import soot.Scene
import soot.SootMethod
import soot.jimple.JimpleBody
import soot.util.Chain

@SourceDebugExtension(["SMAP\nAIAnalysisBaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/LocalVarNameMatch\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1477:1\n774#2:1478\n865#2,2:1479\n*S KotlinDebug\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/LocalVarNameMatch\n*L\n890#1:1478\n890#1:1479,2\n*E\n"])
public class LocalVarNameMatch(sm: SootMethod, name: String) : ILocalVarMatch {
   public final val sm: SootMethod
   public final val name: String

   init {
      this.sm = sm;
      this.name = name;
   }

   public override fun matched(scene: Scene): List<Local> {
      if (!this.sm.hasActiveBody()) {
         return CollectionsKt.emptyList();
      } else {
         val `$i$f$filter`: Body = this.sm.getActiveBody();
         val var10000: JimpleBody = `$i$f$filter` as? JimpleBody;
         if ((`$i$f$filter` as? JimpleBody) == null) {
            return CollectionsKt.emptyList();
         } else {
            val var13: Chain = var10000.getLocals();
            val `$this$filter$iv`: java.lang.Iterable = var13 as java.lang.Iterable;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               val it: Local = `element$iv$iv` as Local;
               if (this.match(it)) {
                  `destination$iv$iv`.add(`element$iv$iv`);
               }
            }

            return `destination$iv$iv` as MutableList<Local>;
         }
      }
   }

   public fun match(local: Local): Boolean {
      return this.name == local.getName();
   }
}
