@file:SourceDebugExtension(["SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/feysh/corax/config/api/utils/UtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,484:1\n1557#2:485\n1628#2,3:486\n1557#2:489\n1628#2,3:490\n1368#2:498\n1454#2,5:499\n1368#2:504\n1454#2,5:505\n808#2,11:510\n1368#2:521\n1454#2,5:522\n1368#2:527\n1454#2,5:528\n1#3:493\n1310#4,2:494\n1310#4,2:496\n*S KotlinDebug\n*F\n+ 1 Utils.kt\ncom/feysh/corax/config/api/utils/UtilsKt\n*L\n128#1:485\n128#1:486,3\n151#1:489\n151#1:490,3\n347#1:498\n347#1:499,5\n348#1:504\n348#1:505,5\n447#1:510,11\n358#1:521\n358#1:522,5\n405#1:527\n405#1:528,5\n285#1:494,2\n291#1:496,2\n*E\n"])

package com.feysh.corax.config.api.utils

import com.feysh.corax.commons.ExceptionsKt
import com.google.common.base.Optional
import java.lang.reflect.Constructor
import java.lang.reflect.Executable
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.ArrayList
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.CallableReference
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KDeclarationContainer
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.full.KCallables
import kotlin.reflect.jvm.ReflectJvmMapping
import org.slf4j.event.Level
import soot.ArrayType
import soot.Body
import soot.BooleanType
import soot.ByteType
import soot.CharType
import soot.DoubleType
import soot.FloatType
import soot.IntType
import soot.Local
import soot.LongType
import soot.RefType
import soot.Scene
import soot.ShortType
import soot.SootClass
import soot.SootMethod
import soot.Type
import soot.VoidType
import soot.asm.AsmUtil
import soot.jimple.Constant
import soot.tagkit.ParamNamesTag
import soot.tagkit.VisibilityAnnotationTag
import soot.util.Chain

public final val functionType: KFunctionType
   public final get() {
      return if (`$this$functionType`.isConstructor())
         KFunctionType.Constructor
         else
         (if (!`$this$functionType`.isStatic() && !`$this$functionType`.isStaticInitializer()) KFunctionType.InstanceMethod else KFunctionType.StaticMethod);
   }


public final val declaringClass: Class<*>
   public final get() {
      val ignore: KDeclarationContainer = if ((`$this$declaringClass` as? CallableReference) != null)
         (`$this$declaringClass` as? CallableReference).getOwner()
         else
         null;
      val res: Class = if ((ignore as? KClass) != null) JvmClassMappingKt.getJavaClass(ignore as? KClass) else null;
      if (res != null) {
         return res;
      } else if (`$this$declaringClass` is KFunction) {
         try {
            var var13: KClassifier;
            label79: {
               val var11: KParameter = KCallables.getInstanceParameter(`$this$declaringClass`);
               if (var11 != null) {
                  val var12: KType = var11.getType();
                  if (var12 != null) {
                     var13 = var12.getClassifier();
                     break label79;
                  }
               }

               var13 = null;
            }

            val var8: KClass = var13 as? KClass;
            if ((var13 as? KClass) != null) {
               return JvmClassMappingKt.getJavaClass(var8);
            }
         } catch (var7: java.lang.Throwable) {
            ExceptionsKt.checkCritical(var7);
         }

         try {
            val var14: Method = ReflectJvmMapping.getJavaMethod(`$this$declaringClass` as KFunction);
            if (var14 != null) {
               val var15: Class = var14.getDeclaringClass();
               if (var15 != null) {
                  return var15;
               }
            }

            val var16: Constructor = ReflectJvmMapping.getJavaConstructor(`$this$declaringClass` as KFunction);
            val var18: Class = if (var16 != null) var16.getDeclaringClass() else null;
            if (var18 == null) {
               throw new IllegalStateException(("$`$this$declaringClass` is neither a method nor a constructor").toString());
            } else {
               return var18;
            }
         } catch (var6: java.lang.Throwable) {
            ExceptionsKt.checkCritical(var6);
            throw new IllegalStateException(("$`$this$declaringClass` has unknown kind").toString());
         }
      } else if (`$this$declaringClass` !is KProperty) {
         throw new IllegalStateException(("Unknown KCallable type: ${`$this$declaringClass`.getClass()::class}").toString());
      } else {
         val var10000: Method = ReflectJvmMapping.getJavaGetter(`$this$declaringClass` as KProperty);
         if (var10000 != null) {
            val var9: Class = var10000.getDeclaringClass();
            if (var9 != null) {
               return var9;
            }
         }

         val var10: Field = ReflectJvmMapping.getJavaField(`$this$declaringClass` as KProperty);
         val var17: Class = if (var10 != null) var10.getDeclaringClass() else null;
         if (var17 == null) {
            throw new IllegalStateException(("not support $`$this$declaringClass`").toString());
         } else {
            return var17;
         }
      }
   }


