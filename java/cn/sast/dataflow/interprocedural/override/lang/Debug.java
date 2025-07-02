package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.dataflow.interprocedural.override.DBG;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;

/* compiled from: Debug.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \n2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/Debug;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: Debug.class */
public final class Debug implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: Debug.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/Debug$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/Debug;", "corax-data-flow"})
    /* loaded from: Debug$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Debug v() {
            return new Debug();
        }
    }

    /* compiled from: Debug.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.Debug$register$1, reason: invalid class name */
    /* loaded from: Debug$register$1.class */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<DBG, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, DBG.class, "dbg", "dbg()V", 0);
        }

        public final void invoke(DBG p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            p0.dbg();
        }

        public /* bridge */ /* synthetic */ Object invoke(Object p1) {
            invoke((DBG) p1);
            return Unit.INSTANCE;
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        KLogger logger = KotlinLogging.INSTANCE.logger(Debug::register$lambda$0);
        $this$register.evalCallAtCaller(UtilsKt.getSootSignature(AnonymousClass1.INSTANCE), (v1) -> {
            return register$lambda$2(r2, v1);
        });
        $this$register.evalCallAtCaller(UtilsKt.getSootSignature(AnonymousClass3.INSTANCE), (v1) -> {
            return register$lambda$4(r2, v1);
        });
    }

    private static final Unit register$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final Object register$lambda$2$lambda$1() {
        return "debug break";
    }

    private static final Unit register$lambda$2(KLogger $logger, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        $logger.debug(Debug::register$lambda$2$lambda$1);
        return Unit.INSTANCE;
    }

    /* compiled from: Debug.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* renamed from: cn.sast.dataflow.interprocedural.override.lang.Debug$register$3, reason: invalid class name */
    /* loaded from: Debug$register$3.class */
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<DBG, Object, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(2, DBG.class, "print", "print(Ljava/lang/Object;)V", 0);
        }

        public final void invoke(DBG p0, Object p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            p0.print(p1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
            invoke((DBG) p1, p2);
            return Unit.INSTANCE;
        }
    }

    private static final Unit register$lambda$4(KLogger $logger, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues res = $this$evalCallAtCaller.arg(0);
        $logger.debug(() -> {
            return register$lambda$4$lambda$3(r1);
        });
        return Unit.INSTANCE;
    }

    private static final Object register$lambda$4$lambda$3(IHeapValues $res) {
        return "debug print(" + $res + ")";
    }
}
