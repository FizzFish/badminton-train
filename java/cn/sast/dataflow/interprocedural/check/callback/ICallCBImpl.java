package cn.sast.dataflow.interprocedural.check.callback;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: CallCallBackImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/ICallCBImpl;", "V", "R", "Lcn/sast/idfa/check/ICallCB;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "corax-data-flow"})
/* loaded from: ICallCBImpl.class */
public interface ICallCBImpl<V, R> extends ICallCB<V, R> {
    @NotNull
    HookEnv getEnv();

    @NotNull
    AnyNewExprEnv getNewEnv();

    @NotNull
    AbstractHeapFactory<IValue> getHf();
}
