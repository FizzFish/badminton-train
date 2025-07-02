package com.feysh.corax.config.api.rules

import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.text.jdk8.RegexExtensionsJDK8Kt
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.EncodeDefault.Mode
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.LinkedHashMapSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt

@SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,373:1\n1557#2:374\n1628#2,3:375\n543#2,6:382\n11165#3:378\n11500#3,3:379\n*S KotlinDebug\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule\n*L\n24#1:374\n24#1:375,3\n314#1:382,6\n27#1:378\n27#1:379,3\n*E\n"])
public object ProcessRule {
   public fun com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase.isValidOf(type: com.feysh.corax.config.api.rules.ProcessRule.Type): String? {
      if (`$this$isValidOf` !is ProcessRule.ProcRegex) {
         return null;
      } else {
         val regex: java.lang.String = (`$this$isValidOf` as ProcessRule.ProcRegex).getRegexExpr();
         val var10000: java.lang.String;
         if (type.isFile() && StringsKt.contains$default(regex, "\\\\", false, 2, null)) {
            var10000 = "Matching regex for file cannot contains \\ char";
         } else {
            if (type.isClassMember() && new Regex("\\\\\\\\|/").containsMatchIn(regex)) {
               return "Matching regex for class cannot contains \\ char";
            }

            var10000 = null;
         }

         return var10000;
      }
   }

   private fun com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase.matcher(str: String?): Boolean? {
      return if (str as java.lang.CharSequence == null || str.length() == 0) null else `$this$matcher`.matches(str);
   }

