package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.infoflow.InfoflowConfigurationExt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.Context;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.core.UsageError;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import com.github.ajalt.clikt.parameters.options.FlagOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionCallTransformContext;
import com.github.ajalt.clikt.parameters.options.OptionKt;
import com.github.ajalt.clikt.parameters.options.OptionWithValues;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.ChoiceKt;
import com.github.ajalt.clikt.parameters.types.IntKt;
import com.github.ajalt.clikt.parameters.types.LongKt;
import com.github.ajalt.clikt.parameters.types.RangeKt;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.InfoflowConfiguration;

/* compiled from: FlowDroidOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u001c\u0010©\u0001\u001a\u00030¨\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u0001J\b\u0010®\u0001\u001a\u00030¯\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\b\u0010\u0006R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0011\u0010\u0006R\u001b\u0010\u0013\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0014\u0010\u0006R\u001b\u0010\u0016\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0017\u0010\u0006R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\n\u001a\u0004\b\u001f\u0010\u0006R\u001b\u0010!\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b\"\u0010\u0006R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b/\u0010\u0006R\u001b\u00101\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\n\u001a\u0004\b2\u0010\u0006R\u001b\u00104\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\n\u001a\u0004\b5\u0010\u0006R\u001b\u00107\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\n\u001a\u0004\b8\u0010\u0006R\u001b\u0010:\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\n\u001a\u0004\b;\u0010\u0006R\u001b\u0010=\u001a\u00020>8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\n\u001a\u0004\b?\u0010@R\u001b\u0010B\u001a\u00020C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\n\u001a\u0004\bD\u0010ER\u001b\u0010G\u001a\u00020H8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\n\u001a\u0004\bI\u0010JR\u001b\u0010L\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\n\u001a\u0004\bM\u0010\u0006R\u001b\u0010O\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\n\u001a\u0004\bP\u0010\u0006R\u001b\u0010R\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010\n\u001a\u0004\bT\u0010UR\u001b\u0010W\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bY\u0010\n\u001a\u0004\bX\u0010\u0006R\u001b\u0010Z\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010\n\u001a\u0004\b[\u0010\u0006R\u001b\u0010]\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u0010\n\u001a\u0004\b^\u0010\u0006R\u001b\u0010`\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bb\u0010\n\u001a\u0004\ba\u0010\u0006R\u001b\u0010c\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010\n\u001a\u0004\bd\u0010\u0006R\u001b\u0010f\u001a\u00020g8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bj\u0010\n\u001a\u0004\bh\u0010iR\u001b\u0010k\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bm\u0010\n\u001a\u0004\bl\u0010\u0006R\u001b\u0010n\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bp\u0010\n\u001a\u0004\bo\u0010\u0006R\u001b\u0010q\u001a\u00020r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010\n\u001a\u0004\bs\u0010tR\u001b\u0010v\u001a\u00020w8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bz\u0010\n\u001a\u0004\bx\u0010yR\u001b\u0010{\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b}\u0010\n\u001a\u0004\b|\u0010\u001cR\u001c\u0010~\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\r\n\u0005\b\u0080\u0001\u0010\n\u001a\u0004\b\u007f\u0010\u001cR\u001e\u0010\u0081\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0083\u0001\u0010\n\u001a\u0005\b\u0082\u0001\u0010\u001cR\u001e\u0010\u0084\u0001\u001a\u00020g8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0086\u0001\u0010\n\u001a\u0005\b\u0085\u0001\u0010iR\u001e\u0010\u0087\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0089\u0001\u0010\n\u001a\u0005\b\u0088\u0001\u0010\u001cR\u001e\u0010\u008a\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008c\u0001\u0010\n\u001a\u0005\b\u008b\u0001\u0010\u001cR\u001e\u0010\u008d\u0001\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008f\u0001\u0010\n\u001a\u0005\b\u008e\u0001\u0010\u0006R\u001e\u0010\u0090\u0001\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0092\u0001\u0010\n\u001a\u0005\b\u0091\u0001\u0010\u0006R\u001e\u0010\u0093\u0001\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0095\u0001\u0010\n\u001a\u0005\b\u0094\u0001\u0010\u0006R\u001e\u0010\u0096\u0001\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0098\u0001\u0010\n\u001a\u0005\b\u0097\u0001\u0010\u0006R\u001e\u0010\u0099\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u009b\u0001\u0010\n\u001a\u0005\b\u009a\u0001\u0010\u001cR\u001e\u0010\u009c\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u009e\u0001\u0010\n\u001a\u0005\b\u009d\u0001\u0010\u001cR\u001e\u0010\u009f\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b¡\u0001\u0010\n\u001a\u0005\b \u0001\u0010\u001cR \u0010¢\u0001\u001a\u00030£\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¦\u0001\u0010\n\u001a\u0006\b¤\u0001\u0010¥\u0001¨\u0006°\u0001"}, d2 = {"Lcn/sast/cli/command/FlowDroidOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "isHidden", "", "<init>", "(Z)V", "()Z", "enableFlowDroid", "getEnableFlowDroid", "enableFlowDroid$delegate", "Lkotlin/properties/ReadOnlyProperty;", "baseDirectory", "", "getBaseDirectory", "()Ljava/lang/String;", "baseDirectory$delegate", "noPathAgnosticResults", "getNoPathAgnosticResults", "noPathAgnosticResults$delegate", "oneResultPerAccessPath", "getOneResultPerAccessPath", "oneResultPerAccessPath$delegate", "mergeNeighbors", "getMergeNeighbors", "mergeNeighbors$delegate", "stopAfterFirstKFlows", "", "getStopAfterFirstKFlows", "()I", "stopAfterFirstKFlows$delegate", "inspectSources", "getInspectSources", "inspectSources$delegate", "inspectSinks", "getInspectSinks", "inspectSinks$delegate", "implicitFlowMode", "Lsoot/jimple/infoflow/InfoflowConfiguration$ImplicitFlowMode;", "getImplicitFlowMode", "()Lsoot/jimple/infoflow/InfoflowConfiguration$ImplicitFlowMode;", "implicitFlowMode$delegate", "sootIntegrationMode", "Lsoot/jimple/infoflow/InfoflowConfiguration$SootIntegrationMode;", "getSootIntegrationMode", "()Lsoot/jimple/infoflow/InfoflowConfiguration$SootIntegrationMode;", "sootIntegrationMode$delegate", "disableFlowSensitiveAliasing", "getDisableFlowSensitiveAliasing", "disableFlowSensitiveAliasing$delegate", "disableExceptionTracking", "getDisableExceptionTracking", "disableExceptionTracking$delegate", "disableArrayTracking", "getDisableArrayTracking", "disableArrayTracking$delegate", "disableArraySizeTainting", "getDisableArraySizeTainting", "disableArraySizeTainting$delegate", "disableTypeChecking", "getDisableTypeChecking", "disableTypeChecking$delegate", "callgraphAlgorithm", "Lsoot/jimple/infoflow/InfoflowConfiguration$CallgraphAlgorithm;", "getCallgraphAlgorithm", "()Lsoot/jimple/infoflow/InfoflowConfiguration$CallgraphAlgorithm;", "callgraphAlgorithm$delegate", "aliasingAlgorithm", "Lsoot/jimple/infoflow/InfoflowConfiguration$AliasingAlgorithm;", "getAliasingAlgorithm", "()Lsoot/jimple/infoflow/InfoflowConfiguration$AliasingAlgorithm;", "aliasingAlgorithm$delegate", "dataFlowDirection", "Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowDirection;", "getDataFlowDirection", "()Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowDirection;", "dataFlowDirection$delegate", "ignoreFlowsInSystemPackages", "getIgnoreFlowsInSystemPackages", "ignoreFlowsInSystemPackages$delegate", "writeOutputFiles", "getWriteOutputFiles", "writeOutputFiles$delegate", "codeEliminationMode", "Lsoot/jimple/infoflow/InfoflowConfiguration$CodeEliminationMode;", "getCodeEliminationMode", "()Lsoot/jimple/infoflow/InfoflowConfiguration$CodeEliminationMode;", "codeEliminationMode$delegate", "disableLogSourcesAndSinks", "getDisableLogSourcesAndSinks", "disableLogSourcesAndSinks$delegate", "enableReflection", "getEnableReflection", "enableReflection$delegate", "disableLineNumbers", "getDisableLineNumbers", "disableLineNumbers$delegate", "disableTaintAnalysis", "getDisableTaintAnalysis", "disableTaintAnalysis$delegate", "incrementalResultReporting", "getIncrementalResultReporting", "incrementalResultReporting$delegate", "dataFlowTimeout", "", "getDataFlowTimeout", "()J", "dataFlowTimeout$delegate", "oneSourceAtATime", "getOneSourceAtATime", "oneSourceAtATime$delegate", "sequentialPathProcessing", "getSequentialPathProcessing", "sequentialPathProcessing$delegate", "pathReconstructionMode", "Lsoot/jimple/infoflow/InfoflowConfiguration$PathReconstructionMode;", "getPathReconstructionMode", "()Lsoot/jimple/infoflow/InfoflowConfiguration$PathReconstructionMode;", "pathReconstructionMode$delegate", "pathBuildingAlgorithm", "Lsoot/jimple/infoflow/InfoflowConfiguration$PathBuildingAlgorithm;", "getPathBuildingAlgorithm", "()Lsoot/jimple/infoflow/InfoflowConfiguration$PathBuildingAlgorithm;", "pathBuildingAlgorithm$delegate", "maxCallStackSize", "getMaxCallStackSize", "maxCallStackSize$delegate", "maxPathLength", "getMaxPathLength", "maxPathLength$delegate", "maxPathsPerAbstraction", "getMaxPathsPerAbstraction", "maxPathsPerAbstraction$delegate", "pathReconstructionTimeout", "getPathReconstructionTimeout", "pathReconstructionTimeout$delegate", "pathReconstructionBatchSize", "getPathReconstructionBatchSize", "pathReconstructionBatchSize$delegate", "accessPathLength", "getAccessPathLength", "accessPathLength$delegate", "useRecursiveAccessPaths", "getUseRecursiveAccessPaths", "useRecursiveAccessPaths$delegate", "useThisChainReduction", "getUseThisChainReduction", "useThisChainReduction$delegate", "useSameFieldReduction", "getUseSameFieldReduction", "useSameFieldReduction$delegate", "disableSparseOpt", "getDisableSparseOpt", "disableSparseOpt$delegate", "maxJoinPointAbstractions", "getMaxJoinPointAbstractions", "maxJoinPointAbstractions$delegate", "maxAbstractionPathLength", "getMaxAbstractionPathLength", "maxAbstractionPathLength$delegate", "maxCalleesPerCallSite", "getMaxCalleesPerCallSite", "maxCalleesPerCallSite$delegate", "dataFlowSolver", "Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowSolver;", "getDataFlowSolver", "()Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowSolver;", "dataFlowSolver$delegate", "initializeGlobalStaticCommandLineOptions", "", "configureInfoFlowConfig", "infoFlowConfig", "Lsoot/jimple/infoflow/InfoflowConfiguration;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "getExtInfoFlowConfig", "Lcn/sast/dataflow/infoflow/InfoflowConfigurationExt;", "corax-cli"})
@SourceDebugExtension({"SMAP\nFlowDroidOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowDroidOptions.kt\ncn/sast/cli/command/FlowDroidOptions\n+ 2 Convert.kt\ncom/github/ajalt/clikt/parameters/options/OptionWithValuesKt__ConvertKt\n+ 3 enum.kt\ncom/github/ajalt/clikt/parameters/types/EnumKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 enum.kt\ncom/github/ajalt/clikt/parameters/types/EnumKt$enum$3\n*L\n1#1,152:1\n35#2,6:153\n70#2:159\n82#2,4:160\n45#3,5:164\n45#3,5:175\n45#3,5:186\n45#3,5:197\n45#3,5:208\n45#3,5:219\n45#3,5:230\n45#3,5:241\n45#3,5:252\n8541#4,2:169\n8801#4,2:171\n8804#4:174\n8541#4,2:180\n8801#4,2:182\n8804#4:185\n8541#4,2:191\n8801#4,2:193\n8804#4:196\n8541#4,2:202\n8801#4,2:204\n8804#4:207\n8541#4,2:213\n8801#4,2:215\n8804#4:218\n8541#4,2:224\n8801#4,2:226\n8804#4:229\n8541#4,2:235\n8801#4,2:237\n8804#4:240\n8541#4,2:246\n8801#4,2:248\n8804#4:251\n8541#4,2:257\n8801#4,2:259\n8804#4:262\n47#5:173\n47#5:184\n47#5:195\n47#5:206\n47#5:217\n47#5:228\n47#5:239\n47#5:250\n47#5:261\n*S KotlinDebug\n*F\n+ 1 FlowDroidOptions.kt\ncn/sast/cli/command/FlowDroidOptions\n*L\n17#1:153,6\n17#1:159\n17#1:160,4\n28#1:164,5\n30#1:175,5\n37#1:186,5\n39#1:197,5\n41#1:208,5\n44#1:219,5\n56#1:230,5\n58#1:241,5\n77#1:252,5\n28#1:169,2\n28#1:171,2\n28#1:174\n30#1:180,2\n30#1:182,2\n30#1:185\n37#1:191,2\n37#1:193,2\n37#1:196\n39#1:202,2\n39#1:204,2\n39#1:207\n41#1:213,2\n41#1:215,2\n41#1:218\n44#1:224,2\n44#1:226,2\n44#1:229\n56#1:235,2\n56#1:237,2\n56#1:240\n58#1:246,2\n58#1:248,2\n58#1:251\n77#1:257,2\n77#1:259,2\n77#1:262\n28#1:173\n30#1:184\n37#1:195\n39#1:206\n41#1:217\n44#1:228\n56#1:239\n58#1:250\n77#1:261\n*E\n"})
/* loaded from: FlowDroidOptions.class */
public final class FlowDroidOptions extends OptionGroup {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "enableFlowDroid", "getEnableFlowDroid()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "baseDirectory", "getBaseDirectory()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "noPathAgnosticResults", "getNoPathAgnosticResults()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "oneResultPerAccessPath", "getOneResultPerAccessPath()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "mergeNeighbors", "getMergeNeighbors()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "stopAfterFirstKFlows", "getStopAfterFirstKFlows()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "inspectSources", "getInspectSources()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "inspectSinks", "getInspectSinks()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "implicitFlowMode", "getImplicitFlowMode()Lsoot/jimple/infoflow/InfoflowConfiguration$ImplicitFlowMode;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "sootIntegrationMode", "getSootIntegrationMode()Lsoot/jimple/infoflow/InfoflowConfiguration$SootIntegrationMode;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableFlowSensitiveAliasing", "getDisableFlowSensitiveAliasing()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableExceptionTracking", "getDisableExceptionTracking()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableArrayTracking", "getDisableArrayTracking()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableArraySizeTainting", "getDisableArraySizeTainting()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableTypeChecking", "getDisableTypeChecking()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "callgraphAlgorithm", "getCallgraphAlgorithm()Lsoot/jimple/infoflow/InfoflowConfiguration$CallgraphAlgorithm;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "aliasingAlgorithm", "getAliasingAlgorithm()Lsoot/jimple/infoflow/InfoflowConfiguration$AliasingAlgorithm;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "dataFlowDirection", "getDataFlowDirection()Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowDirection;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "ignoreFlowsInSystemPackages", "getIgnoreFlowsInSystemPackages()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "writeOutputFiles", "getWriteOutputFiles()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "codeEliminationMode", "getCodeEliminationMode()Lsoot/jimple/infoflow/InfoflowConfiguration$CodeEliminationMode;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableLogSourcesAndSinks", "getDisableLogSourcesAndSinks()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "enableReflection", "getEnableReflection()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableLineNumbers", "getDisableLineNumbers()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableTaintAnalysis", "getDisableTaintAnalysis()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "incrementalResultReporting", "getIncrementalResultReporting()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "dataFlowTimeout", "getDataFlowTimeout()J", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "oneSourceAtATime", "getOneSourceAtATime()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "sequentialPathProcessing", "getSequentialPathProcessing()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "pathReconstructionMode", "getPathReconstructionMode()Lsoot/jimple/infoflow/InfoflowConfiguration$PathReconstructionMode;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "pathBuildingAlgorithm", "getPathBuildingAlgorithm()Lsoot/jimple/infoflow/InfoflowConfiguration$PathBuildingAlgorithm;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxCallStackSize", "getMaxCallStackSize()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxPathLength", "getMaxPathLength()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxPathsPerAbstraction", "getMaxPathsPerAbstraction()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "pathReconstructionTimeout", "getPathReconstructionTimeout()J", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "pathReconstructionBatchSize", "getPathReconstructionBatchSize()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "accessPathLength", "getAccessPathLength()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "useRecursiveAccessPaths", "getUseRecursiveAccessPaths()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "useThisChainReduction", "getUseThisChainReduction()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "useSameFieldReduction", "getUseSameFieldReduction()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "disableSparseOpt", "getDisableSparseOpt()Z", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxJoinPointAbstractions", "getMaxJoinPointAbstractions()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxAbstractionPathLength", "getMaxAbstractionPathLength()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "maxCalleesPerCallSite", "getMaxCalleesPerCallSite()I", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidOptions.class, "dataFlowSolver", "getDataFlowSolver()Lsoot/jimple/infoflow/InfoflowConfiguration$DataFlowSolver;", 0))};
    private final boolean isHidden;

