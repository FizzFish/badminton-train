package cn.sast.framework.engine;

import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.BugMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisReportEnv;", "", "file", "Lcn/sast/api/report/IBugResInfo;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "<init>", "(Lcn/sast/api/report/IBugResInfo;Lcom/feysh/corax/config/api/BugMessage$Env;)V", "getFile", "()Lcn/sast/api/report/IBugResInfo;", "getEnv", "()Lcom/feysh/corax/config/api/BugMessage$Env;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-framework"})
/* loaded from: PreAnalysisReportEnv.class */
public final class PreAnalysisReportEnv {

    @NotNull
    private final IBugResInfo file;

    @NotNull
    private final BugMessage.Env env;

    @NotNull
    public final IBugResInfo component1() {
        return this.file;
    }

    @NotNull
    public final BugMessage.Env component2() {
        return this.env;
    }

    @NotNull
    public final PreAnalysisReportEnv copy(@NotNull IBugResInfo file, @NotNull BugMessage.Env env) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(env, "env");
        return new PreAnalysisReportEnv(file, env);
    }

    public static /* synthetic */ PreAnalysisReportEnv copy$default(PreAnalysisReportEnv preAnalysisReportEnv, IBugResInfo iBugResInfo, BugMessage.Env env, int i, Object obj) {
        if ((i & 1) != 0) {
            iBugResInfo = preAnalysisReportEnv.file;
        }
        if ((i & 2) != 0) {
            env = preAnalysisReportEnv.env;
        }
        return preAnalysisReportEnv.copy(iBugResInfo, env);
    }

    @NotNull
    public String toString() {
        return "PreAnalysisReportEnv(file=" + this.file + ", env=" + this.env + ")";
    }

    public int hashCode() {
        int result = this.file.hashCode();
        return (result * 31) + this.env.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreAnalysisReportEnv)) {
            return false;
        }
        PreAnalysisReportEnv preAnalysisReportEnv = (PreAnalysisReportEnv) other;
        return Intrinsics.areEqual(this.file, preAnalysisReportEnv.file) && Intrinsics.areEqual(this.env, preAnalysisReportEnv.env);
    }

    public PreAnalysisReportEnv(@NotNull IBugResInfo file, @NotNull BugMessage.Env env) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(env, "env");
        this.file = file;
        this.env = env;
    }

    @NotNull
    public final IBugResInfo getFile() {
        return this.file;
    }

    @NotNull
    public final BugMessage.Env getEnv() {
        return this.env;
    }
}