public final val jimpleDesc: List<String>
   public final get() {
      var var10000: java.lang.String = `$this$jimpleDesc`.getSignature();
      val var11: java.util.List = AsmUtil.toJimpleDesc(StringsKt.substringAfter$default(var10000, "(", null, 2, null), Optional.fromNullable(null));
      val `$this$map$iv`: java.lang.Iterable = var11;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var11, 10));

      for (Object item$iv$iv : $this$map$iv) {
         val it: Type = `item$iv$iv` as Type;
         var10000 = getTypename(it);
         `destination$iv$iv`.add(var10000);
      }

      return `destination$iv$iv` as MutableList<java.lang.String>;
   }


public final val sootParamStringList: List<String>
   public final get() {
      val sigTypes: java.util.List = CollectionsKt.toMutableList(getJimpleDesc(`$this$sootParamStringList`));
      sigTypes.remove(sigTypes.size() - 1);
      return sigTypes;
   }


public final val sootReturnType: String
   public final get() {
      return CollectionsKt.last(getJimpleDesc(`$this$sootReturnType`)) as java.lang.String;
   }


public final val subSootSignature: String
   public final get() {
      return "${getSootReturnType(`$this$subSootSignature`)} ${`$this$subSootSignature`.getName()}${CollectionsKt.joinToString$default(
         getSootParamStringList(`$this$subSootSignature`), ",", "(", ")", 0, null, null, 56, null
      )}";
   }


public final val sootParamStringList: List<String>
   public final get() {
      val var10000: java.util.List;
      if (`$this$sootParamStringList` is KFunction) {
         if (`$this$sootParamStringList` is CallableReference) {
            var10000 = getSootParamStringList(`$this$sootParamStringList` as CallableReference);
         } else {
            val `$this$map$iv`: java.lang.Iterable = CollectionsKt.drop(
               (`$this$sootParamStringList` as KFunction).getParameters(), if (KCallables.getInstanceParameter(`$this$sootParamStringList`) != null) 1 else 0
            );
            val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

            for (Object item$iv$iv : $this$map$iv) {
               val var12: java.lang.String = ReflectJvmMapping.getJavaType((`item$iv$iv` as KParameter).getType()).getTypeName();
               `destination$iv$iv`.add(StringsKt.substringBefore$default(var12, '<', null, 2, null));
            }

            var10000 = `destination$iv$iv` as java.util.List;
         }
      } else {
         if (`$this$sootParamStringList` !is KProperty) {
            throw new IllegalStateException(("Unknown KCallable type: ${`$this$sootParamStringList`.getClass()::class}").toString());
         }

         val var13: Method = ReflectJvmMapping.getJavaGetter(`$this$sootParamStringList` as KProperty);
         if (var13 == null) {
            throw new IllegalStateException(("Getter for $`$this$sootParamStringList` not found").toString());
         }

         var10000 = getSootParamStringList(var13);
         if (var10000 == null) {
            throw new IllegalStateException(("Getter for $`$this$sootParamStringList` not found").toString());
         }
      }

      return var10000;
   }


