package com.feysh.corax.config.api.report

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.analysis.SootInfoCache
import com.feysh.corax.cache.analysis.SootMethodExtend
import com.github.javaparser.Position
import com.github.javaparser.Range
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import java.util.Optional
import java.util.regex.Matcher
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import soot.SootMethod
import soot.Unit
import soot.tagkit.AbstractHost
import soot.tagkit.Host
import soot.tagkit.SourceLnPosTag

@SourceDebugExtension(["SMAP\nRegion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Region.kt\ncom/feysh/corax/config/api/report/Region\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,187:1\n57#1:189\n59#1:190\n57#1,2:192\n1#2:188\n1#2:191\n*S KotlinDebug\n*F\n+ 1 Region.kt\ncom/feysh/corax/config/api/report/Region\n*L\n59#1:189\n60#1:190\n60#1:192,2\n60#1:191\n*E\n"])
public data class Region(startLine: Int, startColumn: Int, endLine: Int, endColumn: Int) : java.lang.Comparable<Region> {
   public final val startLine: Int
   public final val startColumn: Int
   public final val endLine: Int
   public final val endColumn: Int

   public final val mutable: com.feysh.corax.config.api.report.Region.Mutable
      public final get() {
         return new Region.Mutable(this.startLine, this.startColumn, this.endLine, this.endColumn);
      }


   public final val valid: Boolean
      public final inline get() {
         return this.startLine >= 0;
      }


   public final val columnValid: Boolean
      public final inline get() {
         return this.startColumn >= 0 && this.getEndColumn() >= 0;
      }


   public final val takeIfValid: Region?
      public final inline get() {
         val it: Region = this;
         return if (this.startLine >= 0) this else null;
      }


   public final val takeIfColumnValid: Region?
      public final inline get() {
         val `this_$iv`: Region = this;
         return if (this.startColumn >= 0 && this.getEndColumn() >= 0) (if (this.startLine >= 0) this else null) else null;
      }


   init {
      this.startLine = startLine;
      this.startColumn = startColumn;
      this.endLine = endLine;
      this.endColumn = endColumn;
   }

   public override fun toString(): String {
      return "${this.startLine}:${this.startColumn}-${this.endLine}:${this.endColumn}";
   }

   public open operator fun compareTo(other: Region): Int {
      var var3: Int = Intrinsics.compare(this.startLine, other.startLine);
      if (var3 != 0) {
         return var3;
      } else {
         var3 = Intrinsics.compare(this.startColumn, other.startColumn);
         if (var3 != 0) {
            return var3;
         } else {
            var3 = Intrinsics.compare(this.endLine, other.endLine);
            if (var3 != 0) {
               return -var3;
            } else {
               var3 = Intrinsics.compare(this.endColumn, other.endColumn);
               return if (var3 != 0) -var3 else var3;
            }
         }
      }
   }

   public operator fun component1(): Int {
      return this.startLine;
   }

   public operator fun component2(): Int {
      return this.startColumn;
   }

   public operator fun component3(): Int {
      return this.endLine;
   }

   public operator fun component4(): Int {
      return this.endColumn;
   }

   public fun copy(startLine: Int = this.startLine, startColumn: Int = this.startColumn, endLine: Int = this.endLine, endColumn: Int = this.endColumn): Region {
      return new Region(startLine, startColumn, endLine, endColumn);
   }

