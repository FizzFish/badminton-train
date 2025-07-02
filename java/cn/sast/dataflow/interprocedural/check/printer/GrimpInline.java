package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Local;
import soot.Singletons;
import soot.SootMethodRef;
import soot.Unit;
import soot.UnitBox;
import soot.UnitPrinter;
import soot.Value;
import soot.grimp.Grimp;
import soot.grimp.internal.ExprBox;
import soot.grimp.internal.GDynamicInvokeExpr;
import soot.grimp.internal.GInterfaceInvokeExpr;
import soot.grimp.internal.GSpecialInvokeExpr;
import soot.grimp.internal.GStaticInvokeExpr;
import soot.grimp.internal.GVirtualInvokeExpr;
import soot.jimple.AbstractExprSwitch;
import soot.jimple.AbstractStmtSwitch;
import soot.jimple.AddExpr;
import soot.jimple.AndExpr;
import soot.jimple.ArrayRef;
import soot.jimple.AssignStmt;
import soot.jimple.BreakpointStmt;
import soot.jimple.CastExpr;
import soot.jimple.CmpExpr;
import soot.jimple.CmpgExpr;
import soot.jimple.CmplExpr;
import soot.jimple.DivExpr;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.EnterMonitorStmt;
import soot.jimple.EqExpr;
import soot.jimple.ExitMonitorStmt;
import soot.jimple.Expr;
import soot.jimple.GeExpr;
import soot.jimple.GotoStmt;
import soot.jimple.GtExpr;
import soot.jimple.IdentityStmt;
import soot.jimple.IfStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceOfExpr;
import soot.jimple.IntConstant;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LeExpr;
import soot.jimple.LengthExpr;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.NegExpr;
import soot.jimple.NewArrayExpr;
import soot.jimple.NewExpr;
import soot.jimple.NewMultiArrayExpr;
import soot.jimple.NopStmt;
import soot.jimple.OrExpr;
import soot.jimple.RemExpr;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.ShlExpr;
import soot.jimple.ShrExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.SubExpr;
import soot.jimple.TableSwitchStmt;
import soot.jimple.ThrowStmt;
import soot.jimple.UshrExpr;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.XorExpr;
import soot.tagkit.Tag;

/* compiled from: GrimpInline.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/printer/GrimpInline;", "Lsoot/grimp/Grimp;", "<init>", "()V", "newExpr", "Lsoot/Value;", "value", "inline", "Lsoot/Unit;", "u", "corax-data-flow"})
/* loaded from: GrimpInline.class */
public class GrimpInline extends Grimp {
    public GrimpInline() {
        super((Singletons.Global) null);
    }

