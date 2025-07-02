package cn.sast.framework.engine;

import cn.sast.api.AnalyzerEnv;
import cn.sast.api.util.Timer;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisImpl;
import com.feysh.corax.config.api.ISourceFileCheckPoint;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\f\u0012\b\u0012\u0006\b\u0002\u0018\u00018��0\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PreAnalysisImpl.kt", l = {276, 314}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"$this$async", "r"}, m = "invokeSuspend", c = "cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$1$asyncResult$1")
/* loaded from: PreAnalysisImpl$atSourceFile$1$asyncResult$1.class */
final class PreAnalysisImpl$atSourceFile$1$asyncResult$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends T>>, Object> {
    Object L$1;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Function1<Continuation<? super Iterator<? extends IResFile>>, Object> $files;
    final /* synthetic */ PreAnalysisImpl this$0;
    final /* synthetic */ PreAnalysisFileConfig $conf;
    final /* synthetic */ Timer $t;
    final /* synthetic */ PreAnalysisImpl.C00261 this$1;
    final /* synthetic */ Function2<ISourceFileCheckPoint, Continuation<? super T>, Object> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PreAnalysisImpl$atSourceFile$1$asyncResult$1(Function1<? super Continuation<? super Iterator<? extends IResFile>>, ? extends Object> function1, PreAnalysisImpl $receiver, PreAnalysisFileConfig $conf, Timer $t, PreAnalysisImpl.C00261 $receiver2, Function2<? super ISourceFileCheckPoint, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super PreAnalysisImpl$atSourceFile$1$asyncResult$1> continuation) {
        super(2, continuation);
        this.$files = function1;
        this.this$0 = $receiver;
        this.$conf = $conf;
        this.$t = $t;
        this.this$1 = $receiver2;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> preAnalysisImpl$atSourceFile$1$asyncResult$1 = new PreAnalysisImpl$atSourceFile$1$asyncResult$1<>(this.$files, this.this$0, this.$conf, this.$t, this.this$1, this.$block, continuation);
        preAnalysisImpl$atSourceFile$1$asyncResult$1.L$0 = value;
        return preAnalysisImpl$atSourceFile$1$asyncResult$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super List<? extends T>> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0235  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.PreAnalysisImpl$atSourceFile$1$asyncResult$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final String invokeSuspend$lambda$0(Integer $threshold, IResFile $file, long $fileSize) {
        return "File size threshold (" + $threshold + ") exceeded for " + $file + " (size: " + $fileSize + ")";
    }

    private static final Object invokeSuspend$lambda$1(PreAnalysisImpl this$0) {
        return "File size threshold exceeded ........ (more than " + this$0.maximumFileSizeThresholdWarnings + ". check log: " + AnalyzerEnv.INSTANCE.getLastLogFile() + ")";
    }
}