public final val sootReturnType: String
   public final get() {
      var var10000: java.lang.String;
      if (`$this$sootReturnType` is KFunction) {
         var10000 = if (`$this$sootReturnType` is CallableReference)
            getSootReturnType(`$this$sootReturnType` as CallableReference)
            else
            getSootTypeName((`$this$sootReturnType` as KFunction).getReturnType());
      } else {
         if (`$this$sootReturnType` !is KProperty) {
            throw new IllegalStateException(("Unknown KCallable type: ${`$this$sootReturnType`.getClass()::class}").toString());
         }

         val var2: Method = ReflectJvmMapping.getJavaGetter(`$this$sootReturnType` as KProperty);
         if (var2 != null) {
            var10000 = getSootReturnType(var2);
            if (var10000 != null) {
               return var10000;
            }
         }

         var10000 = getSootTypeName((`$this$sootReturnType` as KProperty).getGetter().getProperty().getReturnType());
      }

      return var10000;
   }


public final val functionType: KFunctionType
   public final get() {
      return if (Modifier.isStatic(`$this$functionType`.getModifiers())) KFunctionType.StaticMethod else KFunctionType.InstanceMethod;
   }


public final val functionType: KFunctionType
   public final get() {
      return KFunctionType.Constructor;
   }


public final val functionType: KFunctionType
   public final get() {
      val var10000: KFunctionType;
      if (`$this$functionType` is Method) {
         var10000 = getFunctionType(`$this$functionType` as Method);
      } else {
         if (`$this$functionType` !is Constructor) {
            throw new IllegalStateException(("$`$this$functionType` is neither a method nor a constructor").toString());
         }

         var10000 = getFunctionType(`$this$functionType` as Constructor<?>);
      }

      return var10000;
   }


public final val sootName: String
   public final get() {
      val var10000: java.lang.String;
      if (`$this$sootName` is Constructor) {
         var10000 = "<init>";
      } else {
         var10000 = `$this$sootName`.getName();
      }

      return var10000;
   }


private final val sootSubSignature: String
   private final get() {
      val paramTypes: java.lang.String = CollectionsKt.joinToString$default(
         getSootParamStringList(`$this$sootSubSignature`), ",", "(", ")", 0, null, null, 56, null
      );
      val var10000: java.lang.String;
      if (`$this$sootSubSignature` is Constructor) {
         var10000 = "void <init>$paramTypes";
      } else {
         if (`$this$sootSubSignature` !is Method) {
            throw new IllegalStateException(("$`$this$sootSubSignature` is neither a method nor a constructor").toString());
         }

         val var3: Class = (`$this$sootSubSignature` as Method).getReturnType();
         var10000 = "${getSootTypeName(var3)} ${(`$this$sootSubSignature` as Method).getName()}$paramTypes";
      }

      return var10000;
   }


private final val sootReturnType: String
   private final get() {
      val var10000: java.lang.String;
      if (`$this$sootReturnType` is Constructor) {
         var10000 = "void";
      } else {
         if (`$this$sootReturnType` !is Method) {
            throw new IllegalStateException(("$`$this$sootReturnType` is neither a method nor a constructor").toString());
         }

         val var2: Class = (`$this$sootReturnType` as Method).getReturnType();
         var10000 = getSootTypeName(var2);
      }

      return var10000;
   }


public final val sootParamStringList: List<String>
   public final get() {
      val var10000: Array<Class> = `$this$sootParamStringList`.getParameterTypes();
      return getClassesToSootTypeNames(var10000);
   }


public final val sootSignature: String
   public final get() {
      return "<${`$this$sootSignature`.getDeclaringClass().getName()}: ${getSootSubSignature(`$this$sootSignature`)}>";
   }


public final val sootTypeName: String
   public final get() {
      val var10000: java.lang.String = `$this$sootTypeName`.getTypeName();
      return var10000;
   }


public final val sootTypeName: String
   public final get() {
      val var1: KClassifier = `$this$sootTypeName`.getClassifier();
      val var10000: KClass = var1 as? KClass;
      if ((var1 as? KClass) != null) {
         val var2: java.lang.String = getSootTypeName(var10000);
         if (var2 != null) {
            return var2;
         }
      }

      return getSootTypeName(ReflectJvmMapping.getJavaType(`$this$sootTypeName`));
   }


public final val sootTypeName: String
   public final get() {
      return getSootTypeName(JvmClassMappingKt.getJavaClass(`$this$sootTypeName`));
   }


