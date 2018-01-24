package io.vertx.up.media.parse;

interface Info {
    String RESOLVER = "[ ZERO ] ( Resolver ) Select resolver {0} " +
            "for Content-Type {1} when request to {2}";

    String RESOLVER_CONFIG = "[ ZERO ] ( Resolver ) Select resolver from " +
            "annotation config \"{0}\" for Content-Type {1}";
}
