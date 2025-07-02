package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerInfo.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcn/sast/api/config/ChapterFlat;", "", "category", "", "severity", "ruleId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getSeverity", "getRuleId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-api"})
/* loaded from: ChapterFlat.class */
public final class ChapterFlat {

    @NotNull
    private final String category;

    @NotNull
    private final String severity;

    @NotNull
    private final String ruleId;

    @NotNull
    public final String component1() {
        return this.category;
    }

    @NotNull
    public final String component2() {
        return this.severity;
    }

    @NotNull
    public final String component3() {
        return this.ruleId;
    }

    @NotNull
    public final ChapterFlat copy(@NotNull String category, @NotNull String severity, @NotNull String ruleId) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(severity, "severity");
        Intrinsics.checkNotNullParameter(ruleId, "ruleId");
        return new ChapterFlat(category, severity, ruleId);
    }

    public static /* synthetic */ ChapterFlat copy$default(ChapterFlat chapterFlat, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = chapterFlat.category;
        }
        if ((i & 2) != 0) {
            str2 = chapterFlat.severity;
        }
        if ((i & 4) != 0) {
            str3 = chapterFlat.ruleId;
        }
        return chapterFlat.copy(str, str2, str3);
    }

    @NotNull
    public String toString() {
        return "ChapterFlat(category=" + this.category + ", severity=" + this.severity + ", ruleId=" + this.ruleId + ")";
    }

    public int hashCode() {
        int result = this.category.hashCode();
        return (((result * 31) + this.severity.hashCode()) * 31) + this.ruleId.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChapterFlat)) {
            return false;
        }
        ChapterFlat chapterFlat = (ChapterFlat) other;
        return Intrinsics.areEqual(this.category, chapterFlat.category) && Intrinsics.areEqual(this.severity, chapterFlat.severity) && Intrinsics.areEqual(this.ruleId, chapterFlat.ruleId);
    }

    public ChapterFlat(@NotNull String category, @NotNull String severity, @NotNull String ruleId) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(severity, "severity");
        Intrinsics.checkNotNullParameter(ruleId, "ruleId");
        this.category = category;
        this.severity = severity;
        this.ruleId = ruleId;
    }

    @NotNull
    public final String getCategory() {
        return this.category;
    }

    @NotNull
    public final String getSeverity() {
        return this.severity;
    }

    @NotNull
    public final String getRuleId() {
        return this.ruleId;
    }
}