    @NotNull
    private final ReadOnlyProperty enableFlowDroid$delegate;

    @NotNull
    private final ReadOnlyProperty baseDirectory$delegate;

    @NotNull
    private final ReadOnlyProperty noPathAgnosticResults$delegate;

    @NotNull
    private final ReadOnlyProperty oneResultPerAccessPath$delegate;

    @NotNull
    private final ReadOnlyProperty mergeNeighbors$delegate;

    @NotNull
    private final ReadOnlyProperty stopAfterFirstKFlows$delegate;

    @NotNull
    private final ReadOnlyProperty inspectSources$delegate;

    @NotNull
    private final ReadOnlyProperty inspectSinks$delegate;

    @NotNull
    private final ReadOnlyProperty implicitFlowMode$delegate;

    @NotNull
    private final ReadOnlyProperty sootIntegrationMode$delegate;

    @NotNull
    private final ReadOnlyProperty disableFlowSensitiveAliasing$delegate;

    @NotNull
    private final ReadOnlyProperty disableExceptionTracking$delegate;

    @NotNull
    private final ReadOnlyProperty disableArrayTracking$delegate;

    @NotNull
    private final ReadOnlyProperty disableArraySizeTainting$delegate;

    @NotNull
    private final ReadOnlyProperty disableTypeChecking$delegate;

