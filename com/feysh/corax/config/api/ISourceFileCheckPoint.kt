package com.feysh.corax.config.api

import java.net.URI
import java.nio.file.Path
import kotlin.io.path.PathsKt

public interface ISourceFileCheckPoint : ICheckPoint {
   public val path: Path
   public val uri: URI

   public open val filename: String
      public open get() {
      }


   public val archiveFile: Path?
   public val relativePath: IRelativePath

   @Throws(java/io/IOException::class)
   public abstract suspend fun readAllBytes(): ByteArray {
   }

   public abstract suspend fun text(): String? {
   }

   public abstract suspend fun lines(): List<IndexedValue<String>> {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun getFilename(`$this`: ISourceFileCheckPoint): java.lang.String {
         return PathsKt.getName(`$this`.getPath());
      }
   }
}
