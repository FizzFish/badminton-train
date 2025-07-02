package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IPreAnalysisFileConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\n\b��\u0018��2\u00020\u00012\u00020\u0002B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisFileConfig;", "Lcn/sast/framework/engine/PreAnalysisAbsConfig;", "Lcom/feysh/corax/config/api/IPreAnalysisFileConfig;", "skipFilesInArchive", "", "ignoreProjectConfigProcessFilter", "<init>", "(ZZ)V", "getSkipFilesInArchive", "()Z", "setSkipFilesInArchive", "(Z)V", "getIgnoreProjectConfigProcessFilter", "setIgnoreProjectConfigProcessFilter", "corax-framework"})
/* loaded from: PreAnalysisFileConfig.class */
public final class PreAnalysisFileConfig extends PreAnalysisAbsConfig implements IPreAnalysisFileConfig {
    private boolean skipFilesInArchive;
    private boolean ignoreProjectConfigProcessFilter;

    public PreAnalysisFileConfig() {
        this(false, false, 3, null);
    }

    public PreAnalysisFileConfig(boolean skipFilesInArchive, boolean ignoreProjectConfigProcessFilter) {
        super(null, false, 3, null);
        this.skipFilesInArchive = skipFilesInArchive;
        this.ignoreProjectConfigProcessFilter = ignoreProjectConfigProcessFilter;
    }

    public /* synthetic */ PreAnalysisFileConfig(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public boolean getSkipFilesInArchive() {
        return this.skipFilesInArchive;
    }

    public void setSkipFilesInArchive(boolean z) {
        this.skipFilesInArchive = z;
    }

    public boolean getIgnoreProjectConfigProcessFilter() {
        return this.ignoreProjectConfigProcessFilter;
    }

    public void setIgnoreProjectConfigProcessFilter(boolean z) {
        this.ignoreProjectConfigProcessFilter = z;
    }
}
