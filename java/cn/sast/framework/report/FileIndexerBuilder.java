package cn.sast.framework.report;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.util.ArraySet;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\b\u0016\u0018�� \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0016\u001a\u00020\u000fR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n��R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n��R+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u0004\b��\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u00078Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcn/sast/framework/report/FileIndexerBuilder;", "", "<init>", "()V", "fileNameToPathMap", "Ljava/util/concurrent/ConcurrentNavigableMap;", "", "Ljava/util/NavigableSet;", "Lcn/sast/common/IResFile;", "extensionToPathMap", "addIndexMap", "", "resFile", "union", "indexer", "Lcn/sast/framework/report/FileIndexer;", "build", "compressToSet", "", "E", "getCompressToSet", "(Ljava/util/NavigableSet;)Ljava/util/Set;", "sortAndOptimizeMem", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nJavaSourceLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/FileIndexerBuilder\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n267#1,5:507\n267#1,5:517\n72#2,2:491\n72#2,2:494\n72#2,2:497\n72#2,2:500\n1#3:493\n1#3:496\n1#3:499\n1#3:502\n462#4:503\n412#4:504\n462#4:513\n412#4:514\n1246#5,2:505\n1249#5:512\n1246#5,2:515\n1249#5:522\n*S KotlinDebug\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/FileIndexerBuilder\n*L\n277#1:507,5\n278#1:517,5\n248#1:491,2\n249#1:494,2\n254#1:497,2\n257#1:500,2\n248#1:493\n249#1:496\n254#1:499\n257#1:502\n277#1:503\n277#1:504\n278#1:513\n278#1:514\n277#1:505,2\n277#1:512\n278#1:515,2\n278#1:522\n*E\n"})
/* loaded from: FileIndexerBuilder.class */
public class FileIndexerBuilder {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final ConcurrentNavigableMap<String, NavigableSet<IResFile>> fileNameToPathMap = new ConcurrentSkipListMap();

    @NotNull
    private final ConcurrentNavigableMap<String, NavigableSet<IResFile>> extensionToPathMap = new ConcurrentSkipListMap();

    public final void addIndexMap(@NotNull IResFile resFile) {
        Intrinsics.checkNotNullParameter(resFile, "resFile");
        if (resFile.isFile()) {
            ConcurrentMap $this$getOrPut$iv = this.fileNameToPathMap;
            String name = resFile.getName();
            NavigableSet<IResFile> navigableSetPutIfAbsent = $this$getOrPut$iv.get(name);
            if (navigableSetPutIfAbsent == null) {
                ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
                navigableSetPutIfAbsent = $this$getOrPut$iv.putIfAbsent(name, concurrentSkipListSet);
                if (navigableSetPutIfAbsent == null) {
                    navigableSetPutIfAbsent = concurrentSkipListSet;
                }
            }
            navigableSetPutIfAbsent.add(resFile);
            ConcurrentMap $this$getOrPut$iv2 = this.extensionToPathMap;
            String extension = resFile.getExtension();
            NavigableSet<IResFile> navigableSetPutIfAbsent2 = $this$getOrPut$iv2.get(extension);
            if (navigableSetPutIfAbsent2 == null) {
                ConcurrentSkipListSet concurrentSkipListSet2 = new ConcurrentSkipListSet();
                navigableSetPutIfAbsent2 = $this$getOrPut$iv2.putIfAbsent(extension, concurrentSkipListSet2);
                if (navigableSetPutIfAbsent2 == null) {
                    navigableSetPutIfAbsent2 = concurrentSkipListSet2;
                }
            }
            navigableSetPutIfAbsent2.add(resFile);
        }
    }

