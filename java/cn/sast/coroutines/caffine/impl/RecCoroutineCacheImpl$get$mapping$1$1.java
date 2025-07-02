package cn.sast.coroutines.caffine.impl;

import cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.coroutines.RecCoroutineCache;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [V] */
/* compiled from: RecCoroutineCacheImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "V", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "RecCoroutineCacheImpl.kt", l = {91}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"job"}, m = "invokeSuspend", c = "cn.sast.coroutines.caffine.impl.RecCoroutineCacheImpl$get$mapping$1$1")
/* loaded from: RecCoroutineCacheImpl$get$mapping$1$1.class */
final class RecCoroutineCacheImpl$get$mapping$1$1<V> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super V>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ RecCoroutineCacheImpl<K, V> this$0;
    final /* synthetic */ RecCoroutineCacheImpl.RecID $id;
    final /* synthetic */ Function3<RecCoroutineCache<K, V>, K, Continuation<? super V>, Object> $mappingFunction;
    final /* synthetic */ K $k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RecCoroutineCacheImpl$get$mapping$1$1(RecCoroutineCacheImpl<K, V> recCoroutineCacheImpl, RecCoroutineCacheImpl.RecID $id, Function3<? super RecCoroutineCache<K, V>, ? super K, ? super Continuation<? super V>, ? extends Object> function3, K k, Continuation<? super RecCoroutineCacheImpl$get$mapping$1$1> continuation) {
        super(2, continuation);
        this.this$0 = recCoroutineCacheImpl;
        this.$id = $id;
        this.$mappingFunction = function3;
        this.$k = k;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> recCoroutineCacheImpl$get$mapping$1$1 = new RecCoroutineCacheImpl$get$mapping$1$1<>(this.this$0, this.$id, this.$mappingFunction, this.$k, continuation);
        recCoroutineCacheImpl$get$mapping$1$1.L$0 = value;
        return recCoroutineCacheImpl$get$mapping$1$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super V> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invokeSuspend(Object $result) {
        CoroutineScope job;
        Object objInvoke;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$async = (CoroutineScope) this.L$0;
                job = $this$async;
                if (!Intrinsics.areEqual(this.this$0.recursiveId(job.getCoroutineContext()), this.$id)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                Function3<RecCoroutineCache<K, V>, K, Continuation<? super V>, Object> function3 = this.$mappingFunction;
                Object obj = this.this$0;
                K k = this.$k;
                this.L$0 = job;
                this.label = 1;
                objInvoke = function3.invoke(obj, k, this);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                job = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure($result);
                objInvoke = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Object it = objInvoke;
        RecCoroutineCacheImpl<K, V> recCoroutineCacheImpl = this.this$0;
        K k2 = this.$k;
        for (Object ref : (Object[]) ((RecCoroutineCacheImpl) recCoroutineCacheImpl).weakKeyAssociateByValue.invoke(it)) {
            WeakEntryHolder weakEntryHolder = ((RecCoroutineCacheImpl) recCoroutineCacheImpl).weakHolder;
            if (ref != null) {
                weakEntryHolder.put(k2, ref);
            }
        }
        ((RecCoroutineCacheImpl) recCoroutineCacheImpl).weakHolder.put(k2, job);
        return it;
    }
}
