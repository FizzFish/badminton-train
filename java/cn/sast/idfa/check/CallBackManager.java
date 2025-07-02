package cn.sast.idfa.check;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.toolkits.graph.UnitGraph;

/* compiled from: CheckerManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��x\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0017\u001a\u00020\u0018\"\n\b��\u0010\u0019\u0018\u0001*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\b2)\b\b\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001eH\u0086\bø\u0001��¢\u0006\u0002\u0010\u001fJR\u0010\u0017\u001a\u00020\u0018\"\u0004\b��\u0010\u00192\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\b2'\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e¢\u0006\u0002\u0010!JO\u0010\"\u001a+\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e\u0018\u00010#\"\u0004\b��\u0010\u00192\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010$\u001a\u00020\bH\u0016JO\u0010%\u001a+\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e\u0018\u00010#\"\u0004\b��\u0010\u00192\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\bH\u0016J;\u0010\u0017\u001a\u00020\u0018\"\n\b��\u0010\u0019\u0018\u0001*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\r2\u0019\b\b\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u00020\u00180&¢\u0006\u0002\b\u001eH\u0086\bø\u0001��J=\u0010\u0017\u001a\u00020\u0018\"\u0004\b��\u0010\u00192\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\r2\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u00020\u00180&¢\u0006\u0002\b\u001eJO\u0010\"\u001a+\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e\u0018\u00010#\"\u0004\b��\u0010\u00192\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\rH\u0016JF\u0010\"\u001a+\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e\u0018\u00010#\"\n\b��\u0010\u0019\u0018\u0001*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0001H\u0086\bJW\u0010\"\u001a+\u0012%\u0012#\b\u0001\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b\u001e\u0018\u00010#\"\n\b��\u0010\u0019\u0018\u0001*\u00020\u00072\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010(\"\u00020\u0001H\u0086\b¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u0010J\u0010\u0010,\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\bJ)\u0010-\u001a\u00020\u00182!\u0010.\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00180&R7\u0010\u0004\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t0\u00050\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR7\u0010\f\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t0\u00050\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0002\n��R+\u0010\u0011\u001a\u001c\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00120\u0005¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u000bR7\u0010\u0014\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00150\u00120\u0005¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00061"}, d2 = {"Lcn/sast/idfa/check/CallBackManager;", "", "<init>", "()V", "callBacksOfMethod", "", "Ljava/lang/Class;", "Lcn/sast/idfa/check/IStmtCB;", "Lsoot/SootMethod;", "", "getCallBacksOfMethod", "()Ljava/util/Map;", "callBacksOfUnit", "Lsoot/Unit;", "getCallBacksOfUnit", "jimpleOverride", "Lsoot/toolkits/graph/UnitGraph;", "miss", "", "getMiss", "hit", "Lkotlin/Pair;", "getHit", "put", "", "typeCB", "key", "cb", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lsoot/SootMethod;Lkotlin/jvm/functions/Function2;)V", "x", "(Ljava/lang/Class;Lsoot/SootMethod;Lkotlin/jvm/functions/Function2;)V", "get", "", "method", "getRaw", "Lkotlin/Function1;", "keys", "", "([Ljava/lang/Object;)Ljava/util/List;", "putUnitGraphOverride", "override", "getUnitGraphOverride", "reportMissSummaryMethod", "reportMissingMethod", "Lkotlin/ParameterName;", "name", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nCheckerManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerManager.kt\ncn/sast/idfa/check/CallBackManager\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,170:1\n137#1,5:215\n381#2,7:171\n381#2,7:178\n381#2,7:187\n381#2,7:194\n381#2,7:201\n381#2,7:208\n183#3,2:185\n1628#4,3:220\n*S KotlinDebug\n*F\n+ 1 CheckerManager.kt\ncn/sast/idfa/check/CallBackManager\n*L\n147#1:215,5\n88#1:171,7\n89#1:178,7\n105#1:187,7\n111#1:194,7\n127#1:201,7\n128#1:208,7\n97#1:185,2\n163#1:220,3\n*E\n"})
/* loaded from: CallBackManager.class */
public class CallBackManager {

    @NotNull
    private final Map<Class<? extends IStmtCB>, Map<SootMethod, List<Object>>> callBacksOfMethod = new LinkedHashMap();

