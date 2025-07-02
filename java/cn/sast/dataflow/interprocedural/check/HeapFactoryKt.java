package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HookEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.check.callback.ICallCBImpl;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import soot.SootField;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��`\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aX\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b��\u0010\r29\b\u0004\u0010\u000e\u001a3\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\r0\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000fH\u0086\bø\u0001��¢\u0006\u0002\u0010\u0016\u001aF\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u00070\u0018*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001a0\u00192\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u00070\u00182\u0006\u0010\u001c\u001a\u00020\u001d\u001aF\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u00070\u0018*\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001a0\u00192\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u00070\u00182\u0006\u0010\u001c\u001a\u00020\u001d*\f\b\u0002\u0010��\"\u00020\u00012\u00020\u0001*\u0018\b\u0002\u0010\u0002\"\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0012\u0004\u0012\u00020\u00040\u0003*\u001c\b\u0002\u0010\u0005\"\b\u0012\u0004\u0012\u0002`\u00070\u00062\f\u0012\b\u0012\u00060\u0001j\u0002`\u00070\u0006*\u0014\b\u0002\u0010\b\"\u0006\u0012\u0002\b\u00030\t2\u0006\u0012\u0002\b\u00030\t*\u0018\b\u0002\u0010\n\"\b\u0012\u0004\u0012\u00020\u00040\t2\b\u0012\u0004\u0012\u00020\u00040\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"}, d2 = {"V", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "ExprResType", "Lkotlin/sequences/Sequence;", "", "ValueType", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "Lcn/sast/dataflow/interprocedural/check/V;", "SetType", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "SetTypeAny", "flowIt", "Lkotlinx/coroutines/flow/Flow;", "T", "c", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/ParameterName;", "name", "flow", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "getValueField", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/check/callback/ICallCBImpl;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "obj", "valueField", "Lsoot/SootField;", "getConstantValue", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactoryKt\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1430:1\n44#2:1431\n1#3:1432\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/HeapFactoryKt\n*L\n44#1:1431\n*E\n"})
/* loaded from: HeapFactoryKt.class */
public final class HeapFactoryKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: HeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 176, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;"})
    @DebugMetadata(f = "HeapFactory.kt", l = {36}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.dataflow.interprocedural.check.HeapFactoryKt$flowIt$1")
    /* renamed from: cn.sast.dataflow.interprocedural.check.HeapFactoryKt$flowIt$1, reason: invalid class name */
    /* loaded from: HeapFactoryKt$flowIt$1.class */
    public static final class AnonymousClass1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> $c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$c = function2;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass1 = new AnonymousClass1<>(this.$c, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return create(flowCollector, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    FlowCollector $this$flow = (FlowCollector) this.L$0;
                    Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> function2 = this.$c;
                    this.label = 1;
                    if (function2.invoke($this$flow, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invokeSuspend$$forInline(Object $result) {
            FlowCollector $this$flow = (FlowCollector) this.L$0;
            this.$c.invoke($this$flow, this);
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public static final <T> Flow<T> flowIt(@NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(function2, "c");
        return FlowKt.flow(new AnonymousClass1(function2, null));
    }

    @NotNull
    public static final IHeapValues<IValue> getValueField(@NotNull ICallCBImpl<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCBImpl, @NotNull IHeapValues<IValue> iHeapValues, @NotNull SootField valueField) {
        Intrinsics.checkNotNullParameter(iCallCBImpl, "<this>");
        Intrinsics.checkNotNullParameter(iHeapValues, "obj");
        Intrinsics.checkNotNullParameter(valueField, "valueField");
        IFact.Builder.DefaultImpls.assignNewExpr$default(iCallCBImpl.getOut(), iCallCBImpl.getEnv(), "@obj", iHeapValues, false, 8, null);
        IFact.Builder<IValue> out = iCallCBImpl.getOut();
        HookEnv env = iCallCBImpl.getEnv();
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        out.getField(env, "@obj.value", "@obj", new JSootFieldType(valueField), true);
        IHeapValues r = iCallCBImpl.getOut().getTargetsUnsafe("@obj.value");
        iCallCBImpl.getOut().kill("@obj");
        iCallCBImpl.getOut().kill("@obj.value");
        return r == null ? iCallCBImpl.getHf().empty() : r;
    }

    @NotNull
    public static final IHeapValues<IValue> getConstantValue(@NotNull ICallCBImpl<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCBImpl, @NotNull IHeapValues<IValue> iHeapValues, @NotNull SootField valueField) {
        Intrinsics.checkNotNullParameter(iCallCBImpl, "<this>");
        Intrinsics.checkNotNullParameter(iHeapValues, "obj");
        Intrinsics.checkNotNullParameter(valueField, "valueField");
        IHeapValues.Builder res = iCallCBImpl.getHf().emptyBuilder();
        IHeapValues it = getValueField(iCallCBImpl, iHeapValues, valueField);
        res.add(it);
        for (CompanionV o : iHeapValues) {
            if (o.getValue() instanceof ConstVal) {
                res.add(o);
            }
        }
        return res.build();
    }
}
