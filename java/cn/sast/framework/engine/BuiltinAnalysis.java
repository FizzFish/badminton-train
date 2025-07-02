package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.FileResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.Report;
import cn.sast.api.util.OthersKt;
import cn.sast.common.Resource;
import cn.sast.dataflow.analysis.IBugReporter;
import cn.sast.dataflow.analysis.unused.UnusedDetector;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.IBuiltInAnalysisCollector;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.cache.AnalysisDataFactory;
import com.feysh.corax.cache.analysis.SootHostExtend;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.report.Region;
import com.github.javaparser.ast.body.BodyDeclaration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.tagkit.AbstractHost;
import soot.tagkit.Host;

/* compiled from: BuiltinAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� \u00192\u00020\u0001:\u0003\u0017\u0018\u0019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J.\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcn/sast/framework/engine/BuiltinAnalysis;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "analyzeInScene", "", "soot", "Lcn/sast/framework/SootCtx;", "resultCollector", "Lcn/sast/framework/result/IBuiltInAnalysisCollector;", "(Lcn/sast/framework/SootCtx;Lcn/sast/framework/result/IBuiltInAnalysisCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "allMethodsAnalyzeInScene", "builtinAnalysisConfig", "Lcn/sast/api/config/BuiltinAnalysisConfig;", "allMethodsProvider", "Lcn/sast/framework/engine/BuiltinAnalysis$CHA-AllMethodsProvider;", "(Lcn/sast/framework/SootCtx;Lcn/sast/api/config/BuiltinAnalysisConfig;Lcn/sast/framework/engine/BuiltinAnalysis$CHA-AllMethodsProvider;Lcn/sast/framework/result/IBuiltInAnalysisCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "BugReporter", "CHA-AllMethodsProvider", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nBuiltinAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BuiltinAnalysis.kt\ncn/sast/framework/engine/BuiltinAnalysis\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,185:1\n774#2:186\n865#2,2:187\n1368#2:189\n1454#2,2:190\n774#2:192\n865#2,2:193\n1456#2,3:195\n49#3,24:198\n49#3,24:222\n*S KotlinDebug\n*F\n+ 1 BuiltinAnalysis.kt\ncn/sast/framework/engine/BuiltinAnalysis\n*L\n115#1:186\n115#1:187,2\n116#1:189\n116#1:190,2\n116#1:192\n116#1:193,2\n116#1:195,3\n120#1:198,24\n168#1:222,24\n*E\n"})
/* loaded from: BuiltinAnalysis.class */
public final class BuiltinAnalysis {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final SootInfoCache info;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(BuiltinAnalysis::logger$lambda$6);

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "BuiltinAnalysis.kt", l = {171}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "allMethodsAnalyzeInScene", c = "cn.sast.framework.engine.BuiltinAnalysis")
    /* renamed from: cn.sast.framework.engine.BuiltinAnalysis$allMethodsAnalyzeInScene$1, reason: invalid class name */
    /* loaded from: BuiltinAnalysis$allMethodsAnalyzeInScene$1.class */
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
            return BuiltinAnalysis.this.allMethodsAnalyzeInScene(null, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "BuiltinAnalysis.kt", l = {121}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv"}, m = "analyzeInScene", c = "cn.sast.framework.engine.BuiltinAnalysis")
    /* renamed from: cn.sast.framework.engine.BuiltinAnalysis$analyzeInScene$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: BuiltinAnalysis$analyzeInScene$1.class */
    static final class C00171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00171(Continuation<? super C00171> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BuiltinAnalysis.this.analyzeInScene(null, null, (Continuation) this);
        }
    }

    public BuiltinAnalysis(@NotNull MainConfig mainConfig, @NotNull SootInfoCache info) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(info, "info");
        this.mainConfig = mainConfig;
        this.info = info;
    }

    @NotNull
    public final SootInfoCache getInfo() {
        return this.info;
    }

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b��\u0018��2\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J9\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0002J9\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0016J9\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0016J9\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0016J1\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010 \u001a\u00020!2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0016J1\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\u0015H\u0016J\u001b\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010%*\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0096\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0012\u0010(\u001a\u00020)X\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u0004\u0018\u00010-*\u00020.8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b/\u00100R\u001e\u00101\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010-02j\u0002`3X\u0096\u0005¢\u0006\u0006\u001a\u0004\b4\u00105R\u0018\u00106\u001a\u00020'*\u0002078VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b8\u00109R\u0018\u0010:\u001a\u00020'*\u0002078VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b;\u00109R\u0018\u0010<\u001a\u00020'*\u0002078VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b=\u00109R\u0018\u0010>\u001a\u00020'*\u0002078VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b?\u00109¨\u0006@"}, d2 = {"Lcn/sast/framework/engine/BuiltinAnalysis$BugReporter;", "Lcn/sast/dataflow/analysis/IBugReporter;", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "resultCollector", "Lcn/sast/framework/result/IBuiltInAnalysisCollector;", "info", "<init>", "(Lcn/sast/framework/result/IBuiltInAnalysisCollector;Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "finalReport", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "file", "Lcn/sast/api/report/IBugResInfo;", "defaultEnv", "Lcn/sast/api/report/DefaultEnv;", "env", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/BugMessage$Env;", "Lkotlin/ExtensionFunctionType;", "report", "ct", "clazz", "Lsoot/SootClass;", "unit", "Lsoot/Unit;", "atClass", "region", "Lcom/feysh/corax/config/api/report/Region;", "Ljava/nio/file/Path;", "field", "Lsoot/SootField;", "method", "Lsoot/SootMethod;", "getMemberAtLine", "Lcom/github/javaparser/ast/body/BodyDeclaration;", "ln", "", "cache", "Lcom/feysh/corax/cache/AnalysisCache;", "getCache", "()Lcom/feysh/corax/cache/AnalysisCache;", "ext", "Lcom/feysh/corax/cache/analysis/SootHostExtend;", "Lsoot/tagkit/Host;", "getExt", "(Lsoot/tagkit/Host;)Lcom/feysh/corax/cache/analysis/SootHostExtend;", "hostKey", "Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "Lcom/feysh/corax/cache/analysis/SootHostExtInfoFKey;", "getHostKey", "()Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "javaNameSourceEndColumnNumber", "Lsoot/tagkit/AbstractHost;", "getJavaNameSourceEndColumnNumber", "(Lsoot/tagkit/AbstractHost;)I", "javaNameSourceEndLineNumber", "getJavaNameSourceEndLineNumber", "javaNameSourceStartColumnNumber", "getJavaNameSourceStartColumnNumber", "javaNameSourceStartLineNumber", "getJavaNameSourceStartLineNumber", "corax-framework"})
    /* loaded from: BuiltinAnalysis$BugReporter.class */
    public static final class BugReporter implements IBugReporter, SootInfoCache {

        @NotNull
        private final IBuiltInAnalysisCollector resultCollector;

        @NotNull
        private final SootInfoCache info;

        @Nullable
        public BodyDeclaration<?> getMemberAtLine(@NotNull SootClass $this$getMemberAtLine, int ln) {
            Intrinsics.checkNotNullParameter($this$getMemberAtLine, "<this>");
            return this.info.getMemberAtLine($this$getMemberAtLine, ln);
        }

        @NotNull
        public AnalysisCache getCache() {
            return this.info.getCache();
        }

        @NotNull
        public AnalysisDataFactory.Key<SootHostExtend> getHostKey() {
            return this.info.getHostKey();
        }

        @Nullable
        public SootHostExtend getExt(@NotNull Host $this$ext) {
            Intrinsics.checkNotNullParameter($this$ext, "<this>");
            return this.info.getExt($this$ext);
        }

        public int getJavaNameSourceStartLineNumber(@NotNull AbstractHost $this$javaNameSourceStartLineNumber) {
            Intrinsics.checkNotNullParameter($this$javaNameSourceStartLineNumber, "<this>");
            return this.info.getJavaNameSourceStartLineNumber($this$javaNameSourceStartLineNumber);
        }

        public int getJavaNameSourceStartColumnNumber(@NotNull AbstractHost $this$javaNameSourceStartColumnNumber) {
            Intrinsics.checkNotNullParameter($this$javaNameSourceStartColumnNumber, "<this>");
            return this.info.getJavaNameSourceStartColumnNumber($this$javaNameSourceStartColumnNumber);
        }

        public int getJavaNameSourceEndLineNumber(@NotNull AbstractHost $this$javaNameSourceEndLineNumber) {
            Intrinsics.checkNotNullParameter($this$javaNameSourceEndLineNumber, "<this>");
            return this.info.getJavaNameSourceEndLineNumber($this$javaNameSourceEndLineNumber);
        }

        public int getJavaNameSourceEndColumnNumber(@NotNull AbstractHost $this$javaNameSourceEndColumnNumber) {
            Intrinsics.checkNotNullParameter($this$javaNameSourceEndColumnNumber, "<this>");
            return this.info.getJavaNameSourceEndColumnNumber($this$javaNameSourceEndColumnNumber);
        }

        public BugReporter(@NotNull IBuiltInAnalysisCollector resultCollector, @NotNull SootInfoCache info) {
            Intrinsics.checkNotNullParameter(resultCollector, "resultCollector");
            Intrinsics.checkNotNullParameter(info, "info");
            this.resultCollector = resultCollector;
            this.info = info;
        }

        @NotNull
        public final SootInfoCache getInfo() {
            return this.info;
        }

        private final void finalReport(CheckType checkType, IBugResInfo file, DefaultEnv defaultEnv, Function1<? super BugMessage.Env, Unit> function1) {
            defaultEnv.setFileName(file.getReportFileName());
            function1.invoke(defaultEnv);
            this.resultCollector.report(Report.Companion.of$default(Report.Companion, this.info, file, defaultEnv.getRegion().getImmutable(), checkType, defaultEnv, null, 32, null));
        }

        @Override // cn.sast.dataflow.analysis.IBugReporter
        public void report(@NotNull CheckType ct, @NotNull SootClass clazz, @NotNull soot.Unit unit, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(ct, "ct");
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(function1, "env");
            Region regionInvoke = Region.Companion.invoke(this.info, (Host) unit);
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.getERROR();
            }
            report(ct, clazz, regionInvoke, function1);
        }

        @Override // cn.sast.dataflow.analysis.IBugReporter
        public void report(@NotNull CheckType checkType, @NotNull SootClass atClass, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(atClass, "atClass");
            Intrinsics.checkNotNullParameter(region, "region");
            Intrinsics.checkNotNullParameter(function1, "env");
            ClassResInfo file = new ClassResInfo(atClass);
            DefaultEnv defaultEnv = new DefaultEnv(region.getMutable(), null, null, null, null, null, atClass, null, null, 446, null);
            finalReport(checkType, file, defaultEnv, function1);
        }

        @Override // cn.sast.dataflow.analysis.IBugReporter
        public void report(@NotNull CheckType checkType, @NotNull Path file, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(region, "region");
            Intrinsics.checkNotNullParameter(function1, "env");
            FileResInfo fileInfo = new FileResInfo(Resource.INSTANCE.fileOf(file));
            DefaultEnv defaultEnv = new DefaultEnv(region.getMutable(), null, null, null, null, null, null, null, null, 510, null);
            finalReport(checkType, fileInfo, defaultEnv, function1);
        }

        @Override // cn.sast.dataflow.analysis.IBugReporter
        public void report(@NotNull CheckType ct, @NotNull SootField field, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(ct, "ct");
            Intrinsics.checkNotNullParameter(field, "field");
            Intrinsics.checkNotNullParameter(function1, "env");
            SootClass declaringClass = field.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            ClassResInfo file = new ClassResInfo(declaringClass);
            SootClass declaringClass2 = field.getDeclaringClass();
            Region regionInvoke = Region.Companion.invoke(this.info, (AbstractHost) field);
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.getERROR();
            }
            DefaultEnv defaultEnv = new DefaultEnv(regionInvoke.getMutable(), file.getReportFileName(), null, null, null, null, declaringClass2, field, null, 316, null);
            finalReport(ct, file, defaultEnv, function1);
        }

        @Override // cn.sast.dataflow.analysis.IBugReporter
        public void report(@NotNull CheckType ct, @NotNull SootMethod method, @NotNull Function1<? super BugMessage.Env, Unit> function1) {
            Intrinsics.checkNotNullParameter(ct, "ct");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(function1, "env");
            SootClass declaringClass = method.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            ClassResInfo file = new ClassResInfo(declaringClass);
            SootClass declaringClass2 = method.getDeclaringClass();
            Region regionInvoke = Region.Companion.invoke(this.info, (AbstractHost) method);
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.getERROR();
            }
            DefaultEnv defaultEnv = new DefaultEnv(regionInvoke.getMutable(), file.getReportFileName(), null, null, method, null, declaringClass2, null, method, 172, null);
            finalReport(ct, file, defaultEnv, function1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeInScene(@org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r8, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IBuiltInAnalysisCollector r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 937
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.BuiltinAnalysis.analyzeInScene(cn.sast.framework.SootCtx, cn.sast.framework.result.IBuiltInAnalysisCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/engine/BuiltinAnalysis$CHA-AllMethodsProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "classes", "", "Lsoot/SootClass;", "<init>", "(Ljava/util/Collection;)V", "getClasses", "()Ljava/util/Collection;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nBuiltinAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BuiltinAnalysis.kt\ncn/sast/framework/engine/BuiltinAnalysis$CHA-AllMethodsProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,185:1\n827#2:186\n855#2,2:187\n*S KotlinDebug\n*F\n+ 1 BuiltinAnalysis.kt\ncn/sast/framework/engine/BuiltinAnalysis$CHA-AllMethodsProvider\n*L\n140#1:186\n140#1:187,2\n*E\n"})
    /* renamed from: cn.sast.framework.engine.BuiltinAnalysis$CHA-AllMethodsProvider, reason: invalid class name */
    /* loaded from: BuiltinAnalysis$CHA-AllMethodsProvider.class */
    public static final class CHAAllMethodsProvider implements IEntryPointProvider {

        @NotNull
        private final Collection<SootClass> classes;

        @NotNull
        private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

        public CHAAllMethodsProvider() {
            this(null, 1, null);
        }

        @Override // cn.sast.framework.entries.IEntryPointProvider
        public void startAnalyse() {
            IEntryPointProvider.DefaultImpls.startAnalyse(this);
        }

        @Override // cn.sast.framework.entries.IEntryPointProvider
        public void endAnalyse() {
            IEntryPointProvider.DefaultImpls.endAnalyse(this);
        }

        public CHAAllMethodsProvider(@NotNull Collection<? extends SootClass> collection) {
            Intrinsics.checkNotNullParameter(collection, "classes");
            this.classes = collection;
            this.iterator = FlowKt.flow(new BuiltinAnalysis$CHAAllMethodsProvider$iterator$1(this, null));
        }

        public /* synthetic */ CHAAllMethodsProvider(Collection collection, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                Iterable applicationClasses = Scene.v().getApplicationClasses();
                Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
                Iterable $this$filterNot$iv = applicationClasses;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterNot$iv) {
                    SootClass it = (SootClass) element$iv$iv;
                    Intrinsics.checkNotNull(it);
                    if (!OthersKt.isSyntheticComponent(it)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                collection = (List) destination$iv$iv;
            }
            this(collection);
        }

        @NotNull
        public final Collection<SootClass> getClasses() {
            return this.classes;
        }

        @Override // cn.sast.framework.entries.IEntryPointProvider
        @NotNull
        public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
            return this.iterator;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object allMethodsAnalyzeInScene(@org.jetbrains.annotations.NotNull cn.sast.framework.SootCtx r8, @org.jetbrains.annotations.NotNull cn.sast.api.config.BuiltinAnalysisConfig r9, @org.jetbrains.annotations.NotNull cn.sast.framework.engine.BuiltinAnalysis.CHAAllMethodsProvider r10, @org.jetbrains.annotations.NotNull cn.sast.framework.result.IBuiltInAnalysisCollector r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.BuiltinAnalysis.allMethodsAnalyzeInScene(cn.sast.framework.SootCtx, cn.sast.api.config.BuiltinAnalysisConfig, cn.sast.framework.engine.BuiltinAnalysis$CHA-AllMethodsProvider, cn.sast.framework.result.IBuiltInAnalysisCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object allMethodsAnalyzeInScene$lambda$5$lambda$4() {
        return "Please file this bug to us";
    }

    /* compiled from: BuiltinAnalysis.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lcn/sast/framework/engine/BuiltinAnalysis$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "isEnableAllMethodsAnalyzeInScene", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "corax-framework"})
    /* loaded from: BuiltinAnalysis$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final boolean isEnableAllMethodsAnalyzeInScene(@NotNull MainConfig mainConfig) {
            Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
            return UnusedDetector.Companion.isEnable(mainConfig);
        }
    }

    private static final Unit logger$lambda$6() {
        return Unit.INSTANCE;
    }
}
