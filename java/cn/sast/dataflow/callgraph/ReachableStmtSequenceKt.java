package cn.sast.dataflow.callgraph;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.toolkits.callgraph.ReachableMethods;
import soot.util.queue.QueueReader;

/* compiled from: ReachableStmtSequence.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u001a\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\b"}, d2 = {"reachableMethodSequence", "Lkotlin/sequences/Sequence;", "Lsoot/SootMethod;", "entryPoints", "", "Lsoot/MethodOrMethodContext;", "reachableStmtSequence", "Lsoot/jimple/Stmt;", "corax-data-flow"})
/* loaded from: ReachableStmtSequenceKt.class */
public final class ReachableStmtSequenceKt {

    /* compiled from: ReachableStmtSequence.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lsoot/SootMethod;"})
    @DebugMetadata(f = "ReachableStmtSequence.kt", l = {25}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"$this$sequence", "reachableChangedListener"}, m = "invokeSuspend", c = "cn.sast.dataflow.callgraph.ReachableStmtSequenceKt$reachableMethodSequence$1")
    /* renamed from: cn.sast.dataflow.callgraph.ReachableStmtSequenceKt$reachableMethodSequence$1, reason: invalid class name */
    /* loaded from: ReachableStmtSequenceKt$reachableMethodSequence$1.class */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super SootMethod>, Continuation<? super Unit>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Collection<MethodOrMethodContext> $entryPoints;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Collection<? extends MethodOrMethodContext> collection, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$entryPoints = collection;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$entryPoints, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super SootMethod> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Iterator reachableChangedListener;
            SequenceScope $this$sequence;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    $this$sequence = (SequenceScope) this.L$0;
                    Scene scene = Scene.v();
                    ReachableMethods temp = new ReachableMethods(scene.getCallGraph(), this.$entryPoints);
                    temp.update();
                    QueueReader queueReaderListener = temp.listener();
                    Intrinsics.checkNotNullExpressionValue(queueReaderListener, "listener(...)");
                    reachableChangedListener = (Iterator) queueReaderListener;
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    reachableChangedListener = (Iterator) this.L$1;
                    $this$sequence = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (reachableChangedListener.hasNext()) {
                SootMethod m = ((MethodOrMethodContext) reachableChangedListener.next()).method();
                Intrinsics.checkNotNullExpressionValue(m, "method(...)");
                this.L$0 = $this$sequence;
                this.L$1 = reachableChangedListener;
                this.label = 1;
                if ($this$sequence.yield(m, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public static final Sequence<SootMethod> reachableMethodSequence(@NotNull Collection<? extends MethodOrMethodContext> collection) {
        Intrinsics.checkNotNullParameter(collection, "entryPoints");
        return SequencesKt.sequence(new AnonymousClass1(collection, null));
    }

    /* compiled from: ReachableStmtSequence.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lsoot/jimple/Stmt;"})
    @DebugMetadata(f = "ReachableStmtSequence.kt", l = {35}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this$sequence"}, m = "invokeSuspend", c = "cn.sast.dataflow.callgraph.ReachableStmtSequenceKt$reachableStmtSequence$1")
    /* renamed from: cn.sast.dataflow.callgraph.ReachableStmtSequenceKt$reachableStmtSequence$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ReachableStmtSequenceKt$reachableStmtSequence$1.class */
    static final class C00101 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Stmt>, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Collection<MethodOrMethodContext> $entryPoints;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00101(Collection<? extends MethodOrMethodContext> collection, Continuation<? super C00101> continuation) {
            super(2, continuation);
            this.$entryPoints = collection;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> c00101 = new C00101(this.$entryPoints, continuation);
            c00101.L$0 = value;
            return c00101;
        }

        public final Object invoke(SequenceScope<? super Stmt> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0042  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0083 -> B:5:0x0039). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 248
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.callgraph.ReachableStmtSequenceKt.C00101.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @NotNull
    public static final Sequence<Stmt> reachableStmtSequence(@NotNull Collection<? extends MethodOrMethodContext> collection) {
        Intrinsics.checkNotNullParameter(collection, "entryPoints");
        return SequencesKt.sequence(new C00101(collection, null));
    }
}
