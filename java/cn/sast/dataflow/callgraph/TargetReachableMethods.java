package cn.sast.dataflow.callgraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import soot.MethodOrMethodContext;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.callgraph.Filter;
import soot.jimple.toolkits.callgraph.Sources;
import soot.util.queue.ChunkedQueue;
import soot.util.queue.QueueReader;

/* loaded from: TargetReachableMethods.class */
public class TargetReachableMethods {
    protected final ChunkedQueue<MethodOrMethodContext> reachables;
    protected final Set<MethodOrMethodContext> set;
    protected final QueueReader<MethodOrMethodContext> allReachables;
    protected QueueReader<MethodOrMethodContext> unprocessedMethods;
    protected Iterator<Edge> edgeSource;
    protected CallGraph cg;
    protected Filter filter;

    public TargetReachableMethods(CallGraph graph, Iterator<? extends MethodOrMethodContext> lookupPoint, Filter filter) {
        this.reachables = new ChunkedQueue<>();
        this.set = new HashSet();
        this.allReachables = this.reachables.reader();
        this.filter = filter;
        this.cg = graph;
        addMethods(lookupPoint);
        this.unprocessedMethods = this.reachables.reader();
        this.edgeSource = filter == null ? graph.listener() : filter.wrap(graph.listener());
    }

    public TargetReachableMethods(CallGraph graph, Iterator<? extends MethodOrMethodContext> lookupPoint) {
        this(graph, lookupPoint, null);
    }

    public TargetReachableMethods(CallGraph graph, Collection<? extends MethodOrMethodContext> lookupPoint) {
        this(graph, lookupPoint.iterator());
    }

    protected void addMethods(Iterator<? extends MethodOrMethodContext> methods) {
        while (methods.hasNext()) {
            addMethod(methods.next());
        }
    }

    protected void addMethod(MethodOrMethodContext m) {
        if (this.set.add(m)) {
            this.reachables.add(m);
        }
    }

    public void update() {
        MethodOrMethodContext tgtMethod;
        while (this.edgeSource.hasNext()) {
            Edge e = this.edgeSource.next();
            if (e != null && (tgtMethod = e.getTgt()) != null && !e.isInvalid() && this.set.contains(tgtMethod)) {
                addMethod(e.getSrc());
            }
        }
        while (this.unprocessedMethods.hasNext()) {
            MethodOrMethodContext m = (MethodOrMethodContext) this.unprocessedMethods.next();
            Iterator<Edge> sources = this.cg.edgesInto(m);
            if (this.filter != null) {
                sources = this.filter.wrap(sources);
            }
            addMethods(new Sources(sources));
        }
    }

    public QueueReader<MethodOrMethodContext> listener() {
        return this.allReachables.clone();
    }

    public QueueReader<MethodOrMethodContext> newListener() {
        return this.reachables.reader();
    }

    public boolean contains(MethodOrMethodContext m) {
        return this.set.contains(m);
    }

    public int size() {
        return this.set.size();
    }
}
