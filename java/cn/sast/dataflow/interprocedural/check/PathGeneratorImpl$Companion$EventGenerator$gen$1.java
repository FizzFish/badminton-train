package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.report.BugPathEvent;
import cn.sast.dataflow.interprocedural.check.PathGeneratorImpl;
import cn.sast.dataflow.interprocedural.check.printer.PathDiagnosticsGenerator;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Lcn/sast/api/report/BugPathEvent;"})
@DebugMetadata(f = "PathGenerator.kt", l = {135}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2"}, n = {"$this$sequence", "normalizedEvents", "worklist"}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.PathGeneratorImpl$Companion$EventGenerator$gen$1")
@SourceDebugExtension({"SMAP\nPathGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator$gen$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 extensions.kt\nkotlinx/collections/immutable/ExtensionsKt\n*L\n1#1,213:1\n774#2:214\n865#2,2:215\n138#3:217\n138#3:218\n*S KotlinDebug\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator$gen$1\n*L\n125#1:214\n125#1:215,2\n141#1:217\n154#1:218\n*E\n"})
/* loaded from: PathGeneratorImpl$Companion$EventGenerator$gen$1.class */
final class PathGeneratorImpl$Companion$EventGenerator$gen$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends BugPathEvent>>, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ List<IPath> $events;
    final /* synthetic */ PathGeneratorImpl.Companion.EventGenerator this$0;
    final /* synthetic */ int $deep;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PathGeneratorImpl$Companion$EventGenerator$gen$1(List<? extends IPath> list, PathGeneratorImpl.Companion.EventGenerator $receiver, int $deep, Continuation<? super PathGeneratorImpl$Companion$EventGenerator$gen$1> continuation) {
        super(2, continuation);
        this.$events = list;
        this.this$0 = $receiver;
        this.$deep = $deep;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> pathGeneratorImpl$Companion$EventGenerator$gen$1 = new PathGeneratorImpl$Companion$EventGenerator$gen$1(this.$events, this.this$0, this.$deep, continuation);
        pathGeneratorImpl$Companion$EventGenerator$gen$1.L$0 = value;
        return pathGeneratorImpl$Companion$EventGenerator$gen$1;
    }

    public final Object invoke(SequenceScope<? super List<BugPathEvent>> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        ArrayDeque worklist;
        List normalizedEvents;
        SequenceScope $this$sequence;
        PersistentList persistentListAdd;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                $this$sequence = (SequenceScope) this.L$0;
                Iterable $this$filter$iv = this.$events;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    IPath it = (IPath) element$iv$iv;
                    if (!(it instanceof EntryPath)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                normalizedEvents = PathGeneratorKt.getRemoveAdjacentDuplicates((List) destination$iv$iv);
                if (normalizedEvents.isEmpty()) {
                    return Unit.INSTANCE;
                }
                worklist = new ArrayDeque();
                worklist.add(TuplesKt.to(ExtensionsKt.persistentListOf(), Boxing.boxInt(0)));
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                worklist = (ArrayDeque) this.L$2;
                normalizedEvents = (List) this.L$1;
                $this$sequence = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (true) {
            Pair pair = (Pair) worklist.removeLastOrNull();
            if (pair != null) {
                PersistentList pathEvents = (PersistentList) pair.component1();
                int idx = ((Number) pair.component2()).intValue();
                if (idx >= normalizedEvents.size()) {
                    PathDiagnosticsGenerator p = new PathDiagnosticsGenerator(this.this$0.getInfo(), this.this$0.getIcfg(), this.$deep);
                    List ret = PathGeneratorKt.getRemoveAdjacentDuplicates(p.inlineEvents(pathEvents));
                    if (!ret.isEmpty()) {
                        this.L$0 = $this$sequence;
                        this.L$1 = normalizedEvents;
                        this.L$2 = worklist;
                        this.label = 1;
                        if ($this$sequence.yield(ret, (Continuation) this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        continue;
                    }
                } else {
                    int next = idx + 1;
                    IPath curPath = (IPath) normalizedEvents.get(idx);
                    PersistentList curDiagnostic = pathEvents.add(curPath);
                    if (!(curPath instanceof InvokeEdgePath)) {
                        Boxing.boxBoolean(worklist.add(TuplesKt.to(curDiagnostic, Boxing.boxInt(next))));
                    } else {
                        ExitInvoke exit = new ExitInvoke((InvokeEdgePath) curPath);
                        Set<List> callees = this.this$0.diagnosticsCached((InvokeEdgePath) curPath, this.$deep + 1);
                        if (callees.isEmpty()) {
                            Boxing.boxBoolean(worklist.add(TuplesKt.to(pathEvents, Boxing.boxInt(next))));
                        } else {
                            for (List calleeEvents : callees) {
                                if (!(!calleeEvents.isEmpty())) {
                                    throw new IllegalStateException("Check failed.".toString());
                                }
                                if (next == normalizedEvents.size()) {
                                    persistentListAdd = ExtensionsKt.plus(curDiagnostic, calleeEvents);
                                } else {
                                    PersistentList $this$plus$iv = ExtensionsKt.plus(curDiagnostic, calleeEvents);
                                    persistentListAdd = $this$plus$iv.add(exit);
                                }
                                PersistentList nextEvents = persistentListAdd;
                                worklist.add(TuplesKt.to(nextEvents, Boxing.boxInt(next)));
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}
