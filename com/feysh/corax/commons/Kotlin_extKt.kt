@file:SourceDebugExtension(["SMAP\nkotlin-ext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 kotlin-ext.kt\ncom/feysh/corax/commons/Kotlin_extKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"])

package com.feysh.corax.commons

import java.lang.reflect.Field
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public fun <T> Any.delegateField(clazz: Class<*>? = null): PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, T>> {
   return new PropertyDelegateProvider<Object, ReadWriteProperty<? super Object, T>>(`$this$delegateField`, clazz) {
      private final Object instance;

      {
         this.$clazz = `$clazz`;
         this.instance = `$receiver`;
      }

      public final Object getInstance() {
         return this.instance;
      }

      public ReadWriteProperty<Object, T> provideDelegate(Object thisRef, KProperty<?> property) {
         return new ReadWriteProperty<Object, T>(this.$clazz, this, property) {
            {
               this.$clazz = `$clazz`;
               this.this$0 = `$receiver`;
               this.$property = `$property`;
            }

            public final Field getField() {
               var var10000: Class = this.$clazz;
               if (this.$clazz == null) {
                  var10000 = this.this$0.getInstance().getClass();
               }

               val var1: Field = var10000.getDeclaredField(this.$property.getName());
               var1.setAccessible(true);
               return var1;
            }

            public T getValue(Object thisRef, KProperty<?> propertyx) {
               return (T)this.getField().get(this.this$0.getInstance());
            }

            public void setValue(Object thisRef, KProperty<?> propertyx, T value) {
               this.getField().set(this.this$0.getInstance(), value);
            }
         };
      }
   };
}

@JvmSynthetic
fun `delegateField$default`(var0: Any, var1: Class, var2: Int, var3: Any): PropertyDelegateProvider {
   if ((var2 and 1) != 0) {
      var1 = null;
   }

   return delegateField(var0, var1);
}

public fun <T : Comparable<T>> T?.compareToNullable(other: T?): Int {
   if (`$this$compareToNullable` == null && other == null) {
      return 0;
   } else if (`$this$compareToNullable` == null) {
      return 1;
   } else {
      label26:
      if (other == null) {
         return -1;
      } else {
         val var3: Int = `$this$compareToNullable`.compareTo(other);
         val var2: Int = if (var3.intValue() != 0) var3 else null;
         return if (var2 != null) var2.intValue() else 0;
      }
   }
}
