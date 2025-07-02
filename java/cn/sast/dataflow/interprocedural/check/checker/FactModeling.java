package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FieldUtil;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IOpCalculator;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JFieldNameType;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMapBuilder;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet;
import cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSetBuilder;
import cn.sast.dataflow.interprocedural.check.heapimpl.ObjectValues;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.check.ICallCB;
import com.feysh.corax.config.api.AttributeName;
import com.feysh.corax.config.api.BuiltInField;
import com.feysh.corax.config.api.ClassField;
import com.feysh.corax.config.api.Elements;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IIexGetField;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.IIstSetField;
import com.feysh.corax.config.api.IIstStoreLocal;
import com.feysh.corax.config.api.IModelExpressionVisitor;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.MGlobal;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.MParameter;
import com.feysh.corax.config.api.MReturn;
import com.feysh.corax.config.api.MapKeys;
import com.feysh.corax.config.api.MapValues;
import com.feysh.corax.config.api.SubFields;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.ViaProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.Scene;
import soot.Type;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��j\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001BS\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\"\u0010\b\u001a\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\t\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\n*\b\u0012\u0004\u0012\u00020\u00010\u0018J&\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u001aJ<\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010!\u001a\u00020\u001aJ \u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020$2\u0010\u0010*\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050+J<\u0010,\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010!\u001a\u00020\u001aJ\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.R\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R-\u0010\b\u001a\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\t¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001b¨\u0006/"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/checker/FactModeling;", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "summaryCtxCalleeSite", "Lcn/sast/idfa/check/ICallCB;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "builder", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lcn/sast/idfa/check/ICallCB;Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "getSummaryCtxCalleeSite", "()Lcn/sast/idfa/check/ICallCB;", "getBuilder", "()Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "toHV", "", "isArray", "", "(Ljava/lang/Object;)Z", "store", "", "values", "dest", "Lcom/feysh/corax/config/api/MLocal;", "append", "setField", "baseExpr", "Lcom/feysh/corax/config/api/IExpr;", "bases", "field", "Lcom/feysh/corax/config/api/IClassField;", "setConstValue", "rvalue", "newBase", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "propertyPropagate", "getVisitor", "Lcom/feysh/corax/config/api/IModelStmtVisitor;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nCheckerModeling.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerModeling.kt\ncn/sast/dataflow/interprocedural/check/checker/FactModeling\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,565:1\n774#2:566\n865#2,2:567\n774#2:569\n865#2,2:570\n46#3:572\n47#3:574\n1#4:573\n*S KotlinDebug\n*F\n+ 1 CheckerModeling.kt\ncn/sast/dataflow/interprocedural/check/checker/FactModeling\n*L\n197#1:566\n197#1:567,2\n200#1:569\n200#1:570,2\n242#1:572\n242#1:574\n242#1:573\n*E\n"})
/* loaded from: FactModeling.class */
public final class FactModeling {

    @NotNull
    private final AbstractHeapFactory<IValue> hf;

    @NotNull
    private final HeapValuesEnv env;

    @NotNull
    private final ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> summaryCtxCalleeSite;

    @NotNull
    private final IFact.Builder<IValue> builder;

