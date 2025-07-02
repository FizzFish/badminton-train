package com.feysh.corax.config.api

public interface IModelStmtVisitor<TResult> {
   public abstract fun default(stmt: IStmt): Any {
   }

   public open fun visit(stmt: IIstSetField): Any {
   }

   public open fun visit(stmt: IIstStoreLocal): Any {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <TResult> visit(`$this`: IModelStmtVisitor<TResult>, stmt: IIstSetField): TResult {
         return (TResult)`$this`.default(stmt);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelStmtVisitor<TResult>, stmt: IIstStoreLocal): TResult {
         return (TResult)`$this`.default(stmt);
      }
   }
}
