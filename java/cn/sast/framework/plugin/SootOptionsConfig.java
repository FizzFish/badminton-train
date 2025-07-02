package cn.sast.framework.plugin;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.SAOptions;
import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SAConfiguration.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� +2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002*+B&\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\r\u0010\b\u001a\t\u0018\u00010\t¢\u0006\u0002\b\n¢\u0006\u0004\b\u000b\u0010\fB7\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\u0011\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0001H\u0096\u0002J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u001c\u001a\t\u0018\u00010\t¢\u0006\u0002\b\nHÆ\u0003J.\u0010\u001d\u001a\u00020��2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000f\b\u0002\u0010\b\u001a\t\u0018\u00010\t¢\u0006\u0002\b\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u000eHÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020��2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\b\u001a\t\u0018\u00010\t¢\u0006\u0002\b\nX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017¨\u0006,"}, d2 = {"Lcn/sast/framework/plugin/SootOptionsConfig;", "Lcn/sast/framework/plugin/ConfigSerializable;", "Lcn/sast/framework/plugin/IOptional;", "Lcn/sast/framework/plugin/IFieldOptions;", "name", "", "enable", "", "options", "Lcom/feysh/corax/config/api/SAOptions;", "Lkotlinx/serialization/Polymorphic;", "<init>", "(Ljava/lang/String;ZLcom/feysh/corax/config/api/SAOptions;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ZLcom/feysh/corax/config/api/SAOptions;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getEnable", "()Z", "getOptions", "()Lcom/feysh/corax/config/api/SAOptions;", "compareTo", "other", "component1", "component2", "component3", "copy", "equals", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
@SerialName("SootOptionsConfig")
@SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/SootOptionsConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,557:1\n1#2:558\n*E\n"})
/* loaded from: SootOptionsConfig.class */
public final class SootOptionsConfig extends ConfigSerializable implements IOptional, IFieldOptions {

    @NotNull
    private final String name;
    private final boolean enable;

    @Nullable
    private final SAOptions options;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(SAOptions.class), new Annotation[0])};

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final boolean component2() {
        return this.enable;
    }

    @Nullable
    public final SAOptions component3() {
        return this.options;
    }

    @NotNull
    public final SootOptionsConfig copy(@NotNull String name, boolean enable, @Nullable SAOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SootOptionsConfig(name, enable, options);
    }

    public static /* synthetic */ SootOptionsConfig copy$default(SootOptionsConfig sootOptionsConfig, String str, boolean z, SAOptions sAOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sootOptionsConfig.name;
        }
        if ((i & 2) != 0) {
            z = sootOptionsConfig.enable;
        }
        if ((i & 4) != 0) {
            sAOptions = sootOptionsConfig.options;
        }
        return sootOptionsConfig.copy(str, z, sAOptions);
    }

    @NotNull
    public String toString() {
        return "SootOptionsConfig(name=" + this.name + ", enable=" + this.enable + ", options=" + this.options + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((result * 31) + Boolean.hashCode(this.enable)) * 31) + (this.options == null ? 0 : this.options.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SootOptionsConfig)) {
            return false;
        }
        SootOptionsConfig sootOptionsConfig = (SootOptionsConfig) other;
        return Intrinsics.areEqual(this.name, sootOptionsConfig.name) && this.enable == sootOptionsConfig.enable && Intrinsics.areEqual(this.options, sootOptionsConfig.options);
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/plugin/SootOptionsConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/plugin/SootOptionsConfig;", "corax-framework"})
    /* loaded from: SootOptionsConfig$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<SootOptionsConfig> serializer() {
            return SootOptionsConfig$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(SootOptionsConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        ConfigSerializable.write$Self(self, output, serialDesc);
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.getName());
        output.encodeBooleanElement(serialDesc, 1, self.getEnable());
        output.encodeNullableSerializableElement(serialDesc, 2, serializationStrategyArr[2], self.getOptions());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SootOptionsConfig(int seen0, String name, boolean enable, SAOptions options, SerializationConstructorMarker serializationConstructorMarker) {
        super(seen0, serializationConstructorMarker);
        if (7 != (7 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 7, SootOptionsConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.name = name;
        this.enable = enable;
        this.options = options;
    }

    @Override // cn.sast.framework.plugin.ConfigSerializable
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // cn.sast.framework.plugin.IOptional
    public boolean getEnable() {
        return this.enable;
    }

    @Override // cn.sast.framework.plugin.IFieldOptions
    @Nullable
    public SAOptions getOptions() {
        return this.options;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SootOptionsConfig(@NotNull String name, boolean enable, @Nullable SAOptions options) {
        super(null);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.enable = enable;
        this.options = options;
    }

    @Override // cn.sast.framework.plugin.ConfigSerializable, java.lang.Comparable
    public int compareTo(@NotNull ConfigSerializable other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!(other instanceof SootOptionsConfig)) {
            return super.compareTo(other);
        }
        Integer numValueOf = Integer.valueOf(super.compareTo(other));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        if (getEnable() != ((SootOptionsConfig) other).getEnable()) {
            return getEnable() ? -1 : 1;
        }
        return 0;
    }
}
