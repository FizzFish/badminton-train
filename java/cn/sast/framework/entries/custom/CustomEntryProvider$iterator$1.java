package cn.sast.framework.entries.custom;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: CustomEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "CustomEntryProvider.kt", l = {23}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.entries.custom.CustomEntryProvider$iterator$1")
/* loaded from: CustomEntryProvider$iterator$1.class */
final class CustomEntryProvider$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ CustomEntryProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CustomEntryProvider$iterator$1(CustomEntryProvider $receiver, Continuation<? super CustomEntryProvider$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> customEntryProvider$iterator$1 = new CustomEntryProvider$iterator$1(this.this$0, continuation);
        customEntryProvider$iterator$1.L$0 = value;
        return customEntryProvider$iterator$1;
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
                this.label = 1;
                if ($this$flow.emit(new IEntryPointProvider.AnalyzeTask(this.this$0) { // from class: cn.sast.framework.entries.custom.CustomEntryProvider$iterator$1.1
                    private final Set<SootMethod> entries;
                    private final Set<SootClass> components;

                    {
                        this.entries = $receiver.getMethod();
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getAdditionalEntries() {
                        return IEntryPointProvider.AnalyzeTask.DefaultImpls.getAdditionalEntries(this);
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getEntries() {
                        return this.entries;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getMethodsMustAnalyze() {
                        return getEntries();
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    /* renamed from: getComponents */
                    public Set<SootClass> mo276getComponents() {
                        return this.components;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public String getName() {
                        return "(entries size: " + getEntries().size() + ")";
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public void needConstructCallGraph(SootCtx sootCtx) {
                        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
                        sootCtx.constructCallGraph();
                    }
                }, (Continuation) this) == coroutine_suspended) {
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
