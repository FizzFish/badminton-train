package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.AnyNewExprEnv;
import cn.sast.dataflow.interprocedural.analysis.AnyNewValue;
import cn.sast.dataflow.interprocedural.analysis.CallStackContext;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IDiff;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IFieldManager;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JFieldType;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.analysis.JSootFieldType;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphAbstract;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.heapimpl.FieldHeapKV;
import cn.sast.dataflow.interprocedural.override.lang.WString;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.G;
import soot.RefType;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.jimple.AnyNewExpr;
import soot.jimple.Constant;
import soot.jimple.IntConstant;
import soot.jimple.StringConstant;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001GB\u0099\u0001\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000f0\r\u00120\u0010\u0010\u001a,\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u001e\u0012\u001c\u0012\b\u0012\u00060\u000ej\u0002`\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00130\u00110\r\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001cH\u0016JD\u0010\u001d\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0010\u0010 \u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030!2\n\u0010\"\u001a\u00060\u000ej\u0002`\u00122\b\u0010#\u001a\u0004\u0018\u00010\u000eH\u0016J*\u0010$\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0010\u0010%\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000fJ\u001a\u0010&\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030'2\u0006\u0010(\u001a\u00020)H\u0016JH\u0010*\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030+2\u0006\u0010\u001e\u001a\u00020\u001f2\u0010\u0010%\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000f2\u0006\u0010(\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000fH\u0016J\u001c\u0010.\u001a\u0004\u0018\u00010/2\u0010\u00100\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030!H\u0016J\"\u00101\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u000eH\u0016J:\u00103\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\u0010\u00104\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030!2\n\u0010\"\u001a\u00060\u000ej\u0002`\u0012H\u0016JD\u00105\u001a\u0002062\"\u00107\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u000209\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001c082\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u0002092\u0006\u0010<\u001a\u000209H\u0016J:\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020A2\u0010\u0010B\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001c2\u0006\u0010C\u001a\u00020AH\u0002JF\u0010D\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020E2\u0006\u0010C\u001a\u00020E2\u0010\u0010B\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001c2\u0006\u0010F\u001a\u00020\u001aH\u0016¨\u0006H"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PointsToGraphBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphBuilderAbstract;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "orig", "Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "slots", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "heap", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "calledMethods", "Lkotlinx/collections/immutable/PersistentSet$Builder;", "Lsoot/SootMethod;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentSet$Builder;)V", "mayChange", "", "build", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "newSummary", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "src", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "mt", "key", "newSummaryArraySize", "allocSite", "getEmptyFieldSpace", "Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV;", "type", "Lsoot/RefType;", "getEmptyArraySpace", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapKV;", "Lsoot/ArrayType;", "arrayLength", "getType", "Lsoot/Type;", "value", "getOfSlot", "slot", "getConstantPoolObjectData", "cv", "callEntryFlowFunction", "", "context", "Lcn/sast/idfa/analysis/Context;", "Lsoot/Unit;", "callee", "node", "succ", "activeCalleeReports", "Lcn/sast/dataflow/interprocedural/check/PointsToGraphBuilder$PathTransfer;", "container", "ctx", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "callEdgeValue", "calleeCtx", "updateIntraEdge", "Lsoot/Context;", "hasReturnValue", "PathTransfer", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPointsToGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraphBuilder\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 extensions.kt\nkotlinx/collections/immutable/ExtensionsKt\n*L\n1#1,612:1\n49#2:613\n44#2:614\n44#2:615\n44#2:616\n1#3:617\n362#4:618\n362#4:619\n362#4:620\n*S KotlinDebug\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraphBuilder\n*L\n308#1:613\n371#1:614\n372#1:615\n373#1:616\n556#1:618\n593#1:619\n596#1:620\n*E\n"})
/* loaded from: PointsToGraphBuilder.class */
public class PointsToGraphBuilder extends PointsToGraphBuilderAbstract<IValue> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PointsToGraphBuilder(@NotNull PointsToGraphAbstract<IValue> pointsToGraphAbstract, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull IVGlobal vg, @NotNull CallStackContext callStack, @NotNull PersistentMap.Builder<Object, IHeapValues<IValue>> builder, @NotNull PersistentMap.Builder<IValue, PersistentMap<Object, IData<IValue>>> builder2, @NotNull PersistentSet.Builder<SootMethod> builder3) {
        super(pointsToGraphAbstract, abstractHeapFactory, vg, callStack, builder, builder2, builder3);
        Intrinsics.checkNotNullParameter(pointsToGraphAbstract, "orig");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(vg, "vg");
        Intrinsics.checkNotNullParameter(callStack, "callStack");
        Intrinsics.checkNotNullParameter(builder, "slots");
        Intrinsics.checkNotNullParameter(builder2, "heap");
        Intrinsics.checkNotNullParameter(builder3, "calledMethods");
    }

    public final boolean mayChange() {
        return (getSlots().build() == getOrig().getSlots() && getHeap().build() == getOrig().getHeap() && getCalledMethods().build() == getOrig().getCalledMethods()) ? false : true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    @NotNull
    public IFact<IValue> build() {
        if (!mayChange()) {
            return getOrig();
        }
        return new PointsToGraph(getHf(), getVg(), getCallStack(), getSlots().build(), getHeap().build(), getCalledMethods().build());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract
    @Nullable
    public IHeapValues<IValue> newSummary(@NotNull HeapValuesEnv env, @NotNull CompanionV<IValue> companionV, @NotNull Object mt, @Nullable Object key) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(companionV, "src");
        Intrinsics.checkNotNullParameter(mt, "mt");
        if (mt == BuiltInModelT.Field) {
            if (companionV.getValue() instanceof IFieldManager) {
                AbstractHeapFactory<IValue> hf = getHf();
                IFieldManager iFieldManager = (IFieldManager) companionV.getValue();
                Intrinsics.checkNotNull(key, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.JFieldType");
                return hf.push(env, (HeapValuesEnv) iFieldManager.getPhantomField((JFieldType) key)).markSummaryReturnValueFailedGetKeyFromKey(companionV, mt, key).popHV();
            }
            if ((companionV.getValue() instanceof AnyNewValue) && (key instanceof JFieldType)) {
                IVGlobal vg = getHf().getVg();
                FieldUtil fieldUtil = FieldUtil.INSTANCE;
                JFieldType field$iv = (JFieldType) key;
                Pair<Constant, Type> pairDefaultValue = vg.defaultValue(field$iv.getType());
                Constant constant = (Constant) pairDefaultValue.component1();
                Type type = (Type) pairDefaultValue.component2();
                return getHf().push(env, (HeapValuesEnv) getHf().newConstVal2(constant, type)).markOfConstant(constant, "unset null field").popHV();
            }
            return null;
        }
        if (mt == BuiltInModelT.Array) {
            ArrayType type2 = getType(companionV);
            ArrayType arrayTy = type2 instanceof ArrayType ? type2 : null;
            if (arrayTy != null) {
                AbstractHeapFactory<IValue> hf2 = getHf();
                AbstractHeapFactory<IValue> hf3 = getHf();
                Type elementType = arrayTy.getElementType();
                Intrinsics.checkNotNullExpressionValue(elementType, "getElementType(...)");
                return hf2.push(env, (HeapValuesEnv) hf3.newSummaryVal(env, elementType, companionV.getValue().hashCode() + "." + key)).markSummaryReturnValueFailedGetKeyFromKey(companionV, mt, key).popHV();
            }
            return null;
        }
        return null;
    }

    @NotNull
    public final IHeapValues<IValue> newSummaryArraySize(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        AbstractHeapFactory<IValue> hf = getHf();
        AbstractHeapFactory<IValue> hf2 = getHf();
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        return hf.push(env, (HeapValuesEnv) hf2.newSummaryVal(env, typeSoot_IntType, "arraySize")).markSummaryArraySize(iHeapValues).popHV();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract
    @NotNull
    public FieldHeapKV<IValue> getEmptyFieldSpace(@NotNull RefType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new FieldSpace(type, ExtensionsKt.persistentHashMapOf(), getHf().empty());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract
    @NotNull
    public ArrayHeapKV<IValue> getEmptyArraySpace(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues, @NotNull ArrayType type, @Nullable IHeapValues<IValue> iHeapValues2) {
        IHeapValues iHeapValuesNewSummaryArraySize;
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        Intrinsics.checkNotNullParameter(type, "type");
        if (iHeapValues2 == null || iHeapValues2.isEmpty()) {
            iHeapValuesNewSummaryArraySize = newSummaryArraySize(env, iHeapValues);
        } else {
            iHeapValuesNewSummaryArraySize = iHeapValues2;
        }
        IHeapValues size = iHeapValuesNewSummaryArraySize;
        return ArraySpace.Companion.v(getHf(), env, ExtensionsKt.persistentHashMapOf(), getHf().empty(), type, (IHeapValues<IValue>) size);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract
    @Nullable
    public Type getType(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "value");
        return companionV.getValue().getType();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<IValue> getOfSlot(@NotNull HeapValuesEnv env, @NotNull Object slot) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(slot, "slot");
        IHeapValues<IValue> targets = getTargets(slot);
        return targets == null ? getHf().empty() : targets;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract
    @Nullable
    public IData<IValue> getConstantPoolObjectData(@NotNull HeapValuesEnv env, @NotNull CompanionV<IValue> companionV, @NotNull Object mt) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(companionV, "cv");
        Intrinsics.checkNotNullParameter(mt, "mt");
        IValue value = companionV.getValue();
        if ((value instanceof ConstVal) && (((ConstVal) value).getV() instanceof StringConstant)) {
            if (!getHeap().containsKey(companionV.getValue())) {
                Unit node = env.getNode();
                AbstractHeapFactory<IValue> hf = getHf();
                String str = ((ConstVal) value).getV().value;
                Intrinsics.checkNotNullExpressionValue(str, "value");
                byte[] bytes = str.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                JStrArrValue arrayValue = new JStrArrValue(node, hf, bytes);
                WString ws = WString.Companion.v();
                Object objInvoke = emptyFieldFx().invoke(companionV);
                Intrinsics.checkNotNull(objInvoke);
                IHeapKVData.Builder members = ((FieldHeapKV) objInvoke).builder2();
                AbstractHeapFactory<IValue> hf2 = getHf();
                AnyNewExprEnv nEW_Env = getHf().getVg().getNEW_Env();
                AnyNewExpr newValueExpr = ws.getNewValueExpr();
                Intrinsics.checkNotNullExpressionValue(newValueExpr, "<get-newValueExpr>(...)");
                IValue newArrayValue = hf2.anyNewVal(nEW_Env, newValueExpr);
                IHeapValues newValue = getHf().empty().plus(getHf().push(env, (HeapValuesEnv) newArrayValue).dataSequenceToSeq(companionV).pop());
                IHeapValues<IValue> iHeapValuesEmpty = getHf().empty();
                AbstractHeapFactory<IValue> hf3 = getHf();
                AbstractHeapFactory<IValue> hf4 = getHf();
                Constant latin1 = WString.Companion.getLATIN1();
                Type typeSoot_ByteType = G.v().soot_ByteType();
                Intrinsics.checkNotNullExpressionValue(typeSoot_ByteType, "soot_ByteType(...)");
                IHeapValues newCoder = iHeapValuesEmpty.plus(JOperatorV.DefaultImpls.markOfConstant$default(hf3.push(env, (HeapValuesEnv) hf4.newConstVal2(latin1, typeSoot_ByteType)), WString.Companion.getLATIN1(), null, 2, null).pop());
                IHeapValues<IValue> iHeapValuesEmpty2 = getHf().empty();
                AbstractHeapFactory<IValue> hf5 = getHf();
                AbstractHeapFactory<IValue> hf6 = getHf();
                Constant constantV = IntConstant.v(((ConstVal) value).getV().value.hashCode());
                Intrinsics.checkNotNullExpressionValue(constantV, "v(...)");
                Type typeSoot_IntType = G.v().soot_IntType();
                Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
                IHeapValues newHash = iHeapValuesEmpty2.plus(hf5.push(env, (HeapValuesEnv) hf6.newConstVal2(constantV, typeSoot_IntType)).markOfOp("string.hash", companionV, new CompanionV[0]).pop());
                AbstractHeapFactory<IValue> hf7 = getHf();
                FieldUtil fieldUtil = FieldUtil.INSTANCE;
                SootField field$iv = ws.getValueField();
                members.set(hf7, env, new JSootFieldType(field$iv), newValue, false);
                AbstractHeapFactory<IValue> hf8 = getHf();
                FieldUtil fieldUtil2 = FieldUtil.INSTANCE;
                SootField field$iv2 = ws.getCoderField();
                members.set(hf8, env, new JSootFieldType(field$iv2), newCoder, false);
                AbstractHeapFactory<IValue> hf9 = getHf();
                FieldUtil fieldUtil3 = FieldUtil.INSTANCE;
                SootField field$iv3 = ws.getHashField();
                members.set(hf9, env, new JSootFieldType(field$iv3), newHash, false);
                setValueData(env, newArrayValue, BuiltInModelT.Array, arrayValue);
                setValueData(env, value, BuiltInModelT.Field, members.build2());
                String str2 = ((ConstVal) value).getV().value;
                Intrinsics.checkNotNullExpressionValue(str2, "value");
                IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, str2, getHf().empty().plus(companionV), false, 8, null);
            }
            return getValueData(value, mt);
        }
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void callEntryFlowFunction(@NotNull Context<SootMethod, Unit, IFact<IValue>> context, @NotNull SootMethod callee, @NotNull Unit node, @NotNull Unit succ) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callee, "callee");
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(succ, "succ");
        setCallStack(new CallStackContext(getCallStack(), node, callee, getCallStack().getDeep() + 1));
        IHeapValues receivers = (IHeapValues) getSlots().get(-1);
        if (receivers != null) {
            AbstractHeapFactory<IValue> hf = getHf();
            Type type = callee.getDeclaringClass().getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            IHeapValues receiversValues = hf.canStore(receivers, type);
            getSlots().put(-1, receiversValues);
        }
    }

    /* compiled from: PointsToGraph.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n��\u0018��2\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PointsToGraphBuilder$PathTransfer;", "", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "calleePathToCallerPath", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "ctx", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "calleeCtx", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/util/Map;Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lcn/sast/dataflow/interprocedural/analysis/AIContext;)V", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "getCtx", "()Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "getCalleeCtx", "transform", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "calleePath", "entryHeads", "", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nPointsToGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraphBuilder$PathTransfer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,612:1\n1230#2,4:613\n808#2,11:617\n865#2,2:628\n*S KotlinDebug\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraphBuilder$PathTransfer\n*L\n446#1:613,4\n459#1:617,11\n460#1:628,2\n*E\n"})
    /* loaded from: PointsToGraphBuilder$PathTransfer.class */
    public static final class PathTransfer {

        @NotNull
        private final HeapValuesEnv env;

        @NotNull
        private final Map<EntryPath, IPath> calleePathToCallerPath;

        @NotNull
        private final AIContext ctx;

        @NotNull
        private final AIContext calleeCtx;

        /* JADX WARN: Multi-variable type inference failed */
        public PathTransfer(@NotNull HeapValuesEnv env, @NotNull Map<EntryPath, ? extends IPath> map, @NotNull AIContext ctx, @NotNull AIContext calleeCtx) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(map, "calleePathToCallerPath");
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(calleeCtx, "calleeCtx");
            this.env = env;
            this.calleePathToCallerPath = map;
            this.ctx = ctx;
            this.calleeCtx = calleeCtx;
        }

        @NotNull
        public final HeapValuesEnv getEnv() {
            return this.env;
        }

        @NotNull
        public final AIContext getCtx() {
            return this.ctx;
        }

        @NotNull
        public final AIContext getCalleeCtx() {
            return this.calleeCtx;
        }

        @Nullable
        public final InvokeEdgePath transform(@NotNull IPath calleePath, @NotNull Set<EntryPath> set) {
            Intrinsics.checkNotNullParameter(calleePath, "calleePath");
            Intrinsics.checkNotNullParameter(set, "entryHeads");
            if (set.isEmpty()) {
                return null;
            }
            Set<EntryPath> $this$associateByTo$iv = set;
            Map destination$iv = new HashMap();
            for (Object element$iv : $this$associateByTo$iv) {
                EntryPath it = (EntryPath) element$iv;
                UnknownPath unknownPathV = this.calleePathToCallerPath.get(it);
                if (unknownPathV == null) {
                    unknownPathV = UnknownPath.Companion.v(this.env);
                }
                destination$iv.put(unknownPathV, element$iv);
            }
            HashMap interproceduralPathMap = (HashMap) destination$iv;
            return InvokeEdgePath.Companion.v(this.env, interproceduralPathMap, calleePath, this.ctx.getMethod(), this.calleeCtx.getMethod());
        }

        @Nullable
        public final InvokeEdgePath transform(@NotNull IPath calleePath) {
            Intrinsics.checkNotNullParameter(calleePath, "calleePath");
            Iterable heads = PathGenerator.getHeads$default(PathGeneratorImpl.Companion.getPathGenerator(), calleePath, null, 2, null);
            Iterable $this$filterIsInstance$iv = heads;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filterIsInstance$iv) {
                if (element$iv$iv instanceof EntryPath) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable entryHeads = (List) destination$iv$iv;
            Iterable $this$filterTo$iv = entryHeads;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object element$iv : $this$filterTo$iv) {
                EntryPath it = (EntryPath) element$iv;
                if (this.calleeCtx.getEntries().contains(it)) {
                    destination$iv.add(element$iv);
                }
            }
            Set entryHeads2 = (Set) destination$iv;
            if (entryHeads2.isEmpty()) {
                return null;
            }
            return transform(calleePath, entryHeads2);
        }
    }

    private final PathTransfer activeCalleeReports(SootMethod container, HeapValuesEnv env, AIContext ctx, IFact<IValue> iFact, AIContext calleeCtx) {
        final Map calleePathToCallerPath = new LinkedHashMap();
        IDiff<IValue> iDiff = new IDiff<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder$activeCalleeReports$diff$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // cn.sast.dataflow.interprocedural.analysis.IDiff
            public void diff(CompanionV<IValue> companionV, CompanionV<? extends Object> companionV2) {
                Intrinsics.checkNotNullParameter(companionV, "left");
                Intrinsics.checkNotNullParameter(companionV2, "right");
                IPath calleePath = ((PathCompanionV) companionV2).getPath();
                if (calleePath instanceof EntryPath) {
                    calleePathToCallerPath.put(calleePath, ((PathCompanionV) companionV).getPath());
                }
            }
        };
        IFact<IValue> entryValue = calleeCtx.getEntryValue();
        Intrinsics.checkNotNull(entryValue);
        iFact.diff(iDiff, entryValue);
        return new PathTransfer(env, calleePathToCallerPath, ctx, calleeCtx);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    @Nullable
    public IHeapValues<IValue> updateIntraEdge(@NotNull HeapValuesEnv env, @NotNull soot.Context ctx, @NotNull soot.Context calleeCtx, @NotNull IFact<IValue> iFact, boolean hasReturnValue) {
        IHeapValues returnValue;
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(calleeCtx, "calleeCtx");
        Intrinsics.checkNotNullParameter(iFact, "callEdgeValue");
        if (!(iFact instanceof PointsToGraphAbstract)) {
            throw new IllegalArgumentException("updateIntraEdge error of fact type: " + iFact.getClass() + " \n" + iFact);
        }
        final PathTransfer pathTransfer = activeCalleeReports(((AIContext) ctx).getMethod(), env, (AIContext) ctx, iFact, (AIContext) calleeCtx);
        IFact exitValue = ((AIContext) calleeCtx).getExitValue();
        Intrinsics.checkNotNull(exitValue);
        IFact exitValue2 = exitValue;
        if (!(exitValue2 instanceof PointsToGraphAbstract)) {
            if (exitValue2.isBottom()) {
                return null;
            }
            throw new IllegalArgumentException("updateIntraEdge error of fact type: " + exitValue2.getClass() + " \n" + exitValue2);
        }
        getCalledMethods().addAll(((PointsToGraphAbstract) exitValue2).getCalledMethods());
        if (FactValuesKt.getLeastExpr()) {
            IReNew<IValue> iReNew = new IReNew<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder$updateIntraEdge$transformPathx$1
                @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
                public IValue checkNeedReplace(IValue old) {
                    return (IValue) IReNew.DefaultImpls.checkNeedReplace(this, old);
                }

                @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
                public IReNew<IValue> context(Object value) {
                    return IReNew.DefaultImpls.context(this, value);
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
                public CompanionV<IValue> checkNeedReplace(CompanionV<IValue> companionV) throws NotImplementedError {
                    Intrinsics.checkNotNullParameter(companionV, "c");
                    IPath path = ((PathCompanionV) companionV).getPath();
                    InvokeEdgePath p = pathTransfer.transform(path);
                    if (p == null) {
                        return null;
                    }
                    if (companionV instanceof CompanionValueOfConst) {
                        return new CompanionValueOfConst(((CompanionValueOfConst) companionV).getValue(), p, ((CompanionValueOfConst) companionV).getAttr());
                    }
                    if (companionV instanceof CompanionValueImpl1) {
                        return new CompanionValueImpl1(((CompanionValueImpl1) companionV).getValue(), p);
                    }
                    throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
                }
            };
            for (Map.Entry entry : ((PointsToGraphAbstract) exitValue2).getHeap().entrySet()) {
                IValue v = (IValue) entry.getKey();
                Map map = (PersistentMap) entry.getValue();
                Map map2 = (PersistentMap) getHeap().get(v);
                Map mapBuilder = map.builder();
                for (Map.Entry entry2 : map.entrySet()) {
                    Object mt = entry2.getKey();
                    IData data = (IData) entry2.getValue();
                    IData iDataCloneAndReNewObjects = data.cloneAndReNewObjects(iReNew);
                    if (iDataCloneAndReNewObjects != data) {
                        mapBuilder.put(mt, iDataCloneAndReNewObjects);
                    }
                }
                if (Intrinsics.areEqual(v, getHf().getVg().getGLOBAL_SITE())) {
                    BuiltInModelT mt2 = BuiltInModelT.Field;
                    IData staticValues = map2 != null ? (IData) map2.get(mt2) : null;
                    IData old = (IData) mapBuilder.get(mt2);
                    if (staticValues != null && old != null) {
                        Map map3 = mapBuilder;
                        IData.Builder it = old.builder2();
                        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder<*, cn.sast.dataflow.interprocedural.analysis.IValue>");
                        ((HeapDataBuilder) it).updateFrom(getHf(), staticValues);
                        map3.put(mt2, it.build2());
                    }
                }
                Map mapBuild = mapBuilder.build();
                Map map4 = map2;
                if (map4 == null || map4.isEmpty()) {
                    getHeap().put(v, mapBuild);
                } else {
                    getHeap().put(v, ExtensionsKt.putAll(map2, mapBuild));
                }
            }
            ((AIContext) ctx).activeReport((AIContext) calleeCtx, pathTransfer);
            IHeapValues ret = ((PointsToGraphAbstract) exitValue2).getTargetsUnsafe(getVg().getRETURN_LOCAL());
            if (!hasReturnValue || ret == null) {
                return null;
            }
            return ret.cloneAndReNewObjects(iReNew);
        }
        Set orig = new LinkedHashSet();
        for (Map.Entry entry3 : ((PointsToGraphAbstract) iFact).getSlots().entrySet()) {
            entry3.getKey();
            orig.addAll(((IHeapValues) entry3.getValue()).getValues());
        }
        for (Map.Entry entry4 : ((PointsToGraphAbstract) iFact).getHeap().entrySet()) {
            for (Map.Entry entry5 : ((PersistentMap) entry4.getValue()).entrySet()) {
                entry5.getKey();
                ((IData) entry5.getValue()).reference(orig);
            }
        }
        IReNew reNew = getHf().newReNewInterface(orig);
        if (!((PointsToGraphAbstract) exitValue2).getHeap().isEmpty()) {
            for (Map.Entry entry6 : ((PointsToGraphAbstract) exitValue2).getHeap().entrySet()) {
                IValue v2 = (IValue) entry6.getKey();
                Map map5 = (PersistentMap) entry6.getValue();
                Map mapBuilder2 = map5.builder();
                for (Map.Entry entry7 : map5.entrySet()) {
                    Object mt3 = entry7.getKey();
                    IData data2 = (IData) entry7.getValue();
                    IData iDataCloneAndReNewObjects2 = data2.cloneAndReNewObjects(reNew);
                    if (iDataCloneAndReNewObjects2 != data2) {
                        mapBuilder2.put(mt3, iDataCloneAndReNewObjects2);
                    }
                }
                PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) getHeap().get(v2);
                if (persistentMapPersistentHashMapOf == null) {
                    persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
                }
                PersistentMap selfMap = persistentMapPersistentHashMapOf;
                IValue rpVal = reNew.checkNeedReplace((IReNew) v2);
                if (rpVal == null) {
                    Map heap = getHeap();
                    Map map$iv = mapBuilder2.build();
                    heap.put(v2, ExtensionsKt.putAll(selfMap, map$iv));
                } else if (!Intrinsics.areEqual(v2, rpVal)) {
                    Map heap2 = getHeap();
                    Map map$iv2 = mapBuilder2.build();
                    heap2.put(rpVal, ExtensionsKt.putAll(selfMap, map$iv2));
                    getHeap().remove(v2);
                }
            }
        }
        if (!hasReturnValue || (returnValue = ((PointsToGraphAbstract) exitValue2).getTargetsUnsafe(getVg().getRETURN_LOCAL())) == null) {
            return null;
        }
        return returnValue.cloneAndReNewObjects(reNew);
    }
}
