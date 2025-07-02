package com.feysh.corax.config.api

public interface IModelExpressionVisitor<TResult> {
   public abstract fun default(expr: IExpr): Any {
   }

   public open fun visit(expr: IIexLoad): Any {
   }

   public open fun visit(expr: IIexGetField): Any {
   }

   public open fun visit(expr: IUnOpExpr): Any {
   }

   public open fun visit(expr: IBinOpExpr): Any {
   }

   public open fun visit(expr: ITriOpExpr): Any {
   }

   public open fun visit(expr: IQOpExpr): Any {
   }

   public open fun visit(expr: IIexConst): Any {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IIexLoad): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IIexGetField): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IUnOpExpr): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IBinOpExpr): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: ITriOpExpr): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IQOpExpr): TResult {
         return (TResult)`$this`.default(expr);
      }

      @JvmStatic
      fun <TResult> visit(`$this`: IModelExpressionVisitor<TResult>, expr: IIexConst): TResult {
         return (TResult)`$this`.default(expr);
      }
   }
}
