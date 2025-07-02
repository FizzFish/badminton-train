package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: OpOpCalculator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\u0018��2\u00020\u00012\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002BM\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\b\u0012*\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010\u000b0\n\"\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJy\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00022e\u0010\u0012\u001aa\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002\u0012\u001d\u0012\u001b\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012#\u0012!\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00180\n¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a0\u0013¢\u0006\u0002\b\u001bH\u0016R'\u0010\t\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010\u000b0\n¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/OpCalculator;", "Lcn/sast/dataflow/interprocedural/check/CalculatorBase;", "Lcn/sast/dataflow/interprocedural/analysis/IOpCalculator;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "cf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "ops", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;[Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getOps", "()[Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "[Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "resolve", "fx", "Lkotlin/Function3;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "Lkotlin/ParameterName;", "name", "res", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "op", "", "Lkotlin/ExtensionFunctionType;", "corax-data-flow"})
/* loaded from: OpCalculator.class */
public final class OpCalculator extends CalculatorBase implements IOpCalculator<IValue> {

    @NotNull
    private final IHeapValues<IValue>[] ops;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OpCalculator(@NotNull HeapValuesEnv env, @NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull IHeapValues<IValue>... iHeapValuesArr) {
        super(env, abstractHeapFactory);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "cf");
        Intrinsics.checkNotNullParameter(iHeapValuesArr, "ops");
        this.ops = iHeapValuesArr;
    }

    @NotNull
    public final IHeapValues<IValue>[] getOps() {
        return this.ops;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [cn.sast.dataflow.interprocedural.analysis.CompanionV[]] */
    /* JADX WARN: Type inference failed for: r0v26, types: [cn.sast.dataflow.interprocedural.analysis.CompanionV[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v51 */
    /* JADX WARN: Type inference failed for: r0v52 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v22 */
    @Override // cn.sast.dataflow.interprocedural.analysis.IOpCalculator
    @NotNull
    public IOpCalculator<IValue> resolve(@NotNull Function3<? super IOpCalculator<IValue>, ? super IHeapValues.Builder<IValue>, ? super CompanionV<IValue>[], Boolean> function3) {
        Intrinsics.checkNotNullParameter(function3, "fx");
        int sz = this.ops.length;
        Integer[] sizeArray = new Integer[sz];
        for (int i = 0; i < sz; i++) {
            int i2 = i;
            IHeapValues<IValue> iHeapValues = this.ops[i2];
            if (iHeapValues == null) {
                return this;
            }
            Integer numValueOf = Integer.valueOf(iHeapValues.getSize());
            if (numValueOf.intValue() != 0) {
                Unit unit = Unit.INSTANCE;
                sizeArray[i2] = numValueOf;
            } else {
                return this;
            }
        }
        ?? r0 = new CompanionV[sz];
        for (int i3 = 0; i3 < sz; i3++) {
            int i4 = i3;
            r0[i4] = new CompanionV[sizeArray[i4].intValue()];
        }
        Iterator[] iter = new Iterator[sz];
        for (int i5 = 0; i5 < sz; i5++) {
            int i6 = i5;
            IHeapValues<IValue> iHeapValues2 = this.ops[i6];
            Intrinsics.checkNotNull(iHeapValues2);
            iter[i6] = iHeapValues2.iterator();
        }
        Integer[] counter = new Integer[sz];
        for (int i7 = 0; i7 < sz; i7++) {
            counter[i7] = 0;
        }
        ?? r02 = new CompanionV[sz];
        for (int i8 = 0; i8 < sz; i8++) {
            int i9 = i8;
            CompanionV v = (CompanionV) iter[i9].next();
            r0[i9][0] = v;
            r02[i9] = v;
        }
        while (getCount() < getCalculateLimit()) {
            boolean ok = ((Boolean) function3.invoke(this, getRes(), (Object) r02)).booleanValue();
            setCount(getCount() + 1);
            if (!ok) {
                getUnHandle().add(r02);
            }
            int i10 = 0;
            while (true) {
                if (counter[i10].intValue() == sizeArray[i10].intValue() - 1) {
                    counter[i10] = 0;
                    ?? r2 = r0[i10][0];
                    Intrinsics.checkNotNull((Object) r2);
                    r02[i10] = r2;
                    i10++;
                    if (i10 == sz) {
                        break;
                    }
                } else {
                    int i11 = i10;
                    counter[i11] = Integer.valueOf(counter[i11].intValue() + 1);
                    int x = counter[i11].intValue();
                    CompanionV value = r0[i10][x];
                    if (value == null) {
                        value = (CompanionV) iter[i10].next();
                        r0[i10][x] = value;
                    }
                    r02[i10] = value;
                }
            }
            if (i10 == sz) {
                break;
            }
        }
        return this;
    }
}
