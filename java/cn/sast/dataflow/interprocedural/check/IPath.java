package cn.sast.dataflow.interprocedural.check;

import cn.sast.common.interner.InternerEquiv;
import cn.sast.common.interner.WeakInternerX;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.benmanes.caffeine.cache.Interner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018�� \b2\u00020\u0001:\u0001\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\t\t\n\u000b\f\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/IPath;", "Lcn/sast/common/interner/InternerEquiv;", "<init>", "()V", "node", "Lsoot/Unit;", "getNode", "()Lsoot/Unit;", "Companion", "Lcn/sast/dataflow/interprocedural/check/AssignLocalPath;", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "Lcn/sast/dataflow/interprocedural/check/GetEdgePath;", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "Lcn/sast/dataflow/interprocedural/check/LiteralPath;", "Lcn/sast/dataflow/interprocedural/check/MergePath;", "Lcn/sast/dataflow/interprocedural/check/ModelBind;", "Lcn/sast/dataflow/interprocedural/check/SetEdgePath;", "Lcn/sast/dataflow/interprocedural/check/UnknownPath;", "corax-data-flow"})
/* loaded from: IPath.class */
public abstract class IPath implements InternerEquiv {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Interner<Object> specialWeakInterner;

    @NotNull
    private static final WeakInternerX weakInterner;

    @NotNull
    public abstract Unit getNode();

    public /* synthetic */ IPath(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private IPath() {
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u0002H\u0007\"\u0004\b��\u0010\u00072\u0006\u0010\b\u001a\u0002H\u0007¢\u0006\u0002\u0010\tR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n��R\u001f\u0010\f\u001a\u0002H\u0007\"\b\b��\u0010\u0007*\u00020\r*\u0002H\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/IPath$Companion;", "", "<init>", "()V", "specialWeakInterner", "Lcom/github/benmanes/caffeine/cache/Interner;", "specialInterner", "T", "v", "(Ljava/lang/Object;)Ljava/lang/Object;", "weakInterner", "Lcn/sast/common/interner/WeakInternerX;", "interner", "Lcn/sast/common/interner/InternerEquiv;", "getInterner", "(Lcn/sast/common/interner/InternerEquiv;)Lcn/sast/common/interner/InternerEquiv;", "corax-data-flow"})
    /* loaded from: IPath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final <T> T specialInterner(T t) {
            return (T) IPath.specialWeakInterner.intern(t);
        }

        @NotNull
        public final <T extends InternerEquiv> T getInterner(@NotNull T t) {
            Intrinsics.checkNotNullParameter(t, "<this>");
            return (T) IPath.weakInterner.intern(t);
        }
    }

    static {
        Interner<Object> internerNewWeakInterner = Interner.newWeakInterner();
        Intrinsics.checkNotNullExpressionValue(internerNewWeakInterner, "newWeakInterner(...)");
        specialWeakInterner = internerNewWeakInterner;
        weakInterner = new WeakInternerX();
    }
}
