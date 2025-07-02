package cn.sast.api.incremental;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IAnalysisDepends;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.sequences.Sequence;
import org.eclipse.jgit.diff.DiffEntry;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.jimple.toolkits.callgraph.CallGraph;

/* compiled from: IncrementalAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018��2\u00020\u0001:\u0003\u0013\u0014\u0015J$\u0010\n\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J$\u0010\u0010\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bj\u0002`\r2\u0006\u0010\u0011\u001a\u00020\u0012H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles;", "Lcn/sast/api/incremental/IncrementalAnalyze;", "simpleDeclAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "getSimpleDeclAnalysisDependsGraph", "()Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "interProceduralAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "getInterProceduralAnalysisDependsGraph", "()Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "getChangeTypeOfClass", "Lkotlin/Pair;", "Lorg/eclipse/jgit/diff/DiffEntry;", "Lcn/sast/api/incremental/ChangeType;", "cls", "Lsoot/SootClass;", "getChangeTypeOfFile", "file", "", "IDependsGraph", "SimpleDeclAnalysisDependsGraph", "InterProceduralAnalysisDependsGraph", "corax-api"})
/* loaded from: IncrementalAnalyzeByChangeFiles.class */
public interface IncrementalAnalyzeByChangeFiles extends IncrementalAnalyze {

    /* compiled from: IncrementalAnalyze.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u000b\u001a\u00020\bH&J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "Lcom/feysh/corax/config/api/IAnalysisDepends;", "visitChangedDecl", "", "diffPath", "", "changed", "", "Lcom/feysh/corax/config/api/XDecl;", "shouldReAnalyzeDecl", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "target", "shouldReAnalyzeTarget", "", "targetRelate", "Lkotlin/sequences/Sequence;", "targetsRelate", "targets", "factory", "Lcn/sast/api/incremental/ModifyInfoFactory;", "getFactory", "()Lcn/sast/api/incremental/ModifyInfoFactory;", "corax-api"})
    /* loaded from: IncrementalAnalyzeByChangeFiles$IDependsGraph.class */
    public interface IDependsGraph extends IAnalysisDepends {
        void visitChangedDecl(@NotNull String str, @NotNull Collection<? extends XDecl> collection);

        @NotNull
        ProcessRule.ScanAction shouldReAnalyzeDecl(@NotNull XDecl xDecl);

        @NotNull
        ProcessRule.ScanAction shouldReAnalyzeTarget(@NotNull Object obj);

        @NotNull
        Sequence<XDecl> targetRelate(@NotNull XDecl xDecl);

        @NotNull
        Sequence<XDecl> targetsRelate(@NotNull Collection<? extends XDecl> collection);

        @NotNull
        ModifyInfoFactory getFactory();
    }

    /* compiled from: IncrementalAnalyze.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "update", "", "cg", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "corax-api"})
    /* loaded from: IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph.class */
    public interface InterProceduralAnalysisDependsGraph extends IDependsGraph {
        void update(@NotNull CallGraph callGraph);
    }

    /* compiled from: IncrementalAnalyze.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "corax-api"})
    /* loaded from: IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph.class */
    public interface SimpleDeclAnalysisDependsGraph extends IDependsGraph {
    }

    @NotNull
    SimpleDeclAnalysisDependsGraph getSimpleDeclAnalysisDependsGraph();

    @NotNull
    InterProceduralAnalysisDependsGraph getInterProceduralAnalysisDependsGraph();

    @NotNull
    Pair<DiffEntry, DiffEntry> getChangeTypeOfClass(@NotNull SootClass sootClass);

    @NotNull
    Pair<DiffEntry, DiffEntry> getChangeTypeOfFile(@NotNull String str);
}
