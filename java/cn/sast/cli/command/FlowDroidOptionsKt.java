package cn.sast.cli.command;

import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.InfoflowConfiguration;

/* compiled from: FlowDroidOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"cvt", "Lsoot/jimple/infoflow/InfoflowConfiguration$StaticFieldTrackingMode;", "Lcn/sast/api/config/StaticFieldTrackingMode;", "getCvt", "(Lcn/sast/api/config/StaticFieldTrackingMode;)Lsoot/jimple/infoflow/InfoflowConfiguration$StaticFieldTrackingMode;", "corax-cli"})
/* loaded from: FlowDroidOptionsKt.class */
public final class FlowDroidOptionsKt {

    /* compiled from: FlowDroidOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: FlowDroidOptionsKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StaticFieldTrackingMode.values().length];
            try {
                iArr[StaticFieldTrackingMode.ContextFlowSensitive.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StaticFieldTrackingMode.ContextFlowInsensitive.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StaticFieldTrackingMode.None.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @NotNull
    public static final InfoflowConfiguration.StaticFieldTrackingMode getCvt(@NotNull StaticFieldTrackingMode $this$cvt) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter($this$cvt, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$cvt.ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                return InfoflowConfiguration.StaticFieldTrackingMode.ContextFlowSensitive;
            case 2:
                return InfoflowConfiguration.StaticFieldTrackingMode.ContextFlowInsensitive;
            case 3:
                return InfoflowConfiguration.StaticFieldTrackingMode.None;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
