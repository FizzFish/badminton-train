package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.reflect.KProperty
import mu.KLogger
import org.apache.logging.log4j.core.config.Configurator
import org.slf4j.event.Level
import soot.SootClass

public interface AIAnalysisApi : AnalysisApi, IAnalysisDepends {
   public val state: MutableMap<String, Any>
   public val preAnalysis: PreAnalysisApi

   public open val logger: KLogger
      public open get() {
      }


   public val error: com.feysh.corax.config.api.AIAnalysisApi.Error
   public val sootDecl: Sequence<ISootMethodDecl<R>>

   public open fun setVerbosity(verbosity: Level) {
   }

   public abstract fun validate() {
   }

   public abstract fun <This> constructor(at: () -> This): IIMethodDecl0<This, This> {
   }

   public abstract fun <R, THIS> method(at: (THIS) -> R): IIMethodDecl0<R, THIS> {
   }

   public abstract fun <R> staticMethod(at: () -> R): ISMethodDecl0<R> {
   }

   public abstract fun <This, P0> constructor(at: (P0) -> This): IIMethodDecl1<This, This, P0> {
   }

   public abstract fun <R, THIS, P0> method(at: (THIS, P0) -> R): IIMethodDecl1<R, THIS, P0> {
   }

   public abstract fun <R, P0> staticMethod(at: (P0) -> R): ISMethodDecl1<R, P0> {
   }

   public abstract fun <This, P0, P1> constructor(at: (P0, P1) -> This): IIMethodDecl2<This, This, P0, P1> {
   }

   public abstract fun <R, THIS, P0, P1> method(at: (THIS, P0, P1) -> R): IIMethodDecl2<R, THIS, P0, P1> {
   }

   public abstract fun <R, P0, P1> staticMethod(at: (P0, P1) -> R): ISMethodDecl2<R, P0, P1> {
   }

   public abstract fun <This, P0, P1, P2> constructor(at: (P0, P1, P2) -> This): IIMethodDecl3<This, This, P0, P1, P2> {
   }

   public abstract fun <R, THIS, P0, P1, P2> method(at: (THIS, P0, P1, P2) -> R): IIMethodDecl3<R, THIS, P0, P1, P2> {
   }

   public abstract fun <R, P0, P1, P2> staticMethod(at: (P0, P1, P2) -> R): ISMethodDecl3<R, P0, P1, P2> {
   }

   public abstract fun <This, P0, P1, P2, P3> constructor(at: (P0, P1, P2, P3) -> This): IIMethodDecl4<This, This, P0, P1, P2, P3> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3> method(at: (THIS, P0, P1, P2, P3) -> R): IIMethodDecl4<R, THIS, P0, P1, P2, P3> {
   }

   public abstract fun <R, P0, P1, P2, P3> staticMethod(at: (P0, P1, P2, P3) -> R): ISMethodDecl4<R, P0, P1, P2, P3> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4> constructor(at: (P0, P1, P2, P3, P4) -> This): IIMethodDecl5<This, This, P0, P1, P2, P3, P4> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4> method(at: (THIS, P0, P1, P2, P3, P4) -> R): IIMethodDecl5<R, THIS, P0, P1, P2, P3, P4> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4> staticMethod(at: (P0, P1, P2, P3, P4) -> R): ISMethodDecl5<R, P0, P1, P2, P3, P4> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5> constructor(at: (P0, P1, P2, P3, P4, P5) -> This): IIMethodDecl6<This, This, P0, P1, P2, P3, P4, P5> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5> method(at: (THIS, P0, P1, P2, P3, P4, P5) -> R): IIMethodDecl6<R, THIS, P0, P1, P2, P3, P4, P5> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5> staticMethod(at: (P0, P1, P2, P3, P4, P5) -> R): ISMethodDecl6<R, P0, P1, P2, P3, P4, P5> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6> constructor(at: (P0, P1, P2, P3, P4, P5, P6) -> This): IIMethodDecl7<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6) -> R): IIMethodDecl7<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6) -> R): ISMethodDecl7<R, P0, P1, P2, P3, P4, P5, P6> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7) -> This): IIMethodDecl8<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7) -> R): IIMethodDecl8<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7) -> R): ISMethodDecl8<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> This): IIMethodDecl9<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): IIMethodDecl9<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): ISMethodDecl9<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> This): IIMethodDecl10<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): IIMethodDecl10<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): ISMethodDecl10<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> This): IIMethodDecl11<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): IIMethodDecl11<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): ISMethodDecl11<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> This): IIMethodDecl12<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): IIMethodDecl12<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): ISMethodDecl12<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> This
   ): IIMethodDecl13<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R
   ): IIMethodDecl13<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R): ISMethodDecl13<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11,
         P12
      > {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> This
   ): IIMethodDecl14<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
   ): IIMethodDecl14<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
   ): ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> This
   ): IIMethodDecl15<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
   ): IIMethodDecl15<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
   ): ISMethodDecl15<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
   }

   public abstract fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> This
   ): IIMethodDecl16<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
   }

   public abstract fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
   ): IIMethodDecl16<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
   }

   public abstract fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
   ): ISMethodDecl16<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
   }

   public abstract fun <V> field(field: KProperty<V>): IFieldDecl<Any, V> {
   }

   public abstract fun eachClass(block: (ISootClassDecl) -> Unit) {
   }

   public abstract fun eachMethod(block: (ISootMethodDecl<Any>) -> Unit) {
   }

   public abstract fun eachField(block: (ISootFieldDecl<Any, Any>) -> Unit) {
   }

   public abstract fun eachLocalVariable(block: (ISootLocalVarDecl<Any>) -> Unit) {
   }

   public abstract fun clazz(classMatch: IClassMatch): IClassDecl {
   }

   public abstract fun method(method: IMethodMatch): IRawMethodDecl<Any> {
   }

   public abstract fun field(fieldMatch: IFieldMatch): IFieldDecl<Any, Any> {
   }

   public abstract fun localVar(localVarMatch: ILocalVarMatch): ILocalVarDecl<Any> {
   }

   public abstract fun sootClass(sootClass: SootClass): ISootClassDecl {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun getLogger(`$this`: AIAnalysisApi): KLogger {
         return `$this`.getError().getLogger();
      }

      @JvmStatic
      fun setVerbosity(`$this`: AIAnalysisApi, verbosity: Level) {
         Configurator.setAllLevels(`$this`.getLogger().getName(), UtilsKt.level(verbosity));
         System.out.println("Log Level changed to [$verbosity]");
      }
   }

   public interface Error {
      public val logger: KLogger

      public abstract fun error(msg: String) {
      }

      public abstract fun warning(msg: String) {
      }
   }
}
