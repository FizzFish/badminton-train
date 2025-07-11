package cn.sast.framework.report.sqldelight

public data class Verify_rule_name(id: Long, rule_name: String) {
   public final val id: Long
   public final val rule_name: String

   init {
      this.id = id;
      this.rule_name = rule_name;
   }

   public operator fun component1(): Long {
      return this.id;
   }

   public operator fun component2(): String {
      return this.rule_name;
   }

   public fun copy(id: Long = this.id, rule_name: String = this.rule_name): Verify_rule_name {
      return new Verify_rule_name(id, rule_name);
   }

   public override fun toString(): String {
      return "Verify_rule_name(id=${this.id}, rule_name=${this.rule_name})";
   }

   public override fun hashCode(): Int {
      return java.lang.Long.hashCode(this.id) * 31 + this.rule_name.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is Verify_rule_name) {
         return false;
      } else {
         val var2: Verify_rule_name = other as Verify_rule_name;
         if (this.id != (other as Verify_rule_name).id) {
            return false;
         } else {
            return this.rule_name == var2.rule_name;
         }
      }
   }
}
