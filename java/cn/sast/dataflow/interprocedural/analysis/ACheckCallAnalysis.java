package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.report.Counter;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.ForwardInterProceduralAnalysis;
import cn.sast.idfa.analysis.InterproceduralCFG;
import cn.sast.idfa.check.CallBackManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.Local;
import soot.PrimType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.DefinitionStmt;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.Stmt;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.UnitGraph;

/* compiled from: ACheckCallAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018�� Y2\u0012\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0001YB!\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dJ'\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00150!¢\u0006\u0002\b#J+\u0010$\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u001b\u0010$\u001a\u0017\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00150!j\u0002`&¢\u0006\u0002\b#J+\u0010'\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u001b\u0010(\u001a\u0017\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00150!j\u0002`*¢\u0006\u0002\b#J\u0016\u0010+\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-J\u0016\u0010.\u001a\u00020\u00152\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0016J$\u0010.\u001a\u00020\u00152\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002\f\u00102\u001a\b\u0012\u0004\u0012\u00020100H\u0016J\u001e\u00103\u001a\b\u0012\u0004\u0012\u000205042\u0006\u00106\u001a\u0002012\u0006\u00107\u001a\u00020\u0019H\u0016J(\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0010\u0010=\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030>Jj\u0010$\u001a,\u0012\u0004\u0012\u000201\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@\u0012\u0010\u0012\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010A\u0018\u00010?2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u0002012\u0006\u0010;\u001a\u0002052\u0006\u0010D\u001a\u0002052\u0010\u0010E\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@H\u0096@¢\u0006\u0002\u0010FJ \u0010G\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@2\u0006\u0010B\u001a\u00020\u0004H\u0096@¢\u0006\u0002\u0010HJJ\u0010I\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u0002012\u0006\u0010;\u001a\u0002052\u0006\u0010D\u001a\u0002052\u0010\u0010J\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@H\u0096@¢\u0006\u0002\u0010FJ\u001a\u0010K\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@2\u0006\u0010B\u001a\u00020\u0004H\u0016J:\u0010L\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010;\u001a\u0002052\u0010\u0010M\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@H\u0096@¢\u0006\u0002\u0010NJ\u0010\u0010S\u001a\u00020\u00152\u0006\u0010C\u001a\u000201H\u0016J~\u0010T\u001a*\u0012\u0004\u0012\u000201\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@\u0012\u0010\u0012\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010A0?2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010;\u001a\u0002052\u0006\u0010D\u001a\u0002052.\u0010U\u001a*\u0012\u0004\u0012\u000201\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@\u0012\u0010\u0012\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010A0?H\u0096@¢\u0006\u0002\u0010VJ>\u0010W\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010@2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010;\u001a\u0002052\u0006\u0010D\u001a\u0002052\u0010\u0010X\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@H\u0016J\"\u00107\u001a\u00020\u00192\u0006\u0010C\u001a\u0002012\u0010\u0010U\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030@H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR!\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00110\u0010¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010O\u001a\b\u0012\u0004\u0012\u0002010P¢\u0006\b\n��\u001a\u0004\bQ\u0010R¨\u0006Z"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "callBackManager", "Lcn/sast/idfa/check/CallBackManager;", "getCallBackManager", "()Lcn/sast/idfa/check/CallBackManager;", "summaries", "", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "getSummaries", "()Ljava/util/List;", "registerWrapper", "", "smr", "", "isStatic", "", "Lsoot/SootMethodRef;", "registerClassAllWrapper", "sc", "Lsoot/SootClass;", "evalCallAtCaller", "methodSignature", "prevCall", "Lkotlin/Function1;", "Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$EvalCall;", "Lkotlin/ExtensionFunctionType;", "evalCall", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;", "Lcn/sast/dataflow/interprocedural/analysis/EvalCallInCallee;", "postCallAtCaller", "postCall", "Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$PostCall;", "Lcn/sast/dataflow/interprocedural/analysis/PostCallInCallee;", "registerJimpleWrapper", "jimple", "Lsoot/toolkits/graph/UnitGraph;", "doAnalysis", "entries", "", "Lsoot/SootMethod;", "methodsMustAnalyze", "getCfg", "Lsoot/toolkits/graph/DirectedGraph;", "Lsoot/Unit;", "method", "isAnalyzable", "returnPhantom", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "node", "Lsoot/jimple/Stmt;", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "context", "callee", "succ", "inValue", "(Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lsoot/SootMethod;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeEntryValue", "(Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prevCallFunction", "callSiteValue", "computeExitValue", "returnFlowFunction", "returnValue", "(Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "excludeMethods", "Lcn/sast/api/report/Counter;", "getExcludeMethods", "()Lcn/sast/api/report/Counter;", "skip", "postCallAtCallSite", "in1", "(Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lsoot/Unit;Lsoot/Unit;Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wideningFunction", "in", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nACheckCallAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ACheckCallAnalysis.kt\ncn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CheckerManager.kt\ncn/sast/idfa/check/CallBackManager\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,426:1\n1863#2,2:427\n1863#2:438\n1864#2:440\n1863#2,2:462\n1863#2,2:469\n1863#2,2:487\n1863#2,2:494\n1863#2,2:512\n83#3,3:429\n83#3,3:432\n83#3,3:435\n146#3,2:441\n137#3,14:443\n137#3,5:457\n137#3,5:464\n146#3,2:471\n137#3,14:473\n137#3,5:489\n146#3,2:496\n137#3,14:498\n1#4:439\n*S KotlinDebug\n*F\n+ 1 ACheckCallAnalysis.kt\ncn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis\n*L\n65#1:427,2\n108#1:438\n108#1:440\n199#1:462,2\n214#1:469,2\n254#1:487,2\n293#1:494,2\n329#1:512,2\n80#1:429,3\n86#1:432,3\n92#1:435,3\n157#1:441,2\n157#1:443,14\n199#1:457,5\n214#1:464,5\n254#1:471,2\n254#1:473,14\n293#1:489,5\n329#1:496,2\n329#1:498,14\n*E\n"})
/* loaded from: ACheckCallAnalysis.class */
public abstract class ACheckCallAnalysis extends AJimpleInterProceduralAnalysis<IValue, AIContext> {

