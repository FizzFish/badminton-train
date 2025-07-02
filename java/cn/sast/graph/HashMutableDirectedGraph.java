package cn.sast.graph;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soot.toolkits.graph.MutableDirectedGraph;

/* compiled from: HashMutableDirectedGraph.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��l\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0010%\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018�� @*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0002@AB\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0005B\u0017\b\u0016\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0��¢\u0006\u0004\b\u0004\u0010\u0007Bs\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f\u0018\u00010\u000b\u0012\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f\u0018\u00010\u000b\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\f\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0004\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0\u0018H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028��0\u0018H\u0016J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028��0\u00182\u0006\u0010\u001b\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001e2\u0006\u0010\u001b\u001a\u00028��¢\u0006\u0002\u0010\u001fJ\u001b\u0010 \u001a\b\u0012\u0004\u0012\u00028��0\u00182\u0006\u0010\u001b\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u001cJ\u0019\u0010!\u001a\b\u0012\u0004\u0012\u00028��0\u001e2\u0006\u0010\u001b\u001a\u00028��¢\u0006\u0002\u0010\u001fJ\b\u0010\"\u001a\u00020\tH\u0016J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00028��0$H\u0096\u0002J\u001d\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00028��2\u0006\u0010'\u001a\u00028��H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00020\u00162\u0006\u0010&\u001a\u00028��2\u0006\u0010'\u001a\u00028��H\u0016¢\u0006\u0002\u0010(J\u0015\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00028��H\u0002¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020.2\u0006\u0010&\u001a\u00028��2\u0006\u0010'\u001a\u00028��H\u0016¢\u0006\u0002\u0010/J\u0015\u00100\u001a\u00020.2\u0006\u00101\u001a\u00028��H\u0016¢\u0006\u0002\u00102J\u000e\u00103\u001a\b\u0012\u0004\u0012\u00028��0\u0018H\u0016J\u0015\u00104\u001a\u00020\u00162\u0006\u00101\u001a\u00028��H\u0016¢\u0006\u0002\u0010,J\u0015\u00105\u001a\u00020\u00162\u0006\u00101\u001a\u00028��H\u0016¢\u0006\u0002\u0010,J\u0006\u00106\u001a\u00020\u0016JE\u00107\u001a\u00020\u0016\"\n\b\u0001\u0010\u0001*\u0004\u0018\u00010\u00142\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u00010��2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H\u00010>H\u0001¢\u0006\u0002\b?R \u0010\n\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f0\u000bX\u0082\u0004¢\u0006\u0002\n��R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f0\u000bX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028��0\fX\u0082\u0004¢\u0006\u0002\n��¨\u0006B"}, d2 = {"Lcn/sast/graph/HashMutableDirectedGraph;", "N", "Lsoot/toolkits/graph/MutableDirectedGraph;", "", "<init>", "()V", "orig", "(Lcn/sast/graph/HashMutableDirectedGraph;)V", "seen0", "", "nodeToPreds", "", "", "nodeToSuccs", "heads", "tails", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Set;Ljava/util/Set;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "clone", "", "clearAll", "", "getHeads", "", "getTails", "getPredsOf", "s", "(Ljava/lang/Object;)Ljava/util/List;", "getPredsOfAsSet", "", "(Ljava/lang/Object;)Ljava/util/Set;", "getSuccsOf", "getSuccsOfAsSet", "size", "iterator", "", "addEdge", "from", "to", "(Ljava/lang/Object;Ljava/lang/Object;)V", "removeEdge", "removeSingle", "n", "(Ljava/lang/Object;)V", "containsEdge", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "containsNode", "node", "(Ljava/lang/Object;)Z", "getNodes", "addNode", "removeNode", "printGraph", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "write$Self$corax_api", "Companion", "$serializer", "corax-api"})
@SourceDebugExtension({"SMAP\nHashMutableDirectedGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HashMutableDirectedGraph.kt\ncn/sast/graph/HashMutableDirectedGraph\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,220:1\n381#2,7:221\n381#2,7:228\n381#2,7:235\n381#2,7:242\n*S KotlinDebug\n*F\n+ 1 HashMutableDirectedGraph.kt\ncn/sast/graph/HashMutableDirectedGraph\n*L\n106#1:221,7\n110#1:228,7\n171#1:235,7\n172#1:242,7\n*E\n"})
/* loaded from: HashMutableDirectedGraph.class */
public final class HashMutableDirectedGraph<N> implements MutableDirectedGraph<N>, Cloneable {

    @NotNull
    private final Map<N, Set<N>> nodeToPreds;

    @NotNull
    private final Map<N, Set<N>> nodeToSuccs;

    @NotNull
    private final Set<N> heads;

    @NotNull
    private final Set<N> tails;

