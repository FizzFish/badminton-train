package com.feysh.corax.cache.coroutines

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Expiry
import com.github.benmanes.caffeine.cache.RemovalCause
import com.github.benmanes.caffeine.cache.RemovalListener
import com.github.benmanes.caffeine.cache.Ticker
import com.github.benmanes.caffeine.cache.Weigher
import com.github.benmanes.caffeine.cache.stats.StatsCounter
import java.util.concurrent.Executor
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function4
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlin.time.DurationKt
import kotlin.time.DurationUnit
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.future.FutureKt

public class XCache<K, V> private constructor(config: Configuration<Any, Any>, defaultScope: Lazy<CoroutineScope>, caffeine: Caffeine<Any, Any>) {
   private final val config: Configuration<Any, Any>
   public final val defaultScope: Lazy<CoroutineScope>
   public final val caffeine: Caffeine<Any, Any>

   public final val useCallingContext: Boolean
      public final get() {
         return this.config.getUseCallingContext();
      }


   public final val stats: StatsCounter?
      public final get() {
         return this.config.getStatsCounter();
      }


   init {
      this.config = config;
      this.defaultScope = defaultScope;
      this.caffeine = caffeine;
   }

   public fun build(): Cache<Any, Any> {
      val var10000: Cache = this.caffeine.build();
      return var10000;
   }

