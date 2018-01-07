package io.vertx.quiz.fakecluster;


import io.vertx.core.*;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.TaskQueue;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.impl.AsynchronousCounter;
import io.vertx.core.shareddata.impl.AsynchronousLock;
import io.vertx.core.spi.cluster.AsyncMultiMap;
import io.vertx.core.spi.cluster.ChoosableIterable;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeListener;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class FakeClusterManager implements ClusterManager {

    private static final Map<String, FakeClusterManager> nodes = Collections.synchronizedMap(new LinkedHashMap<>());

    private static final ConcurrentMap<String, ConcurrentMap> asyncMaps = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, ConcurrentMap> asyncMultiMaps = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Map> syncMaps = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, AsynchronousLock> locks = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, AtomicLong> counters = new ConcurrentHashMap<>();

    private String nodeID;
    private NodeListener nodeListener;
    private VertxInternal vertx;

    @Override
    public void setVertx(final Vertx vertx) {
        this.vertx = (VertxInternal) vertx;
    }

    private static void doJoin(final String nodeID, final FakeClusterManager node) {
        if (nodes.containsKey(nodeID)) {
            throw new IllegalStateException("Node has already joined!");
        }
        nodes.put(nodeID, node);
        synchronized (nodes) {
            for (final Entry<String, FakeClusterManager> entry : nodes.entrySet()) {
                if (!entry.getKey().equals(nodeID)) {
                    new Thread(() -> entry.getValue().memberAdded(nodeID)).start();
                }
            }
        }
    }

    private synchronized void memberAdded(final String nodeID) {
        if (isActive()) {
            try {
                if (this.nodeListener != null) {
                    this.nodeListener.nodeAdded(nodeID);
                }
            } catch (final Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private static void doLeave(final String nodeID) {
        nodes.remove(nodeID);
        synchronized (nodes) {
            for (final Entry<String, FakeClusterManager> entry : nodes.entrySet()) {
                if (!entry.getKey().equals(nodeID)) {
                    new Thread(() -> entry.getValue().memberRemoved(nodeID)).start();
                }
            }
        }
    }

    private synchronized void memberRemoved(final String nodeID) {
        if (isActive()) {
            try {
                if (this.nodeListener != null) {
                    this.nodeListener.nodeLeft(nodeID);
                }
            } catch (final Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    public <K, V> void getAsyncMultiMap(final String name, final Handler<AsyncResult<AsyncMultiMap<K, V>>> resultHandler) {
        ConcurrentMap map = asyncMultiMaps.get(name);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            final ConcurrentMap prevMap = asyncMultiMaps.putIfAbsent(name, map);
            if (prevMap != null) {
                map = prevMap;
            }
        }
        @SuppressWarnings("unchecked") final ConcurrentMap<K, ChoosableSet<V>> theMap = map;
        this.vertx.runOnContext(v -> resultHandler.handle(Future.succeededFuture(new FakeAsyncMultiMap<>(theMap))));
    }

    @Override
    public <K, V> void getAsyncMap(final String name, final Handler<AsyncResult<AsyncMap<K, V>>> resultHandler) {
        ConcurrentMap map = asyncMaps.get(name);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            final ConcurrentMap prevMap = asyncMaps.putIfAbsent(name, map);
            if (prevMap != null) {
                map = prevMap;
            }
        }
        @SuppressWarnings("unchecked") final ConcurrentMap<K, V> theMap = map;
        this.vertx.runOnContext(v -> resultHandler.handle(Future.succeededFuture(new FakeAsyncMap<>(theMap))));
    }

    @Override
    public <K, V> Map<K, V> getSyncMap(final String name) {
        Map map = syncMaps.get(name);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            final Map prevMap = syncMaps.putIfAbsent(name, map);
            if (prevMap != null) {
                map = prevMap;
            }
        }
        @SuppressWarnings("unchecked") final Map<K, V> theMap = map;
        return theMap;
    }

    @Override
    public void getLockWithTimeout(final String name, final long timeout, final Handler<AsyncResult<Lock>> resultHandler) {
        AsynchronousLock lock = new AsynchronousLock(this.vertx);
        final AsynchronousLock prev = locks.putIfAbsent(name, lock);
        if (prev != null) {
            lock = prev;
        }
        final FakeLock flock = new FakeLock(lock);
        flock.acquire(timeout, resultHandler);
    }

    @Override
    public void getCounter(final String name, final Handler<AsyncResult<Counter>> resultHandler) {
        AtomicLong counter = new AtomicLong();
        final AtomicLong prev = counters.putIfAbsent(name, counter);
        if (prev != null) {
            counter = prev;
        }
        final AtomicLong theCounter = counter;
        final Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> resultHandler.handle(Future.succeededFuture(new AsynchronousCounter(this.vertx, theCounter))));
    }

    @Override
    public String getNodeID() {
        return this.nodeID;
    }

    @Override
    public List<String> getNodes() {
        final ArrayList<String> res;
        synchronized (nodes) {
            res = new ArrayList<>(nodes.keySet());
        }
        return res;
    }

    @Override
    public void nodeListener(final NodeListener listener) {
        this.nodeListener = listener;
    }

    @Override
    public void join(final Handler<AsyncResult<Void>> resultHandler) {
        this.vertx.executeBlocking(fut -> {
            synchronized (this) {
                this.nodeID = UUID.randomUUID().toString();
                doJoin(this.nodeID, this);
            }
            fut.complete();
        }, resultHandler);
    }

    @Override
    public void leave(final Handler<AsyncResult<Void>> resultHandler) {
        this.vertx.executeBlocking(fut -> {
            synchronized (this) {
                if (this.nodeID != null) {
                    if (this.nodeListener != null) {
                        this.nodeListener = null;
                    }
                    doLeave(this.nodeID);
                    this.nodeID = null;
                }
            }
            fut.complete();
        }, resultHandler);
    }

    @Override
    public boolean isActive() {
        return this.nodeID != null;
    }

    public static void reset() {
        nodes.clear();
        asyncMaps.clear();
        asyncMultiMaps.clear();
        locks.clear();
        counters.clear();
        syncMaps.clear();
    }

    private class FakeLock implements Lock {

        private final AsynchronousLock delegate;

        public FakeLock(final AsynchronousLock delegate) {
            this.delegate = delegate;
        }

        public void acquire(final long timeout, final Handler<AsyncResult<Lock>> resultHandler) {
            final Context context = FakeClusterManager.this.vertx.getOrCreateContext();
            this.delegate.doAcquire(context, timeout, resultHandler);
        }

        @Override
        public void release() {
            this.delegate.release();
        }
    }

    private class FakeAsyncMap<K, V> implements AsyncMap<K, V> {

        private final Map<K, V> map;

        public FakeAsyncMap(final Map<K, V> map) {
            this.map = map;
        }

        @Override
        public void get(final K k, final Handler<AsyncResult<V>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.get(k)), resultHandler);
        }

        @Override
        public void put(final K k, final V v, final Handler<AsyncResult<Void>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> {
                this.map.put(k, v);
                fut.complete();
            }, resultHandler);
        }

        @Override
        public void putIfAbsent(final K k, final V v, final Handler<AsyncResult<V>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.putIfAbsent(k, v)), resultHandler);
        }

        @Override
        public void put(final K k, final V v, final long timeout, final Handler<AsyncResult<Void>> completionHandler) {
            put(k, v, completionHandler);
            FakeClusterManager.this.vertx.setTimer(timeout, tid -> this.map.remove(k));
        }

        @Override
        public void putIfAbsent(final K k, final V v, final long timeout, final Handler<AsyncResult<V>> completionHandler) {
            final Future<V> future = Future.future();
            putIfAbsent(k, v, future);
            future.map(vv -> {
                if (vv == null) {
                    FakeClusterManager.this.vertx.setTimer(timeout, tid -> this.map.remove(k));
                }
                return vv;
            }).setHandler(completionHandler);
        }

        @Override
        public void removeIfPresent(final K k, final V v, final Handler<AsyncResult<Boolean>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.remove(k, v)), resultHandler);
        }

        @Override
        public void replace(final K k, final V v, final Handler<AsyncResult<V>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.replace(k, v)), resultHandler);
        }

        @Override
        public void replaceIfPresent(final K k, final V oldValue, final V newValue, final Handler<AsyncResult<Boolean>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.replace(k, oldValue, newValue)), resultHandler);
        }

        @Override
        public void clear(final Handler<AsyncResult<Void>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> {
                this.map.clear();
                fut.complete();
            }, resultHandler);
        }

        @Override
        public void size(final Handler<AsyncResult<Integer>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.size()), resultHandler);
        }

        @Override
        public void keys(final Handler<AsyncResult<Set<K>>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(new HashSet<>(this.map.keySet())), resultHandler);
        }

        @Override
        public void values(final Handler<AsyncResult<List<V>>> asyncResultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(new ArrayList<>(this.map.values())), asyncResultHandler);
        }

        @Override
        public void entries(final Handler<AsyncResult<Map<K, V>>> asyncResultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(new HashMap<>(this.map)), asyncResultHandler);
        }

        @Override
        public void remove(final K k, final Handler<AsyncResult<V>> resultHandler) {
            FakeClusterManager.this.vertx.executeBlocking(fut -> fut.complete(this.map.remove(k)), resultHandler);
        }

    }

    private class FakeAsyncMultiMap<K, V> implements AsyncMultiMap<K, V> {

        private final ConcurrentMap<K, ChoosableSet<V>> map;
        private final TaskQueue taskQueue;

        public FakeAsyncMultiMap(final ConcurrentMap<K, ChoosableSet<V>> map) {
            this.taskQueue = new TaskQueue();
            this.map = map;
        }

        @Override
        public void add(final K k, final V v, final Handler<AsyncResult<Void>> completionHandler) {
            final ContextInternal ctx = FakeClusterManager.this.vertx.getOrCreateContext();
            ctx.executeBlocking(fut -> {
                ChoosableSet<V> vals = this.map.get(k);
                if (vals == null) {
                    vals = new ChoosableSet<>(1);
                    final ChoosableSet<V> prevVals = this.map.putIfAbsent(k, vals);
                    if (prevVals != null) {
                        vals = prevVals;
                    }
                }
                vals.add(v);
                fut.complete();
            }, this.taskQueue, completionHandler);
        }

        @Override
        public void get(final K k, final Handler<AsyncResult<ChoosableIterable<V>>> asyncResultHandler) {
            final ContextInternal ctx = FakeClusterManager.this.vertx.getOrCreateContext();
            ctx.executeBlocking(fut -> {
                ChoosableIterable<V> it = this.map.get(k);
                if (it == null) {
                    it = new ChoosableSet<>(0);
                }
                fut.complete(it);
            }, this.taskQueue, asyncResultHandler);
        }

        @Override
        public void remove(final K k, final V v, final Handler<AsyncResult<Boolean>> completionHandler) {
            final ContextInternal ctx = FakeClusterManager.this.vertx.getOrCreateContext();
            ctx.executeBlocking(fut -> {
                final ChoosableSet<V> vals = this.map.get(k);
                boolean found = false;
                if (vals != null) {
                    final boolean removed = vals.remove(v);
                    if (removed) {
                        if (vals.isEmpty()) {
                            this.map.remove(k);
                        }
                        found = true;
                    }
                }
                fut.complete(found);
            }, this.taskQueue, completionHandler);
        }

        @Override
        public void removeAllForValue(final V v, final Handler<AsyncResult<Void>> completionHandler) {
            removeAllMatching(v::equals, completionHandler);
        }

        @Override
        public void removeAllMatching(final Predicate<V> p, final Handler<AsyncResult<Void>> completionHandler) {
            final ContextInternal ctx = FakeClusterManager.this.vertx.getOrCreateContext();
            ctx.executeBlocking(fut -> {
                final Iterator<Entry<K, ChoosableSet<V>>> mapIter = this.map.entrySet().iterator();
                while (mapIter.hasNext()) {
                    final Entry<K, ChoosableSet<V>> entry = mapIter.next();
                    final ChoosableSet<V> vals = entry.getValue();
                    final Iterator<V> iter = vals.iterator();
                    while (iter.hasNext()) {
                        final V val = iter.next();
                        if (p.test(val)) {
                            iter.remove();
                        }
                    }
                    if (vals.isEmpty()) {
                        mapIter.remove();
                    }
                }
                fut.complete();
            }, this.taskQueue, completionHandler);
        }
    }
}

