package cn.sast.framework.report.sqldelight

public data class ControlFlow(id: Long,
   __file_id: Long,
   _file_abs_path: String,
   message_en: String?,
   message_zh: String?,
   __edge_from_region_id: Long,
   __edge_to_region_id: Long
) {
   public final val id: Long
   public final val __file_id: Long
   public final val _file_abs_path: String
   public final val message_en: String?
   public final val message_zh: String?
   public final val __edge_from_region_id: Long
   public final val __edge_to_region_id: Long

   init {
      this.id = id;
      this.__file_id = __file_id;
      this._file_abs_path = _file_abs_path;
      this.message_en = message_en;
      this.message_zh = message_zh;
      this.__edge_from_region_id = __edge_from_region_id;
      this.__edge_to_region_id = __edge_to_region_id;
   }

   public operator fun component1(): Long {
      return this.id;
   }

   public operator fun component2(): Long {
      return this.__file_id;
   }

   public operator fun component3(): String {
      return this._file_abs_path;
   }

   public operator fun component4(): String? {
      return this.message_en;
   }

   public operator fun component5(): String? {
      return this.message_zh;
   }

   public operator fun component6(): Long {
      return this.__edge_from_region_id;
   }

   public operator fun component7(): Long {
      return this.__edge_to_region_id;
   }

   public fun copy(
      id: Long = this.id,
      __file_id: Long = this.__file_id,
      _file_abs_path: String = this._file_abs_path,
      message_en: String? = this.message_en,
      message_zh: String? = this.message_zh,
      __edge_from_region_id: Long = this.__edge_from_region_id,
      __edge_to_region_id: Long = this.__edge_to_region_id
   ): ControlFlow {
      return new ControlFlow(id, __file_id, _file_abs_path, message_en, message_zh, __edge_from_region_id, __edge_to_region_id);
   }

   public override fun toString(): String {
      return "ControlFlow(id=${this.id}, __file_id=${this.__file_id}, _file_abs_path=${this._file_abs_path}, message_en=${this.message_en}, message_zh=${this.message_zh}, __edge_from_region_id=${this.__edge_from_region_id}, __edge_to_region_id=${this.__edge_to_region_id})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        (
                                 ((java.lang.Long.hashCode(this.id) * 31 + java.lang.Long.hashCode(this.__file_id)) * 31 + this._file_abs_path.hashCode()) * 31
                                    + (if (this.message_en == null) 0 else this.message_en.hashCode())
                              )
                              * 31
                           + (if (this.message_zh == null) 0 else this.message_zh.hashCode())
                     )
                     * 31
                  + java.lang.Long.hashCode(this.__edge_from_region_id)
            )
            * 31
         + java.lang.Long.hashCode(this.__edge_to_region_id);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ControlFlow) {
         return false;
      } else {
         val var2: ControlFlow = other as ControlFlow;
         if (this.id != (other as ControlFlow).id) {
            return false;
         } else if (this.__file_id != var2.__file_id) {
            return false;
         } else if (!(this._file_abs_path == var2._file_abs_path)) {
            return false;
         } else if (!(this.message_en == var2.message_en)) {
            return false;
         } else if (!(this.message_zh == var2.message_zh)) {
            return false;
         } else if (this.__edge_from_region_id != var2.__edge_from_region_id) {
            return false;
         } else {
            return this.__edge_to_region_id == var2.__edge_to_region_id;
         }
      }
   }
}
