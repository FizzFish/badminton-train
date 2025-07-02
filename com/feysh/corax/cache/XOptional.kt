package com.feysh.corax.cache

public class XOptional<T> private constructor(value: Any) {
   public final val value: Any

   init {
      this.value = (T)value;
   }

   public companion object {
      public fun <T> of(value: T): XOptional<T> {
         return new XOptional<>(value, null);
      }
   }
}
