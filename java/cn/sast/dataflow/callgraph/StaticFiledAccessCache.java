package cn.sast.dataflow.callgraph;

import cn.sast.common.OS;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.UnitPatchingChain;
import soot.jimple.StaticFieldRef;
import soot.jimple.Stmt;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Targets;
import soot.util.queue.ChunkedQueue;
import soot.util.queue.QueueReader;

/* compiled from: ReachableStmtSequence.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bRP\u0010\f\u001aB\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00010\u0001 \u000e* \u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0080\u0001\u0010\u0010\u001ar\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013 \u000e*\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00140\u0014 \u000e*8\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013 \u000e*\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00140\u0014\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R-\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00190\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b¨\u0006!"}, d2 = {"Lcn/sast/dataflow/callgraph/StaticFiledAccessCache;", "", "cg", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "<init>", "(Lsoot/jimple/toolkits/callgraph/CallGraph;)V", "getCg", "()Lsoot/jimple/toolkits/callgraph/CallGraph;", "initialCapacity", "", "getInitialCapacity", "()I", "cacheBuilder", "Lcom/google/common/cache/CacheBuilder;", "kotlin.jvm.PlatformType", "Lcom/google/common/cache/CacheBuilder;", "cache", "Lcom/google/common/cache/LoadingCache;", "Lkotlin/Pair;", "Lsoot/SootMethod;", "", "Lcom/google/common/cache/LoadingCache;", "staticFieldRefToSootMethod", "", "Lsoot/jimple/StaticFieldRef;", "", "getStaticFieldRefToSootMethod", "()Ljava/util/Map;", "staticFieldRefToSootMethod$delegate", "Lkotlin/Lazy;", "isAccessible", "entry", "fieldRef", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nReachableStmtSequence.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReachableStmtSequence.kt\ncn/sast/dataflow/callgraph/StaticFiledAccessCache\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,113:1\n381#2,7:114\n*S KotlinDebug\n*F\n+ 1 ReachableStmtSequence.kt\ncn/sast/dataflow/callgraph/StaticFiledAccessCache\n*L\n94#1:114,7\n*E\n"})
/* loaded from: StaticFiledAccessCache.class */
public final class StaticFiledAccessCache {

    @NotNull
    private final CallGraph cg;
    private final CacheBuilder<Object, Object> cacheBuilder;
    private final LoadingCache<Pair<SootMethod, SootMethod>, Boolean> cache;

    @NotNull
    private final Lazy staticFieldRefToSootMethod$delegate;

    public StaticFiledAccessCache(@NotNull CallGraph cg) {
        Intrinsics.checkNotNullParameter(cg, "cg");
        this.cg = cg;
        this.cacheBuilder = CacheBuilder.newBuilder().concurrencyLevel(OS.INSTANCE.getMaxThreadNum()).initialCapacity(getInitialCapacity()).maximumSize(getInitialCapacity() * 2).softValues();
        this.cache = this.cacheBuilder.build(new CacheLoader<Pair<? extends SootMethod, ? extends SootMethod>, Boolean>() { // from class: cn.sast.dataflow.callgraph.StaticFiledAccessCache$cache$1
            public Boolean load(Pair<? extends SootMethod, ? extends SootMethod> pair) throws Exception {
                Intrinsics.checkNotNullParameter(pair, "key");
                CallGraph callGraph = this.this$0.getCg();
                SootMethod src = (SootMethod) pair.getFirst();
                ChunkedQueue reaches = new ChunkedQueue();
                HashSet reachSet = new HashSet();
                reachSet.add(src);
                QueueReader reader = reaches.reader();
                HashSet $this$forEach$iv = reachSet;
                for (Object element$iv : $this$forEach$iv) {
                    SootMethod o = (SootMethod) element$iv;
                    reaches.add(o);
                }
                while (reader.hasNext()) {
                    Targets edgeIt = new Targets(callGraph.edgesOutOf((SootMethod) reader.next()));
                    while (edgeIt.hasNext()) {
                        SootMethod next = edgeIt.next();
                        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type soot.SootMethod");
                        SootMethod target = next;
                        if (reachSet.add(target)) {
                            reaches.add(target);
                            if (Intrinsics.areEqual(target, pair.getSecond())) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });
        this.staticFieldRefToSootMethod$delegate = LazyKt.lazy(StaticFiledAccessCache::staticFieldRefToSootMethod_delegate$lambda$1);
    }

    @NotNull
    public final CallGraph getCg() {
        return this.cg;
    }

    private final int getInitialCapacity() {
        return Math.max(Scene.v().getClasses().size() * 10, Scene.v().getClasses().size() + 1000);
    }

    private final Map<StaticFieldRef, Set<SootMethod>> getStaticFieldRefToSootMethod() {
        return (Map) this.staticFieldRefToSootMethod$delegate.getValue();
    }

    private static final Map staticFieldRefToSootMethod_delegate$lambda$1() {
        Object obj;
        Map res = new LinkedHashMap();
        Iterator it = Scene.v().getClasses().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            SootClass sc = (SootClass) it.next();
            for (SootMethod sm : sc.getMethods()) {
                if (sm.hasActiveBody()) {
                    UnitPatchingChain units = sm.getActiveBody().getUnits();
                    Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
                    Iterator it2 = units.iterator();
                    Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
                    while (it2.hasNext()) {
                        Stmt stmt = (Unit) it2.next();
                        Intrinsics.checkNotNull(stmt, "null cannot be cast to non-null type soot.jimple.Stmt");
                        Stmt stmt2 = stmt;
                        if (stmt2.containsFieldRef() && (stmt2.getFieldRef() instanceof StaticFieldRef)) {
                            StaticFieldRef fieldRef = stmt2.getFieldRef();
                            Intrinsics.checkNotNull(fieldRef, "null cannot be cast to non-null type soot.jimple.StaticFieldRef");
                            StaticFieldRef sf = fieldRef;
                            Object value$iv = res.get(sf);
                            if (value$iv == null) {
                                LinkedHashSet linkedHashSet = new LinkedHashSet();
                                res.put(sf, linkedHashSet);
                                obj = linkedHashSet;
                            } else {
                                obj = value$iv;
                            }
                            Intrinsics.checkNotNull(sm);
                            ((Set) obj).add(sm);
                        }
                    }
                }
            }
        }
        return res;
    }

    public final boolean isAccessible(@NotNull SootMethod entry, @NotNull StaticFieldRef fieldRef) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(fieldRef, "fieldRef");
        Set methodsHaveAccess = getStaticFieldRefToSootMethod().get(fieldRef);
        if (methodsHaveAccess == null) {
            return false;
        }
        for (SootMethod sm : methodsHaveAccess) {
            if (((Boolean) this.cache.get(TuplesKt.to(entry, sm))).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