public final val sootTypeName: String
   public final get() {
      if (`$this$sootTypeName`.isArray()) {
         try {
            var e: Class = `$this$sootTypeName`;

            var dimensions: Int;
            for (dimensions = 0; cl.isArray(); cl = cl.getComponentType()) {
               dimensions++;
            }

            val sb: StringBuilder = new StringBuilder();
            sb.append(e.getName());
            var i: Int = 0;

            for (int var5 = dimensions; i < var5; i++) {
               sb.append("[]");
            }

            val var7: java.lang.String = sb.toString();
            return var7;
         } catch (var6: java.lang.Throwable) {
            ExceptionsKt.checkCritical(var6);
         }
      }

      val var10000: java.lang.String = `$this$sootTypeName`.getName();
      return var10000;
   }


public final val classesToSootTypeNames: List<String>
   public final get() {
      val var1: Int = `$this$classesToSootTypeNames`.length;
      val var2: ArrayList = new ArrayList(`$this$classesToSootTypeNames`.length);

      for (int var3 = 0; var3 < var1; var3++) {
         var2.add(getSootTypeName(`$this$classesToSootTypeNames`[var3]));
      }

      return var2;
   }


public final val functionType: KFunctionType
   public final get() {
      if (`$this$functionType` is KFunction) {
         try {
            if ((`$this$functionType` as KFunction).getName() == "<init>") {
               return KFunctionType.Constructor;
            } else {
               return if (KCallables.getInstanceParameter(`$this$functionType`) == null) KFunctionType.StaticMethod else KFunctionType.InstanceMethod;
            }
         } catch (var16: java.lang.Throwable) {
            ExceptionsKt.checkCritical(var16);

            try {
               val var33: Method = ReflectJvmMapping.getJavaMethod(`$this$functionType` as KFunction);
               if (var33 != null) {
                  val var34: KFunctionType = getFunctionType(var33);
                  if (var34 != null) {
                     return var34;
                  }
               }

               val var35: Constructor = ReflectJvmMapping.getJavaConstructor(`$this$functionType` as KFunction);
               if (var35 == null) {
                  throw new IllegalStateException(("$`$this$functionType` is neither a method nor a constructor").toString());
               } else {
                  return getFunctionType(var35);
               }
            } catch (var15: java.lang.Throwable) {
               ExceptionsKt.checkCritical(var15);
               val callable: CallableReference = `$this$functionType` as CallableReference;
               val found: KDeclarationContainer = (`$this$functionType` as CallableReference).getOwner();
               var var27: KClass = found as? KClass;
               if ((found as? KClass) != null) {
                  val var28: Class = JvmClassMappingKt.getJavaClass(var27);
                  if (var28 != null) {
                     val sig: java.lang.String = getSubSootSignature(callable);
                     val var29: Array<Method> = var28.getDeclaredMethods();
                     var `$this$firstOrNull$iv`: Array<Any> = var29;
                     var var10: Int = 0;
                     var var11: Int = `$this$firstOrNull$iv`.length;

                     while (true) {
                        if (var10 >= var11) {
                           var27 = null;
                           break;
                        }

                        val `element$iv`: Any = `$this$firstOrNull$iv`[var10];
                        val m: Method = `$this$firstOrNull$iv`[var10] as Method;
                        if (getSootSubSignature(m) == sig) {
                           var27 = (KClass)`element$iv`;
                           break;
                        }

                        var10++;
                     }

                     val var17: Executable = var27 as Executable;
                     if (var27 as Executable != null) {
                        return getFunctionType(var17);
                     }

                     val var31: Array<Constructor> = var28.getConstructors();
                     `$this$firstOrNull$iv` = var31;
                     var10 = 0;
                     var11 = `$this$firstOrNull$iv`.length;

                     while (true) {
                        if (var10 >= var11) {
                           var27 = null;
                           break;
                        }

                        val var23: Any = `$this$firstOrNull$iv`[var10];
                        val var24: Constructor = `$this$firstOrNull$iv`[var10] as Constructor;
                        if (getSootSubSignature(var24) == sig) {
                           var27 = (KClass)var23;
                           break;
                        }

                        var10++;
                     }

                     val var18: Executable = var27 as Executable;
                     if (var27 as Executable != null) {
                        return getFunctionType(var18 as Constructor<?>);
                     }

                     throw new IllegalStateException(("not found: $var28 $sig").toString());
                  }
               }

               throw new IllegalStateException(("$`$this$functionType` has unknown kind").toString());
            }
         }
      } else if (`$this$functionType` is KProperty) {
         val var10000: Method = ReflectJvmMapping.getJavaGetter(`$this$functionType` as KProperty);
         if (var10000 != null) {
            val var26: KFunctionType = getFunctionType(var10000);
            if (var26 != null) {
               return var26;
            }
         }

         throw new IllegalStateException(("Getter for $`$this$functionType` not found").toString());
      } else {
         throw new IllegalStateException(("Unknown KCallable type: ${`$this$functionType`.getClass()::class}").toString());
      }
   }


