package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.github.javaparser.Range
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.Node.TreeTraversal
import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.github.javaparser.ast.expr.LambdaExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import java.util.ArrayList
import java.util.Optional
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.optionals.OptionalsKt
import soot.SootClass
import soot.SootMethod
import soot.tagkit.Host

@SourceDebugExtension(["SMAP\nSootHostExtend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootMethodExtend\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n774#2:392\n865#2,2:393\n1053#2:395\n774#2:396\n865#2,2:397\n774#2:399\n865#2,2:400\n1971#2,14:402\n774#2:417\n865#2,2:418\n1053#2:420\n1#3:416\n*S KotlinDebug\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootMethodExtend\n*L\n80#1:392\n80#1:393,2\n81#1:395\n90#1:396\n90#1:397,2\n111#1:399\n111#1:400,2\n112#1:402,14\n122#1:417\n122#1:418,2\n123#1:420\n*E\n"])
public data class SootMethodExtend(host: SootMethod, cu: CompilationUnit) : SootHostExtend(host as Host, cu) {
   public open val host: SootMethod
   public open val cu: CompilationUnit

   public final val classDecl: Node?
      public final get() {
         return this.classDecl$delegate.getValue() as Node;
      }


   public final val sootRange: Pair<Int, Int>?
      public final get() {
         return this.sootRange$delegate.getValue() as Pair<Integer, Integer>;
      }


   public open val decl: CallableDeclaration<*>?
      public open get() {
         return this.decl$delegate.getValue() as CallableDeclaration<?>;
      }


   public final val lambdaExpr: LambdaExpr?
      public final get() {
         return this.lambdaExpr$delegate.getValue() as LambdaExpr;
      }


   public open val nameDecl: NodeWithRange<Node>?
      public open get() {
         val var10000: CallableDeclaration = this.getDecl();
         return (if (var10000 != null) var10000.getName() else null) as NodeWithRange<Node>;
      }


