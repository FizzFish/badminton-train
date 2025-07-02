// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

/**
 * Status of a node during the fix-point iteration.
 */
enum class FixPointStatus {
    /**
     * The value associated with the node changed in the last iteration.
     */
    HasChange,

    /** Reached a fix point without the need of widening. */
    Fixpoint,

    /**
     * Widening operators are required to speed up convergence.
     */
    NeedWideningOperators;

    companion object {
        /**
         * Utility to convert a boolean flag returned from the analysis
         * into the corresponding [FixPointStatus].
         */
        fun of(hasChange: Boolean): FixPointStatus =
            if (hasChange) HasChange else Fixpoint
    }
}


