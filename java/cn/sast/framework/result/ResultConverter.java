package cn.sast.framework.result;

import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.Report;
import cn.sast.dataflow.infoflow.provider.BugTypeProvider;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.engine.PreAnalysisReportEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.infoflow.data.AccessPath;
import soot.jimple.infoflow.data.AccessPathFragment;
import soot.jimple.infoflow.results.DataFlowResult;
import soot.jimple.infoflow.results.ResultSinkInfo;
import soot.jimple.infoflow.results.ResultSourceInfo;
import soot.jimple.infoflow.solver.cfg.IInfoflowCFG;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinition;
import soot.jimple.infoflow.sourcesSinks.definitions.MethodSourceSinkDefinition;
import soot.tagkit.AbstractHost;

/* compiled from: ResultConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u001c2\u00020\u0001:\u0001\u001cB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001d"}, d2 = {"Lcn/sast/framework/result/ResultConverter;", "", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "writeAccessPath", "", "accessPath", "Lsoot/jimple/infoflow/data/AccessPath;", "simple", "", "getReport", "Lcn/sast/api/report/Report;", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcn/sast/framework/engine/PreAnalysisReportEnv;", "", "icfg", "Lsoot/jimple/infoflow/solver/cfg/IInfoflowCFG;", "result", "Lsoot/jimple/infoflow/results/DataFlowResult;", "bugTypeProvider", "Lcn/sast/dataflow/infoflow/provider/BugTypeProvider;", "serializeTaintPath", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nResultConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResultConverter.kt\ncn/sast/framework/result/ResultConverter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,110:1\n1557#2:111\n1628#2,2:112\n1630#2:115\n1557#2:116\n1628#2,3:117\n1#3:114\n*S KotlinDebug\n*F\n+ 1 ResultConverter.kt\ncn/sast/framework/result/ResultConverter\n*L\n78#1:111\n78#1:112,2\n78#1:115\n106#1:116\n106#1:117,3\n*E\n"})
/* loaded from: ResultConverter.class */
public final class ResultConverter {