    public FactModeling(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB, @NotNull IFact.Builder<IValue> builder) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iCallCB, "summaryCtxCalleeSite");
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.hf = abstractHeapFactory;
        this.env = env;
        this.summaryCtxCalleeSite = iCallCB;
        this.builder = builder;
    }

    @NotNull
    public final AbstractHeapFactory<IValue> getHf() {
        return this.hf;
    }

    @NotNull
    public final HeapValuesEnv getEnv() {
        return this.env;
    }

    @NotNull
    public final ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> getSummaryCtxCalleeSite() {
        return this.summaryCtxCalleeSite;
    }

    @NotNull
    public final IFact.Builder<IValue> getBuilder() {
        return this.builder;
    }

    @NotNull
    public final IHeapValues<IValue> toHV(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        IHeapValues.Builder b = this.hf.emptyBuilder();
        for (Object e : list) {
            if (e instanceof CompanionV) {
                if (!(((CompanionV) e).getValue() instanceof IValue)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                b.add((CompanionV) e);
            }
        }
        return b.build();
    }

    public final boolean isArray(@NotNull Object $this$isArray) {
        Intrinsics.checkNotNullParameter($this$isArray, "<this>");
        return ($this$isArray instanceof CompanionV) && (((CompanionV) $this$isArray).getValue() instanceof IValue) && (((IValue) ((CompanionV) $this$isArray).getValue()).getType() instanceof ArrayType);
    }

    public static /* synthetic */ void store$default(FactModeling factModeling, List list, MLocal mLocal, boolean z, int i, Object obj) throws NoWhenBranchMatchedException {
        if ((i & 4) != 0) {
            z = false;
        }
        factModeling.store(list, mLocal, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public final void store(@NotNull List<? extends Object> list, @NotNull MLocal dest, boolean append) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(list, "values");
        Intrinsics.checkNotNullParameter(dest, "dest");
        IFact.Builder<IValue> builder = this.builder;
        Intrinsics.checkNotNull(builder, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract<cn.sast.dataflow.interprocedural.analysis.IValue>");
        PointsToGraphBuilderAbstract out = (PointsToGraphBuilderAbstract) builder;
        IHeapValues value = toHV(list);
        if (Intrinsics.areEqual(dest, MReturn.INSTANCE)) {
            if (append) {
                ICallCB<IHeapValues<IValue>, IFact.Builder<IValue>> iCallCB = this.summaryCtxCalleeSite;
                iCallCB.setReturn(iCallCB.getReturn().plus((IHeapValues<IValue>) value));
                return;
            } else {
                this.summaryCtxCalleeSite.setReturn(value);
                return;
            }
        }
        if (dest instanceof MParameter) {
            Object destValue = this.summaryCtxCalleeSite.argToValue(((MParameter) dest).getIndex());
            out.assignNewExpr(this.env, destValue, value, append);
        } else if (!Intrinsics.areEqual(dest, MGlobal.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public final void setField(@Nullable IExpr baseExpr, @NotNull List<? extends Object> list, @NotNull List<? extends Object> list2, @NotNull IClassField field, boolean append) throws NoWhenBranchMatchedException, NotImplementedError {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "bases");
        Intrinsics.checkNotNullParameter(list2, "values");
        Intrinsics.checkNotNullParameter(field, "field");
        if (!(field instanceof BuiltInField)) {
            if (field instanceof ClassField) {
                IHeapValues base = toHV(list);
                IHeapValues value = toHV(list2);
                FieldUtil fieldUtil = FieldUtil.INSTANCE;
                ClassField field$iv = (ClassField) field;
                String it$iv = field$iv.getFieldType();
                Type ty$iv = it$iv != null ? Scene.v().getTypeUnsafe(it$iv, true) : null;
                String fieldName = field$iv.getFieldName();
                Type type = ty$iv;
                if (type == null) {
                    type = (Type) Scene.v().getObjectType();
                }
                Type type2 = type;
                Intrinsics.checkNotNull(type2);
                JFieldNameType sootField = new JFieldNameType(fieldName, type2);
                IFact.Builder.DefaultImpls.assignNewExpr$default(this.builder, this.env, "@base", base, false, 8, null);
                this.builder.setFieldNew(this.env, "@base", sootField, value);
                this.builder.kill("@base");
                return;
            }
            if (!(field instanceof AttributeName)) {
                if (Intrinsics.areEqual(field, SubFields.INSTANCE)) {
                }
                return;
            }
            Object mt = CheckerModelingKt.getKeyAttribute();
            IHeapValues value2 = toHV(list2);
            IHeapValues baseHeapValues = toHV(list);
            boolean append2 = !baseHeapValues.isSingle();
            for (CompanionV base2 : toHV(list)) {
                IData<IValue> valueData = this.builder.getValueData(base2.getValue(), mt);
                ImmutableElementHashMap attributeMap = valueData instanceof ImmutableElementHashMap ? (ImmutableElementHashMap) valueData : null;
                if (attributeMap == null) {
                    attributeMap = new ImmutableElementHashMap(null, null, 3, null);
                }
                ImmutableElementHashMapBuilder b = attributeMap.builder2();
                b.set(this.hf, this.env, ((AttributeName) field).getName(), value2, append2);
                ImmutableElementHashMap immutableElementHashMapBuild2 = b.build2();
                if (base2.getValue() instanceof ConstVal) {
                    return;
                } else {
                    this.builder.setValueData(this.env, base2.getValue(), mt, immutableElementHashMapBuild2);
                }
            }
            return;
        }
        BuiltInField builtInField = (BuiltInField) field;
        if (Intrinsics.areEqual(builtInField, TaintProperty.INSTANCE) || Intrinsics.areEqual(builtInField, ViaProperty.INSTANCE)) {
            propertyPropagate(baseExpr, list, list2, field, append);
            return;
        }
        if (!Intrinsics.areEqual(builtInField, MapKeys.INSTANCE) && !Intrinsics.areEqual(builtInField, MapValues.INSTANCE) && !Intrinsics.areEqual(builtInField, Elements.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        }
        List baseValues = list;
        BuiltInField builtInField2 = (BuiltInField) field;
        if (builtInField2 instanceof Elements) {
            List $this$filter$iv = baseValues;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                if (isArray(element$iv$iv)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List arrayObjects = (List) destination$iv$iv;
            List $this$filter$iv2 = baseValues;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                if (!isArray(element$iv$iv2)) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            baseValues = (List) destination$iv$iv2;
            if (!arrayObjects.isEmpty()) {
                IFact.Builder.DefaultImpls.assignNewExpr$default(this.builder, this.env, "@arr", toHV(arrayObjects), false, 8, null);
                this.builder.setArrayValueNew(this.env, "@arr", null, toHV(list2));
                this.builder.kill("@arr");
            }
            obj = BuiltInModelT.Element;
        } else if (builtInField2 instanceof MapKeys) {
            obj = BuiltInModelT.MapKeys;
        } else {
            if (!(builtInField2 instanceof MapValues)) {
                throw new NotImplementedError("An operation is not implemented: unreachable");
            }
            obj = BuiltInModelT.MapValues;
        }
        Object mt2 = obj;
        IHeapValues base3 = toHV(baseValues);
        IOpCalculator op = this.hf.resolveOp(this.env, base3);
        boolean append3 = !base3.isSingle();
        op.resolve((v4, v5, v6) -> {
            return setField$lambda$2(r1, r2, r3, r4, v4, v5, v6);
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [cn.sast.dataflow.interprocedural.check.heapimpl.ObjectValuesBuilder] */
    private static final boolean setField$lambda$2(boolean $append, FactModeling this$0, Object $mt, List $values, IOpCalculator $this$solve, IHeapValues.Builder ret, CompanionV[] companionVArr) {
        Intrinsics.checkNotNullParameter($this$solve, "$this$solve");
        Intrinsics.checkNotNullParameter(ret, "ret");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV base = companionVArr[0];
        if ($append) {
            IData valueData = this$0.builder.getValueData(base.getValue(), $mt);
            ObjectValues collection = valueData instanceof ObjectValues ? (ObjectValues) valueData : null;
            if (collection == null) {
                collection = new ObjectValues(this$0.hf.empty());
            }
            ?? Builder2 = collection.builder2();
            Builder2.addAll(this$0.toHV($values));
            this$0.builder.setValueData(this$0.env, base.getValue(), $mt, Builder2.build2());
            return true;
        }
        this$0.builder.setValueData(this$0.env, base.getValue(), $mt, new ObjectValues(this$0.toHV($values)));
        return true;
    }

    public final void setConstValue(@NotNull final IExpr rvalue, @NotNull CompanionV<IValue> companionV) throws NoWhenBranchMatchedException, NotImplementedError {
        Intrinsics.checkNotNullParameter(rvalue, "rvalue");
        Intrinsics.checkNotNullParameter(companionV, "newBase");
        if (rvalue instanceof IIexGetField) {
            if (((IIexGetField) rvalue).getAccessPath().isEmpty()) {
                return;
            }
            final List acp = CollectionsKt.dropLast(((IIexGetField) rvalue).getAccessPath(), 1);
            List bases = SequencesKt.toList(this.hf.resolve(this.env, this.summaryCtxCalleeSite, new IIexGetField() { // from class: cn.sast.dataflow.interprocedural.check.checker.FactModeling$setConstValue$baseExpr$1
                public List<IClassField> getAccessPath() {
                    return acp;
                }

                public IExpr getBase() {
                    return rvalue.getBase();
                }

                public <TResult> TResult accept(IModelExpressionVisitor<TResult> iModelExpressionVisitor) {
                    Intrinsics.checkNotNullParameter(iModelExpressionVisitor, "visitor");
                    return (TResult) iModelExpressionVisitor.visit(this);
                }
            }));
            setField(null, bases, CollectionsKt.listOf(companionV), (IClassField) CollectionsKt.last(((IIexGetField) rvalue).getAccessPath()), true);
            return;
        }
        if (rvalue instanceof IIexLoad) {
            store(CollectionsKt.listOf(companionV), ((IIexLoad) rvalue).getOp(), true);
        }
    }

    public final void propertyPropagate(@Nullable IExpr baseExpr, @NotNull List<? extends Object> list, @NotNull List<? extends Object> list2, @NotNull IClassField field, boolean append) {
        Intrinsics.checkNotNullParameter(list, "bases");
        Intrinsics.checkNotNullParameter(list2, "values");
        Intrinsics.checkNotNullParameter(field, "field");
        IHeapValues base = toHV(list);
        IOpCalculator op = this.hf.resolveOp(this.env, base);
        boolean append1 = !base.isSingle() || append;
        op.resolve((v4, v5, v6) -> {
            return propertyPropagate$lambda$4(r1, r2, r3, r4, v4, v5, v6);
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean propertyPropagate$lambda$4(boolean $append1, FactModeling this$0, IClassField $field, List $values, IOpCalculator $this$solve, IHeapValues.Builder builder, CompanionV[] companionVArr) {
        ImmutableElementSet immutableElementSet;
        ImmutableElementSet immutableElementSetBuild2;
        Intrinsics.checkNotNullParameter($this$solve, "$this$solve");
        Intrinsics.checkNotNullParameter(builder, "<unused var>");
        Intrinsics.checkNotNullParameter(companionVArr, "<destruct>");
        CompanionV base = companionVArr[0];
        if ($append1) {
            IData valueData = this$0.builder.getValueData(base.getValue(), $field);
            immutableElementSet = valueData instanceof ImmutableElementSet ? (ImmutableElementSet) valueData : null;
        } else {
            immutableElementSet = null;
        }
        ImmutableElementSet setDate = immutableElementSet;
        if ((setDate == null || setDate.isEmpty()) && $values.size() == 1) {
            Object objFirst = CollectionsKt.first($values);
            Intrinsics.checkNotNull(objFirst, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementSet<kotlin.Any>");
            immutableElementSetBuild2 = (ImmutableElementSet) objFirst;
        } else {
            immutableElementSetBuild2 = null;
        }
        if (immutableElementSetBuild2 == null) {
            ImmutableElementSetBuilder setBuilder = new ImmutableElementSet(null, null, 3, null).builder2();
            for (Object typeValues : $values) {
                ImmutableElementSet immutableElementSet2 = typeValues instanceof ImmutableElementSet ? (ImmutableElementSet) typeValues : null;
                if (immutableElementSet2 != null) {
                    ImmutableElementSet set = immutableElementSet2;
                    for (Object e : set.getElement()) {
                        IHeapValues v = set.get(this$0.hf, e);
                        setBuilder.set(this$0.hf, this$0.env, e, v, $append1);
                    }
                }
            }
            immutableElementSetBuild2 = setBuilder.build2();
        }
        ImmutableElementSet immutableElementSet3 = immutableElementSetBuild2;
        if (!(((IValue) base.getValue()) instanceof ConstVal)) {
            this$0.builder.setValueData(this$0.env, base.getValue(), CheckerModelingKt.getKeyTaintProperty(), immutableElementSet3);
            return true;
        }
        return false;
    }

    @NotNull
    public final IModelStmtVisitor<Object> getVisitor() {
        return new IModelStmtVisitor<Object>() { // from class: cn.sast.dataflow.interprocedural.check.checker.FactModeling$getVisitor$stmtVisitor$1
            /* renamed from: visit, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m231visit(IIstSetField stmt) throws NoWhenBranchMatchedException, NotImplementedError {
                visit(stmt);
                return Unit.INSTANCE;
            }

            /* renamed from: default, reason: not valid java name */
            public Object m230default(IStmt stmt) {
                Intrinsics.checkNotNullParameter(stmt, "stmt");
                throw new IllegalStateException(stmt.toString());
            }

            public Object visit(IIstStoreLocal stmt) throws NoWhenBranchMatchedException {
                Intrinsics.checkNotNullParameter(stmt, "stmt");
                List values = SequencesKt.toList(this.this$0.getHf().resolve(this.this$0.getEnv(), this.this$0.getSummaryCtxCalleeSite(), stmt.getValue()));
                FactModeling.store$default(this.this$0, values, stmt.getLocal(), false, 4, null);
                return Unit.INSTANCE;
            }

            public void visit(IIstSetField stmt) throws NoWhenBranchMatchedException, NotImplementedError {
                Intrinsics.checkNotNullParameter(stmt, "stmt");
                IClassField field = stmt.getField();
                List bases = SequencesKt.toList(this.this$0.getHf().resolve(this.this$0.getEnv(), this.this$0.getSummaryCtxCalleeSite(), stmt.getBase()));
                List values = SequencesKt.toList(this.this$0.getHf().resolve(this.this$0.getEnv(), this.this$0.getSummaryCtxCalleeSite(), stmt.getValue()));
                this.this$0.setField(stmt.getBase(), bases, values, field, false);
            }
        };
    }
}
