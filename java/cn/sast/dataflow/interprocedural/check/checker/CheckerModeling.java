package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.MainConfigKt;
import cn.sast.common.GLB;
import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.dataflow.util.ConfigInfoLogger;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.InterproceduralCFG;
import cn.sast.idfa.check.CallBackManager;
import com.feysh.corax.cache.coroutines.FastCache;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IAnalysisDepends;
import com.feysh.corax.config.api.IBoolExpr;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IJDecl;
import com.feysh.corax.config.api.IMethodDecl;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.MethodConfig;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootMethod;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0006\u0018�� K2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u00020\u00042\u00020\u0005:\u0002KLB\u001f\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u001c\u001a\u00020\u001d*\u00100\u001ej\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\u001fH\u0016J9\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.2\u001f\u0010/\u001a\u001b\u0012\b\u0012\u00060%j\u0002`&\u0012\u0004\u0012\u00020\u001d0$j\u0002`(¢\u0006\u0002\b'2\u0006\u00100\u001a\u00020)H\u0016JZ\u00101\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.2\u001f\u0010/\u001a\u001b\u0012\b\u0012\u00060%j\u0002`&\u0012\u0004\u0012\u00020\u001d0$j\u0002`(¢\u0006\u0002\b'2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u001d0$¢\u0006\u0002\b'H\u0016J\\\u00108\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.2\u001f\u0010/\u001a\u001b\u0012\b\u0012\u00060%j\u0002`&\u0012\u0004\u0012\u00020\u001d0$j\u0002`(¢\u0006\u0002\b'2\u0006\u00102\u001a\u0002092!\u0010:\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u00020\u001d0$H\u0016J\b\u0010?\u001a\u00020\u001dH\u0016J\u0015\u0010D\u001a\u00020\u001d*\u00020E2\u0006\u0010F\u001a\u00020EH\u0096\u0005J!\u0010D\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020E0G2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020E0GH\u0096\u0005J\u0011\u0010I\u001a\u00020E2\u0006\u0010J\u001a\u00020;H\u0096\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR?\u0010 \u001a3\u0012/\u0012-\u0012\u0004\u0012\u00020#\u0012\u001d\u0012\u001b\u0012\b\u0012\u00060%j\u0002`&\u0012\u0004\u0012\u00020\u001d0$j\u0002`(¢\u0006\u0002\b'\u0012\u0004\u0012\u00020)0\"0!X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020+0!X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010@\u001a\u00020AX\u0096\u0004¢\u0006\b\n��\u001a\u0004\bB\u0010C¨\u0006M"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;", "Lcom/feysh/corax/config/api/IAnalysisDepends;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "preAnalysis", "Lcom/feysh/corax/config/api/PreAnalysisApi;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/idfa/analysis/InterproceduralCFG;Lcom/feysh/corax/config/api/PreAnalysisApi;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "getPreAnalysis", "()Lcom/feysh/corax/config/api/PreAnalysisApi;", "fastCache", "Lcom/feysh/corax/cache/coroutines/FastCache;", "getFastCache", "()Lcom/feysh/corax/cache/coroutines/FastCache;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "summaries", "", "Lkotlin/Triple;", "Lsoot/SootMethod;", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/MethodConfig;", "Lcom/feysh/corax/config/api/MethodConfigType;", "Lkotlin/ExtensionFunctionType;", "Lcom/feysh/corax/config/api/MethodConfigBlockType;", "Lcom/feysh/corax/config/api/IStmt;", "checkPoints", "Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "addStmt", "decl", "Lcom/feysh/corax/config/api/IJDecl;", "config", "stmt", "check", "expr", "Lcom/feysh/corax/config/api/IBoolExpr;", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "eval", "Lcom/feysh/corax/config/api/IExpr;", "accept", "", "Lkotlin/ParameterName;", "name", "value", "validate", "error", "Lcn/sast/dataflow/util/ConfigInfoLogger;", "getError", "()Lcn/sast/dataflow/util/ConfigInfoLogger;", "dependsOn", "Lcom/feysh/corax/config/api/XDecl;", "dep", "", "deps", "toDecl", "target", "Companion", "Checker", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nCheckerModeling.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerModeling.kt\ncn/sast/dataflow/interprocedural/check/checker/CheckerModeling\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CheckerManager.kt\ncn/sast/idfa/check/CallBackManager\n*L\n1#1,565:1\n1863#2:566\n1864#2:585\n1863#2:586\n1864#2:605\n1863#2,2:606\n1863#2,2:608\n83#3,3:567\n83#3,3:570\n83#3,3:573\n83#3,3:576\n83#3,3:579\n83#3,3:582\n83#3,3:587\n83#3,3:590\n83#3,3:593\n83#3,3:596\n83#3,3:599\n83#3,3:602\n*S KotlinDebug\n*F\n+ 1 CheckerModeling.kt\ncn/sast/dataflow/interprocedural/check/checker/CheckerModeling\n*L\n392#1:566\n392#1:585\n436#1:586\n436#1:605\n500#1:606,2\n526#1:608,2\n399#1:567,3\n405#1:570,3\n411#1:573,3\n416#1:576,3\n422#1:579,3\n427#1:582,3\n443#1:587,3\n449#1:590,3\n455#1:593,3\n460#1:596,3\n466#1:599,3\n471#1:602,3\n*E\n"})
/* loaded from: CheckerModeling.class */
public final class CheckerModeling extends AIAnalysisBaseImpl implements SummaryHandlePackage<IValue>, IAnalysisDepends {
    private final /* synthetic */ IAnalysisDepends $$delegate_0;

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final InterproceduralCFG icfg;

    @NotNull
    private final PreAnalysisApi preAnalysis;

    @NotNull
    private final List<Triple<SootMethod, Function1<MethodConfig, Unit>, IStmt>> summaries;

    @NotNull
    private final List<Checker> checkPoints;

    @NotNull
    private final ConfigInfoLogger error;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckerModeling::logger$lambda$10);

    /* compiled from: CheckerModeling.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: CheckerModeling$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MethodConfig.CheckCall.values().length];
            try {
                iArr[MethodConfig.CheckCall.PrevCallInCaller.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MethodConfig.CheckCall.EvalCallInCaller.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MethodConfig.CheckCall.PostCallInCaller.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[MethodConfig.CheckCall.PrevCallInCallee.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[MethodConfig.CheckCall.EvalCallInCallee.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[MethodConfig.CheckCall.PostCallInCallee.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public XDecl toDecl(@NotNull Object target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return this.$$delegate_0.toDecl(target);
    }

    public void dependsOn(@NotNull XDecl $this$dependsOn, @NotNull XDecl dep) {
        Intrinsics.checkNotNullParameter($this$dependsOn, "<this>");
        Intrinsics.checkNotNullParameter(dep, "dep");
        this.$$delegate_0.dependsOn($this$dependsOn, dep);
    }

    public void dependsOn(@NotNull Collection<? extends XDecl> collection, @NotNull Collection<? extends XDecl> collection2) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(collection2, "deps");
        this.$$delegate_0.dependsOn(collection, collection2);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final InterproceduralCFG getIcfg() {
        return this.icfg;
    }

    @NotNull
    public PreAnalysisApi getPreAnalysis() {
        return this.preAnalysis;
    }

    /* compiled from: CheckerModeling.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: CheckerModeling$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    public CheckerModeling(@NotNull MainConfig mainConfig, @NotNull InterproceduralCFG icfg, @NotNull PreAnalysisApi preAnalysis) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        Intrinsics.checkNotNullParameter(preAnalysis, "preAnalysis");
        this.$$delegate_0 = MainConfigKt.interProceduralAnalysisDepends(mainConfig);
        this.mainConfig = mainConfig;
        this.icfg = icfg;
        this.preAnalysis = preAnalysis;
        this.summaries = new ArrayList();
        this.checkPoints = new ArrayList();
        this.error = new ConfigInfoLogger();
    }

    private static final Unit logger$lambda$10() {
        return Unit.INSTANCE;
    }

    @NotNull
    public FastCache getFastCache() {
        return FastCacheImpl.INSTANCE;
    }

    @NotNull
    public CoroutineScope getScope() {
        return GlobalScope.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        getLogger().info(() -> {
            return register$lambda$0(r1);
        });
        Iterable $this$forEach$iv = this.summaries;
        for (Object element$iv : $this$forEach$iv) {
            Triple triple = (Triple) element$iv;
            SootMethod method = (SootMethod) triple.component1();
            Function1 config = (Function1) triple.component2();
            IStmt stmt = (IStmt) triple.component3();
            MethodConfig imp = new MethodConfig(MethodConfig.CheckCall.PostCallInCaller);
            config.invoke(imp);
            ModelingCallBack cb = new ModelingCallBack(method, stmt);
            switch (WhenMappings.$EnumSwitchMapping$0[imp.getAt().ordinal()]) {
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    CallBackManager this_$iv = $this$register.getCallBackManager();
                    this_$iv.put(CallerSiteCBImpl.PrevCall.class, method, new CheckerModeling$register$2$1(cb, $this$register, null));
                    break;
                case 2:
                    CallBackManager this_$iv2 = $this$register.getCallBackManager();
                    this_$iv2.put(CallerSiteCBImpl.EvalCall.class, method, new CheckerModeling$register$2$2(cb, $this$register, null));
                    break;
                case 3:
                    CallBackManager this_$iv3 = $this$register.getCallBackManager();
                    this_$iv3.put(CallerSiteCBImpl.PostCall.class, method, new CheckerModeling$register$2$3(cb, $this$register, null));
                    break;
                case 4:
                    CallBackManager this_$iv4 = $this$register.getCallBackManager();
                    this_$iv4.put(CalleeCBImpl.PrevCall.class, method, new CheckerModeling$register$2$4(cb, $this$register, null));
                    break;
                case 5:
                    CallBackManager this_$iv5 = $this$register.getCallBackManager();
                    this_$iv5.put(CalleeCBImpl.EvalCall.class, method, new CheckerModeling$register$2$5(cb, $this$register, null));
                    break;
                case 6:
                    CallBackManager this_$iv6 = $this$register.getCallBackManager();
                    this_$iv6.put(CalleeCBImpl.PostCall.class, method, new CheckerModeling$register$2$6(cb, $this$register, null));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        getLogger().info(() -> {
            return register$lambda$2(r1);
        });
        Iterable $this$forEach$iv2 = this.checkPoints;
        for (Object element$iv2 : $this$forEach$iv2) {
            Checker checker = (Checker) element$iv2;
            MethodConfig imp2 = new MethodConfig(MethodConfig.CheckCall.PostCallInCaller);
            checker.getConfig().invoke(imp2);
            CheckCallBack cb2 = new CheckCallBack(checker.getAtMethod(), checker);
            SootMethod method2 = checker.getAtMethod();
            switch (WhenMappings.$EnumSwitchMapping$0[imp2.getAt().ordinal()]) {
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    CallBackManager this_$iv7 = $this$register.getCallBackManager();
                    this_$iv7.put(CallerSiteCBImpl.PrevCall.class, method2, new CheckerModeling$register$4$1(cb2, $this$register, null));
                    break;
                case 2:
                    CallBackManager this_$iv8 = $this$register.getCallBackManager();
                    this_$iv8.put(CallerSiteCBImpl.EvalCall.class, method2, new CheckerModeling$register$4$2(cb2, $this$register, null));
                    break;
                case 3:
                    CallBackManager this_$iv9 = $this$register.getCallBackManager();
                    this_$iv9.put(CallerSiteCBImpl.PostCall.class, method2, new CheckerModeling$register$4$3(cb2, $this$register, null));
                    break;
                case 4:
                    CallBackManager this_$iv10 = $this$register.getCallBackManager();
                    this_$iv10.put(CalleeCBImpl.PrevCall.class, method2, new CheckerModeling$register$4$4(cb2, $this$register, null));
                    break;
                case 5:
                    CallBackManager this_$iv11 = $this$register.getCallBackManager();
                    this_$iv11.put(CalleeCBImpl.EvalCall.class, method2, new CheckerModeling$register$4$5(cb2, $this$register, null));
                    break;
                case 6:
                    CallBackManager this_$iv12 = $this$register.getCallBackManager();
                    this_$iv12.put(CalleeCBImpl.PostCall.class, method2, new CheckerModeling$register$4$6(cb2, $this$register, null));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    private static final Object register$lambda$0(CheckerModeling this$0) {
        return "summaries model size: " + this$0.summaries.size();
    }

    private static final Object register$lambda$2(CheckerModeling this$0) {
        return "check-points size: " + this$0.checkPoints.size();
    }

    /* compiled from: CheckerModeling.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001f\u0010\u0004\u001a\u001b\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005j\u0002`\n¢\u0006\u0002\b\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\t¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\"\u0010\u001d\u001a\u001b\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005j\u0002`\n¢\u0006\u0002\b\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\fHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000eHÆ\u0003J\u001a\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tHÆ\u0003Je\u0010!\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032!\b\u0002\u0010\u0004\u001a\u001b\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005j\u0002`\n¢\u0006\u0002\b\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R*\u0010\u0004\u001a\u001b\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005j\u0002`\n¢\u0006\u0002\b\t¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\"\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\t¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0016¨\u0006)"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/CheckerModeling$Checker;", "", "atMethod", "Lsoot/SootMethod;", "config", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/MethodConfig;", "Lcom/feysh/corax/config/api/MethodConfigType;", "", "Lkotlin/ExtensionFunctionType;", "Lcom/feysh/corax/config/api/MethodConfigBlockType;", "guard", "Lcom/feysh/corax/config/api/IBoolExpr;", "report", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "<init>", "(Lsoot/SootMethod;Lkotlin/jvm/functions/Function1;Lcom/feysh/corax/config/api/IBoolExpr;Lcom/feysh/corax/config/api/CheckType;Lkotlin/jvm/functions/Function1;)V", "getAtMethod", "()Lsoot/SootMethod;", "getConfig", "()Lkotlin/jvm/functions/Function1;", "getGuard", "()Lcom/feysh/corax/config/api/IBoolExpr;", "getReport", "()Lcom/feysh/corax/config/api/CheckType;", "getEnv", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: CheckerModeling$Checker.class */
    public static final class Checker {

        @NotNull
        private final SootMethod atMethod;

        @NotNull
        private final Function1<MethodConfig, Unit> config;

        @NotNull
        private final IBoolExpr guard;

        @NotNull
        private final CheckType report;

        @NotNull
        private final Function1<BugMessage.Env, Unit> env;

        @NotNull
        public final SootMethod component1() {
            return this.atMethod;
        }

        @NotNull
        public final Function1<MethodConfig, Unit> component2() {
            return this.config;
        }

        @NotNull
        public final IBoolExpr component3() {
            return this.guard;
        }

        @NotNull
        public final CheckType component4() {
            return this.report;
        }

        @NotNull
        public final Function1<BugMessage.Env, Unit> component5() {
            return this.env;
        }

        @NotNull
        public final Checker copy(@NotNull SootMethod atMethod, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IBoolExpr guard, @NotNull CheckType report, @NotNull Function1<? super BugMessage.Env, Unit> function12) {
            Intrinsics.checkNotNullParameter(atMethod, "atMethod");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(guard, "guard");
            Intrinsics.checkNotNullParameter(report, "report");
            Intrinsics.checkNotNullParameter(function12, "env");
            return new Checker(atMethod, function1, guard, report, function12);
        }

        public static /* synthetic */ Checker copy$default(Checker checker, SootMethod sootMethod, Function1 function1, IBoolExpr iBoolExpr, CheckType checkType, Function1 function12, int i, Object obj) {
            if ((i & 1) != 0) {
                sootMethod = checker.atMethod;
            }
            if ((i & 2) != 0) {
                function1 = checker.config;
            }
            if ((i & 4) != 0) {
                iBoolExpr = checker.guard;
            }
            if ((i & 8) != 0) {
                checkType = checker.report;
            }
            if ((i & 16) != 0) {
                function12 = checker.env;
            }
            return checker.copy(sootMethod, function1, iBoolExpr, checkType, function12);
        }

        @NotNull
        public String toString() {
            return "Checker(atMethod=" + this.atMethod + ", config=" + this.config + ", guard=" + this.guard + ", report=" + this.report + ", env=" + this.env + ")";
        }

        public int hashCode() {
            int result = this.atMethod.hashCode();
            return (((((((result * 31) + this.config.hashCode()) * 31) + this.guard.hashCode()) * 31) + this.report.hashCode()) * 31) + this.env.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Checker)) {
                return false;
            }
            Checker checker = (Checker) other;
            return Intrinsics.areEqual(this.atMethod, checker.atMethod) && Intrinsics.areEqual(this.config, checker.config) && Intrinsics.areEqual(this.guard, checker.guard) && Intrinsics.areEqual(this.report, checker.report) && Intrinsics.areEqual(this.env, checker.env);
        }

        public Checker(@NotNull SootMethod atMethod, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IBoolExpr guard, @NotNull CheckType report, @NotNull Function1<? super BugMessage.Env, Unit> function12) {
            Intrinsics.checkNotNullParameter(atMethod, "atMethod");
            Intrinsics.checkNotNullParameter(function1, "config");
            Intrinsics.checkNotNullParameter(guard, "guard");
            Intrinsics.checkNotNullParameter(report, "report");
            Intrinsics.checkNotNullParameter(function12, "env");
            this.atMethod = atMethod;
            this.config = function1;
            this.guard = guard;
            this.report = report;
            this.env = function12;
        }

        @NotNull
        public final SootMethod getAtMethod() {
            return this.atMethod;
        }

        @NotNull
        public final Function1<MethodConfig, Unit> getConfig() {
            return this.config;
        }

        @NotNull
        public final IBoolExpr getGuard() {
            return this.guard;
        }

        @NotNull
        public final CheckType getReport() {
            return this.report;
        }

        @NotNull
        public final Function1<BugMessage.Env, Unit> getEnv() {
            return this.env;
        }
    }

    public void addStmt(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IStmt stmt) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        if (decl instanceof IMethodDecl) {
            IMethodMatch match = ((IMethodDecl) decl).getMatch();
            Scene sceneV = Scene.v();
            Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
            Iterable sootMethods = match.matched(sceneV);
            Iterable $this$forEach$iv = sootMethods;
            for (Object element$iv : $this$forEach$iv) {
                SootMethod it = (SootMethod) element$iv;
                synchronized (this.summaries) {
                    this.summaries.add(new Triple<>(it, function1, stmt));
                    Unit unit = Unit.INSTANCE;
                }
            }
            return;
        }
        getLogger().debug(() -> {
            return addStmt$lambda$6(r1);
        });
    }

    private static final Object addStmt$lambda$6(IJDecl $decl) {
        return "TODO: decl: " + $decl + " not support";
    }

    public void check(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IBoolExpr expr, @NotNull CheckType checkType, @NotNull Function1<? super BugMessage.Env, Unit> function12) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(function12, "env");
        GLB.INSTANCE.plusAssign(checkType);
        if (!this.mainConfig.isEnable(checkType)) {
            return;
        }
        if (decl instanceof IMethodDecl) {
            IMethodMatch match = ((IMethodDecl) decl).getMatch();
            Scene sceneV = Scene.v();
            Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
            Iterable sootMethods = match.matched(sceneV);
            Iterable $this$forEach$iv = sootMethods;
            for (Object element$iv : $this$forEach$iv) {
                SootMethod it = (SootMethod) element$iv;
                synchronized (this.checkPoints) {
                    this.checkPoints.add(new Checker(it, function1, expr, checkType, function12));
                    Unit unit = Unit.INSTANCE;
                }
            }
            return;
        }
        getLogger().error(() -> {
            return check$lambda$9(r1);
        });
    }

    private static final Object check$lambda$9(IJDecl $decl) {
        return "TODO: decl: " + $decl + " not support";
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public void eval(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IExpr expr, @NotNull Function1<Object, Unit> function12) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(function12, "accept");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public void validate() {
    }

    @NotNull
    /* renamed from: getError, reason: merged with bridge method [inline-methods] */
    public ConfigInfoLogger m227getError() {
        return this.error;
    }
}
