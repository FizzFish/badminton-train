package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0013j\u0002`\u00140\u0012¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/KillEntry;", "", "method", "Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "<init>", "(Lsoot/SootMethod;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;)V", "getMethod", "()Lsoot/SootMethod;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "entries", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "getEntries", "()Ljava/util/Set;", "factory", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "getFactory", "()Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "EntryReplace", "corax-data-flow"})
/* loaded from: KillEntry.class */
public final class KillEntry {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final HeapValuesEnv env;

    @NotNull
    private final Set<EntryPath> entries;

    @NotNull
    private final IReNew<IValue> factory;

    public KillEntry(@NotNull SootMethod method, @NotNull HeapValuesEnv env) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(env, "env");
        this.method = method;
        this.env = env;
        this.entries = new LinkedHashSet();
        this.factory = new EntryReplace(this, null, 1, null);
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final HeapValuesEnv getEnv() {
        return this.env;
    }

    @NotNull
    public final Set<EntryPath> getEntries() {
        return this.entries;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n��\b\u0080\u0004\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ$\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\f2\u0010\u0010\r\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\fH\u0016J\u001a\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/KillEntry$EntryReplace;", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "special", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "<init>", "(Lcn/sast/dataflow/interprocedural/check/KillEntry;Lkotlinx/collections/immutable/PersistentList;)V", "getSpecial", "()Lkotlinx/collections/immutable/PersistentList;", "checkNeedReplace", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "c", "context", "value", "", "corax-data-flow"})
    /* loaded from: KillEntry$EntryReplace.class */
    public final class EntryReplace implements IReNew<IValue> {

        @NotNull
        private final PersistentList<ReferenceContext> special;
        final /* synthetic */ KillEntry this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public EntryReplace(@NotNull KillEntry this$0, PersistentList<? extends ReferenceContext> persistentList) {
            Intrinsics.checkNotNullParameter(persistentList, "special");
            this.this$0 = this$0;
            this.special = persistentList;
        }

        public /* synthetic */ EntryReplace(KillEntry killEntry, PersistentList persistentList, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(killEntry, (i & 1) != 0 ? ExtensionsKt.persistentListOf() : persistentList);
        }

        @NotNull
        public final PersistentList<ReferenceContext> getSpecial() {
            return this.special;
        }

        @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
        @Nullable
        public IValue checkNeedReplace(@NotNull IValue old) {
            return (IValue) IReNew.DefaultImpls.checkNeedReplace(this, old);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
        @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
        @NotNull
        public CompanionV<IValue> checkNeedReplace(@NotNull CompanionV<IValue> companionV) throws NotImplementedError {
            Intrinsics.checkNotNullParameter(companionV, "c");
            EntryPath p = EntryPath.Companion.v(this.special, this.this$0.getMethod(), this.this$0.getEnv());
            this.this$0.getEntries().add(p);
            if (companionV instanceof CompanionValueOfConst) {
                return new CompanionValueOfConst(((CompanionValueOfConst) companionV).getValue(), p, ((CompanionValueOfConst) companionV).getAttr());
            }
            if (companionV instanceof CompanionValueImpl1) {
                return new CompanionValueImpl1(((CompanionValueImpl1) companionV).getValue(), p);
            }
            throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
        }

        @Override // cn.sast.dataflow.interprocedural.analysis.IReNew
        @NotNull
        public IReNew<IValue> context(@NotNull Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new EntryReplace(this.this$0, this.special.add((ReferenceContext) value));
        }
    }

    @NotNull
    public final IReNew<IValue> getFactory() {
        return this.factory;
    }
}
