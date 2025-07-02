package cn.sast.coroutines.caffine.impl;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.graph.NoBackEdgeDirectGraph;
import com.feysh.corax.cache.coroutines.RecCoroutineCache;
import com.feysh.corax.cache.coroutines.XCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Interner;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RecCoroutineCacheImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0010\u0018�� B*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0004?@ABBz\u0012\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00070\u0005\u0012\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00070\t\u0012)\u0010\n\u001a%\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f0\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\f\u0010$\u001a\u00020\u0010*\u00020%H\u0002Jr\u0010(\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`)2\u0006\u0010*\u001a\u00028��2H\u0010+\u001aD\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0013\u0012\u00118��¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\u0006\u0012\u0004\u0018\u00010\u00100,¢\u0006\u0002\b.H\u0096@¢\u0006\u0002\u0010/J\u0081\u0001\u0010(\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`)2\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u0002022\u0006\u0010*\u001a\u00028��2H\u0010+\u001aD\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0013\u0012\u00118��¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\u0006\u0012\u0004\u0018\u00010\u00100,¢\u0006\u0002\b.H\u0002¢\u0006\u0002\u00103Jr\u00104\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`)2\u0006\u0010*\u001a\u00028��2H\u0010+\u001aD\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0013\u0012\u00118��¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\u0006\u0012\u0004\u0018\u00010\u00100,¢\u0006\u0002\b.H\u0096@¢\u0006\u0002\u0010/J\b\u00105\u001a\u000206H\u0016J\b\u0010;\u001a\u00020<H\u0016J\u000e\u0010=\u001a\u00020'H\u0096@¢\u0006\u0002\u0010>R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00070\u0005X\u0082\u0004¢\u0006\u0002\n��R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00070\tX\u0082\u0004¢\u0006\u0002\n��R1\u0010\n\u001a%\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n��R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00028��\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00070\tX\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0\u001dX\u0082\u0004¢\u0006\b\n��\u0012\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100!X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00100#X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n��R\u0014\u00107\u001a\u0002088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:¨\u0006C"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl;", "K", "V", "Lcom/feysh/corax/cache/coroutines/RecCoroutineCache;", "xCache", "Lcom/feysh/corax/cache/coroutines/XCache;", "Lkotlinx/coroutines/Deferred;", "Lcn/sast/coroutines/caffine/impl/CacheValue;", "cache", "Lcom/github/benmanes/caffeine/cache/Cache;", "weakKeyAssociateByValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "", "", "<init>", "(Lcom/feysh/corax/cache/coroutines/XCache;Lcom/github/benmanes/caffeine/cache/Cache;Lkotlin/jvm/functions/Function1;)V", "lruCache", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "cacheStats", "Lcom/github/benmanes/caffeine/cache/stats/CacheStats;", "getCacheStats", "()Lcom/github/benmanes/caffeine/cache/stats/CacheStats;", "interner", "Lcom/github/benmanes/caffeine/cache/Interner;", "getInterner$annotations", "()V", "noBackEdgeDirectGraph", "Lcn/sast/graph/NoBackEdgeDirectGraph;", "weakHolder", "Lcn/sast/coroutines/caffine/impl/WeakEntryHolder;", "recursiveId", "Lkotlin/coroutines/CoroutineContext;", "_x", "", "get", "Lcom/feysh/corax/cache/coroutines/RecRes;", "key", "mappingFunction", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "src", "parentJob", "Lkotlinx/coroutines/Job;", "(Ljava/lang/Object;Lkotlinx/coroutines/Job;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Deferred;", "getEntry", "validateAfterFinished", "", "size", "", "getSize", "()J", "cleanUp", "", "getPredSize", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RecursiveContext", "RecID", "EntryRecID", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nRecCoroutineCacheImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RecCoroutineCacheImpl.kt\ncn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl\n+ 2 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,181:1\n326#2:182\n*S KotlinDebug\n*F\n+ 1 RecCoroutineCacheImpl.kt\ncn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl\n*L\n154#1:182\n*E\n"})
/* loaded from: RecCoroutineCacheImpl.class */
public class RecCoroutineCacheImpl<K, V> implements RecCoroutineCache<K, V> {

    @NotNull
    private final XCache<K, Deferred<V>> xCache;

    @NotNull
    private final Cache<K, Deferred<V>> cache;

    @NotNull
    private final Function1<V, Object[]> weakKeyAssociateByValue;

    @NotNull
    private final Cache<K, Deferred<V>> lruCache;

    @NotNull
    private final CoroutineScope scope;

    @NotNull
    private final Interner<K> interner;

    @NotNull
    private final NoBackEdgeDirectGraph<Object> noBackEdgeDirectGraph;

    @NotNull
    private final WeakEntryHolder<K, Object> weakHolder;
    private int _x;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(RecCoroutineCacheImpl::logger$lambda$4);

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "RecCoroutineCacheImpl.kt", l = {69}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"$this", "key", "mappingFunction", "parentJob", "src"}, m = "get$suspendImpl", c = "cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl")
    /* renamed from: cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl$get$1, reason: invalid class name */
    /* loaded from: RecCoroutineCacheImpl$get$1.class */
    static final class AnonymousClass1<K, V> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        final /* synthetic */ RecCoroutineCacheImpl<K, V> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RecCoroutineCacheImpl<K, V> recCoroutineCacheImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = recCoroutineCacheImpl;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return RecCoroutineCacheImpl.get$suspendImpl(this.this$0, null, null, (Continuation) this);
        }
    }

    private static /* synthetic */ void getInterner$annotations() {
    }

    @Nullable
    public Object get(K k, @NotNull Function3<? super RecCoroutineCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3, @NotNull Continuation<? super Deferred<? extends V>> continuation) {
        return get$suspendImpl(this, k, function3, continuation);
    }

    @Nullable
    public Object getEntry(K k, @NotNull Function3<? super RecCoroutineCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3, @NotNull Continuation<? super Deferred<? extends V>> continuation) {
        return getEntry$suspendImpl(this, k, function3, continuation);
    }

    @Nullable
    public Object getPredSize(@NotNull Continuation<? super Integer> continuation) {
        return getPredSize$suspendImpl(this, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RecCoroutineCacheImpl(@NotNull XCache<K, Deferred<V>> xCache, @NotNull Cache<K, Deferred<V>> cache, @NotNull Function1<? super V, Object[]> function1) {
        Intrinsics.checkNotNullParameter(xCache, "xCache");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(function1, "weakKeyAssociateByValue");
        this.xCache = xCache;
        this.cache = cache;
        this.weakKeyAssociateByValue = function1;
        Caffeine caffeine = Caffeine.newBuilder();
        this.lruCache = caffeine.initialCapacity(2000).maximumSize(10000L).build();
        this.scope = (CoroutineScope) this.xCache.getDefaultScope().getValue();
        Interner<K> internerNewWeakInterner = Interner.newWeakInterner();
        Intrinsics.checkNotNullExpressionValue(internerNewWeakInterner, "newWeakInterner(...)");
        this.interner = internerNewWeakInterner;
        this.noBackEdgeDirectGraph = new NoBackEdgeDirectGraph<>();
        this.weakHolder = new WeakEntryHolder<>();
    }

    @NotNull
    public final CoroutineScope getScope() {
        return this.scope;
    }

    @NotNull
    public CacheStats getCacheStats() {
        CacheStats cacheStatsStats = this.cache.stats();
        Intrinsics.checkNotNullExpressionValue(cacheStatsStats, "stats(...)");
        return cacheStatsStats;
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0006\u0018�� \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$RecursiveContext;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "<init>", "(Ljava/lang/Object;)V", "getId", "()Ljava/lang/Object;", "Key", "corax-api"})
    /* loaded from: RecCoroutineCacheImpl$RecursiveContext.class */
    public static final class RecursiveContext extends AbstractCoroutineContextElement {

        @NotNull
        public static final Key Key = new Key(null);

        @NotNull
        private final Object id;

        /* compiled from: RecCoroutineCacheImpl.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$RecursiveContext$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$RecursiveContext;", "<init>", "()V", "corax-api"})
        /* loaded from: RecCoroutineCacheImpl$RecursiveContext$Key.class */
        public static final class Key implements CoroutineContext.Key<RecursiveContext> {
            public /* synthetic */ Key(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Key() {
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RecursiveContext(@NotNull Object id) {
            super(Key);
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
        }

        @NotNull
        public final Object getId() {
            return this.id;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recursiveId(CoroutineContext $this$recursiveId) {
        RecursiveContext recursiveContext = $this$recursiveId.get(RecursiveContext.Key);
        if (recursiveContext != null) {
            Object id = recursiveContext.getId();
            if (id != null) {
                return id;
            }
        }
        throw new IllegalStateException("current coroutine context has no RecursiveContext".toString());
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0016\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$RecID;", "", "<init>", "()V", "toString", "", "corax-api"})
    /* loaded from: RecCoroutineCacheImpl$RecID.class */
    public static class RecID {
        @NotNull
        public String toString() {
            return "RecID-" + System.identityHashCode(this);
        }
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0016\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$EntryRecID;", "Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$RecID;", "<init>", "()V", "toString", "", "corax-api"})
    /* loaded from: RecCoroutineCacheImpl$EntryRecID.class */
    public static class EntryRecID extends RecID {
        @Override // cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl.RecID
        @NotNull
        public String toString() {
            return "Entry-" + super.toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <K, V> java.lang.Object get$suspendImpl(cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl<K, V> r6, K r7, kotlin.jvm.functions.Function3<? super com.feysh.corax.cache.coroutines.RecCoroutineCache<K, V>, ? super K, ? super kotlin.coroutines.Continuation<? super V>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlinx.coroutines.Deferred<? extends V>> r9) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl.get$suspendImpl(cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Deferred<V> get(Object src, Job parentJob, K k, Function3<? super RecCoroutineCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3) {
        Object canonicalKey = this.interner.intern(k);
        Ref.BooleanRef mapped = new Ref.BooleanRef();
        final Function1 mapping = (v5) -> {
            return get$lambda$0(r0, r1, r2, r3, r4, v5);
        };
        Ref.ObjectRef deferred = new Ref.ObjectRef();
        deferred.element = this.cache.get(canonicalKey, new Function(mapping) { // from class: cn.sast.coroutines.caffine.impl.RecCoroutineCacheImplKt$sam$java_util_function_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(mapping, "function");
                this.function = mapping;
            }

            @Override // java.util.function.Function
            public final /* synthetic */ Object apply(Object p0) {
                return this.function.invoke(p0);
            }
        });
        if (((Deferred) deferred.element).isCancelled() && !parentJob.isCancelled()) {
            mapped.element = false;
            deferred.element = this.cache.get(canonicalKey, new Function(mapping) { // from class: cn.sast.coroutines.caffine.impl.RecCoroutineCacheImplKt$sam$java_util_function_Function$0
                private final /* synthetic */ Function1 function;

                {
                    Intrinsics.checkNotNullParameter(mapping, "function");
                    this.function = mapping;
                }

                @Override // java.util.function.Function
                public final /* synthetic */ Object apply(Object p0) {
                    return this.function.invoke(p0);
                }
            });
        }
        Cache<K, Deferred<V>> cache = this.lruCache;
        final Function1 function1 = (v1) -> {
            return get$lambda$1(r2, v1);
        };
        Object obj = deferred.element;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineScope");
        Object tgt = recursiveId(((CoroutineScope) obj).getCoroutineContext());
        if (!mapped.element) {
            if (((Job) deferred.element).isCompleted()) {
                return (Deferred) deferred.element;
            }
            if (!this.noBackEdgeDirectGraph.addEdgeSynchronized(src, tgt)) {
                return null;
            }
        }
        Function1 completionHandler = (v5) -> {
            return get$lambda$2(r0, r1, r2, r3, r4, v5);
        };
        ((Job) deferred.element).invokeOnCompletion(completionHandler);
        return (Deferred) deferred.element;
    }

    private static final Deferred get$lambda$0(Ref.BooleanRef $mapped, RecCoroutineCacheImpl this$0, Object $src, Job $parentJob, Function3 $mappingFunction, Object k) {
        if (!(!$mapped.element)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        $mapped.element = true;
        RecID id = new RecID();
        if (this$0.noBackEdgeDirectGraph.addEdgeSynchronized($src, id)) {
            return BuildersKt.async(this$0.scope, new RecursiveContext(id).plus((CoroutineContext) $parentJob), CoroutineStart.DEFAULT, new RecCoroutineCacheImpl$get$mapping$1$1(this$0, id, $mappingFunction, k, null));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final Deferred get$lambda$1(Ref.ObjectRef $deferred, Object it) {
        return (Deferred) $deferred.element;
    }

    private static final Unit get$lambda$2(RecCoroutineCacheImpl this$0, Ref.ObjectRef $deferred, Object $canonicalKey, Object $src, Object $tgt, Throwable cause) {
        this$0._x += System.identityHashCode($deferred.element) + System.identityHashCode($canonicalKey);
        this$0.noBackEdgeDirectGraph.removeEdgeSynchronized($src, $tgt);
        return Unit.INSTANCE;
    }

    static /* synthetic */ <K, V> Object getEntry$suspendImpl(RecCoroutineCacheImpl<K, V> recCoroutineCacheImpl, K k, Function3<? super RecCoroutineCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super Deferred<? extends V>> continuation) {
        return recCoroutineCacheImpl.get(new EntryRecID(), JobKt.getJob(continuation.getContext()), k, function3);
    }

    public boolean validateAfterFinished() {
        if (!this.noBackEdgeDirectGraph.isComplete()) {
            logger.error(() -> {
                return validateAfterFinished$lambda$3(r1);
            });
            return false;
        }
        return true;
    }

    private static final Object validateAfterFinished$lambda$3(RecCoroutineCacheImpl this$0) {
        return this$0.noBackEdgeDirectGraph + " is not complete";
    }

    public long getSize() {
        return this.cache.estimatedSize();
    }

    public void cleanUp() {
        this.cache.cleanUp();
        this.noBackEdgeDirectGraph.cleanUp();
        this.weakHolder.clean();
        this.lruCache.cleanUp();
    }

    static /* synthetic */ <K, V> Object getPredSize$suspendImpl(RecCoroutineCacheImpl<K, V> recCoroutineCacheImpl, Continuation<? super Integer> continuation) {
        Object src = recCoroutineCacheImpl.recursiveId(continuation.getContext());
        return Boxing.boxInt(((RecCoroutineCacheImpl) recCoroutineCacheImpl).noBackEdgeDirectGraph.getPredSize(src));
    }

    /* compiled from: RecCoroutineCacheImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/coroutines/caffine/impl/RecCoroutineCacheImpl$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-api"})
    /* loaded from: RecCoroutineCacheImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }
}
