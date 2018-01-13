package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Pager;

class Pagination {
    /**
     * Pager building
     *
     * @param page
     * @param size
     * @return
     */
    static JsonObject toPager(
            final int page,
            final int size) {
        return Pager.create(page, size).toJson();
    }
}
