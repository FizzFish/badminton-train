package cn.sast.framework.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.Orderer;

/* loaded from: PseudoTopologicalOrderer.class */
public class PseudoTopologicalOrderer<N> implements Orderer<N> {
    public static final boolean REVERSE = true;
    private boolean mIsReversed;

    /* loaded from: PseudoTopologicalOrderer$ReverseOrderBuilder.class */
    private static class ReverseOrderBuilder<N> {
        private final DirectedGraph<N> graph;
        private final int graphSize;
        private final int[] indexStack;
        private final N[] stmtStack;
        private final Set<N> visited;
        private final N[] order;
        private int orderLength;

        public ReverseOrderBuilder(DirectedGraph<N> directedGraph) {
            this.graph = directedGraph;
            int size = directedGraph.size();
            this.graphSize = size;
            this.visited = Collections.newSetFromMap(new IdentityHashMap((size * 2) + 1));
            this.indexStack = new int[size];
            this.stmtStack = (N[]) new Object[size];
            this.order = (N[]) new Object[size];
            this.orderLength = 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public List<N> computeOrder(boolean z) {
            for (Object obj : this.graph) {
                if (this.visited.add(obj)) {
                    visitNode(obj);
                }
                if (this.orderLength == this.graphSize) {
                    break;
                }
            }
            if (z) {
                reverseArray(this.order);
            }
            return Arrays.asList(this.order);
        }

        private void visitNode(N n) {
            this.stmtStack[0] = n;
            int i = 0 + 1;
            this.indexStack[0] = -1;
            while (i > 0) {
                int[] iArr = this.indexStack;
                int i2 = i - 1;
                int i3 = iArr[i2] + 1;
                iArr[i2] = i3;
                N n2 = this.stmtStack[i - 1];
                List succsOf = this.graph.getSuccsOf(n2);
                if (i3 >= succsOf.size()) {
                    N[] nArr = this.order;
                    int i4 = this.orderLength;
                    this.orderLength = i4 + 1;
                    nArr[i4] = n2;
                    i--;
                } else {
                    Object obj = succsOf.get(i3);
                    if (this.visited.add(obj)) {
                        ((N[]) this.stmtStack)[i] = obj;
                        int i5 = i;
                        i++;
                        this.indexStack[i5] = -1;
                    }
                }
            }
        }

        private static <T> void reverseArray(T[] array) {
            int max = array.length >> 1;
            int i = 0;
            int j = array.length - 1;
            while (i < max) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
    }

    public PseudoTopologicalOrderer() {
        this.mIsReversed = false;
    }

    public List<N> newList(DirectedGraph<N> g, boolean reverse) {
        this.mIsReversed = reverse;
        return new ReverseOrderBuilder(g).computeOrder(!reverse);
    }

    @Deprecated
    public PseudoTopologicalOrderer(boolean isReversed) {
        this.mIsReversed = false;
        this.mIsReversed = isReversed;
    }

    @Deprecated
    public List<N> newList(DirectedGraph<N> g) {
        return new ReverseOrderBuilder(g).computeOrder(!this.mIsReversed);
    }

    @Deprecated
    public void setReverseOrder(boolean isReversed) {
        this.mIsReversed = isReversed;
    }

    @Deprecated
    public boolean isReverseOrder() {
        return this.mIsReversed;
    }
}
