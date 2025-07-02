package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassMatch
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import soot.RefType
import soot.Scene
import soot.SootClass

@Serializable
@SerialName("QualifiedRefType")
@SourceDebugExtension(["SMAP\nQualifiedRefType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 QualifiedRefType.kt\ncom/feysh/corax/config/api/baseimpl/QualifiedRefType\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1863#2:46\n1864#2:48\n1734#2,3:49\n1611#2,9:52\n1863#2:61\n1864#2:63\n1620#2:64\n774#2:65\n865#2,2:66\n1557#2:68\n1628#2,3:69\n1864#2:72\n1734#2,3:73\n1368#2:76\n1454#2,2:77\n1557#2:79\n1628#2,3:80\n1456#2,3:83\n1368#2:86\n1454#2,2:87\n1557#2:89\n1628#2,3:90\n1456#2,3:93\n1#3:47\n1#3:62\n*S KotlinDebug\n*F\n+ 1 QualifiedRefType.kt\ncom/feysh/corax/config/api/baseimpl/QualifiedRefType\n*L\n29#1:46\n29#1:48\n34#1:49,3\n26#1:52,9\n26#1:61\n26#1:63\n26#1:64\n38#1:65\n38#1:66,2\n38#1:68\n38#1:69,3\n29#1:72\n34#1:73,3\n25#1:76\n25#1:77,2\n25#1:79\n25#1:80,3\n25#1:83,3\n25#1:86\n25#1:87,2\n25#1:89\n25#1:90,3\n25#1:93,3\n26#1:62\n*E\n"])
public open class QualifiedRefType(`package`: List<String>, simpleName: List<String>) : IClassMatch {
   public final val `package`: List<String>
   public open val simpleName: List<String>

   public final val type: List<String>
      public final get() {
         return this.type$delegate.getValue() as MutableList<java.lang.String>;
      }


   public final val sooType: List<RefType>
      public final get() {
         val `$this$mapNotNull$iv`: java.lang.Iterable = this.getType();
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            val var10000: RefType = Scene.v().getRefTypeUnsafe(`element$iv$iv$iv` as java.lang.String);
            if (var10000 != null) {
               `destination$iv$iv`.add(var10000);
            }
         }

         return `destination$iv$iv` as MutableList<RefType>;
      }


   init {
      this.package = var1;
      this.simpleName = simpleName;
      this.type$delegate = LazyKt.lazy(QualifiedRefType::type_delegate$lambda$2);

      val `$this$all$iv`: java.lang.Iterable;
      for (Object element$iv : $this$all$iv) {
         val it: java.lang.String = `element$iv` as java.lang.String;
         if (StringsKt.contains$default(`element$iv` as java.lang.String, '/', false, 2, null)) {
            throw new IllegalArgumentException(("error sig: $it").toString());
         }

         if (StringsKt.contains$default(it, '\\', false, 2, null)) {
            throw new IllegalArgumentException(("error sig: $it").toString());
         }
      }

      if (this.getType().isEmpty()) {
         throw new IllegalStateException(("error syntax: $this").toString());
      } else {
         `$this$all$iv` = this.getType();
         var var10000: Boolean;
         if (`$this$all$iv` is java.util.Collection && (`$this$all$iv` as java.util.Collection).isEmpty()) {
            var10000 = true;
         } else {
            label80: {
               for (Object element$iv : $this$all$iv) {
                  if ((var17 as java.lang.String).length() <= 0) {
                     var10000 = false;
                     break label80;
                  }
               }

               var10000 = true;
            }
         }

         if (!var10000) {
            throw new IllegalStateException(("error syntax: $this").toString());
         }
      }
   }

   public constructor(`package`: String, simpleName: String) : this(CollectionsKt.listOf(var1), CollectionsKt.listOf(simpleName))
   public constructor(`package`: List<String>, simpleName: String) : this(var1, CollectionsKt.listOf(simpleName))
   public constructor(`package`: String, simpleName: List<String>) : this(CollectionsKt.listOf(var1), simpleName)
   public constructor(typeName: String) : this(
         CollectionsKt.listOf(QualifiedRefTypeKt.splitPackage(typeName).getFirst()),
         CollectionsKt.listOf(QualifiedRefTypeKt.splitPackage(typeName).getSecond())
      )
   public override fun matched(scene: Scene): List<SootClass> {
      var `$this$map$iv`: java.lang.Iterable = this.getSooType();
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if ((`item$iv$iv` as RefType).hasSootClass()) {
            `destination$iv$iv`.add(`item$iv$iv`);
         }
      }

      `$this$map$iv` = `destination$iv$iv` as java.util.List;
      `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(`destination$iv$iv` as java.util.List, 10));

      for (Object item$iv$iv : $this$filter$iv) {
         `destination$iv$iv`.add((var17 as RefType).getSootClass());
      }

      return `destination$iv$iv` as MutableList<SootClass>;
   }

   public override fun toString(): String {
      return if (this.getType().size() == 1)
         CollectionsKt.first(this.getType()) as java.lang.String
         else
         CollectionsKt.joinToString$default(this.getType(), ", ", "[", "]", 0, null, null, 56, null);
   }

   @JvmStatic
   fun `type_delegate$lambda$2`(`this$0`: QualifiedRefType): java.util.List {
      val `$this$flatMap$iv`: java.lang.Iterable = `this$0`.package;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         val `list$iv$iv`: java.lang.String = `element$iv$iv` as java.lang.String;
         val `$this$map$iv`: java.lang.Iterable = `this$0`.getSimpleName();
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add("$`list$iv$iv`.${`item$iv$iv` as java.lang.String}");
         }

         CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
      }

      return `destination$iv$iv` as java.util.List;
   }

   @JvmStatic
   fun `_init_$lambda$14`(`this$0`: QualifiedRefType): java.util.List {
      val `$this$flatMap$iv`: java.lang.Iterable = `this$0`.package;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         val `list$iv$iv`: java.lang.String = `element$iv$iv` as java.lang.String;
         val `$this$map$iv`: java.lang.Iterable = `this$0`.getSimpleName();
         val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$ivx`.add("$`list$iv$iv`.${`item$iv$iv` as java.lang.String}");
         }

         CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
      }

      return `destination$iv$iv` as java.util.List;
   }

   public companion object {
      public fun serializer(): KSerializer<QualifiedRefType> {
         return QualifiedRefType.$serializer.INSTANCE as KSerializer<QualifiedRefType>;
      }
   }
}
