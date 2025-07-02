package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.AnyNewValue;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValues;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IIFact;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.analysis.JOperatorC;
import cn.sast.dataflow.interprocedural.analysis.JOperatorHV;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract;
import cn.sast.dataflow.interprocedural.analysis.SummaryValue;
import cn.sast.dataflow.interprocedural.check.checker.CheckerModelingKt;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSetBuilder;
import cn.sast.dataflow.interprocedural.check.heapimpl.ObjectValues;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.utils.UtilsKt;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.AnySubType;
import soot.ArrayType;
import soot.FastHierarchy;
import soot.G;
import soot.NullType;
import soot.PrimType;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.Type;
import soot.Unit;
import soot.UnknownType;
import soot.jimple.AnyNewExpr;
import soot.jimple.BinopExpr;
import soot.jimple.Constant;
import soot.jimple.LengthExpr;
import soot.jimple.NegExpr;
import soot.jimple.NullConstant;
import soot.jimple.NumericConstant;
import soot.jimple.UnopExpr;
import soot.jimple.internal.JIfStmt;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b��\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\n\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000eH\u0016J@\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J,\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016JF\u0010\u001b\u001a\f\u0012\u0004\u0012\u00020\u001d0\u001cj\u0002`\u001e2\u0006\u0010\u0010\u001a\u00020\u00112\"\u0010\u001f\u001a\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00130 2\u0006\u0010!\u001a\u00020\"H\u0016J\u001c\u0010#\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J$\u0010(\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u001dH\u0016J\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010)\u001a\u00020\u001aH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u00102\u001a\u000203H\u0016J\u0018\u0010\u0010\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00102\u001a\u000203H\u0016J\u0012\u00107\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u000308H\u0016JK\u00109\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030:2\u0006\u0010\u0010\u001a\u00020\u00112*\u0010;\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000e0<\"\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010=J$\u0010>\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030?2\u0010\u0010@\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030AH\u0016J#\u0010B\u001a\u0004\u0018\u00010C2\n\u0010D\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010E\u001a\u00020CH\u0016¢\u0006\u0002\u0010FJ#\u0010G\u001a\u0004\u0018\u00010H2\n\u0010D\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010E\u001a\u00020CH\u0016¢\u0006\u0002\u0010IJ$\u0010J\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0010\u0010D\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030KH\u0016J&\u0010L\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030M2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010N\u001a\u00060\u0002j\u0002`\u0003H\u0016J,\u0010L\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030O2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010P\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030KH\u0016J,\u0010L\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030Q2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010P\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000eH\u0016J\u0010\u0010R\u001a\u00020C2\u0006\u0010)\u001a\u00020\u001aH\u0002J(\u0010S\u001a\u0004\u0018\u00010\u00022\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u00022\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020CJH\u0010Z\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010:2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00132\u0006\u0010T\u001a\u00020\u001a2\u0010\u0010[\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000eH\u0016J4\u0010\\\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030:2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010[\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0006\u0010E\u001a\u00020\u001aH\u0016JF\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00020:2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030^2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u001aH\u0016JN\u0010c\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010K2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010`\u001a\u00020d2\u0010\u0010e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030K2\u0010\u0010f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030K2\u0006\u0010b\u001a\u00020\u001aJL\u0010g\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030K2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010`\u001a\u00020d2\u0010\u0010e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030K2\u0010\u0010f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030K2\u0006\u0010b\u001a\u00020\u001aJ`\u0010g\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030:2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00132\u0010\u0010h\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0010\u0010i\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000e2\u0006\u0010`\u001a\u00020d2\u0006\u0010b\u001a\u00020\u001aH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0018\u0010+\u001a\u00060\u0002j\u0002`\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b,\u0010-¨\u0006j"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/HeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/V;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;)V", "getVg", "()Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "empty", "Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "getEmpty", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getField", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "fact", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "base", "field", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "canStore", "receivers", "receiverType", "Lsoot/Type;", "resolve", "Lkotlin/sequences/Sequence;", "", "Lcn/sast/dataflow/interprocedural/check/ExprResType;", "atCall", "Lcn/sast/idfa/check/ICallCB;", "iExpr", "Lcom/feysh/corax/config/api/IExpr;", "anyNewVal", "newExprEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "newExr", "Lsoot/jimple/AnyNewExpr;", "newSummaryVal", "type", "special", "nullConst", "getNullConst", "()Lcn/sast/dataflow/interprocedural/analysis/IValue;", "newConstVal", "Lcn/sast/dataflow/interprocedural/analysis/ConstVal;", "constant", "Lsoot/jimple/Constant;", "node", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "ctx", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "getPathFactory", "Lcn/sast/dataflow/interprocedural/check/PathFactory;", "resolveOp", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "ops", "", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;[Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "newReNewInterface", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "orig", "", "getBooleanValue", "", "v", "checkType", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Boolean;", "getIntValue", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Z)Ljava/lang/Integer;", "single", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "push", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "alloc", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorC;", "value", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "checkTypeHierarchyIsPhantom", "tryCastRef", "toType", "Lsoot/RefLikeType;", "it", "h", "Lsoot/FastHierarchy;", "must", "resolveCast", "fromValues", "resolveInstanceOf", "resolveUnop", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "opValues", "expr", "Lsoot/jimple/UnopExpr;", "resType", "resolveBinopOrNull", "Lsoot/jimple/BinopExpr;", "clop", "crop", "resolveBinop", "op1Values", "op2Values", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1430:1\n1755#2,3:1431\n1#3:1434\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory\n*L\n1150#1:1431,3\n*E\n"})
/* loaded from: HeapFactory.class */
public final class HeapFactory extends AbstractHeapFactory<IValue> {

    @NotNull
    private final IVGlobal vg;

    @NotNull
    private final HeapValues empty;

    @NotNull
    private final IValue nullConst;

    public HeapFactory(@NotNull IVGlobal vg) {
        Intrinsics.checkNotNullParameter(vg, "vg");
        this.vg = vg;
        this.empty = HeapValues.Companion.empty$corax_data_flow();
        ConstVal.Companion companion = ConstVal.Companion;
        NullConstant nullConstantV = NullConstant.v();
        Intrinsics.checkNotNullExpressionValue(nullConstantV, "v(...)");
        NullType nullTypeSoot_NullType = G.v().soot_NullType();
        Intrinsics.checkNotNullExpressionValue(nullTypeSoot_NullType, "soot_NullType(...)");
        this.nullConst = companion.v((Constant) nullConstantV, (Type) nullTypeSoot_NullType);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IVGlobal getVg() {
        return this.vg;
    }

    @NotNull
    public final HeapValues getEmpty() {
        return this.empty;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory
    @NotNull
    public IHeapValues<IValue> empty() {
        return this.empty;
    }

    @NotNull
    public final IHeapValues<IValue> getField(@NotNull HeapValuesEnv env, @NotNull IFact.Builder<IValue> builder, @NotNull IHeapValues<IValue> iHeapValues, @NotNull JFieldType field) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(builder, "fact");
        Intrinsics.checkNotNullParameter(iHeapValues, "base");
        Intrinsics.checkNotNullParameter(field, "field");
        IFact.Builder.DefaultImpls.assignNewExpr$default(builder, env, "@base", iHeapValues, false, 8, null);
        builder.getField(env, "@res", "@base", field, true);
        IHeapValues value = builder.getTargetsUnsafe("@res");
        builder.kill("@res");
        builder.kill("@base");
        return value == null ? this.empty : value;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IHeapValues<IValue> canStore(@NotNull IHeapValues<IValue> iHeapValues, @NotNull Type receiverType) {
        Type type;
        boolean zCanStoreType;
        Intrinsics.checkNotNullParameter(iHeapValues, "receivers");
        Intrinsics.checkNotNullParameter(receiverType, "receiverType");
        IHeapValues.Builder receiversValues = emptyBuilder();
        FastHierarchy hierarchy = Scene.v().getOrMakeFastHierarchy();
        for (CompanionV receiver : iHeapValues) {
            IValue v = receiver.getValue();
            if (v.typeIsConcrete()) {
                zCanStoreType = hierarchy.canStoreType(v.getType(), receiverType);
            } else if (!(receiverType instanceof RefLikeType)) {
                zCanStoreType = true;
            } else if ((receiverType instanceof RefType) && ((RefType) receiverType).getSootClass().isPhantom()) {
                zCanStoreType = true;
            } else {
                if (v.getType() instanceof RefType) {
                    RefType type2 = v.getType();
                    Intrinsics.checkNotNull(type2, "null cannot be cast to non-null type soot.RefType");
                    type = (Type) AnySubType.v(type2);
                } else {
                    type = v.getType();
                }
                Type type3 = type;
                zCanStoreType = Intrinsics.areEqual(v.getType(), receiverType) || hierarchy.canStoreType(type3, receiverType);
            }
            boolean canStore = zCanStoreType;
            if (canStore) {
                receiversValues.add(receiver);
            }
        }
        return receiversValues.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public Sequence<Object> resolve(@NotNull HeapValuesEnv env, @NotNull ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB, @NotNull IExpr iExpr) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iCallCB, "atCall");
        Intrinsics.checkNotNullParameter(iExpr, "iExpr");
        HeapFactory$resolve$visitor$1 visitor = new HeapFactory$resolve$visitor$1(this, env, iCallCB);
        return (Sequence) iExpr.accept(visitor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IValue anyNewVal(@NotNull AnyNewExprEnv newExprEnv, @NotNull AnyNewExpr newExr) {
        Intrinsics.checkNotNullParameter(newExprEnv, "newExprEnv");
        Intrinsics.checkNotNullParameter(newExr, "newExr");
        Unit node = newExprEnv.getNode();
        String signature = newExprEnv.getMethod().getSignature();
        Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
        return new AnyNewValue(node, signature, newExr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IValue newSummaryVal(@NotNull HeapValuesEnv env, @NotNull Type type, @NotNull Object special) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(special, "special");
        return SummaryValue.Companion.v(type, env.getNode(), special);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IValue getNullConst() {
        return this.nullConst;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    /* renamed from: newConstVal, reason: merged with bridge method [inline-methods] */
    public IValue newConstVal2(@NotNull Constant constant, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(constant, "constant");
        Intrinsics.checkNotNullParameter(type, "type");
        return ConstVal.Companion.v(constant, type);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public HeapValuesEnv env(@NotNull Unit node) {
        Intrinsics.checkNotNullParameter(node, "node");
        return new HeapValuesEnvImpl(node);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public HookEnv env(@NotNull AIContext ctx, @NotNull Unit node) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(node, "node");
        return new HookEnv(ctx, node);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public PathFactory<IValue> getPathFactory() {
        return new PathFactoryImpl();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IOpCalculator<IValue> resolveOp(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue>... iHeapValuesArr) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValuesArr, "ops");
        return new OpCalculator(env, this, (IHeapValues[]) Arrays.copyOf(iHeapValuesArr, iHeapValuesArr.length));
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IReNew<IValue> newReNewInterface(@NotNull final Set<IValue> set) {
        Intrinsics.checkNotNullParameter(set, "orig");
        return new IReNew<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.HeapFactory.newReNewInterface.1
            private final Map<IValue, IValue> newValueMap = new LinkedHashMap();

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public CompanionV<IValue> checkNeedReplace(CompanionV<IValue> companionV) {
                return IReNew.DefaultImpls.checkNeedReplace((IReNew) this, (CompanionV) companionV);
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IReNew<IValue> context(Object value) {
                return IReNew.DefaultImpls.context(this, value);
            }

            public final Map<IValue, IValue> getNewValueMap() {
                return this.newValueMap;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IValue checkNeedReplace(IValue old) {
                Intrinsics.checkNotNullParameter(old, "old");
                if (FactValuesKt.getLeastExpr()) {
                    return null;
                }
                if ((!(old instanceof SummaryValue) && !(old instanceof AnyNewValue)) || set.contains(old)) {
                    return null;
                }
                synchronized (this) {
                    IValue iValue = this.newValueMap.get(old);
                    if (iValue != null) {
                        return iValue;
                    }
                    IValue iValueClone = old.clone();
                    this.newValueMap.put(old, iValueClone);
                    return iValueClone;
                }
            }
        };
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @Nullable
    public Boolean getBooleanValue(@NotNull IValue v, boolean checkType) {
        Intrinsics.checkNotNullParameter(v, "v");
        return FactValuesKt.getBooleanValue(v, checkType);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @Nullable
    public Integer getIntValue(@NotNull IValue v, boolean checkType) {
        Intrinsics.checkNotNullParameter(v, "v");
        return FactValuesKt.getIntValue(v, checkType);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory
    @NotNull
    public IHeapValues<IValue> single(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "v");
        return empty().plus(companionV);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public JOperatorV<IValue> push(@NotNull HeapValuesEnv env, @NotNull IValue alloc) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(alloc, "alloc");
        return new OperatorPathFactory(this, env, alloc, null, 8, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public JOperatorC<IValue> push(@NotNull HeapValuesEnv env, @NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(companionV, "value");
        final Ref.ObjectRef res = new Ref.ObjectRef();
        res.element = companionV;
        return new JOperatorC<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.HeapFactory.push.1
            @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorC
            public JOperatorC<IValue> markEntry() {
                return this;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorC
            public CompanionV<IValue> pop() {
                return (CompanionV) res.element;
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorC
            public IHeapValues<IValue> popHV() {
                return this.getEmpty().plus((CompanionV) res.element);
            }
        };
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public JOperatorHV<IValue> push(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        return new JOperatorHVImpl(this, env, iHeapValues);
    }

    private final boolean checkTypeHierarchyIsPhantom(Type type) {
        if (type instanceof ArrayType) {
            Type elementType = ((ArrayType) type).getElementType();
            Intrinsics.checkNotNullExpressionValue(elementType, "getElementType(...)");
            return checkTypeHierarchyIsPhantom(elementType);
        }
        if (!(type instanceof RefType) || !((RefType) type).hasSootClass()) {
            return false;
        }
        SootClass sootClass = ((RefType) type).getSootClass();
        Intrinsics.checkNotNullExpressionValue(sootClass, "getSootClass(...)");
        Iterable $this$any$iv = UtilsKt.findAncestors(sootClass);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SootClass it = (SootClass) element$iv;
            if (it.isPhantom()) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final IValue tryCastRef(@NotNull RefLikeType toType, @NotNull IValue it, @NotNull FastHierarchy h, boolean must) {
        Intrinsics.checkNotNullParameter(toType, "toType");
        Intrinsics.checkNotNullParameter(it, "it");
        Intrinsics.checkNotNullParameter(h, "h");
        Type fromType = it.getType();
        if (fromType instanceof UnknownType) {
            return it;
        }
        boolean fromIsPhantom = checkTypeHierarchyIsPhantom(fromType);
        boolean toIsPhantom = checkTypeHierarchyIsPhantom((Type) toType);
        boolean canStore = h.canStoreType(fromType, (Type) toType);
        if (it.typeIsConcrete() && (canStore || fromIsPhantom || toIsPhantom)) {
            return it;
        }
        if (!must && !it.typeIsConcrete()) {
            if (fromIsPhantom || toIsPhantom) {
                return it.copy((Type) toType);
            }
            if (!canStore && h.canStoreType((Type) toType, fromType)) {
                return it.copy((Type) toType);
            }
            if (canStore && h.canStoreType((Type) toType, fromType)) {
                return it;
            }
            return null;
        }
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @Nullable
    public IOpCalculator<IValue> resolveCast(@NotNull HeapValuesEnv env, @NotNull IFact.Builder<IValue> builder, @NotNull Type toType, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(builder, "fact");
        Intrinsics.checkNotNullParameter(toType, "toType");
        Intrinsics.checkNotNullParameter(iHeapValues, "fromValues");
        IOpCalculator unop = resolveOp(env, iHeapValues);
        if (toType instanceof PrimType) {
            unop.resolve((v4, v5, v6) -> {
                return resolveCast$lambda$1(r1, r2, r3, r4, v4, v5, v6);
            });
            return unop;
        }
        if (!(toType instanceof RefLikeType)) {
            return null;
        }
        FastHierarchy h = Scene.v().getOrMakeFastHierarchy();
        final Map replaceMap = new LinkedHashMap();
        unop.resolve((v6, v7, v8) -> {
            return resolveCast$lambda$2(r1, r2, r3, r4, r5, r6, v6, v7, v8);
        });
        IReNew<IValue> iReNew = new IReNew<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.HeapFactory$resolveCast$rpFactory$1
            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public CompanionV<IValue> checkNeedReplace(CompanionV<IValue> companionV) {
                return IReNew.DefaultImpls.checkNeedReplace((IReNew) this, (CompanionV) companionV);
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IReNew<IValue> context(Object value) {
                return IReNew.DefaultImpls.context(this, value);
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IValue checkNeedReplace(IValue old) {
                Intrinsics.checkNotNullParameter(old, "old");
                return replaceMap.get(old);
            }
        };
        if (!(builder instanceof PointsToGraphBuilderAbstract)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        ((PointsToGraphBuilderAbstract) builder).apply(iReNew);
        for (Map.Entry entry : replaceMap.entrySet()) {
            IValue from = (IValue) entry.getKey();
            IValue to = (IValue) entry.getValue();
            if ((toType instanceof ArrayType) && !(from.getType() instanceof ArrayType) && ((PointsToGraphBuilderAbstract) builder).getArray(to) == null) {
                IData valueData = ((PointsToGraphBuilderAbstract) builder).getValueData(to, BuiltInModelT.Element);
                ObjectValues collection = valueData instanceof ObjectValues ? (ObjectValues) valueData : null;
                if (collection != null) {
                    CompanionV array = push(env, to).markOfCastTo((RefLikeType) toType).pop();
                    IFact.Builder.DefaultImpls.assignNewExpr$default(builder, env, "@arr", this.empty.plus(array), false, 8, null);
                    ((PointsToGraphBuilderAbstract) builder).setArrayValueNew(env, "@arr", null, collection.getValues());
                    ((PointsToGraphBuilderAbstract) builder).kill("@arr");
                }
            }
        }
        return unop;
    }

    private static final boolean resolveCast$lambda$1(HeapFactory $hf, Type $toType, HeapValuesEnv $env, IFact.Builder $fact, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        ConstVal constValNewSummaryVal;
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        ConstVal constVal = op instanceof ConstVal ? (ConstVal) op : null;
        Constant v = constVal != null ? constVal.getV() : null;
        NumericConstant nc = v instanceof NumericConstant ? (NumericConstant) v : null;
        if (nc != null) {
            Constant constantCastTo = SootUtilsKt.castTo(nc, $toType);
            if (constantCastTo == null) {
                return false;
            }
            constValNewSummaryVal = $hf.newConstVal2(constantCastTo, $toType);
        } else {
            constValNewSummaryVal = $hf.newSummaryVal($env, $toType, "castValue");
        }
        IValue casted = constValNewSummaryVal;
        $fact.copyValueData(op, casted);
        res.add($hf.push($env, casted).markOfCastTo((PrimType) $toType).pop());
        return true;
    }

    private static final boolean resolveCast$lambda$2(HeapFactory this$0, Type $toType, FastHierarchy $h, HeapFactory $hf, HeapValuesEnv $env, Map $replaceMap, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        Intrinsics.checkNotNull($h);
        IValue casted = this$0.tryCastRef((RefLikeType) $toType, op, $h, false);
        if (casted == null) {
            return true;
        }
        CompanionV to = $hf.push($env, casted).markOfCastTo((RefLikeType) $toType).pop();
        if (!Intrinsics.areEqual(casted, op)) {
            $replaceMap.put(op, casted);
        }
        res.add(to);
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IOpCalculator<IValue> resolveInstanceOf(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues, @NotNull Type checkType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "fromValues");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        IOpCalculator unop = resolveOp(env, iHeapValues);
        FastHierarchy h = Scene.v().getOrMakeFastHierarchy();
        unop.resolve((v4, v5, v6) -> {
            return resolveInstanceOf$lambda$3(r1, r2, r3, r4, v4, v5, v6);
        });
        Type typeSoot_BooleanType = G.v().soot_BooleanType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
        unop.putSummaryIfNotConcrete(typeSoot_BooleanType, "instanceOfValue");
        return unop;
    }

    private static final boolean resolveInstanceOf$lambda$3(FastHierarchy $h, Type $checkType, HeapFactory $hf, HeapValuesEnv $env, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        Type fromType = op.getType();
        if (fromType == null || (fromType instanceof UnknownType)) {
            return false;
        }
        boolean canStore = $h.canStoreType(fromType, $checkType);
        if (op.typeIsConcrete() || canStore) {
            res.add($hf.push($env, $hf.toConstVal(Boolean.valueOf(canStore))).markOfInstanceOf().pop());
            return true;
        }
        if ($h.canStoreType($checkType, fromType)) {
            return false;
        }
        res.add($hf.push($env, $hf.toConstVal(false)).markOfInstanceOf().pop());
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IOpCalculator<IValue> resolveUnop(@NotNull HeapValuesEnv env, @NotNull IIFact<IValue> iIFact, @NotNull IHeapValues<IValue> iHeapValues, @NotNull UnopExpr expr, @NotNull Type resType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iIFact, "fact");
        Intrinsics.checkNotNullParameter(iHeapValues, "opValues");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(resType, "resType");
        IOpCalculator unop = resolveOp(env, iHeapValues);
        if (expr instanceof NegExpr) {
            unop.resolve((v4, v5, v6) -> {
                return resolveUnop$lambda$4(r1, r2, r3, r4, v4, v5, v6);
            });
            Type typeSoot_BooleanType = G.v().soot_BooleanType();
            Intrinsics.checkNotNullExpressionValue(typeSoot_BooleanType, "soot_BooleanType(...)");
            unop.putSummaryIfNotConcrete(typeSoot_BooleanType, expr);
        } else if (expr instanceof LengthExpr) {
            unop.resolve((v3, v4, v5) -> {
                return resolveUnop$lambda$5(r1, r2, r3, v3, v4, v5);
            });
            unop.putSummaryIfNotConcrete(resType, expr);
        } else {
            unop.putSummaryValue(resType, expr);
        }
        return unop;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean resolveUnop$lambda$4(HeapFactory $hf, HeapValuesEnv $env, Type $resType, UnopExpr $expr, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IValue op = (IValue) cop.getValue();
        ConstVal constVal = op instanceof ConstVal ? (ConstVal) op : null;
        Constant v = constVal != null ? constVal.getV() : null;
        NumericConstant numericConstant = v instanceof NumericConstant ? (NumericConstant) v : null;
        if (numericConstant == null) {
            return false;
        }
        NumericConstant nc = numericConstant;
        NumericConstant numericConstantNegate = nc.negate();
        Intrinsics.checkNotNullExpressionValue(numericConstantNegate, "negate(...)");
        res.add($hf.push($env, (IValue) $hf.newConstVal2((Constant) numericConstantNegate, $resType)).markOfNegExpr((NegExpr) $expr, cop).pop());
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean resolveUnop$lambda$5(IIFact $fact, HeapFactory $hf, HeapValuesEnv $env, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV cop = companionVArr[0];
        IHeapValues length = $fact.getArrayLength(cop.getValue());
        if (length == null) {
            return false;
        }
        res.add($hf.push($env, (IHeapValues<IValue>) length).markOfArrayLength(cop).pop());
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:30:0x00a8
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.Nullable
    public final cn.sast.dataflow.interprocedural.analysis.CompanionV<cn.sast.dataflow.interprocedural.analysis.IValue> resolveBinopOrNull(@org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv r8, @org.jetbrains.annotations.NotNull soot.jimple.BinopExpr r9, @org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.CompanionV<cn.sast.dataflow.interprocedural.analysis.IValue> r10, @org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.CompanionV<cn.sast.dataflow.interprocedural.analysis.IValue> r11, @org.jetbrains.annotations.NotNull soot.Type r12) throws java.lang.ArithmeticException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory.resolveBinopOrNull(cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv, soot.jimple.BinopExpr, cn.sast.dataflow.interprocedural.analysis.CompanionV, cn.sast.dataflow.interprocedural.analysis.CompanionV, soot.Type):cn.sast.dataflow.interprocedural.analysis.CompanionV");
    }

    @NotNull
    public final CompanionV<IValue> resolveBinop(@NotNull HeapValuesEnv env, @NotNull BinopExpr expr, @NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue> companionV2, @NotNull Type resType) throws ArithmeticException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(companionV, "clop");
        Intrinsics.checkNotNullParameter(companionV2, "crop");
        Intrinsics.checkNotNullParameter(resType, "resType");
        CompanionV<IValue> companionVResolveBinopOrNull = resolveBinopOrNull(env, expr, companionV, companionV2, resType);
        return companionVResolveBinopOrNull == null ? push(env, newSummaryVal(env, resType, (Object) expr)).markSootBinOp(expr, companionV, companionV2).pop() : companionVResolveBinopOrNull;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory
    @NotNull
    public IOpCalculator<IValue> resolveBinop(@NotNull HeapValuesEnv env, @NotNull IFact.Builder<IValue> builder, @NotNull IHeapValues<IValue> iHeapValues, @NotNull IHeapValues<IValue> iHeapValues2, @NotNull BinopExpr expr, @NotNull Type resType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(builder, "fact");
        Intrinsics.checkNotNullParameter(iHeapValues, "op1Values");
        Intrinsics.checkNotNullParameter(iHeapValues2, "op2Values");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(resType, "resType");
        IOpCalculator binop = resolveOp(env, iHeapValues, iHeapValues2);
        Set types = new LinkedHashSet();
        binop.resolve((v6, v7, v8) -> {
            return resolveBinop$lambda$8(r1, r2, r3, r4, r5, r6, v6, v7, v8);
        });
        binop.putSummaryIfNotConcrete(resType, expr);
        if (env.getNode() instanceof JIfStmt) {
            return binop;
        }
        IHeapValues res = binop.getRes().build();
        resolveBinop$taint(this, env, builder, res, types, TaintProperty.INSTANCE, false);
        return binop;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean resolveBinop$lambda$8(HeapFactory heapFactory, HeapValuesEnv $env, BinopExpr $expr, Type $resType, IFact.Builder $fact, Set $types, IOpCalculator $this$resolve, IHeapValues.Builder res, CompanionV[] companionVArr) throws ArithmeticException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter($this$resolve, "$this$resolve");
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV clop = companionVArr[0];
        CompanionV crop = companionVArr[1];
        CompanionV r = heapFactory.resolveBinop($env, $expr, clop, crop, $resType);
        Object valueData = $fact.getValueData(clop.getValue(), CheckerModelingKt.getKeyTaintProperty());
        ImmutableElementSet immutableElementSet = valueData instanceof ImmutableElementSet ? (ImmutableElementSet) valueData : null;
        if (immutableElementSet != null) {
            ImmutableElementSet it = immutableElementSet;
            $types.add(it);
        }
        Object valueData2 = $fact.getValueData(crop.getValue(), CheckerModelingKt.getKeyTaintProperty());
        ImmutableElementSet immutableElementSet2 = valueData2 instanceof ImmutableElementSet ? (ImmutableElementSet) valueData2 : null;
        if (immutableElementSet2 != null) {
            ImmutableElementSet it2 = immutableElementSet2;
            $types.add(it2);
        }
        if (r == null) {
            return false;
        }
        res.add(r);
        return true;
    }

    private static final void resolveBinop$taint(HeapFactory hf, HeapValuesEnv $env, IFact.Builder<IValue> builder, IHeapValues<IValue> iHeapValues, Set<ImmutableElementSet<Object>> set, IClassField field, boolean append) {
        IOpCalculator op = hf.resolveOp($env, iHeapValues);
        boolean append1 = !iHeapValues.isSingle() || append;
        op.resolve((v6, v7, v8) -> {
            return resolveBinop$taint$lambda$10(r1, r2, r3, r4, r5, r6, v6, v7, v8);
        });
    }

    private static final boolean resolveBinop$taint$lambda$10(boolean $append1, IFact.Builder $fact, IClassField $field, Set $values, HeapValuesEnv $env, HeapFactory $hf, IOpCalculator $this$solve, IHeapValues.Builder builder, CompanionV[] companionVArr) {
        ImmutableElementSet immutableElementSet;
        ImmutableElementSet immutableElementSetBuild2;
        Intrinsics.checkNotNullParameter($this$solve, "$this$solve");
        Intrinsics.checkNotNullParameter(builder, "<unused var>");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV base = companionVArr[0];
        if ($append1) {
            Object valueData = $fact.getValueData(base.getValue(), $field);
            immutableElementSet = valueData instanceof ImmutableElementSet ? (ImmutableElementSet) valueData : null;
        } else {
            immutableElementSet = null;
        }
        ImmutableElementSet setDate = immutableElementSet;
        if ((setDate == null || setDate.isEmpty()) && $values.size() == 1) {
            Object objFirst = CollectionsKt.first($values);
            Intrinsics.checkNotNull(objFirst, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet<kotlin.Any>");
            immutableElementSetBuild2 = (ImmutableElementSet) objFirst;
        } else {
            immutableElementSetBuild2 = null;
        }
        if (immutableElementSetBuild2 == null) {
            ImmutableElementSet immutableElementSet2 = setDate;
            if (immutableElementSet2 == null) {
                immutableElementSet2 = new ImmutableElementSet(null, null, 3, null);
            }
            ImmutableElementSetBuilder setBuilder = immutableElementSet2.builder2();
            Iterator it = $values.iterator();
            while (it.hasNext()) {
                ImmutableElementSet typeValues = (ImmutableElementSet) it.next();
                ImmutableElementSet immutableElementSet3 = typeValues instanceof ImmutableElementSet ? typeValues : null;
                if (immutableElementSet3 != null) {
                    ImmutableElementSet set = immutableElementSet3;
                    for (Object e : set.getElement()) {
                        IHeapValues v = set.get($hf, e);
                        setBuilder.set($hf, $env, e, v, $append1);
                    }
                }
            }
            immutableElementSetBuild2 = setBuilder.build2();
        }
        ImmutableElementSet immutableElementSet4 = immutableElementSetBuild2;
        if (((IValue) base.getValue()) instanceof ConstVal) {
            return false;
        }
        $fact.setValueData($env, base.getValue(), $field, immutableElementSet4);
        return true;
    }
}
