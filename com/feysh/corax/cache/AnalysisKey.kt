package com.feysh.corax.cache

import com.feysh.corax.cache.AnalysisDataFactory.Key

public abstract class AnalysisKey<T> {
   public open val factoryKey: Key<Any>

   open fun AnalysisKey(factoryKey: AnalysisDataFactoryKey<T>) {
      this.factoryKey = factoryKey;
   }

   public override fun hashCode(): Int {
      throw new IllegalStateException("did u forgot override hashCode()?".toString());
   }

   public override operator fun equals(other: Any?): Boolean {
      throw new IllegalStateException("did u forgot override equals()?".toString());
   }
}
