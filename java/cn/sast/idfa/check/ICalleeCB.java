package cn.sast.idfa.check;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;

/* compiled from: CheckerManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u0003\t\n\u000bR\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcn/sast/idfa/check/ICalleeCB;", "V", "R", "Lcn/sast/idfa/check/IStmtCB;", "Lcn/sast/idfa/check/ICallCB;", "callee", "Lsoot/SootMethod;", "getCallee", "()Lsoot/SootMethod;", "IPrevCall", "IEvalCall", "IPostCall", "corax-idfa-framework"})
/* loaded from: ICalleeCB.class */
public interface ICalleeCB<V, R> extends IStmtCB, ICallCB<V, R> {

    /* compiled from: CheckerManager.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcn/sast/idfa/check/ICalleeCB$IEvalCall;", "V", "R", "Lcn/sast/idfa/check/ICalleeCB;", "Lcn/sast/idfa/check/IEvalCallCB;", "corax-idfa-framework"})
    /* loaded from: ICalleeCB$IEvalCall.class */
    public interface IEvalCall<V, R> extends ICalleeCB<V, R>, IEvalCallCB<V, R> {
    }

    /* compiled from: CheckerManager.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcn/sast/idfa/check/ICalleeCB$IPostCall;", "V", "R", "Lcn/sast/idfa/check/ICalleeCB;", "Lcn/sast/idfa/check/IPostCallCB;", "corax-idfa-framework"})
    /* loaded from: ICalleeCB$IPostCall.class */
    public interface IPostCall<V, R> extends ICalleeCB<V, R>, IPostCallCB<V, R> {
    }

    /* compiled from: CheckerManager.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcn/sast/idfa/check/ICalleeCB$IPrevCall;", "V", "R", "Lcn/sast/idfa/check/ICalleeCB;", "Lcn/sast/idfa/check/IPrevCB;", "corax-idfa-framework"})
    /* loaded from: ICalleeCB$IPrevCall.class */
    public interface IPrevCall<V, R> extends ICalleeCB<V, R>, IPrevCB {
    }

    @NotNull
    SootMethod getCallee();
}
