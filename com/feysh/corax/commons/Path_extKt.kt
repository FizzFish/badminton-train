@file:SourceDebugExtension(["SMAP\npath-ext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 path-ext.kt\ncom/feysh/corax/commons/Path_extKt\n+ 2 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,22:1\n54#2,4:23\n1#3:27\n1317#4,2:28\n*S KotlinDebug\n*F\n+ 1 path-ext.kt\ncom/feysh/corax/commons/Path_extKt\n*L\n8#1:23,4\n8#1:27\n10#1:28,2\n*E\n"])

package com.feysh.corax.commons

import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.nio.file.Files
import java.nio.file.OpenOption
import java.nio.file.Path
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.Ref.IntRef

public fun Path.linesMatch(max: Int, predicate: (String) -> Boolean): String? {
   label76: {
      val var10001: Array<OpenOption> = new OpenOption[0];
      val var10000: InputStream = Files.newInputStream(`$this$linesMatch`, Arrays.copyOf(var10001, var10001.length));
      val var39: Closeable = new InputStreamReader(var10000, Charsets.UTF_8);
      var var40: java.lang.Throwable = null;

      label73: {
         label72: {
            try {
               try {
                  var var9: Closeable;
                  label68: {
                     label77: {
                        var9 = if ((var39 as InputStreamReader) as Reader is BufferedReader)
                           (var39 as InputStreamReader) as BufferedReader
                           else
                           new BufferedReader(var39 as InputStreamReader, 8192);
                        var var41: java.lang.Throwable = null;

                        try {
                           try {
                              val it: Sequence = TextStreamsKt.lineSequence(var9 as BufferedReader);
                              val i: IntRef = new IntRef();
                              val var18: java.util.Iterator = it.iterator();

                              val line: java.lang.String;
                              do {
                                 if (!var18.hasNext()) {
                                    break label68;
                                 }

                                 line = var18.next() as java.lang.String;
                                 if (max > 0 && i.element++ >= max) {
                                    break label77;
                                 }
                              } while (!predicate.invoke(line));
                           } catch (var27: java.lang.Throwable) {
                              var41 = var27;
                              throw var27;
                           }
                        } catch (var28: java.lang.Throwable) {
                           CloseableKt.closeFinally(var9, var41);
                        }

                        CloseableKt.closeFinally(var9, null);
                        break label72;
                     }

                     CloseableKt.closeFinally(var9, null);
                     break label73;
                  }

                  CloseableKt.closeFinally(var9, null);
               } catch (var29: java.lang.Throwable) {
                  var40 = var29;
                  throw var29;
               }
            } catch (var30: java.lang.Throwable) {
               CloseableKt.closeFinally(var39, var40);
            }

            CloseableKt.closeFinally(var39, null);
         }

         CloseableKt.closeFinally(var39, null);
      }

      CloseableKt.closeFinally(var39, null);
   }
}
