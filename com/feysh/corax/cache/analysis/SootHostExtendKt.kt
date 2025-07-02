@file:SourceDebugExtension(["SMAP\nSootHostExtend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootHostExtendKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n774#2:392\n865#2,2:393\n808#2,11:395\n295#2,2:406\n1557#2:408\n1628#2,3:409\n1755#2,3:412\n1#3:415\n*S KotlinDebug\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootHostExtendKt\n*L\n317#1:392\n317#1:393,2\n328#1:395,11\n328#1:406,2\n351#1:408\n351#1:409,3\n378#1:412,3\n*E\n"])

package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.feysh.corax.config.api.utils.UtilsKt
import com.github.javaparser.Position
import com.github.javaparser.Range
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.Node.TreeTraversal
import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.github.javaparser.ast.expr.ObjectCreationExpr
import java.util.ArrayList
import java.util.Optional
import java.util.TreeMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.optionals.OptionalsKt
import soot.SootClass
import soot.SootMethod
import soot.Unit
import soot.tagkit.Host
import soot.util.Chain

public final val line: Int?
   public final get() {
      val var10000: Position = OptionalsKt.getOrNull(`$this$line`) as Position;
      return if (var10000 != null) var10000.line else null;
   }


public final val column: Int?
   public final get() {
      val var10000: Position = OptionalsKt.getOrNull(`$this$column`) as Position;
      return if (var10000 != null) var10000.column else null;
   }


public const val OUTER_INNER_CLASSES_DELIMITER: Char = '$'

public final val declaredClassName: String
   public final get() {
      var var2: java.lang.String;
      if (`$this$declaredClassName`.isInnerClass()) {
         var2 = `$this$declaredClassName`.getJavaStyleName();
         var2 = StringsKt.substringAfterLast$default(var2, '$', null, 2, null);
      } else {
         val var1: java.lang.String = `$this$declaredClassName`.getJavaStyleName();
         var2 = var1;
      }

      return var2;
   }


public final val declaredName: String
   public final get() {
      val var2: java.lang.String;
      if (!`$this$declaredName`.isStaticInitializer() && !`$this$declaredName`.isConstructor()) {
         val var1: java.lang.String = `$this$declaredName`.getName();
         var2 = var1;
      } else {
         val var10000: SootClass = `$this$declaredName`.getDeclaringClass();
         var2 = getDeclaredClassName(var10000);
      }

      return var2;
   }


public final val decl: Node?
   public final get() {
      val allClassDecls: java.util.List = getAllClassDecls(`$context_receiver_0`);
      val var10000: TypeDeclaration = matchClassByFullyQualifiedName(`$this$decl`, allClassDecls);
      label11:
      if (var10000 != null) {
         return var10000 as Node;
      } else {
         val var7: Node = matchClassByRange(`$this$decl`, allClassDecls);
         return var7 ?: null;
      }
   }


public fun Host.ext(c: AnalysisCache = AnalysisCache.G.INSTANCE as AnalysisCache, key: Key<SootHostExtend?>): SootHostExtend? {
   return if (`$this$ext` is Unit) null else c.get(new SootHostExtInfoKey(`$this$ext`, key));
}

@JvmSynthetic
fun `ext$default`(var0: Host, var1: AnalysisCache, var2: AnalysisDataFactory.Key, var3: Int, var4: Any): SootHostExtend {
   if ((var3 and 1) != 0) {
      var1 = AnalysisCache.G.INSTANCE;
   }

   return ext(var0, var1, var2);
}

context(CompilationUnit)
private fun getAllClassDecls(): List<Node> {
   var var10000: java.util.List = `$context_receiver_0`.findAll(TypeDeclaration.class);
   val anonymousClasses: java.lang.Iterable = var10000;
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$filter$iv) {
      if ((`element$iv$iv` as TypeDeclaration) !is AnnotationDeclaration) {
         `destination$iv$iv`.add(`element$iv$iv`);
      }
   }

   val types: java.util.List = `destination$iv$iv` as java.util.List;
   var10000 = `$context_receiver_0`.findAll(ObjectCreationExpr.class, <unrepresentable>.INSTANCE);
   return CollectionsKt.plus(types, CollectionsKt.filterNotNull(var10000));
}

