package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.RefType;
import soot.Type;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018��2\u00020\u0001:\u0001\u0011J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0017\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020��H\u0016¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020��H\u0016J\u0010\u0010\u0010\u001a\u00020��2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IValue;", "", "type", "Lsoot/Type;", "getType", "()Lsoot/Type;", "typeIsConcrete", "", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "isNormal", "objectEqual", "b", "(Lcn/sast/dataflow/interprocedural/analysis/IValue;)Ljava/lang/Boolean;", "clone", "copy", "Kind", "corax-data-flow"})
/* loaded from: IValue.class */
public interface IValue {
    @NotNull
    Type getType();

    boolean typeIsConcrete();

    boolean isNullConstant();

    @NotNull
    Kind getKind();

    boolean isNormal();

    @Nullable
    Boolean objectEqual(@NotNull IValue iValue);

    @NotNull
    IValue clone();

    @NotNull
    IValue copy(@NotNull Type type);

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "Normal", "Entry", "Summary", "corax-data-flow"})
    /* loaded from: IValue$Kind.class */
    public enum Kind {
        Normal,
        Entry,
        Summary;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: IFact.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IValue$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean isNormal(@NotNull IValue $this) {
            return $this.getKind() == Kind.Normal;
        }

        @Nullable
        public static Boolean objectEqual(@NotNull IValue $this, @NotNull IValue b) {
            Intrinsics.checkNotNullParameter(b, "b");
            return (($this.getType() instanceof RefType) && $this == b) ? true : null;
        }

        @NotNull
        public static IValue clone(@NotNull IValue $this) {
            return $this;
        }

        @NotNull
        public static IValue copy(@NotNull IValue $this, @NotNull Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return $this;
        }
    }
}
