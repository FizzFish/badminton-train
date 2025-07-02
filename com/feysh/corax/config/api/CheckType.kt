package com.feysh.corax.config.api

import java.util.LinkedHashSet
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
@SourceDebugExtension(["SMAP\nAIAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/CheckType\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,839:1\n865#2,2:840\n*S KotlinDebug\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/CheckType\n*L\n133#1:840,2\n*E\n"])
public abstract class CheckType {
   public abstract val checker: IChecker
   public open val aliasNames: Set<String>
   public abstract val bugMessage: Map<Language, BugMessage>

   public open val report: IRule
      public open get() {
         return this.getChecker().getReport();
      }


   public open val standards: Set<IRule>
      public open get() {
         return SetsKt.plus(this.getChecker().getStandards(), this.getReport());
      }


   public open val serializerName: String
      public open get() {
         val var10000: java.lang.String = this.getClass().getCanonicalName();
         return var10000;
      }


   public open val simpleName: String
      public open get() {
         val var10000: java.lang.String = this.getClass().getSimpleName();
         return var10000;
      }


   open fun CheckType() {
      this.aliasNames = SetsKt.emptySet();
   }

   public fun getRulesByName(standardName: String): MutableSet<IRule> {
      val `$this$filterTo$iv`: java.lang.Iterable = this.getStandards();
      val `destination$iv`: java.util.Collection = new LinkedHashSet();

      for (Object element$iv : $this$filterTo$iv) {
         if ((`element$iv` as IRule).getStandard().getRealName() == standardName) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableSet<IRule>;
   }

   public fun serializer(): KSerializer<CheckType> {
      return new ObjectSerializer<>(this.getSerializerName(), this);
   }

   public override fun toString(): String {
      return "${this.getChecker().getSimpleName()}.${this.getSimpleName()}";
   }

   public companion object {
      public fun serializer(): KSerializer<CheckType> {
         return this.get$cachedSerializer();
      }
   }
}
