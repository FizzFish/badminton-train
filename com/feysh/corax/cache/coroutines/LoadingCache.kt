package com.feysh.corax.cache.coroutines

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import java.util.LinkedHashMap
import java.util.Map.Entry
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executor
import java.util.function.BiFunction
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.ContinuationImpl
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.future.FutureKt
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@SourceDebugExtension(["SMAP\nLoadingCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoadingCache.kt\ncom/feysh/corax/cache/coroutines/LoadingCache\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,186:1\n462#2:187\n412#2:188\n462#2:193\n412#2:194\n1246#3,4:189\n1246#3,4:195\n*S KotlinDebug\n*F\n+ 1 LoadingCache.kt\ncom/feysh/corax/cache/coroutines/LoadingCache\n*L\n153#1:187\n153#1:188\n161#1:193\n161#1:194\n153#1:189,4\n161#1:195,4\n*E\n"])
public class LoadingCache<K, V>(defaultScope: CoroutineScope, useCallingContext: Boolean, cache: AsyncLoadingCache<Any, Any>) {
   private final val defaultScope: CoroutineScope
   private final val useCallingContext: Boolean
   private final val cache: AsyncLoadingCache<Any, Any>

   init {
      this.defaultScope = defaultScope;
      this.useCallingContext = useCallingContext;
      this.cache = cache;
   }

   public fun underlying(): AsyncLoadingCache<Any, Any> {
      return this.cache;
   }

