// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.check

public interface ICalleeCB<V, R> : cn.sast.idfa.check.IStmtCB, cn.sast.idfa.check.ICallCB<V, R> {
    public abstract val callee: soot.SootMethod

    public interface IPrevCall<V, R> : cn.sast.idfa.check.ICalleeCB<V, R>, cn.sast.idfa.check.IPrevCB {
    }

    public interface IEvalCall<V, R> : cn.sast.idfa.check.ICalleeCB<V, R>, cn.sast.idfa.check.IEvalCallCB<V, R> {
    }

    public interface IPostCall<V, R> : cn.sast.idfa.check.ICalleeCB<V, R>, cn.sast.idfa.check.IPostCallCB<V, R> {
    }
}

