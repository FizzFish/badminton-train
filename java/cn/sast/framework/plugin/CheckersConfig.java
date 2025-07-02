package cn.sast.framework.plugin;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IChecker;
import com.feysh.corax.config.api.utils.UtilsKt;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SAConfiguration.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018�� 42\u00020\u00012\u00020\u0002:\u0003234B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000b\u0010\u000fBG\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000b\u0010\u0014J\u0006\u0010\u001e\u001a\u00020��J\u0011\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0001H\u0096\u0002J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J9\u0010%\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010&\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\u0011HÖ\u0001J\t\u0010)\u001a\u00020\u0004HÖ\u0001J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020��2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00065"}, d2 = {"Lcn/sast/framework/plugin/CheckersConfig;", "Lcn/sast/framework/plugin/ConfigSerializable;", "Lcn/sast/framework/plugin/IOptional;", "name", "", "desc", "enable", "", "checkTypes", "Ljava/util/LinkedHashSet;", "Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/LinkedHashSet;)V", "checker", "Lcom/feysh/corax/config/api/IChecker;", "(Lcom/feysh/corax/config/api/IChecker;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ZLjava/util/LinkedHashSet;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getDesc", "getEnable", "()Z", "getCheckTypes", "()Ljava/util/LinkedHashSet;", "setCheckTypes", "(Ljava/util/LinkedHashSet;)V", "sort", "compareTo", "other", "component1", "component2", "component3", "component4", "copy", "equals", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "CheckTypeConfig", "$serializer", "Companion", "corax-framework"})
@SerialName("CheckerTypeConfig")
@SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/CheckersConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,557:1\n1#2:558\n*E\n"})
/* loaded from: CheckersConfig.class */
public final class CheckersConfig extends ConfigSerializable implements IOptional {

    @NotNull
    private final String name;

    @Nullable
    private final String desc;
    private final boolean enable;

    @NotNull
    private LinkedHashSet<CheckTypeConfig> checkTypes;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new LinkedHashSetSerializer(CheckersConfig$CheckTypeConfig$$serializer.INSTANCE)};

    @NotNull
    public final String component1() {
        return this.name;
    }

    @Nullable
    public final String component2() {
        return this.desc;
    }

    public final boolean component3() {
        return this.enable;
    }

    @NotNull
    public final LinkedHashSet<CheckTypeConfig> component4() {
        return this.checkTypes;
    }

    @NotNull
    public final CheckersConfig copy(@NotNull String name, @Nullable String desc, boolean enable, @NotNull LinkedHashSet<CheckTypeConfig> linkedHashSet) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkTypes");
        return new CheckersConfig(name, desc, enable, linkedHashSet);
    }

    public static /* synthetic */ CheckersConfig copy$default(CheckersConfig checkersConfig, String str, String str2, boolean z, LinkedHashSet linkedHashSet, int i, Object obj) {
        if ((i & 1) != 0) {
            str = checkersConfig.name;
        }
        if ((i & 2) != 0) {
            str2 = checkersConfig.desc;
        }
        if ((i & 4) != 0) {
            z = checkersConfig.enable;
        }
        if ((i & 8) != 0) {
            linkedHashSet = checkersConfig.checkTypes;
        }
        return checkersConfig.copy(str, str2, z, linkedHashSet);
    }

    @NotNull
    public String toString() {
        return "CheckersConfig(name=" + this.name + ", desc=" + this.desc + ", enable=" + this.enable + ", checkTypes=" + this.checkTypes + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((((result * 31) + (this.desc == null ? 0 : this.desc.hashCode())) * 31) + Boolean.hashCode(this.enable)) * 31) + this.checkTypes.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckersConfig)) {
            return false;
        }
        CheckersConfig checkersConfig = (CheckersConfig) other;
        return Intrinsics.areEqual(this.name, checkersConfig.name) && Intrinsics.areEqual(this.desc, checkersConfig.desc) && this.enable == checkersConfig.enable && Intrinsics.areEqual(this.checkTypes, checkersConfig.checkTypes);
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/plugin/CheckersConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/plugin/CheckersConfig;", "corax-framework"})
    /* loaded from: CheckersConfig$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<CheckersConfig> serializer() {
            return CheckersConfig$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(CheckersConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        ConfigSerializable.write$Self(self, output, serialDesc);
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.getName());
        boolean z = output.shouldEncodeElementDefault(serialDesc, 1) || self.desc != null;
        if (z) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.desc);
        }
        output.encodeBooleanElement(serialDesc, 2, self.getEnable());
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.checkTypes, new LinkedHashSet());
        if (z2) {
            output.encodeSerializableElement(serialDesc, 3, serializationStrategyArr[3], self.checkTypes);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CheckersConfig(int seen0, String name, String desc, boolean enable, LinkedHashSet checkTypes, SerializationConstructorMarker serializationConstructorMarker) {
        super(seen0, serializationConstructorMarker);
        if (5 != (5 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 5, CheckersConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.name = name;
        if ((seen0 & 2) == 0) {
            this.desc = null;
        } else {
            this.desc = desc;
        }
        this.enable = enable;
        if ((seen0 & 8) == 0) {
            this.checkTypes = new LinkedHashSet<>();
        } else {
            this.checkTypes = checkTypes;
        }
    }

    public /* synthetic */ CheckersConfig(String str, String str2, boolean z, LinkedHashSet linkedHashSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, z, (i & 8) != 0 ? new LinkedHashSet() : linkedHashSet);
    }

    @Override // cn.sast.framework.plugin.ConfigSerializable
    @NotNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public final String getDesc() {
        return this.desc;
    }

    @Override // cn.sast.framework.plugin.IOptional
    public boolean getEnable() {
        return this.enable;
    }

    @NotNull
    public final LinkedHashSet<CheckTypeConfig> getCheckTypes() {
        return this.checkTypes;
    }

    public final void setCheckTypes(@NotNull LinkedHashSet<CheckTypeConfig> linkedHashSet) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<set-?>");
        this.checkTypes = linkedHashSet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckersConfig(@NotNull String name, @Nullable String desc, boolean enable, @NotNull LinkedHashSet<CheckTypeConfig> linkedHashSet) {
        super(null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkTypes");
        this.name = name;
        this.desc = desc;
        this.enable = enable;
        this.checkTypes = linkedHashSet;
    }

    /* compiled from: SAConfiguration.kt */
    @Serializable
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� (2\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020��0\u0003:\u0002'(B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fB-\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\b\u0010\u0011J\u0011\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020��H\u0096\u0002J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u001a\u001a\u00020��2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u000eHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020��2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006)"}, d2 = {"Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "Lcn/sast/framework/plugin/IOptional;", "Lcn/sast/framework/plugin/IConfig;", "", "checkType", "", "enable", "", "<init>", "(Ljava/lang/String;Z)V", "type", "Lcom/feysh/corax/config/api/CheckType;", "(Lcom/feysh/corax/config/api/CheckType;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCheckType", "()Ljava/lang/String;", "getEnable", "()Z", "compareTo", "other", "component1", "component2", "copy", "equals", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
    @SerialName("CheckTypeConfig")
    @SourceDebugExtension({"SMAP\nSAConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SAConfiguration.kt\ncn/sast/framework/plugin/CheckersConfig$CheckTypeConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,557:1\n1#2:558\n*E\n"})
    /* loaded from: CheckersConfig$CheckTypeConfig.class */
    public static final class CheckTypeConfig implements IOptional, IConfig, Comparable<CheckTypeConfig> {

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private final String checkType;
        private final boolean enable;

        @NotNull
        public final String component1() {
            return this.checkType;
        }

        public final boolean component2() {
            return this.enable;
        }

        @NotNull
        public final CheckTypeConfig copy(@NotNull String checkType, boolean enable) {
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            return new CheckTypeConfig(checkType, enable);
        }

        public static /* synthetic */ CheckTypeConfig copy$default(CheckTypeConfig checkTypeConfig, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = checkTypeConfig.checkType;
            }
            if ((i & 2) != 0) {
                z = checkTypeConfig.enable;
            }
            return checkTypeConfig.copy(str, z);
        }

        @NotNull
        public String toString() {
            return "CheckTypeConfig(checkType=" + this.checkType + ", enable=" + this.enable + ")";
        }

        public int hashCode() {
            int result = this.checkType.hashCode();
            return (result * 31) + Boolean.hashCode(this.enable);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CheckTypeConfig)) {
                return false;
            }
            CheckTypeConfig checkTypeConfig = (CheckTypeConfig) other;
            return Intrinsics.areEqual(this.checkType, checkTypeConfig.checkType) && this.enable == checkTypeConfig.enable;
        }

        /* compiled from: SAConfiguration.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "corax-framework"})
        /* loaded from: CheckersConfig$CheckTypeConfig$Companion.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @NotNull
            public final KSerializer<CheckTypeConfig> serializer() {
                return CheckersConfig$CheckTypeConfig$$serializer.INSTANCE;
            }
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$corax_framework(CheckTypeConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.checkType);
            output.encodeBooleanElement(serialDesc, 1, self.getEnable());
        }

        public /* synthetic */ CheckTypeConfig(int seen0, String checkType, boolean enable, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (3 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException(seen0, 3, CheckersConfig$CheckTypeConfig$$serializer.INSTANCE.getDescriptor());
            }
            this.checkType = checkType;
            this.enable = enable;
        }

        public CheckTypeConfig(@NotNull String checkType, boolean enable) {
            Intrinsics.checkNotNullParameter(checkType, "checkType");
            this.checkType = checkType;
            this.enable = enable;
        }

        @NotNull
        public final String getCheckType() {
            return this.checkType;
        }

        @Override // cn.sast.framework.plugin.IOptional
        public boolean getEnable() {
            return this.enable;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public CheckTypeConfig(@NotNull CheckType type) {
            this(SAConfigurationKt.get1to1SpecialIdentifier(type), true);
            Intrinsics.checkNotNullParameter(type, "type");
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull CheckTypeConfig other) {
            Intrinsics.checkNotNullParameter(other, "other");
            Integer numValueOf = Integer.valueOf(this.checkType.compareTo(other.checkType));
            int it = numValueOf.intValue();
            Integer num = it != 0 ? numValueOf : null;
            if (num != null) {
                int it2 = num.intValue();
                return it2;
            }
            if (getEnable() != other.getEnable()) {
                return getEnable() ? -1 : 1;
            }
            return 0;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CheckersConfig(@NotNull IChecker checker) {
        this(UtilsKt.getSootTypeName(checker.getClass()), checker.getDesc(), true, (LinkedHashSet) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(checker, "checker");
    }

    @NotNull
    public final CheckersConfig sort() {
        this.checkTypes = (LinkedHashSet) CollectionsKt.toCollection(CollectionsKt.sorted(this.checkTypes), new LinkedHashSet());
        return this;
    }

    @Override // cn.sast.framework.plugin.ConfigSerializable, java.lang.Comparable
    public int compareTo(@NotNull ConfigSerializable other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!(other instanceof CheckersConfig)) {
            return super.compareTo(other);
        }
        Integer numValueOf = Integer.valueOf(super.compareTo(other));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        if (getEnable() != ((CheckersConfig) other).getEnable()) {
            return getEnable() ? -1 : 1;
        }
        return 0;
    }
}
