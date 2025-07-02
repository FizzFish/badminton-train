package cn.sast.framework.engine;

import cn.sast.dataflow.analysis.deadcode.DeadCode;
import cn.sast.dataflow.analysis.deadstore.DeadStore;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import soot.SootMethod;
import soot.toolkits.graph.DirectedBodyGraph;
import soot.toolkits.graph.ExceptionalUnitGraphFactory;

/* compiled from: BuiltinAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "BuiltinAnalysis.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.BuiltinAnalysis$analyzeInScene$2$1")
/* loaded from: BuiltinAnalysis$analyzeInScene$2$1.class */
final class BuiltinAnalysis$analyzeInScene$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Collection<SootMethod> $targets;
    final /* synthetic */ DeadStore $deadStore;
    final /* synthetic */ DeadCode $deadCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BuiltinAnalysis$analyzeInScene$2$1(Collection<? extends SootMethod> collection, DeadStore $deadStore, DeadCode $deadCode, Continuation<? super BuiltinAnalysis$analyzeInScene$2$1> continuation) {
        super(2, continuation);
        this.$targets = collection;
        this.$deadStore = $deadStore;
        this.$deadCode = $deadCode;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> builtinAnalysis$analyzeInScene$2$1 = new BuiltinAnalysis$analyzeInScene$2$1(this.$targets, this.$deadStore, this.$deadCode, continuation);
        builtinAnalysis$analyzeInScene$2$1.L$0 = value;
        return builtinAnalysis$analyzeInScene$2$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                for (SootMethod method : this.$targets) {
                    BuildersKt.launch$default($this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(method, this.$deadStore, this.$deadCode, null), 3, (Object) null);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BuiltinAnalysis.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.BuiltinAnalysis$analyzeInScene$2$1$1")
    /* renamed from: cn.sast.framework.engine.BuiltinAnalysis$analyzeInScene$2$1$1, reason: invalid class name */
    /* loaded from: BuiltinAnalysis$analyzeInScene$2$1$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ SootMethod $method;
        final /* synthetic */ DeadStore $deadStore;
        final /* synthetic */ DeadCode $deadCode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SootMethod $method, DeadStore $deadStore, DeadCode $deadCode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$method = $method;
            this.$deadStore = $deadStore;
            this.$deadCode = $deadCode;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass1(this.$method, this.$deadStore, this.$deadCode, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    try {
                        DirectedBodyGraph<soot.Unit> directedBodyGraphCreateExceptionalUnitGraph = ExceptionalUnitGraphFactory.createExceptionalUnitGraph(this.$method.getActiveBody());
                        DeadStore deadStore = this.$deadStore;
                        Intrinsics.checkNotNull(directedBodyGraphCreateExceptionalUnitGraph);
                        deadStore.analyze(directedBodyGraphCreateExceptionalUnitGraph);
                        this.$deadCode.analyze(directedBodyGraphCreateExceptionalUnitGraph);
                    } catch (Exception e) {
                        SootMethod sootMethod = this.$method;
                        BuiltinAnalysis.logger.error(e, () -> {
                            return invokeSuspend$lambda$0(r2);
                        });
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        private static final Object invokeSuspend$lambda$0(SootMethod $method) {
            return "When analyzing method: " + $method + ", please file this bug to us";
        }
    }
}
