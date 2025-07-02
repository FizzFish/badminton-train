package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HeapKVData.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018��*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B1\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0016J(\u0010\u0016\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u00182\u0010\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u001aH\u0016J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\b\u0010 \u001a\u0004\u0018\u00018��H&¢\u0006\u0002\u0010!J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010$H\u0016J+\u0010%\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010$2\u0006\u0010 \u001a\u00028��H\u0016¢\u0006\u0002\u0010&J-\u0010'\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00072\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010$2\b\u0010 \u001a\u0004\u0018\u00018��H\u0016¢\u0006\u0002\u0010&J\u0015\u0010(\u001a\u00020)2\u0006\u0010 \u001a\u00028��H\u0016¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H\u0016J\b\u0010-\u001a\u00020)H\u0016J\b\u0010.\u001a\u00020)H&R#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006/"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapKVData;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData;", "map", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getMap", "()Lkotlinx/collections/immutable/PersistentMap;", "getUnreferenced", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hashCode", "", "Ljava/lang/Integer;", "reference", "", "res", "", "diff", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "equals", "", "other", "computeHash", "isValidKey", "key", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "getFromNullKey", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "getValue", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "get", "ppKey", "", "(Ljava/lang/Object;)Ljava/lang/String;", "ppValue", "value", "toString", "getName", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nHeapKVData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapKVData.kt\ncn/sast/dataflow/interprocedural/analysis/HeapKVData\n+ 2 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstractKt\n*L\n1#1,248:1\n380#2,3:249\n*S KotlinDebug\n*F\n+ 1 HeapKVData.kt\ncn/sast/dataflow/interprocedural/analysis/HeapKVData\n*L\n111#1:249,3\n*E\n"})
/* loaded from: HeapKVData.class */
public abstract class HeapKVData<K, V> implements IHeapKVData<K, V> {

    @NotNull
    private final PersistentMap<K, IHeapValues<V>> map;

    @Nullable
    private final IHeapValues<V> unreferenced;

    @Nullable
    private Integer hashCode;

    @Nullable
    public abstract Boolean isValidKey(@Nullable K k);

    @NotNull
    public abstract String getName();

    /* JADX WARN: Multi-variable type inference failed */
    public HeapKVData(@NotNull PersistentMap<K, ? extends IHeapValues<V>> persistentMap, @Nullable IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(persistentMap, "map");
        this.map = persistentMap;
        this.unreferenced = iHeapValues;
        IHeapValues<V> iHeapValues2 = this.unreferenced;
        if (iHeapValues2 != null) {
            iHeapValues2.isNotEmpty();
        }
    }

    @NotNull
    public final PersistentMap<K, IHeapValues<V>> getMap() {
        return this.map;
    }

    @Nullable
    public final IHeapValues<V> getUnreferenced() {
        return this.unreferenced;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public void reference(@NotNull Collection<V> collection) {
        Intrinsics.checkNotNullParameter(collection, "res");
        for (Map.Entry f : this.map.entrySet()) {
            ((IHeapValues) f.getValue()).reference(collection);
        }
        IHeapValues<V> iHeapValues = this.unreferenced;
        if (iHeapValues != null) {
            iHeapValues.reference(collection);
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
        if (iDiffAble instanceof HeapKVData) {
            for (Object k : CollectionsKt.intersect(this.map.keySet(), ((HeapKVData) iDiffAble).map.keySet())) {
                Object obj = this.map.get(k);
                Intrinsics.checkNotNull(obj);
                Object obj2 = ((HeapKVData) iDiffAble).map.get(k);
                Intrinsics.checkNotNull(obj2);
                ((IHeapValues) obj).diff(iDiff, (IDiffAble) obj2);
            }
            if (this.unreferenced != null && ((HeapKVData) iDiffAble).unreferenced != null) {
                this.unreferenced.diff(iDiff, ((HeapKVData) iDiffAble).unreferenced);
            }
        }
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HeapKVData) && hashCode() == ((HeapKVData) other).hashCode() && Intrinsics.areEqual(this.map, ((HeapKVData) other).map) && Intrinsics.areEqual(this.unreferenced, ((HeapKVData) other).unreferenced);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        int result = (31 * 1) + this.map.hashCode();
        int i = 31 * result;
        IHeapValues<V> iHeapValues = this.unreferenced;
        int result2 = i + (iHeapValues != null ? iHeapValues.hashCode() : 0);
        return result2;
    }

    public int hashCode() {
        Integer hash = this.hashCode;
        if (hash == null) {
            hash = Integer.valueOf(computeHash());
            this.hashCode = hash;
        }
        return hash.intValue();
    }

    @NotNull
    public IHeapValues<V> getFromNullKey(@NotNull IHeapValuesFactory<V> iHeapValuesFactory) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        IHeapValues.Builder b = iHeapValuesFactory.emptyBuilder();
        Map $this$mapTo$iv = this.map;
        for (Map.Entry item$iv : $this$mapTo$iv.entrySet()) {
            b.add((IHeapValues) item$iv.getValue());
        }
        if (this.unreferenced != null) {
            b.add(this.unreferenced);
        }
        return b.build();
    }

    @Nullable
    public IHeapValues<V> getValue(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @NotNull K k) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(k, "key");
        return (IHeapValues) this.map.get(k);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapKVData
    @Nullable
    public IHeapValues<V> get(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @Nullable K k) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        if (k != null) {
            IHeapValues exist = getValue(iHeapValuesFactory, k);
            if (exist != null) {
                if (this.unreferenced != null) {
                    return exist.plus(this.unreferenced);
                }
                return exist;
            }
            return this.unreferenced;
        }
        return getFromNullKey(iHeapValuesFactory);
    }

    @NotNull
    public String ppKey(@NotNull K k) {
        Intrinsics.checkNotNullParameter(k, "key");
        return k.toString();
    }

    @NotNull
    public String ppValue(@NotNull IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        return iHeapValues.toString();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder(getName()).append(" ");
        for (Map.Entry<K, V> entry : this.map.entrySet()) {
            K key = entry.getKey();
            IHeapValues<V> v = (IHeapValues) entry.getValue();
            IHeapValues<V> value = this.unreferenced == null ? v : v.plus(this.unreferenced);
            sb.append(ppKey(key)).append("->").append(ppValue(value)).append(" ; ");
        }
        if (this.map.isEmpty()) {
            sb.append("unreferenced: " + this.unreferenced);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
