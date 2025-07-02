package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import org.jetbrains.annotations.NotNull;
import soot.ArrayType;
import soot.G;
import soot.RefType;
import soot.Type;
import soot.jimple.Jimple;

/* compiled from: WStringBuilder.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \n2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u0007*\u00100\bj\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\tH\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
/* loaded from: WStringBuilder.class */
public final class WStringBuilder implements SummaryHandlePackage<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: WStringBuilder.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WStringBuilder$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WStringBuilder;", "corax-data-flow"})
    /* loaded from: WStringBuilder$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WStringBuilder v() {
            return new WStringBuilder();
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        KCallable kCallable = (Function2) WStringBuilder$register$appendInt$1.INSTANCE;
        KCallable kCallable2 = (Function2) WStringBuilder$register$appendString$1.INSTANCE;
        KCallable kCallable3 = (Function2) WStringBuilder$register$appendBoolean$1.INSTANCE;
        KCallable kCallable4 = (Function2) WStringBuilder$register$appendChar$1.INSTANCE;
        KCallable kCallable5 = (Function2) WStringBuilder$register$appendLong$1.INSTANCE;
        KCallable kCallable6 = (Function2) WStringBuilder$register$appendShort$1.INSTANCE;
        Intrinsics.checkNotNull(kCallable, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable);
        Intrinsics.checkNotNull(kCallable2, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable2);
        Intrinsics.checkNotNull(kCallable3, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable3);
        Intrinsics.checkNotNull(kCallable4, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable4);
        Intrinsics.checkNotNull(kCallable5, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable5);
        Intrinsics.checkNotNull(kCallable6, "null cannot be cast to non-null type kotlin.jvm.internal.CallableReference");
        UtilsKt.getSootSignature((CallableReference) kCallable6);
        Jimple.v().newNewExpr(RefType.v("java.lang.Integer"));
        Type typeV = ArrayType.v(G.v().soot_ByteType(), 1);
        Intrinsics.checkNotNullExpressionValue(typeV, "v(...)");
        SootUtilsKt.getOrMakeField("java.lang.AbstractStringBuilder", "value", typeV);
    }
}
