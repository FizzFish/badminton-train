package cn.sast.dataflow.interprocedural.check;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0010 \n\u0002\b\u0004\"'\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"removeAdjacentDuplicates", "", "E", "getRemoveAdjacentDuplicates", "(Ljava/util/List;)Ljava/util/List;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,213:1\n774#2:214\n865#2,2:215\n1557#2:217\n1628#2,3:218\n*S KotlinDebug\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorKt\n*L\n22#1:214\n22#1:215,2\n23#1:217\n23#1:218,3\n*E\n"})
/* loaded from: PathGeneratorKt.class */
public final class PathGeneratorKt {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <E> List<E> getRemoveAdjacentDuplicates(@NotNull List<? extends E> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return list;
        }
        Iterable $this$filter$iv = CollectionsKt.zipWithNext(list);
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Pair it = (Pair) element$iv$iv;
            if (!Intrinsics.areEqual(it.getFirst(), it.getSecond())) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$map$iv = arrayList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            arrayList2.add(((Pair) item$iv$iv).getFirst());
        }
        return CollectionsKt.plus(arrayList2, CollectionsKt.last(list));
    }
}
