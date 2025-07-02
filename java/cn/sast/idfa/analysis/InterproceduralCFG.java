package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.Local;
import soot.Scene;
import soot.SootMethod;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.IdentityStmt;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.Stmt;
import soot.jimple.ThisRef;
import soot.jimple.toolkits.ide.icfg.AbstractJimpleBasedICFG;
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG;
import soot.toolkits.graph.DirectedBodyGraph;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.scalar.LiveLocals;
import soot.toolkits.scalar.SimpleLiveLocals;

/* compiled from: InterproceduralCFG.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\u001e\n��\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u000e\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0003J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0002H\u0016J\u001e\u0010 \u001a\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030%2\u0006\u0010#\u001a\u00020\u0002H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\u0016\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003J\u000e\u0010*\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0003J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020+2\u0006\u0010(\u001a\u00020\u0003J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030-2\u0006\u0010(\u001a\u00020\u0003J\u000e\u0010.\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u00032\u0006\u0010(\u001a\u00020\u0003J\u000e\u00100\u001a\u0002012\u0006\u0010\u0016\u001a\u00020\u0002J\u0010\u00102\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\"\u00103\u001a\b\u0012\u0004\u0012\u0002040-2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010(\u001a\u00020\u0003R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n��R \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n��R \u0010\f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r\u0012\u0004\u0012\u00020\u000e0\tX\u0082\u0004¢\u0006\u0002\n��R\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u00066"}, d2 = {"Lcn/sast/idfa/analysis/InterproceduralCFG;", "Lcn/sast/idfa/analysis/ProgramRepresentation;", "Lsoot/SootMethod;", "Lsoot/Unit;", "<init>", "()V", "unitToOwner", "", "cfgCacheSummary", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "Lsoot/toolkits/graph/DirectedGraph;", "cfgCache", "liveLocalCache", "Lsoot/toolkits/graph/DirectedBodyGraph;", "Lsoot/toolkits/scalar/LiveLocals;", "delegateICFG", "Lsoot/jimple/toolkits/ide/icfg/AbstractJimpleBasedICFG;", "getDelegateICFG", "()Lsoot/jimple/toolkits/ide/icfg/AbstractJimpleBasedICFG;", "delegateICFG$delegate", "Lkotlin/Lazy;", "getControlFlowGraph", "method", "getSummaryControlFlowGraph", "isCall", "", "node", "getCalleesOfCallAt", "", "callerMethod", "callNode", "getMethodOf", "setOwnerStatement", "", "u", "owner", "g", "", "isAnalyzable", "isFallThroughSuccessor", "unit", "succ", "isCallStmt", "", "getPredsOf", "", "hasPredAsLookupSwitchStmt", "getPredAsLookupSwitchStmt", "getIdentityStmt", "Lsoot/jimple/IdentityStmt;", "isSkipCall", "getNonLiveLocals", "Lsoot/Local;", "ug", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nInterproceduralCFG.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InterproceduralCFG.kt\ncn/sast/idfa/analysis/InterproceduralCFG\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,246:1\n1619#2:247\n1863#2:248\n1864#2:251\n1620#2:252\n1#3:249\n1#3:250\n*S KotlinDebug\n*F\n+ 1 InterproceduralCFG.kt\ncn/sast/idfa/analysis/InterproceduralCFG\n*L\n244#1:247\n244#1:248\n244#1:251\n244#1:252\n244#1:250\n*E\n"})
/* loaded from: InterproceduralCFG.class */
public final class InterproceduralCFG implements ProgramRepresentation<SootMethod, Unit> {

    @NotNull
    private final LoadingCache<SootMethod, DirectedGraph<Unit>> cfgCacheSummary;

    @NotNull
    private final LoadingCache<SootMethod, DirectedGraph<Unit>> cfgCache;

    @NotNull
    private final LoadingCache<DirectedBodyGraph<Unit>, LiveLocals> liveLocalCache;

