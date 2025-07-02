package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IDiff;
import cn.sast.dataflow.interprocedural.analysis.IDiffAble;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JOperatorV;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.G;
import soot.Type;
import soot.Unit;
import soot.jimple.Constant;
import soot.jimple.IntConstant;

/* compiled from: HeapFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� :2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001:\u0001:B)\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ5\u0010\u001b\u001a\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010\u00162\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\"H\u0016J\u001a\u0010#\u001a\u00020$2\u0010\u0010%\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040&H\u0016J\b\u0010'\u001a\u00020\u0002H\u0016J,\u0010(\u001a\u00020$2\u0010\u0010)\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040*2\u0010\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,H\u0016J\b\u0010.\u001a\u00020\u0002H\u0016J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010-H\u0096\u0002J$\u00102\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u0004032\u0010\u00104\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u000405H\u0016J\u0012\u00106\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0016H\u0016J$\u00107\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00162\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\bH\u0016J)\u00108\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00182\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u001cH\u0016¢\u0006\u0002\u00109J\u001a\u0010\u000f\u001a\u00020\n2\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u001cH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0016X\u0082\u0004¢\u0006\u0002\n��R \u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00190\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006;"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/JStrArrValue;", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/IArrayHeapKV;", "", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/check/V;", "node", "Lsoot/Unit;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "byteArray", "", "<init>", "(Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;[B)V", "getNode", "()Lsoot/Unit;", "getByteArray", "()[B", "type", "Lsoot/ArrayType;", "getType", "()Lsoot/ArrayType;", "arrayLength", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "byteArrayConstVal", "", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "[Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "get", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "key", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Ljava/lang/Integer;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "toString", "", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "reference", "", "res", "", "computeHash", "diff", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "", "hashCode", "equals", "", "other", "cloneAndReNewObjects", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "getArrayLength", "getElement", "getArray", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;)[Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nHeapFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/JStrArrValue\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1430:1\n13409#2,2:1431\n13409#2,2:1433\n*S KotlinDebug\n*F\n+ 1 HeapFactory.kt\ncn/sast/dataflow/interprocedural/check/JStrArrValue\n*L\n92#1:1431,2\n164#1:1433,2\n*E\n"})
/* loaded from: JStrArrValue.class */
public final class JStrArrValue implements IArrayHeapKV<Integer, IValue> {

    @NotNull
    private final Unit node;

    @NotNull
    private final byte[] byteArray;

    @NotNull
    private final ArrayType type;

    @NotNull
    private final IHeapValues<IValue> arrayLength;

    @NotNull
    private final CompanionV<IValue>[] byteArrayConstVal;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(JStrArrValue::logger$lambda$4);

    public JStrArrValue(@NotNull Unit node, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull byte[] byteArray) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.node = node;
        this.byteArray = byteArray;
        this.type = abstractHeapFactory.getVg().getBYTE_ARRAY_TYPE();
        Constant constantV = IntConstant.v(this.byteArray.length);
        HeapValuesEnv heapValuesEnvEnv = abstractHeapFactory.env(this.node);
        Intrinsics.checkNotNull(constantV);
        this.arrayLength = JOperatorV.DefaultImpls.markOfConstant$default(abstractHeapFactory.push(heapValuesEnvEnv, (HeapValuesEnv) abstractHeapFactory.newConstVal2(constantV, getType())), constantV, null, 2, null).popHV();
        int length = this.byteArray.length;
        CompanionV<IValue>[] companionVArr = new CompanionV[length];
        for (int i = 0; i < length; i++) {
            int i2 = i;
            Constant constantV2 = IntConstant.v(this.byteArray[i2]);
            Intrinsics.checkNotNull(constantV2);
            Type typeSoot_ByteType = G.v().soot_ByteType();
            Intrinsics.checkNotNullExpressionValue(typeSoot_ByteType, "soot_ByteType(...)");
            IValue cv = abstractHeapFactory.newConstVal2(constantV2, typeSoot_ByteType);
            companionVArr[i2] = JOperatorV.DefaultImpls.markOfConstant$default(abstractHeapFactory.push(abstractHeapFactory.env(this.node), (HeapValuesEnv) cv), constantV2, null, 2, null).pop();
        }
        this.byteArrayConstVal = companionVArr;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapKVData
    public /* bridge */ /* synthetic */ IHeapValues get(IHeapValuesFactory hf, Object key) {
        return get((IHeapValuesFactory<IValue>) hf, (Integer) key);
    }

