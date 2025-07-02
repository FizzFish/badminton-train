package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassMatch
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.utils.KFunctionType
import com.feysh.corax.config.api.utils.UtilsKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import soot.ArrayType
import soot.Scene
import soot.SootClass
import soot.SootMethod
import soot.Type

@Serializable
@SerialName("RawSignatureMatch")
@SourceDebugExtension(["SMAP\nMatchers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Matchers.kt\ncom/feysh/corax/config/api/baseimpl/RawSignatureMatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1#2:244\n1734#3,3:245\n1863#3,2:248\n1863#3,2:250\n774#3:252\n865#3,2:253\n1368#3:255\n1454#3,2:256\n774#3:258\n865#3,2:259\n1456#3,3:261\n1872#3,3:264\n1863#3,2:267\n1863#3,2:269\n*S KotlinDebug\n*F\n+ 1 Matchers.kt\ncom/feysh/corax/config/api/baseimpl/RawSignatureMatch\n*L\n227#1:245,3\n228#1:248,2\n233#1:250,2\n161#1:252\n161#1:253,2\n162#1:255\n162#1:256,2\n162#1:258\n162#1:259,2\n162#1:261,3\n195#1:264,3\n228#1:267,2\n233#1:269,2\n*E\n"])
public data class RawSignatureMatch(clazz: IClassMatch, name: List<String>, returnType: String?, parameters: List<String?>?, actualType: KFunctionType? = null) :
   IMethodMatch {
   public final val clazz: IClassMatch
   public final val name: List<String>
   public final val returnType: String?
   public final val parameters: List<String?>?
   public open val actualType: KFunctionType?

   @Transient
   public open val exception: Exception

   public open val argumentCnt: Int?
      public open get() {
         return if (this.parameters != null && this.parameters.contains("**")) null else (if (this.parameters != null) this.parameters.size() else null);
      }


   public final val signature: String
      public final get() {
         return this.signature$delegate.getValue() as java.lang.String;
      }


   init {
      this.clazz = clazz;
      this.name = name;
      this.returnType = returnType;
      this.parameters = parameters;
      this.actualType = actualType;
      this.exception = new Exception();
      this.signature$delegate = LazyKt.lazy(RawSignatureMatch::signature_delegate$lambda$4);
      if (this.clazz.getSimpleName().isEmpty()) {
         throw new IllegalStateException(("error syntax: $this").toString());
      } else if (this.name.isEmpty()) {
         throw new IllegalStateException(("error syntax: $this").toString());
      } else {
         val `$this$forEach$iv`: java.lang.Iterable = this.name;
         var var10000: Boolean;
         if (this.name is java.util.Collection && this.name.isEmpty()) {
            var10000 = true;
         } else {
            label111: {
               for (Object element$iv : $this$all$iv) {
                  if ((`element$iv` as java.lang.String).length() <= 0) {
                     var10000 = false;
                     break label111;
                  }
               }

               var10000 = true;
            }
         }

         if (!var10000) {
            throw new IllegalStateException(("error syntax: $this").toString());
         } else {
            for (Object element$ivx : $this$all$iv) {
               if (this.name.contains(`element$ivx` as java.lang.String)) {
                  throw new IllegalStateException(("This is a constructor: $this").toString());
               }
            }

            if (this.parameters != null) {
               val var16: java.lang.Iterable;
               for (Object element$ivxx : var16) {
                  if (`element$ivxx` as java.lang.String != null && (`element$ivxx` as java.lang.String).length() == 0) {
                     throw new IllegalStateException(("invalid type: $this").toString());
                  }
               }
            }
         }
      }
   }

   public override fun matched(scene: Scene): List<SootMethod> {
      var `$this$flatMap$iv`: java.lang.Iterable = this.clazz.matched(scene);
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if ((`element$iv$iv` as SootClass).resolvingLevel() >= 2) {
            `destination$iv$iv`.add(`element$iv$iv`);
         }
      }

      `$this$flatMap$iv` = `destination$iv$iv` as java.util.List;
      `destination$iv$iv` = new ArrayList();

      for (Object element$iv$ivx : $this$filter$iv) {
         val var10000: java.util.List = (`element$iv$ivx` as SootClass).getMethods();
         val `$this$filter$ivx`: java.lang.Iterable = var10000;
         val `destination$iv$ivx`: java.util.Collection = new ArrayList();

         for (Object element$iv$ivxx : $this$filter$ivx) {
            val it: SootMethod = `element$iv$ivxx` as SootMethod;
            if (this.match(it)) {
               `destination$iv$ivx`.add(`element$iv$ivxx`);
            }
         }

         CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
      }

      return `destination$iv$iv` as MutableList<SootMethod>;
   }

   private fun isMatches(type: Type, t: String?): Boolean {
      val var10000: Boolean;
      if (t != null && !(t == "*")) {
         if (type.toString() == t) {
            var10000 = true;
         } else if (type is ArrayType && StringsKt.endsWith$default(t, "[]", false, 2, null)) {
            val var10001: Type = (type as ArrayType).getElementType();
            var10000 = this.isMatches(var10001, StringsKt.substringBefore$default(t, "[", null, 2, null));
         } else {
            if (t.length() == 0) {
               throw new IllegalStateException(("invalid type: $this").toString());
            }

            val var3: java.lang.String = UtilsKt.getTypename(type);
            if ((if (var3 != null) StringsKt.substringAfterLast$default(var3, ".", null, 2, null) else null) == t) {
               var10000 = true;
            } else {
               val var4: java.lang.String = UtilsKt.getTypename(type);
               var10000 = (if (var4 != null) StringsKt.substringAfterLast$default(var4, "$", null, 2, null) else null) == t;
            }
         }
      } else {
         var10000 = true;
      }

      return var10000;
   }

   private fun match(sm: SootMethod): Boolean {
      if (!this.name.contains(sm.getName())) {
         return false;
      } else {
         val var10001: Type = sm.getReturnType();
         if (!this.isMatches(var10001, this.returnType)) {
            return false;
         } else if (this.parameters == null) {
            return true;
         } else {
            var count: Int = 0;
            val `$this$forEachIndexed$iv`: java.lang.Iterable = this.parameters;
            var `index$iv`: Int = 0;

            for (Object item$iv : $this$forEachIndexed$iv) {
               val var8: Int = `index$iv`++;
               if (var8 < 0) {
                  CollectionsKt.throwIndexOverflow();
               }

               val match: java.lang.String = `item$iv` as java.lang.String;
               if (`item$iv` as java.lang.String == "**") {
                  return true;
               }

               if (var8 >= sm.getParameterCount()) {
                  return false;
               }

               val type: Type = sm.getParameterType(var8);
               if (type == null || !this.isMatches(type, match)) {
                  return false;
               }

               count++;
            }

            return count == sm.getParameterCount();
         }
      }
   }

   public override fun toString(): String {
      return "method match: ${this.getSignature()}";
   }

   public operator fun component1(): IClassMatch {
      return this.clazz;
   }

   public operator fun component2(): List<String> {
      return this.name;
   }

   public operator fun component3(): String? {
      return this.returnType;
   }

   public operator fun component4(): List<String?>? {
      return this.parameters;
   }

   public operator fun component5(): KFunctionType? {
      return this.actualType;
   }

   public fun copy(
      clazz: IClassMatch = this.clazz,
      name: List<String> = this.name,
      returnType: String? = this.returnType,
      parameters: List<String?>? = this.parameters,
      actualType: KFunctionType? = this.actualType
   ): RawSignatureMatch {
      return new RawSignatureMatch(clazz, name, returnType, parameters, actualType);
   }

   public override fun hashCode(): Int {
      return (
               ((this.clazz.hashCode() * 31 + this.name.hashCode()) * 31 + (if (this.returnType == null) 0 else this.returnType.hashCode())) * 31
                  + (if (this.parameters == null) 0 else this.parameters.hashCode())
            )
            * 31
         + (if (this.actualType == null) 0 else this.actualType.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is RawSignatureMatch) {
         return false;
      } else {
         val var2: RawSignatureMatch = other as RawSignatureMatch;
         if (!(this.clazz == (other as RawSignatureMatch).clazz)) {
            return false;
         } else if (!(this.name == var2.name)) {
            return false;
         } else if (!(this.returnType == var2.returnType)) {
            return false;
         } else if (!(this.parameters == var2.parameters)) {
            return false;
         } else {
            return this.actualType === var2.actualType;
         }
      }
   }

   @JvmStatic
   fun `signature_delegate$lambda$4`(`this$0`: RawSignatureMatch): java.lang.String {
      val nameString: java.lang.String = if (`this$0`.name.size() == 1)
         CollectionsKt.first(`this$0`.name) as java.lang.String
         else
         CollectionsKt.joinToString$default(`this$0`.name, ", ", "[", "]", 0, null, null, 56, null);
      var var10001: java.lang.String = `this$0`.returnType;
      if (`this$0`.returnType == null) {
         var10001 = "*";
      }

      if (`this$0`.parameters != null) {
         val var10003: java.lang.String = CollectionsKt.joinToString$default(`this$0`.parameters, ",", null, null, 0, null, null, 62, null);
         if (var10003 != null) {
            return "^${`this$0`.clazz}: $var10001 $nameString($var10003)";
         }
      }

      return "^${`this$0`.clazz}: $var10001 $nameString(**)";
   }

   @JvmStatic
   fun `_init_$lambda$11`(`this$0`: RawSignatureMatch): java.lang.String {
      val nameString: java.lang.String = if (`this$0`.name.size() == 1)
         CollectionsKt.first(`this$0`.name) as java.lang.String
         else
         CollectionsKt.joinToString$default(`this$0`.name, ", ", "[", "]", 0, null, null, 56, null);
      var var10001: java.lang.String = `this$0`.returnType;
      if (`this$0`.returnType == null) {
         var10001 = "*";
      }

      if (`this$0`.parameters != null) {
         val var10003: java.lang.String = CollectionsKt.joinToString$default(`this$0`.parameters, ",", null, null, 0, null, null, 62, null);
         if (var10003 != null) {
            return "^${`this$0`.clazz}: $var10001 $nameString($var10003)";
         }
      }

      return "^${`this$0`.clazz}: $var10001 $nameString(**)";
   }

   public companion object {
      public operator fun invoke(clazz: String, name: String, returnType: String?, parameters: List<String?>?, actualType: KFunctionType? = null): RawSignatureMatch {
         return new RawSignatureMatch(new QualifiedRefType(clazz), CollectionsKt.listOf(name), returnType, parameters, actualType);
      }

      public fun serializer(): KSerializer<RawSignatureMatch> {
         return RawSignatureMatch.$serializer.INSTANCE as KSerializer<RawSignatureMatch>;
      }
   }
}
