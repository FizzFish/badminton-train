package cn.sast.graph;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: HashMutableDirectedGraph.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018��*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0017\b\u0016\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0015\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003J\u0015\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"cn/sast/graph/HashMutableDirectedGraph.$serializer", "N", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/graph/HashMutableDirectedGraph;", "<init>", "()V", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "childSerializers", "", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "typeParametersSerializers", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-api"})
/* loaded from: HashMutableDirectedGraph$$serializer.class */
public /* synthetic */ class HashMutableDirectedGraph$$serializer<N> implements GeneratedSerializer<HashMutableDirectedGraph<N>> {

    @NotNull
    private final SerialDescriptor descriptor;
    private final /* synthetic */ KSerializer<?> typeSerial0;

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    private HashMutableDirectedGraph$$serializer() {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.graph.HashMutableDirectedGraph", this, 4);
        pluginGeneratedSerialDescriptor.addElement("nodeToPreds", false);
        pluginGeneratedSerialDescriptor.addElement("nodeToSuccs", false);
        pluginGeneratedSerialDescriptor.addElement("heads", false);
        pluginGeneratedSerialDescriptor.addElement("tails", false);
        this.descriptor = pluginGeneratedSerialDescriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull HashMutableDirectedGraph<N> hashMutableDirectedGraph) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(hashMutableDirectedGraph, "value");
        SerialDescriptor serialDescriptor = this.descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        HashMutableDirectedGraph.write$Self$corax_api(hashMutableDirectedGraph, compositeEncoderBeginStructure, serialDescriptor, this.typeSerial0);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final HashMutableDirectedGraph<N> m452deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = this.descriptor;
        boolean z = true;
        int i = 0;
        Map map = null;
        Map map2 = null;
        Set set = null;
        Set set2 = null;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), (Object) null);
            map2 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), (Object) null);
            set = (Set) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, new LinkedHashSetSerializer(this.typeSerial0), (Object) null);
            set2 = (Set) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, new LinkedHashSetSerializer(this.typeSerial0), (Object) null);
            i = 0 | 1 | 2 | 4 | 8;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 0, new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), map);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        map2 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), map2);
                        i |= 2;
                        break;
                    case 2:
                        set = (Set) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, new LinkedHashSetSerializer(this.typeSerial0), set);
                        i |= 4;
                        break;
                    case 3:
                        set2 = (Set) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 3, new LinkedHashSetSerializer(this.typeSerial0), set2);
                        i |= 8;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new HashMutableDirectedGraph<>(i, map, map2, set, set2, null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), new LinkedHashMapSerializer(this.typeSerial0, new LinkedHashSetSerializer(this.typeSerial0)), new LinkedHashSetSerializer(this.typeSerial0), new LinkedHashSetSerializer(this.typeSerial0)};
    }

    @NotNull
    public final KSerializer<?>[] typeParametersSerializers() {
        return new KSerializer[]{this.typeSerial0};
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HashMutableDirectedGraph$$serializer(@NotNull KSerializer<N> kSerializer) {
        this();
        Intrinsics.checkNotNullParameter(kSerializer, "typeSerial0");
        this.typeSerial0 = kSerializer;
    }

    private final /* synthetic */ KSerializer getTypeSerial0() {
        return this.typeSerial0;
    }
}
