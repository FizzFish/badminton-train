package cn.sast.dataflow.interprocedural.check.callback;

import cn.sast.api.config.StaticFieldTrackingMode;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICalleeCB;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.RefType;
import soot.Scene;
import soot.SootMethod;
import soot.Type;
import soot.jimple.Stmt;

/* compiled from: CallCallBackImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010��\n\u0002\b\u000e\u0018��2\u00020\u0001:\u0003789B;\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010&\u001a\f\u0012\u0004\u0012\u00020\u00040'j\u0002`(2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010)\u001a\u00020*H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR%\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020!\u0012\f\u0012\n #*\u0004\u0018\u00010\"0\"0 8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001e\u0010-\u001a\f\u0012\u0004\u0012\u00020\u00040'j\u0002`(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u001e\u00100\u001a\f\u0012\u0004\u0012\u00020\u00040'j\u0002`(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010/R8\u00103\u001a\f\u0012\u0004\u0012\u00020\u00040'j\u0002`(2\u0010\u00102\u001a\f\u0012\u0004\u0012\u00020\u00040'j\u0002`(8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b4\u0010/\"\u0004\b5\u00106¨\u0006:"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;", "Lcn/sast/dataflow/interprocedural/check/callback/ICalleeSiteCBImpl;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "callee", "Lsoot/SootMethod;", "stmt", "Lsoot/jimple/Stmt;", "out", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lsoot/SootMethod;Lsoot/jimple/Stmt;Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;Lcn/sast/dataflow/interprocedural/analysis/HookEnv;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getCallee", "()Lsoot/SootMethod;", "getStmt", "()Lsoot/jimple/Stmt;", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "globalStaticObject", "Lkotlin/Pair;", "", "Lsoot/RefType;", "kotlin.jvm.PlatformType", "getGlobalStaticObject", "()Lkotlin/Pair;", "arg", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "argIndex", "", "argToValue", "", "global", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "this", "getThis", "value", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "PrevCall", "EvalCall", "PostCall", "corax-data-flow"})
/* loaded from: CalleeCBImpl.class */
public final class CalleeCBImpl implements ICalleeSiteCBImpl {

    @NotNull
    private final AbstractHeapFactory<IValue> hf;

    @NotNull
    private final SootMethod callee;

    @NotNull
    private final Stmt stmt;

    @NotNull
    private IFact.Builder<IValue> out;

    @NotNull
    private final HookEnv env;

