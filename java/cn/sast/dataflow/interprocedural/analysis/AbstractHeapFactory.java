package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.check.PathFactory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.IExpr;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;
import soot.Unit;
import soot.jimple.AnyNewExpr;
import soot.jimple.BinopExpr;
import soot.jimple.Constant;
import soot.jimple.UnopExpr;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Þ\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b&\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\u0015\u001a\u00028��2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&¢\u0006\u0002\u0010\u001aJ%\u0010\u001b\u001a\u00028��2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H&¢\u0006\u0002\u0010\"J$\u0010#\u001a\b\u0012\u0004\u0012\u00028��0$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028��0$2\u0006\u0010&\u001a\u00020\u001fH&J\u001d\u0010'\u001a\u00028��2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u001fH&¢\u0006\u0002\u0010*J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020/H&J\u0018\u0010\u001c\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010.\u001a\u00020/H&J!\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00028��2\b\b\u0002\u00106\u001a\u000204H&¢\u0006\u0002\u00107J!\u00108\u001a\u0004\u0018\u0001092\u0006\u00105\u001a\u00028��2\b\b\u0002\u00106\u001a\u000204H&¢\u0006\u0002\u0010:J\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00028��0<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028��0>H&J#\u0010?\u001a\b\u0012\u0004\u0012\u00028��0@2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010A\u001a\u00028��H&¢\u0006\u0002\u0010BJ$\u0010?\u001a\b\u0012\u0004\u0012\u00028��0C2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010D\u001a\b\u0012\u0004\u0012\u00028��0EH&J$\u0010?\u001a\b\u0012\u0004\u0012\u00028��0F2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010D\u001a\b\u0012\u0004\u0012\u00028��0$H&J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00028��0HH&J\u0013\u0010I\u001a\u00028��2\u0006\u00105\u001a\u00020!¢\u0006\u0002\u0010JJ?\u0010K\u001a\b\u0012\u0004\u0012\u00028��0L2\u0006\u0010\u001c\u001a\u00020\u001d2\"\u0010M\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010$0N\"\n\u0012\u0004\u0012\u00028��\u0018\u00010$H&¢\u0006\u0002\u0010OJ<\u0010P\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010L2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028��0\u00062\u0006\u0010R\u001a\u00020\u001f2\f\u0010S\u001a\b\u0012\u0004\u0012\u00028��0$H&J,\u0010T\u001a\b\u0012\u0004\u0012\u00028��0L2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010S\u001a\b\u0012\u0004\u0012\u00028��0$2\u0006\u00106\u001a\u00020\u001fH&JB\u0010U\u001a\b\u0012\u0004\u0012\u00028��0L2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028��0V2\f\u0010W\u001a\b\u0012\u0004\u0012\u00028��0$2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\u001fH&JP\u0010[\u001a\b\u0012\u0004\u0012\u00028��0L2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028��0\u00062\f\u0010\\\u001a\b\u0012\u0004\u0012\u00028��0$2\f\u0010]\u001a\b\u0012\u0004\u0012\u00028��0$2\u0006\u0010X\u001a\u00020^2\u0006\u0010Z\u001a\u00020\u001fH&J>\u0010_\u001a\b\u0012\u0004\u0012\u00020!0`2\u0006\u0010\u001c\u001a\u00020\u001d2\u001e\u0010a\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0$\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0\u00060b2\u0006\u0010d\u001a\u00020eH&R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006X\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010+\u001a\u00028��X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "<init>", "()V", "constantPoolObjectData", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "getConstantPoolObjectData", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setConstantPoolObjectData", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "setLogger", "(Lmu/KLogger;)V", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "getVg", "()Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "anyNewVal", "newExprEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "newExr", "Lsoot/jimple/AnyNewExpr;", "(Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;Lsoot/jimple/AnyNewExpr;)Ljava/lang/Object;", "newSummaryVal", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "type", "Lsoot/Type;", "special", "", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lsoot/Type;Ljava/lang/Object;)Ljava/lang/Object;", "canStore", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "receivers", "receiverType", "newConstVal", "constant", "Lsoot/jimple/Constant;", "(Lsoot/jimple/Constant;Lsoot/Type;)Ljava/lang/Object;", "nullConst", "getNullConst", "()Ljava/lang/Object;", "node", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "ctx", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "getBooleanValue", "", "v", "checkType", "(Ljava/lang/Object;Z)Ljava/lang/Boolean;", "getIntValue", "", "(Ljava/lang/Object;Z)Ljava/lang/Integer;", "newReNewInterface", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "orig", "", "push", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "alloc", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorC;", "value", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "getPathFactory", "Lcn/sast/dataflow/interprocedural/check/PathFactory;", "toConstVal", "(Ljava/lang/Object;)Ljava/lang/Object;", "resolveOp", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "ops", "", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;[Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "resolveCast", "fact", "toType", "fromValues", "resolveInstanceOf", "resolveUnop", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "opValues", "expr", "Lsoot/jimple/UnopExpr;", "resType", "resolveBinop", "op1Values", "op2Values", "Lsoot/jimple/BinopExpr;", "resolve", "Lkotlin/sequences/Sequence;", "atCall", "Lcn/sast/idfa/check/ICallCB;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "iExpr", "Lcom/feysh/corax/config/api/IExpr;", "corax-data-flow"})
/* loaded from: AbstractHeapFactory.class */
public abstract class AbstractHeapFactory<V> implements IHeapValuesFactory<V> {
    public IFact.Builder<V> constantPoolObjectData;

    @NotNull
    private KLogger logger;

    @NotNull
    public abstract IVGlobal getVg();

    public abstract V anyNewVal(@NotNull AnyNewExprEnv anyNewExprEnv, @NotNull AnyNewExpr anyNewExpr);

    public abstract V newSummaryVal(@NotNull HeapValuesEnv heapValuesEnv, @NotNull Type type, @NotNull Object obj);

    @NotNull
    public abstract IHeapValues<V> canStore(@NotNull IHeapValues<V> iHeapValues, @NotNull Type type);

    public abstract V newConstVal(@NotNull Constant constant, @NotNull Type type);

    public abstract V getNullConst();

    @NotNull
    public abstract HeapValuesEnv env(@NotNull Unit unit);

    @NotNull
    public abstract HookEnv env(@NotNull AIContext aIContext, @NotNull Unit unit);

    @Nullable
    public abstract Boolean getBooleanValue(V v, boolean z);

    @Nullable
    public abstract Integer getIntValue(V v, boolean z);

    @NotNull
    public abstract IReNew<V> newReNewInterface(@NotNull Set<V> set);

    @NotNull
    public abstract JOperatorV<V> push(@NotNull HeapValuesEnv heapValuesEnv, V v);

    @NotNull
    public abstract JOperatorC<V> push(@NotNull HeapValuesEnv heapValuesEnv, @NotNull CompanionV<V> companionV);

    @NotNull
    public abstract JOperatorHV<V> push(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IHeapValues<V> iHeapValues);

    @NotNull
    public abstract PathFactory<V> getPathFactory();

    @NotNull
    public abstract IOpCalculator<V> resolveOp(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IHeapValues<V>... iHeapValuesArr);

    @Nullable
    public abstract IOpCalculator<V> resolveCast(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IFact.Builder<V> builder, @NotNull Type type, @NotNull IHeapValues<V> iHeapValues);

    @NotNull
    public abstract IOpCalculator<V> resolveInstanceOf(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IHeapValues<V> iHeapValues, @NotNull Type type);

    @NotNull
    public abstract IOpCalculator<V> resolveUnop(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IIFact<V> iIFact, @NotNull IHeapValues<V> iHeapValues, @NotNull UnopExpr unopExpr, @NotNull Type type);

    @NotNull
    public abstract IOpCalculator<V> resolveBinop(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IFact.Builder<V> builder, @NotNull IHeapValues<V> iHeapValues, @NotNull IHeapValues<V> iHeapValues2, @NotNull BinopExpr binopExpr, @NotNull Type type);

    @NotNull
    public abstract Sequence<Object> resolve(@NotNull HeapValuesEnv heapValuesEnv, @NotNull ICallCB<IHeapValues<V>, IFact.Builder<IValue>> iCallCB, @NotNull IExpr iExpr);

    public AbstractHeapFactory() {
        KotlinLogging kotlinLogging = KotlinLogging.INSTANCE;
        String qualifiedName = Reflection.getOrCreateKotlinClass(AbstractHeapFactory.class).getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        this.logger = kotlinLogging.logger(qualifiedName);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory
    @NotNull
    public IHeapValues.Builder<V> emptyBuilder() {
        return IHeapValuesFactory.DefaultImpls.emptyBuilder(this);
    }

    @NotNull
    public final IFact.Builder<V> getConstantPoolObjectData() {
        IFact.Builder<V> builder = this.constantPoolObjectData;
        if (builder != null) {
            return builder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("constantPoolObjectData");
        return null;
    }

    public final void setConstantPoolObjectData(@NotNull IFact.Builder<V> builder) {
        Intrinsics.checkNotNullParameter(builder, "<set-?>");
        this.constantPoolObjectData = builder;
    }

    @NotNull
    public KLogger getLogger() {
        return this.logger;
    }

    public void setLogger(@NotNull KLogger kLogger) {
        Intrinsics.checkNotNullParameter(kLogger, "<set-?>");
        this.logger = kLogger;
    }

    public static /* synthetic */ Boolean getBooleanValue$default(AbstractHeapFactory abstractHeapFactory, Object obj, boolean z, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBooleanValue");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return abstractHeapFactory.getBooleanValue(obj, z);
    }

    public static /* synthetic */ Integer getIntValue$default(AbstractHeapFactory abstractHeapFactory, Object obj, boolean z, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getIntValue");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return abstractHeapFactory.getIntValue(obj, z);
    }

    public final V toConstVal(@NotNull Object v) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(v, "v");
        Pair<Constant, Type> pairConstOf = SootUtilsKt.constOf(v);
        Constant constant = (Constant) pairConstOf.component1();
        Type type = (Type) pairConstOf.component2();
        return newConstVal(constant, type);
    }
}
