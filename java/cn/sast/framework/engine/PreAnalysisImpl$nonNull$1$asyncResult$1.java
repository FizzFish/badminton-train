package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.PreAnalysisApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\f\u0012\b\u0012\u0006\b\u0002\u0018\u00018��0\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisImpl.kt", l = {124}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$nonNull$1$asyncResult$1")
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$nonNull$1$asyncResult$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,760:1\n1611#2,9:761\n1863#2:770\n1864#2:772\n1620#2:773\n1#3:771\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$nonNull$1$asyncResult$1\n*L\n124#1:761,9\n124#1:770\n124#1:772\n124#1:773\n124#1:771\n*E\n"})
/* loaded from: PreAnalysisImpl$nonNull$1$asyncResult$1.class */
final class PreAnalysisImpl$nonNull$1$asyncResult$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends T>>, Object> {
    int label;
    final /* synthetic */ PreAnalysisApi.Result<T> $this_nonNull;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisImpl$nonNull$1$asyncResult$1(PreAnalysisApi.Result<T> result, Continuation<? super PreAnalysisImpl$nonNull$1$asyncResult$1> continuation) {
        super(2, continuation);
        this.$this_nonNull = result;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new PreAnalysisImpl$nonNull$1$asyncResult$1<>(this.$this_nonNull, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super List<? extends T>> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object objAwait;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                objAwait = this.$this_nonNull.await((Continuation) this);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                ResultKt.throwOnFailure($result);
                objAwait = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable $this$mapNotNull$iv = (Iterable) objAwait;
        Collection destination$iv$iv = new ArrayList();
        for (T t : $this$mapNotNull$iv) {
            if (t != null) {
                destination$iv$iv.add(t);
            }
        }
        return (List) destination$iv$iv;
    }
}
