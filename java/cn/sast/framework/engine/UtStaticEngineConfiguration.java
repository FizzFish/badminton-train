package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.framework.codegen.domain.ForceStaticMocking;
import org.utbot.framework.codegen.domain.StaticsMocking;
import org.utbot.framework.plugin.api.CodegenLanguage;
import org.utbot.framework.plugin.api.MockStrategyApi;
import org.utbot.framework.plugin.api.TreatOverflowAsError;

/* compiled from: UtStaticEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\"\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018�� ;2\u00020\u0001:\u0001;BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\t\u00102\u001a\u00020\u000bHÆ\u0003J\t\u00103\u001a\u00020\rHÆ\u0003JE\u00104\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R \u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070)X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006<"}, d2 = {"Lcn/sast/framework/engine/UtStaticEngineConfiguration;", "", "codegenLanguage", "Lorg/utbot/framework/plugin/api/CodegenLanguage;", "mockStrategy", "Lorg/utbot/framework/plugin/api/MockStrategyApi;", "testFramework", "", "staticsMocking", "Lorg/utbot/framework/codegen/domain/StaticsMocking;", "forceStaticMocking", "Lorg/utbot/framework/codegen/domain/ForceStaticMocking;", "treatOverflowAsError", "Lorg/utbot/framework/plugin/api/TreatOverflowAsError;", "<init>", "(Lorg/utbot/framework/plugin/api/CodegenLanguage;Lorg/utbot/framework/plugin/api/MockStrategyApi;Ljava/lang/String;Lorg/utbot/framework/codegen/domain/StaticsMocking;Lorg/utbot/framework/codegen/domain/ForceStaticMocking;Lorg/utbot/framework/plugin/api/TreatOverflowAsError;)V", "getCodegenLanguage", "()Lorg/utbot/framework/plugin/api/CodegenLanguage;", "setCodegenLanguage", "(Lorg/utbot/framework/plugin/api/CodegenLanguage;)V", "getMockStrategy", "()Lorg/utbot/framework/plugin/api/MockStrategyApi;", "setMockStrategy", "(Lorg/utbot/framework/plugin/api/MockStrategyApi;)V", "getTestFramework", "()Ljava/lang/String;", "setTestFramework", "(Ljava/lang/String;)V", "getStaticsMocking", "()Lorg/utbot/framework/codegen/domain/StaticsMocking;", "setStaticsMocking", "(Lorg/utbot/framework/codegen/domain/StaticsMocking;)V", "getForceStaticMocking", "()Lorg/utbot/framework/codegen/domain/ForceStaticMocking;", "setForceStaticMocking", "(Lorg/utbot/framework/codegen/domain/ForceStaticMocking;)V", "getTreatOverflowAsError", "()Lorg/utbot/framework/plugin/api/TreatOverflowAsError;", "setTreatOverflowAsError", "(Lorg/utbot/framework/plugin/api/TreatOverflowAsError;)V", "classesToMockAlways", "", "getClassesToMockAlways", "()Ljava/util/Set;", "setClassesToMockAlways", "(Ljava/util/Set;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "corax-framework"})
/* loaded from: UtStaticEngineConfiguration.class */
public final class UtStaticEngineConfiguration {

    @NotNull
    private CodegenLanguage codegenLanguage;

    @NotNull
    private MockStrategyApi mockStrategy;

    @NotNull
    private String testFramework;

    @NotNull
    private StaticsMocking staticsMocking;

    @NotNull
    private ForceStaticMocking forceStaticMocking;

    @NotNull
    private TreatOverflowAsError treatOverflowAsError;

    @NotNull
    private Set<String> classesToMockAlways;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(UtStaticEngineConfiguration::logger$lambda$0);

    @NotNull
    public final CodegenLanguage component1() {
        return this.codegenLanguage;
    }

    @NotNull
    public final MockStrategyApi component2() {
        return this.mockStrategy;
    }

    @NotNull
    public final String component3() {
        return this.testFramework;
    }

    @NotNull
    public final StaticsMocking component4() {
        return this.staticsMocking;
    }

    @NotNull
    public final ForceStaticMocking component5() {
        return this.forceStaticMocking;
    }

