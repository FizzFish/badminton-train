// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

public abstract class InterProceduralAnalysis<M, N, A> public constructor(reverse: kotlin.Boolean) {
    protected final val reverse: kotlin.Boolean /* compiled code */

    public abstract fun boundaryValue(entryPoint: M): A

    public abstract fun copy(src: A): A

    public abstract fun doAnalysis(entries: kotlin.collections.Collection<M>): kotlin.Unit

    public abstract fun meet(op1: A, op2: A): A

    public abstract fun shallowMeet(op1: A, op2: A): A

    public abstract fun merge(local: A, ret: A): A

    public abstract fun programRepresentation(): cn.sast.idfa.analysis.ProgramRepresentation<M, N>

    public abstract fun bottomValue(): A
}