   init {
      this.host = host;
      this.cu = cu;
      this.classDecl$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, SootMethodExtend::classDecl_delegate$lambda$1);
      this.sootRange$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, SootMethodExtend::sootRange_delegate$lambda$2);
      this.decl$delegate = LazyKt.lazy(SootMethodExtend::decl_delegate$lambda$3);
      this.lambdaExpr$delegate = LazyKt.lazy(SootMethodExtend::lambdaExpr_delegate$lambda$12);
   }

   public fun <T : NodeWithRange<N>, N> nodeMatcher(node: T): Boolean {
      val var10000: Pair = this.getSootRange();
      if (var10000 == null) {
         return false;
      } else {
         val var4: Optional = node.getBegin();
         val var5: Int = SootHostExtendKt.getLine(var4);
         if (var5 == null) {
            return false;
         } else {
            val var6: Int = var5;
            val var10001: Optional = node.getEnd();
            val var7: Int = SootHostExtendKt.getLine(var10001);
            if (var7 != null) {
               val declRange: Pair = TuplesKt.to(var6, var7);
               if ((declRange.getFirst() as java.lang.Number).intValue() > (declRange.getSecond() as java.lang.Number).intValue()) {
                  return false;
               } else {
                  return (declRange.getSecond() as java.lang.Number).intValue() >= (var10000.getFirst() as java.lang.Number).intValue()
                     && (var10000.getSecond() as java.lang.Number).intValue() >= (declRange.getFirst() as java.lang.Number).intValue();
               }
            } else {
               return false;
            }
         }
      }
   }

   private fun getMethods(methods: List<CallableDeclaration<*>>): CallableDeclaration<*>? {
      val `$this$sortedBy$iv`: java.lang.Iterable = methods;
      val `$this$filter$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if ((`destination$iv$iv` as CallableDeclaration).getNameAsString().equals(SootHostExtendKt.getDeclaredName(this.getHost()))) {
            `$this$filter$iv`.add(`destination$iv$iv`);
         }
      }

      val var17: java.util.List = CollectionsKt.sortedWith(`$this$filter$iv` as java.util.List, new SootMethodExtend$getMethods$$inlined$sortedBy$1());
      val var10000: CallableDeclaration;
      if (var17.isEmpty()) {
         var10000 = null;
      } else if (var17.size() == 1) {
         var10000 = CollectionsKt.first(var17) as CallableDeclaration;
      } else if (this.getSootRange() == null) {
         var10000 = CollectionsKt.first(var17) as CallableDeclaration;
      } else {
         val `$this$filter$ivx`: java.lang.Iterable = var17;
         val `destination$iv$ivx`: java.util.Collection = new ArrayList();

         for (Object element$iv$ivx : $this$filter$ivx) {
            if (this.nodeMatcher((`element$iv$ivx` as CallableDeclaration) as NodeWithRange)) {
               `destination$iv$ivx`.add(`element$iv$ivx`);
            }
         }

         var10000 = if ((`destination$iv$ivx` as java.util.List).isEmpty())
            CollectionsKt.first(var17) as CallableDeclaration
            else
            CollectionsKt.first(`destination$iv$ivx` as java.util.List) as CallableDeclaration;
      }

      return var10000;
   }

   private fun getNormalMethods(classDecl: TypeDeclaration<*>): CallableDeclaration<*>? {
      val var10000: java.util.List = classDecl.getConstructors();
      val var3: java.util.Collection = var10000;
      val var10001: java.util.List = classDecl.getMethods();
      return this.getMethods(CollectionsKt.plus(var3, var10001));
   }

   private fun getMethodsOfAnonymousClass(classDecl: ObjectCreationExpr): CallableDeclaration<*>? {
      val var10000: java.util.List = classDecl.findAll(CallableDeclaration.class, TreeTraversal.DIRECT_CHILDREN);
      return this.getMethods(CollectionsKt.filterNotNull(var10000));
   }

   private fun getLambdaExprs(allLambdaExpr: List<LambdaExpr>): LambdaExpr? {
      val `$this$maxByOrNull$iv`: java.lang.Iterable = allLambdaExpr;
      val `maxElem$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$maxByOrNull$iv) {
         if (this.nodeMatcher((`v$iv` as LambdaExpr) as NodeWithRange)) {
            `maxElem$iv`.add(`v$iv`);
         }
      }

      val `iterator$iv`: java.util.Iterator = (`maxElem$iv` as java.util.List).iterator();
      var var10000: Any;
      if (!`iterator$iv`.hasNext()) {
         var10000 = null;
      } else {
         var var14: Any = `iterator$iv`.next();
         if (!`iterator$iv`.hasNext()) {
            var10000 = (Range)var14;
         } else {
            var10000 = (var14 as LambdaExpr).getRange();
            var10000 = OptionalsKt.getOrNull((Optional)var10000) as Range;
            var var16: Int = if (var10000 != null && var10000.begin != null) var10000.begin.line else -1;

            do {
               val var18: Any = `iterator$iv`.next();
               val var26: Optional = (var18 as LambdaExpr).getRange();
               var10000 = OptionalsKt.getOrNull(var26) as Range;
               val var20: Int = if (var10000 != null && var10000.begin != null) var10000.begin.line else -1;
               if (var16 < (if (var10000 != null && var10000.begin != null) var10000.begin.line else -1)) {
                  var14 = var18;
                  var16 = var20;
               }
            } while (iterator$iv.hasNext());

            var10000 = (Range)var14;
         }
      }

      return var10000 as LambdaExpr;
   }

   public operator fun component1(): SootMethod {
      return this.host;
   }

   public operator fun component2(): CompilationUnit {
      return this.cu;
   }

   public fun copy(host: SootMethod = this.host, cu: CompilationUnit = this.cu): SootMethodExtend {
      return new SootMethodExtend(host, cu);
   }

   public override fun toString(): String {
      return "SootMethodExtend(host=${this.host}, cu=${this.cu})";
   }

   public override fun hashCode(): Int {
      return this.host.hashCode() * 31 + this.cu.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootMethodExtend) {
         return false;
      } else {
         val var2: SootMethodExtend = other as SootMethodExtend;
         if (!(this.host == (other as SootMethodExtend).host)) {
            return false;
         } else {
            return this.cu == var2.cu;
         }
      }
   }

   @JvmStatic
   fun `classDecl_delegate$lambda$1`(`this$0`: SootMethodExtend): Node {
      val `$this$classDecl_delegate_u24lambda_u241_u24lambda_u240`: CompilationUnit = `this$0`.getCu();
      val var10001: SootClass = `this$0`.getHost().getDeclaringClass();
      return SootHostExtendKt.getDecl(`$this$classDecl_delegate_u24lambda_u241_u24lambda_u240`, var10001);
   }

   @JvmStatic
   fun `sootRange_delegate$lambda$2`(`this$0`: SootMethodExtend): Pair {
      return AnalysisCache.G.INSTANCE.get(new SootRangeKey(`this$0`.getHost()));
   }

   @JvmStatic
   fun `decl_delegate$lambda$3`(`this$0`: SootMethodExtend): CallableDeclaration {
      val var10000: Node = `this$0`.getClassDecl();
      if (var10000 == null) {
         return null;
      } else {
         return if (var10000 is TypeDeclaration)
            `this$0`.getNormalMethods(var10000 as TypeDeclaration<?>)
            else
            (if (var10000 is ObjectCreationExpr) `this$0`.getMethodsOfAnonymousClass(var10000 as ObjectCreationExpr) else null);
      }
   }

   @JvmStatic
   fun `lambdaExpr_delegate$lambda$12`(`this$0`: SootMethodExtend): LambdaExpr {
      val var10000: java.lang.String = `this$0`.getHost().getName();
      if (!StringsKt.startsWith$default(var10000, "lambda$", false, 2, null)) {
         return null;
      } else if (`this$0`.getSootRange() != null) {
         val var16: Node = `this$0`.getClassDecl();
         if (var16 == null) {
            return null;
         } else {
            val classDecl: Node = var16;
            val var2: java.util.List = var16.findAll(LambdaExpr.class);
            if (var2 != null) {
               val `$this$filter$iv`: java.lang.Iterable = var2;
               val var6: java.util.Collection = new ArrayList();

               for (Object element$iv$iv : $this$filter$iv) {
                  val var10001: Optional = (`element$iv$iv` as LambdaExpr).findAncestor(new Class[]{classDecl.getClass()});
                  if (classDecl == OptionalsKt.getOrNull(var10001)) {
                     var6.add(`element$iv$iv`);
                  }
               }

               val var13: java.util.List = CollectionsKt.sortedWith(
                  var6 as java.util.List, new SootMethodExtend$lambdaExpr_delegate$lambda$12$$inlined$sortedBy$1()
               );
               if (var13 != null) {
                  return `this$0`.getLambdaExprs(var13);
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }
}