    @NotNull
    private final CallBackManager callBackManager;

    @NotNull
    private final List<SummaryHandlePackage<IValue>> summaries;

    @NotNull
    private final Counter<SootMethod> excludeMethods;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(ACheckCallAnalysis::logger$lambda$21);

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ACheckCallAnalysis.kt", l = {193, 204, 219}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 2, 2, 2}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8"}, n = {"$this", "context", "curValue", "$this", "context", "curValue", "node", "callee", "env", "bak", "prevCall", "$this", "context", "curValue", "node", "callee", "env", "bak", "evalCall"}, m = "computeEntryValue$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis$computeEntryValue$1, reason: invalid class name */
    /* loaded from: ACheckCallAnalysis$computeEntryValue$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        Object L$10;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ACheckCallAnalysis.computeEntryValue$suspendImpl(ACheckCallAnalysis.this, (AIContext) null, (Continuation<? super IFact<IValue>>) this);
        }
    }

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ACheckCallAnalysis.kt", l = {166}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "I$0"}, n = {"$this", "callee", "node", "curValue", "caller", "lastResult", "env", "bak", "evalCall", "hasEvalCall"}, m = "evalCall$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis$evalCall$2, reason: invalid class name */
    /* loaded from: ACheckCallAnalysis$evalCall$2.class */
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int I$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ACheckCallAnalysis.evalCall$suspendImpl(ACheckCallAnalysis.this, (AIContext) null, (SootMethod) null, (Unit) null, (Unit) null, (IFact<IValue>) null, (Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>>) this);
        }
    }

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ACheckCallAnalysis.kt", l = {337}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$11"}, n = {"$this", "node", "in1", "caller", "callee", "curBuilder", "change", "lastResult", "env", "bak", "postCall"}, m = "postCallAtCallSite$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis$postCallAtCallSite$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ACheckCallAnalysis$postCallAtCallSite$1.class */
    static final class C00111 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        Object L$10;
        Object L$11;
        /* synthetic */ Object result;
        int label;

        C00111(Continuation<? super C00111> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ACheckCallAnalysis.postCallAtCallSite$suspendImpl(ACheckCallAnalysis.this, (AIContext) null, (Unit) null, (Unit) null, (ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>) null, (Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>>) this);
        }
    }

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ACheckCallAnalysis.kt", l = {259}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8"}, n = {"$this", "callee", "node", "caller", "curValue", "env", "bak", "prevCall"}, m = "prevCallFunction$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis$prevCallFunction$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ACheckCallAnalysis$prevCallFunction$1.class */
    static final class C00121 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        Object L$10;
        /* synthetic */ Object result;
        int label;

        C00121(Continuation<? super C00121> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ACheckCallAnalysis.prevCallFunction$suspendImpl(ACheckCallAnalysis.this, (AIContext) null, (SootMethod) null, (Unit) null, (Unit) null, (IFact<IValue>) null, (Continuation<? super IFact<IValue>>) this);
        }
    }

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "ACheckCallAnalysis.kt", l = {298}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7"}, n = {"$this", "callee", "env", "tail", "curValue", "bak", "postCall"}, m = "returnFlowFunction$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis$returnFlowFunction$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ACheckCallAnalysis$returnFlowFunction$1.class */
    static final class C00131 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        /* synthetic */ Object result;
        int label;

        C00131(Continuation<? super C00131> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ACheckCallAnalysis.returnFlowFunction$suspendImpl(ACheckCallAnalysis.this, (AIContext) null, (Unit) null, (IFact<IValue>) null, (Continuation<? super IFact<IValue>>) this);
        }
    }

    @Nullable
    public Object evalCall(@NotNull AIContext context, @NotNull SootMethod callee, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<IValue> iFact, @NotNull Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>> continuation) {
        return evalCall$suspendImpl(this, context, callee, node, succ, iFact, continuation);
    }

    @Nullable
    public Object computeEntryValue(@NotNull AIContext context, @NotNull Continuation<? super IFact<IValue>> continuation) {
        return computeEntryValue$suspendImpl(this, context, continuation);
    }

    @Nullable
    public Object prevCallFunction(@NotNull AIContext context, @NotNull SootMethod callee, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<IValue> iFact, @NotNull Continuation<? super IFact<IValue>> continuation) {
        return prevCallFunction$suspendImpl(this, context, callee, node, succ, iFact, continuation);
    }

    @Nullable
    public Object returnFlowFunction(@NotNull AIContext context, @NotNull Unit node, @NotNull IFact<IValue> iFact, @NotNull Continuation<? super IFact<IValue>> continuation) {
        return returnFlowFunction$suspendImpl(this, context, node, iFact, continuation);
    }

    @Nullable
    public Object postCallAtCallSite(@NotNull AIContext context, @NotNull Unit node, @NotNull Unit succ, @NotNull ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>> invokeResult, @NotNull Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>> continuation) {
        return postCallAtCallSite$suspendImpl(this, context, node, succ, invokeResult, continuation);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object evalCall(Context context, SootMethod sootMethod, Unit unit, Unit unit2, Object inValue, Continuation $completion) {
        return evalCall((AIContext) context, sootMethod, unit, unit2, (IFact<IValue>) inValue, (Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>>) $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object computeEntryValue(Context context, Continuation $completion) {
        return computeEntryValue((AIContext) context, (Continuation<? super IFact<IValue>>) $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object prevCallFunction(Context context, SootMethod sootMethod, Unit unit, Unit unit2, Object callSiteValue, Continuation $completion) {
        return prevCallFunction((AIContext) context, sootMethod, unit, unit2, (IFact<IValue>) callSiteValue, (Continuation<? super IFact<IValue>>) $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object returnFlowFunction(Context context, Unit unit, Object returnValue, Continuation $completion) {
        return returnFlowFunction((AIContext) context, unit, (IFact<IValue>) returnValue, (Continuation<? super IFact<IValue>>) $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object postCallAtCallSite(Context context, Unit unit, Unit unit2, ForwardInterProceduralAnalysis.InvokeResult in1, Continuation $completion) {
        return postCallAtCallSite((AIContext) context, unit, unit2, (ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>) in1, (Continuation<? super ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<IValue>, IHeapValues<IValue>>>) $completion);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ACheckCallAnalysis(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull InterproceduralCFG icfg) {
        super(abstractHeapFactory, icfg);
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        this.callBackManager = new CallBackManager();
        this.summaries = new ArrayList();
        this.excludeMethods = new Counter<>();
    }

    @NotNull
    public final CallBackManager getCallBackManager() {
        return this.callBackManager;
    }

    @NotNull
    public final List<SummaryHandlePackage<IValue>> getSummaries() {
        return this.summaries;
    }

    public final void registerWrapper(@NotNull String smr, boolean isStatic) {
        Intrinsics.checkNotNullParameter(smr, "smr");
        registerWrapper(SootUtilsKt.sootSignatureToRef(smr, isStatic));
    }

    public final void registerWrapper(@NotNull SootMethodRef smr) {
        Intrinsics.checkNotNullParameter(smr, "smr");
        SootMethod sm = smr.resolve();
        try {
            Body bodyRetrieveActiveBody = sm.retrieveActiveBody();
            if (bodyRetrieveActiveBody == null) {
                return;
            }
            BriefUnitGraph ug = new BriefUnitGraph(bodyRetrieveActiveBody);
            String signature = sm.getSignature();
            Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
            registerJimpleWrapper(signature, (UnitGraph) ug);
        } catch (Exception e) {
        }
    }

    public final void registerClassAllWrapper(@NotNull String sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        SootClass sootClassUnsafe = Scene.v().getSootClassUnsafe(sc, true);
        Intrinsics.checkNotNullExpressionValue(sootClassUnsafe, "getSootClassUnsafe(...)");
        registerClassAllWrapper(sootClassUnsafe);
    }

    public final void registerClassAllWrapper(@NotNull SootClass sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        Iterable methods = sc.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
        Iterable $this$forEach$iv = methods;
        for (Object element$iv : $this$forEach$iv) {
            SootMethod sm = (SootMethod) element$iv;
            if (sm.getSource() == null && !sm.hasActiveBody()) {
                logger.warn(() -> {
                    return registerClassAllWrapper$lambda$1$lambda$0(r1);
                });
            } else {
                Scene.v().forceResolve(sm.getDeclaringClass().getName(), 3);
                Body bodyRetrieveActiveBody = sm.retrieveActiveBody();
                if (bodyRetrieveActiveBody != null) {
                    BriefUnitGraph ug = new BriefUnitGraph(bodyRetrieveActiveBody);
                    String signature = sm.getSignature();
                    Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
                    registerJimpleWrapper(signature, (UnitGraph) ug);
                }
            }
        }
    }

    private static final Object registerClassAllWrapper$lambda$1$lambda$0(SootMethod $sm) {
        return "method source of " + $sm + " is null";
    }

    public final void evalCallAtCaller(@NotNull String methodSignature, @NotNull Function1<? super CallerSiteCBImpl.EvalCall, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        Intrinsics.checkNotNullParameter(function1, "prevCall");
        SootMethod it = Scene.v().grabMethod(methodSignature);
        if (it != null) {
            CallBackManager this_$iv = this.callBackManager;
            this_$iv.put(CallerSiteCBImpl.EvalCall.class, it, new ACheckCallAnalysis$evalCallAtCaller$1$1(function1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object evalCallAtCaller$lambda$2$suspendConversion0(Function1 $this$evalCallAtCaller_u24lambda_u242_u24suspendConversion0, CallerSiteCBImpl.EvalCall p0, Continuation $completion) {
        $this$evalCallAtCaller_u24lambda_u242_u24suspendConversion0.invoke(p0);
        return kotlin.Unit.INSTANCE;
    }

    public final void evalCall(@NotNull String methodSignature, @NotNull Function1<? super CalleeCBImpl.EvalCall, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        Intrinsics.checkNotNullParameter(function1, "evalCall");
        SootMethod it = Scene.v().grabMethod(methodSignature);
        if (it != null) {
            CallBackManager this_$iv = this.callBackManager;
            this_$iv.put(CalleeCBImpl.EvalCall.class, it, new ACheckCallAnalysis$evalCall$1$1(function1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object evalCall$lambda$4$suspendConversion0$3(Function1 $this$evalCall_u24lambda_u244_u24suspendConversion0_u243, CalleeCBImpl.EvalCall p0, Continuation $completion) {
        $this$evalCall_u24lambda_u244_u24suspendConversion0_u243.invoke(p0);
        return kotlin.Unit.INSTANCE;
    }

    public final void postCallAtCaller(@NotNull String methodSignature, @NotNull Function1<? super CallerSiteCBImpl.PostCall, kotlin.Unit> function1) {
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        Intrinsics.checkNotNullParameter(function1, "postCall");
        SootMethod it = Scene.v().grabMethod(methodSignature);
        if (it != null) {
            CallBackManager this_$iv = this.callBackManager;
            this_$iv.put(CallerSiteCBImpl.PostCall.class, it, new ACheckCallAnalysis$postCallAtCaller$1$1(function1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object postCallAtCaller$lambda$6$suspendConversion0$5(Function1 $this$postCallAtCaller_u24lambda_u246_u24suspendConversion0_u245, CallerSiteCBImpl.PostCall p0, Continuation $completion) {
        $this$postCallAtCaller_u24lambda_u246_u24suspendConversion0_u245.invoke(p0);
        return kotlin.Unit.INSTANCE;
    }

    public final void registerJimpleWrapper(@NotNull String methodSignature, @NotNull UnitGraph jimple) {
        Intrinsics.checkNotNullParameter(methodSignature, "methodSignature");
        Intrinsics.checkNotNullParameter(jimple, "jimple");
        SootMethod it = Scene.v().grabMethod(methodSignature);
        if (it != null) {
            this.callBackManager.putUnitGraphOverride(it, jimple);
        }
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis, cn.sast.idfa.analysis.InterProceduralAnalysis
    public void doAnalysis(@NotNull Collection<? extends SootMethod> collection) {
        Intrinsics.checkNotNullParameter(collection, "entries");
        super.doAnalysis(collection);
        this.excludeMethods.clear();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis
    public void doAnalysis(@NotNull Collection<? extends SootMethod> collection, @NotNull Collection<? extends SootMethod> collection2) {
        Intrinsics.checkNotNullParameter(collection, "entries");
        Intrinsics.checkNotNullParameter(collection2, "methodsMustAnalyze");
        Iterable $this$forEach$iv = this.summaries;
        for (Object element$iv : $this$forEach$iv) {
            SummaryHandlePackage it = (SummaryHandlePackage) element$iv;
            it.register(this);
        }
        super.doAnalysis(collection, collection2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @NotNull
    public DirectedGraph<Unit> getCfg(@NotNull SootMethod method, boolean isAnalyzable) {
        Intrinsics.checkNotNullParameter(method, "method");
        DirectedGraph<Unit> unitGraphOverride = this.callBackManager.getUnitGraphOverride(method);
        return unitGraphOverride != null ? unitGraphOverride : super.getCfg(method, isAnalyzable);
    }

    public final void returnPhantom(@NotNull HeapValuesEnv env, @NotNull Stmt node, @NotNull IFact.Builder<IValue> builder) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (node.containsInvokeExpr()) {
            InstanceInvokeExpr invokeExpr = node.getInvokeExpr();
            if (invokeExpr instanceof InstanceInvokeExpr) {
                Local base = invokeExpr.getBase();
                Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                builder.summarizeTargetFields(base);
            }
            int argCount = invokeExpr.getArgCount();
            for (int i = 0; i < argCount; i++) {
                Value argValue = invokeExpr.getArg(i);
                if (argValue instanceof Local) {
                    builder.summarizeTargetFields(argValue);
                }
            }
        }
        if ((node instanceof DefinitionStmt) && ((DefinitionStmt) node).getLeftOp() != null) {
            Value lhsOp = ((DefinitionStmt) node).getLeftOp();
            Intrinsics.checkNotNullExpressionValue(lhsOp, "getLeftOp(...)");
            if (lhsOp instanceof Local) {
                AbstractHeapFactory<IValue> hf = getHf();
                AbstractHeapFactory<IValue> hf2 = getHf();
                Type type = ((DefinitionStmt) node).getRightOp().getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                IFact.Builder.DefaultImpls.assignNewExpr$default(builder, env, lhsOp, hf.push(env, (HeapValuesEnv) hf2.newSummaryVal(env, type, lhsOp)).markSummaryReturnValueFailedInHook().popHV(), false, 8, null);
                return;
            }
            throw new RuntimeException(lhsOp.toString());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0144 A[PHI: r10 r12 r13 r17 r18 r19 r21 r22 r23
  0x0144: PHI (r10v4 '$this' cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis) = 
  (r10v2 '$this' cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis)
  (r10v3 '$this' cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis)
  (r10v0 '$this' cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis)
 binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r12v5 'callee' soot.SootMethod) = (r12v3 'callee' soot.SootMethod), (r12v4 'callee' soot.SootMethod), (r12v0 'callee' soot.SootMethod) binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r13v4 'node' soot.Unit) = (r13v2 'node' soot.Unit), (r13v3 'node' soot.Unit), (r13v0 'node' soot.Unit) binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r17v5 'curValue' cn.sast.dataflow.interprocedural.analysis.IFact$Builder) = 
  (r17v2 'curValue' cn.sast.dataflow.interprocedural.analysis.IFact$Builder)
  (r17v3 'curValue' cn.sast.dataflow.interprocedural.analysis.IFact$Builder)
  (r17v6 'curValue' cn.sast.dataflow.interprocedural.analysis.IFact$Builder)
 binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r18v3 'caller' soot.SootMethod) = (r18v1 'caller' soot.SootMethod), (r18v2 'caller' soot.SootMethod), (r18v4 'caller' soot.SootMethod) binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r19v5 int) = (r19v2 int), (r19v3 int), (r19v6 int) binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r21v5 'lastResult' cn.sast.dataflow.interprocedural.analysis.IHeapValues) = 
  (r21v2 'lastResult' cn.sast.dataflow.interprocedural.analysis.IHeapValues)
  (r21v3 'lastResult' cn.sast.dataflow.interprocedural.analysis.IHeapValues)
  (r21v7 'lastResult' cn.sast.dataflow.interprocedural.analysis.IHeapValues)
 binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r22v3 'env' cn.sast.dataflow.interprocedural.analysis.HookEnv) = 
  (r22v1 'env' cn.sast.dataflow.interprocedural.analysis.HookEnv)
  (r22v2 'env' cn.sast.dataflow.interprocedural.analysis.HookEnv)
  (r22v6 'env' cn.sast.dataflow.interprocedural.analysis.HookEnv)
 binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]
  0x0144: PHI (r23v3 java.util.Iterator) = (r23v1 java.util.Iterator), (r23v2 java.util.Iterator), (r23v5 java.util.Iterator) binds: [B:45:0x0290, B:39:0x0272, B:27:0x0130] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0275 A[Catch: CancellationException -> 0x0289, Exception -> 0x028e, TryCatch #2 {CancellationException -> 0x0289, Exception -> 0x028e, blocks: (B:31:0x0197, B:38:0x026c, B:40:0x0275, B:37:0x0264), top: B:54:0x0264 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02c5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0272 -> B:28:0x0144). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x0290 -> B:28:0x0144). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object evalCall$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis r10, cn.sast.dataflow.interprocedural.analysis.AIContext r11, soot.SootMethod r12, soot.Unit r13, soot.Unit r14, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue> r15, kotlin.coroutines.Continuation<? super cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<soot.SootMethod, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>, cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue>>> r16) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 722
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis.evalCall$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AIContext, soot.SootMethod, soot.Unit, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object evalCall$lambda$10() {
        return "IR intercept exception";
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x04d7 A[Catch: CancellationException -> 0x054e, Exception -> 0x0553, TryCatch #3 {CancellationException -> 0x054e, Exception -> 0x0553, blocks: (B:63:0x0406, B:70:0x04ce, B:72:0x04d7, B:73:0x050b, B:75:0x0515, B:78:0x0536, B:69:0x04c6), top: B:93:0x04c6 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0536 A[Catch: CancellationException -> 0x054e, Exception -> 0x0553, TRY_LEAVE, TryCatch #3 {CancellationException -> 0x054e, Exception -> 0x0553, blocks: (B:63:0x0406, B:70:0x04ce, B:72:0x04d7, B:73:0x050b, B:75:0x0515, B:78:0x0536, B:69:0x04c6), top: B:93:0x04c6 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x0536 -> B:60:0x03ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x0555 -> B:60:0x03ab). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object computeEntryValue$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis r8, cn.sast.dataflow.interprocedural.analysis.AIContext r9, kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>> r10) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 1428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis.computeEntryValue$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AIContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object computeEntryValue$lambda$12$lambda$11() {
        return "IR intercept exception";
    }

    private static final Object computeEntryValue$lambda$14$lambda$13() {
        return "IR intercept exception";
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:30:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object prevCallFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis r10, cn.sast.dataflow.interprocedural.analysis.AIContext r11, soot.SootMethod r12, soot.Unit r13, soot.Unit r14, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue> r15, kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>> r16) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 759
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis.prevCallFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AIContext, soot.SootMethod, soot.Unit, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object prevCallFunction$lambda$16$lambda$15() {
        return "IR intercept exception";
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @NotNull
    public IFact<IValue> computeExitValue(@NotNull AIContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        IFact exit = (IFact) super.computeExitValue((ACheckCallAnalysis) context);
        if (exit.isBottom()) {
            return exit;
        }
        context.getMethod();
        List tails = context.getControlFlowGraph().getTails();
        Intrinsics.checkNotNullExpressionValue(tails, "getTails(...)");
        Object objFirst = CollectionsKt.first(tails);
        Intrinsics.checkNotNull(objFirst, "null cannot be cast to non-null type soot.jimple.Stmt");
        getHf().env((Stmt) objFirst);
        IFact.Builder exitBuilder = exit.builder();
        exitBuilder.gc();
        return exitBuilder.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object returnFlowFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis r8, cn.sast.dataflow.interprocedural.analysis.AIContext r9, soot.Unit r10, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue> r11, kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>> r12) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 693
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis.returnFlowFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AIContext, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object returnFlowFunction$lambda$18$lambda$17() {
        return "IR intercept exception";
    }

    @NotNull
    public final Counter<SootMethod> getExcludeMethods() {
        return this.excludeMethods;
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public void skip(@NotNull SootMethod callee) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        this.excludeMethods.count(callee);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:30:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0331 -> B:28:0x0181). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object postCallAtCallSite$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis r10, cn.sast.dataflow.interprocedural.analysis.AIContext r11, soot.Unit r12, soot.Unit r13, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<soot.SootMethod, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>, cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue>> r14, kotlin.coroutines.Continuation<? super cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<soot.SootMethod, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>, cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue>>> r15) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 906
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis.postCallAtCallSite$suspendImpl(cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AIContext, soot.Unit, soot.Unit, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis$InvokeResult, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object postCallAtCallSite$lambda$20$lambda$19() {
        return "IR intercept exception";
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @Nullable
    public IFact<IValue> wideningFunction(@NotNull AIContext context, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        Intrinsics.checkNotNullParameter(iFact, "in");
        IFact.Builder out = iFact.builder();
        Set oldSlots = iFact.mo173getSlots();
        HeapValuesEnv env = getHf().env(node);
        for (Object local : oldSlots) {
            IHeapValues target = iFact.getTargets(local);
            IValue iValue = (IValue) CollectionsKt.firstOrNull(target.getValues());
            Type anyType = iValue != null ? iValue.getType() : null;
            if (anyType instanceof PrimType) {
                CompanionV<IValue> summary = getHf().push(env, (HeapValuesEnv) getHf().newSummaryVal(env, anyType, WideningPrimitive.INSTANCE)).markOfWideningSummary().pop();
                IHeapValues.Builder builder = out.getTargets(local).builder();
                builder.add(summary);
                IFact.Builder.DefaultImpls.assignNewExpr$default(out, env, local, builder.build(), false, 8, null);
            }
        }
        return out.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public boolean isAnalyzable(@NotNull SootMethod callee, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(iFact, "in1");
        if (this.callBackManager.getUnitGraphOverride(callee) != null) {
            return true;
        }
        if (!super.isAnalyzable(callee, (IFact) iFact) || ACheckCallAnalysisKt.getExcludeSubSignature().contains(callee.getSubSignature())) {
            return false;
        }
        int hit = this.excludeMethods.get(callee);
        if (hit > 0 && (!callee.getDeclaringClass().isApplicationClass() || hit > 2)) {
            return false;
        }
        DirectedGraph cfg = getCfg(callee, true);
        int dataFlowMethodUnitsSizeLimit = ExtSettings.INSTANCE.getDataFlowMethodUnitsSizeLimit();
        if (dataFlowMethodUnitsSizeLimit > 0 && cfg.size() > dataFlowMethodUnitsSizeLimit) {
            return false;
        }
        return true;
    }

    /* compiled from: ACheckCallAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: ACheckCallAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final kotlin.Unit logger$lambda$21() {
        return kotlin.Unit.INSTANCE;
    }
}
