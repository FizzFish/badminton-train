package cn.sast.api.config;

import cn.sast.api.util.IMonitor;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.PreAnalysisUnit;
import com.feysh.corax.config.api.utils.UtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.sync.Semaphore;
import soot.Scene;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.api.config.PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1")
@SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,99:1\n808#2,11:100\n1863#2,2:111\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1\n*L\n38#1:100,11\n38#1:111,2\n*E\n"})
/* loaded from: PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1.class */
final class PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ PreAnalysisCoroutineScope this$0;
    final /* synthetic */ Set<CheckerUnit> $units;
    final /* synthetic */ Semaphore $semaphore;
    final /* synthetic */ IMonitor $monitor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1(PreAnalysisCoroutineScope $receiver, Set<? extends CheckerUnit> set, Semaphore $semaphore, IMonitor $monitor, Continuation<? super PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$units = set;
        this.$semaphore = $semaphore;
        this.$monitor = $monitor;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisCoroutineScope$processPreAnalysisUnits$2$1 = new PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1(this.this$0, this.$units, this.$semaphore, this.$monitor, continuation);
        preAnalysisCoroutineScope$processPreAnalysisUnits$2$1.L$0 = value;
        return preAnalysisCoroutineScope$processPreAnalysisUnits$2$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                if (Scene.v().doneResolving()) {
                    Scene.v().getOrMakeFastHierarchy();
                    Scene.v().getActiveHierarchy();
                }
                this.this$0.setScope($this$coroutineScope);
                Iterable $this$filterIsInstance$iv = this.$units;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterIsInstance$iv) {
                    if (element$iv$iv instanceof PreAnalysisUnit) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$forEach$iv = (List) destination$iv$iv;
                Semaphore semaphore = this.$semaphore;
                IMonitor iMonitor = this.$monitor;
                PreAnalysisCoroutineScope preAnalysisCoroutineScope = this.this$0;
                for (Object element$iv : $this$forEach$iv) {
                    PreAnalysisUnit it = (PreAnalysisUnit) element$iv;
                    BuildersKt.launch$default($this$coroutineScope, new CoroutineName("Pre:" + UtilsKt.getSootTypeName(it.getClass()) + "::config()"), (CoroutineStart) null, new PreAnalysisCoroutineScope$processPreAnalysisUnits$2$1$1$1(semaphore, it, iMonitor, preAnalysisCoroutineScope, null), 2, (Object) null);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
