package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.InterproceduralCFG;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.PointsToAnalysis;
import soot.PointsToSet;
import soot.Scene;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.DefinitionStmt;
import soot.jimple.infoflow.data.AccessPath;
import soot.jimple.spark.pag.PAG;
import soot.jimple.spark.sets.PointsToSetInternal;

/* compiled from: DemandPTA.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018�� 6*\u0004\b��\u0010\u0001* \b\u0001\u0010\u0002*\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00060\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0007:\u00016B'\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0012\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00150\u00140\u0013H&J\u0016\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040(H\u0016J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020*2\u0006\u0010+\u001a\u00020,JB\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u00052\f\u00102\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u00103\u001a\u000204H\u0096@¢\u0006\u0002\u00105R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050 X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00067"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/DemandPTA;", "V", "CTX", "Lcn/sast/idfa/analysis/Context;", "Lsoot/SootMethod;", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis;", "pta", "Lsoot/PointsToAnalysis;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lsoot/PointsToAnalysis;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "getPta", "()Lsoot/PointsToAnalysis;", "getLocals", "", "Lkotlin/Pair;", "Lsoot/jimple/infoflow/data/AccessPath;", "associationPTS", "Lsoot/jimple/spark/sets/PointsToSetInternal;", "getAssociationPTS", "()Lsoot/jimple/spark/sets/PointsToSetInternal;", "setAssociationPTS", "(Lsoot/jimple/spark/sets/PointsToSetInternal;)V", "associationInstance", "getAssociationInstance", "setAssociationInstance", "associationStmt", "", "getAssociationStmt", "()Ljava/util/Set;", "setAssociationStmt", "(Ljava/util/Set;)V", "doAnalysis", "", "entries", "", "isAssociation", "", "l", "Lsoot/Local;", "isAssociationInstance", "normalFlowFunction", "context", "node", "succ", "inValue", "isNegativeBranch", "Ljava/util/concurrent/atomic/AtomicBoolean;", "(Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Ljava/util/concurrent/atomic/AtomicBoolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nDemandPTA.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DemandPTA.kt\ncn/sast/dataflow/interprocedural/analysis/DemandPTA\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,152:1\n1863#2:153\n1864#2:155\n1557#2:156\n1628#2,3:157\n808#2,11:160\n1#3:154\n*S KotlinDebug\n*F\n+ 1 DemandPTA.kt\ncn/sast/dataflow/interprocedural/analysis/DemandPTA\n*L\n70#1:153\n70#1:155\n115#1:156\n115#1:157,3\n115#1:160,11\n*E\n"})
/* loaded from: DemandPTA.class */
public abstract class DemandPTA<V, CTX extends Context<SootMethod, Unit, IFact<V>>> extends AJimpleInterProceduralAnalysis<V, CTX> {

    @NotNull
    private final PointsToAnalysis pta;

    @Nullable
    private PointsToSetInternal associationPTS;

    @Nullable
    private PointsToSetInternal associationInstance;

    @NotNull
    private Set<Unit> associationStmt;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(DemandPTA::logger$lambda$4);

    @NotNull
    public abstract Set<Pair<Unit, AccessPath>> getLocals();

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis
    @Nullable
    public Object normalFlowFunction(@NotNull CTX ctx, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<V> iFact, @NotNull AtomicBoolean isNegativeBranch, @NotNull Continuation<? super IFact<V>> continuation) {
        return normalFlowFunction$suspendImpl((DemandPTA) this, (Context) ctx, node, succ, (IFact) iFact, isNegativeBranch, (Continuation) continuation);
    }

    public /* synthetic */ DemandPTA(PointsToAnalysis pointsToAnalysis, AbstractHeapFactory abstractHeapFactory, InterproceduralCFG interproceduralCFG, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Scene.v().getPointsToAnalysis() : pointsToAnalysis, abstractHeapFactory, interproceduralCFG);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object normalFlowFunction(Context context, Unit unit, Unit unit2, Object inValue, AtomicBoolean isNegativeBranch, Continuation $completion) {
        return normalFlowFunction((DemandPTA<V, CTX>) context, unit, unit2, (IFact) inValue, isNegativeBranch, $completion);
    }

