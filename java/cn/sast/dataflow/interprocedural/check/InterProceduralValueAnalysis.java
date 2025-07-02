package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysisKt;
import cn.sast.dataflow.interprocedural.analysis.AbstractBOTTOM;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AbstractTOP;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.CallStackContext;
import cn.sast.dataflow.interprocedural.analysis.EntryParam;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphAbstract;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.FixPointStatus;
import cn.sast.idfa.analysis.InterproceduralCFG;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.Body;
import soot.G;
import soot.Local;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.jimple.StaticInvokeExpr;

/* compiled from: InterProceduralValueAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\f\b&\u0018�� 62\u00020\u0001:\u00016B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J*\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J*\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0016J \u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00152\u0010\u0010&\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000fJ\"\u0010*\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00152\u0010\u0010+\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000fH\u0016J8\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150-2\u0006\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u0002002\u0006\u0010$\u001a\u00020%2\u0010\u0010&\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000fH\u0016JD\u00101\u001a\u0002022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u00020%2\u0010\u00104\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000f2\u0010\u00105\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00100\u000fH\u0016R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u00067"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "bottom", "cn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis$bottom$1", "Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis$bottom$1;", "getTopState", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "callStackContext", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "boundaryValue", "entryPoint", "Lsoot/SootMethod;", "copy", "src", "meet", "op1", "op2", "shallowMeet", "merge", "local", "ret", "bottomValue", "newExprEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "context", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "node", "Lsoot/Unit;", "inValue", "isRecursive", "", "callee", "isAnalyzable", "in1", "resolveTargets", "", "callerMethod", "ie", "Lsoot/jimple/InvokeExpr;", "hasChange", "Lcn/sast/idfa/analysis/FixPointStatus;", "succ", "old", "new", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nInterProceduralValueAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InterProceduralValueAnalysis.kt\ncn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,315:1\n1#2:316\n1863#3,2:317\n*S KotlinDebug\n*F\n+ 1 InterProceduralValueAnalysis.kt\ncn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis\n*L\n258#1:317,2\n*E\n"})
/* loaded from: InterProceduralValueAnalysis.class */
public abstract class InterProceduralValueAnalysis extends ACheckCallAnalysis {

    @NotNull
    private final InterProceduralValueAnalysis$bottom$1 bottom;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static KLogger logger = KotlinLogging.INSTANCE.logger(InterProceduralValueAnalysis::logger$lambda$3);