public final val paramSignature: String
   public final get() {
      return CollectionsKt.joinToString$default(getSootParamStringList(`$this$paramSignature`), ",", "(", ")", 0, null, null, 56, null);
   }


public final val sootSubSignature: String
   public final get() {
      val var10000: java.lang.String;
      if (`$this$sootSubSignature` is KFunction) {
         var10000 = "${getSootReturnType(`$this$sootSubSignature`)} ${(`$this$sootSubSignature` as KFunction).getName()}${getParamSignature(
            `$this$sootSubSignature`
         )}";
      } else {
         if (`$this$sootSubSignature` !is KProperty) {
            throw new IllegalStateException(("Unknown KCallable type: ${`$this$sootSubSignature`.getClass()::class}").toString());
         }

         var10000 = "${getSootReturnType(`$this$sootSubSignature`)} ${(`$this$sootSubSignature` as KProperty).getName()}";
      }

      return var10000;
   }


public final val declaringClassName: String
   public final get() {
      return getSootTypeName(getDeclaringClass(`$this$declaringClassName`));
   }


public final val sootTypeName: String
   public final get() {
      val var10000: CallableReference = `$this$sootTypeName` as? CallableReference;
      if ((`$this$sootTypeName` as? CallableReference) != null) {
         val var1: java.util.List = getJimpleDesc(var10000);
         if (var1 != null) {
            val var2: java.lang.String = CollectionsKt.lastOrNull(var1) as java.lang.String;
            if (var2 != null) {
               return var2;
            }
         }
      }

      val var3: Method = ReflectJvmMapping.getJavaGetter(`$this$sootTypeName`);
      if (var3 != null) {
         val var4: Class = var3.getReturnType();
         if (var4 != null) {
            return getSootTypeName(var4);
         }
      }

      label25: {
         val var5: Field = ReflectJvmMapping.getJavaField(`$this$sootTypeName`);
         if (var5 != null) {
            val var6: Class = var5.getType();
            if (var6 != null) {
               var7 = getSootTypeName(var6);
               break label25;
            }
         }

         var7 = null;
      }

      if (var7 == null) {
         throw new IllegalStateException(("not support $`$this$sootTypeName`").toString());
      } else {
         return var7;
      }
   }


public final val sootSignature: String
   public final get() {
      return "<${getDeclaringClassName(`$this$sootSignature`)}: ${getSootSubSignature(`$this$sootSignature`)}>";
   }


public final val callable: KCallable<R>
   public final get() {
      return `$this$callable` as KCallable<R>;
   }


public final val superClasses: Sequence<SootClass>
   public final get() {
      return SequencesKt.generateSequence(`$this$superClasses`, UtilsKt::_get_superClasses_$lambda$8);
   }


public final val superInterfaces: Sequence<SootClass>
   public final get() {
      return SequencesKt.distinct(SequencesKt.flatMapIterable(getSuperClasses(`$this$superInterfaces`), UtilsKt::_get_superInterfaces_$lambda$10));
   }


public final val visibilityAnnotationTag: VisibilityAnnotationTag?
   public final get() {
      return `$this$visibilityAnnotationTag`.getTag("VisibilityAnnotationTag") as VisibilityAnnotationTag;
   }


public final val typename: String?
   public final get() {
      return getTypename(`$this$typename`, false);
   }


public final val activeBodyOrNull: Body?
   public final get() {
      return if (`$this$activeBodyOrNull`.hasActiveBody()) `$this$activeBodyOrNull`.getActiveBody() else null;
   }


