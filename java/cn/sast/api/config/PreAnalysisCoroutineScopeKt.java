package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreAnalysisCoroutineScope.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"processAIAnalysisUnits", "", "Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;", "preAnalysisScope", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "(Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;Lcn/sast/api/config/PreAnalysisCoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
@SourceDebugExtension({"SMAP\nPreAnalysisCoroutineScope.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt\n+ 2 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,99:1\n16#2,2:100\n16#2,8:102\n19#2,3:110\n16#2,8:113\n23#2:121\n*S KotlinDebug\n*F\n+ 1 PreAnalysisCoroutineScope.kt\ncn/sast/api/config/PreAnalysisCoroutineScopeKt\n*L\n63#1:100,2\n89#1:102,8\n63#1:110,3\n89#1:113,8\n63#1:121\n*E\n"})
/* loaded from: PreAnalysisCoroutineScopeKt.class */
public final class PreAnalysisCoroutineScopeKt {

    /* compiled from: PreAnalysisCoroutineScope.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PreAnalysisCoroutineScope.kt", l = {65, 90, 90, 65, 90, 90}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"$this$processAIAnalysisUnits", "preAnalysisScope", "monitor", "semaphore", "preAnalysisScope", "preAnalysisScope", "$this$bracket$iv", "s$iv", "$this$processAIAnalysisUnits", "preAnalysisScope", "monitor", "semaphore", "$this$bracket$iv", "s$iv", "preAnalysisScope", "$this$bracket$iv", "s$iv", "preAnalysisScope", "$this$bracket$iv", "$this$bracket$iv", "s$iv", "s$iv"}, m = "processAIAnalysisUnits", c = "cn.sast.api.config.PreAnalysisCoroutineScopeKt")
    /* renamed from: cn.sast.api.config.PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$1, reason: invalid class name */
    /* loaded from: PreAnalysisCoroutineScopeKt$processAIAnalysisUnits$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return PreAnalysisCoroutineScopeKt.processAIAnalysisUnits(null, null, (Continuation) this);
        }
    }

    /*  JADX ERROR: NullPointerException in pass: AttachTryCatchVisitor
        java.lang.NullPointerException: Cannot invoke "String.charAt(int)" because "obj" is null
        	at jadx.core.utils.Utils.cleanObjectName(Utils.java:41)
        	at jadx.core.dex.instructions.args.ArgType.object(ArgType.java:88)
        	at jadx.core.dex.info.ClassInfo.fromName(ClassInfo.java:42)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:113)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:54)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:42)
        */
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Object processAIAnalysisUnits(@org.jetbrains.annotations.NotNull com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl r9, @org.jetbrains.annotations.NotNull cn.sast.api.config.PreAnalysisCoroutineScope r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 1261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.PreAnalysisCoroutineScopeKt.processAIAnalysisUnits(com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl, cn.sast.api.config.PreAnalysisCoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
