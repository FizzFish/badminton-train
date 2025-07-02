package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProjectConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\u0014\u0010��\u001a\u00020\u0001*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004¨\u0006\u0005"}, d2 = {"validate", "", "", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchItem;", "Lcom/feysh/corax/config/api/rules/ProcessRulesType;", "corax-api"})
@SourceDebugExtension({"SMAP\nProjectConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProjectConfig.kt\ncn/sast/api/config/ProjectConfigKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,45:1\n1863#2,2:46\n*S KotlinDebug\n*F\n+ 1 ProjectConfig.kt\ncn/sast/api/config/ProjectConfigKt\n*L\n9#1:46,2\n*E\n"})
/* loaded from: ProjectConfigKt.class */
public final class ProjectConfigKt {
    public static final void validate(@NotNull List<? extends ProcessRule.IMatchItem> list) {
        String it;
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<? extends ProcessRule.IMatchItem> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            ProcessRule.ErrorCommit errorCommit = (ProcessRule.IMatchItem) element$iv;
            ProcessRule.ErrorCommit errorCommit2 = errorCommit instanceof ProcessRule.ErrorCommit ? errorCommit : null;
            if (errorCommit2 != null && (it = errorCommit2.getError()) != null) {
                throw new IllegalStateException(("Invalid process-regex: `" + errorCommit + "`, error: " + it).toString());
            }
        }
    }
}
