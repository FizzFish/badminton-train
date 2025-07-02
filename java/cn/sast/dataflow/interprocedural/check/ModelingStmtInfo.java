package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.MLocal;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.Constant;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\tH&J&\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140\u0013H&J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018J \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "<init>", "(Lcom/feysh/corax/config/api/IStmt;)V", "getStmt", "()Lcom/feysh/corax/config/api/IStmt;", "firstParamIndex", "", "getFirstParamIndex", "()Ljava/lang/Integer;", "setFirstParamIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getParameterNameByIndex", "index", "Lcom/feysh/corax/config/api/MLocal;", "filter", "Lkotlin/Function1;", "", "getParameterNamesInUnitDefUse", "", "unit", "Lsoot/Unit;", "getParameterNames", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathCompanion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/ModelingStmtInfo\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,994:1\n1628#2,3:995\n1053#2:998\n*S KotlinDebug\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/ModelingStmtInfo\n*L\n362#1:995,3\n447#1:998\n*E\n"})
/* loaded from: ModelingStmtInfo.class */
public abstract class ModelingStmtInfo {

    @NotNull
    private final IStmt stmt;

    @Nullable
    private Integer firstParamIndex;

    @Nullable
    public abstract Object getParameterNameByIndex(int i);

    @Nullable
    public abstract Object getParameterNameByIndex(@NotNull MLocal mLocal, @NotNull Function1<Object, Boolean> function1);

    public ModelingStmtInfo(@NotNull IStmt stmt) {
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        this.stmt = stmt;
    }

    @NotNull
    public final IStmt getStmt() {
        return this.stmt;
    }

    @Nullable
    public final Integer getFirstParamIndex() {
        return this.firstParamIndex;
    }

    public final void setFirstParamIndex(@Nullable Integer num) {
        this.firstParamIndex = num;
    }

    @NotNull
    public final List<Object> getParameterNamesInUnitDefUse(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Iterable useAndDefBoxes = unit.getUseAndDefBoxes();
        Intrinsics.checkNotNullExpressionValue(useAndDefBoxes, "getUseAndDefBoxes(...)");
        Iterable $this$mapTo$iv = useAndDefBoxes;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            ValueBox it = (ValueBox) item$iv;
            destination$iv.add(it.getValue());
        }
        Set useDef = (Set) destination$iv;
        return getParameterNames((v1) -> {
            return getParameterNamesInUnitDefUse$lambda$1(r1, v1);
        });
    }

    private static final boolean getParameterNamesInUnitDefUse$lambda$1(Set $useDef, Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof Local) {
            return $useDef.contains(it);
        }
        if (it instanceof Constant) {
            return false;
        }
        return true;
    }

    @NotNull
    public final List<Object> getParameterNames(@NotNull Function1<Object, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "filter");
        ModelingStmtInfo$getParameterNames$visitor$1 visitor = new ModelingStmtInfo$getParameterNames$visitor$1(this, function1);
        this.stmt.accept(visitor);
        Iterable $this$sortedBy$iv = visitor.getResult();
        return CollectionsKt.toList(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.dataflow.interprocedural.check.ModelingStmtInfo$getParameterNames$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(t.toString(), t2.toString());
            }
        }));
    }
}