    public /* synthetic */ InterProceduralValueAnalysis(IVGlobal iVGlobal, AbstractHeapFactory abstractHeapFactory, InterproceduralCFG interproceduralCFG, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iVGlobal, (i & 2) != 0 ? new HeapFactory(iVGlobal) : abstractHeapFactory, (i & 4) != 0 ? new InterproceduralCFG() : interproceduralCFG);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v9, types: [cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis$bottom$1] */
    public InterProceduralValueAnalysis(@NotNull IVGlobal vg, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull InterproceduralCFG icfg) {
        super(abstractHeapFactory, icfg);
        Intrinsics.checkNotNullParameter(vg, "vg");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Unit unitNewNopStmt = Jimple.v().newNopStmt();
        SootMethod method = Scene.v().makeSootMethod("initConstantPoolObjectData", CollectionsKt.emptyList(), G.v().soot_VoidType());
        Intrinsics.checkNotNull(unitNewNopStmt);
        Intrinsics.checkNotNull(method);
        CallStackContext callStackContext = new CallStackContext(null, unitNewNopStmt, method, 0);
        abstractHeapFactory.setConstantPoolObjectData(getTopState(callStackContext).builder());
        this.bottom = new AbstractBOTTOM<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis$bottom$1
        };
    }

    private final IFact<IValue> getTopState(final CallStackContext callStackContext) {
        final AbstractHeapFactory<IValue> hf = getHf();
        return new AbstractTOP<IValue>(hf) { // from class: cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis$getTopState$top$1
            @Override // cn.sast.dataflow.interprocedural.analysis.IFact
            public IFact.Builder<IValue> builder() {
                return new PointsToGraph(getHf(), getHf().getVg(), callStackContext, ExtensionsKt.persistentHashMapOf(), ExtensionsKt.persistentHashMapOf(), ExtensionsKt.persistentHashSetOf()).builder();
            }
        };
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> boundaryValue(@NotNull SootMethod entryPoint) {
        Intrinsics.checkNotNullParameter(entryPoint, "entryPoint");
        if (!entryPoint.isConcrete()) {
            return this.bottom;
        }
        if (!entryPoint.hasActiveBody()) {
            return this.bottom;
        }
        Unit entryUnit = entryPoint.getActiveBody().getUnits().getFirst();
        Intrinsics.checkNotNull(entryUnit);
        CallStackContext callStackContext = new CallStackContext(null, entryUnit, entryPoint, 0);
        IFact top = getTopState(callStackContext);
        IFact.Builder entryValue = top.builder();
        int parameterCount = entryPoint.getParameterCount();
        for (int argIndex = 0; argIndex < parameterCount; argIndex++) {
            AbstractHeapFactory<IValue> hf = getHf();
            Body activeBody = entryPoint.getActiveBody();
            Intrinsics.checkNotNullExpressionValue(activeBody, "getActiveBody(...)");
            Unit parameterUnit = AJimpleInterProceduralAnalysisKt.getParameterUnit(activeBody, argIndex);
            if (parameterUnit == null) {
                parameterUnit = entryUnit;
            }
            HeapValuesEnv env = hf.env(parameterUnit);
            IFact.Builder.DefaultImpls.assignNewExpr$default(entryValue, env, Integer.valueOf(argIndex), getHf().push(env, (HeapValuesEnv) new EntryParam(entryPoint, argIndex)).markOfEntryMethodParam(entryPoint).popHV(), false, 8, null);
        }
        if (!entryPoint.isStatic()) {
            AbstractHeapFactory<IValue> hf2 = getHf();
            Body activeBody2 = entryPoint.getActiveBody();
            Intrinsics.checkNotNullExpressionValue(activeBody2, "getActiveBody(...)");
            Unit parameterUnit2 = AJimpleInterProceduralAnalysisKt.getParameterUnit(activeBody2, -1);
            if (parameterUnit2 == null) {
                parameterUnit2 = entryUnit;
            }
            HeapValuesEnv env2 = hf2.env(parameterUnit2);
            IFact.Builder.DefaultImpls.assignNewExpr$default(entryValue, env2, -1, getHf().push(env2, (HeapValuesEnv) new EntryParam(entryPoint, -1)).markOfEntryMethodParam(entryPoint).popHV(), false, 8, null);
        }
        getHf().getVg().setStaticFieldTrackingMode(getStaticFieldTrackingMode());
        if (getStaticFieldTrackingMode() != StaticFieldTrackingMode.None) {
            HeapValuesEnv env3 = getHf().env(entryUnit);
            IFact.Builder.DefaultImpls.assignNewExpr$default(entryValue, env3, getHf().getVg().getGLOBAL_LOCAL(), getHf().push(env3, (HeapValuesEnv) getHf().getVg().getGLOBAL_SITE()).markOfEntryMethodParam(entryPoint).popHV(), false, 8, null);
        }
        return entryValue.build();
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> copy(@NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(iFact, "src");
        return iFact;
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> meet(@NotNull IFact<IValue> iFact, @NotNull IFact<IValue> iFact2) {
        Intrinsics.checkNotNullParameter(iFact, "op1");
        Intrinsics.checkNotNullParameter(iFact2, "op2");
        if (iFact.isBottom()) {
            return iFact2;
        }
        if (iFact2.isBottom()) {
            return iFact;
        }
        if (iFact.isTop()) {
            return iFact2;
        }
        if (iFact2.isTop()) {
            return iFact;
        }
        if (iFact == iFact2) {
            return iFact;
        }
        IFact.Builder $this$meet_u24lambda_u240 = iFact.builder();
        $this$meet_u24lambda_u240.union(iFact2);
        return $this$meet_u24lambda_u240.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> shallowMeet(@NotNull IFact<IValue> iFact, @NotNull IFact<IValue> iFact2) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(iFact, "op1");
        Intrinsics.checkNotNullParameter(iFact2, "op2");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> merge(@NotNull IFact<IValue> iFact, @NotNull IFact<IValue> iFact2) {
        Intrinsics.checkNotNullParameter(iFact, "local");
        Intrinsics.checkNotNullParameter(iFact2, "ret");
        return iFact2;
    }

    @Override // cn.sast.idfa.analysis.InterProceduralAnalysis
    @NotNull
    public IFact<IValue> bottomValue() {
        return this.bottom;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis
    @NotNull
    public AnyNewExprEnv newExprEnv(@NotNull AIContext context, @NotNull Unit node, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        return new AnyNewExprEnv(context.getMethod(), node);
    }

    public final boolean isRecursive(@NotNull SootMethod callee, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        PointsToGraphAbstract g = (PointsToGraphAbstract) iFact;
        Set set = new LinkedHashSet();
        for (CallStackContext cur = g.getCallStack(); cur != null; cur = cur.getCaller()) {
            if (!set.add(cur.getMethod())) {
                return true;
            }
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis, cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public boolean isAnalyzable(@NotNull SootMethod callee, @NotNull IFact<IValue> iFact) {
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(iFact, "in1");
        if (!super.isAnalyzable(callee, iFact) || isRecursive(callee, iFact)) {
            return false;
        }
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis
    @NotNull
    public Set<SootMethod> resolveTargets(@NotNull SootMethod callerMethod, @NotNull InvokeExpr ie, @NotNull Unit node, @NotNull IFact<IValue> iFact) {
        RefLikeType refLikeType;
        Intrinsics.checkNotNullParameter(callerMethod, "callerMethod");
        Intrinsics.checkNotNullParameter(ie, "ie");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(iFact, "inValue");
        if ((ie instanceof StaticInvokeExpr) || (ie instanceof DynamicInvokeExpr)) {
            Set<SootMethod> setSingleton = Collections.singleton(ie.getMethod());
            Intrinsics.checkNotNullExpressionValue(setSingleton, "singleton(...)");
            return setSingleton;
        }
        Set targets = new HashSet();
        Local base = ((InstanceInvokeExpr) ie).getBase();
        Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
        Local receiver = base;
        IHeapValues heapNodes = iFact.getTargetsUnsafe(receiver);
        if (heapNodes != null && heapNodes.isNotEmpty()) {
            for (IValue v : heapNodes.getValues()) {
                RefLikeType type = v.getType();
                RefLikeType refLikeType2 = type instanceof RefLikeType ? type : null;
                if (refLikeType2 != null) {
                    RefLikeType type2 = refLikeType2;
                    if (!v.typeIsConcrete() && (type2 instanceof RefType)) {
                        RefLikeType type3 = receiver.getType();
                        RefLikeType objectType = type3 instanceof RefType ? (RefType) type3 : null;
                        if (objectType == null) {
                            objectType = Scene.v().getObjectType();
                        }
                        refLikeType = objectType;
                    } else {
                        refLikeType = type2;
                    }
                    RefLikeType likeType = refLikeType;
                    if (likeType != null) {
                        Iterator iter = SootUtilsKt.getCallTargets((Type) likeType, callerMethod, ie, false);
                        while (iter.hasNext()) {
                            SootMethod target = iter.next();
                            targets.add(target);
                        }
                    }
                }
            }
        }
        if (targets.isEmpty()) {
            Iterable $this$forEach$iv = resolveTargets(callerMethod, node);
            for (Object element$iv : $this$forEach$iv) {
                SootMethod target2 = (SootMethod) element$iv;
                targets.add(target2);
            }
        }
        if (targets.isEmpty()) {
            return SetsKt.setOf(((InstanceInvokeExpr) ie).getMethod());
        }
        long dataFlowResolveTargetsMaxNum = ExtSettings.INSTANCE.getDataFlowResolveTargetsMaxNum();
        if (dataFlowResolveTargetsMaxNum >= 0 && targets.size() >= dataFlowResolveTargetsMaxNum) {
            logger.debug(() -> {
                return resolveTargets$lambda$2(r1, r2, r3);
            });
            return SetsKt.setOf(((InstanceInvokeExpr) ie).getMethod());
        }
        return targets;
    }

    private static final Object resolveTargets$lambda$2(Unit $node, Set $targets, SootMethod $callerMethod) {
        return "Too many callee at " + $node + ". size: " + $targets.size() + ". in " + $callerMethod + " line:" + $node.getJavaSourceStartLineNumber();
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    @NotNull
    public FixPointStatus hasChange(@NotNull AIContext context, @NotNull Unit node, @NotNull Unit succ, @NotNull IFact<IValue> iFact, @NotNull IFact<IValue> iFact2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        Intrinsics.checkNotNullParameter(iFact, "old");
        Intrinsics.checkNotNullParameter(iFact2, "new");
        Map<Unit, Integer> iteratorCount = context.getIteratorCount();
        Intrinsics.checkNotNull(iteratorCount);
        Integer num = iteratorCount.get(node);
        int count = (num != null ? num.intValue() : 0) + 1;
        Integer numValueOf = Integer.valueOf(count);
        Map<Unit, Integer> iteratorCount2 = context.getIteratorCount();
        Intrinsics.checkNotNull(iteratorCount2);
        iteratorCount2.put(node, numValueOf);
        if (!iFact2.isValid()) {
            return FixPointStatus.HasChange;
        }
        if ((!context.getMethod().getDeclaringClass().isApplicationClass() && count < ExtSettings.INSTANCE.getDataFlowIteratorCountForLibClasses()) || (context.getMethod().getDeclaringClass().isApplicationClass() && count < ExtSettings.INSTANCE.getDataFlowIteratorCountForAppClasses())) {
            return iFact.hasChange(context, iFact2);
        }
        Set<Pair<Unit, Unit>> widenNode = context.getWidenNode();
        Intrinsics.checkNotNull(widenNode);
        if (widenNode.add(TuplesKt.to(node, succ))) {
            return FixPointStatus.NeedWideningOperators;
        }
        return FixPointStatus.Fixpoint;
    }

    /* compiled from: InterProceduralValueAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: InterProceduralValueAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final kotlin.Unit logger$lambda$3() {
        return kotlin.Unit.INSTANCE;
    }
}
