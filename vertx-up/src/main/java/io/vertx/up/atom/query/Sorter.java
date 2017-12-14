package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Sorter implements Serializable {
    /**
     * Field
     */
    private transient final List<String> field = new ArrayList<>();
    /**
     * Sort Mode
     */
    private transient final List<Boolean> asc = new ArrayList<>();

    public static Sorter create(final String field,
                                final Boolean asc) {
        return new Sorter(field, asc);
    }

    private Sorter(final String field,
                   final Boolean asc) {
        this.field.add(field);
        this.asc.add(asc);
    }

    public <T> JsonObject getSort(final Function<Boolean, T> function) {
        final JsonObject sort = new JsonObject();
        Fn.itList(this.field, (item, index) -> {
            // Extract value from asc
            final boolean mode = this.asc.get(index);
            // Extract result
            final T result = function.apply(mode);
            sort.put(item, result);
        });
        return sort;
    }

    public Sorter add(final String field,
                      final Boolean asc) {
        this.field.add(field);
        this.asc.add(asc);
        return this;
    }

    public Sorter clear() {
        this.field.clear();
        this.asc.clear();
        return this;
    }
}
