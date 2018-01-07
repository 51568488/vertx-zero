package io.vertx.quiz.fakecluster;


import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.spi.cluster.ChoosableIterable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
class ChoosableSet<T> implements ChoosableIterable<T> {

    private final Set<T> ids;
    private volatile Iterator<T> iter;

    public ChoosableSet(final int initialSize) {
        this.ids = new ConcurrentHashSet<>(initialSize);
    }

    public int size() {
        return this.ids.size();
    }

    public void add(final T elem) {
        this.ids.add(elem);
    }

    public boolean remove(final T elem) {
        return this.ids.remove(elem);
    }

    public void merge(final ChoosableSet<T> toMerge) {
        this.ids.addAll(toMerge.ids);
    }

    @Override
    public boolean isEmpty() {
        return this.ids.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.ids.iterator();
    }

    @Override
    public synchronized T choose() {
        if (!this.ids.isEmpty()) {
            if (this.iter == null || !this.iter.hasNext()) {
                this.iter = this.ids.iterator();
            }
            try {
                return this.iter.next();
            } catch (final NoSuchElementException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}

