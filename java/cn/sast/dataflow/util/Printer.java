package cn.sast.dataflow.util;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Printer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0004\u0018�� \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcn/sast/dataflow/util/Printer;", "", "<init>", "()V", "Companion", "corax-data-flow"})
/* loaded from: Printer.class */
public final class Printer {

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: Printer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005\"\u0004\b��\u0010\u00062\u0006\u0010\u0007\u001a\u0002H\u0006¢\u0006\u0002\u0010\bJ\"\u0010\t\u001a\u00020\u0005\"\u0004\b��\u0010\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000b\u0018\u00010\n¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/util/Printer$Companion;", "", "<init>", "()V", "node2String", "", "V", "node", "(Ljava/lang/Object;)Ljava/lang/String;", "nodes2String", "", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "corax-data-flow"})
    /* loaded from: Printer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final <V> String node2String(V v) {
            return String.valueOf(v);
        }

        @NotNull
        public final <V> String nodes2String(@Nullable Collection<? extends CompanionV<V>> collection) {
            return collection == null ? "!null!" : collection.isEmpty() ? "empty" : "{ " + CollectionsKt.joinToString$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, Companion::nodes2String$lambda$0, 31, (Object) null) + " }";
        }

        private static final CharSequence nodes2String$lambda$0(CompanionV it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Printer.Companion.node2String(it);
        }
    }
}
