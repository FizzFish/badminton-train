package com.google.common.base

import cn.sast.common.interner.InternerEquiv

public class InternerEquals<T extends InternerEquiv> : Equivalence<T> {
   protected open fun doEquivalent(a: Any, b: Any): Boolean {
      return a.equivTo(b);
   }

   protected open fun doHash(o: Any): Int {
      return o.equivHashCode();
   }
}
