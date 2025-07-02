package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IIexConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.PrimType;
import soot.RefLikeType;
import soot.SootMethod;
import soot.jimple.AnyNewExpr;
import soot.jimple.BinopExpr;
import soot.jimple.Constant;
import soot.jimple.NegExpr;

/* compiled from: AbstractHeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008a\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0��H&J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\b\u001a\u00020\fH&J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u000e\u001a\u00020\u000fH&J(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u00122\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0��H&J2\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0\u00122\n\u0010\u0018\u001a\u00060\u000bj\u0002`\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bH&J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0\u0012H&J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0\u0012H&J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028��0 H&J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0012H&J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00028��0��H&J2\u0010#\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u000e\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&JI\u0010(\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010)\u001a\u00020\u000b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020&0\u00122\u001e\u0010+\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020&0\u00120,\"\b\u0012\u0004\u0012\u00020&0\u0012H&¢\u0006\u0002\u0010-JI\u0010(\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010)\u001a\u00020\u000b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0.2\u001e\u0010+\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u000b0.0,\"\b\u0012\u0004\u0012\u00020\u000b0.H&¢\u0006\u0002\u0010/J$\u00100\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u000e\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u00104\u001a\u000205H&J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u00106\u001a\u000207H&J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u000e\u00109\u001a\b\u0012\u0004\u0012\u00028��0��H&J$\u0010:\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010;\u001a\u00020<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&J\u001c\u0010>\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u00102\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&J*\u0010?\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020&0\u00122\f\u0010A\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010C\u001a\u00020DH&J\u001c\u0010E\u001a\b\u0012\u0004\u0012\u00028��0��2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020&0\u0012H&J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00028��0��H&J\u000e\u0010H\u001a\b\u0012\u0004\u0012\u00028��0\u0012H&J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00028��0 H&¨\u0006J"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "V", "Lcn/sast/dataflow/interprocedural/analysis/JOperator;", "markOfEntryMethodParam", "entryPoint", "Lsoot/SootMethod;", "markOfTaint", "markOfConstant", "c", "Lsoot/jimple/Constant;", "info", "", "Lcom/feysh/corax/config/api/IIexConst;", "markOfNewExpr", "expr", "Lsoot/jimple/AnyNewExpr;", "markSummaryValueFromArrayGet", "array", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "markSummaryValueInCaughtExceptionRhs", "markSummaryReturnValueFailedInHook", "markSummaryReturnValueInCalleeSite", "markSummaryReturnValueFailedGetKeyFromKey", "src", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "key", "dataGetElementFromSequence", "sourceSequence", "dataSequenceToSeq", "markSummaryArraySize", "allocSite", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "markArraySizeOf", "markOfCantCalcAbstractResultValue", "markSootBinOp", "Lsoot/jimple/BinopExpr;", "clop", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "crop", "markOfOp", "op", "op1", "ops", "", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;[Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;[Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "markOfNegExpr", "Lsoot/jimple/NegExpr;", "cop", "markOfCastTo", "toPrimType", "Lsoot/PrimType;", "toRefType", "Lsoot/RefLikeType;", "markOfInstanceOf", "markOfArrayContentEqualsBoolResult", "markOfParseString", "hint", "", "str", "markOfGetClass", "markOfObjectEqualsResult", "th1s", "that", "markOfReturnValueOfMethod", "ctx", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;", "markOfStringLatin1Hash", "byteArray", "markOfWideningSummary", "pop", "popHV", "corax-data-flow"})
/* loaded from: JOperatorV.class */
public interface JOperatorV<V> extends JOperator<V> {
    @NotNull
    JOperatorV<V> markOfEntryMethodParam(@NotNull SootMethod sootMethod);

    @NotNull
    JOperatorV<V> markOfTaint();

    @NotNull
    JOperatorV<V> markOfConstant(@NotNull Constant constant, @Nullable Object obj);