   @SourceDebugExtension(["SMAP\nXCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 XCache.kt\ncom/feysh/corax/cache/coroutines/XCache$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,88:1\n1#2:89\n*E\n"])
   public companion object {
      public fun <K, V> cacheBuilder(configure: (Configuration<K, V>) -> Unit = XCache.Companion::cacheBuilder$lambda$0): XCache<K, V> {
         val c: Configuration = new Configuration(
            false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 524287, null
         );
         configure.invoke(c);
         val caffeine: Caffeine = Caffeine.newBuilder();
         val defaultScope: Lazy = LazyKt.lazy(XCache.Companion::cacheBuilder$lambda$1);
         var var10000: Function4 = c.getEvictionListener();
         if (var10000 != null) {
            caffeine.evictionListener(new RemovalListener(XCache.Companion::cacheBuilder$lambda$3$lambda$2) {
               {
                  this.function = function;
               }
            });
         }

         var10000 = c.getRemovalListener();
         if (var10000 != null) {
            caffeine.removalListener(new RemovalListener(XCache.Companion::cacheBuilder$lambda$5$lambda$4) {
               {
                  this.function = function;
               }
            });
         }

         val var45: Int = c.getInitialCapacity();
         if (var45 != null) {
            caffeine.initialCapacity(var45.intValue());
         }

         val var46: Function0 = c.getTicker();
         if (var46 != null) {
            val var15: Function0 = var46;
            caffeine.ticker(new Ticker(var15) {
               {
                  this.function = function;
               }
            });
         }

         val var47: java.lang.Long = c.getMaximumSize();
         if (var47 != null) {
            caffeine.maximumSize(var47.longValue());
         }

         val var48: java.lang.Long = c.getMaximumWeight();
         if (var48 != null) {
            caffeine.maximumWeight(var48.longValue());
         }

         val var49: Function2 = c.getWeigher();
         if (var49 != null) {
            val var18: Function2 = var49;
            caffeine.weigher(new Weigher(var18) {
               {
                  this.function = function;
               }
            });
         }

         val var50: Duration = c.getExpireAfterWrite-FghU774();
         if (var50 != null) {
            val var19: Long = var50.unbox-impl();
            val var10001: java.time.Duration = java.time.Duration.ofSeconds(
               Duration.getInWholeSeconds-impl(var19), (long)Duration.getNanosecondsComponent-impl(var19)
            );
            caffeine.expireAfterWrite(var10001);
         }

         val var51: Duration = c.getExpireAfterAccess-FghU774();
         if (var51 != null) {
            val var20: Long = var51.unbox-impl();
            val var57: java.time.Duration = java.time.Duration.ofSeconds(
               Duration.getInWholeSeconds-impl(var20), (long)Duration.getNanosecondsComponent-impl(var20)
            );
            caffeine.expireAfterAccess(var57);
         }

         val var52: Expiry = c.getExpireAfter();
         if (var52 != null) {
            caffeine.expireAfter(var52);
         }

         val var53: Duration = c.getRefreshAfterWrite-FghU774();
         if (var53 != null) {
            val var22: Long = var53.unbox-impl();
            val var58: java.time.Duration = java.time.Duration.ofSeconds(
               Duration.getInWholeSeconds-impl(var22), (long)Duration.getNanosecondsComponent-impl(var22)
            );
            caffeine.refreshAfterWrite(var58);
         }

         val var54: StatsCounter = c.getStatsCounter();
         if (var54 != null) {
            val var23: StatsCounter = var54;
            caffeine.recordStats(new Supplier(var23) {
               {
                  this.$counter = `$counter`;
               }

               public final StatsCounter get() {
                  return this.$counter;
               }
            });
         }

         if (c.getWeakKeys() == true) {
            caffeine.weakKeys();
         }

         if (c.getWeakValues() == true) {
            caffeine.weakValues();
         }

         if (c.getSoftValues() == true) {
            caffeine.softValues();
         }

         val var55: Scheduler = c.getScheduler();
         if (var55 != null) {
            val var24: Scheduler = var55;
            caffeine.scheduler(
               new com.github.benmanes.caffeine.cache.Scheduler(var24) {
                  {
                     this.$scheduler = `$scheduler`;
                  }

                  public final Future<?> schedule(Executor executor, Runnable command, long delay, TimeUnit unit) {
                     val var10000: Scheduler = this.$scheduler;
                     return FutureKt.asCompletableFuture(
                        var10000.schedule-SxA4cEA(
                           executor, <unrepresentable>::schedule$lambda$0, DurationKt.toDuration(unit.toNanos(delay), DurationUnit.NANOSECONDS)
                        )
                     );
                  }

                  private static final Unit schedule$lambda$0(Runnable $command) {
                     `$command`.run();
                     return Unit.INSTANCE;
                  }
               }
            );
         }

         val var56: com.github.benmanes.caffeine.cache.Scheduler = c.getCaffeineScheduler();
         if (var56 != null) {
            caffeine.scheduler(var56);
         }

         return new XCache<>(c, defaultScope, caffeine, null);
      }

      @JvmStatic
      fun `cacheBuilder$lambda$0`(var0: Configuration): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `cacheBuilder$lambda$1`(`$c`: Configuration): CoroutineScope {
         val var10000: CoroutineScope = `$c`.getScope();
         if (var10000 == null) {
            throw new IllegalStateException("not set scope".toString());
         } else {
            return var10000;
         }
      }

      @JvmStatic
      fun `cacheBuilder$lambda$3$lambda$2`(`$defaultScope`: Lazy, `$listener`: Function4, key: Any, value: Any, cause: RemovalCause): Unit {
         BuildersKt.launch$default(
            `$defaultScope`.getValue() as CoroutineScope,
            null,
            null,
            (new Function2<CoroutineScope, Continuation<? super Unit>, Object>(`$listener`, key, value, cause, null) {
               int label;

               {
                  super(2, `$completionx`);
                  this.$listener = `$listener`;
                  this.$key = (K)`$key`;
                  this.$value = (V)`$value`;
                  this.$cause = `$cause`;
               }

               public final Object invokeSuspend(Object $result) {
                  val var3: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch (this.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        val var10000: Function4 = this.$listener;
                        val var10001: Any = this.$key;
                        val var10002: Any = this.$value;
                        val var2: RemovalCause = this.$cause;
                        this.label = 1;
                        if (var10000.invoke(var10001, var10002, var2, this) === var3) {
                           return var3;
                        }
                        break;
                     case 1:
                        ResultKt.throwOnFailure(`$result`);
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  return Unit.INSTANCE;
               }

               public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                  return (new <anonymous constructor>(this.$listener, this.$key, this.$value, this.$cause, `$completion`)) as Continuation<Unit>;
               }

               public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                  return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
               }
            }) as Function2,
            3,
            null
         );
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `cacheBuilder$lambda$5$lambda$4`(`$defaultScope`: Lazy, `$listener`: Function4, key: Any, value: Any, cause: RemovalCause): Unit {
         BuildersKt.launch$default(
            `$defaultScope`.getValue() as CoroutineScope,
            null,
            null,
            (new Function2<CoroutineScope, Continuation<? super Unit>, Object>(`$listener`, key, value, cause, null) {
               int label;

               {
                  super(2, `$completionx`);
                  this.$listener = `$listener`;
                  this.$key = (K)`$key`;
                  this.$value = (V)`$value`;
                  this.$cause = `$cause`;
               }

               public final Object invokeSuspend(Object $result) {
                  val var3: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch (this.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        val var10000: Function4 = this.$listener;
                        val var10001: Any = this.$key;
                        val var10002: Any = this.$value;
                        val var2: RemovalCause = this.$cause;
                        this.label = 1;
                        if (var10000.invoke(var10001, var10002, var2, this) === var3) {
                           return var3;
                        }
                        break;
                     case 1:
                        ResultKt.throwOnFailure(`$result`);
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  return Unit.INSTANCE;
               }

               public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                  return (new <anonymous constructor>(this.$listener, this.$key, this.$value, this.$cause, `$completion`)) as Continuation<Unit>;
               }

               public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                  return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
               }
            }) as Function2,
            3,
            null
         );
         return Unit.INSTANCE;
      }
   }
}
