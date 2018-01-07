package io.vertx.up.web.origin;

import java.util.Set;

/**
 * Package scanner
 */
public interface Inquirer<R> {

    R scan(Set<Class<?>> clazzes);
}
