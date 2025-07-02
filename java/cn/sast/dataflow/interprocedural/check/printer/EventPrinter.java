package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.analysis.EntryParam;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.PhantomField;
import cn.sast.dataflow.interprocedural.check.AssignLocalPath;
import cn.sast.dataflow.interprocedural.check.ExitInvoke;
import cn.sast.dataflow.interprocedural.check.GetEdgePath;
import cn.sast.dataflow.interprocedural.check.IPath;
import cn.sast.dataflow.interprocedural.check.InvokeEdgePath;
import cn.sast.dataflow.interprocedural.check.LiteralPath;
import cn.sast.dataflow.interprocedural.check.MergePath;
import cn.sast.dataflow.interprocedural.check.ModelBind;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.PrevCallStmtInfo;
import cn.sast.dataflow.interprocedural.check.SetEdgePath;
import cn.sast.dataflow.interprocedural.check.printer.SimpleUnitPrinter;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.baseimpl.IexConst;
import com.feysh.corax.config.api.report.Region;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.Constant;
import soot.jimple.DefinitionStmt;
import soot.jimple.IdentityStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;

/* compiled from: EventPrinter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��r\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\b��\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0003H\u0086\u0002J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010\u001c\u001a\u00020��2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020��2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0003J\b\u0010#\u001a\u00020\u0010H\u0002J\u000e\u0010$\u001a\u00020��2\u0006\u0010%\u001a\u00020&J$\u0010\u001e\u001a\u00020��2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00140(2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010.\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001f\u0010)\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0003\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00062"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/printer/EventPrinter;", "", "prefix", "", "<init>", "(Ljava/lang/String;)V", "outputEn", "Ljava/lang/StringBuffer;", "outputZh", "region", "Lcom/feysh/corax/config/api/report/Region;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "setRegion", "(Lcom/feysh/corax/config/api/report/Region;)V", "plusAssign", "", "string", "default", "pathNode", "Lcn/sast/dataflow/interprocedural/check/IPath;", "unit", "Lsoot/Unit;", "printModelingBinding", "v", "Lcn/sast/dataflow/interprocedural/check/ModelBind;", "sootMethod", "Lsoot/SootMethod;", "printModeling", "pathEvent", "normalPrint", "node", "Lcn/sast/dataflow/interprocedural/check/ExitInvoke;", "write", "s", "clean", "calleeBeginPrint", "invoke", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "nodeI", "Lkotlin/collections/IndexedValue;", "message", "", "Lcom/feysh/corax/config/api/Language;", "getMessage", "()Ljava/util/Map;", "printParameterNameByIndex", "", "index", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nEventPrinter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/EventPrinter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,245:1\n1#2:246\n1#2:249\n1#2:251\n1#2:253\n310#3:247\n303#3:248\n303#3:250\n303#3:252\n808#4,11:254\n*S KotlinDebug\n*F\n+ 1 EventPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/EventPrinter\n*L\n101#1:249\n129#1:251\n156#1:253\n100#1:247\n101#1:248\n129#1:250\n156#1:252\n225#1:254,11\n*E\n"})
/* loaded from: EventPrinter.class */
public final class EventPrinter {

    @NotNull
    private final String prefix;

    @NotNull
    private StringBuffer outputEn;

    @NotNull
    private StringBuffer outputZh;

    @Nullable
    private Region region;

    /* compiled from: EventPrinter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: EventPrinter$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IIexConst.Type.values().length];
            try {
                iArr[IIexConst.Type.TaintSet.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IIexConst.Type.ViaSet.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public EventPrinter(@NotNull String prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        this.prefix = prefix;
        this.outputEn = new StringBuffer();
        this.outputZh = new StringBuffer();
    }

    @Nullable
    public final Region getRegion() {
        return this.region;
    }

    public final void setRegion(@Nullable Region region) {
        this.region = region;
    }

    public final void plusAssign(@NotNull StringBuffer $this$plusAssign, @NotNull String string) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        $this$plusAssign.append(string);
    }

    /* renamed from: default, reason: not valid java name */
    public final void m232default(@NotNull IPath pathNode, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(pathNode, "pathNode");
        Intrinsics.checkNotNullParameter(unit, "unit");
        String keyPath = SimpleUnitPrinter.Companion.getStringOf(unit);
        plusAssign(this.outputEn, "key path: " + keyPath);
        plusAssign(this.outputZh, "关键路径: " + keyPath);
    }

