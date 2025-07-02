package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.IBinOpExpr;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.IIexGetField;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.IModelExpressionVisitor;
import com.feysh.corax.config.api.IQOpExpr;
import com.feysh.corax.config.api.ITriOpExpr;
import com.feysh.corax.config.api.IUnOpExpr;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¥\u0001\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n��*\u0001��\b\n\u0018��2\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00040\u0001J\u001a\u0010\u0005\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J*\u0010\b\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00030\n2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\fH\u0086@¢\u0006\u0002\u0010\u000fJH\u0010\u0010\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\f*\b\u0012\u0004\u0012\u00020\u00030\u0019J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020\u001bH\u0016J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020\u001cH\u0016J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020\u001dH\u0016J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020\u001eH\u0016J$\u0010\u001a\u001a\u0016\u0012\u0012\u0012\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` 0\u00022\u0006\u0010\u0006\u001a\u00020!H\u0016Jk\u00102\u001a\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` *\u00060\rj\u0002`\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u00103\u001a\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` 2*\u00104\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` 05\"\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001f¢\u0006\u0002\u00106J_\u00102\u001a\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` *\u00060\rj\u0002`\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0010\u00103\u001a\f\u0012\u0004\u0012\u00020\u00030/j\u0002`72\"\u00104\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0004\u0012\u00020\u00030/j\u0002`705\"\b\u0012\u0004\u0012\u00020\u00030/¢\u0006\u0002\u00108Jp\u00109\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0006\u001a\u00020:2\u0018\u0010;\u001a\u0014\u0012\b\u0012\u00060\rj\u0002`\u000e\u0018\u00010\u001fj\u0004\u0018\u0001` 2\u0018\u0010<\u001a\u0014\u0012\b\u0012\u00060\rj\u0002`\u000e\u0018\u00010\u001fj\u0004\u0018\u0001` 2\u001a\u00103\u001a\u0016\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020>\u0012\u0006\u0012\u0004\u0018\u00010\u00030=H\u0086@¢\u0006\u0002\u0010?J,\u0010@\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\u00032\b\u0010<\u001a\u0004\u0018\u00010\u0003J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020:H\u0016J\u001a\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\u0006\u001a\u00020AH\u0016R\u0013\u0010\"\u001a\u00020��¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R'\u0010&\u001a\u0014\u0012\b\u0012\u00060\rj\u0002`\u000e\u0018\u00010\u001fj\u0004\u0018\u0001` *\u00020\u00038F¢\u0006\u0006\u001a\u0004\b'\u0010(R'\u0010)\u001a\u00060\rj\u0002`\u000e*\u0010\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001fj\u0002` 8F¢\u0006\u0006\u001a\u0004\b*\u0010+R*\u0010,\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001f*\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u001f8Æ\u0002¢\u0006\u0006\u001a\u0004\b-\u0010.R&\u0010,\u001a\n\u0012\u0002\b\u00030/j\u0002`0*\n\u0012\u0002\b\u00030/j\u0002`08Æ\u0002¢\u0006\u0006\u001a\u0004\b-\u00101¨\u0006B"}, d2 = {"cn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1", "Lcom/feysh/corax/config/api/IModelExpressionVisitor;", "Lkotlin/sequences/Sequence;", "", "Lcn/sast/dataflow/interprocedural/check/ExprResType;", "default", "expr", "Lcom/feysh/corax/config/api/IExpr;", "emit", "", "Lkotlin/sequences/SequenceScope;", "v", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/V;", "(Lkotlin/sequences/SequenceScope;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolve", "baseSet", "accessPath", "", "Lcom/feysh/corax/config/api/IClassField;", "acpIndex", "", "(Lkotlin/sequences/SequenceScope;Lcom/feysh/corax/config/api/IExpr;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toHV", "", "visit", "Lcom/feysh/corax/config/api/IIexGetField;", "Lcom/feysh/corax/config/api/IUnOpExpr;", "Lcom/feysh/corax/config/api/ITriOpExpr;", "Lcom/feysh/corax/config/api/IQOpExpr;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/check/ValueType;", "Lcom/feysh/corax/config/api/IIexLoad;", "self", "getSelf", "()Lcn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1;", "Lcn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1;", "companionV", "getCompanionV", "(Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "bindDelegate", "getBindDelegate", "(Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/IValue;", "assume", "getAssume", "(Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "Lcn/sast/dataflow/interprocedural/check/SetType;", "(Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;)Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "asCompanionV", "op", "op2", "", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcom/feysh/corax/config/api/IExpr;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;[Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/check/SetTypeAny;", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcom/feysh/corax/config/api/IExpr;Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;[Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;)Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "relationalBinOp", "Lcom/feysh/corax/config/api/IBinOpExpr;", "lhs", "rhs", "Lkotlin/Function2;", "", "(Lkotlin/sequences/SequenceScope;Lcom/feysh/corax/config/api/IBinOpExpr;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitBinOp", "Lcom/feysh/corax/config/api/IIexConst;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1430:1\n621#1:1431\n622#1:1438\n622#1:1439\n621#1:1440\n621#1:1441\n621#1:1442\n621#1:1443\n46#2:1432\n47#2:1434\n44#2:1436\n1#3:1433\n1863#4:1435\n1864#4:1437\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactory$resolve$visitor$1\n*L\n310#1:1431\n409#1:1438\n417#1:1439\n641#1:1440\n647#1:1441\n653#1:1442\n659#1:1443\n329#1:1432\n329#1:1434\n339#1:1436\n329#1:1433\n338#1:1435\n338#1:1437\n*E\n"})
/* loaded from: HeapFactory$resolve$visitor$1.class */
public final class HeapFactory$resolve$visitor$1 implements IModelExpressionVisitor<Sequence<? extends Object>> {
    private final HeapFactory$resolve$visitor$1 self = this;
    final /* synthetic */ HeapFactory this$0;
    final /* synthetic */ HeapValuesEnv $env;
    final /* synthetic */ ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> $atCall;

    HeapFactory$resolve$visitor$1(HeapFactory $receiver, HeapValuesEnv $env, ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB) {
        this.this$0 = $receiver;
        this.$env = $env;
        this.$atCall = iCallCB;
    }

    /* renamed from: default, reason: not valid java name and merged with bridge method [inline-methods] */
    public Sequence<Object> m187default(IExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$default$1(this.this$0, expr, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(kotlin.sequences.SequenceScope<java.lang.Object> r7, cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            r0 = r9
            boolean r0 = r0 instanceof cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$emit$1
            if (r0 == 0) goto L27
            r0 = r9
            cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$emit$1 r0 = (cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$emit$1) r0
            r15 = r0
            r0 = r15
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r15
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$emit$1 r0 = new cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1$emit$1
            r1 = r0
            r2 = r6
            r3 = r9
            r1.<init>(r2, r3)
            r15 = r0
        L32:
            r0 = r15
            java.lang.Object r0 = r0.result
            r14 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r16 = r0
            r0 = r15
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto Lac;
                default: goto Ld7;
            }
        L58:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            java.util.Iterator r0 = r0.iterator()
            r10 = r0
        L65:
            r0 = r10
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Ld3
            r0 = r10
            java.lang.Object r0 = r0.next()
            cn.sast.dataflow.interprocedural.analysis.CompanionV r0 = (cn.sast.dataflow.interprocedural.analysis.CompanionV) r0
            r11 = r0
            r0 = r7
            r1 = r11
            r12 = r1
            r1 = 0
            r13 = r1
            r1 = r12
            r2 = r15
            r3 = r15
            r4 = r6
            r3.L$0 = r4
            r3 = r15
            r4 = r7
            r3.L$1 = r4
            r3 = r15
            r4 = r10
            r3.L$2 = r4
            r3 = r15
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.yield(r1, r2)
            r1 = r0
            r2 = r16
            if (r1 != r2) goto Lcf
            r1 = r16
            return r1
        Lac:
            r0 = r15
            java.lang.Object r0 = r0.L$2
            java.util.Iterator r0 = (java.util.Iterator) r0
            r10 = r0
            r0 = r15
            java.lang.Object r0 = r0.L$1
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            r7 = r0
            r0 = r15
            java.lang.Object r0 = r0.L$0
            cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1 r0 = (cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1) r0
            r6 = r0
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r14
        Lcf:
            goto L65
        Ld3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ld7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1.emit(kotlin.sequences.SequenceScope, cn.sast.dataflow.interprocedural.analysis.IHeapValues, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Removed duplicated region for block: B:162:0x08f6  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0a67  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:188:0x0a64 -> B:160:0x08ec). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0209 -> B:42:0x0188). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object resolve(kotlin.sequences.SequenceScope<java.lang.Object> r11, com.feysh.corax.config.api.IExpr r12, cn.sast.dataflow.interprocedural.analysis.IHeapValues<cn.sast.dataflow.interprocedural.analysis.IValue> r13, java.util.List<? extends com.feysh.corax.config.api.IClassField> r14, int r15, kotlin.coroutines.Continuation<? super kotlin.Unit> r16) throws kotlin.NoWhenBranchMatchedException, kotlin.NotImplementedError {
        /*
            Method dump skipped, instructions count: 2749
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.HeapFactory$resolve$visitor$1.resolve(kotlin.sequences.SequenceScope, com.feysh.corax.config.api.IExpr, cn.sast.dataflow.interprocedural.analysis.IHeapValues, java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final IHeapValues<IValue> toHV(Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        IHeapValues.Builder b = this.this$0.emptyBuilder();
        for (Object e : collection) {
            if (e instanceof CompanionV) {
                if (!(((CompanionV) e).getValue() instanceof IValue)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                b.add((CompanionV) e);
            }
        }
        return b.build();
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m188visit(IIexGetField expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visit$1(expr, this, null));
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m189visit(IUnOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visit$2(expr, this, this.this$0, this.$atCall, this.$env, null));
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m190visit(ITriOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return (Sequence) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m191visit(IQOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return (Sequence) IModelExpressionVisitor.DefaultImpls.visit(this, expr);
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<CompanionV<IValue>> m192visit(IIexLoad expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visit$3(this.$atCall, expr, this, null));
    }

    public final HeapFactory$resolve$visitor$1 getSelf() {
        return this.self;
    }

    public final CompanionV<IValue> getCompanionV(Object $this$companionV) {
        Intrinsics.checkNotNullParameter($this$companionV, "<this>");
        if ($this$companionV instanceof CompanionV) {
            return (CompanionV) $this$companionV;
        }
        return null;
    }

    public final IValue getBindDelegate(CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "<this>");
        if (companionV instanceof CompanionValueOfConst) {
            return ((CompanionValueOfConst) companionV).getAttr();
        }
        return companionV.getValue();
    }

    public final CompanionV<IValue> getAssume(CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "<this>");
        return companionV;
    }

    public final ImmutableElementSet<?> getAssume(ImmutableElementSet<?> immutableElementSet) {
        Intrinsics.checkNotNullParameter(immutableElementSet, "<this>");
        return immutableElementSet;
    }

    public final CompanionV<IValue> asCompanionV(IValue $this$asCompanionV, IExpr expr, CompanionV<IValue> companionV, CompanionV<IValue>... companionVArr) {
        Intrinsics.checkNotNullParameter($this$asCompanionV, "<this>");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(companionV, "op");
        Intrinsics.checkNotNullParameter(companionVArr, "op2");
        return this.this$0.push(this.$env, $this$asCompanionV).markOfOp(expr, companionV, (CompanionV<IValue>[]) Arrays.copyOf(companionVArr, companionVArr.length)).pop();
    }

    public final CompanionV<IValue> asCompanionV(IValue $this$asCompanionV, IExpr expr, ImmutableElementSet<Object> immutableElementSet, ImmutableElementSet<Object>... immutableElementSetArr) {
        Intrinsics.checkNotNullParameter($this$asCompanionV, "<this>");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(immutableElementSet, "op");
        Intrinsics.checkNotNullParameter(immutableElementSetArr, "op2");
        return this.this$0.push(this.$env, $this$asCompanionV).markOfOp(expr, immutableElementSet, (ImmutableElementSet<Object>[]) Arrays.copyOf(immutableElementSetArr, immutableElementSetArr.length)).pop();
    }

    public final Object relationalBinOp(SequenceScope<Object> sequenceScope, IBinOpExpr expr, CompanionV<IValue> companionV, CompanionV<IValue> companionV2, Function2<? super Number, ? super Number, ? extends Object> function2, Continuation<? super Unit> continuation) {
        if (companionV == null || companionV2 == null) {
            return Unit.INSTANCE;
        }
        HeapFactory heapFactory = this.this$0;
        Integer intValue$default = FactValuesKt.getIntValue$default(companionV.getValue(), false, 1, null);
        if (intValue$default != null) {
            int leftInt = intValue$default.intValue();
            Integer intValue$default2 = FactValuesKt.getIntValue$default(companionV2.getValue(), false, 1, null);
            if (intValue$default2 != null) {
                int rightInt = intValue$default2.intValue();
                Object objInvoke = function2.invoke(Boxing.boxInt(leftInt), Boxing.boxInt(rightInt));
                if (objInvoke != null) {
                    CompanionV $this$assume$iv = asCompanionV(heapFactory.toConstVal(objInvoke), (IExpr) expr, companionV, companionV2);
                    Object objYield = sequenceScope.yield($this$assume$iv, continuation);
                    return objYield == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objYield : Unit.INSTANCE;
                }
            }
        }
        HeapFactory heapFactory2 = this.this$0;
        Long longValue$default = FactValuesKt.getLongValue$default(companionV.getValue(), false, 1, null);
        if (longValue$default != null) {
            long leftInt2 = longValue$default.longValue();
            Long longValue$default2 = FactValuesKt.getLongValue$default(companionV2.getValue(), false, 1, null);
            if (longValue$default2 != null) {
                long rightInt2 = longValue$default2.longValue();
                Object objInvoke2 = function2.invoke(Boxing.boxLong(leftInt2), Boxing.boxLong(rightInt2));
                if (objInvoke2 != null) {
                    CompanionV $this$assume$iv2 = asCompanionV(heapFactory2.toConstVal(objInvoke2), (IExpr) expr, companionV, companionV2);
                    Object objYield2 = sequenceScope.yield($this$assume$iv2, continuation);
                    return objYield2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objYield2 : Unit.INSTANCE;
                }
            }
        }
        HeapFactory heapFactory3 = this.this$0;
        Float floatValue$default = FactValuesKt.getFloatValue$default(companionV.getValue(), false, 1, null);
        if (floatValue$default != null) {
            float leftInt3 = floatValue$default.floatValue();
            Float floatValue$default2 = FactValuesKt.getFloatValue$default(companionV2.getValue(), false, 1, null);
            if (floatValue$default2 != null) {
                float rightInt3 = floatValue$default2.floatValue();
                Object objInvoke3 = function2.invoke(Boxing.boxFloat(leftInt3), Boxing.boxFloat(rightInt3));
                if (objInvoke3 != null) {
                    CompanionV $this$assume$iv3 = asCompanionV(heapFactory3.toConstVal(objInvoke3), (IExpr) expr, companionV, companionV2);
                    Object objYield3 = sequenceScope.yield($this$assume$iv3, continuation);
                    return objYield3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objYield3 : Unit.INSTANCE;
                }
            }
        }
        HeapFactory heapFactory4 = this.this$0;
        Double doubleValue$default = FactValuesKt.getDoubleValue$default(companionV.getValue(), false, 1, null);
        if (doubleValue$default != null) {
            double leftInt4 = doubleValue$default.doubleValue();
            Double doubleValue$default2 = FactValuesKt.getDoubleValue$default(companionV2.getValue(), false, 1, null);
            if (doubleValue$default2 != null) {
                double rightInt4 = doubleValue$default2.doubleValue();
                Object objInvoke4 = function2.invoke(Boxing.boxDouble(leftInt4), Boxing.boxDouble(rightInt4));
                if (objInvoke4 != null) {
                    CompanionV $this$assume$iv4 = asCompanionV(heapFactory4.toConstVal(objInvoke4), (IExpr) expr, companionV, companionV2);
                    Object objYield4 = sequenceScope.yield($this$assume$iv4, continuation);
                    return objYield4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objYield4 : Unit.INSTANCE;
                }
            }
        }
        return Unit.INSTANCE;
    }

    public final Sequence<Object> visitBinOp(IBinOpExpr expr, Object lhs, Object rhs) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visitBinOp$1(expr, lhs, rhs, this, this.this$0, this.$env, this.$atCall, null));
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m193visit(IBinOpExpr expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visit$4(expr, this, null));
    }

    /* renamed from: visit, reason: merged with bridge method [inline-methods] */
    public Sequence<Object> m194visit(IIexConst expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        return SequencesKt.sequence(new HeapFactory$resolve$visitor$1$visit$5(expr, this.this$0, this, this.$env, null));
    }
}
