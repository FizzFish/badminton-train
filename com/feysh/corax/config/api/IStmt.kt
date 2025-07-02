package com.feysh.corax.config.api

public interface IStmt {
   public abstract fun <TResult> accept(visitor: IModelStmtVisitor<TResult>): TResult {
   }
}
