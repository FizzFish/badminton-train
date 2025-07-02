package cn.sast.framework.engine;

import cn.sast.api.util.Timer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IClassCheckPoint;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import soot.SootClass;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\f\u0012\b\u0012\u0006\b\u0002\u0018\u00018��0\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisImpl.kt", l = {157, 157}, i = {PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1"}, n = {"$this$bracket$iv", "s$iv"}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atClass$1$asyncResult$1")
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atClass$1$asyncResult$1\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,760:1\n16#2,2:761\n19#2,3:776\n23#2:782\n1611#3,9:763\n1863#3:772\n1864#3:774\n1620#3:775\n1863#3,2:779\n1620#3:781\n1#4:773\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/PreAnalysisImpl$atClass$1$asyncResult$1\n*L\n142#1:761,2\n142#1:776,3\n142#1:782\n143#1:763,9\n143#1:772\n143#1:774\n143#1:775\n143#1:779,2\n143#1:781\n143#1:773\n*E\n"})
/* loaded from: PreAnalysisImpl$atClass$1$asyncResult$1.class */
final class PreAnalysisImpl$atClass$1$asyncResult$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends T>>, Object> {
    Object L$1;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Timer $t;
    final /* synthetic */ Collection<SootClass> $classes;
    final /* synthetic */ PreAnalysisImpl this$0;
    final /* synthetic */ PreAnalysisClassConfig $conf;
    final /* synthetic */ PreAnalysisImpl.C00221 this$1;
    final /* synthetic */ Function2<IClassCheckPoint, Continuation<? super T>, Object> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisImpl$atClass$1$asyncResult$1(Timer $t, Collection<? extends SootClass> collection, PreAnalysisImpl $receiver, PreAnalysisClassConfig $conf, PreAnalysisImpl.C00221 $receiver2, Function2<? super IClassCheckPoint, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super PreAnalysisImpl$atClass$1$asyncResult$1> continuation) {
        super(2, continuation);
        this.$t = $t;
        this.$classes = collection;
        this.this$0 = $receiver;
        this.$conf = $conf;
        this.this$1 = $receiver2;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisImpl$atClass$1$asyncResult$1 = new PreAnalysisImpl$atClass$1$asyncResult$1<>(this.$t, this.$classes, this.this$0, this.$conf, this.this$1, this.$block, continuation);
        preAnalysisImpl$atClass$1$asyncResult$1.L$0 = value;
        return preAnalysisImpl$atClass$1$asyncResult$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super List<? extends T>> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x020c A[Catch: all -> 0x02a9, TryCatch #0 {all -> 0x02a9, blocks: (B:37:0x0172, B:38:0x01a3, B:40:0x01ad, B:58:0x023c, B:43:0x01d2, B:46:0x01e5, B:48:0x01ed, B:50:0x01f6, B:52:0x0202, B:55:0x020c, B:61:0x0252, B:68:0x0296, B:67:0x0290), top: B:77:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 703
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl$atClass$1$asyncResult$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
