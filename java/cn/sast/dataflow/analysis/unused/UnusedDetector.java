package cn.sast.dataflow.analysis.unused;

import cn.sast.api.config.BuiltinAnalysisConfig;
import cn.sast.api.config.MainConfig;
import cn.sast.api.util.OthersKt;
import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.analysis.IBugReporter;
import cn.sast.dataflow.infoflow.provider.MethodSummaryProviderKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.utils.UtilsKt;
import com.feysh.corax.config.builtin.checkers.DefineUnusedChecker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.tagkit.AbstractHost;

/* compiled from: UnusedDetector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��h\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018�� 12\u00020\u0001:\u00011B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0019H\u0002J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0016J\u001c\u0010+\u001a\u00020,2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00160/H\u0086@¢\u0006\u0002\u00100R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b\u001f\u0010\u001bR\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n��R\u0018\u0010$\u001a\u00020\u000f*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0018\u0010$\u001a\u00020\u000f*\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010&R\u000e\u0010)\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010*\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n��¨\u00062"}, d2 = {"Lcn/sast/dataflow/analysis/unused/UnusedDetector;", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "builtinAnalysisConfig", "Lcn/sast/api/config/BuiltinAnalysisConfig;", "cg", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "reporter", "Lcn/sast/dataflow/analysis/IBugReporter;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/api/config/BuiltinAnalysisConfig;Lsoot/jimple/toolkits/callgraph/CallGraph;Lcn/sast/dataflow/analysis/IBugReporter;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "isAnnotated", "", "abstractHost", "Lsoot/tagkit/AbstractHost;", "findUnusedField", "", "Lsoot/SootField;", "appClass", "Lsoot/SootClass;", "calleeAndSuperMethods", "", "Lsoot/SootMethod;", "getCalleeAndSuperMethods", "()Ljava/util/Set;", "calleeAndSuperMethods$delegate", "Lkotlin/Lazy;", "allBlackMethods", "getAllBlackMethods", "allBlackMethods$delegate", "blackMethodPatterns", "", "Lkotlin/text/Regex;", "isSkipped", "(Lsoot/SootMethod;)Z", "(Lsoot/SootField;)Z", "isUnused", "sootMethod", "enableUnusedMethod", "enableUrfUnreadField", "analyze", "", "clazz", "classes", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nUnusedDetector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnusedDetector.kt\ncn/sast/dataflow/analysis/unused/UnusedDetector\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,225:1\n1557#2:226\n1628#2,3:227\n865#2,2:230\n1863#2,2:232\n1755#2,3:234\n1755#2,3:237\n1053#2:241\n1053#2:242\n1619#2:243\n1863#2:244\n1864#2:246\n1620#2:247\n1469#2,5:248\n1#3:240\n1#3:245\n*S KotlinDebug\n*F\n+ 1 UnusedDetector.kt\ncn/sast/dataflow/analysis/unused/UnusedDetector\n*L\n92#1:226\n92#1:227,3\n47#1:230,2\n53#1:232,2\n97#1:234,3\n102#1:237,3\n183#1:241\n199#1:242\n65#1:243\n65#1:244\n65#1:246\n65#1:247\n66#1:248,5\n65#1:245\n*E\n"})
/* loaded from: UnusedDetector.class */
public final class UnusedDetector {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final BuiltinAnalysisConfig builtinAnalysisConfig;

    @NotNull
    private final CallGraph cg;

    @NotNull
    private final IBugReporter reporter;

    @NotNull
    private final Lazy calleeAndSuperMethods$delegate;

    @NotNull
    private final Lazy allBlackMethods$delegate;

    @NotNull
    private final List<Regex> blackMethodPatterns;
    private final boolean enableUnusedMethod;
    private final boolean enableUrfUnreadField;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(UnusedDetector::logger$lambda$17);