    @NotNull
    public final Unit getNode() {
        return this.node;
    }

    @NotNull
    public final byte[] getByteArray() {
        return this.byteArray;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public ArrayType getType() {
        return this.type;
    }

    @Nullable
    public IHeapValues<IValue> get(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory, @Nullable Integer key) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        if (key != null) {
            if (key.intValue() < 0 || key.intValue() >= this.byteArray.length) {
                return null;
            }
            return iHeapValuesFactory.single(this.byteArrayConstVal[key.intValue()]);
        }
        IHeapValues.Builder b = iHeapValuesFactory.emptyBuilder();
        for (CompanionV<IValue> companionV : this.byteArrayConstVal) {
            b.add(companionV);
        }
        return b.build();
    }

    @NotNull
    public String toString() {
        return "ImByteArray_" + new String(this.byteArray, Charsets.UTF_8);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public IHeapKVData.Builder<Integer, IValue> builder2() {
        return new IHeapKVData.Builder<Integer, IValue>() { // from class: cn.sast.dataflow.interprocedural.check.JStrArrValue.builder.1
            @Override // cn.sast.dataflow.interprocedural.analysis.IHeapKVData.Builder
            public void set(IHeapValuesFactory<IValue> iHeapValuesFactory, HeapValuesEnv env, Integer key, IHeapValues<IValue> iHeapValues, boolean append) {
                Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
                Intrinsics.checkNotNullParameter(env, "env");
                JStrArrValue.Companion.getLogger().error(() -> {
                    return set$lambda$0(r1, r2);
                });
            }

            private static final Object set$lambda$0(HeapValuesEnv $env, AnonymousClass1 this$0) {
                return $env + " " + this$0.getClass().getSimpleName() + " is immutable!!!";
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
            public void union(AbstractHeapFactory<IValue> abstractHeapFactory, IData<IValue> iData) {
                Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
                Intrinsics.checkNotNullParameter(iData, "that");
                JStrArrValue.Companion.getLogger().error(() -> {
                    return union$lambda$1(r1);
                });
            }

            private static final Object union$lambda$1(AnonymousClass1 this$0) {
                return this$0.getClass().getSimpleName() + " is immutable!!! has no union";
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
            public void cloneAndReNewObjects(IReNew<IValue> iReNew) {
                Intrinsics.checkNotNullParameter(iReNew, "re");
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
            /* renamed from: build */
            public IData<IValue> build2() {
                return JStrArrValue.this;
            }
        };
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public void reference(@NotNull Collection<IValue> collection) {
        Intrinsics.checkNotNullParameter(collection, "res");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        return 1138 + Arrays.hashCode(this.byteArray);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<IValue> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
    }

    public int hashCode() {
        return computeHash();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if ((other instanceof JStrArrValue) && hashCode() == ((JStrArrValue) other).hashCode()) {
            return Arrays.equals(this.byteArray, ((JStrArrValue) other).byteArray);
        }
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<IValue> cloneAndReNewObjects(@NotNull IReNew<IValue> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public IHeapValues<IValue> getArrayLength() {
        return this.arrayLength;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public IHeapValues<IValue> getElement(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        IHeapValues.Builder b = abstractHeapFactory.emptyBuilder();
        for (CompanionV<IValue> companionV : this.byteArrayConstVal) {
            b.add(companionV);
        }
        return b.build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public IValue[] getArray(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        int length = this.byteArrayConstVal.length;
        IValue[] iValueArr = new IValue[length];
        for (int i = 0; i < length; i++) {
            int i2 = i;
            iValueArr[i2] = this.byteArrayConstVal[i2].getValue();
        }
        return iValueArr;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV
    @NotNull
    public byte[] getByteArray(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        return this.byteArray;
    }

    /* compiled from: HeapFactory.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/JStrArrValue$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: JStrArrValue$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return JStrArrValue.logger;
        }
    }

    private static final kotlin.Unit logger$lambda$4() {
        return kotlin.Unit.INSTANCE;
    }
}
