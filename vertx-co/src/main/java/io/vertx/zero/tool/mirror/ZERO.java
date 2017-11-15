package io.vertx.zero.tool.mirror;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Storage {
    /**
     * Singletons
     */
    ConcurrentMap<String, Object> SINGLETON = new ConcurrentHashMap<>();
    /**
     * Class cached.
     */
    ConcurrentMap<String, Class<?>> CLASSES = new ConcurrentHashMap<>();
}
