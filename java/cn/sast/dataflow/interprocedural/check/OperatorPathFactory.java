package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.check.MergePath;
import cn.sast.dataflow.interprocedural.check.callback.CalleeCBImpl;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IIexConst;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.PrimType;
import soot.RefLikeType;
import soot.SootMethod;
import soot.jimple.AnyNewExpr;
import soot.jimple.BinopExpr;
import soot.jimple.Constant;
import soot.jimple.NegExpr;

/* compiled from: OperatorPathFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B9\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u00060\u0002j\u0002`\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0014H\u0016J\u0012\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0016H\u0016J$\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u0018\u001a\u00020\u001cH\u0016J\u001a\u0010\u001d\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J.\u0010 \u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010!\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\"\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u0012\u0010#\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u0012\u0010$\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u0012\u0010%\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u001a\u0010&\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010'\u001a\u00020(H\u0016J6\u0010)\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u001e\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016JM\u0010-\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010.\u001a\u00020\u001b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\u001e\u00100\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00020\u001401\"\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016¢\u0006\u0002\u00102JM\u0010-\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010.\u001a\u00020\u001b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001b032\u001e\u00100\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u001b0301\"\b\u0012\u0004\u0012\u00020\u001b03H\u0016¢\u0006\u0002\u00104J(\u00105\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u001e\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J\u001a\u00108\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u00109\u001a\u00020:H\u0016J\u001a\u00108\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010;\u001a\u00020<H\u0016J\u0012\u0010=\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u0012\u0010>\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J(\u0010?\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010@\u001a\u00020A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J:\u0010C\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010D\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00142\n\u0010E\u001a\u00060\u001bj\u0002`F2\b\u0010G\u001a\u0004\u0018\u00010\u001bH\u0016J$\u0010H\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010I\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0014H\u0016J$\u0010J\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010K\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0016H\u0016J \u0010L\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J.\u0010M\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J\u001a\u0010P\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010Q\u001a\u00020RH\u0016J$\u0010S\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010I\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0014H\u0016J$\u0010T\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010!\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0014H\u0016J\u0012\u0010U\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J \u0010V\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J\u0012\u0010X\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001H\u0016J\u0013\u0010Y\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0007HÆ\u0003J\r\u0010[\u001a\u00060\u0002j\u0002`\u0003HÂ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\nHÆ\u0003JA\u0010]\u001a\u00020��2\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\b\u0002\u0010\b\u001a\u00060\u0002j\u0002`\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010^\u001a\u00020_2\b\u0010`\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010a\u001a\u00020bHÖ\u0001J\t\u0010c\u001a\u00020AHÖ\u0001R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\b\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006d"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/OperatorPathFactory;", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "heapFactory", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "alloc", "path", "Lcn/sast/dataflow/interprocedural/check/IPath;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcn/sast/dataflow/interprocedural/check/IPath;)V", "getHeapFactory", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "getPath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "pop", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "popHV", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "markOfConstant", "c", "Lsoot/jimple/Constant;", "info", "", "Lcom/feysh/corax/config/api/IIexConst;", "markOfNewExpr", "expr", "Lsoot/jimple/AnyNewExpr;", "markSummaryValueFromArrayGet", "array", "markSummaryValueInCaughtExceptionRhs", "markSummaryReturnValueFailedInHook", "markSummaryReturnValueInCalleeSite", "markOfCantCalcAbstractResultValue", "markOfEntryMethodParam", "entryPoint", "Lsoot/SootMethod;", "markSootBinOp", "Lsoot/jimple/BinopExpr;", "clop", "crop", "markOfOp", "op", "op1", "ops", "", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;[Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;[Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorV;", "markOfNegExpr", "Lsoot/jimple/NegExpr;", "cop", "markOfCastTo", "toPrimType", "Lsoot/PrimType;", "toRefType", "Lsoot/RefLikeType;", "markOfInstanceOf", "markOfArrayContentEqualsBoolResult", "markOfParseString", "hint", "", "str", "markSummaryReturnValueFailedGetKeyFromKey", "src", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "key", "dataGetElementFromSequence", "sourceSequence", "markSummaryArraySize", "allocSite", "markOfGetClass", "markOfObjectEqualsResult", "th1s", "that", "markOfReturnValueOfMethod", "ctx", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;", "dataSequenceToSeq", "markArraySizeOf", "markOfTaint", "markOfStringLatin1Hash", "byteArray", "markOfWideningSummary", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nOperatorPathFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OperatorPathFactory.kt\ncn/sast/dataflow/interprocedural/check/OperatorPathFactory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,201:1\n1628#2,3:202\n1454#2,2:205\n1368#2:207\n1557#2:208\n1628#2,3:209\n1456#2,3:212\n*S KotlinDebug\n*F\n+ 1 OperatorPathFactory.kt\ncn/sast/dataflow/interprocedural/check/OperatorPathFactory\n*L\n73#1:202,3\n77#1:205,2\n77#1:207\n77#1:208\n77#1:209,3\n77#1:212,3\n*E\n"})
/* loaded from: OperatorPathFactory.class */
public final class OperatorPathFactory implements JOperatorV<IValue> {

