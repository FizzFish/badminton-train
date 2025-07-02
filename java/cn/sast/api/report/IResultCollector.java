package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: IResultCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcn/sast/api/report/IResultCollector;", "", "flush", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: IResultCollector.class */
public interface IResultCollector {
    @Nullable
    Object flush(@NotNull Continuation<? super Unit> continuation);

    /* compiled from: IResultCollector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IResultCollector$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static Object flush(@NotNull IResultCollector $this, @NotNull Continuation<? super Unit> continuation) {
            return Unit.INSTANCE;
        }
    }
}
