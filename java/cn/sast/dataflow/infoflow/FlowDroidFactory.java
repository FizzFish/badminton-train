package cn.sast.dataflow.infoflow;

import cn.sast.dataflow.infoflow.svfa.SparseInfoFlowSolver;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.File;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.jimple.infoflow.AbstractInfoflow;
import soot.jimple.infoflow.BackwardsInfoflow;
import soot.jimple.infoflow.Infoflow;
import soot.jimple.infoflow.InfoflowConfiguration;
import soot.jimple.infoflow.cfg.BiDirICFGFactory;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.problems.AbstractInfoflowProblem;
import soot.jimple.infoflow.problems.BackwardsInfoflowProblem;
import soot.jimple.infoflow.problems.InfoflowProblem;
import soot.jimple.infoflow.problems.TaintPropagationResults;
import soot.jimple.infoflow.solver.IInfoflowSolver;
import soot.jimple.infoflow.solver.executors.InterruptableExecutor;

/* compiled from: FlowDroidFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JW\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/infoflow/FlowDroidFactory;", "", "<init>", "()V", "createInfoFlow", "Lsoot/jimple/infoflow/AbstractInfoflow;", "dataFlowDirection", "Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowDirection;", "androidPlatformDir", "", "forceAndroidJar", "", "lifecycleMethods", "", "Lsoot/SootMethod;", "cfgFactory", "Lsoot/jimple/infoflow/cfg/BiDirICFGFactory;", "useSparseOpt", "resultAddedHandlers", "", "Lsoot/jimple/infoflow/problems/TaintPropagationResults$OnTaintPropagationResultAdded;", "(Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowDirection;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/Collection;Lsoot/jimple/infoflow/cfg/BiDirICFGFactory;ZLjava/util/Set;)Lsoot/jimple/infoflow/AbstractInfoflow;", "logger", "Lmu/KLogger;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nFlowDroidFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowDroidFactory.kt\ncn/sast/dataflow/infoflow/FlowDroidFactory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n1#2:137\n*E\n"})
/* loaded from: FlowDroidFactory.class */
public final class FlowDroidFactory {

    @NotNull
    public static final FlowDroidFactory INSTANCE = new FlowDroidFactory();

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(FlowDroidFactory::logger$lambda$3);

    private FlowDroidFactory() {
    }

