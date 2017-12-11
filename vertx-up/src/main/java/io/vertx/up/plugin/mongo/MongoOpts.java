package io.vertx.up.plugin.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;

/**
 * Build FindOptions for mongo database.
 */
public class MongoOpts {

    public static FindOptions desc(
            final String sortField,
            final Integer limit
    ) {
        return common(sortField, limit, false);
    }

    public static FindOptions asc(
            final String sortField,
            final Integer limit
    ) {
        return common(sortField, limit, true);
    }

    public static FindOptions common(
            final JsonObject sort,
            final Integer limit
    ) {
        final FindOptions options = new FindOptions();
        if (0 < limit) {
            options.setLimit(limit);
        }
        if (null != sort) {
            options.setSort(sort);
        }
        return options;
    }

    private static FindOptions common(
            final String sortField,
            final Integer limit,
            final boolean asc
    ) {
        final JsonObject sort = new JsonObject();
        sort.put(sortField, asc ? 1 : -1);
        return common(sort, limit);
    }
}