    @JvmField
    @NotNull
    private static final SerialDescriptor $cachedDescriptor;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(HashMutableDirectedGraph.class);

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(HashMutableDirectedGraph self, CompositeEncoder output, SerialDescriptor serialDesc, KSerializer typeSerial0) {
        output.encodeSerializableElement(serialDesc, 0, new LinkedHashMapSerializer(typeSerial0, new LinkedHashSetSerializer(typeSerial0)), self.nodeToPreds);
        output.encodeSerializableElement(serialDesc, 1, new LinkedHashMapSerializer(typeSerial0, new LinkedHashSetSerializer(typeSerial0)), self.nodeToSuccs);
        output.encodeSerializableElement(serialDesc, 2, new LinkedHashSetSerializer(typeSerial0), self.heads);
        output.encodeSerializableElement(serialDesc, 3, new LinkedHashSetSerializer(typeSerial0), self.tails);
    }

    public /* synthetic */ HashMutableDirectedGraph(int seen0, Map nodeToPreds, Map nodeToSuccs, Set heads, Set tails, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (15 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 15, $cachedDescriptor);
        }
        this.nodeToPreds = nodeToPreds;
        this.nodeToSuccs = nodeToSuccs;
        this.heads = heads;
        this.tails = tails;
    }

    public HashMutableDirectedGraph() {
        this.nodeToPreds = new HashMap();
        this.nodeToSuccs = new HashMap();
        this.heads = new HashSet();
        this.tails = new HashSet();
    }

    public HashMutableDirectedGraph(@NotNull HashMutableDirectedGraph<N> hashMutableDirectedGraph) {
        Intrinsics.checkNotNullParameter(hashMutableDirectedGraph, "orig");
        this.nodeToPreds = Companion.deepCopy(hashMutableDirectedGraph.nodeToPreds);
        this.nodeToSuccs = Companion.deepCopy(hashMutableDirectedGraph.nodeToSuccs);
        this.heads = new HashSet(hashMutableDirectedGraph.heads);
        this.tails = new HashSet(hashMutableDirectedGraph.tails);
    }

    @NotNull
    public Object clone() {
        return new HashMutableDirectedGraph(this);
    }

    public final void clearAll() {
        this.nodeToPreds.clear();
        this.nodeToSuccs.clear();
        this.heads.clear();
        this.tails.clear();
    }

    @NotNull
    public List<N> getHeads() {
        return Companion.getCopy(this.heads);
    }

    @NotNull
    public List<N> getTails() {
        return Companion.getCopy(this.tails);
    }

    @NotNull
    public List<N> getPredsOf(N n) {
        Set preds = this.nodeToPreds.get(n);
        if (preds == null) {
            return CollectionsKt.emptyList();
        }
        return Companion.getCopy(preds);
    }

    @NotNull
    public final Set<N> getPredsOfAsSet(N n) {
        Set preds = this.nodeToPreds.get(n);
        if (preds != null) {
            Set<N> setUnmodifiableSet = Collections.unmodifiableSet(preds);
            Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(...)");
            return setUnmodifiableSet;
        }
        return SetsKt.emptySet();
    }

    @NotNull
    public List<N> getSuccsOf(N n) {
        Set succs = this.nodeToSuccs.get(n);
        if (succs == null) {
            return CollectionsKt.emptyList();
        }
        return Companion.getCopy(succs);
    }

    @NotNull
    public final Set<N> getSuccsOfAsSet(N n) {
        Set succs = this.nodeToSuccs.get(n);
        if (succs != null) {
            Set<N> setUnmodifiableSet = Collections.unmodifiableSet(succs);
            Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(...)");
            return setUnmodifiableSet;
        }
        return SetsKt.emptySet();
    }

    public int size() {
        return this.nodeToPreds.keySet().size();
    }

    @NotNull
    public Iterator<N> iterator() {
        return this.nodeToPreds.keySet().iterator();
    }

    public void addEdge(N n, N n2) {
        Object obj;
        Object obj2;
        if (containsEdge(n, n2)) {
            return;
        }
        Map $this$getOrPut$iv = this.nodeToSuccs;
        Object value$iv = $this$getOrPut$iv.get(n);
        if (value$iv == null) {
            this.heads.add(n);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            $this$getOrPut$iv.put(n, linkedHashSet);
            obj = linkedHashSet;
        } else {
            obj = value$iv;
        }
        Set succsList = (Set) obj;
        Map $this$getOrPut$iv2 = this.nodeToPreds;
        Object value$iv2 = $this$getOrPut$iv2.get(n2);
        if (value$iv2 == null) {
            this.tails.add(n2);
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            $this$getOrPut$iv2.put(n2, linkedHashSet2);
            obj2 = linkedHashSet2;
        } else {
            obj2 = value$iv2;
        }
        Set predsList = (Set) obj2;
        this.heads.remove(n2);
        this.tails.remove(n);
        succsList.add(n2);
        predsList.add(n);
    }

    public void removeEdge(N n, N n2) {
        Set succs = this.nodeToSuccs.get(n);
        if (succs == null || !succs.contains(n2)) {
            return;
        }
        Set preds = this.nodeToPreds.get(n2);
        if (preds == null) {
            throw new RuntimeException(n2 + " not in graph!");
        }
        succs.remove(n2);
        preds.remove(n);
        if (succs.isEmpty()) {
            this.tails.add(n);
            this.nodeToSuccs.remove(n);
        }
        if (preds.isEmpty()) {
            this.heads.add(n2);
            this.nodeToPreds.remove(n2);
        }
        removeSingle(n);
        removeSingle(n2);
    }

    private final void removeSingle(N n) {
        boolean z;
        boolean z2;
        Set succs = this.nodeToSuccs.get(n);
        Set preds = this.nodeToPreds.get(n);
        if (succs != null) {
            z = !succs.isEmpty();
        } else {
            z = false;
        }
        if (!z && this.heads.contains(n)) {
            this.heads.remove(n);
        }
        if (preds != null) {
            z2 = !preds.isEmpty();
        } else {
            z2 = false;
        }
        if (!z2 && this.tails.contains(n)) {
            this.tails.remove(n);
        }
    }

    public boolean containsEdge(N n, N n2) {
        Set succs = this.nodeToSuccs.get(n);
        return succs != null && succs.contains(n2);
    }

    public boolean containsNode(N n) {
        return this.nodeToPreds.keySet().contains(n);
    }

    @NotNull
    public List<N> getNodes() {
        return Companion.getCopy(this.nodeToPreds.keySet());
    }

    public void addNode(N n) {
        if (containsNode(n)) {
            return;
        }
        Map $this$getOrPut$iv = this.nodeToSuccs;
        Object value$iv = $this$getOrPut$iv.get(n);
        if (value$iv == null) {
            $this$getOrPut$iv.put(n, new LinkedHashSet());
        }
        Map $this$getOrPut$iv2 = this.nodeToPreds;
        Object value$iv2 = $this$getOrPut$iv2.get(n);
        if (value$iv2 == null) {
            $this$getOrPut$iv2.put(n, new LinkedHashSet());
        }
        this.heads.add(n);
        this.tails.add(n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeNode(N n) {
        Iterator it = new ArrayList(this.nodeToSuccs.get(n)).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Object n2 = it.next();
            removeEdge(n, n2);
        }
        this.nodeToSuccs.remove(n);
        Iterator it2 = new ArrayList(this.nodeToPreds.get(n)).iterator();
        Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
        while (it2.hasNext()) {
            Object n3 = it2.next();
            removeEdge(n3, n);
        }
        this.nodeToPreds.remove(n);
        this.heads.remove(n);
        this.tails.remove(n);
    }

    public final void printGraph() {
        Iterator<N> it = iterator();
        while (it.hasNext()) {
            N next = it.next();
            logger.debug("Node = " + next);
            logger.debug("Preds:");
            for (Object p : getPredsOf(next)) {
                logger.debug("     ");
                logger.debug(p);
            }
            logger.debug("Succs:");
            for (Object s : getSuccsOf(next)) {
                logger.debug("     ");
                logger.debug(s);
            }
        }
    }

    /* compiled from: HashMutableDirectedGraph.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001e\n��\n\u0002\u0010%\n��\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0001\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH\u0002J@\u0010\r\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00100\u000e\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00100\u0013H\u0002J&\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u00160\u0015\"\u0004\b\u0001\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0015R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcn/sast/graph/HashMutableDirectedGraph$Companion;", "", "<init>", "()V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "Lorg/slf4j/Logger;", "getCopy", "", "T", "c", "", "deepCopy", "", "A", "", "B", "in", "", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/graph/HashMutableDirectedGraph;", "N", "typeSerial0", "corax-api"})
    /* loaded from: HashMutableDirectedGraph$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final <N> KSerializer<HashMutableDirectedGraph<N>> serializer(@NotNull KSerializer<N> kSerializer) {
            Intrinsics.checkNotNullParameter(kSerializer, "typeSerial0");
            return new HashMutableDirectedGraph$$serializer<>(kSerializer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T> List<T> getCopy(Collection<? extends T> collection) {
            List<T> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
            Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(...)");
            return listUnmodifiableList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <A, B> Map<A, Set<B>> deepCopy(Map<A, ? extends Set<B>> map) {
            HashMap retVal = new HashMap(map);
            for (Object obj : retVal.entrySet()) {
                Intrinsics.checkNotNullExpressionValue(obj, "next(...)");
                Map.Entry e = (Map.Entry) obj;
                e.setValue(new LinkedHashSet((Collection) e.getValue()));
            }
            return retVal;
        }
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.graph.HashMutableDirectedGraph", (GeneratedSerializer) null, 4);
        pluginGeneratedSerialDescriptor.addElement("nodeToPreds", false);
        pluginGeneratedSerialDescriptor.addElement("nodeToSuccs", false);
        pluginGeneratedSerialDescriptor.addElement("heads", false);
        pluginGeneratedSerialDescriptor.addElement("tails", false);
        $cachedDescriptor = pluginGeneratedSerialDescriptor;
    }
}