    @NotNull
    private final Map<Unit, SootMethod> unitToOwner = new ConcurrentHashMap();

    @NotNull
    private final Lazy delegateICFG$delegate = LazyKt.lazy(InterproceduralCFG::delegateICFG_delegate$lambda$0);

    public InterproceduralCFG() {
        int initialCapacity = Math.max(Scene.v().getClasses().size() / 100, Scene.v().getClasses().size() + 100);
        Caffeine cacheBuilderSummary = Caffeine.newBuilder().initialCapacity(initialCapacity);
        final Function1 function1 = (v1) -> {
            return _init_$lambda$1(r2, v1);
        };
        this.cfgCacheSummary = cacheBuilderSummary.build(new CacheLoader(function1) { // from class: cn.sast.idfa.analysis.InterproceduralCFG$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function1, "function");
                this.function = function1;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
        Caffeine cacheBuilder = Caffeine.newBuilder().expireAfterAccess(30000L, TimeUnit.MINUTES).initialCapacity(initialCapacity).maximumSize(initialCapacity * 2).softValues();
        final Function1 function12 = (v1) -> {
            return _init_$lambda$2(r2, v1);
        };
        this.cfgCache = cacheBuilder.build(new CacheLoader(function12) { // from class: cn.sast.idfa.analysis.InterproceduralCFG$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function12, "function");
                this.function = function12;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
        final Function1 function13 = InterproceduralCFG::_init_$lambda$3;
        this.liveLocalCache = cacheBuilder.build(new CacheLoader(function13) { // from class: cn.sast.idfa.analysis.InterproceduralCFG$sam$com_github_benmanes_caffeine_cache_CacheLoader$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(function13, "function");
                this.function = function13;
            }

            public final /* synthetic */ Object load(Object p0) {
                return this.function.invoke(p0);
            }
        });
    }

    @NotNull
    public final AbstractJimpleBasedICFG getDelegateICFG() {
        return (AbstractJimpleBasedICFG) this.delegateICFG$delegate.getValue();
    }

    private static final JimpleBasedInterproceduralCFG delegateICFG_delegate$lambda$0() {
        return new JimpleBasedInterproceduralCFG(true);
    }

    private static final DirectedGraph _init_$lambda$1(InterproceduralCFG this$0, SootMethod key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new SummaryControlFlowUnitGraph(key, this$0);
    }

    private static final DirectedGraph _init_$lambda$2(InterproceduralCFG this$0, SootMethod key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Body b = key.getActiveBody();
        return this$0.getDelegateICFG().getOrCreateUnitGraph(b);
    }

    private static final LiveLocals _init_$lambda$3(DirectedBodyGraph g) {
        Intrinsics.checkNotNullParameter(g, "g");
        return new SimpleLiveLocals(g);
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    @NotNull
    public DirectedGraph<Unit> getControlFlowGraph(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Object obj = this.cfgCache.get(method);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (DirectedGraph) obj;
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    @NotNull
    public DirectedGraph<Unit> getSummaryControlFlowGraph(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Object obj = this.cfgCacheSummary.get(method);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (DirectedGraph) obj;
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    public boolean isCall(@NotNull Unit node) {
        Intrinsics.checkNotNullParameter(node, "node");
        return ((Stmt) node).containsInvokeExpr();
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    @NotNull
    public Set<SootMethod> getCalleesOfCallAt(@NotNull SootMethod callerMethod, @NotNull Unit callNode) {
        Intrinsics.checkNotNullParameter(callerMethod, "callerMethod");
        Intrinsics.checkNotNullParameter(callNode, "callNode");
        Collection calleesOfCallAt = getDelegateICFG().getCalleesOfCallAt(callNode);
        Intrinsics.checkNotNullExpressionValue(calleesOfCallAt, "getCalleesOfCallAt(...)");
        return CollectionsKt.toSet(calleesOfCallAt);
    }

    @NotNull
    public final SootMethod getMethodOf(@NotNull Unit node) {
        Intrinsics.checkNotNullParameter(node, "node");
        SootMethod sootMethod = this.unitToOwner.get(node);
        if (sootMethod != null) {
            return sootMethod;
        }
        SootMethod methodOf = getDelegateICFG().getMethodOf(node);
        Intrinsics.checkNotNullExpressionValue(methodOf, "getMethodOf(...)");
        return methodOf;
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    public void setOwnerStatement(@NotNull Unit u, @NotNull SootMethod owner) {
        Intrinsics.checkNotNullParameter(u, "u");
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.unitToOwner.put(u, owner);
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    public void setOwnerStatement(@NotNull Iterable<? extends Unit> iterable, @NotNull SootMethod owner) {
        Intrinsics.checkNotNullParameter(iterable, "g");
        Intrinsics.checkNotNullParameter(owner, "owner");
        for (Unit u : iterable) {
            this.unitToOwner.put(u, owner);
        }
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    public boolean isAnalyzable(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return method.hasActiveBody();
    }

    public final boolean isFallThroughSuccessor(@NotNull Unit unit, @NotNull Unit succ) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(succ, "succ");
        return getDelegateICFG().isFallThroughSuccessor(unit, succ);
    }

    public final boolean isCallStmt(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return getDelegateICFG().isCallStmt(unit);
    }

    @NotNull
    public final Collection<SootMethod> getCalleesOfCallAt(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Collection<SootMethod> calleesOfCallAt = getDelegateICFG().getCalleesOfCallAt(unit);
        Intrinsics.checkNotNullExpressionValue(calleesOfCallAt, "getCalleesOfCallAt(...)");
        return calleesOfCallAt;
    }

    @NotNull
    public final List<Unit> getPredsOf(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        List<Unit> predsOf = getDelegateICFG().getPredsOf(unit);
        Intrinsics.checkNotNullExpressionValue(predsOf, "getPredsOf(...)");
        return predsOf;
    }

    public final boolean hasPredAsLookupSwitchStmt(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        for (Unit pred : getDelegateICFG().getPredsOf(unit)) {
            if (pred instanceof LookupSwitchStmt) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final Unit getPredAsLookupSwitchStmt(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        for (Unit pred : getDelegateICFG().getPredsOf(unit)) {
            if (pred instanceof LookupSwitchStmt) {
                return pred;
            }
        }
        return null;
    }

    @NotNull
    public final IdentityStmt getIdentityStmt(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Iterator it = method.getActiveBody().getUnits().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            IdentityStmt identityStmt = (Unit) it.next();
            if ((identityStmt instanceof IdentityStmt) && (identityStmt.getRightOp() instanceof ThisRef)) {
                return identityStmt;
            }
        }
        throw new RuntimeException("couldn't find identityref! in " + method);
    }

    @Override // cn.sast.idfa.analysis.ProgramRepresentation
    public boolean isSkipCall(@NotNull Unit node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (!(node instanceof Stmt) || ((Stmt) node).containsInvokeExpr()) {
        }
        return false;
    }

    @NotNull
    public final List<Local> getNonLiveLocals(@NotNull DirectedBodyGraph<Unit> directedBodyGraph, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(directedBodyGraph, "ug");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Object obj = this.liveLocalCache.get(directedBodyGraph);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        LiveLocals sll = (LiveLocals) obj;
        List liveLocals = sll.getLiveLocalsAfter(unit);
        Iterable useBoxes = unit.getUseBoxes();
        Intrinsics.checkNotNullExpressionValue(useBoxes, "getUseBoxes(...)");
        Iterable $this$mapNotNullTo$iv = useBoxes;
        Collection destination$iv = (List) new ArrayList();
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            ValueBox it = (ValueBox) element$iv$iv;
            Local value = it.getValue();
            Local live = value instanceof Local ? value : null;
            Local local = !liveLocals.contains(live) ? live : null;
            if (local != null) {
                destination$iv.add(local);
            }
        }
        return (List) destination$iv;
    }
}