    @NotNull
    private final Map<Class<? extends IStmtCB>, Map<Unit, List<Object>>> callBacksOfUnit = new LinkedHashMap();

    @NotNull
    private final Map<SootMethod, UnitGraph> jimpleOverride = new LinkedHashMap();

    @NotNull
    private final Map<Class<? extends IStmtCB>, Set<SootMethod>> miss = new LinkedHashMap();

    @NotNull
    private final Map<Class<? extends IStmtCB>, Set<Pair<SootMethod, SootMethod>>> hit = new LinkedHashMap();

    @NotNull
    public final Map<Class<? extends IStmtCB>, Map<SootMethod, List<Object>>> getCallBacksOfMethod() {
        return this.callBacksOfMethod;
    }

    @NotNull
    public final Map<Class<? extends IStmtCB>, Map<Unit, List<Object>>> getCallBacksOfUnit() {
        return this.callBacksOfUnit;
    }

    @NotNull
    public final Map<Class<? extends IStmtCB>, Set<SootMethod>> getMiss() {
        return this.miss;
    }

    @NotNull
    public final Map<Class<? extends IStmtCB>, Set<Pair<SootMethod, SootMethod>>> getHit() {
        return this.hit;
    }

    public final /* synthetic */ <typeCB extends IStmtCB> void put(SootMethod key, Function2<? super typeCB, ? super Continuation<? super kotlin.Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(function2, "cb");
        Intrinsics.reifiedOperationMarker(4, "typeCB");
        put((Class<? extends IStmtCB>) x, key, function2);
    }

