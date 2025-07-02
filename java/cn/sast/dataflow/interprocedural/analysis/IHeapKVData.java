package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HeapKVData.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\rJ-\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00018��H&¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\fH&¨\u0006\u000e"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "get", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "key", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "Builder", "corax-data-flow"})
/* loaded from: IHeapKVData.class */
public interface IHeapKVData<K, V> extends IData<V> {

    /* compiled from: HeapKVData.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018��*\b\b\u0002\u0010\u0001*\u00020\u0002*\u0004\b\u0003\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004JE\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00030\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00018\u00022\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IData$Builder;", "set", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "key", "update", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "append", "", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Z)V", "corax-data-flow"})
    /* loaded from: IHeapKVData$Builder.class */
    public interface Builder<K, V> extends IData.Builder<V> {
        void set(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @NotNull HeapValuesEnv heapValuesEnv, @Nullable K k, @Nullable IHeapValues<V> iHeapValues, boolean z);
    }

    @Nullable
    IHeapValues<V> get(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @Nullable K k);

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    Builder<K, V> builder();
}