    @NotNull
    public final AbstractInfoflow createInfoFlow(@NotNull InfoflowConfiguration.DataFlowDirection dataFlowDirection, @Nullable final String androidPlatformDir, @Nullable Boolean forceAndroidJar, @Nullable final Collection<? extends SootMethod> collection, @Nullable final BiDirICFGFactory cfgFactory, final boolean useSparseOpt, @NotNull final Set<? extends TaintPropagationResults.OnTaintPropagationResultAdded> set) {
        AbstractInfoflow abstractInfoflow;
        Intrinsics.checkNotNullParameter(dataFlowDirection, "dataFlowDirection");
        Intrinsics.checkNotNullParameter(set, "resultAddedHandlers");
        if (!(androidPlatformDir != null || forceAndroidJar == null)) {
            throw new IllegalArgumentException("androidPlatformDir not special".toString());
        }
        if (!(androidPlatformDir == null || forceAndroidJar != null)) {
            throw new IllegalArgumentException("forceAndroidJar not special".toString());
        }
        if (androidPlatformDir != null && forceAndroidJar != null) {
            if (!((StringsKt.endsWith$default(androidPlatformDir, ".jar", false, 2, (Object) null) && forceAndroidJar.booleanValue()) || (new File(androidPlatformDir).isDirectory() && !forceAndroidJar.booleanValue()))) {
                throw new IllegalArgumentException(("error androidPlatformDir: " + androidPlatformDir + " and forceAndroidJar: " + forceAndroidJar).toString());
            }
        }
        if (dataFlowDirection == InfoflowConfiguration.DataFlowDirection.Forwards) {
            final boolean zBooleanValue = forceAndroidJar != null ? forceAndroidJar.booleanValue() : false;
            abstractInfoflow = (AbstractInfoflow) new Infoflow(collection, androidPlatformDir, cfgFactory, set, useSparseOpt, zBooleanValue) { // from class: cn.sast.dataflow.infoflow.FlowDroidFactory$createInfoFlow$infoFlow$1
                final /* synthetic */ Set<TaintPropagationResults.OnTaintPropagationResultAdded> $resultAddedHandlers;
                final /* synthetic */ boolean $useSparseOpt;

                /* compiled from: FlowDroidFactory.kt */
                @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
                /* loaded from: FlowDroidFactory$createInfoFlow$infoFlow$1$WhenMappings.class */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[InfoflowConfiguration.DataFlowSolver.values().length];
                        try {
                            iArr[InfoflowConfiguration.DataFlowSolver.ContextFlowSensitive.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[InfoflowConfiguration.DataFlowSolver.GarbageCollecting.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(androidPlatformDir, zBooleanValue, cfgFactory);
                    this.$resultAddedHandlers = set;
                    this.$useSparseOpt = useSparseOpt;
                    this.additionalEntryPointMethods = collection;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: createInfoflowProblem, reason: merged with bridge method [inline-methods] */
                public InfoflowProblem m112createInfoflowProblem(Abstraction zeroValue) {
                    Intrinsics.checkNotNullParameter(zeroValue, "zeroValue");
                    InfoflowProblem ret = super.createInfoflowProblem(zeroValue);
                    Intrinsics.checkNotNullExpressionValue(ret, "createInfoflowProblem(...)");
                    TaintPropagationResults propagationResults = ret.getResults();
                    Intrinsics.checkNotNullExpressionValue(propagationResults, "getResults(...)");
                    Iterable $this$forEach$iv = this.$resultAddedHandlers;
                    for (Object element$iv : $this$forEach$iv) {
                        TaintPropagationResults.OnTaintPropagationResultAdded handler = (TaintPropagationResults.OnTaintPropagationResultAdded) element$iv;
                        propagationResults.addResultAvailableHandler(handler);
                    }
                    return ret;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
                protected IInfoflowSolver createDataFlowSolver(InterruptableExecutor executor, AbstractInfoflowProblem problem, InfoflowConfiguration.SolverConfiguration solverConfig) throws NotImplementedError {
                    Intrinsics.checkNotNullParameter(executor, "executor");
                    Intrinsics.checkNotNullParameter(problem, "problem");
                    Intrinsics.checkNotNullParameter(solverConfig, "solverConfig");
                    if (!this.$useSparseOpt) {
                        FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$1::createDataFlowSolver$lambda$1);
                        IInfoflowSolver iInfoflowSolverCreateDataFlowSolver = super.createDataFlowSolver(executor, problem, solverConfig);
                        Intrinsics.checkNotNullExpressionValue(iInfoflowSolverCreateDataFlowSolver, "createDataFlowSolver(...)");
                        return iInfoflowSolverCreateDataFlowSolver;
                    }
                    InfoflowConfiguration.DataFlowSolver dataFlowSolver = solverConfig.getDataFlowSolver();
                    switch (dataFlowSolver == null ? -1 : WhenMappings.$EnumSwitchMapping$0[dataFlowSolver.ordinal()]) {
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$1::createDataFlowSolver$lambda$2);
                            return new SparseInfoFlowSolver(problem, executor);
                        case 2:
                            FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$1::createDataFlowSolver$lambda$3);
                            IInfoflowSolver sparseInfoFlowSolver = new cn.sast.dataflow.infoflow.svfa.gcSolver.SparseInfoFlowSolver(problem, executor);
                            this.solverPeerGroup.addSolver(sparseInfoFlowSolver);
                            sparseInfoFlowSolver.setPeerGroup(this.solverPeerGroup);
                            return sparseInfoFlowSolver;
                        default:
                            throw new NotImplementedError("An operation is not implemented: " + ("Sparse opt not support the " + solverConfig.getDataFlowSolver() + " solver yet"));
                    }
                }

                private static final Object createDataFlowSolver$lambda$1() {
                    return "Using forward solver with no sparse opt";
                }

                private static final Object createDataFlowSolver$lambda$2() {
                    return "Using context- and flow-sensitive solver with sparse opt";
                }

                private static final Object createDataFlowSolver$lambda$3() {
                    return "Using garbage-collecting solver with sparse opt";
                }
            };
        } else {
            final boolean zBooleanValue2 = forceAndroidJar != null ? forceAndroidJar.booleanValue() : false;
            abstractInfoflow = new BackwardsInfoflow(collection, androidPlatformDir, cfgFactory, set, useSparseOpt, zBooleanValue2) { // from class: cn.sast.dataflow.infoflow.FlowDroidFactory$createInfoFlow$infoFlow$2
                final /* synthetic */ Set<TaintPropagationResults.OnTaintPropagationResultAdded> $resultAddedHandlers;
                final /* synthetic */ boolean $useSparseOpt;

                /* compiled from: FlowDroidFactory.kt */
                @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
                /* loaded from: FlowDroidFactory$createInfoFlow$infoFlow$2$WhenMappings.class */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[InfoflowConfiguration.DataFlowSolver.values().length];
                        try {
                            iArr[InfoflowConfiguration.DataFlowSolver.ContextFlowSensitive.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[InfoflowConfiguration.DataFlowSolver.GarbageCollecting.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(androidPlatformDir, zBooleanValue2, cfgFactory);
                    this.$resultAddedHandlers = set;
                    this.$useSparseOpt = useSparseOpt;
                    this.additionalEntryPointMethods = collection;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: createInfoflowProblem, reason: merged with bridge method [inline-methods] */
                public BackwardsInfoflowProblem m114createInfoflowProblem(Abstraction zeroValue) {
                    Intrinsics.checkNotNullParameter(zeroValue, "zeroValue");
                    BackwardsInfoflowProblem ret = super.createInfoflowProblem(zeroValue);
                    Intrinsics.checkNotNullExpressionValue(ret, "createInfoflowProblem(...)");
                    TaintPropagationResults propagationResults = ret.getResults();
                    Intrinsics.checkNotNullExpressionValue(propagationResults, "getResults(...)");
                    Iterable $this$forEach$iv = this.$resultAddedHandlers;
                    for (Object element$iv : $this$forEach$iv) {
                        TaintPropagationResults.OnTaintPropagationResultAdded handler = (TaintPropagationResults.OnTaintPropagationResultAdded) element$iv;
                        propagationResults.addResultAvailableHandler(handler);
                    }
                    return ret;
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
                protected IInfoflowSolver createDataFlowSolver(InterruptableExecutor executor, AbstractInfoflowProblem problem, InfoflowConfiguration.SolverConfiguration solverConfig) throws NotImplementedError {
                    Intrinsics.checkNotNullParameter(executor, "executor");
                    Intrinsics.checkNotNullParameter(problem, "problem");
                    Intrinsics.checkNotNullParameter(solverConfig, "solverConfig");
                    if (!this.$useSparseOpt) {
                        FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$2::createDataFlowSolver$lambda$1);
                        IInfoflowSolver iInfoflowSolverCreateDataFlowSolver = super.createDataFlowSolver(executor, problem, solverConfig);
                        Intrinsics.checkNotNullExpressionValue(iInfoflowSolverCreateDataFlowSolver, "createDataFlowSolver(...)");
                        return iInfoflowSolverCreateDataFlowSolver;
                    }
                    InfoflowConfiguration.DataFlowSolver dataFlowSolver = solverConfig.getDataFlowSolver();
                    switch (dataFlowSolver == null ? -1 : WhenMappings.$EnumSwitchMapping$0[dataFlowSolver.ordinal()]) {
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$2::createDataFlowSolver$lambda$2);
                            return new SparseInfoFlowSolver(problem, executor);
                        case 2:
                            FlowDroidFactory.logger.info(FlowDroidFactory$createInfoFlow$infoFlow$2::createDataFlowSolver$lambda$3);
                            IInfoflowSolver sparseInfoFlowSolver = new cn.sast.dataflow.infoflow.svfa.gcSolver.SparseInfoFlowSolver(problem, executor);
                            this.solverPeerGroup.addSolver(sparseInfoFlowSolver);
                            sparseInfoFlowSolver.setPeerGroup(this.solverPeerGroup);
                            return sparseInfoFlowSolver;
                        default:
                            throw new NotImplementedError("An operation is not implemented: " + ("Sparse opt not support the " + solverConfig.getDataFlowSolver() + " solver yet"));
                    }
                }

                private static final Object createDataFlowSolver$lambda$1() {
                    return "Using backward solver with no sparse opt";
                }

                private static final Object createDataFlowSolver$lambda$2() {
                    return "Using context- and flow-sensitive solver with sparse opt";
                }

                private static final Object createDataFlowSolver$lambda$3() {
                    return "Using garbage-collecting solver with sparse opt";
                }
            };
        }
        AbstractInfoflow infoFlow = abstractInfoflow;
        return infoFlow;
    }

    private static final Unit logger$lambda$3() {
        return Unit.INSTANCE;
    }
}
