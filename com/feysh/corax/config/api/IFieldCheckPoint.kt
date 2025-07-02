package com.feysh.corax.config.api

import soot.SootField
import soot.tagkit.VisibilityAnnotationTag

public interface IFieldCheckPoint : IClassMemberCheckPoint {
   public val sootField: SootField
   public val visibilityAnnotationTag: VisibilityAnnotationTag?
}