    public final <typeCB> void put(@NotNull Class<? extends IStmtCB> cls, @NotNull SootMethod key, @NotNull Function2<? super typeCB, ? super Continuation<? super kotlin.Unit>, ? extends Object> function2) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(cls, "x");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(function2, "cb");
        Map $this$getOrPut$iv = this.callBacksOfMethod;
        Object value$iv = $this$getOrPut$iv.get(cls);
        if (value$iv == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            $this$getOrPut$iv.put(cls, linkedHashMap);
            obj = linkedHashMap;
        } else {
            obj = value$iv;
        }
        Map map = (Map) obj;
        Object value$iv2 = map.get(key);
        if (value$iv2 == null) {
            ArrayList arrayList = new ArrayList();
            map.put(key, arrayList);
            obj2 = arrayList;
        } else {
            obj2 = value$iv2;
        }
        ((List) obj2).add(function2);
    }

    @Nullable
    public <typeCB> List<Function2<typeCB, Continuation<? super kotlin.Unit>, Object>> get(@NotNull Class<? extends IStmtCB> cls, @NotNull SootMethod method) {
        Object obj;
        Object obj2;
        List r;
        Object obj3;
        Intrinsics.checkNotNullParameter(cls, "x");
        Intrinsics.checkNotNullParameter(method, "method");
        String subSignature = method.getSubSignature();
        SootClass sootClass = method.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(sootClass, "getDeclaringClass(...)");
        Intrinsics.checkNotNull(subSignature);
        Sequence $this$firstOrNull$iv = SootUtilsKt.findMethodOrNull(sootClass, subSignature);
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            SootMethod sm = (SootMethod) element$iv;
            if (getRaw(cls, sm) != null) {
                obj = element$iv;
                break;
            }
        }
        SootMethod targetCB = (SootMethod) obj;
        if (targetCB == null || ((!Intrinsics.areEqual(targetCB, method) && targetCB.isConcrete()) || (r = getRaw(cls, targetCB)) == null)) {
            synchronized (this.miss) {
                Map $this$getOrPut$iv = this.miss;
                Object value$iv = $this$getOrPut$iv.get(cls);
                if (value$iv == null) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    $this$getOrPut$iv.put(cls, linkedHashSet);
                    obj2 = linkedHashSet;
                } else {
                    obj2 = value$iv;
                }
                ((Set) obj2).add(method);
            }
            return null;
        }
        synchronized (this.hit) {
            Map $this$getOrPut$iv2 = this.hit;
            Object value$iv2 = $this$getOrPut$iv2.get(cls);
            if (value$iv2 == null) {
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                $this$getOrPut$iv2.put(cls, linkedHashSet2);
                obj3 = linkedHashSet2;
            } else {
                obj3 = value$iv2;
            }
            ((Set) obj3).add(TuplesKt.to(method, targetCB));
        }
        return r;
    }

    @Nullable
    public <typeCB> List<Function2<typeCB, Continuation<? super kotlin.Unit>, Object>> getRaw(@NotNull Class<? extends IStmtCB> cls, @NotNull SootMethod key) {
        Intrinsics.checkNotNullParameter(cls, "x");
        Intrinsics.checkNotNullParameter(key, "key");
        Map<SootMethod, List<Object>> map = this.callBacksOfMethod.get(cls);
        if (map != null) {
            return (List) map.get(key);
        }
        return null;
    }

    public final /* synthetic */ <typeCB extends IStmtCB> void put(Unit key, Function1<? super typeCB, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(function1, "cb");
        Intrinsics.reifiedOperationMarker(4, "typeCB");
        put((Class<? extends IStmtCB>) x, key, function1);
    }

    public final <typeCB> void put(@NotNull Class<? extends IStmtCB> cls, @NotNull Unit key, @NotNull Function1<? super typeCB, kotlin.Unit> function1) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(cls, "x");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(function1, "cb");
        Map $this$getOrPut$iv = this.callBacksOfUnit;
        Object value$iv = $this$getOrPut$iv.get(cls);
        if (value$iv == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            $this$getOrPut$iv.put(cls, linkedHashMap);
            obj = linkedHashMap;
        } else {
            obj = value$iv;
        }
        Map map = (Map) obj;
        Object value$iv2 = map.get(key);
        if (value$iv2 == null) {
            ArrayList arrayList = new ArrayList();
            map.put(key, arrayList);
            obj2 = arrayList;
        } else {
            obj2 = value$iv2;
        }
        ((List) obj2).add(function1);
    }

    @Nullable
    public <typeCB> List<Function2<typeCB, Continuation<? super kotlin.Unit>, Object>> get(@NotNull Class<? extends IStmtCB> cls, @NotNull Unit key) {
        Intrinsics.checkNotNullParameter(cls, "x");
        Intrinsics.checkNotNullParameter(key, "key");
        Map<Unit, List<Object>> map = this.callBacksOfUnit.get(cls);
        if (map != null) {
            return (List) map.get(key);
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public final /* synthetic */ <typeCB extends IStmtCB> List<Function2<typeCB, Continuation<? super kotlin.Unit>, Object>> get(Object key) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.reifiedOperationMarker(4, "typeCB");
        if (key instanceof Unit) {
            return get((Class<? extends IStmtCB>) x, (Unit) key);
        }
        if (key instanceof SootMethod) {
            return get((Class<? extends IStmtCB>) x, (SootMethod) key);
        }
        throw new NotImplementedError("An operation is not implemented: " + (key.getClass() + ": " + key));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public final /* synthetic */ <typeCB extends IStmtCB> List<Function2<typeCB, Continuation<? super kotlin.Unit>, Object>> get(Object... keys) throws NotImplementedError {
        List list;
        Intrinsics.checkNotNullParameter(keys, "keys");
        for (Object key : keys) {
            Intrinsics.reifiedOperationMarker(4, "typeCB");
            if (key instanceof Unit) {
                list = get((Class<? extends IStmtCB>) x$iv, (Unit) key);
            } else {
                if (!(key instanceof SootMethod)) {
                    throw new NotImplementedError("An operation is not implemented: " + (key.getClass() + ": " + key));
                }
                list = get((Class<? extends IStmtCB>) x$iv, (SootMethod) key);
            }
            List r = list;
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    @Nullable
    public final UnitGraph putUnitGraphOverride(@NotNull SootMethod key, @NotNull UnitGraph override) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(override, "override");
        return this.jimpleOverride.put(key, override);
    }

    @Nullable
    public final UnitGraph getUnitGraphOverride(@NotNull SootMethod key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.jimpleOverride.get(key);
    }

    public final void reportMissSummaryMethod(@NotNull Function1<? super SootMethod, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "reportMissingMethod");
        Iterable $this$mapTo$iv = CollectionsKt.flatten(this.hit.values());
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            Pair it = (Pair) item$iv;
            destination$iv.add((SootMethod) it.getFirst());
        }
        Set hit = (Set) destination$iv;
        Set miss = CollectionsKt.toSet(CollectionsKt.flatten(this.miss.values()));
        for (SootMethod m : SetsKt.minus(miss, hit)) {
            function1.invoke(m);
        }
    }
}
