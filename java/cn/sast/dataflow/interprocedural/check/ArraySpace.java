package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesKt;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapKV;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.Type;
import soot.jimple.Constant;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0016\u0018��  2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001 Bs\b��\u0012\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\u0012\u0010\r\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016J$\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00072\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0015H\u0016J$\u0010\u0016\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0019H\u0016J+\u0010\u001a\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u001b2\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001cH\u0016¨\u0006!"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ArraySpace;", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapKV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "element", "Lkotlinx/collections/immutable/PersistentMap;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "type", "Lsoot/ArrayType;", "allSize", "size", "initializedValue", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Lsoot/ArrayType;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Ljava/lang/Integer;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)V", "builder", "Lcn/sast/dataflow/interprocedural/check/ArraySpaceBuilder;", "getElement", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "cloneAndReNewObjects", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "getArray", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;)[Lcn/sast/dataflow/interprocedural/analysis/IValue;", "getByteArray", "", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPointsToGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/ArraySpace\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,612:1\n1#2:613\n1863#3,2:614\n*S KotlinDebug\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/ArraySpace\n*L\n225#1:614,2\n*E\n"})
/* loaded from: ArraySpace.class */
public class ArraySpace extends ArrayHeapKV<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    public /* bridge */ /* synthetic */ Object[] getArray(IHeapValuesFactory hf) {
        return getArray((IHeapValuesFactory<IValue>) hf);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArraySpace(@NotNull PersistentMap<Integer, ? extends IHeapValues<IValue>> persistentMap, @Nullable IHeapValues<IValue> iHeapValues, @NotNull ArrayType type, @NotNull IHeapValues<IValue> iHeapValues2, @Nullable Integer size, @Nullable CompanionV<IValue> companionV) {
        super(persistentMap, iHeapValues, iHeapValues2, type, size, companionV);
        Intrinsics.checkNotNullParameter(persistentMap, "element");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(iHeapValues2, "allSize");
    }

    /* compiled from: PointsToGraph.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jj\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000f0\r2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000fJi\u0010\u0004\u001a\u00020\u0005\"\b\b��\u0010\u0014*\u00020\u00152\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u000f¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ArraySpace$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/ArraySpace;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "element", "Lkotlinx/collections/immutable/PersistentMap;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "type", "Lsoot/ArrayType;", "allSize", "T", "", "value", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "array", "", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;[Ljava/lang/Number;Lsoot/ArrayType;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)Lcn/sast/dataflow/interprocedural/check/ArraySpace;", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nPointsToGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/ArraySpace$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,612:1\n13474#2,3:613\n*S KotlinDebug\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/ArraySpace$Companion\n*L\n211#1:613,3\n*E\n"})
    /* loaded from: ArraySpace$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ArraySpace v(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull PersistentMap<Integer, ? extends IHeapValues<IValue>> persistentMap, @NotNull IHeapValues<IValue> iHeapValues, @NotNull ArrayType type, @NotNull IHeapValues<IValue> iHeapValues2) {
            Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(persistentMap, "element");
            Intrinsics.checkNotNullParameter(iHeapValues, "unreferenced");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(iHeapValues2, "allSize");
            Integer size = iHeapValues2.getMaxInt(true);
            Pair<Constant, Type> pairZeroValue = abstractHeapFactory.getVg().zeroValue(type);
            Constant v = (Constant) pairZeroValue.component1();
            Type t = (Type) pairZeroValue.component2();
            CompanionV<IValue> init = abstractHeapFactory.push(env, (HeapValuesEnv) abstractHeapFactory.newConstVal2(v, t)).markOfConstant(v, "array init value").pop();
            return new ArraySpace(persistentMap, iHeapValues, type, iHeapValues2, size, init);
        }

        @NotNull
        public final <T extends Number> ArraySpace v(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull CompanionV<IValue> companionV, @NotNull T[] tArr, @NotNull ArrayType type, @NotNull IHeapValues<IValue> iHeapValues) {
            Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(companionV, "value");
            Intrinsics.checkNotNullParameter(tArr, "array");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(iHeapValues, "allSize");
            Map mapBuilder = ExtensionsKt.persistentHashMapOf().builder();
            int index$iv = 0;
            for (T t : tArr) {
                int index = index$iv;
                index$iv++;
                Map map = mapBuilder;
                Integer numValueOf = Integer.valueOf(index);
                Constant constant = (Constant) SootUtilsKt.constOf(t).getFirst();
                Type elementType = type.getElementType();
                Intrinsics.checkNotNullExpressionValue(elementType, "getElementType(...)");
                map.put(numValueOf, abstractHeapFactory.push(env, (HeapValuesEnv) abstractHeapFactory.newConstVal2(constant, elementType)).dataGetElementFromSequence(companionV).popHV());
            }
            return new ArraySpace(mapBuilder.build(), abstractHeapFactory.empty(), type, iHeapValues, Integer.valueOf(mapBuilder.size()), null);
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public ArraySpaceBuilder builder2() {
        PersistentMap.Builder builder = getMap().builder();
        IHeapValues<IValue> unreferenced = getUnreferenced();
        return new ArraySpaceBuilder(this, builder, unreferenced != null ? unreferenced.builder() : null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public IHeapValues<IValue> getElement(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        IHeapValues.Builder b = abstractHeapFactory.emptyBuilder();
        IHeapValues it = getUnreferenced();
        if (it != null) {
            b.add(it);
        }
        Iterable $this$forEach$iv = getMap().values();
        for (Object element$iv : $this$forEach$iv) {
            b.add((IHeapValues) element$iv);
        }
        return b.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<IValue> cloneAndReNewObjects(@NotNull IReNew<IValue> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        ArraySpaceBuilder b = builder2();
        b.cloneAndReNewObjects(iReNew);
        return b.build2();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @Nullable
    public IValue[] getArray(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        if (getSize() == null) {
            return null;
        }
        Integer size = getSize();
        IValue[] iValueArr = new IValue[size.intValue()];
        for (int i = 0; i < size.intValue(); i++) {
            int i2 = i;
            IHeapValues exist = get((IHeapValuesFactory) iHeapValuesFactory, Integer.valueOf(i2));
            if (exist == null || !exist.isSingle()) {
                return null;
            }
            iValueArr[i2] = exist.getSingle().getValue();
        }
        return iValueArr;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @Nullable
    public byte[] getByteArray(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        IValue[] arr = getArray(iHeapValuesFactory);
        if (arr == null) {
            return null;
        }
        int length = arr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i;
            IValue iValue = arr[i2];
            ConstVal constVal = iValue instanceof ConstVal ? (ConstVal) iValue : null;
            if (constVal == null) {
                return null;
            }
            Byte byteValue = FactValuesKt.getByteValue(constVal, true);
            if (byteValue == null) {
                return null;
            }
            bArr[i2] = byteValue.byteValue();
        }
        return bArr;
    }
}
