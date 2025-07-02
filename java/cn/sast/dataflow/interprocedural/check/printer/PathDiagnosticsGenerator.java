package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.ClassResInfo;
import cn.sast.dataflow.interprocedural.check.ExitInvoke;
import cn.sast.dataflow.interprocedural.check.IPath;
import cn.sast.dataflow.interprocedural.check.InvokeEdgePath;
import cn.sast.dataflow.interprocedural.check.ModelBind;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.InterproceduralCFG;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.SootClass;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Unit;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;
import soot.tagkit.AbstractHost;
import soot.tagkit.Host;

/* compiled from: PathDiagnosticsGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ#\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH��¢\u0006\u0002\b\u001cJ4\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0018\u001a\u00020\u001d2\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010!J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\"\u001a\u00020#2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH��¢\u0006\u0002\b\u001cJ4\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\"\u001a\u00020#2\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010!J@\u0010$\u001a\u00020\u001d2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190&0\u00172\u0006\u0010'\u001a\u00020\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070)2\u000e\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190+J8\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190&0\u00172\u0006\u0010'\u001a\u00020\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070)H\u0002J\u0014\u0010-\u001a\u00020.2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00120\u0017J\u001a\u0010,\u001a\u00020.2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190&0\u0017J\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000100R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n��¨\u00061"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/printer/PathDiagnosticsGenerator;", "", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "deep", "", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/idfa/analysis/InterproceduralCFG;I)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "getDeep", "()I", "result", "Lkotlin/collections/ArrayDeque;", "Lcn/sast/api/report/BugPathEvent;", "prefix", "", "calleePrefix", "emit", "", "node", "Lcn/sast/dataflow/interprocedural/check/IPath;", "message", "Lcn/sast/dataflow/interprocedural/check/printer/EventPrinter;", "emit$corax_data_flow", "Lsoot/Unit;", "", "Lcom/feysh/corax/config/api/Language;", "region", "Lcom/feysh/corax/config/api/report/Region;", "method", "Lsoot/SootMethod;", "inlineAssignment", "pathEvents", "Lkotlin/collections/IndexedValue;", "index", "inlined", "", "type", "Lkotlin/reflect/KClass;", "inlinePathEvents", "inlineBugPathEvents", "", "inlineEvents", "Lkotlinx/collections/immutable/PersistentList;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathDiagnosticsGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathDiagnosticsGenerator.kt\ncn/sast/dataflow/interprocedural/check/printer/PathDiagnosticsGenerator\n+ 2 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Region.kt\ncom/feysh/corax/config/api/report/Region\n*L\n1#1,211:1\n303#2:212\n1#3:213\n1#3:214\n1#3:216\n59#4:215\n57#4:217\n*S KotlinDebug\n*F\n+ 1 PathDiagnosticsGenerator.kt\ncn/sast/dataflow/interprocedural/check/printer/PathDiagnosticsGenerator\n*L\n40#1:212\n40#1:213\n85#1:216\n85#1:215\n85#1:217\n*E\n"})
/* loaded from: PathDiagnosticsGenerator.class */
public final class PathDiagnosticsGenerator {

    @Nullable
    private final SootInfoCache info;

    @NotNull
    private final InterproceduralCFG icfg;
    private final int deep;

    @NotNull
    private final ArrayDeque<BugPathEvent> result;

    @NotNull
    private final String prefix;

    @NotNull
    private final String calleePrefix;

    public PathDiagnosticsGenerator(@Nullable SootInfoCache info, @NotNull InterproceduralCFG icfg, int deep) {
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        this.info = info;
        this.icfg = icfg;
        this.deep = deep;
        this.result = new ArrayDeque<>();
        this.prefix = "[" + this.deep + "]";
        this.calleePrefix = "[" + (this.deep + 1) + "]";
    }

    @Nullable
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @NotNull
    public final InterproceduralCFG getIcfg() {
        return this.icfg;
    }

    public final int getDeep() {
        return this.deep;
    }

