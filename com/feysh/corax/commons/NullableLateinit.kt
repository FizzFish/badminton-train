package com.feysh.corax.commons

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public class NullableLateinit<T>(errorMessage: String) : ReadWriteProperty<Object, T> {
   private final val errorMessage: String
   private final var value: Any?

   init {
      this.errorMessage = errorMessage;
      this.value = NullableLateinit.UNINITIALIZED.INSTANCE;
   }

   public open operator fun getValue(thisRef: Any, property: KProperty<*>): Any {
      if (this.value === NullableLateinit.UNINITIALIZED.INSTANCE) {
         throw new IllegalStateException(this.errorMessage);
      } else {
         try {
            return (T)this.value;
         } catch (var4: ClassCastException) {
            throw new IllegalStateException(this.errorMessage);
         }
      }
   }

   public open operator fun setValue(thisRef: Any, property: KProperty<*>, value: Any) {
      this.value = value;
   }

   public fun uninitialized() {
      this.value = NullableLateinit.UNINITIALIZED.INSTANCE;
   }

   public fun isUninitialized(): Boolean {
      return this.value == NullableLateinit.UNINITIALIZED.INSTANCE;
   }

   private object UNINITIALIZED
}