private fun matchClassByFullyQualifiedName(sootClass: SootClass, classDecls: List<Node>): TypeDeclaration<*>? {
   val `$this$firstOrNull$iv`: java.lang.Iterable = classDecls;
   val `element$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$filterIsInstance$iv) {
      if (fullQualifiedName is TypeDeclaration) {
         `element$iv`.add(fullQualifiedName);
      }
   }

   val `$this$filterIsInstanceTo$iv$iv`: java.util.Iterator = (`element$iv` as java.util.List).iterator();

   var var10000: Any;
   while (true) {
      if (`$this$filterIsInstanceTo$iv$iv`.hasNext()) {
         val var11: Any = `$this$filterIsInstanceTo$iv$iv`.next();
         var10000 = (var11 as TypeDeclaration).getFullyQualifiedName();
         var10000 = OptionalsKt.getOrNull((Optional)var10000) as java.lang.String;
         val var17: Boolean;
         if (var10000 == null) {
            var17 = false;
         } else {
            val var10001: java.lang.String = sootClass.getName();
            var17 = var10000 == StringsKt.replace$default(var10001, '$', '.', false, 4, null);
         }

         if (!var17) {
            continue;
         }

         var10000 = var11;
         break;
      }

      var10000 = null;
      break;
   }

   return var10000 as TypeDeclaration<?>;
}

private fun matchClassByRange(sootClass: SootClass, classDecls: List<Node>): Node? {
   var res: Node = null;

   for (Node clazz : classDecls) {
      if (memberMethods is TypeDeclaration || memberMethods is ObjectCreationExpr) {
         var var10000: Optional = memberMethods.getRange();
         val var27: Range = OptionalsKt.getOrNull(var10000) as Range;
         if (var27 != null) {
            if (rangeCoverClass(var27, sootClass)) {
               val var32: Node;
               if (res != null) {
                  label128: {
                     var10000 = res.getRange();
                     if (var10000 != null) {
                        val var29: Range = OptionalsKt.getOrNull(var10000) as Range;
                        if (var29 != null && var29.begin != null) {
                           var31 = var29.begin.line;
                           break label128;
                        }
                     }

                     var31 = -1;
                  }

                  if (var31 > var27.begin.line) {
                     continue;
                  }

                  var32 = memberMethods;
               } else {
                  var32 = memberMethods;
               }

               res = var32;
            }
         }
      }
   }

   var var33: java.util.List = sootClass.getMethods();
   val var15: java.lang.Iterable = var33;
   val var21: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var33, 10));

   for (Object item$iv$iv : $this$map$iv) {
      var21.add((var10 as SootMethod).getName());
   }

   val var14: java.util.List = var21 as java.util.List;
   if (res is TypeDeclaration) {
      val var34: java.lang.String = sootClass.getShortName();
      if (!StringsKt.endsWith$default(var34, "\$${(res as TypeDeclaration).getName().getIdentifier()}", false, 2, null)) {
         return null;
      }

      var33 = (res as TypeDeclaration).getMethods();
   } else {
      if (res !is ObjectCreationExpr) {
         return null;
      }

      val var17: SootClass = UtilsKt.superClassOrNull(sootClass);
      val var19: java.lang.String = (res as ObjectCreationExpr).getType().getName().getIdentifier();
      if (var17 != null) {
         val var36: java.lang.String = var17.getShortName();
         if ((var36 == null || !var36.equals(var19)) && !(var17.getName() == "java.lang.Object")) {
            return null;
         }
      }

      if (var17 == null || var17.getName() == "java.lang.Object") {
         val var37: Chain = sootClass.getInterfaces();
         val var22: SootClass = CollectionsKt.singleOrNull(var37 as java.lang.Iterable) as SootClass;
         if (var22 != null) {
            val var38: java.lang.String = var22.getShortName();
            if (var38 == null || !var38.equals(var19)) {
               return null;
            }
         }
      }

      var33 = (res as ObjectCreationExpr).findAll(CallableDeclaration.class, TreeTraversal.DIRECT_CHILDREN);
   }

   val var40: Boolean;
   if (var33 != null) {
      val var18: java.lang.Iterable = var33;
      var var39: Boolean;
      if (var33 is java.util.Collection && (var33 as java.util.Collection).isEmpty()) {
         var39 = false;
      } else {
         label151: {
            for (Object element$iv : $this$any$iv) {
               if (!var14.contains((var24 as CallableDeclaration).getNameAsString())) {
                  var39 = true;
                  break label151;
               }
            }

            var39 = false;
         }
      }

      var40 = var39;
   } else {
      var40 = false;
   }

   return if (var40) null else res;
}

private fun rangeCoverClass(range: Range, sootClass: SootClass): Boolean {
   val var10000: SootLineToMethodMap = AnalysisCache.G.INSTANCE.get(new SootLineToMethodMapKey(sootClass));
   if (var10000 != null) {
      val var6: TreeMap = var10000.getMap();
      if (var6 != null) {
         val var7: TreeMap = if (!var6.isEmpty()) var6 else null;
         if (var7 != null) {
            if (range.end.line < (var7.lastKey() as java.lang.Number).intValue()) {
               return false;
            }

            if (range.begin.line > (var7.firstKey() as java.lang.Number).intValue()) {
               return false;
            }

            return true;
         }
      }
   }

   return false;
}
