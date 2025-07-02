package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: kotlin-ext.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a0\u0010��\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001\u001a+\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b��\u0010\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\b\"\u0002H\u0006¢\u0006\u0002\u0010\t\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b��\u0010\u0006¨\u0006\n"}, d2 = {"nonNullValue", "", "K", "V", "concurrentHashSetOf", "", "E", "pairs", "", "([Ljava/lang/Object;)Ljava/util/Set;", "corax-api"})
@SourceDebugExtension({"SMAP\nkotlin-ext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 kotlin-ext.kt\ncn/sast/api/util/Kotlin_extKt\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,13:1\n136#2,9:14\n216#2:23\n217#2:25\n145#2:26\n1#3:24\n9326#4,2:27\n9476#4,4:29\n*S KotlinDebug\n*F\n+ 1 kotlin-ext.kt\ncn/sast/api/util/Kotlin_extKt\n*L\n7#1:14,9\n7#1:23\n7#1:25\n7#1:26\n7#1:24\n10#1:27,2\n10#1:29,4\n*E\n"})
/* loaded from: Kotlin_extKt.class */
public final class Kotlin_extKt {
    @NotNull
    public static final <K, V> Map<K, V> nonNullValue(@NotNull Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Collection destination$iv$iv = new ArrayList();
        for (Map.Entry element$iv$iv$iv : map.entrySet()) {
            K key = element$iv$iv$iv.getKey();
            V value = element$iv$iv$iv.getValue();
            Pair pair = value == null ? null : TuplesKt.to(key, value);
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
        }
        return MapsKt.toMap((List) destination$iv$iv);
    }

    @NotNull
    public static final <E> Set<E> concurrentHashSetOf(@NotNull E... eArr) {
        Intrinsics.checkNotNullParameter(eArr, "pairs");
        LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(eArr.length), 16));
        for (E e : eArr) {
            result$iv.put(e, true);
        }
        Set<E> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap(result$iv));
        Intrinsics.checkNotNullExpressionValue(setNewSetFromMap, "newSetFromMap(...)");
        return setNewSetFromMap;
    }

    @NotNull
    public static final <E> Set<E> concurrentHashSetOf() {
        Set<E> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkNotNullExpressionValue(setNewSetFromMap, "newSetFromMap(...)");
        return setNewSetFromMap;
    }
}
