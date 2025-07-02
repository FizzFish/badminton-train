package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J=\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028��2\n\u0010\n\u001a\u00060\u0002j\u0002`\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028��0\u0006H&¢\u0006\u0002\u0010\rJ\\\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000f2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028��0\u000f26\u0010\u0010\u001a2\u0012\u0013\u0012\u00118��¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00150\u0011H&¨\u0006\u0017"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathFactory;", "V", "", "<init>", "()V", "setModelData", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "obj", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "data", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IData;)Lcn/sast/dataflow/interprocedural/analysis/IData;", "updatePath", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "newPath", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "v", "Lcn/sast/dataflow/interprocedural/check/IPath;", "old", "corax-data-flow"})
/* loaded from: PathFactory.class */
public abstract class PathFactory<V> {
    @NotNull
    public abstract IData<V> setModelData(@NotNull HeapValuesEnv heapValuesEnv, V v, @NotNull Object obj, @NotNull IData<V> iData);

    @NotNull
    public abstract IHeapValues<V> updatePath(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IHeapValues<V> iHeapValues, @NotNull Function2<? super V, ? super IPath, ? extends IPath> function2);
}
