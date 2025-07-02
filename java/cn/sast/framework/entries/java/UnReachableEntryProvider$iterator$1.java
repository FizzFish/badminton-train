package cn.sast.framework.entries.java;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.LinkedHashSet;
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
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: UnReachableEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "UnReachableEntryProvider.kt", l = {67}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.entries.java.UnReachableEntryProvider$iterator$1")
@SourceDebugExtension({"SMAP\nUnReachableEntryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnReachableEntryProvider.kt\ncn/sast/framework/entries/java/UnReachableEntryProvider$iterator$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,79:1\n865#2,2:80\n*S KotlinDebug\n*F\n+ 1 UnReachableEntryProvider.kt\ncn/sast/framework/entries/java/UnReachableEntryProvider$iterator$1\n*L\n66#1:80,2\n*E\n"})
/* loaded from: UnReachableEntryProvider$iterator$1.class */
final class UnReachableEntryProvider$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ UnReachableEntryProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnReachableEntryProvider$iterator$1(UnReachableEntryProvider $receiver, Continuation<? super UnReachableEntryProvider$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> unReachableEntryProvider$iterator$1 = new UnReachableEntryProvider$iterator$1(this.this$0, continuation);
        unReachableEntryProvider$iterator$1.L$0 = value;
        return unReachableEntryProvider$iterator$1;
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
                Iterable $this$filterTo$iv = this.this$0.getEntryMethods();
                Collection destination$iv = (Set) new LinkedHashSet();
                UnReachableEntryProvider unReachableEntryProvider = this.this$0;
                for (Object element$iv : $this$filterTo$iv) {
                    SootMethod it = (SootMethod) element$iv;
                    if (!unReachableEntryProvider.getExclude().contains(it.getSignature())) {
                        destination$iv.add(element$iv);
                    }
                }
                Set methods = (Set) destination$iv;
                this.label = 1;
                if ($this$flow.emit(new IEntryPointProvider.AnalyzeTask(methods, this.this$0) { // from class: cn.sast.framework.entries.java.UnReachableEntryProvider$iterator$1.1
                    private final Set<SootMethod> entries;
                    private final Set<SootClass> components;
                    private final String name;
                    final /* synthetic */ UnReachableEntryProvider this$0;

                    {
                        this.this$0 = $receiver;
                        this.entries = methods;
                        this.name = "(entries size: " + methods.size() + ")";
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
                        Iterable $this$filterTo$iv2 = IEntryPointProvider.AnalyzeTask.DefaultImpls.getMethodsMustAnalyze(this);
                        Collection destination$iv2 = (Set) new LinkedHashSet();
                        UnReachableEntryProvider unReachableEntryProvider2 = this.this$0;
                        for (Object element$iv2 : $this$filterTo$iv2) {
                            SootMethod it2 = (SootMethod) element$iv2;
                            if (!unReachableEntryProvider2.getExclude().contains(it2.getSignature())) {
                                destination$iv2.add(element$iv2);
                            }
                        }
                        return (Set) destination$iv2;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    /* renamed from: getComponents */
                    public Set<SootClass> mo276getComponents() {
                        return this.components;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public String getName() {
                        return this.name;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public void needConstructCallGraph(SootCtx sootCtx) {
                        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
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
