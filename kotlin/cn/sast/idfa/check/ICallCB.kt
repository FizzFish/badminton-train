// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.check

public interface ICallCB<V, R> {
    public abstract val global: V

    public abstract var `return`: V

    public abstract val `this`: V

    public abstract var out: R

    public abstract fun arg(argIndex: kotlin.Int): V

    public abstract fun argToValue(argIndex: kotlin.Int): kotlin.Any
}

