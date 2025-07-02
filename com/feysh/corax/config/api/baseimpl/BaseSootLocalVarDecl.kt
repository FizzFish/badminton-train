package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.ILocalVarDecl
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.ISootLocalVarDecl
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.ILocalVarDecl.IGet
import com.feysh.corax.config.api.ILocalVarDecl.ISet
import soot.Local
import soot.Type

public class BaseSootLocalVarDecl<T>(delegate: ILocalVarDecl<Any>, local: Local) : ISootLocalVarDecl<T>, ILocalVarDecl<T> {
   public final val delegate: ILocalVarDecl<Any>
   public open val local: Local

   init {
      this.delegate = delegate;
      this.local = local;
   }

   override fun getName(): java.lang.String? {
      return ISootLocalVarDecl.DefaultImpls.getName(this);
   }

   override fun getType(): Type {
      return ISootLocalVarDecl.DefaultImpls.getType(this);
   }

   public override fun atGet(config: (MethodConfig) -> Unit, block: (IGet<Any>) -> Unit): ILocalVarDecl<Any> {
      return this.delegate.atGet(config, block);
   }

   public override fun atSet(config: (MethodConfig) -> Unit, block: (ISet<Any>, IParameterT<Any>) -> Unit): ILocalVarDecl<Any> {
      return this.delegate.atSet(config, block);
   }
}
