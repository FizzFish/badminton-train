package cn.sast.framework.graph;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.report.Counter;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.rewrite.IdentityStmt2MethodParamRegion;
import cn.sast.framework.rewrite.StringConcatRewriterTransform;
import com.github.ajalt.mordant.rendering.TextStyle;
import com.github.ajalt.mordant.rendering.Theme;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.Body;
import soot.MethodOrMethodContext;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.UnitPatchingChain;
import soot.VoidType;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.Stmt;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.callgraph.ReachableMethods;

/* compiled from: CGUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��p\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ$\u0010\f\u001a\u00020\n*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ>\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020 J\"\u0010,\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010-\u001a\u00020 2\b\b\u0002\u0010.\u001a\u00020 R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n��¨\u00061"}, d2 = {"Lcn/sast/framework/graph/CGUtils;", "", "<init>", "()V", "missClasses", "Lcn/sast/api/report/Counter;", "Lsoot/SootClass;", "getMissClasses", "()Lcn/sast/api/report/Counter;", "rewriteJimpleBodyAfterCG", "", "makeSpuriousMethodFromInvokeExpr", "forceAddCgEdge", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "src", "Lsoot/SootMethod;", "srcUnit", "Lsoot/jimple/Stmt;", "ie", "Lsoot/jimple/InvokeExpr;", "addCallEdgeForPhantomMethods", "flushMissedClasses", "outputDir", "Lcn/sast/common/IResDirectory;", "removeInvalidMethodBody", "scene", "Lsoot/Scene;", "fixInvalidInterface", "removeLargeClasses", "fixScene", "createSootMethod", "name", "", "argsTypes", "", "Lsoot/Type;", "returnType", "declaringClass", "graphBody", "Lsoot/jimple/JimpleBody;", "isStatic", "", "getOrCreateClass", "className", "createDummyMain", "dummyClassName", "methodName", "logger", "Lmu/KLogger;", "corax-framework"})
@SourceDebugExtension({"SMAP\nCGUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CGUtils.kt\ncn/sast/framework/graph/CGUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,223:1\n1#2:224\n*E\n"})
/* loaded from: CGUtils.class */
public final class CGUtils {

    @NotNull
    public static final CGUtils INSTANCE = new CGUtils();

