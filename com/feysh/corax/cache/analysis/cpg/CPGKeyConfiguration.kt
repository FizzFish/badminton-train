package com.feysh.corax.cache.analysis.cpg

import de.fraunhofer.aisec.cpg.passes.Pass
import java.io.File
import java.util.ArrayList
import kotlin.reflect.KClass

public data class CPGKeyConfiguration private constructor(sourceFiles: Set<File>,
   topLevel: File?,
   passTypes: Set<KClass<out Pass<*>>>,
   inferenceConfigurationType: InferenceConfigurationType,
   lang: Lang
) {
   public final val sourceFiles: Set<File>
   public final val topLevel: File?
   public final val passTypes: Set<KClass<out Pass<*>>>
   public final val inferenceConfigurationType: InferenceConfigurationType
   public final val lang: Lang

   init {
      this.sourceFiles = sourceFiles;
      this.topLevel = topLevel;
      this.passTypes = passTypes;
      this.inferenceConfigurationType = inferenceConfigurationType;
      this.lang = lang;
   }

   public operator fun component1(): Set<File> {
      return this.sourceFiles;
   }

   public operator fun component2(): File? {
      return this.topLevel;
   }

   public operator fun component3(): Set<KClass<out Pass<*>>> {
      return this.passTypes;
   }

   public operator fun component4(): InferenceConfigurationType {
      return this.inferenceConfigurationType;
   }

   public operator fun component5(): Lang {
      return this.lang;
   }

   public fun copy(
      sourceFiles: Set<File> = this.sourceFiles,
      topLevel: File? = this.topLevel,
      passTypes: Set<KClass<out Pass<*>>> = this.passTypes,
      inferenceConfigurationType: InferenceConfigurationType = this.inferenceConfigurationType,
      lang: Lang = this.lang
   ): CPGKeyConfiguration {
      return new CPGKeyConfiguration(sourceFiles, topLevel, passTypes, inferenceConfigurationType, lang);
   }

   public override fun toString(): String {
      return "CPGKeyConfiguration(sourceFiles=${this.sourceFiles}, topLevel=${this.topLevel}, passTypes=${this.passTypes}, inferenceConfigurationType=${this.inferenceConfigurationType}, lang=${this.lang})";
   }

   public override fun hashCode(): Int {
      return (
               ((this.sourceFiles.hashCode() * 31 + (if (this.topLevel == null) 0 else this.topLevel.hashCode())) * 31 + this.passTypes.hashCode()) * 31
                  + this.inferenceConfigurationType.hashCode()
            )
            * 31
         + this.lang.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CPGKeyConfiguration) {
         return false;
      } else {
         val var2: CPGKeyConfiguration = other as CPGKeyConfiguration;
         if (!(this.sourceFiles == (other as CPGKeyConfiguration).sourceFiles)) {
            return false;
         } else if (!(this.topLevel == var2.topLevel)) {
            return false;
         } else if (!(this.passTypes == var2.passTypes)) {
            return false;
         } else if (this.inferenceConfigurationType != var2.inferenceConfigurationType) {
            return false;
         } else {
            return this.lang === var2.lang;
         }
      }
   }

   public class Builder {
      private final var sourceFiles: Set<File> = SetsKt.emptySet()
      private final var topLevel: File?
      private final var passTypes: List<KClass<out Pass<*>>> = (new ArrayList()) as java.util.List
      private final var inferenceConfigurationType: InferenceConfigurationType = InferenceConfigurationType.DEFAULT
      private final var lang: Lang = Lang.JAVA

      public fun sourceFiles(sourceFiles: Collection<File>): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         this.sourceFiles = CollectionsKt.toHashSet(sourceFiles);
         return this;
      }

      public fun topLevel(topLevel: File?): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         this.topLevel = topLevel;
         return this;
      }

      public fun passTypes(passTypes: List<KClass<out Pass<*>>>): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         this.passTypes = passTypes;
         return this;
      }

      public fun inferenceConfigurationType(inferenceConfigurationType: InferenceConfigurationType): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         this.inferenceConfigurationType = inferenceConfigurationType;
         return this;
      }

      public fun lang(lang: Lang): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         this.lang = lang;
         return this;
      }

      public fun build(): CPGKeyConfiguration {
         return new CPGKeyConfiguration(
            CollectionsKt.toHashSet(this.sourceFiles),
            if (this.topLevel != null) this.topLevel.getCanonicalFile() else null,
            CollectionsKt.toHashSet(this.passTypes),
            this.inferenceConfigurationType,
            this.lang,
            null
         );
      }
   }

   public companion object {
      public fun builder(): com.feysh.corax.cache.analysis.cpg.CPGKeyConfiguration.Builder {
         return new CPGKeyConfiguration.Builder();
      }
   }
}
