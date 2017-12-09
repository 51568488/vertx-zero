package io.vertx.up.kidd.outcome;

interface Info {

    String ERROR_NULL = "[ ZERO ] The class {0} is null, could not start Obstain<T>";

    String ERROR_HANDLER = "[ ZERO ] Zero system detect null Message<T> handler, " +
            "error occurs: handler = {0}, class = {1}";

    String ERROR_ENVELOP = "[ ZERO ] The result of Envelop is null, " +
            "it means that response is not ready, class = {0}";
}
