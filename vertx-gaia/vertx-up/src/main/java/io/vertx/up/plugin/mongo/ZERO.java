package io.vertx.up.plugin.mongo;

interface Info {

    String MERGE_INFO = "[ ZERO ] Merged {0} and {1}, sourceKey = {2}, targetKey = {3}";

    String FILTER_INFO = "[ ZERO ] Mongo collection = {0}, filter: {1}";

    String UPDATE_INFO = "[ ZERO ] Mongo collection = {0}, filter: {1}, data: {2}";

    String UPDATE_QUERY = "[ ZERO ] Mongo collection = {0}, query by: {1}, data: {2}";

    String UPDATE_FLOW = "[ ZERO ] Mongo update flow = {0}, filter: {1}, latest: {2}";

    String UPDATE_RESULT = "[ ZERO ] Mongo update result = {0}.";
}