    public CalleeCBImpl(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull SootMethod callee, @NotNull Stmt stmt, @NotNull IFact.Builder<IValue> builder, @NotNull HookEnv env) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        Intrinsics.checkNotNullParameter(builder, "out");
        Intrinsics.checkNotNullParameter(env, "env");
        this.hf = abstractHeapFactory;
        this.callee = callee;
        this.stmt = stmt;
        this.out = builder;
        this.env = env;
    }

    @Override // cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl
    @NotNull
    public AbstractHeapFactory<IValue> getHf() {
        return this.hf;
    }

    @Override // cn.sast.idfa.check.ICalleeCB
    @NotNull
    public SootMethod getCallee() {
        return this.callee;
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
        return new AnyNewExprEnv(getCallee(), getStmt());
    }

    @NotNull
    public final Pair<String, RefType> getGlobalStaticObject() {
        return TuplesKt.to(getHf().getVg().getGLOBAL_LOCAL(), Scene.v().getObjectType());
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> arg(int argIndex) {
        IHeapValues<IValue> targetsUnsafe = getOut().getTargetsUnsafe(Integer.valueOf(argIndex));
        return targetsUnsafe == null ? getHf().empty() : targetsUnsafe;
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public Object argToValue(int argIndex) {
        return Integer.valueOf(argIndex);
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
    public IHeapValues<IValue> getThis() {
        return arg(-1);
    }

    @Override // cn.sast.idfa.check.ICallCB
    @NotNull
    public IHeapValues<IValue> getReturn() {
        IHeapValues it = getOut().getTargetsUnsafe(getHf().getVg().getRETURN_LOCAL());
        if (it != null) {
            return it;
        }
        Type returnType = getCallee().getReturnType();
        AbstractHeapFactory<IValue> hf = getHf();
        HookEnv env = getEnv();
        AbstractHeapFactory<IValue> hf2 = getHf();
        HookEnv env2 = getEnv();
        Intrinsics.checkNotNull(returnType);
        IHeapValues iHeapValuesPopHV = hf.push((HeapValuesEnv) env, (HookEnv) hf2.newSummaryVal(env2, returnType, getHf().getVg().getRETURN_LOCAL())).markSummaryReturnValueInCalleeSite().popHV();
        IFact.Builder.DefaultImpls.assignNewExpr$default(getOut(), getEnv(), getHf().getVg().getRETURN_LOCAL(), iHeapValuesPopHV, false, 8, null);
        return iHeapValuesPopHV;
    }

    @Override // cn.sast.idfa.check.ICallCB
    public void setReturn(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        IFact.Builder.DefaultImpls.assignNewExpr$default(getOut(), getEnv(), getHf().getVg().getRETURN_LOCAL(), iHeapValues, false, 8, null);
    }

    /* compiled from: CallCallBackImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0012\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0001¢\u0006\u0002\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R8\u0010\r\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0010\u0010\f\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0018\u001a\u00020\u0019X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00020\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b!\u0010\u000fR\u0018\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#X\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020'X\u0096\u0005¢\u0006\u0006\u001a\u0004\b(\u0010)R\"\u0010*\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0012\u0010/\u001a\u000200X\u0096\u0005¢\u0006\u0006\u001a\u0004\b1\u00102R\u001c\u00103\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b4\u0010\u000f¨\u00065"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$PrevCall;", "Lcn/sast/idfa/check/ICalleeCB$IPrevCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICalleeSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;)V", "value", "return", "getReturn", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "callee", "Lsoot/SootMethod;", "getCallee", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CalleeCBImpl$PrevCall.class */
    public static final class PrevCall implements ICalleeCB.IPrevCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICalleeSiteCBImpl {

        @NotNull
        private final CalleeCBImpl delegate;

        @Override // cn.sast.idfa.check.ICalleeCB
        @NotNull
        public SootMethod getCallee() {
            return this.delegate.getCallee();
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

        public PrevCall(@NotNull CalleeCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
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
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0011\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0017\u001a\u00020\u0018X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u001cX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#X\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020'X\u0096\u0005¢\u0006\u0006\u001a\u0004\b(\u0010)R\"\u0010*\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u000f¢\u0006\f\u001a\u0004\b0\u0010!\"\u0004\b1\u00102R\u0012\u00103\u001a\u000204X\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u001c\u00107\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b8\u0010!¨\u00069"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$EvalCall;", "Lcn/sast/idfa/check/ICalleeCB$IEvalCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICalleeSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;)V", "isEvalAble", "", "()Z", "setEvalAble", "(Z)V", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "callee", "Lsoot/SootMethod;", "getCallee", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CalleeCBImpl$EvalCall.class */
    public static final class EvalCall implements ICalleeCB.IEvalCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICalleeSiteCBImpl {

        @NotNull
        private final CalleeCBImpl delegate;
        private boolean isEvalAble;

        @Override // cn.sast.idfa.check.ICalleeCB
        @NotNull
        public SootMethod getCallee() {
            return this.delegate.getCallee();
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

        public EvalCall(@NotNull CalleeCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
            this.isEvalAble = true;
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
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\"\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u00060\u00012\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\f\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0001R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u0012\u0010\u0012\u001a\u00020\u0013X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X\u0096\u0005¢\u0006\u0006\u001a\u0004\b#\u0010$R\"\u0010%\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006X\u0096\u000f¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010*\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u000f¢\u0006\f\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010-R\u0012\u0010.\u001a\u00020/X\u0096\u0005¢\u0006\u0006\u001a\u0004\b0\u00101R\u001c\u00102\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u0004X\u0096\u0005¢\u0006\u0006\u001a\u0004\b3\u0010\u001c¨\u00064"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl$PostCall;", "Lcn/sast/idfa/check/ICalleeCB$IPostCall;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/callback/V;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "Lcn/sast/dataflow/interprocedural/check/callback/R;", "Lcn/sast/dataflow/interprocedural/check/callback/ICalleeSiteCBImpl;", "delegate", "Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/callback/CalleeCBImpl;)V", "arg", "argIndex", "", "(I)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "argToValue", "", "callee", "Lsoot/SootMethod;", "getCallee", "()Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HookEnv;", "global", "getGlobal", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "newEnv", "Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "getNewEnv", "()Lcn/sast/dataflow/interprocedural/analysis/AnyNewExprEnv;", "out", "getOut", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "setOut", "(Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "return", "getReturn", "setReturn", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "stmt", "Lsoot/jimple/Stmt;", "getStmt", "()Lsoot/jimple/Stmt;", "this", "getThis", "corax-data-flow"})
    /* loaded from: CalleeCBImpl$PostCall.class */
    public static final class PostCall implements ICalleeCB.IPostCall<IHeapValues<IValue>, IFact.Builder<IValue>>, ICalleeSiteCBImpl {

        @NotNull
        private final CalleeCBImpl delegate;

        @Override // cn.sast.idfa.check.ICalleeCB
        @NotNull
        public SootMethod getCallee() {
            return this.delegate.getCallee();
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

        public PostCall(@NotNull CalleeCBImpl delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
        }
    }
}
