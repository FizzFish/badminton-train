package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIexGetField
import com.feysh.corax.config.api.IModelExpressionVisitor
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.collections.immutable.ExtensionsKt
import kotlinx.collections.immutable.PersistentList

public class IexGetFieldExpr(base: IExpr, accessPath: PersistentList<IClassField>) : IIexGetField {
   public open val base: IExpr
   public open val accessPath: PersistentList<IClassField>

   init {
      this.base = base;
      this.accessPath = accessPath;
   }

   public override fun toString(): String {
      return "IexGetField( ${this.getBase()}.${CollectionsKt.joinToString$default(
         this.getAccessPath() as java.lang.Iterable, ".", null, null, 0, null, null, 62, null
      )} )";
   }

   public override fun <TResult> accept(visitor: IModelExpressionVisitor<TResult>): TResult {
      return (TResult)visitor.visit(this);
   }

   @SourceDebugExtension(["SMAP\nAIAnalysisBaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/IexGetFieldExpr$Companion\n+ 2 extensions.kt\nkotlinx/collections/immutable/ExtensionsKt\n*L\n1#1,1477:1\n138#2:1478\n*S KotlinDebug\n*F\n+ 1 AIAnalysisBaseImpl.kt\ncom/feysh/corax/config/api/baseimpl/IexGetFieldExpr$Companion\n*L\n561#1:1478\n*E\n"])
   public companion object {
      public operator fun invoke(base: IExpr, field: IClassField): IexGetFieldExpr {
         return if (base is IexGetFieldExpr)
            new IexGetFieldExpr((base as IexGetFieldExpr).getBase(), (base as IexGetFieldExpr).getAccessPath().add(field))
            else
            new IexGetFieldExpr(base, ExtensionsKt.persistentListOf(new IClassField[]{field}));
      }
   }
}
