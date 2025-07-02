package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.nio.file.Path;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/* compiled from: ScanFilter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u0002\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H&¨\u0006\u0010"}, d2 = {"Lcn/sast/api/config/MatchContentProvider;", "", "get", "Lcom/feysh/corax/config/api/rules/ProcessRule$FileMatch$MatchTarget;", "file", "Ljava/nio/file/Path;", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch$MatchTarget;", "sf", "Lsoot/SootField;", "sm", "Lsoot/SootMethod;", "sc", "Lsoot/SootClass;", "getClassPath", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassPathMatch$MatchTarget;", "classpath", "corax-api"})
/* loaded from: MatchContentProvider.class */
public interface MatchContentProvider {
    @NotNull
    ProcessRule.FileMatch.MatchTarget get(@NotNull Path path);

    @NotNull
    ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootField sootField);

    @NotNull
    ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootMethod sootMethod);

    @NotNull
    ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootClass sootClass);

    @NotNull
    ProcessRule.ClassPathMatch.MatchTarget getClassPath(@NotNull Path path);
}
