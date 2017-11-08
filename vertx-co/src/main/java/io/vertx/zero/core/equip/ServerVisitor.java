package io.vertx.zero.core.equip;

import io.vertx.zero.core.Visitor;

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
