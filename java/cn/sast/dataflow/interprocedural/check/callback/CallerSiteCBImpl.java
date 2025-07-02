package cn.sast.dataflow.interprocedural.check.callback;

import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IIFact;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.SootUtilsKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallerSiteCB;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.DefinitionStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;

/* compiled from: CallCallBackImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018��2\u00020\u0001:\u0003123BC\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u001a\u0010*\u001a\f\u0012\u0004\u0012\u00020\u00040&j\u0002`'2\u0006\u0010#\u001a\u00020$H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001e\u0010%\u001a\f\u0012\u0004\u0012\u00020\u00040&j\u0002`'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R$\u0010+\u001a\f\u0012\u0004\u0012\u00020\u00040&j\u0002`'X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b,\u0010)\"\u0004\b-\u0010.R\u001e\u0010/\u001a\f\u0012\u0004\u0012\u00020\u00040&j\u0002`'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010)¨\u00064"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallerSiteCBImpl;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "caller", "Lsoot/SootMethod;", "stmt", "Lsoot/jimple/Stmt;", "out", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "returnType", "Lsoot/Type;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lsoot/SootMethod;Lsoot/jimple/Stmt;Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;Lsoot/Type;Lcn/sast/dataflow/interprocedural/analysis/HookEnv;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getCaller", "()Lsoot/SootMethod;", "getStmt", "()Lsoot/jimple/Stmt;", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "argToValue", "", "argIndex", "", "global", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "arg", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "this", "getThis", "PrevCall", "EvalCall", "PostCall", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nCallCallBackImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallCallBackImpl.kt\ncn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl\n+ 2 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n*L\n1#1,164:1\n310#2:165\n*S KotlinDebug\n*F\n+ 1 CallCallBackImpl.kt\ncn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl\n*L\n63#1:165\n*E\n"})
/* loaded from: CallerSiteCBImpl.class */
public final class CallerSiteCBImpl implements ICallerSiteCBImpl {

    @NotNull
    private final AbstractHeapFactory<IValue> hf;

    @NotNull
    private final SootMethod caller;

    @NotNull
    private final Stmt stmt;

    @NotNull
    private IFact.Builder<IValue> out;

    @NotNull
    private final Type returnType;

    @NotNull
    private final HookEnv env;

    /* renamed from: return, reason: not valid java name */
    @NotNull
    private IHeapValues<IValue> f2return;

