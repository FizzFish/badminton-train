package com.feysh.corax.config.api.baseimpl

internal fun String.splitPackage(): Pair<String, String> {
   val index: Int = StringsKt.lastIndexOf$default(`$this$splitPackage`, ".", 0, false, 6, null);
   val var10000: Pair;
   if (index == -1) {
      var10000 = TuplesKt.to("", `$this$splitPackage`);
   } else {
      val var2: java.lang.String = `$this$splitPackage`.substring(0, index);
      val var10001: java.lang.String = `$this$splitPackage`.substring(index + 1);
      var10000 = TuplesKt.to(var2, var10001);
   }

   return var10000;
}
