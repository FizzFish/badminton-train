package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.analysis.SootInfoCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020��2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/api/report/BugPathEventEnvironment;", "", "sootInfoCache", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getSootInfoCache", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-api"})
/* loaded from: BugPathEventEnvironment.class */
public final class BugPathEventEnvironment {

    @Nullable
    private final SootInfoCache sootInfoCache;

    @Nullable
    public final SootInfoCache component1() {
        return this.sootInfoCache;
    }

    @NotNull
    public final BugPathEventEnvironment copy(@Nullable SootInfoCache sootInfoCache) {
        return new BugPathEventEnvironment(sootInfoCache);
    }

    public static /* synthetic */ BugPathEventEnvironment copy$default(BugPathEventEnvironment bugPathEventEnvironment, SootInfoCache sootInfoCache, int i, Object obj) {
        if ((i & 1) != 0) {
            sootInfoCache = bugPathEventEnvironment.sootInfoCache;
        }
        return bugPathEventEnvironment.copy(sootInfoCache);
    }

    @NotNull
    public String toString() {
        return "BugPathEventEnvironment(sootInfoCache=" + this.sootInfoCache + ")";
    }

    public int hashCode() {
        if (this.sootInfoCache == null) {
            return 0;
        }
        return this.sootInfoCache.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BugPathEventEnvironment) && Intrinsics.areEqual(this.sootInfoCache, ((BugPathEventEnvironment) other).sootInfoCache);
    }

    public BugPathEventEnvironment(@Nullable SootInfoCache sootInfoCache) {
        this.sootInfoCache = sootInfoCache;
    }

    @Nullable
    public final SootInfoCache getSootInfoCache() {
        return this.sootInfoCache;
    }
}
