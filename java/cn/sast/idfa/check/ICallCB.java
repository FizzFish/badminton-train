package cn.sast.idfa.check;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: CheckerManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\bf\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028��2\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&R\u0012\u0010\t\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u00028��X¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0018\u0010\u0012\u001a\u00028\u0001X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcn/sast/idfa/check/ICallCB;", "V", "R", "", "arg", "argIndex", "", "(I)Ljava/lang/Object;", "argToValue", "global", "getGlobal", "()Ljava/lang/Object;", "return", "getReturn", "setReturn", "(Ljava/lang/Object;)V", "this", "getThis", "out", "getOut", "setOut", "corax-idfa-framework"})
/* loaded from: ICallCB.class */
public interface ICallCB<V, R> {
    V arg(int i);

    @NotNull
    Object argToValue(int i);

    V getGlobal();

    V getReturn();

    void setReturn(V v);

    V getThis();

    R getOut();

    void setOut(R r);
}
