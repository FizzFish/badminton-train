package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.progressbar.ProgressBarExt;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* compiled from: ForwardInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "ForwardInterProceduralAnalysis.kt", l = {679, 525}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$0", "L$4", "L$5", "L$6", "L$7", "I$0"}, n = {"$this$withPermit$iv", "$this$withPermit$iv", "$this$bracket$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "invokeSuspend", c = "cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1")
@SourceDebugExtension({"SMAP\nForwardInterProceduralAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1\n+ 2 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreKt\n+ 3 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,678:1\n81#2,3:679\n85#2,2:701\n54#3,19:682\n*S KotlinDebug\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1\n*L\n514#1:679,3\n514#1:701,2\n517#1:682,19\n*E\n"})
/* loaded from: ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1.class */
final class ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int I$0;
    int label;
    final /* synthetic */ Semaphore $semaphore;
    final /* synthetic */ AtomicInteger $counter;
    final /* synthetic */ ForwardInterProceduralAnalysis<M, N, A, R, CTX> this$0;
    final /* synthetic */ M $method;
    final /* synthetic */ int $size;
    final /* synthetic */ ProgressBarExt.DefaultProcessInfoRenderer $progressBarRenderer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1(Semaphore $semaphore, AtomicInteger $counter, ForwardInterProceduralAnalysis<M, N, A, R, CTX> forwardInterProceduralAnalysis, M m, int $size, ProgressBarExt.DefaultProcessInfoRenderer $progressBarRenderer, Continuation<? super ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1> continuation) {
        super(2, continuation);
        this.$semaphore = $semaphore;
        this.$counter = $counter;
        this.this$0 = forwardInterProceduralAnalysis;
        this.$method = m;
        this.$size = $size;
        this.$progressBarRenderer = $progressBarRenderer;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1(this.$semaphore, this.$counter, this.this$0, this.$method, this.$size, this.$progressBarRenderer, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r13v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r19v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r19v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r20v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r20v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r22v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r22v2 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r24v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x02d9: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$withPermit$iv' kotlinx.coroutines.sync.Semaphore)])
 A[TRY_LEAVE], block:B:43:0x02d9 */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x0287: MOVE (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x0278 */
    /* JADX WARN: Not initialized variable reg: 19, insn: 0x0278: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r19 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:698), block:B:32:0x0278 */
    /* JADX WARN: Not initialized variable reg: 19, insn: 0x0295: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r19 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:700), block:B:33:0x0295 */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x0283: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r20 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:32:0x0278 */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x02a0: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r20 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:33:0x0295 */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x0281: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r22 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:32:0x0278 */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x029e: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r22 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:33:0x0295 */
    /* JADX WARN: Not initialized variable reg: 23, insn: 0x0265: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r23 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('alreadyLogged$iv' boolean)]) A[TRY_LEAVE], block:B:28:0x0265 */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x026a: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r24 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('res$iv' kotlin.jvm.internal.Ref$ObjectRef)]) (LINE:697), block:B:30:0x026a */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 754
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
