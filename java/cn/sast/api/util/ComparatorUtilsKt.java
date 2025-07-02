package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ComparatorUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000f\n��\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0006\u001a<\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0003j\b\u0012\u0004\u0012\u0002H\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001az\u0010\b\u001a\u00020\u0001\"\u000e\b��\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\n\"\u000e\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\n**\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\f0\u0003j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\f`\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\r\u001aJ\u0010\b\u001a\u00020\u0001\"\u000e\b��\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\n\"\u000e\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\n*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\r\u001a.\u0010\u000f\u001a\u00020\u0001\"\u000e\b��\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\n*\b\u0012\u0004\u0012\u0002H\u00100\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0006\u001a+\u0010\u0011\u001a\u00020\u0001\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n*\u0004\u0018\u0001H\u00022\b\u0010\u000e\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"compareTo", "", "T", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "a", "", "b", "compareToMap", "K", "", "V", "Lkotlin/Pair;", "", "other", "compareToCollection", "E", "compareToNullable", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "corax-api"})
@SourceDebugExtension({"SMAP\nComparatorUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ComparatorUtils.kt\ncn/sast/api/util/ComparatorUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,37:1\n1#2:38\n*E\n"})
/* loaded from: ComparatorUtilsKt.class */
public final class ComparatorUtilsKt {
    public static final <T> int compareTo(@NotNull Comparator<T> comparator, @NotNull Collection<? extends T> collection, @NotNull Collection<? extends T> collection2) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(collection, "a");
        Intrinsics.checkNotNullParameter(collection2, "b");
        Iterator bI = collection2.iterator();
        for (T t : collection) {
            if (!bI.hasNext()) {
                return 1;
            }
            int c = comparator.compare(t, bI.next());
            if (c != 0) {
                return c;
            }
        }
        return bI.hasNext() ? -1 : 0;
    }

    public static final <K extends Comparable<? super K>, V extends Comparable<? super V>> int compareToMap(@NotNull Comparator<Pair<K, V>> comparator, @NotNull Map<K, ? extends V> map, @NotNull Map<K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(comparator, "<this>");
        Intrinsics.checkNotNullParameter(map, "a");
        Intrinsics.checkNotNullParameter(map2, "b");
        Integer numValueOf = Integer.valueOf(Intrinsics.compare(map.size(), map2.size()));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        List aSorted = CollectionsKt.sortedWith(MapsKt.toList(map), comparator);
        List bSorted = CollectionsKt.sortedWith(MapsKt.toList(map2), comparator);
        return compareTo(comparator, aSorted, bSorted);
    }

    public static final <K extends Comparable<? super K>, V extends Comparable<? super V>> int compareToMap(@NotNull Map<K, ? extends V> map, @NotNull Map<K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(map2, "other");
        Comparator comparator = ComparisonsKt.then(new Comparator() { // from class: cn.sast.api.util.ComparatorUtilsKt$compareToMap$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it = (Pair) t;
                Pair it2 = (Pair) t2;
                return ComparisonsKt.compareValues((Comparable) it.getFirst(), (Comparable) it2.getFirst());
            }
        }, new Comparator() { // from class: cn.sast.api.util.ComparatorUtilsKt$compareToMap$$inlined$compareBy$2
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it = (Pair) t;
                Pair it2 = (Pair) t2;
                return ComparisonsKt.compareValues((Comparable) it.getSecond(), (Comparable) it2.getSecond());
            }
        });
        return compareToMap(comparator, map, map2);
    }

    public static final <E extends Comparable<? super E>> int compareToCollection(@NotNull Collection<? extends E> collection, @NotNull Collection<? extends E> collection2) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(collection2, "other");
        return compareTo(new Comparator() { // from class: cn.sast.api.util.ComparatorUtilsKt$compareToCollection$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Comparable it = (Comparable) t;
                Comparable it2 = (Comparable) t2;
                return ComparisonsKt.compareValues(it, it2);
            }
        }, collection, collection2);
    }

    public static final <T extends Comparable<? super T>> int compareToNullable(@Nullable T t, @Nullable T t2) {
        if (t == null && t2 == null) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        Integer numValueOf = Integer.valueOf(t.compareTo(t2));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num == null) {
            return 0;
        }
        int it2 = num.intValue();
        return it2;
    }
}