    public UnusedDetector(@NotNull MainConfig mainConfig, @NotNull BuiltinAnalysisConfig builtinAnalysisConfig, @NotNull CallGraph cg, @NotNull IBugReporter reporter) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(builtinAnalysisConfig, "builtinAnalysisConfig");
        Intrinsics.checkNotNullParameter(cg, "cg");
        Intrinsics.checkNotNullParameter(reporter, "reporter");
        this.mainConfig = mainConfig;
        this.builtinAnalysisConfig = builtinAnalysisConfig;
        this.cg = cg;
        this.reporter = reporter;
        this.calleeAndSuperMethods$delegate = LazyKt.lazy(() -> {
            return calleeAndSuperMethods_delegate$lambda$6(r1);
        });
        this.allBlackMethods$delegate = LazyKt.lazy(() -> {
            return allBlackMethods_delegate$lambda$8(r1);
        });
        Iterable $this$map$iv = this.builtinAnalysisConfig.getUnusedDetectorSootSigPatternBlackList();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(new Regex(it));
        }
        this.blackMethodPatterns = (List) destination$iv$iv;
        this.enableUnusedMethod = this.mainConfig.isEnable((CheckType) DefineUnusedChecker.UnusedMethod.INSTANCE);
        this.enableUrfUnreadField = this.mainConfig.isEnable((CheckType) DefineUnusedChecker.UrfUnreadField.INSTANCE);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    /* compiled from: UnusedDetector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/analysis/unused/UnusedDetector$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "isEnable", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "corax-data-flow"})
    /* loaded from: UnusedDetector$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return UnusedDetector.logger;
        }

        public final boolean isEnable(@NotNull MainConfig mainConfig) {
            Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
            return mainConfig.isAnyEnable(DefineUnusedChecker.UnusedMethod.INSTANCE, DefineUnusedChecker.UrfUnreadField.INSTANCE);
        }
    }

    private static final Unit logger$lambda$17() {
        return Unit.INSTANCE;
    }

    private final boolean isAnnotated(AbstractHost abstractHost) {
        return abstractHost.getTag("VisibilityAnnotationTag") != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.Set<soot.SootField> findUnusedField(soot.SootClass r5) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.analysis.unused.UnusedDetector.findUnusedField(soot.SootClass):java.util.Set");
    }

    private final Set<SootMethod> getCalleeAndSuperMethods() {
        return (Set) this.calleeAndSuperMethods$delegate.getValue();
    }

    private static final Set calleeAndSuperMethods_delegate$lambda$6(UnusedDetector this$0) {
        Iterable $this$mapNotNullTo$iv = CollectionsKt.toCollection(this$0.cg, new LinkedHashSet());
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            Edge it = (Edge) element$iv$iv;
            SootMethod sootMethodTgt = it.tgt();
            if (sootMethodTgt != null) {
                destination$iv.add(sootMethodTgt);
            }
        }
        Set res = (Set) destination$iv;
        Set $this$flatMapTo$iv = res;
        Collection destination$iv2 = (Set) new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            SootMethod sootMethod = (SootMethod) element$iv;
            SootClass declaringClass = sootMethod.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            Sequence list$iv = SequencesKt.filter(SootUtilsKt.findMethodOrNull(declaringClass, sootMethod.getSubSignature().toString()), UnusedDetector::calleeAndSuperMethods_delegate$lambda$6$lambda$4$lambda$3);
            CollectionsKt.addAll(destination$iv2, list$iv);
        }
        Set it2 = (Set) destination$iv2;
        res.addAll(it2);
        return res;
    }

    private static final boolean calleeAndSuperMethods_delegate$lambda$6$lambda$4$lambda$3(SootMethod it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (it.isConstructor() || it.isPrivate()) ? false : true;
    }

    private final Set<SootMethod> getAllBlackMethods() {
        return (Set) this.allBlackMethods$delegate.getValue();
    }

    private static final Set allBlackMethods_delegate$lambda$8(UnusedDetector this$0) {
        Scene scene = Scene.v();
        Set allBlackMethods = new LinkedHashSet();
        for (String blackMethodSig : this$0.builtinAnalysisConfig.getUnusedDetectorMethodSigBlackList()) {
            IMethodMatch match = OthersKt.methodSignatureToMatcher(blackMethodSig);
            if (match == null) {
                Function0 function0 = () -> {
                    return allBlackMethods_delegate$lambda$8$lambda$7(r2);
                };
                throw new IllegalStateException(function0.toString());
            }
            Intrinsics.checkNotNull(scene);
            List<SootMethod> methods = match.matched(scene);
            for (SootMethod mBlack : methods) {
                allBlackMethods.add(mBlack);
                allBlackMethods.addAll(MethodSummaryProviderKt.findAllOverrideMethodsOfMethod(mBlack));
            }
        }
        return allBlackMethods;
    }

    private static final String allBlackMethods_delegate$lambda$8$lambda$7(String $blackMethodSig) {
        return "`" + $blackMethodSig + "` is not a valid method signature";
    }

    private final boolean isSkipped(SootMethod $this$isSkipped) {
        boolean z;
        String sig = $this$isSkipped.getSignature();
        if (!getAllBlackMethods().contains($this$isSkipped)) {
            Iterable $this$any$iv = this.blackMethodPatterns;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        Regex it2 = (Regex) element$iv;
                        Intrinsics.checkNotNull(sig);
                        if (it2.matches(sig)) {
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
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private final boolean isSkipped(SootField $this$isSkipped) {
        String sig = $this$isSkipped.getSignature();
        Iterable $this$any$iv = this.blackMethodPatterns;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            Regex it = (Regex) element$iv;
            Intrinsics.checkNotNull(sig);
            if (it.matches(sig)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isUnused(SootMethod sootMethod) {
        if (sootMethod.isStaticInitializer() || isAnnotated((AbstractHost) sootMethod)) {
            return false;
        }
        if (sootMethod.isConstructor()) {
            if (Intrinsics.areEqual(sootMethod.getSubSignature(), "void <init>()")) {
                return false;
            }
            if (sootMethod.getParameterCount() == 1) {
                String name = sootMethod.getDeclaringClass().getName();
                Type parameterType = sootMethod.getParameterType(0);
                Intrinsics.checkNotNullExpressionValue(parameterType, "getParameterType(...)");
                String typename = UtilsKt.getTypename(parameterType);
                String shortJavaStyleName = sootMethod.getDeclaringClass().getShortJavaStyleName();
                Intrinsics.checkNotNullExpressionValue(shortJavaStyleName, "getShortJavaStyleName(...)");
                if (Intrinsics.areEqual(name, typename + "$" + StringsKt.substringAfterLast$default(shortJavaStyleName, "$", (String) null, 2, (Object) null))) {
                    return false;
                }
            }
        }
        String name2 = sootMethod.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        if (StringsKt.contains$default(name2, "$", false, 2, (Object) null)) {
            return false;
        }
        String name3 = sootMethod.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
        if (StringsKt.contains(name3, "lambda", true)) {
            return false;
        }
        String subSignature = sootMethod.getSubSignature();
        Intrinsics.checkNotNullExpressionValue(subSignature, "getSubSignature(...)");
        if (StringsKt.endsWith$default(subSignature, "valueOf(java.lang.String)", false, 2, (Object) null)) {
            return false;
        }
        String subSignature2 = sootMethod.getSubSignature();
        Intrinsics.checkNotNullExpressionValue(subSignature2, "getSubSignature(...)");
        if (StringsKt.endsWith$default(subSignature2, "[] values()", false, 2, (Object) null)) {
            return false;
        }
        if (!sootMethod.isStatic()) {
            String it = sootMethod.getName();
            Intrinsics.checkNotNull(it);
            if ((StringsKt.startsWith$default(it, "get", false, 2, (Object) null) || StringsKt.startsWith$default(it, "set", false, 2, (Object) null) || StringsKt.startsWith$default(it, "is", false, 2, (Object) null) ? it : null) != null) {
                return false;
            }
        }
        if (sootMethod.isMain() || this.cg.edgesInto((MethodOrMethodContext) sootMethod).hasNext()) {
            return false;
        }
        SootClass declaringClass = sootMethod.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        SootMethod superMethod = SootUtilsKt.findMethodOrNull(declaringClass, sootMethod.getSubSignature().toString(), (v1) -> {
            return isUnused$lambda$14(r2, v1);
        });
        return superMethod == null && !getCalleeAndSuperMethods().contains(sootMethod) && sootMethod.hasActiveBody();
    }

    private static final boolean isUnused$lambda$14(SootMethod $sootMethod, SootMethod it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (Intrinsics.areEqual(it, $sootMethod) || it.isConstructor() || it.isPrivate()) ? false : true;
    }

    public final void analyze(@NotNull SootClass clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (this.enableUnusedMethod) {
            int count = 1;
            int maximumUnusedMethodReportsEachClass = this.builtinAnalysisConfig.getMaximumUnusedMethodReportsEachClass();
            List methods = clazz.getMethods();
            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
            Iterable $this$sortedBy$iv = CollectionsKt.toSet(methods);
            for (SootMethod method : CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    SootMethod it = (SootMethod) t;
                    SootMethod it2 = (SootMethod) t2;
                    return ComparisonsKt.compareValues(it.getSignature(), it2.getSignature());
                }
            })) {
                Intrinsics.checkNotNull(method);
                if (isUnused(method) && !isSkipped(method)) {
                    int i = count;
                    count++;
                    if (i > maximumUnusedMethodReportsEachClass) {
                        break;
                    } else {
                        IBugReporter.DefaultImpls.report$default(this.reporter, DefineUnusedChecker.UnusedMethod.INSTANCE, method, (Function1) null, 4, (Object) null);
                    }
                }
            }
        }
        if (this.enableUrfUnreadField) {
            int count2 = 1;
            int maximumUnusedFieldReportsEachClass = this.builtinAnalysisConfig.getMaximumUnusedFieldReportsEachClass();
            Iterable $this$sortedBy$iv2 = findUnusedField(clazz);
            List<SootField> unused = CollectionsKt.sortedWith($this$sortedBy$iv2, new Comparator() { // from class: cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$$inlined$sortedBy$2
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    SootField it = (SootField) t;
                    SootField it2 = (SootField) t2;
                    return ComparisonsKt.compareValues(it.getSignature(), it2.getSignature());
                }
            });
            for (SootField field : unused) {
                if (!isSkipped(field)) {
                    int i2 = count2;
                    count2++;
                    if (i2 <= maximumUnusedFieldReportsEachClass) {
                        IBugReporter.DefaultImpls.report$default(this.reporter, DefineUnusedChecker.UrfUnreadField.INSTANCE, field, (Function1) null, 4, (Object) null);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: UnusedDetector.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "UnusedDetector.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$3")
    /* renamed from: cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$3, reason: invalid class name */
    /* loaded from: UnusedDetector$analyze$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Collection<SootClass> $classes;
        final /* synthetic */ Semaphore $semaphore;
        final /* synthetic */ UnusedDetector this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Collection<? extends SootClass> collection, Semaphore $semaphore, UnusedDetector $receiver, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$classes = collection;
            this.$semaphore = $semaphore;
            this.this$0 = $receiver;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$classes, this.$semaphore, this.this$0, continuation);
            anonymousClass3.L$0 = value;
            return anonymousClass3;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    for (SootClass clazz : this.$classes) {
                        BuildersKt.launch$default($this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.$semaphore, this.this$0, clazz, null), 3, (Object) null);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* compiled from: UnusedDetector.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
        @DebugMetadata(f = "UnusedDetector.kt", l = {226}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this$withPermit$iv"}, m = "invokeSuspend", c = "cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$3$1")
        @SourceDebugExtension({"SMAP\nUnusedDetector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnusedDetector.kt\ncn/sast/dataflow/analysis/unused/UnusedDetector$analyze$3$1\n+ 2 Semaphore.kt\nkotlinx/coroutines/sync/SemaphoreKt\n*L\n1#1,225:1\n81#2,6:226\n*S KotlinDebug\n*F\n+ 1 UnusedDetector.kt\ncn/sast/dataflow/analysis/unused/UnusedDetector$analyze$3$1\n*L\n217#1:226,6\n*E\n"})
        /* renamed from: cn.sast.dataflow.analysis.unused.UnusedDetector$analyze$3$1, reason: invalid class name */
        /* loaded from: UnusedDetector$analyze$3$1.class */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ Semaphore $semaphore;
            final /* synthetic */ UnusedDetector this$0;
            final /* synthetic */ SootClass $clazz;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Semaphore $semaphore, UnusedDetector $receiver, SootClass $clazz, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$semaphore = $semaphore;
                this.this$0 = $receiver;
                this.$clazz = $clazz;
            }

            public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
                return new AnonymousClass1(this.$semaphore, this.this$0, this.$clazz, continuation);
            }

            public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
                return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object $result) {
                SootClass sootClass;
                UnusedDetector unusedDetector;
                Semaphore $this$withPermit$iv;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        ResultKt.throwOnFailure($result);
                        $this$withPermit$iv = this.$semaphore;
                        unusedDetector = this.this$0;
                        sootClass = this.$clazz;
                        this.L$0 = $this$withPermit$iv;
                        this.L$1 = unusedDetector;
                        this.L$2 = sootClass;
                        this.label = 1;
                        if ($this$withPermit$iv.acquire((Continuation) this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        sootClass = (SootClass) this.L$2;
                        unusedDetector = (UnusedDetector) this.L$1;
                        $this$withPermit$iv = (Semaphore) this.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    unusedDetector.analyze(sootClass);
                    Unit unit = Unit.INSTANCE;
                    $this$withPermit$iv.release();
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    $this$withPermit$iv.release();
                    throw th;
                }
            }
        }
    }

    @Nullable
    public final Object analyze(@NotNull Collection<? extends SootClass> collection, @NotNull Continuation<? super Unit> continuation) {
        Semaphore semaphore = SemaphoreKt.Semaphore$default(this.mainConfig.getParallelsNum() * 2, 0, 2, (Object) null);
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass3(collection, semaphore, this, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
