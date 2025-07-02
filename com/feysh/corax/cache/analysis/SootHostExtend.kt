package com.feysh.corax.cache.analysis

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import java.util.Optional
import kotlin.jvm.internal.SourceDebugExtension
import soot.tagkit.Host
import soot.tagkit.Tag

@SourceDebugExtension(["SMAP\nSootHostExtend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootHostExtend\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n1#2:392\n*E\n"])
public sealed class SootHostExtend protected constructor(host: Host, cu: CompilationUnit) : Host {
   public open val host: Host
   public open val cu: CompilationUnit
   public abstract val decl: NodeWithRange<Node>?
   public abstract val nameDecl: NodeWithRange<Node>?

   public final val javaNameSourceStartLineNumber: Int?
      public final get() {
         val var10000: NodeWithRange = this.getNameDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getBegin();
            if (var1 != null) {
               return SootHostExtendKt.getLine(var1);
            }
         }

         return null;
      }


   public final val javaNameSourceStartColumnNumber: Int?
      public final get() {
         val var10000: NodeWithRange = this.getNameDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getBegin();
            if (var1 != null) {
               return SootHostExtendKt.getColumn(var1);
            }
         }

         return null;
      }


   public final val javaNameSourceEndLineNumber: Int?
      public final get() {
         val var10000: NodeWithRange = this.getNameDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getEnd();
            if (var1 != null) {
               return SootHostExtendKt.getLine(var1);
            }
         }

         return null;
      }


   public final val javaNameSourceEndColumnNumber: Int?
      public final get() {
         val var10000: NodeWithRange = this.getNameDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getEnd();
            if (var1 != null) {
               return SootHostExtendKt.getColumn(var1);
            }
         }

         return null;
      }


   public open val startLine: Int?
      public open get() {
         val var10000: NodeWithRange = this.getDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getBegin();
            if (var1 != null) {
               return SootHostExtendKt.getLine(var1);
            }
         }

         return null;
      }


   public open val startLineColumn: Int?
      public open get() {
         val var10000: NodeWithRange = this.getDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getBegin();
            if (var1 != null) {
               return SootHostExtendKt.getColumn(var1);
            }
         }

         return null;
      }


   public open val endLine: Int?
      public open get() {
         val var10000: NodeWithRange = this.getDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getEnd();
            if (var1 != null) {
               return SootHostExtendKt.getLine(var1);
            }
         }

         return null;
      }


   public open val endLineColumn: Int?
      public open get() {
         val var10000: NodeWithRange = this.getDecl();
         if (var10000 != null) {
            val var1: Optional = var10000.getEnd();
            if (var1 != null) {
               return SootHostExtendKt.getColumn(var1);
            }
         }

         return null;
      }


   init {
      this.host = host;
      this.cu = cu;
   }

   public open fun getJavaSourceStartLineNumber(): Int {
      val var1: Int = this.getHost().getJavaSourceStartLineNumber();
      val it: Int = var1.intValue();
      val var10000: Int = if (it != -1) var1 else null;
      val var4: Int;
      if ((if (it != -1) var1 else null) != null) {
         var4 = var10000;
      } else {
         val var5: Int = this.getStartLine();
         var4 = (int)(var5 ?: -1);
      }

      return var4;
   }

   public open fun getJavaSourceStartColumnNumber(): Int {
      val var1: Int = this.getHost().getJavaSourceStartColumnNumber();
      val it: Int = var1.intValue();
      val var10000: Int = if (it != -1) var1 else null;
      val var4: Int;
      if ((if (it != -1) var1 else null) != null) {
         var4 = var10000;
      } else {
         val var5: Int = this.getStartLineColumn();
         var4 = (int)(var5 ?: -1);
      }

      return var4;
   }

   public open fun getTags(): MutableList<Tag> {
      return this.host.getTags();
   }

   public open fun getTag(p0: String): Tag {
      return this.host.getTag(p0);
   }

   public open fun addTag(p0: Tag) {
      this.host.addTag(p0);
   }

   public open fun removeTag(p0: String) {
      this.host.removeTag(p0);
   }

   public open fun hasTag(p0: String): Boolean {
      return this.host.hasTag(p0);
   }

   public open fun removeAllTags() {
      this.host.removeAllTags();
   }

   public open fun addAllTagsOf(p0: Host) {
      this.host.addAllTagsOf(p0);
   }
}
