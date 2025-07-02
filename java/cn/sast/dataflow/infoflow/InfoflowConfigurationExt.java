package cn.sast.dataflow.infoflow;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InfoflowAndroidConfigurationExt.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0012\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/infoflow/InfoflowConfigurationExt;", "", "useSparseOpt", "", "missing_summaries_file", "", "<init>", "(ZLjava/lang/String;)V", "getUseSparseOpt", "()Z", "setUseSparseOpt", "(Z)V", "getMissing_summaries_file", "()Ljava/lang/String;", "setMissing_summaries_file", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "corax-data-flow"})
/* loaded from: InfoflowConfigurationExt.class */
public final class InfoflowConfigurationExt {
    private boolean useSparseOpt;

    @Nullable
    private String missing_summaries_file;

    public final boolean component1() {
        return this.useSparseOpt;
    }

    @Nullable
    public final String component2() {
        return this.missing_summaries_file;
    }

    @NotNull
    public final InfoflowConfigurationExt copy(boolean useSparseOpt, @Nullable String missing_summaries_file) {
        return new InfoflowConfigurationExt(useSparseOpt, missing_summaries_file);
    }

    public static /* synthetic */ InfoflowConfigurationExt copy$default(InfoflowConfigurationExt infoflowConfigurationExt, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = infoflowConfigurationExt.useSparseOpt;
        }
        if ((i & 2) != 0) {
            str = infoflowConfigurationExt.missing_summaries_file;
        }
        return infoflowConfigurationExt.copy(z, str);
    }

    @NotNull
    public String toString() {
        return "InfoflowConfigurationExt(useSparseOpt=" + this.useSparseOpt + ", missing_summaries_file=" + this.missing_summaries_file + ")";
    }

    public int hashCode() {
        int result = Boolean.hashCode(this.useSparseOpt);
        return (result * 31) + (this.missing_summaries_file == null ? 0 : this.missing_summaries_file.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InfoflowConfigurationExt)) {
            return false;
        }
        InfoflowConfigurationExt infoflowConfigurationExt = (InfoflowConfigurationExt) other;
        return this.useSparseOpt == infoflowConfigurationExt.useSparseOpt && Intrinsics.areEqual(this.missing_summaries_file, infoflowConfigurationExt.missing_summaries_file);
    }

    public InfoflowConfigurationExt() {
        this(false, null, 3, null);
    }

    public InfoflowConfigurationExt(boolean useSparseOpt, @Nullable String missing_summaries_file) {
        this.useSparseOpt = useSparseOpt;
        this.missing_summaries_file = missing_summaries_file;
    }

    public /* synthetic */ InfoflowConfigurationExt(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? null : str);
    }

    public final boolean getUseSparseOpt() {
        return this.useSparseOpt;
    }

    public final void setUseSparseOpt(boolean z) {
        this.useSparseOpt = z;
    }

    @Nullable
    public final String getMissing_summaries_file() {
        return this.missing_summaries_file;
    }

    public final void setMissing_summaries_file(@Nullable String str) {
        this.missing_summaries_file = str;
    }
}
