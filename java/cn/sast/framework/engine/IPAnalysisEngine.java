package cn.sast.framework.engine;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.MainConfig;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.CoverTaint;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.ICoverageCollector;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.dataflow.infoflow.svfa.AP;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PathCompanionKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraph;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.checker.CheckerModelingKt;
import cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.dataflow.interprocedural.check.heapimpl.ObjectValues;
import cn.sast.framework.SootCtx;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IWrapperFileGenerator;
import cn.sast.framework.report.NullWrapperFileGenerator;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.result.IMissingSummaryReporter;
import cn.sast.framework.result.IPreAnalysisResultCollector;
import cn.sast.graph.GraphPlot;
import cn.sast.graph.GraphPlotKt;
import cn.sast.graph.HashMutableDirectedGraph;
import com.feysh.corax.cache.analysis.SootInfoCache;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JvmStreamsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Value;
import soot.ValueBox;
import soot.jimple.toolkits.callgraph.Edge;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: IPAnalysisEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018�� @2\u00020\u0001:\u0001@B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005¢\u0006\u0004\b\t\u0010\nJh\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00162\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u001c2\u0006\u0010\u001d\u001a\u00020\u00012\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u001f2\u0006\u0010 \u001a\u00020!JF\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00162\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u001cJf\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00142\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110/2\u0006\u00101\u001a\u000202H\u0086@¢\u0006\u0002\u00103J>\u00104\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u00105\u001a\u0002062\u0006\u0010+\u001a\u0002072\u0006\u00101\u001a\u000202H\u0086@¢\u0006\u0002\u00108J\u0018\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002J\u0018\u0010>\u001a\u00020\u00132\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002J\u000e\u0010?\u001a\u00020\u00132\u0006\u0010:\u001a\u00020;R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR!\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n��¨\u0006A"}, d2 = {"Lcn/sast/framework/engine/IPAnalysisEngine;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "summaries", "", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "(Lcn/sast/api/config/MainConfig;Ljava/util/List;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getSummaries", "()Ljava/util/List;", "directedGraph", "Lcn/sast/graph/HashMutableDirectedGraph;", "Lsoot/SootMethod;", "coverTaint", "", "Lcn/sast/api/report/ICoverageCollector;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "method", "node", "Lsoot/Unit;", "succ", "out", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "value", "obj", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "visitElement", "", "runAnalysisInScene", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "soot", "Lcn/sast/framework/SootCtx;", "preAnalysisResult", "Lcn/sast/framework/result/IPreAnalysisResultCollector;", "result", "Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;", "coverage", "entries", "", "methodsMustAnalyze", "missWrapper", "Lcn/sast/framework/result/IMissingSummaryReporter;", "(Lcn/sast/framework/report/ProjectFileLocator;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/framework/SootCtx;Lcn/sast/framework/result/IPreAnalysisResultCollector;Lcn/sast/dataflow/interprocedural/check/checker/IIPAnalysisResultCollector;Lcn/sast/api/report/ICoverageCollector;Ljava/util/Collection;Ljava/util/Collection;Lcn/sast/framework/result/IMissingSummaryReporter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "analyze", "provider", "Lcn/sast/framework/entries/IEntryPointProvider;", "Lcn/sast/framework/result/ResultCollector;", "(Lcn/sast/framework/report/ProjectFileLocator;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/framework/SootCtx;Lcn/sast/framework/entries/IEntryPointProvider;Lcn/sast/framework/result/ResultCollector;Lcn/sast/framework/result/IMissingSummaryReporter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dumpJson", "output", "Lcn/sast/common/IResDirectory;", "name", "", "dumpDot", "dump", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nIPAnalysisEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IPAnalysisEngine.kt\ncn/sast/framework/engine/IPAnalysisEngine\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Logging.kt\norg/utbot/common/LoggingKt\n+ 5 JvmStreams.kt\nkotlinx/serialization/json/JvmStreamsKt\n*L\n1#1,344:1\n1557#2:345\n1628#2,3:346\n1619#2:349\n1863#2:350\n1864#2:352\n1620#2:353\n1863#2,2:354\n1557#2:356\n1628#2,3:357\n774#2:360\n865#2,2:361\n774#2:363\n865#2,2:364\n1557#2:366\n1628#2,3:367\n774#2:370\n865#2,2:371\n774#2:373\n865#2,2:374\n1557#2:376\n1628#2,3:377\n774#2:380\n865#2,2:381\n1557#2:383\n1628#2,3:384\n1#3:351\n49#4,24:387\n42#5:411\n*S KotlinDebug\n*F\n+ 1 IPAnalysisEngine.kt\ncn/sast/framework/engine/IPAnalysisEngine\n*L\n143#1:345\n143#1:346,3\n143#1:349\n143#1:350\n143#1:352\n143#1:353\n245#1:354,2\n259#1:356\n259#1:357,3\n260#1:360\n260#1:361,2\n261#1:363\n261#1:364,2\n262#1:366\n262#1:367,3\n263#1:370\n263#1:371,2\n264#1:373\n264#1:374,2\n265#1:376\n265#1:377,3\n267#1:380\n267#1:381,2\n268#1:383\n268#1:384,3\n143#1:351\n281#1:387,24\n303#1:411\n*E\n"})
/* loaded from: IPAnalysisEngine.class */
public final class IPAnalysisEngine {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final List<SummaryHandlePackage<IValue>> summaries;

    @NotNull
    private final HashMutableDirectedGraph<SootMethod> directedGraph;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(IPAnalysisEngine::logger$lambda$18);

    /* compiled from: IPAnalysisEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "IPAnalysisEngine.kt", l = {282}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "analyze", c = "cn.sast.framework.engine.IPAnalysisEngine")
    /* renamed from: cn.sast.framework.engine.IPAnalysisEngine$analyze$1, reason: invalid class name */
    /* loaded from: IPAnalysisEngine$analyze$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return IPAnalysisEngine.this.analyze(null, null, null, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: IPAnalysisEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "IPAnalysisEngine.kt", l = {228}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, n = {"this", "result", "entries", "methodsMustAnalyze", "missWrapper", "analysis", "checker"}, m = "runAnalysisInScene", c = "cn.sast.framework.engine.IPAnalysisEngine")
    /* renamed from: cn.sast.framework.engine.IPAnalysisEngine$runAnalysisInScene$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: IPAnalysisEngine$runAnalysisInScene$1.class */
    static final class C00201 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        /* synthetic */ Object result;
        int label;

        C00201(Continuation<? super C00201> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return IPAnalysisEngine.this.runAnalysisInScene(null, null, null, null, null, null, null, null, null, (Continuation) this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IPAnalysisEngine(@NotNull MainConfig mainConfig, @NotNull List<? extends SummaryHandlePackage<IValue>> list) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(list, "summaries");
        this.mainConfig = mainConfig;
        this.summaries = list;
        this.directedGraph = new HashMutableDirectedGraph<>();
    }

    public /* synthetic */ IPAnalysisEngine(MainConfig mainConfig, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mainConfig, (i & 2) != 0 ? new ArrayList() : list);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final List<SummaryHandlePackage<IValue>> getSummaries() {
        return this.summaries;
    }

    /* compiled from: IPAnalysisEngine.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/IPAnalysisEngine$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: IPAnalysisEngine$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$18() {
        return Unit.INSTANCE;
    }

    public final void coverTaint(@NotNull ICoverageCollector $this$coverTaint, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull SootMethod method, @NotNull soot.Unit node, @NotNull soot.Unit succ, @NotNull IFact<IValue> iFact, @NotNull Object value, @NotNull CompanionV<IValue> companionV, boolean visitElement) {
        Intrinsics.checkNotNullParameter($this$coverTaint, "<this>");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        Intrinsics.checkNotNullParameter(iFact, "out");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(companionV, "obj");
        IValue actual = PathCompanionKt.getBindDelegate(companionV);
        IData<IValue> valueData = iFact.getValueData(actual, CheckerModelingKt.getKeyTaintProperty());
        ImmutableElementSet taintSet = valueData instanceof ImmutableElementSet ? (ImmutableElementSet) valueData : null;
        if (taintSet != null && !taintSet.isEmpty()) {
            $this$coverTaint.cover(new CoverTaint(method, node, value));
        }
        if (visitElement) {
            IData<IValue> valueData2 = iFact.getValueData(actual, BuiltInModelT.Array);
            IArrayHeapKV array = valueData2 instanceof IArrayHeapKV ? (IArrayHeapKV) valueData2 : null;
            if (array != null) {
                for (CompanionV e : array.getElement(abstractHeapFactory)) {
                    coverTaint($this$coverTaint, abstractHeapFactory, method, node, succ, iFact, value, e, false);
                }
            }
            IData<IValue> valueData3 = iFact.getValueData(actual, BuiltInModelT.Element);
            ObjectValues elements = valueData3 instanceof ObjectValues ? (ObjectValues) valueData3 : null;
            if (elements != null) {
                for (CompanionV e2 : elements.getValues()) {
                    coverTaint($this$coverTaint, abstractHeapFactory, method, node, succ, iFact, value, e2, false);
                }
            }
            IData<IValue> valueData4 = iFact.getValueData(actual, BuiltInModelT.MapValues);
            ObjectValues mapValues = valueData4 instanceof ObjectValues ? (ObjectValues) valueData4 : null;
            if (mapValues != null) {
                for (CompanionV e3 : mapValues.getValues()) {
                    coverTaint($this$coverTaint, abstractHeapFactory, method, node, succ, iFact, value, e3, false);
                }
            }
        }
    }

    public final void coverTaint(@NotNull ICoverageCollector $this$coverTaint, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull SootMethod method, @NotNull soot.Unit node, @NotNull soot.Unit succ, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter($this$coverTaint, "<this>");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        Intrinsics.checkNotNullParameter(iFact, "out");
        if (iFact.isValid()) {
            Iterable useAndDefBoxes = node.getUseAndDefBoxes();
            Intrinsics.checkNotNullExpressionValue(useAndDefBoxes, "getUseAndDefBoxes(...)");
            Iterable $this$map$iv = useAndDefBoxes;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv.add(((ValueBox) item$iv$iv).getValue());
            }
            Iterable $this$mapNotNullTo$iv = (List) destination$iv$iv;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object element$iv$iv : $this$mapNotNullTo$iv) {
                Value it = (Value) element$iv$iv;
                AP.Companion companion = AP.Companion;
                Intrinsics.checkNotNull(it);
                AP ap = companion.get(it);
                if (ap != null) {
                    destination$iv.add(ap);
                }
            }
            Set<AP> accessPaths = (Set) destination$iv;
            for (AP accessPath : accessPaths) {
                if (accessPath.getField() == null) {
                    IHeapValues objects = ((PointsToGraph) iFact).getTargets(accessPath.getValue());
                    for (CompanionV obj : objects) {
                        coverTaint($this$coverTaint, abstractHeapFactory, method, node, succ, iFact, accessPath.getValue(), obj, true);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object runAnalysisInScene(@org.jetbrains.annotations.NotNull cn.sast.framework.report.ProjectFileLocator r11, @org.jetbrains.annotations.NotNull com.feysh.corax.cache.analysis.SootInfoCache r12, @org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r13, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IPreAnalysisResultCollector r14, @org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector r15, @org.jetbrains.annotations.Nullable cn.sast.api.report.ICoverageCollector r16, @org.jetbrains.annotations.NotNull java.util.Collection<? extends soot.SootMethod> r17, @org.jetbrains.annotations.NotNull java.util.Collection<? extends soot.SootMethod> r18, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IMissingSummaryReporter r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            Method dump skipped, instructions count: 1927
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.IPAnalysisEngine.runAnalysisInScene(cn.sast.framework.report.ProjectFileLocator, com.feysh.corax.cache.analysis.SootInfoCache, cn.sast.framework.SootCtx, cn.sast.framework.result.IPreAnalysisResultCollector, cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector, cn.sast.api.report.ICoverageCollector, java.util.Collection, java.util.Collection, cn.sast.framework.result.IMissingSummaryReporter, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object runAnalysisInScene$default(IPAnalysisEngine iPAnalysisEngine, ProjectFileLocator projectFileLocator, SootInfoCache sootInfoCache, SootCtx sootCtx, IPreAnalysisResultCollector iPreAnalysisResultCollector, IIPAnalysisResultCollector iIPAnalysisResultCollector, ICoverageCollector iCoverageCollector, Collection collection, Collection collection2, IMissingSummaryReporter iMissingSummaryReporter, Continuation continuation, int i, Object obj) {
        if ((i & 32) != 0) {
            iCoverageCollector = null;
        }
        return iPAnalysisEngine.runAnalysisInScene(projectFileLocator, sootInfoCache, sootCtx, iPreAnalysisResultCollector, iIPAnalysisResultCollector, iCoverageCollector, collection, collection2, iMissingSummaryReporter, continuation);
    }

    private static final boolean runAnalysisInScene$lambda$2(boolean $apponly, ProjectFileLocator $locator, SootMethod it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ($apponly || !it.getDeclaringClass().isLibraryClass() || $locator.get((IBugResInfo) ClassResInfo.Companion.of(it), (IWrapperFileGenerator) NullWrapperFileGenerator.INSTANCE) == null) ? false : true;
    }

    private static final Unit runAnalysisInScene$lambda$13(IMissingSummaryReporter $missWrapper, SootMethod miss) {
        Intrinsics.checkNotNullParameter(miss, "miss");
        $missWrapper.reportMissingMethod(miss);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyze(@org.jetbrains.annotations.NotNull cn.sast.framework.report.ProjectFileLocator r11, @org.jetbrains.annotations.NotNull com.feysh.corax.cache.analysis.SootInfoCache r12, @org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r13, @org.jetbrains.annotations.NotNull cn.sast.framework.entries.IEntryPointProvider r14, @org.jetbrains.annotations.NotNull cn.sast.framework.result.ResultCollector r15, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IMissingSummaryReporter r16, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            Method dump skipped, instructions count: 553
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.IPAnalysisEngine.analyze(cn.sast.framework.report.ProjectFileLocator, com.feysh.corax.cache.analysis.SootInfoCache, cn.sast.framework.SootCtx, cn.sast.framework.entries.IEntryPointProvider, cn.sast.framework.result.ResultCollector, cn.sast.framework.result.IMissingSummaryReporter, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void dumpJson(IResDirectory output, String name) throws IOException {
        IResource cgJson = output.resolve(name);
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(cgJson.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        try {
            OutputStream out = outputStream;
            try {
                Json $this$encodeToStream$iv = IPAnalysisEngineKt.getGraphJson();
                Object value$iv = this.directedGraph;
                JvmStreamsKt.encodeToStream($this$encodeToStream$iv, HashMutableDirectedGraph.Companion.serializer(SerializersKt.noCompiledSerializer($this$encodeToStream$iv.getSerializersModule(), Reflection.getOrCreateKotlinClass(SootMethod.class))), value$iv, out);
                logger.info(() -> {
                    return dumpJson$lambda$16$lambda$15(r1);
                });
            } catch (Exception e) {
                logger.error("failed to encodeToStream jsonGraph", e);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, (Throwable) null);
        } catch (Throwable th) {
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            throw th;
        }
    }

    private static final Object dumpJson$lambda$16$lambda$15(IResource $cgJson) {
        return "json call graph: " + $cgJson;
    }

    private final void dumpDot(IResDirectory output, String name) {
        IResFile cg = output.resolve(name).toFile();
        final HashMutableDirectedGraph<SootMethod> hashMutableDirectedGraph = this.directedGraph;
        try {
            GraphPlotKt.dump(GraphPlot.plot$default(new GraphPlot<SootClass, SootMethod>(hashMutableDirectedGraph) { // from class: cn.sast.framework.engine.IPAnalysisEngine$dumpDot$plot$1
                {
                    super((DirectedGraph) hashMutableDirectedGraph);
                }

                @Override // cn.sast.graph.GraphPlot
                public SootClass getNodeContainer(SootMethod $this$getNodeContainer) {
                    Intrinsics.checkNotNullParameter($this$getNodeContainer, "<this>");
                    SootClass declaringClass = $this$getNodeContainer.getDeclaringClass();
                    Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
                    return declaringClass;
                }
            }, null, 1, null), cg);
            logger.info(() -> {
                return dumpDot$lambda$17(r1);
            });
        } catch (Exception e) {
            logger.error("failed to render dotGraph", e);
        }
    }

    private static final Object dumpDot$lambda$17(IResFile $cg) {
        return "dot call graph: " + $cg;
    }

    public final void dump(@NotNull IResDirectory output) throws IOException {
        SootMethod tgt;
        Intrinsics.checkNotNullParameter(output, "output");
        dumpDot(output, "forward_interprocedural_callgraph" + ".dot");
        dumpJson(output, "forward_interprocedural_callgraph" + ".json");
        Iterator it = Scene.v().getCallGraph().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Edge edge = (Edge) it.next();
            SootMethod src = edge.src();
            if (src != null && (tgt = edge.tgt()) != null) {
                this.directedGraph.addEdge(src, tgt);
            }
        }
        dumpJson(output, "forward_interprocedural_callgraph" + "_complete.json");
        if (ExtSettings.INSTANCE.getDumpCompleteDotCg()) {
            dumpDot(output, "forward_interprocedural_callgraph" + "_complete.dot");
        }
    }
}