    @NotNull
    JOperatorV<V> markOfConstant(@NotNull IIexConst iIexConst);

    @NotNull
    JOperatorV<V> markOfNewExpr(@NotNull AnyNewExpr anyNewExpr);

    @NotNull
    JOperatorV<V> markSummaryValueFromArrayGet(@NotNull CompanionV<V> companionV, @Nullable Object obj);

    @NotNull
    JOperatorV<V> markSummaryValueInCaughtExceptionRhs();

    @NotNull
    JOperatorV<V> markSummaryReturnValueFailedInHook();

    @NotNull
    JOperatorV<V> markSummaryReturnValueInCalleeSite();

    @NotNull
    JOperatorV<V> markSummaryReturnValueFailedGetKeyFromKey(@NotNull CompanionV<V> companionV, @NotNull Object obj, @Nullable Object obj2);

    @NotNull
    JOperatorV<V> dataGetElementFromSequence(@NotNull CompanionV<V> companionV);

    @NotNull
    JOperatorV<V> dataSequenceToSeq(@NotNull CompanionV<V> companionV);

    @NotNull
    JOperatorV<V> markSummaryArraySize(@NotNull IHeapValues<V> iHeapValues);

    @NotNull
    JOperatorV<V> markArraySizeOf(@NotNull CompanionV<V> companionV);

    @NotNull
    JOperatorV<V> markOfCantCalcAbstractResultValue();

    @NotNull
    JOperatorV<V> markSootBinOp(@NotNull BinopExpr binopExpr, @NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue> companionV2);

    @NotNull
    JOperatorV<V> markOfOp(@NotNull Object obj, @NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue>... companionVArr);

    @NotNull
    JOperatorV<V> markOfOp(@NotNull Object obj, @NotNull ImmutableElementSet<Object> immutableElementSet, @NotNull ImmutableElementSet<Object>... immutableElementSetArr);

    @NotNull
    JOperatorV<V> markOfNegExpr(@NotNull NegExpr negExpr, @NotNull CompanionV<IValue> companionV);

    @NotNull
    JOperatorV<V> markOfCastTo(@NotNull PrimType primType);

    @NotNull
    JOperatorV<V> markOfCastTo(@NotNull RefLikeType refLikeType);

    @NotNull
    JOperatorV<V> markOfInstanceOf();

    @NotNull
    JOperatorV<V> markOfArrayContentEqualsBoolResult();

    @NotNull
    JOperatorV<V> markOfParseString(@NotNull String str, @NotNull CompanionV<IValue> companionV);

    @NotNull
    JOperatorV<V> markOfGetClass(@NotNull CompanionV<IValue> companionV);

    @NotNull
    JOperatorV<V> markOfObjectEqualsResult(@NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue> companionV2);

    @NotNull
    JOperatorV<V> markOfReturnValueOfMethod(@NotNull CalleeCBImpl.EvalCall evalCall);

    @NotNull
    JOperatorV<V> markOfStringLatin1Hash(@NotNull CompanionV<IValue> companionV);

    @NotNull
    JOperatorV<V> markOfWideningSummary();

    @NotNull
    CompanionV<V> pop();

    @NotNull
    IHeapValues<V> popHV();

    /* compiled from: AbstractHeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: JOperatorV$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ JOperatorV markOfConstant$default(JOperatorV jOperatorV, Constant constant, Object obj, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markOfConstant");
            }
            if ((i & 2) != 0) {
                obj = null;
            }
            return jOperatorV.markOfConstant(constant, obj);
        }

        public static /* synthetic */ JOperatorV markSummaryValueFromArrayGet$default(JOperatorV jOperatorV, CompanionV companionV, Object obj, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markSummaryValueFromArrayGet");
            }
            if ((i & 2) != 0) {
                obj = null;
            }
            return jOperatorV.markSummaryValueFromArrayGet(companionV, obj);
        }
    }
}
