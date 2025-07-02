package com.feysh.corax.config.api

import kotlin.enums.EnumEntries

public enum class BinOp {
   LT,
   LE,
   EQ,
   GE,
   GT,
   Add,
   Sub,
   Mul,
   Div,
   Mod,
   Or,
   And,
   Xor,
   BvAnd,
   BvOr,
   BvXor,
   BvShr,
   BvShl,
   BvLShr,
   OrSet,
   AndSet,
   RemoveSet,
   HasIntersectionSet,
   ContainsSet,
   StartsWith,
   EndsWith,
   Contains,
   StringEquals,
   IsInstanceOf,
   AnyOf
   @JvmStatic
   fun getEntries(): EnumEntries<BinOp> {
      return $ENTRIES;
   }
}
