package cn.sast.api.report;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: Counter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028��2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00070\f¢\u0006\u0002\u0010\rJ\u0013\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028��¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00028��¢\u0006\u0002\u0010\u0011J(\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00100\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00070\u0013H\u0002J\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\tJ\u0006\u0010\u001b\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u001c"}, d2 = {"Lcn/sast/api/report/Counter;", "T", "", "<init>", "()V", "statistics", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/atomic/AtomicInteger;", "count", "", "item", "map", "", "(Ljava/lang/Object;Ljava/util/Map;)V", "(Ljava/lang/Object;)V", "get", "", "(Ljava/lang/Object;)I", "sortMap", "", "input", "writeResults", "file", "Lcn/sast/common/IResFile;", "isNotEmpty", "", "clear", "size", "corax-api"})
@SourceDebugExtension({"SMAP\nCounter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Counter.kt\ncn/sast/api/report/Counter\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,53:1\n462#2:54\n412#2:55\n1246#3,4:56\n1062#3:60\n1216#3,2:61\n1246#3,4:63\n1053#3:67\n*S KotlinDebug\n*F\n+ 1 Counter.kt\ncn/sast/api/report/Counter\n*L\n26#1:54\n26#1:55\n26#1:56,4\n26#1:60\n27#1:61,2\n27#1:63,4\n36#1:67\n*E\n"})
/* loaded from: Counter.class */
public final class Counter<T> {

    @NotNull
    private ConcurrentHashMap<T, AtomicInteger> statistics = new ConcurrentHashMap<>();

    public final void count(T t, @NotNull Map<T, AtomicInteger> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicInteger old = map.putIfAbsent(t, atomicInteger);
        if (old != null) {
            atomicInteger = old;
        }
        atomicInteger.incrementAndGet();
    }

    public final void count(T t) {
        count(t, this.statistics);
    }

    public final int get(T t) {
        AtomicInteger atomicInteger = this.statistics.get(t);
        if (atomicInteger != null) {
            return atomicInteger.get();
        }
        return 0;
    }

    private final Map<T, Integer> sortMap(Map<T, ? extends AtomicInteger> map) {
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterable $this$associateByTo$iv$iv$iv = map.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv.put(it$iv$iv.getKey(), Integer.valueOf(((AtomicInteger) it.getValue()).get()));
        }
        Iterable $this$sortedByDescending$iv = destination$iv$iv.entrySet();
        Iterable $this$associateBy$iv = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: cn.sast.api.report.Counter$sortMap$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Map.Entry it2 = (Map.Entry) t2;
                Map.Entry it3 = (Map.Entry) t;
                return ComparisonsKt.compareValues((Integer) it2.getValue(), (Integer) it3.getValue());
            }
        });
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv2 = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            Map.Entry it2 = (Map.Entry) element$iv$iv;
            Map.Entry it3 = (Map.Entry) element$iv$iv;
            destination$iv$iv2.put(it2.getKey(), Integer.valueOf(((Number) it3.getValue()).intValue()));
        }
        return destination$iv$iv2;
    }

    public final void writeResults(@NotNull IResFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Map statistics = sortMap(this.statistics);
        if (statistics.isEmpty()) {
            return;
        }
        file.mkdirs();
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(file.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), Charsets.UTF_8);
        Throwable th = null;
        try {
            try {
                OutputStreamWriter writer = outputStreamWriter;
                writer.write("--------sort--------\n");
                Iterable $this$sortedBy$iv = statistics.keySet();
                for (Object item : CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.api.report.Counter$writeResults$lambda$6$$inlined$sortedBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(String.valueOf(t), String.valueOf(t2));
                    }
                })) {
                    writer.write(item + "\n");
                }
                writer.write("\n--------frequency--------\n");
                for (Map.Entry<T, Integer> entry : statistics.entrySet()) {
                    Object item2 = entry.getKey();
                    int count = entry.getValue().intValue();
                    writer.write(count + " " + item2 + "\n");
                }
                writer.flush();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStreamWriter, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStreamWriter, th);
            throw th2;
        }
    }

    public final boolean isNotEmpty() {
        return !this.statistics.isEmpty();
    }

    public final void clear() {
        this.statistics.clear();
    }

    public final int size() {
        return this.statistics.size();
    }
}
