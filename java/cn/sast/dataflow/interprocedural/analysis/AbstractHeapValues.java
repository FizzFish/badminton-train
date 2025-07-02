package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.Printer;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.jimple.Constant;
import soot.jimple.IntConstant;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0082\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001f\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n��\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\b6\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\b\u0004\u0012\u001a\b\u0002\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0011\u001a\u00020\u000bJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J(\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028��0\u00192\u0010\u0010\u001a\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150\u001bH\u0016J\u0016\u0010(\u001a\u00020\u00172\f\u0010)\u001a\b\u0012\u0004\u0012\u00028��0*H\u0016J\b\u0010+\u001a\u00020\u0013H\u0016J\b\u0010,\u001a\u00020\u0013H\u0016J\b\u0010-\u001a\u00020\u0013H\u0016J\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u00022\f\u0010/\u001a\b\u0012\u0004\u0012\u00028��0\u0002H\u0096\u0002J\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u00022\f\u0010/\u001a\b\u0012\u0004\u0012\u00028��0\u0005H\u0096\u0002J6\u0010\u0003\u001a\u00020\u00172\f\u00100\u001a\b\u0012\u0004\u0012\u00028��012\u001e\u00102\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000503H\u0016J<\u00104\u001a\u00020\u00172\f\u00100\u001a\b\u0012\u0004\u0012\u00028��012$\u00102\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0005\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00050503H\u0016J\u0018\u00106\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001072\u0006\u00108\u001a\u00020\u0013H\u0016J\u0015\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00050:H\u0096\u0002J\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00028��0\u00022\f\u0010<\u001a\b\u0012\u0004\u0012\u00028��0=H\u0016J\b\u0010>\u001a\u00020?H\u0016R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00050\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00028��0#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R \u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00050#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%\u0082\u0001\u0001@¨\u0006A"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValues;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "map", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;)V", "getMap", "()Lkotlinx/collections/immutable/PersistentMap;", "hashCode", "", "getHashCode", "()Ljava/lang/Integer;", "setHashCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "computeHash", "equals", "", "other", "", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "single", "getSingle", "()Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "size", "getSize", "()I", "values", "Lkotlinx/collections/immutable/ImmutableSet;", "getValues", "()Lkotlinx/collections/immutable/ImmutableSet;", "valuesCompanion", "getValuesCompanion", "reference", "res", "", "isNotEmpty", "isEmpty", "isSingle", "plus", "rhs", "c", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "transform", "Lkotlin/Function1;", "flatMap", "", "getAllIntValue", "", "must", "iterator", "", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "toString", "", "Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPointsToGraphAbstract.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/AbstractHeapValues\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1045:1\n1#2:1046\n1#2:1049\n1619#3:1047\n1863#3:1048\n1864#3:1050\n1620#3:1051\n*S KotlinDebug\n*F\n+ 1 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/AbstractHeapValues\n*L\n251#1:1049\n251#1:1047\n251#1:1048\n251#1:1050\n251#1:1051\n*E\n"})
/* loaded from: AbstractHeapValues.class */
public abstract class AbstractHeapValues<V> implements IHeapValues<V> {

    @NotNull
    private final PersistentMap<V, CompanionV<V>> map;

    @Nullable
    private Integer hashCode;

    public /* synthetic */ AbstractHeapValues(PersistentMap map, DefaultConstructorMarker $constructor_marker) {
        this(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AbstractHeapValues(PersistentMap<V, ? extends CompanionV<V>> persistentMap) {
        this.map = persistentMap;
    }

    public /* synthetic */ AbstractHeapValues(PersistentMap persistentMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExtensionsKt.persistentHashMapOf() : persistentMap, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @Nullable
    public Integer getMaxInt(boolean must) {
        return IHeapValues.DefaultImpls.getMaxInt(this, must);
    }

    @NotNull
    public final PersistentMap<V, CompanionV<V>> getMap() {
        return this.map;
    }

    @Nullable
    public final Integer getHashCode() {
        return this.hashCode;
    }

    public final void setHashCode(@Nullable Integer num) {
        this.hashCode = num;
    }

    public final int computeHash() {
        int result = (31 * 1) + this.map.hashCode();
        return result;
    }

    public int hashCode() {
        Integer h = this.hashCode;
        if (h == null) {
            h = Integer.valueOf(computeHash());
            this.hashCode = h;
        }
        return h.intValue();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AbstractHeapValues) && hashCode() == ((AbstractHeapValues) other).hashCode() && Intrinsics.areEqual(this.map, ((AbstractHeapValues) other).map);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
        if (iDiffAble instanceof AbstractHeapValues) {
            for (Object k : CollectionsKt.intersect(this.map.keySet(), ((AbstractHeapValues) iDiffAble).map.keySet())) {
                Object obj = this.map.get(k);
                Intrinsics.checkNotNull(obj);
                Object obj2 = ((AbstractHeapValues) iDiffAble).map.get(k);
                Intrinsics.checkNotNull(obj2);
                iDiff.diff((CompanionV) obj, (CompanionV) obj2);
            }
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public CompanionV<V> getSingle() {
        if (!isSingle()) {
            throw new IllegalArgumentException(("error size of " + this).toString());
        }
        return iterator().next();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public int getSize() {
        return this.map.size();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public ImmutableSet<V> getValues() {
        return this.map.keySet();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public ImmutableSet<CompanionV<V>> getValuesCompanion() {
        Set r = new LinkedHashSet();
        Iterator<CompanionV<V>> it = iterator();
        while (it.hasNext()) {
            CompanionV e = it.next();
            r.add(e);
        }
        return ExtensionsKt.toImmutableSet(r);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public void reference(@NotNull Collection<V> collection) {
        Intrinsics.checkNotNullParameter(collection, "res");
        collection.addAll(this.map.keySet());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public boolean isNotEmpty() {
        return !this.map.isEmpty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public boolean isSingle() {
        return this.map.size() == 1;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public IHeapValues<V> plus(@NotNull IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "rhs");
        if (iHeapValues.isEmpty()) {
            return this;
        }
        if (isEmpty()) {
            return iHeapValues;
        }
        IHeapValues.Builder it = builder();
        it.add(iHeapValues);
        return it.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public IHeapValues<V> plus(@NotNull CompanionV<V> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "rhs");
        IHeapValues.Builder it = builder();
        it.add(companionV);
        return it.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public void map(@NotNull IHeapValues.Builder<V> builder, @NotNull Function1<? super CompanionV<V>, ? extends CompanionV<V>> function1) {
        Intrinsics.checkNotNullParameter(builder, "c");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Iterator<CompanionV<V>> it = iterator();
        while (it.hasNext()) {
            CompanionV e = it.next();
            builder.add((CompanionV) function1.invoke(e));
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    public void flatMap(@NotNull IHeapValues.Builder<V> builder, @NotNull Function1<? super CompanionV<V>, ? extends Collection<? extends CompanionV<V>>> function1) {
        Intrinsics.checkNotNullParameter(builder, "c");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Iterator<CompanionV<V>> it = iterator();
        while (it.hasNext()) {
            CompanionV e = it.next();
            for (CompanionV t : (Collection) function1.invoke(e)) {
                builder.add(t);
            }
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @Nullable
    public Set<Integer> getAllIntValue(boolean must) {
        Iterable $this$mapNotNullTo$iv = this.map.keySet();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            ConstVal constVal = element$iv$iv instanceof ConstVal ? (ConstVal) element$iv$iv : null;
            Constant v = constVal != null ? constVal.getV() : null;
            IntConstant intConstant = v instanceof IntConstant ? (IntConstant) v : null;
            Integer num = intConstant != null ? Integer.valueOf(intConstant.value) : null;
            if (must && num == null) {
                return null;
            }
            if (num != null) {
                destination$iv.add(num);
            }
        }
        return (Set) destination$iv;
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0017\n��\n\u0002\u0010(\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001��\b\n\u0018��2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0002J\u000f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0002H\u0096\u0002¨\u0006\u0006"}, d2 = {"cn/sast/dataflow/interprocedural/analysis/AbstractHeapValues$iterator$1", "", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "hasNext", "", "next", "corax-data-flow"})
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.AbstractHeapValues$iterator$1, reason: invalid class name */
    /* loaded from: AbstractHeapValues$iterator$1.class */
    public static final class AnonymousClass1 implements Iterator<CompanionV<V>>, KMappedMarker {
        final /* synthetic */ Iterator<Map.Entry<V, CompanionV<V>>> $mi;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Iterator<? extends Map.Entry<? extends V, ? extends CompanionV<V>>> it) {
            this.$mi = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.$mi.hasNext();
        }

        @Override // java.util.Iterator
        public CompanionV<V> next() {
            return this.$mi.next().getValue();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public Iterator<CompanionV<V>> iterator() {
        Iterator mi = this.map.entrySet().iterator();
        return new AnonymousClass1(mi);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public IHeapValues<V> cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        IHeapValues.Builder b = builder();
        b.cloneAndReNewObjects(iReNew);
        return b.build();
    }

    @NotNull
    public String toString() {
        return Printer.Companion.nodes2String(this.map.values());
    }
}