public final val possibleTypes: Set<Type>
   public final get() {
      if (`$this$possibleTypes` is Constant) {
         return SetsKt.setOf((`$this$possibleTypes` as Constant).getType());
      } else if (`$this$possibleTypes` !is Local) {
         return SetsKt.emptySet();
      } else if (!Scene.v().hasPointsToAnalysis()) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         var var10000: java.util.Set = Scene.v().getPointsToAnalysis().reachingObjects(`$this$possibleTypes` as Local).possibleTypes();
         if (var10000 == null) {
            var10000 = SetsKt.emptySet();
         }

         return var10000;
      }
   }


public fun findAncestors(sc: SootClass): List<SootClass> {
   val superClasses: java.util.List = new ArrayList();
   val superInterfaces: java.util.List = new ArrayList();
   if (sc.isInterface()) {
      val var5: java.util.Collection = superInterfaces;
      val var10000: java.util.List = Scene.v().getActiveHierarchy().getSuperinterfacesOfIncluding(sc);
      CollectionsKt.addAll(var5, var10000);
   } else {
      var var15: java.util.Collection = superClasses;
      var var28: java.util.List = Scene.v().getActiveHierarchy().getSuperclassesOfIncluding(sc);
      CollectionsKt.addAll(var15, var28);
      var15 = superInterfaces;
      var `$this$flatMap$iv`: java.lang.Iterable = superClasses;
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         val var29: Chain = (`element$iv$iv` as SootClass).getInterfaces();
         CollectionsKt.addAll(`destination$iv$iv`, var29 as java.lang.Iterable);
      }

      `$this$flatMap$iv` = `destination$iv$iv` as java.util.List;
      `destination$iv$iv` = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         var28 = Scene.v().getActiveHierarchy().getSuperinterfacesOfIncluding(var23 as SootClass);
         CollectionsKt.addAll(`destination$iv$iv`, var28);
      }

      CollectionsKt.addAll(var15, `destination$iv$iv` as java.util.List);
   }

   return CollectionsKt.plus(superClasses, superInterfaces);
}

public fun SootClass.superClassOrNull(): SootClass? {
   return if (`$this$superClassOrNull`.hasSuperclass()) `$this$superClassOrNull`.getSuperclass() else null;
}

public fun level(level: Level): org.apache.logging.log4j.Level {
   return org.apache.logging.log4j.Level.toLevel(level.name());
}

public fun Type.getTypename(short: Boolean = false): String? {
   var var7: java.lang.String;
   if (`$this$getTypename` is RefType) {
      if (var1) {
         var7 = (`$this$getTypename` as RefType).getClassName();
         var7 = StringsKt.substringAfterLast$default(var7, ".", null, 2, null);
      } else {
         var7 = (`$this$getTypename` as RefType).getClassName();
      }
   } else if (`$this$getTypename` is LongType) {
      var7 = "long";
   } else if (`$this$getTypename` is ShortType) {
      var7 = "short";
   } else if (`$this$getTypename` is DoubleType) {
      var7 = "double";
   } else if (`$this$getTypename` is IntType) {
      var7 = "int";
   } else if (`$this$getTypename` is FloatType) {
      var7 = "float";
   } else if (`$this$getTypename` is ByteType) {
      var7 = "byte";
   } else if (`$this$getTypename` is CharType) {
      var7 = "char";
   } else if (`$this$getTypename` is VoidType) {
      var7 = "void";
   } else if (`$this$getTypename` is BooleanType) {
      var7 = "boolean";
   } else {
      if (`$this$getTypename` is ArrayType) {
         val buffer: StringBuilder = new StringBuilder();
         val var5: Type = (`$this$getTypename` as ArrayType).baseType;
         val var10001: java.lang.String = getTypename(var5);
         if (var10001 == null) {
            return null;
         }

         buffer.append(var10001);
         var i: Int = 0;

         for (int var6 = ((ArrayType)$this$getTypename).numDimensions; i < var6; i++) {
            buffer.append("[]");
         }

         return buffer.toString();
      }

      var7 = null;
   }

   return var7;
}

@JvmSynthetic
fun `getTypename$default`(var0: Type, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return getTypename(var0, var1);
}

