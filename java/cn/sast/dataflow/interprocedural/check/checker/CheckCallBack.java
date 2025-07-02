package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.checker.CheckerModeling;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u001b2\u00020\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J`\u0010\f\u001a\u000e\u0012\b\u0012\u00060\u000ej\u0002`\u000f\u0018\u00010\r2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u000ej\u0002`\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\"\u0010\u0014\u001a\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u000ej\u0002`\u000f0\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00170\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001c"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/CheckCallBack;", "", "atMethod", "Lsoot/SootMethod;", "define", "Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "<init>", "(Lsoot/SootMethod;Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;)V", "getAtMethod", "()Lsoot/SootMethod;", "toString", "", "check", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "summaryCtxCalleeSite", "Lcn/sast/idfa/check/ICallCB;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/HookEnv;Lcn/sast/idfa/check/ICallCB;Lcn/sast/idfa/analysis/InterproceduralCFG;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-data-flow"})
/* loaded from: CheckCallBack.class */
public final class CheckCallBack {

    @NotNull
    private final SootMethod atMethod;

    @NotNull
    private final CheckerModeling.Checker define;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckCallBack::logger$lambda$1);

    public CheckCallBack(@NotNull SootMethod atMethod, @NotNull CheckerModeling.Checker define) {
        Intrinsics.checkNotNullParameter(atMethod, "atMethod");
        Intrinsics.checkNotNullParameter(define, "define");
        this.atMethod = atMethod;
        this.define = define;
    }

    @NotNull
    public final SootMethod getAtMethod() {
        return this.atMethod;
    }

    @NotNull
    public String toString() {
        return "check " + this.atMethod + ":  " + this.define.getGuard();
    }

    /* compiled from: CheckerModeling.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/CheckCallBack$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: CheckCallBack$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object check(@org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory<cn.sast.dataflow.interprocedural.analysis.IValue> r11, @org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.HookEnv r12, @org.jetbrains.annotations.NotNull cn.sast.idfa.check.ICallCB<cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue>, cn.sast.dataflow.interprocedural.analysis.IFact.Builder<cn.sast.dataflow.interprocedural.analysis.IValue>> r13, @org.jetbrains.annotations.NotNull cn.sast.idfa.analysis.InterproceduralCFG r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>> r15) {
        /*
            Method dump skipped, instructions count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.checker.CheckCallBack.check(cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory, cn.sast.dataflow.interprocedural.analysis.HookEnv, cn.sast.idfa.check.ICallCB, cn.sast.idfa.analysis.InterproceduralCFG, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object check$lambda$0(CheckCallBack this$0) {
        return "found a bug at: method: " + this$0.atMethod + ". define = " + this$0.define;
    }
}