    @NotNull
    private static final Counter<SootClass> missClasses = new Counter<>();

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CGUtils::logger$lambda$8);

    private CGUtils() {
    }

    @NotNull
    public final Counter<SootClass> getMissClasses() {
        return missClasses;
    }

    /* compiled from: CGUtils.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "CGUtils.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.graph.CGUtils$rewriteJimpleBodyAfterCG$1")
    /* renamed from: cn.sast.framework.graph.CGUtils$rewriteJimpleBodyAfterCG$1, reason: invalid class name */
    /* loaded from: CGUtils$rewriteJimpleBodyAfterCG$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Iterator<SootClass> $all;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Iterator<? extends SootClass> it, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$all = it;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$all, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$runBlocking = (CoroutineScope) this.L$0;
                    Iterator<SootClass> it = this.$all;
                    Intrinsics.checkNotNull(it);
                    while (it.hasNext()) {
                        SootClass sc = it.next();
                        if (!sc.isPhantom()) {
                            List methods = sc.getMethods();
                            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
                            List<SootMethod> methods2 = CollectionsKt.toList(methods);
                            for (SootMethod sm : methods2) {
                                if (sm.hasActiveBody()) {
                                    BuildersKt.launch$default($this$runBlocking, (CoroutineContext) null, (CoroutineStart) null, new C00011(sm, null), 3, (Object) null);
                                }
                            }
                        }
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* compiled from: CGUtils.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
        @DebugMetadata(f = "CGUtils.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.graph.CGUtils$rewriteJimpleBodyAfterCG$1$1")
        /* renamed from: cn.sast.framework.graph.CGUtils$rewriteJimpleBodyAfterCG$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: CGUtils$rewriteJimpleBodyAfterCG$1$1.class */
        static final class C00011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SootMethod $sm;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00011(SootMethod $sm, Continuation<? super C00011> continuation) {
                super(2, continuation);
                this.$sm = $sm;
            }

            public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
                return new C00011(this.$sm, continuation);
            }

            public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
                return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Body body;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        ResultKt.throwOnFailure(obj);
                        if (!this.$sm.hasActiveBody()) {
                            return Unit.INSTANCE;
                        }
                        try {
                            body = this.$sm.getActiveBody();
                        } catch (RuntimeException e) {
                        }
                        if (body == null) {
                            return Unit.INSTANCE;
                        }
                        PackManager.v().getTransform(StringConcatRewriterTransform.phase).apply(body);
                        PackManager.v().getTransform(IdentityStmt2MethodParamRegion.phase).apply(body);
                        this.$sm.setActiveBody(body);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void rewriteJimpleBodyAfterCG() {
        Iterator all = Scene.v().getClasses().snapshotIterator();
        BuildersKt.runBlocking(Dispatchers.getDefault(), new AnonymousClass1(all, null));
    }

    public final void makeSpuriousMethodFromInvokeExpr() {
        Iterable applicationClasses = Scene.v().getApplicationClasses();
        Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
        List<SootClass> app = CollectionsKt.toList(applicationClasses);
        for (SootClass appSc : app) {
            if (!appSc.isPhantom()) {
                List methods = appSc.getMethods();
                Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
                List<SootMethod> methods2 = CollectionsKt.toList(methods);
                for (SootMethod sm : methods2) {
                    if (sm.isConcrete() && sm.getSource() != null) {
                        try {
                            Body body = sm.retrieveActiveBody();
                            if (body != null) {
                                UnitPatchingChain units = body.getUnits();
                                Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
                                Iterator it = units.iterator();
                                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                                while (it.hasNext()) {
                                    Stmt stmt = (soot.Unit) it.next();
                                    Stmt srcUnit = stmt instanceof Stmt ? stmt : null;
                                    if (srcUnit != null) {
                                        if (srcUnit.containsInvokeExpr()) {
                                            srcUnit.getInvokeExpr().getMethod();
                                        }
                                        if (srcUnit.containsFieldRef()) {
                                            srcUnit.getFieldRef().getField();
                                        }
                                    }
                                }
                            }
                        } catch (RuntimeException e) {
                        }
                    }
                }
            }
        }
    }

    private final void forceAddCgEdge(CallGraph $this$forceAddCgEdge, SootMethod src, Stmt srcUnit, InvokeExpr ie) {
        MethodOrMethodContext method = ie.getMethod();
        if (srcUnit.getInvokeExpr() instanceof DynamicInvokeExpr) {
            return;
        }
        Edge edge = new Edge((MethodOrMethodContext) src, srcUnit, method);
        $this$forceAddCgEdge.addEdge(edge);
    }

    public final void addCallEdgeForPhantomMethods() {
        Scene scene = Scene.v();
        CallGraph cg = scene.getCallGraph();
        ReachableMethods reachableMethods = scene.getReachableMethods();
        reachableMethods.update();
        Iterator itListener = reachableMethods.listener();
        Intrinsics.checkNotNullExpressionValue(itListener, "listener(...)");
        Iterator listener = itListener;
        while (listener.hasNext()) {
            SootMethod src = ((MethodOrMethodContext) listener.next()).method();
            if (src.hasActiveBody()) {
                UnitPatchingChain units = src.getActiveBody().getUnits();
                Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
                Iterator it = units.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    soot.Unit u = (soot.Unit) it.next();
                    Intrinsics.checkNotNull(u, "null cannot be cast to non-null type soot.jimple.Stmt");
                    Stmt srcUnit = (Stmt) u;
                    if (srcUnit.containsInvokeExpr()) {
                        InvokeExpr ie = srcUnit.getInvokeExpr();
                        Iterator edges = cg.edgesOutOf(u);
                        if (!edges.hasNext()) {
                            SootClass tgtClass = ie.getMethodRef().getDeclaringClass();
                            if (tgtClass != null && tgtClass.isPhantom() && !Scene.v().isExcluded(tgtClass)) {
                                String name = tgtClass.getName();
                                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                                if (!StringsKt.startsWith$default(name, "soot.dummy", false, 2, (Object) null)) {
                                    CGUtils cGUtils = INSTANCE;
                                    missClasses.count(tgtClass);
                                }
                            }
                            Intrinsics.checkNotNull(cg);
                            Intrinsics.checkNotNull(src);
                            Intrinsics.checkNotNull(ie);
                            forceAddCgEdge(cg, src, srcUnit, ie);
                        }
                    }
                }
            }
        }
    }

    public final void flushMissedClasses(@NotNull IResDirectory outputDir) throws IOException {
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        IResFile out = outputDir.resolve("phantom_dependence_classes.txt").toFile();
        if (missClasses.isNotEmpty()) {
            logger.warn(() -> {
                return flushMissedClasses$lambda$1(r1);
            });
            missClasses.writeResults(out);
        } else {
            Files.deleteIfExists(out.getPath());
        }
    }

    private static final Object flushMissedClasses$lambda$1(IResFile $out) {
        TextStyle warning = Theme.Companion.getDefault().getWarning();
        CGUtils cGUtils = INSTANCE;
        return warning.invoke("Incomplete analysis! The num of " + missClasses.size() + " dependent classes cannot be found here. check: " + $out.getAbsolute().getNormalize());
    }

    public final void removeInvalidMethodBody(@NotNull Scene scene) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Iterator it = scene.getClasses().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            SootClass sc = (SootClass) it.next();
            for (SootMethod sm : sc.getMethods()) {
                if (sm.hasActiveBody()) {
                    Body body = sm.getActiveBody();
                    if (body.getUnits().isEmpty()) {
                        sm.setActiveBody((Body) null);
                        sm.setPhantom(true);
                    }
                }
            }
        }
    }

    public final void fixInvalidInterface(@NotNull Scene scene) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Iterator it = scene.getClasses().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            SootClass sc = (SootClass) it.next();
            Iterator itSnapshotIterator = sc.getInterfaces().snapshotIterator();
            Intrinsics.checkNotNullExpressionValue(itSnapshotIterator, "snapshotIterator(...)");
            while (itSnapshotIterator.hasNext()) {
                SootClass i = (SootClass) itSnapshotIterator.next();
                if (!i.isInterface()) {
                    logger.warn(() -> {
                        return fixInvalidInterface$lambda$2(r1, r2);
                    });
                    try {
                        sc.removeInterface(i);
                    } catch (Exception e) {
                        logger.warn(e, () -> {
                            return fixInvalidInterface$lambda$3(r2, r3);
                        });
                    }
                }
            }
        }
    }

    private static final Object fixInvalidInterface$lambda$2(SootClass $i, SootClass $sc) {
        return $i + " is not a interface. but contains in interfaces of " + $sc;
    }

    private static final Object fixInvalidInterface$lambda$3(SootClass $i, SootClass $sc) {
        return "remove interface " + $i + " from " + $sc + " failed";
    }

    public final void removeLargeClasses(@NotNull Scene scene) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        int skipClassByMaximumMethods = ExtSettings.INSTANCE.getSkip_large_class_by_maximum_methods();
        int skipClassByMaximumFields = ExtSettings.INSTANCE.getSkip_large_class_by_maximum_fields();
        if (skipClassByMaximumMethods > 0 || skipClassByMaximumFields > 0) {
            Iterator itSnapshotIterator = scene.getClasses().snapshotIterator();
            Intrinsics.checkNotNullExpressionValue(itSnapshotIterator, "snapshotIterator(...)");
            while (itSnapshotIterator.hasNext()) {
                SootClass sc = (SootClass) itSnapshotIterator.next();
                boolean removeIt = false;
                if (skipClassByMaximumMethods > 0 && sc.getMethodCount() > skipClassByMaximumMethods) {
                    removeIt = true;
                    logger.warn(() -> {
                        return removeLargeClasses$lambda$4(r1, r2);
                    });
                }
                if (skipClassByMaximumFields > 0 && sc.getFieldCount() > skipClassByMaximumFields) {
                    removeIt = true;
                    logger.warn(() -> {
                        return removeLargeClasses$lambda$5(r1, r2);
                    });
                }
                if (removeIt) {
                    scene.removeClass(sc);
                }
            }
        }
    }

    private static final Object removeLargeClasses$lambda$4(SootClass $sc, int $skipClassByMaximumMethods) {
        return "Remove large class: " + $sc + " which is too large. Limit the class methods count should less than " + $skipClassByMaximumMethods;
    }

    private static final Object removeLargeClasses$lambda$5(SootClass $sc, int $skipClassByMaximumFields) {
        return "Remove big class: " + $sc + " which is too large. Limit the class fields count should less than " + $skipClassByMaximumFields;
    }

    public final void fixScene(@NotNull Scene scene) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        removeInvalidMethodBody(scene);
        fixInvalidInterface(scene);
    }

    public static /* synthetic */ SootMethod createSootMethod$default(CGUtils cGUtils, String str, List list, Type type, SootClass sootClass, JimpleBody jimpleBody, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = true;
        }
        return cGUtils.createSootMethod(str, list, type, sootClass, jimpleBody, z);
    }

    @NotNull
    public final SootMethod createSootMethod(@NotNull String name, @NotNull List<? extends Type> list, @NotNull Type returnType, @NotNull SootClass declaringClass, @NotNull JimpleBody graphBody, boolean isStatic) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "argsTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(declaringClass, "declaringClass");
        Intrinsics.checkNotNullParameter(graphBody, "graphBody");
        SootMethod it = new SootMethod(name, list, returnType, isStatic ? 8 : 0);
        declaringClass.addMethod(it);
        it.setActiveBody((Body) graphBody);
        return it;
    }

    @NotNull
    public final SootClass getOrCreateClass(@NotNull Scene scene, @NotNull String className) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(className, "className");
        SootClass mainClass = scene.getSootClassUnsafe(className, false);
        if (mainClass == null) {
            SootClass sootClassMakeSootClass = scene.makeSootClass(className);
            Intrinsics.checkNotNull(sootClassMakeSootClass);
            mainClass = sootClassMakeSootClass;
            mainClass.setResolvingLevel(3);
            scene.addClass(mainClass);
        }
        return mainClass;
    }

    public static /* synthetic */ SootClass createDummyMain$default(CGUtils cGUtils, Scene scene, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "dummyMainClass";
        }
        if ((i & 4) != 0) {
            str2 = "fakeMethod";
        }
        return cGUtils.createDummyMain(scene, str, str2);
    }

    @NotNull
    public final SootClass createDummyMain(@NotNull Scene scene, @NotNull String dummyClassName, @NotNull String methodName) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(dummyClassName, "dummyClassName");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Jimple jimple = Jimple.v();
        SootClass dummyClass = getOrCreateClass(scene, dummyClassName);
        dummyClass.setApplicationClass();
        JimpleBody $this$createDummyMain_u24lambda_u247 = jimple.newBody();
        $this$createDummyMain_u24lambda_u247.getUnits().add(jimple.newNopStmt());
        List listEmptyList = CollectionsKt.emptyList();
        Type typeV = VoidType.v();
        Intrinsics.checkNotNullExpressionValue(typeV, "v(...)");
        Intrinsics.checkNotNull($this$createDummyMain_u24lambda_u247);
        createSootMethod$default(this, methodName, listEmptyList, typeV, dummyClass, $this$createDummyMain_u24lambda_u247, false, 32, null);
        return dummyClass;
    }

    private static final Unit logger$lambda$8() {
        return Unit.INSTANCE;
    }
}