    @NotNull
    public final List<BugPathEvent> emit$corax_data_flow(@NotNull IPath node, @NotNull EventPrinter message) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(message, "message");
        return emit(node.getNode(), message.getMessage(), message.getRegion());
    }

    @NotNull
    public final List<BugPathEvent> emit(@NotNull Unit node, @Nullable Map<Language, String> map, @Nullable Region region) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (map == null) {
            return CollectionsKt.emptyList();
        }
        SootClass clazz = this.icfg.getMethodOf(node).getDeclaringClass();
        InvokeExpr invokeExpr = ((node instanceof Stmt ? (Stmt) node : null) == null || !((Stmt) node).containsInvokeExpr()) ? null : ((Stmt) node).getInvokeExpr();
        SootMethodRef methodRef = invokeExpr != null ? invokeExpr.getMethodRef() : null;
        if (methodRef != null && ((!(methodRef instanceof SootMethod) || ((SootMethod) methodRef).isDeclared()) && Intrinsics.areEqual(methodRef.getDeclaringClass().getName(), "java.lang.String"))) {
            String name = methodRef.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (StringsKt.contains(name, "equals", true)) {
                return CollectionsKt.emptyList();
            }
        }
        if (clazz.isJavaLibraryClass()) {
            return CollectionsKt.emptyList();
        }
        Map<Language, String> map2 = map;
        ClassResInfo.Companion companion = ClassResInfo.Companion;
        Intrinsics.checkNotNull(clazz);
        ClassResInfo classResInfoOf = companion.of(clazz);
        Region regionInvoke = region;
        if (regionInvoke == null) {
            SootInfoCache it = this.info;
            if (it != null) {
                map2 = map2;
                classResInfoOf = classResInfoOf;
                regionInvoke = Region.Companion.invoke(it, (Host) node);
            } else {
                regionInvoke = null;
            }
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.invoke(node);
                if (regionInvoke == null) {
                    regionInvoke = Region.Companion.getERROR();
                }
            }
        }
        return CollectionsKt.listOf(new BugPathEvent(map2, classResInfoOf, regionInvoke, Integer.valueOf(this.deep)));
    }

    @NotNull
    public final List<BugPathEvent> emit$corax_data_flow(@NotNull SootMethod method, @Nullable EventPrinter message) {
        Intrinsics.checkNotNullParameter(method, "method");
        return message == null ? CollectionsKt.emptyList() : emit(method, message.getMessage(), message.getRegion());
    }

    @NotNull
    public final List<BugPathEvent> emit(@NotNull SootMethod method, @Nullable Map<Language, String> map, @Nullable Region region) {
        Intrinsics.checkNotNullParameter(method, "method");
        if (map == null) {
            return CollectionsKt.emptyList();
        }
        SootClass clazz = method.getDeclaringClass();
        if (method.isDeclared() && Intrinsics.areEqual(method.getDeclaringClass().getName(), "java.lang.String")) {
            String name = method.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (StringsKt.contains(name, "equals", true)) {
                return CollectionsKt.emptyList();
            }
        }
        if (clazz.isJavaLibraryClass()) {
            return CollectionsKt.emptyList();
        }
        Map<Language, String> map2 = map;
        ClassResInfo.Companion companion = ClassResInfo.Companion;
        Intrinsics.checkNotNull(clazz);
        ClassResInfo classResInfoOf = companion.of(clazz);
        Region error = region;
        if (error == null) {
            SootInfoCache it = this.info;
            if (it != null) {
                map2 = map2;
                classResInfoOf = classResInfoOf;
                error = Region.Companion.invoke(it, (AbstractHost) method);
            } else {
                error = null;
            }
            if (error == null) {
                Region this_$iv = new Region(method.getJavaSourceStartLineNumber(), method.getJavaSourceStartColumnNumber(), -1, -1);
                error = this_$iv.startLine >= 0 ? this_$iv : null;
                if (error == null) {
                    error = Region.Companion.getERROR();
                }
            }
        }
        return CollectionsKt.listOf(new BugPathEvent(map2, classResInfoOf, error, Integer.valueOf(this.deep)));
    }

    @NotNull
    public final Unit inlineAssignment(@NotNull final List<? extends IndexedValue<? extends IPath>> list, final int index, @NotNull final Set<Integer> set, @NotNull final KClass<? extends IPath> kClass) {
        Intrinsics.checkNotNullParameter(list, "pathEvents");
        Intrinsics.checkNotNullParameter(set, "inlined");
        Intrinsics.checkNotNullParameter(kClass, "type");
        final IPath pathEvent = (IPath) list.get(index).getValue();
        return new GrimpInline() { // from class: cn.sast.dataflow.interprocedural.check.printer.PathDiagnosticsGenerator$inlineAssignment$g$1
            private Set<Value> visited = new LinkedHashSet();

            public final Set<Value> getVisited() {
                return this.visited;
            }

            public final void setVisited(Set<Value> set2) {
                Intrinsics.checkNotNullParameter(set2, "<set-?>");
                this.visited = set2;
            }

            @Override // cn.sast.dataflow.interprocedural.check.printer.GrimpInline
            public Value newExpr(Value value) {
                Intrinsics.checkNotNullParameter(value, "value");
                Value result = null;
                if (!this.visited.add(value)) {
                    return value;
                }
                if (value instanceof Local) {
                    int reverseIndex = index;
                    while (true) {
                        reverseIndex--;
                        if (reverseIndex < 0) {
                            break;
                        }
                        IPath defUnitPathNode = (IPath) list.get(reverseIndex).getValue();
                        AssignStmt node = defUnitPathNode.getNode();
                        if (!Intrinsics.areEqual(node, pathEvent.getNode()) && kClass.isInstance(defUnitPathNode) && (node instanceof AssignStmt) && node.getJavaSourceStartLineNumber() == pathEvent.getNode().getJavaSourceStartLineNumber() && node.getJavaSourceStartLineNumber() > 0) {
                            Local leftOp = node.getLeftOp();
                            Intrinsics.checkNotNull(leftOp, "null cannot be cast to non-null type soot.Local");
                            Local dst = leftOp;
                            if (Intrinsics.areEqual(dst, value)) {
                                Value rightOp = node.getRightOp();
                                result = rightOp;
                                set.add(Integer.valueOf(reverseIndex));
                                break;
                            }
                        }
                    }
                }
                if (result != null) {
                    return super.newExpr(result);
                }
                return super.newExpr(value);
            }
        }.inline(pathEvent.getNode());
    }

    private final List<BugPathEvent> inlinePathEvents(List<? extends IndexedValue<? extends IPath>> list, int index, Set<Integer> set) {
        IndexedValue pathEventI = list.get(index);
        IPath pathEvent = (IPath) pathEventI.getValue();
        Unit lineUnit = inlineAssignment(list, index, set, Reflection.getOrCreateKotlinClass(pathEvent.getClass()));
        SootMethod sootMethod = this.icfg.getMethodOf(pathEvent.getNode());
        if (pathEvent instanceof ModelBind) {
            return emit$corax_data_flow(pathEvent, new EventPrinter(this.prefix).printModeling((ModelBind) pathEvent, lineUnit, sootMethod));
        }
        List n = emit$corax_data_flow(pathEvent, new EventPrinter(this.prefix).normalPrint(pathEventI, lineUnit, sootMethod));
        if (pathEvent instanceof InvokeEdgePath) {
            return CollectionsKt.plus(n, emit$corax_data_flow(((InvokeEdgePath) pathEvent).getCallee(), new EventPrinter(this.calleePrefix).calleeBeginPrint((InvokeEdgePath) pathEvent)));
        }
        return n;
    }

    public final void inlineBugPathEvents(@NotNull List<BugPathEvent> list) {
        Intrinsics.checkNotNullParameter(list, "pathEvents");
        CollectionsKt.addAll(this.result, list);
    }

    public final void inlinePathEvents(@NotNull List<? extends IndexedValue<? extends IPath>> list) {
        Intrinsics.checkNotNullParameter(list, "pathEvents");
        Set inlined = new LinkedHashSet();
        int insertIndex = this.result.size();
        int index = list.size();
        while (true) {
            index--;
            if (index >= 0) {
                if (!inlined.contains(Integer.valueOf(index))) {
                    this.result.addAll(insertIndex, inlinePathEvents(list, index, inlined));
                }
            } else {
                return;
            }
        }
    }

    @NotNull
    public final List<BugPathEvent> inlineEvents(@NotNull PersistentList<? extends Object> persistentList) {
        Intrinsics.checkNotNullParameter(persistentList, "pathEvents");
        List curEvents = new ArrayList();
        List curPathEvents = new ArrayList();
        int i = 0;
        for (Object event : persistentList) {
            if (event instanceof BugPathEvent) {
                if (!curPathEvents.isEmpty()) {
                    inlinePathEvents(curPathEvents);
                    curPathEvents.clear();
                }
                curEvents.add(event);
            } else if (event instanceof IPath) {
                if (!curEvents.isEmpty()) {
                    inlineBugPathEvents(curEvents);
                    curEvents.clear();
                }
                curPathEvents.add(new IndexedValue(i, event));
            } else if (event instanceof ExitInvoke) {
                if (!curPathEvents.isEmpty()) {
                    inlinePathEvents(curPathEvents);
                    curPathEvents.clear();
                }
                if (!curEvents.isEmpty()) {
                    inlineBugPathEvents(curEvents);
                    curEvents.clear();
                }
                EventPrinter printer = new EventPrinter(this.prefix);
                CollectionsKt.addAll(this.result, emit$corax_data_flow(((ExitInvoke) event).getInvoke(), printer.normalPrint((ExitInvoke) event)));
            } else {
                throw new IllegalStateException("internal error".toString());
            }
            i++;
        }
        if (!curEvents.isEmpty()) {
            inlineBugPathEvents(curEvents);
        }
        if (!curPathEvents.isEmpty()) {
            inlinePathEvents(curPathEvents);
        }
        return this.result;
    }
}
