package cn.sast.framework.entries.apk;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;

/* compiled from: ApkLifeCycleComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010��\u001a\u00020\u0002*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0006"}, d2 = {"convert", "Lcn/sast/framework/entries/apk/CallbackAnalyzerType;", "Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration$CallbackAnalyzer;", "getConvert", "(Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration$CallbackAnalyzer;)Lcn/sast/framework/entries/apk/CallbackAnalyzerType;", "(Lcn/sast/framework/entries/apk/CallbackAnalyzerType;)Lsoot/jimple/infoflow/android/InfoflowAndroidConfiguration$CallbackAnalyzer;", "corax-framework"})
/* loaded from: ApkLifeCycleComponentKt.class */
public final class ApkLifeCycleComponentKt {

    /* compiled from: ApkLifeCycleComponent.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: ApkLifeCycleComponentKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[InfoflowAndroidConfiguration.CallbackAnalyzer.values().length];
            try {
                iArr[InfoflowAndroidConfiguration.CallbackAnalyzer.Default.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[InfoflowAndroidConfiguration.CallbackAnalyzer.Fast.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CallbackAnalyzerType.values().length];
            try {
                iArr2[CallbackAnalyzerType.Default.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[CallbackAnalyzerType.Fast.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @NotNull
    public static final CallbackAnalyzerType getConvert(@NotNull InfoflowAndroidConfiguration.CallbackAnalyzer $this$convert) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter($this$convert, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$convert.ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                return CallbackAnalyzerType.Default;
            case 2:
                return CallbackAnalyzerType.Fast;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @NotNull
    public static final InfoflowAndroidConfiguration.CallbackAnalyzer getConvert(@NotNull CallbackAnalyzerType $this$convert) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter($this$convert, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$convert.ordinal()]) {
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                return InfoflowAndroidConfiguration.CallbackAnalyzer.Default;
            case 2:
                return InfoflowAndroidConfiguration.CallbackAnalyzer.Fast;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
