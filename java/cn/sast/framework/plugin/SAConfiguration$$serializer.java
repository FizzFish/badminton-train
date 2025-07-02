package cn.sast.framework.plugin;

import cn.sast.api.config.BuiltinAnalysisConfig;
import cn.sast.api.config.BuiltinAnalysisConfig$$serializer;
import cn.sast.api.config.PreAnalysisConfig;
import cn.sast.api.config.PreAnalysisConfig$$serializer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: SAConfiguration.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"cn/sast/framework/plugin/SAConfiguration.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/framework/plugin/SAConfiguration;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-framework"})
/* loaded from: SAConfiguration$$serializer.class */
public /* synthetic */ class SAConfiguration$$serializer implements GeneratedSerializer<SAConfiguration> {

    @NotNull
    public static final SAConfiguration$$serializer INSTANCE = new SAConfiguration$$serializer();

    @NotNull
    private static final SerialDescriptor descriptor;

    private SAConfiguration$$serializer() {
    }

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull SAConfiguration value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        SAConfiguration.write$Self$corax_framework(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final SAConfiguration m327deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        boolean z = true;
        int i = 0;
        BuiltinAnalysisConfig builtinAnalysisConfig = null;
        PreAnalysisConfig preAnalysisConfig = null;
        LinkedHashMap linkedHashMap = null;
        LinkedHashSet linkedHashSet = null;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        DeserializationStrategy[] deserializationStrategyArr = SAConfiguration.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            builtinAnalysisConfig = (BuiltinAnalysisConfig) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, BuiltinAnalysisConfig$$serializer.INSTANCE, (Object) null);
            preAnalysisConfig = (PreAnalysisConfig) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, PreAnalysisConfig$$serializer.INSTANCE, (Object) null);
            linkedHashMap = (LinkedHashMap) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, deserializationStrategyArr[2], (Object) null);
            linkedHashSet = (LinkedHashSet) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, deserializationStrategyArr[3], (Object) null);
            i = 0 | 1 | 2 | 4 | 8;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        builtinAnalysisConfig = (BuiltinAnalysisConfig) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, BuiltinAnalysisConfig$$serializer.INSTANCE, builtinAnalysisConfig);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        preAnalysisConfig = (PreAnalysisConfig) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, PreAnalysisConfig$$serializer.INSTANCE, preAnalysisConfig);
                        i |= 2;
                        break;
                    case 2:
                        linkedHashMap = (LinkedHashMap) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, deserializationStrategyArr[2], linkedHashMap);
                        i |= 4;
                        break;
                    case 3:
                        linkedHashSet = (LinkedHashSet) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, deserializationStrategyArr[3], linkedHashSet);
                        i |= 8;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new SAConfiguration(i, builtinAnalysisConfig, preAnalysisConfig, linkedHashMap, linkedHashSet, (SerializationConstructorMarker) null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr = SAConfiguration.$childSerializers;
        return new KSerializer[]{BuiltinAnalysisConfig$$serializer.INSTANCE, PreAnalysisConfig$$serializer.INSTANCE, kSerializerArr[2], kSerializerArr[3]};
    }

    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.framework.plugin.SAConfiguration", INSTANCE, 4);
        pluginGeneratedSerialDescriptor.addElement("builtinAnalysisConfig", true);
        pluginGeneratedSerialDescriptor.addElement("preAnalysisConfig", true);
        pluginGeneratedSerialDescriptor.addElement("configurations", true);
        pluginGeneratedSerialDescriptor.addElement("checkers", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }
}