    @NotNull
    public final PointsToAnalysis getPta() {
        return this.pta;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DemandPTA(@NotNull PointsToAnalysis pta, @NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull InterproceduralCFG icfg) {
        super(abstractHeapFactory, icfg);
        Intrinsics.checkNotNullParameter(pta, "pta");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        this.pta = pta;
        this.associationStmt = new LinkedHashSet();
    }

    @Nullable
    public final PointsToSetInternal getAssociationPTS() {
        return this.associationPTS;
    }

    public final void setAssociationPTS(@Nullable PointsToSetInternal pointsToSetInternal) {
        this.associationPTS = pointsToSetInternal;
    }

    @Nullable
    public final PointsToSetInternal getAssociationInstance() {
        return this.associationInstance;
    }

    public final void setAssociationInstance(@Nullable PointsToSetInternal pointsToSetInternal) {
        this.associationInstance = pointsToSetInternal;
    }

    @NotNull
    public final Set<Unit> getAssociationStmt() {
        return this.associationStmt;
    }

    public final void setAssociationStmt(@NotNull Set<Unit> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.associationStmt = set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.InterProceduralAnalysis
    public void doAnalysis(@NotNull Collection<? extends SootMethod> collection) {
        PointsToSet pointsToSetReachingObjects;
        Intrinsics.checkNotNullParameter(collection, "entries");
        if (this.pta instanceof PAG) {
            PointsToSetInternal associationInstance = this.pta.getSetFactory().newSet((Type) null, this.pta);
            PointsToSetInternal associationPTS = this.pta.getSetFactory().newSet((Type) null, this.pta);
            Iterable $this$forEach$iv = getLocals();
            for (Object element$iv : $this$forEach$iv) {
                Pair pair = (Pair) element$iv;
                Unit u = (Unit) pair.component1();
                AccessPath accessPath = (AccessPath) pair.component2();
                if (u != null) {
                    this.associationStmt.add(u);
                }
                if (accessPath.getFirstFragment() != null) {
                    PointsToSetInternal pointsToSetInternalReachingObjects = this.pta.reachingObjects(accessPath.getPlainValue());
                    if (pointsToSetInternalReachingObjects != null && (pointsToSetInternalReachingObjects instanceof PointsToSetInternal)) {
                        associationInstance.addAll(pointsToSetInternalReachingObjects, (PointsToSetInternal) null);
                    }
                    pointsToSetReachingObjects = this.pta.reachingObjects(accessPath.getPlainValue(), accessPath.getFirstFragment().getField());
                } else {
                    pointsToSetReachingObjects = this.pta.reachingObjects(accessPath.getPlainValue());
                }
                PointsToSet pts = pointsToSetReachingObjects;
                if (pts != null && (pts instanceof PointsToSetInternal)) {
                    associationPTS.addAll((PointsToSetInternal) pts, (PointsToSetInternal) null);
                }
            }
            this.associationInstance = associationInstance;
            this.associationPTS = associationPTS;
        } else {
            logger.error(() -> {
                return doAnalysis$lambda$2(r1);
            });
        }
        super.doAnalysis(collection);
    }

    private static final Object doAnalysis$lambda$2(DemandPTA this$0) {
        return "error pta type: " + this$0.pta.getClass();
    }

    public final boolean isAssociation(@NotNull Local l) {
        Intrinsics.checkNotNullParameter(l, "l");
        PointsToSet ptsL = this.pta.reachingObjects(l);
        PointsToSetInternal pointsToSetInternal = this.associationPTS;
        if (pointsToSetInternal != null) {
            return pointsToSetInternal.hasNonEmptyIntersection(ptsL);
        }
        return true;
    }

    public final boolean isAssociationInstance(@NotNull Local l) {
        Intrinsics.checkNotNullParameter(l, "l");
        PointsToSet ptsL = this.pta.reachingObjects(l);
        PointsToSetInternal pointsToSetInternal = this.associationInstance;
        if (pointsToSetInternal != null) {
            return pointsToSetInternal.hasNonEmptyIntersection(ptsL);
        }
        return true;
    }

    static /* synthetic */ <V, CTX extends Context<SootMethod, Unit, IFact<V>>> Object normalFlowFunction$suspendImpl(DemandPTA<V, CTX> demandPTA, CTX ctx, Unit node, Unit succ, IFact<V> iFact, AtomicBoolean isNegativeBranch, Continuation<? super IFact<V>> continuation) {
        Iterable useAndDefBoxes = node.getUseAndDefBoxes();
        Intrinsics.checkNotNullExpressionValue(useAndDefBoxes, "getUseAndDefBoxes(...)");
        Iterable $this$map$iv = useAndDefBoxes;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ValueBox it = (ValueBox) item$iv$iv;
            destination$iv$iv.add(it.getValue());
        }
        Iterable $this$filterIsInstance$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof Local) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        if (!(node instanceof DefinitionStmt)) {
            return iFact;
        }
        Intrinsics.checkNotNullExpressionValue(((DefinitionStmt) node).getLeftOp(), "getLeftOp(...)");
        Intrinsics.checkNotNullExpressionValue(((DefinitionStmt) node).getRightOp(), "getRightOp(...)");
        return super.normalFlowFunction((DemandPTA<V, CTX>) ctx, node, succ, iFact, isNegativeBranch, continuation);
    }

    /* compiled from: DemandPTA.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/DemandPTA$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: DemandPTA$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final kotlin.Unit logger$lambda$4() {
        return kotlin.Unit.INSTANCE;
    }
}
