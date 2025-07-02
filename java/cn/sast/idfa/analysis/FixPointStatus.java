package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: FixPointStatus.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018�� \u00072\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcn/sast/idfa/analysis/FixPointStatus;", "", "<init>", "(Ljava/lang/String;I)V", "HasChange", "Fixpoint", "NeedWideningOperators", "Companion", "corax-idfa-framework"})
/* loaded from: FixPointStatus.class */
public enum FixPointStatus {
    HasChange,
    Fixpoint,
    NeedWideningOperators;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    @NotNull
    public static final Companion Companion = new Companion(null);

    /* compiled from: FixPointStatus.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\b¨\u0006\b"}, d2 = {"Lcn/sast/idfa/analysis/FixPointStatus$Companion;", "", "<init>", "()V", "of", "Lcn/sast/idfa/analysis/FixPointStatus;", "hasChange", "", "corax-idfa-framework"})
    /* loaded from: FixPointStatus$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final FixPointStatus of(boolean hasChange) {
            return hasChange ? FixPointStatus.HasChange : FixPointStatus.Fixpoint;
        }
    }

    @NotNull
    public static EnumEntries<FixPointStatus> getEntries() {
        return $ENTRIES;
    }
}
