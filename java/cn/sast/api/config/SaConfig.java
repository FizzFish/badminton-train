package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.ISootInitializeHandler;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MainConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007HÆ\u0003JI\u0010 \u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007HÆ\u0001J\u0013\u0010!\u001a\u00020\u00192\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0014¨\u0006'"}, d2 = {"Lcn/sast/api/config/SaConfig;", "", "builtinAnalysisConfig", "Lcn/sast/api/config/BuiltinAnalysisConfig;", "preAnalysisConfig", "Lcn/sast/api/config/PreAnalysisConfig;", "checkers", "", "Lcom/feysh/corax/config/api/CheckerUnit;", "sootConfig", "Lcom/feysh/corax/config/api/ISootInitializeHandler;", "enableCheckTypes", "Lcom/feysh/corax/config/api/CheckType;", "<init>", "(Lcn/sast/api/config/BuiltinAnalysisConfig;Lcn/sast/api/config/PreAnalysisConfig;Ljava/util/Set;Lcom/feysh/corax/config/api/ISootInitializeHandler;Ljava/util/Set;)V", "getBuiltinAnalysisConfig", "()Lcn/sast/api/config/BuiltinAnalysisConfig;", "getPreAnalysisConfig", "()Lcn/sast/api/config/PreAnalysisConfig;", "getCheckers", "()Ljava/util/Set;", "getSootConfig", "()Lcom/feysh/corax/config/api/ISootInitializeHandler;", "getEnableCheckTypes", "isEnable", "", "checkType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "corax-api"})
/* loaded from: SaConfig.class */
public final class SaConfig {

    @NotNull
    private final BuiltinAnalysisConfig builtinAnalysisConfig;

    @NotNull
    private final PreAnalysisConfig preAnalysisConfig;

    @NotNull
    private final Set<CheckerUnit> checkers;

    @NotNull
    private final ISootInitializeHandler sootConfig;

    @Nullable
    private final Set<CheckType> enableCheckTypes;

    @NotNull
    public final BuiltinAnalysisConfig component1() {
        return this.builtinAnalysisConfig;
    }

    @NotNull
    public final PreAnalysisConfig component2() {
        return this.preAnalysisConfig;
    }

    @NotNull
    public final Set<CheckerUnit> component3() {
        return this.checkers;
    }

    @NotNull
    public final ISootInitializeHandler component4() {
        return this.sootConfig;
    }

    @Nullable
    public final Set<CheckType> component5() {
        return this.enableCheckTypes;
    }

    @NotNull
    public final SaConfig copy(@NotNull BuiltinAnalysisConfig builtinAnalysisConfig, @NotNull PreAnalysisConfig preAnalysisConfig, @NotNull Set<? extends CheckerUnit> set, @NotNull ISootInitializeHandler sootConfig, @Nullable Set<? extends CheckType> set2) {
        Intrinsics.checkNotNullParameter(builtinAnalysisConfig, "builtinAnalysisConfig");
        Intrinsics.checkNotNullParameter(preAnalysisConfig, "preAnalysisConfig");
        Intrinsics.checkNotNullParameter(set, "checkers");
        Intrinsics.checkNotNullParameter(sootConfig, "sootConfig");
        return new SaConfig(builtinAnalysisConfig, preAnalysisConfig, set, sootConfig, set2);
    }

    public static /* synthetic */ SaConfig copy$default(SaConfig saConfig, BuiltinAnalysisConfig builtinAnalysisConfig, PreAnalysisConfig preAnalysisConfig, Set set, ISootInitializeHandler iSootInitializeHandler, Set set2, int i, Object obj) {
        if ((i & 1) != 0) {
            builtinAnalysisConfig = saConfig.builtinAnalysisConfig;
        }
        if ((i & 2) != 0) {
            preAnalysisConfig = saConfig.preAnalysisConfig;
        }
        if ((i & 4) != 0) {
            set = saConfig.checkers;
        }
        if ((i & 8) != 0) {
            iSootInitializeHandler = saConfig.sootConfig;
        }
        if ((i & 16) != 0) {
            set2 = saConfig.enableCheckTypes;
        }
        return saConfig.copy(builtinAnalysisConfig, preAnalysisConfig, set, iSootInitializeHandler, set2);
    }

