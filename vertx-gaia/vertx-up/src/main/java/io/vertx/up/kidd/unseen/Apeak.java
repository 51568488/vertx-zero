package io.vertx.up.kidd.unseen;

import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/**
 * Configuration to store the mapping
 */
public class Apeak implements Iterable<Map.Entry<String, String>> {

    private static final Annal LOGGER = Annal.get(Apeak.class);

    private transient final List<String> from = new ArrayList<>();
    private transient final List<String> to = new ArrayList<>();
    private transient final ConcurrentMap<String, String> vector =
            new ConcurrentHashMap<>();

    public static Apeak create(final String from, final String to) {
        return new Apeak(from, to);
    }

    private Apeak(final String from, final String to) {
        this.add(from, to);
    }

    public Apeak add(final String from, final String to) {
        if (this.from.contains(from) || this.to.contains(to)) {
            if (this.from.contains(from)) {
                LOGGER.warn(Info.EMPTY_ADDED, from, "From");
            }
            if (this.to.contains(to)) {
                LOGGER.warn(Info.EMPTY_ADDED, to, "To");
            }
        } else {
            this.from.add(from);
            this.to.add(to);
            this.vector.put(from, to);
        }
        return this;
    }

    public Apeak remove(final String from) {
        final int index = from.indexOf(from);
        if (Values.ZERO <= index) {
            this.remove(from);
            this.to.remove(this.to.get(index));
            this.vector.remove(from);
        }
        return this;
    }

    public Apeak clear() {
        this.from.clear();
        this.to.clear();
        this.vector.clear();
        return this;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return this.vector.entrySet().iterator();
    }

    @Override
    public void forEach(final Consumer<? super Map.Entry<String, String>> action) {
        this.vector.entrySet().forEach(action);
    }

    @Override
    public Spliterator<Map.Entry<String, String>> spliterator() {
        return this.vector.entrySet().spliterator();
    }
}
