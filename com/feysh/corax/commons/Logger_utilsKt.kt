package com.feysh.corax.commons

import java.lang.reflect.Field
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.slf4j.Log4jLogger
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

private fun level(level: Level): org.apache.logging.log4j.Level {
   return org.apache.logging.log4j.Level.toLevel(level.name());
}

public fun setVerbosity(verbosity: Level) {
   System.out.println("Log Level changed to [$verbosity]");
   val apacheLog4j: Logger = LogManager.getRootLogger();
   Configurator.setAllLevels(apacheLog4j.getName(), level(verbosity));
   System.out
      .println(
         "apache log4j2 root logger: isTraceEnabled: ${apacheLog4j.isTraceEnabled()}, isDebugEnabled: ${apacheLog4j.isDebugEnabled()}, isInfoEnabled: ${apacheLog4j.isInfoEnabled()}, isWarnEnabled: ${apacheLog4j.isWarnEnabled()}, isErrorEnabled: ${apacheLog4j.isErrorEnabled()}"
      );
   var var10000: org.slf4j.Logger = LoggerFactory.getLogger("ROOT");
   val slf4jLogger: org.slf4j.Logger = var10000;

   var var5: Logger;
   try {
      val var9: Field = Log4jLogger.class.getDeclaredField("logger");
      var9.setAccessible(true);
      val ignore: Any = var9.get(slf4jLogger);
      var5 = ignore as? Logger;
   } catch (var7: NoSuchFieldException) {
      var5 = null;
   }

   var10000 = var5;
   if (var5 == null) {
      var10000 = var10000;
   }

   if (!(apacheLog4j.getClass() == var10000.getClass())) {
      System.out
         .println(
            "org.slf4j root logger:${var10000.getClass().getSimpleName()} isTraceEnabled: ${var10000.isTraceEnabled()}, isDebugEnabled: ${var10000.isDebugEnabled()}, isInfoEnabled: ${var10000.isInfoEnabled()}, isWarnEnabled: ${var10000.isWarnEnabled()}, isErrorEnabled: ${var10000.isErrorEnabled()}"
         );
      throw new IllegalStateException(("invalid logger: ${apacheLog4j.getClass()} != $var10000, 不使用 apache log4j2 可能会导致分析效率减少一倍").toString());
   }
}
