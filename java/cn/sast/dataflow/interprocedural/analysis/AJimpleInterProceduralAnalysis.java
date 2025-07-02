package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.api.util.OthersKt;
import cn.sast.common.CustomRepeatingTimer;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.ForwardInterProceduralAnalysis;
import cn.sast.idfa.analysis.InterproceduralCFG;
import cn.sast.idfa.analysis.ProcessInfoView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ImmutableSet;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.G;
import soot.Local;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.VoidType;
import soot.jimple.BinopExpr;
import soot.jimple.CastExpr;
import soot.jimple.Constant;
import soot.jimple.DefinitionStmt;
import soot.jimple.Expr;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InstanceOfExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.StaticFieldRef;
import soot.jimple.Stmt;
import soot.jimple.UnopExpr;
import soot.jimple.infoflow.cfg.FlowDroidEssentialMethodTag;
import soot.jimple.internal.JEqExpr;
import soot.jimple.internal.JimpleLocal;
import soot.tagkit.Tag;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: AJimpleInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0098\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018�� X*\u0004\b��\u0010\u0001* \b\u0001\u0010\u0002*\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00060\u00032,\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\b\u0012\u0004\u0012\u0002H\u00020\u0007:\u0001XB\u001d\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u001f\u001a\u00020\fH\u0016J+\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u0006H&¢\u0006\u0002\u0010%J4\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040'2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*2\u0006\u0010#\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u0006H&J(\u0010+\u001a\u0004\u0018\u00010\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0002J9\u0010,\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0016¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J9\u00103\u001a\u00028\u00012\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u0005052\u0006\u00106\u001a\u00020\u00042\f\u00107\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u00108\u001a\u00020\u0014H\u0016¢\u0006\u0002\u00109JB\u0010:\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u00101\u001a\u000202H\u0096@¢\u0006\u0002\u0010;J \u0010<\u001a\u00020\u00142\u0006\u0010=\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0004H\u0016JA\u0010?\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010>\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0016¢\u0006\u0002\u0010@Jq\u0010A\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010\b0B2\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010>\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u00062\f\u0010C\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u00108\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010DJq\u0010E\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010\b0B2\u0006\u0010\"\u001a\u00028\u00012\u0006\u0010>\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00028��0\u00062\f\u0010C\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u00108\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010DJy\u0010F\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010\b0B2\u0006\u0010\"\u001a\u00028\u00012\f\u0010C\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010>\u001a\u00020\u00042\f\u0010G\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010H\u001a\u00028\u00012\u0006\u0010#\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010IJ\u001e\u0010J\u001a\b\u0012\u0004\u0012\u00020\u0005052\u0006\u00106\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0014H\u0016J\u001e\u00108\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0016J$\u0010L\u001a\u0002002\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00040N2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00040NH\u0016J.\u0010P\u001a\b\u0012\u0004\u0012\u00028��0\b*\b\u0012\u0004\u0012\u00028��0Q2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020WR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\n¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006Y"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis;", "V", "CTX", "Lcn/sast/idfa/analysis/Context;", "Lsoot/SootMethod;", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "analyzeLibraryClasses", "", "getAnalyzeLibraryClasses", "()Z", "setAnalyzeLibraryClasses", "(Z)V", "needAnalyze", "Lkotlin/Function1;", "getNeedAnalyze", "()Lkotlin/jvm/functions/Function1;", "setNeedAnalyze", "(Lkotlin/jvm/functions/Function1;)V", "programRepresentation", "newExprEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "context", "node", "inValue", "(Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;)Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "resolveTargets", "", "callerMethod", "ie", "Lsoot/jimple/InvokeExpr;", "resolveClinit", "normalFlowUnAccessibleFunction", "succ", "(Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;)Lcn/sast/dataflow/interprocedural/analysis/IFact;", "shutDownAnalyze", "", "isNegativeBranch", "Ljava/util/concurrent/atomic/AtomicBoolean;", "newContext", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "method", "entryValue", "isAnalyzable", "(Lsoot/toolkits/graph/DirectedGraph;Lsoot/SootMethod;Lcn/sast/dataflow/interprocedural/analysis/IFact;Z)Lcn/sast/idfa/analysis/Context;", "normalFlowFunction", "(Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Ljava/util/concurrent/atomic/AtomicBoolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isDummyComponentInvoke", "container", "callee", "callEntryFlowFunction", "(Lcn/sast/idfa/analysis/Context;Lsoot/SootMethod;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;)Lcn/sast/dataflow/interprocedural/analysis/IFact;", "recursiveCallFlowFunction", "Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "siteValue", "(Lcn/sast/idfa/analysis/Context;Lsoot/SootMethod;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Lcn/sast/dataflow/interprocedural/analysis/IFact;Z)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "failedInvokeResult", "callExitFlowFunction", "callEdgeValue", "calleeCtx", "(Lcn/sast/idfa/analysis/Context;Lcn/sast/dataflow/interprocedural/analysis/IFact;Lsoot/SootMethod;Lcn/sast/dataflow/interprocedural/analysis/IFact;Lcn/sast/idfa/analysis/Context;Lsoot/Unit;Lsoot/Unit;Z)Lcn/sast/idfa/analysis/ForwardInterProceduralAnalysis$InvokeResult;", "getCfg", "in1", "doAnalysis", "entries", "", "methodsMustAnalyze", "resolveExpr", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "expr", "Lsoot/jimple/Expr;", "resType", "Lsoot/Type;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nAJimpleInterProceduralAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AJimpleInterProceduralAnalysis.kt\ncn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis\n+ 2 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 3 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,859:1\n333#2,6:860\n333#2,6:870\n44#3:866\n44#3:867\n44#3:868\n44#3:869\n44#3:876\n1755#4,3:877\n*S KotlinDebug\n*F\n+ 1 AJimpleInterProceduralAnalysis.kt\ncn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis\n*L\n267#1:860,6\n586#1:870,6\n432#1:866\n442#1:867\n492#1:868\n499#1:869\n608#1:876\n768#1:877,3\n*E\n"})
/* loaded from: AJimpleInterProceduralAnalysis.class */
public abstract class AJimpleInterProceduralAnalysis<V, CTX extends Context<SootMethod, Unit, IFact<V>>> extends ForwardInterProceduralAnalysis<SootMethod, Unit, IFact<V>, IHeapValues<V>, CTX> {

