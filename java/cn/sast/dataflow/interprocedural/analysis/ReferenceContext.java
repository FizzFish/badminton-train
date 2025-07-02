package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018��2\u00020\u0001:\b\u0004\u0005\u0006\u0007\b\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "", "<init>", "()V", "ArrayElement", "ArraySize", "ArrayInitialized", "KVPosition", "KVUnreferenced", "ObjectValues", "PTG", "Slot", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArrayElement;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArrayInitialized;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArraySize;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$KVPosition;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$KVUnreferenced;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ObjectValues;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$PTG;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$Slot;", "corax-data-flow"})
/* loaded from: ReferenceContext.class */
public abstract class ReferenceContext {
    public /* synthetic */ ReferenceContext(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\bÆ\n\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArrayElement;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$ArrayElement.class */
    public static final class ArrayElement extends ReferenceContext {

        @NotNull
        public static final ArrayElement INSTANCE = new ArrayElement();

        @NotNull
        public String toString() {
            return "ArrayElement";
        }

        public int hashCode() {
            return 1172040842;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArrayElement)) {
                return false;
            }
            return true;
        }

        private ArrayElement() {
            super(null);
        }
    }

    private ReferenceContext() {
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\bÆ\n\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArraySize;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$ArraySize.class */
    public static final class ArraySize extends ReferenceContext {

        @NotNull
        public static final ArraySize INSTANCE = new ArraySize();

        @NotNull
        public String toString() {
            return "ArraySize";
        }

        public int hashCode() {
            return -1580946125;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArraySize)) {
                return false;
            }
            return true;
        }

        private ArraySize() {
            super(null);
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\bÆ\n\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ArrayInitialized;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$ArrayInitialized.class */
    public static final class ArrayInitialized extends ReferenceContext {

        @NotNull
        public static final ArrayInitialized INSTANCE = new ArrayInitialized();

        @NotNull
        public String toString() {
            return "ArrayInitialized";
        }

        public int hashCode() {
            return 120126818;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArrayInitialized)) {
                return false;
            }
            return true;
        }

        private ArrayInitialized() {
            super(null);
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$KVPosition;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "key", "", "<init>", "(Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$KVPosition.class */
    public static final class KVPosition extends ReferenceContext {

        @NotNull
        private final Object key;

        @NotNull
        public final Object component1() {
            return this.key;
        }

        @NotNull
        public final KVPosition copy(@NotNull Object key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return new KVPosition(key);
        }

        public static /* synthetic */ KVPosition copy$default(KVPosition kVPosition, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = kVPosition.key;
            }
            return kVPosition.copy(obj);
        }

        @NotNull
        public String toString() {
            return "KVPosition(key=" + this.key + ")";
        }

        public int hashCode() {
            return this.key.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof KVPosition) && Intrinsics.areEqual(this.key, ((KVPosition) other).key);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KVPosition(@NotNull Object key) {
            super(null);
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
        }

        @NotNull
        public final Object getKey() {
            return this.key;
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\bÆ\n\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$KVUnreferenced;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$KVUnreferenced.class */
    public static final class KVUnreferenced extends ReferenceContext {

        @NotNull
        public static final KVUnreferenced INSTANCE = new KVUnreferenced();

        @NotNull
        public String toString() {
            return "KVUnreferenced";
        }

        public int hashCode() {
            return 523384612;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KVUnreferenced)) {
                return false;
            }
            return true;
        }

        private KVUnreferenced() {
            super(null);
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$ObjectValues;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "value", "", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$ObjectValues.class */
    public static final class ObjectValues extends ReferenceContext {

        @NotNull
        private final Object value;

        @NotNull
        public final Object component1() {
            return this.value;
        }

        @NotNull
        public final ObjectValues copy(@NotNull Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new ObjectValues(value);
        }

        public static /* synthetic */ ObjectValues copy$default(ObjectValues objectValues, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = objectValues.value;
            }
            return objectValues.copy(obj);
        }

        @NotNull
        public String toString() {
            return "ObjectValues(value=" + this.value + ")";
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ObjectValues) && Intrinsics.areEqual(this.value, ((ObjectValues) other).value);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ObjectValues(@NotNull Object value) {
            super(null);
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        @NotNull
        public final Object getValue() {
            return this.value;
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0003j\u0002`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\r\u0010\f\u001a\u00060\u0003j\u0002`\u0005HÆ\u0003J!\u0010\r\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0003j\u0002`\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00060\u0003j\u0002`\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$PTG;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "obj", "", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getObj", "()Ljava/lang/Object;", "getMt", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$PTG.class */
    public static final class PTG extends ReferenceContext {

        @NotNull
        private final Object obj;

        @NotNull
        private final Object mt;

        @NotNull
        public final Object component1() {
            return this.obj;
        }

        @NotNull
        public final Object component2() {
            return this.mt;
        }

        @NotNull
        public final PTG copy(@NotNull Object obj, @NotNull Object mt) {
            Intrinsics.checkNotNullParameter(obj, "obj");
            Intrinsics.checkNotNullParameter(mt, "mt");
            return new PTG(obj, mt);
        }

        public static /* synthetic */ PTG copy$default(PTG ptg, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = ptg.obj;
            }
            if ((i & 2) != 0) {
                obj2 = ptg.mt;
            }
            return ptg.copy(obj, obj2);
        }

        @NotNull
        public String toString() {
            return "PTG(obj=" + this.obj + ", mt=" + this.mt + ")";
        }

        public int hashCode() {
            int result = this.obj.hashCode();
            return (result * 31) + this.mt.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PTG)) {
                return false;
            }
            PTG ptg = (PTG) other;
            return Intrinsics.areEqual(this.obj, ptg.obj) && Intrinsics.areEqual(this.mt, ptg.mt);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PTG(@NotNull Object obj, @NotNull Object mt) {
            super(null);
            Intrinsics.checkNotNullParameter(obj, "obj");
            Intrinsics.checkNotNullParameter(mt, "mt");
            this.obj = obj;
            this.mt = mt;
        }

        @NotNull
        public final Object getObj() {
            return this.obj;
        }

        @NotNull
        public final Object getMt() {
            return this.mt;
        }
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext$Slot;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "slot", "", "<init>", "(Ljava/lang/Object;)V", "getSlot", "()Ljava/lang/Object;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: ReferenceContext$Slot.class */
    public static final class Slot extends ReferenceContext {

        @NotNull
        private final Object slot;

        @NotNull
        public final Object component1() {
            return this.slot;
        }

        @NotNull
        public final Slot copy(@NotNull Object slot) {
            Intrinsics.checkNotNullParameter(slot, "slot");
            return new Slot(slot);
        }

        public static /* synthetic */ Slot copy$default(Slot slot, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = slot.slot;
            }
            return slot.copy(obj);
        }

        @NotNull
        public String toString() {
            return "Slot(slot=" + this.slot + ")";
        }

        public int hashCode() {
            return this.slot.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Slot) && Intrinsics.areEqual(this.slot, ((Slot) other).slot);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Slot(@NotNull Object slot) {
            super(null);
            Intrinsics.checkNotNullParameter(slot, "slot");
            this.slot = slot;
        }

        @NotNull
        public final Object getSlot() {
            return this.slot;
        }
    }
}
