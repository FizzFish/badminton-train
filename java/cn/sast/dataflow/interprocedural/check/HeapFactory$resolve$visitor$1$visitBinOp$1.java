package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.BinOp;
import com.feysh.corax.config.api.IBinOpExpr;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""})
@DebugMetadata(f = "HeapFactory.kt", l = {682, 684, 685, 689, 690, 694, 695, 720, 721, 722, 725, 738, 773, 803, 825, 829, 840, 851, 862, 873, 884, 895, 906, 917, 930}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, 2, 3, 4, 5, 13, 13, 13}, s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$1", "L$2"}, n = {"$this$sequence", "$this$sequence", "$this$sequence", "$this$sequence", "$this$sequence", "$this$sequence", "$this$sequence", "op", "prefix"}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visitBinOp$1")
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visitBinOp$1\n+ 2 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1430:1\n621#2:1431\n622#2:1433\n622#2:1434\n622#2:1435\n621#2:1436\n621#2:1437\n621#2:1438\n621#2:1439\n621#2:1440\n1#3:1432\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visitBinOp$1\n*L\n682#1:1431\n720#1:1433\n721#1:1434\n722#1:1435\n729#1:1436\n742#1:1437\n773#1:1438\n803#1:1439\n825#1:1440\n*E\n"})
/* loaded from: HeapFactory$resolve$visitor$1$visitBinOp$1.class */
final class HeapFactory$resolve$visitor$1$visitBinOp$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ IBinOpExpr $expr;
    final /* synthetic */ Object $lhs;
    final /* synthetic */ Object $rhs;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;
    final /* synthetic */ HeapFactory this$1;
    final /* synthetic */ HeapValuesEnv $env;
    final /* synthetic */ ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> $atCall;

    /* compiled from: HeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: HeapFactory$resolve$visitor$1$visitBinOp$1$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BinOp.values().length];
            try {
                iArr[BinOp.Or.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BinOp.AnyOf.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BinOp.OrSet.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BinOp.AndSet.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BinOp.RemoveSet.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BinOp.ContainsSet.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[BinOp.HasIntersectionSet.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[BinOp.And.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[BinOp.Xor.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[BinOp.StartsWith.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[BinOp.EndsWith.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[BinOp.Contains.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[BinOp.StringEquals.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[BinOp.IsInstanceOf.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[BinOp.LT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[BinOp.LE.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[BinOp.EQ.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[BinOp.GE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[BinOp.GT.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[BinOp.Add.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[BinOp.Sub.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[BinOp.Mul.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[BinOp.Div.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[BinOp.Mod.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[BinOp.BvAnd.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[BinOp.BvOr.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[BinOp.BvXor.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[BinOp.BvShr.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[BinOp.BvShl.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[BinOp.BvLShr.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$visitBinOp$1(IBinOpExpr $expr, Object $lhs, Object $rhs, HeapFactory$resolve$visitor$1 $receiver, HeapFactory $receiver2, HeapValuesEnv $env, ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB, Continuation<? super HeapFactory$resolve$visitor$1$visitBinOp$1> continuation) {
        super(2, continuation);
        this.$expr = $expr;
        this.$lhs = $lhs;
        this.$rhs = $rhs;
        this.this$0 = $receiver;
        this.this$1 = $receiver2;
        this.$env = $env;
        this.$atCall = iCallCB;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> heapFactory$resolve$visitor$1$visitBinOp$1 = new HeapFactory$resolve$visitor$1$visitBinOp$1(this.$expr, this.$lhs, this.$rhs, this.this$0, this.this$1, this.$env, this.$atCall, continuation);
        heapFactory$resolve$visitor$1$visitBinOp$1.L$0 = value;
        return heapFactory$resolve$visitor$1$visitBinOp$1;
    }

    public final Object invoke(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x08fc, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0906  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x09a2  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x09a6  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x09f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x08fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0359  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws kotlin.NoWhenBranchMatchedException, java.lang.ArithmeticException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 3737
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visitBinOp$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final Object invokeSuspend$lambda$6(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Boolean.valueOf(iIntValue < ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Boolean.valueOf(jLongValue < ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Boolean.valueOf(fFloatValue < ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Boolean.valueOf(dDoubleValue < ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$7(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Boolean.valueOf(iIntValue <= ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Boolean.valueOf(jLongValue <= ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Boolean.valueOf(fFloatValue <= ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Boolean.valueOf(dDoubleValue <= ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$8(Number l, Number r) {
        if (l instanceof Integer) {
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Boolean.valueOf(Intrinsics.areEqual(l, (Integer) r));
        }
        if (l instanceof Long) {
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Boolean.valueOf(Intrinsics.areEqual(l, (Long) r));
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Boolean.valueOf(fFloatValue == ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Boolean.valueOf(dDoubleValue == ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$9(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Boolean.valueOf(iIntValue >= ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Boolean.valueOf(jLongValue >= ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Boolean.valueOf(fFloatValue >= ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Boolean.valueOf(dDoubleValue >= ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$10(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Boolean.valueOf(iIntValue > ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Boolean.valueOf(jLongValue > ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Boolean.valueOf(fFloatValue > ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Boolean.valueOf(dDoubleValue > ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$11(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Integer.valueOf(iIntValue + ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Long.valueOf(jLongValue + ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Float.valueOf(fFloatValue + ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Double.valueOf(dDoubleValue + ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$12(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Integer.valueOf(iIntValue - ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Long.valueOf(jLongValue - ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Float.valueOf(fFloatValue - ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Double.valueOf(dDoubleValue - ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$13(Number l, Number r) {
        if (l instanceof Integer) {
            int iIntValue = l.intValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
            return Integer.valueOf(iIntValue * ((Integer) r).intValue());
        }
        if (l instanceof Long) {
            long jLongValue = l.longValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
            return Long.valueOf(jLongValue * ((Long) r).longValue());
        }
        if (l instanceof Float) {
            float fFloatValue = l.floatValue();
            Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
            return Float.valueOf(fFloatValue * ((Float) r).floatValue());
        }
        if (!(l instanceof Double)) {
            return null;
        }
        double dDoubleValue = l.doubleValue();
        Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
        return Double.valueOf(dDoubleValue * ((Double) r).doubleValue());
    }

    private static final Object invokeSuspend$lambda$14(Number l, Number r) {
        Unit unit;
        Unit unitValueOf;
        try {
            if (l instanceof Integer) {
                int iIntValue = l.intValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
                unitValueOf = Integer.valueOf(iIntValue / ((Integer) r).intValue());
            } else if (l instanceof Long) {
                long jLongValue = l.longValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
                unitValueOf = Long.valueOf(jLongValue / ((Long) r).longValue());
            } else if (l instanceof Float) {
                float fFloatValue = l.floatValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
                unitValueOf = Float.valueOf(fFloatValue / ((Float) r).floatValue());
            } else if (l instanceof Double) {
                double dDoubleValue = l.doubleValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
                unitValueOf = Double.valueOf(dDoubleValue / ((Double) r).doubleValue());
            } else {
                unitValueOf = null;
            }
            unit = unitValueOf;
        } catch (RuntimeException e) {
            unit = Unit.INSTANCE;
        }
        return unit;
    }

    private static final Object invokeSuspend$lambda$15(Number l, Number r) {
        Unit unit;
        Unit unitValueOf;
        try {
            if (l instanceof Integer) {
                int iIntValue = l.intValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Int");
                unitValueOf = Integer.valueOf(iIntValue % ((Integer) r).intValue());
            } else if (l instanceof Long) {
                long jLongValue = l.longValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Long");
                unitValueOf = Long.valueOf(jLongValue % ((Long) r).longValue());
            } else if (l instanceof Float) {
                float fFloatValue = l.floatValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Float");
                unitValueOf = Float.valueOf(fFloatValue % ((Float) r).floatValue());
            } else if (l instanceof Double) {
                double dDoubleValue = l.doubleValue();
                Intrinsics.checkNotNull(r, "null cannot be cast to non-null type kotlin.Double");
                unitValueOf = Double.valueOf(dDoubleValue % ((Double) r).doubleValue());
            } else {
                unitValueOf = null;
            }
            unit = unitValueOf;
        } catch (RuntimeException e) {
            unit = Unit.INSTANCE;
        }
        return unit;
    }
}
