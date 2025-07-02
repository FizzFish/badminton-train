package com.feysh.corax.config.api

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind.OBJECT
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nAIAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ObjectSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,839:1\n565#2,4:840\n*S KotlinDebug\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ObjectSerializer\n*L\n114#1:840,4\n*E\n"])
internal class ObjectSerializer<T>(serialName: String, objectInstance: Any) : KSerializer<T> {
   private final val objectInstance: Any

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.descriptor$delegate.getValue() as SerialDescriptor;
      }


   init {
      this.objectInstance = (T)objectInstance;
      this.descriptor$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, ObjectSerializer::descriptor_delegate$lambda$0);
   }

   public open fun serialize(encoder: Encoder, value: Any) {
      encoder.beginStructure(this.getDescriptor()).endStructure(this.getDescriptor());
   }

   public open fun deserialize(decoder: Decoder): Any {
      val `descriptor$iv`: SerialDescriptor = this.getDescriptor();
      val `composite$iv`: CompositeDecoder = decoder.beginStructure(`descriptor$iv`);
      val index: Int = `composite$iv`.decodeElementIndex(this.getDescriptor());
      if (index == -1) {
         `composite$iv`.endStructure(`descriptor$iv`);
         return this.objectInstance;
      } else {
         throw new SerializationException("Unexpected index $index");
      }
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$0`(`$serialName`: java.lang.String): SerialDescriptor {
      return SerialDescriptorsKt.buildSerialDescriptor$default(`$serialName`, OBJECT.INSTANCE as SerialKind, new SerialDescriptor[0], null, 8, null);
   }
}
