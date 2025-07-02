package cn.sast.coroutines.caffine.impl;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.coroutines.RecCoroutineCache;
import com.feysh.corax.cache.coroutines.RecCoroutineLoadingCache;
import com.feysh.corax.cache.coroutines.XCache;
import com.github.benmanes.caffeine.cache.Cache;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RecCoroutineCacheImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b��\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004BÄ\u0001\u0012\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0007j\b\u0012\u0004\u0012\u00028\u0001`\b0\u0006\u0012\"\u0010\t\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0007j\b\u0012\u0004\u0012\u00028\u0001`\b0\n\u0012)\u0010\u000b\u001a%\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00100\f\u0012H\u0010\u0012\u001aD\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0013\u0012\u00118��¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0013¢\u0006\u0002\b\u0016¢\u0006\u0004\b\u0017\u0010\u0018J(\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007j\b\u0012\u0004\u0012\u00028\u0001`\u001b2\u0006\u0010\u0014\u001a\u00028��H\u0096@¢\u0006\u0002\u0010\u001cJ(\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007j\b\u0012\u0004\u0012\u00028\u0001`\u001b2\u0006\u0010\u0014\u001a\u00028��H\u0096@¢\u0006\u0002\u0010\u001cRR\u0010\u0012\u001aD\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0013\u0012\u00118��¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0013¢\u0006\u0002\b\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006\u001e"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineLoadingCacheImpl;", "K", "V", "Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl;", "Lcom/feysh/corax/cache/coroutines/RecCoroutineLoadingCache;", "xCache", "Lcom/feysh/corax/cache/coroutines/XCache;", "Lkotlinx/coroutines/Deferred;", "Lcn/sast/coroutines/caffine/impl/CacheValue;", "cache", "Lcom/github/benmanes/caffeine/cache/Cache;", "weakKeyAssociateBy", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "", "", "mappingFunction", "Lkotlin/Function3;", "key", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lcom/feysh/corax/cache/coroutines/XCache;Lcom/github/benmanes/caffeine/cache/Cache;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "get", "Lcom/feysh/corax/cache/coroutines/RecRes;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEntry", "corax-api"})
/* loaded from: RecCoroutineLoadingCacheImpl.class */
public final class RecCoroutineLoadingCacheImpl<K, V> extends RecCoroutineCacheImpl<K, V> implements RecCoroutineLoadingCache<K, V> {

    @NotNull
    private final Function3<RecCoroutineLoadingCache<K, V>, K, Continuation<? super V>, Object> mappingFunction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecCoroutineLoadingCacheImpl(@NotNull XCache<K, Deferred<V>> xCache, @NotNull Cache<K, Deferred<V>> cache, @NotNull Function1<? super V, Object[]> function1, @NotNull Function3<? super RecCoroutineLoadingCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3) {
        super(xCache, cache, function1);
        Intrinsics.checkNotNullParameter(xCache, "xCache");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(function1, "weakKeyAssociateBy");
        Intrinsics.checkNotNullParameter(function3, "mappingFunction");
        this.mappingFunction = function3;
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "V", "K", "Lcom/feysh/corax/cache/coroutines/RecCoroutineCache;", "it"})
    @DebugMetadata(f = "RecCoroutineCacheImpl.kt", l = {172}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.caffine.impl.RecCoroutineLoadingCacheImpl$get$2")
    /* renamed from: cn.sast.coroutines.caffine.impl.RecCoroutineLoadingCacheImpl$get$2, reason: invalid class name */
    /* loaded from: RecCoroutineLoadingCacheImpl$get$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<RecCoroutineCache<K, V>, K, Continuation<? super V>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RecCoroutineLoadingCacheImpl<K, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(RecCoroutineLoadingCacheImpl<K, V> recCoroutineLoadingCacheImpl, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.this$0 = recCoroutineLoadingCacheImpl;
        }

        public final Object invoke(RecCoroutineCache<K, V> recCoroutineCache, K k, Continuation<? super V> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = k;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
            return invoke((RecCoroutineCache<RecCoroutineCache<K, V>, V>) p1, (RecCoroutineCache<K, V>) p2, (Continuation) p3);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    Object it = this.L$0;
                    Function3 function3 = ((RecCoroutineLoadingCacheImpl) this.this$0).mappingFunction;
                    RecCoroutineLoadingCacheImpl<K, V> recCoroutineLoadingCacheImpl = this.this$0;
                    this.label = 1;
                    Object objInvoke = function3.invoke(recCoroutineLoadingCacheImpl, it, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objInvoke;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Nullable
    public Object get(K k, @NotNull Continuation<? super Deferred<? extends V>> continuation) {
        return get(k, new AnonymousClass2(this, null), continuation);
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "V", "K", "Lcom/feysh/corax/cache/coroutines/RecCoroutineCache;", "it"})
    @DebugMetadata(f = "RecCoroutineCacheImpl.kt", l = {178}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.coroutines.caffine.impl.RecCoroutineLoadingCacheImpl$getEntry$2")
    /* renamed from: cn.sast.coroutines.caffine.impl.RecCoroutineLoadingCacheImpl$getEntry$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: RecCoroutineLoadingCacheImpl$getEntry$2.class */
    static final class C00092 extends SuspendLambda implements Function3<RecCoroutineCache<K, V>, K, Continuation<? super V>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ RecCoroutineLoadingCacheImpl<K, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00092(RecCoroutineLoadingCacheImpl<K, V> recCoroutineLoadingCacheImpl, Continuation<? super C00092> continuation) {
            super(3, continuation);
            this.this$0 = recCoroutineLoadingCacheImpl;
        }

        public final Object invoke(RecCoroutineCache<K, V> recCoroutineCache, K k, Continuation<? super V> continuation) {
            C00092 c00092 = new C00092(this.this$0, continuation);
            c00092.L$0 = k;
            return c00092.invokeSuspend(Unit.INSTANCE);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
            return invoke((RecCoroutineCache<RecCoroutineCache<K, V>, V>) p1, (RecCoroutineCache<K, V>) p2, (Continuation) p3);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    Object it = this.L$0;
                    Function3 function3 = ((RecCoroutineLoadingCacheImpl) this.this$0).mappingFunction;
                    RecCoroutineLoadingCacheImpl<K, V> recCoroutineLoadingCacheImpl = this.this$0;
                    this.label = 1;
                    Object objInvoke = function3.invoke(recCoroutineLoadingCacheImpl, it, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objInvoke;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Nullable
    public Object getEntry(K k, @NotNull Continuation<? super Deferred<? extends V>> continuation) {
        return getEntry(k, new C00092(this, null), continuation);
    }
}
