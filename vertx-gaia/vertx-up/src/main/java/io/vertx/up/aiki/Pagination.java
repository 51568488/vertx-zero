package io.vertx.up.aiki;

import io.vertx.up.atom.query.Pager;
import io.vertx.up.atom.query.Sorter;

class Pagination {

    static Pager toPager(
            final int page,
            final int size) {
        return Pager.create(page, size);
    }

    static Sorter toSorter(
            final String field,
            final Integer mode
    ) {
        return Sorter.create(field, mode > 0);
    }

    static Sorter toSorter(
            final String field,
            final boolean asc
    ) {
        return Sorter.create(field, asc);
    }
}
