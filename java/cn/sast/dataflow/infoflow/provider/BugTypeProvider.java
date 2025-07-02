package cn.sast.dataflow.infoflow.provider;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.PreAnalysisCoroutineScope;
import cn.sast.api.config.PreAnalysisCoroutineScopeKt;
import cn.sast.common.GLB;
import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.ConfigInfoLogger;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.coroutines.FastCache;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IBoolExpr;
import com.feysh.corax.config.api.IChecker;
import com.feysh.corax.config.api.IClassDecl;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IFieldDecl;
import com.feysh.corax.config.api.IJDecl;
import com.feysh.corax.config.api.ILocalVarDecl;
import com.feysh.corax.config.api.IMethodDecl;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.MethodConfig;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootMethod;

/* compiled from: BugTypeProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u000eJ\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0016\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/BugTypeProvider;", "", "config", "Lcn/sast/api/config/MainConfig;", "preAnalysisImpl", "Lcn/sast/api/config/PreAnalysisCoroutineScope;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/api/config/PreAnalysisCoroutineScope;)V", "getConfig", "()Lcn/sast/api/config/MainConfig;", "getPreAnalysisImpl", "()Lcn/sast/api/config/PreAnalysisCoroutineScope;", "methodToCheckType", "", "Lsoot/SootMethod;", "", "Lcom/feysh/corax/config/api/CheckType;", "init", "", "lookUpChecker", "", "Lcom/feysh/corax/config/api/IChecker;", "method", "lookUpCheckType", "aiCheckerImpl", "Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nBugTypeProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BugTypeProvider.kt\ncn/sast/dataflow/infoflow/provider/BugTypeProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,84:1\n1797#2,3:85\n1#3:88\n*S KotlinDebug\n*F\n+ 1 BugTypeProvider.kt\ncn/sast/dataflow/infoflow/provider/BugTypeProvider\n*L\n34#1:85,3\n*E\n"})
/* loaded from: BugTypeProvider.class */
public final class BugTypeProvider {

    @NotNull
    private final MainConfig config;

    @NotNull
    private final PreAnalysisCoroutineScope preAnalysisImpl;

    @NotNull
    private Map<SootMethod, Set<CheckType>> methodToCheckType;

    @NotNull
    private final AIAnalysisBaseImpl aiCheckerImpl;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(BugTypeProvider::logger$lambda$2);

    public BugTypeProvider(@NotNull MainConfig config, @NotNull PreAnalysisCoroutineScope preAnalysisImpl) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(preAnalysisImpl, "preAnalysisImpl");
        this.config = config;
        this.preAnalysisImpl = preAnalysisImpl;
        this.methodToCheckType = new LinkedHashMap();
        this.aiCheckerImpl = new AIAnalysisBaseImpl() { // from class: cn.sast.dataflow.infoflow.provider.BugTypeProvider$aiCheckerImpl$1
            private final ConfigInfoLogger error = new ConfigInfoLogger();
            private final PreAnalysisApi preAnalysis;

            {
                this.preAnalysis = this.this$0.getPreAnalysisImpl();
            }

            public FastCache getFastCache() {
                return FastCacheImpl.INSTANCE;
            }

            /* renamed from: getError, reason: merged with bridge method [inline-methods] */
            public ConfigInfoLogger m117getError() {
                return this.error;
            }

            public PreAnalysisApi getPreAnalysis() {
                return this.preAnalysis;
            }

            public CoroutineScope getScope() {
                return GlobalScope.INSTANCE;
            }

            public void addStmt(IJDecl decl, Function1<? super MethodConfig, Unit> function1, IStmt stmt) {
                Intrinsics.checkNotNullParameter(decl, "decl");
                Intrinsics.checkNotNullParameter(function1, "config");
                Intrinsics.checkNotNullParameter(stmt, "stmt");
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
            public void check(IJDecl decl, Function1<? super MethodConfig, Unit> function1, IBoolExpr expr, CheckType checkType, Function1<? super BugMessage.Env, Unit> function12) throws NoWhenBranchMatchedException, NotImplementedError {
                Object obj;
                Intrinsics.checkNotNullParameter(decl, "decl");
                Intrinsics.checkNotNullParameter(function1, "config");
                Intrinsics.checkNotNullParameter(expr, "expr");
                Intrinsics.checkNotNullParameter(checkType, "checkType");
                Intrinsics.checkNotNullParameter(function12, "env");
                GLB.INSTANCE.plusAssign(checkType);
                if (!(decl instanceof IMethodDecl)) {
                    if (decl instanceof IClassDecl) {
                        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
                    }
                    if (decl instanceof IFieldDecl) {
                        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
                    }
                    if (!(decl instanceof ILocalVarDecl)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
                }
                IMethodMatch match = ((IMethodDecl) decl).getMatch();
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                List<SootMethod> sootMethods = match.matched(sceneV);
                for (SootMethod sink : sootMethods) {
                    Map map = this.this$0.methodToCheckType;
                    BugTypeProvider bugTypeProvider = this.this$0;
                    synchronized (map) {
                        Map $this$getOrPut$iv = bugTypeProvider.methodToCheckType;
                        Object value$iv = $this$getOrPut$iv.get(sink);
                        if (value$iv == null) {
                            LinkedHashSet linkedHashSet = new LinkedHashSet();
                            $this$getOrPut$iv.put(sink, linkedHashSet);
                            obj = linkedHashSet;
                        } else {
                            obj = value$iv;
                        }
                        ((Set) obj).add(checkType);
                    }
                }
            }

            public void eval(IJDecl decl, Function1<? super MethodConfig, Unit> function1, IExpr expr, Function1<Object, Unit> function12) {
                Intrinsics.checkNotNullParameter(decl, "decl");
                Intrinsics.checkNotNullParameter(function1, "config");
                Intrinsics.checkNotNullParameter(expr, "expr");
                Intrinsics.checkNotNullParameter(function12, "accept");
            }

            public void validate() {
            }
        };
    }

    @NotNull
    public final MainConfig getConfig() {
        return this.config;
    }

    @NotNull
    public final PreAnalysisCoroutineScope getPreAnalysisImpl() {
        return this.preAnalysisImpl;
    }

    /* compiled from: BugTypeProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BugTypeProvider.kt", l = {25}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.infoflow.provider.BugTypeProvider$init$1")
    /* renamed from: cn.sast.dataflow.infoflow.provider.BugTypeProvider$init$1, reason: invalid class name */
    /* loaded from: BugTypeProvider$init$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return BugTypeProvider.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (PreAnalysisCoroutineScopeKt.processAIAnalysisUnits(BugTypeProvider.this.aiCheckerImpl, BugTypeProvider.this.getPreAnalysisImpl(), (Continuation) this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void init() {
        BuildersKt.runBlocking$default((CoroutineContext) null, new AnonymousClass1(null), 1, (Object) null);
    }

    /* compiled from: BugTypeProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/BugTypeProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: BugTypeProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<IChecker> lookUpChecker(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Iterable $this$fold$iv = lookUpCheckType(method);
        Set setEmptySet = SetsKt.emptySet();
        for (Object element$iv : $this$fold$iv) {
            Set acc = setEmptySet;
            CheckType type = (CheckType) element$iv;
            setEmptySet = SetsKt.plus(acc, type.getChecker());
        }
        Set set = setEmptySet;
        return set.isEmpty() ? SetsKt.emptySet() : set;
    }

    @NotNull
    public final Set<CheckType> lookUpCheckType(@NotNull SootMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        Set<CheckType> set = this.methodToCheckType.get(method);
        return set == null ? SetsKt.emptySet() : set;
    }
}