    @Nullable
    private final SootInfoCache info;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ResultConverter::logger$lambda$9);

    /* compiled from: ResultConverter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/result/ResultConverter$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: ResultConverter$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    public ResultConverter(@Nullable SootInfoCache info) {
        this.info = info;
    }

    @Nullable
    public final SootInfoCache getInfo() {
        return this.info;
    }

    private static final Unit logger$lambda$9() {
        return Unit.INSTANCE;
    }

    static /* synthetic */ String writeAccessPath$default(ResultConverter resultConverter, AccessPath accessPath, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return resultConverter.writeAccessPath(accessPath, z);
    }

    private final String writeAccessPath(AccessPath accessPath, boolean simple) {
        if (!simple) {
            String string = accessPath.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
        StringBuilder b = new StringBuilder();
        if (accessPath.getPlainValue() != null) {
            b.append(accessPath.getPlainValue().toString());
        }
        if (accessPath.getBaseType() != null) {
            b.append(accessPath.getBaseType().toString());
        }
        if (accessPath.getFragmentCount() > 0) {
            int fragmentCount = accessPath.getFragmentCount();
            for (int i = 0; i < fragmentCount; i++) {
                AccessPathFragment fragment = accessPath.getFragments()[i];
                b.append(".").append(fragment.getField().toString());
            }
        }
        b.append(accessPath.getTaintSubFields() ? "*" : "");
        String string2 = b.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }

    @NotNull
    public final Report getReport(@NotNull CheckType checkType, @NotNull PreAnalysisReportEnv env) {
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(env, "env");
        return Report.Companion.of$default(Report.Companion, this.info, env.getFile(), env.getEnv().getRegion().getImmutable(), checkType, env.getEnv(), null, 32, null);
    }

    public static /* synthetic */ List getReport$default(ResultConverter resultConverter, IInfoflowCFG iInfoflowCFG, DataFlowResult dataFlowResult, BugTypeProvider bugTypeProvider, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return resultConverter.getReport(iInfoflowCFG, dataFlowResult, bugTypeProvider, z);
    }

    @NotNull
    public final List<Report> getReport(@NotNull IInfoflowCFG icfg, @NotNull DataFlowResult result, @NotNull BugTypeProvider bugTypeProvider, boolean serializeTaintPath) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(bugTypeProvider, "bugTypeProvider");
        ResultSinkInfo sink = result.getSink();
        Stmt stmt = sink.getStmt();
        AbstractHost abstractHost = (SootMethod) icfg.getMethodOf(sink.getStmt());
        MethodSourceSinkDefinition definition = sink.getDefinition();
        if (!(definition instanceof MethodSourceSinkDefinition)) {
            logger.warn(() -> {
                return getReport$lambda$0(r1);
            });
            return CollectionsKt.emptyList();
        }
        SootMethod sinkMethod = Scene.v().grabMethod(definition.getMethod().getSignature());
        if (sinkMethod == null) {
            logger.warn(() -> {
                return getReport$lambda$1(r1);
            });
            return CollectionsKt.emptyList();
        }
        ResultSourceInfo source = result.getSource();
        if (!serializeTaintPath || source.getPath() == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            Stmt[] path = source.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            Iterable $this$map$iv = ArraysKt.getIndices(path);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            IntIterator it = $this$map$iv.iterator();
            while (it.hasNext()) {
                int item$iv$iv = it.nextInt();
                soot.Unit unit = source.getPath()[item$iv$iv];
                Intrinsics.checkNotNullExpressionValue(unit, "get(...)");
                AbstractHost abstractHost2 = (SootMethod) icfg.getMethodOf(unit);
                AccessPath curAP = source.getPathAccessPaths()[item$iv$iv];
                Intrinsics.checkNotNullExpressionValue(curAP, "get(...)");
                String ap = writeAccessPath$default(this, curAP, false, 2, null);
                Region region = Region.Companion.invoke(unit);
                if (region == null) {
                    SootInfoCache it2 = this.info;
                    if (it2 != null) {
                        Region.Companion companion = Region.Companion;
                        Intrinsics.checkNotNull(abstractHost2);
                        region = companion.invoke(it2, abstractHost2);
                    } else {
                        region = null;
                    }
                    if (region == null) {
                        region = Region.Companion.getERROR();
                    }
                }
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to(Language.EN, "`" + ap + "` is tainted after `" + unit + "`"));
                ClassResInfo.Companion companion2 = ClassResInfo.Companion;
                Intrinsics.checkNotNull(abstractHost2);
                destination$iv$iv.add(new BugPathEvent(mapMapOf, companion2.of((SootMethod) abstractHost2), region, null, 8, null));
            }
            listEmptyList = (List) destination$iv$iv;
        }
        List events = listEmptyList;
        Set checkTypes = bugTypeProvider.lookUpCheckType(sinkMethod);
        if (checkTypes.isEmpty()) {
            logger.warn(() -> {
                return getReport$lambda$4(r1);
            });
        }
        Region.Companion companion3 = Region.Companion;
        Intrinsics.checkNotNull(stmt);
        Region regionInvoke = companion3.invoke((soot.Unit) stmt);
        if (regionInvoke == null) {
            SootInfoCache it3 = this.info;
            if (it3 != null) {
                Region.Companion companion4 = Region.Companion;
                Intrinsics.checkNotNull(abstractHost);
                regionInvoke = companion4.invoke(it3, abstractHost);
            } else {
                regionInvoke = null;
            }
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.getERROR();
            }
        }
        Region region2 = regionInvoke;
        DefaultEnv it4 = new DefaultEnv(region2.getMutable(), null, null, null, null, null, null, null, null, 510, null);
        it4.setCallSite((soot.Unit) stmt);
        it4.setClazz(abstractHost.getDeclaringClass());
        it4.setContainer(abstractHost);
        it4.setCallee(sinkMethod);
        it4.setMethod(sinkMethod);
        Stmt stmt2 = Boolean.valueOf(stmt.containsInvokeExpr()).booleanValue() ? stmt : null;
        it4.setInvokeExpr(stmt2 != null ? stmt2.getInvokeExpr() : null);
        Set<CheckType> $this$map$iv2 = checkTypes;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (CheckType type : $this$map$iv2) {
            Report.Companion companion5 = Report.Companion;
            SootInfoCache sootInfoCache = this.info;
            ClassResInfo.Companion companion6 = ClassResInfo.Companion;
            Intrinsics.checkNotNull(abstractHost);
            destination$iv$iv2.add(companion5.of(sootInfoCache, companion6.of((SootMethod) abstractHost), region2, type, it4, events));
        }
        return (List) destination$iv$iv2;
    }

    private static final Object getReport$lambda$0(ISourceSinkDefinition $definition) {
        return "Definition: " + $definition + " is not a MethodSourceSinkDefinition.";
    }

    private static final Object getReport$lambda$1(ISourceSinkDefinition $definition) {
        return "Soot can not find method: " + $definition;
    }

    private static final Object getReport$lambda$4(SootMethod $sinkMethod) {
        return "could not find any checkTypes from bugTypeProvider at sink method: " + $sinkMethod;
    }
}
