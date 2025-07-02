package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.analysis.ACheckCallAnalysis;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CallerSiteCBImpl;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.AnyNewExpr;
import soot.jimple.IntConstant;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.StringConstant;

/* compiled from: WEnum.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u001a2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0016\u001a\u00020\u0017*\u00100\u0018j\f\u0012\b\u0012\u00060\u0002j\u0002`\u0003`\u0019H\u0016RU\u0010\u0006\u001a<\u0012\u0018\u0012\u0016\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0012\u0004\u0012\u00020\u000b0\b\u0012\u001e\u0012\u001c\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\f0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WEnum;", "Lcn/sast/dataflow/interprocedural/analysis/SummaryHandlePackage;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "invokeEnumInitExpr", "", "Lkotlin/Pair;", "Lsoot/SootClass;", "kotlin.jvm.PlatformType", "", "Lkotlin/Triple;", "", "getInvokeEnumInitExpr", "()Ljava/util/Map;", "invokeEnumInitExpr$delegate", "Lkotlin/Lazy;", "nameField", "Lsoot/SootField;", "getNameField", "()Lsoot/SootField;", "register", "", "Lcn/sast/dataflow/interprocedural/analysis/ACheckCallAnalysis;", "Lcn/sast/dataflow/interprocedural/analysis/AnalysisInSummary;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWEnum.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WEnum.kt\ncn/sast/dataflow/interprocedural/override/lang/WEnum\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,87:1\n1368#2:88\n1454#2,5:89\n774#2:94\n865#2,2:95\n1454#2,2:97\n1611#2,9:99\n1863#2:108\n1864#2:110\n1620#2:111\n1456#2,3:112\n1202#2,2:115\n1230#2,4:117\n1#3:109\n44#4:121\n*S KotlinDebug\n*F\n+ 1 WEnum.kt\ncn/sast/dataflow/interprocedural/override/lang/WEnum\n*L\n25#1:88\n25#1:89,5\n25#1:94\n25#1:95,2\n25#1:97,2\n26#1:99,9\n26#1:108\n26#1:110\n26#1:111\n25#1:112,3\n39#1:115,2\n39#1:117,4\n26#1:109\n62#1:121\n*E\n"})
/* loaded from: WEnum.class */
public final class WEnum implements SummaryHandlePackage<IValue> {

    @NotNull
    private final Lazy invokeEnumInitExpr$delegate = LazyKt.lazy(WEnum::invokeEnumInitExpr_delegate$lambda$5);

    @Nullable
    private final SootField nameField = Scene.v().grabField("<java.lang.Enum: java.lang.String name>");

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(WEnum::logger$lambda$9);

