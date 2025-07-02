package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.IUnOpExpr;
import com.feysh.corax.config.api.UnOp;
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
@DebugMetadata(f = "HeapFactory.kt", l = {483, 500, 512, 523, 527, 545, 568, 572}, i = {PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, 2, 2, 3, 3, 4, 4, 5, 6, 7}, s = {"L$0", "L$0", "L$0", "L$2", "L$0", "L$2", "L$0", "L$2", "L$0", "L$0", "L$0"}, n = {"$this$sequence", "$this$sequence", "$this$sequence", "op1", "$this$sequence", "op1", "$this$sequence", "op1", "$this$sequence", "$this$sequence", "$this$sequence"}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$2")
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$2\n+ 2 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,1430:1\n622#2:1431\n621#2:1433\n621#2:1434\n621#2:1435\n621#2:1437\n621#2:1438\n1#3:1432\n44#4:1436\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1$visit$2\n*L\n483#1:1431\n512#1:1433\n523#1:1434\n527#1:1435\n568#1:1437\n572#1:1438\n544#1:1436\n*E\n"})
/* loaded from: HeapFactory$resolve$visitor$1$visit$2.class */
final class HeapFactory$resolve$visitor$1$visit$2 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ IUnOpExpr $expr;
    final /* synthetic */ HeapFactory$resolve$visitor$1 this$0;
    final /* synthetic */ HeapFactory this$1;
    final /* synthetic */ ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> $atCall;
    final /* synthetic */ HeapValuesEnv $env;

    /* compiled from: HeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: HeapFactory$resolve$visitor$1$visit$2$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UnOp.values().length];
            try {
                iArr[UnOp.GetBoolean.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[UnOp.GetInt.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[UnOp.GetLong.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[UnOp.GetString.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[UnOp.ToLowerCase.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[UnOp.Not.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[UnOp.IsConstant.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[UnOp.GetSet.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[UnOp.GetEnumName.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeapFactory$resolve$visitor$1$visit$2(IUnOpExpr $expr, HeapFactory$resolve$visitor$1 $receiver, HeapFactory $receiver2, ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB, HeapValuesEnv $env, Continuation<? super HeapFactory$resolve$visitor$1$visit$2> continuation) {
        super(2, continuation);
        this.$expr = $expr;
        this.this$0 = $receiver;
        this.this$1 = $receiver2;
        this.$atCall = iCallCB;
        this.$env = $env;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> heapFactory$resolve$visitor$1$visit$2 = new HeapFactory$resolve$visitor$1$visit$2(this.$expr, this.this$0, this.this$1, this.$atCall, this.$env, continuation);
        heapFactory$resolve$visitor$1$visit$2.L$0 = value;
        return heapFactory$resolve$visitor$1$visit$2;
    }

    public final Object invoke(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0079  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x02cd -> B:5:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws kotlin.NoWhenBranchMatchedException {
        /*
            Method dump skipped, instructions count: 1946
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$visit$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
