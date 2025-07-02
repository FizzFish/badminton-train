package cn.sast.framework.report.sarif;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� )2\u00020\u0001:\u0002()B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000bB;\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0007\u0010\u000fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020��2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0011¨\u0006*"}, d2 = {"Lcn/sast/framework/report/sarif/Region;", "", "startLine", "", "startColumn", "endLine", "endColumn", "<init>", "(IIII)V", "region", "Lcom/feysh/corax/config/api/report/Region;", "(Lcom/feysh/corax/config/api/report/Region;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStartLine", "()I", "getStartColumn", "getEndLine", "getEndColumn", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Region.class */
public final class Region {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int startLine;
    private final int startColumn;
    private final int endLine;
    private final int endColumn;

    public final int component1() {
        return this.startLine;
    }

    public final int component2() {
        return this.startColumn;
    }

    public final int component3() {
        return this.endLine;
    }

    public final int component4() {
        return this.endColumn;
    }

    @NotNull
    public final Region copy(int startLine, int startColumn, int endLine, int endColumn) {
        return new Region(startLine, startColumn, endLine, endColumn);
    }

    public static /* synthetic */ Region copy$default(Region region, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = region.startLine;
        }
        if ((i5 & 2) != 0) {
            i2 = region.startColumn;
        }
        if ((i5 & 4) != 0) {
            i3 = region.endLine;
        }
        if ((i5 & 8) != 0) {
            i4 = region.endColumn;
        }
        return region.copy(i, i2, i3, i4);
    }

    @NotNull
    public String toString() {
        return "Region(startLine=" + this.startLine + ", startColumn=" + this.startColumn + ", endLine=" + this.endLine + ", endColumn=" + this.endColumn + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.startLine);
        return (((((result * 31) + Integer.hashCode(this.startColumn)) * 31) + Integer.hashCode(this.endLine)) * 31) + Integer.hashCode(this.endColumn);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Region)) {
            return false;
        }
        Region region = (Region) other;
        return this.startLine == region.startLine && this.startColumn == region.startColumn && this.endLine == region.endLine && this.endColumn == region.endColumn;
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/Region$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/Region;", "corax-framework"})
    /* loaded from: Region$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Region> serializer() {
            return Region$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Region self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.startLine);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || self.startColumn != 0;
        if (z) {
            output.encodeIntElement(serialDesc, 1, self.startColumn);
        }
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 2) || self.endLine != 0;
        if (z2) {
            output.encodeIntElement(serialDesc, 2, self.endLine);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 3) || self.endColumn != 0;
        if (z3) {
            output.encodeIntElement(serialDesc, 3, self.endColumn);
        }
    }

    public /* synthetic */ Region(int seen0, int startLine, int startColumn, int endLine, int endColumn, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (1 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, Region$$serializer.INSTANCE.getDescriptor());
        }
        this.startLine = startLine;
        if ((seen0 & 2) == 0) {
            this.startColumn = 0;
        } else {
            this.startColumn = startColumn;
        }
        if ((seen0 & 4) == 0) {
            this.endLine = 0;
        } else {
            this.endLine = endLine;
        }
        if ((seen0 & 8) == 0) {
            this.endColumn = 0;
        } else {
            this.endColumn = endColumn;
        }
    }

    public Region(int startLine, int startColumn, int endLine, int endColumn) {
        this.startLine = startLine;
        this.startColumn = startColumn;
        this.endLine = endLine;
        this.endColumn = endColumn;
    }

    public /* synthetic */ Region(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public final int getStartLine() {
        return this.startLine;
    }

    public final int getStartColumn() {
        return this.startColumn;
    }

    public final int getEndLine() {
        return this.endLine;
    }

    public final int getEndColumn() {
        return this.endColumn;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Region(@NotNull com.feysh.corax.config.api.report.Region region) {
        this(Math.max(region.startLine, 0), Math.max(region.startColumn, 0), Math.max(region.getEndLine(), 0), Math.max(region.getEndColumn() + 1, 0));
        Intrinsics.checkNotNullParameter(region, "region");
    }
}
