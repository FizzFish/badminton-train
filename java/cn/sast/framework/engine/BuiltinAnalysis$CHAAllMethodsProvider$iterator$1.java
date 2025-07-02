package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.CgAlgorithmProvider;
import cn.sast.framework.SootCtx;
import cn.sast.framework.engine.BuiltinAnalysis;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import soot.SootClass;
import soot.SootMethod;
import soot.options.Options;

/* compiled from: BuiltinAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "BuiltinAnalysis.kt", l = {143}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.BuiltinAnalysis$CHA-AllMethodsProvider$iterator$1")
/* renamed from: cn.sast.framework.engine.BuiltinAnalysis$CHA-AllMethodsProvider$iterator$1, reason: invalid class name */
/* loaded from: BuiltinAnalysis$CHA-AllMethodsProvider$iterator$1.class */
final class BuiltinAnalysis$CHAAllMethodsProvider$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ BuiltinAnalysis.CHAAllMethodsProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BuiltinAnalysis$CHAAllMethodsProvider$iterator$1(BuiltinAnalysis.CHAAllMethodsProvider $receiver, Continuation<? super BuiltinAnalysis$CHAAllMethodsProvider$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> builtinAnalysis$CHAAllMethodsProvider$iterator$1 = new BuiltinAnalysis$CHAAllMethodsProvider$iterator$1(this.this$0, continuation);
        builtinAnalysis$CHAAllMethodsProvider$iterator$1.L$0 = value;
        return builtinAnalysis$CHAAllMethodsProvider$iterator$1;
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
                if ($this$flow.emit(new IEntryPointProvider.AnalyzeTask(this.this$0) { // from class: cn.sast.framework.engine.BuiltinAnalysis$CHA-AllMethodsProvider$iterator$1.1
                    private final Set<SootMethod> entries;
                    private final Void components;
                    private final String name;

                    {
                        Iterable $this$filter$iv = $receiver.getClasses();
                        Collection destination$iv$iv = new ArrayList();
                        for (Object element$iv$iv : $this$filter$iv) {
                            SootClass it = (SootClass) element$iv$iv;
                            if (it.isInScene()) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                        }
                        Iterable<SootClass> $this$flatMap$iv = (List) destination$iv$iv;
                        Collection destination$iv$iv2 = new ArrayList();
                        for (SootClass it2 : $this$flatMap$iv) {
                            Iterable methods = it2.getMethods();
                            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
                            Iterable list$iv$iv = methods;
                            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
                        }
                        this.entries = CollectionsKt.toSet((List) destination$iv$iv2);
                        this.name = "(entries size: " + getEntries().size() + ")";
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getMethodsMustAnalyze() {
                        return IEntryPointProvider.AnalyzeTask.DefaultImpls.getMethodsMustAnalyze(this);
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getAdditionalEntries() {
                        return IEntryPointProvider.AnalyzeTask.DefaultImpls.getAdditionalEntries(this);
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    /* renamed from: getComponents, reason: collision with other method in class */
                    public /* bridge */ /* synthetic */ Set mo276getComponents() {
                        return (Set) getComponents();
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public Set<SootMethod> getEntries() {
                        return this.entries;
                    }

                    public Void getComponents() {
                        return this.components;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public String getName() {
                        return this.name;
                    }

                    @Override // cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask
                    public void needConstructCallGraph(SootCtx sootCtx) {
                        Intrinsics.checkNotNullParameter(sootCtx, "sootCtx");
                        sootCtx.releaseCallGraph();
                        Options options = Options.v();
                        MainConfig mainConfig = sootCtx.getMainConfig();
                        BuiltinAnalysis.logger.info(() -> {
                            return needConstructCallGraph$lambda$2(r1);
                        });
                        Intrinsics.checkNotNull(options);
                        CgAlgorithmProvider cgAlgorithmProvider = sootCtx.configureCallGraph(options, mainConfig.getCallGraphAlgorithmBuiltIn(), true, false);
                        sootCtx.constructCallGraph(cgAlgorithmProvider, true, false);
                        sootCtx.configureCallGraph(options);
                    }

                    private static final Object needConstructCallGraph$lambda$2(MainConfig $mainConfig) {
                        return "Constructing call graph for BuiltinAnalysis with algorithm: " + $mainConfig.getCallGraphAlgorithmBuiltIn() + "...";
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