    @NotNull
    private final AbstractHeapFactory<IValue> heapFactory;

    @NotNull
    private final HeapValuesEnv env;

    @NotNull
    private final IValue alloc;

    @Nullable
    private final IPath path;

    @NotNull
    public final AbstractHeapFactory<IValue> component1() {
        return this.heapFactory;
    }

    @NotNull
    public final HeapValuesEnv component2() {
        return this.env;
    }

    private final IValue component3() {
        return this.alloc;
    }

    @Nullable
    public final IPath component4() {
        return this.path;
    }

    @NotNull
    public final OperatorPathFactory copy(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull IValue alloc, @Nullable IPath path) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "heapFactory");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(alloc, "alloc");
        return new OperatorPathFactory(abstractHeapFactory, env, alloc, path);
    }

    public static /* synthetic */ OperatorPathFactory copy$default(OperatorPathFactory operatorPathFactory, AbstractHeapFactory abstractHeapFactory, HeapValuesEnv heapValuesEnv, IValue iValue, IPath iPath, int i, Object obj) {
        if ((i & 1) != 0) {
            abstractHeapFactory = operatorPathFactory.heapFactory;
        }
        if ((i & 2) != 0) {
            heapValuesEnv = operatorPathFactory.env;
        }
        if ((i & 4) != 0) {
            iValue = operatorPathFactory.alloc;
        }
        if ((i & 8) != 0) {
            iPath = operatorPathFactory.path;
        }
        return operatorPathFactory.copy(abstractHeapFactory, heapValuesEnv, iValue, iPath);
    }

    @NotNull
    public String toString() {
        return "OperatorPathFactory(heapFactory=" + this.heapFactory + ", env=" + this.env + ", alloc=" + this.alloc + ", path=" + this.path + ")";
    }

    public int hashCode() {
        int result = this.heapFactory.hashCode();
        return (((((result * 31) + this.env.hashCode()) * 31) + this.alloc.hashCode()) * 31) + (this.path == null ? 0 : this.path.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperatorPathFactory)) {
            return false;
        }
        OperatorPathFactory operatorPathFactory = (OperatorPathFactory) other;
        return Intrinsics.areEqual(this.heapFactory, operatorPathFactory.heapFactory) && Intrinsics.areEqual(this.env, operatorPathFactory.env) && Intrinsics.areEqual(this.alloc, operatorPathFactory.alloc) && Intrinsics.areEqual(this.path, operatorPathFactory.path);
    }

    public OperatorPathFactory(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull IValue alloc, @Nullable IPath path) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "heapFactory");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(alloc, "alloc");
        this.heapFactory = abstractHeapFactory;
        this.env = env;
        this.alloc = alloc;
        this.path = path;
    }

    public /* synthetic */ OperatorPathFactory(AbstractHeapFactory abstractHeapFactory, HeapValuesEnv heapValuesEnv, IValue iValue, IPath iPath, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractHeapFactory, heapValuesEnv, iValue, (i & 8) != 0 ? null : iPath);
    }

    @NotNull
    public final AbstractHeapFactory<IValue> getHeapFactory() {
        return this.heapFactory;
    }

    @NotNull
    public final HeapValuesEnv getEnv() {
        return this.env;
    }

    @Nullable
    public final IPath getPath() {
        return this.path;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public CompanionV<IValue> pop() {
        IValue iValue = this.alloc;
        UnknownPath unknownPathV = this.path;
        if (unknownPathV == null) {
            unknownPathV = UnknownPath.Companion.v(this.env);
        }
        return new CompanionValueImpl1(iValue, unknownPathV);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public IHeapValues<IValue> popHV() {
        return this.heapFactory.empty().plus(pop());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfConstant(@NotNull Constant c, @Nullable Object info) {
        Intrinsics.checkNotNullParameter(c, "c");
        return copy$default(this, null, null, null, LiteralPath.Companion.v(this.env, c, info), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfConstant(@NotNull IIexConst c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return copy$default(this, null, null, null, LiteralPath.Companion.v(this.env, c), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfNewExpr(@NotNull AnyNewExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryValueFromArrayGet(@NotNull CompanionV<IValue> companionV, @Nullable Object info) {
        Intrinsics.checkNotNullParameter(companionV, "array");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryValueInCaughtExceptionRhs() {
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryReturnValueFailedInHook() {
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryReturnValueInCalleeSite() {
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfCantCalcAbstractResultValue() {
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfEntryMethodParam(@NotNull SootMethod entryPoint) {
        Intrinsics.checkNotNullParameter(entryPoint, "entryPoint");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSootBinOp(@NotNull BinopExpr expr, @NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue> companionV2) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(companionV, "clop");
        Intrinsics.checkNotNullParameter(companionV2, "crop");
        return copy$default(this, null, null, null, MergePath.Companion.v(this.env, ((PathCompanionV) companionV).getPath(), ((PathCompanionV) companionV2).getPath()), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfOp(@NotNull Object op, @NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue>... companionVArr) {
        Intrinsics.checkNotNullParameter(op, "op");
        Intrinsics.checkNotNullParameter(companionV, "op1");
        Intrinsics.checkNotNullParameter(companionVArr, "ops");
        MergePath.Companion companion = MergePath.Companion;
        HeapValuesEnv heapValuesEnv = this.env;
        Iterable $this$mapTo$iv = CollectionsKt.plus(CollectionsKt.listOf(companionV), companionVArr);
        Collection destination$iv = new HashSet(8);
        for (Object item$iv : $this$mapTo$iv) {
            Object obj = (CompanionV) item$iv;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PathCompanionV");
            destination$iv.add(((PathCompanionV) obj).getPath());
        }
        return copy$default(this, null, null, null, companion.v(heapValuesEnv, (Set<? extends IPath>) destination$iv), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfOp(@NotNull Object op, @NotNull ImmutableElementSet<Object> immutableElementSet, @NotNull ImmutableElementSet<Object>... immutableElementSetArr) {
        Intrinsics.checkNotNullParameter(op, "op");
        Intrinsics.checkNotNullParameter(immutableElementSet, "op1");
        Intrinsics.checkNotNullParameter(immutableElementSetArr, "ops");
        MergePath.Companion companion = MergePath.Companion;
        HeapValuesEnv heapValuesEnv = this.env;
        Iterable $this$flatMapTo$iv = CollectionsKt.plus(CollectionsKt.listOf(immutableElementSet), immutableElementSetArr);
        Collection destination$iv = new HashSet(8);
        for (Object element$iv : $this$flatMapTo$iv) {
            ImmutableElementSet set = (ImmutableElementSet) element$iv;
            Iterable $this$flatMap$iv = set.getMap().values();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                IHeapValues values = (IHeapValues) element$iv$iv;
                Iterable $this$map$iv = values.getValuesCompanion();
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    Object obj = (CompanionV) item$iv$iv;
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PathCompanionV");
                    destination$iv$iv2.add(((PathCompanionV) obj).getPath());
                }
                Iterable list$iv$iv = (List) destination$iv$iv2;
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            Iterable list$iv = (List) destination$iv$iv;
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return copy$default(this, null, null, null, companion.v(heapValuesEnv, (Set<? extends IPath>) destination$iv), 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfNegExpr(@NotNull NegExpr expr, @NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(companionV, "cop");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfCastTo(@NotNull PrimType toPrimType) {
        Intrinsics.checkNotNullParameter(toPrimType, "toPrimType");
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfCastTo(@NotNull RefLikeType toRefType) {
        Intrinsics.checkNotNullParameter(toRefType, "toRefType");
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfInstanceOf() {
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfArrayContentEqualsBoolResult() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfParseString(@NotNull String hint, @NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(companionV, "str");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryReturnValueFailedGetKeyFromKey(@NotNull CompanionV<IValue> companionV, @NotNull Object mt, @Nullable Object key) {
        Intrinsics.checkNotNullParameter(companionV, "src");
        Intrinsics.checkNotNullParameter(mt, "mt");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> dataGetElementFromSequence(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "sourceSequence");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markSummaryArraySize(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfGetClass(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "cop");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfObjectEqualsResult(@NotNull CompanionV<IValue> companionV, @NotNull CompanionV<IValue> companionV2) {
        Intrinsics.checkNotNullParameter(companionV, "th1s");
        Intrinsics.checkNotNullParameter(companionV2, "that");
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfReturnValueOfMethod(@NotNull CalleeCBImpl.EvalCall ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> dataSequenceToSeq(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "sourceSequence");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markArraySizeOf(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "array");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfTaint() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfStringLatin1Hash(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "byteArray");
        return copy$default(this, null, null, null, ((PathCompanionV) companionV).getPath(), 7, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorV
    @NotNull
    public JOperatorV<IValue> markOfWideningSummary() {
        return this;
    }
}
