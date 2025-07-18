package cn.sast.api.report

import cn.sast.common.IResource
import soot.SootClass
import soot.SootMethod

public interface IReportHashCalculator {
   public abstract fun from(clazz: SootClass): String {
   }

   public abstract fun from(method: SootMethod): String {
   }

   public abstract fun fromAbsPath(absolutePath: IResource): String {
   }

   public open fun fromPath(path: IResource): String {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun fromPath(`$this`: IReportHashCalculator, path: IResource): java.lang.String {
         return `$this`.fromAbsPath(path.getAbsolute().getNormalize());
      }
   }
}
