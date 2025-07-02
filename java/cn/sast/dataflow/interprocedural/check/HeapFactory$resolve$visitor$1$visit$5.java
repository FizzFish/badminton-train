package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import com.feysh.corax.config.api.IIexConst;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""})
@DebugMetadata(f = "HeapFactory.kt", l = {983, 1029, 1033}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$5")
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$5\n+ 2 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n*L\n1#1,1430:1\n622#2:1431\n621#2:1432\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$5\n*L\n1029#1:1431\n1033#1:1432\n*E\n"})
/* loaded from: HeapFactory$resolve$visitor$1$visit$5.class */
final class HeapFactory$resolve$visitor$1$visit$5 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ IIexConst $expr;
    final /* synthetic */ HeapFactory this$0;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$1;
    final /* synthetic */ HeapValuesEnv $env;

    /* compiled from: HeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: HeapFactory$resolve$visitor$1$visit$5$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IIexConst.Type.values().length];
            try {
                iArr[IIexConst.Type.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IIexConst.Type.EmptyElement.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IIexConst.Type.Boolean.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IIexConst.Type.Short.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[IIexConst.Type.Int.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[IIexConst.Type.Long.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[IIexConst.Type.Float.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[IIexConst.Type.Double.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[IIexConst.Type.String.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[IIexConst.Type.Class.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[IIexConst.Type.TaintSet.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[IIexConst.Type.ViaSet.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$visit$5(IIexConst $expr, HeapFactory $receiver, HeapFactory$resolve$visitor$1 $receiver2, HeapValuesEnv $env, Continuation<? super HeapFactory$resolve$visitor$1$visit$5> continuation) {
        super(2, continuation);
        this.$expr = $expr;
        this.this$0 = $receiver;
        this.this$1 = $receiver2;
        this.$env = $env;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> heapFactory$resolve$visitor$1$visit$5 = new HeapFactory$resolve$visitor$1$visit$5(this.$expr, this.this$0, this.this$1, this.$env, continuation);
        heapFactory$resolve$visitor$1$visit$5.L$0 = value;
        return heapFactory$resolve$visitor$1$visit$5;
    }

    public final Object invoke(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Removed duplicated region for block: B:119:0x046c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws kotlin.NoWhenBranchMatchedException {
        /*
            Method dump skipped, instructions count: 1156
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final CompanionV<IValue> invokeSuspend$asConstCompanionV(IValue $this$invokeSuspend_u24asConstCompanionV, HeapFactory this$0, HeapValuesEnv $env, IIexConst $expr) {
        return this$0.push($env, $this$invokeSuspend_u24asConstCompanionV).markOfConstant($expr).pop();
    }
}