public fun SootClass.adjustLevel(level: Int) {
   if (`$this$adjustLevel`.resolvingLevel() < level) {
      `$this$adjustLevel`.setResolvingLevel(level);
   }
}

public fun SootClass.resolveCallsDispatchChainBase(subSignature: String, predicate: (SootMethod) -> Boolean): Sequence<SootMethod> {
   adjustLevel(`$this$resolveCallsDispatchChainBase`, 2);
   return SequencesKt.filter(
      SequencesKt.flatMapIterable(
         SequencesKt.plus(
            SequencesKt.generateSequence(`$this$resolveCallsDispatchChainBase`, UtilsKt::resolveCallsDispatchChainBase$lambda$11),
            SequencesKt.distinct(
               SequencesKt.flatMapIterable(
                  SequencesKt.generateSequence(`$this$resolveCallsDispatchChainBase`, UtilsKt::resolveCallsDispatchChainBase$lambda$12),
                  UtilsKt::resolveCallsDispatchChainBase$lambda$14
               )
            )
         ),
         UtilsKt::resolveCallsDispatchChainBase$lambda$15
      ),
      UtilsKt::resolveCallsDispatchChainBase$lambda$16
   );
}

public fun SootClass.resolveCallsDispatchChain(subSignature: String, predicate: ((SootMethod) -> Boolean)? = null): Sequence<SootMethod> {
   return resolveCallsDispatchChainBase(`$this$resolveCallsDispatchChain`, subSignature, UtilsKt::resolveCallsDispatchChain$lambda$17);
}

@JvmSynthetic
fun `resolveCallsDispatchChain$default`(var0: SootClass, var1: java.lang.String, var2: Function1, var3: Int, var4: Any): Sequence {
   if ((var3 and 2) != 0) {
      var2 = null;
   }

   return resolveCallsDispatchChain(var0, var1, var2);
}

public fun SootMethod.getParameterNameByIndex(index: Int): String? {
   if (index == -1) {
      return "${`$this$getParameterNameByIndex`.getDeclaringClass().getShortName()}.this";
   } else if (0 > index || index >= `$this$getParameterNameByIndex`.getParameterCount()) {
      return null;
   } else {
      var var10000: java.util.List = `$this$getParameterNameByIndex`.getTags();
      val `$this$filterIsInstance$iv`: java.lang.Iterable = var10000;
      val it: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filterIsInstance$iv) {
         if (`element$iv$iv` is ParamNamesTag) {
            it.add(`element$iv$iv`);
         }
      }

      label54: {
         val var15: ParamNamesTag = CollectionsKt.firstOrNull(it as java.util.List) as ParamNamesTag;
         if (var15 != null) {
            var10000 = var15.getNames();
            if (var10000 != null) {
               break label54;
            }
         }

         var10000 = CollectionsKt.emptyList();
      }

      var var17: java.lang.String = CollectionsKt.getOrNull(var10000, index) as java.lang.String;
      if (var17 == null) {
         try {
            label44: {
               val var18: Body = getActiveBodyOrNull(`$this$getParameterNameByIndex`);
               if (var18 != null) {
                  val var19: Local = var18.getParameterLocal(index);
                  if (var19 != null) {
                     var20 = java.lang.String.valueOf(var19);
                     break label44;
                  }
               }

               var20 = null;
            }

            var12 = var20;
         } catch (var11: RuntimeException) {
            var12 = null;
         }

         var17 = var12;
      }

      return var17;
   }
}

public fun SootMethod.getSignatureWithOriginalName(shortParamType: Boolean = false): String {
   val var10002: java.lang.String = `$this$getSignatureWithOriginalName`.getName();
   val var10003: java.util.List = `$this$getSignatureWithOriginalName`.getParameterTypes();
   val var10004: Type = `$this$getSignatureWithOriginalName`.getReturnType();
   return "${`$this$getSignatureWithOriginalName`.getDeclaringClass().getName()}: ${getSignatureWithOriginalName$getSubSignatureImplInternal(
      `$this$getSignatureWithOriginalName`, shortParamType, var10002, var10003, var10004
   )}";
}

@JvmSynthetic
fun `getSignatureWithOriginalName$default`(var0: SootMethod, var1: Boolean, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return getSignatureWithOriginalName(var0, var1);
}

