package cn.sast.api.incremental;

import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.Collection;
import kotlin.Metadata;
import org.eclipse.jgit.diff.DiffEntry;
import org.jetbrains.annotations.NotNull;

/* compiled from: IncrementalAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H&J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0011"}, d2 = {"Lcn/sast/api/incremental/ModifyInfoFactory;", "", "toDecl", "Lcom/feysh/corax/config/api/XDecl;", "target", "getPatchedDeclsByDiff", "", "diff", "Lorg/eclipse/jgit/diff/DiffEntry;", "getSubDecls", "decl", "createSimpleDeclAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "createInterProceduralAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "getScanAction", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "corax-api"})
/* loaded from: ModifyInfoFactory.class */
public interface ModifyInfoFactory {
    @NotNull
    XDecl toDecl(@NotNull Object obj);

    @NotNull
    Collection<XDecl> getPatchedDeclsByDiff(@NotNull Object obj, @NotNull DiffEntry diffEntry);

    @NotNull
    Collection<XDecl> getSubDecls(@NotNull XDecl xDecl);

    @NotNull
    IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph createSimpleDeclAnalysisDependsGraph();

    @NotNull
    IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph createInterProceduralAnalysisDependsGraph();

    @NotNull
    ProcessRule.ScanAction getScanAction(@NotNull XDecl xDecl);
}