    @NotNull
    public final TreatOverflowAsError component6() {
        return this.treatOverflowAsError;
    }

    @NotNull
    public final UtStaticEngineConfiguration copy(@NotNull CodegenLanguage codegenLanguage, @NotNull MockStrategyApi mockStrategy, @NotNull String testFramework, @NotNull StaticsMocking staticsMocking, @NotNull ForceStaticMocking forceStaticMocking, @NotNull TreatOverflowAsError treatOverflowAsError) {
        Intrinsics.checkNotNullParameter(codegenLanguage, "codegenLanguage");
        Intrinsics.checkNotNullParameter(mockStrategy, "mockStrategy");
        Intrinsics.checkNotNullParameter(testFramework, "testFramework");
        Intrinsics.checkNotNullParameter(staticsMocking, "staticsMocking");
        Intrinsics.checkNotNullParameter(forceStaticMocking, "forceStaticMocking");
        Intrinsics.checkNotNullParameter(treatOverflowAsError, "treatOverflowAsError");
        return new UtStaticEngineConfiguration(codegenLanguage, mockStrategy, testFramework, staticsMocking, forceStaticMocking, treatOverflowAsError);
    }

    public static /* synthetic */ UtStaticEngineConfiguration copy$default(UtStaticEngineConfiguration utStaticEngineConfiguration, CodegenLanguage codegenLanguage, MockStrategyApi mockStrategyApi, String str, StaticsMocking staticsMocking, ForceStaticMocking forceStaticMocking, TreatOverflowAsError treatOverflowAsError, int i, Object obj) {
        if ((i & 1) != 0) {
            codegenLanguage = utStaticEngineConfiguration.codegenLanguage;
        }
        if ((i & 2) != 0) {
            mockStrategyApi = utStaticEngineConfiguration.mockStrategy;
        }
        if ((i & 4) != 0) {
            str = utStaticEngineConfiguration.testFramework;
        }
        if ((i & 8) != 0) {
            staticsMocking = utStaticEngineConfiguration.staticsMocking;
        }
        if ((i & 16) != 0) {
            forceStaticMocking = utStaticEngineConfiguration.forceStaticMocking;
        }
        if ((i & 32) != 0) {
            treatOverflowAsError = utStaticEngineConfiguration.treatOverflowAsError;
        }
        return utStaticEngineConfiguration.copy(codegenLanguage, mockStrategyApi, str, staticsMocking, forceStaticMocking, treatOverflowAsError);
    }

    @NotNull
    public String toString() {
        return "UtStaticEngineConfiguration(codegenLanguage=" + this.codegenLanguage + ", mockStrategy=" + this.mockStrategy + ", testFramework=" + this.testFramework + ", staticsMocking=" + this.staticsMocking + ", forceStaticMocking=" + this.forceStaticMocking + ", treatOverflowAsError=" + this.treatOverflowAsError + ")";
    }