    @NotNull
    public Value newExpr(@NotNull Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Expr) {
            final ExprBox returnedExpr = new ExprBox(IntConstant.v(0));
            value.apply(new AbstractExprSwitch<Object>() { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline.newExpr.1
                public void caseAddExpr(AddExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newAddExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseAndExpr(AndExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newAndExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseCmpExpr(CmpExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newCmpExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseCmpgExpr(CmpgExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newCmpgExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseCmplExpr(CmplExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newCmplExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseDivExpr(DivExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newDivExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseEqExpr(EqExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newEqExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseNeExpr(NeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newNeExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseGeExpr(GeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newGeExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseGtExpr(GtExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newGtExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseLeExpr(LeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newLeExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseLtExpr(LtExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newLtExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseMulExpr(MulExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newMulExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseOrExpr(OrExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newOrExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseRemExpr(RemExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newRemExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseShlExpr(ShlExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newShlExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseShrExpr(ShrExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newShrExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseUshrExpr(UshrExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newUshrExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseSubExpr(SubExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newSubExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseXorExpr(XorExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op1 = v.getOp1();
                    Intrinsics.checkNotNullExpressionValue(op1, "getOp1(...)");
                    Value valueNewExpr = grimpInline2.newExpr(op1);
                    GrimpInline grimpInline3 = this;
                    Value op2 = v.getOp2();
                    Intrinsics.checkNotNullExpressionValue(op2, "getOp2(...)");
                    exprBox.setValue(grimpInline.newXorExpr(valueNewExpr, grimpInline3.newExpr(op2)));
                }

                public void caseInterfaceInvokeExpr(InterfaceInvokeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    final ArrayList newArgList = new ArrayList();
                    int argCount = v.getArgCount();
                    for (int i = 0; i < argCount; i++) {
                        GrimpInline grimpInline = this;
                        Value arg = v.getArg(i);
                        Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                        newArgList.add(grimpInline.newExpr(arg));
                    }
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline2 = this;
                    Local base = v.getBase();
                    Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                    final Value valueNewExpr = grimpInline2.newExpr((Value) base);
                    final SootMethodRef methodRef = v.getMethodRef();
                    exprBox.setValue(new GInterfaceInvokeExpr(newArgList, valueNewExpr, methodRef) { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline$newExpr$1$caseInterfaceInvokeExpr$1
                        {
                            super(valueNewExpr, methodRef, newArgList);
                        }

                        public void toString(UnitPrinter up) {
                            Intrinsics.checkNotNullParameter(up, "up");
                            GrimpInlineKt.invokeToString((InvokeExpr) this, up);
                        }
                    });
                }

                public void caseSpecialInvokeExpr(SpecialInvokeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    final ArrayList newArgList = new ArrayList();
                    int argCount = v.getArgCount();
                    for (int i = 0; i < argCount; i++) {
                        GrimpInline grimpInline = this;
                        Value arg = v.getArg(i);
                        Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                        newArgList.add(grimpInline.newExpr(arg));
                    }
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline2 = this;
                    Local base = v.getBase();
                    Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                    final Value valueNewExpr = grimpInline2.newExpr((Value) base);
                    final SootMethodRef methodRef = v.getMethodRef();
                    exprBox.setValue(new GSpecialInvokeExpr(newArgList, valueNewExpr, methodRef) { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline$newExpr$1$caseSpecialInvokeExpr$1
                        {
                            super(valueNewExpr, methodRef, newArgList);
                        }

                        public void toString(UnitPrinter up) {
                            Intrinsics.checkNotNullParameter(up, "up");
                            GrimpInlineKt.invokeToString((InvokeExpr) this, up);
                        }
                    });
                }

                public void caseStaticInvokeExpr(StaticInvokeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    final ArrayList newArgList = new ArrayList();
                    int argCount = v.getArgCount();
                    for (int i = 0; i < argCount; i++) {
                        GrimpInline grimpInline = this;
                        Value arg = v.getArg(i);
                        Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                        newArgList.add(grimpInline.newExpr(arg));
                    }
                    ExprBox exprBox = returnedExpr;
                    final SootMethodRef methodRef = v.getMethodRef();
                    exprBox.setValue(new GStaticInvokeExpr(newArgList, methodRef) { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline$newExpr$1$caseStaticInvokeExpr$1
                        {
                            super(methodRef, newArgList);
                        }

                        public void toString(UnitPrinter up) {
                            Intrinsics.checkNotNullParameter(up, "up");
                            GrimpInlineKt.invokeToString((InvokeExpr) this, up);
                        }
                    });
                }

                public void caseVirtualInvokeExpr(VirtualInvokeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    final ArrayList newArgList = new ArrayList();
                    int argCount = v.getArgCount();
                    for (int i = 0; i < argCount; i++) {
                        GrimpInline grimpInline = this;
                        Value arg = v.getArg(i);
                        Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                        newArgList.add(grimpInline.newExpr(arg));
                    }
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline2 = this;
                    Local base = v.getBase();
                    Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                    final Value valueNewExpr = grimpInline2.newExpr((Value) base);
                    final SootMethodRef methodRef = v.getMethodRef();
                    exprBox.setValue(new GVirtualInvokeExpr(newArgList, valueNewExpr, methodRef) { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline$newExpr$1$caseVirtualInvokeExpr$1
                        {
                            super(valueNewExpr, methodRef, newArgList);
                        }

                        public void toString(UnitPrinter up) {
                            Intrinsics.checkNotNullParameter(up, "up");
                            GrimpInlineKt.invokeToString((InvokeExpr) this, up);
                        }
                    });
                }

                public void caseDynamicInvokeExpr(DynamicInvokeExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    final ArrayList newArgList = new ArrayList();
                    int argCount = v.getArgCount();
                    for (int i = 0; i < argCount; i++) {
                        GrimpInline grimpInline = this;
                        Value arg = v.getArg(i);
                        Intrinsics.checkNotNullExpressionValue(arg, "getArg(...)");
                        newArgList.add(grimpInline.newExpr(arg));
                    }
                    ExprBox exprBox = returnedExpr;
                    final SootMethodRef bootstrapMethodRef = v.getBootstrapMethodRef();
                    final List bootstrapArgs = v.getBootstrapArgs();
                    final SootMethodRef methodRef = v.getMethodRef();
                    final int handleTag = v.getHandleTag();
                    exprBox.setValue(new GDynamicInvokeExpr(newArgList, bootstrapMethodRef, bootstrapArgs, methodRef, handleTag) { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline$newExpr$1$caseDynamicInvokeExpr$1
                        {
                            super(bootstrapMethodRef, bootstrapArgs, methodRef, handleTag, newArgList);
                        }

                        public void toString(UnitPrinter up) {
                            Intrinsics.checkNotNullParameter(up, "up");
                            GrimpInlineKt.invokeToString((InvokeExpr) this, up);
                        }
                    });
                }

                public void caseCastExpr(CastExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op = v.getOp();
                    Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                    exprBox.setValue(grimpInline.newCastExpr(grimpInline2.newExpr(op), v.getType()));
                }

                public void caseInstanceOfExpr(InstanceOfExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op = v.getOp();
                    Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                    exprBox.setValue(grimpInline.newInstanceOfExpr(grimpInline2.newExpr(op), v.getCheckType()));
                }

                public void caseNewArrayExpr(NewArrayExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    returnedExpr.setValue(this.newNewArrayExpr(v.getBaseType(), v.getSize()));
                }

                public void caseNewMultiArrayExpr(NewMultiArrayExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    returnedExpr.setValue(this.newNewMultiArrayExpr(v.getBaseType(), v.getSizes()));
                }

                public void caseNewExpr(NewExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    returnedExpr.setValue((Value) v);
                }

                public void caseLengthExpr(LengthExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op = v.getOp();
                    Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                    exprBox.setValue(grimpInline.newLengthExpr(grimpInline2.newExpr(op)));
                }

                public void caseNegExpr(NegExpr v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ExprBox exprBox = returnedExpr;
                    GrimpInline grimpInline = this;
                    GrimpInline grimpInline2 = this;
                    Value op = v.getOp();
                    Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                    exprBox.setValue(grimpInline.newNegExpr(grimpInline2.newExpr(op)));
                }

                public void defaultCase(Object v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    returnedExpr.setValue((Expr) v);
                }
            });
            Value value2 = returnedExpr.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "getValue(...)");
            return value2;
        }
        if (value instanceof ArrayRef) {
            Value base = ((ArrayRef) value).getBase();
            Value index = ((ArrayRef) value).getIndex();
            Intrinsics.checkNotNullExpressionValue(index, "getIndex(...)");
            Value valueNewArrayRef = newArrayRef(base, newExpr(index));
            Intrinsics.checkNotNullExpressionValue(valueNewArrayRef, "newArrayRef(...)");
            return valueNewArrayRef;
        }
        if (value instanceof InstanceFieldRef) {
            Value base2 = ((InstanceFieldRef) value).getBase();
            Intrinsics.checkNotNullExpressionValue(base2, "getBase(...)");
            Value valueNewInstanceFieldRef = newInstanceFieldRef(newExpr(base2), ((InstanceFieldRef) value).getFieldRef());
            Intrinsics.checkNotNullExpressionValue(valueNewInstanceFieldRef, "newInstanceFieldRef(...)");
            return valueNewInstanceFieldRef;
        }
        return value;
    }

    @NotNull
    public final Unit inline(@NotNull Unit u) {
        Intrinsics.checkNotNullParameter(u, "u");
        final UnitBox newStmtBox = newStmtBox(null);
        u.apply(new AbstractStmtSwitch<Object>() { // from class: cn.sast.dataflow.interprocedural.check.printer.GrimpInline.inline.1
            public void caseAssignStmt(AssignStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                Value leftOp = s.getLeftOp();
                GrimpInline grimpInline2 = this;
                Value rightOp = s.getRightOp();
                Intrinsics.checkNotNullExpressionValue(rightOp, "getRightOp(...)");
                unitBox.setUnit(grimpInline.newAssignStmt(leftOp, grimpInline2.newExpr(rightOp)));
            }

            public void caseIdentityStmt(IdentityStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                Value leftOp = s.getLeftOp();
                GrimpInline grimpInline2 = this;
                Value rightOp = s.getRightOp();
                Intrinsics.checkNotNullExpressionValue(rightOp, "getRightOp(...)");
                unitBox.setUnit(grimpInline.newIdentityStmt(leftOp, grimpInline2.newExpr(rightOp)));
            }

            public void caseBreakpointStmt(BreakpointStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                newStmtBox.setUnit(this.newBreakpointStmt(s));
            }

            public void caseInvokeStmt(InvokeStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                GrimpInline grimpInline = this;
                InvokeExpr invokeExpr = s.getInvokeExpr();
                Intrinsics.checkNotNullExpressionValue(invokeExpr, "getInvokeExpr(...)");
                Value expr = grimpInline.newExpr((Value) invokeExpr);
                newStmtBox.setUnit(this.newInvokeStmt(expr));
            }

            public void caseEnterMonitorStmt(EnterMonitorStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value op = s.getOp();
                Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                unitBox.setUnit(grimpInline.newEnterMonitorStmt(grimpInline2.newExpr(op)));
            }

            public void caseExitMonitorStmt(ExitMonitorStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value op = s.getOp();
                Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                unitBox.setUnit(grimpInline.newExitMonitorStmt(grimpInline2.newExpr(op)));
            }

            public void caseGotoStmt(GotoStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                newStmtBox.setUnit(this.newGotoStmt(s));
            }

            public void caseIfStmt(IfStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value condition = s.getCondition();
                Intrinsics.checkNotNullExpressionValue(condition, "getCondition(...)");
                unitBox.setUnit(grimpInline.newIfStmt(grimpInline2.newExpr(condition), (Unit) s.getTarget()));
            }

            public void caseLookupSwitchStmt(LookupSwitchStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value key = s.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
                unitBox.setUnit(grimpInline.newLookupSwitchStmt(grimpInline2.newExpr(key), s.getLookupValues(), s.getTargets(), s.getDefaultTarget()));
            }

            public void caseNopStmt(NopStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                newStmtBox.setUnit(this.newNopStmt(s));
            }

            public void caseReturnStmt(ReturnStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value op = s.getOp();
                Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                unitBox.setUnit(grimpInline.newReturnStmt(grimpInline2.newExpr(op)));
            }

            public void caseReturnVoidStmt(ReturnVoidStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                newStmtBox.setUnit(this.newReturnVoidStmt(s));
            }

            public void caseTableSwitchStmt(TableSwitchStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value key = s.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
                unitBox.setUnit(grimpInline.newTableSwitchStmt(grimpInline2.newExpr(key), s.getLowIndex(), s.getHighIndex(), s.getTargets(), s.getDefaultTarget()));
            }

            public void caseThrowStmt(ThrowStmt s) {
                Intrinsics.checkNotNullParameter(s, "s");
                UnitBox unitBox = newStmtBox;
                GrimpInline grimpInline = this;
                GrimpInline grimpInline2 = this;
                Value op = s.getOp();
                Intrinsics.checkNotNullExpressionValue(op, "getOp(...)");
                unitBox.setUnit(grimpInline.newThrowStmt(grimpInline2.newExpr(op)));
            }
        });
        Unit unit = newStmtBox.getUnit();
        Intrinsics.checkNotNull(unit, "null cannot be cast to non-null type soot.jimple.Stmt");
        Unit unit2 = (Stmt) unit;
        Tag lnTag = u.getTag("LineNumberTag");
        if (lnTag != null) {
            unit2.addTag(lnTag);
        }
        Tag slpTag = u.getTag("SourceLnPosTag");
        if (slpTag != null) {
            unit2.addTag(slpTag);
        }
        return unit2;
    }
}
