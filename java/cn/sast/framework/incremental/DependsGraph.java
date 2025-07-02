package cn.sast.framework.incremental;

import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.api.incremental.ModifyInfoFactory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.graph.HashMutableDirectedGraph;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import soot.toolkits.graph.MutableDirectedGraph;

/* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\n0\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0096\u0004J\u001e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\u0096\u0004J&\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00152\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u001d2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020$H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u001d2\u0006\u0010#\u001a\u00020\nH\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010#\u001a\u00020\nH\u0016J\u0010\u0010(\u001a\u00020'2\u0006\u0010#\u001a\u00020$H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n��R \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n��¨\u0006)"}, d2 = {"Lcn/sast/framework/incremental/DependsGraph;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "factory", "Lcn/sast/api/incremental/ModifyInfoFactory;", "<init>", "(Lcn/sast/api/incremental/ModifyInfoFactory;)V", "getFactory", "()Lcn/sast/api/incremental/ModifyInfoFactory;", "dependenceGraph", "Lsoot/toolkits/graph/MutableDirectedGraph;", "Lcom/feysh/corax/config/api/XDecl;", "patchRelateAnalysisTargets", "", "", "", "patchRelateObjects", "patchRelateChangedWalk", "isOld", "", "dependsOn", "", "", "deps", "visitChangedDecl", "diffPath", "changed", "walkALl", "dep", "walk", "Lkotlin/sequences/Sequence;", "node", "forward", "targetsRelate", "targets", "toDecl", "target", "", "targetRelate", "shouldReAnalyzeDecl", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "shouldReAnalyzeTarget", "corax-framework"})
@SourceDebugExtension({"SMAP\nIncrementalAnalyzeImplByChangeFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/DependsGraph\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,395:1\n381#2,7:396\n1317#3,2:403\n*S KotlinDebug\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/DependsGraph\n*L\n157#1:396,7\n168#1:403,2\n*E\n"})
/* loaded from: DependsGraph.class */
public final class DependsGraph implements IncrementalAnalyzeByChangeFiles.IDependsGraph {

    @NotNull
    private final ModifyInfoFactory factory;

    @NotNull
    private final MutableDirectedGraph<XDecl> dependenceGraph;

    @NotNull
    private final Map<String, Set<XDecl>> patchRelateAnalysisTargets;

    @NotNull
    private final Set<XDecl> patchRelateObjects;

    @NotNull
    private final Set<XDecl> patchRelateChangedWalk;
    private boolean isOld;

    public DependsGraph(@NotNull ModifyInfoFactory factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.factory = factory;
        this.dependenceGraph = new HashMutableDirectedGraph();
        this.patchRelateAnalysisTargets = new LinkedHashMap();
        this.patchRelateObjects = new LinkedHashSet();
        this.patchRelateChangedWalk = new LinkedHashSet();
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    @NotNull
    public ModifyInfoFactory getFactory() {
        return this.factory;
    }

    public void dependsOn(@NotNull Collection<? extends XDecl> collection, @NotNull Collection<? extends XDecl> collection2) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(collection2, "deps");
        for (XDecl n : collection) {
            for (XDecl d : collection2) {
                dependsOn(n, d);
            }
        }
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    public void visitChangedDecl(@NotNull String diffPath, @NotNull Collection<? extends XDecl> collection) {
        Object obj;
        Intrinsics.checkNotNullParameter(diffPath, "diffPath");
        Intrinsics.checkNotNullParameter(collection, "changed");
        for (XDecl e : collection) {
            Map $this$getOrPut$iv = this.patchRelateAnalysisTargets;
            Object value$iv = $this$getOrPut$iv.get(diffPath);
            if (value$iv == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                $this$getOrPut$iv.put(diffPath, linkedHashSet);
                obj = linkedHashSet;
            } else {
                obj = value$iv;
            }
            ((Set) obj).add(e);
            this.patchRelateObjects.add(e);
        }
        this.patchRelateChangedWalk.clear();
        this.isOld = true;
    }

    private final void walkALl() {
        if (this.isOld) {
            synchronized (this) {
                if (this.isOld) {
                    Sequence $this$forEach$iv = targetsRelate(this.patchRelateObjects);
                    for (Object element$iv : $this$forEach$iv) {
                        XDecl it = (XDecl) element$iv;
                        this.patchRelateChangedWalk.add(it);
                    }
                    this.isOld = false;
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }

    public void dependsOn(@NotNull XDecl $this$dependsOn, @NotNull XDecl dep) {
        Intrinsics.checkNotNullParameter($this$dependsOn, "<this>");
        Intrinsics.checkNotNullParameter(dep, "dep");
        this.dependenceGraph.addEdge($this$dependsOn, dep);
        this.isOld = true;
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lcom/feysh/corax/config/api/XDecl;"})
    @DebugMetadata(f = "IncrementalAnalyzeImplByChangeFiles.kt", l = {189}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"$this$sequence", "worklist", "visited", "cur"}, m = "invokeSuspend", c = "cn.sast.framework.incremental.DependsGraph$walk$1")
    @SourceDebugExtension({"SMAP\nIncrementalAnalyzeImplByChangeFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/DependsGraph$walk$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,395:1\n1863#2,2:396\n*S KotlinDebug\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/DependsGraph$walk$1\n*L\n194#1:396,2\n*E\n"})
    /* renamed from: cn.sast.framework.incremental.DependsGraph$walk$1, reason: invalid class name */
    /* loaded from: DependsGraph$walk$1.class */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super XDecl>, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Collection<XDecl> $node;
        final /* synthetic */ boolean $forward;
        final /* synthetic */ DependsGraph this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Collection<? extends XDecl> collection, boolean $forward, DependsGraph $receiver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$node = collection;
            this.$forward = $forward;
            this.this$0 = $receiver;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$node, this.$forward, this.this$0, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super XDecl> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0126 A[LOOP:1: B:26:0x011c->B:28:0x0126, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0147 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0062  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0123 -> B:5:0x0052). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 341
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.incremental.DependsGraph.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final Sequence<XDecl> walk(Collection<? extends XDecl> collection, boolean forward) {
        return SequencesKt.sequence(new AnonymousClass1(collection, forward, this, null));
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    @NotNull
    public Sequence<XDecl> targetsRelate(@NotNull Collection<? extends XDecl> collection) {
        Intrinsics.checkNotNullParameter(collection, "targets");
        return SequencesKt.plus(walk(collection, true), walk(collection, false));
    }

    @NotNull
    public XDecl toDecl(@NotNull Object target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return getFactory().toDecl(target);
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    @NotNull
    public Sequence<XDecl> targetRelate(@NotNull XDecl target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return targetsRelate(CollectionsKt.listOf(target));
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    @NotNull
    public ProcessRule.ScanAction shouldReAnalyzeDecl(@NotNull XDecl target) {
        Intrinsics.checkNotNullParameter(target, "target");
        walkALl();
        ProcessRule.ScanAction actionByFactory = getFactory().getScanAction(target);
        if (actionByFactory != ProcessRule.ScanAction.Keep) {
            return actionByFactory;
        }
        if (this.patchRelateChangedWalk.contains(target)) {
            return ProcessRule.ScanAction.Process;
        }
        return ProcessRule.ScanAction.Skip;
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
    @NotNull
    public ProcessRule.ScanAction shouldReAnalyzeTarget(@NotNull Object target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return shouldReAnalyzeDecl(getFactory().toDecl(target));
    }
}
