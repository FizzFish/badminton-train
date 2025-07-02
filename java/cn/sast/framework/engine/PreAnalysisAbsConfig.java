package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IPreAnalysisConfig;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u000b\b \u0018��2\u00020\u0001B%\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR$\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/engine/PreAnalysisAbsConfig;", "Lcom/feysh/corax/config/api/IPreAnalysisConfig;", "processRules", "", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchItem;", "Lcom/feysh/corax/config/api/rules/ProcessRulesType;", "incrementalAnalyze", "", "<init>", "(Ljava/util/List;Z)V", "getProcessRules", "()Ljava/util/List;", "setProcessRules", "(Ljava/util/List;)V", "getIncrementalAnalyze", "()Z", "setIncrementalAnalyze", "(Z)V", "corax-framework"})
/* loaded from: PreAnalysisAbsConfig.class */
public abstract class PreAnalysisAbsConfig implements IPreAnalysisConfig {

    @NotNull
    private List<? extends ProcessRule.IMatchItem> processRules;
    private boolean incrementalAnalyze;

    public PreAnalysisAbsConfig() {
        this(null, false, 3, null);
    }

    public PreAnalysisAbsConfig(@NotNull List<? extends ProcessRule.IMatchItem> list, boolean incrementalAnalyze) {
        Intrinsics.checkNotNullParameter(list, "processRules");
        this.processRules = list;
        this.incrementalAnalyze = incrementalAnalyze;
    }

    public /* synthetic */ PreAnalysisAbsConfig(List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? true : z);
    }

    @NotNull
    public List<ProcessRule.IMatchItem> getProcessRules() {
        return this.processRules;
    }

    public void setProcessRules(@NotNull List<? extends ProcessRule.IMatchItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.processRules = list;
    }

    public boolean getIncrementalAnalyze() {
        return this.incrementalAnalyze;
    }

    public void setIncrementalAnalyze(boolean z) {
        this.incrementalAnalyze = z;
    }
}
