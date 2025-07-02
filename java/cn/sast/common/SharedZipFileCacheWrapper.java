package cn.sast.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.ForwardingLoadingCache;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.io.Closeable;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import org.apache.commons.compress.archivers.zip.ZipFile;
import soot.util.SharedCloseable;

/* loaded from: SharedZipFileCacheWrapper.class */
public class SharedZipFileCacheWrapper {
    private final SharedResourceCache<Path, ZipFile> cache;

    /* loaded from: SharedZipFileCacheWrapper$SharedResourceCache.class */
    private static class SharedResourceCache<K, V extends Closeable> extends ForwardingLoadingCache<K, SharedCloseable<V>> {
        private final LoadingCache<K, SharedCloseable<V>> delegate;
        private final DelayedRemovalListener<K, SharedCloseable<V>> removalListener = new DelayedRemovalListener<>();

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: get, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object m94get(Object obj) throws ExecutionException {
            return get((SharedResourceCache<K, V>) obj);
        }

        /* loaded from: SharedZipFileCacheWrapper$SharedResourceCache$DelayedRemovalListener.class */
        private static class DelayedRemovalListener<K, V extends SharedCloseable<?>> implements RemovalListener<K, V> {
            private static final BiFunction<Object, Integer, Integer> INC;
            private static final BiFunction<Object, Integer, Integer> DEC;
            private final ConcurrentHashMap<K, Integer> delayed = new ConcurrentHashMap<>();
            private final Queue<RemovalNotification<K, V>> delayQueue = new ConcurrentLinkedQueue();
            static final /* synthetic */ boolean $assertionsDisabled;

            private DelayedRemovalListener() {
            }

            static {
                $assertionsDisabled = !SharedZipFileCacheWrapper.class.desiredAssertionStatus();
                INC = new BiFunction<Object, Integer, Integer>() { // from class: cn.sast.common.SharedZipFileCacheWrapper.SharedResourceCache.DelayedRemovalListener.1
                    @Override // java.util.function.BiFunction
                    public Integer apply(Object t, Integer u) {
                        return Integer.valueOf(u == null ? 1 : u.intValue() + 1);
                    }
                };
                DEC = new BiFunction<Object, Integer, Integer>() { // from class: cn.sast.common.SharedZipFileCacheWrapper.SharedResourceCache.DelayedRemovalListener.2
                    @Override // java.util.function.BiFunction
                    public Integer apply(Object t, Integer u) {
                        if (u.intValue() == 1) {
                            return null;
                        }
                        return Integer.valueOf(u.intValue() - 1);
                    }
                };
            }

            public void onRemoval(RemovalNotification<K, V> rn) {
                process();
                removeOrEnqueue(rn, this.delayQueue);
            }

            public void delay(K key) {
                Integer val = this.delayed.compute(key, INC);
                if ($assertionsDisabled) {
                    return;
                }
                if (val == null || val.intValue() <= 0) {
                    throw new AssertionError();
                }
            }

            public void release(K key) {
                Integer val = this.delayed.compute(key, DEC);
                if (!$assertionsDisabled && val != null && val.intValue() <= 0) {
                    throw new AssertionError();
                }
                process();
            }

            private void process() {
                Queue<RemovalNotification<K, V>> delayFurther = new LinkedList<>();
                while (true) {
                    RemovalNotification<K, V> rn = this.delayQueue.poll();
                    if (rn != null) {
                        removeOrEnqueue(rn, delayFurther);
                    } else {
                        this.delayQueue.addAll(delayFurther);
                        return;
                    }
                }
            }

            private void removeOrEnqueue(RemovalNotification<K, V> rn, Queue<RemovalNotification<K, V>> q) {
                if (this.delayed.containsKey(rn.getKey())) {
                    q.offer(rn);
                    return;
                }
                SharedCloseable sharedCloseable = (SharedCloseable) rn.getValue();
                if (!$assertionsDisabled && sharedCloseable == null) {
                    throw new AssertionError();
                }
                sharedCloseable.release();
            }
        }

        public SharedResourceCache(int initSize, int maxSize, final CacheLoader<K, V> loader) {
            CacheBuilder<K, SharedCloseable<V>> builder = CacheBuilder.newBuilder().concurrencyLevel(OS.INSTANCE.getMaxThreadNum()).expireAfterAccess(15L, TimeUnit.SECONDS).removalListener(this.removalListener);
            builder = initSize > 0 ? builder.initialCapacity(initSize) : builder;
            this.delegate = (maxSize > 0 ? builder.maximumSize(maxSize) : builder).build(new CacheLoader<K, SharedCloseable<V>>() { // from class: cn.sast.common.SharedZipFileCacheWrapper.SharedResourceCache.1
                /* renamed from: load, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ Object m97load(Object obj) throws Exception {
                    return load((AnonymousClass1) obj);
                }

                public SharedCloseable<V> load(K key) throws Exception {
                    return new SharedCloseable<>((Closeable) loader.load(key));
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: delegate, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final LoadingCache<K, SharedCloseable<V>> m96delegate() {
            return this.delegate;
        }

        public final SharedCloseable<V> get(K key) throws ExecutionException {
            this.removalListener.delay(key);
            try {
                return ((SharedCloseable) super.get(key)).acquire();
            } finally {
                this.removalListener.release(key);
            }
        }
    }

    public SharedZipFileCacheWrapper(int initSize, int maxSize) {
        this.cache = new SharedResourceCache<>(initSize, maxSize, new CacheLoader<Path, ZipFile>() { // from class: cn.sast.common.SharedZipFileCacheWrapper.1
            public ZipFile load(Path archivePath) throws Exception {
                return new ZipFile(archivePath);
            }
        });
    }

    public SharedCloseable<ZipFile> getRef(Path archivePath) throws ExecutionException {
        return this.cache.get((SharedResourceCache<Path, ZipFile>) archivePath);
    }

    public void invalidateAll() {
        this.cache.invalidateAll();
    }
}
