package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassMatch

public fun IClassMatch.method(vararg methodName: String): RawSignatureMatch {
   return new RawSignatureMatch(`$this$method`, ArraysKt.toList(methodName), null, null, null, 16, null);
}
