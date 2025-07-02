@file:SourceDebugExtension(["SMAP\nMatchUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MatchUtils.kt\ncom/feysh/corax/config/api/baseimpl/MatchUtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,220:1\n1557#2:221\n1628#2,3:222\n1#3:225\n*S KotlinDebug\n*F\n+ 1 MatchUtils.kt\ncom/feysh/corax/config/api/baseimpl/MatchUtilsKt\n*L\n43#1:221\n43#1:222,3\n*E\n"])

package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.AIAnalysisApi
import com.feysh.corax.config.api.utils.KFunctionType
import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.jvm.internal.SourceDebugExtension

public final val patternSimpleSig: Pattern

public fun matchSoot(sootSignature: String): SootSignatureMatch {
   return SootSignatureMatch.Companion.invoke(sootSignature);
}

public fun match(function: () -> *): SootSignatureMatch {
   return SootSignatureMatch.Companion.invoke(function);
}

public fun matchMethodName(`class`: String, methodName: String): RawSignatureMatch {
   return RawSignatureMatch.Companion.invoke$default(RawSignatureMatch.Companion, var0, methodName, null, null, null, 16, null);
}

public fun matchSimpleSig(sig: String): RawSignatureMatch {
   val var10000: Matcher = patternSimpleSig.matcher(sig);
   if (!var10000.matches() || StringsKt.startsWith$default(sig, "<", false, 2, null) || StringsKt.endsWith$default(sig, ">", false, 2, null)) {
      throw new IllegalArgumentException(
         ("invalid java style signature syntax: $sig. eg: \"java.util.Map: Object getOrDefault(Object key, Object defaultValue, *)\"").toString()
      );
   } else {
      val clazz: java.lang.String = var10000.group("class");
      val parameters: java.lang.String = var10000.group("returnType");
      val returnType: java.lang.String = if (parameters == "*") null else parameters;
      val methodName: java.lang.String = var10000.group("methodName");
      val s: java.lang.String = var10000.group("parameters");
      val var24: java.util.List;
      if (s == "**") {
         var24 = null;
      } else if (s.length() == 0) {
         var24 = CollectionsKt.emptyList();
      } else {
         val var23: java.lang.Iterable = StringsKt.split$default(s, new java.lang.String[]{","}, false, 0, 6, null);
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var23, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(matchSimpleSig$extractParameter(sig, `item$iv$iv` as java.lang.String));
         }

         var24 = `destination$iv$iv` as java.util.List;
      }

      if (CollectionsKt.last(StringsKt.split$default(clazz, new java.lang.String[]{"."}, false, 0, 6, null)) == methodName) {
         throw new IllegalStateException(("This is a constructor: $sig").toString());
      } else {
         val var27: RawSignatureMatch.Companion = RawSignatureMatch.Companion;
         return RawSignatureMatch.Companion.invoke$default(var27, clazz, methodName, returnType, var24, null, 16, null);
      }
   }
}

public fun RawSignatureMatch.matchParameters(vararg parameters: String?): RawSignatureMatch {
   return new RawSignatureMatch(
      `$this$matchParameters`.getClazz(),
      `$this$matchParameters`.getName(),
      `$this$matchParameters`.getReturnType(),
      ArraysKt.toList(parameters),
      `$this$matchParameters`.getActualType()
   );
}

public fun RawSignatureMatch.matchFunctionType(actualType: KFunctionType?): RawSignatureMatch {
   return new RawSignatureMatch(
      `$this$matchFunctionType`.getClazz(),
      `$this$matchFunctionType`.getName(),
      `$this$matchFunctionType`.getReturnType(),
      `$this$matchFunctionType`.getParameters(),
      actualType
   );
}

public fun RawSignatureMatch.matchReturnType(returnType: String?): RawSignatureMatch {
   return new RawSignatureMatch(
      `$this$matchReturnType`.getClazz(),
      `$this$matchReturnType`.getName(),
      returnType,
      `$this$matchReturnType`.getParameters(),
      `$this$matchReturnType`.getActualType()
   );
}

public fun AIAnalysisApi.`package`(`package`: String, block: (PackageDeclare) -> Unit) {
   block.invoke(new PackageDeclare(var1));
}

public fun <This> matchConstructor(function: () -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS> matchMethod(function: (THIS) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R> matchStaticMethod(function: () -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0> matchConstructor(function: (P0) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0> matchMethod(function: (THIS, P0) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0> matchStaticMethod(function: (P0) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1> matchConstructor(function: (P0, P1) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1> matchMethod(function: (THIS, P0, P1) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1> matchStaticMethod(function: (P0, P1) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2> matchConstructor(function: (P0, P1, P2) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2> matchMethod(function: (THIS, P0, P1, P2) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2> matchStaticMethod(function: (P0, P1, P2) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3> matchConstructor(function: (P0, P1, P2, P3) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3> matchMethod(function: (THIS, P0, P1, P2, P3) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3> matchStaticMethod(function: (P0, P1, P2, P3) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4> matchConstructor(function: (P0, P1, P2, P3, P4) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4> matchMethod(function: (THIS, P0, P1, P2, P3, P4) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4> matchStaticMethod(function: (P0, P1, P2, P3, P4) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5> matchConstructor(function: (P0, P1, P2, P3, P4, P5) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6, P7) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> matchConstructor(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> This): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> matchMethod(function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> matchConstructor(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> This
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> matchMethod(
   function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> matchStaticMethod(function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> matchConstructor(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> This
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> matchMethod(
   function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> matchStaticMethod(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> matchConstructor(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> This
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> matchMethod(
   function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> matchStaticMethod(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> matchConstructor(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> This
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> matchMethod(
   function: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

public fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> matchStaticMethod(
   function: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
): SootSignatureMatch {
   return match(function as Function<?>);
}

fun `matchSimpleSig$extractParameter`(`$sig`: java.lang.String, str: java.lang.String): java.lang.String {
   val sp: java.util.List = StringsKt.split$default(
      StringsKt.substringBefore$default(StringsKt.trim(str).toString(), "<", null, 2, null), new java.lang.String[]{" "}, false, 0, 6, null
   );
   if (sp.size() > 2) {
      throw new IllegalArgumentException(("error syntax signature: $`$sig`").toString());
   } else {
      val var6: java.lang.String = CollectionsKt.first(sp) as java.lang.String;
      return if (var6 == "*") null else var6;
   }
}