    @NotNull
    private final ReadOnlyProperty callgraphAlgorithm$delegate;

    @NotNull
    private final ReadOnlyProperty aliasingAlgorithm$delegate;

    @NotNull
    private final ReadOnlyProperty dataFlowDirection$delegate;

    @NotNull
    private final ReadOnlyProperty ignoreFlowsInSystemPackages$delegate;

    @NotNull
    private final ReadOnlyProperty writeOutputFiles$delegate;

    @NotNull
    private final ReadOnlyProperty codeEliminationMode$delegate;

    @NotNull
    private final ReadOnlyProperty disableLogSourcesAndSinks$delegate;

    @NotNull
    private final ReadOnlyProperty enableReflection$delegate;

    @NotNull
    private final ReadOnlyProperty disableLineNumbers$delegate;

    @NotNull
    private final ReadOnlyProperty disableTaintAnalysis$delegate;

    @NotNull
    private final ReadOnlyProperty incrementalResultReporting$delegate;

    @NotNull
    private final ReadOnlyProperty dataFlowTimeout$delegate;

    @NotNull
    private final ReadOnlyProperty oneSourceAtATime$delegate;

    @NotNull
    private final ReadOnlyProperty sequentialPathProcessing$delegate;

    @NotNull
    private final ReadOnlyProperty pathReconstructionMode$delegate;

