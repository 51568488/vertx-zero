package io.vertx.zero.ke.equip;

import io.vertx.zero.ke.Visitor;

import java.util.concurrent.ConcurrentMap;

/**
 * @param <T>
 * @author Lang
 */
public interface ServerVisitor<T>
        extends Visitor<ConcurrentMap<Integer, T>> {

    String YKEY_TYPE = "type";

    String YKEY_CONFIG = "config";
}
