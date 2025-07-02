package com.feysh.corax.cache.analysis

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.TypeDeclaration
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import kotlin.jvm.internal.SourceDebugExtension
import soot.SootClass
import soot.tagkit.Host

@SourceDebugExtension(["SMAP\nSootHostExtend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootClassExtend\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n1#2:392\n*E\n"])
public data class SootClassExtend(host: SootClass, cu: CompilationUnit) : SootHostExtend(host as Host, cu) {
   public open val host: SootClass
   public open val cu: CompilationUnit

   public open val decl: Node?
      public open get() {
         return this.decl$delegate.getValue() as Node;
      }


   public open val nameDecl: NodeWithRange<Node>?
      public open get() {
         val var10000: Node = this.getDecl();
         if (var10000 == null) {
            return null;
         } else {
            return if (var10000 is TypeDeclaration)
               (var10000 as TypeDeclaration).getName() as NodeWithRange
               else
               ((var10000 as ObjectCreationExpr).getType().getName() as? NodeWithRange);
         }
      }


   init {
      this.host = host;
      this.cu = cu;
      this.decl$delegate = LazyKt.lazy(SootClassExtend::decl_delegate$lambda$1);
   }

   public operator fun component1(): SootClass {
      return this.host;
   }

   public operator fun component2(): CompilationUnit {
      return this.cu;
   }

   public fun copy(host: SootClass = this.host, cu: CompilationUnit = this.cu): SootClassExtend {
      return new SootClassExtend(host, cu);
   }

   public override fun toString(): String {
      return "SootClassExtend(host=${this.host}, cu=${this.cu})";
   }

   public override fun hashCode(): Int {
      return this.host.hashCode() * 31 + this.cu.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is SootClassExtend) {
         return false;
      } else {
         val var2: SootClassExtend = other as SootClassExtend;
         if (!(this.host == (other as SootClassExtend).host)) {
            return false;
         } else {
            return this.cu == var2.cu;
         }
      }
   }

   @JvmStatic
   fun `decl_delegate$lambda$1`(`this$0`: SootClassExtend): Node {
      return SootHostExtendKt.getDecl(`this$0`.getCu(), `this$0`.getHost());
   }
}
