package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;

/* compiled from: FIeldHeap.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0002\b&\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0011J(\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0013\u0010\u0019\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV;", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapKVData;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "clz", "Lsoot/RefType;", "fields", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lsoot/RefType;Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getClz", "()Lsoot/RefType;", "isValidKey", "", "key", "(Lcn/sast/dataflow/interprocedural/analysis/JFieldType;)Ljava/lang/Boolean;", "get", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "ppKey", "", "computeHash", "", "equals", "other", "", "hashCode", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nFIeldHeap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FIeldHeap.kt\ncn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,56:1\n50#2:57\n*S KotlinDebug\n*F\n+ 1 FIeldHeap.kt\ncn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV\n*L\n27#1:57\n*E\n"})
/* loaded from: FieldHeapKV.class */
public abstract class FieldHeapKV<V> extends HeapKVData<JFieldType, V> {

    @NotNull
    private final RefType clz;

    @NotNull
    public final RefType getClz() {
        return this.clz;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldHeapKV(@NotNull RefType clz, @NotNull PersistentMap<JFieldType, ? extends IHeapValues<V>> persistentMap, @Nullable IHeapValues<V> iHeapValues) {
        super(persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(clz, "clz");
        Intrinsics.checkNotNullParameter(persistentMap, "fields");
        this.clz = clz;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @Nullable
    public Boolean isValidKey(@Nullable JFieldType key) {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IHeapKVData
    @Nullable
    public IHeapValues<V> get(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @Nullable JFieldType key) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        if (key != null) {
            return (IHeapValues) getMap().get(key);
        }
        return getFromNullKey(iHeapValuesFactory);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String ppKey(@NotNull JFieldType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        return key.getName();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData, cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        int result = super.computeHash();
        return (31 * result) + this.clz.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public boolean equals(@Nullable Object other) {
        if (super.equals(other) && (other instanceof FieldHeapKV)) {
            return Intrinsics.areEqual(this.clz, ((FieldHeapKV) other).clz);
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public int hashCode() {
        return super.hashCode();
    }
}
