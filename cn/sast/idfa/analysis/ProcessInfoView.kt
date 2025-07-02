package cn.sast.idfa.analysis

import java.util.Arrays
import java.util.concurrent.atomic.AtomicLong
import java.util.function.LongUnaryOperator
import kotlin.jvm.internal.SourceDebugExtension
import org.eclipse.microprofile.metrics.Gauge

@SourceDebugExtension(["SMAP\nProcessInfoView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessInfoView.kt\ncn/sast/idfa/analysis/ProcessInfoView\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"])
public open class ProcessInfoView(metrics: UsefulMetrics = UsefulMetrics.Companion.getMetrics()) {
   public final val metrics: UsefulMetrics
   public final val maxUsedMemory: AtomicLong

   public open val memFmt: String
      public open get() {
         val var4: Array<Any> = new Object[]{(double)`$this$memFmt` / 1024.0 / (double)1024 / (double)1024};
         val var10000: java.lang.String = java.lang.String.format("%.1f", Arrays.copyOf(var4, var4.length));
         return var10000;
      }


   public open val jvmMemoryUsedText: String
      public open get() {
         this.updateStat();
         return "${this.metrics.getMemFmt(this.metrics.getJvmMemoryUsed())}${if (this.metrics.isLongTermThresholdTriggered()) "(JVM mem)" else ""}${if (this.metrics
               .isMemoryThresholdTriggered())
            "!"
            else
            ""}";
      }


   public open val maxUsedMemoryText: String
      public open get() {
         return this.getMemFmt(this.maxUsedMemory.get());
      }


   public open val jvmMemoryCommittedText: String
      public open get() {
         return this.metrics.getMemFmt(this.metrics.getJvmMemoryCommitted());
      }


   public open val jvmMemoryMaxText: String
      public open get() {
         return "${this.metrics.getMemFmt(this.metrics.getJvmMemoryMax())}G";
      }


   public open val jvmMemoryUsageText: String
      public open get() {
         return "${this.getJvmMemoryUsedText()}/${this.getMaxUsedMemoryText()}/${this.getJvmMemoryCommittedText()}/${this.getJvmMemoryMaxText()}";
      }


   public open val freeMemoryText: String
      public open get() {
         val phy: java.lang.Long = this.metrics.getMemSize(this.metrics.getFreePhysicalSize());
         if (phy != null) {
            val var10000: java.lang.String = this.getMemFmt(phy);
            if (var10000 != null) {
               return "free:$var10000G${if (phy != null && phy.longValue() < 8.0530637E8F) "(low memory warning!)" else ""}";
            }
         }

         return "free:${null}${if (phy != null && phy.longValue() < 8.0530637E8F) "(low memory warning!)" else ""}";
      }


   public open val cpuLoadText: String
      public open get() {
         return "cpu:${this.metrics.getLoadFmt(this.metrics.getCpuSystemCpuLoad())}";
      }


   public open val processInfoText: String
      public open get() {
         return "${this.getJvmMemoryUsageText()} ${this.getFreeMemoryText()}";
      }


   init {
      this.metrics = metrics;
      this.maxUsedMemory = new AtomicLong(0L);
   }

   public fun updateStat() {
      val var10000: Gauge = this.metrics.getJvmMemoryUsed();
      if (var10000 != null) {
         val var4: java.lang.Long = var10000.getValue() as java.lang.Long;
         if (var4 != null) {
            val usedMemory: Long = var4.longValue();
            this.maxUsedMemory.updateAndGet(new LongUnaryOperator(usedMemory) {
               {
                  this.$usedMemory = `$usedMemory`;
               }

               @Override
               public final long applyAsLong(long it) {
                  return if (it < this.$usedMemory) this.$usedMemory else it;
               }
            });
         }
      }
   }

   open fun ProcessInfoView() {
      this(null, 1, null);
   }

   public companion object {
      public final val globalProcessInfo: ProcessInfoView
   }
}
