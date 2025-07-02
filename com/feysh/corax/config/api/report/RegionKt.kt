package com.feysh.corax.config.api.report

import soot.tagkit.SourceLnPosTag
import soot.tagkit.Tag

public final val convert: Region?
   public final get() {
      return Region.Companion.invoke(`$this$convert`);
   }


public final val pos: SourceLnPosTag?
   public final get() {
      val var1: Tag = `$this$pos`.getTag("SourceLnPosTag");
      return var1 as? SourceLnPosTag;
   }

