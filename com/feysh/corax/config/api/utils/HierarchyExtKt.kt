package com.feysh.corax.config.api.utils

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function2
import soot.FastHierarchy
import soot.Hierarchy
import soot.RefLikeType
import soot.SootClass

public fun Hierarchy.getSubclassesOrImplementersOf(sootClass: SootClass): MutableList<SootClass> {
   return if (sootClass.isInterface())
      `$this$getSubclassesOrImplementersOf`.getImplementersOf(sootClass)
      else
      `$this$getSubclassesOrImplementersOf`.getSubclassesOf(sootClass);
}

@Deprecated(message = "Scene.v().activeHierarchy().getSubclassesOf()")
public fun FastHierarchy.getAllSubclasses(sootClass: SootClass): Sequence<SootClass> {
   return SequencesKt.sequence(
      (new Function2<SequenceScope<? super SootClass>, Continuation<? super Unit>, Object>(sootClass, `$this$getAllSubclasses`, null) {
         Object L$1;
         Object L$2;
         int label;

         {
            super(2, `$completionx`);
            this.$sootClass = `$sootClass`;
            this.$this_getAllSubclasses = `$receiver`;
         }

         // $VF: Irreducible bytecode was duplicated to produce valid code
         public final Object invokeSuspend(Object $result) {
            val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            var `$this$sequence`: SequenceScope;
            var var4: java.util.Iterator;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  `$this$sequence` = this.L$0 as SequenceScope;
                  if (this.$sootClass.getType() !is RefLikeType) {
                     return Unit.INSTANCE;
                  }

                  var4 = this.$this_getAllSubclasses.getSubclassesOf(this.$sootClass).iterator();
                  break;
               case 1:
                  val subClass: SootClass = this.L$2 as SootClass;
                  var4 = this.L$1 as java.util.Iterator;
                  `$this$sequence` = this.L$0 as SequenceScope;
                  ResultKt.throwOnFailure(`$result`);
                  val var10001: FastHierarchy = this.$this_getAllSubclasses;
                  val var8: Sequence = HierarchyExtKt.getAllSubclasses(var10001, subClass);
                  val var10002: Continuation = this as Continuation;
                  this.L$0 = `$this$sequence`;
                  this.L$1 = var4;
                  this.L$2 = null;
                  this.label = 2;
                  if (`$this$sequence`.yieldAll(var8, var10002) === var6) {
                     return var6;
                  }
                  break;
               case 2:
                  var4 = this.L$1 as java.util.Iterator;
                  `$this$sequence` = this.L$0 as SequenceScope;
                  ResultKt.throwOnFailure(`$result`);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            val var10: Sequence;
            var var12: Continuation;
            do {
               if (!var4.hasNext()) {
                  return Unit.INSTANCE;
               }

               val var7: SootClass = var4.next() as SootClass;
               var12 = this as Continuation;
               this.L$0 = `$this$sequence`;
               this.L$1 = var4;
               this.L$2 = var7;
               this.label = 1;
               if (`$this$sequence`.yield(var7, var12) === var6) {
                  return var6;
               }

               val var9: FastHierarchy = this.$this_getAllSubclasses;
               var10 = HierarchyExtKt.getAllSubclasses(var9, var7);
               var12 = this as Continuation;
               this.L$0 = `$this$sequence`;
               this.L$1 = var4;
               this.L$2 = null;
               this.label = 2;
            } while ($this$sequence.yieldAll(var10, var12) != var6);

            return var6;
         }

         public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
            val var3: Function2 = new <anonymous constructor>(this.$sootClass, this.$this_getAllSubclasses, `$completion`);
            var3.L$0 = value;
            return var3 as Continuation<Unit>;
         }

         public final Object invoke(SequenceScope<? super SootClass> p1, Continuation<? super Unit> p2) {
            return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
         }
      }) as Function2
   );
}
