package cn.sast.dataflow.interprocedural.analysis.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;

/* compiled from: ArrayHeapKV.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n��\bf\u0018��*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH&J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\rH&J#\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H&¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H&R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/heapimpl/IArrayHeapKV;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData;", "type", "Lsoot/ArrayType;", "getType", "()Lsoot/ArrayType;", "getArrayLength", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getElement", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getArray", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;)[Ljava/lang/Object;", "getByteArray", "", "corax-data-flow"})
/* loaded from: IArrayHeapKV.class */
public interface IArrayHeapKV<K, V> extends IHeapKVData<K, V> {
    @NotNull
    ArrayType getType();

    @NotNull
    IHeapValues<V> getArrayLength();

    @NotNull
    IHeapValues<V> getElement(@NotNull AbstractHeapFactory<V> abstractHeapFactory);

    @Nullable
    V[] getArray(@NotNull IHeapValuesFactory<V> iHeapValuesFactory);

    @Nullable
    byte[] getByteArray(@NotNull IHeapValuesFactory<V> iHeapValuesFactory);
}
