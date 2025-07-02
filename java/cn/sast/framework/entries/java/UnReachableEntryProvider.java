package cn.sast.framework.entries.java;

import cn.sast.dataflow.callgraph.TargetReachableMethods;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.EntryPoints;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Sources;
import soot.util.Chain;

/* compiled from: UnReachableEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� \u00132\u00020\u0001:\u0001\u0013B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/entries/java/UnReachableEntryProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "ctx", "Lcn/sast/framework/SootCtx;", "exclude", "", "", "<init>", "(Lcn/sast/framework/SootCtx;Ljava/util/Set;)V", "getExclude", "()Ljava/util/Set;", "getEntryMethods", "", "Lsoot/SootMethod;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nUnReachableEntryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnReachableEntryProvider.kt\ncn/sast/framework/entries/java/UnReachableEntryProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,79:1\n1368#2:80\n1454#2,5:81\n774#2:86\n865#2,2:87\n*S KotlinDebug\n*F\n+ 1 UnReachableEntryProvider.kt\ncn/sast/framework/entries/java/UnReachableEntryProvider\n*L\n59#1:80\n59#1:81,5\n59#1:86\n59#1:87,2\n*E\n"})
/* loaded from: UnReachableEntryProvider.class */
public class UnReachableEntryProvider implements IEntryPointProvider {

    @NotNull
    private final SootCtx ctx;

    @NotNull
    private final Set<String> exclude;

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(UnReachableEntryProvider::logger$lambda$3);

    public UnReachableEntryProvider(@NotNull SootCtx ctx, @NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(set, "exclude");
        this.ctx = ctx;
        this.exclude = set;
        this.iterator = FlowKt.flow(new UnReachableEntryProvider$iterator$1(this, null));
    }

    public /* synthetic */ UnReachableEntryProvider(SootCtx sootCtx, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sootCtx, (i & 2) != 0 ? new LinkedHashSet() : set);
    }

    @NotNull
    public final Set<String> getExclude() {
        return this.exclude;
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    /* compiled from: UnReachableEntryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lcn/sast/framework/entries/java/UnReachableEntryProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getEntryPoints", "", "Lsoot/SootMethod;", "ctx", "Lcn/sast/framework/SootCtx;", "methodsToFind", "", "corax-framework"})
    /* loaded from: UnReachableEntryProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final Set<SootMethod> getEntryPoints(@NotNull SootCtx ctx, @NotNull List<? extends SootMethod> list) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(list, "methodsToFind");
            ctx.releaseCallGraph();
            UnReachableEntryProvider.logger.info(Companion::getEntryPoints$lambda$0);
            UnReachableEntryProvider.logger.info(() -> {
                return getEntryPoints$lambda$1(r1);
            });
            Scene scene = Scene.v();
            List entryPointsOrig = EntryPoints.v().all();
            Intrinsics.checkNotNullExpressionValue(entryPointsOrig, "all(...)");
            scene.setEntryPoints(list);
            ctx.constructCallGraph();
            CallGraph cg = ctx.getSootMethodCallGraph();
            TargetReachableMethods reachable = new TargetReachableMethods(cg, list);
            reachable.update();
            Set it = new LinkedHashSet();
            Iterator itListener = reachable.listener();
            Intrinsics.checkNotNullExpressionValue(itListener, "listener(...)");
            Iterator iter = itListener;
            while (iter.hasNext()) {
                MethodOrMethodContext cur = (MethodOrMethodContext) iter.next();
                Sources sources = new Sources(cg.edgesInto(cur));
                if (!sources.hasNext() && cur.method().isConcrete()) {
                    SootMethod sootMethodMethod = cur.method();
                    Intrinsics.checkNotNullExpressionValue(sootMethodMethod, "method(...)");
                    it.add(sootMethodMethod);
                }
            }
            it.addAll(entryPointsOrig);
            UnReachableEntryProvider.logger.info(() -> {
                return getEntryPoints$lambda$3$lambda$2(r1);
            });
            if (it.isEmpty()) {
                UnReachableEntryProvider.logger.warn("no entry points");
            }
            return it;
        }

        private static final Object getEntryPoints$lambda$0() {
            return "auto make the entry points by UnReachableMethodsFinder.";
        }

        private static final Object getEntryPoints$lambda$1(List $methodsToFind) {
            return "reach methods num: " + $methodsToFind.size();
        }

        private static final Object getEntryPoints$lambda$3$lambda$2(Set $it) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Integer.valueOf($it.size())};
            String str = String.format("unreachable entry methods num :%d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
    }

    private static final Unit logger$lambda$3() {
        return Unit.INSTANCE;
    }

    @NotNull
    public Set<SootMethod> getEntryMethods() {
        Scene scene = Scene.v();
        Iterable applicationClasses = scene.getApplicationClasses();
        logger.info(() -> {
            return getEntryMethods$lambda$0(r1);
        });
        Intrinsics.checkNotNull(applicationClasses);
        Iterable $this$flatMap$iv = applicationClasses;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$flatMap$iv.iterator();
        while (it.hasNext()) {
            Iterable methods = ((SootClass) it.next()).getMethods();
            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
            Iterable list$iv$iv = methods;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SootMethod it2 = (SootMethod) element$iv$iv;
            if (!scene.isExcluded(it2.getDeclaringClass().getName()) || scene.isIncluded(it2.getDeclaringClass().getName())) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List filterSm = (List) destination$iv$iv2;
        return Companion.getEntryPoints(this.ctx, filterSm);
    }

    private static final Object getEntryMethods$lambda$0(Chain $reachClasses) {
        return "reach classes num: " + $reachClasses.size();
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