    @NotNull
    public String toString() {
        return "SaConfig(builtinAnalysisConfig=" + this.builtinAnalysisConfig + ", preAnalysisConfig=" + this.preAnalysisConfig + ", checkers=" + this.checkers + ", sootConfig=" + this.sootConfig + ", enableCheckTypes=" + this.enableCheckTypes + ")";
    }

    public int hashCode() {
        int result = this.builtinAnalysisConfig.hashCode();
        return (((((((result * 31) + this.preAnalysisConfig.hashCode()) * 31) + this.checkers.hashCode()) * 31) + this.sootConfig.hashCode()) * 31) + (this.enableCheckTypes == null ? 0 : this.enableCheckTypes.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaConfig)) {
            return false;
        }
        SaConfig saConfig = (SaConfig) other;
        return Intrinsics.areEqual(this.builtinAnalysisConfig, saConfig.builtinAnalysisConfig) && Intrinsics.areEqual(this.preAnalysisConfig, saConfig.preAnalysisConfig) && Intrinsics.areEqual(this.checkers, saConfig.checkers) && Intrinsics.areEqual(this.sootConfig, saConfig.sootConfig) && Intrinsics.areEqual(this.enableCheckTypes, saConfig.enableCheckTypes);
    }

    public SaConfig(@NotNull BuiltinAnalysisConfig builtinAnalysisConfig, @NotNull PreAnalysisConfig preAnalysisConfig, @NotNull Set<? extends CheckerUnit> set, @NotNull ISootInitializeHandler sootConfig, @Nullable Set<? extends CheckType> set2) {
        Intrinsics.checkNotNullParameter(builtinAnalysisConfig, "builtinAnalysisConfig");
        Intrinsics.checkNotNullParameter(preAnalysisConfig, "preAnalysisConfig");
        Intrinsics.checkNotNullParameter(set, "checkers");
        Intrinsics.checkNotNullParameter(sootConfig, "sootConfig");
        this.builtinAnalysisConfig = builtinAnalysisConfig;
        this.preAnalysisConfig = preAnalysisConfig;
        this.checkers = set;
        this.sootConfig = sootConfig;
        this.enableCheckTypes = set2;
    }

    public /* synthetic */ SaConfig(BuiltinAnalysisConfig builtinAnalysisConfig, PreAnalysisConfig preAnalysisConfig, Set set, ISootInitializeHandler iSootInitializeHandler, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new BuiltinAnalysisConfig((List) null, (List) null, 0, 0, 15, (DefaultConstructorMarker) null) : builtinAnalysisConfig, (i & 2) != 0 ? new PreAnalysisConfig(0, 0, 0, (Map) null, 0, 31, (DefaultConstructorMarker) null) : preAnalysisConfig, set, iSootInitializeHandler, set2);
    }

    @NotNull
    public final BuiltinAnalysisConfig getBuiltinAnalysisConfig() {
        return this.builtinAnalysisConfig;
    }

    @NotNull
    public final PreAnalysisConfig getPreAnalysisConfig() {
        return this.preAnalysisConfig;
    }

    @NotNull
    public final Set<CheckerUnit> getCheckers() {
        return this.checkers;
    }

    @NotNull
    public final ISootInitializeHandler getSootConfig() {
        return this.sootConfig;
    }

    @Nullable
    public final Set<CheckType> getEnableCheckTypes() {
        return this.enableCheckTypes;
    }

    public final boolean isEnable(@NotNull CheckType checkType) {
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Set<CheckType> set = this.enableCheckTypes;
        if (set != null) {
            return set.contains(checkType);
        }
        return true;
    }
}