   public override fun hashCode(): Int {
      return ((Integer.hashCode(this.startLine) * 31 + Integer.hashCode(this.startColumn)) * 31 + Integer.hashCode(this.endLine)) * 31
         + Integer.hashCode(this.endColumn);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is Region) {
         return false;
      } else {
         val var2: Region = other as Region;
         if (this.startLine != (other as Region).startLine) {
            return false;
         } else if (this.startColumn != var2.startColumn) {
            return false;
         } else if (this.endLine != var2.endLine) {
            return false;
         } else {
            return this.endColumn == var2.endColumn;
         }
      }
   }

   @SourceDebugExtension(["SMAP\nRegion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Region.kt\ncom/feysh/corax/config/api/report/Region$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 4 Region.kt\ncom/feysh/corax/config/api/report/Region\n*L\n1#1,187:1\n1#2:188\n1#2:196\n1#2:199\n1#2:202\n1#2:205\n1#2:208\n1#2:211\n1104#3,3:189\n1104#3,3:192\n59#4:195\n57#4:197\n59#4:198\n57#4:200\n59#4:201\n57#4:203\n59#4:204\n57#4:206\n59#4:207\n57#4:209\n59#4:210\n57#4:212\n*S KotlinDebug\n*F\n+ 1 Region.kt\ncom/feysh/corax/config/api/report/Region$Companion\n*L\n157#1:196\n162#1:199\n164#1:202\n166#1:205\n168#1:208\n171#1:211\n103#1:189,3\n107#1:192,3\n157#1:195\n157#1:197\n162#1:198\n162#1:200\n164#1:201\n164#1:203\n166#1:204\n166#1:206\n168#1:207\n168#1:209\n171#1:210\n171#1:212\n*E\n"])
   public companion object {
      public final val ERROR: Region

      @JvmName(name = "invoke_Optional_Position")
      private operator fun invoke(startLine: Optional<Position>): Region? {
         if (!startLine.isPresent()) {
            return null;
         } else {
            val var10000: Region.Companion = Region.Companion;
            val var10001: Any = startLine.get();
            return var10000.invoke(var10001 as Position);
         }
      }

      private operator fun invoke(start: Optional<Position>, end: Optional<Position>): Region? {
         var var10000: Optional = if (start.isPresent()) start else null;
         val start1: Position = if (var10000 != null) var10000.get() as Position else null;
         var10000 = if (end.isPresent()) end else null;
         val end1: Position = if (var10000 != null) var10000.get() as Position else null;
         var var12: Position = start1;
         if (start1 == null) {
            var12 = end1;
            if (end1 == null) {
               return null;
            }
         }

         var var13: Position = end1;
         if (end1 == null) {
            var13 = var12;
         }

         return Region.Companion.invoke(var12, var13);
      }

      public operator fun invoke(regionNode: NodeWithRange<*>): Region? {
         val var10000: Region.Companion = Region.Companion;
         val var10001: Optional = regionNode.getBegin();
         val var10002: Optional = regionNode.getEnd();
         return var10000.invoke(var10001, var10002);
      }

      @JvmName(name = "invoke_Optional_Range")
      public operator fun invoke(regionNode: Optional<Range>): Region? {
         val var10000: Optional = if (regionNode.isPresent()) regionNode else null;
         if (var10000 != null) {
            val var6: Range = var10000.get() as Range;
            if (var6 != null) {
               return Region.Companion.invoke(var6);
            }
         }

         return null;
      }

      public operator fun invoke(regionNode: Range): Region? {
         val var10000: Region.Companion = Region.Companion;
         val var10001: Position = regionNode.begin;
         return var10000.invoke(var10001, regionNode.end);
      }

      public operator fun invoke(atLine: Int, stringToReport: String, lineText: String): Region {
         val var6: Int = StringsKt.indexOf$default(lineText, stringToReport, 0, false, 6, null);
         val it: Int = var6.intValue();
         val var10000: Int = if (it >= 0) var6 else null;
         if ((if (it >= 0) var6 else null) != null) {
            val start: Int = var10000;
            return Region.Companion.invoke(atLine, lineText, start, start + stringToReport.length());
         } else {
            return new Region(atLine, -1, atLine, -1);
         }
      }

      public operator fun invoke(atLine: Int, inputToMatch: String, start: Int, end: Int): Region {
         if (start < 0 || end < 0) {
            throw new IllegalStateException(("atLine: $atLine, inputToMatch: $inputToMatch, start: $start, end: $end").toString());
         } else {
            var var10000: java.lang.String = inputToMatch.substring(0, start);
            val startCol: java.lang.CharSequence = var10000;
            var endCol: Int = 0;

            for (int $i$f$count = 0; $i$f$count < startCol.length(); $i$f$count++) {
               if (startCol.charAt(`$i$f$count`) == '\n') {
                  endCol++;
               }
            }

            val var15: Int = start - StringsKt.lastIndexOf$default(inputToMatch, "\n", start, false, 4, null);
            var10000 = inputToMatch.substring(0, end);
            val var18: java.lang.CharSequence = var10000;
            var `count$ivx`: Int = 0;

            for (int it = 0; it < var18.length(); it++) {
               if (var18.charAt(it) == '\n') {
                  `count$ivx`++;
               }
            }

            return new Region(
               atLine + endCol, var15, atLine + `count$ivx`, end - 1 - StringsKt.lastIndexOf$default(inputToMatch, "\n", end - 1, false, 4, null) + 1
            );
         }
      }

      public operator fun invoke(atLine: Int, inputToMatch: String, matcher: Matcher): Region {
         return Region.Companion.invoke(atLine, inputToMatch, matcher.start(), matcher.end());
      }

      public operator fun invoke(inputToMatch: String, matcher: Matcher): Region {
         val matchedText: java.lang.String = matcher.group();
         val line: Int = (this.getLineAndColumn(inputToMatch, matcher.start()).component2() as java.lang.Number).intValue();
         val var10000: Region.Companion = Region.Companion;
         return var10000.invoke(line, matchedText, matcher);
      }

      public fun getLineAndColumn(text: String, index: Int): Triple<Int, Int, Int> {
         var line: Int = 1;
         var col: Int = 0;
         var start: Int = 0;

         for (int i = 0; i < index; i++) {
            if (text.charAt(i) == '\n') {
               line++;
               start = i + 1;
               col = 0;
            } else {
               col++;
            }
         }

         return new Triple(start, line, col);
      }

      public operator fun invoke(c: SootInfoCache, sootHost: Host): Region? {
         val var10000: Region;
         if (sootHost is Unit) {
            var10000 = Region.Companion.invoke(sootHost as Unit);
         } else {
            if (sootHost !is AbstractHost) {
               throw new IllegalStateException(("Host: $sootHost is not unsupported yet").toString());
            }

            var10000 = Region.Companion.invoke(c, sootHost as AbstractHost);
         }

         return var10000;
      }

      public operator fun invoke(c: SootInfoCache, sootHost: AbstractHost): Region? {
         val `this_$iv`: Region = new Region(
            c.getJavaNameSourceStartLineNumber(sootHost),
            c.getJavaNameSourceStartColumnNumber(sootHost),
            c.getJavaNameSourceEndLineNumber(sootHost),
            c.getJavaNameSourceEndColumnNumber(sootHost)
         );
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      public operator fun invoke(unit: Unit): Region? {
         val var10000: SourceLnPosTag = RegionKt.getPos(unit as Host);
         if (var10000 != null) {
            val var10: Region = Region.Companion.invoke(var10000);
            if (var10 != null) {
               return var10;
            }
         }

         val `this_$iv`: Region = new Region(unit.getJavaSourceStartLineNumber(), unit.getJavaSourceStartColumnNumber(), -1, -1);
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      public operator fun invoke(sootSourceLnPos: SourceLnPosTag): Region? {
         val `this_$iv`: Region = new Region(sootSourceLnPos.startLn(), sootSourceLnPos.startPos(), sootSourceLnPos.endLn(), sootSourceLnPos.endPos());
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      public operator fun invoke(region: de.fraunhofer.aisec.cpg.sarif.Region): Region? {
         val `this_$iv`: Region = new Region(region.startLine, region.startColumn, region.getEndLine(), region.getEndColumn());
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      private operator fun invoke(start: Position): Region? {
         val `this_$iv`: Region = new Region(start.line, start.column, -1, -1);
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      public operator fun invoke(start: Position, end: Position?): Region? {
         val `this_$iv`: Region = new Region(start.line, start.column, if (end != null) end.line else -1, if (end != null) end.column else -1);
         return if (`this_$iv`.startLine >= 0) `this_$iv` else null;
      }

      public fun getParamRegion(sm: SootMethod, index: Int): Region? {
         val var10000: SootMethodExtend = AnalysisCache.G.INSTANCE.sootHost2decl(sm as Host) as SootMethodExtend;
         if (var10000 == null) {
            return null;
         } else {
            var var8: Region;
            if (index == -1) {
               var8 = null;
            } else {
               label32:
               if (index >= 0 && index < sm.getParameterCount()) {
                  val var9: CallableDeclaration = var10000.getDecl();
                  if (var9 != null) {
                     val var10: NodeList = var9.getParameters();
                     if (var10 != null) {
                        val var11: Parameter = CollectionsKt.getOrNull(var10 as java.util.List, index) as Parameter;
                        if (var11 != null) {
                           var8 = Region.Companion.invoke(var11 as NodeWithRange<?>);
                           break label32;
                        }
                     }
                  }

                  var8 = null;
               } else {
                  var8 = null;
               }
            }

            if (var8 == null) {
               val var12: NodeWithRange = var10000.getNameDecl();
               var8 = if (var12 != null) Region.Companion.invoke(var12) else null;
            }

            return var8;
         }
      }
   }

   public data class Mutable(startLine: Int, startColumn: Int, endLine: Int, endColumn: Int) {
      public final var startLine: Int
         private set

      public final var startColumn: Int
         private set

      public final var endLine: Int
         internal set

      public final var endColumn: Int
         internal set

      public final val immutable: Region
         public final get() {
            return new Region(this.startLine, this.startColumn, this.endLine, this.endColumn);
         }


      init {
         this.startLine = startLine;
         this.startColumn = startColumn;
         this.endLine = endLine;
         this.endColumn = endColumn;
      }

      public override fun toString(): String {
         return "${this.startLine}:${this.startColumn}-${this.endLine}:${this.endColumn}";
      }

      public operator fun component1(): Int {
         return this.startLine;
      }

      public operator fun component2(): Int {
         return this.startColumn;
      }

      public operator fun component3(): Int {
         return this.endLine;
      }

      public operator fun component4(): Int {
         return this.endColumn;
      }

      public fun copy(startLine: Int = this.startLine, startColumn: Int = this.startColumn, endLine: Int = this.endLine, endColumn: Int = this.endColumn): com.feysh.corax.config.api.report.Region.Mutable {
         return new Region.Mutable(startLine, startColumn, endLine, endColumn);
      }

      public override fun hashCode(): Int {
         return ((Integer.hashCode(this.startLine) * 31 + Integer.hashCode(this.startColumn)) * 31 + Integer.hashCode(this.endLine)) * 31
            + Integer.hashCode(this.endColumn);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Region.Mutable) {
            return false;
         } else {
            val var2: Region.Mutable = other as Region.Mutable;
            if (this.startLine != (other as Region.Mutable).startLine) {
               return false;
            } else if (this.startColumn != var2.startColumn) {
               return false;
            } else if (this.endLine != var2.endLine) {
               return false;
            } else {
               return this.endColumn == var2.endColumn;
            }
         }
      }
   }
}
