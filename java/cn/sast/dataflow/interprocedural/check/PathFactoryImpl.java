package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: PathFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005JD\u0010\u0006\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0007H\u0016Jh\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00102\u0006\u0010\b\u001a\u00020\t2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00102:\u0010\u0011\u001a6\u0012\u0017\u0012\u00150\u0002j\u0002`\u0003¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00160\u0012H\u0016¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathFactoryImpl;", "Lcn/sast/dataflow/interprocedural/check/PathFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "<init>", "()V", "setModelData", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "obj", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "data", "updatePath", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "newPath", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "v", "Lcn/sast/dataflow/interprocedural/check/IPath;", "old", "corax-data-flow"})
/* loaded from: PathFactoryImpl.class */
public final class PathFactoryImpl extends PathFactory<IValue> {
    @Override // cn.sast.dataflow.interprocedural.check.PathFactory
    @NotNull
    public IData<IValue> setModelData(@NotNull final HeapValuesEnv env, @NotNull final IValue obj, @NotNull final Object mt, @NotNull final IData<IValue> iData) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(mt, "mt");
        Intrinsics.checkNotNullParameter(iData, "data");
        return iData.cloneAndReNewObjects(new IReNew<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.PathFactoryImpl$setModelData$pathReNew$1
            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IValue checkNeedReplace(IValue old) {
                return (IValue) IReNew.DefaultImpls.checkNeedReplace(this, old);
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IReNew<IValue> context(Object value) {
                return IReNew.DefaultImpls.context(this, value);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public CompanionV<IValue> checkNeedReplace(CompanionV<IValue> companionV) {
                Intrinsics.checkNotNullParameter(companionV, "c");
                if (iData instanceof ImmutableElementHashMap) {
                    ModelBind p = ModelBind.Companion.v(env, obj, mt, iData, ((PathCompanionV) companionV).getPath());
                    if (companionV instanceof CompanionValueOfConst) {
                        return new CompanionValueOfConst(((CompanionValueOfConst) companionV).getValue(), p, ((CompanionValueOfConst) companionV).getAttr());
                    }
                    if (companionV instanceof CompanionValueImpl1) {
                        return new CompanionValueImpl1(((CompanionValueImpl1) companionV).getValue(), p);
                    }
                    return null;
                }
                return null;
            }
        });
    }

    @Override // cn.sast.dataflow.interprocedural.check.PathFactory
    @NotNull
    public IHeapValues<IValue> updatePath(@NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues, @NotNull final Function2<? super IValue, ? super IPath, ? extends IPath> function2) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "data");
        Intrinsics.checkNotNullParameter(function2, "newPath");
        return iHeapValues.cloneAndReNewObjects(new IReNew<IValue>() { // from class: cn.sast.dataflow.interprocedural.check.PathFactoryImpl$updatePath$pathReNew$1
            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IValue checkNeedReplace(IValue old) {
                return (IValue) IReNew.DefaultImpls.checkNeedReplace(this, old);
            }

            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public IReNew<IValue> context(Object value) {
                return IReNew.DefaultImpls.context(this, value);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
            public CompanionV<IValue> checkNeedReplace(CompanionV<IValue> companionV) {
                Intrinsics.checkNotNullParameter(companionV, "c");
                IPath p = (IPath) function2.invoke(companionV.getValue(), ((PathCompanionV) companionV).getPath());
                if (companionV instanceof CompanionValueOfConst) {
                    return new CompanionValueOfConst(((CompanionValueOfConst) companionV).getValue(), p, ((CompanionValueOfConst) companionV).getAttr());
                }
                if (companionV instanceof CompanionValueImpl1) {
                    return new CompanionValueImpl1(((CompanionValueImpl1) companionV).getValue(), p);
                }
                return null;
            }
        });
    }
}