    @NotNull
    private final AbstractHeapFactory<V> hf;

    @NotNull
    private final InterproceduralCFG icfg;
    private boolean analyzeLibraryClasses;

    @Nullable
    private Function1<? super SootMethod, Boolean> needAnalyze;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(AJimpleInterProceduralAnalysis::logger$lambda$13);

    /* compiled from: AJimpleInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "AJimpleInterProceduralAnalysis.kt", l = {275, 391, 392, 474, 475}, i = {PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9"}, n = {"$this", "env", "stmt", "out", "invokeFold", "$this", "env", "stmt", "out", "invokeFold", "callee2", "$this", "context", "node", "env", "out", "lhsOp", "rhsOp", "invokeFold", "$this", "context", "node", "env", "out", "lhsOp", "rhsOp", "invokeFold", "callee2"}, m = "normalFlowFunction$suspendImpl", c = "cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis")
    /* renamed from: cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis$normalFlowFunction$1, reason: invalid class name */
    /* loaded from: AJimpleInterProceduralAnalysis$normalFlowFunction$1.class */
    static final class AnonymousClass1<V, CTX extends Context<SootMethod, Unit, IFact<V>>> extends ContinuationImpl {
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
        final /* synthetic */ AJimpleInterProceduralAnalysis<V, CTX> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AJimpleInterProceduralAnalysis<V, CTX> aJimpleInterProceduralAnalysis, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = aJimpleInterProceduralAnalysis;
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AJimpleInterProceduralAnalysis.normalFlowFunction$suspendImpl(this.this$0, null, null, null, null, null, (Continuation) this);
        }
    }

    @NotNull
    public abstract AnyNewExprEnv newExprEnv(@NotNull CTX ctx, @NotNull Unit unit, @NotNull IFact<V> iFact);

    @NotNull
    public abstract Set<SootMethod> resolveTargets(@NotNull SootMethod sootMethod, @NotNull InvokeExpr invokeExpr, @NotNull Unit unit, @NotNull IFact<V> iFact);

    @Nullable
    public Object normalFlowFunction(@NotNull CTX ctx, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<V> iFact, @NotNull AtomicBoolean isNegativeBranch, @NotNull Continuation<? super IFact<V>> continuation) {
        return normalFlowFunction$suspendImpl(this, ctx, node, succ, iFact, isNegativeBranch, continuation);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object normalFlowFunction(Context context, Unit unit, Unit unit2, Object inValue, AtomicBoolean isNegativeBranch, Continuation $completion) {
        return normalFlowFunction((AJimpleInterProceduralAnalysis<V, CTX>) context, unit, unit2, (IFact) inValue, isNegativeBranch, $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object callEntryFlowFunction(Context context, SootMethod sootMethod, Unit unit, Unit unit2, Object inValue) {
        return callEntryFlowFunction((AJimpleInterProceduralAnalysis<V, CTX>) context, sootMethod, unit, unit2, (IFact) inValue);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ ForwardInterProceduralAnalysis.InvokeResult recursiveCallFlowFunction(Context context, SootMethod sootMethod, Unit unit, Unit unit2, Object inValue, Object siteValue, boolean isAnalyzable) {
        return recursiveCallFlowFunction((AJimpleInterProceduralAnalysis<V, CTX>) context, sootMethod, unit, unit2, (IFact) inValue, (IFact) siteValue, isAnalyzable);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ ForwardInterProceduralAnalysis.InvokeResult failedInvokeResult(Context context, SootMethod sootMethod, Unit unit, Unit unit2, Object inValue, Object siteValue, boolean isAnalyzable) {
        return failedInvokeResult((AJimpleInterProceduralAnalysis<V, CTX>) context, sootMethod, unit, unit2, (IFact) inValue, (IFact) siteValue, isAnalyzable);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ ForwardInterProceduralAnalysis.InvokeResult callExitFlowFunction(Context context, Object siteValue, SootMethod sootMethod, Object callEdgeValue, Context calleeCtx, Unit unit, Unit unit2, boolean isAnalyzable) {
        return callExitFlowFunction((IFact<V>) context, (IFact) siteValue, sootMethod, (IFact) callEdgeValue, (IFact<V>) calleeCtx, unit, unit2, isAnalyzable);
    }

    @NotNull
    public final AbstractHeapFactory<V> getHf() {
        return this.hf;
    }

    @NotNull
    public final InterproceduralCFG getIcfg() {
        return this.icfg;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AJimpleInterProceduralAnalysis(@NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull InterproceduralCFG icfg) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        this.hf = abstractHeapFactory;
        this.icfg = icfg;
        this.analyzeLibraryClasses = true;
    }

    public final boolean getAnalyzeLibraryClasses() {
        return this.analyzeLibraryClasses;
    }

    public final void setAnalyzeLibraryClasses(boolean z) {
        this.analyzeLibraryClasses = z;
    }

    @Nullable
    public final Function1<SootMethod, Boolean> getNeedAnalyze() {
        return this.needAnalyze;
    }

    public final void setNeedAnalyze(@Nullable Function1<? super SootMethod, Boolean> function1) {
        this.needAnalyze = function1;
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public InterproceduralCFG programRepresentation() {
        return this.icfg;
    }

    private final SootMethod resolveClinit(SootMethod callerMethod, Unit node, IFact<V> iFact) {
        if (iFact.isBottom()) {
            return null;
        }
        Intrinsics.checkNotNull(node, "null cannot be cast to non-null type soot.jimple.Stmt");
        DefinitionStmt definitionStmt = (Stmt) node;
        if (!(definitionStmt instanceof DefinitionStmt)) {
            return null;
        }
        SootMethod ret = null;
        StaticFieldRef leftOp = definitionStmt.getLeftOp();
        Intrinsics.checkNotNullExpressionValue(leftOp, "getLeftOp(...)");
        StaticFieldRef rightOp = definitionStmt.getRightOp();
        Intrinsics.checkNotNullExpressionValue(rightOp, "getRightOp(...)");
        StaticFieldRef staticReference = null;
        if (leftOp instanceof StaticFieldRef) {
            staticReference = leftOp;
        } else if (rightOp instanceof StaticFieldRef) {
            staticReference = rightOp;
        }
        if (staticReference != null) {
            SootClass declaringClass = staticReference.getField().getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            if (declaringClass.isLibraryClass()) {
                Iterator it = declaringClass.getFields().iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    SootField field = (SootField) it.next();
                    if (!field.isStatic() || (field.getType() instanceof RefLikeType)) {
                    }
                }
            }
            if (declaringClass.declaresMethodByName("<clinit>")) {
                SootMethod clinit = declaringClass.getMethodByName("<clinit>");
                Intrinsics.checkNotNullExpressionValue(clinit, "getMethodByName(...)");
                ImmutableSet clinitCalled = iFact.mo174getCalledMethods();
                if (!clinitCalled.contains(clinit)) {
                    ret = clinit;
                }
            }
        }
        if (Intrinsics.areEqual(ret, callerMethod)) {
            ret = null;
        }
        return ret;
    }

    @NotNull
    public IFact<V> normalFlowUnAccessibleFunction(@NotNull CTX ctx, @NotNull Unit unit, @NotNull Unit unit2, @NotNull IFact<V> iFact) {
        Intrinsics.checkNotNullParameter(ctx, "context");
        Intrinsics.checkNotNullParameter(unit, "node");
        Intrinsics.checkNotNullParameter(unit2, "succ");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        if ((unit instanceof ReturnStmt) || (unit instanceof ReturnVoidStmt)) {
            IFact.Builder<V> builder = iFact.builder();
            SootMethod sootMethod = (SootMethod) ctx.getMethod();
            if (!sootMethod.isStatic()) {
                builder.summarizeTargetFields(-1);
            }
            int parameterCount = sootMethod.getParameterCount();
            for (int i = 0; i < parameterCount; i++) {
                builder.summarizeTargetFields(Integer.valueOf(i));
            }
            if (!(sootMethod.getReturnType() instanceof VoidType)) {
                HeapValuesEnv heapValuesEnvEnv = this.hf.env(unit);
                AbstractHeapFactory<V> abstractHeapFactory = this.hf;
                AbstractHeapFactory<V> abstractHeapFactory2 = this.hf;
                Type returnType = sootMethod.getReturnType();
                Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
                IHeapValues<V> iHeapValuesPopHV = abstractHeapFactory.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory2.newSummaryVal(heapValuesEnvEnv, returnType, "return")).markSummaryReturnValueInCalleeSite().popHV();
                if (sootMethod.getReturnType() instanceof RefType) {
                    iHeapValuesPopHV = iHeapValuesPopHV.plus(this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) this.hf.getNullConst()).markSummaryReturnValueInCalleeSite().popHV());
                }
                IFact.Builder.DefaultImpls.assignNewExpr$default(builder, heapValuesEnvEnv, this.hf.getVg().getRETURN_LOCAL(), iHeapValuesPopHV, false, 8, null);
            }
            return builder.build();
        }
        return iFact;
    }

    public void shutDownAnalyze(@NotNull AtomicBoolean isNegativeBranch) {
        Intrinsics.checkNotNullParameter(isNegativeBranch, "isNegativeBranch");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @NotNull
    public CTX newContext(@NotNull DirectedGraph<Unit> directedGraph, @NotNull SootMethod sootMethod, @NotNull IFact<V> iFact, boolean z) {
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        Intrinsics.checkNotNullParameter(sootMethod, "method");
        Intrinsics.checkNotNullParameter(iFact, "entryValue");
        CTX ctx = (CTX) super.newContext((DirectedGraph) directedGraph, (DirectedGraph<Unit>) sootMethod, (SootMethod) iFact, z);
        if (logger.isTraceEnabled()) {
            logger.trace(() -> {
                return newContext$lambda$0(r1);
            });
            logger.trace(() -> {
                return newContext$lambda$1(r1);
            });
        }
        if (ctx.getPathSensitiveEnable() && OthersKt.getSkipPathSensitive(sootMethod)) {
            ctx.setPathSensitiveEnable(false);
        }
        return ctx;
    }

    private static final Object newContext$lambda$0(SootMethod $method) {
        return "new context for " + $method;
    }

    private static final Object newContext$lambda$1(SootMethod $method) {
        return $method.getActiveBody();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x080d  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0bea  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0d01  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0d13  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0d48  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0d67  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0dc1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:229:0x0d38 -> B:211:0x0be0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ <V, CTX extends cn.sast.idfa.analysis.Context<soot.SootMethod, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact<V>>> java.lang.Object normalFlowFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis<V, CTX> r10, CTX r11, soot.Unit r12, soot.Unit r13, cn.sast.dataflow.interprocedural.analysis.IFact<V> r14, java.util.concurrent.atomic.AtomicBoolean r15, kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<V>> r16) {
        /*
            Method dump skipped, instructions count: 4092
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis.normalFlowFunction$suspendImpl(cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.Context, soot.Unit, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact, java.util.concurrent.atomic.AtomicBoolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final <V, CTX extends Context<SootMethod, Unit, IFact<V>>> Boolean normalFlowFunction$lambda$3$check(AJimpleInterProceduralAnalysis<V, CTX> aJimpleInterProceduralAnalysis, HeapValuesEnv env, Ref.ObjectRef<IFact.Builder<V>> objectRef, IHeapValues<V> iHeapValues, Value key, IHeapValues<V> iHeapValues2) {
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        AbstractHeapFactory<V> abstractHeapFactory = ((AJimpleInterProceduralAnalysis) aJimpleInterProceduralAnalysis).hf;
        IFact.Builder<V> builder = (IFact.Builder) objectRef.element;
        BinopExpr jEqExpr = new JEqExpr(new JimpleLocal("a", key.getType()), new JimpleLocal("b", key.getType()));
        Intrinsics.checkNotNull(typeSoot_BooleanType);
        IOpCalculator r = abstractHeapFactory.resolveBinop(env, builder, iHeapValues, iHeapValues2, jEqExpr, typeSoot_BooleanType);
        IHeapValues eq = r.getRes().build();
        if (r.isFullySimplified() && eq.isSingle()) {
            V value = eq.getSingle().getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.IValue");
            return FactValuesKt.getBooleanValue((IValue) value, true);
        }
        return null;
    }

    public boolean isDummyComponentInvoke(@NotNull SootMethod container, @NotNull Unit node, @NotNull SootMethod callee) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(callee, "callee");
        return false;
    }

    @NotNull
    public IFact<V> callEntryFlowFunction(@NotNull CTX ctx, @NotNull SootMethod sootMethod, @NotNull Unit unit, @NotNull Unit unit2, @NotNull IFact<V> iFact) throws NotImplementedError {
        Type parameterType;
        IHeapValues<V> iHeapValuesPopHV;
        Intrinsics.checkNotNullParameter(ctx, "context");
        Intrinsics.checkNotNullParameter(sootMethod, "callee");
        Intrinsics.checkNotNullParameter(unit, "node");
        Intrinsics.checkNotNullParameter(unit2, "succ");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        if (iFact.isBottom()) {
            return iFact;
        }
        Stmt stmt = (Stmt) unit;
        boolean zIsDummyComponentInvoke = isDummyComponentInvoke((SootMethod) ctx.getMethod(), unit, sootMethod);
        SootMethod sootMethod2 = (SootMethod) ctx.getMethod();
        IFact.Builder<V> builder = iFact.builder();
        if (!sootMethod2.isStatic()) {
            builder.kill(-1);
        }
        int parameterCount = sootMethod2.getParameterCount();
        for (int i = 0; i < parameterCount; i++) {
            builder.kill(Integer.valueOf(i));
        }
        HeapValuesEnv heapValuesEnvEnv = this.hf.env(unit);
        if (stmt.containsInvokeExpr()) {
            InstanceInvokeExpr invokeExpr = stmt.getInvokeExpr();
            if ((invokeExpr instanceof InstanceInvokeExpr) && sootMethod2.hasActiveBody()) {
                Local base = invokeExpr.getBase();
                Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                builder.assignLocal(heapValuesEnvEnv, -1, base);
            }
            int argCount = invokeExpr.getArgCount();
            for (int i2 = 0; i2 < argCount; i2++) {
                Local arg = invokeExpr.getArg(i2);
                if (arg instanceof Local) {
                    if (zIsDummyComponentInvoke && (arg.getType() instanceof ArrayType)) {
                        AbstractHeapFactory<V> abstractHeapFactory = this.hf;
                        Type type = arg.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                        IFact.Builder.DefaultImpls.assignNewExpr$default(builder, heapValuesEnvEnv, Integer.valueOf(i2), this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory.newSummaryVal(heapValuesEnvEnv, type, "dummyMainMethodArgArrayElement")).markOfEntryMethodParam(sootMethod).popHV(), false, 8, null);
                    } else {
                        builder.assignLocal(heapValuesEnvEnv, Integer.valueOf(i2), arg);
                    }
                } else {
                    if (!(arg instanceof Constant)) {
                        throw new RuntimeException(arg.toString());
                    }
                    Type type2 = ((Constant) arg).getType();
                    if (!(type2 instanceof RefLikeType)) {
                        parameterType = invokeExpr.getMethodRef().getParameterType(i2);
                        Intrinsics.checkNotNullExpressionValue(parameterType, "getParameterType(...)");
                    } else {
                        parameterType = type2;
                    }
                    Type type3 = parameterType;
                    if (!zIsDummyComponentInvoke) {
                        iHeapValuesPopHV = JOperatorV.DefaultImpls.markOfConstant$default(this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) this.hf.newConstVal2((Constant) arg, type3)), (Constant) arg, null, 2, null).popHV();
                    } else {
                        iHeapValuesPopHV = this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) this.hf.newSummaryVal(heapValuesEnvEnv, type3, "dummyMainMethodArg")).markOfEntryMethodParam(sootMethod).popHV();
                    }
                    IFact.Builder.DefaultImpls.assignNewExpr$default(builder, heapValuesEnvEnv, Integer.valueOf(i2), iHeapValuesPopHV, false, 8, null);
                }
            }
        } else {
            if (!sootMethod.isStaticInitializer()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            Iterator it = sootMethod.getDeclaringClass().getFields().iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                SootField sootField = (SootField) it.next();
                IVGlobal vg = this.hf.getVg();
                Type type4 = sootField.getType();
                Intrinsics.checkNotNullExpressionValue(type4, "getType(...)");
                Pair<Constant, Type> pairDefaultValue = vg.defaultValue(type4);
                Constant constant = (Constant) pairDefaultValue.component1();
                Type type5 = (Type) pairDefaultValue.component2();
                if (sootField.isStatic()) {
                    String global_local = this.hf.getVg().getGLOBAL_LOCAL();
                    FieldUtil fieldUtil = FieldUtil.INSTANCE;
                    Intrinsics.checkNotNull(sootField);
                    builder.setFieldNew(heapValuesEnvEnv, global_local, new JSootFieldType(sootField), JOperatorV.DefaultImpls.markOfConstant$default(this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) this.hf.newConstVal2(constant, type5)), constant, null, 2, null).popHV());
                }
            }
        }
        if (sootMethod2.hasActiveBody()) {
            Iterator it2 = sootMethod2.getActiveBody().getLocals().iterator();
            Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
            while (it2.hasNext()) {
                Local local = (Local) it2.next();
                Intrinsics.checkNotNull(local);
                builder.kill(local);
            }
        }
        builder.kill(this.hf.getVg().getRETURN_LOCAL());
        builder.callEntryFlowFunction(ctx, sootMethod, unit, unit2);
        if (this.hf.getVg().getStaticFieldTrackingMode() == StaticFieldTrackingMode.ContextFlowInsensitive || sootMethod.isJavaLibraryMethod() || sootMethod.getDeclaringClass().isLibraryClass()) {
            builder.kill(this.hf.getVg().getGLOBAL_LOCAL());
        }
        builder.gc();
        if (this.hf.getVg().getStaticFieldTrackingMode() == StaticFieldTrackingMode.ContextFlowInsensitive) {
            IFact.Builder.DefaultImpls.assignNewExpr$default(builder, heapValuesEnvEnv, this.hf.getVg().getGLOBAL_LOCAL(), this.hf.push(heapValuesEnvEnv, (HeapValuesEnv) this.hf.getVg().getGLOBAL_SITE()).popHV(), false, 8, null);
        }
        return builder.build();
    }

    @NotNull
    public ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<V>, IHeapValues<V>> recursiveCallFlowFunction(@NotNull CTX ctx, @NotNull SootMethod sootMethod, @NotNull Unit unit, @NotNull Unit unit2, @NotNull IFact<V> iFact, @NotNull IFact<V> iFact2, boolean z) {
        IHeapValues<V> iHeapValuesPopHV;
        Intrinsics.checkNotNullParameter(ctx, "context");
        Intrinsics.checkNotNullParameter(sootMethod, "callee");
        Intrinsics.checkNotNullParameter(unit, "node");
        Intrinsics.checkNotNullParameter(unit2, "succ");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        Intrinsics.checkNotNullParameter(iFact2, "siteValue");
        Type returnType = sootMethod.getReturnType();
        boolean z2 = !Intrinsics.areEqual(returnType, G.v().soot_VoidType());
        IFact.Builder<V> builder = iFact2.builder();
        if (z2) {
            HeapValuesEnv heapValuesEnvEnv = this.hf.env(unit);
            AbstractHeapFactory<V> abstractHeapFactory = this.hf;
            AbstractHeapFactory<V> abstractHeapFactory2 = this.hf;
            Intrinsics.checkNotNull(returnType);
            iHeapValuesPopHV = abstractHeapFactory.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory2.newSummaryVal(heapValuesEnvEnv, returnType, "recursiveReturn")).markSummaryReturnValueInCalleeSite().popHV();
        } else {
            iHeapValuesPopHV = null;
        }
        builder.addCalledMethod(sootMethod);
        return new ForwardInterProceduralAnalysis.InvokeResult<>(sootMethod, builder.build(), iHeapValuesPopHV);
    }

    @NotNull
    public ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<V>, IHeapValues<V>> failedInvokeResult(@NotNull CTX ctx, @NotNull SootMethod sootMethod, @NotNull Unit unit, @NotNull Unit unit2, @NotNull IFact<V> iFact, @NotNull IFact<V> iFact2, boolean z) {
        IHeapValues<V> iHeapValuesPopHV;
        Intrinsics.checkNotNullParameter(ctx, "context");
        Intrinsics.checkNotNullParameter(sootMethod, "callee");
        Intrinsics.checkNotNullParameter(unit, "node");
        Intrinsics.checkNotNullParameter(unit2, "succ");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        Intrinsics.checkNotNullParameter(iFact2, "siteValue");
        Type returnType = sootMethod.getReturnType();
        boolean z2 = !Intrinsics.areEqual(returnType, G.v().soot_VoidType());
        IFact.Builder<V> builder = iFact2.builder();
        if (z2) {
            HeapValuesEnv heapValuesEnvEnv = this.hf.env(unit);
            AbstractHeapFactory<V> abstractHeapFactory = this.hf;
            AbstractHeapFactory<V> abstractHeapFactory2 = this.hf;
            Intrinsics.checkNotNull(returnType);
            iHeapValuesPopHV = abstractHeapFactory.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory2.newSummaryVal(heapValuesEnvEnv, returnType, "failedReturn")).markSummaryReturnValueInCalleeSite().popHV();
        } else {
            iHeapValuesPopHV = null;
        }
        builder.addCalledMethod(sootMethod);
        return new ForwardInterProceduralAnalysis.InvokeResult<>(sootMethod, builder.build(), iHeapValuesPopHV);
    }

    @NotNull
    public ForwardInterProceduralAnalysis.InvokeResult<SootMethod, IFact<V>, IHeapValues<V>> callExitFlowFunction(@NotNull CTX ctx, @NotNull IFact<V> iFact, @NotNull SootMethod sootMethod, @NotNull IFact<V> iFact2, @NotNull CTX ctx2, @NotNull Unit unit, @NotNull Unit unit2, boolean z) {
        Intrinsics.checkNotNullParameter(ctx, "context");
        Intrinsics.checkNotNullParameter(iFact, "siteValue");
        Intrinsics.checkNotNullParameter(sootMethod, "callee");
        Intrinsics.checkNotNullParameter(iFact2, "callEdgeValue");
        Intrinsics.checkNotNullParameter(ctx2, "calleeCtx");
        Intrinsics.checkNotNullParameter(unit, "node");
        Intrinsics.checkNotNullParameter(unit2, "succ");
        IFact.Builder<V> builder = iFact.builder();
        Object exitValue = ctx2.getExitValue();
        Intrinsics.checkNotNull(exitValue);
        IFact iFact3 = (IFact) exitValue;
        Type returnType = sootMethod.getReturnType();
        boolean z2 = !Intrinsics.areEqual(returnType, G.v().soot_VoidType());
        HeapValuesEnv heapValuesEnvEnv = this.hf.env(unit);
        IHeapValues<V> iHeapValuesUpdateIntraEdge = builder.updateIntraEdge(heapValuesEnvEnv, ctx, ctx2, iFact2, z2);
        if (z2 && (iHeapValuesUpdateIntraEdge == null || iHeapValuesUpdateIntraEdge.isEmpty())) {
            logger.trace(() -> {
                return callExitFlowFunction$lambda$6(r1, r2);
            });
            AbstractHeapFactory<V> abstractHeapFactory = this.hf;
            AbstractHeapFactory<V> abstractHeapFactory2 = this.hf;
            Intrinsics.checkNotNull(returnType);
            iHeapValuesUpdateIntraEdge = abstractHeapFactory.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory2.newSummaryVal(heapValuesEnvEnv, returnType, "ret_" + sootMethod.getDeclaringClass().getShortName() + "::" + sootMethod.getName())).markSummaryReturnValueInCalleeSite().popHV();
        }
        builder.addCalledMethod(sootMethod);
        return new ForwardInterProceduralAnalysis.InvokeResult<>(sootMethod, builder.build(), iHeapValuesUpdateIntraEdge);
    }

    private static final Object callExitFlowFunction$lambda$6(HeapValuesEnv $env, IFact $calleeExitAbs) {
        return "returnValue is empty. at " + $env + " exitValue: " + $calleeExitAbs;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @NotNull
    public DirectedGraph<Unit> getCfg(@NotNull SootMethod method, boolean isAnalyzable) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method.hasActiveBody()) {
            DirectedGraph cfg = programRepresentation().getControlFlowGraph(method);
            List heads = cfg.getHeads();
            Intrinsics.checkNotNullExpressionValue(heads, "getHeads(...)");
            if (!heads.isEmpty()) {
                return cfg;
            }
            return programRepresentation().getSummaryControlFlowGraph(method);
        }
        return programRepresentation().getSummaryControlFlowGraph(method);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public boolean isAnalyzable(@NotNull SootMethod callee, @NotNull IFact<V> iFact) {
        boolean z;
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(iFact, "in1");
        if (!this.icfg.isAnalyzable(callee)) {
            return false;
        }
        Function1<? super SootMethod, Boolean> function1 = this.needAnalyze;
        boolean z2 = function1 != null && ((Boolean) function1.invoke(callee)).booleanValue();
        if (z2) {
            return true;
        }
        if (this.analyzeLibraryClasses) {
            int dataFlowInterProceduralCalleeDepChainMaxNumForLibClasses = ExtSettings.INSTANCE.getCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow();
            if (dataFlowInterProceduralCalleeDepChainMaxNumForLibClasses >= 0) {
                List libraryMethods = new ArrayList();
                if (!callee.getDeclaringClass().isApplicationClass()) {
                    libraryMethods.add(callee);
                }
                for (CallStackContext cur = iFact.getCallStack(); cur != null; cur = cur.getCaller()) {
                    if (!cur.getMethod().getDeclaringClass().isApplicationClass()) {
                        libraryMethods.add(cur.getMethod());
                    }
                    if (libraryMethods.size() > dataFlowInterProceduralCalleeDepChainMaxNumForLibClasses) {
                        return false;
                    }
                }
            }
        } else if (!callee.getDeclaringClass().isApplicationClass()) {
            return false;
        }
        Iterable tags = callee.getTags();
        Intrinsics.checkNotNullExpressionValue(tags, "getTags(...)");
        Iterable $this$any$iv = tags;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Tag it2 = (Tag) element$iv;
                    if (it2 instanceof FlowDroidEssentialMethodTag) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z && Scene.v().isExcluded(callee.getDeclaringClass())) {
            return false;
        }
        return true;
    }

    public void doAnalysis(@NotNull Collection<? extends SootMethod> collection, @NotNull Collection<? extends SootMethod> collection2) {
        Intrinsics.checkNotNullParameter(collection, "entries");
        Intrinsics.checkNotNullParameter(collection2, "methodsMustAnalyze");
        Set reachableMethodsTotal = new LinkedHashSet();
        Set methodsMustAnalyzeSet = SetsKt.plus(CollectionsKt.toSet(collection2), collection);
        logger.info(AJimpleInterProceduralAnalysis::doAnalysis$lambda$8);
        Ref.ObjectRef works = new Ref.ObjectRef();
        works.element = CollectionsKt.toSet(collection);
        Set surplusOld = methodsMustAnalyzeSet;
        Ref.IntRef count = new Ref.IntRef();
        while (true) {
            if (!(!surplusOld.isEmpty())) {
                if (!(!((Collection) works.element).isEmpty())) {
                    return;
                }
            }
            if (((Set) works.element).isEmpty()) {
                works.element = surplusOld;
            }
            logger.info(() -> {
                return doAnalysis$lambda$9(r1, r2, r3);
            });
            CustomRepeatingTimer $this$doAnalysis_u24lambda_u2411 = new CustomRepeatingTimer(60000L, AJimpleInterProceduralAnalysis::doAnalysis$lambda$10);
            $this$doAnalysis_u24lambda_u2411.setRepeats(true);
            $this$doAnalysis_u24lambda_u2411.start();
            try {
                doAnalysis((Collection) works.element);
                $this$doAnalysis_u24lambda_u2411.stop();
                count.element++;
                CollectionsKt.addAll(reachableMethodsTotal, getReachableMethods());
                Set newSurplus = SetsKt.minus(methodsMustAnalyzeSet, reachableMethodsTotal);
                if (!Intrinsics.areEqual(surplusOld, newSurplus)) {
                    surplusOld = newSurplus;
                    works.element = newSurplus;
                } else {
                    return;
                }
            } catch (Throwable th) {
                $this$doAnalysis_u24lambda_u2411.stop();
                throw th;
            }
        }
    }

    private static final Object doAnalysis$lambda$8() {
        return "Before Analyze: Process information: " + ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText();
    }

    private static final Object doAnalysis$lambda$9(Ref.IntRef $count, Ref.ObjectRef $works, Collection $methodsMustAnalyze) {
        return "iteration " + $count.element + " : [" + ((Set) $works.element).size() + "/" + $methodsMustAnalyze.size() + "]";
    }

    private static final kotlin.Unit doAnalysis$lambda$10() {
        System.gc();
        return kotlin.Unit.INSTANCE;
    }

    @NotNull
    public final IHeapValues<V> resolveExpr(@NotNull IFact.Builder<V> builder, @NotNull HeapValuesEnv env, @NotNull Expr expr, @NotNull Type resType) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(resType, "resType");
        if (expr instanceof CastExpr) {
            Type toType = ((CastExpr) expr).getCastType();
            Value op = ((CastExpr) expr).getOp();
            Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
            Type type = ((CastExpr) expr).getOp().getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            IHeapValues fromValues = builder.getOfSootValue(env, op, type);
            AbstractHeapFactory<V> hf = builder.getHf();
            Intrinsics.checkNotNull(toType);
            IOpCalculator<V> iOpCalculatorResolveCast = hf.resolveCast(env, builder, toType, fromValues);
            if (iOpCalculatorResolveCast != null) {
                IHeapValues.Builder<V> res = iOpCalculatorResolveCast.getRes();
                if (res != null) {
                    IHeapValues<V> iHeapValuesBuild = res.build();
                    if (iHeapValuesBuild != null) {
                        return iHeapValuesBuild;
                    }
                }
            }
            return fromValues;
        }
        if (expr instanceof InstanceOfExpr) {
            Type checkType = ((InstanceOfExpr) expr).getCheckType();
            Value op2 = ((InstanceOfExpr) expr).getOp();
            Intrinsics.checkNotNullExpressionValue(op2, "getOp(...)");
            Type type2 = ((InstanceOfExpr) expr).getOp().getType();
            Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
            IHeapValues fromValues2 = builder.getOfSootValue(env, op2, type2);
            AbstractHeapFactory<V> hf2 = builder.getHf();
            Intrinsics.checkNotNull(checkType);
            return hf2.resolveInstanceOf(env, fromValues2, checkType).getRes().build();
        }
        if (expr instanceof UnopExpr) {
            Value op3 = ((UnopExpr) expr).getOp();
            Intrinsics.checkNotNullExpressionValue(op3, "getOp(...)");
            Type type3 = ((UnopExpr) expr).getOp().getType();
            Intrinsics.checkNotNullExpressionValue(type3, "getType(...)");
            IHeapValues opValues = builder.getOfSootValue(env, op3, type3);
            return builder.getHf().resolveUnop(env, builder, opValues, (UnopExpr) expr, resType).getRes().build();
        }
        if (expr instanceof BinopExpr) {
            Value op1 = ((BinopExpr) expr).getOp1();
            Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
            Type type4 = ((BinopExpr) expr).getOp1().getType();
            Intrinsics.checkNotNullExpressionValue(type4, "getType(...)");
            IHeapValues op1Values = builder.getOfSootValue(env, op1, type4);
            Value op22 = ((BinopExpr) expr).getOp2();
            Intrinsics.checkNotNullExpressionValue(op22, "getOp2(...)");
            Type type5 = ((BinopExpr) expr).getOp2().getType();
            Intrinsics.checkNotNullExpressionValue(type5, "getType(...)");
            IHeapValues op2Values = builder.getOfSootValue(env, op22, type5);
            return builder.getHf().resolveBinop(env, builder, op1Values, op2Values, (BinopExpr) expr, resType).getRes().build();
        }
        builder.getHf().getLogger().error(() -> {
            return resolveExpr$lambda$12(r1);
        });
        return builder.getHf().empty();
    }

    private static final Object resolveExpr$lambda$12(Expr $expr) {
        return "resolveExpr: not yet impl type of expr " + $expr;
    }

    /* compiled from: AJimpleInterProceduralAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AJimpleInterProceduralAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: AJimpleInterProceduralAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final kotlin.Unit logger$lambda$13() {
        return kotlin.Unit.INSTANCE;
    }
}
