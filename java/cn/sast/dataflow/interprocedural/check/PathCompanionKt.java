package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.MergePath;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��>\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u001c\u0010\u0013\u001a\u00020\u0014*\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u00152\u0006\u0010\u0016\u001a\u00020\u0017\"#\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006*\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"<\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000b\"\u0004\b��\u0010\f\"\u0004\b\u0001\u0010\r*\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"*\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b��\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0012*\f\b��\u0010��\"\u00020\u00012\u00020\u0001*\f\b��\u0010\u0002\"\u00020\u00032\u00020\u0003¨\u0006\u0018"}, d2 = {"CompanionValueImpl", "Lcn/sast/dataflow/interprocedural/check/CompanionValueImpl1;", "CompanionValueImplConst", "Lcn/sast/dataflow/interprocedural/check/CompanionValueOfConst;", "bindDelegate", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "getBindDelegate", "(Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/IValue;", "short", "", "K", "V", "getShort", "(Ljava/util/Map;)Ljava/util/Map;", "", "E", "(Ljava/util/Set;)Ljava/util/Set;", "path", "Lcn/sast/dataflow/interprocedural/check/IPath;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathCompanion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PathCompanionKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,994:1\n1628#2,3:995\n*S KotlinDebug\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PathCompanionKt\n*L\n643#1:995,3\n*E\n"})
/* loaded from: PathCompanionKt.class */
public final class PathCompanionKt {
    @NotNull
    public static final IValue getBindDelegate(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "<this>");
        if (companionV instanceof CompanionValueOfConst) {
            return ((CompanionValueOfConst) companionV).getAttr();
        }
        return companionV.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<K, V> getShort(Map<K, ? extends V> map) {
        int sz = map.size();
        switch (sz) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                Map<K, V> mapEmptyMap = Collections.emptyMap();
                Intrinsics.checkNotNull(mapEmptyMap);
                return mapEmptyMap;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                Object k = entry.getKey();
                Object v = entry.getValue();
                Map<K, V> mapSingletonMap = Collections.singletonMap(k, v);
                Intrinsics.checkNotNull(mapSingletonMap);
                return mapSingletonMap;
            default:
                return map;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <E> Set<E> getShort(Set<? extends E> set) {
        int sz = set.size();
        switch (sz) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                Set<E> setEmptySet = Collections.emptySet();
                Intrinsics.checkNotNull(setEmptySet);
                return setEmptySet;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                Object e = set.iterator().next();
                Set<E> setSingleton = Collections.singleton(e);
                Intrinsics.checkNotNull(setSingleton);
                return setSingleton;
            default:
                return set;
        }
    }

    @NotNull
    public static final IPath path(@NotNull IHeapValues<IValue> iHeapValues, @NotNull HeapValuesEnv env) {
        Intrinsics.checkNotNullParameter(iHeapValues, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        MergePath.Companion companion = MergePath.Companion;
        Iterable $this$mapTo$iv = iHeapValues.getValuesCompanion();
        Collection destination$iv = new HashSet(iHeapValues.getValuesCompanion().size());
        for (Object item$iv : $this$mapTo$iv) {
            Object obj = (CompanionV) item$iv;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PathCompanionV");
            destination$iv.add(((PathCompanionV) obj).getPath());
        }
        return companion.v(env, (Set<? extends IPath>) destination$iv);
    }
}
