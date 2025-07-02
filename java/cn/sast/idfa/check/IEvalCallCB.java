package cn.sast.idfa.check;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;

/* compiled from: CheckerManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004R\u0018\u0010\u0005\u001a\u00020\u0006X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcn/sast/idfa/check/IEvalCallCB;", "V", "R", "Lcn/sast/idfa/check/IEvalCB;", "Lcn/sast/idfa/check/ICallCB;", "isEvalAble", "", "()Z", "setEvalAble", "(Z)V", "corax-idfa-framework"})
/* loaded from: IEvalCallCB.class */
public interface IEvalCallCB<V, R> extends IEvalCB, ICallCB<V, R> {
    boolean isEvalAble();

    void setEvalAble(boolean z);
}