    /* compiled from: WEnum.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/WEnum$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/override/lang/WEnum;", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: WEnum$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final WEnum v() {
            Type typeV = RefType.v("java.lang.String");
            Intrinsics.checkNotNullExpressionValue(typeV, "v(...)");
            SootUtilsKt.getOrMakeField("java.lang.Enum", "name", typeV);
            return new WEnum();
        }
    }

    private static final Unit logger$lambda$9() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Map<Pair<SootClass, String>, Triple<SootClass, String, Integer>> getInvokeEnumInitExpr() {
        return (Map) this.invokeEnumInitExpr$delegate.getValue();
    }

    private static final Map invokeEnumInitExpr_delegate$lambda$5() {
        Triple triple;
        String enumName;
        Iterable classes = Scene.v().getClasses();
        Intrinsics.checkNotNullExpressionValue(classes, "getClasses(...)");
        Iterable $this$flatMap$iv = classes;
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
            if (((SootMethod) element$iv$iv).hasActiveBody()) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable $this$flatMap$iv2 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        Iterator it2 = $this$flatMap$iv2.iterator();
        while (it2.hasNext()) {
            Iterable units = ((SootMethod) it2.next()).getActiveBody().getUnits();
            Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
            Iterable $this$mapNotNull$iv = units;
            Collection destination$iv$iv4 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                InvokeStmt invokeStmt = (soot.Unit) element$iv$iv$iv;
                if ((invokeStmt instanceof InvokeStmt) && invokeStmt.containsInvokeExpr() && invokeStmt.getInvokeExpr().getMethod().getDeclaringClass().isEnum() && Intrinsics.areEqual(invokeStmt.getInvokeExpr().getMethod().getSubSignature(), "void <init>(java.lang.String,int)")) {
                    List args = invokeStmt.getInvokeExpr().getArgs();
                    Intrinsics.checkNotNull(args);
                    StringConstant stringConstant = (Value) args.get(0);
                    IntConstant intConstant = (Value) args.get(1);
                    StringConstant stringConstant2 = stringConstant instanceof StringConstant ? stringConstant : null;
                    if (stringConstant2 == null || (enumName = stringConstant2.value) == null) {
                        triple = null;
                    } else {
                        IntConstant intConstant2 = intConstant instanceof IntConstant ? intConstant : null;
                        if (intConstant2 != null) {
                            int id = intConstant2.value;
                            triple = new Triple(invokeStmt.getInvokeExpr().getMethod().getDeclaringClass(), enumName, Integer.valueOf(id));
                        } else {
                            triple = null;
                        }
                    }
                } else {
                    triple = null;
                }
                if (triple != null) {
                    destination$iv$iv4.add(triple);
                }
            }
            Iterable list$iv$iv2 = (List) destination$iv$iv4;
            CollectionsKt.addAll(destination$iv$iv3, list$iv$iv2);
        }
        Iterable $this$associateBy$iv = (List) destination$iv$iv3;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv5 = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv2 : $this$associateBy$iv) {
            Triple it3 = (Triple) element$iv$iv2;
            destination$iv$iv5.put(TuplesKt.to(it3.getFirst(), it3.getSecond()), element$iv$iv2);
        }
        return destination$iv$iv5;
    }

    @Nullable
    public final SootField getNameField() {
        return this.nameField;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.SummaryHandlePackage
    public void register(@NotNull ACheckCallAnalysis $this$register) {
        Intrinsics.checkNotNullParameter($this$register, "<this>");
        if (this.nameField == null) {
            logger.error(WEnum::register$lambda$6);
        } else {
            $this$register.evalCallAtCaller("<java.lang.Enum: java.lang.Enum valueOf(java.lang.Class,java.lang.String)>", (v1) -> {
                return register$lambda$8(r2, v1);
            });
            $this$register.registerClassAllWrapper("java.lang.Enum");
        }
    }

    private static final Object register$lambda$6() {
        return "<java.lang.Enum: java.lang.String name> not exist";
    }

    private static final Unit register$lambda$8(WEnum this$0, CallerSiteCBImpl.EvalCall $this$evalCallAtCaller) {
        Intrinsics.checkNotNullParameter($this$evalCallAtCaller, "$this$evalCallAtCaller");
        IHeapValues classP = $this$evalCallAtCaller.arg(0);
        IHeapValues nameP = $this$evalCallAtCaller.arg(1);
        IOpCalculator valueOfOp = $this$evalCallAtCaller.getHf().resolveOp($this$evalCallAtCaller.getEnv(), classP);
        valueOfOp.resolve((v3, v4, v5) -> {
            return register$lambda$8$lambda$7(r1, r2, r3, v3, v4, v5);
        });
        Type refType = Scene.v().getRefType("java.lang.Enum");
        Intrinsics.checkNotNullExpressionValue(refType, "getRefType(...)");
        valueOfOp.putSummaryIfNotConcrete(refType, "valueOf");
        $this$evalCallAtCaller.setReturn(valueOfOp.getRes().build());
        return Unit.INSTANCE;
    }

    private static final boolean register$lambda$8$lambda$7(CallerSiteCBImpl.EvalCall $this_evalCallAtCaller, WEnum this$0, IHeapValues $nameP, IOpCalculator $this$valueOf, IHeapValues.Builder ret, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$valueOf, "$this$valueOf");
        Intrinsics.checkNotNullParameter(ret, "ret");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV clazzC = companionVArr[0];
        RefType classValue$default = FactValuesKt.getClassValue$default((IValue) clazzC.getValue(), false, 1, null);
        RefType refType = classValue$default instanceof RefType ? classValue$default : null;
        if (refType == null) {
            return false;
        }
        RefType clazz = refType;
        AnyNewExpr anyNewExprNewNewExpr = Jimple.v().newNewExpr(clazz);
        AbstractHeapFactory<IValue> hf = $this_evalCallAtCaller.getHf();
        HookEnv env = $this_evalCallAtCaller.getEnv();
        AbstractHeapFactory<IValue> hf2 = $this_evalCallAtCaller.getHf();
        AnyNewExprEnv newEnv = $this_evalCallAtCaller.getNewEnv();
        Intrinsics.checkNotNull(anyNewExprNewNewExpr);
        IHeapValues enumNew = hf.push((HeapValuesEnv) env, (HookEnv) hf2.anyNewVal(newEnv, anyNewExprNewNewExpr)).popHV();
        IFact.Builder.DefaultImpls.assignNewExpr$default($this_evalCallAtCaller.getOut(), $this_evalCallAtCaller.getEnv(), "@new", enumNew, false, 8, null);
        IFact.Builder<IValue> out = $this_evalCallAtCaller.getOut();
        HookEnv env2 = $this_evalCallAtCaller.getEnv();
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        SootField field$iv = this$0.nameField;
        out.setFieldNew(env2, "@new", new JSootFieldType(field$iv), $nameP);
        $this_evalCallAtCaller.getOut().kill("@new");
        $this$valueOf.getRes().add(enumNew);
        return true;
    }
}
