package com.feysh.corax.cache

import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.feysh.corax.cache.analysis.CompilationUnitOfSCFactory
import com.feysh.corax.cache.analysis.CompilationUnitOfSCKey
import com.feysh.corax.cache.analysis.SootHostExtInfoFactory
import com.feysh.corax.cache.analysis.SootHostExtend
import com.feysh.corax.cache.analysis.SootHostExtendKt
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import java.nio.file.Path
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import mu.KLogger
import soot.SootClass
import soot.tagkit.Host

@SourceDebugExtension(["SMAP\nAnalysisCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalysisCache.kt\ncom/feysh/corax/cache/AnalysisCache\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,94:1\n216#2,2:95\n*S KotlinDebug\n*F\n+ 1 AnalysisCache.kt\ncom/feysh/corax/cache/AnalysisCache\n*L\n77#1:95,2\n*E\n"])
public open class AnalysisCache {
   private final val factoryMap: MutableMap<Key<*>, AnalysisDataFactory<*, *>> = (new LinkedHashMap()) as java.util.Map

   public suspend fun <T> getAsync(key: AnalysisKey<T>, ctx: CoroutineContext = ...): T {
      return BuildersKt.withContext(ctx, (new Function2<CoroutineScope, Continuation<? super T>, Object>(this, key, null) {
         int label;

         {
            super(2, `$completionx`);
            this.this$0 = `$receiver`;
            this.$key = `$key`;
         }

         public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  return this.this$0.get(this.$key);
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
         }

         public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
            return (new <anonymous constructor>(this.this$0, this.$key, `$completion`)) as Continuation<Unit>;
         }

         public final Object invoke(CoroutineScope p1, Continuation<? super T> p2) {
            return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
         }
      }) as Function2, `$completion`);
   }

   public fun <T> get(key: AnalysisKey<T>): T {
      val var3: Any = this.factoryMap.get(key.getFactoryKey());
      val var10000: AnalysisDataFactory = var3 as? AnalysisDataFactory;
      if ((var3 as? AnalysisDataFactory) == null) {
         throw new IllegalStateException((AnalysisCache::get$lambda$0).toString());
      } else {
         return (T)(var10000.getCache().get(key) as XOptional).getValue();
      }
   }

   public open fun <T, K : AnalysisKey<T>> registerFactory(factory: AnalysisDataFactory<T, K>) {
      this.factoryMap.put(factory.getKey(), factory);
   }

   public fun clear() {
      for (Entry element$iv : this.factoryMap.entrySet()) {
         (`element$iv`.getValue() as AnalysisDataFactory).getCache().cleanUp();
      }
   }

   @JvmStatic
   fun `get$lambda$0`(`$key`: AnalysisKey): java.lang.String {
      return "Did you forget to call registerFactory(${`$key`.getFactoryKey()})?";
   }

   @JvmStatic
   fun `logger$lambda$2`(): Unit {
      return Unit.INSTANCE;
   }

   public companion object {
      private final val logger: KLogger
   }

   public object G : AnalysisCache.GlobalAnalysisCache

   public open class GlobalAnalysisCache : AnalysisCache {
      public open lateinit var compilationUnitOfSCFactory: CompilationUnitOfSCFactory
         internal final set

      public open lateinit var sootHostExtInfoFactory: SootHostExtInfoFactory
         internal final set

      public final val analysisCache: Cache<Any, Any> = Caffeine.newBuilder().build()

      public fun class2CompilationUnit(sc: SootClass): CompilationUnitOfSCKey {
         return new CompilationUnitOfSCKey(sc, this.getCompilationUnitOfSCFactory().getKey());
      }

      public fun class2SourceFile(sc: SootClass): Path? {
         return this.getCompilationUnitOfSCFactory().getLocator().invoke(sc) as Path;
      }

      public fun sootHost2decl(host: Host): SootHostExtend? {
         return SootHostExtendKt.ext(host, AnalysisCache.G.INSTANCE, this.getSootHostExtInfoFactory().getKey());
      }
   }
}