   public suspend fun contains(key: Any): Boolean {
      var `$continuation`: Continuation;
      label29: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label29;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.contains(null, this as Continuation<? super java.lang.Boolean>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var10000 = this.cache.getIfPresent(key);
            if (var10000 != null) {
               var10000 = var10000 as CompletionStage;
               `$continuation`.label = 1;
               var10000 = FutureKt.await((CompletionStage)var10000, `$continuation`);
               if (var10000 === var5) {
                  return var5;
               }
            } else {
               var10000 = null;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxBoolean(var10000 != null);
   }

   public suspend fun getIfPresent(key: Any): Any? {
      val var10000: CompletableFuture = this.cache.getIfPresent(key);
      return if (var10000 != null) FutureKt.await(var10000, `$completion`) else null;
   }

   public suspend fun get(key: Any): Any {
      val var10000: CompletableFuture = this.cache.get(key);
      return FutureKt.await(var10000, `$completion`);
   }

   public suspend fun getAsync(key: Any): CompletableFuture<Any> {
      val var10000: CompletableFuture = this.cache.get(key);
      return var10000;
   }

   public suspend fun getAll(keys: Collection<Any>): Map<Any, Any> {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.getAll(null, this as Continuation<? super java.utilMap<K, ? extends V>>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var3: CompletableFuture = this.cache.getAll(keys);
            var10000 = var3;
            `$continuation`.label = 1;
            var10000 = FutureKt.await((CompletionStage)var10000, `$continuation`);
            if (var10000 === var6) {
               return var6;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return var10000;
   }

   public suspend fun getAll(keys: Collection<Any>, compute: (Set<Any>, Continuation<Map<Any, Any>>) -> Any?): Map<Any, Any> {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.getAll(null, null, this as Continuation<? super java.utilMap<K, ? extends V>>);
            }
         };
      }

      var var10000: Any;
      label22: {
         val `$result`: Any = `$continuation`.result;
         val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               `$continuation`.L$0 = this;
               `$continuation`.L$1 = keys;
               `$continuation`.L$2 = compute;
               `$continuation`.label = 1;
               var10000 = this.scope(`$continuation`);
               if (var10000 === var8) {
                  return var8;
               }
               break;
            case 1:
               compute = `$continuation`.L$2 as Function2;
               keys = `$continuation`.L$1 as java.util.Collection;
               this = `$continuation`.L$0 as LoadingCache;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label22;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var10000 = this.cache.getAll(keys, new BiFunction(LoadingCache::getAll$lambda$0) {
            {
               this.function = function;
            }
         });
         var10000 = var10000 as CompletionStage;
         `$continuation`.L$0 = null;
         `$continuation`.L$1 = null;
         `$continuation`.L$2 = null;
         `$continuation`.label = 2;
         var10000 = FutureKt.await((CompletionStage)var10000, `$continuation`);
         if (var10000 === var8) {
            return var8;
         }
      }

      return var10000;
   }

   public suspend fun get(key: Any, compute: (Any, Continuation<Any>) -> Any?): Any {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.get(null, null, this as Continuation<? super V>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var9: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = this;
            `$continuation`.L$1 = key;
            `$continuation`.L$2 = compute;
            `$continuation`.label = 1;
            var9 = this.scope(`$continuation`);
            if (var9 === var8) {
               return var8;
            }
            break;
         case 1:
            compute = `$continuation`.L$2 as Function2;
            key = `$continuation`.L$1;
            this = `$continuation`.L$0 as LoadingCache;
            ResultKt.throwOnFailure(`$result`);
            var9 = `$result`;
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var9 = this.cache.get(key, new BiFunction(LoadingCache::get$lambda$1) {
         {
            this.function = function;
         }
      });
      var9 = var9 as CompletionStage;
      `$continuation`.L$0 = null;
      `$continuation`.L$1 = null;
      `$continuation`.L$2 = null;
      `$continuation`.label = 2;
      var9 = FutureKt.await((CompletionStage)var9, `$continuation`);
      return if (var9 === var8) var8 else var9;
   }

   public suspend fun getOrNull(key: Any, compute: (Any, Continuation<Any?>) -> Any?): Any? {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.getOrNull(null, null, this as Continuation<? super V>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var9: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = this;
            `$continuation`.L$1 = key;
            `$continuation`.L$2 = compute;
            `$continuation`.label = 1;
            var9 = this.scope(`$continuation`);
            if (var9 === var8) {
               return var8;
            }
            break;
         case 1:
            compute = `$continuation`.L$2 as Function2;
            key = `$continuation`.L$1;
            this = `$continuation`.L$0 as LoadingCache;
            ResultKt.throwOnFailure(`$result`);
            var9 = `$result`;
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var9 = this.cache.get(key, new BiFunction(LoadingCache::getOrNull$lambda$2) {
         {
            this.function = function;
         }
      });
      var9 = var9 as CompletionStage;
      `$continuation`.L$0 = null;
      `$continuation`.L$1 = null;
      `$continuation`.L$2 = null;
      `$continuation`.label = 2;
      var9 = FutureKt.await((CompletionStage)var9, `$continuation`);
      return if (var9 === var8) var8 else var9;
   }

   @Deprecated(message = "Use get", replaceWith = @ReplaceWith(expression = "get(key, compute)", imports = []))
   public suspend fun getOrPut(key: Any, compute: (Any, Continuation<Any>) -> Any?): Any {
      return this.get((K)key, compute, `$completion`);
   }

   public suspend fun put(key: Any, compute: (Continuation<Any>) -> Any?) {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.put(null, null, this as Continuation<? super Unit>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = this;
            `$continuation`.L$1 = key;
            `$continuation`.L$2 = compute;
            `$continuation`.label = 1;
            var10000 = this.scope(`$continuation`);
            if (var10000 === var7) {
               return var7;
            }
            break;
         case 1:
            compute = `$continuation`.L$2 as Function1;
            key = `$continuation`.L$1;
            this = `$continuation`.L$0 as LoadingCache;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      this.cache
         .put(
            key,
            FutureKt.asCompletableFuture(
               BuildersKt.async$default(
                  var10000 as CoroutineScope, null, null, (new Function2<CoroutineScope, Continuation<? super V>, Object>(compute, null) {
                     int label;

                     {
                        super(2, `$completionx`);
                        this.$compute = `$compute`;
                     }

                     public final Object invokeSuspend(Object $result) {
                        val var2: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        var var10000: Any;
                        switch (this.label) {
                           case 0:
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = this.$compute;
                              this.label = 1;
                              var10000 = (Function1)var10000.invoke(this);
                              if (var10000 === var2) {
                                 return var2;
                              }
                              break;
                           case 1:
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = (Function1)`$result`;
                              break;
                           default:
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        return var10000;
                     }

                     public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                        return (new <anonymous constructor>(this.$compute, `$completion`)) as Continuation<Unit>;
                     }

                     public final Object invoke(CoroutineScope p1, Continuation<? super V> p2) {
                        return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
                     }
                  }) as Function2, 3, null
               )
            )
         );
      return Unit.INSTANCE;
   }

   public fun put(key: Any, value: Any) {
      this.cache.put(key, CompletableFuture.completedFuture(value));
   }

   public operator fun set(key: Any, value: Any) {
      this.put((K)key, (V)value);
   }

   public suspend fun asMap(): Map<Any, Any> {
      var `$continuation`: Continuation;
      label33: {
         if (`$completion` is <unrepresentable>) {
            `$continuation` = `$completion` as <unrepresentable>;
            if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label33;
            }
         }

         `$continuation` = new ContinuationImpl(this, `$completion`) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            {
               super(`$completion`);
               this.this$0 = `this$0`;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = `$result`;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.asMap(this as Continuation<? super java.utilMap<K, ? extends V>>);
            }
         };
      }

      val `$result`: Any = `$continuation`.result;
      val var22: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `destination$iv$iv`: java.util.Map;
      var var9: java.util.Iterator;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val `$this$mapValues$iv`: ConcurrentMap = this.cache.asMap();
            `destination$iv$iv` = new LinkedHashMap(MapsKt.mapCapacity(`$this$mapValues$iv`.size()));
            var9 = `$this$mapValues$iv`.entrySet().iterator();
            break;
         case 1:
            val var18: Any = `$continuation`.L$3;
            val var17: java.util.Map = `$continuation`.L$2 as java.util.Map;
            var9 = `$continuation`.L$1 as java.util.Iterator;
            `destination$iv$iv` = `$continuation`.L$0 as java.util.Map;
            ResultKt.throwOnFailure(`$result`);
            var17.put(var18, `$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (var9.hasNext()) {
         val `element$iv$iv$iv`: Any = var9.next();
         val var10001: Any = (`element$iv$iv$iv` as Entry).getKey();
         val var16: Any = (`element$iv$iv$iv` as Entry).getValue();
         var var10000: CompletionStage = var16 as CompletionStage;
         `$continuation`.L$0 = `destination$iv$iv`;
         `$continuation`.L$1 = var9;
         `$continuation`.L$2 = `destination$iv$iv`;
         `$continuation`.L$3 = var10001;
         `$continuation`.label = 1;
         var10000 = (CompletionStage)FutureKt.await(var10000, `$continuation`);
         if (var10000 === var22) {
            return var22;
         }

         `destination$iv$iv`.put(var10001, var10000);
      }

      return `destination$iv$iv`;
   }

   public fun asDeferredMap(): Map<Any, Deferred<Any>> {
      var var10000: ConcurrentMap = this.cache.asMap();
      val `$this$mapValues$iv`: java.util.Map = var10000;
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap(MapsKt.mapCapacity(var10000.size()));

      val `$this$associateByTo$iv$iv$iv`: java.lang.Iterable;
      for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         val var10001: Any = (`element$iv$iv$iv` as Entry).getKey();
         var10000 = (ConcurrentMap)(`element$iv$iv$iv` as Entry).getValue();
         `destination$iv$iv`.put(var10001, FutureKt.asDeferred(var10000 as CompletionStage));
      }

      return `destination$iv$iv`;
   }

   public fun invalidate(key: Any) {
      this.cache.synchronous().invalidate(key);
   }

   public fun invalidateAll() {
      this.cache.synchronous().invalidateAll();
   }

   private suspend fun scope(): CoroutineScope {
      return if (this.useCallingContext) CoroutineScopeKt.CoroutineScope(`$completion`.getContext()) else this.defaultScope;
   }

   @JvmStatic
   fun `getAll$lambda$0`(`$scope`: CoroutineScope, `$compute`: Function2, k: java.util.Set, var3: Executor): CompletableFuture {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(
            `$scope`, null, null, (new Function2<CoroutineScope, Continuation<? super java.util.Map<K, ? extends V>>, Object>(`$compute`, k, null) {
               int label;

               {
                  super(2, `$completionx`);
                  this.$compute = `$compute`;
                  this.$k = `$k`;
               }

               public final Object invokeSuspend(Object $result) {
                  val var3: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  var var10000: Any;
                  switch (this.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        var10000 = this.$compute;
                        val var2: java.util.Set = this.$k;
                        val var10001: java.util.Set = CollectionsKt.toSet(var2);
                        this.label = 1;
                        var10000 = (Function2)var10000.invoke(var10001, this);
                        if (var10000 === var3) {
                           return var3;
                        }
                        break;
                     case 1:
                        ResultKt.throwOnFailure(`$result`);
                        var10000 = (Function2)`$result`;
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  return var10000;
               }

               public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                  return (new <anonymous constructor>(this.$compute, this.$k, `$completion`)) as Continuation<Unit>;
               }

               public final Object invoke(CoroutineScope p1, Continuation<? super java.util.Map<K, ? extends V>> p2) {
                  return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
               }
            }) as Function2, 3, null
         )
      );
   }

   @JvmStatic
   fun `get$lambda$1`(`$scope`: CoroutineScope, `$compute`: Function2, k: Any, var3: Executor): CompletableFuture {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(`$scope`, null, null, (new Function2<CoroutineScope, Continuation<? super V>, Object>(`$compute`, k, null) {
            int label;

            {
               super(2, `$completionx`);
               this.$compute = `$compute`;
               this.$k = (K)`$k`;
            }

            public final Object invokeSuspend(Object $result) {
               val var2: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               var var10000: Any;
               switch (this.label) {
                  case 0:
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = this.$compute;
                     val var10001: Any = this.$k;
                     this.label = 1;
                     var10000 = (Function2)var10000.invoke(var10001, this);
                     if (var10000 === var2) {
                        return var2;
                     }
                     break;
                  case 1:
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = (Function2)`$result`;
                     break;
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return var10000;
            }

            public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
               return (new <anonymous constructor>(this.$compute, this.$k, `$completion`)) as Continuation<Unit>;
            }

            public final Object invoke(CoroutineScope p1, Continuation<? super V> p2) {
               return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
            }
         }) as Function2, 3, null)
      );
   }

   @JvmStatic
   fun `getOrNull$lambda$2`(`$scope`: CoroutineScope, `$compute`: Function2, k: Any, var3: Executor): CompletableFuture {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(`$scope`, null, null, (new Function2<CoroutineScope, Continuation<? super V>, Object>(`$compute`, k, null) {
            int label;

            {
               super(2, `$completionx`);
               this.$compute = `$compute`;
               this.$k = (K)`$k`;
            }

            public final Object invokeSuspend(Object $result) {
               val var2: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               var var10000: Any;
               switch (this.label) {
                  case 0:
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = this.$compute;
                     val var10001: Any = this.$k;
                     this.label = 1;
                     var10000 = (Function2)var10000.invoke(var10001, this);
                     if (var10000 === var2) {
                        return var2;
                     }
                     break;
                  case 1:
                     ResultKt.throwOnFailure(`$result`);
                     var10000 = (Function2)`$result`;
                     break;
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return var10000;
            }

            public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
               return (new <anonymous constructor>(this.$compute, this.$k, `$completion`)) as Continuation<Unit>;
            }

            public final Object invoke(CoroutineScope p1, Continuation<? super V> p2) {
               return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
            }
         }) as Function2, 3, null)
      );
   }
}
