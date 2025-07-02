package cn.sast.dataflow.interprocedural.analysis.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IDiff;
import cn.sast.dataflow.interprocedural.analysis.IDiffAble;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;

/* compiled from: ArrayHeapKV.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u00022\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0004Ba\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u001fJ(\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028��0#2\u0010\u0010$\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010&0%H\u0016J&\u0010'\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00028��0)2\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J-\u0010*\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00028��0)2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010+J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00028��0\u0007H\u0016J\b\u0010-\u001a\u00020\u0003H\u0016J\u0013\u0010.\u001a\u00020\u001d2\b\u0010/\u001a\u0004\u0018\u00010&H\u0096\u0002J\b\u00100\u001a\u00020\u0003H\u0016J\u0010\u00101\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u001c\u00102\u001a\b\u0012\u0004\u0012\u00028��0\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00028��0)H\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019¨\u00063"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapKV;", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapKVData;", "", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/IArrayHeapKV;", "element", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "allSize", "type", "Lsoot/ArrayType;", "size", "initializedValue", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Lsoot/ArrayType;Ljava/lang/Integer;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)V", "getAllSize", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getType", "()Lsoot/ArrayType;", "getSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getInitializedValue", "()Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "getName", "", "isValidKey", "", "key", "(Ljava/lang/Integer;)Ljava/lang/Boolean;", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "", "getValue", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "get", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Ljava/lang/Integer;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getArrayLength", "computeHash", "equals", "other", "hashCode", "ppKey", "getFromNullKey", "corax-data-flow"})
/* loaded from: ArrayHeapKV.class */
public abstract class ArrayHeapKV<V> extends HeapKVData<Integer, V> implements IArrayHeapKV<Integer, V> {

    @NotNull
    private final IHeapValues<V> allSize;

    @NotNull
    private final ArrayType type;

    @Nullable
    private final Integer size;

    @Nullable
    private final CompanionV<V> initializedValue;

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public /* bridge */ /* synthetic */ IHeapValues getValue(IHeapValuesFactory hf, Integer num) {
        return getValue(hf, num.intValue());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public /* bridge */ /* synthetic */ String ppKey(Integer num) {
        return ppKey(num.intValue());
    }

    @NotNull
    public final IHeapValues<V> getAllSize() {
        return this.allSize;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public ArrayType getType() {
        return this.type;
    }

    @Nullable
    public final Integer getSize() {
        return this.size;
    }

    @Nullable
    public final CompanionV<V> getInitializedValue() {
        return this.initializedValue;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayHeapKV(@NotNull PersistentMap<Integer, ? extends IHeapValues<V>> persistentMap, @Nullable IHeapValues<V> iHeapValues, @NotNull IHeapValues<V> iHeapValues2, @NotNull ArrayType type, @Nullable Integer size, @Nullable CompanionV<V> companionV) {
        super(persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(persistentMap, "element");
        Intrinsics.checkNotNullParameter(iHeapValues2, "allSize");
        Intrinsics.checkNotNullParameter(type, "type");
        this.allSize = iHeapValues2;
        this.type = type;
        this.size = size;
        this.initializedValue = companionV;
        if (this.allSize.isNotEmpty()) {
        } else {
            throw new IllegalArgumentException("array length value set is empty".toString());
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String getName() {
        return getType().getElementType() + "[" + this.size + "]";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @Nullable
    public Boolean isValidKey(@Nullable Integer key) {
        return ArrayHeapKVKt.isValidKey(key, this.size);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
        if (iDiffAble instanceof ArrayHeapKV) {
            this.allSize.diff(iDiff, ((ArrayHeapKV) iDiffAble).allSize);
            if (this.initializedValue != null && ((ArrayHeapKV) iDiffAble).initializedValue != null) {
                iDiff.diff(this.initializedValue, ((ArrayHeapKV) iDiffAble).initializedValue);
            }
        }
        super.diff(iDiff, iDiffAble);
    }

    @Nullable
    public IHeapValues<V> getValue(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, int key) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        IHeapValues<V> value = super.getValue(iHeapValuesFactory, (IHeapValuesFactory<V>) Integer.valueOf(key));
        if (value != null) {
            return value;
        }
        if (this.size != null) {
            Integer num = this.size;
            int size = getMap().size();
            if (num != null && num.intValue() == size) {
                IHeapValues<V> unreferenced = getUnreferenced();
                boolean z = unreferenced != null && unreferenced.isNotEmpty();
                if (z && this.initializedValue != null) {
                    return iHeapValuesFactory.single(this.initializedValue);
                }
            }
        }
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IHeapKVData
    @Nullable
    public IHeapValues<V> get(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @Nullable Integer key) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        if (Intrinsics.areEqual(isValidKey(key), false)) {
            return null;
        }
        return super.get(iHeapValuesFactory, (IHeapValuesFactory<V>) key);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public IHeapValues<V> getArrayLength() {
        return this.allSize;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        int result = super.computeHash();
        return (31 * result) + this.allSize.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public boolean equals(@Nullable Object other) {
        if (super.equals(other) && (other instanceof ArrayHeapKV)) {
            return Intrinsics.areEqual(this.allSize, ((ArrayHeapKV) other).allSize);
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public int hashCode() {
        return super.hashCode();
    }

    @NotNull
    public String ppKey(int key) {
        return String.valueOf(key);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cn.sast.dataflow.interprocedural.analysis.IHeapValues<V> getFromNullKey(@org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory<V> r5) {
        /*
            r4 = this;
            r0 = r5
            java.lang.String r1 = "hf"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            r1 = r5
            cn.sast.dataflow.interprocedural.analysis.IHeapValues r0 = super.getFromNullKey(r1)
            r6 = r0
            r0 = r4
            java.lang.Integer r0 = r0.size
            if (r0 == 0) goto L30
            r0 = r4
            java.lang.Integer r0 = r0.size
            r1 = r4
            kotlinx.collections.immutable.PersistentMap r1 = r1.getMap()
            int r1 = r1.size()
            r7 = r1
            r1 = r0
            if (r1 != 0) goto L29
        L26:
            goto L30
        L29:
            int r0 = r0.intValue()
            r1 = r7
            if (r0 == r1) goto L48
        L30:
            r0 = r4
            cn.sast.dataflow.interprocedural.analysis.CompanionV<V> r0 = r0.initializedValue
            if (r0 == 0) goto L48
            r0 = r6
            r1 = r5
            r2 = r4
            cn.sast.dataflow.interprocedural.analysis.CompanionV<V> r2 = r2.initializedValue
            cn.sast.dataflow.interprocedural.analysis.IHeapValues r1 = r1.single(r2)
            cn.sast.dataflow.interprocedural.analysis.IHeapValues r0 = r0.plus(r1)
            return r0
        L48:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapKV.getFromNullKey(cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory):cn.sast.dataflow.interprocedural.analysis.IHeapValues");
    }
}
