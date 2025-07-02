package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IFieldMatch
import com.feysh.corax.config.api.utils.UtilsKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty
import soot.Scene
import soot.SootClass
import soot.SootField
import soot.Type
import soot.util.Chain

@SourceDebugExtension(["SMAP\nMatchers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Matchers.kt\ncom/feysh/corax/config/api/baseimpl/SootFieldSignatureMatch\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1454#2,2:244\n774#2:246\n865#2,2:247\n1456#2,3:249\n*S KotlinDebug\n*F\n+ 1 Matchers.kt\ncom/feysh/corax/config/api/baseimpl/SootFieldSignatureMatch\n*L\n46#1:244,2\n47#1:246\n47#1:247,2\n46#1:249,3\n*E\n"])
public class SootFieldSignatureMatch(declaringClassType: String, fieldName: String, fieldType: String?, isStatic: Boolean?) : IFieldMatch {
   public open val declaringClassType: String
   public open val fieldName: String
   public open val fieldType: String?
   public open val isStatic: Boolean?

   init {
      this.declaringClassType = declaringClassType;
      this.fieldName = fieldName;
      this.fieldType = fieldType;
      this.isStatic = isStatic;
   }

   public constructor(sf: SootField)  {
      val var10001: java.lang.String = sf.getDeclaringClass().getName();
      val var10002: java.lang.String = sf.getName();
      val var10003: Type = sf.getType();
      var var2: java.lang.String = UtilsKt.getTypename(var10003);
      if (var2 == null) {
         var2 = "unknown";
      }

      this(var10001, var10002, var2, sf.isStatic());
   }

   public constructor(sf: KProperty<*>)  {
      val var10001: java.lang.String = UtilsKt.getDeclaringClass(sf as KCallable).getName();
      this(var10001, sf.getName(), UtilsKt.getSootTypeName(sf), null);
   }

   public override fun matched(scene: Scene): List<SootField> {
      val var10000: SootClass = scene.getSootClassUnsafe(this.getDeclaringClassType(), false);
      if (var10000 == null) {
         return CollectionsKt.emptyList();
      } else {
         val `$this$flatMapTo$iv`: java.lang.Iterable = CollectionsKt.listOf(var10000);
         val var19: java.util.Collection = new ArrayList();

         for (Object element$iv : $this$flatMapTo$iv) {
            val var23: Chain = (`element$iv` as SootClass).getFields();
            val `$this$filter$iv`: java.lang.Iterable = var23 as java.lang.Iterable;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               val sm: SootField = `element$iv$iv` as SootField;
               if (this.match(sm)) {
                  `destination$iv$iv`.add(`element$iv$iv`);
               }
            }

            CollectionsKt.addAll(var19, `destination$iv$iv` as java.util.List);
         }

         return var19 as MutableList<SootField>;
      }
   }

   private fun match(field: SootField): Boolean {
      if (!(this.getFieldName() == field.getName())) {
         return false;
      } else if (!(this.getDeclaringClassType() == field.getDeclaringClass().getName())) {
         return false;
      } else if (this.isStatic() != null && !(this.isStatic() == field.isStatic())) {
         return false;
      } else {
         if (this.getFieldType() != null) {
            val var10000: java.lang.String = this.getFieldType();
            val var10001: Type = field.getType();
            if (!(var10000 == UtilsKt.getTypename(var10001))) {
               return false;
            }
         }

         return true;
      }
   }

   public override fun toString(): String {
      return "<${this.getDeclaringClassType()}: ${this.getFieldType()} ${this.getFieldName()}>";
   }
}