    private final void printModelingBinding(ModelBind v, Unit unit, SootMethod sootMethod) {
        Region paramRegion;
        if (v.getMt() instanceof TaintProperty) {
            IValue obj = v.getObj();
            if ((obj instanceof EntryParam) || (obj instanceof PhantomField)) {
                plusAssign(this.outputZh, "污点源 Source 点: ");
                plusAssign(this.outputEn, "Taint source: ");
                IValue iValue = v.getObj();
                if ((iValue instanceof EntryParam) && printParameterNameByIndex(((EntryParam) iValue).getMethod(), ((EntryParam) iValue).getParamIndex())) {
                    return;
                }
            } else {
                plusAssign(this.outputZh, "污点传递");
                plusAssign(this.outputEn, "Taint propagate");
            }
            String call = SimpleUnitPrinter.Companion.getStringOf(unit);
            boolean msg = false;
            if (!(unit instanceof IdentityStmt)) {
                write(": " + call);
                msg = true;
            }
            if (v.getInfo() != null) {
                List params = v.getInfo().getParameterNamesInUnitDefUse(unit);
                if (!params.isEmpty()) {
                    String paramsStr = CollectionsKt.joinToString$default(params, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
                    plusAssign(this.outputZh, ", 关键位置: `" + paramsStr + "`");
                    plusAssign(this.outputEn, ", key scope: `" + paramsStr + "`");
                    if (v.getInfo() instanceof PrevCallStmtInfo) {
                        EventPrinter eventPrinter = this;
                        Integer firstParamIndex = ((PrevCallStmtInfo) v.getInfo()).getFirstParamIndex();
                        if (firstParamIndex != null) {
                            int it = firstParamIndex.intValue();
                            eventPrinter = eventPrinter;
                            paramRegion = Region.Companion.getParamRegion(sootMethod, it);
                        } else {
                            paramRegion = null;
                        }
                        eventPrinter.region = paramRegion;
                    }
                    msg = true;
                }
            }
            if (msg) {
                return;
            } else {
                clean();
            }
        }
        m232default(v, unit);
    }

    @NotNull
    public final EventPrinter printModeling(@NotNull ModelBind pathEvent, @NotNull Unit unit, @NotNull SootMethod sootMethod) {
        Intrinsics.checkNotNullParameter(pathEvent, "pathEvent");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(sootMethod, "sootMethod");
        printModelingBinding(pathEvent, unit, sootMethod);
        return this;
    }

    @NotNull
    public final EventPrinter normalPrint(@NotNull ExitInvoke node) {
        Intrinsics.checkNotNullParameter(node, "node");
        SimpleUnitPrinter.Companion companion = SimpleUnitPrinter.Companion;
        SootMethod callee = node.getInvoke().getCallee();
        DefinitionStmt node2 = node.getInvoke().getNode();
        DefinitionStmt definitionStmt = node2 instanceof DefinitionStmt ? node2 : null;
        Value leftOp = definitionStmt != null ? definitionStmt.getLeftOp() : null;
        Stmt node3 = node.getInvoke().getNode();
        InvokeExpr invokeExpr = ((node3 instanceof Stmt ? node3 : null) == null || !node3.containsInvokeExpr()) ? null : node3.getInvokeExpr();
        String call = SimpleUnitPrinter.Companion.getStringOf$default(companion, callee, leftOp, invokeExpr, false, 8, null);
        plusAssign(this.outputZh, "离开被调用方法: `" + call + "`");
        plusAssign(this.outputEn, "return from calling: `" + call + "`");
        return this;
    }

    public final void write(@NotNull String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        plusAssign(this.outputZh, s);
        plusAssign(this.outputEn, s);
    }

    private final void clean() {
        this.outputZh = new StringBuffer();
        this.outputEn = new StringBuffer();
    }

    @NotNull
    public final EventPrinter calleeBeginPrint(@NotNull InvokeEdgePath invoke) {
        Intrinsics.checkNotNullParameter(invoke, "invoke");
        SootMethod container = invoke.getContainer();
        String call = container.getDeclaringClass().getName() + "#" + container.getName();
        plusAssign(this.outputZh, "从 `" + call + "` 进入调用");
        plusAssign(this.outputEn, "calling from: `" + call + "`");
        return this;
    }

    @NotNull
    public final EventPrinter normalPrint(@NotNull IndexedValue<? extends IPath> indexedValue, @NotNull Unit unit, @NotNull SootMethod sootMethod) {
        Intrinsics.checkNotNullParameter(indexedValue, "nodeI");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(sootMethod, "sootMethod");
        IPath node = (IPath) indexedValue.getValue();
        if (node instanceof InvokeEdgePath) {
            SimpleUnitPrinter.Companion companion = SimpleUnitPrinter.Companion;
            SootMethod callee = ((InvokeEdgePath) node).getCallee();
            InvokeExpr invokeExpr = ((unit instanceof Stmt ? (Stmt) unit : null) == null || !((Stmt) unit).containsInvokeExpr()) ? null : ((Stmt) unit).getInvokeExpr();
            String call = SimpleUnitPrinter.Companion.getStringOf$default(companion, callee, null, invokeExpr, false, 8, null);
            plusAssign(this.outputZh, "进入被调用方法: `" + call + "`");
            plusAssign(this.outputEn, "calling: `" + call + "`");
        } else if (!(node instanceof MergePath)) {
            if (node instanceof AssignLocalPath) {
                m232default(node, unit);
            } else if (node instanceof ModelBind) {
                printModelingBinding((ModelBind) node, unit, sootMethod);
            } else if (node instanceof LiteralPath) {
                Object obj = ((LiteralPath) node).getConst();
                if (obj instanceof IexConst) {
                    switch (WhenMappings.$EnumSwitchMapping$0[((IexConst) obj).getType().ordinal()]) {
                        case PseudoTopologicalOrderer.REVERSE /* 1 */:
                            InvokeExpr invokeExpr2 = ((unit instanceof Stmt ? (Stmt) unit : null) == null || !((Stmt) unit).containsInvokeExpr()) ? null : ((Stmt) unit).getInvokeExpr();
                            InvokeExpr invokeExpr3 = invokeExpr2;
                            if (invokeExpr3 != null && indexedValue.getIndex() == 0) {
                                plusAssign(this.outputZh, "污点源 Source 点: ");
                                plusAssign(this.outputEn, "Taint source: ");
                                write(SimpleUnitPrinter.Companion.getStringOf$default(SimpleUnitPrinter.Companion, null, null, invokeExpr3, false, 8, null));
                                break;
                            } else {
                                clean();
                                return this;
                            }
                            break;
                        case 2:
                            clean();
                            return this;
                        default:
                            plusAssign(this.outputZh, ((IexConst) obj).getType() + " 类型的常量: ");
                            plusAssign(this.outputEn, ((IexConst) obj).getType() + " type constant: ");
                            write("`" + ((IexConst) obj).getConst() + "`");
                            break;
                    }
                } else if (obj instanceof Constant) {
                    Type type = ((Constant) obj).getType();
                    Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                    String ty = EventPrinterKt.getPname(type);
                    plusAssign(this.outputZh, ty + " 类型的常量: ");
                    plusAssign(this.outputEn, ty + " type constant: ");
                    write(String.valueOf(obj));
                }
            } else if (!(node instanceof GetEdgePath) && (node instanceof SetEdgePath)) {
                m232default(node, unit);
            } else {
                m232default(node, unit);
            }
        }
        return this;
    }

    @Nullable
    public final Map<Language, String> getMessage() {
        if (this.outputEn.length() == 0) {
            return null;
        }
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(Language.EN, this.prefix + " " + this.outputEn), TuplesKt.to(Language.ZH, this.prefix + " " + this.outputZh)});
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:23:0x00cd
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    private final boolean printParameterNameByIndex(soot.SootMethod r7, int r8) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.printer.EventPrinter.printParameterNameByIndex(soot.SootMethod, int):boolean");
    }
}