    public int hashCode() {
        int result = this.codegenLanguage.hashCode();
        return (((((((((result * 31) + this.mockStrategy.hashCode()) * 31) + this.testFramework.hashCode()) * 31) + this.staticsMocking.hashCode()) * 31) + this.forceStaticMocking.hashCode()) * 31) + this.treatOverflowAsError.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UtStaticEngineConfiguration)) {
            return false;
        }
        UtStaticEngineConfiguration utStaticEngineConfiguration = (UtStaticEngineConfiguration) other;
        return this.codegenLanguage == utStaticEngineConfiguration.codegenLanguage && this.mockStrategy == utStaticEngineConfiguration.mockStrategy && Intrinsics.areEqual(this.testFramework, utStaticEngineConfiguration.testFramework) && Intrinsics.areEqual(this.staticsMocking, utStaticEngineConfiguration.staticsMocking) && this.forceStaticMocking == utStaticEngineConfiguration.forceStaticMocking && this.treatOverflowAsError == utStaticEngineConfiguration.treatOverflowAsError;
    }

    public UtStaticEngineConfiguration() {
        this(null, null, null, null, null, null, 63, null);
    }

    public UtStaticEngineConfiguration(@NotNull CodegenLanguage codegenLanguage, @NotNull MockStrategyApi mockStrategy, @NotNull String testFramework, @NotNull StaticsMocking staticsMocking, @NotNull ForceStaticMocking forceStaticMocking, @NotNull TreatOverflowAsError treatOverflowAsError) {
        Intrinsics.checkNotNullParameter(codegenLanguage, "codegenLanguage");
        Intrinsics.checkNotNullParameter(mockStrategy, "mockStrategy");
        Intrinsics.checkNotNullParameter(testFramework, "testFramework");
        Intrinsics.checkNotNullParameter(staticsMocking, "staticsMocking");
        Intrinsics.checkNotNullParameter(forceStaticMocking, "forceStaticMocking");
        Intrinsics.checkNotNullParameter(treatOverflowAsError, "treatOverflowAsError");
        this.codegenLanguage = codegenLanguage;
        this.mockStrategy = mockStrategy;
        this.testFramework = testFramework;
        this.staticsMocking = staticsMocking;
        this.forceStaticMocking = forceStaticMocking;
        this.treatOverflowAsError = treatOverflowAsError;
        this.classesToMockAlways = SetsKt.emptySet();
    }

    public /* synthetic */ UtStaticEngineConfiguration(CodegenLanguage codegenLanguage, MockStrategyApi mockStrategyApi, String str, StaticsMocking staticsMocking, ForceStaticMocking forceStaticMocking, TreatOverflowAsError treatOverflowAsError, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CodegenLanguage.Companion.getDefaultItem() : codegenLanguage, (i & 2) != 0 ? MockStrategyApi.NO_MOCKS : mockStrategyApi, (i & 4) != 0 ? "junit4" : str, (i & 8) != 0 ? StaticsMocking.Companion.getDefaultItem() : staticsMocking, (i & 16) != 0 ? ForceStaticMocking.Companion.getDefaultItem() : forceStaticMocking, (i & 32) != 0 ? TreatOverflowAsError.Companion.getDefaultItem() : treatOverflowAsError);
    }

    @NotNull
    public final CodegenLanguage getCodegenLanguage() {
        return this.codegenLanguage;
    }

    public final void setCodegenLanguage(@NotNull CodegenLanguage codegenLanguage) {
        Intrinsics.checkNotNullParameter(codegenLanguage, "<set-?>");
        this.codegenLanguage = codegenLanguage;
    }

    @NotNull
    public final MockStrategyApi getMockStrategy() {
        return this.mockStrategy;
    }

    public final void setMockStrategy(@NotNull MockStrategyApi mockStrategyApi) {
        Intrinsics.checkNotNullParameter(mockStrategyApi, "<set-?>");
        this.mockStrategy = mockStrategyApi;
    }

    @NotNull
    public final String getTestFramework() {
        return this.testFramework;
    }

    public final void setTestFramework(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.testFramework = str;
    }

    @NotNull
    public final StaticsMocking getStaticsMocking() {
        return this.staticsMocking;
    }

    public final void setStaticsMocking(@NotNull StaticsMocking staticsMocking) {
        Intrinsics.checkNotNullParameter(staticsMocking, "<set-?>");
        this.staticsMocking = staticsMocking;
    }

    @NotNull
    public final ForceStaticMocking getForceStaticMocking() {
        return this.forceStaticMocking;
    }

    public final void setForceStaticMocking(@NotNull ForceStaticMocking forceStaticMocking) {
        Intrinsics.checkNotNullParameter(forceStaticMocking, "<set-?>");
        this.forceStaticMocking = forceStaticMocking;
    }

    @NotNull
    public final TreatOverflowAsError getTreatOverflowAsError() {
        return this.treatOverflowAsError;
    }

    public final void setTreatOverflowAsError(@NotNull TreatOverflowAsError treatOverflowAsError) {
        Intrinsics.checkNotNullParameter(treatOverflowAsError, "<set-?>");
        this.treatOverflowAsError = treatOverflowAsError;
    }

    /* compiled from: UtStaticEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/UtStaticEngineConfiguration$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: UtStaticEngineConfiguration$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @NotNull
    public final Set<String> getClassesToMockAlways() {
        return this.classesToMockAlways;
    }

    public final void setClassesToMockAlways(@NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.classesToMockAlways = set;
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }
}