    public CallerSiteCBImpl(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull SootMethod caller, @NotNull Stmt stmt, @NotNull IFact.Builder<IValue> builder, @NotNull Type returnType, @NotNull HookEnv env) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        Intrinsics.checkNotNullParameter(builder, "out");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(env, "env");
        this.hf = abstractHeapFactory;
        this.caller = caller;
        this.stmt = stmt;
        this.out = builder;
        this.returnType = returnType;
        this.env = env;
        AbstractHeapFactory<IValue> hf = getHf();
        HookEnv env2 = getEnv();
        AbstractHeapFactory<IValue> hf2 = getHf();
        HookEnv env3 = getEnv();
        Type type = this.returnType;
        DefinitionStmt definitionStmt = (Unit) getStmt();
        DefinitionStmt definitionStmt2 = definitionStmt instanceof DefinitionStmt ? definitionStmt : null;
        Value leftOp = definitionStmt2 != null ? definitionStmt2.getLeftOp() : null;
        this.f2return = hf.push((HeapValuesEnv) env2, (HookEnv) hf2.newSummaryVal(env3, type, leftOp != null ? (Serializable) leftOp : "return")).markSummaryReturnValueInCalleeSite().popHV();
    }

    @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
    @NotNull
    public AbstractHeapFactory<IValue> getHf() {
        return this.hf;
    }

    @Override // cn.sast.dataflow.interprocedural.check.callback.ICallerSiteCBImpl, cn.sast.idfa.check.ICallerSiteCB
    @NotNull
    public SootMethod getCaller() {
        return this.caller;
    }

    @Override // cn.sast.idfa.check.IStmtCB
    @NotNull
    public Stmt getStmt() {
        return this.stmt;
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IFact.Builder<IValue> getOut() {
        return this.out;
    }

    @Override // cn.sast.idfa.check.ICallCB
    public void setOut(@NotNull IFact.Builder<IValue> builder) {
        Intrinsics.checkNotNullParameter(builder, "<set-?>");
        this.out = builder;
    }

    @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
    @NotNull
    public HookEnv getEnv() {
        return this.env;
    }

    @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
    @NotNull
    public AnyNewExprEnv getNewEnv() {
        return new AnyNewExprEnv(getCaller(), getStmt());
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public Object argToValue(int argIndex) {
        InvokeExpr iee = getStmt().getInvokeExpr();
        Intrinsics.checkNotNull(iee);
        Pair<Value, Type> pairArgToOpAndType = SootUtilsKt.argToOpAndType(iee, argIndex);
        Value sootValue = (Value) pairArgToOpAndType.component1();
        return sootValue;
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> getGlobal() {
        if (getHf().getVg().getStaticFieldTrackingMode() != StaticFieldTrackingMode.None) {
            return getHf().push((HeapValuesEnv) getEnv(), (HookEnv) getHf().getVg().getGLOBAL_SITE()).popHV();
        }
        return getHf().empty();
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> arg(int argIndex) {
        if (!getStmt().containsInvokeExpr()) {
            throw new IllegalStateException(("env: " + getEnv() + "\nstmt = " + getStmt() + "\nargIndex=" + argIndex).toString());
        }
        InvokeExpr iee = getStmt().getInvokeExpr();
        Intrinsics.checkNotNull(iee);
        Pair<Value, Type> pairArgToOpAndType = SootUtilsKt.argToOpAndType(iee, argIndex);
        Value sootValue = (Value) pairArgToOpAndType.component1();
        Type type = (Type) pairArgToOpAndType.component2();
        return getOut().getOfSootValue(getEnv(), sootValue, type);
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> getReturn() {
        return this.f2return;
    }

    @Override // cn.sast.idfa.check.ICallCB
    public void setReturn(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "<set-?>");
        this.f2return = iHeapValues;
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> getThis() {
        return arg(-1);
    }

    /* compiled from: CallCallBackImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fJ \u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0096\u0001¢\u0006\u0002\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R8\u0010\u0011\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0010\u0010\u0010\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0012\u0010\u001c\u001a\u00020\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020!X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010\u0013R\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'X\u0096\u0005¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0012\u0010*\u001a\u00020+X\u0096\u0005¢\u0006\u0006\u001a\u0004\b,\u0010-R\"\u0010.\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0012\u00103\u001a\u000204X\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u001c\u00107\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b8\u0010\u0013¨\u00069"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$PrevCall;", "Lcn/sast/idfa/check/ICallerSiteCB$IPrevCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallerSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;)V", "emit", "", "fact", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "value", "return", "getReturn", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "caller", "Lsoot/SootMethod;", "getCaller", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CallerSiteCBImpl$PrevCall.class */
    public static final class PrevCall implements ICallerSiteCB.IPrevCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICallerSiteCBImpl {

        @NotNull
        private final CallerSiteCBImpl delegate;

        @Override // cn.sast.idfa.check.ICallerSiteCB
        @NotNull
        public SootMethod getCaller() {
            return this.delegate.getCaller();
        }

        @Override // cn.sast.idfa.check.IStmtCB
        @NotNull
        public Stmt getStmt() {
            return this.delegate.getStmt();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> arg(int argIndex) {
            return this.delegate.arg(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public Object argToValue(int argIndex) {
            return this.delegate.argToValue(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getGlobal() {
            return this.delegate.getGlobal();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getThis() {
            return this.delegate.getThis();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IFact.Builder<IValue> getOut() {
            return this.delegate.getOut();
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setOut(@NotNull IFact.Builder<IValue> builder) {
            Intrinsics.checkNotNullParameter(builder, "value");
            this.delegate.setOut(builder);
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public HookEnv getEnv() {
            return this.delegate.getEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AnyNewExprEnv getNewEnv() {
            return this.delegate.getNewEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AbstractHeapFactory<IValue> getHf() {
            return this.delegate.getHf();
        }

        public PrevCall(@NotNull CallerSiteCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
        }

        public final void emit(@NotNull IIFact<IValue> iIFact) {
            Intrinsics.checkNotNullParameter(iIFact, "fact");
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getReturn() {
            throw new IllegalStateException("prev call has no return".toString());
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setReturn(@NotNull IHeapValues<IValue> iHeapValues) {
            Intrinsics.checkNotNullParameter(iHeapValues, "value");
            throw new IllegalStateException("prev call has no return".toString());
        }
    }

    /* compiled from: CallCallBackImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fJ \u0010\u0015\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0096\u0001¢\u0006\u0002\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0012\u0010\u001b\u001a\u00020\u001cX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0012\u0010\u001f\u001a\u00020 X\u0096\u0005¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'X\u0096\u0005¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0012\u0010*\u001a\u00020+X\u0096\u0005¢\u0006\u0006\u001a\u0004\b,\u0010-R\"\u0010.\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u000f¢\u0006\f\u001a\u0004\b4\u0010%\"\u0004\b5\u00106R\u0012\u00107\u001a\u000208X\u0096\u0005¢\u0006\u0006\u001a\u0004\b9\u0010:R\u001c\u0010;\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b<\u0010%¨\u0006="}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$EvalCall;", "Lcn/sast/idfa/check/ICallerSiteCB$IEvalCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallerSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;)V", "emit", "", "fact", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "isEvalAble", "", "()Z", "setEvalAble", "(Z)V", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "caller", "Lsoot/SootMethod;", "getCaller", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CallerSiteCBImpl$EvalCall.class */
    public static final class EvalCall implements ICallerSiteCB.IEvalCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICallerSiteCBImpl {

        @NotNull
        private final CallerSiteCBImpl delegate;
        private boolean isEvalAble;

        @Override // cn.sast.idfa.check.ICallerSiteCB
        @NotNull
        public SootMethod getCaller() {
            return this.delegate.getCaller();
        }

        @Override // cn.sast.idfa.check.IStmtCB
        @NotNull
        public Stmt getStmt() {
            return this.delegate.getStmt();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> arg(int argIndex) {
            return this.delegate.arg(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public Object argToValue(int argIndex) {
            return this.delegate.argToValue(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getGlobal() {
            return this.delegate.getGlobal();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getReturn() {
            return this.delegate.getReturn();
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setReturn(@NotNull IHeapValues<IValue> iHeapValues) {
            Intrinsics.checkNotNullParameter(iHeapValues, "value");
            this.delegate.setReturn(iHeapValues);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getThis() {
            return this.delegate.getThis();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IFact.Builder<IValue> getOut() {
            return this.delegate.getOut();
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setOut(@NotNull IFact.Builder<IValue> builder) {
            Intrinsics.checkNotNullParameter(builder, "value");
            this.delegate.setOut(builder);
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public HookEnv getEnv() {
            return this.delegate.getEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AnyNewExprEnv getNewEnv() {
            return this.delegate.getNewEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AbstractHeapFactory<IValue> getHf() {
            return this.delegate.getHf();
        }

        public EvalCall(@NotNull CallerSiteCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
            this.isEvalAble = true;
        }

        public final void emit(@NotNull IIFact<IValue> iIFact) {
            Intrinsics.checkNotNullParameter(iIFact, "fact");
        }

        @Override // cn.sast.idfa.check.IEvalCallCB
        public boolean isEvalAble() {
            return this.isEvalAble;
        }

        @Override // cn.sast.idfa.check.IEvalCallCB
        public void setEvalAble(boolean z) {
            this.isEvalAble = z;
        }
    }

    /* compiled from: CallCallBackImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fJ \u0010\u0010\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0001¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\"X\u0096\u0005¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X\u0096\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(R\"\u0010)\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u000f¢\u0006\f\u001a\u0004\b/\u0010 \"\u0004\b0\u00101R\u0012\u00102\u001a\u000203X\u0096\u0005¢\u0006\u0006\u001a\u0004\b4\u00105R\u001c\u00106\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b7\u0010 ¨\u00068"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl$PostCall;", "Lcn/sast/idfa/check/ICallerSiteCB$IPostCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallerSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CallerSiteCBImpl;)V", "emit", "", "fact", "Lcn/sast/dataflow/interprocedural/analysis/IIFact;", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "caller", "Lsoot/SootMethod;", "getCaller", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CallerSiteCBImpl$PostCall.class */
    public static final class PostCall implements ICallerSiteCB.IPostCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICallerSiteCBImpl {

        @NotNull
        private final CallerSiteCBImpl delegate;

        @Override // cn.sast.idfa.check.ICallerSiteCB
        @NotNull
        public SootMethod getCaller() {
            return this.delegate.getCaller();
        }

        @Override // cn.sast.idfa.check.IStmtCB
        @NotNull
        public Stmt getStmt() {
            return this.delegate.getStmt();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> arg(int argIndex) {
            return this.delegate.arg(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public Object argToValue(int argIndex) {
            return this.delegate.argToValue(argIndex);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getGlobal() {
            return this.delegate.getGlobal();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getReturn() {
            return this.delegate.getReturn();
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setReturn(@NotNull IHeapValues<IValue> iHeapValues) {
            Intrinsics.checkNotNullParameter(iHeapValues, "value");
            this.delegate.setReturn(iHeapValues);
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IHeapValues<IValue> getThis() {
            return this.delegate.getThis();
        }

        @Override // cn.sast.idfa.check.ICallCB
        @NotNull
        public IFact.Builder<IValue> getOut() {
            return this.delegate.getOut();
        }

        @Override // cn.sast.idfa.check.ICallCB
        public void setOut(@NotNull IFact.Builder<IValue> builder) {
            Intrinsics.checkNotNullParameter(builder, "value");
            this.delegate.setOut(builder);
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public HookEnv getEnv() {
            return this.delegate.getEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AnyNewExprEnv getNewEnv() {
            return this.delegate.getNewEnv();
        }

        @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
        @NotNull
        public AbstractHeapFactory<IValue> getHf() {
            return this.delegate.getHf();
        }

        public PostCall(@NotNull CallerSiteCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
        }

        public final void emit(@NotNull IIFact<IValue> iIFact) {
            Intrinsics.checkNotNullParameter(iIFact, "fact");
        }
    }
}
