package com.feysh.corax.config.api.utils

import java.io.File
import java.net.URI
import java.net.URL
import java.nio.file.FileSystemNotFoundException
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nClassCommons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassCommons.kt\ncom/feysh/corax/config/api/utils/ClassCommons\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"])
public object ClassCommons {
   public fun Class<*>.toClassFilePath(): String {
      val var10000: java.lang.String = `$this$toClassFilePath`.getName();
      if (var10000 == null) {
         throw new IllegalArgumentException("Class is local or anonymous".toString());
      } else {
         return "${StringsKt.replace$default(var10000, '.', '/', false, 4, null)}.class";
      }
   }

   private fun URI.extractJarName(): URI {
      val var10002: java.lang.String = `$this$extractJarName`.getSchemeSpecificPart();
      return new URI(StringsKt.replace$default(StringsKt.substringBefore$default(var10002, "!", null, 2, null), " ", "%20", false, 4, null));
   }

   public fun locateAllClass(clazz: Class<*>): Path {
      val path: java.lang.String = this.toClassFilePath(clazz);
      val var10000: URL = clazz.getClassLoader().getResource(path);
      if (var10000 == null) {
         throw new IllegalArgumentException(("No such file: $path").toString());
      } else {
         val var13: Path;
         if (var10000.toURI().getScheme() == "jar") {
            val var10001: URI = var10000.toURI();
            var13 = Paths.get(this.extractJarName(var10001));
         } else {
            val uri: URI = var10000.toURI();
            val var14: Path;
            if (uri.getScheme() == "file") {
               var14 = Paths.get(uri);
            } else {
               try {
                  FileSystems.getFileSystem(uri);
               } catch (var9: FileSystemNotFoundException) {
                  FileSystems.newFileSystem(uri, MapsKt.mapOf(TuplesKt.to("zipinfo-time", "false")));
               }

               var14 = Paths.get(uri);
            }

            val var15: Path = var14.toAbsolutePath();
            val var10: Path = new File(
                  StringsKt.removeSuffix(
                     StringsKt.replace$default(StringsKt.replace$default(var15.normalize().toString(), "\\", "/", false, 4, null), "//", "/", false, 4, null),
                     path
                  )
               )
               .toPath();
            var13 = var10;
         }

         return var13;
      }
   }
}
