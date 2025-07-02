package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002J \u0010\u0003\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00070\u0004H&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\u00072\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "getPhantomFieldMap", "", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/PhantomField;", "getPhantomField", "field", "ap", "corax-data-flow"})
/* loaded from: IFieldManager.class */
public interface IFieldManager<V extends IValue> extends IValue {
    @NotNull
    Map<PersistentList<JFieldType>, PhantomField<V>> getPhantomFieldMap();

    @NotNull
    PhantomField<V> getPhantomField(@NotNull JFieldType jFieldType);

    @NotNull
    PhantomField<V> getPhantomField(@NotNull PersistentList<? extends JFieldType> persistentList);

    /* compiled from: FactValues.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IFieldManager$DefaultImpls.class */
    public static final class DefaultImpls {
        public static <V extends IValue> boolean isNormal(@NotNull IFieldManager<V> iFieldManager) {
            return IValue.DefaultImpls.isNormal(iFieldManager);
        }

        @Nullable
        public static <V extends IValue> Boolean objectEqual(@NotNull IFieldManager<V> iFieldManager, @NotNull IValue b) {
            Intrinsics.checkNotNullParameter(b, "b");
            return IValue.DefaultImpls.objectEqual(iFieldManager, b);
        }

        @NotNull
        public static <V extends IValue> IValue clone(@NotNull IFieldManager<V> iFieldManager) {
            return IValue.DefaultImpls.clone(iFieldManager);
        }

        @NotNull
        public static <V extends IValue> IValue copy(@NotNull IFieldManager<V> iFieldManager, @NotNull Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return IValue.DefaultImpls.copy(iFieldManager, type);
        }

        @NotNull
        public static <V extends IValue> PhantomField<V> getPhantomField(@NotNull IFieldManager<V> iFieldManager, @NotNull JFieldType field) {
            Intrinsics.checkNotNullParameter(field, "field");
            return iFieldManager.getPhantomField(ExtensionsKt.persistentListOf(new JFieldType[]{field}));
        }

        @NotNull
        public static <V extends IValue> PhantomField<V> getPhantomField(@NotNull IFieldManager<V> iFieldManager, @NotNull PersistentList<? extends JFieldType> persistentList) {
            Intrinsics.checkNotNullParameter(persistentList, "ap");
            synchronized (iFieldManager) {
                Map map = iFieldManager.getPhantomFieldMap();
                PhantomField<V> value = map.get(persistentList);
                if (value != null) {
                    return value;
                }
                PhantomField phantomField = new PhantomField(iFieldManager, persistentList);
                map.put(persistentList, phantomField);
                return phantomField;
            }
        }
    }
}
