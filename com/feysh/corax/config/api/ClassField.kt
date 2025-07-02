package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

public data class ClassField(declaringClassType: String?, fieldName: String, fieldType: String?) : IClassField {
   public final val declaringClassType: String?
   public final val fieldName: String
   public final val fieldType: String?

   init {
      this.declaringClassType = declaringClassType;
      this.fieldName = fieldName;
      this.fieldType = fieldType;
   }

   public constructor(field: KProperty<*>) : this(UtilsKt.getDeclaringClassName(field as KCallable<?>), field.getName(), UtilsKt.getSootTypeName(field))
   public override fun toString(): String {
      var var10000: java.lang.String = this.declaringClassType;
      if (this.declaringClassType == null) {
         var10000 = "*";
      }

      return "<$var10000: ${this.fieldType} ${this.fieldName}>";
   }

   public operator fun component1(): String? {
      return this.declaringClassType;
   }

   public operator fun component2(): String {
      return this.fieldName;
   }

   public operator fun component3(): String? {
      return this.fieldType;
   }

   public fun copy(declaringClassType: String? = this.declaringClassType, fieldName: String = this.fieldName, fieldType: String? = this.fieldType): ClassField {
      return new ClassField(declaringClassType, fieldName, fieldType);
   }

   public override fun hashCode(): Int {
      return ((if (this.declaringClassType == null) 0 else this.declaringClassType.hashCode()) * 31 + this.fieldName.hashCode()) * 31
         + (if (this.fieldType == null) 0 else this.fieldType.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ClassField) {
         return false;
      } else {
         val var2: ClassField = other as ClassField;
         if (!(this.declaringClassType == (other as ClassField).declaringClassType)) {
            return false;
         } else if (!(this.fieldName == var2.fieldName)) {
            return false;
         } else {
            return this.fieldType == var2.fieldType;
         }
      }
   }
}
