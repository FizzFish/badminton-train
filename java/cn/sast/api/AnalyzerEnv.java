package cn.sast.api;

import cn.sast.common.IResFile;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnalyzerEnv.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcn/sast/api/AnalyzerEnv;", "", "<init>", "()V", "shouldV3r14y", "", "getShouldV3r14y", "()Z", "setShouldV3r14y", "(Z)V", "bvs1n3ss", "Ljava/util/concurrent/atomic/AtomicInteger;", "getBvs1n3ss", "()Ljava/util/concurrent/atomic/AtomicInteger;", "setBvs1n3ss", "(Ljava/util/concurrent/atomic/AtomicInteger;)V", "lastLogFile", "Lcn/sast/common/IResFile;", "getLastLogFile", "()Lcn/sast/common/IResFile;", "corax-api"})
/* loaded from: AnalyzerEnv.class */
public final class AnalyzerEnv {

    @NotNull
    public static final AnalyzerEnv INSTANCE = new AnalyzerEnv();
    private static boolean shouldV3r14y = true;

    @NotNull
    private static AtomicInteger bvs1n3ss = new AtomicInteger(0);

    @NotNull
    private static final IResFile lastLogFile = Resource.INSTANCE.fileOf(System.getProperty("user.home") + File.separator + "logs" + File.separator + "corax" + File.separator + "last.log");

    private AnalyzerEnv() {
    }

    public final boolean getShouldV3r14y() {
        return shouldV3r14y;
    }

    public final void setShouldV3r14y(boolean z) {
        shouldV3r14y = z;
    }

    @NotNull
    public final AtomicInteger getBvs1n3ss() {
        return bvs1n3ss;
    }

    public final void setBvs1n3ss(@NotNull AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<set-?>");
        bvs1n3ss = atomicInteger;
    }

    @NotNull
    public final IResFile getLastLogFile() {
        return lastLogFile;
    }
}
