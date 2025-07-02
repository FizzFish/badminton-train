package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.report.BugPathEvent;
import cn.sast.dataflow.interprocedural.check.PathGeneratorImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.graph.HashMutableDirectedGraph;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Lcn/sast/api/report/BugPathEvent;"})
@DebugMetadata(f = "PathGenerator.kt", l = {199}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this$sequence"}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.PathGeneratorImpl$Companion$EventGenerator$diagnostics$1")
/* loaded from: PathGeneratorImpl$Companion$EventGenerator$diagnostics$1.class */
final class PathGeneratorImpl$Companion$EventGenerator$diagnostics$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends BugPathEvent>>, Continuation<? super Unit>, Object> {
    Object L$1;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ PathGeneratorImpl.Companion.EventGenerator this$0;
    final /* synthetic */ InvokeEdgePath $this_diagnostics;
    final /* synthetic */ int $deep;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathGeneratorImpl$Companion$EventGenerator$diagnostics$1(PathGeneratorImpl.Companion.EventGenerator $receiver, InvokeEdgePath $receiver2, int $deep, Continuation<? super PathGeneratorImpl$Companion$EventGenerator$diagnostics$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$this_diagnostics = $receiver2;
        this.$deep = $deep;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> pathGeneratorImpl$Companion$EventGenerator$diagnostics$1 = new PathGeneratorImpl$Companion$EventGenerator$diagnostics$1(this.this$0, this.$this_diagnostics, this.$deep, continuation);
        pathGeneratorImpl$Companion$EventGenerator$diagnostics$1.L$0 = value;
        return pathGeneratorImpl$Companion$EventGenerator$diagnostics$1;
    }

    public final Object invoke(SequenceScope<? super List<BugPathEvent>> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Iterator it;
        SequenceScope $this$sequence;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                $this$sequence = (SequenceScope) this.L$0;
                if (this.this$0.getInvokeCount() > 500) {
                    PathGeneratorImpl.Companion.EventGenerator.Companion.getLogger().error(PathGeneratorImpl$Companion$EventGenerator$diagnostics$1::invokeSuspend$lambda$0);
                    return Unit.INSTANCE;
                }
                PathGenerator generator = PathGeneratorImpl.Companion.getPathGenerator();
                HashMutableDirectedGraph directGraph = new HashMutableDirectedGraph();
                Set heads = generator.getHeads(this.$this_diagnostics.getPath(), directGraph);
                Map entryToPath = generator.flush((DirectedGraph) directGraph, heads);
                it = CollectionsKt.toSet(entryToPath.values()).iterator();
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                it = (Iterator) this.L$1;
                $this$sequence = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            List events = (List) it.next();
            if (!(CollectionsKt.first(events) instanceof LiteralPath)) {
                this.L$0 = $this$sequence;
                this.L$1 = it;
                this.label = 1;
                if ($this$sequence.yieldAll(this.this$0.gen(this.$deep, events), (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }

    private static final Object invokeSuspend$lambda$0() {
        return "too much call edge of report path. limited!";
    }
}
