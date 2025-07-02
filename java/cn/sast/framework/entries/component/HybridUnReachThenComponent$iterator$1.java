package cn.sast.framework.entries.component;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import soot.SootMethod;

/* compiled from: HybridUnReachThenComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "HybridUnReachThenComponent.kt", l = {29}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.entries.component.HybridUnReachThenComponent$iterator$1")
@SourceDebugExtension({"SMAP\nHybridUnReachThenComponent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HybridUnReachThenComponent.kt\ncn/sast/framework/entries/component/HybridUnReachThenComponent$iterator$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,31:1\n1557#2:32\n1628#2,3:33\n*S KotlinDebug\n*F\n+ 1 HybridUnReachThenComponent.kt\ncn/sast/framework/entries/component/HybridUnReachThenComponent$iterator$1\n*L\n16#1:32\n16#1:33,3\n*E\n"})
/* loaded from: HybridUnReachThenComponent$iterator$1.class */
final class HybridUnReachThenComponent$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ HybridUnReachThenComponent this$0;
    final /* synthetic */ SootCtx $ctx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HybridUnReachThenComponent$iterator$1(HybridUnReachThenComponent $receiver, SootCtx $ctx, Continuation<? super HybridUnReachThenComponent$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$ctx = $ctx;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> hybridUnReachThenComponent$iterator$1 = new HybridUnReachThenComponent$iterator$1(this.this$0, this.$ctx, continuation);
        hybridUnReachThenComponent$iterator$1.L$0 = value;
        return hybridUnReachThenComponent$iterator$1;
    }

    public final Object invoke(FlowCollector<? super IEntryPointProvider.AnalyzeTask> flowCollector, Continuation<? super Unit> continuation) {
        return create(flowCollector, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                FlowCollector $this$flow = (FlowCollector) this.L$0;
                Set entries = this.this$0.getEntryMethods();
                SootCtx sootCtx = this.$ctx;
                Set $this$map$iv = entries;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    SootMethod it = (SootMethod) item$iv$iv;
                    destination$iv$iv.add(it.getSignature());
                }
                ComponentEntryProvider component = new ComponentEntryProvider(sootCtx, (List) destination$iv$iv);
                Flow iterator = FlowKt.flow(new HybridUnReachThenComponent$iterator$1$iterator$1(component, entries, null));
                this.label = 1;
                if (FlowKt.emitAll($this$flow, iterator, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
