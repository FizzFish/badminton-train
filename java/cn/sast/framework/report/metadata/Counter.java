package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.EncodeDefault;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalysisMetadata.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� &2\u00020\u0001:\u0002%&B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0005\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0016\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020��2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R$\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n��\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n��\u0012\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006'"}, d2 = {"Lcn/sast/framework/report/metadata/Counter;", "", "missed", "", "covered", "<init>", "(II)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMissed$annotations", "()V", "getMissed", "()I", "setMissed", "(I)V", "getCovered$annotations", "getCovered", "setCovered", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Counter.class */
public final class Counter {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private int missed;
    private int covered;

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getMissed$annotations() {
    }

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getCovered$annotations() {
    }

    public final int component1() {
        return this.missed;
    }

    public final int component2() {
        return this.covered;
    }

    @NotNull
    public final Counter copy(int missed, int covered) {
        return new Counter(missed, covered);
    }

    public static /* synthetic */ Counter copy$default(Counter counter, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = counter.missed;
        }
        if ((i3 & 2) != 0) {
            i2 = counter.covered;
        }
        return counter.copy(i, i2);
    }

    @NotNull
    public String toString() {
        return "Counter(missed=" + this.missed + ", covered=" + this.covered + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.missed);
        return (result * 31) + Integer.hashCode(this.covered);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Counter)) {
            return false;
        }
        Counter counter = (Counter) other;
        return this.missed == counter.missed && this.covered == counter.covered;
    }

    /* compiled from: AnalysisMetadata.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/metadata/Counter$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/metadata/Counter;", "corax-framework"})
    /* loaded from: Counter$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Counter> serializer() {
            return Counter$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Counter self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.missed);
        output.encodeIntElement(serialDesc, 1, self.covered);
    }

    public /* synthetic */ Counter(int seen0, int missed, int covered, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 3, Counter$$serializer.INSTANCE.getDescriptor());
        }
        this.missed = missed;
        this.covered = covered;
    }

    public Counter(int missed, int covered) {
        this.missed = missed;
        this.covered = covered;
    }

    public final int getMissed() {
        return this.missed;
    }

    public final void setMissed(int i) {
        this.missed = i;
    }

    public final int getCovered() {
        return this.covered;
    }

    public final void setCovered(int i) {
        this.covered = i;
    }
}
