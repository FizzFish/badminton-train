package cn.sast.framework.report.sarif;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.EncodeDefault;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifLog.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� 22\u00020\u0001:\u000212B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0004\b\r\u0010\u000eBW\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0012J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0003JG\u0010#\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0005HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020��2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001c¨\u00063"}, d2 = {"Lcn/sast/framework/report/sarif/Result;", "", "ruleId", "", "ruleIndex", "", "message", "Lcn/sast/framework/report/sarif/IndexedMessage;", "locations", "", "Lcn/sast/framework/report/sarif/Location;", "codeFlows", "Lcn/sast/framework/report/sarif/CodeFlow;", "<init>", "(Ljava/lang/String;ILcn/sast/framework/report/sarif/IndexedMessage;Ljava/util/List;Ljava/util/List;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILcn/sast/framework/report/sarif/IndexedMessage;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRuleId", "()Ljava/lang/String;", "getRuleIndex", "()I", "getMessage$annotations", "()V", "getMessage", "()Lcn/sast/framework/report/sarif/IndexedMessage;", "getLocations", "()Ljava/util/List;", "getCodeFlows", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
/* loaded from: Result.class */
public final class Result {

    @NotNull
    private final String ruleId;
    private final int ruleIndex;

    @NotNull
    private final IndexedMessage message;

    @NotNull
    private final List<Location> locations;

    @NotNull
    private final List<CodeFlow> codeFlows;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new ArrayListSerializer(Location$$serializer.INSTANCE), new ArrayListSerializer(CodeFlow$$serializer.INSTANCE)};

    @EncodeDefault(mode = EncodeDefault.Mode.ALWAYS)
    public static /* synthetic */ void getMessage$annotations() {
    }

    @NotNull
    public final String component1() {
        return this.ruleId;
    }

    public final int component2() {
        return this.ruleIndex;
    }

    @NotNull
    public final IndexedMessage component3() {
        return this.message;
    }

    @NotNull
    public final List<Location> component4() {
        return this.locations;
    }

    @NotNull
    public final List<CodeFlow> component5() {
        return this.codeFlows;
    }

    @NotNull
    public final Result copy(@NotNull String ruleId, int ruleIndex, @NotNull IndexedMessage message, @NotNull List<Location> list, @NotNull List<CodeFlow> list2) {
        Intrinsics.checkNotNullParameter(ruleId, "ruleId");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(list, "locations");
        Intrinsics.checkNotNullParameter(list2, "codeFlows");
        return new Result(ruleId, ruleIndex, message, list, list2);
    }

    public static /* synthetic */ Result copy$default(Result result, String str, int i, IndexedMessage indexedMessage, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = result.ruleId;
        }
        if ((i2 & 2) != 0) {
            i = result.ruleIndex;
        }
        if ((i2 & 4) != 0) {
            indexedMessage = result.message;
        }
        if ((i2 & 8) != 0) {
            list = result.locations;
        }
        if ((i2 & 16) != 0) {
            list2 = result.codeFlows;
        }
        return result.copy(str, i, indexedMessage, list, list2);
    }

    @NotNull
    public String toString() {
        return "Result(ruleId=" + this.ruleId + ", ruleIndex=" + this.ruleIndex + ", message=" + this.message + ", locations=" + this.locations + ", codeFlows=" + this.codeFlows + ")";
    }

    public int hashCode() {
        int result = this.ruleId.hashCode();
        return (((((((result * 31) + Integer.hashCode(this.ruleIndex)) * 31) + this.message.hashCode()) * 31) + this.locations.hashCode()) * 31) + this.codeFlows.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Result)) {
            return false;
        }
        Result result = (Result) other;
        return Intrinsics.areEqual(this.ruleId, result.ruleId) && this.ruleIndex == result.ruleIndex && Intrinsics.areEqual(this.message, result.message) && Intrinsics.areEqual(this.locations, result.locations) && Intrinsics.areEqual(this.codeFlows, result.codeFlows);
    }

    /* compiled from: SarifLog.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/sarif/Result$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/report/sarif/Result;", "corax-framework"})
    /* loaded from: Result$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<Result> serializer() {
            return Result$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(Result self, CompositeEncoder output, SerialDescriptor serialDesc) {
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.ruleId);
        output.encodeIntElement(serialDesc, 1, self.ruleIndex);
        output.encodeSerializableElement(serialDesc, 2, IndexedMessage$$serializer.INSTANCE, self.message);
        output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.locations);
        boolean z = output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.codeFlows, CollectionsKt.emptyList());
        if (z) {
            output.encodeSerializableElement(serialDesc, 4, serializationStrategyArr[4], self.codeFlows);
        }
    }

    public /* synthetic */ Result(int seen0, String ruleId, int ruleIndex, IndexedMessage message, List locations, List codeFlows, SerializationConstructorMarker serializationConstructorMarker) {
        if (11 != (11 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 11, Result$$serializer.INSTANCE.getDescriptor());
        }
        this.ruleId = ruleId;
        this.ruleIndex = ruleIndex;
        if ((seen0 & 4) == 0) {
            this.message = new IndexedMessage((String) null, 1, (DefaultConstructorMarker) null);
        } else {
            this.message = message;
        }
        this.locations = locations;
        if ((seen0 & 16) == 0) {
            this.codeFlows = CollectionsKt.emptyList();
        } else {
            this.codeFlows = codeFlows;
        }
    }

    public Result(@NotNull String ruleId, int ruleIndex, @NotNull IndexedMessage message, @NotNull List<Location> list, @NotNull List<CodeFlow> list2) {
        Intrinsics.checkNotNullParameter(ruleId, "ruleId");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(list, "locations");
        Intrinsics.checkNotNullParameter(list2, "codeFlows");
        this.ruleId = ruleId;
        this.ruleIndex = ruleIndex;
        this.message = message;
        this.locations = list;
        this.codeFlows = list2;
    }

    public /* synthetic */ Result(String str, int i, IndexedMessage indexedMessage, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? new IndexedMessage((String) null, 1, (DefaultConstructorMarker) null) : indexedMessage, list, (i2 & 16) != 0 ? CollectionsKt.emptyList() : list2);
    }

    @NotNull
    public final String getRuleId() {
        return this.ruleId;
    }

    public final int getRuleIndex() {
        return this.ruleIndex;
    }

    @NotNull
    public final IndexedMessage getMessage() {
        return this.message;
    }

    @NotNull
    public final List<Location> getLocations() {
        return this.locations;
    }

    @NotNull
    public final List<CodeFlow> getCodeFlows() {
        return this.codeFlows;
    }
}
