package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.feysh.corax.cache.analysis.cpg.InferenceConfigurationType
import com.feysh.corax.cache.analysis.cpg.Lang
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import de.fraunhofer.aisec.cpg.TranslationConfiguration
import de.fraunhofer.aisec.cpg.TranslationManager
import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.TranslationConfiguration.Builder
import de.fraunhofer.aisec.cpg.passes.Pass
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import mu.KLogger
import mu.KotlinLogging

@SourceDebugExtension(["SMAP\nCompilationUnitOfCPG.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompilationUnitOfCPG.kt\ncom/feysh/corax/cache/analysis/CompilationUnitOfCPGDataFactory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,112:1\n1863#2,2:113\n*S KotlinDebug\n*F\n+ 1 CompilationUnitOfCPG.kt\ncom/feysh/corax/cache/analysis/CompilationUnitOfCPGDataFactory\n*L\n91#1:113,2\n*E\n"])
public object CompilationUnitOfCPGDataFactory : AnalysisDataFactory<TranslationResult, CPGCompilationUnitKey> {
   private final val logger: KLogger = KotlinLogging.INSTANCE.logger(CompilationUnitOfCPGDataFactory::logger$lambda$0)

   private final val defaultBuilder: Caffeine<Any?, Any?>
      private final get() {
         val var10000: Caffeine = Caffeine.newBuilder().expireAfterAccess(45L, TimeUnit.SECONDS).softValues();
         return var10000;
      }


   public open val cache: LoadingCache<CPGCompilationUnitKey, XOptional<TranslationResult?>> =
      AnalysisCacheKt.buildX(INSTANCE.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<TranslationResult?> = (new AnalysisDataFactory.Key<TranslationResult>() {}) as AnalysisDataFactory.Key

   @Throws(java/lang/Exception::class)
   public fun analyze(
      files: List<File>,
      topLevel: File?,
      passTypes: List<KClass<out Pass<*>>>,
      inferenceConfigurationType: InferenceConfigurationType,
      lang: Lang
   ): TranslationResult? {
      val builder: Builder = TranslationConfiguration.Companion
         .builder()
         .sourceLocations(files)
         .topLevel(topLevel)
         .loadIncludes(false)
         .debugParser(false)
         .failOnError(true)
         .useParallelFrontends(true)
         .registerLanguage(lang.getLanguage())
         .inferenceConfiguration(inferenceConfigurationType.getConfiguration());
      if (passTypes.isEmpty()) {
         builder.defaultPasses();
      } else {
         val config: java.lang.Iterable;
         for (Object element$iv : config) {
            builder.registerPass(`element$iv` as KClass);
         }
      }

      return TranslationManager.Companion.builder().config(builder.build()).build().analyze().get(2000L, TimeUnit.MILLISECONDS) as TranslationResult;
   }

   public fun analyze2(files: List<File>, configModifier: Consumer<Builder>? = null): TranslationResult {
      val builder: Builder = TranslationConfiguration.Companion.builder().sourceLocations(files).defaultPasses();
      if (configModifier != null) {
         configModifier.accept(builder);
      }

      val var10000: Any = TranslationManager.Companion.builder().config(builder.build()).build().analyze().get();
      return var10000 as TranslationResult;
   }

   @JvmStatic
   fun `logger$lambda$0`(): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