    public final void union(@NotNull FileIndexer indexer) {
        Intrinsics.checkNotNullParameter(indexer, "indexer");
        for (Map.Entry<String, Set<IResFile>> entry : indexer.getFileNameToPathMap$corax_framework().entrySet()) {
            String k = entry.getKey();
            Set v = entry.getValue();
            ConcurrentMap $this$getOrPut$iv = this.fileNameToPathMap;
            NavigableSet<IResFile> navigableSetPutIfAbsent = $this$getOrPut$iv.get(k);
            if (navigableSetPutIfAbsent == null) {
                ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
                navigableSetPutIfAbsent = $this$getOrPut$iv.putIfAbsent(k, concurrentSkipListSet);
                if (navigableSetPutIfAbsent == null) {
                    navigableSetPutIfAbsent = concurrentSkipListSet;
                }
            }
            navigableSetPutIfAbsent.addAll(v);
        }
        for (Map.Entry<String, Set<IResFile>> entry2 : indexer.getExtensionToPathMap$corax_framework().entrySet()) {
            String k2 = entry2.getKey();
            Set v2 = entry2.getValue();
            ConcurrentMap $this$getOrPut$iv2 = this.extensionToPathMap;
            NavigableSet<IResFile> navigableSetPutIfAbsent2 = $this$getOrPut$iv2.get(k2);
            if (navigableSetPutIfAbsent2 == null) {
                ConcurrentSkipListSet concurrentSkipListSet2 = new ConcurrentSkipListSet();
                navigableSetPutIfAbsent2 = $this$getOrPut$iv2.putIfAbsent(k2, concurrentSkipListSet2);
                if (navigableSetPutIfAbsent2 == null) {
                    navigableSetPutIfAbsent2 = concurrentSkipListSet2;
                }
            }
            navigableSetPutIfAbsent2.addAll(v2);
        }
    }

    @NotNull
    public final FileIndexer build() {
        return new FileIndexer(this.fileNameToPathMap, this.extensionToPathMap);
    }

    private final <E> Set<E> getCompressToSet(NavigableSet<E> navigableSet) {
        int size = navigableSet.size();
        if (size == 0) {
            return SetsKt.emptySet();
        }
        if (size == 1) {
            Set<E> setSingleton = Collections.singleton(navigableSet.first());
            Intrinsics.checkNotNullExpressionValue(setSingleton, "singleton(...)");
            return setSingleton;
        }
        boolean z = 0 <= size && size < 17;
        return z ? new ArraySet<>(navigableSet) : navigableSet;
    }

    @NotNull
    public final FileIndexer sortAndOptimizeMem() {
        NavigableSet navigableSetSingleton;
        NavigableSet navigableSetSingleton2;
        Map $this$mapValues$iv = this.fileNameToPathMap;
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
        Iterable $this$associateByTo$iv$iv$iv = $this$mapValues$iv.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            Object value = it.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            NavigableSet $this$compressToSet$iv = (NavigableSet) value;
            int size = $this$compressToSet$iv.size();
            if (size == 0) {
                navigableSetSingleton2 = SetsKt.emptySet();
            } else if (size == 1) {
                navigableSetSingleton2 = Collections.singleton($this$compressToSet$iv.first());
                Intrinsics.checkNotNullExpressionValue(navigableSetSingleton2, "singleton(...)");
            } else {
                boolean z = 0 <= size && size < 17;
                navigableSetSingleton2 = z ? (Set) new ArraySet($this$compressToSet$iv) : $this$compressToSet$iv;
            }
            destination$iv$iv.put(key, navigableSetSingleton2);
        }
        Map $this$mapValues$iv2 = this.extensionToPathMap;
        Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv2.size()));
        Iterable $this$associateByTo$iv$iv$iv2 = $this$mapValues$iv2.entrySet();
        for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
            Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
            Object key2 = it$iv$iv2.getKey();
            Map.Entry it2 = (Map.Entry) element$iv$iv$iv2;
            Object value2 = it2.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "<get-value>(...)");
            NavigableSet $this$compressToSet$iv2 = (NavigableSet) value2;
            int size2 = $this$compressToSet$iv2.size();
            if (size2 == 0) {
                navigableSetSingleton = SetsKt.emptySet();
            } else if (size2 == 1) {
                navigableSetSingleton = Collections.singleton($this$compressToSet$iv2.first());
                Intrinsics.checkNotNullExpressionValue(navigableSetSingleton, "singleton(...)");
            } else {
                boolean z2 = 0 <= size2 && size2 < 17;
                navigableSetSingleton = z2 ? (Set) new ArraySet($this$compressToSet$iv2) : $this$compressToSet$iv2;
            }
            destination$iv$iv2.put(key2, navigableSetSingleton);
        }
        return new FileIndexer(destination$iv$iv, destination$iv$iv2);
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcn/sast/framework/report/FileIndexerBuilder$Companion;", "", "<init>", "()V", "corax-framework"})
    /* loaded from: FileIndexerBuilder$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
