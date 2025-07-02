package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.api.report.Report;
import cn.sast.dataflow.interprocedural.check.EntryPath;
import cn.sast.dataflow.interprocedural.check.IPath;
import cn.sast.dataflow.interprocedural.check.InvokeEdgePath;
import cn.sast.dataflow.interprocedural.check.LiteralPath;
import cn.sast.dataflow.interprocedural.check.MergePath;
import cn.sast.dataflow.interprocedural.check.PathGenerator;
import cn.sast.dataflow.interprocedural.check.PathGeneratorImpl;
import cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.PostCallStmtInfo;
import cn.sast.dataflow.interprocedural.check.checker.CheckerModeling;
import cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector;
import cn.sast.dataflow.interprocedural.check.checker.ProgramStateContext;
import cn.sast.dataflow.interprocedural.check.printer.PathDiagnosticsGenerator;
import cn.sast.dataflow.interprocedural.check.printer.SimpleUnitPrinter;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.graph.HashMutableDirectedGraph;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.InterproceduralCFG;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.IIstStoreLocal;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.MGlobal;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.baseimpl.IexConst;
import com.feysh.corax.config.api.report.Region;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.Stmt;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.MutableDirectedGraph;

/* compiled from: AIContext.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� 52\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u00040\u0001:\u000245BG\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001e\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,J \u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0002J\u0016\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020��2\u0006\u00102\u001a\u000203R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#¢\u0006\b\n��\u001a\u0004\b%\u0010\u001f¨\u00066"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "Lcn/sast/idfa/analysis/Context;", "Lsoot/SootMethod;", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "result", "Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "method", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "reverse", "", "isAnalyzable", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/idfa/analysis/InterproceduralCFG;Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;Lsoot/SootMethod;Lsoot/toolkits/graph/DirectedGraph;ZZ)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "getResult", "()Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "entries", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "getEntries", "()Ljava/util/Set;", "setEntries", "(Ljava/util/Set;)V", "reports", "", "Lcn/sast/dataflow/interprocedural/analysis/AIContext$NotCompleteReport;", "getReports", "report", "paths", "Lcn/sast/dataflow/interprocedural/check/IPath;", "ctx", "Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;", "definition", "Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "flushInvalidPathReports", "", "sink", "activeReport", "callee", "pathTransfer", "Lcn/sast/dataflow/interprocedural/check/PointsToGraphBuilder$PathTransfer;", "NotCompleteReport", "Companion", "corax-data-flow"})
/* loaded from: AIContext.class */
public final class AIContext extends Context<SootMethod, Unit, IFact<IValue>> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final SootInfoCache info;

    @NotNull
    private final InterproceduralCFG icfg;

    @NotNull
    private final IIPAnalysisResultCollector result;
    public Set<EntryPath> entries;

    @NotNull
    private final Set<NotCompleteReport> reports;

    @Nullable
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @NotNull
    public final InterproceduralCFG getIcfg() {
        return this.icfg;
    }

    @NotNull
    public final IIPAnalysisResultCollector getResult() {
        return this.result;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AIContext(@Nullable SootInfoCache info, @NotNull InterproceduralCFG icfg, @NotNull IIPAnalysisResultCollector result, @NotNull SootMethod method, @NotNull DirectedGraph<Unit> directedGraph, boolean reverse, boolean isAnalyzable) {
        super(method, directedGraph, reverse, isAnalyzable);
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        this.info = info;
        this.icfg = icfg;
        this.result = result;
        this.reports = new LinkedHashSet();
    }

    @NotNull
    public final Set<EntryPath> getEntries() {
        Set<EntryPath> set = this.entries;
        if (set != null) {
            return set;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entries");
        return null;
    }

    public final void setEntries(@NotNull Set<EntryPath> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.entries = set;
    }

    /* compiled from: AIContext.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003J7\u0010\u0019\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AIContext$NotCompleteReport;", "", "entries", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "sink", "Lcn/sast/dataflow/interprocedural/check/IPath;", "ctx", "Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;", "define", "Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "<init>", "(Ljava/util/Set;Lcn/sast/dataflow/interprocedural/check/IPath;Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;)V", "getEntries", "()Ljava/util/Set;", "getSink", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "getCtx", "()Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;", "getDefine", "()Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: AIContext$NotCompleteReport.class */
    public static final class NotCompleteReport {

        @NotNull
        private final Set<EntryPath> entries;

        @NotNull
        private final IPath sink;

        @NotNull
        private final ProgramStateContext ctx;

        @NotNull
        private final CheckerModeling.Checker define;

        @NotNull
        public final Set<EntryPath> component1() {
            return this.entries;
        }

        @NotNull
        public final IPath component2() {
            return this.sink;
        }

        @NotNull
        public final ProgramStateContext component3() {
            return this.ctx;
        }

        @NotNull
        public final CheckerModeling.Checker component4() {
            return this.define;
        }

        @NotNull
        public final NotCompleteReport copy(@NotNull Set<EntryPath> set, @NotNull IPath sink, @NotNull ProgramStateContext ctx, @NotNull CheckerModeling.Checker define) {
            Intrinsics.checkNotNullParameter(set, "entries");
            Intrinsics.checkNotNullParameter(sink, "sink");
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(define, "define");
            return new NotCompleteReport(set, sink, ctx, define);
        }

        public static /* synthetic */ NotCompleteReport copy$default(NotCompleteReport notCompleteReport, Set set, IPath iPath, ProgramStateContext programStateContext, CheckerModeling.Checker checker, int i, Object obj) {
            if ((i & 1) != 0) {
                set = notCompleteReport.entries;
            }
            if ((i & 2) != 0) {
                iPath = notCompleteReport.sink;
            }
            if ((i & 4) != 0) {
                programStateContext = notCompleteReport.ctx;
            }
            if ((i & 8) != 0) {
                checker = notCompleteReport.define;
            }
            return notCompleteReport.copy(set, iPath, programStateContext, checker);
        }

        @NotNull
        public String toString() {
            return "NotCompleteReport(entries=" + this.entries + ", sink=" + this.sink + ", ctx=" + this.ctx + ", define=" + this.define + ")";
        }

        public int hashCode() {
            int result = this.entries.hashCode();
            return (((((result * 31) + this.sink.hashCode()) * 31) + this.ctx.hashCode()) * 31) + this.define.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotCompleteReport)) {
                return false;
            }
            NotCompleteReport notCompleteReport = (NotCompleteReport) other;
            return Intrinsics.areEqual(this.entries, notCompleteReport.entries) && Intrinsics.areEqual(this.sink, notCompleteReport.sink) && Intrinsics.areEqual(this.ctx, notCompleteReport.ctx) && Intrinsics.areEqual(this.define, notCompleteReport.define);
        }

        public NotCompleteReport(@NotNull Set<EntryPath> set, @NotNull IPath sink, @NotNull ProgramStateContext ctx, @NotNull CheckerModeling.Checker define) {
            Intrinsics.checkNotNullParameter(set, "entries");
            Intrinsics.checkNotNullParameter(sink, "sink");
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(define, "define");
            this.entries = set;
            this.sink = sink;
            this.ctx = ctx;
            this.define = define;
        }

        @NotNull
        public final Set<EntryPath> getEntries() {
            return this.entries;
        }

        @NotNull
        public final IPath getSink() {
            return this.sink;
        }

        @NotNull
        public final ProgramStateContext getCtx() {
            return this.ctx;
        }

        @NotNull
        public final CheckerModeling.Checker getDefine() {
            return this.define;
        }
    }

    @NotNull
    public final Set<NotCompleteReport> getReports() {
        return this.reports;
    }

    /* compiled from: AIContext.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJh\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AIContext$Companion;", "", "<init>", "()V", "findSinkFromExpr", "", "expr", "Lcom/feysh/corax/config/api/IExpr;", "sinkStmt", "Lsoot/jimple/Stmt;", "reportByEntry", "", "notCompletePath", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "generator", "Lcn/sast/dataflow/interprocedural/check/PathGenerator;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "directGraph", "Lsoot/toolkits/graph/DirectedGraph;", "entry", "ctx", "Lcn/sast/dataflow/interprocedural/check/checker/ProgramStateContext;", "definition", "Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "result", "Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nAIContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIContext.kt\ncn/sast/dataflow/interprocedural/analysis/AIContext$Companion\n+ 2 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,192:1\n307#2:193\n1#3:194\n1#3:209\n774#4:195\n865#4,2:196\n669#4,11:198\n1557#4:210\n1628#4,3:211\n*S KotlinDebug\n*F\n+ 1 AIContext.kt\ncn/sast/dataflow/interprocedural/analysis/AIContext$Companion\n*L\n75#1:193\n75#1:194\n86#1:195\n86#1:196,2\n86#1:198,11\n110#1:210\n110#1:211,3\n*E\n"})
    /* loaded from: AIContext$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final List<Object> findSinkFromExpr(@NotNull final IExpr expr, @NotNull Stmt sinkStmt) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(sinkStmt, "sinkStmt");
            return new PostCallStmtInfo(new IIstStoreLocal() { // from class: cn.sast.dataflow.interprocedural.analysis.AIContext$Companion$findSinkFromExpr$1
                public MLocal getLocal() {
                    return MGlobal.INSTANCE;
                }

                public IExpr getValue() {
                    return expr;
                }

                public <TResult> TResult accept(IModelStmtVisitor<TResult> iModelStmtVisitor) {
                    Intrinsics.checkNotNullParameter(iModelStmtVisitor, "visitor");
                    return (TResult) iModelStmtVisitor.visit(this);
                }
            }, (Unit) sinkStmt).getParameterNamesInUnitDefUse((Unit) sinkStmt);
        }

        public final void reportByEntry(@NotNull Set<EntryPath> set, @NotNull PathGenerator<IPath> pathGenerator, @NotNull DirectedGraph<IPath> directedGraph, @NotNull Set<? extends IPath> set2, @NotNull ProgramStateContext ctx, @NotNull CheckerModeling.Checker definition, @Nullable SootInfoCache info, @NotNull InterproceduralCFG icfg, @NotNull IIPAnalysisResultCollector result) {
            Object obj;
            Intrinsics.checkNotNullParameter(set, "notCompletePath");
            Intrinsics.checkNotNullParameter(pathGenerator, "generator");
            Intrinsics.checkNotNullParameter(directedGraph, "directGraph");
            Intrinsics.checkNotNullParameter(set2, "entry");
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(definition, "definition");
            Intrinsics.checkNotNullParameter(icfg, "icfg");
            Intrinsics.checkNotNullParameter(result, "result");
            Map pathEventsMap = pathGenerator.flush(directedGraph, set2);
            Companion $this$reportByEntry_u24lambda_u240 = this;
            PathDiagnosticsGenerator p = new PathDiagnosticsGenerator(info, icfg, 0);
            Stmt sinkStmt = ctx.getCallSiteStmt();
            String sinCall = SimpleUnitPrinter.Companion.getStringOf(ctx.getCallee1(), null, sinkStmt.containsInvokeExpr() ? sinkStmt.getInvokeExpr() : null, false);
            String sinks = CollectionsKt.joinToString$default($this$reportByEntry_u24lambda_u240.findSinkFromExpr(ctx.getGuard(), sinkStmt), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            List sink = p.emit((Unit) sinkStmt, MapsKt.mapOf(new Pair[]{TuplesKt.to(Language.ZH, "污点汇聚 Sink 点: `" + sinCall + "`, 关键参数: `" + sinks + "` 位于方法 " + ctx.getCallee1()), TuplesKt.to(Language.EN, "Taint sink: `" + sinCall + "`, key argument: `" + sinks + "` of " + ctx.getCallee1())}), (Region) null);
            Map bugPathEventsMap = new LinkedHashMap();
            for (Map.Entry<IPath, List<IPath>> entry : pathEventsMap.entrySet()) {
                IPath head = entry.getKey();
                List events = entry.getValue();
                List $this$filter$iv = events;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    IPath it = (IPath) element$iv$iv;
                    if (!(it instanceof MergePath)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$singleOrNull$iv = (List) destination$iv$iv;
                Object single$iv = null;
                boolean found$iv = false;
                Iterator it2 = $this$singleOrNull$iv.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object element$iv = it2.next();
                        IPath it3 = (IPath) element$iv;
                        if (it3 instanceof LiteralPath) {
                            if (found$iv) {
                                obj = null;
                                break;
                            } else {
                                single$iv = element$iv;
                                found$iv = true;
                            }
                        }
                    } else {
                        obj = !found$iv ? null : single$iv;
                    }
                }
                IPath singleLiteralPath = (IPath) obj;
                if (!(singleLiteralPath instanceof LiteralPath) || !(((LiteralPath) singleLiteralPath).getConst() instanceof IexConst) || ((IexConst) ((LiteralPath) singleLiteralPath).getConst()).getType() != IIexConst.Type.TaintSet || !Intrinsics.areEqual(((LiteralPath) singleLiteralPath).getNode(), ctx.getCallSiteStmt())) {
                    Pair pair = TuplesKt.to(head, SequencesKt.toSet(PathGeneratorImpl.Companion.generateEvents(info, icfg, events)));
                    bugPathEventsMap.put(pair.getFirst(), pair.getSecond());
                }
            }
            if (bugPathEventsMap.isEmpty()) {
                return;
            }
            if ((!set.isEmpty()) && bugPathEventsMap.size() == 1) {
                Pair pair2 = (Pair) CollectionsKt.first(MapsKt.toList(bugPathEventsMap));
                IPath k = (IPath) pair2.component1();
                Set v = (Set) pair2.component2();
                if ((k instanceof LiteralPath) && v.size() == 1 && ((List) CollectionsKt.first(v)).size() == 1) {
                    return;
                }
            }
            Set bugPathEvents = CollectionsKt.toSet(CollectionsKt.flatten(bugPathEventsMap.values()));
            Set set3 = bugPathEvents;
            Collection notEmptyBugPathEvents = set3.isEmpty() ? CollectionsKt.listOf(CollectionsKt.emptyList()) : set3;
            if (!(!notEmptyBugPathEvents.isEmpty())) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Collection $this$map$iv = notEmptyBugPathEvents;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                List it4 = (List) item$iv$iv;
                destination$iv$iv2.add(Report.Companion.of(info, ctx.getResInfo(), ctx.getRegion().getImmutable(), definition.getReport(), ctx, CollectionsKt.plus(it4, sink)));
            }
            List reports = (List) destination$iv$iv2;
            result.reportDataFlowBug(CollectionsKt.toList(reports));
        }
    }

    public final boolean report(@NotNull IPath paths, @NotNull ProgramStateContext ctx, @NotNull CheckerModeling.Checker definition) {
        Intrinsics.checkNotNullParameter(paths, "paths");
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(definition, "definition");
        PathGenerator generator = PathGeneratorImpl.Companion.getPathGenerator();
        DirectedGraph<IPath> hashMutableDirectedGraph = new HashMutableDirectedGraph<>();
        Set heads = generator.getHeads(paths, (MutableDirectedGraph) hashMutableDirectedGraph);
        Set notEntryPaths = new LinkedHashSet();
        Set notCompleteReport = new LinkedHashSet();
        for (IPath head : heads) {
            if (head instanceof EntryPath) {
                notCompleteReport.add(head);
            } else {
                notEntryPaths.add(head);
            }
        }
        if (!notCompleteReport.isEmpty()) {
            this.reports.add(new NotCompleteReport(notCompleteReport, paths, ctx, definition));
        }
        if (!notEntryPaths.isEmpty()) {
            Companion.reportByEntry(notCompleteReport, generator, hashMutableDirectedGraph, notEntryPaths, ctx, definition, this.info, this.icfg, this.result);
            return true;
        }
        if (notCompleteReport.isEmpty()) {
            flushInvalidPathReports(paths, ctx, definition);
        }
        return !notCompleteReport.isEmpty();
    }

    private final void flushInvalidPathReports(IPath sink, ProgramStateContext ctx, CheckerModeling.Checker definition) {
        PathGenerator generator = PathGeneratorImpl.Companion.getPathGenerator();
        DirectedGraph<IPath> hashMutableDirectedGraph = new HashMutableDirectedGraph<>();
        Set heads = generator.getHeads(sink, (MutableDirectedGraph) hashMutableDirectedGraph);
        Companion.reportByEntry(SetsKt.emptySet(), generator, hashMutableDirectedGraph, heads, ctx, definition, this.info, this.icfg, this.result);
    }

    public final void activeReport(@NotNull AIContext callee, @NotNull PointsToGraphBuilder.PathTransfer pathTransfer) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(pathTransfer, "pathTransfer");
        for (NotCompleteReport report : callee.reports) {
            InvokeEdgePath p = pathTransfer.transform(report.getSink(), report.getEntries());
            Intrinsics.checkNotNull(p);
            report(p, report.getCtx(), report.getDefine());
        }
    }
}