    @NotNull
    private final ReadOnlyProperty pathBuildingAlgorithm$delegate;

    @NotNull
    private final ReadOnlyProperty maxCallStackSize$delegate;

    @NotNull
    private final ReadOnlyProperty maxPathLength$delegate;

    @NotNull
    private final ReadOnlyProperty maxPathsPerAbstraction$delegate;

    @NotNull
    private final ReadOnlyProperty pathReconstructionTimeout$delegate;

    @NotNull
    private final ReadOnlyProperty pathReconstructionBatchSize$delegate;

    @NotNull
    private final ReadOnlyProperty accessPathLength$delegate;

    @NotNull
    private final ReadOnlyProperty useRecursiveAccessPaths$delegate;

    @NotNull
    private final ReadOnlyProperty useThisChainReduction$delegate;

    @NotNull
    private final ReadOnlyProperty useSameFieldReduction$delegate;

    @NotNull
    private final ReadOnlyProperty disableSparseOpt$delegate;

    @NotNull
    private final ReadOnlyProperty maxJoinPointAbstractions$delegate;

    @NotNull
    private final ReadOnlyProperty maxAbstractionPathLength$delegate;

    @NotNull
    private final ReadOnlyProperty maxCalleesPerCallSite$delegate;

    @NotNull
    private final ReadOnlyProperty dataFlowSolver$delegate;

    public FlowDroidOptions() {
        this(false, 1, null);
    }

