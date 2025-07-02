package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassDecl
import com.feysh.corax.config.api.IClassMatch

public class BaseClassDecl(analyzeConfig: AIAnalysisBaseImpl, match: IClassMatch) : IClassDecl {
   public final val analyzeConfig: AIAnalysisBaseImpl
   public open val match: IClassMatch

   init {
      this.analyzeConfig = analyzeConfig;
      this.match = match;
   }

   public override fun toString(): String {
      return "class decl: ${this.getMatch()}";
   }
}