   public fun <T : com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget> List<com.feysh.corax.config.api.rules.ProcessRule.IMatchItem>.matches(content: T): Pair<
         com.feysh.corax.config.api.rules.ProcessRule.IMatchItem?,
         com.feysh.corax.config.api.rules.ProcessRule.ScanAction
      > {
      if (`$this$matches`.isEmpty()) {
         return TuplesKt.to(null, ProcessRule.ScanAction.Keep);
      } else {
         val `iterator$iv`: java.util.ListIterator = `$this$matches`.listIterator(`$this$matches`.size());

         var var10000: Any;
         while (true) {
            if (`iterator$iv`.hasPrevious()) {
               val `element$iv`: Any = `iterator$iv`.previous();
               if (!(`element$iv` as ProcessRule.IMatchItem).matches(content)) {
                  continue;
               }

               var10000 = `element$iv`;
               break;
            }

            var10000 = null;
            break;
         }

         val ruleItem: ProcessRule.IMatchItem = var10000 as ProcessRule.IMatchItem;
         val var10001: ProcessRule.OP = if (var10000 as ProcessRule.IMatchItem != null) (var10000 as ProcessRule.IMatchItem).getOp() else null;
         var var10: ProcessRule.ScanAction;
         switch (var10001 == null ? -1 : ProcessRule.WhenMappings.$EnumSwitchMapping$0[var10001.ordinal()]) {
            case -1:
               var10 = ProcessRule.ScanAction.Keep;
               break;
            case 0:
            default:
               throw new NoWhenBranchMatchedException();
            case 1:
               var10 = ProcessRule.ScanAction.Process;
               break;
            case 2:
               var10 = ProcessRule.ScanAction.Skip;
         }

         return TuplesKt.to(ruleItem, var10);
      }
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,373:1\n1#2:374\n1863#3,2:375\n*S KotlinDebug\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch\n*L\n205#1:375,2\n*E\n"])
   public data class ClassMemberMatch(op: com.feysh.corax.config.api.rules.ProcessRule.OP,
         classNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null,
         classSrcMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null,
         methodSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null,
         methodNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null,
         fieldSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null,
         fieldNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = null
      ) :
      ProcessRule.IMatchItem,
      ProcessRule.ErrorCommit {
      public open val op: com.feysh.corax.config.api.rules.ProcessRule.OP

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("class:name")
      public final val classNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("class:src")
      public final val classSrcMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("method:signature")
      public final val methodSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("method:name")
      public final val methodNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("field:signature")
      public final val fieldSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      @EncodeDefault(
         mode = Mode.NEVER
      )
      @SerialName("field:name")
      public final val fieldNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase?

      public final val isMatchField: Boolean
         public final get() {
            return this.fieldSignatureMatch != null || this.fieldNameMatch != null;
         }


      public final val isMatchMethod: Boolean
         public final get() {
            return this.methodSignatureMatch != null || this.methodNameMatch != null;
         }


      public final val isMatchClass: Boolean
         public final get() {
            return this.classSrcMatch != null || this.classNameMatch != null;
         }


      public open val error: String?
         public open get() {
            val `$i$f$forEach`: Unit = Unit.INSTANCE;
            if ((if (!this.isMatchClass() && !this.isMatchMethod() && !this.isMatchField()) `$i$f$forEach` else null) != null) {
               return "empty ClassMemberMatch";
            } else {
               val var12: java.lang.Iterable;
               for (Object element$iv : var12) {
                  val regex: ProcessRule.ProcRegexBase = (var14 as Pair).component1() as ProcessRule.ProcRegexBase;
                  val type: ProcessRule.Type = (var14 as Pair).component2() as ProcessRule.Type;
                  if (regex != null) {
                     val var10000: java.lang.String = ProcessRule.INSTANCE.isValidOf(regex, type);
                     if (var10000 != null) {
                        return var10000;
                     }
                  }
               }

               return if (this.isMatchField() && this.isMatchMethod())
                  "The use of both class methods and class fields in the matching is not permitted."
                  else
                  null;
            }
         }


      init {
         this.op = op;
         this.classNameMatch = classNameMatch;
         this.classSrcMatch = classSrcMatch;
         this.methodSignatureMatch = methodSignatureMatch;
         this.methodNameMatch = methodNameMatch;
         this.fieldSignatureMatch = fieldSignatureMatch;
         this.fieldNameMatch = fieldNameMatch;
      }

      public override fun matches(matchContent: com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget): Boolean {
         if (matchContent !is ProcessRule.ClassMemberMatch.MatchTarget) {
            return false;
         } else {
            var anyMatch: Boolean = false;
            if (this.classSrcMatch != null) {
               val var10000: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.classSrcMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getClassSrc()
               );
               if (var10000 != null) {
                  if (!var10000) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            if (this.classNameMatch != null) {
               val var15: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.classNameMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getClassName()
               );
               if (var15 != null) {
                  if (!var15) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            if (this.methodSignatureMatch != null) {
               val var16: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.methodSignatureMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getMethodSignature()
               );
               if (var16 != null) {
                  if (!var16) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            if (this.methodNameMatch != null) {
               val var17: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.methodNameMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getMethodName()
               );
               if (var17 != null) {
                  if (!var17) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            if (this.fieldSignatureMatch != null) {
               val var18: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.fieldSignatureMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getFieldSignature()
               );
               if (var18 != null) {
                  if (!var18) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            if (this.fieldNameMatch != null) {
               val var19: java.lang.Boolean = ProcessRule.access$matcher(
                  ProcessRule.INSTANCE, this.fieldNameMatch, (matchContent as ProcessRule.ClassMemberMatch.MatchTarget).getFieldName()
               );
               if (var19 != null) {
                  if (!var19) {
                     return false;
                  }

                  anyMatch = true;
               }
            }

            return anyMatch;
         }
      }

      public override fun toString(): String {
         val var2: java.util.List = new ArrayList();
         if (this.isMatchClass()) {
            var2.add("class:name=${this.classNameMatch}");
            var2.add("class:src=${this.classSrcMatch}");
         }

         if (this.isMatchMethod()) {
            var2.add("method:name=${this.methodNameMatch}");
            var2.add("method:signature=${this.methodSignatureMatch}");
         }

         if (this.isMatchField()) {
            var2.add("field:name=${this.fieldNameMatch}");
            var2.add("field:signature=${this.fieldSignatureMatch}");
         }

         return CollectionsKt.joinToString$default(var2, ", ", "(${this.getOp().getPretty()})", "", 0, null, null, 56, null);
      }

      public operator fun component1(): com.feysh.corax.config.api.rules.ProcessRule.OP {
         return this.op;
      }

      public operator fun component2(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.classNameMatch;
      }

      public operator fun component3(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.classSrcMatch;
      }

      public operator fun component4(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.methodSignatureMatch;
      }

      public operator fun component5(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.methodNameMatch;
      }

      public operator fun component6(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.fieldSignatureMatch;
      }

      public operator fun component7(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? {
         return this.fieldNameMatch;
      }

      public fun copy(
         op: com.feysh.corax.config.api.rules.ProcessRule.OP = this.op,
         classNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.classNameMatch,
         classSrcMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.classSrcMatch,
         methodSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.methodSignatureMatch,
         methodNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.methodNameMatch,
         fieldSignatureMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.fieldSignatureMatch,
         fieldNameMatch: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase? = this.fieldNameMatch
      ): com.feysh.corax.config.api.rules.ProcessRule.ClassMemberMatch {
         return new ProcessRule.ClassMemberMatch(op, classNameMatch, classSrcMatch, methodSignatureMatch, methodNameMatch, fieldSignatureMatch, fieldNameMatch);
      }

      public override fun hashCode(): Int {
         return (
                  (
                           (
                                    (
                                             (this.op.hashCode() * 31 + (if (this.classNameMatch == null) 0 else this.classNameMatch.hashCode())) * 31
                                                + (if (this.classSrcMatch == null) 0 else this.classSrcMatch.hashCode())
                                          )
                                          * 31
                                       + (if (this.methodSignatureMatch == null) 0 else this.methodSignatureMatch.hashCode())
                                 )
                                 * 31
                              + (if (this.methodNameMatch == null) 0 else this.methodNameMatch.hashCode())
                        )
                        * 31
                     + (if (this.fieldSignatureMatch == null) 0 else this.fieldSignatureMatch.hashCode())
               )
               * 31
            + (if (this.fieldNameMatch == null) 0 else this.fieldNameMatch.hashCode());
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ProcessRule.ClassMemberMatch) {
            return false;
         } else {
            val var2: ProcessRule.ClassMemberMatch = other as ProcessRule.ClassMemberMatch;
            if (this.op != (other as ProcessRule.ClassMemberMatch).op) {
               return false;
            } else if (!(this.classNameMatch == var2.classNameMatch)) {
               return false;
            } else if (!(this.classSrcMatch == var2.classSrcMatch)) {
               return false;
            } else if (!(this.methodSignatureMatch == var2.methodSignatureMatch)) {
               return false;
            } else if (!(this.methodNameMatch == var2.methodNameMatch)) {
               return false;
            } else if (!(this.fieldSignatureMatch == var2.fieldSignatureMatch)) {
               return false;
            } else {
               return this.fieldNameMatch == var2.fieldNameMatch;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<com.feysh.corax.config.api.rules.ProcessRule.ClassMemberMatch> {
            return ProcessRule.ClassMemberMatch.$serializer.INSTANCE as KSerializer<ProcessRule.ClassMemberMatch>;
         }
      }

      public data class MatchTarget(className: String?,
            classSrc: String?,
            methodSignature: String?,
            methodName: String?,
            fieldSignature: String?,
            fieldName: String?
         ) :
         ProcessRule.IMatchTarget {
         public final val className: String?
         public final val classSrc: String?
         public final val methodSignature: String?
         public final val methodName: String?
         public final val fieldSignature: String?
         public final val fieldName: String?

         public open val type: String
            public open get() {
               val var1: java.util.List = new ArrayList();
               if (this.isMatchClass()) {
                  var1.add("class");
               }

               if (this.isMatchMethod()) {
                  var1.add("method");
               }

               if (this.isMatchField()) {
                  var1.add("field");
               }

               return CollectionsKt.joinToString$default(var1, "_", "", "", 0, null, null, 56, null);
            }


         public final val isMatchField: Boolean
            public final get() {
               return this.fieldSignature != null || this.fieldName != null;
            }


         public final val isMatchMethod: Boolean
            public final get() {
               return this.methodSignature != null || this.methodName != null;
            }


         public final val isMatchClass: Boolean
            public final get() {
               return this.className != null || this.classSrc != null;
            }


         init {
            this.className = className;
            this.classSrc = classSrc;
            this.methodSignature = methodSignature;
            this.methodName = methodName;
            this.fieldSignature = fieldSignature;
            this.fieldName = fieldName;
         }

         public override fun toString(): String {
            val var2: java.util.List = new ArrayList();
            if (this.isMatchClass()) {
               var2.add("class:name=${this.className}");
               var2.add("class:src=${this.classSrc}");
            }

            if (this.isMatchMethod()) {
               var2.add("method:name=${this.methodName}");
               var2.add("method:signature=${this.methodSignature}");
            }

            if (this.isMatchField()) {
               var2.add("field:name=${this.fieldName}");
               var2.add("field:signature=${this.fieldSignature}");
            }

            return CollectionsKt.joinToString$default(var2, ", ", "${this.getType()}: { ", " }", 0, null, null, 56, null);
         }

         public operator fun component1(): String? {
            return this.className;
         }

         public operator fun component2(): String? {
            return this.classSrc;
         }

         public operator fun component3(): String? {
            return this.methodSignature;
         }

         public operator fun component4(): String? {
            return this.methodName;
         }

         public operator fun component5(): String? {
            return this.fieldSignature;
         }

         public operator fun component6(): String? {
            return this.fieldName;
         }

         public fun copy(
            className: String? = this.className,
            classSrc: String? = this.classSrc,
            methodSignature: String? = this.methodSignature,
            methodName: String? = this.methodName,
            fieldSignature: String? = this.fieldSignature,
            fieldName: String? = this.fieldName
         ): com.feysh.corax.config.api.rules.ProcessRule.ClassMemberMatch.MatchTarget {
            return new ProcessRule.ClassMemberMatch.MatchTarget(className, classSrc, methodSignature, methodName, fieldSignature, fieldName);
         }

         public override fun hashCode(): Int {
            return (
                     (
                              (
                                       (
                                                (if (this.className == null) 0 else this.className.hashCode()) * 31
                                                   + (if (this.classSrc == null) 0 else this.classSrc.hashCode())
                                             )
                                             * 31
                                          + (if (this.methodSignature == null) 0 else this.methodSignature.hashCode())
                                    )
                                    * 31
                                 + (if (this.methodName == null) 0 else this.methodName.hashCode())
                           )
                           * 31
                        + (if (this.fieldSignature == null) 0 else this.fieldSignature.hashCode())
                  )
                  * 31
               + (if (this.fieldName == null) 0 else this.fieldName.hashCode());
         }

         public override operator fun equals(other: Any?): Boolean {
            if (this === other) {
               return true;
            } else if (other !is ProcessRule.ClassMemberMatch.MatchTarget) {
               return false;
            } else {
               val var2: ProcessRule.ClassMemberMatch.MatchTarget = other as ProcessRule.ClassMemberMatch.MatchTarget;
               if (!(this.className == (other as ProcessRule.ClassMemberMatch.MatchTarget).className)) {
                  return false;
               } else if (!(this.classSrc == var2.classSrc)) {
                  return false;
               } else if (!(this.methodSignature == var2.methodSignature)) {
                  return false;
               } else if (!(this.methodName == var2.methodName)) {
                  return false;
               } else if (!(this.fieldSignature == var2.fieldSignature)) {
                  return false;
               } else {
                  return this.fieldName == var2.fieldName;
               }
            }
         }
      }
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$ClassPathMatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,373:1\n1#2:374\n*E\n"])
   public data class ClassPathMatch(op: com.feysh.corax.config.api.rules.ProcessRule.OP, path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase) :
      ProcessRule.IMatchItem,
      ProcessRule.ErrorCommit {
      public open val op: com.feysh.corax.config.api.rules.ProcessRule.OP

      @EncodeDefault(
         mode = Mode.ALWAYS
      )
      @SerialName("path")
      public final val path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase

      public open val error: String?
         public open get() {
            val var10000: java.lang.String = ProcessRule.INSTANCE.isValidOf(this.path, ProcessRule.Type.File);
            return var10000 ?: null;
         }


      init {
         this.op = op;
         this.path = path;
      }

      public override fun matches(matchContent: com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget): Boolean {
         if (matchContent !is ProcessRule.ClassPathMatch.MatchTarget) {
            return false;
         } else {
            var anyMatch: Boolean = false;
            val var10000: java.lang.Boolean = ProcessRule.access$matcher(
               ProcessRule.INSTANCE, this.path, (matchContent as ProcessRule.ClassPathMatch.MatchTarget).getPath()
            );
            if (var10000 != null) {
               if (!var10000) {
                  return false;
               }

               anyMatch = true;
            }

            return anyMatch;
         }
      }

      public override fun toString(): String {
         val var2: java.util.List = new ArrayList();
         var2.add("path=${this.path}");
         return CollectionsKt.joinToString$default(var2, ", ", "(${this.getOp().getPretty()})", "", 0, null, null, 56, null);
      }

      public operator fun component1(): com.feysh.corax.config.api.rules.ProcessRule.OP {
         return this.op;
      }

      public operator fun component2(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase {
         return this.path;
      }

      public fun copy(
         op: com.feysh.corax.config.api.rules.ProcessRule.OP = this.op,
         path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase = this.path
      ): com.feysh.corax.config.api.rules.ProcessRule.ClassPathMatch {
         return new ProcessRule.ClassPathMatch(op, path);
      }

      public override fun hashCode(): Int {
         return this.op.hashCode() * 31 + this.path.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ProcessRule.ClassPathMatch) {
            return false;
         } else {
            val var2: ProcessRule.ClassPathMatch = other as ProcessRule.ClassPathMatch;
            if (this.op != (other as ProcessRule.ClassPathMatch).op) {
               return false;
            } else {
               return this.path == var2.path;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<com.feysh.corax.config.api.rules.ProcessRule.ClassPathMatch> {
            return ProcessRule.ClassPathMatch.$serializer.INSTANCE as KSerializer<ProcessRule.ClassPathMatch>;
         }
      }

      public data class MatchTarget(path: String?) : ProcessRule.IMatchTarget {
         public final val path: String?

         public open val type: String
            public open get() {
               return "classpath";
            }


         init {
            this.path = path;
         }

         public override fun toString(): String {
            val var2: java.util.List = new ArrayList();
            if (this.path != null) {
               var2.add("path=${this.path}");
            }

            return CollectionsKt.joinToString$default(var2, ", ", "${this.getType()}: { ", " }", 0, null, null, 56, null);
         }

         public operator fun component1(): String? {
            return this.path;
         }

         public fun copy(path: String? = this.path): com.feysh.corax.config.api.rules.ProcessRule.ClassPathMatch.MatchTarget {
            return new ProcessRule.ClassPathMatch.MatchTarget(path);
         }

         public override fun hashCode(): Int {
            return if (this.path == null) 0 else this.path.hashCode();
         }

         public override operator fun equals(other: Any?): Boolean {
            if (this === other) {
               return true;
            } else if (other !is ProcessRule.ClassPathMatch.MatchTarget) {
               return false;
            } else {
               return this.path == (other as ProcessRule.ClassPathMatch.MatchTarget).path;
            }
         }
      }
   }

   public interface ErrorCommit {
      public val error: String?
   }

   @Serializable
   @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$FileMatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,373:1\n1#2:374\n*E\n"])
   public data class FileMatch(op: com.feysh.corax.config.api.rules.ProcessRule.OP, path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase) :
      ProcessRule.IMatchItem,
      ProcessRule.ErrorCommit {
      public open val op: com.feysh.corax.config.api.rules.ProcessRule.OP

      @EncodeDefault(
         mode = Mode.ALWAYS
      )
      @SerialName("path")
      public final val path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase

      public open val error: String?
         public open get() {
            val var10000: java.lang.String = ProcessRule.INSTANCE.isValidOf(this.path, ProcessRule.Type.File);
            return var10000 ?: null;
         }


      init {
         this.op = op;
         this.path = path;
      }

      public override fun matches(matchContent: com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget): Boolean {
         if (matchContent !is ProcessRule.FileMatch.MatchTarget) {
            return false;
         } else {
            var anyMatch: Boolean = false;
            val var10000: java.lang.Boolean = ProcessRule.access$matcher(
               ProcessRule.INSTANCE, this.path, (matchContent as ProcessRule.FileMatch.MatchTarget).getPath()
            );
            if (var10000 != null) {
               if (!var10000) {
                  return false;
               }

               anyMatch = true;
            }

            return anyMatch;
         }
      }

      public override fun toString(): String {
         val var2: java.util.List = new ArrayList();
         var2.add("path=${this.path}");
         return CollectionsKt.joinToString$default(var2, ", ", "(${this.getOp().getPretty()})", "", 0, null, null, 56, null);
      }

      public operator fun component1(): com.feysh.corax.config.api.rules.ProcessRule.OP {
         return this.op;
      }

      public operator fun component2(): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase {
         return this.path;
      }

      public fun copy(
         op: com.feysh.corax.config.api.rules.ProcessRule.OP = this.op,
         path: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase = this.path
      ): com.feysh.corax.config.api.rules.ProcessRule.FileMatch {
         return new ProcessRule.FileMatch(op, path);
      }

      public override fun hashCode(): Int {
         return this.op.hashCode() * 31 + this.path.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ProcessRule.FileMatch) {
            return false;
         } else {
            val var2: ProcessRule.FileMatch = other as ProcessRule.FileMatch;
            if (this.op != (other as ProcessRule.FileMatch).op) {
               return false;
            } else {
               return this.path == var2.path;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<com.feysh.corax.config.api.rules.ProcessRule.FileMatch> {
            return ProcessRule.FileMatch.$serializer.INSTANCE as KSerializer<ProcessRule.FileMatch>;
         }
      }

      public data class MatchTarget(path: String?) : ProcessRule.IMatchTarget {
         public final val path: String?

         public open val type: String
            public open get() {
               return "file";
            }


         init {
            this.path = path;
         }

         public override fun toString(): String {
            val var2: java.util.List = new ArrayList();
            if (this.path != null) {
               var2.add("path=${this.path}");
            }

            return CollectionsKt.joinToString$default(var2, ", ", "${this.getType()}: { ", " }", 0, null, null, 56, null);
         }

         public operator fun component1(): String? {
            return this.path;
         }

         public fun copy(path: String? = this.path): com.feysh.corax.config.api.rules.ProcessRule.FileMatch.MatchTarget {
            return new ProcessRule.FileMatch.MatchTarget(path);
         }

         public override fun hashCode(): Int {
            return if (this.path == null) 0 else this.path.hashCode();
         }

         public override operator fun equals(other: Any?): Boolean {
            if (this === other) {
               return true;
            } else if (other !is ProcessRule.FileMatch.MatchTarget) {
               return false;
            } else {
               return this.path == (other as ProcessRule.FileMatch.MatchTarget).path;
            }
         }
      }
   }

   public interface IMatchItem {
      public val op: com.feysh.corax.config.api.rules.ProcessRule.OP

      public abstract fun matches(matchContent: com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget): Boolean {
      }
   }

   public interface IMatchTarget {
      public val type: String
   }

   @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$InlineRuleStringSerialize\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Json.kt\nkotlinx/serialization/json/JsonKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,373:1\n295#2,2:374\n774#2:378\n865#2,2:379\n1187#2,2:381\n1261#2,4:383\n211#3:376\n1#4:377\n*S KotlinDebug\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$InlineRuleStringSerialize\n*L\n338#1:374,2\n358#1:378\n358#1:379,2\n358#1:381,2\n358#1:383,4\n346#1:376\n*E\n"])
   public object InlineRuleStringSerialize {
      private final val jsonFormat: Json = JsonKt.Json$default(null, ProcessRule.InlineRuleStringSerialize::jsonFormat$lambda$0, 1, null)

      public fun <R : com.feysh.corax.config.api.rules.ProcessRule.IMatchItem> deserializeMatchFromLineString(
         serializer: KSerializer<R>,
         objectKeyValues: String
      ): R {
         val itemRegex: Regex = new Regex(
            "^\\((?<op>${CollectionsKt.joinToString$default(
               ProcessRule.OP.getEntries() as java.lang.Iterable,
               "|",
               "[",
               "]",
               0,
               null,
               ProcessRule.InlineRuleStringSerialize::deserializeMatchFromLineString$lambda$1,
               24,
               null
            )})\\)(?<fields>.+)\$"
         );
         var var10000: MatchResult = itemRegex.matchEntire(objectKeyValues);
         if (var10000 == null) {
            throw new SerializationException("No match found in the input string: `$objectKeyValues`. matcher: $itemRegex, serializer=$serializer");
         } else {
            val var25: MatchGroup = RegexExtensionsJDK8Kt.get(var10000.getGroups(), "op");
            if (var25 != null) {
               val var26: java.lang.String = var25.getValue();
               if (var26 != null) {
                  val opText: java.lang.String = var26;
                  val `$this$encodeToJsonElement$iv`: java.util.Iterator = (ProcessRule.OP.getEntries() as java.lang.Iterable).iterator();

                  while (true) {
                     if (`$this$encodeToJsonElement$iv`.hasNext()) {
                        val r: Any = `$this$encodeToJsonElement$iv`.next();
                        if (!((r as ProcessRule.OP).getPretty() == opText)) {
                           continue;
                        }

                        var10000 = (MatchResult)r;
                        break;
                     }

                     var10000 = null;
                     break;
                  }

                  val var28: ProcessRule.OP = var10000 as ProcessRule.OP;
                  if (var10000 as ProcessRule.OP == null) {
                     throw new SerializationException("invalid op syntax: `$opText` in `$objectKeyValues`, serializer=$serializer");
                  }

                  val var29: MatchGroup = RegexExtensionsJDK8Kt.get(var10000.getGroups(), "fields");
                  if (var29 != null) {
                     val var30: java.lang.String = var29.getValue();
                     if (var30 != null) {
                        val var16: java.util.Map = MapsKt.toMutableMap(this.lineString2map(serializer, var30));
                        var16.put("op", var28.name());
                        val var18: Json = jsonFormat;
                        jsonFormat.getSerializersModule();
                        val var19: Any = jsonFormat.decodeFromJsonElement(
                           serializer as DeserializationStrategy,
                           var18.encodeToJsonElement(
                              (new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE)) as SerializationStrategy, var16
                           )
                        );
                        val var22: ProcessRule.IMatchItem = var19 as ProcessRule.IMatchItem;
                        val var31: ProcessRule.ErrorCommit = var19 as ProcessRule.IMatchItem as? ProcessRule.ErrorCommit;
                        if ((var22 as? ProcessRule.ErrorCommit) != null) {
                           val var32: java.lang.String = var31.getError();
                           if (var32 != null) {
                              throw new IllegalStateException(("Invalid process-regex: `$objectKeyValues`, error: $var32").toString());
                           }
                        }

                        return (R)(var19 as ProcessRule.IMatchItem);
                     }
                  }

                  throw new IllegalStateException(("invalid fields syntax in `$objectKeyValues`, serializer=$serializer").toString());
               }
            }

            throw new SerializationException("invalid op syntax: `$objectKeyValues`, serializer=$serializer");
         }
      }

      public fun <T> lineString2map(serializer: KSerializer<T>, fieldInline: String): Map<String, String> {
         val elementNames: java.lang.String = CollectionsKt.joinToString$default(
            SerialDescriptorKt.getElementNames(serializer.getDescriptor()), "|", null, null, 0, null, null, 62, null
         );
         val split: Regex = new Regex(",\\s*(?=($elementNames)\\s*=)");
         val var10000: Pattern = Pattern.compile("^\\s*(?<key>$elementNames)\\s*=(?<value>.*)\$", 0);
         val pairRegex: Pattern = var10000;
         var var22: java.lang.Iterable = split.split(fieldInline, 0);
         val `$this$associateTo$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : var22) {
            if ((`element$iv$iv` as java.lang.String).length() > 0) {
               `$this$associateTo$iv$iv`.add(`element$iv$iv`);
            }
         }

         var22 = `$this$associateTo$iv$iv` as java.util.List;
         val `destination$iv$ivx`: java.util.Map = new LinkedHashMap(
            RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`$this$associateTo$iv$iv` as java.util.List, 10)), 16)
         );

         for (Object element$iv$ivx : var22) {
            val it: java.lang.String = `element$iv$ivx` as java.lang.String;
            val matcher: Matcher = pairRegex.matcher(`element$iv$ivx` as java.lang.String);
            if (!matcher.find()) {
               throw new SerializationException("invalid syntax regex `$it` in `$fieldInline`, serializer=$serializer");
            }

            val var31: java.lang.String = matcher.group("key");
            if (var31 == null) {
               throw new IllegalStateException(("key not exists in `$it`, pattern: $pairRegex, serializer=$serializer").toString());
            }

            val var32: java.lang.String = matcher.group("value");
            if (var32 == null) {
               throw new IllegalStateException(("value not exists in `$it`, pattern: $pairRegex, serializer=$serializer").toString());
            }

            val var30: Pair = TuplesKt.to(var31, var32);
            `destination$iv$ivx`.put(var30.getFirst(), var30.getSecond());
         }

         return `destination$iv$ivx`;
      }

      @JvmStatic
      fun JsonBuilder.`jsonFormat$lambda$0`(): Unit {
         `$this$Json`.setUseArrayPolymorphism(true);
         `$this$Json`.setPrettyPrint(true);
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `deserializeMatchFromLineString$lambda$1`(it: ProcessRule.OP): java.lang.CharSequence {
         return Regex.Companion.escape(it.getPretty());
      }
   }

   public enum class OP(pretty: String) {
      Add("+"),
      Sub("-")
      public final val pretty: String

      init {
         this.pretty = pretty;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<ProcessRule.OP> {
         return $ENTRIES;
      }
   }

   @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$ProcRegex\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,373:1\n1#2:374\n*E\n"])
   private data class ProcRegex(regexExpr: String) : ProcessRule.ProcRegexBase {
      public final val regexExpr: String

      private final val regex: Regex
         private final get() {
            return this.regex$delegate.getValue() as Regex;
         }


      init {
         this.regexExpr = regexExpr;
         if (this.regexExpr.length() <= 0) {
            throw new IllegalStateException("regex expr could not be empty".toString());
         } else {
            this.regex$delegate = LazyKt.lazy(ProcessRule.ProcRegex::regex_delegate$lambda$1);
         }
      }

      public override fun serializerToString(): String {
         return this.regexExpr;
      }

      public override fun matches(identifier: String): Boolean {
         return this.getRegex().containsMatchIn(identifier);
      }

      public override fun toString(): String {
         return this.serializerToString();
      }

      public operator fun component1(): String {
         return this.regexExpr;
      }

      public fun copy(regexExpr: String = this.regexExpr): com.feysh.corax.config.api.rules.ProcessRule.ProcRegex {
         return new ProcessRule.ProcRegex(regexExpr);
      }

      public override fun hashCode(): Int {
         return this.regexExpr.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ProcessRule.ProcRegex) {
            return false;
         } else {
            return this.regexExpr == (other as ProcessRule.ProcRegex).regexExpr;
         }
      }

      @JvmStatic
      fun `regex_delegate$lambda$1`(`this$0`: ProcessRule.ProcRegex): Regex {
         return new Regex(`this$0`.regexExpr);
      }
   }

   @Serializable(with = ProcessRule.ProcRegexBaseSerializer::class)
   public abstract class ProcRegexBase {
      public abstract fun serializerToString(): String {
      }

      public abstract fun matches(identifier: String): Boolean {
      }

      public override fun toString(): String {
         return this.serializerToString();
      }

      @SourceDebugExtension(["SMAP\nProcessRule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule$ProcRegexBase$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,373:1\n1#2:374\n*E\n"])
      public companion object {
         public fun parse(str: String, origin: String? = null): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase {
            if (str.length() != 0) {
               return new ProcessRule.ProcRegex(str);
            } else {
               var var10000: java.lang.String;
               label19: {
                  if (origin != null) {
                     var10000 = " in ScanRegexItem: $origin";
                     if (var10000 != null) {
                        break label19;
                     }
                  }

                  var10000 = "";
               }

               throw new SerializationException("empty regex expression$var10000");
            }
         }

         public fun serializer(): KSerializer<com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase> {
            return ProcessRule.ProcRegexBaseSerializer.INSTANCE;
         }
      }
   }

   public object ProcRegexBaseSerializer : KSerializer<ProcessRule.ProcRegexBase> {
      public open val descriptor: SerialDescriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("proc-regex", STRING.INSTANCE as PrimitiveKind)

      public open fun serialize(encoder: Encoder, value: com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase) {
         encoder.encodeString(value.serializerToString());
      }

      public open fun deserialize(decoder: Decoder): com.feysh.corax.config.api.rules.ProcessRule.ProcRegexBase {
         return ProcessRule.ProcRegexBase.Companion.parse(decoder.decodeString(), null);
      }
   }

   public enum class ScanAction {
      Process,
      Skip,
      Keep
      @JvmStatic
      fun getEntries(): EnumEntries<ProcessRule.ScanAction> {
         return $ENTRIES;
      }
   }

   public enum class Type(isFile: Boolean, isClassMember: Boolean) {
      File(true, false),
      Class(false, true),
      Method(false, true),
      Field(false, true)
      public final val isFile: Boolean
      public final val isClassMember: Boolean

      init {
         this.isFile = isFile;
         this.isClassMember = isClassMember;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<ProcessRule.Type> {
         return $ENTRIES;
      }
   }
}