    public FlowDroidOptions(boolean isHidden) {
        super("FlowDroid Options", (String) null, 2, (DefaultConstructorMarker) null);
        this.isHidden = isHidden;
        final OptionWithValues $this$convert_u24default$iv = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null);
        final String metavar$iv = "BOOL";
        Function1 metavar$iv$iv$iv = new Function1<Context, String>() { // from class: cn.sast.cli.command.FlowDroidOptions$special$$inlined$convert$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @NotNull
            public final String invoke(@NotNull Context $this$convert) {
                Intrinsics.checkNotNullParameter($this$convert, "$this$convert");
                return metavar$iv;
            }
        };
        Function2 valueTransform$iv$iv$iv = new Function2<OptionCallTransformContext, String, Boolean>() { // from class: cn.sast.cli.command.FlowDroidOptions$special$$inlined$convert$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.github.ajalt.clikt.core.UsageError */
            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
            @NotNull
            public final Boolean invoke(@NotNull OptionCallTransformContext $this$null, @NotNull String it) throws UsageError, KotlinNothingValueException {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                Intrinsics.checkNotNullParameter(it, "it");
                try {
                    return Boolean.valueOf(Boolean.parseBoolean((String) $this$convert_u24default$iv.getTransformValue().invoke($this$null, it)));
                } catch (UsageError err) {
                    UsageError usageError = err;
                    String paramName = err.getParamName();
                    if (paramName == null) {
                        String n = $this$null.getName();
                        usageError = usageError;
                        paramName = !(n.length() == 0) ? n : null;
                        if (paramName == null) {
                            paramName = OptionKt.longestName($this$null.getOption());
                        }
                    }
                    usageError.setParamName(paramName);
                    throw err;
                } catch (Exception err2) {
                    String message = err2.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    $this$null.fail(message);
                    throw new KotlinNothingValueException();
                }
            }
        };
        Function2 function2DefaultEachProcessor = OptionWithValuesKt.defaultEachProcessor();
        Function2 function2DefaultAllProcessor = OptionWithValuesKt.defaultAllProcessor();
        Function2 function2DefaultValidator = OptionWithValuesKt.defaultValidator();
        Function1 metavarGetter = $this$convert_u24default$iv.getMetavarGetter();
        metavarGetter = metavarGetter == null ? metavar$iv$iv$iv : metavarGetter;
        CompletionCandidates explicitCompletionCandidates = $this$convert_u24default$iv.getExplicitCompletionCandidates();
        this.enableFlowDroid$delegate = OptionWithValuesKt.help(OptionWithValuesKt.required(OptionWithValues.DefaultImpls.copy$default($this$convert_u24default$iv, valueTransform$iv$iv$iv, function2DefaultEachProcessor, function2DefaultAllProcessor, function2DefaultValidator, (Set) null, metavarGetter, (IntRange) null, (Function1) null, false, (Map) null, (String) null, (String) null, (Regex) null, explicitCompletionCandidates == null ? null : explicitCompletionCandidates, (Set) null, false, false, false, 253904, (Object) null)), "Set if the FlowDroid engine shall be enabled").provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.baseDirectory$delegate = OptionWithValuesKt.default$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), "", (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.noPathAgnosticResults$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[2]);
        this.oneResultPerAccessPath$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[3]);
        this.mergeNeighbors$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[4]);
        this.stopAfterFirstKFlows$delegate = OptionWithValuesKt.default$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), 0, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[5]);
        this.inspectSources$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[6]);
        this.inspectSinks$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[7]);
        OptionWithValues $this$enum_u24default$iv = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues = InfoflowConfiguration.ImplicitFlowMode.values();
        int capacity$iv$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues.length), 16);
        Map destination$iv$iv$iv = new LinkedHashMap(capacity$iv$iv);
        for (Enum r0 : enumArrValues) {
            Enum it$iv = r0;
            destination$iv$iv$iv.put(it$iv.name(), r0);
        }
        this.implicitFlowMode$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv, destination$iv$iv$iv, (String) null, true, 2, (Object) null), InfoflowConfiguration.ImplicitFlowMode.ArrayAccesses, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[8]);
        OptionWithValues $this$enum_u24default$iv2 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues2 = InfoflowConfiguration.SootIntegrationMode.values();
        int capacity$iv$iv2 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues2.length), 16);
        Map destination$iv$iv$iv2 = new LinkedHashMap(capacity$iv$iv2);
        for (Enum r02 : enumArrValues2) {
            Enum it$iv2 = r02;
            destination$iv$iv$iv2.put(it$iv2.name(), r02);
        }
        this.sootIntegrationMode$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv2, destination$iv$iv$iv2, (String) null, true, 2, (Object) null), InfoflowConfiguration.SootIntegrationMode.CreateNewInstance, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[9]);
        this.disableFlowSensitiveAliasing$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[10]);
        this.disableExceptionTracking$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[11]);
        this.disableArrayTracking$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[12]);
        this.disableArraySizeTainting$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[13]);
        this.disableTypeChecking$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[14]);
        OptionWithValues $this$enum_u24default$iv3 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues3 = InfoflowConfiguration.CallgraphAlgorithm.values();
        int capacity$iv$iv3 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues3.length), 16);
        Map destination$iv$iv$iv3 = new LinkedHashMap(capacity$iv$iv3);
        for (Enum r03 : enumArrValues3) {
            Enum it$iv3 = r03;
            destination$iv$iv$iv3.put(it$iv3.name(), r03);
        }
        this.callgraphAlgorithm$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv3, destination$iv$iv$iv3, (String) null, true, 2, (Object) null), InfoflowConfiguration.CallgraphAlgorithm.AutomaticSelection, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[15]);
        OptionWithValues $this$enum_u24default$iv4 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues4 = InfoflowConfiguration.AliasingAlgorithm.values();
        int capacity$iv$iv4 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues4.length), 16);
        Map destination$iv$iv$iv4 = new LinkedHashMap(capacity$iv$iv4);
        for (Enum r04 : enumArrValues4) {
            Enum it$iv4 = r04;
            destination$iv$iv$iv4.put(it$iv4.name(), r04);
        }
        this.aliasingAlgorithm$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv4, destination$iv$iv$iv4, (String) null, true, 2, (Object) null), InfoflowConfiguration.AliasingAlgorithm.FlowSensitive, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[16]);
        OptionWithValues $this$enum_u24default$iv5 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues5 = InfoflowConfiguration.DataFlowDirection.values();
        int capacity$iv$iv5 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues5.length), 16);
        Map destination$iv$iv$iv5 = new LinkedHashMap(capacity$iv$iv5);
        for (Enum r05 : enumArrValues5) {
            Enum it$iv5 = r05;
            destination$iv$iv$iv5.put(it$iv5.name(), r05);
        }
        this.dataFlowDirection$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv5, destination$iv$iv$iv5, (String) null, true, 2, (Object) null), InfoflowConfiguration.DataFlowDirection.Forwards, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[17]);
        this.ignoreFlowsInSystemPackages$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[18]);
        this.writeOutputFiles$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[19]);
        OptionWithValues $this$enum_u24default$iv6 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues6 = InfoflowConfiguration.CodeEliminationMode.values();
        int capacity$iv$iv6 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues6.length), 16);
        Map destination$iv$iv$iv6 = new LinkedHashMap(capacity$iv$iv6);
        for (Enum r06 : enumArrValues6) {
            Enum it$iv6 = r06;
            destination$iv$iv$iv6.put(it$iv6.name(), r06);
        }
        this.codeEliminationMode$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv6, destination$iv$iv$iv6, (String) null, true, 2, (Object) null), InfoflowConfiguration.CodeEliminationMode.NoCodeElimination, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[20]);
        this.disableLogSourcesAndSinks$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[21]);
        this.enableReflection$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[22]);
        this.disableLineNumbers$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[23]);
        this.disableTaintAnalysis$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[24]);
        this.incrementalResultReporting$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[25]);
        this.dataFlowTimeout$delegate = OptionWithValuesKt.default$default(LongKt.long$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), 0L, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[26]);
        this.oneSourceAtATime$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[27]);
        this.sequentialPathProcessing$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[28]);
        OptionWithValues $this$enum_u24default$iv7 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues7 = InfoflowConfiguration.PathReconstructionMode.values();
        int capacity$iv$iv7 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues7.length), 16);
        Map destination$iv$iv$iv7 = new LinkedHashMap(capacity$iv$iv7);
        for (Enum r07 : enumArrValues7) {
            Enum it$iv7 = r07;
            destination$iv$iv$iv7.put(it$iv7.name(), r07);
        }
        this.pathReconstructionMode$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv7, destination$iv$iv$iv7, (String) null, true, 2, (Object) null), InfoflowConfiguration.PathReconstructionMode.Precise, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[29]);
        OptionWithValues $this$enum_u24default$iv8 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues8 = InfoflowConfiguration.PathBuildingAlgorithm.values();
        int capacity$iv$iv8 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues8.length), 16);
        Map destination$iv$iv$iv8 = new LinkedHashMap(capacity$iv$iv8);
        for (Enum r08 : enumArrValues8) {
            Enum it$iv8 = r08;
            destination$iv$iv$iv8.put(it$iv8.name(), r08);
        }
        this.pathBuildingAlgorithm$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv8, destination$iv$iv$iv8, (String) null, true, 2, (Object) null), InfoflowConfiguration.PathBuildingAlgorithm.ContextSensitive, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[30]);
        this.maxCallStackSize$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), 30, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[31]);
        this.maxPathLength$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), 75, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[32]);
        this.maxPathsPerAbstraction$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), 15, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[33]);
        this.pathReconstructionTimeout$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(LongKt.long$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new LongRange(-1, Long.MAX_VALUE), false, 2, (Object) null), 0L, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[34]);
        this.pathReconstructionBatchSize$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), 5, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[35]);
        this.accessPathLength$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), 25, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[36]);
        this.useRecursiveAccessPaths$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[37]);
        this.useThisChainReduction$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[38]);
        this.useSameFieldReduction$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[39]);
        this.disableSparseOpt$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[40]);
        this.maxJoinPointAbstractions$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), -1, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[41]);
        this.maxAbstractionPathLength$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), -1, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[42]);
        this.maxCalleesPerCallSite$delegate = OptionWithValuesKt.default$default(RangeKt.restrictTo$default(IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null), new IntRange(-1, Integer.MAX_VALUE), false, 2, (Object) null), -1, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[43]);
        OptionWithValues $this$enum_u24default$iv9 = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, this.isHidden, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null);
        Enum[] enumArrValues9 = InfoflowConfiguration.DataFlowSolver.values();
        int capacity$iv$iv9 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(enumArrValues9.length), 16);
        Map destination$iv$iv$iv9 = new LinkedHashMap(capacity$iv$iv9);
        for (Enum r09 : enumArrValues9) {
            Enum it$iv9 = r09;
            destination$iv$iv$iv9.put(it$iv9.name(), r09);
        }
        this.dataFlowSolver$delegate = OptionWithValuesKt.default$default(ChoiceKt.choice$default($this$enum_u24default$iv9, destination$iv$iv$iv9, (String) null, true, 2, (Object) null), InfoflowConfiguration.DataFlowSolver.ContextFlowSensitive, (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[44]);
    }

    public /* synthetic */ FlowDroidOptions(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean isHidden() {
        return this.isHidden;
    }

    public final boolean getEnableFlowDroid() {
        return ((Boolean) this.enableFlowDroid$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    private final String getBaseDirectory() {
        return (String) this.baseDirectory$delegate.getValue(this, $$delegatedProperties[1]);
    }

    private final boolean getNoPathAgnosticResults() {
        return ((Boolean) this.noPathAgnosticResults$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    private final boolean getOneResultPerAccessPath() {
        return ((Boolean) this.oneResultPerAccessPath$delegate.getValue(this, $$delegatedProperties[3])).booleanValue();
    }

    private final boolean getMergeNeighbors() {
        return ((Boolean) this.mergeNeighbors$delegate.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    private final int getStopAfterFirstKFlows() {
        return ((Number) this.stopAfterFirstKFlows$delegate.getValue(this, $$delegatedProperties[5])).intValue();
    }

    private final boolean getInspectSources() {
        return ((Boolean) this.inspectSources$delegate.getValue(this, $$delegatedProperties[6])).booleanValue();
    }

    private final boolean getInspectSinks() {
        return ((Boolean) this.inspectSinks$delegate.getValue(this, $$delegatedProperties[7])).booleanValue();
    }

    private final InfoflowConfiguration.ImplicitFlowMode getImplicitFlowMode() {
        return (InfoflowConfiguration.ImplicitFlowMode) this.implicitFlowMode$delegate.getValue(this, $$delegatedProperties[8]);
    }

    private final InfoflowConfiguration.SootIntegrationMode getSootIntegrationMode() {
        return (InfoflowConfiguration.SootIntegrationMode) this.sootIntegrationMode$delegate.getValue(this, $$delegatedProperties[9]);
    }

    private final boolean getDisableFlowSensitiveAliasing() {
        return ((Boolean) this.disableFlowSensitiveAliasing$delegate.getValue(this, $$delegatedProperties[10])).booleanValue();
    }

    private final boolean getDisableExceptionTracking() {
        return ((Boolean) this.disableExceptionTracking$delegate.getValue(this, $$delegatedProperties[11])).booleanValue();
    }

    private final boolean getDisableArrayTracking() {
        return ((Boolean) this.disableArrayTracking$delegate.getValue(this, $$delegatedProperties[12])).booleanValue();
    }

    private final boolean getDisableArraySizeTainting() {
        return ((Boolean) this.disableArraySizeTainting$delegate.getValue(this, $$delegatedProperties[13])).booleanValue();
    }

    private final boolean getDisableTypeChecking() {
        return ((Boolean) this.disableTypeChecking$delegate.getValue(this, $$delegatedProperties[14])).booleanValue();
    }

    private final InfoflowConfiguration.CallgraphAlgorithm getCallgraphAlgorithm() {
        return (InfoflowConfiguration.CallgraphAlgorithm) this.callgraphAlgorithm$delegate.getValue(this, $$delegatedProperties[15]);
    }

    private final InfoflowConfiguration.AliasingAlgorithm getAliasingAlgorithm() {
        return (InfoflowConfiguration.AliasingAlgorithm) this.aliasingAlgorithm$delegate.getValue(this, $$delegatedProperties[16]);
    }

    private final InfoflowConfiguration.DataFlowDirection getDataFlowDirection() {
        return (InfoflowConfiguration.DataFlowDirection) this.dataFlowDirection$delegate.getValue(this, $$delegatedProperties[17]);
    }

    private final boolean getIgnoreFlowsInSystemPackages() {
        return ((Boolean) this.ignoreFlowsInSystemPackages$delegate.getValue(this, $$delegatedProperties[18])).booleanValue();
    }

    private final boolean getWriteOutputFiles() {
        return ((Boolean) this.writeOutputFiles$delegate.getValue(this, $$delegatedProperties[19])).booleanValue();
    }

    private final InfoflowConfiguration.CodeEliminationMode getCodeEliminationMode() {
        return (InfoflowConfiguration.CodeEliminationMode) this.codeEliminationMode$delegate.getValue(this, $$delegatedProperties[20]);
    }

    private final boolean getDisableLogSourcesAndSinks() {
        return ((Boolean) this.disableLogSourcesAndSinks$delegate.getValue(this, $$delegatedProperties[21])).booleanValue();
    }

    private final boolean getEnableReflection() {
        return ((Boolean) this.enableReflection$delegate.getValue(this, $$delegatedProperties[22])).booleanValue();
    }

    private final boolean getDisableLineNumbers() {
        return ((Boolean) this.disableLineNumbers$delegate.getValue(this, $$delegatedProperties[23])).booleanValue();
    }

    private final boolean getDisableTaintAnalysis() {
        return ((Boolean) this.disableTaintAnalysis$delegate.getValue(this, $$delegatedProperties[24])).booleanValue();
    }

    private final boolean getIncrementalResultReporting() {
        return ((Boolean) this.incrementalResultReporting$delegate.getValue(this, $$delegatedProperties[25])).booleanValue();
    }

    private final long getDataFlowTimeout() {
        return ((Number) this.dataFlowTimeout$delegate.getValue(this, $$delegatedProperties[26])).longValue();
    }

    private final boolean getOneSourceAtATime() {
        return ((Boolean) this.oneSourceAtATime$delegate.getValue(this, $$delegatedProperties[27])).booleanValue();
    }

    private final boolean getSequentialPathProcessing() {
        return ((Boolean) this.sequentialPathProcessing$delegate.getValue(this, $$delegatedProperties[28])).booleanValue();
    }

    private final InfoflowConfiguration.PathReconstructionMode getPathReconstructionMode() {
        return (InfoflowConfiguration.PathReconstructionMode) this.pathReconstructionMode$delegate.getValue(this, $$delegatedProperties[29]);
    }

    private final InfoflowConfiguration.PathBuildingAlgorithm getPathBuildingAlgorithm() {
        return (InfoflowConfiguration.PathBuildingAlgorithm) this.pathBuildingAlgorithm$delegate.getValue(this, $$delegatedProperties[30]);
    }

    private final int getMaxCallStackSize() {
        return ((Number) this.maxCallStackSize$delegate.getValue(this, $$delegatedProperties[31])).intValue();
    }

    private final int getMaxPathLength() {
        return ((Number) this.maxPathLength$delegate.getValue(this, $$delegatedProperties[32])).intValue();
    }

    private final int getMaxPathsPerAbstraction() {
        return ((Number) this.maxPathsPerAbstraction$delegate.getValue(this, $$delegatedProperties[33])).intValue();
    }

    private final long getPathReconstructionTimeout() {
        return ((Number) this.pathReconstructionTimeout$delegate.getValue(this, $$delegatedProperties[34])).longValue();
    }

    private final int getPathReconstructionBatchSize() {
        return ((Number) this.pathReconstructionBatchSize$delegate.getValue(this, $$delegatedProperties[35])).intValue();
    }

    private final int getAccessPathLength() {
        return ((Number) this.accessPathLength$delegate.getValue(this, $$delegatedProperties[36])).intValue();
    }

    private final boolean getUseRecursiveAccessPaths() {
        return ((Boolean) this.useRecursiveAccessPaths$delegate.getValue(this, $$delegatedProperties[37])).booleanValue();
    }

    private final boolean getUseThisChainReduction() {
        return ((Boolean) this.useThisChainReduction$delegate.getValue(this, $$delegatedProperties[38])).booleanValue();
    }

    private final boolean getUseSameFieldReduction() {
        return ((Boolean) this.useSameFieldReduction$delegate.getValue(this, $$delegatedProperties[39])).booleanValue();
    }

    private final boolean getDisableSparseOpt() {
        return ((Boolean) this.disableSparseOpt$delegate.getValue(this, $$delegatedProperties[40])).booleanValue();
    }

    private final int getMaxJoinPointAbstractions() {
        return ((Number) this.maxJoinPointAbstractions$delegate.getValue(this, $$delegatedProperties[41])).intValue();
    }

    private final int getMaxAbstractionPathLength() {
        return ((Number) this.maxAbstractionPathLength$delegate.getValue(this, $$delegatedProperties[42])).intValue();
    }

    private final int getMaxCalleesPerCallSite() {
        return ((Number) this.maxCalleesPerCallSite$delegate.getValue(this, $$delegatedProperties[43])).intValue();
    }

    private final InfoflowConfiguration.DataFlowSolver getDataFlowSolver() {
        return (InfoflowConfiguration.DataFlowSolver) this.dataFlowSolver$delegate.getValue(this, $$delegatedProperties[44]);
    }

    private final void initializeGlobalStaticCommandLineOptions() {
        InfoflowConfiguration.setBaseDirectory(getBaseDirectory());
        InfoflowConfiguration.setOneResultPerAccessPath(getOneResultPerAccessPath());
        InfoflowConfiguration.setMergeNeighbors(getMergeNeighbors());
    }

    public final void configureInfoFlowConfig(@NotNull InfoflowConfiguration infoFlowConfig, @NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(infoFlowConfig, "infoFlowConfig");
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        initializeGlobalStaticCommandLineOptions();
        infoFlowConfig.setOneSourceAtATime(getOneSourceAtATime());
        infoFlowConfig.setStopAfterFirstKFlows(getStopAfterFirstKFlows());
        infoFlowConfig.setInspectSources(getInspectSources());
        infoFlowConfig.setInspectSinks(getInspectSinks());
        infoFlowConfig.setImplicitFlowMode(getImplicitFlowMode());
        infoFlowConfig.setStaticFieldTrackingMode(FlowDroidOptionsKt.getCvt(mainConfig.getStaticFieldTrackingMode()));
        infoFlowConfig.setSootIntegrationMode(getSootIntegrationMode());
        infoFlowConfig.setFlowSensitiveAliasing(!getDisableFlowSensitiveAliasing());
        infoFlowConfig.setEnableExceptionTracking(!getDisableExceptionTracking());
        infoFlowConfig.setEnableArrayTracking(!getDisableArrayTracking());
        infoFlowConfig.setEnableArraySizeTainting(!getDisableArraySizeTainting());
        infoFlowConfig.setCallgraphAlgorithm(getCallgraphAlgorithm());
        infoFlowConfig.setAliasingAlgorithm(getAliasingAlgorithm());
        infoFlowConfig.setDataFlowDirection(getDataFlowDirection());
        infoFlowConfig.setEnableTypeChecking(!getDisableTypeChecking());
        infoFlowConfig.setIgnoreFlowsInSystemPackages(getIgnoreFlowsInSystemPackages());
        infoFlowConfig.setExcludeSootLibraryClasses(mainConfig.getApponly());
        infoFlowConfig.setMaxThreadNum(mainConfig.getParallelsNum());
        infoFlowConfig.setWriteOutputFiles(getWriteOutputFiles());
        infoFlowConfig.setCodeEliminationMode(getCodeEliminationMode());
        infoFlowConfig.setLogSourcesAndSinks(!getDisableLogSourcesAndSinks());
        infoFlowConfig.setEnableReflection(getEnableReflection());
        infoFlowConfig.setEnableLineNumbers(!getDisableLineNumbers());
        infoFlowConfig.setEnableOriginalNames(true);
        infoFlowConfig.setTaintAnalysisEnabled(!getDisableTaintAnalysis());
        infoFlowConfig.setIncrementalResultReporting(getIncrementalResultReporting());
        infoFlowConfig.setDataFlowTimeout(getDataFlowTimeout());
        infoFlowConfig.setMemoryThreshold(mainConfig.getMemoryThreshold());
        infoFlowConfig.setPathAgnosticResults(!getNoPathAgnosticResults());
        InfoflowConfiguration.PathConfiguration pathConfiguration = infoFlowConfig.getPathConfiguration();
        pathConfiguration.setSequentialPathProcessing(getSequentialPathProcessing());
        pathConfiguration.setPathReconstructionMode(getPathReconstructionMode());
        pathConfiguration.setPathBuildingAlgorithm(getPathBuildingAlgorithm());
        pathConfiguration.setMaxCallStackSize(getMaxCallStackSize());
        pathConfiguration.setMaxPathLength(getMaxPathLength());
        pathConfiguration.setMaxPathsPerAbstraction(getMaxPathsPerAbstraction());
        pathConfiguration.setPathReconstructionTimeout(getPathReconstructionTimeout());
        pathConfiguration.setPathReconstructionBatchSize(getPathReconstructionBatchSize());
        InfoflowConfiguration.AccessPathConfiguration accessPathConfiguration = infoFlowConfig.getAccessPathConfiguration();
        accessPathConfiguration.setAccessPathLength(getAccessPathLength());
        accessPathConfiguration.setUseRecursiveAccessPaths(getUseRecursiveAccessPaths());
        accessPathConfiguration.setUseThisChainReduction(getUseThisChainReduction());
        accessPathConfiguration.setUseSameFieldReduction(getUseSameFieldReduction());
        InfoflowConfiguration.SolverConfiguration solverConfiguration = infoFlowConfig.getSolverConfiguration();
        solverConfiguration.setMaxJoinPointAbstractions(getMaxJoinPointAbstractions());
        solverConfiguration.setMaxAbstractionPathLength(getMaxAbstractionPathLength());
        solverConfiguration.setMaxCalleesPerCallSite(getMaxCalleesPerCallSite());
        solverConfiguration.setDataFlowSolver(getDataFlowSolver());
    }

    @NotNull
    public final InfoflowConfigurationExt getExtInfoFlowConfig() {
        InfoflowConfigurationExt $this$getExtInfoFlowConfig_u24lambda_u241 = new InfoflowConfigurationExt(false, null, 3, null);
        $this$getExtInfoFlowConfig_u24lambda_u241.setUseSparseOpt(!getDisableSparseOpt());
        return $this$getExtInfoFlowConfig_u24lambda_u241;
    }
}
