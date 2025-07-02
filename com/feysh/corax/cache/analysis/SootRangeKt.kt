package com.feysh.corax.cache.analysis

import soot.Unit
import soot.UnitPatchingChain
import soot.tagkit.Host
import soot.tagkit.LineNumberTag
import soot.tagkit.SourceLnNamePosTag
import soot.tagkit.Tag

public final val range: Pair<Int, Int>?
   public final get() {
      if (!`$this$range`.hasActiveBody()) {
         return null;
      } else {
         val units: UnitPatchingChain = `$this$range`.getActiveBody().getUnits();
         var var8: Int = -1;
         var var9: Int = -1;
         val var10000: java.util.Iterator = units.iterator();
         val var4: java.util.Iterator = var10000;

         while (var4.hasNext()) {
            val u: Unit = var4.next() as Unit;
            u.getJavaSourceStartLineNumber();
            val var10: Int = findLineNumber(u as Host);
            if (var10 != null) {
               val it: Int = var10.intValue();
               if (var8 == -1) {
                  var8 = it;
               }

               var8 = Math.min(var8, it);
               var9 = Math.max(var9, it);
            }
         }

         if (var8 != -1 && var9 != -1) {
            return if (var8 > var9) TuplesKt.to(var9, var9) else TuplesKt.to(var8, var9);
         } else if (var8 != -1) {
            return TuplesKt.to(var8, var8);
         } else {
            return if (var9 != -1) TuplesKt.to(var9, var9) else null;
         }
      }
   }


internal fun Host.findLineNumber(): Int? {
   if (`$this$findLineNumber`.getJavaSourceStartLineNumber() != -1) {
      return `$this$findLineNumber`.getJavaSourceStartLineNumber();
   } else {
      for (Tag tag : $this$findLineNumber.getTags()) {
         if (tag is LineNumberTag && (tag as LineNumberTag).getLineNumber() >= 0) {
            return (tag as LineNumberTag).getLineNumber();
         }

         if (tag is SourceLnNamePosTag && (tag as SourceLnNamePosTag).startLn() >= 0) {
            return (tag as SourceLnNamePosTag).startLn();
         }
      }

      return null;
   }
}
