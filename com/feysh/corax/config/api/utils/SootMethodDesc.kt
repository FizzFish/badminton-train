package com.feysh.corax.config.api.utils

import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import soot.SootMethod
import soot.Type

@Serializable
@SourceDebugExtension(["SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/feysh/corax/config/api/utils/SootMethodDesc\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,484:1\n1#2:485\n*E\n"])
public data class SootMethodDesc(clazz: String, name: String, returnType: String, parameters: List<String>) : IMethodDescriptor {
   public open val clazz: String
   public open val name: String
   public open val returnType: String
   public open val parameters: List<String>

   public final val signature: String
      public final get() {
         return this.signature$delegate.getValue() as java.lang.String;
      }


   init {
      this.clazz = clazz;
      this.name = name;
      this.returnType = returnType;
      this.parameters = parameters;
      if (StringsKt.indexOf$default(this.getClazz(), '/', 0, false, 6, null) != -1) {
         throw new IllegalArgumentException(("invalid format clazz: ${this.getClazz()}").toString());
      } else {
         this.signature$delegate = LazyKt.lazy(SootMethodDesc::signature_delegate$lambda$1);
      }
   }

   public override fun toString(): String {
      return this.getSignature();
   }

   public operator fun component1(): String {
      return this.clazz;
   }

   public operator fun component2(): String {
      return this.name;
   }

   public operator fun component3(): String {
      return this.returnType;
   }

   public operator fun component4(): List<String> {
      return this.parameters;
   }

   public fun copy(clazz: String = this.clazz, name: String = this.name, returnType: String = this.returnType, parameters: List<String> = this.parameters): SootMethodDesc {
      return new SootMethodDesc(clazz, name, returnType, parameters);
   }

   public override fun hashCode(): Int {
      return ((this.clazz.hashCode() * 31 + this.name.hashCode()) * 31 + this.returnType.hashCode()) * 31 + this.parameters.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootMethodDesc) {
         return false;
      } else {
         val var2: SootMethodDesc = other as SootMethodDesc;
         if (!(this.clazz == (other as SootMethodDesc).clazz)) {
            return false;
         } else if (!(this.name == var2.name)) {
            return false;
         } else if (!(this.returnType == var2.returnType)) {
            return false;
         } else {
            return this.parameters == var2.parameters;
         }
      }
   }

   @JvmStatic
   fun `signature_delegate$lambda$1`(`this$0`: SootMethodDesc): java.lang.String {
      return "<${`this$0`.getClazz()}: ${`this$0`.getReturnType()} ${`this$0`.getName()}(${CollectionsKt.joinToString$default(
         `this$0`.getParameters(), ",", null, null, 0, null, null, 62, null
      )})>";
   }

   @JvmStatic
   fun `_init_$lambda$3`(`this$0`: SootMethodDesc): java.lang.String {
      return "<${`this$0`.getClazz()}: ${`this$0`.getReturnType()} ${`this$0`.getName()}(${CollectionsKt.joinToString$default(
         `this$0`.getParameters(), ",", null, null, 0, null, null, 62, null
      )})>";
   }

   @JvmStatic
   fun {
      val var10000: Pattern = Pattern.compile("^<(?<className>.*?): (?<returnType>.*?) (?<methodName>.*?)\\((?<parameters>.*?)\\)>$");
      patternMethodSig = var10000;
   }

   @SourceDebugExtension(["SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/feysh/corax/config/api/utils/SootMethodDesc$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,484:1\n1557#2:485\n1628#2,3:486\n*S KotlinDebug\n*F\n+ 1 Utils.kt\ncom/feysh/corax/config/api/utils/SootMethodDesc$Companion\n*L\n87#1:485\n87#1:486,3\n*E\n"])
   public companion object {
      private final val patternMethodSig: Pattern

      public operator fun invoke(parseString: String): SootMethodDesc? {
         if (parseString.length() == 0) {
            return null;
         } else {
            val var10000: Matcher = SootMethodDesc.access$getPatternMethodSig$cp().matcher(parseString);
            if (var10000.matches()) {
               val className: java.lang.String = var10000.group("className");
               val returnType: java.lang.String = var10000.group("returnType");
               val methodName: java.lang.String = var10000.group("methodName");
               val paramList: java.lang.String = var10000.group("parameters");
               return new SootMethodDesc(className, methodName, returnType, StringsKt.split$default(paramList, new java.lang.String[]{","}, false, 0, 6, null));
            } else {
               return null;
            }
         }
      }

      public operator fun invoke(sm: SootMethod): SootMethodDesc {
         var var10000: java.lang.String = sm.getDeclaringClass().getName();
         val var10001: java.lang.String = sm.getName();
         val var10002: Type = sm.getReturnType();
         var var20: java.lang.String = UtilsKt.getTypename(var10002);
         if (var20 == null) {
            var20 = sm.getReturnType().toString();
         }

         val var10003: java.util.List = sm.getParameterTypes();
         val `$this$map$iv`: java.lang.Iterable = var10003;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10003, 10));

         for (Object item$iv$iv : $this$map$iv) {
            val it: Type = `item$iv$iv` as Type;
            var10000 = UtilsKt.getTypename(it);
            if (var10000 == null) {
               var10000 = it.toString();
            }

            `destination$iv$iv`.add(var10000);
         }

         return new SootMethodDesc(var10000, var10001, var20, `destination$iv$iv` as MutableList<java.lang.String>);
      }

      public fun serializer(): KSerializer<SootMethodDesc> {
         return SootMethodDesc.$serializer.INSTANCE as KSerializer<SootMethodDesc>;
      }
   }
}