fun `_get_superClasses_$lambda$8`(it: SootClass): SootClass {
   return superClassOrNull(it);
}

fun `_get_superInterfaces_$lambda$10`(sootClass: SootClass): java.lang.Iterable {
   val var10000: Chain = sootClass.getInterfaces();
   val `$this$flatMap$iv`: java.lang.Iterable = var10000 as java.lang.Iterable;
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val `list$iv$iv`: SootClass = `element$iv$iv` as SootClass;
      CollectionsKt.addAll(`destination$iv$iv`, findAncestors(`list$iv$iv`));
   }

   return `destination$iv$iv` as java.util.List;
}

fun `resolveCallsDispatchChainBase$lambda$11`(it: SootClass): SootClass {
   return superClassOrNull(it);
}

fun `resolveCallsDispatchChainBase$lambda$12`(it: SootClass): SootClass {
   return superClassOrNull(it);
}

fun `resolveCallsDispatchChainBase$lambda$14`(sootClass: SootClass): java.lang.Iterable {
   val var10000: Chain = sootClass.getInterfaces();
   val `$this$flatMap$iv`: java.lang.Iterable = var10000 as java.lang.Iterable;
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val `list$iv$iv`: SootClass = `element$iv$iv` as SootClass;
      CollectionsKt.addAll(`destination$iv$iv`, findAncestors(`list$iv$iv`));
   }

   return `destination$iv$iv` as java.util.List;
}

fun `resolveCallsDispatchChainBase$lambda$15`(it: SootClass): java.lang.Iterable {
   val var10000: java.util.List = it.getMethods();
   return var10000;
}

fun `resolveCallsDispatchChainBase$lambda$16`(`$params`: java.lang.String, `$predicate`: Function1, it: SootMethod): Boolean {
   val var10000: java.lang.String = it.getSubSignature();
   if (!(StringsKt.substringAfter$default(var10000, " ", null, 2, null) == `$params`)) {
      return false;
   } else {
      return `$predicate`.invoke(it) as java.lang.Boolean;
   }
}

fun `resolveCallsDispatchChain$lambda$17`(`$this_resolveCallsDispatchChain`: SootClass, `$predicate`: Function1, it: SootMethod): Boolean {
   if (!(`$this_resolveCallsDispatchChain` == it.getDeclaringClass())) {
      if (it.isConstructor()) {
         return false;
      }

      if (it.isStatic() || it.isStaticInitializer()) {
         return false;
      }

      if (it.isPrivate()) {
         return false;
      }
   }

   return `$predicate` == null || `$predicate`.invoke(it) as java.lang.Boolean;
}

fun `getSignatureWithOriginalName$getSubSignatureImplInternal$lambda$20`(
   `$this_getSignatureWithOriginalName`: SootMethod, `$shortParamType`: Boolean, var2: IndexedValue
): java.lang.CharSequence {
   var it: Type;
   var var8: java.lang.String;
   label11: {
      it = var2.component2() as Type;
      var8 = getParameterNameByIndex(`$this_getSignatureWithOriginalName`, var2.component1());
      if (var8 != null) {
         var8 = " $var8";
         if (var8 != null) {
            break label11;
         }
      }

      var8 = "";
   }

   return "${getTypename(it, `$shortParamType`)}$var8";
}

fun `getSignatureWithOriginalName$getSubSignatureImplInternal`(
   `$this_getSignatureWithOriginalName`: SootMethod, `$shortParamType`: Boolean, name: java.lang.String, params: MutableList<Type>, returnType: Type
): java.lang.String {
   val buffer: StringBuilder = new StringBuilder();
   buffer.append(returnType.toQuotedString());
   buffer.append(' ');
   buffer.append(Scene.v().quotedNameOf(name));
   buffer.append('(');
   if (params != null) {
      buffer.append(
         CollectionsKt.joinToString$default(
            CollectionsKt.withIndex(params), ",", null, null, 0, null, UtilsKt::getSignatureWithOriginalName$getSubSignatureImplInternal$lambda$20, 30, null
         )
      );
   }

   buffer.append(')');
   val var10000: java.lang.String = buffer.toString();
   return var10000;
}
