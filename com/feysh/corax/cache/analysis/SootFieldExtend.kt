package com.feysh.corax.cache.analysis

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.Node.TreeTraversal
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import java.util.Optional
import java.util.function.Predicate
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.optionals.OptionalsKt
import soot.SootClass
import soot.SootField
import soot.tagkit.Host

@SourceDebugExtension(["SMAP\nSootHostExtend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootFieldExtend\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n1#2:392\n*E\n"])
public data class SootFieldExtend(host: SootField, cu: CompilationUnit) : SootHostExtend(host as Host, cu) {
   public open val host: SootField
   public open val cu: CompilationUnit

   public final val classDecl: Node?
      public final get() {
         return this.classDecl$delegate.getValue() as Node;
      }


   public open val decl: FieldDeclaration?
      public open get() {
         return this.decl$delegate.getValue() as FieldDeclaration;
      }


   public open val nameDecl: NodeWithRange<Node>?
      public open get() {
         val var10000: FieldDeclaration = this.getDecl();
         return (if (var10000 != null) var10000.getElementType() else null) as NodeWithRange<Node>;
      }


   init {
      this.host = host;
      this.cu = cu;
      this.classDecl$delegate = LazyKt.lazy(SootFieldExtend::classDecl_delegate$lambda$1);
      this.decl$delegate = LazyKt.lazy(SootFieldExtend::decl_delegate$lambda$2);
   }

   private fun getFields(fields: List<FieldDeclaration>): FieldDeclaration? {
      val var3: Optional = fields.stream().filter(new Predicate(SootFieldExtend::getFields$lambda$4) {
         {
            this.function = function;
         }
      }).findFirst();
      return OptionalsKt.getOrNull(var3) as FieldDeclaration;
   }

   private fun getNormalFields(classDecl: TypeDeclaration<*>): FieldDeclaration? {
      val var10001: java.util.List = classDecl.getFields();
      return this.getFields(var10001);
   }

   private fun getFieldsOfAnonymousClass(classDecl: ObjectCreationExpr): FieldDeclaration? {
      val var10001: java.util.List = classDecl.findAll(FieldDeclaration.class, TreeTraversal.DIRECT_CHILDREN);
      return this.getFields(CollectionsKt.filterNotNull(var10001));
   }

   public operator fun component1(): SootField {
      return this.host;
   }

   public operator fun component2(): CompilationUnit {
      return this.cu;
   }

   public fun copy(host: SootField = this.host, cu: CompilationUnit = this.cu): SootFieldExtend {
      return new SootFieldExtend(host, cu);
   }

   public override fun toString(): String {
      return "SootFieldExtend(host=${this.host}, cu=${this.cu})";
   }

   public override fun hashCode(): Int {
      return this.host.hashCode() * 31 + this.cu.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootFieldExtend) {
         return false;
      } else {
         val var2: SootFieldExtend = other as SootFieldExtend;
         if (!(this.host == (other as SootFieldExtend).host)) {
            return false;
         } else {
            return this.cu == var2.cu;
         }
      }
   }

   @JvmStatic
   fun `classDecl_delegate$lambda$1`(`this$0`: SootFieldExtend): Node {
      val `$this$classDecl_delegate_u24lambda_u241_u24lambda_u240`: CompilationUnit = `this$0`.getCu();
      val var10001: SootClass = `this$0`.getHost().getDeclaringClass();
      return SootHostExtendKt.getDecl(`$this$classDecl_delegate_u24lambda_u241_u24lambda_u240`, var10001);
   }

   @JvmStatic
   fun `decl_delegate$lambda$2`(`this$0`: SootFieldExtend): FieldDeclaration {
      val var10000: Node = `this$0`.getClassDecl();
      if (var10000 == null) {
         return null;
      } else {
         return if (var10000 is TypeDeclaration)
            `this$0`.getNormalFields(var10000 as TypeDeclaration<?>)
            else
            (if (var10000 is ObjectCreationExpr) `this$0`.getFieldsOfAnonymousClass(var10000 as ObjectCreationExpr) else null);
      }
   }

   @JvmStatic
   fun `getFields$lambda$4$lambda$3`(`this$0`: SootFieldExtend, v: VariableDeclarator): Boolean {
      return v.getNameAsString().equals(`this$0`.getHost().getName());
   }

   @JvmStatic
   fun `getFields$lambda$4`(`this$0`: SootFieldExtend, f: FieldDeclaration): Boolean {
      return f.getVariables().stream().anyMatch(new Predicate(SootFieldExtend::getFields$lambda$4$lambda$3) {
         {
            this.function = function;
         }
      });
   }
}
