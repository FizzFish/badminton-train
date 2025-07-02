package cn.sast.idfa.analysis

import kotlin.enums.EnumEntries

public enum class FixPointStatus {
   HasChange,
   Fixpoint,
   NeedWideningOperators   @JvmStatic
   public FixPointStatus.Companion Companion = new FixPointStatus.Companion(null);

   @JvmStatic
   fun getEntries(): EnumEntries<FixPointStatus> {
      return $ENTRIES;
   }

   public companion object {
      public inline fun of(hasChange: Boolean): FixPointStatus {
         return if (hasChange) FixPointStatus.HasChange else FixPointStatus.Fixpoint;
      }
   }
}
